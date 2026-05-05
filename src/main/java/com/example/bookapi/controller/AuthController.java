package com.example.bookapi.controller;

import com.example.bookapi.dto.LoginRequest;
import com.example.bookapi.dto.RegisterRequest;
import com.example.bookapi.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {

        try {
            service.register(request.getUsername(), request.getPassword());
            return ResponseEntity.ok("User registered successfully");

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginRequest loginRequest,
            HttpServletRequest request,
            HttpServletResponse response) {

        try {
            service.login(
                    loginRequest.getUsername(),
                    loginRequest.getPassword(),
                    request,
                    response
            );

            return ResponseEntity.ok("Login successful");

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Something went wrong");
        }
    }
}
