-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.27-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema scout
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ scout;
USE scout;

--
-- Structure for table `scout`.`tapoderado`
--

DROP TABLE IF EXISTS `scout`.`tapoderado`;
CREATE TABLE  `scout`.`tapoderado` (
  `cID` bigint(20) NOT NULL,
  `cRUT` varchar(10) NOT NULL,
  `cPupilo` varchar(10) NOT NULL,
  PRIMARY KEY  (`cID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `scout`.`tapoderado`
--

/*!40000 ALTER TABLE `tapoderado` DISABLE KEYS */;
INSERT INTO `scout`.`tapoderado` (`cID`,`cRUT`,`cPupilo`) VALUES 
 (-7258680789780133559,'3-5','1-9');
/*!40000 ALTER TABLE `tapoderado` ENABLE KEYS */;


--
-- Structure for table `scout`.`tcargo`
--

DROP TABLE IF EXISTS `scout`.`tcargo`;
CREATE TABLE  `scout`.`tcargo` (
  `cID` bigint(20) NOT NULL,
  `cNombre` varchar(50) NOT NULL,
  `cPrioridad` int(11) NOT NULL default '0',
  PRIMARY KEY  (`cID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `scout`.`tcargo`
--

/*!40000 ALTER TABLE `tcargo` DISABLE KEYS */;
INSERT INTO `scout`.`tcargo` (`cID`,`cNombre`,`cPrioridad`) VALUES 
 (1,'Responsable de Grupo',5),
 (2,'Asistente de Grupo',4),
 (3,'Asesor Religioso',0),
 (4,'Presidente del Comité de Grupo',1),
 (5,'Segretario del Comité de Grupo',0),
 (6,'Tesorero del Comité de Grupo',0),
 (7,'Rep. Institución Patrocinante',1),
 (8,'Rep. Comité de Grupo',1),
 (9,'Sin Cargo en el Grupo',1),
 (10,'Asistente de Unidad',14),
 (11,'Responsable de Unidad',14);
/*!40000 ALTER TABLE `tcargo` ENABLE KEYS */;


--
-- Structure for table `scout`.`tcategoria`
--

DROP TABLE IF EXISTS `scout`.`tcategoria`;
CREATE TABLE  `scout`.`tcategoria` (
  `cID` bigint(20) NOT NULL,
  `cCategoria` char(1) NOT NULL,
  `cFechaInicio` datetime NOT NULL,
  `cFechaTermino` datetime NOT NULL,
  `cValor` int(11) NOT NULL,
  PRIMARY KEY  (`cID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `scout`.`tcategoria`
--

/*!40000 ALTER TABLE `tcategoria` DISABLE KEYS */;
INSERT INTO `scout`.`tcategoria` (`cID`,`cCategoria`,`cFechaInicio`,`cFechaTermino`,`cValor`) VALUES 
 (1,'A','2007-01-01 00:00:00','2007-05-31 23:59:59',6100),
 (2,'A','2007-06-01 00:00:00','2007-07-31 23:59:59',6930),
 (3,'A','2007-08-01 00:00:00','2007-09-30 23:59:59',7880),
 (4,'A','2007-10-01 00:00:00','2007-12-31 23:59:59',8990),
 (5,'B','2007-01-01 00:00:00','2007-05-31 23:59:59',5070),
 (6,'B','2007-06-01 00:00:00','2007-07-31 23:59:59',5730),
 (7,'B','2007-08-01 00:00:00','2007-09-30 23:59:59',6500),
 (8,'B','2007-10-01 00:00:00','2007-12-31 23:59:59',7380),
 (9,'C','2007-01-01 00:00:00','2007-05-31 23:59:59',4040),
 (10,'C','2007-06-01 00:00:00','2007-07-31 23:59:59',4540),
 (11,'C','2007-08-01 00:00:00','2007-09-30 23:59:59',5110),
 (12,'C','2007-10-01 00:00:00','2007-12-31 23:59:59',5780),
 (13,'D','2007-01-01 00:00:00','2007-05-31 23:59:59',3010),
 (14,'D','2007-06-01 00:00:00','2007-07-31 23:59:59',3340),
 (15,'D','2007-08-01 00:00:00','2007-09-30 23:59:59',3720),
 (16,'D','2007-10-01 00:00:00','2007-12-31 23:59:59',4170),
 (17,'E','2007-01-01 00:00:00','2007-05-31 23:59:59',1980);
INSERT INTO `scout`.`tcategoria` (`cID`,`cCategoria`,`cFechaInicio`,`cFechaTermino`,`cValor`) VALUES 
 (18,'E','2007-06-01 00:00:00','2007-07-31 23:59:59',2150),
 (19,'E','2007-08-01 00:00:00','2007-09-30 23:59:59',2340),
 (20,'E','2007-10-01 00:00:00','2007-12-31 23:59:59',2560);
/*!40000 ALTER TABLE `tcategoria` ENABLE KEYS */;


--
-- Structure for table `scout`.`tcomuna`
--

DROP TABLE IF EXISTS `scout`.`tcomuna`;
CREATE TABLE  `scout`.`tcomuna` (
  `cID` bigint(20) NOT NULL,
  `cNombre` varchar(50) NOT NULL,
  `cRegion` smallint(6) NOT NULL,
  `cPrioridad` int(11) NOT NULL default '0',
  PRIMARY KEY  (`cID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `scout`.`tcomuna`
--

/*!40000 ALTER TABLE `tcomuna` DISABLE KEYS */;
INSERT INTO `scout`.`tcomuna` (`cID`,`cNombre`,`cRegion`,`cPrioridad`) VALUES 
 (1,'Alto Hospicio',1,0),
 (2,'Camiña',1,0),
 (3,'Colchane',1,0),
 (4,'Huara',1,0),
 (5,'Iquique',1,0),
 (6,'Pica',1,0),
 (7,'Pozo Almonte',1,0),
 (8,'Antofagasta',2,0),
 (9,'Calama',2,0),
 (10,'María Elena',2,0),
 (11,'Mejillones',2,0),
 (12,'Ollagüe',2,0),
 (13,'San Pedro de Atacama',2,0),
 (14,'Sierra Gorda',2,0),
 (15,'Taltal',2,0),
 (16,'Tocopilla',2,0),
 (17,'Alto del Carmen',3,0),
 (18,'Caldera',3,0),
 (19,'Chañaral',3,0),
 (20,'Copiapó',3,0),
 (21,'Diego de Almagro',3,0),
 (22,'Freirina',3,0),
 (23,'Huasco',3,0),
 (24,'Tierra Amarilla',3,0),
 (25,'Vallenar',3,0),
 (26,'Andacollo',4,0),
 (27,'Canela',4,0),
 (28,'Combarbalá',4,0),
 (29,'Coquimbo',4,0),
 (30,'Illapel',4,0),
 (31,'La Higuera',4,0),
 (32,'La Serena',4,0),
 (33,'Los Vilos',4,0),
 (34,'Monte Patria',4,0),
 (35,'Ovalle',4,0),
 (36,'Paihuano',4,0),
 (37,'PunitaquI',4,0),
 (38,'Río Hurtado',4,0),
 (39,'Salamanca',4,0),
 (40,'Vicuña',4,0),
 (41,'Algarrobo',5,0);
INSERT INTO `scout`.`tcomuna` (`cID`,`cNombre`,`cRegion`,`cPrioridad`) VALUES 
 (42,'Cabildo',5,0),
 (43,'Calle Larga',5,0),
 (44,'Cartagena',5,0),
 (45,'Casablanca',5,0),
 (46,'Catemu',5,0),
 (47,'Concón',5,0),
 (48,'El Quisco',5,0),
 (49,'El Tabo',5,0),
 (50,'Hijuelas',5,0),
 (51,'Isla de Pascua',5,0),
 (52,'Juan Fernández',5,0),
 (53,'La Calera',5,0),
 (54,'La Cruz',5,0),
 (55,'La Ligua',5,0),
 (56,'Limache',5,0),
 (57,'Llay llay',5,0),
 (58,'Los Andes',5,0),
 (59,'Nogales',5,0),
 (60,'Olmué',5,0),
 (61,'Panquehue',5,0),
 (62,'Papudo',5,0),
 (63,'Petorca',5,0),
 (64,'Puchuncaví',5,0),
 (65,'Putaendo',5,0),
 (66,'Quillota',5,0),
 (67,'Quilpué',5,0),
 (68,'Quintero',5,0),
 (69,'Rinconada',5,0),
 (70,'San Antonio',5,0),
 (71,'San Esteban',5,0),
 (72,'San Felipe',5,0),
 (73,'Santa María',5,0),
 (74,'Santo Domingo',5,0),
 (75,'Valparaíso',5,0),
 (76,'Villa Alemana',5,0),
 (77,'Viña del Mar',5,0),
 (78,'Zapallar',5,0),
 (79,'Chépica',6,0),
 (80,'Chimbarongo',6,0),
 (81,'Codegua',6,0),
 (82,'Coinco',6,0);
INSERT INTO `scout`.`tcomuna` (`cID`,`cNombre`,`cRegion`,`cPrioridad`) VALUES 
 (83,'Coltauco',6,0),
 (84,'Doñihue',6,0),
 (85,'Graneros',6,0),
 (86,'La Estrella',6,0),
 (87,'Las Cabras',6,0),
 (88,'Litueche',6,0),
 (89,'Lolol',6,0),
 (90,'Machalí',6,0),
 (91,'Malloa',6,0),
 (92,'Marchigüe',6,0),
 (93,'Mostazal',6,0),
 (94,'Nancagua',6,0),
 (95,'Navidad',6,0),
 (96,'Olivar',6,0),
 (97,'Palmilla',6,0),
 (98,'Paredones',6,0),
 (99,'Peralillo',6,0),
 (100,'Peumo',6,0),
 (101,'Pichidegua',6,0),
 (102,'Pichilemu',6,0),
 (103,'Placilla',6,0),
 (104,'Pumanque',6,0),
 (105,'Quinta de Tilcoco',6,0),
 (106,'Rancagua',6,0),
 (107,'Rengo',6,0),
 (108,'Requinoa',6,0),
 (109,'San Fernando',6,0),
 (110,'San Vicente de Tagua Tagua',6,0),
 (111,'Santa Cruz',6,0),
 (112,'Cauquenes',7,0),
 (113,'Chanco',7,0),
 (114,'Colbún',7,0),
 (115,'Constitución',7,0),
 (116,'Curepto',7,0),
 (117,'Curicó',7,0),
 (118,'Empedrado',7,0),
 (119,'Hualañé',7,0),
 (120,'Licantén',7,0),
 (121,'Linares',7,0),
 (122,'Longaví',7,0),
 (123,'Maule',7,0);
INSERT INTO `scout`.`tcomuna` (`cID`,`cNombre`,`cRegion`,`cPrioridad`) VALUES 
 (124,'Molina',7,0),
 (125,'Parral',7,0),
 (126,'Pelarco',7,0),
 (127,'Pelluhue',7,0),
 (128,'Pencahue',7,0),
 (129,'Rauco',7,0),
 (130,'Retiro',7,0),
 (131,'Río Claro',7,0),
 (132,'Romeral',7,0),
 (133,'Sagrada Familia',7,0),
 (134,'San Clemente',7,0),
 (135,'San Javier',7,0),
 (136,'San Rafael',7,0),
 (137,'Talca',7,0),
 (138,'Teno',7,0),
 (139,'Vichuquén',7,0),
 (140,'Villa Alegre',7,0),
 (141,'Yerbas Buenas',7,0),
 (142,'Alto Bío Bío',8,0),
 (143,'Antuco',8,0),
 (144,'Arauco',8,0),
 (145,'Bulnes',8,0),
 (146,'Cabrero',8,0),
 (147,'Cañete',8,0),
 (148,'Chiguayante',8,0),
 (149,'Chillán',8,0),
 (150,'Chillán Viejo',8,0),
 (151,'Cobquecura',8,0),
 (152,'Coelemu',8,0),
 (153,'Coihueco',8,0),
 (154,'Concepción',8,0),
 (155,'Contulmo',8,0),
 (156,'Coronel',8,0),
 (157,'Curanilahue',8,0),
 (158,'El Carmen',8,0),
 (159,'Florida',8,1),
 (160,'Hualpén',8,0),
 (161,'Hualqui',8,0),
 (162,'Laja',8,0),
 (163,'Lebu',8,0),
 (164,'Los Alamos',8,0);
INSERT INTO `scout`.`tcomuna` (`cID`,`cNombre`,`cRegion`,`cPrioridad`) VALUES 
 (165,'Los Ángeles',8,0),
 (166,'Lota',8,0),
 (167,'Mulchén',8,0),
 (168,'Nacimiento',8,0),
 (169,'Negrete',8,0),
 (170,'Ninhue',8,0),
 (171,'Ñiquén',8,0),
 (172,'Pemuco',8,0),
 (173,'Penco',8,0),
 (174,'Pinto',8,0),
 (175,'Portezuelo',8,0),
 (176,'Quilaco',8,0),
 (177,'Quilleco',8,0),
 (178,'Quillón',8,0),
 (179,'Quirihue',8,0),
 (180,'Ranquíl',8,0),
 (181,'San Carlos',8,0),
 (182,'San Fabián',8,0),
 (183,'San Ignacio',8,0),
 (184,'San Nicolás',8,0),
 (185,'San Pedro de la Paz',8,0),
 (186,'San Rosendo',8,0),
 (187,'Santa Bárbara',8,0),
 (188,'Santa Juana',8,0),
 (189,'Talcahuano',8,0),
 (190,'Tirúa',8,0),
 (191,'Tome',8,0),
 (192,'Trehuaco',8,0),
 (193,'Tucapel',8,0),
 (194,'Yumbel',8,0),
 (195,'Yungay',8,0),
 (196,'Angol',9,0),
 (197,'Carahue',9,0),
 (198,'Cholchol',9,0),
 (199,'CollipullI',9,0),
 (200,'Cunco',9,0),
 (201,'Curacautín',9,0),
 (202,'Curarrehue',9,0),
 (203,'Ercilla',9,0),
 (204,'Freire',9,0),
 (205,'Galvarino',9,0);
INSERT INTO `scout`.`tcomuna` (`cID`,`cNombre`,`cRegion`,`cPrioridad`) VALUES 
 (206,'Gorbea',9,0),
 (207,'Lautaro',9,0),
 (208,'Loncoche',9,0),
 (209,'Lonquimay',9,0),
 (210,'Los Sauces',9,0),
 (211,'Lumaco',9,0),
 (212,'Melipeuco',9,0),
 (213,'Nueva Imperial',9,0),
 (214,'Padre Las Casas',9,0),
 (215,'Perquenco',9,0),
 (216,'Pitrufquén',9,0),
 (217,'Pucón',9,0),
 (218,'Purén',9,0),
 (219,'Renaico',9,0),
 (220,'Saavedra',9,0),
 (221,'Temuco',9,0),
 (222,'Teodoro Schmidt',9,0),
 (223,'Toltén',9,0),
 (224,'Traiguén',9,0),
 (225,'Victoria',9,0),
 (226,'Vilcún',9,0),
 (227,'Villarrica',9,0),
 (228,'Ancud',10,0),
 (229,'Calbuco',10,0),
 (230,'Castro',10,0),
 (231,'Chaitén',10,0),
 (232,'ChonchI',10,0),
 (233,'Cochamó',10,0),
 (234,'Curaco de Vélez',10,0),
 (235,'Dalcahue',10,0),
 (236,'Fresia',10,0),
 (237,'Frutillar',10,0),
 (238,'Futaleufú',10,0),
 (239,'Hualaihué',10,0),
 (240,'Llanquihue',10,0),
 (241,'Los Muermos',10,0),
 (242,'Maullín',10,0),
 (243,'Osorno',10,0),
 (244,'Palena',10,0),
 (245,'Puerto Montt',10,0);
INSERT INTO `scout`.`tcomuna` (`cID`,`cNombre`,`cRegion`,`cPrioridad`) VALUES 
 (246,'Puerto Octay',10,0),
 (247,'Puerto Varas',10,0),
 (248,'Puqueldón',10,0),
 (249,'Purranque',10,0),
 (250,'Puyehue',10,0),
 (251,'Queilén',10,0),
 (252,'Quellón',10,0),
 (253,'QuemchI',10,0),
 (254,'Quinchao',10,0),
 (255,'Río Negro',10,0),
 (256,'San Juan de la Costa',10,0),
 (257,'San Pablo',10,0),
 (258,'Aysén',11,0),
 (259,'Chile Chico',11,0),
 (260,'Cisnes',11,0),
 (261,'Cochrane',11,0),
 (262,'Coyhaique',11,0),
 (263,'Guaitecas',11,0),
 (264,'Lago Verde',11,0),
 (265,'O’Higgins',11,0),
 (266,'Río Ibáñez',11,0),
 (267,'Tortel',11,0),
 (268,'Cabo de Hornos',12,0),
 (269,'Laguna Blanca',12,0),
 (270,'Porvenir',12,0),
 (271,'Primavera',12,0),
 (272,'Puerto Natales',12,0),
 (273,'Punta Arenas',12,0),
 (274,'Río Verde',12,0),
 (275,'San Gregorio',12,0),
 (276,'Timaukel',12,0),
 (277,'Torres del Paine',12,0),
 (278,'Alhué',13,0),
 (279,'Buin',13,0),
 (280,'Calera de Tango',13,0),
 (281,'Cerillos',13,0),
 (282,'Cerro Navia',13,0);
INSERT INTO `scout`.`tcomuna` (`cID`,`cNombre`,`cRegion`,`cPrioridad`) VALUES 
 (283,'Colina',13,0),
 (284,'Conchalí',13,0),
 (285,'Curacaví',13,0),
 (286,'El Bosque',13,0),
 (287,'El Monte',13,0),
 (288,'Estación Central',13,0),
 (289,'Huechuraba',13,0),
 (290,'Independencia',13,0),
 (291,'Isla de Maipo',13,0),
 (292,'La Cisterna',13,0),
 (293,'La Florida',13,5),
 (294,'La Granja',13,1),
 (295,'La Pintana',13,0),
 (296,'La Reina',13,0),
 (297,'Lampa',13,0),
 (298,'Las Condes',13,0),
 (299,'Lo Barnechea',13,0),
 (300,'Lo Espejo',13,0),
 (301,'Lo Prado',13,0),
 (302,'Macul',13,0),
 (303,'Maipú',13,0),
 (304,'María Pinto',13,0),
 (305,'Melipilla',13,0),
 (306,'Ñuñoa',13,0),
 (307,'Padre Hurtado',13,0),
 (308,'Paine',13,0),
 (309,'Pedro Aguirre Cerda',13,0),
 (310,'Peñaflor',13,0),
 (311,'Peñalolén',13,0),
 (312,'Pirque',13,0),
 (313,'Providencia',13,0),
 (314,'Pudahuel',13,0),
 (315,'Puente Alto',13,153),
 (316,'Quilicura',13,0),
 (317,'Quinta Normal',13,0),
 (318,'Recoleta',13,0),
 (319,'Renca',13,0);
INSERT INTO `scout`.`tcomuna` (`cID`,`cNombre`,`cRegion`,`cPrioridad`) VALUES 
 (320,'San Bernardo',13,1),
 (321,'San Joaquín',13,0),
 (322,'San José de Maipo',13,0),
 (323,'San Miguel',13,0),
 (324,'San Pedro',13,0),
 (325,'San Ramón',13,0),
 (326,'Santiago',13,0),
 (327,'Talagante',13,0),
 (328,'Til-Til',13,0),
 (329,'Vitacura',13,0),
 (330,'Arica',13,0),
 (331,'Camarones',13,0),
 (332,'General Lagos',13,0),
 (333,'Putre',13,0),
 (334,'Corral',13,0),
 (335,'Futrono',13,0),
 (336,'Lago Ranco',13,0),
 (337,'Lanco',13,0),
 (338,'Los Lagos',13,0),
 (339,'Máfil',13,0),
 (340,'Paillaco',13,0),
 (341,'PanguipullI',13,0),
 (342,'Río Bueno',13,0),
 (343,'San José de la Mariquina',13,0),
 (344,'Valdivia',13,0);
/*!40000 ALTER TABLE `tcomuna` ENABLE KEYS */;


--
-- Structure for table `scout`.`tdistrito`
--

DROP TABLE IF EXISTS `scout`.`tdistrito`;
CREATE TABLE  `scout`.`tdistrito` (
  `cID` bigint(20) NOT NULL,
  `cNombre` varchar(50) NOT NULL,
  `cZona` bigint(20) NOT NULL,
  PRIMARY KEY  (`cID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `scout`.`tdistrito`
--

/*!40000 ALTER TABLE `tdistrito` DISABLE KEYS */;
INSERT INTO `scout`.`tdistrito` (`cID`,`cNombre`,`cZona`) VALUES 
 (1,'Puente Alto Poninte',1);
/*!40000 ALTER TABLE `tdistrito` ENABLE KEYS */;


--
-- Structure for table `scout`.`tdyg`
--

DROP TABLE IF EXISTS `scout`.`tdyg`;
CREATE TABLE  `scout`.`tdyg` (
  `cRUT` varchar(12) NOT NULL,
  `cGentilicio` smallint(6) NOT NULL,
  `cNumeroCasilla` varchar(10) NOT NULL,
  `cEstadoCivil` smallint(6) NOT NULL,
  `cOcupacion` varchar(30) NOT NULL,
  `cEmpleador` varchar(30) NOT NULL,
  `cTitulo` varchar(30) NOT NULL,
  `cTelefonoLaboral` varchar(12) default NULL,
  `cAnexo` varchar(4) NOT NULL,
  `cNivelFormacion` smallint(6) NOT NULL,
  `cNumeroMiembroActivo` char(5) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `scout`.`tdyg`
--

/*!40000 ALTER TABLE `tdyg` DISABLE KEYS */;
INSERT INTO `scout`.`tdyg` (`cRUT`,`cGentilicio`,`cNumeroCasilla`,`cEstadoCivil`,`cOcupacion`,`cEmpleador`,`cTitulo`,`cTelefonoLaboral`,`cAnexo`,`cNivelFormacion`,`cNumeroMiembroActivo`) VALUES 
 ('12166670-7',2,'',0,'ADMINISTRATIVA','ATENTO CHILE','ESTUDIANTE','','',2,'4902'),
 ('14184576-4',0,'',0,'0','','INGENIERO EN NEGOCIOS INTERNAC','','',1,''),
 ('16094925-2',2,'',0,'ESTUDIANTE','','','','',0,''),
 ('15843093-2',0,'',0,'METALURGICO','','','','',1,''),
 ('15348723-5',2,'',0,'PARVULARIA','','','','',0,''),
 ('9804928-2',2,'',0,'AUXILIAR DENTAL','CONSULTORIO SAN GERONIMO','AUXILIAR DENTAL','','',1,''),
 ('12893929-6',0,'',0,'JEFE ADMINISTRATIVO','INGEIN','','','',3,'4044'),
 ('9489372-0',1,'',1,'educadora de párvulos','fundación chaminade','educadora de parvulos','2882259','',2,'1445'),
 ('13688721-1',0,'',0,'INGENIERIA ELECTRICA','','INGENIERO CIVIL ELECTRICO','','',2,'5556'),
 ('15432427-5',2,'',0,'desempleada','','Ingenieria en Madera','','',2,'5736'),
 ('10780267-3',1,'',1,'Dueña de casa','','','','',0,''),
 ('8002680-3',1,'',0,'Analista de Sistemas','','','4213411','',0,'');
INSERT INTO `scout`.`tdyg` (`cRUT`,`cGentilicio`,`cNumeroCasilla`,`cEstadoCivil`,`cOcupacion`,`cEmpleador`,`cTitulo`,`cTelefonoLaboral`,`cAnexo`,`cNivelFormacion`,`cNumeroMiembroActivo`) VALUES 
 ('9159201-0',1,'',1,'Administrativa','','','6878638','',0,''),
 ('15348013-3',1,'',1,'estudiante','','','','',1,''),
 ('16030361-1',0,'',0,'universitario','','','','',1,''),
 ('14382614-7',0,'',1,'universitario','','','','',2,'01'),
 ('9492211-9',0,'',1,'meteorologo','','','4364568','',3,'0692'),
 ('14197827-6',0,'',0,'estudiante','','','','',1,''),
 ('13292168-7',1,'',1,'educadora de \'parvulos','','','','',2,'5718'),
 ('12902733-9',0,'',1,'Diseñador gráfico','','','','',0,''),
 ('7257877-5',1,'',1,'DUEÑA DE CASA','','','','',2,'3441'),
 ('15737411-7',2,'',0,'ESTUDIANTE','','TECNICO EN ALIMENTOS','','',2,'1'),
 ('16977402-1',0,'',0,'ESTUDIANTE','','','','',0,''),
 ('13756837-3',2,'',0,'PROFESORA','','LIC. EN FILOSOFIA','','',2,'1221'),
 ('7576611-4',1,'',1,'PROFESORA','','LIC. EN HISTORIA Y GEOGRAFIA','','',2,'3068'),
 ('15327508-4',2,'',0,'ESTUDIANTE','','','','',0,'');
INSERT INTO `scout`.`tdyg` (`cRUT`,`cGentilicio`,`cNumeroCasilla`,`cEstadoCivil`,`cOcupacion`,`cEmpleador`,`cTitulo`,`cTelefonoLaboral`,`cAnexo`,`cNivelFormacion`,`cNumeroMiembroActivo`) VALUES 
 ('4854354-5',0,'',1,'EMPLEADO FISCAL','SER. REG. CIVIL E IDENTIF.','DIB. TEC. MEC.','7822091','2091',3,'2129'),
 ('11872128-4',1,'',1,'DUEÑA DE CASA','','','','',1,''),
 ('13701626-5',0,'',0,'geologo','','Ingeniero en minas','','',0,''),
 ('7383278-0',1,'',0,'Transportista','','','','',0,''),
 ('8702397-4',1,'',0,'laboratorista','','','','',2,'5282'),
 ('13688666-5',1,'',0,'Asistente Social','','','','',3,'5627'),
 ('7895432-9',0,'',1,'Jefe operaciones','Adem Ltda','Sub tecnico  Administracion','','',2,'5773'),
 ('2-7',0,'',1,'Ingeniero','Excelsys','Egresado de Ingenieria','4997766','',1,'');
/*!40000 ALTER TABLE `tdyg` ENABLE KEYS */;


--
-- Structure for table `scout`.`tgrupo`
--

DROP TABLE IF EXISTS `scout`.`tgrupo`;
CREATE TABLE  `scout`.`tgrupo` (
  `cID` bigint(20) NOT NULL,
  `cNombre` varchar(50) NOT NULL,
  `cDistrito` bigint(20) NOT NULL,
  `cPrioridad` int(11) NOT NULL default '0',
  `cCategoria` char(1) NOT NULL,
  `cHabilitado` smallint(6) NOT NULL default '0',
  PRIMARY KEY  (`cID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `scout`.`tgrupo`
--

/*!40000 ALTER TABLE `tgrupo` DISABLE KEYS */;
INSERT INTO `scout`.`tgrupo` (`cID`,`cNombre`,`cDistrito`,`cPrioridad`,`cCategoria`,`cHabilitado`) VALUES 
 (1,'Gilberto Mendez Castro',1,38,'D',1),
 (2,'José Chaminade',1,70,'D',1),
 (3,'Barnabitas',1,30,'D',1),
 (4,'Pillan Auca',1,1,'D',0),
 (5,'Pietro Bonilli',1,13,'D',1),
 (6,'AMULEK',1,0,'D',1),
 (7,'Fernando de Aragon',1,8,'E',1);
/*!40000 ALTER TABLE `tgrupo` ENABLE KEYS */;


--
-- Structure for table `scout`.`tmmbb`
--

DROP TABLE IF EXISTS `scout`.`tmmbb`;
CREATE TABLE  `scout`.`tmmbb` (
  `cRUT` varchar(12) NOT NULL,
  `cEtapa` varchar(15) NOT NULL,
  `cUnidad` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `scout`.`tmmbb`
--

/*!40000 ALTER TABLE `tmmbb` DISABLE KEYS */;
INSERT INTO `scout`.`tmmbb` (`cRUT`,`cEtapa`,`cUnidad`) VALUES 
 ('18864187-3','',3),
 ('19313839-k','alba',3),
 ('18732055-0','Luz',3),
 ('19080079-2','alba',3),
 ('19057693-0','alba',3),
 ('18370793-0','amanecer',3),
 ('19309240-3','Guía de Vuelo',1),
 ('20225955-3','Pichón',1),
 ('18664975-3','amanecer',3),
 ('18593379-2','luz',3),
 ('20164963-3','Pichón',1),
 ('18171781-5','',4),
 ('20464167-6','Pichón',1),
 ('19842332-7','Aprendiz',1),
 ('19701065-7','Viajera',1),
 ('19918859-3','Aprendiz',1),
 ('20227057-3','Pichón',1),
 ('20120084-9','Aprendiz',1),
 ('20189899-4','Pichón',1),
 ('20298066-K','Pichón',1),
 ('20165535-8','Pichón',1),
 ('18669242-k','',7),
 ('20428312-5','Pichón',1),
 ('20248118-3','',1),
 ('20224290-1','Pichón',1),
 ('20109898-K','Pichón',1),
 ('18731657-k','',3),
 ('15739898-9','',4),
 ('19523216-4','Guia de Vuelo',1),
 ('18609251-1','',3),
 ('19421755-2','Guía de Vuelo',1),
 ('19382888-4','Guía de Vuelo',1),
 ('20040318-5','Aprendiz',1),
 ('20455781-0','',2),
 ('19685973-K','Guía de Vuelo',1);
INSERT INTO `scout`.`tmmbb` (`cRUT`,`cEtapa`,`cUnidad`) VALUES 
 ('19522565-6','Guía de Vuelo',1),
 ('19925464-2','Aprendiz',1),
 ('19038998-7','',4),
 ('18628275-2','',4),
 ('18605856-9','',4),
 ('21595764-0','Pichón',1),
 ('19288971-5','',4),
 ('19423175-k','',2),
 ('18974286-k','',1),
 ('17612704-k','',1),
 ('18990452-5','',1),
 ('19680235-5','',1),
 ('17271201-0','',6),
 ('19245364-K','',4),
 ('17599071-2','descubrimiento',5),
 ('19410677-7','',4),
 ('18864186-5','cernicalo',4),
 ('18533447-3','',4),
 ('19290099-9','',4),
 ('19427297-9','',3),
 ('19953415-7','',1),
 ('19959458-3','',2),
 ('19643818-1','',2),
 ('19319943-7','',4),
 ('19955423-9','',2),
 ('19193872-0','',2),
 ('20200481-4','',2),
 ('20327783-0','',2),
 ('19831151-0','',1),
 ('19570546-1','',1),
 ('19226492-8','',3),
 ('18927560-9','',3),
 ('18845772-k','',3),
 ('19136836-3','',3),
 ('18211647-5','',4),
 ('18187697-2','',4),
 ('18060623-8','',5),
 ('17672144-8','',5),
 ('1767489-7','',5),
 ('17707415-2','',5),
 ('18210294-6','',5),
 ('17024077-4','',6);
INSERT INTO `scout`.`tmmbb` (`cRUT`,`cEtapa`,`cUnidad`) VALUES 
 ('17242482-1','',6),
 ('20237963-k','',2),
 ('20243587-4','',2),
 ('20243470-3','',2),
 ('19700237-9','',2),
 ('19932973-1','',2),
 ('20187971-k','',2),
 ('20278314-7','diestro',2),
 ('19924735-2','',2),
 ('19830775-0','',2),
 ('19526497-k','',2),
 ('20200456-3','',2),
 ('19421606-8','Guía de Vuelo',1),
 ('20132475-0','',2),
 ('19784712-3','',2),
 ('19409412-4','',2),
 ('19993388-4','',2),
 ('20048449-5','',2),
 ('20052940-5','',2),
 ('18730952-2','amanecer',3),
 ('19406298-2','alba',3),
 ('19671346-8','Diestro',2),
 ('20469804-k','Lobezno',2),
 ('20387603-3','Lobezno',2),
 ('19501100-1','Diestro',2),
 ('20045631-9','Saltador',2),
 ('19289855-2','Cazador',2),
 ('19742224-6','Diestro',2),
 ('19743816-9','Diestro',2),
 ('19733750-8','',1),
 ('19310817-2','',1),
 ('19441597-4','',1),
 ('19407788-2','',1),
 ('19513830-3','',1),
 ('19729092-7','',1),
 ('19526084-2','',1),
 ('19422179-7','',1),
 ('20041822-0','Aprendiz',1),
 ('18622963-0','',3),
 ('18548136-0','',3);
INSERT INTO `scout`.`tmmbb` (`cRUT`,`cEtapa`,`cUnidad`) VALUES 
 ('18003769-1','',3),
 ('18355507-3','',3),
 ('18192394-6','',3),
 ('18927505-6','',3),
 ('18537051-8','',3),
 ('19035041-k','',3),
 ('1-9','',7);
/*!40000 ALTER TABLE `tmmbb` ENABLE KEYS */;


--
-- Structure for table `scout`.`tnacionalidad`
--

DROP TABLE IF EXISTS `scout`.`tnacionalidad`;
CREATE TABLE  `scout`.`tnacionalidad` (
  `cID` bigint(20) NOT NULL,
  `cNombre` varchar(50) NOT NULL,
  `cPrioridad` int(11) NOT NULL default '0',
  PRIMARY KEY  (`cID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `scout`.`tnacionalidad`
--

/*!40000 ALTER TABLE `tnacionalidad` DISABLE KEYS */;
INSERT INTO `scout`.`tnacionalidad` (`cID`,`cNombre`,`cPrioridad`) VALUES 
 (1,'Antigua y Barbuda',0),
 (2,'Antillas Holandesas',0),
 (3,'Argentina',0),
 (4,'Aruba',0),
 (5,'Bahamas',0),
 (6,'Barbados',0),
 (7,'Belice',0),
 (8,'Bolivia',0),
 (9,'Brasil',0),
 (10,'Canadá',0),
 (11,'Chile',160),
 (12,'Colombia',0),
 (13,'Costa ',0),
 (14,'Cuba',0),
 (15,'Dominica ',0),
 (16,'Ecuador',0),
 (17,'El Salvador',0),
 (18,'Estados ',0),
 (19,'Granada ',0),
 (20,'Groenlandia ',0),
 (21,'Guadalupe ',0),
 (22,'Guatemala',0),
 (23,'Guyana Francesa',0),
 (24,'Guyana ',0),
 (25,'Haití ',0),
 (26,'Honduras',0),
 (27,'Islas Filipinas',0),
 (28,'Islas Caiman',0),
 (29,'Islas Virgenes UK',0),
 (30,'Jamaica ',0),
 (31,'Martinica ',0),
 (32,'Montserrat ',0),
 (33,'México',0),
 (34,'Nicaragua',0),
 (35,'Panamá',0),
 (36,'Paraguay',0),
 (37,'Perú',0),
 (38,'Puerto Rico',0),
 (39,'República Dominicana',0),
 (40,'San Cristobal-Nevis',0),
 (41,'San Vincente y las Granadinas',0),
 (42,'San Marino',0),
 (43,'Santa Lucia',0),
 (44,'Surinam ',0);
INSERT INTO `scout`.`tnacionalidad` (`cID`,`cNombre`,`cPrioridad`) VALUES 
 (45,'Trinidad y Tobago',0),
 (46,'Turcos ',0),
 (47,'Uruguay',0),
 (48,'Venezuela',0),
 (49,'Bases antárticas',0),
 (50,'Institutos antárticos ',0),
 (51,'Afganistán ',0),
 (52,'Arabia Saudita',0),
 (53,'Armenia ',0),
 (54,'Azerbaiyán ',0),
 (55,'Bahrein ',0),
 (56,'Bangladesh ',0),
 (57,'Bhután ',0),
 (58,'Brunei ',0),
 (59,'Camboya ',0),
 (60,'China ',0),
 (61,'Chipre ',0),
 (62,'Corea del Norte',0),
 (63,'Corea del Sur',0),
 (64,'Emiratos Arabes',0),
 (65,'Filipinas ',0),
 (66,'Georgia ',0),
 (67,'Hong Kong',0),
 (68,'India ',0),
 (69,'Indonesia ',0),
 (70,'Iraq ',0),
 (71,'Irán ',0),
 (72,'Israel ',0),
 (73,'Japón ',0),
 (74,'Jordania ',0),
 (75,'Kazajstán ',0),
 (76,'Kirguistán ',0),
 (77,'Kuwait ',0),
 (78,'Laos ',0),
 (79,'Líbano ',0),
 (80,'Macao ',0),
 (81,'Malasia ',0),
 (82,'Maldivas ',0),
 (83,'Mongolia ',0),
 (84,'Myanmar ',0),
 (85,'Nepal ',0),
 (86,'Omán ',0),
 (87,'Pakistán ',0),
 (88,'Palestina ',0),
 (89,'Qatar ',0);
INSERT INTO `scout`.`tnacionalidad` (`cID`,`cNombre`,`cPrioridad`) VALUES 
 (90,'Rusia',0),
 (91,'Singapur ',0),
 (92,'Siria ',0),
 (93,'Sri Lanka',0),
 (94,'Tailandia ',0),
 (95,'Taiwán ',0),
 (96,'Tayikistán ',0),
 (97,'Timor Oriental',0),
 (98,'Turkmenistán ',0),
 (99,'Turquía ',0),
 (100,'Uzbekistán ',0),
 (101,'Vietnam ',0),
 (102,'Yemén ',0),
 (103,'Alemania ',0),
 (104,'Andorra ',0),
 (105,'Armenia',0),
 (106,'Austria ',0),
 (107,'Azerbaiyán',0),
 (108,'Bielorrusia ',0),
 (109,'Bosnia ',0),
 (110,'Bulgaria ',0),
 (111,'Bélgica ',0),
 (112,'Chipre',0),
 (114,'Croacia ',0),
 (115,'Dinamarca ',0),
 (116,'Escocia',0),
 (117,'Eslovaquia ',0),
 (118,'Eslovenia ',0),
 (119,'España',0),
 (120,'Estonia ',0),
 (121,'Finlandia ',0),
 (122,'Francia ',0),
 (123,'Gales',0),
 (124,'Georgia',0),
 (125,'Grecia ',0),
 (126,'Groenlandia',0),
 (127,'Holanda',0),
 (128,'Hungría ',0),
 (129,'Inglaterra',0),
 (130,'Irlanda ',0),
 (131,'Islandia ',0),
 (132,'Islas Virgenes US',0),
 (133,'Italia ',0),
 (134,'Letonia ',0);
INSERT INTO `scout`.`tnacionalidad` (`cID`,`cNombre`,`cPrioridad`) VALUES 
 (135,'Liechtenstein ',0),
 (136,'Lituania ',0),
 (137,'Luxemburgo ',0),
 (138,'Macedonia ',0),
 (139,'Malta ',0),
 (140,'Moldavia ',0),
 (141,'Mónaco ',0),
 (142,'Noruega ',0),
 (143,'Países ',0),
 (144,'Polonia ',0),
 (145,'Portugal ',0),
 (146,'Reino ',0),
 (147,'República ',0),
 (148,'Rumanía ',0),
 (149,'Rusia ',0),
 (150,'San ',0),
 (151,'Serbia ',0),
 (152,'Suecia ',0),
 (153,'Suiza ',0),
 (154,'Turquía',0),
 (155,'Ucrania ',0),
 (156,'Yugoslavia',0),
 (157,'Albania ',0),
 (158,'Alemania ',0),
 (159,'Andorra ',0),
 (160,'Armenia',0),
 (161,'Austria ',0),
 (162,'Azerbaiyán',0),
 (163,'Bielorrusia ',0),
 (164,'Bosnia ',0),
 (165,'Bulgaria ',0),
 (166,'Bélgica ',0),
 (167,'Chipre',0),
 (168,'Ciudad ',0),
 (169,'Croacia ',0),
 (170,'Dinamarca ',0),
 (171,'Escocia',0),
 (172,'Eslovaquia ',0),
 (173,'Eslovenia ',0),
 (174,'España',0),
 (175,'Estonia ',0),
 (176,'Finlandia ',0),
 (177,'Francia ',0),
 (178,'Gales',0),
 (179,'Georgia',0);
INSERT INTO `scout`.`tnacionalidad` (`cID`,`cNombre`,`cPrioridad`) VALUES 
 (180,'Grecia ',0),
 (181,'Groenlandia',0),
 (182,'Holanda',0),
 (183,'Hungría ',0),
 (184,'Inglaterra',0),
 (185,'Irlanda ',0),
 (186,'Islandia ',0),
 (187,'Islas ',0),
 (188,'Italia ',0),
 (189,'Letonia ',0),
 (190,'Liechtenstein ',0),
 (191,'Lituania ',0),
 (192,'Luxemburgo ',0),
 (193,'Macedonia ',0),
 (194,'Malta ',0),
 (195,'Moldavia ',0),
 (196,'Mónaco ',0),
 (197,'Noruega ',0),
 (198,'Países ',0),
 (199,'Polonia ',0),
 (200,'Portugal ',0),
 (201,'Reino ',0),
 (202,'República ',0),
 (203,'Rumanía ',0),
 (204,'Rusia ',0),
 (205,'San ',0),
 (206,'Serbia ',0),
 (207,'Suecia ',0),
 (208,'Suiza ',0),
 (209,'Turquía',0),
 (210,'Ucrania ',0),
 (211,'Yugoslavia',0),
 (212,'Australia ',0),
 (213,'Christmas ',0),
 (214,'Fiji ',0),
 (215,'Guam ',0),
 (216,'Hawaii',0),
 (217,'Isla ',0),
 (218,'Isla ',0),
 (219,'Islas ',0),
 (220,'Islas ',0),
 (221,'Islas ',0),
 (222,'Islas ',0),
 (223,'Islas ',0),
 (224,'Kiribati ',0),
 (225,'Marianas ',0);
INSERT INTO `scout`.`tnacionalidad` (`cID`,`cNombre`,`cPrioridad`) VALUES 
 (226,'Micronesia ',0),
 (227,'Naurú ',0),
 (228,'Niue ',0),
 (229,'Norfolk ',0),
 (230,'Nueva ',0),
 (231,'Nueva ',0),
 (232,'Palau ',0),
 (233,'Papúa ',0),
 (234,'Polinesia ',0),
 (235,'Samoa ',0),
 (236,'Samoa ',0),
 (237,'Tokelau ',0),
 (238,'Tonga ',0),
 (239,'Tuvalu ',0),
 (240,'Vanuatu ',0),
 (241,'Wallis ',0),
 (242,'Angola ',0),
 (243,'Argelia ',0),
 (244,'Benin ',0),
 (245,'Botsuana ',0),
 (246,'Burkina ',0),
 (247,'Burundi ',0),
 (248,'Cabo ',0),
 (249,'Camerún ',0),
 (250,'Ceuta',0),
 (251,'Chad ',0),
 (252,'Comoras ',0),
 (253,'Congo ',0),
 (254,'Costa ',0),
 (255,'Djibouti ',0),
 (256,'Egipto ',0),
 (257,'Eritrea ',0),
 (258,'Etiopía ',0),
 (259,'Gabón ',0),
 (260,'Gambia ',0),
 (261,'Ghana ',0),
 (262,'Guinea ',0),
 (263,'Guinea ',0),
 (264,'Guinea ',0),
 (265,'Kenia ',0),
 (266,'Lesotho ',0),
 (267,'Liberia ',0),
 (268,'Libia ',0),
 (269,'Madagascar ',0),
 (270,'Malawi ',0),
 (271,'Mali ',0),
 (272,'Marruecos ',0);
INSERT INTO `scout`.`tnacionalidad` (`cID`,`cNombre`,`cPrioridad`) VALUES 
 (273,'Mauricio ',0),
 (274,'Mauritania ',0),
 (275,'Mayotte ',0),
 (276,'Melilla',0),
 (277,'Mozambique ',0),
 (278,'Namibia ',0),
 (279,'Nigeria ',0),
 (280,'Níger ',0),
 (281,'República ',0),
 (282,'República ',0),
 (283,'Reunión ',0),
 (284,'Ruanda ',0),
 (285,'Santa Sede',0),
 (287,'Senegal ',0),
 (288,'Seychelles ',0),
 (289,'Sierra Leona',0),
 (290,'Somalia ',0),
 (291,'Sudáfrica ',0),
 (292,'Sudán ',0),
 (293,'Swazilandia ',0),
 (294,'Sáhara ',0),
 (295,'Tanzania ',0),
 (296,'Togo ',0),
 (297,'Túnez ',0),
 (298,'Uganda ',0),
 (299,'Zambia ',0),
 (300,'Zimbabwe ',0);
/*!40000 ALTER TABLE `tnacionalidad` ENABLE KEYS */;


--
-- Structure for table `scout`.`tpersona`
--

DROP TABLE IF EXISTS `scout`.`tpersona`;
CREATE TABLE  `scout`.`tpersona` (
  `cID` bigint(20) NOT NULL,
  `cRUT` varchar(10) NOT NULL,
  `cMasculino` smallint(6) NOT NULL,
  `cFechaRegistro` datetime NOT NULL,
  `cNombre` varchar(50) NOT NULL,
  `cApellidoPaterno` varchar(20) NOT NULL,
  `cApellidoMaterno` varchar(20) NOT NULL,
  `cDireccion` varchar(20) NOT NULL,
  `cNumero` varchar(5) NOT NULL,
  `cVilla` varchar(30) NOT NULL,
  `cBlock` varchar(5) NOT NULL,
  `cDepartamento` varchar(5) NOT NULL,
  `cComuna` bigint(20) NOT NULL,
  `cTelefono` varchar(12) NOT NULL,
  `cCelular` varchar(12) NOT NULL,
  `cMail` varchar(50) NOT NULL,
  `cTabla` varchar(10) NOT NULL,
  PRIMARY KEY  (`cID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `scout`.`tpersona`
--

/*!40000 ALTER TABLE `tpersona` DISABLE KEYS */;
INSERT INTO `scout`.`tpersona` (`cID`,`cRUT`,`cMasculino`,`cFechaRegistro`,`cNombre`,`cApellidoPaterno`,`cApellidoMaterno`,`cDireccion`,`cNumero`,`cVilla`,`cBlock`,`cDepartamento`,`cComuna`,`cTelefono`,`cCelular`,`cMail`,`cTabla`) VALUES 
 (-2854199468373250890,'2-7',1,'2008-03-29 00:00:00','Claudio Rodrigo','Moscoso','Howard','Malveira','540','ciudad del sol','','',315,'3146420','','claudio.moscoso@gmail.com','tDYG'),
 (0,'12166670-7',0,'2007-05-23 00:00:00','MARCIA OLIVIA','SANDOVAL','FARIAS','PJE. DAMIAN','1691','DOÑA GARBIELA','','',315,'2-5361611','9-3788354','marcia_sandovalf@hotmail.com','tDYG'),
 (1,'14184576-4',1,'2007-05-23 00:00:00','FELIPE FRANCISCO','IBAÑEZ','ESCOBAR','PJE. LAS CALESAS','1111','SAN GERONIMO','','',315,'2-8509029','8-3575908','gibson19@hotmail.com','tDYG'),
 (2,'16094925-2',0,'2007-05-23 00:00:00','VALENTINA CRISTAL','BRAVO','FARIAS','PJE. DAMIAN','1691','DOÑA GARBIELA','','',315,'5361611','8-8590139','about_boo@hotmail.com','tDYG'),
 (3,'15843093-2',1,'2007-05-23 00:00:00','FELIPE ANDRES','PEÑALOZA','PIZARRO','DOMINGO TOCORNAL','462','DOMINGO TOCORNAL','','',315,'0','','fonaco@gmail.com','tDYG'),
 (4,'15348723-5',0,'2007-05-23 00:00:00','MARIA EUGENIA','DURAN','RAMIREZ','LIRQUEN','7888','0','','',294,'5252740','8-8218417','quenitahermosa_22@hotmail.com','tDYG');
INSERT INTO `scout`.`tpersona` (`cID`,`cRUT`,`cMasculino`,`cFechaRegistro`,`cNombre`,`cApellidoPaterno`,`cApellidoMaterno`,`cDireccion`,`cNumero`,`cVilla`,`cBlock`,`cDepartamento`,`cComuna`,`cTelefono`,`cCelular`,`cMail`,`cTabla`) VALUES 
 (5,'9804928-2',0,'2007-05-24 00:00:00','SANDRA  CAROLINA','MORENO','ULLOA','UNO NORTE','422','DOMINGO TOCORNAL','','',315,'2-8512911','8-2353074','','tDYG'),
 (6,'12893929-6',1,'2007-05-24 00:00:00','CLAUDIO ENRIQUE','ROMERO ','CABELLO','PROFESOR ALCAINO','0742','','','24',315,'2-8511484','8-7909020','claudioromeroc@hotmail.com','tDYG'),
 (7,'9489372-0',0,'2007-05-25 00:00:00','Angélica Cecilia','González ','Salazar','Cerro Vilcanao Orien','2815','los silos','','',315,'7923929','09-7241900','angelgonza40@gmail.com','tDYG'),
 (8,'18864187-3',0,'2007-05-25 00:00:00','Catalina Paz','Montecinos','González','Cerro Vilcanao Orien','2815','Los Silos','','',315,'7923929','','kta_osita_adorable@hotmail.com','tMMBB'),
 (9,'13688721-1',1,'2007-05-25 00:00:00','JORGE LUIS','ESTRADA','GONZALEZ','PSJE. EL VIRREY','4596','ALTOS DE RUCALHUE','','',315,'2669866','82896690','JOESTRADA@GMAIL.COM','tDYG'),
 (10,'15432427-5',0,'2007-05-25 00:00:00','Karen Judith','Alvarez','Palacios','Psje El Virrey','4596','Altos de Rucalhue','','',315,'2669866','9-3122296','guiakarencita@gmail.com','tDYG');
INSERT INTO `scout`.`tpersona` (`cID`,`cRUT`,`cMasculino`,`cFechaRegistro`,`cNombre`,`cApellidoPaterno`,`cApellidoMaterno`,`cDireccion`,`cNumero`,`cVilla`,`cBlock`,`cDepartamento`,`cComuna`,`cTelefono`,`cCelular`,`cMail`,`cTabla`) VALUES 
 (11,'19313839-k',0,'2007-05-25 00:00:00','Valentina  Francisca','Jaña ','Améstica','Toscana','226','Ciudad del Sol','','',315,'3186696','','','tMMBB'),
 (12,'18732055-0',0,'2007-05-25 00:00:00','Mónica Melitza','Iturrieta','Tobar','Apollinaire','3269','','','',315,'2956961','','','tMMBB'),
 (13,'19080079-2',0,'2007-05-27 00:00:00','Alexandra Katerina ','Urra','Pozo','Pasaje Curarrehue ','337','','','',315,'3153228','','','tMMBB'),
 (14,'19057693-0',0,'2007-05-27 00:00:00','Belén Ignacia','Rojas','Carrasco','Pasaje Pemuco','2784','','','',315,'4932693','','','tMMBB'),
 (15,'18370793-0',0,'2007-05-27 00:00:00','katherine','Pino','Vergara','Miguel Ángel','420','','','',315,'3131294','','','tMMBB'),
 (16,'19309240-3',0,'2007-05-28 00:00:00','Pamela Andrea','López','Quintero','Pje. El Ruil','2666','Villa Los Alamos','','',315,'7692144','','','tMMBB'),
 (17,'20225955-3',0,'2007-05-28 00:00:00','Aylin Nicolet','Cofre','Silva','Pje. Hernán Cortés','806','Villa Los Conquistadores','','',315,'2883262','','','tMMBB');
INSERT INTO `scout`.`tpersona` (`cID`,`cRUT`,`cMasculino`,`cFechaRegistro`,`cNombre`,`cApellidoPaterno`,`cApellidoMaterno`,`cDireccion`,`cNumero`,`cVilla`,`cBlock`,`cDepartamento`,`cComuna`,`cTelefono`,`cCelular`,`cMail`,`cTabla`) VALUES 
 (18,'18664975-3',0,'2007-05-28 00:00:00','Lorena del Pilar','Canales ','Urra','Luis matte ','999','','','',315,'5422044','','','tMMBB'),
 (19,'18593379-2',0,'2007-05-28 00:00:00','Tamara Paola','Valencia ','Gutiérrez','Piedad ','372','','','',315,'3112458','','','tMMBB'),
 (20,'20164963-3',0,'2007-05-28 00:00:00','Camila Francisca','Calderón','Espinosa','Pje. Los Manglares','2537','Villa La Foresta','','',315,'3146433','','','tMMBB'),
 (21,'10780267-3',0,'2007-05-28 00:00:00','Karem Liliana','Silva','Fuentes','Pje. Hernán Cortés','806','Villa Los Conquistadores','','',315,'2883262','','','tDYG'),
 (24,'18171781-5',1,'2007-05-29 00:00:00','Carlos alexis','Leyton ','Valencia','Nonato Coo','3295','','','',315,'8752450','','','tMMBB'),
 (25,'8002680-3',0,'2007-05-29 00:00:00','Mariela de los Angeles','Quintero','Moreno','Pje. El Ruil','2666','Villa Los Alamos','','',315,'7692144','','','tDYG'),
 (26,'9159201-0',0,'2007-05-29 00:00:00','Rosa Elsa','Espinosa','Navarro','Pje. Los Manglares','2537','Villa La Foresta','','',315,'3146433','','','tDYG');
INSERT INTO `scout`.`tpersona` (`cID`,`cRUT`,`cMasculino`,`cFechaRegistro`,`cNombre`,`cApellidoPaterno`,`cApellidoMaterno`,`cDireccion`,`cNumero`,`cVilla`,`cBlock`,`cDepartamento`,`cComuna`,`cTelefono`,`cCelular`,`cMail`,`cTabla`) VALUES 
 (27,'20464167-6',0,'2007-05-29 00:00:00','Darinka Trinidad','Jimenez','Salinas','Pje. Santa Alicia','2260','Parque San Carlos','','',315,'4935844','','','tMMBB'),
 (28,'19842332-7',0,'2007-05-29 00:00:00','Imara Belén','Malverde','Campos','Pje. Isla de Pascua','3183','Villa Real','','',315,'7921240','','','tMMBB'),
 (29,'19701065-7',0,'2007-05-29 00:00:00','Catalina Ignacia','Oviedo','Campos','Calle Del Capitolio','3035','Parque Campanario','','',315,'3184054','','','tMMBB'),
 (30,'19918859-3',0,'2007-05-29 00:00:00','Ashlie Solange','Avalos','Contreras','Ejercito Libertadore','4156','Valle Verde II','','167',315,'091655856','','','tMMBB'),
 (31,'20227057-3',0,'2007-05-29 00:00:00','Ignacia Gabriela','Rivera','Cortés','Pje. 8 ','0672','Pobl. Papelera','','',315,'078516104','','','tMMBB'),
 (32,'20120084-9',0,'2007-05-29 00:00:00','Javiera Ignacia','Beltran','Silva','Pje. Trehualemu','2755','Villa Los Productores 2','','',315,'3189107','','','tMMBB');
INSERT INTO `scout`.`tpersona` (`cID`,`cRUT`,`cMasculino`,`cFechaRegistro`,`cNombre`,`cApellidoPaterno`,`cApellidoMaterno`,`cDireccion`,`cNumero`,`cVilla`,`cBlock`,`cDepartamento`,`cComuna`,`cTelefono`,`cCelular`,`cMail`,`cTabla`) VALUES 
 (33,'20189899-4',0,'2007-05-29 00:00:00','Catalina Belén','Aguilera','Jabes','Ejercito Libertador','4104','Valle Verde II','','',315,'7912615','','','tMMBB'),
 (34,'20298066-K',0,'2007-05-29 00:00:00','Gabriela Paz','Vergara','Abarca','J. de Dios Malebran','2311','Villa La Foresta','','',315,'4931551','','','tMMBB'),
 (35,'20165535-8',0,'2007-05-29 00:00:00','Souling Fernanda','Marquez','Guzmán','Francisco de Rivera','3210','Villa Los Conquistadores','','',315,'085940923','','','tMMBB'),
 (36,'18669242-k',1,'2007-05-29 00:00:00','simon','bernechea ','moreno','pasaje tres ujina','1193','las torres','','',315,'5816272','','','tMMBB'),
 (37,'20428312-5',0,'2007-05-29 00:00:00','Catalina Valeria','Echeverria','Montoya','La Manzanilla','1816','Villa Casas de las Viñas II','','',315,'8534287','','','tMMBB'),
 (38,'20248118-3',0,'2007-05-29 00:00:00','genesis rachel','peña','prado','obispooscar romero','1454','las torres','','',315,'2959054','','','tMMBB');
INSERT INTO `scout`.`tpersona` (`cID`,`cRUT`,`cMasculino`,`cFechaRegistro`,`cNombre`,`cApellidoPaterno`,`cApellidoMaterno`,`cDireccion`,`cNumero`,`cVilla`,`cBlock`,`cDepartamento`,`cComuna`,`cTelefono`,`cCelular`,`cMail`,`cTabla`) VALUES 
 (39,'20224290-1',0,'2007-05-29 00:00:00','Nicole Alejandra','Jaque','Quiroz','Calle Inéz de Suárez','13826','Pobl. Santiago Nuevo Extremo','','',315,'092065459','','','tMMBB'),
 (40,'20109898-K',0,'2007-05-29 00:00:00','Ignacia Paz','Laurent','Cerda','Pje. El Santuario','294','Parque Campanario','','',315,'3131670','','','tMMBB'),
 (41,'18731657-k',0,'2007-05-29 00:00:00','magda dennisse','alfarn','menares','rimbau','3012','vicente huidobro','','',315,'7250224','','','tMMBB'),
 (42,'15739898-9',1,'2007-05-29 00:00:00','juan ernesto','rosado','hernandez','julio segundo','1750','gabriela','','',315,'5429340','','','tMMBB'),
 (43,'19523216-4',0,'2007-05-29 00:00:00','Francisca Andrea','Rodríguez','Díaz','Pje. Caña de la Indi','2630','Villa Los Alamos','','',315,'2656956','','','tMMBB'),
 (44,'18609251-1',0,'2007-05-29 00:00:00','ninoska aimara','diaz','gallardo','pasaje ninquihue','12903','los productores','','',315,'2959054','','','tMMBB');
INSERT INTO `scout`.`tpersona` (`cID`,`cRUT`,`cMasculino`,`cFechaRegistro`,`cNombre`,`cApellidoPaterno`,`cApellidoMaterno`,`cDireccion`,`cNumero`,`cVilla`,`cBlock`,`cDepartamento`,`cComuna`,`cTelefono`,`cCelular`,`cMail`,`cTabla`) VALUES 
 (45,'19421755-2',0,'2007-05-29 00:00:00','Javiera Angélica','Rojas','Castillo','Av. 4 Oriente','3799','Villa Los Aromos','','',315,'5474503','','','tMMBB'),
 (46,'19382888-4',0,'2007-05-29 00:00:00','Lorena Andrea','Urra','Nuñez','Pje. Curarrehue','337','Villa Araucaría','','',315,'3153228','','','tMMBB'),
 (47,'20040318-5',0,'2007-05-29 00:00:00','Lorena Amanda','Muñoz','Rivas','Miguel Angel','03740','Las Rosas','','',315,'5423336','','','tMMBB'),
 (48,'20455781-0',1,'2007-05-29 00:00:00','fabian eduardo','aguilera','muñoz','miguel angel','1845','los nogales','','',315,'8470043','','','tMMBB'),
 (49,'19685973-K',0,'2007-05-29 00:00:00','Paula Carolina','Laurent','Cerda','Pje. El Santuario','294','Parque Campanario','','',315,'3131670','','','tMMBB'),
 (50,'19522565-6',0,'2007-05-29 00:00:00','Isidora Ignacia','Ayala','Madariaga','Arq. Eugenio Cerda','675','Villa Los Alamos','','',315,'7231204','','','tMMBB');
INSERT INTO `scout`.`tpersona` (`cID`,`cRUT`,`cMasculino`,`cFechaRegistro`,`cNombre`,`cApellidoPaterno`,`cApellidoMaterno`,`cDireccion`,`cNumero`,`cVilla`,`cBlock`,`cDepartamento`,`cComuna`,`cTelefono`,`cCelular`,`cMail`,`cTabla`) VALUES 
 (51,'19925464-2',0,'2007-05-29 00:00:00','Valentina Paz','Llantén','Santander','Pje Corona del Poeta','2603','Villa Los Alamos','','',315,'3186216','','','tMMBB'),
 (52,'15348013-3',0,'2007-05-29 00:00:00','barbara makarena','rubio','zapata','jose miguel carrera','130','','','',159,'4932132','','','tDYG'),
 (53,'16030361-1',1,'2007-05-29 00:00:00','patricio alejandro','barrera','ovalle','avenida creta','3070','','','',315,'4932132','','','tDYG'),
 (54,'19038998-7',1,'2007-05-29 00:00:00','jainson camilo','barrera ','ovalle','av. creta','3070','','','',315,'5459323','','','tMMBB'),
 (55,'14382614-7',1,'2007-05-29 00:00:00','hernan andres','barrera','ovalle','jose miguel carrera','130','','','',293,'4932132','','','tDYG'),
 (56,'18628275-2',1,'2007-05-29 00:00:00','richard alexander','montenegro','muñoz','pasaje diguillin','2864','los productores','','',315,'4935611','','','tMMBB'),
 (57,'18605856-9',1,'2007-05-29 00:00:00','ignacio alonso','molina','canales','pasaje pirita','1544','la salud','','',315,'5362618','','','tMMBB');
INSERT INTO `scout`.`tpersona` (`cID`,`cRUT`,`cMasculino`,`cFechaRegistro`,`cNombre`,`cApellidoPaterno`,`cApellidoMaterno`,`cDireccion`,`cNumero`,`cVilla`,`cBlock`,`cDepartamento`,`cComuna`,`cTelefono`,`cCelular`,`cMail`,`cTabla`) VALUES 
 (58,'21595764-0',0,'2007-05-29 00:00:00','Katherine Anais','Rivas','Larenas','Pje. El Santuario','337','Parque Campanario','','',315,'094417408','','','tMMBB'),
 (59,'19288971-5',1,'2007-05-29 00:00:00','patricio esteban','lopez','zarate','padre hurtado','1148','los productores','','',315,'2899118','','','tMMBB'),
 (60,'19423175-k',1,'2007-05-29 00:00:00','Cristobal Andres','Ortiz ','Maureira','Lago Atravesado','1215','','','',315,'0','09-8624012','','tMMBB'),
 (61,'18974286-k',0,'2007-05-29 00:00:00','Anais Karim','Ibañez','Reyes','Psje. Barreiro','481','C. del Sol','','',315,'3130334','0','','tMMBB'),
 (62,'17612704-k',0,'2007-05-29 00:00:00','Javiera Alexandra','Castro ','Alegria','La Parroquia','0146','','','',315,'8425365','','','tMMBB'),
 (63,'18990452-5',0,'2007-05-29 00:00:00','Valentina Fernanda','Sopia','Veliz','Psje. Cerro de la ig','01528','','','',315,'2680672','','','tMMBB'),
 (64,'19680235-5',0,'2007-05-29 00:00:00','JAVIERA ESPERANZA','SANCHEZ ','ARMIJO','ODIVELAS NORTE','875','','','',315,'7911158','','','tMMBB');
INSERT INTO `scout`.`tpersona` (`cID`,`cRUT`,`cMasculino`,`cFechaRegistro`,`cNombre`,`cApellidoPaterno`,`cApellidoMaterno`,`cDireccion`,`cNumero`,`cVilla`,`cBlock`,`cDepartamento`,`cComuna`,`cTelefono`,`cCelular`,`cMail`,`cTabla`) VALUES 
 (65,'17271201-0',0,'2007-05-29 00:00:00','NICOLE MARION','VEGA ','ARMIJO','ODIVELAS NORTE','875','','','',315,'7911158','','','tMMBB'),
 (66,'19245364-K',1,'2007-05-29 00:00:00','FERNANDO ANDRES','GAETE','BASUALTO','COQUIMBO','2151','','','',315,'3197240','','','tMMBB'),
 (67,'17599071-2',1,'2007-05-29 00:00:00','Pablo Sebastián','Leyton ','Valencia','Nonato Coo','3295','','','',315,'8752450','','','tMMBB'),
 (68,'19410677-7',1,'2007-05-29 00:00:00','FELIPE IGNACIO ','NUÑEZ ','LOBOS','LOS HUALOS','2648','','','',315,'2656392','','','tMMBB'),
 (69,'18864186-5',1,'2007-05-29 00:00:00','Arturo Andrés','Montecinos','González','Cerro Vilcanao Orien','2815','Los Silos','','',315,'7923929','','','tMMBB'),
 (70,'9492211-9',1,'2007-05-29 00:00:00','Jaime Gerardo','Leyton','Aguirre','los castaños ','615','','','',315,'7231634','09-5694028','','tDYG'),
 (71,'18533447-3',1,'2007-05-29 00:00:00','FRACISCO IGNACIO','NUÑEZ','LOBOS','LOS HUALOS','2648','','','',315,'2656392','','','tMMBB');
INSERT INTO `scout`.`tpersona` (`cID`,`cRUT`,`cMasculino`,`cFechaRegistro`,`cNombre`,`cApellidoPaterno`,`cApellidoMaterno`,`cDireccion`,`cNumero`,`cVilla`,`cBlock`,`cDepartamento`,`cComuna`,`cTelefono`,`cCelular`,`cMail`,`cTabla`) VALUES 
 (72,'19290099-9',1,'2007-05-29 00:00:00','PAUL SEBASTIAN JOSE','ORTIZ ','MAUREIRA','LAGO ATRAVESADO','1215','','','',315,'0','09-8624012','','tMMBB'),
 (73,'19427297-9',0,'2007-05-29 00:00:00','SIMONETTE ELISABETH','MORIZ','OLIVARES','AZORES PON.','1971','','','º',315,'4933345','','','tMMBB'),
 (74,'19953415-7',0,'2007-05-29 00:00:00','NATALIA PASCAL','MARTINEZ ','MORAGA','LOS ALAMOS','2632','','','',315,'3585481','','','tMMBB'),
 (75,'19959458-3',1,'2007-05-29 00:00:00','IGNACIO ANDRES','REYES','DELGADO','PSJE. TAMARICO','720','','','',315,'3187937','','','tMMBB'),
 (76,'19643818-1',1,'2007-05-29 00:00:00','DIEGO ALEJANDRO ','MENA','CABEZAS','AZORES OR.','1984','','','',315,'3189843','','','tMMBB'),
 (77,'19319943-7',1,'2007-05-29 00:00:00','Jorge  Patricio','Figueroa','Fritz','Cardenal ','211','Campanario','','',315,'3132382','','','tMMBB'),
 (78,'14197827-6',1,'2007-05-29 00:00:00','Carlos Rodrigo','Romero','Pizarro','Tolpán ','603','Los naranjos','','',315,'2880356','','pitnes@hotmail.com','tDYG');
INSERT INTO `scout`.`tpersona` (`cID`,`cRUT`,`cMasculino`,`cFechaRegistro`,`cNombre`,`cApellidoPaterno`,`cApellidoMaterno`,`cDireccion`,`cNumero`,`cVilla`,`cBlock`,`cDepartamento`,`cComuna`,`cTelefono`,`cCelular`,`cMail`,`cTabla`) VALUES 
 (79,'13292168-7',0,'2007-05-29 00:00:00','Paula Carolina','Romero','Pizarro','Huentelauquén ','724','','','',315,'7854289','','','tDYG'),
 (80,'12902733-9',1,'2007-05-29 00:00:00','Leonardo Antonio','Sepúlveda','Catalán','Huentelauquén','724','','','',315,'7854289','','','tDYG'),
 (81,'19955423-9',1,'2007-05-29 00:00:00','Matías Ignacio','Araya','Nuñez','Julio césar ','3163','','','',315,'7923929','','','tMMBB'),
 (82,'19193872-0',1,'2007-05-29 00:00:00','Eduardo Ignacio','Cádiz','Huerta','Ulises ','3193','Villa Real','','',315,'2959837','','','tMMBB'),
 (83,'20200481-4',1,'2007-05-29 00:00:00','Esteban ','González','Sánchez','Juan Antonio ríos','0247','','','',315,'7923929','','','tMMBB'),
 (84,'20327783-0',1,'2007-05-29 00:00:00','Bastián Fabrizio','HErnández','Sepúlveda','De los Metales','3091','','','',315,'7923929','','','tMMBB'),
 (85,'19831151-0',0,'2007-05-31 00:00:00','Antonia Anais Aranzu','Riqelme','Burgos','Angel Pimentel','258','','','',315,'5660396','','','tMMBB');
INSERT INTO `scout`.`tpersona` (`cID`,`cRUT`,`cMasculino`,`cFechaRegistro`,`cNombre`,`cApellidoPaterno`,`cApellidoMaterno`,`cDireccion`,`cNumero`,`cVilla`,`cBlock`,`cDepartamento`,`cComuna`,`cTelefono`,`cCelular`,`cMail`,`cTabla`) VALUES 
 (86,'19570546-1',0,'2007-05-31 00:00:00','Valentina Tamara','Estay ','Padilla','Calabria norte ','435','','','',315,'4549003','','','tMMBB'),
 (87,'19226492-8',0,'2007-05-31 00:00:00','Karin Melissa ','Martinez ','Oses','Psje. Piloto Oyazo ','1928','','','',315,'7920716','','','tMMBB'),
 (88,'18927560-9',0,'2007-05-31 00:00:00','Valentina Beatriz','Cofre ','Peñaloza','Pj. Trento poniente ','1398','','','',315,'3113171','','','tMMBB'),
 (89,'18845772-k',0,'2007-05-31 00:00:00','Fernanda Alexandra','Lange','Mora','Coquimbo ','1610 ','','','',315,'0','','','tMMBB'),
 (90,'19136836-3',0,'2007-05-31 00:00:00','Pamela Belen','Campos','Paredes','Eugenio Cerda ','980','','','',315,'3191273','','','tMMBB'),
 (91,'18211647-5',1,'2007-05-31 00:00:00','Oscar alfonso','Paz ','Cofre','Van Ghogh ','2581','Pob. Llaverias','','',315,'4548147','','','tMMBB'),
 (92,'18187697-2',1,'2007-05-31 00:00:00','Nicolas Antonio','Pino ','Alegria','guayacan','143',' Comandante Faverio','','',315,'3197240','','','tMMBB');
INSERT INTO `scout`.`tpersona` (`cID`,`cRUT`,`cMasculino`,`cFechaRegistro`,`cNombre`,`cApellidoPaterno`,`cApellidoMaterno`,`cDireccion`,`cNumero`,`cVilla`,`cBlock`,`cDepartamento`,`cComuna`,`cTelefono`,`cCelular`,`cMail`,`cTabla`) VALUES 
 (93,'18060623-8',1,'2007-05-31 00:00:00','Diego Ignacio','Espinoza','De la cuadra','Pje. Puntalegre','531','','','',315,'3110188','','','tMMBB'),
 (94,'17672144-8',1,'2007-05-31 00:00:00','Daniel Alfonso','Paz','Cofre','Van Ghogh ','2581','','','',315,'4548147','','','tMMBB'),
 (95,'1767489-7',1,'2007-05-31 00:00:00','Patricio  Alexander','Gonzalez ','Retamales','Bombero Francisco Br','1757','','','',315,'8724174','','','tMMBB'),
 (96,'17707415-2',0,'2007-05-31 00:00:00','Nathalie Geovanna','Doddis','Villa','Av. Gabriela',' 1385','','','',315,'5450223','','','tMMBB'),
 (97,'18210294-6',1,'2007-05-31 00:00:00','Diego Ignacio','Estay','Padilla','Calabria norte ','435','','','',315,'4549003','','','tMMBB'),
 (98,'17024077-4',1,'2007-05-31 00:00:00','Robinson Ariel','Briceño ','Fabio','Profeta Exequiel ','843','','','',315,'0','9-8793527','','tMMBB'),
 (99,'17242482-1',1,'2007-05-31 00:00:00','Joseph Alexander','Garday','Morales','AV. San Carlos ','769','','','',315,'8514340','','','tMMBB');
INSERT INTO `scout`.`tpersona` (`cID`,`cRUT`,`cMasculino`,`cFechaRegistro`,`cNombre`,`cApellidoPaterno`,`cApellidoMaterno`,`cDireccion`,`cNumero`,`cVilla`,`cBlock`,`cDepartamento`,`cComuna`,`cTelefono`,`cCelular`,`cMail`,`cTabla`) VALUES 
 (100,'20237963-k',1,'2007-05-31 00:00:00','Gonzalo Andrés','Leibur','Medel','Las Nieves Oriente','049','','','',315,'3194515','','','tMMBB'),
 (101,'20243587-4',1,'2007-05-31 00:00:00','Nicolás patricio','Machuca','Sandoval','Cristobal Colón','2546','','','',315,'2656994','','','tMMBB'),
 (102,'20243470-3',1,'2007-05-31 00:00:00','Francisco josé','Maureira','Gómez','Los Agustinos','2965','','','',315,'8748053','','','tMMBB'),
 (103,'19700237-9',1,'2007-05-31 00:00:00','matías Reinaldo','Pérez ','Silva','Volcán Peteroa','1682','','','',315,'7246791','','','tMMBB'),
 (104,'19932973-1',1,'2007-05-31 00:00:00','kevin Maverick','Povea','Cabeza','Los almendros ','3196','','','',315,'2954029','','','tMMBB'),
 (105,'20187971-k',1,'2007-05-31 00:00:00','Benjamín Hernán','Quiroz','Villar','Inés de Suárez','13826','','','',315,'5425098','','','tMMBB'),
 (106,'20278314-7',1,'2007-05-31 00:00:00','Jorge  Matías','Rivas','Larenas','Pasaje el Santuario','337','parque campanario','','',315,'2880301','','','tMMBB');
INSERT INTO `scout`.`tpersona` (`cID`,`cRUT`,`cMasculino`,`cFechaRegistro`,`cNombre`,`cApellidoPaterno`,`cApellidoMaterno`,`cDireccion`,`cNumero`,`cVilla`,`cBlock`,`cDepartamento`,`cComuna`,`cTelefono`,`cCelular`,`cMail`,`cTabla`) VALUES 
 (107,'19924735-2',1,'2007-05-31 00:00:00','Diego andrés','Rojas','Castillo','Avda. 4 oriente ','3799','','','',315,'5474503','','','tMMBB'),
 (108,'19830775-0',1,'2007-05-31 00:00:00','Diego Ignacio','Salinas','Guerrero','Carlos Borr Pietri','10268','','','',293,'4932925','','','tMMBB'),
 (109,'19526497-k',1,'2007-05-31 00:00:00','Gabriel Antonio','Sánchez','Sáez','Pasaje naranjillo','3860','','','',315,'2894281','','','tMMBB'),
 (110,'20200456-3',1,'2007-05-31 00:00:00','Sebastián Jesús','Santibáñez ','García','Pasaje del santuario','184','campanario','','',315,'3111732','','','tMMBB'),
 (111,'19421606-8',0,'2007-05-31 00:00:00','Constanza Pamela','Rivera','Cortés','Pasaje 8','0672','','','',315,'09-3156607','','','tMMBB'),
 (112,'20132475-0',1,'2007-05-31 00:00:00','Alejandro Ignacio','Valdebenito ','Muñoz','Volcán purranao','2906','Los silos','','',315,'7231184','','','tMMBB'),
 (113,'19784712-3',1,'2007-05-31 00:00:00','Diego Enrique','Valenzuela','Bravo','Paje. los Helechos','2665','','','',315,'3146402','','','tMMBB');
INSERT INTO `scout`.`tpersona` (`cID`,`cRUT`,`cMasculino`,`cFechaRegistro`,`cNombre`,`cApellidoPaterno`,`cApellidoMaterno`,`cDireccion`,`cNumero`,`cVilla`,`cBlock`,`cDepartamento`,`cComuna`,`cTelefono`,`cCelular`,`cMail`,`cTabla`) VALUES 
 (114,'19409412-4',1,'2007-05-31 00:00:00','david josué','Ramírez','González','Coquimbo','2696','','','',315,'3112772','','','tMMBB'),
 (115,'19993388-4',1,'2007-05-31 00:00:00','René Francisco','Cádiz ','Huerta','Ulises ','3193','','','',315,'2959837','','','tMMBB'),
 (116,'20048449-5',1,'2007-05-31 00:00:00','Victor Andrés','Barrientos','Escobar','Islas Canarias ','2190','','','',315,'087760816','','','tMMBB'),
 (117,'7257877-5',0,'2007-05-31 00:00:00','ROSA CRISTINA','OSES','PEREIRA','PJE. LORENZO DE MEDI','1558','V. DOÑA GABRIELA','','',315,'5428849','08-5745037','ROSA.OSES@GMAIL.COM','tDYG'),
 (118,'15737411-7',0,'2007-05-31 00:00:00','TABATA INGER','ZAMORANO','PONTIGGIA','PJE. EL MOISES','1606','DOÑA GABRIELA','','',315,'5455473','09-8928626','TABATA_ZAMORANO@YAHOO.ES','tDYG'),
 (119,'16977402-1',1,'2007-05-31 00:00:00','ANTONIO BILLING','COFRE','SCHMIDT','PJE VALLE DEL SALADO','3256','V. EL MIRADOR DE LOS ANDES','','',315,'8752553','','','tDYG');
INSERT INTO `scout`.`tpersona` (`cID`,`cRUT`,`cMasculino`,`cFechaRegistro`,`cNombre`,`cApellidoPaterno`,`cApellidoMaterno`,`cDireccion`,`cNumero`,`cVilla`,`cBlock`,`cDepartamento`,`cComuna`,`cTelefono`,`cCelular`,`cMail`,`cTabla`) VALUES 
 (120,'13756837-3',0,'2007-05-31 00:00:00','CINDY ANDREA','ZAMORANO','PONTIGGIA','PJE. EL MOISES','1606','DOÑA GABRIELA','','',315,'5455473','08-7528228','CINDY.LOOPER@GMAIL.COM','tDYG'),
 (121,'7576611-4',0,'2007-06-01 00:00:00','MONICA ELIZABETH','SCHMIDT','BARAHONA','PJE VALLE DEL SALADO','3256','V. EL MIRADOR DE LOS ANDES','','',315,'8752553','07-6996680','MONATARDECER@GMAIL.COM','tDYG'),
 (122,'15327508-4',0,'2007-06-01 00:00:00','YASMIN ESTER','SANTIBAÑEZ','MAZA','LOS LINGUES','2416','V. LA FORESTA UNO','','',315,'2655026','08-7911572','MIMISOLCITO@HOTMAIL.COM','tDYG'),
 (123,'4854354-5',1,'2007-06-01 00:00:00','SERGIO ANTONIO','COFRE','MORALES','PJE VALLE DEL SALADO','3256','V. EL MIRADOR DE LOS ANDES','','',315,'8752553','09-4193911','SCOFREM@GMAIL.COM','tDYG'),
 (124,'11872128-4',0,'2007-06-01 00:00:00','ALICIA DEL CARMEN','PIRAUD','GONZALEZ','PJE. PUNTA GUANACO','3814','V. LA CAPILLA V','','',315,'3115510','','','tDYG');
INSERT INTO `scout`.`tpersona` (`cID`,`cRUT`,`cMasculino`,`cFechaRegistro`,`cNombre`,`cApellidoPaterno`,`cApellidoMaterno`,`cDireccion`,`cNumero`,`cVilla`,`cBlock`,`cDepartamento`,`cComuna`,`cTelefono`,`cCelular`,`cMail`,`cTabla`) VALUES 
 (125,'20052940-5',1,'2007-06-01 00:00:00','Joaquín René','Castillo ','Ortega','Avda gabriela ','668','','','',315,'3150496','','','tMMBB'),
 (126,'18730952-2',0,'2007-06-01 00:00:00','Daniela  constanza','Miranda','Vega','Volcán lanin oriente','2915','','','',315,'3148021','','','tMMBB'),
 (127,'19406298-2',1,'2007-06-01 00:00:00','Claudia Andrea','Colil ','Orellana','Los Mercedarios','359','','','',315,'3143347','','','tMMBB'),
 (128,'13701626-5',1,'2007-06-01 00:00:00','Luis Alejandro','Salinas','Riquelme','Santa Alicia ','2260','','','',315,'4935844','','','tDYG'),
 (129,'19671346-8',1,'2007-06-05 00:00:00','Alejandro','Abarca','Martinez','Pasaje Tarragona','630','Parque Quillay','','',315,'4190408','','','tMMBB'),
 (130,'20469804-k',1,'2007-06-05 00:00:00','Thomás Joaquín ','Herrera','Verdugo','Tolpán ','644','Villa Los Naranjos','','',315,'4190492','','','tMMBB'),
 (131,'20387603-3',1,'2007-06-05 00:00:00','Alberto Ronaldo','Leiva ','Astorga','Los reyes católicos','12103','','','',293,'2007187','','','tMMBB');
INSERT INTO `scout`.`tpersona` (`cID`,`cRUT`,`cMasculino`,`cFechaRegistro`,`cNombre`,`cApellidoPaterno`,`cApellidoMaterno`,`cDireccion`,`cNumero`,`cVilla`,`cBlock`,`cDepartamento`,`cComuna`,`cTelefono`,`cCelular`,`cMail`,`cTabla`) VALUES 
 (132,'19501100-1',1,'2007-06-05 00:00:00','David Alfredo','Contreras','Martínez','Los Toros','0533','','','',315,'2897753','','','tMMBB'),
 (133,'20045631-9',1,'2007-06-05 00:00:00','Cristopher Jesus','Barraza','Toro','Pasaje Tineo','3861','','','',315,'2884581','','','tMMBB'),
 (134,'19289855-2',1,'2007-06-05 00:00:00','Joaquín Alonso','Leyton','Bulnes','Vicuña Mackenna','11847','','','11',293,'7231324','','','tMMBB'),
 (135,'19742224-6',1,'2007-06-05 00:00:00','Stephen Alexander Gregg','Booth','Sepulveda','Parque del Arrayan','0103','Villa Parque del Arrayan','','',315,'2009672','','','tMMBB'),
 (136,'19743816-9',1,'2007-06-05 00:00:00','Christian Sebastian','Araya','Briones','Pasaje Albaricoque','3783','','','',315,'2959137','','','tMMBB'),
 (137,'7383278-0',0,'2007-06-06 00:00:00','Monica ','Palacios','Roa','Elisa Correa','100','','','',315,'3111783','','','tDYG'),
 (138,'19733750-8',0,'2007-06-06 00:00:00','Natale','Peña','Robles','Pasaje Reñaca','0175','','','',315,'3149348','','','tMMBB');
INSERT INTO `scout`.`tpersona` (`cID`,`cRUT`,`cMasculino`,`cFechaRegistro`,`cNombre`,`cApellidoPaterno`,`cApellidoMaterno`,`cDireccion`,`cNumero`,`cVilla`,`cBlock`,`cDepartamento`,`cComuna`,`cTelefono`,`cCelular`,`cMail`,`cTabla`) VALUES 
 (139,'19310817-2',0,'2007-06-06 00:00:00','Macarena ','Rodriguez','Tudela','Av. Los Toros','01082','','','',315,'3146484','','','tMMBB'),
 (140,'19441597-4',0,'2007-06-06 00:00:00','Fernanda','Navarrete','Quiroz','Pasaje Clarines','3246','','','',315,'8746104','','','tMMBB'),
 (141,'19407788-2',0,'2007-06-06 00:00:00','Daniela','Soto','Aguilar','Pasaje Reñaca ','3898','','','',315,'2895237','','','tMMBB'),
 (142,'19513830-3',0,'2007-06-06 00:00:00','Marisol','Agueda','Fuentecilla','Valle Central','0876','','','',315,'3148169','','','tMMBB'),
 (143,'19729092-7',0,'2007-06-06 00:00:00','Alexandra ','Lara','Lara','Pasaje Iloca','4293','','','',315,'3586356','','','tMMBB'),
 (144,'19526084-2',0,'2007-06-06 00:00:00','Elizabeth','Bravo','Bravo','Pasaje Pichidanghi','0574','','','',315,'2955056','','','tMMBB'),
 (145,'19422179-7',0,'2007-06-06 00:00:00','Valeria','Soriano','Fernandez','Av San Francisco','2521','','','',315,'4921950','','','tMMBB');
INSERT INTO `scout`.`tpersona` (`cID`,`cRUT`,`cMasculino`,`cFechaRegistro`,`cNombre`,`cApellidoPaterno`,`cApellidoMaterno`,`cDireccion`,`cNumero`,`cVilla`,`cBlock`,`cDepartamento`,`cComuna`,`cTelefono`,`cCelular`,`cMail`,`cTabla`) VALUES 
 (146,'8702397-4',0,'2007-06-06 00:00:00','Soledad','Bravo','Sanchez','Pasaje Pìchidangui','0574','','','',315,'2955056','','','tDYG'),
 (147,'20041822-0',0,'2007-06-08 00:00:00','Antonia Ignacia','Reyes','Villanueva','Santa Marta','58','','','',320,'08-8184085','','','tMMBB'),
 (148,'18622963-0',0,'2007-06-08 00:00:00','Casandra','Espinoza','Sepulveda','Calle Verde ','12456','','','',315,'2880513','','','tMMBB'),
 (149,'18548136-0',0,'2007-06-08 00:00:00','Daniela ','San Martin','Fernandez','La Palma ','0451','','','',315,'7922195','','','tMMBB'),
 (150,'18003769-1',0,'2007-06-08 00:00:00','Geraldine','Diaz','Vergara','Pje. El Pistacho ','3855','','','',315,'4921091','','','tMMBB'),
 (151,'18355507-3',0,'2007-06-08 00:00:00','Laura','Leyton','Bulnes','Vicuña Mackenna ','11847','','','11',315,'7231324','','','tMMBB'),
 (152,'18192394-6',0,'2007-06-08 00:00:00','Nicol','Ovalle','Garces','Las Ñipas ','3742','','','',315,'2952179','','','tMMBB');
INSERT INTO `scout`.`tpersona` (`cID`,`cRUT`,`cMasculino`,`cFechaRegistro`,`cNombre`,`cApellidoPaterno`,`cApellidoMaterno`,`cDireccion`,`cNumero`,`cVilla`,`cBlock`,`cDepartamento`,`cComuna`,`cTelefono`,`cCelular`,`cMail`,`cTabla`) VALUES 
 (153,'18927505-6',0,'2007-06-08 00:00:00','Kristen','Booth','Sepulveda','Parque El Arrayán','0103','','','',315,'2889672','','','tMMBB'),
 (154,'18537051-8',0,'2007-06-08 00:00:00','Sheryl','Booth','Sepulveda','Parque El Arrayán','0103','','','',315,'2889672','','','tMMBB'),
 (155,'19035041-k',0,'2007-06-08 00:00:00','Tatiana','Rojas','Azocar','Psje Las Camelias ','3640','','','',315,'4549786','','','tMMBB'),
 (156,'13688666-5',0,'2007-06-08 00:00:00','Maria Alejandra','Machuca','Arana','La Quebrada ','4195','','','',315,'4478510','','','tDYG'),
 (157,'7895432-9',1,'2007-06-08 00:00:00','Ricardo Eduardo','Espinoza','Arellano','Tegualda','470','Galvarino','','',293,'2891136','09579844','Kaa470@gmail.com','tDYG'),
 (226217145399491614,'1-9',1,'2008-03-29 00:00:00','Claudio','odssdo','dosid','acdihsdiucsh','221','','','',315,'3146420','','','tMMBB'),
 (1118822914707226534,'3-5',1,'2008-03-29 00:00:00','claudio rodrigo','moscoso','howard','malveira','540','ciudad del sol','','',315,'3146420','','claudio.moscoso@gmail.com','tApoderado');
/*!40000 ALTER TABLE `tpersona` ENABLE KEYS */;


--
-- Structure for table `scout`.`tr_dyg_cargo`
--

DROP TABLE IF EXISTS `scout`.`tr_dyg_cargo`;
CREATE TABLE  `scout`.`tr_dyg_cargo` (
  `cDYG` varchar(12) NOT NULL,
  `cCargo` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `scout`.`tr_dyg_cargo`
--

/*!40000 ALTER TABLE `tr_dyg_cargo` DISABLE KEYS */;
INSERT INTO `scout`.`tr_dyg_cargo` (`cDYG`,`cCargo`) VALUES 
 ('16094925-2',11),
 ('15843093-2',11),
 ('15348723-5',10),
 ('12166670-7',10),
 ('12166670-7',1),
 ('14184576-4',2),
 ('14184576-4',10),
 ('9804928-2',10),
 ('12893929-6',11),
 ('13688721-1',10),
 ('13688721-1',1),
 ('10780267-3',10),
 ('9159201-0',10),
 ('8002680-3',11),
 ('15348013-3',11),
 ('16030361-1',10),
 ('14382614-7',1),
 ('9489372-0',11),
 ('9489372-0',1),
 ('14197827-6',10),
 ('13292168-7',11),
 ('12902733-9',11),
 ('9492211-9',11),
 ('9492211-9',2),
 ('7257877-5',10),
 ('15737411-7',11),
 ('16977402-1',10),
 ('13756837-3',11),
 ('7576611-4',11),
 ('15327508-4',10),
 ('15327508-4',2),
 ('4854354-5',11),
 ('4854354-5',1),
 ('11872128-4',9),
 ('15432427-5',10),
 ('15432427-5',2),
 ('13701626-5',10),
 ('7383278-0',11),
 ('8702397-4',10),
 ('13688666-5',7),
 ('7895432-9',10),
 ('2-7',4),
 ('2-7',8);
/*!40000 ALTER TABLE `tr_dyg_cargo` ENABLE KEYS */;


--
-- Structure for table `scout`.`treligion`
--

DROP TABLE IF EXISTS `scout`.`treligion`;
CREATE TABLE  `scout`.`treligion` (
  `cID` bigint(20) NOT NULL,
  `cNombre` varchar(50) NOT NULL,
  `cPrioridad` int(11) NOT NULL default '0',
  PRIMARY KEY  (`cID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `scout`.`treligion`
--

/*!40000 ALTER TABLE `treligion` DISABLE KEYS */;
INSERT INTO `scout`.`treligion` (`cID`,`cNombre`,`cPrioridad`) VALUES 
 (1,'Catolica',154),
 (2,'Evangelica',1),
 (3,'Testigo de Jehova',0),
 (4,'Ningúna',0),
 (5,'Otra',5),
 (6,'Mormona',0),
 (7,'Adventista',0),
 (8,'Judia',0),
 (9,'Hinduísmo',0),
 (10,'Islamismo',0);
/*!40000 ALTER TABLE `treligion` ENABLE KEYS */;


--
-- Structure for table `scout`.`tscout`
--

DROP TABLE IF EXISTS `scout`.`tscout`;
CREATE TABLE  `scout`.`tscout` (
  `cRUT` varchar(12) NOT NULL,
  `cFechaNacimiento` datetime default NULL,
  `cFax` varchar(12) NOT NULL,
  `cCodigoPostal` varchar(10) NOT NULL,
  `cNacionalidad` bigint(20) NOT NULL default '0',
  `cReligion` bigint(20) NOT NULL,
  `cGrupo` bigint(20) NOT NULL,
  `cUnidad` bigint(20) NOT NULL,
  `cIdioma1` varchar(20) default NULL,
  `cIdiomaLee1` smallint(6) NOT NULL,
  `cIdiomaHabla1` smallint(6) NOT NULL,
  `cIdiomaEscribe1` smallint(6) NOT NULL,
  `cIdioma2` varchar(20) default NULL,
  `cIdiomaLee2` smallint(6) NOT NULL,
  `cIdiomaHabla2` smallint(6) NOT NULL,
  `cIdiomaEscribe2` smallint(6) NOT NULL,
  `cObservacion` text NOT NULL,
  `cFechaPago` datetime default NULL,
  `cValorPago` int(11) default NULL,
  `cInformado` smallint(6) NOT NULL default '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `scout`.`tscout`
--

/*!40000 ALTER TABLE `tscout` DISABLE KEYS */;
INSERT INTO `scout`.`tscout` (`cRUT`,`cFechaNacimiento`,`cFax`,`cCodigoPostal`,`cNacionalidad`,`cReligion`,`cGrupo`,`cUnidad`,`cIdioma1`,`cIdiomaLee1`,`cIdiomaHabla1`,`cIdiomaEscribe1`,`cIdioma2`,`cIdiomaLee2`,`cIdiomaHabla2`,`cIdiomaEscribe2`,`cObservacion`,`cFechaPago`,`cValorPago`,`cInformado`) VALUES 
 ('12166670-7','1978-02-01 00:00:00','','',11,5,1,3,'INGLES',1,1,1,'',0,0,0,'',NULL,NULL,0),
 ('14184576-4','1981-05-19 00:00:00','','',11,5,1,5,'ingles',1,1,1,'',0,0,0,'',NULL,NULL,0),
 ('16094925-2','1985-02-13 00:00:00','','',11,5,1,3,'ingles',1,1,1,'',0,0,0,'',NULL,NULL,0),
 ('15843093-2','1984-12-02 00:00:00','','',11,5,1,4,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('15348723-5','1982-06-01 00:00:00','','',11,5,1,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('9804928-2','1964-04-27 00:00:00','','',11,1,1,5,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('12893929-6','1974-01-30 00:00:00','','',11,1,1,5,'INGLES',1,1,1,'',0,0,0,'',NULL,NULL,0),
 ('9489372-0','1965-01-04 00:00:00','','',11,1,2,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('18864187-3','1995-09-13 00:00:00','','',11,1,2,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0);
INSERT INTO `scout`.`tscout` (`cRUT`,`cFechaNacimiento`,`cFax`,`cCodigoPostal`,`cNacionalidad`,`cReligion`,`cGrupo`,`cUnidad`,`cIdioma1`,`cIdiomaLee1`,`cIdiomaHabla1`,`cIdiomaEscribe1`,`cIdioma2`,`cIdiomaLee2`,`cIdiomaHabla2`,`cIdiomaEscribe2`,`cObservacion`,`cFechaPago`,`cValorPago`,`cInformado`) VALUES 
 ('13688721-1','1979-10-06 00:00:00','','',11,1,3,5,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('15432427-5','1982-03-01 00:00:00','','',11,1,3,3,'Ingles basico',1,0,1,'',0,0,0,'',NULL,NULL,0),
 ('19313839-k','1996-03-28 00:00:00','','',11,1,2,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('18732055-0','1994-10-29 00:00:00','','',11,1,2,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19080079-2','1995-06-22 00:00:00','','',11,5,2,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19057693-0','1995-07-26 00:00:00','','',11,1,2,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('18370793-0','1993-10-06 00:00:00','','',11,1,2,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19309240-3','1996-06-11 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('20225955-3','1999-10-10 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0);
INSERT INTO `scout`.`tscout` (`cRUT`,`cFechaNacimiento`,`cFax`,`cCodigoPostal`,`cNacionalidad`,`cReligion`,`cGrupo`,`cUnidad`,`cIdioma1`,`cIdiomaLee1`,`cIdiomaHabla1`,`cIdiomaEscribe1`,`cIdioma2`,`cIdiomaLee2`,`cIdiomaHabla2`,`cIdiomaEscribe2`,`cObservacion`,`cFechaPago`,`cValorPago`,`cInformado`) VALUES 
 ('18664975-3','1994-01-26 00:00:00','','',11,1,2,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('18593379-2','1993-09-06 00:00:00','','',11,1,2,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('20164963-3','1999-09-04 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('10780267-3','1979-10-12 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('18171781-5','1993-02-09 00:00:00','','',11,1,2,4,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('8002680-3','1960-12-30 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('9159201-0','1962-09-04 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('20464167-6','2000-01-28 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19842332-7','1998-06-03 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0);
INSERT INTO `scout`.`tscout` (`cRUT`,`cFechaNacimiento`,`cFax`,`cCodigoPostal`,`cNacionalidad`,`cReligion`,`cGrupo`,`cUnidad`,`cIdioma1`,`cIdiomaLee1`,`cIdiomaHabla1`,`cIdiomaEscribe1`,`cIdioma2`,`cIdiomaLee2`,`cIdiomaHabla2`,`cIdiomaEscribe2`,`cObservacion`,`cFechaPago`,`cValorPago`,`cInformado`) VALUES 
 ('19701065-7','1998-03-06 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19918859-3','1998-10-16 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('20227057-3','1999-12-11 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('20120084-9','1999-01-30 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('20189899-4','1999-08-10 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('20298066-K','1999-11-12 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('20165535-8','1999-11-15 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('18669242-k','1982-06-24 00:00:00','','',11,1,5,7,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('20428312-5','2000-04-09 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0);
INSERT INTO `scout`.`tscout` (`cRUT`,`cFechaNacimiento`,`cFax`,`cCodigoPostal`,`cNacionalidad`,`cReligion`,`cGrupo`,`cUnidad`,`cIdioma1`,`cIdiomaLee1`,`cIdiomaHabla1`,`cIdiomaEscribe1`,`cIdioma2`,`cIdiomaLee2`,`cIdiomaHabla2`,`cIdiomaEscribe2`,`cObservacion`,`cFechaPago`,`cValorPago`,`cInformado`) VALUES 
 ('20248118-3','1999-08-17 00:00:00','','',11,1,5,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('20224290-1','1999-06-26 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('20109898-K','1999-06-09 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('18731657-k','1994-09-06 00:00:00','','',11,1,5,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('15739898-9','1983-07-07 00:00:00','','',11,1,5,4,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19523216-4','1996-10-19 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('18609251-1','1994-05-10 00:00:00','','',11,1,5,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19421755-2','1996-07-27 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19382888-4','1996-05-25 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0);
INSERT INTO `scout`.`tscout` (`cRUT`,`cFechaNacimiento`,`cFax`,`cCodigoPostal`,`cNacionalidad`,`cReligion`,`cGrupo`,`cUnidad`,`cIdioma1`,`cIdiomaLee1`,`cIdiomaHabla1`,`cIdiomaEscribe1`,`cIdioma2`,`cIdiomaLee2`,`cIdiomaHabla2`,`cIdiomaEscribe2`,`cObservacion`,`cFechaPago`,`cValorPago`,`cInformado`) VALUES 
 ('20040318-5','1998-10-04 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('20455781-0','2000-11-25 00:00:00','','',11,1,5,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19685973-K','1997-05-01 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19522565-6','1996-09-05 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19925464-2','1998-12-17 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('15348013-3','1982-06-12 00:00:00','','',11,1,5,6,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('16030361-1','1985-03-27 00:00:00','','',11,1,5,5,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19038998-7','1996-01-19 00:00:00','','',11,1,5,4,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('14382614-7','1980-05-23 00:00:00','','',11,1,5,7,'',0,0,0,'',0,0,0,'',NULL,NULL,0);
INSERT INTO `scout`.`tscout` (`cRUT`,`cFechaNacimiento`,`cFax`,`cCodigoPostal`,`cNacionalidad`,`cReligion`,`cGrupo`,`cUnidad`,`cIdioma1`,`cIdiomaLee1`,`cIdiomaHabla1`,`cIdiomaEscribe1`,`cIdioma2`,`cIdiomaLee2`,`cIdiomaHabla2`,`cIdiomaEscribe2`,`cObservacion`,`cFechaPago`,`cValorPago`,`cInformado`) VALUES 
 ('18628275-2','1993-12-31 00:00:00','','',11,1,5,4,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('18605856-9','1993-12-14 00:00:00','','',11,1,5,4,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('21595764-0','2000-06-29 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19288971-5','1995-12-07 00:00:00','','',11,1,5,4,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19423175-k','1997-03-14 00:00:00','0','',11,1,1,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('18974286-k','2000-11-09 00:00:00','0','',11,1,1,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('17612704-k','1991-02-04 00:00:00','','',11,1,1,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('18990452-5','1994-11-05 00:00:00','','',11,1,1,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19680235-5','1997-02-20 00:00:00','','',11,1,1,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0);
INSERT INTO `scout`.`tscout` (`cRUT`,`cFechaNacimiento`,`cFax`,`cCodigoPostal`,`cNacionalidad`,`cReligion`,`cGrupo`,`cUnidad`,`cIdioma1`,`cIdiomaLee1`,`cIdiomaHabla1`,`cIdiomaEscribe1`,`cIdioma2`,`cIdiomaLee2`,`cIdiomaHabla2`,`cIdiomaEscribe2`,`cObservacion`,`cFechaPago`,`cValorPago`,`cInformado`) VALUES 
 ('17271201-0','1989-12-21 00:00:00','','',11,1,1,6,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19245364-K','1996-03-06 00:00:00','','',11,1,1,4,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('17599071-2','1990-08-06 00:00:00','','',11,1,2,5,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19410677-7','1996-07-12 00:00:00','','',11,1,1,4,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('18864186-5','1995-09-13 00:00:00','','',11,1,2,4,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('9492211-9','1963-02-20 00:00:00','','',11,1,2,4,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('18533447-3','1993-10-23 00:00:00','','',11,1,1,4,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19290099-9','1995-11-02 00:00:00','','',11,1,1,4,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19427297-9','1996-02-21 00:00:00','','',11,1,1,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0);
INSERT INTO `scout`.`tscout` (`cRUT`,`cFechaNacimiento`,`cFax`,`cCodigoPostal`,`cNacionalidad`,`cReligion`,`cGrupo`,`cUnidad`,`cIdioma1`,`cIdiomaLee1`,`cIdiomaHabla1`,`cIdiomaEscribe1`,`cIdioma2`,`cIdiomaLee2`,`cIdiomaHabla2`,`cIdiomaEscribe2`,`cObservacion`,`cFechaPago`,`cValorPago`,`cInformado`) VALUES 
 ('19953415-7','1998-03-12 00:00:00','','',11,1,1,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19959458-3','1998-09-03 00:00:00','','',11,1,1,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19643818-1','1997-03-04 00:00:00','','',11,1,1,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19319943-7','1996-03-28 00:00:00','','',11,1,2,4,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('14197827-6','1981-08-17 00:00:00','','',11,1,2,4,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('13292168-7','1977-02-13 00:00:00','','',11,1,2,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('12902733-9','1975-01-24 00:00:00','','',11,1,2,5,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19955423-9','1998-05-10 00:00:00','','',11,1,2,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19193872-0','1996-08-14 00:00:00','','',11,1,2,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0);
INSERT INTO `scout`.`tscout` (`cRUT`,`cFechaNacimiento`,`cFax`,`cCodigoPostal`,`cNacionalidad`,`cReligion`,`cGrupo`,`cUnidad`,`cIdioma1`,`cIdiomaLee1`,`cIdiomaHabla1`,`cIdiomaEscribe1`,`cIdioma2`,`cIdiomaLee2`,`cIdiomaHabla2`,`cIdiomaEscribe2`,`cObservacion`,`cFechaPago`,`cValorPago`,`cInformado`) VALUES 
 ('20200481-4','1999-07-21 00:00:00','','',11,1,2,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('20327783-0','1999-08-26 00:00:00','','',11,1,2,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19831151-0','1997-12-31 00:00:00','','',11,1,1,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19570546-1','1997-12-15 00:00:00','','',11,1,1,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19226492-8','1995-10-17 00:00:00','','',11,1,1,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('18927560-9','1994-11-04 00:00:00','','',11,1,1,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('18845772-k','1994-07-19 00:00:00','','',11,1,1,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19136836-3','1995-11-14 00:00:00','','',11,1,1,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('18211647-5','1992-11-28 00:00:00','','',11,1,1,4,'',0,0,0,'',0,0,0,'',NULL,NULL,0);
INSERT INTO `scout`.`tscout` (`cRUT`,`cFechaNacimiento`,`cFax`,`cCodigoPostal`,`cNacionalidad`,`cReligion`,`cGrupo`,`cUnidad`,`cIdioma1`,`cIdiomaLee1`,`cIdiomaHabla1`,`cIdiomaEscribe1`,`cIdioma2`,`cIdiomaLee2`,`cIdiomaHabla2`,`cIdiomaEscribe2`,`cObservacion`,`cFechaPago`,`cValorPago`,`cInformado`) VALUES 
 ('18187697-2','1996-03-06 00:00:00','','',11,1,1,4,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('18060623-8','1992-02-26 00:00:00','','',11,1,1,5,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('17672144-8','1990-10-22 00:00:00','','',11,1,1,5,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('1767489-7','1990-01-29 00:00:00','','',11,1,1,5,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('17707415-2','1991-04-19 00:00:00','','',11,1,1,5,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('18210294-6','1993-06-25 00:00:00','','',11,1,1,5,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('17024077-4','1988-09-15 00:00:00','','',11,1,1,6,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('17242482-1','1989-06-04 00:00:00','','',11,1,1,6,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('20237963-k','1999-05-13 00:00:00','','',11,1,2,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0);
INSERT INTO `scout`.`tscout` (`cRUT`,`cFechaNacimiento`,`cFax`,`cCodigoPostal`,`cNacionalidad`,`cReligion`,`cGrupo`,`cUnidad`,`cIdioma1`,`cIdiomaLee1`,`cIdiomaHabla1`,`cIdiomaEscribe1`,`cIdioma2`,`cIdiomaLee2`,`cIdiomaHabla2`,`cIdiomaEscribe2`,`cObservacion`,`cFechaPago`,`cValorPago`,`cInformado`) VALUES 
 ('20243587-4','1999-11-01 00:00:00','','',11,1,2,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('20243470-3','1999-11-06 00:00:00','','',11,1,2,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19700237-9','1997-10-17 00:00:00','','',11,1,2,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19932973-1','1997-12-18 00:00:00','','',11,1,2,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('20187971-k','1999-05-11 00:00:00','','',11,1,2,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('20278314-7','1997-04-07 00:00:00','','',11,1,2,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19924735-2','1998-08-04 00:00:00','','',11,1,2,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19830775-0','1997-12-26 00:00:00','','',11,1,2,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19526497-k','1997-03-07 00:00:00','','',11,1,2,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0);
INSERT INTO `scout`.`tscout` (`cRUT`,`cFechaNacimiento`,`cFax`,`cCodigoPostal`,`cNacionalidad`,`cReligion`,`cGrupo`,`cUnidad`,`cIdioma1`,`cIdiomaLee1`,`cIdiomaHabla1`,`cIdiomaEscribe1`,`cIdioma2`,`cIdiomaLee2`,`cIdiomaHabla2`,`cIdiomaEscribe2`,`cObservacion`,`cFechaPago`,`cValorPago`,`cInformado`) VALUES 
 ('20200456-3','1999-07-05 00:00:00','','',11,1,2,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19421606-8','1996-07-15 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('20132475-0','1997-07-01 00:00:00','','',11,1,2,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19784712-3','1997-12-05 00:00:00','','',11,1,2,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19409412-4','1996-07-02 00:00:00','','',11,1,2,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19993388-4','1998-07-11 00:00:00','','',11,1,2,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('20048449-5','1999-02-06 00:00:00','','',11,1,2,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('7257877-5','1952-09-25 00:00:00','','',11,1,7,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('15737411-7','1983-12-24 00:00:00','','',11,1,7,1,'INGLES',1,1,1,'',0,0,0,'',NULL,NULL,0);
INSERT INTO `scout`.`tscout` (`cRUT`,`cFechaNacimiento`,`cFax`,`cCodigoPostal`,`cNacionalidad`,`cReligion`,`cGrupo`,`cUnidad`,`cIdioma1`,`cIdiomaLee1`,`cIdiomaHabla1`,`cIdiomaEscribe1`,`cIdioma2`,`cIdiomaLee2`,`cIdiomaHabla2`,`cIdiomaEscribe2`,`cObservacion`,`cFechaPago`,`cValorPago`,`cInformado`) VALUES 
 ('16977402-1','1988-11-18 00:00:00','','',11,1,7,2,'INGLES',1,0,0,'',0,0,0,'',NULL,NULL,0),
 ('13756837-3','1980-06-19 00:00:00','','',11,1,7,2,'INGLES',1,1,1,'',0,0,0,'',NULL,NULL,0),
 ('7576611-4','1959-05-22 00:00:00','','',11,1,7,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('15327508-4','1982-03-16 00:00:00','','',11,1,7,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('4854354-5','1957-03-08 00:00:00','','',11,1,7,4,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('11872128-4','1971-02-26 00:00:00','','',11,1,7,7,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('20052940-5','1998-12-02 00:00:00','','',11,1,2,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('18730952-2','1994-05-05 00:00:00','','',11,1,2,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19406298-2','1996-03-24 00:00:00','','',11,1,2,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0);
INSERT INTO `scout`.`tscout` (`cRUT`,`cFechaNacimiento`,`cFax`,`cCodigoPostal`,`cNacionalidad`,`cReligion`,`cGrupo`,`cUnidad`,`cIdioma1`,`cIdiomaLee1`,`cIdiomaHabla1`,`cIdiomaEscribe1`,`cIdioma2`,`cIdiomaLee2`,`cIdiomaHabla2`,`cIdiomaEscribe2`,`cObservacion`,`cFechaPago`,`cValorPago`,`cInformado`) VALUES 
 ('13701626-5','1979-01-30 00:00:00','','',11,1,2,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19671346-8','1997-07-16 00:00:00','','',11,1,3,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('20469804-k','2000-04-01 00:00:00','','',11,1,3,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('20387603-3','1999-12-17 00:00:00','','',11,1,3,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19501100-1','1997-07-31 00:00:00','','',11,1,3,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('20045631-9','1998-08-27 00:00:00','','',11,1,3,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19289855-2','1996-01-16 00:00:00','','',11,1,3,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19742224-6','1997-08-06 00:00:00','','',11,1,3,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19743816-9','1997-08-22 00:00:00','','',11,1,3,2,'',0,0,0,'',0,0,0,'',NULL,NULL,0);
INSERT INTO `scout`.`tscout` (`cRUT`,`cFechaNacimiento`,`cFax`,`cCodigoPostal`,`cNacionalidad`,`cReligion`,`cGrupo`,`cUnidad`,`cIdioma1`,`cIdiomaLee1`,`cIdiomaHabla1`,`cIdiomaEscribe1`,`cIdioma2`,`cIdiomaLee2`,`cIdiomaHabla2`,`cIdiomaEscribe2`,`cObservacion`,`cFechaPago`,`cValorPago`,`cInformado`) VALUES 
 ('7383278-0','1961-05-06 00:00:00','','',11,1,3,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19733750-8','1997-11-25 00:00:00','','',11,1,3,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19310817-2','1996-08-13 00:00:00','','',11,1,3,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19441597-4','1996-10-22 00:00:00','','',11,1,3,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19407788-2','1996-05-30 00:00:00','','',11,1,3,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19513830-3','1996-09-12 00:00:00','','',11,1,3,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19729092-7','1997-05-13 00:00:00','','',11,1,3,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19526084-2','1997-02-04 00:00:00','','',11,1,3,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('19422179-7','1996-10-15 00:00:00','','',11,1,3,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0);
INSERT INTO `scout`.`tscout` (`cRUT`,`cFechaNacimiento`,`cFax`,`cCodigoPostal`,`cNacionalidad`,`cReligion`,`cGrupo`,`cUnidad`,`cIdioma1`,`cIdiomaLee1`,`cIdiomaHabla1`,`cIdiomaEscribe1`,`cIdioma2`,`cIdiomaLee2`,`cIdiomaHabla2`,`cIdiomaEscribe2`,`cObservacion`,`cFechaPago`,`cValorPago`,`cInformado`) VALUES 
 ('8702397-4','1960-10-24 00:00:00','','',11,1,3,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('20041822-0','1998-12-16 00:00:00','','',11,1,2,1,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('18622963-0','1995-06-09 00:00:00','','',11,1,3,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('18548136-0','1993-09-26 00:00:00','','',11,1,3,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('18003769-1','1992-07-16 00:00:00','','',11,1,3,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('18355507-3','1992-11-21 00:00:00','','',11,1,3,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('18192394-6','1993-01-02 00:00:00','','',11,1,3,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('18927505-6','1994-11-03 00:00:00','','',11,1,3,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('18537051-8','1993-04-06 00:00:00','','',11,1,3,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0);
INSERT INTO `scout`.`tscout` (`cRUT`,`cFechaNacimiento`,`cFax`,`cCodigoPostal`,`cNacionalidad`,`cReligion`,`cGrupo`,`cUnidad`,`cIdioma1`,`cIdiomaLee1`,`cIdiomaHabla1`,`cIdiomaEscribe1`,`cIdioma2`,`cIdiomaLee2`,`cIdiomaHabla2`,`cIdiomaEscribe2`,`cObservacion`,`cFechaPago`,`cValorPago`,`cInformado`) VALUES 
 ('19035041-k','1995-04-05 00:00:00','','',11,1,3,3,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('13688666-5','1979-09-25 00:00:00','','',11,1,3,7,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('7895432-9','1958-10-18 00:00:00','','',11,1,3,4,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('1-9','1975-05-29 00:00:00','','',11,1,1,7,'',0,0,0,'',0,0,0,'',NULL,NULL,0),
 ('2-7','1975-05-29 00:00:00','','',11,2,1,7,'',0,0,0,'',0,0,0,'',NULL,NULL,0);
/*!40000 ALTER TABLE `tscout` ENABLE KEYS */;


--
-- Structure for table `scout`.`tunidad`
--

DROP TABLE IF EXISTS `scout`.`tunidad`;
CREATE TABLE  `scout`.`tunidad` (
  `cID` bigint(20) NOT NULL,
  `cNombre` varchar(50) NOT NULL,
  `cPrioridad` int(11) NOT NULL default '0',
  PRIMARY KEY  (`cID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `scout`.`tunidad`
--

/*!40000 ALTER TABLE `tunidad` DISABLE KEYS */;
INSERT INTO `scout`.`tunidad` (`cID`,`cNombre`,`cPrioridad`) VALUES 
 (1,'Bandada',49),
 (2,'Manada',37),
 (3,'Compañía',32),
 (4,'Tropa',19),
 (5,'Avanzada',14),
 (6,'Clan',3),
 (7,'Ningúna',6);
/*!40000 ALTER TABLE `tunidad` ENABLE KEYS */;


--
-- Structure for table `scout`.`tzona`
--

DROP TABLE IF EXISTS `scout`.`tzona`;
CREATE TABLE  `scout`.`tzona` (
  `cID` bigint(20) NOT NULL,
  `cNombre` varchar(50) NOT NULL,
  PRIMARY KEY  (`cID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `scout`.`tzona`
--

/*!40000 ALTER TABLE `tzona` DISABLE KEYS */;
INSERT INTO `scout`.`tzona` (`cID`,`cNombre`) VALUES 
 (1,'Cajon del Maipo');
/*!40000 ALTER TABLE `tzona` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
