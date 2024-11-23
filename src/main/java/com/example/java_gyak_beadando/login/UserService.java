package com.example.java_gyak_beadando.login;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Integer getUserIdByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(User::getId) // Extract the user ID
                .orElse(null); // Return null if no user is found
    }
}