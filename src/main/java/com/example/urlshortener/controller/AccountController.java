package com.example.urlshortener.controller;

import com.example.urlshortener.entity.dto.res.AccountUrlsResponseDto;
import com.example.urlshortener.mapper.Mapper;
import com.example.urlshortener.security.AuthUser;
import com.example.urlshortener.entity.Url;
import com.example.urlshortener.entity.dto.res.AccountInfoResponseDto;
import com.example.urlshortener.service.AccountService;
import com.example.urlshortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class AccountController {
    @Value("${urls-per-page}")
    private Integer urlsPerPage;
    private final AccountService accountService;
    private final UrlService urlService;
    private final Mapper<Page<Url>, AccountUrlsResponseDto> urlsMapper;

    @GetMapping
    public AccountInfoResponseDto getAccountInfo(@AuthenticationPrincipal AuthUser authUser) {
        return accountService.getAccountInfo(authUser.getAccount());
    }

    @GetMapping("/urls")
    public AccountUrlsResponseDto getAccountUrls(@AuthenticationPrincipal AuthUser authUser,
                                                 @RequestParam(value = "page", defaultValue = "1") Integer page) {
        Pageable pageable = PageRequest.of(page - 1, urlsPerPage, Sort.by(Sort.Direction.DESC, "timestamp"));
        Page<Url> pages = urlService.getPageableUrlsByAccount(authUser.getAccount(), pageable);
        return urlsMapper.map(pages);
    }
}
