package com.example.urlshortener.service;

import com.example.urlshortener.entity.Account;
import com.example.urlshortener.entity.dto.res.AccountInfoResponseDto;

import java.util.Optional;

public interface AccountService {
    Optional<Account> findByUsername(String username);

    Account save(Account account);

    AccountInfoResponseDto getAccountInfo(Account account);
}
