package hd.ceneoCommentViewer.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import hd.ceneoCommentViewer.model.Comment;
import hd.ceneoCommentViewer.model.Product;

public class Parser {

	public static Product parseProductFromCeneo(Document productPage, Integer productId) {
		Product product = null;

		if (productPage != null && productId != null) {
			Element productNameElement = productPage.getElementsByClass("product-name").first();
			String productFullName = productNameElement.text();
			Element categoryElements = productPage.getElementsByClass("breadcrumb").last()
					.select("span[itemprop=title]").first();
			String categoryName = categoryElements.text();

			product = new Product();
			product.setId(productId);
			product.setBrand(productFullName.substring(0, productFullName.indexOf(' ')));
			product.setModel(productFullName.substring(productFullName.indexOf(' '), productFullName.length()));
			product.setType(categoryName);
		}else{
			System.out.println();
		}

		return product;
	}

	public static List<Comment> parseCommentsFromCeneo(List<Document> commentsPages) throws ParseException {
		List<Comment> comments = new ArrayList<>();

		for (Document doc : commentsPages) {
			Elements commentsElements = doc.getElementsByClass("product-review");
			for (Element commentElement : commentsElements) {
				Comment comment = new Comment();
				String id = commentElement.getElementsByClass("js_product-review-comments").attr("id");
				Integer commentId = Integer.parseInt(id.substring(id.lastIndexOf("-") + 1, id.length()));
				comment.setId(commentId);

				comment.setAuthor(commentElement.getElementsByClass("product-reviewer").text());
				comment.setSummary(commentElement.getElementsByClass("product-review-body").text());
				SimpleDateFormat parserSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				comment.setDate(parserSDF.parse(commentElement.select(".review-time time").first().attr("datetime")));
				comment.setRecommended(
						commentElement.getElementsByClass("product-recommended").text().equals("Polecam"));

				String[] starsText = commentElement.getElementsByClass("review-score-count").text().split("/");
				comment.setStars(Double.parseDouble(starsText[0].replace(",", ".")));
				comment.setNumberOfOpinions(
						Integer.parseInt(commentElement.getElementById("votes-" + commentId).text()));
				comment.setNumberOfPositiveOpinions(
						Integer.parseInt(commentElement.getElementById("votes-yes-" + commentId).text()));

				List<String> advantages = new ArrayList<>();
				Elements advantagesElements = commentElement.select(".pros-cell li");
				for (Element advantageElement : advantagesElements) {
					advantages.add(advantageElement.text());
				}
				comment.setAdvantages(advantages);

				List<String> disadvantages = new ArrayList<>();
				Elements disadvantagesElements = commentElement.select(".cons-cell li");
				for (Element disadvantageElement : disadvantagesElements) {
					disadvantages.add(disadvantageElement.text());
				}
				comment.setDisadvantages(disadvantages);

				comments.add(comment);
			}
		}
		return comments;
	}

	public static List<Comment> parseCommentsFromMorele(List<Document> commentsPages) throws ParseException {
		List<Comment> comments = new ArrayList<>();

		// for (Document doc : commentsPages) {
		// Elements commentsElements = doc.getElementsByClass("product-review");
		// for (Element commentElement : commentsElements) {
		// Comment comment = new Comment();
		// comment.setAuthor(commentElement.getElementsByClass("product-reviewer").text());
		// comment.setSummary(commentElement.getElementsByClass("product-review-body").text());
		// SimpleDateFormat parserSDF = new SimpleDateFormat("yyyy-MM-dd
		// HH:mm:ss");
		// comment.setDate(parserSDF.parse(commentElement.select(".review-time
		// time").first().attr("datetime")));
		// comments.add(comment);
		// }
		// }
		return comments;
	}

}
