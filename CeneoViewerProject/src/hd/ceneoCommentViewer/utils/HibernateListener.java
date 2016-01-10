package hd.ceneoCommentViewer.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 
 * Klasa zawiera metody służące do inicjalizacji i zakończenia sesji bazy danych.
 *
 */
public class HibernateListener implements ServletContextListener {

	/**
	 * Metoda inicjalizująca.
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		HibernateUtil.getSessionFactory();
		HibernateUtil.runInserters();
	}
	
	/**
	 * Metoda kończąca sesje.
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		HibernateUtil.getSessionFactory().close();
	}
}
