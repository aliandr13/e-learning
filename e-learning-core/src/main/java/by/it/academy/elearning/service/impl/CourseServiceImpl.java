package by.it.academy.elearning.service.impl;

import by.it.academy.elearning.dao.CourseDao;
import by.it.academy.elearning.model.Course;
import by.it.academy.elearning.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public Course create(Course course) {
        return courseDao.create(course);
    }

    @Override
    public Optional<Course> findById(Number id) {
        return courseDao.find(id);
    }

    @Override
    public void delete(Course course) {
        courseDao.create(course);
    }

    @Override
    public List<Course> findAll() {
        log.info("Find all Courses");
        List<Course> courses = courseDao.findAll();
        log.debug("Find all courses result: {}", courses);
        return courses;
    }
}
