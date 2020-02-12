package by.it.academy.elearning.dao.impl;

import by.it.academy.elearning.dao.DAO;
import by.it.academy.elearning.db.connection.pool.ElDataSource;
import by.it.academy.elearning.exception.ELearningException;
import by.it.academy.elearning.hibernate.HibernateUtil;
import lombok.NonNull;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

abstract class AbstractDao<E> implements DAO<E> {

    public static final String DELETE_BY_ID_TEMPLATE = "DELETE %s where id = : id";
    private final String GET_ALL;
    private final String DELETE_BY_ID;
    protected final Logger logger;
    protected final Class<E> entityClass;

    protected AbstractDao(Logger logger, @NonNull Class<E> entityClass) {
        this.logger = logger;
        this.entityClass = entityClass;
        this.DELETE_BY_ID = String.format(DELETE_BY_ID_TEMPLATE, entityClass.getSimpleName());
        this.GET_ALL = "FROM " + entityClass.getSimpleName();
    }

    protected Connection getConnection() throws SQLException {
        return ElDataSource.getConnection();
    }

    public E create(E entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
            return entity;
        } catch (HibernateException he) {
            rollback(transaction);
            throw new ELearningException("Error creating entity", he);
        } finally {
            closeQuietly(session);
        }
    }

    @Override
    public Optional<E> find(Number id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            E entity = session.find(entityClass, id);
            transaction.commit();
            return Optional.ofNullable(entity);
        } catch (HibernateException he) {
            rollback(transaction);
            throw new ELearningException("Error reading entity", he);
        } finally {
            closeQuietly(session);
        }
    }

    @Override
    public void update(E entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (HibernateException he) {
            rollback(transaction);
            throw new ELearningException("Error updating entity", he);
        } finally {
            closeQuietly(session);
        }
    }

    @Override
    public int delete(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query<E> query = session.createQuery(DELETE_BY_ID, entityClass);
            query.setParameter("id", id);
            int i = query.executeUpdate();
            transaction.commit();
            return i;
        } catch (HibernateException he) {
            rollback(transaction);
            throw new ELearningException("Error deleting entity", he);
        } finally {
            closeQuietly(session);
        }
    }

    @Override
    public List<E> findAll() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            List<E> list = session.createQuery(GET_ALL, entityClass).list();
            transaction.commit();
            return list;
        } catch (HibernateException he) {
            rollback(transaction);
            throw new ELearningException("Error reading all entities", he);
        } finally {
            closeQuietly(session);
        }
    }


    protected void closeQuietly(AutoCloseable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            logger.error("Error while closing closable: " + closeable, e);
        }
    }

    protected void rollback(Transaction transaction) {
        if (transaction != null) {
            transaction.rollback();
        }
    }

}
