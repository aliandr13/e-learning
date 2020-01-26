package by.it.academy.elearning.dao.converter;

import by.it.academy.elearning.model.Course;
import by.it.academy.elearning.model.Group;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class GroupConverter {

    public static Group convert(ResultSet rs) throws SQLException {
        return convert(rs, false);
    }

    public static Group convert(ResultSet rs, boolean withCourse) throws SQLException {
        Course course = null;
        if (withCourse) {
            course = CourseConverter.convert(rs);
        }
        Date startDate = rs.getDate("start_date");
        Long group_id = rs.getLong("group_id");
        String groupName = rs.getString("group_name");
        if (groupName != null) {


            return new Group(
                    groupName,
                    startDate != null ? startDate.toLocalDate() : null
                    , course
            );
        }
        return null;
    }

}
