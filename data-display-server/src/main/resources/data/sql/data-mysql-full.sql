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
