package ru.pdasilem.spring_rest.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pdasilem.spring_rest.dao.UserDAOImpl;
import ru.pdasilem.spring_rest.model.User;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDAOImpl userRepository;

    public UserDetailsServiceImpl(UserDAOImpl userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<User> users = userRepository.getUsersWithRoles();
        for(User user: users) {
            if(user.getEmail().equals(s)) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User not found");
    }
}
