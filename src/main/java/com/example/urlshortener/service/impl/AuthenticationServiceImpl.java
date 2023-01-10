package com.example.urlshortener.service.impl;

import com.example.urlshortener.entity.Account;
import com.example.urlshortener.exception.RegistrationException;
import com.example.urlshortener.exception.AuthenticationException;
import com.example.urlshortener.service.AccountService;
import com.example.urlshortener.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private AccountService accountService;
    private PasswordEncoder passwordEncoder;

    @Override
    public Account register(String username, String password) throws RegistrationException {
        Optional<Account> accountFromDb = accountService.findByUsername(username);
        if (accountFromDb.isPresent()) {
            throw new RegistrationException("Username '" + username + "' already taken");
        }
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        return accountService.save(account);
    }

    @Override
    public Account login(String username, String password) throws AuthenticationException {
        Optional<Account> account = accountService.findByUsername(username);
        if (account.isPresent() && passwordEncoder.matches(password, account.get().getPassword())) {
            return account.get();
        }
        throw new AuthenticationException("Username or password is wrong");
    }
}
