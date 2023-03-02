package com.example.urlshortener.entity.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AccountResponseDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("username")
    private String username;
}
