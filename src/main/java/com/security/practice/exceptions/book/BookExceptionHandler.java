package com.security.practice.exceptions.book;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class BookExceptionHandler {
    
    @ExceptionHandler(value = BookNotFoundException.class)
    public ResponseEntity<Object> getExceptionMessage(BookNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}