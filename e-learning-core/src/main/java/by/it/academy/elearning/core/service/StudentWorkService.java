package by.it.academy.elearning.core.service;

import by.it.academy.elearning.core.model.StudentWork;

import java.util.List;

public interface StudentWorkService extends CrudService<StudentWork> {

    List<StudentWork> findByUser(Long userId);
}
