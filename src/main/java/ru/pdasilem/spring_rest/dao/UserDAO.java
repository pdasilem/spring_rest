package ru.pdasilem.spring_rest.dao;




import ru.pdasilem.spring_rest.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getUsersWithRoles();
    void save(User user);
    void delete(Long id);
    User getUserById(Long id);
    void update(User updUser);
    String getUserPassword(User user);
}
