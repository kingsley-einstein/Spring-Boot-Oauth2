package com.security.practice.auth;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service(value = "userService")
public class UserServiceImplementation implements UserDetailsService {
    
    @Autowired
    OAuthDAO oAuthDAO;
    
    @Override
    public CustomAuthenticatedUser loadUserByUsername(String username) {
        try {
            CustomAuthenticatedUser customAuthenticatedUser = new CustomAuthenticatedUser(oAuthDAO.loadUserByUsername(username));
            System.out.println(customAuthenticatedUser.getPassword()+" --- "+customAuthenticatedUser.getAuthorities()+" "+customAuthenticatedUser.isEnabled());
            
            return customAuthenticatedUser;
        }
        catch(Exception exception) {
            
            throw new UsernameNotFoundException("User record not found");
        }
        
    }
}