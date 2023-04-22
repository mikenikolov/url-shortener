package com.example.urlshortener.service;

import com.example.urlshortener.entity.Account;
import com.example.urlshortener.entity.Url;
import org.springframework.data.domain.Page;

public interface UrlService {
    Page<Url> getPageableUrlsByAccount(Account account, Integer pageIndex);
}
