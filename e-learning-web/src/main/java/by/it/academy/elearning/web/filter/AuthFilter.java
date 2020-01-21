package by.it.academy.elearning.web.filter;

import by.it.academy.elearning.web.util.SessionUtils;
import by.it.academy.elearning.web.dto.UserAccount;
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
import java.util.Optional;

//@WebFilter(urlPatterns = {"/user/*", "/admin/*"}, dispatcherTypes = DispatcherType.REQUEST)
public class AuthFilter extends HttpFilter {

    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        logger.debug("Auth filter");
        Optional<UserAccount> userAccount = SessionUtils.getUserAccount(req);
        if (userAccount.isPresent()) {
            super.doFilter(req, res, chain);
        } else {
            res.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
