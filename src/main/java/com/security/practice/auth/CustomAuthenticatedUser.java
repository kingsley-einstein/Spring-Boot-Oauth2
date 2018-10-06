package com.security.practice.auth;

import com.security.practice.models.User;

public class CustomAuthenticatedUser extends org.springframework.security.core.userdetails.User {
    
    private static final long serialVersionUID = 1L;

    public CustomAuthenticatedUser(User user) {
        super(user.getUsername(), user.getPassword(), user.getGrantedAuthoritiesList());
    }
}