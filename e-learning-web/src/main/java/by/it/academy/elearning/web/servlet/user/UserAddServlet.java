package by.it.academy.elearning.web.servlet.user;

import by.it.academy.elearning.model.Group;
import by.it.academy.elearning.model.User;
import by.it.academy.elearning.service.GroupService;
import by.it.academy.elearning.service.UserService;
import by.it.academy.elearning.web.config.ServerContext;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin/user-add")
public class UserAddServlet extends HttpServlet {

    private final UserService userService;
    private final GroupService groupService;

    public UserAddServlet(UserService userService, GroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
    }

    public UserAddServlet() {
        this.groupService = ServerContext.getBean(GroupService.class);
        this.userService = ServerContext.getBean(UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Group> groups = groupService.findAll();
        Long groupId = Long.parseLong(req.getParameter("groupId"));
        req.setAttribute("selectedGroup", groupId);
        req.setAttribute("groups", groups);
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


        String groupIdStr = req.getParameter("groupId");
        if (StringUtils.isNotEmpty(groupIdStr)) {
            long groupId = Long.parseLong(groupIdStr);
            userService.create(user, groupId);
        }
        userService.create(user);

        if (StringUtils.isEmpty(groupIdStr) || "-1".equals(groupIdStr)) {
            resp.sendRedirect(req.getContextPath() + "/admin/user-list");
        } else {
            resp.sendRedirect(req.getContextPath() + "/teacher/group?id=" + groupIdStr);
        }
    }
}
