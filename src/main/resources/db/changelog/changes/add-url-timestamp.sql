--liquibase formatted sql
--changeset <dev>:<add-urls-timestamp>

ALTER TABLE urls ADD COLUMN timestamp timestamp;

--rollback ALTER TABLE urls DROP timestamp;