package com.example.urlshortener.exception;

public class DisallowedCustomUrl extends RuntimeException {
    public DisallowedCustomUrl(String message) {
        super(message);
    }
}
