package com.example.urlshortener.entity.dto.req;

import com.example.urlshortener.annotation.CheckUrl;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UrlRequestDto {
    @CheckUrl
    @JsonProperty("original_url")
    private String originalUrl;
}
