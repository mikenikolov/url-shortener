package com.example.urlshortener.controller;

import com.example.urlshortener.entity.Account;
import com.example.urlshortener.entity.dto.req.AccountRequestDto;
import com.example.urlshortener.entity.dto.res.AccountResponseDto;
import com.example.urlshortener.entity.dto.res.JwtResponseDto;
import com.example.urlshortener.exception.AuthenticationException;
import com.example.urlshortener.exception.RegistrationException;
import com.example.urlshortener.mapper.AccountMapper;
import com.example.urlshortener.security.filter.JwtProvider;
import com.example.urlshortener.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("/")
@AllArgsConstructor
public class AuthenticationController {
    private JwtProvider provider;
    private AuthenticationService authService;
    private AccountMapper accountMapper;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponseDto register(@RequestBody @Valid AccountRequestDto requestDto)
            throws RegistrationException {
        Account account = authService.register(requestDto.getUsername(), requestDto.getPassword());
        return accountMapper.toDto(account);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtResponseDto login(@RequestBody @Valid AccountRequestDto requestDto)
            throws AuthenticationException {
        authService.login(requestDto.getUsername(), requestDto.getPassword());
        String jwt = provider.generate(requestDto.getUsername());
        return provider.generateResponse(jwt);
    }
}
