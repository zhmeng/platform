# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table bank (
  name                      varchar(255),
  bank_no                   varchar(255))
;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists bank;

SET REFERENTIAL_INTEGRITY TRUE;

