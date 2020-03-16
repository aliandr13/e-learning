package by.it.academy.elearning.core.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

public enum UserRole {

    ADMIN(1),
    STUDENT(2),
    TEACHER(3);

    @Getter
    private final int key;

    UserRole(int key) {
        this.key = key;
    }

    public static Optional<UserRole> getByKey(final int key) {
        return Arrays.stream(UserRole.values()).filter(r -> r.key == key).findAny();
    }
}
