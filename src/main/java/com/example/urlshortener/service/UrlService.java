package com.example.urlshortener.service;

import com.example.urlshortener.entity.Url;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UrlService {
    Page<Url> getPageableUrlsByAccount(Long id, Pageable pageable);
}
