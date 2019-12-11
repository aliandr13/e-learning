package by.it.academy.elearning.model;

import lombok.ToString;
import lombok.Value;

@Value
public class User {

    String email;
    @ToString.Exclude
    String password;
    String role;

}
