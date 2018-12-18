DROP DATABASE IF EXISTS FEFE;
CREATE DATABASE FEFE;
USE FEFE;

CREATE TABLE userType (
 userTypeID     TINYINT UNSIGNED AUTO_INCREMENT,
 uType             VARCHAR(20) NOT NULL,
 description             VARCHAR(20),
 CONSTRAINT USERT_PK PRIMARY KEY (userTypeID)
);
CREATE TABLE user (
 userID          smallint UNSIGNED AUTO_INCREMENT,
 uName               VARCHAR(25)    UNIQUE NOT NULL,
 password              VARCHAR(25)     NOT NULL,
 rName              VARCHAR(25)     NOT NULL,
 surname              VARCHAR(25)     NOT NULL,
 mail              VARCHAR(45)     NOT NULL,
 bornDat                VARCHAR(10),
 userTypeID     TINYINT UNSIGNED,
 CONSTRAINT USER_PK PRIMARY KEY (userID),
 CONSTRAINT USER_TYP FOREIGN KEY (userTypeID) REFERENCES userType (userTypeID)
);
CREATE TABLE departament (
 departamentID     TINYINT UNSIGNED,
 depName             VARCHAR(20) NOT NULL,
 description             VARCHAR(20),
 CONSTRAINT DEPT_PK PRIMARY KEY (departamentID)
);
CREATE TABLE product (
 productID     smallint UNSIGNED AUTO_INCREMENT,
 prodName             VARCHAR(20) NOT NULL,
 price     TINYINT UNSIGNED,
 departamentID     TINYINT UNSIGNED,
 CONSTRAINT PROD_PK PRIMARY KEY (productID),
 CONSTRAINT DEPT_PK FOREIGN KEY (departamentID) REFERENCES departament (departamentID)
);
CREATE TABLE orderT (
 orderID     smallint UNSIGNED AUTO_INCREMENT,
 refD               VARCHAR(10),
 userId       smallint UNSIGNED,
 CONSTRAINT ORD_PK PRIMARY KEY (orderID),
 CONSTRAINT USR_FK FOREIGN KEY (userId) REFERENCES user (userId)
 );
CREATE TABLE orderProduct (
 orderProductId smallint UNSIGNED AUTO_INCREMENT,
 productID     smallint UNSIGNED,
 orderId       smallint UNSIGNED,
 quantity       smallint UNSIGNED,
 CONSTRAINT ORDPRD_PK PRIMARY KEY (orderProductId),
 CONSTRAINT ORD_FK FOREIGN KEY (orderId) REFERENCES orderT (orderId),
 CONSTRAINT PRD_FK FOREIGN KEY (productID) REFERENCES product (productID)
);
CREATE TABLE segment (
 segmentID     TINYINT UNSIGNED AUTO_INCREMENT,
 segment             VARCHAR(20) NOT NULL,
 posX SMALLINT NOT NULL ,
 posY SMALLINT NOT NULL ,
 description             VARCHAR(20),
 CONSTRAINT SEGMENT_PK PRIMARY KEY (segmentId)
);
CREATE TABLE authoMach (
 machineID     TINYINT UNSIGNED AUTO_INCREMENT,
 machine             VARCHAR(20) NOT NULL,
 description             VARCHAR(20),
 segmentID     TINYINT UNSIGNED, 
 state         BOOLEAN, /*Hutsik o beteta*/
 CONSTRAINT MACH_PK PRIMARY KEY (machineID),
 CONSTRAINT SEGMA_FK FOREIGN KEY (segmentID) REFERENCES segment (segmentID)
);
CREATE TABLE workstation (
 workstationID     TINYINT UNSIGNED AUTO_INCREMENT,
 workstationNam             VARCHAR(20) NOT NULL,
 description             VARCHAR(20),
 segmentID     TINYINT UNSIGNED,
 state         BOOLEAN, /*Hutsik o beteta*/
 CONSTRAINT workS_PK PRIMARY KEY (workstationID),
 CONSTRAINT SEGMW_FK FOREIGN KEY (segmentID) REFERENCES segment (segmentID)
);
CREATE TABLE carries (
 orderProductId smallint UNSIGNED AUTO_INCREMENT,
 initialWorkstationID     tinyint UNSIGNED,
 destinyWorkstationID     tinyint UNSIGNED,
 machineID       tinyint UNSIGNED,
 state          boolean,/*eraman duen o ez*/
 CONSTRAINT ORDPRD_FK FOREIGN KEY (orderProductId) REFERENCES orderProduct (orderProductId),
 CONSTRAINT WORK_IN_FK FOREIGN KEY (initialWorkstationID) REFERENCES workstation (workstationID),
 CONSTRAINT WORK_FIN_FK FOREIGN KEY (destinyWorkstationID) REFERENCES workstation (workstationID),
 CONSTRAINT MACH_FK FOREIGN KEY (machineID) REFERENCES authoMach (machineID)
);
/* Values*/
INSERT INTO userType VALUES (1,'Customer','Can buy');
INSERT INTO userType VALUES (2,'Operator','Operates');
INSERT INTO userType VALUES (3,'Manager','Manages');

INSERT INTO user VALUES (1,'Manexzini','eskola','Manex','Bengoa','manex@manex.com','1998-05-25',1);

INSERT INTO departament VALUES (1,'Electronic',NULL);

INSERT INTO orderT VALUES (1,'2018-12-09',1);

INSERT INTO product VALUES (1,'Nokia 112',12,1);
INSERT INTO product VALUES (2,'Movember',121,1);

INSERT INTO orderProduct VALUES (1,1,1,2);
INSERT INTO orderProduct VALUES (2,2,1,2);

INSERT INTO segment VALUES (2,'Zigarro sueltuak',1,1,NULL);

INSERT INTO authoMach VALUES (2,'Petanca izar',NULL,2,0);
  


INSERT INTO workstation VALUES (2,'Zigarro sueltuak',NULL,2,1);

INSERT INTO carries VALUES (1,2,2,1);


