CREATE DATABASE IF NOT EXISTS `data_display_server`;
USE data_display_server;
DROP TABLE IF EXISTS `tb_global_param_config`;
CREATE TABLE IF NOT EXISTS `tb_global_param_config`
(
   `id` int (8) NOT NULL AUTO_INCREMENT,
   `param_key` varchar (100) NOT NULL,
   `param_value` varchar (100) DEFAULT NULL,
   `param_comment` varchar (100) DEFAULT NULL,
   `create_date` timestamp DEFAULT CURRENT_TIMESTAMP (),
   `update_date` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   `deleted` varchar (11) NOT NULL DEFAULT '0' COMMENT '是否删除',
   `comments` varchar (200) DEFAULT NULL COMMENT '备注',
   PRIMARY KEY (`id`)
)
ENGINE = InnoDB CHARSET = utf8;
DROP TABLE IF EXISTS `tb_database_type`;
CREATE TABLE IF NOT EXISTS `tb_database_type`
(
   `id` int (8) NOT NULL AUTO_INCREMENT,
   `type_name` varchar (100) NOT NULL,
   `type_ename` varchar (100) DEFAULT NULL,
   `create_date` timestamp DEFAULT CURRENT_TIMESTAMP (),
   `update_date` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   `create_user` varchar (100) DEFAULT NULL,
   `update_user` varchar (100) DEFAULT NULL,
   `description` varchar (200) DEFAULT NULL COMMENT '备注',
   PRIMARY KEY (`id`)
)
ENGINE = InnoDB CHARSET = utf8;
DROP TABLE IF EXISTS `tb_database_info`;
CREATE TABLE IF NOT EXISTS `tb_database_info`
(
   `id` int (8) NOT NULL AUTO_INCREMENT,
   `database_name` varchar (100) NOT NULL,
   `database_ip` varchar (100) DEFAULT NULL,
   `connect_url` varchar (500) DEFAULT NULL,
   `port` bigint (10) DEFAULT NULL,
   `instance_name` varchar(100) DEFAULT NULL COMMENT '数据库实例名',
   `username` varchar (100) DEFAULT NULL,
   `password` varchar (100) DEFAULT NULL,
   `database_type_id` bigint (10) DEFAULT NULL,
   `create_date` timestamp DEFAULT CURRENT_TIMESTAMP (),
   `update_date` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   `create_user` varchar (100) DEFAULT NULL,
   `update_user` varchar (100) DEFAULT NULL,
   `description` varchar (200) DEFAULT NULL COMMENT '备注',
   PRIMARY KEY (`id`)
)
ENGINE = InnoDB CHARSET = utf8;
DROP TABLE IF EXISTS `tb_data_coming_source`;
CREATE TABLE IF NOT EXISTS `tb_data_coming_source`
(
   `id` int (8) NOT NULL AUTO_INCREMENT,
   `source_name` varchar (100) NOT NULL,
   `execute_sql` varchar (100) DEFAULT NULL,
   `database_info_id`  bigint(8) NOT NULL ,
   `create_date` timestamp DEFAULT CURRENT_TIMESTAMP (),
   `update_date` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   `create_user` varchar (100) DEFAULT NULL,
   `update_user` varchar (100) DEFAULT NULL,
   `description` varchar (200) DEFAULT NULL COMMENT '备注',
   PRIMARY KEY (`id`)
)
ENGINE = InnoDB CHARSET = utf8;