--liquibase formatted sql
--changeset <dev>:<create-accounts-table>

CREATE TABLE IF NOT EXISTS accounts (
    id bigint NOT NULL,
    username varchar(16) NOT NULL,
    password varchar(255) NOT NULL ,
    CONSTRAINT accounts_pk PRIMARY KEY (id)
);

--rollback DROP TABLE accounts;
