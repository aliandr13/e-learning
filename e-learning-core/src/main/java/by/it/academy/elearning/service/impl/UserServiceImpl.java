package by.it.academy.elearning.service.impl;

import by.it.academy.elearning.dao.GroupDao;
import by.it.academy.elearning.dao.RoleDao;
import by.it.academy.elearning.dao.UserDao;
import by.it.academy.elearning.hibernate.HibernateUtil;
import by.it.academy.elearning.model.Group;
import by.it.academy.elearning.model.Role;
import by.it.academy.elearning.model.RoleEnum;
import by.it.academy.elearning.model.User;
import by.it.academy.elearning.security.EncryptUtils;
import by.it.academy.elearning.service.GroupService;
import by.it.academy.elearning.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final GroupService groupService;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, GroupService groupService) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.groupService = groupService;
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
    public User create(User user, Long groupId) {
        Role studentRole = roleDao.getByName(RoleEnum.STUDENT);
        user.setRole(studentRole);

        Optional<Group> byId = groupService.findById(groupId);
        if (byId.isPresent()) {
            List<Group> groups = user.getGroups();
            if (groups == null) {
                Group group = byId.get();
                user.setGroups(Collections.singletonList(group));
            } else {
                groups.add(byId.get());
            }
        }

        return userDao.create(user);
    }

    @Override
    public Optional<User> findById(Number id) {
        return userDao.find(id);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user.getId());
    }

    @Override
    public List<User> findStudentsByGroup(long groupId) {
        log.info("find students by group id: {}", groupId);
        List<User> students = userDao.findByRoleAndGroup(RoleEnum.STUDENT, groupId);
        log.debug("find students by id result {}", students);
        return students;
    }

}
