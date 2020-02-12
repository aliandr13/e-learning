package by.it.academy.elearning.web.servlet.group;

import by.it.academy.elearning.model.Group;
import by.it.academy.elearning.service.GroupService;
import by.it.academy.elearning.web.config.ServerContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin/group-list")
public class GroupListServlet extends HttpServlet {

    private final GroupService groupService;

    public GroupListServlet(GroupService groupService) {
        this.groupService = groupService;
    }

    public GroupListServlet() {
        this.groupService = ServerContext.getBean(GroupService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Group> groups = groupService.findAll();
        req.setAttribute("groups", groups);
        req.getRequestDispatcher("/WEB-INF/jsp/group/group-list.jsp").forward(req, resp);
    }
}
