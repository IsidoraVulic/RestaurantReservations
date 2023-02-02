/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 10.4.27-MariaDB : Database - restaurant
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`restaurant` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci */;

USE `restaurant`;

/*Table structure for table `dining_table` */

DROP TABLE IF EXISTS `dining_table`;

CREATE TABLE `dining_table` (
  `TableID` varchar(255) NOT NULL,
  `RestaurantID` varchar(255) NOT NULL,
  `Chairs` int(11) NOT NULL,
  PRIMARY KEY (`TableID`),
  KEY `RestaurantID` (`RestaurantID`),
  CONSTRAINT `dining_table_ibfk_1` FOREIGN KEY (`RestaurantID`) REFERENCES `restaurant` (`RestaurantID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `dining_table` */

insert  into `dining_table`(`TableID`,`RestaurantID`,`Chairs`) values 
('16752562','123',6),
('2','123',3),
('3','123',4),
('4','123',2),
('5','123',2);

/*Table structure for table `guest` */

DROP TABLE IF EXISTS `guest`;

CREATE TABLE `guest` (
  `GuestID` varchar(255) NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `Contact` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  PRIMARY KEY (`GuestID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `guest` */

insert  into `guest`(`GuestID`,`FirstName`,`LastName`,`Contact`,`Email`) values 
('123456','Isidora','Vulic','0652000528','vulic.isi@gmail.com'),
('1675101513','Marija','Petrovic','064787892','mara@gmail.com'),
('1675103683','Janko','Antic','0648759322','janko@gmail.com'),
('1675103772','Ana','Krstic','0645798123','ana@gmail.com'),
('1675103800','Mirko','Antonic','064897524','mirko@gmail.com'),
('1675103857','Filip','Jovanovic','0645678951','filip@gmail.com'),
('1675103911','Jana','Milic','06457981234','jana@gmail.com'),
('1675103952','Dusan','Kostic','06478921465','dusan@gmail.com'),
('1675104036','Jovana','Nikolic','0647891234','jovana@gmail.com'),
('1675104139','Petar','Peric','064578921','pera@gmail.com'),
('1675104454','Nina','Mijalic','065782145','nina@gmail.com'),
('1675104555','Tara','Novakovic','0631278546','tara@gmail.com'),
('1675104619','Sofija','Jakic','0624785125','sofija@gmail.com'),
('1675110441','Jelena','Janicijevic','067894515','jelena@gmail.com'),
('1675110560','Nikolina','Jocic','067894514','nikolina@gmail.com'),
('1675185004','Helena','Jugovic','0654789125','helena@gmail.com'),
('1675185271','Jelena','Grujic','0647895463','jelenag@gmail.com'),
('1675185492','Nadja','Pesic','0645798542','nadja@gmail.com'),
('1675185685','Nevena','Radovic','0697546812','nena@gmail.com'),
('1675249313','Jana','Krstic','0641257858','janak@gmail.com'),
('1675249481','Martina','Tomasevic','064578548','martina@gmail.com'),
('1675271679','Nikola','Gojkovic','0617548595','nikolag@gmail.com'),
('456789','Marko','Markovic','0642758932','marko@gmail.com');

/*Table structure for table `reservation` */

DROP TABLE IF EXISTS `reservation`;

CREATE TABLE `reservation` (
  `ReservationID` varchar(255) NOT NULL,
  `GuestID` varchar(255) NOT NULL,
  `TableID` varchar(255) NOT NULL,
  `Note` varchar(255) NOT NULL,
  `Date` date NOT NULL,
  `Time` time NOT NULL,
  `UserID` varchar(255) NOT NULL,
  PRIMARY KEY (`ReservationID`),
  KEY `GuestID` (`GuestID`),
  KEY `TableID` (`TableID`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`GuestID`) REFERENCES `guest` (`GuestID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`TableID`) REFERENCES `dining_table` (`TableID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reservation_ibfk_3` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `reservation` */

insert  into `reservation`(`ReservationID`,`GuestID`,`TableID`,`Note`,`Date`,`Time`,`UserID`) values 
('1675342556','123456','16752562','','2023-02-04','15:00:00','1');

/*Table structure for table `restaurant` */

DROP TABLE IF EXISTS `restaurant`;

CREATE TABLE `restaurant` (
  `RestaurantID` varchar(255) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Address` varchar(255) NOT NULL,
  PRIMARY KEY (`RestaurantID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `restaurant` */

insert  into `restaurant`(`RestaurantID`,`Name`,`Address`) values 
('123','Pastel d.o.o.','Trg republike 3'),
('12345678','Venecija d.o.o.','Kralja Petra 2'),
('45612378','Jazz place','Takovska 10'),
('45698721','Asian Food d.o.o.','Vuka Karadzica 32'),
('45781236','Oplenac','Bulevar kralja Aleksandra 143'),
('78451245','JJ d.o.o.','Trg Slavija 15'),
('87654321','Fantazija','Bulevar oslobodjenja 54');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `UserID` varchar(255) NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

/*Data for the table `user` */

insert  into `user`(`UserID`,`FirstName`,`LastName`,`Username`,`Password`) values 
('1','Isidora','Vulic','isi','123456'),
('2','Jovan','Petrovic','jp','admin');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
