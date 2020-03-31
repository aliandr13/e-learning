package by.it.academy.elearning.core.service.impl;

import by.it.academy.elearning.core.model.Course;
import by.it.academy.elearning.core.repository.CourseRepository;
import by.it.academy.elearning.core.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class CourseServiceImpl extends BaseCrudService<CourseRepository, Course> implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        super(courseRepository, log);
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findByTeacher(Long teacherId) {
        log.info("Find courses by teacher id: {}", teacherId);
        List<Course> courses = courseRepository.findByTeacherId(teacherId);
        log.debug("Found {} results", courses.size());
        return courses;
    }

    @Override
    public Optional<Course> findByIdWithStudents(Long id) {
        return courseRepository.findByIdWithStudents(id);
    }
}
