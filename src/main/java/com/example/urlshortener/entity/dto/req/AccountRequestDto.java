package com.example.urlshortener.entity.dto.req;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
public class AccountRequestDto {
    @NotNull(message = "username field cannot be empty")
    @Size(
            min = 2,
            max = 16,
            message = "The username must be between 2 and 16 characters long")
    private String username;
    @NotNull(message = "password field cannot be empty")
    @Size(
            min = 6,
            max = 32,
            message = "The password must be between 6 and 32 characters long")
    private String password;
}
