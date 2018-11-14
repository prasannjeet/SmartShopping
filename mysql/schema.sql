DROP DATABASE IF EXISTS userservice;
DROP DATABASE IF EXISTS cartservice;
DROP DATABASE IF EXISTS productservice;
DROP DATABASE IF EXISTS storeservice1;
DROP DATABASE IF EXISTS storeservice2;

CREATE DATABASE userservice;
CREATE DATABASE cartservice;
CREATE DATABASE productservice;
CREATE DATABASE storeservice1;
CREATE DATABASE storeservice2;


USE userservice;

DROP TABLE IF EXISTS user;

CREATE TABLE user (
  id VARCHAR (255) PRIMARY KEY,
  username VARCHAR (255) UNIQUE NOT NULL,
  password VARCHAR (255) NOT NULL,
  deleted BOOLEAN
);


USE cartservice;

DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS product;

CREATE TABLE cart (
  user_id VARCHAR (255) PRIMARY KEY,
  deleted BOOLEAN
);

CREATE TABLE product (
  id VARCHAR (255) PRIMARY KEY,
  barcode VARCHAR (255) NOT NULL,
  name VARCHAR (255) NOT NULL,
  quantity VARCHAR (255) NOT NULL,
  has_weight BOOLEAN NOT NULL,
  user_id VARCHAR(255) NOT NULL,
  deleted BOOLEAN
);


USE productservice;

DROP table IF EXISTS product;

create table product (
  id VARCHAR (255) PRIMARY KEY,
  barcode VARCHAR(255) UNIQUE NOT NULL,
  name VARCHAR(255) NOT NULL,
  has_weight VARCHAR(10) NOT NULL,
  deleted BOOLEAN
);

USE storeservice1;

DROP table IF EXISTS store;

create table store (
  id VARCHAR(255) PRIMARY KEY,
  name varchar(255) NOT NULL,
  website varchar(255) NOT NULL,
  location varchar(255) NOT NULL,
  deleted BOOLEAN
);

DROP table IF EXISTS price_tag;

create table price_tag (
  id VARCHAR(255) PRIMARY KEY,
  barcode VARCHAR(255) UNIQUE NOT NULL,
  price VARCHAR(255) NOT NULL,
  deleted BOOLEAN
);

USE storeservice2;

DROP table IF EXISTS store;

create table store (
  id VARCHAR(255) PRIMARY KEY,
  name varchar(255) NOT NULL,
  website varchar(255) NOT NULL,
  location varchar(255) NOT NULL,
  deleted BOOLEAN
);

DROP table IF EXISTS price_tag;

create table price_tag (
  id VARCHAR(255) PRIMARY KEY,
  barcode VARCHAR(255) UNIQUE NOT NULL,
  price VARCHAR(255) NOT NULL,
  deleted BOOLEAN
);

SHOW DATABASES;
