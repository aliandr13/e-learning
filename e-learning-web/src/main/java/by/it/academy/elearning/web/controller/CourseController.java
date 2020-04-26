package by.it.academy.elearning.web.controller;

import by.it.academy.elearning.core.model.Course;
import by.it.academy.elearning.core.model.Lesson;
import by.it.academy.elearning.core.model.StudentWork;
import by.it.academy.elearning.core.model.User;
import by.it.academy.elearning.core.service.CourseService;
import by.it.academy.elearning.core.service.LessonService;
import by.it.academy.elearning.core.service.UserService;
import by.it.academy.elearning.web.exception.ForbiddenException;
import by.it.academy.elearning.web.exception.NotFoundExceptionException;
import by.it.academy.elearning.web.security.ElUser;
import by.it.academy.elearning.web.validation.CourseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final UserService userService;
    private final CourseValidator courseValidator;
    private final LessonService lessonService;

    @Autowired
    public CourseController(CourseService courseService, UserService userService, CourseValidator courseValidator,
                            LessonService lessonService) {
        this.courseService = courseService;
        this.userService = userService;
        this.courseValidator = courseValidator;
        this.lessonService = lessonService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model, Authentication authentication) {
        ElUser user = (ElUser) authentication.getPrincipal();
        List<Course> courses = getCoursesByUser(user);
        model.addAttribute("courses", courses);
        return "courses/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getCourse(@PathVariable("id") Long id, Model model) {
        Course course = courseService.findByIdWithStudents(id).orElseThrow(NotFoundExceptionException::new);
        var lessons = lessonService.findByCourse(course.getId());
        course.setLessons(lessons);
        model.addAttribute("course", course);

        var map = new HashMap<String, StudentWork>();
        for (Lesson lesson : lessons) {
            for (StudentWork studentWork : lesson.getStudentWorks()) {
                map.put(lesson.getId().toString() + "_" + studentWork.getStudent().getId(), studentWork);
            }
        }

        model.addAttribute("map", map);
        return "courses/course";
    }

    private List<Course> getCoursesByUser(ElUser user) {
        if (user.isAdmin()) {
            return courseService.findAll();
        } else if (user.isTeacher()) {
            return courseService.findByTeacher(user.getId());
        } else {
            throw new ForbiddenException();
        }
    }


    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String addPage(Model model) {
        List<User> teachers = userService.getTeachers();
        model.addAttribute("course", new Course());
        model.addAttribute("teachers", teachers);
        return "courses/add";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addSubmit(@Validated @ModelAttribute("course") Course course, BindingResult bindingResult) {
        log.debug("Form result: {}", course);
        courseValidator.validate(course, bindingResult);
        if (bindingResult.hasErrors()) {
            return "courses/add";
        }
        courseService.create(course);
        return "redirect:/courses";
    }


}
