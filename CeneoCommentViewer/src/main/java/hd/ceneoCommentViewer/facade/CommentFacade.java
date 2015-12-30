package hd.ceneoCommentViewer.facade;

import hd.ceneoCommentViewer.dao.CommentDAO;
import hd.ceneoCommentViewer.model.Comment;

import java.io.Serializable;

public class CommentFacade implements Serializable {

	private static final long serialVersionUID = 5241679152254395633L;

	private CommentDAO commentDAO = new CommentDAO();

	public void createComment(Comment comment) {
		commentDAO.beginTransaction();
		commentDAO.save(comment);
		commentDAO.commitAndCloseTransaction();
	}
}
