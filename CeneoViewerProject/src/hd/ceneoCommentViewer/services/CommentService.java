package hd.ceneoCommentViewer.services;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import hd.ceneoCommentViewer.facade.CommentFacade;
import hd.ceneoCommentViewer.model.Comment;

@ManagedBean(name = "commentService")
@ApplicationScoped
public class CommentService implements Serializable {

	private CommentFacade commentFacade;

	private Integer progress = 0;

	public CommentFacade getCommentFacade() {
		if (commentFacade == null) {
			commentFacade = new CommentFacade();
		}
		return commentFacade;
	}

	public void createComment(Comment comment) {
		try {
			getCommentFacade().createComment(comment);
			System.out.println("Dodano komentarz " + comment.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Comment> getAllComments() {
		return getCommentFacade().listAll();
	}

	// temporary
	public List<Comment> downloadComments(Integer productId) {
		int COMMENTS_PER_PAGE = 10;

		List<Comment> comments = new ArrayList<>();

		Document doc = null;
		String mainLink = "http://www.ceneo.pl/";
		String commentsFirstPageLinkPart = "#tab=reviews_scroll";
		String commentsNextPagesLinkPart = "/opinie-";
		// String productCode = "35379075";
		Integer numberOfComments = 0;

		try {
			doc = Jsoup.connect(mainLink + productId + commentsFirstPageLinkPart).get();
			Elements commentsElements = doc.getElementsByClass("product-review");
			for (Element commentElement : commentsElements) {
				Comment comment = new Comment();
				comment.setAuthor(commentElement.getElementsByClass("product-reviewer").text());
				comment.setSummary(commentElement.getElementsByClass("product-review-body").text());
				SimpleDateFormat parserSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				comment.setDate(parserSDF.parse(commentElement.select(".review-time time").first().attr("datetime")));
				createComment(comment);
				comments.add(comment);
			}

			Element commentsPaginator = doc.select("span[itemprop=reviewCount]").first();
			numberOfComments = Integer.parseInt(commentsPaginator.text());
			Integer commentsPages = (int) Math.ceil(numberOfComments / (double) COMMENTS_PER_PAGE);

			for (int i = 2; i <= commentsPages; i++) {
				doc = Jsoup.connect(mainLink + productId + commentsNextPagesLinkPart + i).get();
				commentsElements = doc.getElementsByClass("product-review");
				for (Element commentElement : commentsElements) {
					Comment comment = new Comment();
					comment.setAuthor(commentElement.getElementsByClass("product-reviewer").text());
					comment.setSummary(commentElement.getElementsByClass("product-review-body").text());
					SimpleDateFormat parserSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					comment.setDate(
							parserSDF.parse(commentElement.select(".review-time time").first().attr("datetime")));
					createComment(comment);
					comments.add(comment);
					progress = i * 100 / commentsPages;
				}
			}
		} catch (HttpStatusException e){
			e.printStackTrace();
			System.out.println("Brak produktu");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return comments;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}
}
