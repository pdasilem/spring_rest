
package ru.pdasilem.spring_rest.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pdasilem.spring_rest.model.User;
import ru.pdasilem.spring_rest.service.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUsers(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        model.addAttribute("users", userService.getUsers());
        return "admin";
    }
}

