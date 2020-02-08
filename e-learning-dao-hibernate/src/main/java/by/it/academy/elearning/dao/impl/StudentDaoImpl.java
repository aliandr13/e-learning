package by.it.academy.elearning.dao.impl;

import by.it.academy.elearning.dao.StudentDao;
import by.it.academy.elearning.hibernate.HibernateUtil;
import by.it.academy.elearning.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class StudentDaoImpl extends AbstractDao<Student> implements StudentDao {


    protected StudentDaoImpl() {
        super(log, Student.class);
    }

    @Override
    public List<Student> getStudentsByGroup(Long groupId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Student> query = session.createQuery("from Student where group.id =: groupId", Student.class);
            query.setParameter("groupId", groupId);
            return query.list();
        } finally {
            closeQuietly(session);
        }
    }

}
