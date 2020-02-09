package by.it.academy.elearning.dao;

import by.it.academy.elearning.model.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserDao extends DAO<User> {

    Optional<User> getByLogin(String email) throws SQLException;

}
