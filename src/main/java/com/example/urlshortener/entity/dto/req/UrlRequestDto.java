package com.example.urlshortener.entity.dto.req;

import com.example.urlshortener.annotation.Url;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class UrlRequestDto {
    @Url
    @NotNull(message = "original_url field cannot be empty")
    @JsonProperty("original_url")
    private String originalUrl;
}
