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

/**
 * 
 * Klasa konfiguruąca połączenie z bazą danych.
 *
 */
public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	/**
	 * Konfiguracja połączenia z bazą danych.
	 */
	static {
		try {
			Configuration configuration = new Configuration();
			configuration.configure("hibernate-annotation.cfg.xml");

			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure(
					"hibernate-annotation.cfg.xml").build();
			Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			sessionFactory = metadata.getSessionFactoryBuilder().build();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * Pobieranie okiektu tworzącego sesje.
	 * @return Obiekt tworzący sesje.
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Metoda uruchamiająca funkcje wstawiające dane
	 */
	public static void runInserters() {
		insertProducts();
		insertComments();
	}

	/**
	 * Metoda wstawiająca produkty
	 */
	private static void insertProducts() {
	}

	/**
	 * Metoda wstawiająca komentarze
	 */
	private static void insertComments() {
	}
}
