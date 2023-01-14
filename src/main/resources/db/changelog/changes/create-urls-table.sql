--liquibase formatted sql
--changeset <dev>:<create-urls-table>

CREATE TABLE IF NOT EXISTS urls (
    id bigint NOT NULL,
    original_url text NOT NULL,
    short_url text NOT NULL,
    account_id bigint NOT NULL,
    CONSTRAINT urls_pk PRIMARY KEY (id),
    CONSTRAINT account_id_fk FOREIGN KEY(account_id) REFERENCES accounts(id)
);

--rollback DROP TABLE urls;
