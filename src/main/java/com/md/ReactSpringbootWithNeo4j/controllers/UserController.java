package com.md.ReactSpringbootWithNeo4j.controllers;

import com.md.ReactSpringbootWithNeo4j.models.User;
import com.md.ReactSpringbootWithNeo4j.objects.UserDTO;
import com.md.ReactSpringbootWithNeo4j.requests.CreateUserRequest;
import com.md.ReactSpringbootWithNeo4j.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public String loggedInUser(Principal principal){
        return principal.getName();
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> signUp(@RequestBody CreateUserRequest request){
        // Here we will first create user.
        User user  = userService.cerateUser(request);
        // Below object responseUser will return the response after the user is created.
        UserDTO responseUser = new UserDTO(user.getName(), user.getUsername(), user.getRoles());
        return new ResponseEntity<>(responseUser, HttpStatus.CREATED);
    }
}
