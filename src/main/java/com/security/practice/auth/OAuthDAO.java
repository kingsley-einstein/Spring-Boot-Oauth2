package com.security.practice.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.ArrayList;

import com.security.practice.models.User;
import com.security.practice.repositories.UserRepository;

@Repository
public class OAuthDAO {
    
    @Autowired
    UserRepository userRepo;
    
    public User loadUserByUsername(String username) {
        User user = userRepo.findByUsername(username);
        
        List<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
        
        grantedAuthoritiesList.add(new SimpleGrantedAuthority("ROLE_SYSTEMADMIN"));
        
        user.setGrantedAuthoritiesList(grantedAuthoritiesList);
        
        userRepo.save(user);
        
        return user;
    }
}