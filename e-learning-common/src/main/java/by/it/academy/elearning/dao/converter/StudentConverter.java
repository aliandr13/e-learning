package by.it.academy.elearning.dao.converter;

import by.it.academy.elearning.model.Group;
import by.it.academy.elearning.model.Student;
import by.it.academy.elearning.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class StudentConverter {

    public static Student convert(ResultSet rs) throws SQLException {
        long userId = rs.getLong("id");
        String firstName = rs.getString("first_name");
        String middleName = rs.getString("middle_name");
        String lastName = rs.getString("last_name");
        String phone = rs.getString("phone");
        return new Student(userId, firstName, middleName, lastName, phone, "", new Group());
    }

}
