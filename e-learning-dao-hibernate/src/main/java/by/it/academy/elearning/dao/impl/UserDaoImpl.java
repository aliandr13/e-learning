package by.it.academy.elearning.dao.impl;

import by.it.academy.elearning.dao.UserDao;
import by.it.academy.elearning.hibernate.HibernateUtil;
import by.it.academy.elearning.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao implements UserDao {

    public static final String SELECT_USER_BY_LOGIN = "FROM User e where e.login =: login";

    public UserDaoImpl() {
        super(LoggerFactory.getLogger(UserDaoImpl.class));
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
    public Optional<User> getByLogin(String login) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query<User> query = session.createQuery(SELECT_USER_BY_LOGIN, User.class);

            query.setParameter("login", login);
            User user = query.uniqueResult();
            transaction.commit();

            return Optional.ofNullable(user);
        } finally {
            closeQuietly(session);
        }
    }
}
