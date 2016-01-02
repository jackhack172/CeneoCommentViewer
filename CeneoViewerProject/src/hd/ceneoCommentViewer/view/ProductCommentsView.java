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
import hd.ceneoCommentViewer.services.ProductService;

@ManagedBean(name = "productCommentsView")
@ViewScoped
public class ProductCommentsView implements Serializable {

	private List<Comment> comments;

	private Product product;

	private Integer productId;

	@ManagedProperty("#{commentService}")
	private CommentService commentService;

	@ManagedProperty("#{productService}")
	private ProductService productService;

	public void initDB() {
		if (productId != null) {
			product = productService.downloadProduct(productId);
			comments = commentService.downloadComments(productId);
		}
	}

	@PostConstruct
	public void init() {
		// initDB();
		//
		// comments = commentService.getAllComments();

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

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public void onComplete() {
		commentService.setProgress(0);
	}
}
