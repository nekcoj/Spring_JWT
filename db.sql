-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Värd: 127.0.0.1
-- Tid vid skapande: 20 maj 2020 kl 17:12
-- Serverversion: 10.4.11-MariaDB
-- PHP-version: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databas: `whistleblower`
--

-- --------------------------------------------------------

--
-- Tabellstruktur `category`
--

CREATE TABLE `category` (
  `id` int(10) UNSIGNED NOT NULL,
  `category_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumpning av Data i tabell `category`
--

INSERT INTO `category` (`id`, `category_name`) VALUES
(1, 'Annat'),
(4, 'Bedrägeri, missbruk och stöld'),
(2, 'Dataskydd och brott mot IT-säkerhet'),
(3, 'Diskriminering, trakasserier och andra arbetsrelaterade lagproblem'),
(5, 'Hälsa, säkerhet & miljö'),
(8, 'Mutor, korruption & förfalskning'),
(6, 'Penningtvätt'),
(7, 'Personal');

-- --------------------------------------------------------

--
-- Tabellstruktur `issue`
--

CREATE TABLE `issue` (
  `id` int(10) UNSIGNED NOT NULL,
  `category_id` int(10) UNSIGNED DEFAULT NULL,
  `when_issue` varchar(1024) NOT NULL,
  `where_issue` varchar(1024) NOT NULL,
  `details` varchar(1024) NOT NULL,
  `employee_awareness` varchar(1024) NOT NULL,
  `attachment` varchar(1024) DEFAULT NULL,
  `temp_user_id` int(10) UNSIGNED NOT NULL,
  `lawyer_id` int(10) UNSIGNED DEFAULT NULL,
  `created` datetime NOT NULL,
  `assigned` datetime DEFAULT NULL,
  `issue_status_id` int(10) UNSIGNED NOT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Dumpning av Data i tabell `issue`
--

INSERT INTO `issue` (`id`, `category_id`, `when_issue`, `where_issue`, `details`, `employee_awareness`, `attachment`, `temp_user_id`, `lawyer_id`, `created`, `assigned`, `issue_status_id`, `active`) VALUES
(16, 1, 'idag', 'utomhus', 'tagen på bar gärning', 'absolut', 'postman.com', 46, 45, '2020-05-15 08:20:02', '2020-05-15 08:26:42', 2, b'1'),
(17, 1, 'idag', 'utomhus', 'tagen på bar gärning', 'absolut', 'postman.com', 47, 45, '2020-05-18 06:50:48', '2020-05-18 07:39:13', 2, b'1'),
(18, 1, 'idag', 'utomhus', 'tagen på bar gärning', 'absolut', 'postman.com', 48, NULL, '2020-05-18 08:36:28', NULL, 1, b'1'),
(19, 1, 'idag', 'utomhus', 'tagen på bar gärning', 'absolut', 'postman.com', 49, NULL, '2020-05-18 08:43:49', NULL, 1, b'1');

-- --------------------------------------------------------

--
-- Tabellstruktur `issue_status`
--

CREATE TABLE `issue_status` (
  `id` int(10) UNSIGNED NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumpning av Data i tabell `issue_status`
--

INSERT INTO `issue_status` (`id`, `status`) VALUES
(1, 'UNASSIGNED'),
(2, 'ASSIGNED'),
(3, 'OPEN'),
(4, 'CLOSED');

-- --------------------------------------------------------

--
-- Tabellstruktur `postbox_post`
--

CREATE TABLE `postbox_post` (
  `id` int(10) UNSIGNED NOT NULL,
  `sent_date` datetime NOT NULL,
  `temp_user_id` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `lawyer_id` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `message` varchar(2048) NOT NULL,
  `replied_date` datetime DEFAULT NULL,
  `reply` text DEFAULT NULL,
  `replied` bit(1) NOT NULL DEFAULT b'0',
  `sent` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumpning av Data i tabell `postbox_post`
--

INSERT INTO `postbox_post` (`id`, `sent_date`, `temp_user_id`, `lawyer_id`, `message`, `replied_date`, `reply`, `replied`, `sent`) VALUES
(1, '2020-05-18 08:17:42', 46, 45, 'Meddelande från jurist till visslare', NULL, NULL, b'0', NULL);

-- --------------------------------------------------------

--
-- Tabellstruktur `user`
--

CREATE TABLE `user` (
  `id` int(10) UNSIGNED NOT NULL,
  `username` varchar(50) NOT NULL DEFAULT '',
  `password` varchar(512) NOT NULL DEFAULT '',
  `role` varchar(10) NOT NULL DEFAULT '',
  `last_login` datetime DEFAULT NULL,
  `created` datetime NOT NULL DEFAULT current_timestamp(),
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `enabled` bit(1) NOT NULL DEFAULT b'1',
  `consent` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumpning av Data i tabell `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `role`, `last_login`, `created`, `first_name`, `last_name`, `enabled`, `consent`) VALUES
