-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : sam. 11 mai 2024 à 18:47
-- Version du serveur : 8.3.0
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
  `id_parking` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `commune` varchar(255) DEFAULT NULL,
  `nb_places` int DEFAULT NULL,
  `latitude` decimal(9,6) DEFAULT NULL,
  `longitude` decimal(9,6) DEFAULT NULL,
  `tarif` decimal(10,2) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `image` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id_parking`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `parking`
--

INSERT INTO `parking` (`id_parking`, `name`, `commune`, `nb_places`, `latitude`, `longitude`, `tarif`, `adresse`, `image`) VALUES
(1, 'Parking A', 'City A', 100, '40.712800', '-74.006000', '10.00', '123 Main St', NULL),
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
  `idPlace` int NOT NULL,
  `idParking` int DEFAULT NULL,
  `reservee` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idPlace`),
  KEY `idParking` (`idParking`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `idReservation` int NOT NULL,
  `idPlace` int DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `heureEntree` time DEFAULT NULL,
  `heureSortie` time DEFAULT NULL,
  `codeQR` varchar(255) DEFAULT NULL,
  `prix` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idReservation`),
  KEY `idPlace` (`idPlace`)
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

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `place`
--
ALTER TABLE `place`
  ADD CONSTRAINT `place_ibfk_1` FOREIGN KEY (`idParking`) REFERENCES `parking` (`id_parking`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`idPlace`) REFERENCES `place` (`idPlace`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
