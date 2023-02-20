package com.example.urlshortener.service.impl;

import com.example.urlshortener.entity.Url;
import com.example.urlshortener.exception.TakenCustomUrlException;
import com.example.urlshortener.respository.UrlRepository;
import com.example.urlshortener.service.UrlShortenerService;
import com.example.urlshortener.service.component.UrlGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UrlShortenerServiceImpl implements UrlShortenerService {
    private UrlRepository urlRepository;
    private UrlGenerator urlGenerator;

    @Override
    public Url shortUrl(String originalUrl, Long accountId) {
        String shortUrl = urlGenerator.generate();
        Url resultUrl = new Url()
                .setTimestamp(LocalDateTime.now())
                .setOriginalUrl(originalUrl)
                .setShortUrl(shortUrl)
                .setAccountId(accountId);
        return urlRepository.save(resultUrl);
    }

    @Override
    public Url shortUrl(String originalUrl, String customUrl, Long accountId) {
        Optional<Url> urlFromDb = urlRepository.findByShortUrl(customUrl);
        if (urlFromDb.isPresent()) {
            throw new TakenCustomUrlException("This custom url is already taken. Try another one.");
        }
        Url resultUrl = new Url()
                .setTimestamp(LocalDateTime.now())
                .setOriginalUrl(originalUrl)
                .setShortUrl(customUrl)
                .setAccountId(accountId);
        return urlRepository.save(resultUrl);
    }
}
