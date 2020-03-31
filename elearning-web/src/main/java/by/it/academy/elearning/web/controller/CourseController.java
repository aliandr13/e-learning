package by.it.academy.elearning.web.controller;

import by.it.academy.elearning.core.model.Course;
import by.it.academy.elearning.core.model.Lesson;
import by.it.academy.elearning.core.model.User;
import by.it.academy.elearning.core.service.CourseService;
import by.it.academy.elearning.core.service.UserService;
import by.it.academy.elearning.web.dto.CourseDto;
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

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final UserService userService;
    private final CourseValidator courseValidator;

    @Autowired
    public CourseController(CourseService courseService, UserService userService, CourseValidator courseValidator) {
        this.courseService = courseService;
        this.userService = userService;
        this.courseValidator = courseValidator;
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
        Course course = courseService.findById(id).orElseThrow(NotFoundExceptionException::new);
        CourseDto courseDto = new CourseDto();
        courseDto.setCourse(course);
        Lesson lessons = new Lesson();
        lessons.setDate(LocalDate.now().minusDays(3));
        courseDto.setLessons(Collections.singletonList(lessons));
        courseDto.setStudents(userService.findAll());
        model.addAttribute("course", courseDto);
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
