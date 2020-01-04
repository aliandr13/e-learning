package by.it.academy.elearning.dao.converter;

import by.it.academy.elearning.model.Course;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class CourseConverter {

    public static Course convert(ResultSet rs) throws SQLException {
        return new Course(
                rs.getLong("course_id"),
                rs.getString("course_name")
        );
    }
}
