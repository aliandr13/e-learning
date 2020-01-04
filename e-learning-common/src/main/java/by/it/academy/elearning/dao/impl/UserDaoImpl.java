package by.it.academy.elearning.dao.impl;

import by.it.academy.elearning.dao.UserDao;
import by.it.academy.elearning.model.User;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao implements UserDao {

    private static final UserDaoImpl INSTANCE = new UserDaoImpl();

    public static final String SELECT_BY_USER_NAME = "SELECT  * FROM user u JOIN user_role r ON u.role_id = r.id WHERE u.user_name = ?";

    private UserDaoImpl() {
        super(LoggerFactory.getLogger(UserDaoImpl.class));
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    @Override
    public Long create(User user) throws SQLException {
        return null;
    }

    @Override
    public Optional<User> read(Long id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public int update(User user) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Long id) throws SQLException {
        return 0;
    }

    @Override
    public List<User> getAll() throws SQLException {
        return null;
    }

    @Override
    public Optional<User> getByUserName(String userName) throws SQLException {
        ResultSet rs = null;
        try {
            PreparedStatement statement = getConnection().prepareStatement(SELECT_BY_USER_NAME);
            statement.setString(1, userName);

            rs = statement.executeQuery();

            if (rs.next()) {
                User user = new User(rs.getLong("id"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("salt"),
                        rs.getString("role")
                );
                return Optional.of(user);
            }
        } finally {
            closeQuietly(rs);
        }
        return Optional.empty();
    }
}
