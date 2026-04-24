package com.example.bookapi.controller;

import com.example.bookapi.model.User;
import com.example.bookapi.service.UserService;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@RestController
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public User login(@RequestParam String username,
                      @RequestParam String password,
                      HttpSession session) {

        User user = service.login(username, password);

        if (user != null) {
            session.setAttribute("user", user.getId());
            return user;
        }

        return null;
    }



}