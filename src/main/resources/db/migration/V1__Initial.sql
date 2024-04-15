use school_management;

drop table if exists users;
CREATE TABLE users (
     id int primary key auto_increment,
     full_name varchar(255),
     email varchar(255) unique,
     password varchar(255),
     created_at DATE,
     updated_at DATE
);
