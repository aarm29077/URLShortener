package com.example.URLShortener.util;

public class InvalidFullUrlException extends RuntimeException{
    public InvalidFullUrlException(String message) {
        super(message);
    }
}
