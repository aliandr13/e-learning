package by.it.academy.elearning.dao;

import by.it.academy.elearning.exception.ELearningException;

import java.util.List;
import java.util.Optional;

public interface DAO<E> {

    E create(E e) throws ELearningException;

    Optional<E> find(Number id) throws ELearningException;

    void update(E e) throws ELearningException;

    int delete(Long id) throws ELearningException;

    List<E> findAll() throws ELearningException;

}
