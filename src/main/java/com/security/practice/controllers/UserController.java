package com.security.practice.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.security.practice.repositories.UserRepository;
import com.security.practice.auth.UserServiceImplementation;
import com.security.practice.models.User;
import com.security.practice.exceptions.user.UserNotFoundException;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserController {
    
    @Autowired
    UserRepository repo;
    
    @Autowired
    UserServiceImplementation userServiceImpl;
    
    @GetMapping("/")
    @ResponseBody
    public String welcome() {
        return "Hello and Welcome";
    }
    
    @GetMapping("/users")
    @ResponseBody
    public List<User> getAllUsers() {
        return repo.findAll();
    }
    
    @GetMapping("/users/{id}")
    @ResponseBody
    public User getUserById(@PathVariable("id") Long id) {
        if (repo.findById(id).get() == null) {
            throw new UserNotFoundException("User not found");
        }
        else {
            return repo.findById(id).get();
        }
        
    }
    
    @PostMapping("/users")
    @ResponseBody
    public User createUser(@RequestBody Map<String, String> body) {
        User user = new User(body.get("username"), BCrypt.gensalt(16), body.get("email"));
        user.setPassword(BCrypt.hashpw(body.get("password"), user.getSalt()));
        return repo.save(user);
    }
    
    @PostMapping("/token")
    @ResponseBody
    public String createAuth(@RequestBody Map<String, String> body) {
        userServiceImpl.loadUserByUsername(body.get("username"));
        return "Done";
    }
    
    @PostMapping("/login")
    @ResponseBody
    public User logUser(@RequestBody Map<String, String> body) {
        User user = repo.findByUsername(body.get("username"));
        
        if (user == null) {
            throw new UserNotFoundException("User not found in database");
        }
        else {
            if (user.checkPassword(BCrypt.hashpw(body.get("password"), user.getSalt()))) {
                return user;
            }
            else {
                throw new UserNotFoundException("Incorrect password");
            }
        }
    }
    
    @PutMapping("/users/{id}")
    @ResponseBody
    public User editUser(@PathVariable("id") Long id, @RequestBody Map<String, String> body) {
        User user = repo.findById(id).get();
        
        user.setPassword(body.get("password"));
        
        return user;
    }
}