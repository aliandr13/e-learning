package by.it.academy.elearning.dao.converter;

import by.it.academy.elearning.model.Group;
import by.it.academy.elearning.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class StudentConverter {

    public static Student convert(ResultSet rs) throws SQLException {
        return convert(rs, false);
    }

    public static Student convert(ResultSet rs, boolean withGroup) throws SQLException {
        long userId = rs.getLong("id");
        String firstName = rs.getString("first_name");
        String middleName = rs.getString("middle_name");
        String lastName = rs.getString("last_name");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        Group group = null;
        if (withGroup) {
            group = GroupConverter.convert(rs);
        }
        return new Student(userId, firstName, middleName, lastName, phone, email, group);
    }


}
