SET SCHEMA `microservice`;

CREATE TABLE IF NOT EXISTS `file` (
    `id` BIGINT AUTO_INCREMENT NOT NULL,
    `created` TIMESTAMP NOT NULL,
    `extension` VARCHAR(255) NOT NULL,
    `filedata` BLOB,
    `name` VARCHAR(255) NOT NULL,
    `updated` TIMESTAMP NOT NULL,
    `customerid` BIGINT NOT NULL
);