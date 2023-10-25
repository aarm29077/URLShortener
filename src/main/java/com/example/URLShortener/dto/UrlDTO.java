package com.example.URLShortener.dto;


import jakarta.validation.constraints.NotEmpty;

public class UrlDTO {
    @NotEmpty
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
