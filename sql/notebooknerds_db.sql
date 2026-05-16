-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: localhost    Database: notebooknerds
-- ------------------------------------------------------
-- Server version	8.0.45

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
-- Table structure for table `auftrag`
--

DROP TABLE IF EXISTS `auftrag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auftrag` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `modell` varchar(100) DEFAULT NULL,
  `baujahr` int DEFAULT NULL,
  `typ` varchar(50) DEFAULT NULL,
  `zoll` varchar(10) DEFAULT NULL,
  `zustand` varchar(50) DEFAULT NULL,
  `schaden` varchar(255) DEFAULT NULL,
  `tastatur_deutsch` tinyint(1) DEFAULT NULL,
  `netzteil` tinyint(1) DEFAULT NULL,
  `angebotspreis` decimal(10,2) DEFAULT NULL,
  `status` varchar(50) DEFAULT 'NEU',
  `kunde_name` varchar(100) DEFAULT NULL,
  `kunde_email` varchar(100) DEFAULT NULL,
  `erstellt_am` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auftrag`
--

LOCK TABLES `auftrag` WRITE;
/*!40000 ALTER TABLE `auftrag` DISABLE KEYS */;
INSERT INTO `auftrag` VALUES (11,NULL,2025,'MacBook Air','13','gut','Sonstige Schaden',0,0,80.00,'ANGENOMMEN','user1','user1@user1.de','2026-03-03 18:03:35'),(12,NULL,2025,'MacBook Air','13','gut','Sonstige Schaden',0,0,150.00,'ANGENOMMEN','user2','user2@user2.de','2026-03-03 18:06:12'),(13,NULL,2025,'MacBook Air','13','gut','Sonstige Schaden',0,1,200.00,'ABGELEHNT_DURCH_KUNDE','user1','user1@user1.de','2026-03-03 19:12:14'),(14,NULL,2022,'MacBook Air','13','akzeptabel','Flüssigkeitsschaden',0,1,75.00,'OFFEN','user2','user2@user2.de','2026-03-03 19:12:44'),(15,NULL,2019,'MacBook Pro','14','akzeptabel','Sonstige Schaden',1,1,135.00,'ABGELEHNT','Mustermann','Muster@muster.de','2026-03-04 18:13:18'),(16,NULL,2020,'MacBook Pro','13','akzeptabel','Displayschaden',1,1,100.00,'ANGENOMMEN','Mustermann1','Muster1@muster1.de','2026-03-05 14:37:54'),(17,NULL,2019,'MacBook Pro','16','schlecht','Sonstige Schaden',1,1,100.00,'GEGENANGEBOT','Mustermann1','Muster2@muster2.de','2026-03-14 20:37:49'),(18,NULL,2024,'MacBook Pro','15','akzeptabel','Flüssigkeitsschaden',1,1,150.00,'ANGENOMMEN','Mustermann3','Muster3@muster3.de','2026-03-15 16:45:18');
/*!40000 ALTER TABLE `auftrag` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-17 20:49:57
