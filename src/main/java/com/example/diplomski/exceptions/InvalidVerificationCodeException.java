package com.example.diplomski.exceptions;

public class InvalidVerificationCodeException extends RuntimeException {
    public InvalidVerificationCodeException() {
    }

    public InvalidVerificationCodeException(String message) {
        super(message);
    }

    public InvalidVerificationCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
