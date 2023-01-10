package com.example.urlshortener.mapper.impl;

import com.example.urlshortener.entity.Account;
import com.example.urlshortener.entity.dto.res.AccountResponseDto;
import com.example.urlshortener.mapper.AccountMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public AccountResponseDto toDto(Account account) {
        return new AccountResponseDto()
                .setId(account.getId())
                .setUsername(account.getUsername());
    }
}
