package by.it.academy.elearning.dao.impl;

import by.it.academy.elearning.dao.CourseDao;
import by.it.academy.elearning.model.Course;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CourseDaoImpl extends AbstractDao<Course> implements CourseDao {

    protected CourseDaoImpl() {
        super(log, Course.class);
    }
}
