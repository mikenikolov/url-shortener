package com.example.urlshortener.service;

import com.example.urlshortener.entity.Url;

public interface UrlShortenerService {
    Url shortUrl(String originalUrl);

    Url shortUrl(String originalUrl, String customUrl);
}
