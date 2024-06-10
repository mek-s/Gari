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

    public String createUser(User user) {
        userRepository.save(user);
        return user.getUsername();
    }
}
