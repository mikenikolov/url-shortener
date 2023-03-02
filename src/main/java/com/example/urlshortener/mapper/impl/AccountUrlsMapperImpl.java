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
        int pageNum;
        if (pages.getNumber() >= pages.getTotalPages()) {
            pageNum = pages.getTotalPages();
        } else {
            pageNum = pages.getNumber() + 1;
        }
        return new AccountUrlsResponseDto()
                .setUrls(urlsDto)
                .setCurrentPage(pageNum)
                .setTotalPages(pages.getTotalPages())
                .setNextPage(domainName + "/profile/urls?page="
                        + (pages.hasNext() ? pageNum + 1 : pageNum))
                .setPreviousPage(domainName + "/profile/urls?page="
                        + (pages.hasPrevious() ? pageNum - 1 : pageNum))
                .setIsLastPage(!pages.hasNext());
    }
}

