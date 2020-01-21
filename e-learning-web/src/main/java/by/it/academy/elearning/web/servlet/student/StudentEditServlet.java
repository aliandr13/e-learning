package by.it.academy.elearning.web.servlet.student;

import by.it.academy.elearning.model.Group;
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
@WebServlet(urlPatterns = "/user/student-edit")
public class StudentEditServlet extends HttpServlet {

    public static final String GROUPS = "groups";
    public static final String STUDENT = "student";
    public static final String STUDENT_EDIT_JSP = "/WEB-INF/jsp/student/student-edit.jsp";
    public static final String STUDENT_ID = "student_id";
    private StudentService studentService;
    private GroupService groupService;

    public StudentEditServlet(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }

    public StudentEditServlet() {
        this.studentService = ServerConfig.getStudentService();
        this.groupService = ServerConfig.getGroupService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long studentId = Long.valueOf(req.getParameter("id"));
        req.setAttribute(GROUPS, groupService.getAll());
        req.setAttribute(STUDENT_ID, studentId);
        Student student = studentService.getById(studentId).orElseThrow(() -> new IllegalArgumentException("Student not found"));
        req.setAttribute(STUDENT, student);
        if (student.getGroup() != null) {
            req.setAttribute("groupSelected", student.getGroup().getId());
        }
        req.getRequestDispatcher(STUDENT_EDIT_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long courseId = Long.parseLong(req.getParameter("courseId"));

        Student student = new Student(
                Long.valueOf(req.getParameter(STUDENT_ID)),
                req.getParameter("firstName"),
                req.getParameter("middleName"),
                req.getParameter("lastName"),
                req.getParameter("phone"),
                req.getParameter("email"),
                courseId != -1 ? new Group(courseId) : null);

        studentService.update(student);
        resp.sendRedirect(req.getContextPath() + "/user/student-list");

    }
}