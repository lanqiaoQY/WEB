
CREATE TABLE `order` (
  `oid` int(10) NOT NULL AUTO_INCREMENT,
  `oname` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `osex` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ophone` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `optime` date DEFAULT NULL,
  `otime` date DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
