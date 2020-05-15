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

-- Dumping structure for table whistleblower.category
CREATE TABLE IF NOT EXISTS `category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category_name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_category_name_uindex` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table whistleblower.category: ~11 rows (approximately)
DELETE FROM `category`;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`id`, `category_name`) VALUES
	(1, 'Annat'),
	(15, 'banan tjuvar'),
	(18, 'bananfobi'),
	(4, 'Bedrägeri, missbruk och stöld'),
	(2, 'Dataskydd och brott mot IT-säkerhet'),
	(3, 'Diskriminering, trakasserier och andra arbetsrelaterade lagproblem'),
	(5, 'Hälsa, säkerhet & miljö'),
	(8, 'Mutor, korruption & förfalskning'),
	(6, 'Penningtvätt'),
	(7, 'Personal'),
	(16, 'tomtar på loftet');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- Dumping structure for event whistleblower.clear_records_older_than_6_month
DELIMITER //
CREATE EVENT `clear_records_older_than_6_month` ON SCHEDULE EVERY 1 DAY STARTS '2020-05-07 01:00:00' ON COMPLETION PRESERVE ENABLE DO DELETE FROM admin  WHERE latest_login < DATE_SUB(NOW(), INTERVAL 6 MONTH)//
DELIMITER ;

-- Dumping structure for table whistleblower.issue
CREATE TABLE IF NOT EXISTS `issue` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category_id` int(10) unsigned DEFAULT NULL,
  `when_issue` varchar(1024) NOT NULL,
  `where_issue` varchar(1024) NOT NULL,
  `details` varchar(1024) NOT NULL,
  `employee_awareness` varchar(1024) NOT NULL,
  `attachment` varchar(1024) DEFAULT NULL,
  `temp_user_id` int(10) unsigned NOT NULL,
  `lawyer_id` int(10) unsigned DEFAULT NULL,
  `created` datetime NOT NULL,
  `assigned` datetime DEFAULT NULL,
  `issue_status_id` int(10) unsigned NOT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `temp_user_id` (`temp_user_id`) USING BTREE,
  KEY `assigned_issue_user_id_fk` (`lawyer_id`),
  KEY `FK_issue_category` (`category_id`),
  KEY `FK_issue_issue_status` (`issue_status_id`),
  CONSTRAINT `FK_issue_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `FK_issue_issue_status` FOREIGN KEY (`issue_status_id`) REFERENCES `issue_status` (`id`) ON UPDATE NO ACTION,
  CONSTRAINT `FKkbbg50l7cewo3wo6737ioh54d` FOREIGN KEY (`temp_user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `assigned_issue_user_id_fk` FOREIGN KEY (`lawyer_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- Dumping data for table whistleblower.issue: ~1 rows (approximately)
DELETE FROM `issue`;
/*!40000 ALTER TABLE `issue` DISABLE KEYS */;
INSERT INTO `issue` (`id`, `category_id`, `when_issue`, `where_issue`, `details`, `employee_awareness`, `attachment`, `temp_user_id`, `lawyer_id`, `created`, `assigned`, `issue_status_id`, `active`) VALUES
	(16, 1, 'idag', 'utomhus', 'tagen på bar gärning', 'absolut', 'postman.com', 46, 45, '2020-05-15 08:20:02', '2020-05-15 08:26:42', 2, b'1');
/*!40000 ALTER TABLE `issue` ENABLE KEYS */;

-- Dumping structure for table whistleblower.issue_status
CREATE TABLE IF NOT EXISTS `issue_status` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table whistleblower.issue_status: ~4 rows (approximately)
DELETE FROM `issue_status`;
/*!40000 ALTER TABLE `issue_status` DISABLE KEYS */;
INSERT INTO `issue_status` (`id`, `status`) VALUES
	(1, 'UNASSIGNED'),
	(2, 'ASSIGNED'),
	(3, 'OPEN'),
	(4, 'CLOSED');
/*!40000 ALTER TABLE `issue_status` ENABLE KEYS */;

-- Dumping structure for table whistleblower.postbox_post
CREATE TABLE IF NOT EXISTS `postbox_post` (
  `id` int(10) unsigned NOT NULL,
  `sent_date` datetime NOT NULL,
  `temp_user_id` int(10) unsigned NOT NULL DEFAULT 0,
  `lawyer_id` int(10) unsigned NOT NULL DEFAULT 0,
  `message` varchar(2048) NOT NULL,
  `replied_date` datetime DEFAULT NULL,
  `reply` text DEFAULT NULL,
  `replied` bit(1) NOT NULL,
  `sent` datetime DEFAULT NULL,
  `sent_by` varchar(255) DEFAULT NULL,
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
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `enabled` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table whistleblower.user: ~3 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `username`, `password`, `role`, `last_login`, `created`, `first_name`, `last_name`, `enabled`) VALUES
	(44, 'Admin', '$2a$10$nvVOs2DFIaHFoHXxuVU4bedqHPWuK8qWoU3Aqp.pTvom/EESftnES', 'ADMIN', '2020-05-15 09:09:15', '2020-05-15 08:09:24', NULL, NULL, b'0'),
	(45, 'Lawyer', '$2a$10$ua7Y3SAJfquUXbs/aNqEqOyRWFVhHb4fw38fwnlCbmsF9cs7S1dVq', 'LAWYER', '2020-05-15 09:08:34', '2020-05-15 08:09:24', NULL, NULL, b'0'),
	(46, '21316156', '$2a$10$XdE5t92lTFbtdqwtOx9Aw.4PKnEZd/8po4kmJh.9IkthDdJZSqIEa', 'USER', '2020-05-15 08:20:40', '2020-05-15 08:20:02', NULL, NULL, b'1');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
