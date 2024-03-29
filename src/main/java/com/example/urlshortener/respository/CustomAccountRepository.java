package com.example.urlshortener.respository;

import com.example.urlshortener.entity.Account;
import com.example.urlshortener.entity.dto.res.AccountInfoResponseDto;
import org.springframework.data.jpa.repository.Query;

public interface CustomAccountRepository {

    @Query("SELECT new com.example.urlshortener.entity.dto.res.AccountInfoResponseDto(a.id, a.username, COUNT(u)) " +
            "FROM Url u RIGHT JOIN u.account a WHERE a = ?1 GROUP BY a.id")
    AccountInfoResponseDto getAccountInfo(Account account);
}
