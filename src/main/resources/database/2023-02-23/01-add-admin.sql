--liquibase formatted sql
--changeset Sawk:18

insert into users (id, username, password, enabled) values (1, 'admin', '{bcrypt}$2a$12$vCX.D.6AWfXt3j03qwoCm..xF.gH.wcwtfPkjEvF6hC2Ky5leI1hi', true);
insert into authorities (username, authority) values ('admin','ROLE_ADMIN');