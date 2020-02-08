package by.it.academy.elearning.service.impl;

import by.it.academy.elearning.dao.CourseDao;
import by.it.academy.elearning.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImp {

    private final CourseDao courseDao;

    @Autowired
    public CourseServiceImp(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public List<Course> getAll() {
        return courseDao.getAll();
    }
}
