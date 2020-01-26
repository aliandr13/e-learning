package by.it.academy.elearning.dao.impl;

import by.it.academy.elearning.dao.GroupDao;
import by.it.academy.elearning.exception.ELearningException;
import by.it.academy.elearning.exception.NotImplementedException;
import by.it.academy.elearning.hibernate.HibernateUtil;
import by.it.academy.elearning.model.Group;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
public class GroupDaoImpl extends AbstractDao implements GroupDao {

    public static final String SELECT_ALL = "FROM  Group";


    public GroupDaoImpl() {
        super(log);
    }

    @Override
    public Long create(Group group) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.persist(group);
            transaction.commit();
            return group.getId();
        } catch (HibernateException e) {
            logger.error("error", e);
            session.getTransaction().rollback();
            throw new ELearningException("error", e);
        } finally {
            closeQuietly(session);
        }
    }

    @Override
    public Optional<Group> read(Long id) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public int update(Group group) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public int delete(Long id) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Query<Group> query = session.createQuery("DELETE Group where id = : id", Group.class);
            query.setParameter("id", id);
            int i = query.executeUpdate();
            transaction.commit();
            return i;
        } catch (HibernateException e) {
            logger.error("error", e);
            session.getTransaction().rollback();
            throw new ELearningException("error", e);
        } finally {
            closeQuietly(session);
        }
    }


    @Override
    public List<Group> getAll() throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery(SELECT_ALL, Group.class).list();
        } finally {
            closeQuietly(session);
        }
    }

}
