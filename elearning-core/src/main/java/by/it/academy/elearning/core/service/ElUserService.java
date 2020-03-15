package by.it.academy.elearning.core.service;

import by.it.academy.elearning.core.model.ELUser;

import java.util.Optional;

public interface ElUserService extends CrudService<ELUser> {

    Optional<ELUser> findByUsername(String username);
}
