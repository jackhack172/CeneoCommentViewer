package hd.ceneoCommentViewer.facade;

import java.io.Serializable;
import java.util.List;

import hd.ceneoCommentViewer.dao.CommentDAO;
import hd.ceneoCommentViewer.model.Comment;
import hd.ceneoCommentViewer.model.CommentId;
import hd.ceneoCommentViewer.model.Product;

/**
 * 
 * Klasa służąca do wymiany informacji z bazą danych 
 * związanymi z komentarzami.
 *
 */
public class CommentFacade implements Serializable {

	private static final long serialVersionUID = 5241679152254395633L;

	/**
	 * Obiekt DAO komentarza.
	 */
	private CommentDAO commentDAO = new CommentDAO();

	/**
	 * Metoda tworzy rekord z informacjami o komentarzu
	 * @param comment Komentarz.
	 */
	public void createComment(Comment comment) {
		commentDAO.beginTransaction();
		commentDAO.save(comment);
		commentDAO.commitAndCloseTransaction();
	}

	/**
	 * Metoda uaktualnia rekord.
	 * @param comment Uaktualniany komentarz.
	 */
	public void updateComment(Comment comment) {
		commentDAO.beginTransaction();
		commentDAO.update(comment);
		commentDAO.commitAndCloseTransaction();
	}

	/**
	 * Metoda znajduje komentarz w bazie danych.
	 * @param commentId Id komentarza.
	 * @return Znaleziony komentarz.
	 */
	public Comment findComment(CommentId commentId) {
		commentDAO.beginTransaction();
		Comment comment = commentDAO.findbyCommentId(commentId);
		commentDAO.closeTransaction();
		return comment;
	}

	/**
	 * Metoda znajduje wszystkie komentarze.
	 * @return Lista znalezionych komentarzy.
	 */
	public List<Comment> listAll() {
		commentDAO.beginTransaction();
		List<Comment> result = commentDAO.findAll();
		commentDAO.closeTransaction();
		return result;
	}

	/**
	 * Metoda usuwa komentarz.
	 * @param comment Usuwany komentarz.
	 */
	public void deleteComment(Comment comment) {
		commentDAO.beginTransaction();
		commentDAO.delete(comment);
		commentDAO.commitAndCloseTransaction();
	}

	/**
	 * Metoda zamyka transakcje
	 */
	public void closeTransaction() {
		commentDAO.beginTransaction();
	}
}
