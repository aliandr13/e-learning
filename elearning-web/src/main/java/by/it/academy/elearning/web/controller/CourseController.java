package by.it.academy.elearning.web.controller;

import by.it.academy.elearning.core.model.Course;
import by.it.academy.elearning.core.model.User;
import by.it.academy.elearning.core.service.CourseService;
import by.it.academy.elearning.core.service.UserService;
import by.it.academy.elearning.web.validation.CourseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String getAll(Model model) {
        List<Course> courses = courseService.findAll();
        model.addAttribute("courses", courses);
        return "courses/list";
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
