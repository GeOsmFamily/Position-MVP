-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3308
-- Généré le :  mer. 17 nov. 2021 à 05:19
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
) ENGINE=MyISAM AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
(63, 1, '127.0.0.1', 'http://127.0.0.1:8000/api/search/etablissements?q=sogefi', '2021-11-16 15:19:06', '2021-11-16 15:19:06');

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
  `nom` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
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
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `batiments`
--

INSERT INTO `batiments` (`id`, `nom`, `nombreNiveaux`, `codeBatiment`, `longitude`, `latitude`, `image`, `indication`, `rue`, `ville`, `commune`, `quartier`, `created_at`, `updated_at`) VALUES
(1, 'ROCKS', 3, 'BATIMENT_3', 13, 7, '/storage/uploads/batiments/images/ROCKS/1637057427_Wavy_Bus-39_Single-06.jpg', 'devant polytech', '542 melen', 'Yaounde', 'Yaounde 4', 'Melen', '2021-11-16 10:10:27', '2021-11-16 10:10:27'),
(2, 'ROCKS', 3, 'BATIMENT_3', 13, 7, '/storage/uploads/batiments/images/ROCKS/1637057596_Wavy_Bus-39_Single-06.jpg', 'devant polytech', '542 melen', 'Yaounde', 'Yaounde 4', 'Melen', '2021-11-16 10:13:16', '2021-11-16 10:13:16');

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
) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `categories`
--

