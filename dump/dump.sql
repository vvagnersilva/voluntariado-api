-- MySQL dump 10.13  Distrib 8.0.22, for Linux (x86_64)
--
-- Host: localhost    Database: voluntariado
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `equipe`
--

DROP TABLE IF EXISTS `equipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipe` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` text,
  `cor` varchar(15) DEFAULT NULL,
  `url_img` text,
  `ativo` char(1) DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipe`
--

LOCK TABLES `equipe` WRITE;
/*!40000 ALTER TABLE `equipe` DISABLE KEYS */;
INSERT INTO `equipe` VALUES (1,'Reiki','0xff7cb342','reiki.png','S'),(2,'Amigo do leito','0xFF0D47A1','amigos.jpg','S'),(3,'Sinfonia da saúde','0xFF827717','sinfonia.jpg','S'),(4,'Contadores de história','0xFFFB8C00','contadoresHistoria.jpg','S'),(5,'Brinquedoteca','0xFF546E7A','brinquedoteca.jpg','S');
/*!40000 ALTER TABLE `equipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `local`
--

DROP TABLE IF EXISTS `local`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `local` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` text,
  `endereco` text,
  `cor` varchar(15) DEFAULT NULL,
  `url_img` text,
  `ativo` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `local`
--

LOCK TABLES `local` WRITE;
/*!40000 ALTER TABLE `local` DISABLE KEYS */;
INSERT INTO `local` VALUES (1,'Golfinho - Clínica','Hospital da Criança','0xFFE53935','golfinho.jpg','S'),(2,'Baleia - Clínica','Hospital da Criança','0xFFE53935','baleia.jpg','S'),(3,'Caranguejo - Oncologia/Hematologia','Hospital da Criança','0xFF827717','caranguejo.jpg','S'),(4,'Tartaruga - Cirurgias','Hospital da Criança','0xFFFB8C00','tartaruga.jpg','S'),(5,'Gaivota - Cirurgias','Hospital da Criança','0xFF0D47A1','gaivota.jpg','S'),(6,'Peixe - Cuidados Paliativos','Hospital da Criança','0xFFE53935','peixe.jpg','S'),(7,'UTE - Unidade de Tratamento Endovenosa','Hospital da Criança','0xFF0D47A1','ute.jpg','S'),(8,'Hemodiálise','Hospital da Criança','0xFF827717','hemodialise.jpg','S'),(9,'UTI - Unidade de Tratamento Intensivo','Hospital da Criança','0xFFE53935','uti.jpg','S');
/*!40000 ALTER TABLE `local` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trabalho`
--

DROP TABLE IF EXISTS `trabalho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trabalho` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_equipe` bigint NOT NULL,
  `id_local` bigint NOT NULL,
  `id_usuario` bigint NOT NULL,
  `data` varchar(10) NOT NULL,
  `hora_inicio` varchar(5) NOT NULL,
  `hora_fim` varchar(5) NOT NULL,
  `descricao` varchar(150) NOT NULL,
  `num_atendimento` int NOT NULL,
  `tp_atendimento` char(1) NOT NULL DEFAULT 'P' COMMENT 'Tipo de atendimento pode ser:\nP - Presencial\nR - Remoto\n\n',
  PRIMARY KEY (`id`),
  KEY `fk_trabalho_local_idx` (`id_local`),
  KEY `fk_trabalho_equipe_idx` (`id_equipe`),
  KEY `fk_trabalho_usuario1` (`id_usuario`),
  CONSTRAINT `fk_trabalho_equipe` FOREIGN KEY (`id_equipe`) REFERENCES `equipe` (`id`),
  CONSTRAINT `fk_trabalho_local` FOREIGN KEY (`id_local`) REFERENCES `local` (`id`),
  CONSTRAINT `fk_trabalho_usuario1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trabalho`
--

LOCK TABLES `trabalho` WRITE;
/*!40000 ALTER TABLE `trabalho` DISABLE KEYS */;
INSERT INTO `trabalho` VALUES (12,1,5,1,'07-05-2020','12:06','18:06','teste',5,'P'),(15,1,2,1,'21-05-2020','12:02','18:02','teste do Wagner',5,'P');
/*!40000 ALTER TABLE `trabalho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `email` text NOT NULL,
  `senha` text NOT NULL,
  `telefone` varchar(15) NOT NULL,
  `adm` char(1) NOT NULL DEFAULT 'N',
  `url_img` text,
  `ativo` char(1) NOT NULL DEFAULT 'S',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Wagner Silva','vvagner','1111','61983485438','S','wagner.png','S'),(2,'Lucas Ventura','lucas','1111','61983411217','N','lucas.jpg','N'),(3,'Marco Amaral','xavantess@gmail.com ','1111','61983391111','S','marco.png','S');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_equipe`
--

DROP TABLE IF EXISTS `usuario_equipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_equipe` (
  `id_usuario` bigint NOT NULL,
  `id_equipe` bigint NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_equipe`),
  KEY `fk_usuario_has_equipe_equipe1_idx` (`id_equipe`),
  KEY `fk_usuario_has_equipe_usuario1_idx` (`id_usuario`),
  CONSTRAINT `fk_usuario_equipe_equipe` FOREIGN KEY (`id_equipe`) REFERENCES `equipe` (`id`),
  CONSTRAINT `fk_usuario_equipe_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_equipe`
--

LOCK TABLES `usuario_equipe` WRITE;
/*!40000 ALTER TABLE `usuario_equipe` DISABLE KEYS */;
INSERT INTO `usuario_equipe` VALUES (1,1),(2,1),(3,1),(1,2),(2,2),(3,2),(1,3),(3,5);
/*!40000 ALTER TABLE `usuario_equipe` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-28 23:42:40
