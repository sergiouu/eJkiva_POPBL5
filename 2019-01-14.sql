-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.6.39 - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para mydatabase
CREATE DATABASE IF NOT EXISTS `mydatabase` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `mydatabase`;

-- Volcando estructura para tabla mydatabase.authomach
CREATE TABLE IF NOT EXISTS `authomach` (
  `machineID` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `machine` varchar(20) NOT NULL,
  `description` tinytext,
  `segmentID` tinyint(3) unsigned DEFAULT NULL,
  `state` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`machineID`),
  KEY `SEGMA_FK` (`segmentID`),
  CONSTRAINT `SEGMA_FK` FOREIGN KEY (`segmentID`) REFERENCES `segment` (`segmentID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla mydatabase.authomach: ~1 rows (aproximadamente)
DELETE FROM `authomach`;
/*!40000 ALTER TABLE `authomach` DISABLE KEYS */;
INSERT INTO `authomach` (`machineID`, `machine`, `description`, `segmentID`, `state`) VALUES
	(3, 'Petanca izar', NULL, 2, 0);
/*!40000 ALTER TABLE `authomach` ENABLE KEYS */;

-- Volcando estructura para tabla mydatabase.carries
CREATE TABLE IF NOT EXISTS `carries` (
  `orderProductID` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `initialWorkstationID` tinyint(3) unsigned DEFAULT NULL,
  `destinyWorkstationID` tinyint(3) unsigned DEFAULT NULL,
  `machineID` tinyint(3) unsigned DEFAULT NULL,
  `state` tinyint(1) DEFAULT NULL,
  KEY `ORDPRD_FK` (`orderProductID`),
  KEY `WORK_IN_FK` (`initialWorkstationID`),
  KEY `WORK_FIN_FK` (`destinyWorkstationID`),
  KEY `MACH_FK` (`machineID`),
  CONSTRAINT `MACH_FK` FOREIGN KEY (`machineID`) REFERENCES `authomach` (`machineID`),
  CONSTRAINT `ORDPRD_FK` FOREIGN KEY (`orderProductID`) REFERENCES `orderproduct` (`orderProductID`),
  CONSTRAINT `WORK_FIN_FK` FOREIGN KEY (`destinyWorkstationID`) REFERENCES `workstation` (`workstationID`),
  CONSTRAINT `WORK_IN_FK` FOREIGN KEY (`initialWorkstationID`) REFERENCES `workstation` (`workstationID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla mydatabase.carries: ~0 rows (aproximadamente)
DELETE FROM `carries`;
/*!40000 ALTER TABLE `carries` DISABLE KEYS */;
/*!40000 ALTER TABLE `carries` ENABLE KEYS */;

-- Volcando estructura para tabla mydatabase.departament
CREATE TABLE IF NOT EXISTS `departament` (
  `departamentID` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `departament_name` varchar(20) NOT NULL,
  `description` tinytext,
  PRIMARY KEY (`departamentID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla mydatabase.departament: ~4 rows (aproximadamente)
DELETE FROM `departament`;
/*!40000 ALTER TABLE `departament` DISABLE KEYS */;
INSERT INTO `departament` (`departamentID`, `departament_name`, `description`) VALUES
	(1, 'Electronic', 'Electronic equipment'),
	(2, 'Sport', 'Sport equipment'),
	(3, 'Music', 'CDs, merchandise'),
	(4, 'Books', 'Book collection');
/*!40000 ALTER TABLE `departament` ENABLE KEYS */;

-- Volcando estructura para tabla mydatabase.order
CREATE TABLE IF NOT EXISTS `order` (
  `orderID` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `userID` tinyint(3) unsigned DEFAULT NULL,
  PRIMARY KEY (`orderID`),
  KEY `USR_FK` (`userID`),
  CONSTRAINT `USR_FK` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla mydatabase.order: ~2 rows (aproximadamente)
DELETE FROM `order`;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` (`orderID`, `date`, `userID`) VALUES
	(2, '2018-12-09', 1),
	(3, '2019-01-09', 1);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;

-- Volcando estructura para tabla mydatabase.orderproduct
CREATE TABLE IF NOT EXISTS `orderproduct` (
  `orderProductID` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `productID` tinyint(3) unsigned NOT NULL,
  `orderID` tinyint(3) unsigned NOT NULL,
  `quantity` smallint(5) unsigned NOT NULL,
  PRIMARY KEY (`orderProductID`),
  KEY `ORD_FK` (`orderID`),
  KEY `PRD_FK` (`productID`),
  CONSTRAINT `ORD_FK` FOREIGN KEY (`orderID`) REFERENCES `order` (`orderID`),
  CONSTRAINT `PRD_FK` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla mydatabase.orderproduct: ~2 rows (aproximadamente)
DELETE FROM `orderproduct`;
/*!40000 ALTER TABLE `orderproduct` DISABLE KEYS */;
INSERT INTO `orderproduct` (`orderProductID`, `productID`, `orderID`, `quantity`) VALUES
	(3, 1, 1, 2),
	(4, 2, 1, 2);
/*!40000 ALTER TABLE `orderproduct` ENABLE KEYS */;

-- Volcando estructura para tabla mydatabase.product
CREATE TABLE IF NOT EXISTS `product` (
  `productID` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `product_name` varchar(20) NOT NULL,
  `description` tinytext,
  `price` float unsigned NOT NULL,
  `departamentID` tinyint(3) unsigned DEFAULT NULL,
  PRIMARY KEY (`productID`),
  KEY `DEPT_PK` (`departamentID`),
  CONSTRAINT `DEPT_PK` FOREIGN KEY (`departamentID`) REFERENCES `departament` (`departamentID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla mydatabase.product: ~10 rows (aproximadamente)
DELETE FROM `product`;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`productID`, `product_name`, `description`, `price`, `departamentID`) VALUES
	(3, 'Nokia 112', 'Patatofonoa', 12, 1),
	(4, 'Samsung SWO2000', 'Punta puntako teknologiya', 500, 1),
	(5, 'Katu hankak', 'Eskalatzeko katu hankak', 15.8, 2),
	(6, 'CD Negu Gorriak', 'Klasiko bat', 15, 3),
	(7, 'Txomin komunean', 'Txominen esperientziak komunean', 10.5, 4),
	(8, 'Magnesioa', 'Eskalatzeko magnesioa', 7.9, 2),
	(9, 'Iphone 3', 'Modaz ta prezioz pasauta', 100, 1),
	(10, 'Brigade Loco kamixet', 'Manexen talde faboritoa', 10, 3),
	(11, 'Capitalismo en crisi', 'Gia erabilgarri bat', 15.8, 4),
	(12, 'Arnesa', 'Eskalatzeko arnesa', 40.6, 2);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

-- Volcando estructura para tabla mydatabase.segment
CREATE TABLE IF NOT EXISTS `segment` (
  `segmentID` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `segment` varchar(20) NOT NULL,
  `posX` smallint(6) NOT NULL,
  `posY` smallint(6) NOT NULL,
  `description` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`segmentID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla mydatabase.segment: ~1 rows (aproximadamente)
DELETE FROM `segment`;
/*!40000 ALTER TABLE `segment` DISABLE KEYS */;
INSERT INTO `segment` (`segmentID`, `segment`, `posX`, `posY`, `description`) VALUES
	(3, 'Zigarro sueltuak', 1, 1, NULL);
/*!40000 ALTER TABLE `segment` ENABLE KEYS */;

-- Volcando estructura para tabla mydatabase.user
CREATE TABLE IF NOT EXISTS `user` (
  `userID` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `name` varchar(25) NOT NULL,
  `surname` varchar(25) NOT NULL,
  `mail` varchar(45) NOT NULL,
  `bornDat` date DEFAULT NULL,
  `usertypeID` tinyint(3) unsigned DEFAULT '1',
  PRIMARY KEY (`userID`),
  UNIQUE KEY `username` (`username`),
  KEY `USER_TYP` (`usertypeID`),
  CONSTRAINT `USER_TYP` FOREIGN KEY (`usertypeID`) REFERENCES `usertype` (`usertypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla mydatabase.user: ~5 rows (aproximadamente)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`userID`, `username`, `password`, `name`, `surname`, `mail`, `bornDat`, `usertypeID`) VALUES
	(10, 'Admin', 'admin', 'admin', 'admin', 'admin@admin.com', '0000-00-00', 1),
	(11, 'Manexzini', 'eskola', 'Manex', 'Bengoa', 'manex@manex.com', '1998-05-25', 1),
	(12, 'newUser', 'eskola', 'User', 'New', 'new@user.com', '0000-00-00', 1),
	(13, 'manager', 'eskola', 'M', 'M', 'M', '0000-00-00', 3),
	(14, 'operator', 'eskola', 'O', 'O', 'O', '0000-00-00', 2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Volcando estructura para tabla mydatabase.usertype
CREATE TABLE IF NOT EXISTS `usertype` (
  `usertypeID` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `usertype` varchar(20) NOT NULL,
  `description` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`usertypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla mydatabase.usertype: ~3 rows (aproximadamente)
DELETE FROM `usertype`;
/*!40000 ALTER TABLE `usertype` DISABLE KEYS */;
INSERT INTO `usertype` (`usertypeID`, `usertype`, `description`) VALUES
	(1, 'Customer', 'Can buy'),
	(2, 'Operator', 'Operates'),
	(3, 'Manager', 'Manages');
/*!40000 ALTER TABLE `usertype` ENABLE KEYS */;

-- Volcando estructura para tabla mydatabase.workstation
CREATE TABLE IF NOT EXISTS `workstation` (
  `workstationID` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `workstationNam` varchar(20) NOT NULL,
  `description` varchar(20) DEFAULT NULL,
  `segmentID` tinyint(3) unsigned DEFAULT NULL,
  `state` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`workstationID`),
  KEY `SEGMW_FK` (`segmentID`),
  CONSTRAINT `SEGMW_FK` FOREIGN KEY (`segmentID`) REFERENCES `segment` (`segmentID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla mydatabase.workstation: ~1 rows (aproximadamente)
DELETE FROM `workstation`;
/*!40000 ALTER TABLE `workstation` DISABLE KEYS */;
INSERT INTO `workstation` (`workstationID`, `workstationNam`, `description`, `segmentID`, `state`) VALUES
	(3, 'Zigarro sueltuak', NULL, 2, 1);
/*!40000 ALTER TABLE `workstation` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
