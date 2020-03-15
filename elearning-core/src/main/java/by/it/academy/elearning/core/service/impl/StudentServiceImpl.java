package by.it.academy.elearning.core.service.impl;

import by.it.academy.elearning.core.model.Student;
import by.it.academy.elearning.core.repository.StudentRepository;
import by.it.academy.elearning.core.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class StudentServiceImpl extends BaseCrudService<StudentRepository, Student> implements StudentService {

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        super(studentRepository, log);
    }
}
