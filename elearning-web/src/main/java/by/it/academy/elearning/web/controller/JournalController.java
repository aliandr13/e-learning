package by.it.academy.elearning.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JournalController {


    @GetMapping("/journal")
    public String journal() {
        return "journal";
    }

}
