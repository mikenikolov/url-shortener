package com.example.urlshortener.entity.dto.req;


import com.example.urlshortener.annotation.CheckUrl;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
public class CustomUrlRequestDto {
    @CheckUrl
    @NotNull(message = "original_url field cannot be empty")
    @JsonProperty("original_url")
    private String originalUrl;
    @NotNull(message = "short_url field cannot be empty")
    @Size(min = 5, max = 16)
    @JsonProperty("short_url")
    private String shortUrl;
}
