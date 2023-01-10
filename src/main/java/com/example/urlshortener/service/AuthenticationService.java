package com.example.urlshortener.service;

import com.example.urlshortener.entity.Account;
import com.example.urlshortener.exception.AuthenticationException;
import com.example.urlshortener.exception.RegistrationException;

public interface AuthenticationService {
    Account register(String username, String password) throws RegistrationException;

    Account login(String username, String password) throws AuthenticationException;
}
