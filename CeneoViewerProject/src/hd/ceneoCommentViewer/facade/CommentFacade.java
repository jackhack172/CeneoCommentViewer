package hd.ceneoCommentViewer.facade;

import java.io.Serializable;
import java.util.List;

import hd.ceneoCommentViewer.dao.CommentDAO;
import hd.ceneoCommentViewer.model.Comment;

public class CommentFacade implements Serializable {

	private static final long serialVersionUID = 5241679152254395633L;

	private CommentDAO commentDAO = new CommentDAO();

	public void createComment(Comment comment) {
		commentDAO.beginTransaction();
		commentDAO.save(comment);
		commentDAO.commitAndCloseTransaction();
	}

	public List<Comment> listAll() {
		commentDAO.beginTransaction();
		List<Comment> result = commentDAO.findAll();
		commentDAO.closeTransaction();
		return result;
	}
}
