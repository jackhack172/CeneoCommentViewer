package hd.ceneoCommentViewer.facade;

import java.io.Serializable;
import java.util.List;

import hd.ceneoCommentViewer.dao.CommentDAO;
import hd.ceneoCommentViewer.model.Comment;
import hd.ceneoCommentViewer.model.CommentId;
import hd.ceneoCommentViewer.model.Product;

public class CommentFacade implements Serializable {

	private static final long serialVersionUID = 5241679152254395633L;

	private CommentDAO commentDAO = new CommentDAO();

	public void createComment(Comment comment) {
		commentDAO.beginTransaction();
		commentDAO.save(comment);
		commentDAO.commitAndCloseTransaction();
	}

	public void updateComment(Comment comment) {
		commentDAO.beginTransaction();
		commentDAO.update(comment);
		commentDAO.commitAndCloseTransaction();
	}

	public Comment findComment(CommentId commentId) {
		commentDAO.beginTransaction();
		Comment comment = commentDAO.findbyCommentId(commentId);
		commentDAO.closeTransaction();
		return comment;
	}

	public List<Comment> listAll() {
		commentDAO.beginTransaction();
		List<Comment> result = commentDAO.findAll();
		commentDAO.closeTransaction();
		return result;
	}

	public void deleteComment(Comment comment) {
		commentDAO.beginTransaction();
		commentDAO.delete(comment);
		commentDAO.commitAndCloseTransaction();
	}

	public void closeTransaction() {
		commentDAO.beginTransaction();
	}
}
