package by.it.academy.elearning.dao;

import by.it.academy.elearning.exception.ELearningException;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface DAO<E> {

    E create(E e) throws ELearningException;

    Optional<E> find(Serializable id) throws ELearningException;

    void update(E e) throws ELearningException;

    void delete(Serializable id) throws ELearningException;

    List<E> findAll() throws ELearningException;

}
