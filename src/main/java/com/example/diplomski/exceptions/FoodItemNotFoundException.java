package com.example.diplomski.exceptions;

public class FoodItemNotFoundException extends Exception {
    public FoodItemNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
