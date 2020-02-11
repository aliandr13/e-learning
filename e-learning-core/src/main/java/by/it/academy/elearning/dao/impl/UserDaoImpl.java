package by.it.academy.elearning.dao.impl;

import by.it.academy.elearning.dao.UserDao;
import by.it.academy.elearning.hibernate.HibernateUtil;
import by.it.academy.elearning.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public static final String SELECT_USER_BY_LOGIN = "SELECT e FROM User e join e.userAuth as a where a.login =: login";

    public UserDaoImpl() {
        super(LoggerFactory.getLogger(UserDaoImpl.class), User.class);
    }

    @Override
    public Optional<User> getUserAuthByLogin(String login) {
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