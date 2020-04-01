package by.it.academy.elearning.core.service;

import by.it.academy.elearning.core.model.Lesson;

import java.util.List;

public interface LessonService extends CrudService<Lesson> {

    List<Lesson> findByCourse(Long courseId);
}
