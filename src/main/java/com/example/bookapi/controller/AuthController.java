package com.example.bookapi.controller;

import com.example.bookapi.dto.UserResponse;
import com.example.bookapi.model.User;
import com.example.bookapi.service.UserService;
import io.lettuce.core.GeoValue;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")

public class AuthController {

    private final UserService service;
    private final ModelMapper modelMapper;

    public AuthController(UserService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }
    @PostMapping("/register")
    public UserResponse register(@RequestBody User user) {

        User saved = service.register(user);

        return modelMapper.map(saved, UserResponse.class);
    }

    @PostMapping("/login")
    public UserResponse login(@RequestParam String username,
                              @RequestParam String password,
                              HttpSession session) {

        User user = service.login(username, password);

        if (user != null) {
            session.setAttribute("userId", user.getId());
            return modelMapper.map(user, UserResponse.class);
        }

        throw new RuntimeException("Invalid credentials");
    }



}