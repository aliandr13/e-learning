package by.it.academy.elearning.web.servlet.student;

import by.it.academy.elearning.model.Group;
import by.it.academy.elearning.model.Student;
import by.it.academy.elearning.service.StudentService;
import by.it.academy.elearning.service.impl.CourseServiceImp;
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

    private StudentService service;

    public StudentAddServlet(StudentService service) {
        this.service = service;
    }

    public StudentAddServlet() {
        this.service = ServerConfig.getStudentService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("courseSelected", 4L);
        req.setAttribute("courses", CourseServiceImp.getService().getAll());
        req.getRequestDispatcher("/WEB-INF/jsp/student/student-add.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseId = req.getParameter("courseId");

        log.info("course id: {}", courseId);
        Student student = new Student(null,
                req.getParameter("firstName"),
                req.getParameter("middleName"),
                req.getParameter("lastName"),
                req.getParameter("phone")
                , "", new Group());

        service.add(student);
        resp.sendRedirect(req.getContextPath() + "/user/student-list");

    }
}
