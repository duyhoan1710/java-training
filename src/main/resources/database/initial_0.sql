create database school_management;
use school_management;

drop table if exists class;
CREATE TABLE class (
                       id int primary key auto_increment,
                       name varchar(255) unique
);

drop table if exists student;
CREATE TABLE student (
                         id int primary key auto_increment,
                         name varchar(255),
                         age int,
                         is_male boolean,
                         class_id int
);

drop table if exists teacher;
CREATE TABLE teacher (
                         id int primary key auto_increment,
                         name varchar(255),
                         age int,
                         address nvarchar(1000),
                         is_male boolean
);

drop table if exists users;
CREATE TABLE users (
                         id int primary key auto_increment,
                         full_name varchar(255),
                         email varchar(255) unique,
                         password varchar(255),
                         created_at DATE,
                         updated_at DATE
);

-- alter table student drop constraint student_class_fk_1;
alter table student add constraint student_class_fk_1 FOREIGN KEY (class_id) REFERENCES class(id);


