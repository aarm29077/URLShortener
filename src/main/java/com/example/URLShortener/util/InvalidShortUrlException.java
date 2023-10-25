package com.example.URLShortener.util;

public class InvalidShortUrlException extends RuntimeException{
    public InvalidShortUrlException(String message) {
        super(message);
    }
}
