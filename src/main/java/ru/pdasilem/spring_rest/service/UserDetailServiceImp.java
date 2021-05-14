package ru.pdasilem.spring_rest.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.pdasilem.spring_rest.repository.UserRepo;


@Service
public class UserDetailServiceImp implements UserDetailsService {

    private final UserRepo userRepo;


    public UserDetailServiceImp(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo.findByUserEmail(email);
    }
}
