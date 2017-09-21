USE `springboot`;
-- --------------------------------------------------------------------------------
-- Dumping structure for table springboot.users
DROP TABLE IF EXISTS`Users`;
CREATE TABLE `Users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(200) UNIQUE NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`, `username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
-- ----------------------------------------------------------------------------------
-- Dumping data for table springboot.users: ~2 rows (approximately)
INSERT INTO `Users` (`id`, `username`, `password`) VALUES
	(1, 'test', '123'),
	(2, 'test1', '123'); 