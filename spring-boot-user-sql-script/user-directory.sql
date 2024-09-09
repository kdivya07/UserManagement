CREATE DATABASE  IF NOT EXISTS `user_directory`;
USE `user_directory`;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `user`
--

INSERT INTO `user` VALUES 
	(1,'Manasi','Giri','test123','manasi.giri@airtel.com'),
	(2,'Divya','Kirad','test123','divya.kirad@airtel.com'),
	(3,'Khadija','Koraswala','test123','khadija.koraswala@airtel.com'),
	(4,'Javali','Murari','test123','murari.sai@airtel.com'),
	(5,'Dev','Maheshwari','test123','dev.maheshwari@airtel.com');
