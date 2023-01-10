package com.example.urlshortener.service;

import com.example.urlshortener.entity.Account;

import java.util.Optional;

public interface AccountService {
    Optional<Account> findByUsername(String username);

    Account save(Account account);
}
