package by.it.academy.elearning.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiaryController {


    @GetMapping("/diary")
    public String getDiary(Model model)
    {
        return "diary";
    }

}
