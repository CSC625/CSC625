DROP DATABASE IF EXISTS `checkin`;
DROP USER IF EXISTS 'api'@'localhost';

CREATE DATABASE  IF NOT EXISTS `checkin` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `checkin`;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
	`ID` int(11) NOT NULL AUTO_INCREMENT,
	`Email` varchar(45) NOT NULL,
	`UID` varchar(45) NOT NULL,
	`Last_Login` date NOT NULL,
	`Active` varchar(45) NOT NULL,
	`FirstName` varchar(45) NOT NULL,
	`LastName` varchar(45) NOT NULL,
	PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
	`ID` int(11) NOT NULL AUTO_INCREMENT,
	`Active` varchar(45) NOT NULL,
	`FirstName` varchar(45) NOT NULL,
	`LastName` varchar(45) NOT NULL,
	`User_ID` int(11) NOT NULL,
	PRIMARY KEY (`ID`),
	KEY `User_ID` (`User_ID`),
	CONSTRAINT `student_idfk_1` FOREIGN KEY (`User_ID`) REFERENCES `user` (`ID`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `QrCode`;
CREATE TABLE `QrCode` (
	`ID` int(11) NOT NULL AUTO_INCREMENT,
	`Student_ID` int(11) NOT NULL,
	`Active` varchar(45) NOT NULL,
	`Code` blob NOT NULL,
	PRIMARY KEY (`ID`),
	KEY `Student_ID` (`Student_ID`),
	CONSTRAINT `Qrcode_idfk_1` FOREIGN KEY (`Student_ID`) REFERENCES `student` (`ID`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `CheckIns`;
CREATE TABLE `CheckIns` (
	`ID` int(11) NOT NULL AUTO_INCREMENT,
	`Student_ID` int(11) NOT NULL,
	`Check_In_Date` timestamp NOT NULL,
	PRIMARY KEY (`ID`),
	KEY `Student_ID` (`Student_ID`),
	CONSTRAINT `CheckIns_idfk_1` FOREIGN KEY (`Student_ID`) REFERENCES `student` (`ID`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE VIEW `checkin_view` AS
SELECT student.ID, Check_In_Date, FirstName, LastName from checkins
join student on checkins.Student_ID = student.ID;

CREATE USER 'api'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON `%`.* TO 'api'@'localhost' WITH GRANT OPTION;