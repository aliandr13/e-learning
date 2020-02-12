package by.it.academy.elearning.web.filter;

import by.it.academy.elearning.model.User;
import by.it.academy.elearning.service.UserService;
import by.it.academy.elearning.service.impl.UserServiceImpl;
import by.it.academy.elearning.web.dto.UserAccount;
import by.it.academy.elearning.web.util.CookieUtils;
import by.it.academy.elearning.web.util.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", dispatcherTypes = DispatcherType.REQUEST)
public class CookieFilter extends HttpFilter {

    private static final Logger logger = LoggerFactory.getLogger(CookieFilter.class);
    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (SessionUtils.getUserAccount(req).isEmpty()) {
            CookieUtils.getAuthUserId(req)
                    .flatMap(userService::findUserById)
                    .ifPresent(u -> addUserToSession(req, u));

        }
        super.doFilter(req, res, chain);
    }

    private void addUserToSession(HttpServletRequest req, User u) {
        UserAccount user = new UserAccount(u);
        SessionUtils.setUserSession(req, user);
        logger.info("User restored from cookie; {}", user);
    }
}
