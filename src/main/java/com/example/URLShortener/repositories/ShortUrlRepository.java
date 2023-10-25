package com.example.URLShortener.repositories;

import com.example.URLShortener.models.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ShortUrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByShortUrl(String shortUrl);

    Optional<Url> findByFullUrl(String fullUrl);

    Url findTopByOrderByIdDesc();
}
