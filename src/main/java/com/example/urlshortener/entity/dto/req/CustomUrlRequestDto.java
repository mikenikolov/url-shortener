package com.example.urlshortener.entity.dto.req;


import com.example.urlshortener.annotation.constraint.CustomUrl;
import com.example.urlshortener.annotation.constraint.Url;
import com.example.urlshortener.annotation.constraint.seq.First;
import com.example.urlshortener.annotation.constraint.seq.Second;
import com.example.urlshortener.annotation.constraint.seq.Third;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
public class CustomUrlRequestDto {
    @NotNull(message = "original_url field cannot be empty",
             groups = First.class)
    @Url(groups = Second.class)
    @JsonProperty("original_url")
    private String originalUrl;

    @NotNull(message = "short_url field cannot be empty",
             groups = First.class)
    @CustomUrl(groups = Second.class)
    @Size(
            min = 5,
            max = 16,
            message = "short_url field must contains 5-16 characters",
            groups = Third.class)
    @JsonProperty("short_url")
    private String shortUrl;
}
