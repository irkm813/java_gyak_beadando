package com.example.java_gyak_beadando.login;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(String email,String username,String password, String confirmPassword) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("A felhasználó már létezik");
        }

        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("A két jelszó nem egyezik");
        }

        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setRole("user");
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }
}