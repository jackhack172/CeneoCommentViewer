package hd.ceneoCommentViewer.facade;

import hd.ceneoCommentViewer.dao.ProductDAO;
import hd.ceneoCommentViewer.model.Product;

import java.io.Serializable;
import java.util.List;

public class ProductFacade implements Serializable {

	private static final long serialVersionUID = -1864623953443810589L;

	private ProductDAO productDAO = new ProductDAO();

	public void createProduct(Product product) {
		productDAO.beginTransaction();
		productDAO.save(product);
		productDAO.commitAndCloseTransaction();
	}

	public void updateProduct(Product product) {
		productDAO.beginTransaction();
		productDAO.update(product);
		productDAO.commitAndCloseTransaction();
	}

	public Product findProduct(int productId) {
		return null;
	}

	public List<Product> listAll() {
		productDAO.beginTransaction();
		List<Product> result = productDAO.findAll();
		productDAO.closeTransaction();
		return result;
	}

	public void deleteProdukt(Product product) {
		productDAO.beginTransaction();
		productDAO.delete(product);
		productDAO.commitAndCloseTransaction();
	}
}
