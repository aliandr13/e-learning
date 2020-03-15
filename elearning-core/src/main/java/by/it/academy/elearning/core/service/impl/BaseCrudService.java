package by.it.academy.elearning.core.service.impl;

import by.it.academy.elearning.core.service.CrudService;
import org.slf4j.Logger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

abstract class BaseCrudService<Repository extends CrudRepository<Model, Long>, Model> implements CrudService<Model> {

    private final Repository repository;
    private final Logger log;

    BaseCrudService(Repository repository, Logger log) {
        this.repository = repository;
        this.log = log;
    }

    @Override
    public Model create(Model model) {
        log.info("Creating {}", model);
        return repository.save(model);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Model> findById(Long id) {
        log.info("Find by id {}", id);
        return repository.findById(id);
    }

    @Override
    public void update(Model model) {
        log.info("Updating {}", model);
        repository.save(model);
    }

    @Override
    public void delete(Model model) {
        log.info("Deleting {}", model);
        repository.delete(model);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting by id {}", id);
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Model> findAll() {
        log.info("Find all");
        return (List<Model>) repository.findAll();
    }
}
