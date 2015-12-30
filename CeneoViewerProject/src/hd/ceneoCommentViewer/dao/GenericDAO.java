package hd.ceneoCommentViewer.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import hd.ceneoCommentViewer.utils.HibernateUtil;

public abstract class GenericDAO<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private transient Session session;

	private Class<T> entityClass;

	public void beginTransaction() {
		session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
	}

	public void commit() {
		session.getTransaction().commit();
	}

	public void rollback() {
		session.getTransaction().rollback();
	}

	public void closeTransaction() {
		session.close();
	}

	public void commitAndCloseTransaction() {
		commit();
		closeTransaction();
	}

	public void flush() {
		session.flush();
	}

	public GenericDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public void save(T entity) {
		session.persist(entity);
	}

	public void delete(Object object) {
		session.delete(object);
	}

	@SuppressWarnings("unchecked")
	public T update(T entity) {
		return (T) session.merge(entity);
	}

	@SuppressWarnings("unchecked")
	public T find(int entityID) {
		return (T) session.get(entityClass, entityID);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return session.createCriteria(entityClass).list();
	}
}
