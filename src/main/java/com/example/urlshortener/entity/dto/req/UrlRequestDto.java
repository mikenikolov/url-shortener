package com.example.urlshortener.entity.dto.req;

import com.example.urlshortener.annotation.constraint.Url;
import com.example.urlshortener.annotation.constraint.seq.First;
import com.example.urlshortener.annotation.constraint.seq.Second;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class UrlRequestDto {
    @NotNull(message = "original_url field cannot be empty",
             groups = First.class)
    @Url(groups = Second.class)
    @JsonProperty("original_url")
    private String originalUrl;
}
