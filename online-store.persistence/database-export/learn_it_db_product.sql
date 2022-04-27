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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `product_name` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(1500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `price` decimal(15,2) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `img_name` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'HP Laptop 1','This is a good laptop',1500.56,1,'hp.png'),(2,'Apple iPhone',NULL,2000.99,2,'iphone.jpg'),(3,'Samsung Galaxy S21',NULL,1100.10,2,'samsung-galaxy.jpg'),(4,'Asus Zenbook',NULL,1857.28,1,'asus-zenbook.jpg'),(6,'Samsung QLED TV',NULL,2519.78,3,'qled.jpg'),(8,'Laptop Dell',NULL,30.00,1,'dell.jpg'),(9,'Chair 1',NULL,39.00,4,'chair.jpg'),(10,'Chair 2',NULL,49.00,4,'chair.jpg'),(11,'Chair 3',NULL,59.00,4,'chair.jpg'),(12,'Sofa 1',NULL,487.00,6,'sofa.jpg'),(13,'Sofa 2',NULL,557.00,6,'sofa.jpg'),(14,'Sofa 3',NULL,673.00,6,'sofa.jpg'),(15,'Bed 1',NULL,488.00,5,'bed.jpg'),(16,'Bed 2',NULL,599.00,5,'bed.jpg'),(17,'Bed 3',NULL,732.00,5,'bed.jpg'),(18,'Camera 1',NULL,1100.00,8,'camera.jpg'),(19,'Camera 2',NULL,1800.00,8,'camera.jpg'),(20,'Camera 3',NULL,2800.00,8,'camera.jpg'),(21,'HP Laptop 2','This is a good laptop',1899.99,1,'hp.png'),(22,'HP Laptop 3','This is a good laptop',1899.99,1,'hp.png'),(23,'HP Laptop 4','This is a good laptop',1899.99,1,'hp.png'),(24,'HP Laptop 5','This is a good laptop',1899.99,1,'hp.png'),(25,'HP Laptop 6','This is a good laptop',1899.99,1,'hp.png'),(26,'HP Laptop 7','This is a good laptop',1899.99,1,'hp.png'),(27,'HP Laptop 8','This is a good laptop',1899.99,1,'hp.png'),(28,'HP Laptop 9','This is a good laptop',1899.99,1,'hp.png'),(29,'HP Laptop 10','This is a good laptop',1899.99,1,'hp.png'),(30,'HP Laptop 11','This is a good laptop',1899.99,1,'hp.png');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
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
