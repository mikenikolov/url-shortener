package com.example.urlshortener.mapper.impl;

import com.example.urlshortener.entity.Url;
import com.example.urlshortener.entity.dto.res.AccountUrlsResponseDto;
import com.example.urlshortener.entity.dto.res.UrlResponseDto;
import com.example.urlshortener.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
class AccountUrlsMapperImpl implements Mapper<Page<Url>, AccountUrlsResponseDto> {
    @Value("${domain-name}")
    private String domainName;
    private final Mapper<Url, UrlResponseDto> urlMapper;

    public AccountUrlsResponseDto map(Page<Url> pages) {
        List<UrlResponseDto> urlsDto = pages.stream()
                .map(urlMapper::map)
                .collect(Collectors.toList());
        String endpoint = domainName + "/profile/urls?page=";
        int totalPages = pages.isEmpty() ? 1 : pages.getTotalPages();
        int currPage = pages.getNumber() + 1;
        int nextPage = pages.hasNext() ? currPage + 1 : currPage;
        int prevPage = pages.isFirst() ? 1 : currPage - 1;
        return new AccountUrlsResponseDto()
                .setUrls(urlsDto)
                .setCurrentPage(currPage)
                .setTotalPages(totalPages)
                .setNextPage(endpoint + nextPage)
                .setPreviousPage(endpoint + prevPage)
                .setIsLastPage(pages.isLast());
    }
}

