package by.it.academy.elearning.web.servlet.student;

import by.it.academy.elearning.model.Student;
import by.it.academy.elearning.service.StudentService;
import by.it.academy.elearning.service.impl.CourseServiceImp;
import by.it.academy.elearning.service.impl.StudentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/student-add")
public class StudentAddServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentAddServlet.class);
    private final StudentService service = StudentServiceImpl.getService();

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

        LOGGER.info("course id: {}", courseId);
        log("course id: " + courseId);
        Student student = new Student(null,
                req.getParameter("firstName"),
                req.getParameter("middleName"),
                req.getParameter("lastName"),
                req.getParameter("phone"));

        service.add(student);
        resp.sendRedirect(req.getContextPath() + "/user/student-list");

    }
}
