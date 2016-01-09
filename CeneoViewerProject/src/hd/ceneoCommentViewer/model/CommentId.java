package hd.ceneoCommentViewer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CommentId implements Serializable {
	
	private static final long serialVersionUID = 4310752384832966176L;

	private Integer commentId;
	
	private String portalName;
	
	public CommentId() {

	}
	
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
