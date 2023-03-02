package com.example.urlshortener.security;

import com.example.urlshortener.entity.Account;
import com.example.urlshortener.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("Account with username '" + username + "' not found"));
        AuthUser authUser = new AuthUser(account.getId(), username, "", new ArrayList<>());
        authUser.setId(account.getId());
        return authUser;
    }
}
