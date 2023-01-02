--liquibase formatted sql
--changeset Sawk:16

alter table `order` add user_username varchar(50) AFTER `payment_id`;

--liquibase formatted sql
--changeset Sawk:17

alter table `order` add constraint fk_order_user_username foreign key (user_username) references users(username);