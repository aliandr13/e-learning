package by.it.academy.elearning.service.impl;

import by.it.academy.elearning.dao.UserDao;
import by.it.academy.elearning.model.UserAuth;
import by.it.academy.elearning.security.EncryptUtils;
import by.it.academy.elearning.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
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
    public List<UserAuth> findAll() {
        log.info("find all users");
        List<UserAuth> users = userDao.getAll();
        log.debug("Find all users result: {}", users);
        return users;
    }

    @Override
    public Optional<UserAuth> findUserByLoginAndPassword(String login, String password) {
        try {
            Optional<UserAuth> userOption = userDao.getByLogin(login);
            if (userOption.isPresent()) {
                UserAuth user = userOption.get();
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
    public Optional<UserAuth> findUserById(Long id) {
        return userDao.read(id);
    }
}
