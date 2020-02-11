package by.it.academy.elearning.service;

import java.util.List;
import java.util.Optional;

public interface BasicService<M> {

    M create(M model);

    Optional<M> findById(Number id);

    void delete(M model);

    List<M> findAll();

}
