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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
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
INSERT INTO `cort_especiales` VALUES (1,_binary '\0',123123,'ROLLER','SISTEMA VTX15 - 32MM',6),(2,_binary '\0',50570,'ROLLER','SISTEMA VTX15 -38MM',6),(3,_binary '\0',55740,'ROLLER','SISTEMA VTX20 -45MM',6),(4,_binary '\0',48230,'VERTICALES','SISTEMA VERTICAL',6),(5,_binary '\0',95920,'DUBAI','SISTEMA DUBAI COMPLETO CASETTE 100',6),(6,_binary '\0',0,'PERSIANA','PERSIANA',6),(7,_binary '\0',107180,'ROMANA','SISTEMA ROMANA CON TELA Y CADENA',6),(8,_binary '\0',0,'ADICIONAL','Sin Adicional',6),(9,_binary '\0',3520,'ADICIONAL','Sop Intermedio',6),(10,_binary '\0',14780,'ADICIONAL','Sop Doble',6),(11,_binary '',91910,'DUBAI','ZAFIRO (ANCHO 2.80 MTS)',6),(12,_binary '',15685,'DUBAI','WOODGRAIN (ANCHO 2.80 MTS)',6),(13,_binary '',92100,'PERSIANA','ALUMINIO SOLIDO',6),(14,_binary '',106760,'PERSIANA','ALUMINIO MICROPERFORADO',6),(15,_binary '',48310,'ROLLER','BLACKOUT VINILICO (Ancho 1.83 mts)',6),(16,_binary '',66540,'ROLLER','DIONE BLACKOUT (Ancho 2.49 mts)',6),(17,_binary '',84250,'VERTICALES','SCREEN BALI',6),(18,_binary '',52470,'VERTICALES','CALGARY',6),(19,_binary '\0',34080,'ROLLER','RC Sistema Roller 32Mm',7),(20,_binary '\0',40070,'ROLLER','RC Sistema Roller 38Mm',7),(21,_binary '\0',53470,'ROLLER','RC Sistema Roller 45Mm',7),(22,_binary '\0',95150,'ZEBRA','RC Sistema Zebra',7),(23,_binary '\0',0,'ADICIONAL','Sin Adicionales',7),(24,_binary '\0',7480,'ADICIONAL','Sop Intermedio',7),(25,_binary '\0',7790,'ADICIONAL','Sop Doble',7),(26,_binary '',33180,'ROLLER','RC Blackout Premium(Ancho 1.83 mts)',7),(27,_binary '',50700,'ROLLER','RC Sunscreen 5% 2 (Ancho 2.49 mts)',7),(28,_binary '',121230,'ZEBRA','RC Zebra Capri(Ancho 2.99 mts)',7),(29,_binary '',82230,'ZEBRA','RC Zebra Cairo(Ancho 2.59 mts)',7),(30,_binary '\0',123123,'ROLLER','VTX15 - 32MM',6);
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marca`
--

LOCK TABLES `marca` WRITE;
/*!40000 ALTER TABLE `marca` DISABLE KEYS */;
INSERT INTO `marca` VALUES (1,'FREDA PLAST'),(2,'FATI'),(3,'MURRAY LEA'),(5,'SHEFFA'),(6,'FLEXCOLOR'),(7,'ROYALCORT'),(8,'KOVI'),(9,'SABEL CORT');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presupuesto`
--

LOCK TABLES `presupuesto` WRITE;
/*!40000 ALTER TABLE `presupuesto` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'1L',_binary '\0','Riel Eu 160',10000,1),(2,'1L',_binary '\0','Riel Eu 200',20000,1),(3,'1L',_binary '\0','Riel Am 120',30000,3),(4,'1L',_binary '\0','Riel Am 220',40000,3),(5,'10L',_binary '','Voile Lino Portobello',33180,5),(6,'11L',_binary '','Voile Niza',50700,5),(7,'xxx',_binary '','Solo Confeccion',0,2),(8,'12L',_binary '','Madras Pesado',121230,8),(9,'9L',_binary '','Rafia Liviana',82230,8),(10,'0L',_binary '\0','Barral 33 240',50000,9),(11,'1L',_binary '\0','Barral 22 140',60000,9);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicio`
--

LOCK TABLES `servicio` WRITE;
/*!40000 ALTER TABLE `servicio` DISABLE KEYS */;
INSERT INTO `servicio` VALUES (1,'Sin Colocacion',0,'COLOCACION',2),(2,'Propia',28990,'CONFECCION',2);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taller`
--

LOCK TABLES `taller` WRITE;
/*!40000 ALTER TABLE `taller` DISABLE KEYS */;
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

-- Dump completed on 2025-05-18 21:43:13
