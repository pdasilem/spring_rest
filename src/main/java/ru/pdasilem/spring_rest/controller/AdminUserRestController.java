package ru.pdasilem.spring_rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.pdasilem.spring_rest.model.User;
import ru.pdasilem.spring_rest.service.UserService;

import java.util.List;

@RestController
public class AdminUserRestController {

    private final UserService userService;

    public AdminUserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/admin/users")
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/api/admin/user/new")
    public ResponseEntity<User> newUser(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/api/admin/user")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.update(user);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/api/admin/user/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}
