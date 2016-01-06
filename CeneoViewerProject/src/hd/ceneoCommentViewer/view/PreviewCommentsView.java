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

@ManagedBean(name = "previewCommentsView")
@ViewScoped
public class PreviewCommentsView implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Comment> comments;

	private List<Product> products;

	private Product currentProduct;

	private Comment selectedComment;

	@ManagedProperty("#{commentService}")
	private CommentService commentService;

	@ManagedProperty("#{productService}")
	private ProductService productService;

	@PostConstruct
	public void init() {
		products = productService.getAllProducts();
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product getCurrentProduct() {
		return currentProduct;
	}

	public void setCurrentProduct(Product currentProduct) {
		this.currentProduct = currentProduct;
	}

	public Comment getSelectedComment() {
		return selectedComment;
	}

	public void setSelectedComment(Comment selectedComment) {
		this.selectedComment = selectedComment;
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

}
