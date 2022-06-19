package ru.pdasilem.spring_rest.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pdasilem.spring_rest.model.User;

@Controller
public class UserController {


    @RequestMapping("/user")
    public String userInfo(ModelMap model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        return "user";
    }
}
