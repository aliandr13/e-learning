package by.it.academy.elearning.core.repository;

import by.it.academy.elearning.core.model.Lesson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson, Long> {

    @Query("select distinct l from Lesson l left join fetch l.studentWorks w left join fetch w.student where l.course.id = :courseId order by l.date")
    List<Lesson> findByCourseIdWithStudentWorks(Long courseId);

}
