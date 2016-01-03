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
}
