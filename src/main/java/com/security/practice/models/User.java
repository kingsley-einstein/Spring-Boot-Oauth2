package com.security.practice.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.validation.constraints.NotBlank;
import javax.persistence.Transient;
//import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "users")
public class User implements java.io.Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(nullable = false, unique = true)
    private String username;
    
    @Column(nullable = false)
    private String salt;
    
    @NotBlank
    @Column(nullable = false)
    private String password;
    
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;
    
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;
    
    @Transient
    private List<GrantedAuthority> grantedAuthoritiesList;
    
    protected User(){}
    
    public User(String username, String salt, String email) {
        this.username = username;
        this.salt = salt;
        this.email = email;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    public String getSalt() {
        return salt;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }
    
    public boolean checkPassword(String password) {
        return password.equals(getPassword());
    }
    
    public List<Book> getBooks() {
        return books;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setGrantedAuthoritiesList(List<GrantedAuthority> grantedAuthoritiesList) {
        this.grantedAuthoritiesList = grantedAuthoritiesList;
    }
    
    public List<GrantedAuthority> getGrantedAuthoritiesList() {
        return grantedAuthoritiesList;
    }
    
} 