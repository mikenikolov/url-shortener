package com.example.urlshortener.exception;

public class TakenCustomUrlException extends RuntimeException {
    public TakenCustomUrlException(String message) {
        super(message);
    }
}
