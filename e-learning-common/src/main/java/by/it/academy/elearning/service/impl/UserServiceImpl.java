package by.it.academy.elearning.service.impl;

import by.it.academy.elearning.dao.UserDao;
import by.it.academy.elearning.dao.impl.UserDaoImpl;
import by.it.academy.elearning.model.User;
import by.it.academy.elearning.security.EncryptUtils;
import by.it.academy.elearning.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final UserService INSTANCE = new UserServiceImpl();

    private final UserDao userDao = UserDaoImpl.getInstance();

    public static UserService getInstance() {
        return INSTANCE;
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        try {
            Optional<User> userOption = userDao.getByUserName(login);
            if (userOption.isPresent()) {
                User user = userOption.get();
                String hash = EncryptUtils.getSHA256(password, user.getSalt());
                if (user.getPassword().equals(hash)) {
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            logger.error("Error find user by login and password " + login, e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        try {
            return userDao.read(id);
        } catch (SQLException e) {
            logger.error("Error find user by id: " + id, e);
        }
        return Optional.empty();
    }
}
