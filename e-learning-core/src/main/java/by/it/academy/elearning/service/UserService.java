package by.it.academy.elearning.service;

import by.it.academy.elearning.model.UserAuth;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserAuth> findAll();

    Optional<UserAuth> findUserByLoginAndPassword(String login, String password);

    Optional<UserAuth> findUserById(Long userId);
}
