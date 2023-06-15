package com.md.ReactSpringbootWithNeo4j.services;

import com.md.ReactSpringbootWithNeo4j.models.User;
import com.md.ReactSpringbootWithNeo4j.repositories.UserRepository;
import com.md.ReactSpringbootWithNeo4j.requests.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User cerateUser(CreateUserRequest request) {
        // Validate the request
        if (request.getName() == null || request.getUsername() == null || request.getRoles() == null || request.getPassword() == null) {
            throw new RuntimeException("Not enough user details to register.");
        }

        // Create a new User object
        User user = new User();
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setRoles(request.getRoles());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Save the User object to the database
        userRepository.save(user);

        // Return the User object
        return user;
    }
}
