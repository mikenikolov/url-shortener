package com.example.urlshortener.service;

import com.example.urlshortener.entity.Url;

public interface RedirectService {
    Url findShortUrl(String shortUrl);
}
