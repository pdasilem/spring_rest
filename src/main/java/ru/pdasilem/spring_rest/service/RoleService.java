package ru.pdasilem.spring_rest.service;


import ru.pdasilem.spring_rest.model.Roles;

import java.util.List;

public interface RoleService {
    List<Roles> rolesList();
    Roles findRoleById(long id);
}
