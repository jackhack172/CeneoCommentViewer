package hd.ceneoCommentViewer.dao;

import hd.ceneoCommentViewer.model.Comment;

public class CommentDAO extends GenericDAO<Comment>{

	private static final long serialVersionUID = 71237241059008858L;

	public CommentDAO() {
		super(Comment.class);
	}
	
}
