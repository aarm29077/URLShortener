package com.example.URLShortener.repositories;

import com.example.URLShortener.models.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortUrlRepository extends JpaRepository<UrlEntity, Long> {
    Optional<UrlEntity> findByShortUrl(String shortUrl);

    Optional<UrlEntity> findByFullUrl(String fullUrl);

    UrlEntity findTopByOrderByIdDesc();
}
