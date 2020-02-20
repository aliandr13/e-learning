package by.it.academy.elearning.dao.impl;

import by.it.academy.elearning.dao.DAO;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

abstract class AbstractDao<E> implements DAO<E> {

    public static final String DELETE_BY_ID_TEMPLATE = "DELETE %s where id = : id";
    private final String GET_ALL;
    private final String DELETE_BY_ID;
    protected final Class<E> entityClass;

    @Getter
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    protected AbstractDao(@NonNull Class<E> entityClass) {
        this.entityClass = entityClass;
        this.DELETE_BY_ID = String.format(DELETE_BY_ID_TEMPLATE, entityClass.getSimpleName());
        this.GET_ALL = "FROM " + entityClass.getSimpleName();
    }

    public E create(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Optional<E> find(Serializable id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @Override
    public void update(E entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Serializable id) {
        TypedQuery<E> query = entityManager.createQuery(DELETE_BY_ID, entityClass);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<E> findAll() {
        return entityManager.createQuery(GET_ALL, entityClass).getResultList();
    }

}
