package com.example.urlshortener.exception;

public class RedirectException extends RuntimeException {
    public RedirectException(String message) {
        super(message);
    }
}
