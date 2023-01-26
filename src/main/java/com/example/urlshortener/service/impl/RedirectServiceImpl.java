package com.example.urlshortener.service.impl;

import com.example.urlshortener.entity.Url;
import com.example.urlshortener.exception.RedirectException;
import com.example.urlshortener.respository.UrlRepository;
import com.example.urlshortener.service.RedirectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RedirectServiceImpl implements RedirectService {
    private UrlRepository urlRepository;

    @Override
    public Url findShortUrl(String shortUrl) {
        Optional<Url> urlFromDb = urlRepository.findByShortUrl(shortUrl);
        return urlFromDb.orElseThrow(() -> new RedirectException("This url is not exists"));
    }
}
