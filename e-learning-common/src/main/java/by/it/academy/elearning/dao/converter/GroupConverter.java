package by.it.academy.elearning.dao.converter;

import by.it.academy.elearning.model.Group;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class GroupConverter {

    public static Group convert(ResultSet rs) throws SQLException {
        return new Group(
                rs.getLong("group_id"),
                rs.getString("group_name"),
                CourseConverter.convert(rs),
                rs.getDate("start_date").toLocalDate()
        );
    }

}
