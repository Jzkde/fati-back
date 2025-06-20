-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: fatideco
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
  `direccion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Aca','Mariana','123'),(2,'Nose','Diego','456'),(3,'Mas Aca','Julieta','789'),(4,'Lejos','Mauricio','321'),(5,'Cerca','Francisco','654'),(6,'Ningun Lugar','Nadie','123'),(10,'123','123123','123'),(11,NULL,'123',NULL),(12,NULL,'asdasdasd',NULL),(13,NULL,'123123123',NULL),(14,'Aca','Mariana','123'),(15,'Nose','Diego','456'),(16,'Mas Aca','Julieta','789'),(17,'Lejos','Mauricio','321'),(18,'Cerca','Francisco','654'),(19,'Ningun Lugar','Nadie','123');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cort_especiales`
--

DROP TABLE IF EXISTS `cort_especiales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cort_especiales` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `es_tela` bit(1) NOT NULL,
  `precio` double NOT NULL,
  `sistema` varchar(255) DEFAULT NULL,
  `tela` varchar(255) DEFAULT NULL,
  `marca_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpfauoas06p9oi2fg8soiy7vry` (`marca_id`),
  CONSTRAINT `FKpfauoas06p9oi2fg8soiy7vry` FOREIGN KEY (`marca_id`) REFERENCES `marca` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cort_especiales`
--

LOCK TABLES `cort_especiales` WRITE;
/*!40000 ALTER TABLE `cort_especiales` DISABLE KEYS */;
INSERT INTO `cort_especiales` VALUES (1,_binary '\0',1500,'ROLLER','Cortina ROLLER',1),(2,_binary '\0',800,'PERSIANA','Persiana VENECIANA',1),(3,_binary '\0',2000,'ZEBRA','Cortina BLACKOUT',1),(4,_binary '\0',1500,'ROLLER','Cortina ROLLER',1),(5,_binary '\0',800,'PERSIANA','Persiana VENECIANA',1),(6,_binary '\0',2000,'ZEBRA','Cortina BLACKOUT',1),(7,_binary '',1500,'ROLLER','Cortina ROLLER',1),(8,_binary '\0',800,'PERSIANA','Persiana VENECIANA',1),(9,_binary '',2000,'ZEBRA','Cortina BLACKOUT',1),(10,_binary '',1500,'ROLLER','Cortina ROLLER',1),(11,_binary '\0',800,'PERSIANA','Persiana VENECIANA',1),(12,_binary '',2000,'ZEBRA','Cortina BLACKOUT',1),(13,_binary '',1500,'ROLLER','Cortina ROLLER',1),(14,_binary '\0',800,'PERSIANA','Persiana VENECIANA',1),(15,_binary '',2000,'ZEBRA','Cortina BLACKOUT',1),(16,_binary '',1500,'ROLLER','Cortina ROLLER',1),(17,_binary '\0',800,'PERSIANA','Persiana VENECIANA',1),(18,_binary '',2000,'ZEBRA','Cortina BLACKOUT',1),(19,_binary '',1500,'ROLLER','Cortina ROLLER',1),(20,_binary '\0',800,'PERSIANA','Persiana VENECIANA',1),(21,_binary '',2000,'ZEBRA','Cortina BLACKOUT',1),(22,_binary '\0',46290,'ROLLER',' SISTEMA VTX15 - 32MM',1),(23,_binary '\0',50570,'ROLLER',' SISTEMA VTX15 -38MM',1),(24,_binary '\0',55740,'ROLLER',' SISTEMA VTX20 -45MM',1),(25,_binary '\0',48230,'VERTICALES',' SISTEMA VERTICAL',1),(26,_binary '\0',95920,'DUBAI',' SISTEMA DUBAI COMPLETO CASETTE 100',1),(27,_binary '\0',0,'PERSIANA',' PERSIANA',1),(28,_binary '\0',107180,'ROMANA',' SISTEMA ROMANA CON TELA Y CADENA',1),(29,_binary '\0',0,'ADICIONAL',' Sin Adicionales',1),(30,_binary '\0',3520,'ADICIONAL',' Sop Intermedio',1);
/*!40000 ALTER TABLE `cort_especiales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marca`
--

DROP TABLE IF EXISTS `marca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marca` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `marca` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marca`
--

