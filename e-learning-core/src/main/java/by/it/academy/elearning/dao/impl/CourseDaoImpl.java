package by.it.academy.elearning.dao.impl;

import by.it.academy.elearning.dao.CourseDao;
import by.it.academy.elearning.model.Course;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDaoImpl extends AbstractDao<Course> implements CourseDao {

    protected CourseDaoImpl() {
        super(Course.class);
    }
}
