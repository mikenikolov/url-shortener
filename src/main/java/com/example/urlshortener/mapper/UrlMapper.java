package com.example.urlshortener.mapper;

import com.example.urlshortener.entity.Url;
import com.example.urlshortener.entity.dto.res.UrlResponseDto;

public interface UrlMapper {
    UrlResponseDto toDto(Url url);
}
