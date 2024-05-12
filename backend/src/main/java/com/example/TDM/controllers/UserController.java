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
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        boolean loginSuccessful = userService.login(user.getUsername(), user.getPassword());
        if (loginSuccessful) {
            return ResponseEntity.ok("Login successful. Username: " + user.getUsername());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }

}
