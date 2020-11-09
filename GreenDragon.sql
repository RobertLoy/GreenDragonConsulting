DROP TABLE IF EXISTS `photo`.`greendragon`;
DROP TABLE IF EXISTS `user`.`greendragon`;
DROP TABLE IF EXISTS `contact`.`greendragon`;
DROP TABLE IF EXISTS `location`.`greendragon`;

DROP DATABASE IF EXISTS `greendragon`;

SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE `greendragon` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `greendragon`;

CREATE USER  IF NOT EXISTS 'DB_access'@'localhost' IDENTIFIED BY 'db_p@$$word';
GRANT INSERT, UPDATE, DELETE, SELECT ON `greendragon`.* TO 'DB_access'@'localhost';

CREATE TABLE `photo` (
  `photo_id` int unsigned NOT NULL AUTO_INCREMENT,
  `photo_date` date NOT NULL,
  `photo_descrip` varchar(45) DEFAULT '',
  PRIMARY KEY (`photo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user` (
  `user_id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(8) NOT NULL,
  `business_role` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `contact` (
  `contact_id` int unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(1) NOT NULL,
  `name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL DEFAULT '',
  `phone_type` varchar(1) NOT NULL,
  `phone_num` bigint unsigned NOT NULL,
  `email` varchar(150) NOT NULL,
  `dob` date DEFAULT NULL,
  `hours` varchar(45) DEFAULT '',
  `website` varchar(150) DEFAULT '',
  `description` varchar(45) NOT NULL,
  `photo_id` int unsigned NOT NULL,
  `user_id` int unsigned NOT NULL,
  PRIMARY KEY (`contact_id`),
  KEY `fk_contact_photo_idx` (`photo_id`),
  KEY `fk_contact_user_idx` (`user_id`),
  CONSTRAINT `fk_contact_photo` FOREIGN KEY (`photo_id`) REFERENCES `photo` (`photo_id`),
  CONSTRAINT `fk_contact_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `location` (
  `contact_id` int unsigned NOT NULL,
  `street` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  PRIMARY KEY (`contact_id`),
  CONSTRAINT `fk_location_contact` FOREIGN KEY (`contact_id`) REFERENCES `contact` (`contact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table contact
--
SET AUTOCOMMIT=0;
INSERT INTO contact VALUES
(1, 'B', 'James', 'Sparks', 'W', '8086524444', 'james@sparks.com', null, '10-2', 'james.com', 'This is a teacher', 1, 1),
(2, 'B', 'Rob', 'Loy', 'W', '8087725544', 'rob@loy.com', null, '10-2', 'rob.com', 'This is a teacher', 2, 2),
(3, 'P', 'Phil', 'Evans', 'H', '8094225544', 'phil@evans.com', '2010-02-14 04:34:00', '', '', 'This is a classmate', 3, 3),
(4, 'P', 'Steve', 'Sorden', 'H', '9092725544', 'steve@sorden.com', '2010-02-14 04:34:00', '', '', 'This is a classmate',4, 4),
(5, 'P', 'Mara', 'Munoz', 'H', '2023275544', 'mara@munoz.com', '2010-02-14 04:34:00', '', '', 'This is a classmate', 5, 5);
COMMIT;
--
-- Dumping data for table location
--
SET AUTOCOMMIT=0;
INSERT INTO location VALUES
(1, 'Bell', 'Phoenix', 'Arizona'),
(2, 'West', 'Phoenix', 'Arizona'),
(3, 'Annapolis', 'Phoenix', 'Arizona'),
(4, 'North Cave Creek', 'Phoenix', 'Arizona'),
(5, 'Anaheim', 'Phoenix', 'Arizona');
COMMIT;
--
-- Dumping data for table photo
--
SET AUTOCOMMIT=0;
INSERT INTO photo VALUES
(1,'2019-02-14 04:34:00', 'Ice cream'),
(2,'2019-02-14 04:34:00', 'This and that'),
(3,'2019-02-14 04:34:00', 'Arizona'),
(4,'2019-02-14 04:34:00','Arizona'),
(5,'2019-02-14 04:34:00','Arizona');
COMMIT;
--
-- Dumping data for table user
--
SET AUTOCOMMIT=0;
INSERT INTO user VALUES
(1,'Mara', 'mypass', 'user'),
(2,'Phil', 'mypass',  'user'),
(3,'James', 'mypass', 'admin'),
(4,'Steve','mypass', 'user'),
(5,'Rob','mypass','admin');
COMMIT;
SET FOREIGN_KEY_CHECKS = 1;