LOCK TABLES `marca` WRITE;
/*!40000 ALTER TABLE `marca` DISABLE KEYS */;
INSERT INTO `marca` VALUES (1,'FLEXCOLOR'),(2,'ROYALCORT'),(3,'123'),(5,'FLEXCOLOR'),(6,'ROYALCORT');
/*!40000 ALTER TABLE `marca` ENABLE KEYS */;
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (2,'123','PEDIDO',NULL,'2025-05-16',_binary '\0',1,'123','123','123','','123','123123','123');
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
  `caida` bit(1) NOT NULL,
  `cliente_nombre` varchar(255) DEFAULT NULL,
  `comando` varchar(255) DEFAULT NULL,
  `comprado` bit(1) NOT NULL,
  `fecha` date DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `sistema` varchar(255) DEFAULT NULL,
  `viejo` bit(1) NOT NULL,
  `cliente_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrk2dr9sk2ukvud57mp4pxbgic` (`cliente_id`),
  CONSTRAINT `FKrk2dr9sk2ukvud57mp4pxbgic` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presupuesto`
--

LOCK TABLES `presupuesto` WRITE;
/*!40000 ALTER TABLE `presupuesto` DISABLE KEYS */;
INSERT INTO `presupuesto` VALUES (2,'',123,'',123,'NO_POSEE',_binary '','123123','DERECHO',_binary '\0','2025-05-16','','ROLLER',_binary '\0',10);
/*!40000 ALTER TABLE `presupuesto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `art` varchar(255) DEFAULT NULL,
  `es_tela` bit(1) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` double NOT NULL,
  `marca_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK868tnrt85f21kgcvt9bftgr8r` (`marca_id`),
  CONSTRAINT `FK868tnrt85f21kgcvt9bftgr8r` FOREIGN KEY (`marca_id`) REFERENCES `marca` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicio`
--

DROP TABLE IF EXISTS `servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicio` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` double NOT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `marca_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKeehn4xje5su5s1mov4yver20` (`marca_id`),
  CONSTRAINT `FKeehn4xje5su5s1mov4yver20` FOREIGN KEY (`marca_id`) REFERENCES `marca` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicio`
--

LOCK TABLES `servicio` WRITE;
/*!40000 ALTER TABLE `servicio` DISABLE KEYS */;
/*!40000 ALTER TABLE `servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taller`
--

DROP TABLE IF EXISTS `taller`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taller` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `accesorios` varchar(255) DEFAULT NULL,
  `alto` int NOT NULL,
  `ambiente` varchar(255) DEFAULT NULL,
  `ancho` int NOT NULL,
  `apertura` varchar(255) DEFAULT NULL,
  `cliente_nombre` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `fecha_entrega` date DEFAULT NULL,
  `fecha_pedido` date DEFAULT NULL,
  `llego` bit(1) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `cliente_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK85l5dpmh5hjp7de49x92e5l46` (`cliente_id`),
  CONSTRAINT `FK85l5dpmh5hjp7de49x92e5l46` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taller`
--

LOCK TABLES `taller` WRITE;
/*!40000 ALTER TABLE `taller` DISABLE KEYS */;
INSERT INTO `taller` VALUES (1,'',123,'',123,'CENTRAL','123123123','PEDIDO',NULL,'2025-05-16',_binary '\0','',13),(2,'',123,'',123,'NO_POSEE','123','PEDIDO',NULL,'2025-05-16',_binary '\0','',11);
/*!40000 ALTER TABLE `taller` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-17 16:20:06
