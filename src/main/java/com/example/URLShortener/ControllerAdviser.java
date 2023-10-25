package com.example.URLShortener;

import com.example.URLShortener.util.UrlErrorResponse;
import com.example.URLShortener.util.UrlNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ControllerAdviser {

    @ExceptionHandler
    private ResponseEntity<UrlErrorResponse> handleException(ConstraintViolationException e) {
        UrlErrorResponse response = new UrlErrorResponse(
                "this url is invalid",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);//404
    }

    @ExceptionHandler
    private ResponseEntity<UrlErrorResponse> handleException(UrlNotFoundException e) {
        UrlErrorResponse response = new UrlErrorResponse(
                "this url is not found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);//404
    }
}
