package by.it.academy.elearning.core.service.impl;

import by.it.academy.elearning.core.model.Lesson;
import by.it.academy.elearning.core.repository.LessonRepository;
import by.it.academy.elearning.core.service.LessonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class LessonServiceImpl extends BaseCrudService<LessonRepository, Lesson> implements LessonService {

    private final LessonRepository repository;

    @Autowired
    public LessonServiceImpl(LessonRepository repository) {
        super(repository, log);
        this.repository = repository;
    }

    @Override
    public List<Lesson> findByCourse(Long courseId) {
        log.info("Find lessons by course Id: {}", courseId);
        List<Lesson> lessons = repository.findByCourseIdWithStudentWorks(courseId);
        log.debug("Find lessons by course result {}", lessons.size());
        return lessons;
    }

}
