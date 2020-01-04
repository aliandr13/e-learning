package by.it.academy.elearning.web.servlet.group;

import by.it.academy.elearning.model.Group;
import by.it.academy.elearning.service.GroupService;
import by.it.academy.elearning.web.init.ServerConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/user/group-list")
public class GroupListServlet extends HttpServlet {

    public static final String GROUPS_JSP = "/WEB-INF/jsp/group/group-list.jsp";
    public static final String GROUPS = "groups";
    private GroupService service;

    public GroupListServlet() {
        service = ServerConfig.getGroupService();
    }

    public GroupListServlet(GroupService service) {
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Group> groups = service.getAll();
        req.setAttribute(GROUPS, groups);
        req.getRequestDispatcher(GROUPS_JSP).forward(req, resp);
    }
}
