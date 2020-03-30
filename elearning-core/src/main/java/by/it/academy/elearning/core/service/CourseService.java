package by.it.academy.elearning.core.service;

import by.it.academy.elearning.core.model.Course;

import java.util.List;

public interface CourseService extends CrudService<Course> {

    List<Course> findByTeacher(Long teacherId);

}
