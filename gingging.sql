DROP DATABASE ssafit_pjt;

CREATE DATABASE ssafit_pjt;
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
    `user_id` INT NOT NULL,
    CONSTRAINT `user_jog_fk` FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`),
    `jog_date` TIMESTAMP DEFAULT NOW(),
    `jog_name` VARCHAR(40) NOT NULL,
    `address` VARCHAR(40) NOT NULL,
    `length` VARCHAR(40) NOT NULL,
    `time` VARCHAR(40) NOT NULL
)ENGINE=InnoDB;

CREATE TABLE `poster` (
	`poster_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` INT NOT NULL,
    CONSTRAINT `user_poster_fk` FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`),
    `jog_id` INT NOT NULL,
    CONSTRAINT `jog_id_fk` FOREIGN KEY (`jog_id`) REFERENCES `jogging`(`jog_id`) on delete cascade,
    `reg_date` TIMESTAMP DEFAULT NOW(),
    `review` VARCHAR(100),
    `mood` VARCHAR(100) NOT NULL,
    `weather` VARCHAR(100) NOT NULL,
    `song` VARCHAR(50),
    `companion` VARCHAR(50),
    `user_photo` VARCHAR(100),
    `user_org_photo` VARCHAR(100)
)ENGINE = InnoDB;