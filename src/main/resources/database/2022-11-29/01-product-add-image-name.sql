--liquibase formatted sql
--changeset Sawk:2

alter table product add image varchar(64) after currency;