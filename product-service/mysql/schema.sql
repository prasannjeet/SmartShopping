USE eventuate;

DROP table IF EXISTS product;

create table product (
  id varchar(255) PRIMARY KEY,
  deleted BOOLEAN
);
