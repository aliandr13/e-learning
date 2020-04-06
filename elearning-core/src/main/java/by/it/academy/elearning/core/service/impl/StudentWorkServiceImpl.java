package by.it.academy.elearning.core.service.impl;

import by.it.academy.elearning.core.model.StudentWork;
import by.it.academy.elearning.core.repository.StudentWorkRepository;
import by.it.academy.elearning.core.service.StudentWorkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StudentWorkServiceImpl extends BaseCrudService<StudentWorkRepository, StudentWork> implements StudentWorkService {

    private final StudentWorkRepository repository;

    @Autowired
    public StudentWorkServiceImpl(StudentWorkRepository repository) {
        super(repository, log);
        this.repository = repository;
    }

    @Override
    public List<StudentWork> findByUser(Long studentId) {
        log.info("Find student work by student id: {}", studentId);
        var works = repository.findByStudentIdWithLessons(studentId);
        log.debug("Find student work by student result: {}", works.size());
        return works;
    }
}
