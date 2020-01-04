package by.it.academy.elearning.dao.impl;

import by.it.academy.elearning.dao.GroupDao;
import by.it.academy.elearning.dao.converter.GroupConverter;
import by.it.academy.elearning.exception.NotImplementedException;
import by.it.academy.elearning.model.Group;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class GroupDaoImpl extends AbstractDao implements GroupDao {

    public static final String SELECT_ALL = "SELECT * FROM `group` join course c on `group`.course_id = c.course_id";

    public GroupDaoImpl() {
        super(log);
    }

    @Override
    public Long create(Group group) throws SQLException {
        throw new NotImplementedException();
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
        throw new NotImplementedException();
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
