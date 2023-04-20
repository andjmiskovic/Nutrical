package com.example.diplomski.exceptions;

public class EmailAlreadyVerifiedException extends RuntimeException {
    public EmailAlreadyVerifiedException() {
    }

    public EmailAlreadyVerifiedException(String message) {
        super(message);
    }

    public EmailAlreadyVerifiedException(String message, Throwable cause) {
        super(message, cause);
    }
}
