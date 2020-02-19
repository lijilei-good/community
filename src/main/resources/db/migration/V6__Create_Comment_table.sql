CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parentId` bigint(20) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `commentor` int(11) DEFAULT NULL,
  `comment` varchar(1024) DEFAULT NULL,
  `like_count` bigint(20) DEFAULT '0',
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

