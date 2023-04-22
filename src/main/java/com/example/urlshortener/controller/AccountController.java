package com.example.urlshortener.controller;

import com.example.urlshortener.entity.Url;
import com.example.urlshortener.entity.dto.res.AccountInfoResponseDto;
import com.example.urlshortener.entity.dto.res.AccountUrlsResponseDto;
import com.example.urlshortener.mapper.Mapper;
import com.example.urlshortener.security.AuthUser;
import com.example.urlshortener.service.AccountService;
import com.example.urlshortener.service.UrlService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/profile")
@AllArgsConstructor
public class AccountController {
    private AccountService accountService;
    private UrlService urlService;
    private Mapper<Page<Url>, AccountUrlsResponseDto> urlsMapper;

    @GetMapping
    public AccountInfoResponseDto getAccountInfo(@AuthenticationPrincipal AuthUser authUser) {
        return accountService.getAccountInfo(authUser.getAccount());
    }

    @GetMapping("/urls")
    public AccountUrlsResponseDto getAccountUrls(@AuthenticationPrincipal AuthUser authUser,
                                                 @RequestParam(value = "page", defaultValue = "1") Integer pageIndex) {
        Page<Url> pages = urlService.getPageableUrlsByAccount(authUser.getAccount(), pageIndex);
        return urlsMapper.map(pages);
    }
}
