package com.example.URLShortener.controller;

import com.example.URLShortener.util.Limits;
import com.example.URLShortener.models.Url;
import com.example.URLShortener.services.UrlService;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/urls")
@Validated
public class UrlController {

    private final UrlService urlService;

    @Autowired
    public UrlController(UrlService shortUrlService) {
        this.urlService = shortUrlService;
    }


    @GetMapping("/full")
    public List<String> getFullUrls() {
        return urlService.findAll().stream().map(Url::getFullUrl).collect(Collectors.toList());
    }

    @GetMapping("/short")
    public List<String> getShortUrls() {
        return urlService.findAll().stream().map(Url::getShortUrl).collect(Collectors.toList());
    }

    @PostMapping("/full/{fullUrl}")
    public String receiveShort(@PathVariable @Validated @NotBlank(message = "The string is blank") @Size(min = Limits.FULL_URL_MIN_SIZE, max = Limits.FULL_URL_MAX_SIZE,message = "The URL size is invalid") final String fullUrl) {
        Url ourUrl = urlService.findByFullUrl(fullUrl).orElse(urlService.save(fullUrl));

        return ourUrl.getShortUrl();


    }

    @PostMapping("/short/{shortUrl}")
    public String receiveFull(@PathVariable @Validated @NotBlank(message = "The url is blank") @Size(max = Limits.SHORT_URL_MAX_SIZE) String shortUrl) {

        Url ourUrl = urlService.findByShortUrl(shortUrl);

        return ourUrl.getFullUrl();

    }

}
