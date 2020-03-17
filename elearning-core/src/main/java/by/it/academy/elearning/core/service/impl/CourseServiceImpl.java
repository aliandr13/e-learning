package by.it.academy.elearning.core.service.impl;

import by.it.academy.elearning.core.model.Course;
import by.it.academy.elearning.core.repository.CourseRepository;
import by.it.academy.elearning.core.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class CourseServiceImpl extends BaseCrudService<CourseRepository, Course> implements CourseService {

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        super(courseRepository, log);
    }

}
