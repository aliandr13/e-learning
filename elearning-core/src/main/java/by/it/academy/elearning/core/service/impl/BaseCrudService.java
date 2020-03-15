package by.it.academy.elearning.core.service.impl;

import by.it.academy.elearning.core.service.CrudService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

abstract class BaseCrudService<Repository extends CrudRepository<Model, Long>, Model> implements CrudService<Model> {

    private final Repository repository;

    BaseCrudService(Repository repository)
    {
        this.repository = repository;
    }

    @Override
    public Model create(Model model) {
        return repository.save(model);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Model> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void update(Model model) {
        repository.save(model);
    }

    @Override
    public void delete(Model model) {
        repository.delete(model);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Model> findAll() {
        return (List<Model>) repository.findAll();
    }
}
