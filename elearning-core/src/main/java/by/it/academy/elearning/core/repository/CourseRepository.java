package by.it.academy.elearning.core.repository;

import by.it.academy.elearning.core.model.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Long> {

    List<Course> findByTeacherId(Long teacherId);
}
