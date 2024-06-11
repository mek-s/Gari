-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 10 juin 2024 à 22:34
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
(2, 'Parking B', 'City B', 150, '34.052200', '-118.243700', '12.50', '456 Elm St', 'images/parking.png'),
(3, 'Parking C', 'City C', 200, '51.507400', '-0.127800', '15.00', '789 Oak St', 'images/parking.png'),
(4, 'Parking D', 'City D', 120, '48.856600', '2.352200', '8.00', '101 Pine St', 'images/parking.png'),
(5, 'Parking E', 'City E', 180, '55.755800', '37.617600', '20.00', '202 Maple St', 'images/parking.png'),
(6, 'Parking F', 'City F', 90, '52.520000', '13.405000', '18.00', '303 Birch St', 'images/parking.png'),
(7, 'Parking G', 'City G', 110, '-33.868800', '151.209300', '25.00', '404 Cedar St', 'images/parking.png'),
(8, 'Parking H', 'City H', 130, '-22.906800', '-43.172900', '30.00', '505 Walnut St', 'images/parking.png'),
(9, 'Parking I', 'City I', 140, '45.421500', '-75.691000', '22.00', '606 Pineapple St', 'images/parking.png'),
(10, 'Parking J', 'City J', 160, '37.774900', '-122.419400', '28.00', '707 Orange St', 'images/parking.png');

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
(1, 1, 1),
(2, 1, 1),
(3, 1, 0),
(4, 2, 0),
(5, 2, 1),
(6, 2, 1),
(7, 2, 1),
(8, 2, 1),
(9, 3, 1),
(10, 3, 0),
(11, 3, 0),
(12, 3, 0);

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `id_reservation` int NOT NULL AUTO_INCREMENT,
  `id_place` int NOT NULL,
  `Date` date DEFAULT NULL,
  `heure_entree` time DEFAULT NULL,
  `heure_sortie` time DEFAULT NULL,
  `code_qr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `prix` decimal(10,2) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `id_parking` int DEFAULT NULL,
  PRIMARY KEY (`id_reservation`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id_reservation`, `id_place`, `Date`, `heure_entree`, `heure_sortie`, `code_qr`, `prix`, `username`, `id_parking`) VALUES
(7, 3, '2024-05-16', '19:42:00', '22:42:00', 'ABCD', '30.00', 'meme', NULL),
(8, 8, '2024-05-23', '05:45:00', '09:45:00', 'ABCD', '50.00', 'meme', NULL),
(9, 5, '2024-05-15', '08:53:00', '10:53:00', 'ABCD', '25.00', 'meme', NULL),
(14, 5, '2024-05-01', '19:17:00', '23:17:00', 'ABCD', '50.00', 'meme', NULL),
(15, 6, '2024-05-16', '18:19:00', '21:19:00', 'ABCD', '37.50', 'soumi', NULL),
(16, 1, '2024-05-07', '18:33:00', '22:33:00', 'ABCD', '40.00', 'soumi', NULL),
(17, 6, '2024-05-17', '10:00:00', '12:00:00', 'ABCD', '10.00', 'meme', NULL),
(18, 2, '2024-05-10', '11:23:00', '02:23:00', '0:2:2024-05-10:11:23:2:23:-90.0:meme', '-90.00', 'meme', NULL),
(19, 1, '2024-06-10', '08:00:00', '17:00:00', 'QRCODE12345', '20.50', 'john_doe', NULL),
(20, 1, '2024-06-10', '08:00:00', '17:00:00', 'QRCODE12345', '20.50', 'john_doe', NULL),
(21, 1, '2024-06-10', '08:00:00', '17:00:00', 'QRCODE12345', '20.50', 'john_doe', NULL),
(22, 1, '2024-06-10', '08:00:00', '17:00:00', 'QRCODE12345', '20.50', 'john_doe', 1),
(28, 3, '2024-06-10', '08:00:00', '17:00:00', 'QRCODE12345', '20.50', 'john_doe', 2),
(29, 3, '2024-06-10', '17:21:00', '19:21:00', '3:2024-06-10:20.0:ami', '20.00', 'ami', 1),
(32, 3, '2024-06-12', '19:49:00', '22:49:00', '3:2024-06-12:30.0:ami', '30.00', 'ami', 1),
(33, 3, '2024-06-19', '19:56:00', '21:56:00', '3:2024-06-19:20.0:ami', '20.00', 'ami', 1),
(34, 3, '2024-06-10', '19:17:00', '21:17:00', '3:2024-06-10:20.0:ami', '20.00', 'ami', 1),
(35, 3, '2024-06-10', '19:23:00', '21:23:00', '3:2024-06-10:20.0:ami', '20.00', 'ami', 1),
(36, 3, '2024-06-23', '19:24:00', '12:24:00', '3:2024-06-23:-70.0:ami', '-70.00', 'ami', 1),
(37, 3, '2024-06-10', '19:35:00', '22:35:00', '3:2024-06-10:30.0', '30.00', 'ami', 1),
(38, 3, '2024-06-10', '19:35:00', '22:35:00', '3:2024-06-10:30.0', '30.00', 'ami', 1);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`username`, `password`, `nom`, `prenom`, `photo`, `email`) VALUES
('ami', '12345678', 'aminom', 'amiprenom', '', NULL),
('amira_791', '12345678', 'Bellali ', 'Amira ', '', 'amira2000@gmail.com'),
('exampleUser', 'password123', 'John', 'Doe', 'example.jpg', 'amira2000@gmail.com'),
('lala', '12121212', 'lolo', 'lili', '', 'amira2000@gmail.com'),
('meme', '12345678', 'memeNom', 'memePrenom', '', 'amira2000@gmail.com'),
('soumi', '12345678', 'mkk', 'smm', '', 'amira2000@gmail.com'),
('test', '12345678', 'testNom', 'testPrenom', '', 'amira2000@gmail.com'),
('user1', 'password1', 'Amira', 'Bellali', NULL, 'amira2000@gmail.com'),
('user2', 'password2', 'Soumeya', 'Mekki', NULL, 'amira2000@gmail.com'),
('user3', 'password3', 'Salim', 'Brown', NULL, 'amira2000@gmail.com'),
('user4', 'password4', 'Halima', 'Taylor', NULL, 'amira2000@gmail.com');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
