package by.it.academy.elearning.service;

import by.it.academy.elearning.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findUserByLoginAndPassword(String login, String password);

    Optional<User> findUserById(Long userId);
}
