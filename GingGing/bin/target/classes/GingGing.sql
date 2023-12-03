CREATE DATABASE IF NOT EXISTS ssafit_pjt;
USE ssafit_pjt;

CREATE TABLE IF NOT EXISTS `users` (
    `user_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `id` VARCHAR(50) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
    `email` VARCHAR(50) NOT NULL,
    `nick_name` VARCHAR(20) NOT NULL,
    `age` INT NOT NULL,
    `gender` INT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `jogging` (
    `jog_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `jog_name` VARCHAR(40) NOT NULL,
    `address` VARCHAR(100) NOT NULL,
    `description` VARCHAR(100) NOT NULL,
    `length` INT NOT NULL,
    `like` INT default 0,
    `jog_photo` VARCHAR(100) NOT NULL
)ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `poster` (
    `poster_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` INT NOT NULL,
    CONSTRAINT `user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`),
    `jog_id` INT NOT NULL,
    CONSTRAINT `jog_id_fk` FOREIGN KEY (`jog_id`) REFERENCES `jogging`(`jog_id`),
    `review` VARCHAR(100),
    `mood` INT NOT NULL,
    `weather` INT NOT NULL,
    `song` VARCHAR(50),
    `companion` VARCHAR(50),
    `user_photo` VARCHAR(100),
    `save` INT default 0
)ENGINE = InnoDB;