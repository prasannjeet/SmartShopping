USE eventuate;

DROP table IF EXISTS cart;

create table cart (
  id varchar(255) PRIMARY KEY,
  user_id varchar(255),
  deleted BOOLEAN
);
