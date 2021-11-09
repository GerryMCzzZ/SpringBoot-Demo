/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : springboot_demo

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2021-11-09 16:05:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sd_system_info_users
-- ----------------------------
DROP TABLE IF EXISTS `sd_system_info_users`;
CREATE TABLE `sd_system_info_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表id',
  `username` varchar(255) NOT NULL COMMENT '唯一账号',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `name` varchar(255) NOT NULL COMMENT '真实姓名',
  `company_id` int(11) NOT NULL COMMENT '所属公司id',
  `role_id` int(11) NOT NULL COMMENT '权限角色id：0管理，1教师',
  `token_code` varchar(32) DEFAULT NULL COMMENT '登陆token码',
  `token_timeout` varchar(255) DEFAULT NULL COMMENT 'token过期时间，登陆赋值改变，验证',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sd_system_info_users
-- ----------------------------
INSERT INTO `sd_system_info_users` VALUES ('1', 'GerryMC', '43f7482ea68d5fd0c5cae553375d4afa', '格里晨', '0', '0', null, null);
