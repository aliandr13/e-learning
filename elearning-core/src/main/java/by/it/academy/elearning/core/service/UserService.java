package by.it.academy.elearning.core.service;

import by.it.academy.elearning.core.model.User;

import java.util.Optional;

public interface UserService extends CrudService<User> {

    Optional<User> findByEmail(String email);
}
