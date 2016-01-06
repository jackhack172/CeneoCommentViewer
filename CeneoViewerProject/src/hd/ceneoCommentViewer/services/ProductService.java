package hd.ceneoCommentViewer.services;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import hd.ceneoCommentViewer.facade.ProductFacade;
import hd.ceneoCommentViewer.model.Comment;
import hd.ceneoCommentViewer.model.Product;

@ManagedBean(name = "productService")
@ApplicationScoped
public class ProductService implements Serializable {

	private ProductFacade productFacade;

	public ProductFacade getProductFacade() {
		if (productFacade == null) {
			productFacade = new ProductFacade();
		}
		return productFacade;
	}

	public void createProduct(Product product) {
		try {
			getProductFacade().createProduct(product);
			System.out.println("Dodano Produkt " + product.getModel());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Product> getAllProducts() {
		return getProductFacade().listAll();
	}
}
