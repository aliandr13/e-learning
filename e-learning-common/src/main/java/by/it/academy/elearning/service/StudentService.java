package by.it.academy.elearning.service;

import by.it.academy.elearning.model.Student;

import java.util.List;
import java.util.Optional;

/**
 * Student service interface
 */
public interface StudentService {

    /**
     * Gets all students
     *
     * @return list of found students
     */
    List<Student> getAllStudents();

    /**
     * Gets student by id
     *
     * @param id id
     * @return Optional of student
     */
    Optional<Student> getById(Long id);


    /**
     * Add new student
     *
     * @param student student to add
     * @return student with generated id
     */
    Student add(Student student);

    /**
     * Deletes student by Id
     *
     * @param id to delete
     */
    void delete(Long id);

    /**
     * Updates student
     *
     * @param student to update
     * @return updated student
     */
    Student update(Student student);

}

