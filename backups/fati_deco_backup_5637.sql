-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: fati_deco
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Quiroga','J J Paso 421','Jose','3884790124'),(2,'Mamani','Las vicuñas 1421','Ernesto','3884280999'),(3,'Melano','mz 13 lt 91 - B° Malvinas','Maria','3714963344'),(4,'Jure','San Martin 915','Ines','3884227966');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flex`
--

DROP TABLE IF EXISTS `flex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flex` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `precio` double NOT NULL,
  `tela` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flex`
--

LOCK TABLES `flex` WRITE;
/*!40000 ALTER TABLE `flex` DISABLE KEYS */;
INSERT INTO `flex` VALUES (1,46290,'SISTEMA VTX15 - 32MM'),(2,50570,'SISTEMA VTX15 -38MM'),(3,55740,'SISTEMA VTX20 -45MM'),(4,48310,'BLACKOUT VINILICO (Ancho 1.83 mts)'),(5,80370,'ONIX BLACKOUT (Ancho 1.83 mts)'),(6,48310,'BLACKOUT VINILICO (Ancho 2.44 mts)'),(7,55970,'BLACKOUT VINILICO (Ancho 2.99 mts)'),(8,104930,'FRACTAL BLACKOUT (Ancho 2.80 mts)'),(9,116130,'ANCONA BLACKOUT (Ancho 2.40 mts)'),(10,122420,'APOLLO BLACKOUT (Ancho 4.10 mts)'),(11,66540,'DIONE BLACKOUT (Ancho 2.49 mts)'),(12,97280,'SINGULAR BLACKOUT (Ancho 2.79 mts)'),(13,105060,'DAKU BLACK OUT (Ancho 2.65)'),(14,103050,'LINEN BLACKOUT (Ancho 2.70)'),(15,76830,'MATTIZ BLACKOUT (Ancho 2.00 mts)'),(16,79330,'DUBLIN BLACKOUT (Ancho 2.40)'),(17,87330,'DUBLIN BLACKOUT (Ancho 3.05)'),(18,104470,'LINO BLACKOUT (Ancho 2.40)'),(19,94890,'SHANGAI BLACK OUT (ANCHO 2.40)'),(20,85110,'SCREEN BARU (Ancho 2.50mts)'),(21,94880,'SCREEN 0.5 (Ancho 3 mts)'),(22,104930,'SCREEN PLOT (Ancho 2.5)'),(23,88850,'SCREEN BARACOA (Ancho 2.50)'),(24,106360,'SCREEN 10% CALICO'),(25,100350,'SCREEN 314 (Ancho 2.50 mts)'),(26,87270,'SCREEN 351 APERTURA 1%  (Ancho 2.49 mts)'),(27,74310,'SCREEN SOLAR  APERTURA 5% (ancho 2.49 mts)'),(28,87120,'SCREEN SOLAR  APERTURA 5% (ancho 3.00 mts)'),(29,71080,'SCREEN SLIM  APERTURA 5% (ancho 2.50)'),(30,83340,'SCREEN SLIM  APERTURA 5% (3.00 mts)'),(31,100350,'SCREEN BALI (Ancho 1.60 mts)'),(32,110390,'SCREEN BALI (Ancho 2.50 mts)'),(33,125700,'SCREEN 405 (Ancho 2.49 mts)'),(34,82160,'SCREEN 3% (ancho 2.49)'),(35,82160,'SCREEN 3% (ancho 2.49)'),(36,87390,'SCREEN 3% (ancho 3.00)'),(37,129250,'SCREEN LUMINUM 5% (ANCHO 2.35)'),(38,60730,'CAIRO (Ancho 3 mts)'),(39,88800,'ATRIO (Ancho 2.50 mts)'),(40,38720,'CINA (Ancho 2.50 ) '),(41,98900,'BRICK (Ancho 2.50)'),(42,42860,'VERDANA (Ancho 2.49 mts)'),(43,53290,'MATTIZ (Ancho 2.00 mts)'),(44,53290,'MATTIZ (Ancho 2.40 mts)'),(45,67690,'LINEN (Ancho 2.70 mts)'),(46,106550,'LUN� (Ancho 2.80mts)'),(47,71250,'ROME (Ancho 2.69 mts)'),(48,82290,'MAMBO POLYESTER (Ancho 2.49 mts)'),(49,37450,'KENYA (Ancho 2.70 mts) '),(50,61210,'DUBLIN (Ancho 2.40 mts)'),(51,84380,'SPIDER (Ancho 2.40 mts)'),(52,174890,'TROPICAL (Ancho 2.35 mts)'),(53,110870,'FOREST (Ancho 2.40 mts)'),(54,69750,'MISTRAL (Ancho 2.70 mts)'),(55,120910,'NAZCA (Ancho 2.50 mts)'),(56,45800,'MALAGA (Ancho 2.50 mts)'),(57,53110,'PORTO  (Ancho 2.40 mts)'),(58,75170,'LINO (ANCHO 2.40)'),(59,88690,'TREE DUBLIN (ANCHO 2.40)'),(60,100950,'TAIPEI (ANCHO 2.30)'),(61,41550,'BARROCO (ANCHO 2.40)');
/*!40000 ALTER TABLE `flex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cliente_nombre` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `fecha_llegada` date DEFAULT NULL,
  `fecha_pedido` date DEFAULT NULL,
  `llego` bit(1) NOT NULL,
  `monto` double NOT NULL,
  `n_factura` varchar(255) DEFAULT NULL,
  `n_pedido` varchar(255) DEFAULT NULL,
  `n_remito` varchar(255) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `provedor` varchar(255) DEFAULT NULL,
  `responsable` varchar(255) DEFAULT NULL,
  `via` varchar(255) DEFAULT NULL,
  `cliente_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK30s8j2ktpay6of18lbyqn3632` (`cliente_id`),
  CONSTRAINT `FK30s8j2ktpay6of18lbyqn3632` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (1,'Jose Quiroga','PEDIDO',NULL,'2024-07-08',_binary '\0',987987,'A-15666','19999','R-986765','','Flexcolor','Diego','Mail',1),(2,'Jose Quiroga','PEDIDO',NULL,'2024-07-08',_binary '\0',987987,'15666','987456','R-98665','','SabelCort','Diego','Web',1),(3,'Jose Quiroga','PEDIDO',NULL,'2024-07-08',_binary '\0',65465,'B-9890','7894','R-68756','','SabeCort','Gonzalo','Telefono',1),(4,'Ernesto Mamani','PEDIDO',NULL,'2024-07-08',_binary '\0',987987,'A-15666','19999','R-986765','','Muresco','Diego','Mail',2),(5,'Ernesto Mamani','PEDIDO',NULL,'2024-07-08',_binary '\0',987987,'15666','5656','R-854665','','SabelCort','Gonzalo','Web',2),(6,'Ernesto Mamani','PEDIDO',NULL,'2024-07-08',_binary '\0',65465,'B-9890','7894','R-68756','','SabeCort','Matias','Telefono',2),(7,'Maria Melano','PEDIDO',NULL,'2024-07-08',_binary '\0',987987,'A-15666','19999','R-986765','','Flexcolor','Matias','Mail',3),(8,'Ines Jure','PEDIDO',NULL,'2024-07-08',_binary '\0',987987,'15666','987456','R-98665','','Shefa','Diego','Web',4),(9,'Ines Jure','PEDIDO',NULL,'2024-07-08',_binary '\0',65465,'B-9890','7894','R-68756','','SabeCort','Gonzalo','Telefono',4);
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presupuesto`
--

