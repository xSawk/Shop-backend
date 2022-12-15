--liquibase formatted sql
--changeset Sawk:8

create table `order`(
    id bigint not null auto_increment PRIMARY KEY,
    place_date datetime not null,
    order_status varchar(32) not null,
    gross_value decimal(6,2) not null,
    firstname varchar(64) not null,
    lastname varchar(64) not null,
    street varchar(64) not null,
    zipcode varchar(10) not null,
    city varchar(64) not null,
    email varchar(64) not null,
    phone varchar(64) not null
);