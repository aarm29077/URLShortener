package com.example.URLShortener.services;

import com.example.URLShortener.models.Url;
import com.example.URLShortener.repositories.ShortUrlRepository;
import com.example.URLShortener.util.UrlNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.function.Predicate.not;

@Service
@Transactional(readOnly = true)
public class UrlService {
    private final ShortUrlRepository shortUrlRepository;

    @Autowired
    public UrlService(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    public List<Url> findAll() {
        return shortUrlRepository.findAll();
    }

    public Url findByShortUrl(String shortUrl) {
        Optional<Url> foundUrl = shortUrlRepository.findByShortUrl(shortUrl);
        return foundUrl.orElseThrow(UrlNotFoundException::new);
    }

    public Optional<Url> findByFullUrl(String fullUrl) {
        return shortUrlRepository.findByFullUrl(fullUrl);
    }

    @Transactional
    public Url save(final String fullUrl) {
        return   Optional.ofNullable(fullUrl)
                .filter(not(String::isBlank))
                .map(this::generate)
                .map(shortUrlRepository::save)
                .orElseThrow(() -> new IllegalArgumentException("Not valid url"));
    }


    public Url generate(String fullUrl) {
        char[] map = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

        StringBuilder shortUrl = new StringBuilder();

        long n = getNextAvailableIndex();

        while (n > 0) {

            shortUrl.append(map[(int) (n % 62L)]);
            n = n / 62;
        }
        Url url = new Url();
        url.setShortUrl(shortUrl.toString());
        url.setFullUrl(fullUrl);
        url.setCreatedAt(LocalDateTime.now());

        return url;
    }

    private Long getNextAvailableIndex() {
        Url lastEntity = shortUrlRepository.findTopByOrderByIdDesc(); // Find the last entity
        if (lastEntity != null) {
            return lastEntity.getId() + 1; // Increment the last ID
        } else {
            return 1L; // Start from 1 if the table is empty
        }
    }
}
