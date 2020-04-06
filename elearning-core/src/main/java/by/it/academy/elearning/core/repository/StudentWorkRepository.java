package by.it.academy.elearning.core.repository;

import by.it.academy.elearning.core.model.StudentWork;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentWorkRepository extends CrudRepository<StudentWork, Long> {

    @Query("from StudentWork w left join fetch w.lesson where w.student.id = :studentId order by w.lesson.date asc")
    List<StudentWork> findByStudentIdWithLessons(Long studentId);

    List<StudentWork> findByLessonId(Long lessonId);

}
