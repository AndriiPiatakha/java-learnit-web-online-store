-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: learn_it_db
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_name` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fk_user_role` int DEFAULT NULL,
  `money` decimal(15,2) DEFAULT '0.00',
  `credit_card` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `partner_code` varchar(6) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `referrer_user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `role_id_idx` (`fk_user_role`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`fk_user_role`) REFERENCES `role` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Sergey','Ivanov','s.ivanov@email.com','testpass',NULL,400.60,'1234567890123456',NULL,52),(3,'Petr','Skomorohov','p.skomorohov@email.com',NULL,3,100.25,'4444444444444444',NULL,NULL),(11,'Andrey','Pavlenkoa','a.pavlenko@email.coma',NULL,NULL,754.28,'5555555555555555',NULL,NULL),(12,'John','Smith','john.smith@email.com',NULL,4,760.75,'6666666666666666',NULL,NULL),(17,'Yegor','Hromov','y.hromov@email.com',NULL,4,120.00,'7777777777777777',NULL,NULL),(18,'Vasyl','Leonenko','v.leonenko@email.com',NULL,6,160.00,'8888888888888888',NULL,NULL),(31,'Dmytriy','Voloshov','d.voloshov@email.com',NULL,4,0.00,NULL,NULL,NULL),(36,'Evheniy','Kachanov','e.kachanov@email.com',NULL,6,1000.00,NULL,NULL,NULL),(48,'Admin','Admin','admin@email.com','admin',101,0.00,NULL,'KGUR92',NULL),(52,'Test','Test','test@test.com','test',102,122.04,NULL,'PFQ4VW',NULL),(55,'Manager','Manager','manager@manager.com','testtest*',103,30.01,NULL,'JVCAWL',NULL),(56,'Partner','Partner','p@p.com','partnerrr*',102,0.00,NULL,'9PA57Q',52),(57,'T','T','test2@test.com','testtest*',102,0.00,NULL,'51KK9A',55),(58,'E','E','e@e.email','qwerqwer*',102,0.00,NULL,'3HSQAI',55);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-05  1:23:07
