package by.it.academy.elearning.web.servlet.group;

import by.it.academy.elearning.model.Course;
import by.it.academy.elearning.model.Group;
import by.it.academy.elearning.service.GroupService;
import by.it.academy.elearning.service.impl.CourseServiceImp;
import by.it.academy.elearning.web.init.ServerConfig;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@Slf4j
@WebServlet(urlPatterns = "/user/group-add")
public class GroupAddServlet extends HttpServlet {

    private GroupService service;

    public GroupAddServlet(GroupService service) {
        this.service = service;
    }

    public GroupAddServlet() {
        this.service = ServerConfig.getGroupService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("courseSelected", 4L);
        req.setAttribute("courses", CourseServiceImp.getService().getAll());
        req.getRequestDispatcher("/WEB-INF/jsp/group/group-add.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseId = req.getParameter("courseId");
        String startDate = req.getParameter("startDate");


        log.info("course id: {}", courseId);
        log.info("start date: {}", startDate);
        Group group = new Group(null,
                req.getParameter("groupName"),
                new Course(Long.parseLong(courseId), ""),
                LocalDate.parse(startDate));

        service.create(group);
        resp.sendRedirect(req.getContextPath() + "/user/group-list");

    }
}
