-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.11-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for whistleblower
CREATE DATABASE IF NOT EXISTS `whistleblower` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `whistleblower`;

-- Dumping structure for table whistleblower.assigned_issue
CREATE TABLE IF NOT EXISTS `assigned_issue` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category` varchar(1024) NOT NULL DEFAULT 'Annat',
  `when_issue` varchar(1024) NOT NULL,
  `where_issue` varchar(1024) NOT NULL,
  `details` varchar(1024) NOT NULL,
  `employee_awareness` varchar(1024) NOT NULL,
  `attachment` varchar(1024) NOT NULL,
  `temp_user_id` int(10) unsigned NOT NULL,
  `lawyer_id` int(10) unsigned NOT NULL,
  `created` datetime NOT NULL,
  `issue_status` char(15) DEFAULT 'ASSIGNED',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `temp_user_id` (`temp_user_id`) USING BTREE,
  KEY `assigned_issue_user_id_fk` (`lawyer_id`),
  CONSTRAINT `FKkbbg50l7cewo3wo6737ioh54d` FOREIGN KEY (`temp_user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `assigned_issue_user_id_fk` FOREIGN KEY (`lawyer_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `status_contraint` CHECK (`issue_status` in ('ASSIGNED','OPEN'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- Dumping data for table whistleblower.assigned_issue: ~0 rows (approximately)
DELETE FROM `assigned_issue`;
/*!40000 ALTER TABLE `assigned_issue` DISABLE KEYS */;
/*!40000 ALTER TABLE `assigned_issue` ENABLE KEYS */;

-- Dumping structure for event whistleblower.clear_records_older_than_6_month
DELIMITER //
CREATE EVENT `clear_records_older_than_6_month` ON SCHEDULE EVERY 1 DAY STARTS '2020-05-07 01:00:00' ON COMPLETION PRESERVE ENABLE DO DELETE FROM admin  WHERE latest_login < DATE_SUB(NOW(), INTERVAL 6 MONTH)//
DELIMITER ;

-- Dumping structure for table whistleblower.closed_issue
CREATE TABLE IF NOT EXISTS `closed_issue` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category` varchar(1024) NOT NULL DEFAULT 'Annat',
  `when_issue` varchar(1024) NOT NULL,
  `where_issue` varchar(1024) NOT NULL,
  `details` varchar(1024) NOT NULL,
  `employee_awareness` varchar(1024) NOT NULL,
  `attachment` varchar(1024) NOT NULL,
  `temp_user_id` int(10) unsigned NOT NULL,
  `lawyer_id` int(10) unsigned DEFAULT NULL,
  `created` datetime NOT NULL DEFAULT current_timestamp(),
  `issue_status` char(15) DEFAULT 'CLOSED',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `temp_user_id` (`temp_user_id`) USING BTREE,
  KEY `fk_lawyer_id` (`lawyer_id`) USING BTREE,
  CONSTRAINT `FK_closed_issue_user` FOREIGN KEY (`temp_user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_closed_issue_user_2` FOREIGN KEY (`lawyer_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `status_contraint` CHECK (`issue_status` = 'CLOSED')
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- Dumping data for table whistleblower.closed_issue: ~0 rows (approximately)
DELETE FROM `closed_issue`;
/*!40000 ALTER TABLE `closed_issue` DISABLE KEYS */;
/*!40000 ALTER TABLE `closed_issue` ENABLE KEYS */;

-- Dumping structure for table whistleblower.new_issue
CREATE TABLE IF NOT EXISTS `new_issue` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category` varchar(1024) NOT NULL DEFAULT 'Annat',
  `when_issue` varchar(1024) NOT NULL,
  `where_issue` varchar(1024) NOT NULL,
  `details` varchar(1024) NOT NULL,
  `employee_awareness` varchar(1024) NOT NULL,
  `attachment` varchar(1024) DEFAULT NULL,
  `temp_user_id` int(10) unsigned NOT NULL,
  `created` datetime NOT NULL DEFAULT current_timestamp(),
  `issue_status` char(15) NOT NULL DEFAULT 'UNASSIGNED',
  PRIMARY KEY (`id`),
  KEY `FK_new_issue_user` (`temp_user_id`),
  CONSTRAINT `FK_new_issue_user` FOREIGN KEY (`temp_user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `status_contraint` CHECK (`issue_status` = 'UNASSIGNED')
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table whistleblower.new_issue: ~4 rows (approximately)
DELETE FROM `new_issue`;
/*!40000 ALTER TABLE `new_issue` DISABLE KEYS */;
INSERT INTO `new_issue` (`id`, `category`, `when_issue`, `where_issue`, `details`, `employee_awareness`, `attachment`, `temp_user_id`, `created`, `issue_status`) VALUES
	(16, 'hoppsan', 'idag', 'utomhus', 'tagen på bar gärning', 'absolut', 'postman.com', 16, '2020-05-08 11:05:43', 'UNASSIGNED'),
	(17, 'hoppsan', 'idag', 'utomhus', 'tagen på bar gärning', 'absolut', 'postman.com', 17, '2020-05-08 11:11:57', 'UNASSIGNED'),
	(18, 'hoppsan', 'idag', 'utomhus', 'tagen på bar gärning', 'absolut', 'postman.com', 18, '2020-05-08 11:14:44', 'UNASSIGNED'),
	(19, 'hoppsan', 'idag', 'utomhus', 'tagen på bar gärning', 'absolut', 'postman.com', 19, '2020-05-08 11:22:06', 'UNASSIGNED');
/*!40000 ALTER TABLE `new_issue` ENABLE KEYS */;

-- Dumping structure for table whistleblower.postbox_post
CREATE TABLE IF NOT EXISTS `postbox_post` (
  `id` int(10) unsigned NOT NULL,
  `sent` datetime NOT NULL,
  `temp_user_id` int(10) unsigned NOT NULL DEFAULT 0,
  `lawyer_id` int(10) unsigned NOT NULL DEFAULT 0,
  `message` varchar(2048) DEFAULT NULL,
  `sent_by` varchar(20) NOT NULL,
  `replied` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `FK_postbox_post_user` (`temp_user_id`),
  KEY `FK_postbox_post_user_2` (`lawyer_id`),
  CONSTRAINT `FK_postbox_post_user` FOREIGN KEY (`temp_user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_postbox_post_user_2` FOREIGN KEY (`lawyer_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table whistleblower.postbox_post: ~0 rows (approximately)
DELETE FROM `postbox_post`;
/*!40000 ALTER TABLE `postbox_post` DISABLE KEYS */;
/*!40000 ALTER TABLE `postbox_post` ENABLE KEYS */;

-- Dumping structure for table whistleblower.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL DEFAULT '',
  `password` varchar(512) NOT NULL DEFAULT '',
  `role` varchar(10) NOT NULL DEFAULT '',
  `last_login` datetime DEFAULT NULL,
  `created` datetime NOT NULL DEFAULT current_timestamp(),
  `token_id` varchar(255) DEFAULT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `surname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_username_uindex` (`username`),
  UNIQUE KEY `user_token_id_uindex` (`token_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table whistleblower.user: ~7 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `username`, `password`, `role`, `last_login`, `created`, `token_id`, `first_name`, `surname`) VALUES
	(15, 'Admin', '$2a$10$n1BkMSLK6cZ3RjFYkMZwJO/eZ.Sgx2Z5ehPdE607U/h4o20CZpE6G', 'ADMIN', NULL, '2020-05-08 11:03:05', NULL, NULL, NULL),
	(16, '12575043', '$2a$10$2R8V1RLq8lyo7op2/Wv6f.9H/jyPbUxrwXAtDB9//MrPUnl9iUjZC', 'USER', NULL, '2020-05-08 11:05:43', NULL, NULL, NULL),
	(17, '33764026', '$2a$10$W0vH5FINxU0L5bBzoM1DxOf3aU6Rq8Z67/nt9gTdi5b/P/qJGLmFi', 'USER', '2020-05-08 11:20:57', '2020-05-08 11:11:57', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJzZWN1cmUtYXBpIiwiYXVkIjoic2VjdXJlLWFwcCIsInN1YiI6IjMzNzY0MDI2IiwiZXhwIjoxNTg5ODAwODU3LCJyb2wiOltdfQ.gDv0Jy9YAJfEwnlSizALTfWhuvmSNGdnMQTP--fcUDdh9os6s9VNj8eZrrdus7n8IAyLnNFTw5abod_GATEXOw', NULL, NULL),
	(18, '88088744', '$2a$10$Fu90jJdXShJx7pS0c/U1G.LsCJrbpTsnTam2FPNnJTgCKtAsa7IZ6', 'USER', '2020-05-08 11:14:59', '2020-05-08 11:14:44', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJzZWN1cmUtYXBpIiwiYXVkIjoic2VjdXJlLWFwcCIsInN1YiI6Ijg4MDg4NzQ0IiwiZXhwIjoxNTg5ODAwNDk5LCJyb2wiOltdfQ.tGQ8foRrZ7EPwZ9F_gImUSH89K4TWJxGNqpC1O1fgKhGdpvf2OT0r0_pOAj2LH5XczRxsNMDzzVY35PbrvFXFw', NULL, NULL),
	(19, '92997063', '$2a$10$N1aiSoN7HgIiQcAvCnn9f.P/r5omEqvYt4kQpd/keWw0zDhvx/V3e', 'USER', NULL, '2020-05-08 11:22:06', NULL, NULL, NULL),
	(20, '29999097', '$2a$10$3uqN8APnhogGoyUiYniyC.MzjJhnzoQrz12d6s5rCcKxluxLrTvgu', 'USER', NULL, '2020-05-11 07:29:43', NULL, NULL, NULL),
	(21, '20191330', '$2a$10$ioIRhfwwBJBIzo/ia49Om.58GMvMP1RIRauDDZq/AQHMHnU0GEtpO', 'USER', NULL, '2020-05-11 07:31:51', NULL, NULL, NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
