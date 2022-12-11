--liquibase formatted sql
--changeset Sawk:6

create table cart(
    id bigint not null auto_increment PRIMARY KEY,
    created datetime not null
);