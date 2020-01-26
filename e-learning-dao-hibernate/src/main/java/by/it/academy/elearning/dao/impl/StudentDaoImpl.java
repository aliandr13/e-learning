package by.it.academy.elearning.dao.impl;

import by.it.academy.elearning.dao.StudentDao;
import by.it.academy.elearning.exception.ELearningException;
import by.it.academy.elearning.hibernate.HibernateUtil;
import by.it.academy.elearning.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
public class StudentDaoImpl extends AbstractDao implements StudentDao {

    public static final String INSERT_STUDENT = "INSERT INTO user_info (first_name, middle_name, last_name, phone) VALUES (?,?,?,?)";
    public static final String SELECT_STUDENT_BY_ID = "SELECT * FROM user_info left join `group` g on user_info.group_id = g.group_id WHERE id = ?";
    public static final String SELECT_STUDENT_BY_GROUP_ID = "SELECT * FROM user_info WHERE group_id = ?";
    public static final String SELECT_ALL_STUDENT = "SELECT * FROM user_info left join `group` g on user_info.group_id = g.group_id";
    public static final String UPDATE_STUDENT = "UPDATE user_info  SET first_name = ? , middle_name = ?, last_name = ?, phone = ?, email =?, group_id =? WHERE id = ?";
    public static final String DELETE_STUDENT_BY_ID = "DELETE FROM user_info  WHERE id = ?";

    public StudentDaoImpl() {
        super(log);
    }

    @Override
    public Long create(Student student) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();
            return student.getId();
        } catch (HibernateException e) {
            logger.error("error", e);
            session.getTransaction().rollback();
            throw new ELearningException("error", e);
        } finally {
            closeQuietly(session);
        }
    }

    @Override
    public Optional<Student> read(Long id) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Student student = session.find(Student.class, id);
            return Optional.ofNullable(student);
        } catch (HibernateException e) {
            logger.error("error", e);
            throw new ELearningException("error", e);
        } finally {
            closeQuietly(session);
        }
    }


    @Override
    public int update(Student student) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(student);
            transaction.commit();
            return -1;
        } catch (HibernateException e) {
            logger.error("error", e);
            session.getTransaction().rollback();
            throw new ELearningException("error", e);
        } finally {
            closeQuietly(session);
        }
    }

    @Override
    public int delete(Long id) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Student object = new Student();
            object.setId(id);
            session.delete(object);
            transaction.commit();
            return -1;
        } catch (HibernateException e) {
            logger.error("error", e);
            session.getTransaction().rollback();
            throw new ELearningException("error", e);
        } finally {
            closeQuietly(session);
        }
    }

    @Override
    public List<Student> getAll() throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery("FROM Student", Student.class).list();
        } finally {
            closeQuietly(session);
        }
    }


    @Override
    public List<Student> getStudentsByGroup(Long groupId) throws SQLException {
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
