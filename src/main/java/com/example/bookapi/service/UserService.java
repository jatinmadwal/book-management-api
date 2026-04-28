package com.example.bookapi.service;

import com.example.bookapi.dto.UserResponse;
import com.example.bookapi.model.User;
import com.example.bookapi.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        return repository.save(user);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUser(int id) {
        repository.deleteById(id);
    }

    public User register(User user) {

        User existing = repository.findByUsername(user.getUsername());

        if (existing != null) {
            throw new RuntimeException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        return repository.save(user);
    }

    public User login(String username, String password) {

        User user = repository.findByUsername(username);

        if (user == null) {
            return null;
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return null;
        }

        return user;
    }
}