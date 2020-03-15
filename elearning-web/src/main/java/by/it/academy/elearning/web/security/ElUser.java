package by.it.academy.elearning.web.security;

import lombok.Getter;

@Getter
public class ElUser {

    private final String username;
    private final String password;
    private final String[] roles;

    public ElUser(String username, String password, String... roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

}
