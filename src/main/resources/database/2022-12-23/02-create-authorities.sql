--liquibase formatted sql
--changeset Sawk:15

create table authorities (
    username varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);
--liquibase formatted sql
--changeset Sawk:16
create unique index ix_auth_username on authorities (username,authority);