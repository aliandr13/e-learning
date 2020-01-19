package by.it.academy.elearning.web.servlet.student;

import by.it.academy.elearning.service.StudentService;
import by.it.academy.elearning.web.init.ServerConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/student-delete")
public class StudentDeleteServlet extends HttpServlet {

    private StudentService service;

    public StudentDeleteServlet(StudentService service) {
        this.service = service;
    }

    public StudentDeleteServlet() {
        this.service = ServerConfig.getStudentService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        deleteStudent(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        deleteStudent(req, resp);
    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long studentId = Long.valueOf(req.getParameter("id"));
        service.delete(studentId);
        resp.sendRedirect(req.getContextPath() + "/user/student-list");
    }
}
