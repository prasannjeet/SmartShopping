USE eventuate;

DROP table IF EXISTS store;

create table store (
  id varchar(255) PRIMARY KEY,
  deleted BOOLEAN
);
