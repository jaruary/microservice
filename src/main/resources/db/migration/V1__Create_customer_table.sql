CREATE SCHEMA IF NOT EXISTS `microservice`;
SET SCHEMA `microservice`;

DROP TABLE IF EXISTS `customer`;

CREATE TABLE IF NOT EXISTS `customer` (
  `id`        BIGINT AUTO_INCREMENT NOT NULL,
  `firstname` VARCHAR(255)          NOT NULL,
  `lastname`  VARCHAR(255)          NOT NULL
);