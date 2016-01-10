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

/**
 * 
 * Serwis odczytujący i zapisujący dane do bazy danych dotyczące komentarzy.
 *
 */
@ManagedBean(name = "commentService")
@ApplicationScoped
public class CommentService implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 *  Obiekt służący do wymiany informacji z bazą danych.
	 */
	private CommentFacade commentFacade;

	/**
	 * Metoda tworzy i zwraca obiekt służący do wymiany informacji z bazą.
	 * @return Obiekt służący do wymiany informacji o komentarzu.
	 */
	public CommentFacade getCommentFacade() {
		if (commentFacade == null) {
			commentFacade = new CommentFacade();
		}
		return commentFacade;
	}

	/**
	 * Metoda zapisuje komentarz do bazy danych.
	 * @param comment Zapisywany komentarz.
	 * @return Zwraca true jeśli komentarz nie istniał w bazie.
	 */
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

	/**
	 * Metoda uaktualnia dane o komentarzu.
	 * @param comment Uaktualniany komentarz
	 */
	public void updateComment(Comment comment) {
		try {
			getCommentFacade().updateComment(comment);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metoda usuwa wszytkie komentarze
	 */
	public void deleteAllComments() {
		List<Comment> comments = getCommentFacade().listAll();
		for (Comment comment : comments) {
			getCommentFacade().deleteComment(comment);
		}
	}

	/**
	 * Metoda zwraca wszystkie komentarze
	 * @return Lista komentarzy
	 */
	public List<Comment> getAllComments() {
		return getCommentFacade().listAll();
	}
}
