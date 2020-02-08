package by.it.academy.elearning.service;

import by.it.academy.elearning.model.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseService {

    List<Course> getAll() throws SQLException;

}
