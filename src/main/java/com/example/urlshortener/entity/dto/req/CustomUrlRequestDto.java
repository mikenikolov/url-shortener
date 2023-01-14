package com.example.urlshortener.entity.dto.req;


import com.example.urlshortener.annotation.CheckUrl;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomUrlRequestDto {
    @CheckUrl
    @JsonProperty("original_url")
    private String originalUrl;
    @JsonProperty("short_url")
    private String shortUrl;
}
