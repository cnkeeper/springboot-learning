/*
SQLyog Enterprise v12.08 (64 bit)
MySQL - 5.7.16-log : Database - news
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '姓名'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '用户信息表';


/*Table structure for table `db_meta_info` */
DROP TABLE IF EXISTS `db_meta_info`;

CREATE TABLE `db_meta_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '所属用户id',
  `name` varchar(50) NOT NULL COMMENT '数据库名',
  `driverClassName` VARCHAR(100) NOT NULL COMMENT '数据库驱动',
  `url` VARCHAR(200) NOT NULL COMMENT '数据库url',
  `username` VARCHAR(20) NOT NULL COMMENT '登录用户名',
  `password` VARCHAR(50) NOT NULL COMMENT '登录密码'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '用户数据库配置元数据';



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
