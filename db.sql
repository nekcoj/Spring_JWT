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

-- Dumping structure for table whistleblower.admin
CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `password` varchar(50) NOT NULL DEFAULT '',
  `latest_login` datetime DEFAULT current_timestamp(),
  `username` varchar(50) NOT NULL,
  `token_id` varchar(255) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `tokenid` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table whistleblower.admin: ~1 rows (approximately)
DELETE FROM `admin`;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` (`id`, `password`, `latest_login`, `username`, `token_id`, `last_login`, `tokenid`, `user_name`) VALUES
	(2, 'asd', '2020-05-06 10:48:13', 'asdad', NULL, NULL, NULL, NULL),
	(3, 'asdd', '2010-05-06 11:04:42', 'ba', NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;

-- Dumping structure for table whistleblower.assigned_issue
CREATE TABLE IF NOT EXISTS `assigned_issue` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category` varchar(1024) NOT NULL DEFAULT 'Annat',
  `when` varchar(1024) NOT NULL,
  `where` varchar(1024) NOT NULL,
  `details` varchar(1024) NOT NULL,
  `employee_awareness` varchar(1024) NOT NULL,
  `attachment` varchar(1024) NOT NULL,
  `temp_user_id` int(10) unsigned NOT NULL,
  `lawyer_id` int(10) unsigned NOT NULL,
  `created` datetime NOT NULL,
  `issue_status` char(15) DEFAULT 'ASSIGNED',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `temp_user_id` (`temp_user_id`) USING BTREE,
  KEY `fk_lawyer_id` (`lawyer_id`),
  CONSTRAINT `fk_lawyer_id` FOREIGN KEY (`lawyer_id`) REFERENCES `lawyer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_temp_user_id` FOREIGN KEY (`temp_user_id`) REFERENCES `temp_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
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
  `when` varchar(1024) NOT NULL,
  `where` varchar(1024) NOT NULL,
  `details` varchar(1024) NOT NULL,
  `employee_awareness` varchar(1024) NOT NULL,
  `attachment` varchar(1024) NOT NULL,
  `temp_user_id` int(10) unsigned NOT NULL,
  `lawyer_id` int(10) unsigned NOT NULL,
  `created` datetime NOT NULL DEFAULT current_timestamp(),
  `issue_status` char(15) DEFAULT 'CLOSED',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `temp_user_id` (`temp_user_id`) USING BTREE,
  KEY `fk_lawyer_id` (`lawyer_id`) USING BTREE,
  CONSTRAINT `closed_issue_ibfk_1` FOREIGN KEY (`lawyer_id`) REFERENCES `lawyer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `closed_issue_ibfk_2` FOREIGN KEY (`temp_user_id`) REFERENCES `temp_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `status_contraint` CHECK (`issue_status` = 'CLOSED')
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- Dumping data for table whistleblower.closed_issue: ~2 rows (approximately)
DELETE FROM `closed_issue`;
/*!40000 ALTER TABLE `closed_issue` DISABLE KEYS */;
INSERT INTO `closed_issue` (`id`, `category`, `when`, `where`, `details`, `employee_awareness`, `attachment`, `temp_user_id`, `lawyer_id`, `created`, `issue_status`) VALUES
	(1, 'Annat', '', '', '', '', '', 3, 22, '2020-05-06 11:06:05', 'CLOSED'),
	(2, 'Annat', '', '', '', '', '', 1, 22, '2009-09-12 11:45:05', 'CLOSED');
/*!40000 ALTER TABLE `closed_issue` ENABLE KEYS */;

-- Dumping structure for table whistleblower.lawyer
CREATE TABLE IF NOT EXISTS `lawyer` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `password` varchar(50) NOT NULL DEFAULT '',
  `latest_login` datetime DEFAULT current_timestamp(),
  `username` varchar(50) NOT NULL,
  `token_id` varchar(255) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `tokenid` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- Dumping data for table whistleblower.lawyer: ~1 rows (approximately)
DELETE FROM `lawyer`;
/*!40000 ALTER TABLE `lawyer` DISABLE KEYS */;
INSERT INTO `lawyer` (`id`, `password`, `latest_login`, `username`, `token_id`, `last_login`, `tokenid`, `user_name`) VALUES
	(1, 'asdasd', '2020-05-06 10:49:40', 'asdasd', NULL, NULL, NULL, NULL),
	(22, 'asdad', '2010-05-06 11:05:05', 'asdasd', NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `lawyer` ENABLE KEYS */;

-- Dumping structure for table whistleblower.new_issue
CREATE TABLE IF NOT EXISTS `new_issue` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category` varchar(1024) NOT NULL DEFAULT 'Annat',
  `when` varchar(1024) NOT NULL,
  `where` varchar(1024) NOT NULL,
  `details` varchar(1024) NOT NULL,
  `employee_awareness` varchar(1024) NOT NULL,
  `attachment` varchar(1024) NOT NULL,
  `temp_user_id` int(10) unsigned NOT NULL,
  `created` datetime NOT NULL,
  `issue_status` char(15) NOT NULL DEFAULT 'UNASSIGNED',
  PRIMARY KEY (`id`),
  KEY `temp_user_id` (`temp_user_id`),
  CONSTRAINT `temp_user_id` FOREIGN KEY (`temp_user_id`) REFERENCES `temp_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `status_contraint` CHECK (`issue_status` = 'UNASSIGNED')
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table whistleblower.new_issue: ~0 rows (approximately)
DELETE FROM `new_issue`;
/*!40000 ALTER TABLE `new_issue` DISABLE KEYS */;
/*!40000 ALTER TABLE `new_issue` ENABLE KEYS */;

-- Dumping structure for table whistleblower.postbox_post
CREATE TABLE IF NOT EXISTS `postbox_post` (
  `id` int(10) unsigned NOT NULL,
  `sent` datetime NOT NULL,
  `temp_user_id` int(10) unsigned NOT NULL DEFAULT 0,
  `lawyer_id` int(10) unsigned NOT NULL DEFAULT 0,
  `message` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_temp_user_id2` (`temp_user_id`),
  KEY `fk_lawyer_id2` (`lawyer_id`),
  CONSTRAINT `fk_lawyer_id2` FOREIGN KEY (`lawyer_id`) REFERENCES `lawyer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_temp_user_id2` FOREIGN KEY (`temp_user_id`) REFERENCES `temp_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table whistleblower.postbox_post: ~0 rows (approximately)
DELETE FROM `postbox_post`;
/*!40000 ALTER TABLE `postbox_post` DISABLE KEYS */;
/*!40000 ALTER TABLE `postbox_post` ENABLE KEYS */;

-- Dumping structure for table whistleblower.temp_user
CREATE TABLE IF NOT EXISTS `temp_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `password` varchar(50) NOT NULL DEFAULT '',
  `latest_login` datetime DEFAULT current_timestamp(),
  `username` varchar(50) NOT NULL,
  `token_id` varchar(255) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `tokenid` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- Dumping data for table whistleblower.temp_user: ~1 rows (approximately)
DELETE FROM `temp_user`;
/*!40000 ALTER TABLE `temp_user` DISABLE KEYS */;
INSERT INTO `temp_user` (`id`, `password`, `latest_login`, `username`, `token_id`, `last_login`, `tokenid`, `user_name`) VALUES
	(1, 'qweqwe', '2020-05-06 10:50:48', 'hththh', NULL, NULL, NULL, NULL),
	(3, 'asddd', '2000-05-06 11:05:28', 'asdasd', NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `temp_user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
