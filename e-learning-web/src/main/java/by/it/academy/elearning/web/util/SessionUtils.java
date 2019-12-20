package by.it.academy.elearning.web.util;

import by.it.academy.elearning.web.dto.UserAccount;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class SessionUtils {

    private static final String SESSION_ATTR_NAME = "auth_user";

    public static void setUserSession(HttpServletRequest request, UserAccount user) {
        request.getSession().setAttribute(SESSION_ATTR_NAME, user);
    }

    public static Optional<UserAccount> getUserAccount(HttpServletRequest request) {
        return Optional.ofNullable((UserAccount) request.getSession().getAttribute(SESSION_ATTR_NAME));
    }
}
