package com.example.URLShortener.models;

import com.example.URLShortener.util.Limits;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "urls")
public class UrlEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_url", columnDefinition = "VARCHAR(255) COLLATE utf8_bin", unique = true)
    @Size(min = Limits.FULL_URL_MIN_SIZE, max = Limits.FULL_URL_MAX_SIZE, message = "fullUrl should be between 1 and 20 characters")
    @NotBlank
    private String fullUrl;

    @Column(name = "short_url", columnDefinition = "VARCHAR(255) COLLATE utf8_bin")
    @Size(max = Limits.SHORT_URL_MAX_SIZE, message = "shortUrl should be between 1 and 4 characters")
    @NotBlank
    private String shortUrl;

    @Column(name = "created_at")
    @NotNull
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", shortUrl='" + shortUrl + '\'' +
                ", fullUrl='" + fullUrl + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
