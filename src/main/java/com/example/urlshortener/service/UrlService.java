package com.example.urlshortener.service;

import com.example.urlshortener.entity.Url;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UrlService {
    List<Url> getUrlListByAccountId(Long id, Pageable pageable);
}
