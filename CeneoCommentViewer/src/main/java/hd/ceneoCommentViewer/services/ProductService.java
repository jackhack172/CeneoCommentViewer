package hd.ceneoCommentViewer.services;

import hd.ceneoCommentViewer.facade.ProductFacade;
import hd.ceneoCommentViewer.model.Product;

public class ProductService {

	private ProductFacade productFacade;
	
	public ProductFacade getProductFacade(){
		if (productFacade == null) {
			productFacade = new ProductFacade();
		}
		return productFacade;
	}
	
	public void createProduct(Product product) {
		try {
			getProductFacade().createProduct(product);
			System.out.println("Dodano Produkt "
					+ product.getModel());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
