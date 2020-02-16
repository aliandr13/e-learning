package by.it.academy.elearning.dao.impl;

import by.it.academy.elearning.dao.UserDao;
import by.it.academy.elearning.exception.ELearningException;
import by.it.academy.elearning.hibernate.HibernateUtil;
import by.it.academy.elearning.model.Group;
import by.it.academy.elearning.model.RoleEnum;
import by.it.academy.elearning.model.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public static final String SELECT_USER_BY_LOGIN = "SELECT e FROM User e join e.userAuth as a where a.login =: login";
    public static final String SELECT_USER_BY_ROLE_AND_GROUP_ID = "select u from User u join u.groups g where u.role.role =: roleName and g.id =: groupId";

    public UserDaoImpl() {
        super(log, User.class);
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

    @Override
    public List<User> findByRoleAndGroup(RoleEnum roleName, long groupId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query<User> query = session.createQuery(SELECT_USER_BY_ROLE_AND_GROUP_ID, User.class);

            query.setParameter("roleName", roleName);
            query.setParameter("groupId", groupId);
            List<User> users = query.list();
            transaction.commit();

            return users;
        } catch (HibernateException hb) {
            log.error("Error while find by id {} and role {}", groupId, roleName);
            throw new ELearningException("Error while find by id and role", hb);
        } finally {
            closeQuietly(session);
        }
    }


    public User create(User user) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            List<Group> groups = user.getGroups();
            if (groups != null && !groups.isEmpty()) {
                for (Group group : groups) {
                    session.refresh(group);
                    group.getUsers().add(user);
                }
            }
            session.saveOrUpdate(user);
            transaction.commit();
            return user;
        } catch (HibernateException he) {
            rollback(transaction);
            throw new ELearningException("Error creating entity", he);
        } finally {
            closeQuietly(session);
        }
    }
}
