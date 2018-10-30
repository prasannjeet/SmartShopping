USE eventuate;

DROP table IF EXISTS store;

DROP table IF EXISTS pricetag;

create table store (
  id varchar(255) PRIMARY KEY,
  store_name varchar(255),
  website varchar(255),
  location varchar(255),
  deleted BOOLEAN
);

create table pricetag (
  id VARCHAR (255) PRIMARY KEY,
  barcode VARCHAR (255),
  price VARCHAR (255),
  store VARCHAR (255),
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