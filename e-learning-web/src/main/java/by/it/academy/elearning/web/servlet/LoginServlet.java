package by.it.academy.elearning.web.servlet;

import by.it.academy.elearning.model.User;
import by.it.academy.elearning.service.UserService;
import by.it.academy.elearning.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String rememberMeStr = req.getParameter("remember");
        boolean remember = "Y".equals(rememberMeStr);

        String errorMsg = "";
        boolean hasError = false;

        if (email == null || email.length() == 0 || password == null || password.length() == 0) {
            hasError = true;
            errorMsg = "UserName and password should not be empty; ";
        } else {
            Optional<User> user = userService.findUser(email, password);
            if (user.isEmpty()) {
                hasError = true;
                errorMsg = "Invalid user name or password";
            } else {
                req.getSession().setAttribute("user", user.get());
            }
        }

        if (hasError) {
            req.setAttribute("errorString", errorMsg);
            req.setAttribute("email", email);
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/home");
        }

    }
}
