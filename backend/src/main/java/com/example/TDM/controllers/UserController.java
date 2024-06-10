package com.example.TDM.controllers;

import com.example.TDM.models.User;
import com.example.TDM.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        boolean loginSuccessful = userService.login(username, password);
        if (loginSuccessful) {
            // Return just the username upon successful login
            return ResponseEntity.ok().body(username);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String username = userService.createUser(user);
        if (username != null) {
            return ResponseEntity.ok().body(username);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user");
        }
    }




}
