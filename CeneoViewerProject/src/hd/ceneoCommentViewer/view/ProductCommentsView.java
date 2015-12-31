package hd.ceneoCommentViewer.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import hd.ceneoCommentViewer.model.Comment;
import hd.ceneoCommentViewer.model.Product;
import hd.ceneoCommentViewer.services.CommentService;

@ManagedBean(name = "productCommentsView")
@ViewScoped
public class ProductCommentsView implements Serializable {

	private List<Comment> comments;

	private Product product;

	@ManagedProperty("#{commentService}")
	private CommentService commentService;

	public void initDB() {
		commentService.downloadComments();
	}

	@PostConstruct
	public void init() {
		initDB();

		comments = commentService.getAllComments();
		
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public CommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
}
