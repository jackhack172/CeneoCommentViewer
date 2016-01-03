package hd.ceneoCommentViewer.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import hd.ceneoCommentViewer.model.Comment;

public class Parser {

	public static List<Comment> parseCommentsFromCeneo(List<Document> commentsPages) throws ParseException {
		List<Comment> comments = new ArrayList<>();

		for (Document doc : commentsPages) {
			Elements commentsElements = doc.getElementsByClass("product-review");
			for (Element commentElement : commentsElements) {
				Comment comment = new Comment();
				comment.setAuthor(commentElement.getElementsByClass("product-reviewer").text());
				comment.setSummary(commentElement.getElementsByClass("product-review-body").text());
				SimpleDateFormat parserSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				comment.setDate(parserSDF.parse(commentElement.select(".review-time time").first().attr("datetime")));
				comments.add(comment);
			}
		}
		return comments;
	}
	
	public static List<Comment> parseCommentsFromMorele(List<Document> commentsPages) throws ParseException {
		List<Comment> comments = new ArrayList<>();
		
//		for (Document doc : commentsPages) {
//			Elements commentsElements = doc.getElementsByClass("product-review");
//			for (Element commentElement : commentsElements) {
//				Comment comment = new Comment();
//				comment.setAuthor(commentElement.getElementsByClass("product-reviewer").text());
//				comment.setSummary(commentElement.getElementsByClass("product-review-body").text());
//				SimpleDateFormat parserSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				comment.setDate(parserSDF.parse(commentElement.select(".review-time time").first().attr("datetime")));
//				comments.add(comment);
//			}
//		}
		return comments;
	}

}
