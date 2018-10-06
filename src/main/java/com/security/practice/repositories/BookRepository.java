package com.security.practice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.practice.models.Book;
import com.security.practice.models.User;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    public List<Book> findByAuthor(User author);
    public Book findByTitle(String title);
}