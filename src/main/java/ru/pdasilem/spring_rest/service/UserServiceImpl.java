package ru.pdasilem.spring_rest.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pdasilem.spring_rest.dao.UserDAOImpl;
import ru.pdasilem.spring_rest.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAOImpl userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDAOImpl userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsersWithRoles();
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    @Transactional
    public void update(User updUser) {
        if (!userRepository.getUserPassword(updUser).equals(updUser.getPassword())) {
            updUser.setPassword(passwordEncoder.encode(updUser.getPassword()));
        }
        userRepository.update(updUser);
    }
}
