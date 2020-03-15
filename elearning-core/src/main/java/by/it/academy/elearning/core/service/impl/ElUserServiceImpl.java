package by.it.academy.elearning.core.service.impl;

import by.it.academy.elearning.core.model.ELUser;
import by.it.academy.elearning.core.repository.UserRepository;
import by.it.academy.elearning.core.service.ElUserService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class ElUserServiceImpl extends BaseCrudService<UserRepository, ELUser> implements ElUserService {

    private final UserRepository repository;

    @Autowired
    public ElUserServiceImpl(UserRepository repository) {
        super(repository, log);
        this.repository = repository;
    }

    @Override
    public ELUser create(ELUser elUser) {
        elUser.setUsername(elUser.getUsername().trim().toLowerCase());
        return super.create(elUser);
    }

    @Override
    public Optional<ELUser> findByUsername(@NonNull String username) {
        log.info("Finding user by username: {}", username);
        return repository.findByUsername(username);
    }
}
