package com.example.urlshortener.exception;

public class IncorrectPageException extends RuntimeException {
    public IncorrectPageException(String message) {
        super(message);
    }
}
