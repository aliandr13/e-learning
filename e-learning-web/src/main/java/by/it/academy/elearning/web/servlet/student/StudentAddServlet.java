package by.it.academy.elearning.web.servlet.student;

import by.it.academy.elearning.model.Student;
import by.it.academy.elearning.service.GroupService;
import by.it.academy.elearning.service.StudentService;
import by.it.academy.elearning.web.init.ServerConfig;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = "/user/student-add")
public class StudentAddServlet extends HttpServlet {

    public static final String GROUPS = "groups";
    public static final String STUDENT_ADD_JSP = "/WEB-INF/jsp/student/student-add.jsp";
    private StudentService studentService;
    private GroupService groupService;

    public StudentAddServlet(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    public StudentAddServlet() {
        this.studentService = ServerConfig.getStudentService();
        this.groupService = ServerConfig.getGroupService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(GROUPS, groupService.getAll());
        req.getRequestDispatcher(STUDENT_ADD_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseId = req.getParameter("courseId");

        log.info("course id: {}", courseId);
        Student student = new Student(
                req.getParameter("firstName"),
                req.getParameter("middleName"),
                req.getParameter("lastName"),
                req.getParameter("phone"),
                req.getParameter("email"),
                0.0f, null, null);

        studentService.add(student);
        resp.sendRedirect(req.getContextPath() + "/user/student-list");

    }
}
