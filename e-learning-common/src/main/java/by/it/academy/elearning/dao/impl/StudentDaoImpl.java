package by.it.academy.elearning.dao.impl;

import by.it.academy.elearning.dao.StudentDao;
import by.it.academy.elearning.model.Group;
import by.it.academy.elearning.model.Student;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDaoImpl extends AbstractDao implements StudentDao {

    private static final StudentDaoImpl INSTANCE = new StudentDaoImpl();

    public static final String INSERT_STUDENT = "INSERT INTO student (first_name, middle_name, last_name, phone) VALUE (?,?,?,?)";
    public static final String SELECT_STUDENT_BY_ID = "SELECT * FROM student WHERE id = ?";
    public static final String SELECT_ALL_STUDENT = "SELECT * FROM user_info";
    public static final String UPDATE_STUDENT = "UPDATE student  SET first_name = ? , middle_name = ?, last_name = ?, phone = ? WHERE id = ?";
    public static final String DELETE_STUDENT_BY_ID = "DELETE FROM student WHERE id = ?";

    private StudentDaoImpl() {
        super(LoggerFactory.getLogger(StudentDaoImpl.class));
    }

    public static StudentDao getInstance() {
        return INSTANCE;
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
                result = Optional.of(mapStudent(resultSet));
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
            statement.setLong(5, student.getId());

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
                result.add(mapStudent(resultSet));
            }
        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    private Student mapStudent(ResultSet resultSet) throws SQLException {
        long userId = resultSet.getLong("id");
        String firstName = resultSet.getString("first_name");
        String middleName = resultSet.getString("middle_name");
        String lastName = resultSet.getString("last_name");
        String phone = resultSet.getString("phone");
        return new Student(userId, firstName, middleName, lastName, phone, "", new Group());
    }
}
