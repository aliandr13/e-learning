package by.it.academy.elearning.web.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.stream.Stream;

public class CookieUtils {

    private static final String AUTH_USER_COOKIE_NAME = "e_learning_user_id";
    public static final int MAX_AGE_24H = 24 * 60 * 60;

    public static void storeUserCookie(HttpServletResponse response, Long userId) {
        Cookie cookie = new Cookie(AUTH_USER_COOKIE_NAME, String.valueOf(userId));
        cookie.setMaxAge(MAX_AGE_24H);
        response.addCookie(cookie);
    }

    public static Optional<Long> getAuthUserId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return Optional.empty();
        } else {
            return Stream.of(cookies)
                    .filter(c -> c.getName().equals(AUTH_USER_COOKIE_NAME))
                    .map(Cookie::getValue)
                    .filter(StringUtils::isNumeric)
                    .map(Long::valueOf)
                    .findFirst();
        }
    }

    public static void removeCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(AUTH_USER_COOKIE_NAME, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
