package com.example.urlshortener.mapper.impl;

import com.example.urlshortener.entity.Account;
import com.example.urlshortener.entity.dto.res.AccountResponseDto;
import com.example.urlshortener.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AccountMapperImpl implements Mapper<Account, AccountResponseDto> {

    @Override
    public AccountResponseDto map(Account account) {
        return new AccountResponseDto()
                .setId(account.getId())
                .setUsername(account.getUsername());
    }
}
