package com.springboot.sentinel.service;

import java.util.Optional;

import javax.management.relation.Role;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.sentinel.enums.RoleEnum;
import com.springboot.sentinel.model.User;
import com.springboot.sentinel.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String email, String password, RoleEnum role) {
        String passwordCrypt = passwordEncoder.encode(password);
       User user = new User(username, email, passwordCrypt, role);
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
