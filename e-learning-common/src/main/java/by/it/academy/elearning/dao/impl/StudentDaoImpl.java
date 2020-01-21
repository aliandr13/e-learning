package by.it.academy.elearning.dao.impl;

import by.it.academy.elearning.dao.StudentDao;
import by.it.academy.elearning.dao.converter.StudentConverter;
import by.it.academy.elearning.model.Group;
import by.it.academy.elearning.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class StudentDaoImpl extends AbstractDao implements StudentDao {

    public static final String INSERT_STUDENT = "INSERT INTO user_info (first_name, middle_name, last_name, phone) VALUES (?,?,?,?)";
    public static final String SELECT_STUDENT_BY_ID = "SELECT * FROM user_info left join `group` g on user_info.group_id = g.group_id WHERE id = ?";
    public static final String SELECT_STUDENT_BY_GROUP_ID = "SELECT * FROM user_info WHERE group_id = ?";
    public static final String SELECT_ALL_STUDENT = "SELECT * FROM user_info left join `group` g on user_info.group_id = g.group_id";
    public static final String UPDATE_STUDENT = "UPDATE user_info  SET first_name = ? , middle_name = ?, last_name = ?, phone = ?, email =?, group_id =? WHERE id = ?";
    public static final String DELETE_STUDENT_BY_ID = "DELETE FROM user_info  WHERE id = ?";

    public StudentDaoImpl() {
        super(log);
    }

    @Override
    public Long create(Student student) throws SQLException {
        ResultSet resultSet = null;
        Long result = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_STUDENT, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getMiddleName());
            statement.setString(3, student.getLastName());
            statement.setString(4, student.getPhone());

            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();

            while (resultSet.next()) {
                result = resultSet.getLong(1);
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    @Override
    public Optional<Student> read(Long id) throws SQLException {
        ResultSet resultSet = null;
        Optional<Student> result = Optional.empty();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_STUDENT_BY_ID)) {

            statement.setLong(1, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                result = Optional.of(StudentConverter.convert(resultSet, true));
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }


    @Override
    public int update(Student student) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT)) {

            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getMiddleName());
            statement.setString(3, student.getLastName());
            statement.setString(4, student.getPhone());
            statement.setString(5, student.getEmail());
            statement.setLong(6, student.getGroup() != null ? student.getGroup().getId() : null);
            statement.setLong(7, student.getId());

            return statement.executeUpdate();
        }
    }

    @Override
    public int delete(Long id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_BY_ID)) {
            statement.setLong(1, id);
            return statement.executeUpdate();
        }
    }

    @Override
    public List<Student> getAll() throws SQLException {
        ResultSet resultSet = null;
        List<Student> result = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_STUDENT)) {

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(StudentConverter.convert(resultSet, true));
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }


    @Override
    public List<Student> getStudentsByGroup(Long groupId) throws SQLException {
        ResultSet resultSet = null;
        List<Student> result = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_STUDENT_BY_GROUP_ID)) {

            statement.setLong(1, groupId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(StudentConverter.convert(resultSet));
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

}
