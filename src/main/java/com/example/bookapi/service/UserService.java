package com.example.bookapi.service;

import com.example.bookapi.model.User;
import com.example.bookapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
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

        return repository.save(user);
    }
    public User login(String username, String password) {


        User user = repository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

}