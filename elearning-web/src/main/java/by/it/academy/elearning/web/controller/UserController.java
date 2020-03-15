package by.it.academy.elearning.web.controller;

import by.it.academy.elearning.core.model.ELUser;
import by.it.academy.elearning.core.service.ElUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@RequestMapping("/users")
public class UserController {

    private final ElUserService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(ElUserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAllUsers(Model model) {
        List<ELUser> users = userService.findAll();
        model.addAttribute("users", users);
        return "users/list";
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String addUserPage(Model model) {
        model.addAttribute("user", new ELUser());
        return "users/add";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addUserSubmit(@Valid @ModelAttribute("user") ELUser user, BindingResult bindingResult) {
        log.info("Form result: {}", user);
        if (bindingResult.hasErrors()) {
            return "users/add";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.create(user);
        return "redirect:/users";
    }

}
