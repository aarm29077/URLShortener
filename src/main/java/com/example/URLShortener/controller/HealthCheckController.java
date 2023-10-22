package com.example.URLShortener.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthCheckController {

    @GetMapping("/health")
    public String healthCheck() {
        return "Application is healthy";
    }
}
//
//C:\Users\ProgrammingFolder\mysql_data
//
//docker run --name mysql-container -v C:\Users\ProgrammingFolder\mysql_data:/var/lib/mysql -p 3306:3306 mysql:latest
