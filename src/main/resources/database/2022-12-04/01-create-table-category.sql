--liquibase formatted sql
--changeset Sawk:3
create table category(
    id bigint not null auto_increment PRIMARY KEY,
    name varchar(255) not null,
    description text

);
