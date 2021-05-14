package ru.pdasilem.spring_rest.service;

import org.springframework.stereotype.Service;
import ru.pdasilem.spring_rest.model.Roles;
import ru.pdasilem.spring_rest.repository.RoleRepo;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepo roleRepo;

    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public List<Roles> rolesList() {
        return roleRepo.findAll();
    }

    @Override
    public Roles findRoleById(long id) {
        Optional<Roles> userRoles = roleRepo.findById(id);
        return userRoles.get();
    }

}
