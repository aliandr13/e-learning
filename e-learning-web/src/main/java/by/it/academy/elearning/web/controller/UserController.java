package by.it.academy.elearning.web.controller;

import by.it.academy.elearning.core.model.User;
import by.it.academy.elearning.core.model.UserRole;
import by.it.academy.elearning.core.service.UserService;
import by.it.academy.elearning.web.dto.UserDto;
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
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAllUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users/list";
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String addUserPage(Model model) {
        model.addAttribute("user", new UserDto());
        return "users/add";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addUserSubmit(@Valid @ModelAttribute("user") UserDto user, BindingResult bindingResult) {
        log.info("Form result: {}", user);
        Optional<UserRole> userRole = UserRole.getByKey(user.getRole());
        if (userRole.isEmpty()) {
            bindingResult.reject("role", "invalid user role");
        }
        if (bindingResult.hasErrors()) {
            return "users/add";
        }
        userService.create(User.builder()
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(userRole.get())
                .phone(user.getPhone()).build());
        return "redirect:/users";
    }

}
