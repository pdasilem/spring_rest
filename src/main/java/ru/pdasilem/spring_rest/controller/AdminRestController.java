package ru.pdasilem.spring_rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.pdasilem.spring_rest.model.UserModel;
import ru.pdasilem.spring_rest.service.RoleService;
import ru.pdasilem.spring_rest.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

//    // updating user
//    @GetMapping(value = "/update/{id}")
//    public ResponseEntity<?> updateUser(@RequestBody UserModel userModel, @PathVariable("id") Long id) {
//        userService.update(userModel, id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    // updating user
    @GetMapping(value = "/update")
    public ResponseEntity<?> updateUser(@RequestBody UserModel userModel) {
        userService.update(userModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value ="/infoUser")
    @ResponseBody
    public UserModel infoUser(@AuthenticationPrincipal UserModel user_authentication){
        UserModel userModel = userService.getUserByLogin(user_authentication.getEmail());
        return userModel;
    }
//
//    private Set<Roles> setRoles(Set<Roles> roles) {
//        Set<Roles> rolesSet = new HashSet<>();
//        for (Roles role : roles) {
//            rolesSet.add(roleService.getRoleByName(role.getRolename()));
//        }
//        return roleSet;
//    }
}
