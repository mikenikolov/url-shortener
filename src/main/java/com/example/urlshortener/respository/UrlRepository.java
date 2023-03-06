package com.example.urlshortener.respository;

import com.example.urlshortener.entity.Account;
import com.example.urlshortener.entity.Url;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByShortUrl(String shortUrl);

    Page<Url> findAllByAccount(Account account, Pageable pageable);
}
