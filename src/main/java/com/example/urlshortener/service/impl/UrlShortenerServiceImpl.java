package com.example.urlshortener.service.impl;

import com.example.urlshortener.entity.Account;
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
    public Url shortUrl(String originalUrl, Account account) {
        String shortUrl = urlGenerator.generate();
        Url resultUrl = new Url()
                .setTimestamp(LocalDateTime.now())
                .setOriginalUrl(originalUrl)
                .setShortUrl(shortUrl)
                .setAccount(account);
        return urlRepository.save(resultUrl);
    }

    @Override
    public Url shortUrl(String originalUrl, String customUrl, Account account) {
        Optional<Url> urlFromDb = urlRepository.findByShortUrl(customUrl);
        if (urlFromDb.isPresent()) {
            throw new TakenCustomUrlException("This custom url is already taken. Try another one.");
        }
        Url resultUrl = new Url()
                .setTimestamp(LocalDateTime.now())
                .setOriginalUrl(originalUrl)
                .setShortUrl(customUrl)
                .setAccount(account);
        return urlRepository.save(resultUrl);
    }
}
