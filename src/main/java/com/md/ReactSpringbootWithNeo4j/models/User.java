package com.md.ReactSpringbootWithNeo4j.models;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String username;
    private String password;
    private String roles;

    /**
     *
     * we can add a multiple role here, for example, ADMIN_ROLE, TEACHER_ROLE, OFFICE_ROLE, etc. But for now we will be working on one role.
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // It is important to split roles by "," as we can have multiple roles as mentioned above.
        return Arrays.stream(roles.split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
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
