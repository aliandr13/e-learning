package by.it.academy.elearning.web.servlet;

import by.it.academy.elearning.model.UserAuth;
import by.it.academy.elearning.service.UserService;
import by.it.academy.elearning.web.config.ServerContext;
import by.it.academy.elearning.web.dto.UserAccount;
import by.it.academy.elearning.web.util.CookieUtils;
import by.it.academy.elearning.web.util.SessionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private UserService userService;

    public LoginServlet(UserService userService) {
        this.userService = userService;
    }

    public LoginServlet() {
        this.userService = ServerContext.getBean(UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        boolean remember = "Y".equals(req.getParameter("rememberMe"));

        String errorMsg = "";
        boolean hasError = false;

        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            hasError = true;
            errorMsg = "UserName and password should not be empty; ";
        } else {
            Optional<UserAuth> user = userService.findUserByLoginAndPassword(email, password);
            if (user.isEmpty()) {
                hasError = true;
                errorMsg = "Invalid user name or password";
            } else {
                SessionUtils.setUserSession(req, new UserAccount(user.get()));
                if (remember) {
                    CookieUtils.storeUserCookie(resp, user.get().getId());
                }
            }
        }

        if (hasError) {
            req.setAttribute("errorString", errorMsg);
            req.setAttribute("userName", email);
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/home");
        }

    }
}
