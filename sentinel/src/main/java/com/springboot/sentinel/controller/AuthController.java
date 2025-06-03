package com.springboot.sentinel.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.sentinel.model.User;
import com.springboot.sentinel.security.JwtUtil;
import com.springboot.sentinel.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
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
        User user = userService.registerUser(
            request.get("username"),
            request.get("email"),
            request.get("password")
        );
        // Nunca retorne a senha
        return ResponseEntity.ok(Map.of(
            "id", user.getId(),
            "username", user.getUsername(),
            "email", user.getEmail()
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        Optional<User> user = userService.findByEmail(request.get("email"));
        if (user.isPresent() && passwordEncoder.matches(request.get("password"), user.get().getPassword())) {
            String token = JwtUtil.generateToken(user.get().getEmail());
            return ResponseEntity.ok(Map.of(
                "token", token,
                "email", user.get().getEmail()
            ));
        }
        return ResponseEntity.status(401).body("invalid credentials");
    }
}