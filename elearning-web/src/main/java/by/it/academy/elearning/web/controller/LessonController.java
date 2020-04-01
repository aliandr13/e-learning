package by.it.academy.elearning.web.controller;

import by.it.academy.elearning.core.model.Lesson;
import by.it.academy.elearning.core.service.CourseService;
import by.it.academy.elearning.core.service.LessonService;
import by.it.academy.elearning.web.exception.NotFoundExceptionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class LessonController {

    private final LessonService lessonService;
    private final CourseService courseService;

    @Autowired
    public LessonController(LessonService lessonService, CourseService courseService) {
        this.lessonService = lessonService;
        this.courseService = courseService;
    }

    @RequestMapping(value = "/courses/{courseId}/lessons", method = RequestMethod.GET)
    public String getLessonPage(@PathVariable("courseId") Long courseId, Model model) {
        model.addAttribute("course", courseService.findByIdWithLessons(courseId)
                .orElseThrow(NotFoundExceptionException::new));
        return "lessons/lessonsList";
    }

    @RequestMapping(value = "/courses/{courseId}/lessons/add", method = RequestMethod.GET)
    public String addLessonPage(Model model, @PathVariable("courseId") Long id) {
        model.addAttribute("courseId", id);
        model.addAttribute("lesson", new Lesson());
        return "lessons/lessonAdd";
    }

    @RequestMapping(value = "/courses/{courseId}/lessons/add", method = RequestMethod.GET)
    public String addLessonSubmit(@PathVariable("courseId") Long id, @Valid @ModelAttribute("lesson") Lesson lesson, BindingResult bindingResult) {

        return "redirect:/courses/" + id + "/lessons";
    }

}
