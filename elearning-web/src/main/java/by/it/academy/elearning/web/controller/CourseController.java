package by.it.academy.elearning.web.controller;

import by.it.academy.elearning.core.model.Course;
import by.it.academy.elearning.core.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;


    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        List<Course> courses = courseService.findAll();
        model.addAttribute("courses", courses);
        return "courses/list";
    }


    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String addPage(Model model) {
        model.addAttribute("course", new Course());
        return "courses/add";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addSubmit(@Valid @ModelAttribute("course") Course course, BindingResult bindingResult) {
        log.debug("Form result: {}", course);
        if (bindingResult.hasErrors()) {
            return "courses/add";
        }
        courseService.create(course);
        return "redirect:/courses";
    }


}
