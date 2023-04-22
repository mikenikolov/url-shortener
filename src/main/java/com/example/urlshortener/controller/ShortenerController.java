package com.example.urlshortener.controller;

import com.example.urlshortener.annotation.constraint.seq.DefaultSeq;
import com.example.urlshortener.mapper.Mapper;
import com.example.urlshortener.security.AuthUser;
import com.example.urlshortener.entity.Url;
import com.example.urlshortener.entity.dto.req.CustomUrlRequestDto;
import com.example.urlshortener.entity.dto.req.UrlRequestDto;
import com.example.urlshortener.entity.dto.res.UrlResponseDto;
import com.example.urlshortener.service.UrlShortenerService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/short")
@AllArgsConstructor
public class ShortenerController {
    private UrlShortenerService shortenerService;
    private Mapper<Url, UrlResponseDto> urlMapper;

    @PostMapping
    public UrlResponseDto shortUrl(@Validated(DefaultSeq.class) @RequestBody UrlRequestDto requestDto,
                                   @AuthenticationPrincipal AuthUser auth) {
        Url shortUrl = shortenerService.shortUrl(requestDto.getOriginalUrl(), auth.getAccount());
        return urlMapper.map(shortUrl);
    }

    @PostMapping("/custom")
    public UrlResponseDto shortCustomUrl(@Validated(DefaultSeq.class) @RequestBody CustomUrlRequestDto requestDto,
                                         @AuthenticationPrincipal AuthUser auth) {
        Url shortUrl = shortenerService.shortUrl(requestDto.getOriginalUrl(), requestDto.getShortUrl(), auth.getAccount());
        return urlMapper.map(shortUrl);
    }
}
