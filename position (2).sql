-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3308
-- Généré le :  lun. 22 nov. 2021 à 13:32
-- Version du serveur :  8.0.18
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `position`
--

-- --------------------------------------------------------

--
-- Structure de la table `api_keys`
--

DROP TABLE IF EXISTS `api_keys`;
CREATE TABLE IF NOT EXISTS `api_keys` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `key` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `api_keys_name_index` (`name`),
  KEY `api_keys_key_index` (`key`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `api_keys`
--

INSERT INTO `api_keys` (`id`, `name`, `key`, `active`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 'web', 'dEeeqWdIr5AaXAKFREAG5Pu33QkR25uOASgFxIkxFDz2wkp13BSP5xGSQGcARf1M', 1, '2021-11-13 08:39:02', '2021-11-13 08:39:02', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `api_key_access_events`
--

DROP TABLE IF EXISTS `api_key_access_events`;
CREATE TABLE IF NOT EXISTS `api_key_access_events` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `api_key_id` int(10) UNSIGNED NOT NULL,
  `ip_address` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `url` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `api_key_access_events_ip_address_index` (`ip_address`),
  KEY `api_key_access_events_api_key_id_foreign` (`api_key_id`)
) ENGINE=MyISAM AUTO_INCREMENT=259 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `api_key_access_events`
--

INSERT INTO `api_key_access_events` (`id`, `api_key_id`, `ip_address`, `url`, `created_at`, `updated_at`) VALUES
(1, 1, '127.0.0.1', 'http://localhost:8000/api/categories', '2021-11-13 08:39:53', '2021-11-13 08:39:53'),
(2, 1, '127.0.0.1', 'http://localhost:8000/api/categories', '2021-11-13 08:43:08', '2021-11-13 08:43:08'),
(3, 1, '127.0.0.1', 'http://localhost:8000/api/categories', '2021-11-13 08:59:18', '2021-11-13 08:59:18'),
(4, 1, '127.0.0.1', 'http://localhost:8000/api/categories', '2021-11-13 09:05:30', '2021-11-13 09:05:30'),
(5, 1, '127.0.0.1', 'http://localhost:8000/api/categories', '2021-11-13 10:22:58', '2021-11-13 10:22:58'),
(6, 1, '127.0.0.1', 'http://localhost:8000/api/categories', '2021-11-13 10:23:39', '2021-11-13 10:23:39'),
(7, 1, '127.0.0.1', 'http://localhost:8000/api/categories', '2021-11-13 10:27:25', '2021-11-13 10:27:25'),
(8, 1, '127.0.0.1', 'http://localhost:8000/api/categories', '2021-11-13 10:27:58', '2021-11-13 10:27:58'),
(9, 1, '127.0.0.1', 'http://localhost:8000/api/categories', '2021-11-13 13:14:16', '2021-11-13 13:14:16'),
(10, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/auth/login', '2021-11-13 17:48:15', '2021-11-13 17:48:15'),
(11, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/user/me', '2021-11-13 17:48:16', '2021-11-13 17:48:16'),
(12, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/categories', '2021-11-13 17:48:31', '2021-11-13 17:48:31'),
(13, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/categories', '2021-11-13 17:50:19', '2021-11-13 17:50:19'),
(14, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/categories', '2021-11-13 17:51:52', '2021-11-13 17:51:52'),
(15, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/categories', '2021-11-13 17:54:56', '2021-11-13 17:54:56'),
(16, 1, '127.0.0.1', 'http://localhost:8000/api/categories', '2021-11-13 17:58:53', '2021-11-13 17:58:53'),
(17, 1, '127.0.0.1', 'http://localhost:8000/api/categories', '2021-11-13 18:03:56', '2021-11-13 18:03:56'),
(18, 1, '127.0.0.1', 'http://localhost:8000/api/categories', '2021-11-13 18:04:35', '2021-11-13 18:04:35'),
(19, 1, '127.0.0.1', 'http://localhost:8000/api/categories', '2021-11-13 18:05:10', '2021-11-13 18:05:10'),
(20, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/categories', '2021-11-13 18:13:09', '2021-11-13 18:13:09'),
(21, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/categories', '2021-11-13 18:14:52', '2021-11-13 18:14:52'),
(22, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/categories', '2021-11-13 18:19:13', '2021-11-13 18:19:13'),
(23, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/categories', '2021-11-13 18:20:58', '2021-11-13 18:20:58'),
(24, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/categories', '2021-11-13 18:25:03', '2021-11-13 18:25:03'),
(25, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/categories', '2021-11-14 08:57:06', '2021-11-14 08:57:06'),
(26, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/categories', '2021-11-14 08:57:19', '2021-11-14 08:57:19'),
(27, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/categories', '2021-11-14 08:59:29', '2021-11-14 08:59:29'),
(28, 1, '127.0.0.1', 'http://localhost:8000/api/search/categories?q=tourisme', '2021-11-16 09:40:53', '2021-11-16 09:40:53'),
(29, 1, '127.0.0.1', 'http://localhost:8000/api/search/souscategories?q=tourisme', '2021-11-16 09:43:39', '2021-11-16 09:43:39'),
(30, 1, '127.0.0.1', 'http://localhost:8000/api/categories/25', '2021-11-16 09:44:13', '2021-11-16 09:44:13'),
(31, 1, '127.0.0.1', 'http://localhost:8000/api/search/souscategories?q=voyage', '2021-11-16 09:45:00', '2021-11-16 09:45:00'),
(32, 1, '127.0.0.1', 'http://localhost:8000/api/search/souscategories?q=croissant', '2021-11-16 09:45:24', '2021-11-16 09:45:24'),
(33, 1, '127.0.0.1', 'http://localhost:8000/api/search/souscategories?q=boulangerie', '2021-11-16 09:45:34', '2021-11-16 09:45:34'),
(34, 1, '127.0.0.1', 'http://localhost:8000/api/search/souscategories?q=boulangerie', '2021-11-16 09:55:23', '2021-11-16 09:55:23'),
(35, 1, '127.0.0.1', 'http://localhost:8000/api/auth/login', '2021-11-16 09:59:11', '2021-11-16 09:59:11'),
(36, 1, '127.0.0.1', 'http://localhost:8000/api/batiments', '2021-11-16 10:10:27', '2021-11-16 10:10:27'),
(37, 1, '127.0.0.1', 'http://localhost:8000/api/batiments', '2021-11-16 10:13:16', '2021-11-16 10:13:16'),
(38, 1, '127.0.0.1', 'http://localhost:8000/api/etablissements', '2021-11-16 10:17:06', '2021-11-16 10:17:06'),
(39, 1, '127.0.0.1', 'http://localhost:8000/api/search/souscategories?q=boulangerie', '2021-11-16 10:18:13', '2021-11-16 10:18:13'),
(40, 1, '127.0.0.1', 'http://localhost:8000/api/search/souscategories?q=boulangerie', '2021-11-16 10:25:11', '2021-11-16 10:25:11'),
(41, 1, '127.0.0.1', 'http://localhost:8000/api/etablissements', '2021-11-16 10:26:09', '2021-11-16 10:26:09'),
(42, 1, '127.0.0.1', 'http://localhost:8000/api/search/souscategories?q=boulangerie', '2021-11-16 10:26:39', '2021-11-16 10:26:39'),
(43, 1, '127.0.0.1', 'http://localhost:8000/api/search/souscategories?q=boulangerie', '2021-11-16 10:27:40', '2021-11-16 10:27:40'),
(44, 1, '127.0.0.1', 'http://localhost:8000/api/etablissements', '2021-11-16 10:30:52', '2021-11-16 10:30:52'),
(45, 1, '127.0.0.1', 'http://localhost:8000/api/etablissements/3', '2021-11-16 10:31:05', '2021-11-16 10:31:05'),
(46, 1, '127.0.0.1', 'http://localhost:8000/api/search/souscategories?q=boulangerie', '2021-11-16 10:31:16', '2021-11-16 10:31:16'),
(47, 1, '127.0.0.1', 'http://localhost:8000/api/search/souscategories?q=boulangerie', '2021-11-16 10:32:07', '2021-11-16 10:32:07'),
(48, 1, '127.0.0.1', 'http://localhost:8000/api/search/souscategories?q=boulangerie', '2021-11-16 10:33:43', '2021-11-16 10:33:43'),
(49, 1, '127.0.0.1', 'http://localhost:8000/api/search/souscategories?q=boulangerie', '2021-11-16 10:35:44', '2021-11-16 10:35:44'),
(50, 1, '127.0.0.1', 'http://localhost:8000/api/etablissements/3', '2021-11-16 10:36:26', '2021-11-16 10:36:26'),
(51, 1, '127.0.0.1', 'http://localhost:8000/api/search/souscategories?q=boulangerie', '2021-11-16 10:37:36', '2021-11-16 10:37:36'),
(52, 1, '127.0.0.1', 'http://localhost:8000/api/search/souscategories?q=boulangerie', '2021-11-16 10:37:58', '2021-11-16 10:37:58'),
(53, 1, '127.0.0.1', 'http://localhost:8000/api/search/souscategories?q=boulangerie', '2021-11-16 10:38:48', '2021-11-16 10:38:48'),
(54, 1, '127.0.0.1', 'http://localhost:8000/api/search/etablissements?q=sogefi', '2021-11-16 10:45:58', '2021-11-16 10:45:58'),
(55, 1, '127.0.0.1', 'http://localhost:8000/api/search/etablissements?q=sogefi', '2021-11-16 10:46:28', '2021-11-16 10:46:28'),
(56, 1, '127.0.0.1', 'http://localhost:8000/api/search/etablissements?q=devant', '2021-11-16 10:47:41', '2021-11-16 10:47:41'),
(57, 1, '192.168.225.221', 'http://192.168.225.221:8000/api/search/souscategories?q=tourisme', '2021-11-16 11:30:23', '2021-11-16 11:30:23'),
(58, 1, '192.168.225.221', 'http://192.168.225.221:8000/api/search/souscategories?q=boulangerie', '2021-11-16 11:30:36', '2021-11-16 11:30:36'),
(59, 1, '192.168.225.221', 'http://192.168.225.221:8000/api/search/souscategories?q=boulangerie', '2021-11-16 12:03:55', '2021-11-16 12:03:55'),
(60, 1, '192.168.225.221', 'http://192.168.225.221:8000/api/search/souscategories?q=boulangerie', '2021-11-16 12:04:42', '2021-11-16 12:04:42'),
(61, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/search/etablissements?q=sogefi', '2021-11-16 15:15:06', '2021-11-16 15:15:06'),
(62, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/search/etablissements?q=sogefi', '2021-11-16 15:18:37', '2021-11-16 15:18:37'),
(63, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/search/etablissements?q=sogefi', '2021-11-16 15:19:06', '2021-11-16 15:19:06'),
(64, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/batiments', '2021-11-17 14:26:47', '2021-11-17 14:26:47'),
(65, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/auth/login', '2021-11-17 14:39:11', '2021-11-17 14:39:11'),
(66, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/user/me', '2021-11-17 14:39:15', '2021-11-17 14:39:15'),
(67, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/batiments', '2021-11-17 14:40:21', '2021-11-17 14:40:21'),
(68, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/user/me', '2021-11-17 14:48:55', '2021-11-17 14:48:55'),
(69, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/commercial', '2021-11-17 14:49:50', '2021-11-17 14:49:50'),
(70, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/commercial', '2021-11-17 14:50:47', '2021-11-17 14:50:47'),
(71, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-17 14:52:42', '2021-11-17 14:52:42'),
(72, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/batiments', '2021-11-17 14:53:41', '2021-11-17 14:53:41'),
(73, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-17 14:57:17', '2021-11-17 14:57:17'),
(74, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/batiments', '2021-11-17 14:58:02', '2021-11-17 14:58:02'),
(75, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/batiments/3', '2021-11-17 15:01:25', '2021-11-17 15:01:25'),
(76, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-17 15:27:55', '2021-11-17 15:27:55'),
(77, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/batiments', '2021-11-17 15:28:40', '2021-11-17 15:28:40'),
(78, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-17 15:30:42', '2021-11-17 15:30:42'),
(79, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-17 15:34:57', '2021-11-17 15:34:57'),
(80, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/batiments', '2021-11-17 15:37:16', '2021-11-17 15:37:16'),
(81, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/batiments', '2021-11-17 15:39:09', '2021-11-17 15:39:09'),
(82, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/batiments', '2021-11-17 15:39:27', '2021-11-17 15:39:27'),
(83, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/batiments', '2021-11-17 15:41:10', '2021-11-17 15:41:10'),
(84, 1, '127.0.0.1', 'http://localhost:8000/api/batiments', '2021-11-17 15:42:37', '2021-11-17 15:42:37'),
(85, 1, '127.0.0.1', 'http://localhost:8000/api/batiments', '2021-11-17 15:43:17', '2021-11-17 15:43:17'),
(86, 1, '127.0.0.1', 'http://localhost:8000/api/batiments', '2021-11-17 15:43:56', '2021-11-17 15:43:56'),
(87, 1, '127.0.0.1', 'http://localhost:8000/api/batiments', '2021-11-17 15:46:13', '2021-11-17 15:46:13'),
(88, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-17 20:43:40', '2021-11-17 20:43:40'),
(89, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/categories', '2021-11-17 20:43:42', '2021-11-17 20:43:42'),
(90, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/batiments', '2021-11-17 20:45:56', '2021-11-17 20:45:56'),
(91, 1, '127.0.0.1', 'http://localhost:8000/api/categories', '2021-11-18 15:14:23', '2021-11-18 15:14:23'),
(92, 1, '127.0.0.1', 'http://localhost:8000/api/categories', '2021-11-18 15:23:31', '2021-11-18 15:23:31'),
(93, 1, '127.0.0.1', 'http://localhost:8000/api/categories', '2021-11-18 15:29:18', '2021-11-18 15:29:18'),
(94, 1, '127.0.0.1', 'http://localhost:8000/api/categories', '2021-11-18 15:29:19', '2021-11-18 15:29:19'),
(95, 1, '127.0.0.1', 'http://localhost:8000/api/categories', '2021-11-18 15:30:32', '2021-11-18 15:30:32'),
(96, 1, '127.0.0.1', 'http://localhost:8000/api/categories', '2021-11-18 15:30:33', '2021-11-18 15:30:33'),
(97, 1, '127.0.0.1', 'http://localhost:8000/api/categories', '2021-11-18 15:31:12', '2021-11-18 15:31:12'),
(98, 1, '127.0.0.1', 'http://localhost:8000/api/auth/login', '2021-11-18 23:35:39', '2021-11-18 23:35:39'),
(99, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/commercial', '2021-11-18 23:36:03', '2021-11-18 23:36:03'),
(100, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/commercial', '2021-11-18 23:41:43', '2021-11-18 23:41:43'),
(101, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/commercial', '2021-11-18 23:42:39', '2021-11-18 23:42:39'),
(102, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/commercial', '2021-11-18 23:46:10', '2021-11-18 23:46:10'),
(103, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/commercial', '2021-11-18 23:55:02', '2021-11-18 23:55:02'),
(104, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/auth/login', '2021-11-19 11:36:15', '2021-11-19 11:36:15'),
(105, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/user/me', '2021-11-19 11:36:16', '2021-11-19 11:36:16'),
(106, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-19 11:37:36', '2021-11-19 11:37:36'),
(107, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/categories', '2021-11-19 11:37:37', '2021-11-19 11:37:37'),
(108, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/etablissements', '2021-11-19 11:38:57', '2021-11-19 11:38:57'),
(109, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/etablissements', '2021-11-19 11:41:08', '2021-11-19 11:41:08'),
(110, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/etablissements/4', '2021-11-19 11:43:43', '2021-11-19 11:43:43'),
(111, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/images', '2021-11-19 11:43:44', '2021-11-19 11:43:44'),
(112, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/images', '2021-11-19 11:43:45', '2021-11-19 11:43:45'),
(113, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/telephones', '2021-11-19 11:43:45', '2021-11-19 11:43:45'),
(114, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/telephones', '2021-11-19 11:43:46', '2021-11-19 11:43:46'),
(115, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/telephones', '2021-11-19 11:43:46', '2021-11-19 11:43:46'),
(116, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/horaires', '2021-11-19 11:43:47', '2021-11-19 11:43:47'),
(117, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/images', '2021-11-19 11:43:47', '2021-11-19 11:43:47'),
(118, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/horaires', '2021-11-19 11:43:48', '2021-11-19 11:43:48'),
(119, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/horaires', '2021-11-19 11:43:48', '2021-11-19 11:43:48'),
(120, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/horaires', '2021-11-19 11:43:49', '2021-11-19 11:43:49'),
(121, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/horaires', '2021-11-19 11:43:49', '2021-11-19 11:43:49'),
(122, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/horaires', '2021-11-19 11:43:50', '2021-11-19 11:43:50'),
(123, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/horaires', '2021-11-19 11:43:50', '2021-11-19 11:43:50'),
(124, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-19 13:14:04', '2021-11-19 13:14:04'),
(125, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/categories', '2021-11-19 13:14:06', '2021-11-19 13:14:06'),
(126, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/etablissements', '2021-11-19 13:17:22', '2021-11-19 13:17:22'),
(127, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/etablissements/5', '2021-11-19 13:20:38', '2021-11-19 13:20:38'),
(128, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/images', '2021-11-19 13:20:38', '2021-11-19 13:20:38'),
(129, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/images', '2021-11-19 13:20:39', '2021-11-19 13:20:39'),
(130, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/images', '2021-11-19 13:20:39', '2021-11-19 13:20:39'),
(131, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/images', '2021-11-19 13:20:39', '2021-11-19 13:20:39'),
(132, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-19 13:54:27', '2021-11-19 13:54:27'),
(133, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/categories', '2021-11-19 13:54:27', '2021-11-19 13:54:27'),
(134, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/etablissements', '2021-11-19 13:56:18', '2021-11-19 13:56:18'),
(135, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/etablissements/6', '2021-11-19 13:58:24', '2021-11-19 13:58:24'),
(136, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/telephones', '2021-11-19 13:58:24', '2021-11-19 13:58:24'),
(137, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/telephones', '2021-11-19 13:58:24', '2021-11-19 13:58:24'),
(138, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/telephones', '2021-11-19 13:58:24', '2021-11-19 13:58:24'),
(139, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/horaires', '2021-11-19 14:00:43', '2021-11-19 14:00:43'),
(140, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/horaires', '2021-11-19 14:00:43', '2021-11-19 14:00:43'),
(141, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/images', '2021-11-19 14:02:43', '2021-11-19 14:02:43'),
(142, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-19 14:19:19', '2021-11-19 14:19:19'),
(143, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/categories', '2021-11-19 14:19:20', '2021-11-19 14:19:20'),
(144, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/etablissements', '2021-11-19 14:20:00', '2021-11-19 14:20:00'),
(145, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/etablissements/7', '2021-11-19 14:20:56', '2021-11-19 14:20:56'),
(146, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/telephones', '2021-11-19 14:20:56', '2021-11-19 14:20:56'),
(147, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/telephones', '2021-11-19 14:20:57', '2021-11-19 14:20:57'),
(148, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/horaires', '2021-11-19 14:22:49', '2021-11-19 14:22:49'),
(149, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/horaires', '2021-11-19 14:22:49', '2021-11-19 14:22:49'),
(150, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/horaires', '2021-11-19 14:22:50', '2021-11-19 14:22:50'),
(151, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/horaires', '2021-11-19 14:22:50', '2021-11-19 14:22:50'),
(152, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/horaires', '2021-11-19 14:22:50', '2021-11-19 14:22:50'),
(153, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/images', '2021-11-19 14:25:38', '2021-11-19 14:25:38'),
(154, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-19 14:58:58', '2021-11-19 14:58:58'),
(155, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/categories', '2021-11-19 14:58:59', '2021-11-19 14:58:59'),
(156, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/etablissements', '2021-11-19 14:59:27', '2021-11-19 14:59:27'),
(157, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/etablissements/8', '2021-11-19 15:00:00', '2021-11-19 15:00:00'),
(158, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/etablissements/8', '2021-11-19 15:02:33', '2021-11-19 15:02:33'),
(159, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/telephones', '2021-11-19 15:02:34', '2021-11-19 15:02:34'),
(160, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/telephones', '2021-11-19 15:02:34', '2021-11-19 15:02:34'),
(161, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/telephones', '2021-11-19 15:02:35', '2021-11-19 15:02:35'),
(162, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-19 15:17:32', '2021-11-19 15:17:32'),
(163, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/categories', '2021-11-19 15:17:33', '2021-11-19 15:17:33'),
(164, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/etablissements', '2021-11-19 15:17:55', '2021-11-19 15:17:55'),
(165, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/etablissements/9', '2021-11-19 15:18:21', '2021-11-19 15:18:21'),
(166, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/telephones', '2021-11-19 15:18:22', '2021-11-19 15:18:22'),
(167, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/telephones', '2021-11-19 15:18:22', '2021-11-19 15:18:22'),
(168, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/telephones', '2021-11-19 15:18:23', '2021-11-19 15:18:23'),
(169, 1, '127.0.0.1', 'http://localhost:8000/api/auth/login', '2021-11-19 21:33:11', '2021-11-19 21:33:11'),
(170, 1, '127.0.0.1', 'http://localhost:8000/api/user/me', '2021-11-19 21:33:53', '2021-11-19 21:33:53'),
(171, 1, '127.0.0.1', 'http://localhost:8000/api/user/me', '2021-11-19 21:35:40', '2021-11-19 21:35:40'),
(172, 1, '127.0.0.1', 'http://localhost:8000/api/user/me', '2021-11-19 21:36:56', '2021-11-19 21:36:56'),
(173, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/auth/login', '2021-11-19 21:49:31', '2021-11-19 21:49:31'),
(174, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/user/me', '2021-11-19 21:49:32', '2021-11-19 21:49:32'),
(175, 1, '127.0.0.1', 'http://localhost:8000/api/user/me', '2021-11-20 09:54:35', '2021-11-20 09:54:35'),
(176, 1, '127.0.0.1', 'http://localhost:8000/api/tracking', '2021-11-20 09:55:13', '2021-11-20 09:55:13'),
(177, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/tracking', '2021-11-20 10:05:32', '2021-11-20 10:05:32'),
(178, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/tracking', '2021-11-20 10:05:36', '2021-11-20 10:05:36'),
(179, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/tracking', '2021-11-20 10:07:42', '2021-11-20 10:07:42'),
(180, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/tracking', '2021-11-20 10:09:04', '2021-11-20 10:09:04'),
(181, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/tracking', '2021-11-20 10:10:48', '2021-11-20 10:10:48'),
(182, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/tracking', '2021-11-20 10:23:17', '2021-11-20 10:23:17'),
(183, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/tracking', '2021-11-20 10:43:16', '2021-11-20 10:43:16'),
(184, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/tracking', '2021-11-20 10:58:15', '2021-11-20 10:58:15'),
(185, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/auth/login', '2021-11-21 09:28:44', '2021-11-21 09:28:44'),
(186, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/user/me', '2021-11-21 09:28:49', '2021-11-21 09:28:49'),
(187, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/auth/login', '2021-11-21 09:30:43', '2021-11-21 09:30:43'),
(188, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/user/me', '2021-11-21 09:30:44', '2021-11-21 09:30:44'),
(189, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/auth/login', '2021-11-21 09:32:12', '2021-11-21 09:32:12'),
(190, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/user/me', '2021-11-21 09:32:12', '2021-11-21 09:32:12'),
(191, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-21 09:34:23', '2021-11-21 09:34:23'),
(192, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-21 09:36:37', '2021-11-21 09:36:37'),
(193, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/tracking', '2021-11-21 09:38:16', '2021-11-21 09:38:16'),
(194, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-21 09:38:56', '2021-11-21 09:38:56'),
(195, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-21 09:40:16', '2021-11-21 09:40:16'),
(196, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-21 09:40:59', '2021-11-21 09:40:59'),
(197, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-21 09:48:06', '2021-11-21 09:48:06'),
(198, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/tracking', '2021-11-21 10:07:46', '2021-11-21 10:07:46'),
(199, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-21 10:07:49', '2021-11-21 10:07:49'),
(200, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-21 10:13:30', '2021-11-21 10:13:30'),
(201, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-21 10:21:06', '2021-11-21 10:21:06'),
(202, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/tracking', '2021-11-21 10:22:46', '2021-11-21 10:22:46'),
(203, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/tracking', '2021-11-21 10:37:46', '2021-11-21 10:37:46'),
(204, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/tracking', '2021-11-21 10:52:46', '2021-11-21 10:52:46'),
(205, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/tracking', '2021-11-21 11:07:45', '2021-11-21 11:07:45'),
(206, 1, '127.0.0.1', 'http://localhost:8000/api/batiments', '2021-11-21 17:43:55', '2021-11-21 17:43:55'),
(207, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/tracking', '2021-11-21 18:04:49', '2021-11-21 18:04:49'),
(208, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-21 18:04:52', '2021-11-21 18:04:52'),
(209, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-21 18:11:33', '2021-11-21 18:11:33'),
(210, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/tracking', '2021-11-21 18:21:36', '2021-11-21 18:21:36'),
(211, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-21 18:21:43', '2021-11-21 18:21:43'),
(212, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/categories', '2021-11-21 18:21:59', '2021-11-21 18:21:59'),
(213, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-21 18:25:18', '2021-11-21 18:25:18'),
(214, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-21 18:28:53', '2021-11-21 18:28:53'),
(215, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-21 18:35:34', '2021-11-21 18:35:34'),
(216, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/tracking', '2021-11-21 18:36:35', '2021-11-21 18:36:35'),
(217, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/categories', '2021-11-21 18:36:58', '2021-11-21 18:36:58'),
(218, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/categories', '2021-11-21 18:41:00', '2021-11-21 18:41:00'),
(219, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/tracking', '2021-11-22 09:52:18', '2021-11-22 09:52:18'),
(220, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-22 09:52:19', '2021-11-22 09:52:19'),
(221, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-22 09:52:55', '2021-11-22 09:52:55'),
(222, 1, '127.0.0.1', 'http://10.0.2.2:8000/api/batiments', '2021-11-22 09:53:21', '2021-11-22 09:53:21'),
(223, 1, '127.0.0.1', 'http://localhost:8000/api/categories/1', '2021-11-22 11:53:59', '2021-11-22 11:53:59'),
(224, 1, '127.0.0.1', 'http://localhost:8000/api/auth/login', '2021-11-22 11:54:40', '2021-11-22 11:54:40'),
(225, 1, '127.0.0.1', 'http://localhost:8000/api/categories/1', '2021-11-22 11:55:13', '2021-11-22 11:55:13'),
(226, 1, '127.0.0.1', 'http://localhost:8000/api/categories/1', '2021-11-22 11:57:03', '2021-11-22 11:57:03'),
(227, 1, '127.0.0.1', 'http://localhost:8000/api/categories/1', '2021-11-22 12:00:15', '2021-11-22 12:00:15'),
(228, 1, '127.0.0.1', 'http://localhost:8000/api/categories/1', '2021-11-22 12:01:12', '2021-11-22 12:01:12'),
(229, 1, '127.0.0.1', 'http://localhost:8000/api/categories/1', '2021-11-22 12:04:08', '2021-11-22 12:04:08'),
(230, 1, '127.0.0.1', 'http://localhost:8000/api/categories/1', '2021-11-22 12:04:34', '2021-11-22 12:04:34'),
(231, 1, '127.0.0.1', 'http://localhost:8000/api/categories/1', '2021-11-22 12:06:11', '2021-11-22 12:06:11'),
(232, 1, '127.0.0.1', 'http://localhost:8000/api/categories/1', '2021-11-22 12:06:21', '2021-11-22 12:06:21'),
(233, 1, '127.0.0.1', 'http://localhost:8000/api/categories/1', '2021-11-22 12:06:33', '2021-11-22 12:06:33'),
(234, 1, '127.0.0.1', 'http://localhost:8000/api/categories/1', '2021-11-22 12:07:19', '2021-11-22 12:07:19'),
(235, 1, '127.0.0.1', 'http://localhost:8000/api/categories/1', '2021-11-22 12:08:04', '2021-11-22 12:08:04'),
(236, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/categories/1', '2021-11-22 12:10:09', '2021-11-22 12:10:09'),
(237, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/categories/1', '2021-11-22 12:11:22', '2021-11-22 12:11:22'),
(238, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/categories/1', '2021-11-22 12:11:33', '2021-11-22 12:11:33'),
(239, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/categories/1', '2021-11-22 12:13:09', '2021-11-22 12:13:09'),
(240, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/categories/1', '2021-11-22 12:13:59', '2021-11-22 12:13:59'),
(241, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/categories/1', '2021-11-22 12:16:38', '2021-11-22 12:16:38'),
(242, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/categories/1', '2021-11-22 12:17:23', '2021-11-22 12:17:23'),
(243, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/categories/1', '2021-11-22 12:35:25', '2021-11-22 12:35:25'),
(244, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/categories/1', '2021-11-22 12:36:09', '2021-11-22 12:36:09'),
(245, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/categories/1', '2021-11-22 12:37:16', '2021-11-22 12:37:16'),
(246, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/categories/1', '2021-11-22 12:37:21', '2021-11-22 12:37:21'),
(247, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/categories/1', '2021-11-22 12:38:49', '2021-11-22 12:38:49'),
(248, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/categories/1', '2021-11-22 12:39:36', '2021-11-22 12:39:36'),
(249, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/categories/1', '2021-11-22 12:39:41', '2021-11-22 12:39:41'),
(250, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/categories/1', '2021-11-22 12:40:41', '2021-11-22 12:40:41'),
(251, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/categories/1', '2021-11-22 12:41:30', '2021-11-22 12:41:30'),
(252, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/categories/2', '2021-11-22 12:44:21', '2021-11-22 12:44:21'),
(253, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/auth/login', '2021-11-22 13:19:06', '2021-11-22 13:19:06'),
(254, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/etablissements/1', '2021-11-22 13:23:29', '2021-11-22 13:23:29'),
(255, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/etablissements/2', '2021-11-22 13:23:42', '2021-11-22 13:23:42'),
(256, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/etablissements/3', '2021-11-22 13:23:47', '2021-11-22 13:23:47'),
(257, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/etablissements/4', '2021-11-22 13:23:54', '2021-11-22 13:23:54'),
(258, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/etablissements/2', '2021-11-22 13:24:02', '2021-11-22 13:24:02');

-- --------------------------------------------------------

--
-- Structure de la table `api_key_admin_events`
--

DROP TABLE IF EXISTS `api_key_admin_events`;
CREATE TABLE IF NOT EXISTS `api_key_admin_events` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `api_key_id` int(10) UNSIGNED NOT NULL,
  `ip_address` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `event` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `api_key_admin_events_ip_address_index` (`ip_address`),
  KEY `api_key_admin_events_event_index` (`event`),
  KEY `api_key_admin_events_api_key_id_foreign` (`api_key_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `api_key_admin_events`
--

INSERT INTO `api_key_admin_events` (`id`, `api_key_id`, `ip_address`, `event`, `created_at`, `updated_at`) VALUES
(1, 1, '127.0.0.1', 'created', '2021-11-13 08:39:02', '2021-11-13 08:39:02');

-- --------------------------------------------------------

--
-- Structure de la table `batiments`
--

DROP TABLE IF EXISTS `batiments`;
CREATE TABLE IF NOT EXISTS `batiments` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nom` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nombreNiveaux` int(11) NOT NULL,
  `codeBatiment` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `longitude` double NOT NULL,
  `latitude` double NOT NULL,
  `image` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `indication` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `rue` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ville` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `commune` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `quartier` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `batiments`
--

INSERT INTO `batiments` (`id`, `nom`, `nombreNiveaux`, `codeBatiment`, `longitude`, `latitude`, `image`, `indication`, `rue`, `ville`, `commune`, `quartier`, `created_at`, `updated_at`) VALUES
(1, 'ROCKS', 3, 'BATIMENT_3', 13, 7, '/storage/uploads/batiments/images/ROCKS/1637057427_Wavy_Bus-39_Single-06.jpg', 'devant polytech', '542 melen', 'Yaounde', 'Yaounde 4', 'Melen', '2021-11-16 10:10:27', '2021-11-16 10:10:27'),
(2, 'ROCKS', 3, 'BATIMENT_3', 13, 7, '/storage/uploads/batiments/images/ROCKS/1637057596_Wavy_Bus-39_Single-06.jpg', 'devant polytech', '542 melen', 'Yaounde', 'Yaounde 4', 'Melen', '2021-11-16 10:13:16', '2021-11-16 10:13:16'),
(4, 'ROCKS', 3, 'BATIMENT_3', 13, 7, NULL, 'devant polytech', '542 melen', 'Yaounde', 'Yaounde 4', 'Melen', '2021-11-17 15:41:10', '2021-11-17 15:41:10'),
(5, 'ROCKS', 3, 'BATIMENT_3', 13, 7, NULL, 'devant polytech', '542 melen', 'Yaounde', 'Yaounde 4', 'Melen', '2021-11-17 15:42:37', '2021-11-17 15:42:37'),
(6, 'ROCKS', 3, 'BATIMENT_3', 13, 7, NULL, 'devant polytech', '542 melen', 'Yaounde', 'Yaounde 4', 'Melen', '2021-11-17 15:43:56', '2021-11-17 15:43:56'),
(7, 'TATIANA', 2, '1', 14, 2, '/storage/uploads/batiments/images/TATIANA/1637163973_images.jpg', 'test', 'devant polytech', 'yaounde', 'Yaounde 4', 'Melen', '2021-11-17 15:46:13', '2021-11-17 15:46:14'),
(8, 'TEST', 4, 'BATIMENT_TEST_93003', -73.986208, 40.7544, '/storage/uploads/batiments/images/TEST/1637181821_IMG_20211117_204308751.jpg', 'test', 'Broadway', 'New York', 'New York', 'test', '2021-11-17 20:43:41', '2021-11-17 20:43:41'),
(12, 'ROCKSTAR', 4, 'BATIMENT_BIYEMASSI_41718', -122.08129275, 37.41627985, '/storage/uploads/batiments/images/ROCKSTAR/1637331559_IMG_20211119_141904762.jpg', 'derriere immeuble rose', 'Plymouth Street', 'Mountain View', 'Mountain View', 'BiyemAssi', '2021-11-19 14:19:19', '2021-11-19 14:19:19'),
(13, 'testa', 5, 'BATIMENT_TESQ_96340', -122.083586790932, 37.4187519, '/storage/uploads/batiments/images/testa/1637333938_IMG_20211119_145848638.jpg', 'tesa', 'Huff Avenue', 'Mountain View', 'Mountain View', 'tesq', '2021-11-19 14:58:58', '2021-11-19 14:58:58'),
(14, 'testr', 5, 'BATIMENT_BHIO_5068', -122.082347, 37.420021, '/storage/uploads/batiments/images/testr/1637335052_IMG_20211119_151724654.jpg', 'ghy', 'Charleston Road', 'Mountain View', 'Mountain View', 'bhio', '2021-11-19 15:17:32', '2021-11-19 15:17:32');

-- --------------------------------------------------------

--
-- Structure de la table `categories`
--

DROP TABLE IF EXISTS `categories`;
CREATE TABLE IF NOT EXISTS `categories` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nom` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `logo_url` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `categories`
--

INSERT INTO `categories` (`id`, `nom`, `logo_url`, `created_at`, `updated_at`) VALUES
(1, 'Achats', '/storage/uploads/categories/logos/Achats/1637584890_icon-categorie-achats.svg', '2021-11-18 10:44:39', '2021-11-22 12:41:31'),
(2, 'Administrations', '/storage/uploads/categories/logos/Administrations/1637585061_icon-categorie-administration.svg', '2021-11-18 10:44:39', '2021-11-22 12:44:21'),
(3, 'Agriculture', NULL, '2021-11-18 10:44:39', '2021-11-18 10:44:39'),
(4, 'Alimentation', NULL, '2021-11-18 10:44:39', '2021-11-18 10:44:39'),
(5, 'Automobile, Moto, Engins', NULL, '2021-11-18 10:44:39', '2021-11-18 10:44:39'),
(6, 'Banques, finances et assurances', NULL, '2021-11-18 10:44:39', '2021-11-18 10:44:39'),
(7, 'Batiments & Constructions', NULL, '2021-11-18 10:44:39', '2021-11-18 10:44:39'),
(8, 'Bien-être', NULL, '2021-11-18 10:44:39', '2021-11-18 10:44:39'),
(9, 'Commerce - Import & Export', NULL, '2021-11-18 10:44:39', '2021-11-18 10:44:39'),
(10, 'Communication, Journalisme, Audiovisuel', NULL, '2021-11-18 10:44:39', '2021-11-18 10:44:39'),
(11, 'Eau', NULL, '2021-11-18 10:44:39', '2021-11-18 10:44:39'),
(12, 'Education & Formation', NULL, '2021-11-18 10:44:39', '2021-11-18 10:44:39'),
(13, 'Energie', NULL, '2021-11-18 10:44:39', '2021-11-18 10:44:39'),
(14, 'Hydrocarbures, Pétroliers, Forages', NULL, '2021-11-18 10:44:39', '2021-11-18 10:44:39'),
(15, 'Immobilier', NULL, '2021-11-18 10:44:39', '2021-11-18 10:44:39'),
(16, 'Industries', NULL, '2021-11-18 10:44:39', '2021-11-18 10:44:39'),
(17, 'Informatique, Internet, Nouvelles Technologies', NULL, '2021-11-18 10:44:39', '2021-11-18 10:44:39'),
(18, 'Justice', NULL, '2021-11-18 10:44:39', '2021-11-18 10:44:39'),
(19, 'Loisirs', NULL, '2021-11-18 10:44:39', '2021-11-18 10:44:39'),
(20, 'Restos, bars', NULL, '2021-11-18 10:44:39', '2021-11-18 10:44:39'),
(21, 'Santé & Médecine', NULL, '2021-11-18 10:44:39', '2021-11-18 10:44:39'),
(22, 'Securite, Gardiennage, Protection Incendie', NULL, '2021-11-18 10:44:39', '2021-11-18 10:44:39'),
(23, 'Télécommunication', NULL, '2021-11-18 10:44:39', '2021-11-18 10:44:39'),
(24, 'Textiles & Prêt à Porter', NULL, '2021-11-18 10:44:39', '2021-11-18 10:44:39'),
(25, 'Tourisme', NULL, '2021-11-18 10:44:39', '2021-11-18 10:44:39'),
(26, 'Transports', NULL, '2021-11-18 10:44:39', '2021-11-18 10:44:39'),
(27, 'Autres', NULL, '2021-11-18 10:44:39', '2021-11-18 10:44:39');

-- --------------------------------------------------------

--
-- Structure de la table `commercials`
--

DROP TABLE IF EXISTS `commercials`;
CREATE TABLE IF NOT EXISTS `commercials` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `numeroCni` int(11) NOT NULL,
  `numeroBadge` int(11) NOT NULL,
  `ville` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `quartier` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `imageProfil` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `zone` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `actif` tinyint(1) NOT NULL DEFAULT '1',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `commercials`
--

INSERT INTO `commercials` (`id`, `idUser`, `numeroCni`, `numeroBadge`, `ville`, `quartier`, `imageProfil`, `zone`, `actif`, `created_at`, `updated_at`) VALUES
(2, 5, 12345678, 52, 'Yaounde', 'Melen', '/storage/uploads/commerciaux/profils/1637279707_Image1.jpg', 'Zone 1', 1, '2021-11-18 23:55:07', '2021-11-18 23:55:07');

-- --------------------------------------------------------

--
-- Structure de la table `etablissements`
--

DROP TABLE IF EXISTS `etablissements`;
CREATE TABLE IF NOT EXISTS `etablissements` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idBatiment` int(11) NOT NULL,
  `nom` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `indicationAdresse` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `codePostal` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `siteInternet` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `idSousCategorie` int(11) NOT NULL,
  `idCommercial` int(11) NOT NULL,
  `idManager` int(11) DEFAULT NULL,
  `etage` int(11) NOT NULL,
  `cover` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `vues` int(11) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `description` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `etablissements`
--

INSERT INTO `etablissements` (`id`, `idBatiment`, `nom`, `indicationAdresse`, `codePostal`, `siteInternet`, `idSousCategorie`, `idCommercial`, `idManager`, `etage`, `cover`, `vues`, `created_at`, `updated_at`, `description`) VALUES
(1, 1, 'SOGEFI', 'devant polytech', '14440', 'sogefi.cm', 10, 1, NULL, 1, '/storage/uploads/batiments/images/BATIMENT_3/SOGEFI/1637057826_Wavy_Bus-39_Single-06.jpg', 0, '2021-11-16 10:17:06', '2021-11-16 10:17:08', NULL),
(2, 1, 'CODIMED', 'devant polytech', '14440', 'sogefi.cm', 50, 1, NULL, 2, '/storage/uploads/batiments/images/BATIMENT_3/CODIMED/1637058369_Wavy_Bus-39_Single-06.jpg', 0, '2021-11-16 10:26:09', '2021-11-16 10:26:09', NULL),
(5, 10, 'SOGEFISTART', NULL, NULL, NULL, 316, 2, NULL, 1, '/storage/uploads/batiments/images/BATIMENT_MELEN_57763/SOGEFISTART/1637327842_IMG_20211119_131701662.jpg', 0, '2021-11-19 13:17:22', '2021-11-19 13:17:22', NULL),
(7, 12, 'CODIMEDSTART', NULL, NULL, NULL, 387, 2, NULL, 2, '/storage/uploads/batiments/images/BATIMENT_BIYEMASSI_41718/CODIMEDSTART/1637331600_IMG_20211119_141950745.jpg', 0, '2021-11-19 14:20:00', '2021-11-19 14:20:00', NULL),
(8, 13, 'tesr', NULL, NULL, NULL, 1, 2, NULL, 2, '/storage/uploads/batiments/images/BATIMENT_TESQ_96340/tesr/1637333967_IMG_20211119_145920968.jpg', 0, '2021-11-19 14:59:27', '2021-11-19 14:59:27', NULL),
(9, 14, 'jki', 'test', 'oui', 'yes', 1, 2, NULL, 2, '/storage/uploads/batiments/images/BATIMENT_BHIO_5068/jki/1637335075_IMG_20211119_151748962.jpg', 0, '2021-11-19 15:17:55', '2021-11-19 15:18:21', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `failed_jobs`
--

DROP TABLE IF EXISTS `failed_jobs`;
CREATE TABLE IF NOT EXISTS `failed_jobs` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `uuid` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `connection` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `queue` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `payload` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `exception` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `failed_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `failed_jobs_uuid_unique` (`uuid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `horaires`
--

DROP TABLE IF EXISTS `horaires`;
CREATE TABLE IF NOT EXISTS `horaires` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idEtablissement` int(11) NOT NULL,
  `jour` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ouvert` tinyint(1) NOT NULL DEFAULT '1',
  `heureOuverture` time DEFAULT NULL,
  `heureFermeture` time DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `horaires`
--

INSERT INTO `horaires` (`id`, `idEtablissement`, `jour`, `ouvert`, `heureOuverture`, `heureFermeture`, `created_at`, `updated_at`) VALUES
(1, 7, 'Lundi', 1, '02:22:00', '07:22:00', '2021-11-19 14:22:49', '2021-11-19 14:22:49'),
(2, 7, 'Mercredi', 1, '10:22:00', '03:22:00', '2021-11-19 14:22:49', '2021-11-19 14:22:49'),
(3, 7, 'Mardi', 1, '02:22:00', '07:22:00', '2021-11-19 14:22:50', '2021-11-19 14:22:50'),
(4, 7, 'Vendredi', 1, '08:22:00', '06:22:00', '2021-11-19 14:22:50', '2021-11-19 14:22:50'),
(5, 7, 'Jeudi', 1, '07:22:00', '12:22:00', '2021-11-19 14:22:50', '2021-11-19 14:22:50');

-- --------------------------------------------------------

--
-- Structure de la table `images`
--

DROP TABLE IF EXISTS `images`;
CREATE TABLE IF NOT EXISTS `images` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idEtablissement` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `imageUrl` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `images`
--

INSERT INTO `images` (`id`, `idEtablissement`, `imageUrl`, `created_at`, `updated_at`) VALUES
(1, '7', '/storage/uploads/batiments/images/BATIMENT_BIYEMASSI_41718/CODIMEDSTART/1637331938_IMG_20211119_142532684.jpg', '2021-11-19 14:25:38', '2021-11-19 14:25:38');

-- --------------------------------------------------------

--
-- Structure de la table `managers`
--

DROP TABLE IF EXISTS `managers`;
CREATE TABLE IF NOT EXISTS `managers` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `migrations`
--

DROP TABLE IF EXISTS `migrations`;
CREATE TABLE IF NOT EXISTS `migrations` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `migration` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(1, '2014_10_12_000000_create_users_table', 1),
(2, '2014_10_12_100000_create_password_resets_table', 1),
(3, '2016_06_01_000001_create_oauth_auth_codes_table', 1),
(4, '2016_06_01_000002_create_oauth_access_tokens_table', 1),
(5, '2016_06_01_000003_create_oauth_refresh_tokens_table', 1),
(6, '2016_06_01_000004_create_oauth_clients_table', 1),
(7, '2016_06_01_000005_create_oauth_personal_access_clients_table', 1),
(8, '2016_12_28_111110_create_api_keys_table', 1),
(9, '2016_12_28_111111_create_api_key_access_events_table', 1),
(10, '2016_12_28_111112_create_api_key_admin_events_table', 1),
(11, '2019_08_19_000000_create_failed_jobs_table', 1),
(12, '2019_12_14_000001_create_personal_access_tokens_table', 1),
(13, '2021_11_08_100517_create_commercials_table', 1),
(14, '2021_11_09_125349_create_managers_table', 1),
(15, '2021_11_09_162117_create_categories_table', 1),
(16, '2021_11_10_103134_create_sous_categories_table', 1),
(17, '2021_11_10_151228_create_batiments_table', 1),
(18, '2021_11_10_163720_create_images_table', 1),
(19, '2021_11_10_163749_create_telephones_table', 1),
(20, '2021_11_10_163808_create_horaires_table', 1),
(21, '2021_11_10_164644_create_etablissements_table', 1),
(22, '2021_11_18_062350_add_description_to_etablissements_table', 2),
(23, '2021_11_19_112043_add_principal_to_telephones_table', 3),
(24, '2021_11_20_103942_create_trackings_table', 4),
(25, '2021_11_22_135820_create_zones_table', 5);

-- --------------------------------------------------------

--
-- Structure de la table `oauth_access_tokens`
--

DROP TABLE IF EXISTS `oauth_access_tokens`;
CREATE TABLE IF NOT EXISTS `oauth_access_tokens` (
  `id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` bigint(20) UNSIGNED DEFAULT NULL,
  `client_id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `scopes` text COLLATE utf8mb4_unicode_ci,
  `revoked` tinyint(1) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `expires_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `oauth_access_tokens_user_id_index` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `oauth_access_tokens`
--

INSERT INTO `oauth_access_tokens` (`id`, `user_id`, `client_id`, `name`, `scopes`, `revoked`, `created_at`, `updated_at`, `expires_at`) VALUES
('1bc21e0ff7c5310c15e62960002c8e2c81d701bf2f9ca575456608c9358c4cfef905aa023e63ae8d', 1, 1, 'Position', '[]', 0, '2021-11-13 17:48:15', '2021-11-13 17:48:15', '2021-12-13 18:48:15'),
('af561466164bee6a8c13dcc46991ef642f6ad3a1475425aff311731192fd9417b77184bf0cfa3058', 1, 1, 'Position', '[]', 0, '2021-11-16 09:59:11', '2021-11-16 09:59:11', '2021-12-16 10:59:11'),
('8e4d276ed5da627898d94a60ea5b551e586f37109db1fb5b947d65e3df52b9bf27f49426ba62a3fd', 1, 1, 'Position', '[]', 0, '2021-11-17 14:39:13', '2021-11-17 14:39:13', '2021-12-17 15:39:13'),
('4d1a51c250b4e9371adb62b234b1d5f7c48182c72d3a90a2819b3ad9cefcf545e02275a623498811', 1, 3, 'Position', '[]', 0, '2021-11-18 23:35:39', '2021-11-18 23:35:39', '2021-12-19 00:35:39'),
('73151c68a130b84042d26771280e73f32e020cc2b3d410bab1246140db79395c730cb8cbf4c0d8d6', 5, 3, 'Position', '[]', 0, '2021-11-19 11:36:15', '2021-11-19 11:36:15', '2021-12-19 12:36:15'),
('c0cf617223dec14c29238af91b4577cae8aae5ec061784832954cdfa6196b6d8b88f9e7c046c43c7', 5, 3, 'Position', '[]', 0, '2021-11-19 21:33:13', '2021-11-19 21:33:13', '2021-12-19 22:33:13'),
('331b9c0d49726c56b782294cd96a5ae5402e359f822856de08462011ec7a43ddbae685e00434be2e', 5, 3, 'Position', '[]', 0, '2021-11-19 21:49:32', '2021-11-19 21:49:32', '2021-12-19 22:49:32'),
('f8b0c6f67bc0ad2d418ca7130859b8242f9d02070df8d960da903965c5572ec15fd40649de144aed', 5, 3, 'Position', '[]', 0, '2021-11-21 09:28:47', '2021-11-21 09:28:47', '2021-12-21 10:28:47'),
('0e9b8a7c7cea766378d7402a2fbd4fc05523c4865ba20e2ccf5c69f68ffbdd34b48cc70904e5ae81', 5, 3, 'Position', '[]', 0, '2021-11-21 09:30:43', '2021-11-21 09:30:43', '2021-12-21 10:30:43'),
('9a9b0eb1970305fb5c7f88173674ce9522f810032aad7fd4d549d08ff60c51b054e17e8c15e53a04', 5, 3, 'Position', '[]', 0, '2021-11-21 09:32:12', '2021-11-21 09:32:12', '2021-12-21 10:32:12'),
('e72868156a2784b6a1aa963452049c25394713d5a3bc7cbbe4e426a0d13180bca5afd38ec4fe0363', 1, 3, 'Position', '[]', 0, '2021-11-22 11:54:43', '2021-11-22 11:54:43', '2021-12-22 12:54:43'),
('784f541d278ca8801bea492f1417f39fc03345bea8f7176e0404109ad562d3f4730e07f880db2645', 5, 3, 'Position', '[]', 0, '2021-11-22 13:19:07', '2021-11-22 13:19:07', '2021-12-22 14:19:07');

-- --------------------------------------------------------

--
-- Structure de la table `oauth_auth_codes`
--

DROP TABLE IF EXISTS `oauth_auth_codes`;
CREATE TABLE IF NOT EXISTS `oauth_auth_codes` (
  `id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `client_id` bigint(20) UNSIGNED NOT NULL,
  `scopes` text COLLATE utf8mb4_unicode_ci,
  `revoked` tinyint(1) NOT NULL,
  `expires_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `oauth_auth_codes_user_id_index` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `oauth_clients`
--

DROP TABLE IF EXISTS `oauth_clients`;
CREATE TABLE IF NOT EXISTS `oauth_clients` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) UNSIGNED DEFAULT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `secret` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `provider` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `redirect` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `personal_access_client` tinyint(1) NOT NULL,
  `password_client` tinyint(1) NOT NULL,
  `revoked` tinyint(1) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `oauth_clients_user_id_index` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `oauth_clients`
--

INSERT INTO `oauth_clients` (`id`, `user_id`, `name`, `secret`, `provider`, `redirect`, `personal_access_client`, `password_client`, `revoked`, `created_at`, `updated_at`) VALUES
(1, NULL, 'Position Personal Access Client', 'wtd2nSEKzBD8TqeuIfFwRztnC0rBMQ5Erj5qlDfu', NULL, 'http://localhost', 1, 0, 0, '2021-11-13 08:11:53', '2021-11-13 08:11:53'),
(2, NULL, 'Position Password Grant Client', 'wGaK8HcDSB1xiQ5w8QHAdpW1S7KBWAATCUtFdgjW', 'users', 'http://localhost', 0, 1, 0, '2021-11-13 08:11:53', '2021-11-13 08:11:53'),
(3, NULL, 'Position Personal Access Client', 'IUZbh6bDyqyXkPhvzyTxTSna6swSK6cGQGcRYIIY', NULL, 'http://localhost', 1, 0, 0, '2021-11-18 23:34:05', '2021-11-18 23:34:05'),
(4, NULL, 'Position Password Grant Client', 'hluNPx5UY3E4lueP0v8dW25N702sRuIsrwef7Cvb', 'users', 'http://localhost', 0, 1, 0, '2021-11-18 23:34:05', '2021-11-18 23:34:05');

-- --------------------------------------------------------

--
-- Structure de la table `oauth_personal_access_clients`
--

DROP TABLE IF EXISTS `oauth_personal_access_clients`;
CREATE TABLE IF NOT EXISTS `oauth_personal_access_clients` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `client_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `oauth_personal_access_clients`
--

INSERT INTO `oauth_personal_access_clients` (`id`, `client_id`, `created_at`, `updated_at`) VALUES
(1, 1, '2021-11-13 08:11:53', '2021-11-13 08:11:53'),
(2, 3, '2021-11-18 23:34:05', '2021-11-18 23:34:05');

-- --------------------------------------------------------

--
-- Structure de la table `oauth_refresh_tokens`
--

DROP TABLE IF EXISTS `oauth_refresh_tokens`;
CREATE TABLE IF NOT EXISTS `oauth_refresh_tokens` (
  `id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `access_token_id` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `revoked` tinyint(1) NOT NULL,
  `expires_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `oauth_refresh_tokens_access_token_id_index` (`access_token_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `password_resets`
--

DROP TABLE IF EXISTS `password_resets`;
CREATE TABLE IF NOT EXISTS `password_resets` (
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  KEY `password_resets_email_index` (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `personal_access_tokens`
--

DROP TABLE IF EXISTS `personal_access_tokens`;
CREATE TABLE IF NOT EXISTS `personal_access_tokens` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `tokenable_type` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tokenable_id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `abilities` text COLLATE utf8mb4_unicode_ci,
  `last_used_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `personal_access_tokens_token_unique` (`token`),
  KEY `personal_access_tokens_tokenable_type_tokenable_id_index` (`tokenable_type`,`tokenable_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `sous_categories`
--

DROP TABLE IF EXISTS `sous_categories`;
CREATE TABLE IF NOT EXISTS `sous_categories` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nom` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `idCategorie` int(11) NOT NULL,
  `logoUrl` varchar(191) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=480 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `sous_categories`
--

INSERT INTO `sous_categories` (`id`, `nom`, `idCategorie`, `logoUrl`, `created_at`, `updated_at`) VALUES
(1, 'Boutiques', 1, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(2, 'Brocante', 1, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(3, 'Supermarché', 1, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(4, 'Epicerie', 1, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(5, 'Blanchisseries et Pressings', 1, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(6, 'Centre Commercial', 1, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(7, 'Maison et Jardin', 1, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(8, 'Hifi, téléphonie', 1, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(9, 'Fleuriste', 1, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(10, 'Boulangerie, Pâtisserie', 1, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(11, 'Caviste', 1, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(12, 'Tabac', 1, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(13, 'Jouets et jeux', 1, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(14, 'Magasin de sport', 1, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(15, 'Ameublement et Mobilier', 1, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(16, 'Fournitures de Bureaux', 1, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(17, 'Mobilier de Bureaux', 1, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(18, 'Mobilier de Jardin', 1, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(19, 'Vêtements', 1, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(20, 'Chaussures', 1, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(21, 'Bijoux et accessoires', 1, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(22, 'Puériculture', 1, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(23, 'Administrations', 2, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(24, 'Ambassades et Consulats', 2, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(25, 'Associations, syndicats', 2, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(26, 'Douane, Agences', 2, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(27, 'Minisères', 2, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(28, 'O.N.G & Organisations Internationales', 2, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(29, 'Offices Nationaux', 2, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(30, 'Poste', 2, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(31, 'Sécurité Sociale', 2, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(32, 'Institution publique', 2, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(33, 'Matériels et Produits agricoles', 3, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(34, 'Agricole, Produits Chimiques', 3, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(35, 'Agriculture', 3, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(36, 'Equipements et Matériel agricoles', 3, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(37, 'Agro-Alimentaire', 3, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(38, 'Agro-Industrie', 3, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(39, 'Elevage', 3, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(40, 'Elevage - Consultants', 3, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(41, 'Abattoirs et Viande en Gros', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(42, 'Alcools, vins, spiritueux, drogueries', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(43, 'Alimentaire, Distributeurs et Grossistes', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(44, 'Industries alimentaires', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(45, 'Produits alimentaires', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(46, 'Alimentation Animale', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(47, 'Alimentation Générale', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(48, 'Boissons', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(49, 'Boucherie - charcuterie', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(50, 'Boulangeries, Patisseries, Glaces', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(51, 'Boulangeries, Patisseries, Glaces - matériel & équipement', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(52, 'Brasseries', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(53, 'Brasseries - matériel & équipement', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(54, 'Cacao - Production et Exportation', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(55, 'Café - Production et Exportation', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(56, 'Catering', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(57, 'Distilleries', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(58, 'Environnement', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(59, 'Gomme Arabique', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(60, 'Lait, Yaourt et Fromage', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(61, 'Laiteries', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(62, 'Minoteries', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(63, 'Pêche - Commercialisation et Exportation', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(64, 'Pêche - Congelation', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(65, 'Sucre, Fabrication et Raffinage', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(66, 'Supermarchés', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(67, 'Thé - Production et Commercialisation', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(68, 'Traiteurs', 4, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(69, 'Auto-Ecoles', 5, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(70, 'Automobiles et Concessionnaires', 5, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(71, 'Expertises automobiles', 5, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(72, 'Automobiles - Pièces Détachées et Accessoires', 5, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(73, 'Voitures d\'occasion', 5, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(74, 'Constructions Mécaniques', 5, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(75, 'Contrôles Techniques', 5, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(76, 'Cycles et Motos', 5, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(77, 'Engins ee Chantier et Matériels', 5, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(78, 'Garages', 5, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(79, 'Mécanique Générale', 5, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(80, 'Mécanique - Industries', 5, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(81, 'Moteurs et Pompes', 5, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(82, 'Pneumatiques', 5, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(83, 'Tracking', 5, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(84, 'Véhicules Industriels', 5, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(85, 'Station-service', 5, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(86, 'Parking', 5, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(87, 'Garage', 5, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(88, 'Assurances', 6, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(89, 'Assurances Vies', 6, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(90, 'Assurances médicales', 6, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(91, 'Assureurs - Courtiers Et Conseils', 6, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(92, 'Banques et Organismes Financiers', 6, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(93, 'Equipements de Sécurité Bancaire', 6, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(94, 'Banques - Services Mobiles', 6, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(95, 'Bureaux de Change et Transferts d\'argent', 6, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(96, 'Bureaux de Contrôle', 6, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(97, 'Cabinets Comptables', 6, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(98, 'Centres d\'Information', 6, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(99, 'Crédits et Finances', 6, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(100, 'Enquêtes, recherches et investigation', 6, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(101, 'Holdings', 6, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(102, 'Investissements', 6, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(103, 'Transports de Fonds', 6, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(104, 'Adduction d\'eau et VRD', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(105, 'Agencement et Décoration', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(106, 'Industrie d\'aluminium', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(107, 'Aménagement des Terrains Urbains', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(108, 'Architecture et Urbanisme', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(109, 'Ascenseurs', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(110, 'Assainissement et Canalisations', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(111, 'Bâches et Stores', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(112, 'Bâtiments et Travaux Publics', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(113, 'Bâtiments - Expertises', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(114, 'Bâtiments - Nettoyage', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(115, 'Bâtiments - Préfabriqué', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(116, 'Bâtiments - Ravalement', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(117, 'Bâtiments - Réhabilitation', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(118, 'Bâtiments - Second Œuvre', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(119, 'Bitume', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(120, 'Bois et Négoce', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(121, 'Bois - Industries', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(122, 'Bois - Scieries', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(123, 'Bricolage', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(124, 'Briqueteries', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(125, 'Bureaux d\'Etudes et D\'Ingénierie', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(126, 'Câbles', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(127, 'Caoutchouc', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(128, 'Carrelages', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(129, 'Carrières - Exploitations et Exploration', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(130, 'Chaudronnerie', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(131, 'Cimenteries', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(132, 'Citernes - Métallique et Plastique', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(133, 'Climatisation', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(134, 'Climatisation - Dépannage et Maintenance', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(135, 'Constructions Industrielles', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(136, 'Constructions Métalliques', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(137, 'Développement Rural et Urbain', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(138, 'Ebenistes', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(139, 'Echafaudages', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(140, 'Espaces Verts et Jardins', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(141, 'Etanchéité', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(142, 'Faux-Plafonds et Staff', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(143, 'Fer et Acier', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(144, 'Forages', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(145, 'Forages - Maintenance', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(146, 'Forages - Matériels et Equipements', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(147, 'Forestiers - Matériels et Equipement', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(148, 'Génie Civil', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(149, 'Géologues', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(150, 'Géomètres - topographes', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(151, 'Groupes Electrogènes', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(152, 'Hydraulique', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(153, 'Hydraulique - Constructions', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(154, 'Hydraulique - Matériels et Equipements', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(155, 'Isolation industrielle', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(156, 'Laboratoires d\'Analyses du Bâtiment', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(157, 'Location d\'Engins de Chantier', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(158, 'Matériaux de Construction', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(159, 'Menuiseries Aluminium', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(160, 'Menuiseries Bois', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(161, 'Menuiseries Industrielles', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(162, 'Menuiseries Métalliques', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(163, 'Mines - Exploitations et Exploration', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(164, 'Mines - Import et Export', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(165, 'Mines - Matériels et Equipements', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(166, 'Mines - Recherche', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(167, 'Miroiteries et Vitreries', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(168, 'Ouvrages d\'Art', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(169, 'Peintres', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(170, 'Peinture', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(171, 'Piscines', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(172, 'Plomberie et Sanitaires', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(173, 'Portes et Fenêtres', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(174, 'Propreté urbaine', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(175, 'Quincailleries', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(176, 'Routes', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(177, 'Routes - Matériaux', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(178, 'Routes - Signalisation', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(179, 'Serrureries', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(180, 'Toitures - Matériaux', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(181, 'Tôles', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(182, 'Topographie', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(183, 'Travaux Publics et Terrassement', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(184, 'Travaux Publics et Terrassement - Maintenance', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(185, 'Tuyauteries et PVC', 7, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(186, 'Coiffeur', 8, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(187, 'Institut de beauté, parfumerie, relaxation', 8, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(188, 'Massages', 8, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(189, 'Centrales d\'Achats', 9, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(190, 'Chambres de Commerce', 9, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(191, 'Commerce Exterieur - Promotion', 9, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(192, 'Commerce Général', 9, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(193, 'E-Commerce', 9, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(194, 'Groupements D\'Entreprises', 9, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(195, 'Import et Export', 9, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(196, 'Promotion Commerciale et Etudes', 9, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(197, 'Recouvrements Commerciaux', 9, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(198, 'Représentation Commerciale', 9, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(199, 'Agences de Mannequins', 10, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(200, 'Agences de Presse et d\'Information', 10, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(201, 'Agences de Publicité et de Communication', 10, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(202, 'Audiovisuel - Matériels et Production', 10, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(203, 'Evénementiel', 10, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(204, 'Journaux et Presse', 10, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(205, 'Maison d\'édition', 10, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(206, 'Marketing', 10, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(207, 'Objets Publicitaires', 10, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(208, 'Organisations de Conférences', 10, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(209, 'Radio et Télévision', 10, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(210, 'Radio-Communication', 10, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(211, 'Relations Publiques et Organisations', 10, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(212, 'Ressources Humaines', 10, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(213, 'Sérigraphie', 10, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(214, 'Eau - Distribution', 11, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(215, 'Eau - Laboratoires d\'Analyses', 11, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(216, 'Eau - Traitement', 11, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(217, 'Centres de Documentation', 12, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(218, 'Crèche', 12, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(219, 'Centre de loisirs d\'enfants', 12, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(220, 'Centre linguistique - enseignement', 12, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(221, 'Centres de Formation', 12, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(222, 'Centres de Recherche', 12, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(223, 'Ecoles et Universités', 12, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(224, 'Automatisme', 13, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(225, 'Batteries et Piles', 13, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(226, 'Electricité - Générale et Industrielle', 13, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(227, 'Electricité - Expertises', 13, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(228, 'Electricité - Industries', 13, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(229, 'Electricité - Ingénierie', 13, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(230, 'Electricité - Materiels et Equipement', 13, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(231, 'Electrification Rurale Et Urbaine', 13, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(232, 'Electromécanique', 13, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(233, 'Energie', 13, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(234, 'Energie Nouvelle et Renouvelable', 13, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(235, 'Energie Solaire', 13, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(236, 'Gaz Domestique Et Industriel', 13, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(237, 'Géotechnique', 13, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(238, 'Hydrocarbures - Aviation', 14, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(239, 'Hydrocarbures - Distribution', 14, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(240, 'Hydrocarbures - Equipements Et Mater...', 14, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(241, 'Hydrocarbures - Expertises', 14, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(242, 'Hydrocarbures - Exploration', 14, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(243, 'Hydrocarbures - Logistiques', 14, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(244, 'Hydrocarbures - Lubrifiants', 14, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(245, 'Hydrocarbures - Maintenance', 14, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(246, 'Hydrocarbures - Production', 14, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(247, 'Hydrocarbures - Transports', 14, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(248, 'Pétroliers, Constructions', 14, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(249, 'Pétroliers - Matériels et Equipement', 14, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(250, 'Raffineries', 14, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(251, 'Garde Meubles', 15, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(252, 'Immobilier - Agences', 15, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(253, 'Immobilier - Expertises', 15, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(254, 'Immobilier - Gestion', 15, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(255, 'Immobilier - Location', 15, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(256, 'Immobilier - Promoteurs', 15, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(257, 'Electro-Ménager', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(258, 'Electronique', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(259, 'Allumettes et Bougies', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(260, 'Armes et Munitions', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(261, 'Assistance Technique', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(262, 'Cartonneries', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(263, 'Chaussures, Manufactures', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(264, 'Coton', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(265, 'Dépannage et Maintenance Industrielle', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(266, 'Diamant et Or', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(267, 'Emballage et Conditionnement', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(268, 'Equipements de Bureaux', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(269, 'Equipements Industriels', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(270, 'Fournitures Industrielles', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(271, 'Froid Industriel', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(272, 'Location De Matériels', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(273, 'Machines-Outils, Manufactures', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(274, 'Manutention Industrielle', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(275, 'Matelas', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(276, 'Matelas, Equipements et Materiels', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(277, 'Mesure et Pesage, Materiels', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(278, 'Nettoyage Industriel', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(279, 'Nettoyage, Matériels et Equipements', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(280, 'Papier - Industries', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(281, 'Pièces Détachées Industrielles', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(282, 'Plastique - Industries', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(283, 'Plastique - Produits', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(284, 'Savonneries', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(285, 'Soudage, Matériels et Produits', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(286, 'Tabac, Manufactures et Importateurs', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(287, 'Travail Temporaire, Interim', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(288, 'Tuyauteries', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(289, 'Verre - Industries', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(290, 'Zone Franche Industrielle', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(291, 'Ameublement et Mobilier, Industries', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(292, 'Fournitures de Bureaux, Industries', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(293, 'Mobilier - Industries', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(294, 'Chimie - Industries', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(295, 'Chimie - Produits', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(296, 'Parfum, Industries', 16, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(297, 'Antennes Paraboliques, Equipements', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(298, 'Archivage Numerique', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(299, 'Bureautique', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(300, 'Business Center', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(301, 'Centres d\'Appels', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(302, 'Courriers Express, Colis Express', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(303, 'Cyber-Cafes', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(304, 'Domiciliation de Sociétés', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(305, 'Editeurs', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(306, 'Etudes de Marchés', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(307, 'Imprimeries', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(308, 'Imprimeries, Matériels et Equipement', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(309, 'Informatique', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(310, 'Informatique - Consommables', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(311, 'Informatique - Constructeurs', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(312, 'Informatique - Consultants', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(313, 'Informatique - Environnement', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(314, 'Informatique - Expertises', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(315, 'Informatique - Gestion De Maintenance', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(316, 'Informatique - Ingénierie', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(317, 'Informatique - Ingénieurs', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(318, 'Informatique - Maintenance', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(319, 'Informatique - Reseaux', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(320, 'Informatique - Securite', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(321, 'Internet', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(322, 'Internet - Provider', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(323, 'Internet - Web Design', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(324, 'Internet - E-Business & E-Commerce', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(325, 'Paratonnerre', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(326, 'Technologies Nouvelles', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(327, 'Technologies Nouvelles, Consultants', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(328, 'Traducteurs Et Interpretes', 17, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(329, 'Avocats', 18, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(330, 'Avocats d\'Affaires Internationales', 18, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(331, 'Cabinets Juridiques et Fiscaux', 18, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(332, 'Consultants', 18, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(333, 'Consultants en Immigration', 18, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(334, 'Consultants Internationaux', 18, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(335, 'Expertises  Comptables, Audit Et Conseil', 18, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(336, 'Expertises - Etudes, Communication', 18, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(337, 'Expertises Judiciaire', 18, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(338, 'Huissiers De Justice', 18, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(339, 'Notaires', 18, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(340, 'Projets et Bureaux D\'Appui', 18, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(341, 'Artisanat, Antiquaires et Galeries d\'Art', 19, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(342, 'Cadeaux', 19, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(343, 'Casino', 19, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(344, 'Cinéma et vidéothèques', 19, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(345, 'Centre culturel', 19, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(346, 'Musée', 19, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(347, 'Musique', 19, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(348, 'Théâtre, spectacle', 19, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(349, 'Cabaret', 19, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(350, 'Bibliothèque et Centre de Documentation', 19, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(351, 'Librairie, papeterie', 19, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(352, 'Sport', 19, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(353, 'Espaces verts et Jardins', 19, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(354, 'Salle de spectacles - salle de fêtes', 19, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(355, 'Loisirs, jeux et entertainment', 19, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(356, 'Loterie', 19, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(357, 'Voyance, Medium', 19, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(358, 'Restaurant', 20, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(359, 'Night Clubs et Discothèques', 20, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(360, 'Restauration rapide', 20, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(361, 'Bar, Café', 20, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(362, 'Salons de Thé et Glaciers', 20, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(363, 'Ambulance', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(364, 'Assistance sociale', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(365, 'Médecine Générale', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(366, 'Médecine Spécialisée - dentiste', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(367, 'Médecine Spécialisée - dermatologie', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(368, 'Médecine Spécialisée - cardiologie', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(369, 'Médecine Spécialisée - oncologie', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(370, 'Médecine Spécialisée - ophtalmologie', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(371, 'Médecine Spécialisée - psychologie', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(372, 'Médecine Spécialisée - angiologie', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(373, 'Médecine Spécialisée - pédicure et podologie', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(374, 'Médecine Spécialisée - ostéopathie', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(375, 'Médecine Spécialisée - gynécologie', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(376, 'Médecine Spécialisée - kinésithérapeute', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(377, 'Médecine naturelle, traditionnelle', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(378, 'Médecine du travail', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(379, 'Chirurgie', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(380, 'Chirurgie Esthétique', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(381, 'Cliniques et Hopitaux', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(382, 'Cosmétiques - Industries', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(383, 'Cosmétiques - Produits', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(384, 'Drogueries', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(385, 'Laboratoires d\'Analyses Médicales', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(386, 'Laboratoires, Matériels et Equipement', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(387, 'Laboratoires - Produits', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(388, 'Medical - Assistance', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(389, 'Medical - Gaz Medical', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(390, 'Medical - Industries', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(391, 'Medical - Materiels Et Equipements', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(392, 'Medical - Produits', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(393, 'Opticiens', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(394, 'Opticiens - Matériels et Equipements', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(395, 'Para-Pharmacie', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(396, 'Pharmaceutiques - Distributeurs', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(397, 'Pharmaceutiques - Industries', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(398, 'Pharmaceutiques - Laboratoires', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(399, 'Pharmaceutiques - Materiels Et Equipement', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(400, 'Pharmaceutiques - Produits', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(401, 'Pharmacies', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(402, 'Phytosanitaires - Traitements', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(403, 'Urgences Medicales', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(404, 'Vétérinaires', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(405, 'Vétérinaires, Produits et Pharmacie', 21, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(406, 'Alarmes Et Surveillance, Matériels électroniques', 22, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(407, 'Badges, Fabrication', 22, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(408, 'Controles Techniques', 22, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(409, 'Détectives', 22, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(410, 'Escorte Véhicules', 22, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(411, 'Gardiennage et Sécurité', 22, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(412, 'Gardiennage et Sécurité - Equipements', 22, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(413, 'Incendie et Protection - Matériels', 22, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(414, 'Sécurité - Audits et Consultants', 22, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(415, 'Télé-Surveillance et Vidéo-Surveillance', 22, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(416, 'Télécommunications - Transmission de signal', 23, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(417, 'Télécommunications et Téléphonie', 23, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(418, 'Télécommunications - Fibres Optique', 23, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(419, 'Télécommunications - Installation et maintenance', 23, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(420, 'Télécommunications - Matériels et équipement', 23, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(421, 'Télécommunications - Mobiles', 23, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(422, 'Télécommunications - Réseaux', 23, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(423, 'Télécommunications - Satellite', 23, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(424, 'Confection, Couture, Broderie', 24, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(425, 'Equipements et vêtements militaires', 24, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(426, 'Friperies', 24, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(427, 'Prêt à Porter - Gros et Détail', 24, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(428, 'Textile', 24, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(429, 'Textile, Filature, Tissage, Impression', 24, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(430, 'Textile, Fournitures', 24, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(431, 'Textile - Matériels et Equipements', 24, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(432, 'Vêtements  Manufactures', 24, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(433, 'Agences de Tourisme', 25, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(434, 'Centres d\'Information', 25, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(435, 'Agences de voyage, tours opérateurs', 25, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(436, 'Equipements Hôtels et Restaurants', 25, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(437, 'Hôtel', 25, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(438, 'Auberge', 25, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(439, 'Location d\'avions', 25, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(440, 'Location de bateaux', 25, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(441, 'Location de voitures', 25, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(442, 'Transport Logistique', 25, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(443, 'Transports Touristiques', 25, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(444, 'Accastillage', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(445, 'Acconage', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(446, 'Aéronautique', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(447, 'Aéronautique - Centrales D\'Achats', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(448, 'Aéronautique - Maintenance', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(449, 'Aéroports', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(450, 'Aéroports - Equipements', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(451, 'Aéroports -  Maintenance', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(452, 'Aéroports -  Securite Aerienne', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(453, 'Aéroports -  Services', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(454, 'Agences en Douane', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(455, 'Agences Maritimes', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(456, 'Aéronautique - Pièces Détachées', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(457, 'Chantiers Navals', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(458, 'Chemin de Fer', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(459, 'Compagnies Aériennes', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(460, 'Containers, Fabrication et Location', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(461, 'Déménagements', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(462, 'Expertises Maritimes et Terrestres', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(463, 'Fret aérien - maritime & international', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(464, 'Manutention et Entreposage', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(465, 'Manutention - Equipements', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(466, 'Manutention - Portuaire', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(467, 'Ports', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(468, 'Ports, Matériels et Equipements', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(469, 'Shipchandler', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(470, 'Transit et Consignation', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(471, 'Transports Aériens', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(472, 'Transports en Commun', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(473, 'Transports Ferroviaires', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(474, 'Transports Internationaux', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(475, 'Transports Maritimes', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(476, 'Transports Routiers', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(477, 'Transports Urbains', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(478, 'Travaux Maritimes', 26, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45'),
(479, 'Autres', 27, NULL, '2021-11-18 10:44:45', '2021-11-18 10:44:45');

-- --------------------------------------------------------

--
-- Structure de la table `telephones`
--

DROP TABLE IF EXISTS `telephones`;
CREATE TABLE IF NOT EXISTS `telephones` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idEtablissement` int(11) NOT NULL,
  `numero` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `whatsapp` tinyint(1) NOT NULL DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `principal` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `telephones`
--

INSERT INTO `telephones` (`id`, `idEtablissement`, `numero`, `whatsapp`, `created_at`, `updated_at`, `principal`) VALUES
(1, 7, '699999999', 0, '2021-11-19 14:20:56', '2021-11-19 14:20:56', 1),
(2, 7, '693236987', 1, '2021-11-19 14:20:57', '2021-11-19 14:20:57', 0),
(3, 8, '8855', 1, '2021-11-19 15:02:34', '2021-11-19 15:02:34', 0),
(4, 8, '63333', 0, '2021-11-19 15:02:34', '2021-11-19 15:02:34', 1),
(5, 8, '8112', 1, '2021-11-19 15:02:35', '2021-11-19 15:02:35', 0),
(6, 9, '6663', 0, '2021-11-19 15:18:22', '2021-11-19 15:18:22', 1),
(7, 9, '69870', 1, '2021-11-19 15:18:22', '2021-11-19 15:18:22', 0),
(8, 9, '6999', 1, '2021-11-19 15:18:23', '2021-11-19 15:18:23', 0);

-- --------------------------------------------------------

--
-- Structure de la table `trackings`
--

DROP TABLE IF EXISTS `trackings`;
CREATE TABLE IF NOT EXISTS `trackings` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `longitude` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `latitude` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `trackings`
--

INSERT INTO `trackings` (`id`, `idUser`, `longitude`, `latitude`, `created_at`, `updated_at`) VALUES
(1, 5, '13', '7', '2021-11-20 09:55:13', '2021-11-20 09:55:13'),
(2, 5, '-122.084', '37.4219983', '2021-11-20 10:05:32', '2021-11-20 10:05:32'),
(3, 5, '-122.084', '37.4219983', '2021-11-20 10:05:36', '2021-11-20 10:05:36'),
(4, 5, '-122.084', '37.4219983', '2021-11-20 10:07:42', '2021-11-20 10:07:42'),
(5, 5, '-122.084', '37.4219983', '2021-11-20 10:09:04', '2021-11-20 10:09:04'),
(6, 5, '-122.084', '37.4219983', '2021-11-20 10:10:48', '2021-11-20 10:10:48'),
(7, 5, '-122.084', '37.4219983', '2021-11-20 10:23:17', '2021-11-20 10:23:17'),
(8, 5, '-122.084', '37.4219983', '2021-11-20 10:43:16', '2021-11-20 10:43:16'),
(9, 5, '-122.084', '37.4219983', '2021-11-20 10:58:15', '2021-11-20 10:58:15'),
(10, 5, '-122.084', '37.4219983', '2021-11-21 09:38:16', '2021-11-21 09:38:16'),
(11, 5, '-122.084', '37.4219983', '2021-11-21 10:07:46', '2021-11-21 10:07:46'),
(12, 5, '-122.084', '37.4219983', '2021-11-21 10:22:46', '2021-11-21 10:22:46'),
(13, 5, '-122.084', '37.4219983', '2021-11-21 10:37:46', '2021-11-21 10:37:46'),
(14, 5, '-122.084', '37.4219983', '2021-11-21 10:52:46', '2021-11-21 10:52:46'),
(15, 5, '-122.084', '37.4219983', '2021-11-21 11:07:45', '2021-11-21 11:07:45'),
(16, 5, '-122.084', '37.4219983', '2021-11-21 18:04:49', '2021-11-21 18:04:49'),
(17, 5, '-122.084', '37.4219983', '2021-11-21 18:21:36', '2021-11-21 18:21:36'),
(18, 5, '-122.084', '37.4219983', '2021-11-21 18:36:35', '2021-11-21 18:36:35'),
(19, 5, '-122.084', '37.4219983', '2021-11-22 09:52:18', '2021-11-22 09:52:18');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email_verified_at` timestamp NULL DEFAULT NULL,
  `password` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` int(11) NOT NULL DEFAULT '4',
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_email_unique` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `email_verified_at`, `password`, `phone`, `role`, `remember_token`, `created_at`, `updated_at`) VALUES
(1, 'Admin', 'admin@position.cm', '2021-11-13 08:12:05', '$2y$10$cfqkUD2FtzqnZL119oCWr.VE/iW36wxDWjGFGzyyz3TZYn.XrfDG6', '699999999', 1, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(5, 'Commercial', 'bt@geo.sm', '2021-11-18 23:55:27', '$2y$10$1/fsz3L63rW5DNGBfX48quVVJXaVxuyq/rPhtSLWcAN0cX/GRQ76.', '691201946', 2, NULL, '2021-11-18 23:55:02', '2021-11-18 23:55:27');

-- --------------------------------------------------------

--
-- Structure de la table `zones`
--

DROP TABLE IF EXISTS `zones`;
CREATE TABLE IF NOT EXISTS `zones` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nom` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ville` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
