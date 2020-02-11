package by.it.academy.elearning.service;

import by.it.academy.elearning.model.User;

import java.util.Optional;

public interface UserService extends BasicService<User> {

    Optional<User> findUserByLoginAndPassword(String login, String password);

}
