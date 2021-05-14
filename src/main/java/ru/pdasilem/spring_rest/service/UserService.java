package ru.pdasilem.spring_rest.service;


import ru.pdasilem.spring_rest.model.Roles;
import ru.pdasilem.spring_rest.model.UserModel;

import java.util.List;

public interface UserService {
    List<UserModel> usersList();
    UserModel showById(long id);
    void save(UserModel userModel);
    void delete(long id);
    void update(UserModel newUser);
    UserModel getUserByLogin(String email);
    Roles getRoleByName(String role);

}
