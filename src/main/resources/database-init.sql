DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS stations;
DROP TABLE IF EXISTS carriers;

CREATE TABLE users
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    password VARCHAR(100) NOT NULL,
    phone VARCHAR(15)
);

CREATE TABLE stations
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    city VARCHAR(50) NOT NULL,
    address VARCHAR(255) NOT NULL,
    status ENUM('unavailable', 'available') NOT NULL,
    latitude VARCHAR(10) NOT NULL,
    longitude VARCHAR(10) NOT NULL
);

CREATE TABLE carriers
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    shipping ENUM('ground', 'air') NOT NULL,
    burden INT NOT NULL,
    capacity INT NOT NULL,
    battery INT NOT NULL
);

CREATE TABLE orders
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    carrier_id INT NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (carrier_id) REFERENCES carriers(id) ON DELETE CASCADE,
    pickup_addr VARCHAR(255) NOT NULL,
    delivery_addr VARCHAR(255) NOT NULL,
    order_time DATETIME NOT NULL,
    estimated_pickup_time DATETIME NOT NULL,
    estimated_delivery_time DATETIME NOT NULL,
    price FLOAT,
    status ENUM('ordered', 'pickedup', 'delivered', 'reviewed') NOT NULL
);