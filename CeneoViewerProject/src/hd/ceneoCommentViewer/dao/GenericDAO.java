package hd.ceneoCommentViewer.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import hd.ceneoCommentViewer.model.CommentId;
import hd.ceneoCommentViewer.utils.HibernateUtil;

/**
 * 
 * Główny obiekt DAO zawierający metody służące do komunikacji z bazą danych.
 *
 * @param <T> Klasa reprezentująca encje znajdujące się w bazie danych.
 */
public abstract class GenericDAO<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Sesja podczas, której wykonywane sa operacje na bazie. 
	 */
	private transient Session session;

	/**
	 * Obiekt klasy reprezentujacej encje.
	 */
	private Class<T> entityClass;

	/**
	 * Metoda tworzy sesje i rozpoczynajaca transakcje.
	 */
	public void beginTransaction() {
		session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
	}

	/**
	 * Metoda zatwierdza zmiany wykonane podczas transakcji.
	 */
	public void commit() {
		session.getTransaction().commit();
	}

	/**
	 * Metoda służy do cofnięcia zmian.
	 */
	public void rollback() {
		session.getTransaction().rollback();
	}

	/**
	 * Metoda zamyka transakcje i kończy sesje.
	 */
	public void closeTransaction() {
		session.close();
	}

	/**
	 * Metoda zatwierdza zmiany i zamyka transakcje.
	 */
	public void commitAndCloseTransaction() {
		commit();
		closeTransaction();
	}

	/**
	 * Metoda czyszcząca buffor zawierający stan obiektów.
	 */
	public void flush() {
		session.flush();
	}

	/**
	 * Konstruktor
	 * @param entityClass Obiekt klasy reprezentujacej encje.
	 */
	public GenericDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * Metoda zapisuje obiekt do bazy danych.
	 * @param entity Zapisywany obiekt.
	 */
	public void save(T entity) {
		session.persist(entity);
	}

	/**
	 * Metoda usuwa obiekt z bazy danych.
	 * @param object Usuwany obiekt.
	 */
	public void delete(Object object) {
		session.delete(object);
	}

	/**
	 * Metoda uaktualnia obiekt w bazy danych.
	 * @param entity Uaktualniany obiekt.
	 * @return Aktualny obiekt.
	 */
	@SuppressWarnings("unchecked")
	public T update(T entity) {
		return (T) session.merge(entity);
	}

	/**
	 * Metoda wyszukuje obiekt w bazie danych.
	 * @param entityID Id poszukiwanego obiektu.
	 * @return Znaleziony obiekt.
	 */
	@SuppressWarnings("unchecked")
	public T find(int entityID) {
		return (T) session.get(entityClass, entityID);
	}
	
	/**
	 * Metoda wyszukuje komentarz w bazie danych.
	 * @param entityID Id komentarza.
	 * @return Znaleziony komentarz.
	 */
	@SuppressWarnings("unchecked")
	public T findbyCommentId(CommentId entityID) {
		return (T) session.get(entityClass, entityID);
	}

	/**
	 * Metoda wyszukuje wszystkie obiekty danego typu.
	 * @return Lista znalezionych obiektów.
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return session.createCriteria(entityClass).list();
	}
}
