--liquibase formatted sql
--changeset <dev>:<create-urls-seq>

CREATE SEQUENCE IF NOT EXISTS urls_id_seq START 1 INCREMENT 1 MINVALUE 1;

--rollback DROP SEQUENCE urls_id_seq;
