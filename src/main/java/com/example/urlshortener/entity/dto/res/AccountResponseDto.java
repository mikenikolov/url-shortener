package com.example.urlshortener.entity.dto.res;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AccountResponseDto {
    private Long id;
    private String username;
}
