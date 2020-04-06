package by.it.academy.elearning.web.controller;

import by.it.academy.elearning.core.model.StudentWork;
import by.it.academy.elearning.core.service.StudentWorkService;
import by.it.academy.elearning.web.security.ElUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DiaryController {

    private final StudentWorkService studentWorkService;

    @Autowired
    public DiaryController(StudentWorkService studentWorkService) {
        this.studentWorkService = studentWorkService;
    }

    @GetMapping("/diary")
    public String getDiary(Model model, Authentication authentication) {
        ElUser user = (ElUser) authentication.getPrincipal();
        List<StudentWork> works = studentWorkService.findByUser(user.getId());
        model.addAttribute("works", works);
        return "diary";
    }

}
