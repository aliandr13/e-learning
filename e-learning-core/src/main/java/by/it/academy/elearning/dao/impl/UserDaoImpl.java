package by.it.academy.elearning.dao.impl;

import by.it.academy.elearning.dao.UserDao;
import by.it.academy.elearning.exception.ELearningException;
import by.it.academy.elearning.hibernate.HibernateUtil;
import by.it.academy.elearning.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public static final String SELECT_USER_BY_LOGIN = "FROM User e where e.email =: email";

    public UserDaoImpl() {
        super(LoggerFactory.getLogger(UserDaoImpl.class), User.class);
    }

    @Override
    public Optional<User> getByLogin(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query<User> query = session.createQuery(SELECT_USER_BY_LOGIN, User.class);

            query.setParameter("email", email);
            User user = query.uniqueResult();
            transaction.commit();

            return Optional.ofNullable(user);
        } finally {
            closeQuietly(session);
        }
    }

    @Override
    public List<User> getAll() throws ELearningException {
        return null;
    }
}
