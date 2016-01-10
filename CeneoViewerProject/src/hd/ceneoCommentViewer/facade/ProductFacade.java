package hd.ceneoCommentViewer.facade;

import java.io.Serializable;
import java.util.List;

import hd.ceneoCommentViewer.dao.ProductDAO;
import hd.ceneoCommentViewer.model.Product;

/**
 * 
 * Klasa służąca do wymiany informacji bazą danych 
 * związanymi z produktami.
 *
 */
public class ProductFacade implements Serializable {

	private static final long serialVersionUID = -1864623953443810589L;

	/**
	 * Obiekt DAO produktu.
	 */
	private ProductDAO productDAO = new ProductDAO();

	/**
	 * Metoda tworzy rekord z informacjami o produkcie.
	 * @param product Produkt.
	 */
	public void createProduct(Product product) {
		productDAO.beginTransaction();
		productDAO.save(product);
		productDAO.commitAndCloseTransaction();
	}

	/**
	 * Metoda uaktualnia rekord.
	 * @param product Uaktualniany produkt.
	 */
	public void updateProduct(Product product) {
		productDAO.beginTransaction();
		productDAO.update(product);
		productDAO.commitAndCloseTransaction();
	}

	/**
	 * Metoda znajduje produkt w bazie danych.
	 * @param productId Id produktu.
	 * @return Znaleziony produkt.
	 */
	public Product findProduct(int productId) {
		productDAO.beginTransaction();
		Product product = productDAO.find(productId);
		productDAO.closeTransaction();
		return product;
	}

	/**
	 * Metoda znajduje wszystkie produkty.
	 * @return Lista znalezionych produktów.
	 */
	public List<Product> listAll() {
		productDAO.beginTransaction();
		List<Product> result = productDAO.findAll();
		productDAO.closeTransaction();
		return result;
	}

	/**
	 * Metoda usuwa produkt.
	 * @param product Usuwany produkt.
	 */
	public void deleteProdukt(Product product) {
		productDAO.beginTransaction();
		productDAO.delete(product);
		productDAO.commitAndCloseTransaction();
	}

	/**
	 * Metoda zamyka transakcje
	 */
	public void closeTransaction() {
		productDAO.beginTransaction();
	}
}
