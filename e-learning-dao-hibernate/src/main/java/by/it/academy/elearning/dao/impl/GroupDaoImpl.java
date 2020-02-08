package by.it.academy.elearning.dao.impl;

import by.it.academy.elearning.dao.GroupDao;
import by.it.academy.elearning.exception.NotImplementedException;
import by.it.academy.elearning.hibernate.HibernateUtil;
import by.it.academy.elearning.model.Group;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class GroupDaoImpl extends AbstractDao<Group> implements GroupDao {

    public static final String SELECT_ALL = "FROM  Group";


    public GroupDaoImpl() {
        super(log, Group.class);
    }

    @Override
    public List<Group> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery(SELECT_ALL, Group.class).list();
        } finally {
            closeQuietly(session);
        }
    }

}
