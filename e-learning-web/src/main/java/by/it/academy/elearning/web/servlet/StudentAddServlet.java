package by.it.academy.elearning.web.servlet;

import by.it.academy.elearning.model.Student;
import by.it.academy.elearning.service.StudentService;
import by.it.academy.elearning.service.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/student-add")
public class StudentAddServlet extends HttpServlet {

    private final StudentService service = StudentServiceImpl.getService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/student/student-add.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = new Student(null,
                req.getParameter("firstName"),
                req.getParameter("middleName"),
                req.getParameter("lastName"),
                req.getParameter("phone"));

        service.add(student);
        resp.sendRedirect(req.getContextPath() + "/student-list");

    }
}
