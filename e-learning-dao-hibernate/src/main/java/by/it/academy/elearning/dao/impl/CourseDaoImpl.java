package by.it.academy.elearning.dao.impl;

import by.it.academy.elearning.dao.CourseDao;
import by.it.academy.elearning.model.Course;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
public class CourseDaoImpl extends AbstractDao<Course> implements CourseDao {

    protected CourseDaoImpl() {
        super(log, Course.class);
    }


    @Override
    public List<Course> getAll(){
        return null;
    }
}
