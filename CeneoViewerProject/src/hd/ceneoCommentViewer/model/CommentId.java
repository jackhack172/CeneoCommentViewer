package hd.ceneoCommentViewer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * Klasa reprezentujaca klucz podstawowy encji komentarz.
 *
 */
@Embeddable
public class CommentId implements Serializable {
	
	private static final long serialVersionUID = 4310752384832966176L;

	/**
	 * Id komentarza.
	 */
	private Integer commentId;
	
	/**
	 * Nazwa portalu z którego pochodzi komentarz.
	 */
	private String portalName;
	
	/**
	 * Konstruktor.
	 */
	public CommentId() {
	}
	
	/**
	 * Konstruktor.
	 * @param commentId Id komentarza.
	 * @param portalName Nazwa portalu z którego pochodzi komentarz.
	 */
	public CommentId(Integer commentId, String portalName){
		this.commentId = commentId;
		this.portalName = portalName;
	}

	public Integer getCommentId() {
		return commentId;
	}
	
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	
	public String getPortalName() {
		return portalName;
	}
	
	public void setPortalName(String portalName) {
		this.portalName = portalName;
	}
}
