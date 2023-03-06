package com.example.urlshortener.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@Accessors(chain = true)
public class Account {
    @Id
    @GeneratedValue(generator = "accounts_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "accounts_id_seq",
                sequenceName = "accounts_id_seq",
                allocationSize = 1)
    private Long id;
    private String username;
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id)
                && Objects.equals(username, account.username)
                && Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }
}
