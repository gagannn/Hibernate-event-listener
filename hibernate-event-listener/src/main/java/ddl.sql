DROP DATABASE IF EXISTS `test_book`;
CREATE DATABASE IF NOT EXISTS `test_book` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `test_book`;

-- Dumping structure for table test_book.book
DROP TABLE IF EXISTS `book`;
CREATE TABLE IF NOT EXISTS `book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `version_id` int(11) NOT NULL,
  `createdon` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;