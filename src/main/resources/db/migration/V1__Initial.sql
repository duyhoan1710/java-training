use java_training;

drop table if exists users;
CREATE TABLE users (
     id int primary key auto_increment,
     name varchar(255),
     phone varchar(10) unique,
     email varchar(255) unique,
     password varchar(255),
     role varchar(10),
     created_at DATE,
     updated_at DATE
);

drop table if exists customers;
CREATE TABLE customers (
    id int primary key auto_increment,
    name varchar(255),
    phone varchar(10) unique,
    address varchar(255),
    created_at DATE,
    updated_at DATE
);

drop table if exists products;
CREATE TABLE products (
    id int primary key auto_increment,
    name varchar(255),
    price double,
    created_at DATE,
    updated_at DATE
);

drop table if exists inventories;
CREATE TABLE inventories (
    id int primary key auto_increment,
    product_id int unique,
    stock_quantity int,
    created_at DATE,
    updated_at DATE
);

alter table inventories add constraint inventory_product_fk_1 FOREIGN KEY (product_id) REFERENCES products(id);

drop table if exists orders;
CREATE TABLE orders (
    id int primary key auto_increment,
    customer_id int,
    total_money double,
    created_at DATE,
    updated_at DATE
);

alter table orders add constraint order_customer_fk_1 FOREIGN KEY (customer_id) REFERENCES customers(id);

drop table if exists line_orders;
CREATE TABLE line_orders (
    id int primary key auto_increment,
    customer_id int,
    order_id int,
    product_id int,
    quantity int,
    created_at DATE,
    updated_at DATE
);

alter table line_orders add constraint line_order_customer_fk_1 FOREIGN KEY (customer_id) REFERENCES customers(id);
alter table line_orders add constraint line_order_order_fk_1 FOREIGN KEY (order_id) REFERENCES orders(id);
alter table line_orders add constraint line_order_product_fk_1 FOREIGN KEY (product_id) REFERENCES products(id);
