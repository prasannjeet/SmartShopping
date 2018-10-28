USE eventuate;

DROP table IF EXISTS cart;

DROP table IF EXISTS product;

create table cart (
  id VARCHAR (255) PRIMARY KEY,
  user_id VARCHAR (255),
  deleted BOOLEAN
);

create table product (
  id VARCHAR (255) PRIMARY KEY,
  barcode VARCHAR (255),
  name VARCHAR (255),
  brand VARCHAR (255),
  quantity VARCHAR (255),
  user_id VARCHAR(255),
  deleted BOOLEAN
);