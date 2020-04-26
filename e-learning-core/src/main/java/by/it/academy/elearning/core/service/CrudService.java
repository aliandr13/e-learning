package by.it.academy.elearning.core.service;

import org.dom4j.rule.Mode;

import java.util.List;
import java.util.Optional;

public interface CrudService<Model> {

    Model create(Model model);

    Optional<Model> findById(Long id);

    void update(Model model);

    void delete(Model model);

    void delete(Long id);

    List<Model> findAll();
}
