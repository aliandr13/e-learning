package by.it.academy.elearning.web.servlet.group;

import by.it.academy.elearning.model.Course;
import by.it.academy.elearning.model.Group;
import by.it.academy.elearning.service.CourseService;
import by.it.academy.elearning.service.GroupService;
import by.it.academy.elearning.web.config.ServerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@Slf4j
@WebServlet(urlPatterns = "/admin/group-add")
public class GroupAddServlet extends HttpServlet {

    private final GroupService groupService;
    private final CourseService courseService;

    public GroupAddServlet(GroupService groupService, CourseService courseService) {
        this.groupService = groupService;
        this.courseService = courseService;
    }

    public GroupAddServlet() {
        this.groupService = ServerContext.getBean(GroupService.class);
        this.courseService = ServerContext.getBean(CourseService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("courses", courseService.findAll());
        req.getRequestDispatcher("/WEB-INF/jsp/group/group-add.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("groupName");
        String startDateStr = req.getParameter("startDate");
        String finishDateStr = req.getParameter("finishDate");
        String courseIdStr = req.getParameter("courseId");
        log.debug("Create new group name: {}, start {} finish {}, course id {}", name, startDateStr, finishDateStr, courseIdStr);

        Group group = new Group();
        group.setName(name);
        group.setStartDate(LocalDate.parse(startDateStr));
        if (StringUtils.isNoneEmpty(finishDateStr)) {
            group.setFinishDate(LocalDate.parse(finishDateStr));
        }

        int courseId = Integer.parseInt(courseIdStr);
        Course course = courseService.findById(courseId).orElseThrow(() -> new IllegalArgumentException("Invalid course id"));
        group.setCourse(course);

        groupService.create(group);
        resp.sendRedirect(req.getContextPath() + "/admin/group-list");
    }
}
