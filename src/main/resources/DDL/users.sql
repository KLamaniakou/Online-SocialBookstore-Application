CREATE DATABASE  IF NOT EXISTS `secure_users_directory`;
USE `secure_users_directory`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `usersprofile`;
DROP TABLE IF EXISTS `books`;
DROP TABLE IF EXISTS `request`;


CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` text DEFAULT NULL,
  `password` text DEFAULT NULL,
  `role` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `usersprofile` (
    `id` int NOT NULL AUTO_INCREMENT,
    `user_name` text DEFAULT NULL,
    `full_name` text DEFAULT NULL,
    `address` text DEFAULT NULL,
    `age` text DEFAULT NULL,
    `phone_number` text DEFAULT NULL,
    `category` text DEFAULT NULL,
    `author` text DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
CREATE TABLE `books` (
    `book_id` int NOT NULL AUTO_INCREMENT,
    `title` text DEFAULT NULL,
    `authors` text DEFAULT NULL,
    `category` text DEFAULT NULL,
    `username` text DEFAULT NULL,
    PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
CREATE TABLE `request` (
    `id` int NOT NULL AUTO_INCREMENT,
    `username` text DEFAULT NULL,
    `title` text DEFAULT NULL,
    `owner` text DEFAULT NULL,
    `answer` text DEFAULT NULL,
    `status` bool DEFAULT FALSE,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

