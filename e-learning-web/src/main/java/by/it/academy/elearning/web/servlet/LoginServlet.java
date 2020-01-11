package by.it.academy.elearning.web.servlet;

import by.it.academy.elearning.model.User;
import by.it.academy.elearning.service.UserService;
import by.it.academy.elearning.service.impl.UserServiceImpl;
import by.it.academy.elearning.web.dto.UserAccount;
import by.it.academy.elearning.web.util.CookieUtils;
import by.it.academy.elearning.web.util.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    public static final String LOGIN_JSP = "/WEB-INF/jsp/login.jsp";
    public static final String ERROR_STRING_ATTR_NAME = "errorString";
    public static final String SHOULD_NOT_BE_EMPTY = "UserName and password should not be empty; ";
    public static final String USER_ATTR_NAME = "userName";
    public static final String INVALID_USER_NAME = "Invalid user name or password";
    public static final String PASSWORD = "password";
    public static final String REMEMBER_ME = "rememberMe";
    public static final String HOME = "/home";

    private final UserService userService;

    public LoginServlet() {
        this.userService = UserServiceImpl.getInstance();
    }

    public LoginServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(LOGIN_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter(USER_ATTR_NAME);
        String password = req.getParameter(PASSWORD);
        boolean remember = "Y".equals(req.getParameter(REMEMBER_ME));

        String errorMsg = "";
        boolean hasError = false;

        if (userName == null || userName.length() == 0 || password == null || password.length() == 0) {
            hasError = true;
            errorMsg = SHOULD_NOT_BE_EMPTY;
        } else {
            Optional<User> user = userService.findUserByLoginAndPassword(userName, password);
            if (user.isEmpty()) {
                hasError = true;
                errorMsg = INVALID_USER_NAME;
            } else {
                SessionUtils.setUserSession(req, new UserAccount(user.get()));
                if (remember) {
                    CookieUtils.storeUserCookie(resp, user.get().getId());
                }
            }
        }

        if (hasError) {
            req.setAttribute(ERROR_STRING_ATTR_NAME, errorMsg);
            req.setAttribute(USER_ATTR_NAME, userName);
            req.getRequestDispatcher(LOGIN_JSP).forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + HOME);
        }

    }
}
