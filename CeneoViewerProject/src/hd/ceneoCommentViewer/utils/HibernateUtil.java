package hd.ceneoCommentViewer.utils;

import hd.ceneoCommentViewer.facade.CommentFacade;
import hd.ceneoCommentViewer.facade.ProductFacade;
import hd.ceneoCommentViewer.model.Comment;
import hd.ceneoCommentViewer.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	static {
		try {
			// Create the SessionFactory
			Configuration configuration = new Configuration();
			configuration.configure("hibernate-annotation.cfg.xml");

			// configuration.setProperty("hibernate.connection.driver_class",
			// "com.mysql.jdbc.Driver");
			// configuration.setProperty("hibernate.connection.url",
			// "jdbc:mysql://localhost:3306/ceneo");
			// configuration.setProperty("hibernate.connection.username",
			// "ceneo");
			// configuration.setProperty("hibernate.connection.password",
			// "ceneo");
			// configuration.setProperty("hibernate.show_sql", "true");
			// configuration.setProperty("hbm2ddl.auto", "create");

			// configuration.addAnnotatedClass(Product.class);

			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure(
					"hibernate-annotation.cfg.xml").build();
			Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			sessionFactory = metadata.getSessionFactoryBuilder().build();

			// ServiceRegistry serviceRegistry = new
			// StandardServiceRegistryBuilder().applySettings(
			// configuration.getProperties()).build();
			// sessionFactory =
			// configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void runInserters() {
		insertUsers();
		insertProducts();
		insertComments();
	}

	private static void insertUsers() {
	}

	private static void insertProducts() {
		ProductFacade productFacade = new ProductFacade();
		Random random = new Random();

		Product product = new Product();
		product.setBrand("Intel");
		product.setModel("i5 6300");

		productFacade.createProduct(product);
	}

	private static void insertComments() {
		CommentFacade commentFacade = new CommentFacade();
		Random random = new Random();

		Comment comment = new Comment();
		comment.setSummary("good");
		comment.setAuthor("jan_kowalski_543");
		
		List <String> advantages=new ArrayList<>();
		advantages.add("fast");
		advantages.add("quiet");
		comment.setAdvantages(advantages);

		commentFacade.createComment(comment);
	}
}
