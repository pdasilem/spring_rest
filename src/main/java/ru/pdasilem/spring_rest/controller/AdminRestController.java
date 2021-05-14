package ru.pdasilem.spring_rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.pdasilem.spring_rest.model.UserModel;
import ru.pdasilem.spring_rest.service.UserService;

import java.util.List;


@RestController
@RequestMapping(value = "/rest")
public class AdminRestController {

    private final UserService userService;


    public AdminRestController(UserService userService) {
        this.userService = userService;

    }

    // List of all users
    @GetMapping(value = "/users")
    public ResponseEntity<List<UserModel>> allUsers() {
       List<UserModel> userModels = userService.usersList();
        return userModels != null && !userModels.isEmpty()
                ? new ResponseEntity<>(userModels, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // add a new user
    @PostMapping(value = "/newuser")
    public ResponseEntity<?> newUser(@RequestBody UserModel userModel) {
        userService.save(userModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // show user by id
    @GetMapping(value = "/findUser/{id}")
    public ResponseEntity<UserModel> showUser(@PathVariable(name = "id") Long id) {
        UserModel userModel = userService.showById(id);
        return userModel != null
                ? new ResponseEntity<>(userModel, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // updating user
    @PutMapping(value = "/update")
    public ResponseEntity<?> updateUser(@RequestBody UserModel userModel) {
        userService.update(userModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value ="/infoUser")
    @ResponseBody
    public UserModel infoUser(@AuthenticationPrincipal UserModel user_authentication){
        return userService.getUserByLogin(user_authentication.getEmail());
    }
}
