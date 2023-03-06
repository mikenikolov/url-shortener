package com.example.urlshortener.service.impl;

import com.example.urlshortener.entity.Account;
import com.example.urlshortener.entity.Url;
import com.example.urlshortener.exception.DisallowedCustomUrl;
import com.example.urlshortener.exception.TakenCustomUrlException;
import com.example.urlshortener.respository.UrlRepository;
import com.example.urlshortener.service.UrlShortenerService;
import com.example.urlshortener.service.component.UrlGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlShortenerServiceImpl implements UrlShortenerService {
    private final UrlRepository urlRepository;
    private final UrlGenerator urlGenerator;
    private List<String> disallowedCustomUrls;

    @PostConstruct
    private void init() {
        disallowedCustomUrls = new ArrayList<>();
        disallowedCustomUrls.add("short");
        disallowedCustomUrls.add("login");
        disallowedCustomUrls.add("register");
        disallowedCustomUrls.add("profile");
    }

    @Override
    public Url shortUrl(String originalUrl, Account account) {
        String shortUrl = urlGenerator.generate();
        if (disallowedCustomUrls.contains(shortUrl)) {
            throw new DisallowedCustomUrl("this 'custom_url' is disallowed, try another one.");
        }
        Url resultUrl = new Url()
                .setTimestamp(LocalDateTime.now())
                .setOriginalUrl(originalUrl)
                .setShortUrl(shortUrl)
                .setAccount(account);
        return urlRepository.save(resultUrl);
    }

    @Override
    public Url shortUrl(String originalUrl, String customUrl, Account account) {
        if (disallowedCustomUrls.contains(customUrl)) {
            throw new DisallowedCustomUrl("this 'custom_url' is disallowed, try another one.");
        }
        Optional<Url> urlFromDb = urlRepository.findByShortUrl(customUrl);
        if (urlFromDb.isPresent()) {
            throw new TakenCustomUrlException("this 'custom_url' is already taken, try another one.");
        }
        Url resultUrl = new Url()
                .setTimestamp(LocalDateTime.now())
                .setOriginalUrl(originalUrl)
                .setShortUrl(customUrl)
                .setAccount(account);
        return urlRepository.save(resultUrl);
    }
}
