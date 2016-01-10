package hd.ceneoCommentViewer.view;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.dom4j.DocumentException;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import hd.ceneoCommentViewer.model.Comment;
import hd.ceneoCommentViewer.model.Product;
import hd.ceneoCommentViewer.services.CommentService;
import hd.ceneoCommentViewer.services.DownloadService;
import hd.ceneoCommentViewer.services.ProductService;
import hd.ceneoCommentViewer.utils.Parser;

/**
 * 
 * Klasa zawierająca dane i metody dla widoku.
 *
 */
@ManagedBean(name = "productCommentsView")
@ViewScoped
public class ProductCommentsView implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Aktualny stan widoku.
	 */
	private ViewState viewState = ViewState.BLANK;

	/**
	 * Pobrane komentarze.
	 */
	private List<Comment> comments;

	/**
	 * Pobrany produkt.
	 */
	private Product product;

	/**
	 * Id pobranego produktu.
	 */
	private Integer productId;

	/**
	 * Aktualnie przeglądane komentarze.
	 */
	private List<Comment> previewComments;

	/**
	 * Przeglądane produkty.
	 */
	private List<Product> previewProducts;

	/**
	 * Produkt wybrany do objerzenia.
	 */
	private Product selectedPreviewProduct;

	/**
	 * Komentarz wybrany do objerzenia.
	 */
	private Comment selectedPreviewComment;

	/**
	 * Serwis zarządzjący komentarzami.
	 */
	@ManagedProperty("#{commentService}")
	private CommentService commentService;

	/**
	 * Serwis zarządzjący produktami.
	 */
	@ManagedProperty("#{productService}")
	private ProductService productService;

	/**
	 * Serwis pobierający strony z www.ceneo.pl.
	 */
	@ManagedProperty("#{ceneoDownloadService}")
	private DownloadService ceneoDownloadService;

	/**
	 * Serwis pobierający strony z www.morele.net.
	 */
	@ManagedProperty("#{moreleDownloadService}")
	private DownloadService moreleDownloadService;

	/**
	 * Metoda czyszcząca bazę danych.
	 */
	public void erase() {
		List<Product> products = productService.getAllProducts();
		for (Product product : products) {
			product.getComments().clear();
			productService.updateProduct(product);
		}

		productService.deleteAllProducts();
		previewProducts = productService.getAllProducts();
		selectedPreviewProduct = null;

		commentService.deleteAllComments();
		previewComments = null;

		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Baza zostala wyczyszczona."));
	}

	/**
	 * Metoda wykonująca process ETL.
	 */
	public void etl() {
		if (productId != null) {
			try {
				extract();
				transform();
				load();
			} catch (IOException e) {
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "Brak produktu o podanym id"));
			}
		}
	}

	/**
	 * Metoda wykonująca process Extract.
	 * @throws IOException Zwraca błąd jeżeli produkt nie istnieje.
	 */
	public void extract() throws IOException {
		if (productId != null) {
			viewState = ViewState.EXTRACT;
			ceneoDownloadService.downloadProductPage(productId);
			product = Parser.parseProductFromCeneo(ceneoDownloadService.getProductPage(), productId);
			ceneoDownloadService.downloadCommentsPages(productId);
			moreleDownloadService.downloadCommentsPages(productId);

			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
							"Liczba wczytanych stron: " + (ceneoDownloadService.getCommentsPages().size()
									+ moreleDownloadService.getCommentsPages().size() + 1)));

		}
	}

	/**
	 * Metoda wykonująca process Transform.
	 */
	public void transform() {
		viewState = ViewState.TRANSFORM;
		try {
			comments = Parser.parseCommentsFromCeneo(ceneoDownloadService.getCommentsPages());
			if (!moreleDownloadService.getCommentsPages().isEmpty()) {
				comments.addAll(Parser.parseCommentsFromMorele(moreleDownloadService.getCommentsPages()));
			}

			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
					"Liczba odczytanych komentarzy: " + comments.size()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metoda wykonująca process Load.
	 */
	public void load() {
		int recordsNumber = 0;
		viewState = ViewState.LOAD;
		product.setComments(comments);
		for (Comment comment : comments) {
			if (commentService.createComment(comment)) {
				recordsNumber++;
			}
		}

		if (productService.createProduct(product)) {
			recordsNumber++;
		}
		previewProducts = productService.getAllProducts();
		viewState = ViewState.BLANK;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
				"Liczba rekordów zapisanych do bazy: " + recordsNumber));
	}

	/**
	 * Metoda pobiera z bazy danych aktualną listę produktów.
	 */
	@PostConstruct
	public void init() {
		previewProducts = productService.getAllProducts();
	}

	/**
	 * Metoda pobierajaca komentarze dla wybranego produktu.
	 * @param event Zdarzenie wybrania produktu.
	 */
	public void onRowSelect(SelectEvent event) {
		selectedPreviewProduct = (Product) event.getObject();
		previewComments = selectedPreviewProduct.getComments();
	}

	/**
	 * Metoda usuwajaca zaznaczenie produktu.
	 * @param event Zdarzenie odznaczenia produktu.
	 */
	public void onRowUnselect(UnselectEvent event) {
		selectedPreviewProduct = null;
		previewComments = null;
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
		ceneoDownloadService.setProgress(0);
	}

	public DownloadService getCeneoDownloadService() {
		return ceneoDownloadService;
	}

	public void setCeneoDownloadService(DownloadService ceneoDownloadService) {
		this.ceneoDownloadService = ceneoDownloadService;
	}

	public DownloadService getMoreleDownloadService() {
		return moreleDownloadService;
	}

	public void setMoreleDownloadService(DownloadService moreleDownloadService) {
		this.moreleDownloadService = moreleDownloadService;
	}

	public ViewState getViewState() {
		return viewState;
	}

	public void setViewState(ViewState viewState) {
		this.viewState = viewState;
	}

	public int getProgress() {
		if (isBlankViewState()) {
			return 0;
		}
		if (isExtractViewState()) {
			return ceneoDownloadService.getProgress();
		}
		return 0;
	}

	public boolean isBlankViewState() {
		return viewState == ViewState.BLANK;
	}

	public boolean isExtractViewState() {
		return viewState == ViewState.EXTRACT;
	}

	public boolean isTransformViewState() {
		return viewState == ViewState.TRANSFORM;
	}

	public boolean isLoadViewState() {
		return viewState == ViewState.LOAD;
	}

	public boolean isTDisabled() {
		return !isExtractViewState();
	}

	public boolean isLDisabled() {
		return !isTransformViewState();
	}

	public List<Comment> getPreviewComments() {
		return previewComments;
	}

	public void setPreviewComments(List<Comment> previewComments) {
		this.previewComments = previewComments;
	}

	public List<Product> getPreviewProducts() {
		return previewProducts;
	}

	public void setPreviewProducts(List<Product> previewProducts) {
		this.previewProducts = previewProducts;
	}

	public Product getSelectedPreviewProduct() {
		return selectedPreviewProduct;
	}

	public void setSelectedPreviewProduct(Product selectedPreviewProduct) {
		this.selectedPreviewProduct = selectedPreviewProduct;
	}

	public Comment getSelectedPreviewComment() {
		return selectedPreviewComment;
	}

	public void setSelectedPreviewComment(Comment selectedPreviewComment) {
		this.selectedPreviewComment = selectedPreviewComment;
	}

	/**
	 * 
	 * Stany w jakich może byc widok.
	 *
	 */
	public enum ViewState {
		BLANK, EXTRACT, TRANSFORM, LOAD;
	}
}
