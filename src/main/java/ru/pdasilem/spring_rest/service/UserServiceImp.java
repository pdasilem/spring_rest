package ru.pdasilem.spring_rest.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.pdasilem.spring_rest.model.Roles;
import ru.pdasilem.spring_rest.model.UserModel;
import ru.pdasilem.spring_rest.repository.RoleRepo;
import ru.pdasilem.spring_rest.repository.UserRepo;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImp implements UserService{

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    public UserServiceImp(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public List<UserModel> usersList() {
        return userRepo.findAll();
    }

    @Override
    public UserModel showById(long id) {
        Optional<UserModel> userModel = userRepo.findById(id);
        return userModel.get();
    }

    @Override
    public void save(UserModel userModel) {
        userModel.setPassw(new BCryptPasswordEncoder().encode(userModel.getPassw()));
        userRepo.save(userModel);
    }

    @Override
    public void delete(long id) {
        userRepo.deleteById(id);
    }

    @Override
    public void update(UserModel newUser) {
        userRepo.save(newUser);
    }

    @Override
    public UserModel getUserByLogin(String email) {
        return userRepo.findByUserEmail(email);
    }

    @Override
    public Roles getRoleByName(String role) {
        return roleRepo.findByRole(role);
    }

}
