package by.it.academy.elearning.web.filter;

import by.it.academy.elearning.model.User;
import by.it.academy.elearning.service.UserService;
import by.it.academy.elearning.web.config.ServerContext;
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

//@WebFilter(urlPatterns = "/*", dispatcherTypes = DispatcherType.REQUEST)
public class CookieFilter extends HttpFilter {

    private static final Logger logger = LoggerFactory.getLogger(CookieFilter.class);
    private final UserService userService;

    public CookieFilter(UserService userService) {
        this.userService = userService;
    }

    public CookieFilter() {
        this.userService = ServerContext.getBean(UserService.class);
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (SessionUtils.getUserAccount(req).isEmpty()) {
            CookieUtils.getAuthUserId(req)
                    .flatMap(userService::findById)
                    .ifPresent(u -> addUserToSession(req, u));
        }
        super.doFilter(req, res, chain);
    }

    private void addUserToSession(HttpServletRequest req, User user) {
        UserAccount userAccount = new UserAccount(user);
        SessionUtils.setUserSession(req, userAccount);
        logger.info("User restored from cookie; {}", userAccount);
    }
}