(44, 'Admin', '$2a$10$nvVOs2DFIaHFoHXxuVU4bedqHPWuK8qWoU3Aqp.pTvom/EESftnES', 'ADMIN', '2020-05-18 08:20:26', '2020-05-15 08:09:24', NULL, NULL, b'0', b'0'),
(45, 'Lawyer', '$2a$10$ua7Y3SAJfquUXbs/aNqEqOyRWFVhHb4fw38fwnlCbmsF9cs7S1dVq', 'LAWYER', '2020-05-18 08:24:37', '2020-05-15 08:09:24', NULL, NULL, b'0', b'0'),
(46, '21316156', '$2a$10$XdE5t92lTFbtdqwtOx9Aw.4PKnEZd/8po4kmJh.9IkthDdJZSqIEa', 'USER', '2020-05-15 08:20:40', '2020-05-15 08:20:02', NULL, NULL, b'1', b'0'),
(47, '34302446', '$2a$10$Yx3.SHhwhkmpAmIAP8hx7uboQ1x5aazckQ.nD.mtD9lVj.3BoYPuS', 'USER', '2020-05-18 06:52:42', '2020-05-18 06:50:48', NULL, NULL, b'1', b'0'),
(48, '05626407', '$2a$10$lRCfo6ipBsCilQcVyhezDecd9NaJ44lFyStLNJujL/O7t85FwMtc.', 'USER', NULL, '2020-05-18 08:36:28', NULL, NULL, b'1', b'0'),
(49, '95615564', '$2a$10$lou6xoGQ13x464Vj3Zo4yesOlepg1pR3gDVXgCw5QcW/PVV4ZLdd.', 'USER', '2020-05-18 08:44:19', '2020-05-18 08:43:49', NULL, NULL, b'1', b'0');

--
-- Index för dumpade tabeller
--

--
-- Index för tabell `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `category_category_name_uindex` (`category_name`);

--
-- Index för tabell `issue`
--
ALTER TABLE `issue`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD KEY `temp_user_id` (`temp_user_id`) USING BTREE,
  ADD KEY `assigned_issue_user_id_fk` (`lawyer_id`),
  ADD KEY `FK_issue_category` (`category_id`),
  ADD KEY `FK_issue_issue_status` (`issue_status_id`);

--
-- Index för tabell `issue_status`
--
ALTER TABLE `issue_status`
  ADD PRIMARY KEY (`id`);

--
-- Index för tabell `postbox_post`
--
ALTER TABLE `postbox_post`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `FK_postbox_post_user` (`temp_user_id`),
  ADD KEY `FK_postbox_post_user_2` (`lawyer_id`);

--
-- Index för tabell `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user_username_uindex` (`username`);

--
-- AUTO_INCREMENT för dumpade tabeller
--

--
-- AUTO_INCREMENT för tabell `category`
--
ALTER TABLE `category`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT för tabell `issue`
--
ALTER TABLE `issue`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT för tabell `issue_status`
--
ALTER TABLE `issue_status`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT för tabell `postbox_post`
--
ALTER TABLE `postbox_post`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT för tabell `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- Restriktioner för dumpade tabeller
--

--
-- Restriktioner för tabell `issue`
--
ALTER TABLE `issue`
  ADD CONSTRAINT `FK_issue_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_issue_issue_status` FOREIGN KEY (`issue_status_id`) REFERENCES `issue_status` (`id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `FKkbbg50l7cewo3wo6737ioh54d` FOREIGN KEY (`temp_user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `assigned_issue_user_id_fk` FOREIGN KEY (`lawyer_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restriktioner för tabell `postbox_post`
--
ALTER TABLE `postbox_post`
  ADD CONSTRAINT `FK_postbox_post_user` FOREIGN KEY (`temp_user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_postbox_post_user_2` FOREIGN KEY (`lawyer_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

DELIMITER $$
--
-- Händelser
--
CREATE DEFINER=`root`@`localhost` EVENT `clear_records_older_than_6_month` ON SCHEDULE EVERY 1 DAY STARTS '2020-05-07 01:00:00' ON COMPLETION PRESERVE ENABLE DO DELETE FROM admin  WHERE latest_login < DATE_SUB(NOW(), INTERVAL 6 MONTH)$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
