package ru.pdasilem.spring_rest.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pdasilem.spring_rest.model.Roles;
import ru.pdasilem.spring_rest.model.UserModel;
import ru.pdasilem.spring_rest.service.RoleService;
import ru.pdasilem.spring_rest.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;


    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String showAllUsers(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserModel user = (UserModel) auth.getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("users", userService.usersList());
        model.addAttribute("newUser", new UserModel());
        List<Roles> roles = roleService.rolesList();
        model.addAttribute("roles", roles);
        return "users";
    }
//
//    @GetMapping("/newuser")
//    public String newUser(Model model) {
//        model.addAttribute("newUser", new UserModel());
//        return "newuser";
//    }
//
//
//    @PostMapping("/newuser")
//    public String createUser(@ModelAttribute("userModel") UserModel userModel, @RequestParam(value = "roles") Long[] role) {
//        Set<Roles> roleSet = new HashSet<>();
//        for (Long roles : role) {
//            roleSet.add(roleService.findRoleById(roles));
//        }
//        userModel.setRoles(roleSet);
//        userService.save(userModel);
//        return "redirect:/admin/";
//    }
//
//    @GetMapping("/update")
//    public String edit(Model model, @RequestParam("id") Long id) {
//        model.addAttribute("user", userService.showById(id));
//        model.addAttribute("roles", roleService.rolesList());
//        return "update";
//    }
//
//    @PatchMapping("/update")
//    public String updateUser(@ModelAttribute("user") UserModel userModel, @RequestParam(value = "roles") Long[] role) {
//        Set<Roles> roleSet = new HashSet<>();
//        for (Long roles : role) {
//            roleSet.add(roleService.findRoleById(roles));
//        }
//        Long id = userModel.getId();
//        userModel.setRoles(roleSet);
//        userService.update(userModel, id);
//        return "redirect:/admin/";
//    }
//
//    @PostMapping("/delete")
//    public String deleteUser(@RequestParam (value = "id") Long id) {
//        userService.delete(id);
//        return "redirect:/admin/";
//    }
//
//    @GetMapping("/{id}")
//    public String showUser(@PathVariable("id") Long id, Model model) {
//        UserModel user = userService.showById(id);
//        model.addAttribute("user", user);
//        return "user";
//    }
}
