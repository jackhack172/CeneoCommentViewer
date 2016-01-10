package hd.ceneoCommentViewer.dao;

import hd.ceneoCommentViewer.model.Product;

/**
 * 
 * Obiekt DAO komentarza
 * 
 */
public class ProductDAO extends GenericDAO<Product>{

	private static final long serialVersionUID = 44196245361468412L;

	/**
	 * Konstruktor
	 */
	public ProductDAO() {
		super(Product.class);
	}

}
