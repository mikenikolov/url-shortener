package com.example.urlshortener.controller;

import com.example.urlshortener.security.AuthUser;
import com.example.urlshortener.entity.Url;
import com.example.urlshortener.entity.dto.res.AccountInfoResponseDto;
import com.example.urlshortener.entity.dto.res.UrlResponseDto;
import com.example.urlshortener.mapper.UrlMapper;
import com.example.urlshortener.service.AccountService;
import com.example.urlshortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final UrlService urlService;
    private final UrlMapper urlMapper;
    @Value("${urls-per-page}")
    private Integer urlsPerPage;

    @GetMapping
    public AccountInfoResponseDto getAccountInfo(@AuthenticationPrincipal AuthUser authUser) {
        return accountService.findAccountInfoById(authUser.getId());
    }

    @GetMapping("/urls")
    public List<UrlResponseDto> urlsFromAccount(@AuthenticationPrincipal AuthUser authUser,
                                                @RequestParam(value = "page", defaultValue = "1") Integer page) {
        Pageable pageable = PageRequest.of(page - 1, urlsPerPage, Sort.by(Sort.Direction.DESC, "timestamp"));
        List<Url> urls = urlService.getUrlListByAccountId(authUser.getId(), pageable);
        return urls.stream()
                .map(urlMapper::toDto)
                .collect(Collectors.toList());
    }
}
