package com.springboot.sentinel.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.sentinel.model.User;
import com.springboot.sentinel.enums.RoleEnum;
import com.springboot.sentinel.security.JwtUtil;
import com.springboot.sentinel.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        System.out.println("Role recebido: " + request.get("role"));
        RoleEnum role;
        try {
            role = RoleEnum.fromValue(request.get("role"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid role: " + request.get("role"));
        }

        User user = userService.registerUser(
                request.get("username"),
                request.get("email"),
                request.get("password"),
                role);

        return ResponseEntity.ok(Map.of(
                "id", user.getId(),
                "username", user.getUsername(),
                "email", user.getEmail(),
                "role", user.getRole().name()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        Optional<User> userOptional = userService.findByEmail(request.get("email"));

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (passwordEncoder.matches(request.get("password"), user.getPassword())) {
                String token = JwtUtil.generateToken(user.getEmail());

                return ResponseEntity.ok(Map.of(
                        "token", token,
                        "id", user.getId(),
                        "username", user.getUsername(),
                        "email", user.getEmail(),
                        "role", user.getRole().name()
                ));
            }
        }

        return ResponseEntity.status(401).body("invalid credentials");
    }
}