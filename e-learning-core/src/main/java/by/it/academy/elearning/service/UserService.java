package by.it.academy.elearning.service;

import by.it.academy.elearning.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends BasicService<User> {

    Optional<User> findUserByLoginAndPassword(String login, String password);

    List<User> findStudentsByGroup(long groupId);

    void assignToGroup(User user, long groupId);
}
