package com.example.urlshortener.respository;

import com.example.urlshortener.entity.dto.res.AccountInfoResponseDto;
import org.springframework.data.jpa.repository.Query;

public interface CustomAccountRepository {

    @Query("SELECT new com.example.urlshortener.entity.dto.res.AccountInfoResponseDto(a.id, a.username, COUNT(urls)) " +
            "FROM Account a JOIN a.urls as urls WHERE a.id = :id GROUP BY a.id")
    AccountInfoResponseDto findAccountInfoById(Long id);
}
