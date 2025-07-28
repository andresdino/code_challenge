# CREATE DATABASE prueba;
CREATE TABLE customer
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    create_at datetime     NOT NULL,
    update_at datetime     NOT NULL,
    delete_at datetime NULL,
    name      VARCHAR(500) NOT NULL,
    last_name VARCHAR(500) NOT NULL,
    age       INT          NOT NULL,
    birt_date datetime     NOT NULL,
    CONSTRAINT pk_customer PRIMARY KEY (id)
);

CREATE TABLE user
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    create_at   datetime     NOT NULL,
    update_at   datetime     NOT NULL,
    delete_at   datetime NULL,
    username    VARCHAR(255) NOT NULL,
    password    VARCHAR(255) NOT NULL,
    rol         VARCHAR(100) NOT NULL,
    customer_id BIGINT       NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE user
    ADD CONSTRAINT uc_user_username UNIQUE (username);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_CUSTOMERID FOREIGN KEY (customer_id) REFERENCES customer (id);

-- Customers
INSERT INTO prueba.customer (id, create_at, update_at, delete_at, name, last_name, age, birt_date) VALUES
                                                                                                       (2, NOW(), NOW(), NULL, 'Jane', 'Smith', 28, '1996-05-12'),
                                                                                                       (3, NOW(), NOW(), NULL, 'Carlos', 'Gomez', 35, '1989-03-23'),
                                                                                                       (4, NOW(), NOW(), NULL, 'Maria', 'Lopez', 40, '1984-07-19'),
                                                                                                       (5, NOW(), NOW(), NULL, 'Luis', 'Martinez', 22, '2002-11-05'),
                                                                                                       (6, NOW(), NOW(), NULL, 'Ana', 'Ramirez', 31, '1993-09-10');

-- Users
INSERT INTO prueba.`user` (id, create_at, customer_id, delete_at, update_at, rol, password, username) VALUES
                                                                                                          (2, NOW(), 2, NULL, NOW(), 'USER', 'jane123', 'jane.smith'),
                                                                                                          (3, NOW(), 3, NULL, NOW(), 'MODERATOR', 'carlos123', 'carlos.gomez'),
                                                                                                          (4, NOW(), 4, NULL, NOW(), 'USER', 'maria123', 'maria.lopez'),
                                                                                                          (5, NOW(), 5, NULL, NOW(), 'USER', 'luis123', 'luis.martinez'),
                                                                                                          (6, NOW(), 6, NULL, NOW(), 'ADMIN', 'ana123', 'ana.ramirez');