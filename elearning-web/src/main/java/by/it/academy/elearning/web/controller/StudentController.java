package by.it.academy.elearning.web.controller;

import by.it.academy.elearning.core.model.Student;
import by.it.academy.elearning.core.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getStudents(Model model) {
        List<Student> all = studentService.findAll();
        model.addAttribute("students", all);
        return "students";
    }

}
