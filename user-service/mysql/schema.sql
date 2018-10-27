USE eventuate;

DROP table IF EXISTS user;

create table user (
  id varchar(255) PRIMARY KEY,
  deleted BOOLEAN
);
