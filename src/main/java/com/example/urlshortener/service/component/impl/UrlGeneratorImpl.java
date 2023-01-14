package com.example.urlshortener.service.component.impl;

import com.example.urlshortener.respository.UrlRepository;
import com.example.urlshortener.service.component.UrlGenerator;
import liquibase.repackaged.org.apache.commons.lang3.RandomStringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UrlGeneratorImpl implements UrlGenerator {
    private UrlRepository urlRepository;

    @Override
    public String generate() {
        String randomUrl = RandomStringUtils.random(5, true, true);
        while (urlRepository.findByShortUrl(randomUrl).isPresent()) {
            randomUrl = RandomStringUtils.random(5, true, true);
        }
        return randomUrl;
    }
}
