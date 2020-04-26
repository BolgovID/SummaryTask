-- MySQL Script generated by MySQL Workbench
-- Tue Mar 17 16:20:13 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema rent
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema rent
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `rent` DEFAULT CHARACTER SET utf8;
USE `rent`;

-- -----------------------------------------------------
-- Table `rent`.`user_statuses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rent`.`user_statuses`;
CREATE TABLE IF NOT EXISTS `rent`.`user_statuses`
(
    `id_status` INT         NOT NULL AUTO_INCREMENT,
    `status`    VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id_status`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rent`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rent`.`roles`;

CREATE TABLE IF NOT EXISTS `rent`.`roles`
(
    `id_role` INT         NOT NULL AUTO_INCREMENT,
    `role`    VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id_role`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rent`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rent`.`users`;

CREATE TABLE IF NOT EXISTS `rent`.`users`
(
    `id_user`   INT         NOT NULL AUTO_INCREMENT,
    `login`     VARCHAR(45) NOT NULL,
    `password`  VARCHAR(45) NOT NULL,
    `firstname` VARCHAR(45) NOT NULL,
    `lastname`  VARCHAR(45) NOT NULL,
    `age`       INT         NOT NULL,
    `id_status` INT         NOT NULL DEFAULT 1,
    `id_role`   INT         NOT NULL DEFAULT 3,
    PRIMARY KEY (`id_user`),
    UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
    INDEX `fk_users_user_statuses_idx` (`id_status` ASC) VISIBLE,
    INDEX `fk_users_roles1_idx` (`id_role` ASC) VISIBLE,
    CONSTRAINT `fk_users_user_statuses`
        FOREIGN KEY (`id_status`)
            REFERENCES `rent`.`user_statuses` (`id_status`)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT,
    CONSTRAINT `fk_users_roles1`
        FOREIGN KEY (`id_role`)
            REFERENCES `rent`.`roles` (`id_role`)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rent`.`brands`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rent`.`brands`;

CREATE TABLE IF NOT EXISTS `rent`.`brands`
(
    `id_brand` INT         NOT NULL AUTO_INCREMENT,
    `brand`    VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id_brand`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rent`.`categories`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rent`.`categories`;

CREATE TABLE IF NOT EXISTS `rent`.`categories`
(
    `id_category` INT         NOT NULL AUTO_INCREMENT,
    `category`    VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id_category`)
)
    ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `rent`.`cars`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rent`.`cars`;

CREATE TABLE IF NOT EXISTS `rent`.`cars`
(
    `id_car`      INT         NOT NULL AUTO_INCREMENT,
    `model`       VARCHAR(45) NOT NULL,
    `cost`        DOUBLE      NOT NULL,
    `id_brand`    INT         NULL,
    `id_category` INT         NULL,

    PRIMARY KEY (`id_car`),
    INDEX `fk_cars_brands1_idx` (`id_brand` ASC) VISIBLE,
    INDEX `fk_cars_categories1_idx` (`id_category` ASC) VISIBLE,
    CONSTRAINT `fk_cars_brands1`
        FOREIGN KEY (`id_brand`)
            REFERENCES `rent`.`brands` (`id_brand`)
            ON DELETE SET NULL
            ON UPDATE SET NULL,
    CONSTRAINT `fk_cars_categories1`
        FOREIGN KEY (`id_category`)
            REFERENCES `rent`.`categories` (`id_category`)
            ON DELETE SET NULL
            ON UPDATE SET NULL
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rent`.`order_statuses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rent`.`order_statuses`;

CREATE TABLE IF NOT EXISTS `rent`.`order_statuses`
(
    `id_status` INT         NOT NULL AUTO_INCREMENT,
    `status`    VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id_status`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rent`.`orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rent`.`orders`;

CREATE TABLE IF NOT EXISTS `rent`.`orders`
(
    `id_order`   INT          NOT NULL AUTO_INCREMENT,
    `driver`     TINYINT      NOT NULL,
    `fromdate`   TIMESTAMP    NOT NULL,
    `todate`     TIMESTAMP    NOT NULL,
    `reasondeny` VARCHAR(100) NULL,
    `id_car`     INT          NULL,
    `id_status`  INT          NULL DEFAULT 1,
    `id_user`    INT          NULL,
    PRIMARY KEY (`id_order`),
    INDEX `fk_orders_cars1_idx` (`id_car` ASC) VISIBLE,
    INDEX `fk_orders_order_statuses1_idx` (`id_status` ASC) VISIBLE,
    INDEX `fk_orders_users1_idx` (`id_user` ASC) VISIBLE,
    CONSTRAINT `fk_orders_cars1`
        FOREIGN KEY (`id_car`)
            REFERENCES `rent`.`cars` (`id_car`)
            ON DELETE SET NULL
            ON UPDATE SET NULL,
    CONSTRAINT `fk_orders_order_statuses1`
        FOREIGN KEY (`id_status`)
            REFERENCES `rent`.`order_statuses` (`id_status`)
            ON DELETE SET NULL
            ON UPDATE SET NULL,
    CONSTRAINT `fk_orders_users1`
        FOREIGN KEY (`id_user`)
            REFERENCES `rent`.`users` (`id_user`)
            ON DELETE SET NULL
            ON UPDATE SET NULL
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rent`.`bills`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rent`.`bills`;

CREATE TABLE IF NOT EXISTS `rent`.`bills`
(
    `id_bill`  INT         NOT NULL AUTO_INCREMENT,
    `cost`     DOUBLE      NOT NULL,
    `reason`   VARCHAR(45) NOT NULL,
    `paid`     TINYINT     NOT NULL DEFAULT '0',
    `id_order` INT         NOT NULL,
    PRIMARY KEY (`id_bill`),
    INDEX `fk_bills_orders1_idx` (`id_order` ASC) VISIBLE,
    CONSTRAINT `fk_bills_orders1`
        FOREIGN KEY (`id_order`)
            REFERENCES `rent`.`orders` (`id_order`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;

INSERT INTO user_statuses(status) value ('unbanned');
INSERT INTO user_statuses(status) value ('banned');
INSERT INTO user_statuses(status) value ('penalty');

INSERT INTO roles(role) value ('admin');
INSERT INTO roles(role) value ('manager');
INSERT INTO roles(role) value ('client');

INSERT INTO users(id_role, id_status, login, password, firstname, lastname, age)
values ('1', '1', 'admin', 'adminpass', 'adminName', 'adminLastName', '18');
INSERT INTO users(id_role, id_status, login, password, firstname, lastname, age)
values ('2', '1', 'manager', 'managerpass', 'managerName', 'managerLastName', '18');
INSERT INTO users(id_role, id_status, login, password, firstname, lastname, age)
values ('3', '1', 'client', 'clientpass', 'clientName', 'clientLastName', '18');

INSERT INTO brands(brand) value ('BMW');
INSERT INTO brands(brand) value ('Audi');
INSERT INTO brands(brand) value ('Toyota');

INSERT INTO categories(category) value ('Business');
INSERT INTO categories(category) value ('Standart');
INSERT INTO categories(category) value ('Economy');

INSERT INTO order_statuses(status) value ('considering');
INSERT INTO order_statuses(status) value ('accepted');
INSERT INTO order_statuses(status) value ('rejected');
INSERT INTO order_statuses(status)
values ('paid');
INSERT INTO order_statuses(status)
values ('returning');
INSERT INTO order_statuses(status)
values ('closed');

INSERT INTO cars(id_brand, id_category, model, cost) value ('1', '1', 'X-MAN CAR', '150.23');
INSERT INTO cars(id_brand, id_category, model, cost) value ('1', '2', 'BATCAR', '130.53');
INSERT INTO cars(id_brand, id_category, model, cost) value ('1', '3', 'Party Bus', '110.28');

INSERT INTO cars(id_brand, id_category, model, cost) value ('2', '1', 'Car without wheels', '160.21');
INSERT INTO cars(id_brand, id_category, model, cost) value ('2', '2', 'Flying carpet', '140.10');
INSERT INTO cars(id_brand, id_category, model, cost) value ('2', '3', 'Gir Bord', '120.23');

INSERT INTO cars(id_brand, id_category, model, cost) value ('3', '1', 'Car with big gun ', '180.98');
INSERT INTO cars(id_brand, id_category, model, cost) value ('3', '2', 'Car with Nikki Minage', '130.50');
INSERT INTO cars(id_brand, id_category, model, cost) value ('3', '3', 'Halloween car', '100.00');

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;