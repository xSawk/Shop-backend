--liquibase formatted sql
--changeset Sawk:14
create table users(
    id bigint not null auto_increment PRIMARY KEY,
    username varchar(50) not null unique,
    password varchar(600) not null,
    enabled boolean not null
);