package com.example.urlshortener.security;

import com.example.urlshortener.entity.Account;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class AuthUser extends User {
    private transient Account account;

    public AuthUser(Account account,
                    String username,
                    String password,
                    Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.account = account;
    }
}
