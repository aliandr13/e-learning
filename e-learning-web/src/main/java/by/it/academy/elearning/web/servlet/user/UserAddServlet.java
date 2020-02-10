package by.it.academy.elearning.web.servlet.user;

import by.it.academy.elearning.model.User;
import by.it.academy.elearning.service.UserService;
import by.it.academy.elearning.web.config.ServerContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/user-add")
public class UserAddServlet extends HttpServlet {

    private final UserService userService;

    public UserAddServlet(UserService userService) {
        this.userService = userService;
    }

    public UserAddServlet() {
        this.userService = ServerContext.getBean(UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/user/user-add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = User.builder()
                .name(req.getParameter("name"))
                .middleName(req.getParameter("middleName"))
                .surname(req.getParameter("surname"))
                .email(req.getParameter("email"))
                .phone(req.getParameter("phone"))
                .build();

        userService.createUser(user);

        resp.sendRedirect(req.getContextPath() + "/admin/user-list");
    }
}
