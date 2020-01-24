package by.it.academy.elearning.dao.impl;

import by.it.academy.elearning.dao.GroupDao;
import by.it.academy.elearning.dao.converter.GroupConverter;
import by.it.academy.elearning.exception.NotImplementedException;
import by.it.academy.elearning.model.Group;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class GroupDaoImpl extends AbstractDao implements GroupDao {

    public static final String SELECT_ALL = "SELECT * FROM `group` join course c on `group`.course_id = c.course_id";
    public static final String INSERT_GROUP = "INSERT INTO `group` (GROUP_NAME, COURSE_ID, START_DATE) VALUES (?, ?, ?)";
    public static final String DELETE_GROUP_BY_ID = "DELETE FROM `group` WHERE GROUP_ID = ?";


    public GroupDaoImpl() {
        super(log);
    }

    @Override
    public Long create(Group group) throws SQLException {
        ResultSet resultSet = null;
        Long result = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_GROUP, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, group.getName());
            statement.setLong(2, group.getCourse().getId());
            statement.setDate(3, Date.valueOf(group.getStartDate()));

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
    public Optional<Group> read(Long id) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public int update(Group group) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public int delete(Long id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_GROUP_BY_ID)) {
            statement.setLong(1, id);
            return statement.executeUpdate();
        }
    }

    @Override
    public List<Group> getAll() throws SQLException {
        ResultSet resultSet = null;
        List<Group> groups = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                groups.add(GroupConverter.convert(resultSet));
            }
        } finally {
            closeQuietly(resultSet);
        }
        return groups;
    }

}
