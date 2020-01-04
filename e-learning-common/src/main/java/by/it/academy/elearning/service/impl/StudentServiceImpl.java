package by.it.academy.elearning.service.impl;

import by.it.academy.elearning.dao.StudentDao;
import by.it.academy.elearning.exception.ELearningException;
import by.it.academy.elearning.model.Student;
import by.it.academy.elearning.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    @Override
    public List<Student> getAllStudents() {
        log.debug("Get all students");
        try {
            return studentDao.getAll();
        } catch (SQLException e) {
            String msg = "Error while getting all students";
            log.error(msg, e);
            throw new ELearningException(msg, e);
        }
    }

    @Override
    public List<Student> getStudentsByGroup(Long groupId) {
        log.debug("Get students by group id {}", groupId);
        try {
            return studentDao.getStudentsByGroup(groupId);
        } catch (SQLException e) {
            String msg = "Error while get students by group id " + groupId;
            log.error(msg, e);
            throw new ELearningException(msg, e);
        }
    }

    @Override
    public Optional<Student> getById(Long id) {
        log.debug("Get student by id {}", id);
        try {
            Optional<Student> student = studentDao.read(id);
            log.debug("result {}", student);
            return student;
        } catch (SQLException e) {
            String msg = "Error while getting student by id " + id;
            log.error(msg, e);
            throw new ELearningException(msg, e);
        }
    }

    @Override
    public Student add(Student student) {
        log.debug("add new student {}", student);
        try {
            Long id = studentDao.create(student);
            student.setId(id);
            log.debug("result {}", id);
        } catch (SQLException e) {
            log.error("Error while creating student " + student, e);
        }
        return student;
    }

    @Override
    public void delete(Long id) {
        log.debug("deleting student id = {}", id);
        try {
            int delete = studentDao.delete(id);
            log.debug("result {}", delete);
        } catch (SQLException e) {
            log.error("Error while deleting student id=" + id, e);
        }
    }

    @Override
    public Student update(Student student) {
        log.debug("updating student {}", student);
        try {
            int update = studentDao.update(student);
            log.debug("result {}", update);
        } catch (SQLException e) {
            log.error("Error while updating student " + student, e);
        }
        return student;
    }
}
