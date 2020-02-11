package by.it.academy.elearning.service.impl;

import by.it.academy.elearning.dao.RoleDao;
import by.it.academy.elearning.dao.UserDao;
import by.it.academy.elearning.model.Role;
import by.it.academy.elearning.model.RoleEnum;
import by.it.academy.elearning.model.User;
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

    private final RoleDao roleDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public List<User> findAll() {
        log.info("find all users");
        List<User> users = userDao.findAll();
        log.debug("Find all users result: {}", users);
        return users;
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        try {
            Optional<User> userOption = userDao.getUserAuthByLogin(login);
            if (userOption.isPresent()) {
                User user = userOption.get();
                String hash = EncryptUtils.getSHA256(password, user.getUserAuth().getSalt());
                if (user.getUserAuth().getPassword().equals(hash)) {
                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            log.error("Error find user by login and password " + login, e);
        }
        return Optional.empty();
    }

    @Override
    public User create(User user) {
        Role studentRole = roleDao.getByName(RoleEnum.STUDENT);
        user.setRole(studentRole);
        return userDao.create(user);
    }

    @Override
    public Optional<User> findById(Number id) {
        return userDao.find((Long) id);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user.getId());
    }

}
