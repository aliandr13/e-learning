package by.it.academy.elearning.core.service;

import by.it.academy.elearning.core.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService extends CrudService<Course> {

    List<Course> findByTeacher(Long teacherId);


    Optional<Course> findByIdWithStudents(Long courseId);

    Optional<Course> findByIdWithLessons(Long courseId);

}
