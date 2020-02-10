package by.it.academy.elearning.service;

import by.it.academy.elearning.model.User;
import by.it.academy.elearning.model.UserAuth;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findUserByLoginAndPassword(String login, String password);

    Optional<User> findUserById(Long userId);
}
