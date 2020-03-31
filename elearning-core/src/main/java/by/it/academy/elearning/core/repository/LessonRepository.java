package by.it.academy.elearning.core.repository;

import by.it.academy.elearning.core.model.Lesson;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson, Long> {

    List<Lesson> findByCourse_Id(Long id);

}
