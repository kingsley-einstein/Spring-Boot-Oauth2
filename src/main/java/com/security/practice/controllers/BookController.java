package com.security.practice.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

import com.security.practice.repositories.UserRepository;
import com.security.practice.repositories.BookRepository;
import com.security.practice.models.Book;
import com.security.practice.exceptions.book.BookNotFoundException;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class BookController {
    
    @Autowired
    BookRepository bookRepo;
    
    @Autowired
    UserRepository userRepo;
    
    @GetMapping("/books")
    @ResponseBody
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }
    
    @GetMapping("/books/{id}")
    @ResponseBody
    public Book getBookById(@PathVariable("id") Long id) {
        if(bookRepo.findById(id).get() == null) {
            throw new BookNotFoundException("Book not found");
        }
        else {
            return bookRepo.findById(id).get();
        }
    }
    
    @PostMapping("/users/{id}/books")
    @ResponseBody
    public Book createBook(@PathVariable("id") Long id, @RequestBody Map<String, String> body) {
       return bookRepo.save(new Book(body.get("title"), body.get("content"), new java.util.Date(), userRepo.findById(id).get()));
    }
    
    @PutMapping("/books/{id}")
    @ResponseBody
    public Book editBook(@PathVariable("id") Long id, @RequestBody Map<String, String> body) {
        Book book = bookRepo.findById(id).get();
        
        book.setTitle(body.get("title"));
        book.setContent(body.get("content"));
        return book;
    }
    
    @DeleteMapping("/books/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable("id") Long id) {
        bookRepo.deleteById(id);
        
        return "Book Deleted";
    }
    
}