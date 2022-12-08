--liquibase formatted sql
--changeset Sawk:5
alter table review add moderated boolean default false;