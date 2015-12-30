package hd.ceneoCommentViewer.services;

import hd.ceneoCommentViewer.facade.CommentFacade;
import hd.ceneoCommentViewer.model.Comment;

public class CommentService {

	CommentFacade commentFacade;

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
}
