package by.it.academy.elearning.web.servlet.course;

import by.it.academy.elearning.model.Course;
import by.it.academy.elearning.service.CourseService;
import by.it.academy.elearning.web.config.ServerContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin/course-list")
public class CourseListServlet extends HttpServlet {


    private final CourseService courseService;

    public CourseListServlet(CourseService courseService) {
        this.courseService = courseService;
    }

    public CourseListServlet() {
        this.courseService = ServerContext.getBean(CourseService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Course> courses = courseService.findAll();
        req.setAttribute("courses", courses);
        req.getRequestDispatcher("/WEB-INF/jsp/course/course-list.jsp").forward(req, resp);
    }
}
