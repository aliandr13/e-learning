package by.it.academy.elearning.service.impl;

import by.it.academy.elearning.dao.UserDao;
import by.it.academy.elearning.model.User;
import by.it.academy.elearning.security.EncryptUtils;
import by.it.academy.elearning.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        try {
            Optional<User> userOption = userDao.getByLogin(login);
            if (userOption.isPresent()) {
                User user = userOption.get();
                String hash = EncryptUtils.getSHA256(password, user.getSalt());
                if (user.getPassword().equals(hash)) {
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            log.error("Error find user by login and password " + login, e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userDao.read(id);
    }
}
