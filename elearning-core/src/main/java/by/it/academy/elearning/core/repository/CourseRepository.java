package by.it.academy.elearning.core.repository;

import by.it.academy.elearning.core.model.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends CrudRepository<Course, Long> {

    List<Course> findByTeacherId(Long teacherId);

    @Query("from Course c left join fetch c.students where c.id = :id")
    Optional<Course> findByIdWithStudents(@Param("id") Long id);
}
