package by.it.academy.elearning.core.repository;

import by.it.academy.elearning.core.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
