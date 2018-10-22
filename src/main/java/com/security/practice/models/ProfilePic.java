package com.security.practice.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "profile_pics")
public class ProfilePic implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @Lob
    @Column(name = "data", columnDefinition="mediumBlob")
    private String data;

    @JsonIgnore
    @OneToOne
    User owner;

    protected ProfilePic(){}

    public ProfilePic(String data, User owner) {
        this.data = data;
        this.owner = owner;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }
}