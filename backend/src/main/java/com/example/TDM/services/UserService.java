package com.example.TDM.services;

import com.example.TDM.models.User;
import com.example.TDM.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public  User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public boolean updateUserInformation(String username, User updatedUser) {
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            // Update user information
            existingUser.setNom(updatedUser.getNom());
            existingUser.setPrenom(updatedUser.getPrenom());
            existingUser.setPhoto(updatedUser.getPhoto());
            userRepository.save(existingUser);
            return true;
        }
        return false;
    }

    public boolean updateUserPassword(String username, String newPassword) {
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            // Update password
            existingUser.setPassword(newPassword);
            userRepository.save(existingUser);
            return true;
        }
        return false;
    }

    public String createUser(User user) {
        userRepository.save(user);
        return user.getUsername();
    }

    public User getUserInfo(String username) {
        return userRepository.findByUsername(username);
    }


    public boolean updateUserPhoto(String username, String photoName) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setPhoto(photoName);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

}
