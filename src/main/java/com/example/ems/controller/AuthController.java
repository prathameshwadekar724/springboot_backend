package com.example.ems.controller;

import com.example.ems.model.User;
import com.example.ems.repository.UserRepository;
import com.example.ems.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")

public class AuthController {
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager manager;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository repository, PasswordEncoder encoder, AuthenticationManager manager, JwtUtil jwtUtil) {
        this.repository = repository;
        this.encoder = encoder;
        this.manager = manager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user){
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        repository.save(user);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public Map<String,String> login(@RequestBody User user){
        manager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getUsername(),user.getPassword()
        ));

        String token = jwtUtil.generateToken(user.getUsername());
        return Map.of("token",token);
    }
}
