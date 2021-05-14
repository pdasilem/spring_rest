package ru.pdasilem.spring_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pdasilem.spring_rest.model.UserModel;
import ru.pdasilem.spring_rest.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String showUser1(@AuthenticationPrincipal UserModel userModel, Model model) {
        UserModel user = userService.showById(userModel.getId());
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") long id, Model model) {
        UserModel user = userService.showById(id);
        model.addAttribute("user", user);
        return "user";
    }


}
