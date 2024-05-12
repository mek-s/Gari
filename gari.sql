-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : dim. 12 mai 2024 à 10:31
-- Version du serveur : 8.0.31
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gari`
--

-- --------------------------------------------------------

--
-- Structure de la table `parking`
--

DROP TABLE IF EXISTS `parking`;
CREATE TABLE IF NOT EXISTS `parking` (
  `id_parking` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `commune` varchar(255) DEFAULT NULL,
  `nb_places` int DEFAULT NULL,
  `latitude` decimal(9,6) DEFAULT NULL,
  `longitude` decimal(9,6) DEFAULT NULL,
  `tarif` decimal(10,2) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `image` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id_parking`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `parking`
--

INSERT INTO `parking` (`id_parking`, `name`, `commune`, `nb_places`, `latitude`, `longitude`, `tarif`, `adresse`, `image`) VALUES
(1, 'Parking A', 'City A', 100, '40.712800', '-74.006000', '10.00', '123 Main St', 'images/parking.png'),
(2, 'Parking B', 'City B', 150, '34.052200', '-118.243700', '12.50', '456 Elm St', NULL),
(3, 'Parking C', 'City C', 200, '51.507400', '-0.127800', '15.00', '789 Oak St', NULL),
(4, 'Parking D', 'City D', 120, '48.856600', '2.352200', '8.00', '101 Pine St', NULL),
(5, 'Parking E', 'City E', 180, '55.755800', '37.617600', '20.00', '202 Maple St', NULL),
(6, 'Parking F', 'City F', 90, '52.520000', '13.405000', '18.00', '303 Birch St', NULL),
(7, 'Parking G', 'City G', 110, '-33.868800', '151.209300', '25.00', '404 Cedar St', NULL),
(8, 'Parking H', 'City H', 130, '-22.906800', '-43.172900', '30.00', '505 Walnut St', NULL),
(9, 'Parking I', 'City I', 140, '45.421500', '-75.691000', '22.00', '606 Pineapple St', NULL),
(10, 'Parking J', 'City J', 160, '37.774900', '-122.419400', '28.00', '707 Orange St', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `place`
--

DROP TABLE IF EXISTS `place`;
CREATE TABLE IF NOT EXISTS `place` (
  `id_place` int NOT NULL AUTO_INCREMENT,
  `id_parking` int DEFAULT NULL,
  `reservee` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_place`),
  KEY `idParking` (`id_parking`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `place`
--

INSERT INTO `place` (`id_place`, `id_parking`, `reservee`) VALUES
(1, 1, 0),
(2, 1, 0),
(3, 1, 0),
(4, 2, 0),
(5, 2, 0),
(6, 2, 0),
(7, 2, 0),
(8, 2, 0),
(9, 3, 0),
(10, 3, 0),
(11, 3, 0),
(12, 3, 0);

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `id_reservation` int NOT NULL,
  `id_place` int NOT NULL,
  `Date` date DEFAULT NULL,
  `heure_entree` time DEFAULT NULL,
  `heure_sortie` time DEFAULT NULL,
  `code_qr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `prix` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_reservation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
