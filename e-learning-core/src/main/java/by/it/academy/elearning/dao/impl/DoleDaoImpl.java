package by.it.academy.elearning.dao.impl;

import by.it.academy.elearning.dao.RoleDao;
import by.it.academy.elearning.exception.ELearningException;
import by.it.academy.elearning.hibernate.HibernateUtil;
import by.it.academy.elearning.model.Role;
import by.it.academy.elearning.model.RoleEnum;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class DoleDaoImpl extends AbstractDao<Role> implements RoleDao { // RoleDaoImpl

    protected DoleDaoImpl() {
        super(log, Role.class);
    }

    @Override
    public Role getByName(RoleEnum role) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Query<Role> query = session.createQuery("FROM Role where role =: roleName", entityClass);
            query.setParameter("roleName", role);
            Role result = query.uniqueResult();
            transaction.commit();
            return result;
        } catch (HibernateException he) {
            rollback(transaction);
            throw new ELearningException("Error get role by name", he);
        } finally {
            closeQuietly(session);
        }
    }
}
