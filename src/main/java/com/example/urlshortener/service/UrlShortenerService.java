package com.example.urlshortener.service;

import com.example.urlshortener.entity.Account;
import com.example.urlshortener.entity.Url;

public interface UrlShortenerService {
    Url shortUrl(String originalUrl, Account account);

    Url shortUrl(String originalUrl, String customUrl, Account account);
}
