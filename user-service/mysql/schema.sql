USE eventuate;

DROP table IF EXISTS user;

create table user (
  id varchar(255) PRIMARY KEY,
  username VARCHAR (255),
  password VARCHAR (255),
  deleted BOOLEAN
);
