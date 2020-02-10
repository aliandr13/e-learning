package by.it.academy.elearning.web.servlet.user;

import by.it.academy.elearning.model.UserAuth;
import by.it.academy.elearning.service.UserService;
import by.it.academy.elearning.web.config.ServerContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/user/user-list")
@Slf4j
public class UserListServlet extends HttpServlet {

    private final UserService userService;

    public UserListServlet(UserService userService) {
        this.userService = userService;
    }

    public UserListServlet() {
        this.userService = ServerContext.getBean(UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserAuth> userList = userService.findAll();
        req.setAttribute("users", userList);
        req.getRequestDispatcher("/WEB-INF/jsp/user/user-list.jsp").forward(req, resp);
    }
}
