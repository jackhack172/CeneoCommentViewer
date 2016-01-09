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

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import hd.ceneoCommentViewer.facade.CommentFacade;
import hd.ceneoCommentViewer.model.Comment;
import hd.ceneoCommentViewer.model.Product;

@ManagedBean(name = "commentService")
@ApplicationScoped
public class CommentService implements Serializable {

	private static final long serialVersionUID = 1L;

	private CommentFacade commentFacade;

	public CommentFacade getCommentFacade() {
		if (commentFacade == null) {
			commentFacade = new CommentFacade();
		}
		return commentFacade;
	}

	public boolean createComment(Comment comment) {
		try {
			if (getCommentFacade().findComment(comment.getId()) == null) {
				getCommentFacade().createComment(comment);
				return true;
			} else {
				getCommentFacade().updateComment(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
			getCommentFacade().closeTransaction();
		}
		return false;
	}

	public void updateComment(Comment comment) {
		try {
			getCommentFacade().updateComment(comment);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteAllComments() {
		List<Comment> comments = getCommentFacade().listAll();
		for (Comment comment : comments) {
			getCommentFacade().deleteComment(comment);
		}
	}

	public List<Comment> getAllComments() {
		return getCommentFacade().listAll();
	}
}
