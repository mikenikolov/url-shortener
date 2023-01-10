package com.example.urlshortener.security;

import com.example.urlshortener.entity.Account;
import com.example.urlshortener.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("Account with username '" + username + "' not found"));
        User.UserBuilder userBuilder = User.withUsername(username);
        userBuilder.password(account.getPassword());
        List<GrantedAuthority> authorities = new ArrayList<>();
        userBuilder.authorities(authorities);
        return userBuilder.build();
    }
}
