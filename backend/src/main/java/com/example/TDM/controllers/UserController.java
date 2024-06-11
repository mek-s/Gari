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
    @GetMapping("/getUserByEmail/{email}")
    public  ResponseEntity<User> getUserByEmail(@PathVariable("email") String email){
        User user = userService.getUserByEmail(email);
        if (user != null) {
            System.out.println(user.getUsername());
            return ResponseEntity.ok().body(user);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/update-info/{username}")
    public ResponseEntity<String> updateUserInformation(@PathVariable("username") String username, @RequestBody User user) {
        boolean updated = userService.updateUserInformation(username, user);
        if (updated) {
            return ResponseEntity.ok().body("User information updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update user information");
        }
    }

    @PutMapping("/update-password/{username}")
    public ResponseEntity<String> updateUserPassword(@PathVariable("username") String username, @RequestParam("password") String password) {
        boolean updated = userService.updateUserPassword(username, password);
        if (updated) {
            return ResponseEntity.ok().body("Password updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update password");
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserInfo(@PathVariable String username) {
        User user = userService.getUserInfo(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updatePhoto/{username}")
    public ResponseEntity<String> updateUserPhoto(
            @PathVariable String username,
            @RequestParam("photoName") String photoName
    ) {
        boolean updated = userService.updateUserPhoto(username, photoName);
        if (updated) {
            return ResponseEntity.ok("User photo updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }




}