DROP TABLE IF EXISTS `presupuesto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `presupuesto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `accesorios` varchar(255) DEFAULT NULL,
  `alto` int NOT NULL,
  `ambiente` varchar(255) DEFAULT NULL,
  `ancho` int NOT NULL,
  `apertura` varchar(255) DEFAULT NULL,
  `cliente_nombre` varchar(255) DEFAULT NULL,
  `comando` varchar(255) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `sistema` varchar(255) DEFAULT NULL,
  `cliente_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrk2dr9sk2ukvud57mp4pxbgic` (`cliente_id`),
  CONSTRAINT `FKrk2dr9sk2ukvud57mp4pxbgic` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presupuesto`
--

LOCK TABLES `presupuesto` WRITE;
/*!40000 ALTER TABLE `presupuesto` DISABLE KEYS */;
INSERT INTO `presupuesto` VALUES (1,'',100,'',120,'NO_POSEE','Jose Quiroga','DERECHO','','PERCIANA',1),(2,'',210,'',180,'BILATERAL','Ernesto Mamani','NO_POSEE','','PRESILLA',2),(3,'',130,'',120,'CENTRAL','Maria Melano','IZQUIERDO','','VERTICALES',3),(4,'',130,'',120,'CENTRAL','Maria Melano','NO_POSEE','','AMERICANA',3),(5,'',115,'',100,'NO_POSEE','Ines Jure','DERECHO','','DUBAI',4);
/*!40000 ALTER TABLE `presupuesto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-08 13:56:37
