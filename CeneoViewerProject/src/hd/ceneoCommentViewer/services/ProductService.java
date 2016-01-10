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

/**
 * 
 * Serwis odczytujący i zapisujący dane do bazy danych dotyczące produktów.
 *
 */
@ManagedBean(name = "productService")
@ApplicationScoped
public class ProductService implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 *  Obiekt służący do wymiany informacji z bazą danych.
	 */
	private ProductFacade productFacade;

	/**
	 * Metoda tworzy i zwraca obiekt służący do wymiany informacji z bazą.
	 * @return Obiekt służący do wymiany informacji o produkcie.
	 */
	public ProductFacade getProductFacade() {
		if (productFacade == null) {
			productFacade = new ProductFacade();
		}
		return productFacade;
	}

	/**
	 * Metoda zapisuje produkt do bazy danych.
	 * @param product Zapisywany produkt.
	 * @return Zwraca true jeśli produkt nie istniał w bazie.
	 */
	public boolean createProduct(Product product) {
		try {
			if (getProductFacade().findProduct(product.getId()) == null) {
				getProductFacade().createProduct(product);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			getProductFacade().closeTransaction();
		}
		return false;
	}

	/**
	 * Metoda uaktualnia dane o produkcie.
	 * @param product Uaktualniany produkt
	 */
	public void updateProduct(Product product) {
		try {
			getProductFacade().updateProduct(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metoda usuwa wszytkie produkty
	 */
	public void deleteAllProducts() {
		List<Product> products = getProductFacade().listAll();
		for (Product product : products) {
			getProductFacade().deleteProdukt(product);
		}
	}

	/**
	 * Metoda zwraca wszystkie produkty
	 * @return Lista produktów
	 */
	public List<Product> getAllProducts() {
		return getProductFacade().listAll();
	}
}
