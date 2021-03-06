package ru.pdasilem.spring_rest.service;



import ru.pdasilem.spring_rest.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    void save(User user);

    void delete(Long id);

    User getUserById(Long id);

    void update(User updUser);

}

