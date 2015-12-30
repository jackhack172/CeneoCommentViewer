package hd.ceneoCommentViewer.services;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import hd.ceneoCommentViewer.facade.CommentFacade;
import hd.ceneoCommentViewer.model.Comment;

@ManagedBean(name = "commentService")
@ApplicationScoped
public class CommentService implements Serializable{

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
	
	public List<Comment> getAllComments(){
		return getCommentFacade().listAll();
	}
}
