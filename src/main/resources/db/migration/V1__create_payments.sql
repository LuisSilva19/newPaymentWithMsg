create table payments (
     id SERIAL PRIMARY KEY,
     value decimal(19,2) NOT NULL,
     name varchar(100) DEFAULT NULL,
     number varchar(100) DEFAULT NULL,
     expiration varchar(100) DEFAULT NULL,
     cod varchar(100) DEFAULT NULL,
     status varchar(100) DEFAULT NULL,
     order_id int NOT NULL,
     form_of_payment int NOT NULL
);