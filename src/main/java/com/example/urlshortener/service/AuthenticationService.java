package com.example.urlshortener.service;

import com.example.urlshortener.entity.Account;

public interface AuthenticationService {
    Account register(String username, String password);

    Account login(String username, String password);
}
