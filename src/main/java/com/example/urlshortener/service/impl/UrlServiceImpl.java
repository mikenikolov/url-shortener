package com.example.urlshortener.service.impl;

import com.example.urlshortener.entity.Account;
import com.example.urlshortener.entity.Url;
import com.example.urlshortener.respository.UrlRepository;
import com.example.urlshortener.service.UrlService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UrlServiceImpl implements UrlService {
    private UrlRepository urlRepository;

    @Override
    public Page<Url> getPageableUrlsByAccount(Account account, Pageable pageable) {
        return urlRepository.findAllByAccount(account, pageable);
    }
}
