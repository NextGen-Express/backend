DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS stations;
DROP TABLE IF EXISTS carriers;

CREATE TABLE users
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    first_name VARCHAR(255) NOT NULL DEFAULT '',
    last_name VARCHAR(255) NOT NULL DEFAULT '',
    phone_number VARCHAR(15) NOT NULL DEFAULT 0000000000,
    enabled TINYINT NOT NULL DEFAULT 1
);

CREATE TABLE authorities
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username  VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE stations
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    city VARCHAR(50) NOT NULL,
    address VARCHAR(255) NOT NULL,
    latitude DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL,
    status ENUM('unavailable', 'available')
);

CREATE TABLE carriers
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    shipping_method ENUM('RobotCar', 'UAV') NOT NULL,
    burden INT NOT NULL,
    capacity INT NOT NULL,
    battery INT
);

CREATE TABLE orders
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    order_time DATETIME NOT NULL,
    estimated_pick_time DATETIME NOT NULL,
    estimated_delivery_time DATETIME NOT NULL,
    pickup_addr VARCHAR(255) NOT NULL,
    delivery_addr VARCHAR(255) NOT NULL,
    carrier_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (carrier_id) REFERENCES carriers(id) ON DELETE CASCADE,
    price DOUBLE NOT NULL ,
    status ENUM('ordered', 'pickup', 'delivered', 'reviewed') NOT NULL,
    strpie_product_id VARCHAR(100)
);
