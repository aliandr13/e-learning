package by.it.academy.elearning.web.servlet.group;

import by.it.academy.elearning.service.GroupService;
import by.it.academy.elearning.web.init.ServerConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/group-delete")
public class GroupDeleteServlet extends HttpServlet {

    private GroupService service;

    public GroupDeleteServlet(GroupService service) {
        this.service = service;
    }

    public GroupDeleteServlet() {
        this.service = ServerConfig.getGroupService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        deleteGroup(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        deleteGroup(req, resp);
    }

    private void deleteGroup(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long groupId = Long.valueOf(req.getParameter("id"));
        service.delete(groupId);
        resp.sendRedirect(req.getContextPath() + "/user/group-list");
    }
}
