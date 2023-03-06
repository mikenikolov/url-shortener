package com.example.urlshortener.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Url)) return false;
        Url url = (Url) o;
        return Objects.equals(id, url.id)
                && Objects.equals(originalUrl, url.originalUrl)
                && Objects.equals(shortUrl, url.shortUrl)
                && Objects.equals(timestamp, url.timestamp)
                && Objects.equals(account, url.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originalUrl, shortUrl, timestamp, account);
    }
}
