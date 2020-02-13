package by.it.academy.elearning.web.servlet.group;

import by.it.academy.elearning.exception.ELearningException;
import by.it.academy.elearning.model.Group;
import by.it.academy.elearning.model.User;
import by.it.academy.elearning.service.GroupService;
import by.it.academy.elearning.service.UserService;
import by.it.academy.elearning.web.config.ServerContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/teacher/group")
public class GroupServlet extends HttpServlet {

    private final UserService userService;
    private final GroupService groupService;

    public GroupServlet(UserService userService, GroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
    }

    public GroupServlet() {
        this.groupService = ServerContext.getBean(GroupService.class);
        this.userService = ServerContext.getBean(UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long groupId = Long.parseLong(req.getParameter("id"));
        Group group = groupService.findById(groupId).orElseThrow(() -> new ELearningException("Group not found by id " + groupId));
        List<User> users = userService.findStudentsByGroup(groupId);
        req.setAttribute("group", group);
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/jsp/group/group.jsp").forward(req, resp);
    }
}
