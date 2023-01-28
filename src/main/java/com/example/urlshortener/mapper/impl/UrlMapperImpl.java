package com.example.urlshortener.mapper.impl;

import com.example.urlshortener.entity.Url;
import com.example.urlshortener.entity.dto.res.UrlResponseDto;
import com.example.urlshortener.mapper.UrlMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlMapperImpl implements UrlMapper {
    @Value("${domain-name}")
    private String domainName;

    @Override
    public UrlResponseDto toDto(Url url) {
        return new UrlResponseDto()
                .setTimestamp(url.getTimestamp())
                .setShortUrl(domainName + "/" + url.getShortUrl())
                .setOriginalUrl(url.getOriginalUrl());
    }
}
