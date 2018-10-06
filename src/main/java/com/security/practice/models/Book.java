package com.security.practice.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.Date;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "books")
public class Book implements java.io.Serializable {
    
    @Transient
    private final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(nullable = false)
    private String title;
    
    @NotBlank
    @Column(nullable = false, columnDefinition="TEXT")
    private String content;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date created;
    
    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "author")
    private User author;
    
    protected Book(){}
    
    public Book(String title, String content, Date created, User author) {
        this.title = title;
        this.content = content;
        this.created = created;
        this.author = author;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getContent() {
        return content;
    }
    
    public User getAuthor() {
        return author;
    }
    
    public Date getCreated() {
        return created;
    }
    
    public String getDateFormat() {
        return format.format(created);
    }
    
    public Long getId() {
        return id;
    }
}