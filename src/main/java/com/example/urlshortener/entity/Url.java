package com.example.urlshortener.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "urls")
@Getter
@Setter
@Accessors(chain = true)
public class Url {
    @Id
    @GeneratedValue(generator = "urls_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "urls_id_seq",
            sequenceName = "urls_id_seq",
            allocationSize = 1)
    private Long id;
    @Column(name = "original_url")
    private String originalUrl;
    @Column(name = "short_url")
    private String shortUrl;
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Url url = (Url) o;
        return Objects.equals(id, url.id)
                && Objects.equals(originalUrl, url.originalUrl)
                && Objects.equals(shortUrl, url.shortUrl)
                && Objects.equals(accountId, url.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originalUrl, shortUrl, accountId);
    }
}
