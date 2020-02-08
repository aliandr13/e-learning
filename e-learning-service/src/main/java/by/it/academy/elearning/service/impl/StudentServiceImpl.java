package by.it.academy.elearning.service.impl;

import by.it.academy.elearning.dao.StudentDao;
import by.it.academy.elearning.model.Student;
import by.it.academy.elearning.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private final StudentDao studentDao;

    @Override
    public List<Student> getAllStudents() {
        log.debug("Get all students");
        return studentDao.getAll();
    }

    @Override
    public List<Student> getStudentsByGroup(Long groupId) {
        log.debug("Get students by group id {}", groupId);
        return studentDao.getStudentsByGroup(groupId);
    }

    @Override
    public Optional<Student> getById(Long id) {
        log.debug("Get student by id {}", id);
        Optional<Student> student = studentDao.read(id);
        log.debug("result {}", student);
        return student;
    }

    @Override
    public Student add(Student student) {
        log.debug("add new student {}", student);
        Student saved = studentDao.create(student);
        log.debug("result {}", saved);
        return saved;
    }

    @Override
    public void delete(Long id) {
        log.debug("deleting student id = {}", id);
        int delete = studentDao.delete(id);
        log.debug("result {}", delete);
    }

    @Override
    public Student update(Student student) {
        log.debug("updating student {}", student);
        studentDao.update(student);
        return student;
    }
}
