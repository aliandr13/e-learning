package by.it.academy.elearning.dao;

import by.it.academy.elearning.model.RoleEnum;
import by.it.academy.elearning.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDao extends DAO<User> {

    Optional<User> getUserAuthByLogin(String email) throws SQLException;

    List<User> findByRoleAndGroup(RoleEnum student, long groupId);
}
