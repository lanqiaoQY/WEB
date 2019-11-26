CREATE TABLE `housing` (
  `hid` INT(10) NOT NULL AUTO_INCREMENT,
  `hmoney` DOUBLE DEFAULT NULL,
  `harea` DOUBLE DEFAULT NULL,
  `haddress` VARCHAR(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `housetype` VARCHAR(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `hphoto` VARCHAR(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `horder` INT(20) DEFAULT NULL,
  `hsynopsis` VARCHAR(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`hid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;