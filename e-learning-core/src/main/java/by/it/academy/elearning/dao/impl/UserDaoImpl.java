package by.it.academy.elearning.dao.impl;

import by.it.academy.elearning.dao.UserDao;
import by.it.academy.elearning.hibernate.HibernateUtil;
import by.it.academy.elearning.model.UserAuth;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl extends AbstractDao<UserAuth> implements UserDao {

    public static final String SELECT_USER_BY_LOGIN = "FROM UserAuth e where e.email =: email";

    public UserDaoImpl() {
        super(LoggerFactory.getLogger(UserDaoImpl.class), UserAuth.class);
    }

    @Override
    public Optional<UserAuth> getByLogin(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query<UserAuth> query = session.createQuery(SELECT_USER_BY_LOGIN, UserAuth.class);

            query.setParameter("email", email);
            UserAuth user = query.uniqueResult();
            transaction.commit();

            return Optional.ofNullable(user);
        } finally {
            closeQuietly(session);
        }
    }

}
