package by.it.academy.elearning.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String helloPage(ModelAndView modelAndView) {
        modelAndView.addObject("hello", "hello Alex!");
        return "hello";
    }

}
