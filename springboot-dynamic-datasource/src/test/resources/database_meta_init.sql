/*
SQLyog Enterprise v12.08 (64 bit)
MySQL - 5.7.17
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;
insert into `user` (`id`, `name`) values('1','test_01');
insert into `user` (`id`, `name`) values('2','test_02');

--  配置数据源信息
insert into `db_meta_info` (`id`, `user_id`, `name`, `driverClassName`, `url`, `username`, `password`)
	values('1','1','test_01','com.mysql.cj.jdbc.Driver','jdbc:mysql://172.16.22.135:3306/test_01?useSSL=FALSE&autoReconnect=TRUE&sessionVariables=FOREIGN_KEY_CHECKS=0&useUnicode=yes&characterEncoding=UTF-8','root','123456');

insert into `db_meta_info` (`id`, `user_id`, `name`, `driverClassName`, `url`, `username`, `password`)
	values('2','2','test_02','com.mysql.cj.jdbc.Driver','jdbc:mysql://172.16.22.135:3306/test_02?useSSL=FALSE&autoReconnect=TRUE&sessionVariables=FOREIGN_KEY_CHECKS=0&useUnicode=yes&characterEncoding=UTF-8','root','123456');
