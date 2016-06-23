# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table bank (
  name                      varchar(255),
  bank_no                   varchar(255))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table bank;

SET FOREIGN_KEY_CHECKS=1;

