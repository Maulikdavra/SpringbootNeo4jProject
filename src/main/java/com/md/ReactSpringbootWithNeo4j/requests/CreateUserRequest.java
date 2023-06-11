package com.md.ReactSpringbootWithNeo4j.requests;

import org.apache.catalina.Store;

/**
 *
 * The idea behind creating below class is to get all the information from user via REST API
 * Later we will be passing that information to User class, and then we will save it to a database.
 * <p>
 * In short, the class CreateUserRequest is a container that holds the user data for us, coming from API.
 * </p>
 * The idea behind creating this 'CreateUserRequest' class is to make relationships between users and courses
 * In short, the idea is to enroll user into courses.
 * CreateUserRequest class contains all the fields as User (model package) except ID
 */
public class CreateUserRequest {

    private String name;
    private String username;
    private String password;
    private String roles;

    public CreateUserRequest(String name, String username, String password, String roles) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
