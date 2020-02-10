package by.it.academy.elearning.dao;

import by.it.academy.elearning.model.UserAuth;

import java.sql.SQLException;
import java.util.Optional;

public interface UserDao extends DAO<UserAuth> {

    Optional<UserAuth> getByLogin(String email) throws SQLException;

}
