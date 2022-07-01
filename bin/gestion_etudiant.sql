-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : Dim 28 juin 2020 à 13:44
-- Version du serveur :  10.4.11-MariaDB
-- Version de PHP : 7.2.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gestion_etudiant`
--

-- --------------------------------------------------------

--
-- Structure de la table `enseignant`
--

CREATE TABLE `enseignant` (
  `num_enseignant` int(11) NOT NULL,
  `cin` int(11) NOT NULL,
  `nom` varchar(150) NOT NULL,
  `prenom` varchar(150) NOT NULL,
  `telephone` int(8) NOT NULL,
  `adresse` varchar(150) NOT NULL,
  `pays` varchar(250) NOT NULL,
  `gouvernourat` varchar(250) NOT NULL,
  `date_naissance` date NOT NULL,
  `sexe` varchar(100) NOT NULL,
  `login` varchar(250) NOT NULL,
  `motpasse` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `enseignant`
--

INSERT INTO `enseignant` (`num_enseignant`, `cin`, `nom`, `prenom`, `telephone`, `adresse`, `pays`, `gouvernourat`, `date_naissance`, `sexe`, `login`, `motpasse`) VALUES
(3, 9877016, 'bennourddine', 'bennourddine', 20222222, 'sousse', 'France', 'Nord-Pas-de-Calais', '2019-10-31', 'Femme', 'ibtisem@gmail.com', 'ibtisem'),
(4, 12548561, 'bennourdin', 'said', 24516758, 'dar chaaben', 'Tunisie', 'Nabeul', '2019-02-28', 'Homme', 'said@gmail.com', 'said'),
(5, 15785246, 'ben saleh', 'souhir', 24581845, 'manzel abd rahmen', 'Tunisie', 'Bizerte', '2019-12-31', 'Femme', 'souhir@gmail.com', 'souhir');

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
  `num_etudiant` int(11) NOT NULL,
  `cin` int(11) NOT NULL,
  `nom` varchar(150) NOT NULL,
  `prenom` varchar(150) NOT NULL,
  `adresse` varchar(150) NOT NULL,
  `pays` varchar(250) NOT NULL,
  `gouvernourat` varchar(250) NOT NULL,
  `telephone` int(8) NOT NULL,
  `date_naissance` date NOT NULL,
  `sexe` varchar(100) NOT NULL,
  `login` varchar(250) NOT NULL,
  `motpasse` varchar(250) NOT NULL,
  `id_filiere` int(11) NOT NULL,
  `niveau` varchar(250) NOT NULL,
  `moyenne` float DEFAULT NULL,
  `etat` varchar(100) DEFAULT NULL,
  `somme_credit` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`num_etudiant`, `cin`, `nom`, `prenom`, `adresse`, `pays`, `gouvernourat`, `telephone`, `date_naissance`, `sexe`, `login`, `motpasse`, `id_filiere`, `niveau`, `moyenne`, `etat`, `somme_credit`) VALUES
(4, 1234582, 'sameh', 'sameh', 'sidi mansour ghzela benzart', 'Algérie', 'Alger', 24443862, '2019-11-30', 'Femme', 'hhhg@gmail.com', 'dfghjk', 1, '2 eme licence', 11.6, 'admis', 3),
(7, 12543675, 'sam', 'sam', 'dhhhhhhhhhhhhhhh hdddds', 'Belgique', 'Flamande', 24516289, '2020-06-30', 'Homme', 'sam@gmail.com', 'sam', 3, '2 eme licence', 16, 'admis', 6),
(8, 15544224, 'ebtissem', 'ebtissem', 'nabeul', 'Tunisie', 'Nabeul', 24443862, '2020-07-31', 'Femme', 'ebtissem@gmail.com', 'ebtissem', 8, '3 eme licence', 0, 'Contrôle', NULL),
(16, 12345675, 'jemai', 'sameh', 'mateur', 'Tunisie', 'Bizerte', 24586155, '2020-03-31', 'Femme', 'sameh@gmail.com', 'sam', 1, '1 ere master', 7.6, 'Contrôle', 6),
(17, 12345678, 'sahbi', 'sarra', 'ras jbal', 'France', 'Bretagne', 24586155, '2020-03-31', 'Femme', 'sarra@gmail.com', 'sarra', 1, '3 eme licence', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `filiere`
--

CREATE TABLE `filiere` (
  `id_filiere` int(11) NOT NULL,
  `abriviation` varchar(100) NOT NULL,
  `nom_filiere` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `filiere`
--

INSERT INTO `filiere` (`id_filiere`, `abriviation`, `nom_filiere`) VALUES
(1, 'IAG', 'informatique applique de gestion'),
(3, 'fgh', 'economie'),
(7, 'info', 'info'),
(8, 'lae', 'economie applique'),
(9, 'lag', 'gestion applique'),
(10, 'AD', 'administration d\'affaire'),
(15, 'lag', 'gestion appliquer'),
(16, 'info', 'informatique'),
(18, 'cm', 'comptabilité'),
(19, 'lai', 'informatique appliquer');

-- --------------------------------------------------------

--
-- Structure de la table `filiere_matiere`
--

CREATE TABLE `filiere_matiere` (
  `id_filierematiere` int(11) NOT NULL,
  `id_filiere` int(11) NOT NULL,
  `id_matiere` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `filiere_matiere`
--

INSERT INTO `filiere_matiere` (`id_filierematiere`, `id_filiere`, `id_matiere`) VALUES
(81, 1, 17),
(82, 1, 20),
(58, 3, 17),
(65, 7, 17),
(66, 7, 20),
(67, 7, 22),
(55, 8, 17),
(60, 15, 17),
(61, 15, 20),
(76, 16, 17),
(77, 16, 20),
(78, 16, 23),
(74, 18, 17),
(75, 18, 22),
(79, 19, 17),
(80, 19, 20);

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

CREATE TABLE `matiere` (
  `id_matiere` int(11) NOT NULL,
  `type_matiere` varchar(50) NOT NULL,
  `crédit` int(11) NOT NULL,
  `DS` tinyint(1) NOT NULL DEFAULT 0,
  `coefficient` float NOT NULL,
  `num_enseignant` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `matiere`
--

INSERT INTO `matiere` (`id_matiere`, `type_matiere`, `crédit`, `DS`, `coefficient`, `num_enseignant`) VALUES
(17, 'java', 6, 1, 3, 3),
(20, 'poo', 4, 1, 3, 5),
(22, 'francais', 4, 1, 2, 5),
(23, 'english', 4, 0, 2, 5);

-- --------------------------------------------------------

--
-- Structure de la table `note`
--

CREATE TABLE `note` (
  `id_note` int(11) NOT NULL,
  `id_matiere` int(11) NOT NULL,
  `id_etudiant` int(11) NOT NULL,
  `note_examen` double DEFAULT NULL,
  `note_Ds1` double DEFAULT NULL,
  `note_Ds2` double DEFAULT NULL,
  `note_tp` double DEFAULT NULL,
  `note_Orale` double DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `note`
--

INSERT INTO `note` (`id_note`, `id_matiere`, `id_etudiant`, `note_examen`, `note_Ds1`, `note_Ds2`, `note_tp`, `note_Orale`) VALUES
(23, 17, 7, NULL, 17, 18, NULL, 10),
(27, 17, 16, NULL, 15, 17, NULL, 12);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `enseignant`
--
ALTER TABLE `enseignant`
  ADD PRIMARY KEY (`num_enseignant`),
  ADD UNIQUE KEY `cin` (`cin`,`login`);

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`num_etudiant`),
  ADD UNIQUE KEY `cin` (`cin`),
  ADD UNIQUE KEY `login` (`login`),
  ADD KEY `id_filiere` (`id_filiere`);

--
-- Index pour la table `filiere`
--
ALTER TABLE `filiere`
  ADD PRIMARY KEY (`id_filiere`);

--
-- Index pour la table `filiere_matiere`
--
ALTER TABLE `filiere_matiere`
  ADD PRIMARY KEY (`id_filierematiere`),
  ADD KEY `id_Filiere` (`id_filiere`,`id_matiere`),
  ADD KEY `id_matiere` (`id_matiere`);

--
-- Index pour la table `matiere`
--
ALTER TABLE `matiere`
  ADD PRIMARY KEY (`id_matiere`),
  ADD KEY `matiere_ibfk_1` (`num_enseignant`);

--
-- Index pour la table `note`
--
ALTER TABLE `note`
  ADD PRIMARY KEY (`id_note`),
  ADD KEY `id_etudiant` (`id_etudiant`),
  ADD KEY `id_matiere` (`id_matiere`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `enseignant`
--
ALTER TABLE `enseignant`
  MODIFY `num_enseignant` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `etudiant`
--
ALTER TABLE `etudiant`
  MODIFY `num_etudiant` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT pour la table `filiere`
--
ALTER TABLE `filiere`
  MODIFY `id_filiere` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT pour la table `filiere_matiere`
--
ALTER TABLE `filiere_matiere`
  MODIFY `id_filierematiere` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=83;

--
-- AUTO_INCREMENT pour la table `matiere`
--
ALTER TABLE `matiere`
  MODIFY `id_matiere` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT pour la table `note`
--
ALTER TABLE `note`
  MODIFY `id_note` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `etudiant_ibfk_1` FOREIGN KEY (`id_filiere`) REFERENCES `filiere` (`id_filiere`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `filiere_matiere`
--
ALTER TABLE `filiere_matiere`
  ADD CONSTRAINT `filiere_matiere_ibfk_2` FOREIGN KEY (`id_matiere`) REFERENCES `matiere` (`id_matiere`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `filiere_matiere_ibfk_3` FOREIGN KEY (`id_filiere`) REFERENCES `filiere` (`id_filiere`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `matiere`
--
ALTER TABLE `matiere`
  ADD CONSTRAINT `matiere_ibfk_1` FOREIGN KEY (`num_enseignant`) REFERENCES `enseignant` (`num_enseignant`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `note`
--
ALTER TABLE `note`
  ADD CONSTRAINT `note_ibfk_1` FOREIGN KEY (`id_etudiant`) REFERENCES `etudiant` (`num_etudiant`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `note_ibfk_2` FOREIGN KEY (`id_matiere`) REFERENCES `matiere` (`id_matiere`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
