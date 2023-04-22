package com.example.urlshortener.service.impl;

import com.example.urlshortener.entity.Account;
import com.example.urlshortener.entity.Url;
import com.example.urlshortener.exception.IncorrectPageException;
import com.example.urlshortener.respository.UrlRepository;
import com.example.urlshortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {
    @Value("${urls-per-page}")
    private Integer urlsPerPage;
    private final UrlRepository urlRepository;

    @Override
    public Page<Url> getPageableUrlsByAccount(Account account, Integer pageIndex) {
        if (pageIndex < 1) {
            throw new IncorrectPageException("Incorrect page number! Must be greater than zero.");
        }
        Pageable pageable = PageRequest.of(pageIndex - 1, urlsPerPage, Sort.by(Sort.Direction.DESC, "timestamp"));
        Page<Url> urls = urlRepository.findAllByAccount(account, pageable);
        if (pageIndex > urls.getTotalPages() && !urls.isFirst()) {
            throw new IncorrectPageException("Incorrect page number! Must be less or equal total pages.");
        }
        return urls;
    }
}
