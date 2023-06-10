package com.md.ReactSpringbootWithNeo4j.requests;

/**
 *
 * The idea behind creating this 'CreateUserRequest' class is to make relationships between user and courses
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
