package com.example.urlshortener.respository;

import com.example.urlshortener.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long>, CustomAccountRepository {
    Optional<Account> findByUsername(String username);
}
