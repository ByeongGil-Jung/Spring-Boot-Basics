-- SQL 스크립트를 사용한 데이터베이스 초기화
-- (보통, spring.jpa.show-sql=true 을 통해 console 에 찍힌 것과 똑같다.)

drop table if exists account
drop table if exists hibernate_sequence
create table account (id bigint not null, password varchar(255), username varchar(255), primary key (id)) engine=InnoDB
create table hibernate_sequence (next_val bigint) engine=InnoDB