package by.it.academy.elearning.core.repository.utils;

import by.it.academy.elearning.core.model.UserRole;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.operation.Operation;

public final class CommonOperations {

    public static final String EMAIL_1 = "email_1";
    public static final String NAME_1 = "name_1";
    public static final String SURNAME_1 = "surname_1";
    public static final String PASS_1 = "pass_1";
    public static final UserRole ROLE_1 = UserRole.TEACHER;
    public static final String ROLE_PROPERTY = "role";
    public static final String PASSWORD_PROPERTY = "password";
    public static final String NAME_PROPERTY = "name";
    public static final String SURNAME_PROPERTY = "surname";
    public static final String EMAIL_PROPERTY = "email";
    public static final String EMAIL_2 = "email_2";
    public static final String NAME_2 = "name_2";
    public static final String SURNAME_2 = "surname_2";
    public static final String PASS_2 = "pass_2";
    public static final UserRole ROLE_2 = UserRole.STUDENT;


    public static final Operation DELETE_ALL = Operations.deleteAllFrom("user");

    public static final Operation ADD_USERS = Operations.insertInto("user")
            .columns("id", EMAIL_PROPERTY, PASSWORD_PROPERTY, NAME_PROPERTY, SURNAME_PROPERTY, ROLE_PROPERTY)
            .values(1L, EMAIL_1, PASS_1, NAME_1, SURNAME_1, ROLE_1.name())
            .values(2L, EMAIL_2, PASS_2, NAME_2, SURNAME_2, ROLE_2.name())
            .build();
}
