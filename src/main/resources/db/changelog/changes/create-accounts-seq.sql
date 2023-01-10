--liquibase formatted sql
--changeset <dev>:<create-accounts-seq>

CREATE SEQUENCE IF NOT EXISTS accounts_id_seq START 1 INCREMENT 1 MINVALUE 1;

--rollback DROP SEQUENCE account_id_seq;