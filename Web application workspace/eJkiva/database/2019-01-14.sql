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
DROP DATABASE IF EXISTS `mydatabase`;
CREATE DATABASE IF NOT EXISTS `mydatabase` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `mydatabase`;

-- Volcando estructura para tabla mydatabase.authomach
DROP TABLE IF EXISTS `authomach`;
CREATE TABLE IF NOT EXISTS `authomach` (
  `machineID` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `machine` varchar(20) NOT NULL,
  `description` varchar(20) DEFAULT NULL,
  `segmentID` tinyint(3) unsigned DEFAULT NULL,
  `state` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`machineID`),
  KEY `SEGMA_FK` (`segmentID`),
  CONSTRAINT `SEGMA_FK` FOREIGN KEY (`segmentID`) REFERENCES `segment` (`segmentID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla mydatabase.authomach: ~1 rows (aproximadamente)
DELETE FROM `authomach`;
/*!40000 ALTER TABLE `authomach` DISABLE KEYS */;
INSERT INTO `authomach` (`machineID`, `machine`, `description`, `segmentID`, `state`) VALUES
	(2, 'Petanca izar', NULL, 2, 0);
/*!40000 ALTER TABLE `authomach` ENABLE KEYS */;

-- Volcando estructura para tabla mydatabase.carries
DROP TABLE IF EXISTS `carries`;
CREATE TABLE IF NOT EXISTS `carries` (
  `orderProductId` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `initialWorkstationID` tinyint(3) unsigned DEFAULT NULL,
  `destinyWorkstationID` tinyint(3) unsigned DEFAULT NULL,
  `machineID` tinyint(3) unsigned DEFAULT NULL,
  `state` tinyint(1) DEFAULT NULL,
  KEY `ORDPRD_FK` (`orderProductId`),
  KEY `WORK_IN_FK` (`initialWorkstationID`),
  KEY `WORK_FIN_FK` (`destinyWorkstationID`),
  KEY `MACH_FK` (`machineID`),
  CONSTRAINT `MACH_FK` FOREIGN KEY (`machineID`) REFERENCES `authomach` (`machineID`),
  CONSTRAINT `ORDPRD_FK` FOREIGN KEY (`orderProductId`) REFERENCES `orderproduct` (`orderProductId`),
  CONSTRAINT `WORK_FIN_FK` FOREIGN KEY (`destinyWorkstationID`) REFERENCES `workstation` (`workstationID`),
  CONSTRAINT `WORK_IN_FK` FOREIGN KEY (`initialWorkstationID`) REFERENCES `workstation` (`workstationID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla mydatabase.carries: ~0 rows (aproximadamente)
DELETE FROM `carries`;
/*!40000 ALTER TABLE `carries` DISABLE KEYS */;
/*!40000 ALTER TABLE `carries` ENABLE KEYS */;

-- Volcando estructura para tabla mydatabase.departament
DROP TABLE IF EXISTS `departament`;
CREATE TABLE IF NOT EXISTS `departament` (
  `departamentID` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `depName` varchar(20) NOT NULL,
  `description` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`departamentID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla mydatabase.departament: ~1 rows (aproximadamente)
DELETE FROM `departament`;
/*!40000 ALTER TABLE `departament` DISABLE KEYS */;
INSERT INTO `departament` (`departamentID`, `depName`, `description`) VALUES
	(1, 'Electronic', NULL);
/*!40000 ALTER TABLE `departament` ENABLE KEYS */;

-- Volcando estructura para tabla mydatabase.order
DROP TABLE IF EXISTS `order`;
CREATE TABLE IF NOT EXISTS `order` (
  `orderID` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `refD` varchar(10) DEFAULT NULL,
  `userId` smallint(5) unsigned DEFAULT NULL,
  PRIMARY KEY (`orderID`),
  KEY `USR_FK` (`userId`),
  CONSTRAINT `USR_FK` FOREIGN KEY (`userId`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla mydatabase.order: ~1 rows (aproximadamente)
DELETE FROM `order`;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` (`orderID`, `refD`, `userId`) VALUES
	(1, '2018-12-09', 1);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;

-- Volcando estructura para tabla mydatabase.orderproduct
DROP TABLE IF EXISTS `orderproduct`;
CREATE TABLE IF NOT EXISTS `orderproduct` (
  `orderProductId` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `productID` smallint(5) unsigned DEFAULT NULL,
  `orderId` smallint(5) unsigned DEFAULT NULL,
  `quantity` smallint(5) unsigned DEFAULT NULL,
  PRIMARY KEY (`orderProductId`),
  KEY `ORD_FK` (`orderId`),
  KEY `PRD_FK` (`productID`),
  CONSTRAINT `ORD_FK` FOREIGN KEY (`orderId`) REFERENCES `order` (`orderID`),
  CONSTRAINT `PRD_FK` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla mydatabase.orderproduct: ~2 rows (aproximadamente)
DELETE FROM `orderproduct`;
/*!40000 ALTER TABLE `orderproduct` DISABLE KEYS */;
INSERT INTO `orderproduct` (`orderProductId`, `productID`, `orderId`, `quantity`) VALUES
	(1, 1, 1, 2),
	(2, 2, 1, 2);
/*!40000 ALTER TABLE `orderproduct` ENABLE KEYS */;

-- Volcando estructura para tabla mydatabase.product
DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `productID` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `prodName` varchar(20) NOT NULL,
  `price` tinyint(3) unsigned DEFAULT NULL,
  `departamentID` tinyint(3) unsigned DEFAULT NULL,
  PRIMARY KEY (`productID`),
  KEY `DEPT_PK` (`departamentID`),
  CONSTRAINT `DEPT_PK` FOREIGN KEY (`departamentID`) REFERENCES `departament` (`departamentID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla mydatabase.product: ~2 rows (aproximadamente)
DELETE FROM `product`;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`productID`, `prodName`, `price`, `departamentID`) VALUES
	(1, 'Nokia 112', 12, 1),
	(2, 'Movember', 121, 1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

-- Volcando estructura para tabla mydatabase.segment
DROP TABLE IF EXISTS `segment`;
CREATE TABLE IF NOT EXISTS `segment` (
  `segmentID` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `segment` varchar(20) NOT NULL,
  `posX` smallint(6) NOT NULL,
  `posY` smallint(6) NOT NULL,
  `description` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`segmentID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla mydatabase.segment: ~1 rows (aproximadamente)
DELETE FROM `segment`;
/*!40000 ALTER TABLE `segment` DISABLE KEYS */;
INSERT INTO `segment` (`segmentID`, `segment`, `posX`, `posY`, `description`) VALUES
	(2, 'Zigarro sueltuak', 1, 1, NULL);
/*!40000 ALTER TABLE `segment` ENABLE KEYS */;

-- Volcando estructura para tabla mydatabase.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `userID` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `uName` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `rName` varchar(25) NOT NULL,
  `surname` varchar(25) NOT NULL,
  `mail` varchar(45) NOT NULL,
  `bornDat` varchar(10) DEFAULT NULL,
  `userTypeID` tinyint(3) unsigned DEFAULT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `uName` (`uName`),
  KEY `USER_TYP` (`userTypeID`),
  CONSTRAINT `USER_TYP` FOREIGN KEY (`userTypeID`) REFERENCES `usertype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla mydatabase.user: ~8 rows (aproximadamente)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`userID`, `uName`, `password`, `rName`, `surname`, `mail`, `bornDat`, `userTypeID`) VALUES
	(0, 'Admin', 'admin', 'admin', 'admin', 'admin@admin.com', '', 1),
	(1, 'Manexzini', 'eskola', 'Manex', 'Bengoa', 'manex@manex.com', '1998-05-25', 1),
	(2, 'newUser', 'eskola', 'User', 'New', 'new@user.com', 'aldatu', 1),
	(3, 'manager', 'eskola', 'M', 'M', 'M', 'M', 3),
	(4, 'operator', 'eskola', 'O', 'O', 'O', 'O', 2),
	(5, 'new', 'ew', 'ew', 'ew', 'ew', NULL, NULL),
	(6, 'lei', 'eskola', 'lei', 'lei', 'lei@lei.com', '2014-04-04', 1),
	(9, 'leire', 'eskola', 'leire', 'leire', 'lei@lei.com', '2014-04-04', 1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Volcando estructura para tabla mydatabase.usertype
DROP TABLE IF EXISTS `usertype`;
CREATE TABLE IF NOT EXISTS `usertype` (
  `id` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `uType` varchar(20) NOT NULL,
  `description` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla mydatabase.usertype: ~3 rows (aproximadamente)
DELETE FROM `usertype`;
/*!40000 ALTER TABLE `usertype` DISABLE KEYS */;
INSERT INTO `usertype` (`id`, `uType`, `description`) VALUES
	(1, 'Customer', 'Can buy'),
	(2, 'Operator', 'Operates'),
	(3, 'Manager', 'Manages');
/*!40000 ALTER TABLE `usertype` ENABLE KEYS */;

-- Volcando estructura para tabla mydatabase.workstation
DROP TABLE IF EXISTS `workstation`;
CREATE TABLE IF NOT EXISTS `workstation` (
  `workstationID` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `workstationNam` varchar(20) NOT NULL,
  `description` varchar(20) DEFAULT NULL,
  `segmentID` tinyint(3) unsigned DEFAULT NULL,
  `state` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`workstationID`),
  KEY `SEGMW_FK` (`segmentID`),
  CONSTRAINT `SEGMW_FK` FOREIGN KEY (`segmentID`) REFERENCES `segment` (`segmentID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla mydatabase.workstation: ~1 rows (aproximadamente)
DELETE FROM `workstation`;
/*!40000 ALTER TABLE `workstation` DISABLE KEYS */;
INSERT INTO `workstation` (`workstationID`, `workstationNam`, `description`, `segmentID`, `state`) VALUES
	(2, 'Zigarro sueltuak', NULL, 2, 1);
/*!40000 ALTER TABLE `workstation` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
