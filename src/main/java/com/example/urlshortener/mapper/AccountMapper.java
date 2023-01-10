package com.example.urlshortener.mapper;

import com.example.urlshortener.entity.Account;
import com.example.urlshortener.entity.dto.res.AccountResponseDto;

public interface AccountMapper {

    AccountResponseDto toDto(Account account);
}
