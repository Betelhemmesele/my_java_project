
CREATE DATABASE IF NOT EXISTS clinic;
use clinic;


CREATE TABLE IF NOT EXISTS admin (
name VARCHAR(30) NOT NULL ,
id VARCHAR(30) NOT NULL ,
address VARCHAR(50) NOT NULL,
password VARCHAR(20) NOT NULL,
phonenumber VARCHAR(20)  NOT NULL
);
 insert into admin (id,name,phonenumber,address,password) values ("0001A","root","0983198886","addisababa","12345");
CREATE TABLE IF NOT EXISTS doctor (
name VARCHAR(30) NOT NULL ,
id VARCHAR(30) NOT NULL ,
address VARCHAR(50) NOT NULL,
password VARCHAR(20) NOT NULL,
phonenumber VARCHAR(20)  NOT NULL
);
CREATE TABLE IF NOT EXISTS patient (
name VARCHAR(30) NOT NULL ,
id VARCHAR(30) NOT NULL ,
address VARCHAR(50) NOT NULL,
admissiondate VARCHAR(20) NOT NULL,
recentdate VARCHAR(20) NOT NULL,
doctorid VARCHAR(20) NOT NULL,
phonenumber VARCHAR(20)  NOT NULL
);
CREATE TABLE IF NOT EXISTS receptionist (
name VARCHAR(30) NOT NULL ,
id VARCHAR(30) NOT NULL ,
address VARCHAR(50) NOT NULL,
password VARCHAR(20) NOT NULL,
phonenumber VARCHAR(20)  NOT NULL
);


