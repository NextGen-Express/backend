DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS carriers;
DROP TABLE IF EXISTS stations;

CREATE TABLE IF NOT EXISTS users
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS carriers
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(10) NOT NULL ,
    weight_capacity VARCHAR(10),
    max_weight_capacity VARCHAR(10),
    battery_level VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS orders
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    order_time DATETIME NOT NULL ,
    estimated_pickup_time DATETIME NOT NULL ,
    estimated_delivery_time DATETIME NOT NULL ,
    pickup_addr VARCHAR(100) NOT NULL,
    delivery_addr VARCHAR(100) NOT NULL,
    carrier_id INT NOT NULL,
    price VARCHAR(10) NOT NULL,
    status VARCHAR(10),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (carrier_id) REFERENCES carriers(id)
);

CREATE TABLE IF NOT EXISTS stations
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    country VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    address VARCHAR(100) NOT NULL,
    status VARCHAR(10),
    latitude FLOAT,
    longtitude FLOAT
);