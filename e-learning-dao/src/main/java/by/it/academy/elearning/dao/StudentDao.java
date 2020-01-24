package by.it.academy.elearning.dao;

import by.it.academy.elearning.model.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao extends DAO<Student> {


    List<Student> getStudentsByGroup(Long groupId) throws SQLException;
}
