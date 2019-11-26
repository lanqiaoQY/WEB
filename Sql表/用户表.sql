

CREATE TABLE `user` (
  `uid` int(10) NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `openid` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `nickname` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `headimg` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `utime` date DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

