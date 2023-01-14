package com.example.urlshortener.controller;


import com.example.urlshortener.entity.Url;
import com.example.urlshortener.entity.dto.req.CustomUrlRequestDto;
import com.example.urlshortener.entity.dto.req.UrlRequestDto;
import com.example.urlshortener.entity.dto.res.UrlResponseDto;
import com.example.urlshortener.mapper.UrlMapper;
import com.example.urlshortener.service.UrlShortenerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/short")
@AllArgsConstructor
public class ShortenerController {
    private UrlShortenerService shortenerService;
    private UrlMapper urlMapper;

    @PostMapping
    public UrlResponseDto shortUrl(@Valid @RequestBody UrlRequestDto requestDto) {
        Url shortUrl = shortenerService.shortUrl(requestDto.getOriginalUrl());
        return urlMapper.toDto(shortUrl);
    }

    @PostMapping("/custom")
    public UrlResponseDto shortCustomUrl(@Valid @RequestBody CustomUrlRequestDto requestDto) {
        Url shortUrl = shortenerService.shortUrl(requestDto.getOriginalUrl(), requestDto.getShortUrl());
        return urlMapper.toDto(shortUrl);
    }
}
