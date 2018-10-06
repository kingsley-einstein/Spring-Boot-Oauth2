package com.security.practice.exceptions.user;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class UserExceptionHandler {
    
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> getExceptionMessage(UserNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}