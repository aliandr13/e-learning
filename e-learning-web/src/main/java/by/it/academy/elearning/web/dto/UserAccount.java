package by.it.academy.elearning.web.dto;

import by.it.academy.elearning.model.Role;
import by.it.academy.elearning.model.User;
import lombok.Builder;
import lombok.Value;

@Value
public class UserAccount {

    private final String userId;
    private final String login;
    private final Role role;

    public UserAccount(User user) {
        this.userId = String.valueOf(user.getId());
        this.login = user.getEmail();
        this.role = user.getRole();
    }

}
