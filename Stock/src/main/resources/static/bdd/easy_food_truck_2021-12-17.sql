# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Hôte: localhost (MySQL 5.7.25)
# Base de données: easy_food_truck
# Temps de génération: 2021-12-17 11:13:09 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Affichage de la table estimations
# ------------------------------------------------------------

DROP TABLE IF EXISTS `estimations`;

CREATE TABLE `estimations` (
  `id_estimation` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  PRIMARY KEY (`id_estimation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `estimations` WRITE;
/*!40000 ALTER TABLE `estimations` DISABLE KEYS */;

INSERT INTO `estimations` (`id_estimation`, `date`)
VALUES
	(1,'2010-12-10'),
	(12,'2022-01-10');

/*!40000 ALTER TABLE `estimations` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table ingredients
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ingredients`;

CREATE TABLE `ingredients` (
  `id_ingredient` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `description` varchar(150) NOT NULL,
  `stock` int(11) NOT NULL,
  `id_unit` int(11) NOT NULL,
  `division` int(11) NOT NULL,
  PRIMARY KEY (`id_ingredient`),
  KEY `id_unit` (`id_unit`),
  CONSTRAINT `ingredients_ibfk_1` FOREIGN KEY (`id_unit`) REFERENCES `units` (`id_unit`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ingredients_ibfk_2` FOREIGN KEY (`id_unit`) REFERENCES `units` (`id_unit`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ingredients_ibfk_3` FOREIGN KEY (`id_unit`) REFERENCES `units` (`id_unit`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `ingredients` WRITE;
/*!40000 ALTER TABLE `ingredients` DISABLE KEYS */;

INSERT INTO `ingredients` (`id_ingredient`, `name`, `description`, `stock`, `id_unit`, `division`)
VALUES
	(20,'tomates','rondelle de tomate vendu par 100g',10,1,100),
	(21,'poulet','morceau de poulet vendu par 100g',12,1,100),
	(22,'salade','morceau de salade vendu par 100g',3,1,100),
	(23,'oeuf','boite oeuf vendu par 12',10,3,12),
	(25,'pack_coca','pack de coca, 1L * 6',0,3,6),
	(26,'pack_ice_tea','pack de ice tea, 1L * 6',10,3,6);

/*!40000 ALTER TABLE `ingredients` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table menu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `id_menu` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '',
  `description` varchar(200) DEFAULT '',
  PRIMARY KEY (`id_menu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;

INSERT INTO `menu` (`id_menu`, `name`, `description`)
VALUES
	(1,'menu_etudiant','menu à 5€'),
	(7,'menu_enfant','menu à 5€'),
	(11,'menu_complet','menu à 5€');

/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table menu_estimation
# ------------------------------------------------------------

DROP TABLE IF EXISTS `menu_estimation`;

CREATE TABLE `menu_estimation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_menu` int(11) NOT NULL,
  `id_estimation` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_estimation` (`id_estimation`),
  KEY `id_menu` (`id_menu`),
  CONSTRAINT `menu_estimation_ibfk_1` FOREIGN KEY (`id_menu`) REFERENCES `menu` (`id_menu`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `menu_estimation_ibfk_2` FOREIGN KEY (`id_estimation`) REFERENCES `estimations` (`id_estimation`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `menu_estimation` WRITE;
/*!40000 ALTER TABLE `menu_estimation` DISABLE KEYS */;

INSERT INTO `menu_estimation` (`id`, `id_menu`, `id_estimation`, `quantity`)
VALUES
	(5,7,12,10),
	(6,11,12,5),
	(7,7,1,1);

/*!40000 ALTER TABLE `menu_estimation` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table menu_product
# ------------------------------------------------------------

DROP TABLE IF EXISTS `menu_product`;

CREATE TABLE `menu_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_menu` int(11) NOT NULL,
  `id_product` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_ingredient` (`id_product`),
  KEY `id_menu` (`id_menu`),
  CONSTRAINT `menu_product_ibfk_1` FOREIGN KEY (`id_menu`) REFERENCES `menu` (`id_menu`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `menu_product_ibfk_2` FOREIGN KEY (`id_product`) REFERENCES `products` (`id_product`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `menu_product` WRITE;
/*!40000 ALTER TABLE `menu_product` DISABLE KEYS */;

INSERT INTO `menu_product` (`id`, `id_menu`, `id_product`)
VALUES
	(1,7,21),
	(2,7,31),
	(3,1,27),
	(6,11,21),
	(7,11,31);

/*!40000 ALTER TABLE `menu_product` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table product_estimation
# ------------------------------------------------------------

DROP TABLE IF EXISTS `product_estimation`;

CREATE TABLE `product_estimation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_product` int(11) NOT NULL,
  `id_estimation` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_estimation` (`id_estimation`),
  KEY `id_produit` (`id_product`),
  CONSTRAINT `product_estimation_ibfk_1` FOREIGN KEY (`id_product`) REFERENCES `products` (`id_product`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `product_estimation_ibfk_2` FOREIGN KEY (`id_estimation`) REFERENCES `estimations` (`id_estimation`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `product_estimation` WRITE;
/*!40000 ALTER TABLE `product_estimation` DISABLE KEYS */;

INSERT INTO `product_estimation` (`id`, `id_product`, `id_estimation`, `quantity`)
VALUES
	(1,21,12,25),
	(2,31,12,30),
	(3,21,1,10);

/*!40000 ALTER TABLE `product_estimation` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table products
# ------------------------------------------------------------

DROP TABLE IF EXISTS `products`;

CREATE TABLE `products` (
  `id_product` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '',
  `description` varchar(250) DEFAULT NULL,
  `id_type` int(11) NOT NULL,
  PRIMARY KEY (`id_product`),
  KEY `id_type` (`id_type`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`id_type`) REFERENCES `type` (`id_type`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;

INSERT INTO `products` (`id_product`, `name`, `description`, `id_type`)
VALUES
	(21,'kebab-poulet-tomate','kebab poulet tomate',1),
	(22,'kebab-poulet','kebab poulet tomate',1),
	(27,'omelette','omelette nature',1),
	(31,'coca','bouteille de coca 1L',2),
	(39,'ice_tea','bouteille de d ice_tea 1L',2);

/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table products_ingredients
# ------------------------------------------------------------

DROP TABLE IF EXISTS `products_ingredients`;

CREATE TABLE `products_ingredients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_product` int(11) NOT NULL,
  `id_ingredient` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_plat` (`id_product`),
  KEY `id_ingredient` (`id_ingredient`),
  CONSTRAINT `products_ingredients_ibfk_1` FOREIGN KEY (`id_product`) REFERENCES `products` (`id_product`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `products_ingredients_ibfk_2` FOREIGN KEY (`id_ingredient`) REFERENCES `ingredients` (`id_ingredient`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `products_ingredients` WRITE;
/*!40000 ALTER TABLE `products_ingredients` DISABLE KEYS */;

INSERT INTO `products_ingredients` (`id`, `id_product`, `id_ingredient`, `quantity`)
VALUES
	(28,39,26,1),
	(29,31,25,1),
	(30,21,21,100);

/*!40000 ALTER TABLE `products_ingredients` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table type
# ------------------------------------------------------------

DROP TABLE IF EXISTS `type`;

CREATE TABLE `type` (
  `id_type` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`id_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;

INSERT INTO `type` (`id_type`, `name`)
VALUES
	(1,'meal'),
	(2,'drink'),
	(3,'dessert');

/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;


# Affichage de la table units
# ------------------------------------------------------------

DROP TABLE IF EXISTS `units`;

CREATE TABLE `units` (
  `id_unit` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL DEFAULT '',
  PRIMARY KEY (`id_unit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `units` WRITE;
/*!40000 ALTER TABLE `units` DISABLE KEYS */;

INSERT INTO `units` (`id_unit`, `name`)
VALUES
	(1,'g'),
	(2,'l'),
	(3,'unitary');

/*!40000 ALTER TABLE `units` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
