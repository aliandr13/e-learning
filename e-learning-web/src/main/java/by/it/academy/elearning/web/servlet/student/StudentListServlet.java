package by.it.academy.elearning.web.servlet.student;

import by.it.academy.elearning.model.Student;
import by.it.academy.elearning.service.StudentService;
import by.it.academy.elearning.web.init.ServerConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/user/student-list")
public class StudentListServlet extends HttpServlet {

    public static final String STUDENTS_JSP = "/WEB-INF/jsp/student/student-list.jsp";
    public static final String STUDENTS = "students";
    public static final String GROUP_ID_PARAM = "group_id";

    private StudentService service;

    public StudentListServlet(StudentService service) {
        this.service = service;
    }

    public StudentListServlet() {
        this.service = ServerConfig.getStudentService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String groupId = req.getParameter(GROUP_ID_PARAM);

        List<Student> students;

        if (groupId != null) {
            students = service.getStudentsByGroup(Long.valueOf(groupId));
        } else {
            students = service.getAllStudents();
        }

        req.setAttribute(STUDENTS, students);
        req.getRequestDispatcher(STUDENTS_JSP).forward(req, resp);
    }
}
