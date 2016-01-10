package hd.ceneoCommentViewer.dao;

import hd.ceneoCommentViewer.model.Comment;

/**
 * 
 * Obiekt DAO komentarza
 *
 */
public class CommentDAO extends GenericDAO<Comment>{

	private static final long serialVersionUID = 71237241059008858L;

	/**
	 * Konstruktor
	 */
	public CommentDAO() {
		super(Comment.class);
	}
}
