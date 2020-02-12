package by.it.academy.elearning.service.impl;

import by.it.academy.elearning.dao.StudentDao;
import by.it.academy.elearning.dao.impl.StudentDaoImpl;
import by.it.academy.elearning.model.Student;
import by.it.academy.elearning.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class StudentServiceImpl implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    private static final StudentService INSTANCE = new StudentServiceImpl();

    private final StudentDao studentDao = StudentDaoImpl.getInstance();

    private StudentServiceImpl() {
    }

    public static StudentService getService() {
        return INSTANCE;
    }

    @Override
    public List<Student> getAllStudents() {
        logger.debug("Get all students");
        try {
            return studentDao.getAll();
        } catch (SQLException e) {
            logger.error("Error while getting all students", e);
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Student> getById(Long id) {
        logger.debug("Get student by id {}", id);
        try {
            Optional<Student> student = studentDao.read(id);
            logger.debug("result {}", student);
            return student;
        } catch (SQLException e) {
            logger.error("Error while getting student by id " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public Student add(Student student) {
        logger.debug("add new student {}", student);
        try {
            Long id = studentDao.create(student);
            student.setId(id);
            logger.debug("result {}", id);
        } catch (SQLException e) {
            logger.error("Error while creating student " + student, e);
        }
        return student;
    }

    @Override
    public void delete(Long id) {
        logger.debug("deleting student id = {}", id);
        try {
            int delete = studentDao.delete(id);
            logger.debug("result {}", delete);
        } catch (SQLException e) {
            logger.error("Error while deleting student id=" + id, e);
        }
    }

    @Override
    public Student update(Student student) {
        logger.debug("updating student {}", student);
        try {
            int update = studentDao.update(student);
            logger.debug("result {}", update);
        } catch (SQLException e) {
            logger.error("Error while updating student " + student, e);
        }
        return student;
    }
}
