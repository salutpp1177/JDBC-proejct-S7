-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  mar. 11 déc. 2018 à 17:32
-- Version du serveur :  5.7.17
-- Version de PHP :  5.6.30
CREATE DATABASE IF NOT EXISTS sallereservation;
USE sallereservation;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `sallereservation`
--

-- --------------------------------------------------------

--
-- Structure de la table `horaires`
--

CREATE TABLE `horaires` (
  `IDTEMP` int(11) NOT NULL,
  `NOMSALLE` varchar(11) NOT NULL,
  `STATUS` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `horaires`
--

INSERT INTO `horaires` (`IDTEMP`, `NOMSALLE`, `STATUS`) VALUES
(1, 'Amphi', 'Occupe'),
(1, 'Pascal', 'Libre'),
(1, 'shannon', 'Occupe'),
(1, 'Turing', 'Occupe'),
(2, 'Amphi', 'Occupe'),
(2, 'Pascal', 'Occupe'),
(2, 'shannon', 'Occupe'),
(2, 'Turing', 'Occupe'),
(3, 'Amphi', 'Occupe'),
(3, 'Pascal', 'Libre'),
(3, 'shannon', 'Occupe'),
(3, 'Turing', 'Occupe'),
(4, 'Amphi', 'Occupe'),
(4, 'Pascal', 'Occupe'),
(4, 'shannon', 'Occupe'),
(4, 'Turing', 'Occupe'),
(5, 'Amphi', 'Libre'),
(5, 'Pascal', 'Occupe'),
(5, 'shannon', 'Libre'),
(5, 'Turing', 'Occupe'),
(6, 'Amphi', 'Libre'),
(6, 'Pascal', 'Libre'),
(6, 'shannon', 'Occupe'),
(6, 'Turing', 'Libre'),
(7, 'Amphi', 'Libre'),
(7, 'Pascal', 'Libre'),
(7, 'shannon', 'Libre'),
(7, 'Turing', 'Libre'),
(8, 'Amphi', 'Libre'),
(8, 'Pascal', 'Libre'),
(8, 'shannon', 'Libre'),
(8, 'Turing', 'Libre');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `NOMSALLE` varchar(32) NOT NULL,
  `IDTEMP` int(11) NOT NULL,
  `IDUTILISATEUR` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`NOMSALLE`, `IDTEMP`, `IDUTILISATEUR`) VALUES
('Turing', 2, 9),
('Amphi', 1, 10),
('Amphi', 3, 10),
('shannon', 3, 16),
('shannon', 2, 17);

-- --------------------------------------------------------

--
-- Structure de la table `salles`
--

CREATE TABLE `salles` (
  `NOMSALLE` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `salles`
--

INSERT INTO `salles` (`NOMSALLE`) VALUES
('Amphi'),
('Pascal'),
('shannon'),
('Turing');

-- --------------------------------------------------------

--
-- Structure de la table `temp`
--

CREATE TABLE `temp` (
  `IDTEMP` int(11) NOT NULL,
  `DATE` date DEFAULT NULL,
  `HEURE` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `temp`
--

INSERT INTO `temp` (`IDTEMP`, `DATE`, `HEURE`) VALUES
(1, '2018-12-06', '08:00:00'),
(2, '2018-12-06', '10:00:00'),
(3, '2018-12-06', '14:00:00'),
(4, '2018-12-06', '16:00:00'),
(5, '2018-12-07', '08:00:00'),
(6, '2018-12-07', '10:00:00'),
(7, '2018-12-07', '14:00:00'),
(8, '2018-12-07', '16:00:00'),
(9, '2018-12-08', '08:00:00'),
(10, '2018-12-08', '10:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE `utilisateurs` (
  `IDUTILISATEUR` int(11) NOT NULL,
  `NOM` char(32) DEFAULT NULL,
  `PRENOM` char(32) DEFAULT NULL,
  `NOMUTILISATEUR` char(32) NOT NULL,
  `MOTDEPASSE` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`IDUTILISATEUR`, `NOM`, `PRENOM`, `NOMUTILISATEUR`, `MOTDEPASSE`) VALUES
(1, 'FENG', 'Jiaming', 'Windshear', '123456'),
(2, 'feng', 'bo', 'fancyfor', ''),
(9, '', '', 'feng', '1'),
(10, 'meng', 'qingling', 'meng', '123'),
(11, '123', '432', '232', '123'),
(13, NULL, NULL, '1', ''),
(14, 'harry', 'feng', 'bobo', '321'),
(15, 'bobob', 'mem', 'mm', '123'),
(16, 'yu', 'yan', 'yu', '1'),
(17, 'jiang', 'yaqi', 'yaqi', '1');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `horaires`
--
ALTER TABLE `horaires`
  ADD PRIMARY KEY (`IDTEMP`,`NOMSALLE`),
  ADD KEY `FK_HORAIRES_SALLES` (`NOMSALLE`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`NOMSALLE`,`IDTEMP`),
  ADD KEY `FK_RESERVATION_UTILISATEURS` (`IDUTILISATEUR`),
  ADD KEY `FK_RESERVATION_TEMP` (`IDTEMP`);

--
-- Index pour la table `salles`
--
ALTER TABLE `salles`
  ADD PRIMARY KEY (`NOMSALLE`),
  ADD UNIQUE KEY `NOMSALLE` (`NOMSALLE`);

--
-- Index pour la table `temp`
--
ALTER TABLE `temp`
  ADD PRIMARY KEY (`IDTEMP`);

--
-- Index pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD PRIMARY KEY (`IDUTILISATEUR`),
  ADD UNIQUE KEY `NOMUTILISATEUR` (`NOMUTILISATEUR`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  MODIFY `IDUTILISATEUR` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
