package by.it.academy.elearning.dao.converter;

import by.it.academy.elearning.model.Course;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class CourseConverter {

    public static Course convert(ResultSet rs) throws SQLException {
        Course course_name = new Course(
                rs.getString("course_name")
        );
        course_name.setId(rs.getLong("course_id"));
        return course_name;
    }
}
