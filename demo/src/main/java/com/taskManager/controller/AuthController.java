package com.taskManager.controller;

import com.taskManager.model.User;
import com.taskManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepo.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(Instant.now());
        // default role if none provided
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Set.of("ROLE_USER"));
        }
        User saved = userRepo.save(user);
        // hide password in response
        saved.setPassword(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
