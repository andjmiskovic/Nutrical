package com.example.diplomski.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
