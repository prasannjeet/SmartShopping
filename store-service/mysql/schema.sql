USE eventuate;

DROP table IF EXISTS store;

create table store (
  id varchar(255) PRIMARY KEY,
  store_name varchar(255),
  website varchar(255),
  deleted BOOLEAN
);