INSERT INTO `categories` (`id`, `nom`, `logo_url`, `created_at`, `updated_at`) VALUES
(1, 'Achats', NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(2, 'Administrations', NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(3, 'Agriculture', NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(4, 'Alimentation', NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(5, 'Automobile, Moto, Engins', NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(6, 'Banques, finances et assurances', NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(7, 'Batiments & Constructions', NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(8, 'Bien-être', NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(9, 'Commerce - Import & Export', NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(10, 'Communication, Journalisme, Audiovisuel', NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(11, 'Eau', NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(12, 'Education & Formation', NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(13, 'Energie', NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(14, 'Hydrocarbures, Pétroliers, Forages', NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(15, 'Immobilier', NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(16, 'Industries', NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(17, 'Informatique, Internet, Nouvelles Technologies', NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(18, 'Justice', NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(19, 'Loisirs', NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(20, 'Restos, bars', NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(21, 'Santé & Médecine', NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(22, 'Securite, Gardiennage, Protection Incendie', NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(23, 'Télécommunication', NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(24, 'Textiles & Prêt à Porter', NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(25, 'Tourisme', NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(26, 'Transports', NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05');

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `etablissements`
--

DROP TABLE IF EXISTS `etablissements`;
CREATE TABLE IF NOT EXISTS `etablissements` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idBatiment` int(11) NOT NULL,
  `nom` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `indicationAdresse` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
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
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `etablissements`
--

INSERT INTO `etablissements` (`id`, `idBatiment`, `nom`, `indicationAdresse`, `codePostal`, `siteInternet`, `idSousCategorie`, `idCommercial`, `idManager`, `etage`, `cover`, `vues`, `created_at`, `updated_at`) VALUES
(1, 1, 'SOGEFI', 'devant polytech', '14440', 'sogefi.cm', 10, 1, NULL, 1, '/storage/uploads/batiments/images/BATIMENT_3/SOGEFI/1637057826_Wavy_Bus-39_Single-06.jpg', 0, '2021-11-16 10:17:06', '2021-11-16 10:17:08'),
(2, 1, 'CODIMED', 'devant polytech', '14440', 'sogefi.cm', 50, 1, NULL, 2, '/storage/uploads/batiments/images/BATIMENT_3/CODIMED/1637058369_Wavy_Bus-39_Single-06.jpg', 0, '2021-11-16 10:26:09', '2021-11-16 10:26:09');

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `images`
--

DROP TABLE IF EXISTS `images`;
CREATE TABLE IF NOT EXISTS `images` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idEtablissement` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `imageUrl` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
(21, '2021_11_10_164644_create_etablissements_table', 1);

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
('af561466164bee6a8c13dcc46991ef642f6ad3a1475425aff311731192fd9417b77184bf0cfa3058', 1, 1, 'Position', '[]', 0, '2021-11-16 09:59:11', '2021-11-16 09:59:11', '2021-12-16 10:59:11');

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
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `oauth_clients`
--

INSERT INTO `oauth_clients` (`id`, `user_id`, `name`, `secret`, `provider`, `redirect`, `personal_access_client`, `password_client`, `revoked`, `created_at`, `updated_at`) VALUES
(1, NULL, 'Position Personal Access Client', 'wtd2nSEKzBD8TqeuIfFwRztnC0rBMQ5Erj5qlDfu', NULL, 'http://localhost', 1, 0, 0, '2021-11-13 08:11:53', '2021-11-13 08:11:53'),
(2, NULL, 'Position Password Grant Client', 'wGaK8HcDSB1xiQ5w8QHAdpW1S7KBWAATCUtFdgjW', 'users', 'http://localhost', 0, 1, 0, '2021-11-13 08:11:53', '2021-11-13 08:11:53');

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
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `oauth_personal_access_clients`
--

INSERT INTO `oauth_personal_access_clients` (`id`, `client_id`, `created_at`, `updated_at`) VALUES
(1, 1, '2021-11-13 08:11:53', '2021-11-13 08:11:53');

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
) ENGINE=MyISAM AUTO_INCREMENT=479 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `sous_categories`
--

INSERT INTO `sous_categories` (`id`, `nom`, `idCategorie`, `logoUrl`, `created_at`, `updated_at`) VALUES
(1, 'Boutiques', 1, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(2, 'Brocante', 1, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(3, 'Supermarché', 1, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(4, 'Epicerie', 1, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(5, 'Blanchisseries et Pressings', 1, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(6, 'Centre Commercial', 1, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(7, 'Maison et Jardin', 1, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(8, 'Hifi, téléphonie', 1, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(9, 'Fleuriste', 1, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(10, 'Boulangerie, Pâtisserie', 1, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(11, 'Caviste', 1, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(12, 'Tabac', 1, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(13, 'Jouets et jeux', 1, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(14, 'Magasin de sport', 1, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(15, 'Ameublement et Mobilier', 1, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(16, 'Fournitures de Bureaux', 1, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(17, 'Mobilier de Bureaux', 1, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(18, 'Mobilier de Jardin', 1, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(19, 'Vêtements', 1, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(20, 'Chaussures', 1, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(21, 'Bijoux et accessoires', 1, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(22, 'Puériculture', 1, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(23, 'Administrations', 2, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(24, 'Ambassades et Consulats', 2, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(25, 'Associations, syndicats', 2, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(26, 'Douane, Agences', 2, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(27, 'Minisères', 2, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(28, 'O.N.G & Organisations Internationales', 2, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(29, 'Offices Nationaux', 2, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(30, 'Poste', 2, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(31, 'Sécurité Sociale', 2, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(32, 'Institution publique', 2, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(33, 'Matériels et Produits agricoles', 3, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(34, 'Agricole, Produits Chimiques', 3, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(35, 'Agriculture', 3, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(36, 'Equipements et Matériel agricoles', 3, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(37, 'Agro-Alimentaire', 3, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(38, 'Agro-Industrie', 3, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(39, 'Elevage', 3, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(40, 'Elevage - Consultants', 3, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(41, 'Abattoirs et Viande en Gros', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(42, 'Alcools, vins, spiritueux, drogueries', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(43, 'Alimentaire, Distributeurs et Grossistes', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(44, 'Industries alimentaires', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(45, 'Produits alimentaires', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(46, 'Alimentation Animale', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(47, 'Alimentation Générale', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(48, 'Boissons', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(49, 'Boucherie - charcuterie', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(50, 'Boulangeries, Patisseries, Glaces', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(51, 'Boulangeries, Patisseries, Glaces - matériel & équipement', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(52, 'Brasseries', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(53, 'Brasseries - matériel & équipement', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(54, 'Cacao - Production et Exportation', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(55, 'Café - Production et Exportation', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(56, 'Catering', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(57, 'Distilleries', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(58, 'Environnement', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(59, 'Gomme Arabique', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(60, 'Lait, Yaourt et Fromage', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(61, 'Laiteries', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(62, 'Minoteries', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(63, 'Pêche - Commercialisation et Exportation', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(64, 'Pêche - Congelation', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(65, 'Sucre, Fabrication et Raffinage', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(66, 'Supermarchés', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(67, 'Thé - Production et Commercialisation', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(68, 'Traiteurs', 4, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(69, 'Auto-Ecoles', 5, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(70, 'Automobiles et Concessionnaires', 5, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(71, 'Expertises automobiles', 5, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(72, 'Automobiles - Pièces Détachées et Accessoires', 5, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(73, 'Voitures d\'occasion', 5, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(74, 'Constructions Mécaniques', 5, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(75, 'Contrôles Techniques', 5, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(76, 'Cycles et Motos', 5, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(77, 'Engins ee Chantier et Matériels', 5, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(78, 'Garages', 5, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(79, 'Mécanique Générale', 5, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(80, 'Mécanique - Industries', 5, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(81, 'Moteurs et Pompes', 5, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(82, 'Pneumatiques', 5, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(83, 'Tracking', 5, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(84, 'Véhicules Industriels', 5, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(85, 'Station-service', 5, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(86, 'Parking', 5, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(87, 'Garage', 5, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(88, 'Assurances', 6, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(89, 'Assurances Vies', 6, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(90, 'Assurances médicales', 6, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(91, 'Assureurs - Courtiers Et Conseils', 6, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(92, 'Banques et Organismes Financiers', 6, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(93, 'Equipements de Sécurité Bancaire', 6, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(94, 'Banques - Services Mobiles', 6, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(95, 'Bureaux de Change et Transferts d\'argent', 6, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(96, 'Bureaux de Contrôle', 6, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(97, 'Cabinets Comptables', 6, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(98, 'Centres d\'Information', 6, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(99, 'Crédits et Finances', 6, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(100, 'Enquêtes, recherches et investigation', 6, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(101, 'Holdings', 6, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(102, 'Investissements', 6, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(103, 'Transports de Fonds', 6, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(104, 'Adduction d\'eau et VRD', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(105, 'Agencement et Décoration', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(106, 'Industrie d\'aluminium', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(107, 'Aménagement des Terrains Urbains', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(108, 'Architecture et Urbanisme', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(109, 'Ascenseurs', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(110, 'Assainissement et Canalisations', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(111, 'Bâches et Stores', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(112, 'Bâtiments et Travaux Publics', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(113, 'Bâtiments - Expertises', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(114, 'Bâtiments - Nettoyage', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(115, 'Bâtiments - Préfabriqué', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(116, 'Bâtiments - Ravalement', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(117, 'Bâtiments - Réhabilitation', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(118, 'Bâtiments - Second Œuvre', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(119, 'Bitume', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(120, 'Bois et Négoce', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(121, 'Bois - Industries', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(122, 'Bois - Scieries', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(123, 'Bricolage', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(124, 'Briqueteries', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(125, 'Bureaux d\'Etudes et D\'Ingénierie', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(126, 'Câbles', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(127, 'Caoutchouc', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(128, 'Carrelages', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(129, 'Carrières - Exploitations et Exploration', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(130, 'Chaudronnerie', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(131, 'Cimenteries', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(132, 'Citernes - Métallique et Plastique', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(133, 'Climatisation', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(134, 'Climatisation - Dépannage et Maintenance', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(135, 'Constructions Industrielles', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(136, 'Constructions Métalliques', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(137, 'Développement Rural et Urbain', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(138, 'Ebenistes', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(139, 'Echafaudages', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(140, 'Espaces Verts et Jardins', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(141, 'Etanchéité', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(142, 'Faux-Plafonds et Staff', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(143, 'Fer et Acier', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(144, 'Forages', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(145, 'Forages - Maintenance', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(146, 'Forages - Matériels et Equipements', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(147, 'Forestiers - Matériels et Equipement', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(148, 'Génie Civil', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(149, 'Géologues', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(150, 'Géomètres - topographes', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(151, 'Groupes Electrogènes', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(152, 'Hydraulique', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(153, 'Hydraulique - Constructions', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(154, 'Hydraulique - Matériels et Equipements', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(155, 'Isolation industrielle', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(156, 'Laboratoires d\'Analyses du Bâtiment', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(157, 'Location d\'Engins de Chantier', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(158, 'Matériaux de Construction', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(159, 'Menuiseries Aluminium', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(160, 'Menuiseries Bois', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(161, 'Menuiseries Industrielles', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(162, 'Menuiseries Métalliques', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(163, 'Mines - Exploitations et Exploration', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(164, 'Mines - Import et Export', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(165, 'Mines - Matériels et Equipements', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(166, 'Mines - Recherche', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(167, 'Miroiteries et Vitreries', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(168, 'Ouvrages d\'Art', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(169, 'Peintres', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(170, 'Peinture', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(171, 'Piscines', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(172, 'Plomberie et Sanitaires', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(173, 'Portes et Fenêtres', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(174, 'Propreté urbaine', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(175, 'Quincailleries', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(176, 'Routes', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(177, 'Routes - Matériaux', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(178, 'Routes - Signalisation', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(179, 'Serrureries', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(180, 'Toitures - Matériaux', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(181, 'Tôles', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(182, 'Topographie', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(183, 'Travaux Publics et Terrassement', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(184, 'Travaux Publics et Terrassement - Maintenance', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(185, 'Tuyauteries et PVC', 7, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(186, 'Coiffeur', 8, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(187, 'Institut de beauté, parfumerie, relaxation', 8, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(188, 'Massages', 8, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(189, 'Centrales d\'Achats', 9, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(190, 'Chambres de Commerce', 9, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(191, 'Commerce Exterieur - Promotion', 9, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(192, 'Commerce Général', 9, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(193, 'E-Commerce', 9, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(194, 'Groupements D\'Entreprises', 9, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(195, 'Import et Export', 9, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(196, 'Promotion Commerciale et Etudes', 9, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(197, 'Recouvrements Commerciaux', 9, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(198, 'Représentation Commerciale', 9, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(199, 'Agences de Mannequins', 10, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(200, 'Agences de Presse et d\'Information', 10, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(201, 'Agences de Publicité et de Communication', 10, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(202, 'Audiovisuel - Matériels et Production', 10, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(203, 'Evénementiel', 10, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(204, 'Journaux et Presse', 10, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(205, 'Maison d\'édition', 10, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(206, 'Marketing', 10, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(207, 'Objets Publicitaires', 10, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(208, 'Organisations de Conférences', 10, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(209, 'Radio et Télévision', 10, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(210, 'Radio-Communication', 10, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(211, 'Relations Publiques et Organisations', 10, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(212, 'Ressources Humaines', 10, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(213, 'Sérigraphie', 10, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(214, 'Eau - Distribution', 11, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(215, 'Eau - Laboratoires d\'Analyses', 11, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(216, 'Eau - Traitement', 11, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(217, 'Centres de Documentation', 12, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(218, 'Crèche', 12, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(219, 'Centre de loisirs d\'enfants', 12, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(220, 'Centre linguistique - enseignement', 12, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(221, 'Centres de Formation', 12, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(222, 'Centres de Recherche', 12, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(223, 'Ecoles et Universités', 12, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(224, 'Automatisme', 13, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(225, 'Batteries et Piles', 13, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(226, 'Electricité - Générale et Industrielle', 13, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(227, 'Electricité - Expertises', 13, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(228, 'Electricité - Industries', 13, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(229, 'Electricité - Ingénierie', 13, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(230, 'Electricité - Materiels et Equipement', 13, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(231, 'Electrification Rurale Et Urbaine', 13, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(232, 'Electromécanique', 13, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(233, 'Energie', 13, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(234, 'Energie Nouvelle et Renouvelable', 13, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(235, 'Energie Solaire', 13, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(236, 'Gaz Domestique Et Industriel', 13, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(237, 'Géotechnique', 13, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(238, 'Hydrocarbures - Aviation', 14, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(239, 'Hydrocarbures - Distribution', 14, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(240, 'Hydrocarbures - Equipements Et Mater...', 14, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(241, 'Hydrocarbures - Expertises', 14, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(242, 'Hydrocarbures - Exploration', 14, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(243, 'Hydrocarbures - Logistiques', 14, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(244, 'Hydrocarbures - Lubrifiants', 14, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(245, 'Hydrocarbures - Maintenance', 14, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(246, 'Hydrocarbures - Production', 14, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(247, 'Hydrocarbures - Transports', 14, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(248, 'Pétroliers, Constructions', 14, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(249, 'Pétroliers - Matériels et Equipement', 14, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(250, 'Raffineries', 14, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(251, 'Garde Meubles', 15, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(252, 'Immobilier - Agences', 15, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(253, 'Immobilier - Expertises', 15, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(254, 'Immobilier - Gestion', 15, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(255, 'Immobilier - Location', 15, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(256, 'Immobilier - Promoteurs', 15, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(257, 'Electro-Ménager', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(258, 'Electronique', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(259, 'Allumettes et Bougies', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(260, 'Armes et Munitions', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(261, 'Assistance Technique', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(262, 'Cartonneries', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(263, 'Chaussures, Manufactures', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(264, 'Coton', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(265, 'Dépannage et Maintenance Industrielle', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(266, 'Diamant et Or', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(267, 'Emballage et Conditionnement', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(268, 'Equipements de Bureaux', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(269, 'Equipements Industriels', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(270, 'Fournitures Industrielles', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(271, 'Froid Industriel', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(272, 'Location De Matériels', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(273, 'Machines-Outils, Manufactures', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(274, 'Manutention Industrielle', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(275, 'Matelas', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(276, 'Matelas, Equipements et Materiels', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(277, 'Mesure et Pesage, Materiels', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(278, 'Nettoyage Industriel', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(279, 'Nettoyage, Matériels et Equipements', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(280, 'Papier - Industries', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(281, 'Pièces Détachées Industrielles', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(282, 'Plastique - Industries', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(283, 'Plastique - Produits', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(284, 'Savonneries', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(285, 'Soudage, Matériels et Produits', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(286, 'Tabac, Manufactures et Importateurs', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(287, 'Travail Temporaire, Interim', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(288, 'Tuyauteries', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(289, 'Verre - Industries', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(290, 'Zone Franche Industrielle', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(291, 'Ameublement et Mobilier, Industries', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(292, 'Fournitures de Bureaux, Industries', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(293, 'Mobilier - Industries', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(294, 'Chimie - Industries', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(295, 'Chimie - Produits', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(296, 'Parfum, Industries', 16, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(297, 'Antennes Paraboliques, Equipements', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(298, 'Archivage Numerique', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(299, 'Bureautique', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(300, 'Business Center', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(301, 'Centres d\'Appels', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(302, 'Courriers Express, Colis Express', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(303, 'Cyber-Cafes', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(304, 'Domiciliation de Sociétés', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(305, 'Editeurs', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(306, 'Etudes de Marchés', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(307, 'Imprimeries', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(308, 'Imprimeries, Matériels et Equipement', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(309, 'Informatique', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(310, 'Informatique - Consommables', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(311, 'Informatique - Constructeurs', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(312, 'Informatique - Consultants', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(313, 'Informatique - Environnement', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(314, 'Informatique - Expertises', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(315, 'Informatique - Gestion De Maintenance', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(316, 'Informatique - Ingénierie', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(317, 'Informatique - Ingénieurs', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(318, 'Informatique - Maintenance', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(319, 'Informatique - Reseaux', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(320, 'Informatique - Securite', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(321, 'Internet', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(322, 'Internet - Provider', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(323, 'Internet - Web Design', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(324, 'Internet - E-Business & E-Commerce', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(325, 'Paratonnerre', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(326, 'Technologies Nouvelles', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(327, 'Technologies Nouvelles, Consultants', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(328, 'Traducteurs Et Interpretes', 17, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(329, 'Avocats', 18, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(330, 'Avocats d\'Affaires Internationales', 18, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(331, 'Cabinets Juridiques et Fiscaux', 18, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(332, 'Consultants', 18, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(333, 'Consultants en Immigration', 18, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(334, 'Consultants Internationaux', 18, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(335, 'Expertises  Comptables, Audit Et Conseil', 18, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(336, 'Expertises - Etudes, Communication', 18, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(337, 'Expertises Judiciaire', 18, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(338, 'Huissiers De Justice', 18, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(339, 'Notaires', 18, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(340, 'Projets et Bureaux D\'Appui', 18, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(341, 'Artisanat, Antiquaires et Galeries d\'Art', 19, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(342, 'Cadeaux', 19, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(343, 'Casino', 19, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(344, 'Cinéma et vidéothèques', 19, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(345, 'Centre culturel', 19, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(346, 'Musée', 19, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(347, 'Musique', 19, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(348, 'Théâtre, spectacle', 19, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(349, 'Cabaret', 19, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(350, 'Bibliothèque et Centre de Documentation', 19, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(351, 'Librairie, papeterie', 19, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(352, 'Sport', 19, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(353, 'Espaces verts et Jardins', 19, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(354, 'Salle de spectacles - salle de fêtes', 19, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(355, 'Loisirs, jeux et entertainment', 19, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(356, 'Loterie', 19, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(357, 'Voyance, Medium', 19, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(358, 'Restaurant', 20, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(359, 'Night Clubs et Discothèques', 20, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(360, 'Restauration rapide', 20, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(361, 'Bar, Café', 20, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(362, 'Salons de Thé et Glaciers', 20, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(363, 'Ambulance', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(364, 'Assistance sociale', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(365, 'Médecine Générale', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(366, 'Médecine Spécialisée - dentiste', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(367, 'Médecine Spécialisée - dermatologie', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(368, 'Médecine Spécialisée - cardiologie', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(369, 'Médecine Spécialisée - oncologie', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(370, 'Médecine Spécialisée - ophtalmologie', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(371, 'Médecine Spécialisée - psychologie', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(372, 'Médecine Spécialisée - angiologie', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(373, 'Médecine Spécialisée - pédicure et podologie', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(374, 'Médecine Spécialisée - ostéopathie', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(375, 'Médecine Spécialisée - gynécologie', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(376, 'Médecine Spécialisée - kinésithérapeute', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(377, 'Médecine naturelle, traditionnelle', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(378, 'Médecine du travail', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(379, 'Chirurgie', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(380, 'Chirurgie Esthétique', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(381, 'Cliniques et Hopitaux', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(382, 'Cosmétiques - Industries', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(383, 'Cosmétiques - Produits', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(384, 'Drogueries', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(385, 'Laboratoires d\'Analyses Médicales', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(386, 'Laboratoires, Matériels et Equipement', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(387, 'Laboratoires - Produits', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(388, 'Medical - Assistance', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(389, 'Medical - Gaz Medical', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(390, 'Medical - Industries', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(391, 'Medical - Materiels Et Equipements', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(392, 'Medical - Produits', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(393, 'Opticiens', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(394, 'Opticiens - Matériels et Equipements', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(395, 'Para-Pharmacie', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(396, 'Pharmaceutiques - Distributeurs', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(397, 'Pharmaceutiques - Industries', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(398, 'Pharmaceutiques - Laboratoires', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(399, 'Pharmaceutiques - Materiels Et Equipement', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(400, 'Pharmaceutiques - Produits', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(401, 'Pharmacies', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(402, 'Phytosanitaires - Traitements', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(403, 'Urgences Medicales', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(404, 'Vétérinaires', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(405, 'Vétérinaires, Produits et Pharmacie', 21, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(406, 'Alarmes Et Surveillance, Matériels électroniques', 22, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(407, 'Badges, Fabrication', 22, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(408, 'Controles Techniques', 22, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(409, 'Détectives', 22, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(410, 'Escorte Véhicules', 22, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(411, 'Gardiennage et Sécurité', 22, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(412, 'Gardiennage et Sécurité - Equipements', 22, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(413, 'Incendie et Protection - Matériels', 22, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(414, 'Sécurité - Audits et Consultants', 22, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(415, 'Télé-Surveillance et Vidéo-Surveillance', 22, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(416, 'Télécommunications - Transmission de signal', 23, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(417, 'Télécommunications et Téléphonie', 23, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(418, 'Télécommunications - Fibres Optique', 23, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(419, 'Télécommunications - Installation et maintenance', 23, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(420, 'Télécommunications - Matériels et équipement', 23, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(421, 'Télécommunications - Mobiles', 23, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(422, 'Télécommunications - Réseaux', 23, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(423, 'Télécommunications - Satellite', 23, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(424, 'Confection, Couture, Broderie', 24, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(425, 'Equipements et vêtements militaires', 24, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(426, 'Friperies', 24, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(427, 'Prêt à Porter - Gros et Détail', 24, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(428, 'Textile', 24, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(429, 'Textile, Filature, Tissage, Impression', 24, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(430, 'Textile, Fournitures', 24, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(431, 'Textile - Matériels et Equipements', 24, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(432, 'Vêtements  Manufactures', 24, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(433, 'Agences de Tourisme', 25, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(434, 'Centres d\'Information', 25, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(435, 'Agences de voyage, tours opérateurs', 25, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(436, 'Equipements Hôtels et Restaurants', 25, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(437, 'Hôtel', 25, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(438, 'Auberge', 25, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(439, 'Location d\'avions', 25, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(440, 'Location de bateaux', 25, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(441, 'Location de voitures', 25, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(442, 'Transport Logistique', 25, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(443, 'Transports Touristiques', 25, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(444, 'Accastillage', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(445, 'Acconage', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(446, 'Aéronautique', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(447, 'Aéronautique - Centrales D\'Achats', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(448, 'Aéronautique - Maintenance', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(449, 'Aéroports', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(450, 'Aéroports - Equipements', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(451, 'Aéroports -  Maintenance', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(452, 'Aéroports -  Securite Aerienne', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(453, 'Aéroports -  Services', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(454, 'Agences en Douane', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(455, 'Agences Maritimes', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(456, 'Aéronautique - Pièces Détachées', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(457, 'Chantiers Navals', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(458, 'Chemin de Fer', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(459, 'Compagnies Aériennes', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(460, 'Containers, Fabrication et Location', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(461, 'Déménagements', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(462, 'Expertises Maritimes et Terrestres', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(463, 'Fret aérien - maritime & international', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(464, 'Manutention et Entreposage', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(465, 'Manutention - Equipements', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(466, 'Manutention - Portuaire', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(467, 'Ports', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(468, 'Ports, Matériels et Equipements', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(469, 'Shipchandler', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(470, 'Transit et Consignation', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(471, 'Transports Aériens', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(472, 'Transports en Commun', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(473, 'Transports Ferroviaires', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(474, 'Transports Internationaux', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(475, 'Transports Maritimes', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(476, 'Transports Routiers', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(477, 'Transports Urbains', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05'),
(478, 'Travaux Maritimes', 26, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05');

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
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `email_verified_at`, `password`, `phone`, `role`, `remember_token`, `created_at`, `updated_at`) VALUES
(1, 'Admin', 'admin@position.cm', '2021-11-13 08:12:05', '$2y$10$cfqkUD2FtzqnZL119oCWr.VE/iW36wxDWjGFGzyyz3TZYn.XrfDG6', '699999999', 1, NULL, '2021-11-13 08:12:05', '2021-11-13 08:12:05');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
