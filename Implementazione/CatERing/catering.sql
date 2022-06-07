-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Creato il: Giu 07, 2022 alle 17:48
-- Versione del server: 10.4.21-MariaDB
-- Versione PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `catering`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `Availability`
--

CREATE TABLE `Availability` (
  `idUser` int(11) NOT NULL,
  `idTurn` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `Availability`
--

INSERT INTO `Availability` (`idUser`, `idTurn`) VALUES
(2, 1),
(4, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `CookTask`
--

CREATE TABLE `CookTask` (
  `idCook` int(11) NOT NULL,
  `idTask` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `Events`
--

CREATE TABLE `Events` (
  `id` int(11) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `date_start` date DEFAULT NULL,
  `date_end` date DEFAULT NULL,
  `expected_participants` int(11) DEFAULT NULL,
  `organizer_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `Events`
--

INSERT INTO `Events` (`id`, `name`, `date_start`, `date_end`, `expected_participants`, `organizer_id`) VALUES
(1, 'Convegno Agile Community', '2020-09-25', '2020-09-25', 100, 2),
(2, 'Compleanno di Manuela', '2020-08-13', '2020-08-13', 25, 2),
(3, 'Fiera del Sedano Rapa', '2020-10-02', '2020-10-04', 400, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `KitchenTasks`
--

CREATE TABLE `KitchenTasks` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `idTurn` int(11) DEFAULT NULL,
  `idProcedure` int(11) DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `duration` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `MenuFeatures`
--

CREATE TABLE `MenuFeatures` (
  `menu_id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL DEFAULT '',
  `value` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `MenuFeatures`
--

INSERT INTO `MenuFeatures` (`menu_id`, `name`, `value`) VALUES
(24, 'Richiede cuoco', 1),
(24, 'Buffet', 0),
(24, 'Richiede cucina', 0),
(24, 'Finger food', 1),
(24, 'Piatti caldi', 0),
(25, 'Richiede cuoco', 1),
(25, 'Buffet', 1),
(25, 'Richiede cucina', 1),
(25, 'Finger food', 1),
(25, 'Piatti caldi', 1),
(26, 'Richiede cuoco', 0),
(26, 'Buffet', 0),
(26, 'Richiede cucina', 0),
(26, 'Finger food', 0),
(26, 'Piatti caldi', 0),
(32, 'Richiede cuoco', 0),
(32, 'Buffet', 0),
(32, 'Richiede cucina', 0),
(32, 'Finger food', 0),
(32, 'Piatti caldi', 0),
(39, 'Richiede cuoco', 0),
(39, 'Buffet', 0),
(39, 'Richiede cucina', 0),
(39, 'Finger food', 0),
(39, 'Piatti caldi', 0),
(40, 'Richiede cuoco', 0),
(40, 'Buffet', 0),
(40, 'Richiede cucina', 0),
(40, 'Finger food', 0),
(40, 'Piatti caldi', 0),
(41, 'Richiede cuoco', 0),
(41, 'Buffet', 0),
(41, 'Richiede cucina', 0),
(41, 'Finger food', 0),
(41, 'Piatti caldi', 0),
(45, 'Richiede cuoco', 0),
(45, 'Buffet', 0),
(45, 'Richiede cucina', 0),
(45, 'Finger food', 0),
(45, 'Piatti caldi', 0),
(46, 'Richiede cuoco', 0),
(46, 'Buffet', 0),
(46, 'Richiede cucina', 0),
(46, 'Finger food', 0),
(46, 'Piatti caldi', 0),
(47, 'Richiede cuoco', 0),
(47, 'Buffet', 0),
(47, 'Richiede cucina', 0),
(47, 'Finger food', 0),
(47, 'Piatti caldi', 0),
(48, 'Richiede cuoco', 0),
(48, 'Buffet', 0),
(48, 'Richiede cucina', 0),
(48, 'Finger food', 0),
(48, 'Piatti caldi', 0),
(49, 'Richiede cuoco', 0),
(49, 'Buffet', 0),
(49, 'Richiede cucina', 0),
(49, 'Finger food', 0),
(49, 'Piatti caldi', 0),
(50, 'Richiede cuoco', 0),
(50, 'Buffet', 0),
(50, 'Richiede cucina', 0),
(50, 'Finger food', 0),
(50, 'Piatti caldi', 0),
(55, 'Richiede cuoco', 0),
(55, 'Buffet', 0),
(55, 'Richiede cucina', 0),
(55, 'Finger food', 0),
(55, 'Piatti caldi', 0),
(56, 'Richiede cuoco', 0),
(56, 'Buffet', 0),
(56, 'Richiede cucina', 0),
(56, 'Finger food', 0),
(56, 'Piatti caldi', 0),
(57, 'Richiede cuoco', 0),
(57, 'Buffet', 0),
(57, 'Richiede cucina', 0),
(57, 'Finger food', 0),
(57, 'Piatti caldi', 0),
(58, 'Richiede cuoco', 0),
(58, 'Buffet', 0),
(58, 'Richiede cucina', 0),
(58, 'Finger food', 0),
(58, 'Piatti caldi', 0),
(63, 'Richiede cuoco', 0),
(63, 'Buffet', 0),
(63, 'Richiede cucina', 0),
(63, 'Finger food', 0),
(63, 'Piatti caldi', 0),
(66, 'Richiede cuoco', 0),
(66, 'Buffet', 0),
(66, 'Richiede cucina', 0),
(66, 'Finger food', 0),
(66, 'Piatti caldi', 0),
(68, 'Richiede cuoco', 0),
(68, 'Buffet', 0),
(68, 'Richiede cucina', 0),
(68, 'Finger food', 0),
(68, 'Piatti caldi', 0),
(72, 'Richiede cuoco', 0),
(72, 'Buffet', 0),
(72, 'Richiede cucina', 0),
(72, 'Finger food', 0),
(72, 'Piatti caldi', 0),
(73, 'Richiede cuoco', 0),
(73, 'Buffet', 0),
(73, 'Richiede cucina', 0),
(73, 'Finger food', 0),
(73, 'Piatti caldi', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `MenuItems`
--

CREATE TABLE `MenuItems` (
  `id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `section_id` int(11) DEFAULT NULL,
  `description` tinytext DEFAULT NULL,
  `recipe_id` int(11) NOT NULL,
  `position` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `MenuItems`
--

INSERT INTO `MenuItems` (`id`, `menu_id`, `section_id`, `description`, `recipe_id`, `position`) VALUES
(16, 24, 16, 'Penne del marinaio', 5, 0),
(17, 25, 17, 'Vitello tonnato', 1, 0),
(18, 25, 17, 'Carpaccio di spada', 2, 1),
(19, 25, 17, 'Alici marinate', 3, 2),
(20, 25, 19, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(21, 25, 19, 'Salmone al forno', 8, 1),
(22, 25, 0, 'Insalata di riso', 4, 0),
(23, 25, 0, 'Penne al sugo di baccalà', 5, 1),
(24, 26, 20, 'Vitello tonnato', 1, 0),
(25, 26, 20, 'Carpaccio di spada', 2, 1),
(26, 26, 20, 'Alici marinate', 3, 2),
(27, 26, 22, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(28, 26, 22, 'Salmone al forno', 8, 1),
(29, 26, 0, 'Insalata di riso', 4, 0),
(30, 26, 0, 'Penne al sugo di baccalà', 5, 1),
(66, 32, 38, 'Vitello tonnato', 1, 0),
(67, 32, 38, 'Carpaccio di spada', 2, 1),
(68, 32, 38, 'Alici marinate', 3, 2),
(69, 32, 40, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(70, 32, 40, 'Salmone al forno', 8, 1),
(71, 32, 0, 'Insalata di riso', 4, 0),
(72, 32, 0, 'Penne al sugo di baccalà', 5, 1),
(115, 39, 59, 'Vitello tonnato', 1, 0),
(116, 39, 59, 'Carpaccio di spada', 2, 1),
(117, 39, 59, 'Alici marinate', 3, 2),
(118, 39, 61, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(119, 39, 61, 'Salmone al forno', 8, 1),
(120, 39, 0, 'Insalata di riso', 4, 0),
(121, 39, 0, 'Penne al sugo di baccalà', 5, 1),
(122, 40, 62, 'Vitello tonnato', 1, 0),
(123, 40, 62, 'Carpaccio di spada', 2, 1),
(124, 40, 62, 'Alici marinate', 3, 2),
(125, 40, 64, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(126, 40, 64, 'Salmone al forno', 8, 1),
(127, 40, 0, 'Insalata di riso', 4, 0),
(128, 40, 0, 'Penne al sugo di baccalà', 5, 1),
(129, 41, 65, 'Vitello tonnato', 1, 0),
(130, 41, 65, 'Carpaccio di spada', 2, 1),
(131, 41, 65, 'Alici marinate', 3, 2),
(132, 41, 67, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(133, 41, 67, 'Salmone al forno', 8, 1),
(134, 41, 0, 'Insalata di riso', 4, 0),
(135, 41, 0, 'Penne al sugo di baccalà', 5, 1),
(157, 45, 77, 'Vitello tonnato', 1, 0),
(158, 45, 77, 'Carpaccio di spada', 2, 1),
(159, 45, 77, 'Alici marinate', 3, 2),
(160, 45, 79, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(161, 45, 79, 'Salmone al forno', 8, 1),
(162, 45, 0, 'Insalata di riso', 4, 0),
(163, 45, 0, 'Penne al sugo di baccalà', 5, 1),
(164, 46, 80, 'Vitello tonnato', 1, 0),
(165, 46, 80, 'Carpaccio di spada', 2, 1),
(166, 46, 80, 'Alici marinate', 3, 2),
(167, 46, 82, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(168, 46, 82, 'Salmone al forno', 8, 1),
(169, 46, 0, 'Insalata di riso', 4, 0),
(170, 46, 0, 'Penne al sugo di baccalà', 5, 1),
(171, 47, 83, 'Vitello tonnato', 1, 0),
(172, 47, 83, 'Carpaccio di spada', 2, 1),
(173, 47, 83, 'Alici marinate', 3, 2),
(174, 47, 85, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(175, 47, 85, 'Salmone al forno', 8, 1),
(176, 47, 0, 'Insalata di riso', 4, 0),
(177, 47, 0, 'Penne al sugo di baccalà', 5, 1),
(178, 48, 86, 'Vitello tonnato', 1, 0),
(179, 48, 86, 'Carpaccio di spada', 2, 1),
(180, 48, 86, 'Alici marinate', 3, 2),
(181, 48, 88, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(182, 48, 88, 'Salmone al forno', 8, 1),
(183, 48, 0, 'Insalata di riso', 4, 0),
(184, 48, 0, 'Penne al sugo di baccalà', 5, 1),
(185, 49, 89, 'Vitello tonnato', 1, 0),
(186, 49, 89, 'Carpaccio di spada', 2, 1),
(187, 49, 89, 'Alici marinate', 3, 2),
(188, 49, 91, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(189, 49, 91, 'Salmone al forno', 8, 1),
(190, 49, 0, 'Insalata di riso', 4, 0),
(191, 49, 0, 'Penne al sugo di baccalà', 5, 1),
(192, 50, 92, 'Vitello tonnato', 1, 0),
(193, 50, 92, 'Carpaccio di spada', 2, 1),
(194, 50, 92, 'Alici marinate', 3, 2),
(195, 50, 94, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(196, 50, 94, 'Salmone al forno', 8, 1),
(197, 50, 0, 'Insalata di riso', 4, 0),
(198, 50, 0, 'Penne al sugo di baccalà', 5, 1),
(227, 55, 107, 'Vitello tonnato', 1, 0),
(228, 55, 107, 'Carpaccio di spada', 2, 1),
(229, 55, 107, 'Alici marinate', 3, 2),
(230, 55, 109, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(231, 55, 109, 'Salmone al forno', 8, 1),
(232, 55, 0, 'Insalata di riso', 4, 0),
(233, 55, 0, 'Penne al sugo di baccalà', 5, 1),
(234, 56, 110, 'Vitello tonnato', 1, 0),
(235, 56, 110, 'Carpaccio di spada', 2, 1),
(236, 56, 110, 'Alici marinate', 3, 2),
(237, 56, 112, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(238, 56, 112, 'Salmone al forno', 8, 1),
(239, 56, 0, 'Insalata di riso', 4, 0),
(240, 56, 0, 'Penne al sugo di baccalà', 5, 1),
(241, 57, 113, 'Vitello tonnato', 1, 0),
(242, 57, 113, 'Carpaccio di spada', 2, 1),
(243, 57, 113, 'Alici marinate', 3, 2),
(244, 57, 115, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(245, 57, 115, 'Salmone al forno', 8, 1),
(246, 57, 0, 'Insalata di riso', 4, 0),
(247, 57, 0, 'Penne al sugo di baccalà', 5, 1),
(248, 58, 116, 'Vitello tonnato', 1, 0),
(249, 58, 116, 'Carpaccio di spada', 2, 1),
(250, 58, 116, 'Alici marinate', 3, 2),
(251, 58, 118, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(252, 58, 118, 'Salmone al forno', 8, 1),
(253, 58, 0, 'Insalata di riso', 4, 0),
(254, 58, 0, 'Penne al sugo di baccalà', 5, 1),
(283, 63, 131, 'Vitello tonnato', 1, 0),
(284, 63, 131, 'Carpaccio di spada', 2, 1),
(285, 63, 131, 'Alici marinate', 3, 2),
(286, 63, 133, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(287, 63, 133, 'Salmone al forno', 8, 1),
(288, 63, 0, 'Insalata di riso', 4, 0),
(289, 63, 0, 'Penne al sugo di baccalà', 5, 1),
(304, 66, 140, 'Vitello tonnato', 1, 0),
(305, 66, 140, 'Alici marinate', 3, 1),
(306, 66, 142, 'Salmone al forno', 8, 0),
(307, 66, 0, 'Insalata di riso', 4, 0),
(308, 66, 0, 'Penne al sugo di baccalà', 5, 1),
(314, 68, 146, 'Vitello tonnato', 1, 0),
(315, 68, 146, 'Carpaccio di spada', 2, 1),
(316, 68, 146, 'Alici marinate', 3, 2),
(317, 68, 148, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(318, 68, 148, 'Salmone al forno', 8, 1),
(319, 68, 0, 'Insalata di riso', 4, 0),
(320, 68, 0, 'Penne al sugo di baccalà', 5, 1),
(342, 72, 158, 'Vitello tonnato', 1, 0),
(343, 72, 158, 'Carpaccio di spada', 2, 1),
(344, 72, 160, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(345, 72, 160, 'Salmone al forno', 8, 1),
(346, 72, 0, 'Insalata di riso', 4, 0),
(347, 72, 0, 'Penne al sugo di baccalà', 5, 1),
(348, 73, 161, 'Vitello tonnato', 1, 0),
(349, 73, 161, 'Carpaccio di spada', 2, 1),
(350, 73, 163, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(351, 73, 163, 'Salmone al forno', 8, 1),
(352, 73, 0, 'Insalata di riso', 4, 0),
(353, 73, 0, 'Penne al sugo di baccalà', 5, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `Menus`
--

CREATE TABLE `Menus` (
  `id` int(11) NOT NULL,
  `title` tinytext DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  `published` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `Menus`
--

INSERT INTO `Menus` (`id`, `title`, `owner_id`, `published`) VALUES
(24, 'Menu del disperato', 2, 1),
(25, 'Titolo Nuovo', 2, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `MenuSections`
--

CREATE TABLE `MenuSections` (
  `id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `name` tinytext DEFAULT NULL,
  `position` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `MenuSections`
--

INSERT INTO `MenuSections` (`id`, `menu_id`, `name`, `position`) VALUES
(16, 24, 'Antipasti', 0),
(17, 25, 'Antipasti', 0),
(18, 25, 'Primi', 1),
(19, 25, 'Secondi', 2),
(20, 26, 'Antipasti', 0),
(21, 26, 'Primi', 1),
(22, 26, 'Secondi', 2),
(38, 32, 'Antipasti', 0),
(39, 32, 'Primi', 1),
(40, 32, 'Secondi', 2),
(59, 39, 'Antipasti', 0),
(60, 39, 'Primi', 1),
(61, 39, 'Secondi', 2),
(62, 40, 'Antipasti', 0),
(63, 40, 'Primi', 1),
(64, 40, 'Secondi', 2),
(65, 41, 'Antipasti', 0),
(66, 41, 'Primi', 1),
(67, 41, 'Secondi', 2),
(77, 45, 'Antipasti', 0),
(78, 45, 'Primi', 1),
(79, 45, 'Secondi', 2),
(80, 46, 'Antipasti', 0),
(81, 46, 'Primi', 1),
(82, 46, 'Secondi', 2),
(83, 47, 'Antipasti', 0),
(84, 47, 'Primi', 1),
(85, 47, 'Secondi', 2),
(86, 48, 'Antipasti', 0),
(87, 48, 'Primi', 1),
(88, 48, 'Secondi', 2),
(89, 49, 'Antipasti', 0),
(90, 49, 'Primi', 1),
(91, 49, 'Secondi', 2),
(92, 50, 'Antipasti', 0),
(93, 50, 'Primi', 1),
(94, 50, 'Secondi', 2),
(107, 55, 'Antipasti', 0),
(108, 55, 'Primi', 1),
(109, 55, 'Secondi', 2),
(110, 56, 'Antipasti', 0),
(111, 56, 'Primi', 1),
(112, 56, 'Secondi', 2),
(113, 57, 'Antipasti', 0),
(114, 57, 'Primi', 1),
(115, 57, 'Secondi', 2),
(116, 58, 'Antipasti', 0),
(117, 58, 'Primi', 1),
(118, 58, 'Secondi', 2),
(131, 63, 'Antipasti', 0),
(132, 63, 'Primi', 1),
(133, 63, 'Secondi', 2),
(140, 66, 'Antipasti', 0),
(141, 66, 'Primi', 1),
(142, 66, 'Secondi', 2),
(146, 68, 'Antipasti', 0),
(147, 68, 'Primi', 1),
(148, 68, 'Secondi', 2),
(158, 72, 'Antipasti', 0),
(159, 72, 'Primi', 1),
(160, 72, 'Secondi', 2),
(161, 73, 'Antipasti', 0),
(162, 73, 'Primi', 1),
(163, 73, 'Secondi', 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `Recipes`
--

CREATE TABLE `Recipes` (
  `id` int(11) NOT NULL,
  `name` tinytext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `Recipes`
--

INSERT INTO `Recipes` (`id`, `name`) VALUES
(1, 'Vitello tonnato'),
(2, 'Carpaccio di spada'),
(3, 'Alici marinate'),
(4, 'Insalata di riso'),
(5, 'Penne al sugo di baccalà'),
(6, 'Pappa al pomodoro'),
(7, 'Hamburger con bacon e cipolla caramellata'),
(8, 'Salmone al forno'),
(9, 'Torta Al Cioccolato');

-- --------------------------------------------------------

--
-- Struttura della tabella `Roles`
--

CREATE TABLE `Roles` (
  `id` char(1) NOT NULL,
  `role` varchar(128) NOT NULL DEFAULT 'servizio'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `Roles`
--

INSERT INTO `Roles` (`id`, `role`) VALUES
('c', 'cuoco'),
('h', 'chef'),
('o', 'organizzatore'),
('s', 'servizio');

-- --------------------------------------------------------

--
-- Struttura della tabella `Services`
--

CREATE TABLE `Services` (
  `id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `proposed_menu_id` int(11) NOT NULL DEFAULT 0,
  `approved_menu_id` int(11) DEFAULT 0,
  `service_date` date DEFAULT NULL,
  `time_start` time DEFAULT NULL,
  `time_end` time DEFAULT NULL,
  `expected_participants` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `Services`
--

INSERT INTO `Services` (`id`, `event_id`, `name`, `proposed_menu_id`, `approved_menu_id`, `service_date`, `time_start`, `time_end`, `expected_participants`) VALUES
(1, 2, 'Cena', 0, 25, '2020-08-13', '20:00:00', '23:30:00', 25),
(2, 1, 'Coffee break mattino', 0, 25, '2020-09-25', '10:30:00', '11:30:00', 100),
(3, 1, 'Colazione di lavoro', 0, 25, '2020-09-25', '13:00:00', '14:00:00', 80),
(4, 1, 'Coffee break pomeriggio', 0, 25, '2020-09-25', '16:00:00', '16:30:00', 100),
(5, 1, 'Cena sociale', 0, 25, '2020-09-25', '20:00:00', '22:30:00', 40),
(6, 3, 'Pranzo giorno 1', 0, 25, '2020-10-02', '12:00:00', '15:00:00', 200),
(7, 3, 'Pranzo giorno 2', 0, 25, '2020-10-03', '12:00:00', '15:00:00', 300),
(8, 3, 'Pranzo giorno 3', 0, 25, '2020-10-04', '12:00:00', '15:00:00', 400);

-- --------------------------------------------------------

--
-- Struttura della tabella `ToDoLists`
--

CREATE TABLE `ToDoLists` (
  `idService` int(11) NOT NULL,
  `idTask` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura della tabella `Turns`
--

CREATE TABLE `Turns` (
  `id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `Turns`
--

INSERT INTO `Turns` (`id`) VALUES
(1),
(2),
(3);

-- --------------------------------------------------------

--
-- Struttura della tabella `UserRoles`
--

CREATE TABLE `UserRoles` (
  `user_id` int(11) NOT NULL,
  `role_id` char(1) NOT NULL DEFAULT 's'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `UserRoles`
--

INSERT INTO `UserRoles` (`user_id`, `role_id`) VALUES
(1, 'o'),
(2, 'o'),
(2, 'h'),
(3, 'h'),
(4, 'h'),
(4, 'c'),
(5, 'c'),
(6, 'c'),
(7, 'c'),
(8, 's'),
(9, 's'),
(10, 's'),
(7, 's');

-- --------------------------------------------------------

--
-- Struttura della tabella `Users`
--

CREATE TABLE `Users` (
  `id` int(11) NOT NULL,
  `username` varchar(128) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `Users`
--

INSERT INTO `Users` (`id`, `username`) VALUES
(1, 'Carlin'),
(2, 'Lidia'),
(3, 'Tony'),
(4, 'Marinella'),
(5, 'Guido'),
(6, 'Antonietta'),
(7, 'Paola'),
(8, 'Silvia'),
(9, 'Marco'),
(10, 'Piergiorgio');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `Events`
--
ALTER TABLE `Events`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `KitchenTasks`
--
ALTER TABLE `KitchenTasks`
  ADD UNIQUE KEY `id` (`id`);

--
-- Indici per le tabelle `MenuItems`
--
ALTER TABLE `MenuItems`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `Menus`
--
ALTER TABLE `Menus`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `MenuSections`
--
ALTER TABLE `MenuSections`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `Recipes`
--
ALTER TABLE `Recipes`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `Roles`
--
ALTER TABLE `Roles`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `Services`
--
ALTER TABLE `Services`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `Turns`
--
ALTER TABLE `Turns`
  ADD UNIQUE KEY `id` (`id`);

--
-- Indici per le tabelle `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `Events`
--
ALTER TABLE `Events`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `KitchenTasks`
--
ALTER TABLE `KitchenTasks`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=277;

--
-- AUTO_INCREMENT per la tabella `MenuItems`
--
ALTER TABLE `MenuItems`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=378;

--
-- AUTO_INCREMENT per la tabella `Menus`
--
ALTER TABLE `Menus`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;

--
-- AUTO_INCREMENT per la tabella `MenuSections`
--
ALTER TABLE `MenuSections`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=176;

--
-- AUTO_INCREMENT per la tabella `Recipes`
--
ALTER TABLE `Recipes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT per la tabella `Services`
--
ALTER TABLE `Services`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT per la tabella `Turns`
--
ALTER TABLE `Turns`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `Users`
--
ALTER TABLE `Users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
