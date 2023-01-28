package com.example.urlshortener.service.impl;

import com.example.urlshortener.entity.Url;
import com.example.urlshortener.respository.UrlRepository;
import com.example.urlshortener.service.UrlService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UrlServiceImpl implements UrlService {
    private UrlRepository urlRepository;

    @Override
    public List<Url> getUrlListByAccountId(Long id, Pageable pageable) {
        return urlRepository.findAllByAccountId(id, pageable);
    }
}
