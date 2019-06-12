/*
 Navicat MySQL Data Transfer

 Source Server         : tx_im_qq
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost
 Source Database       : antfin

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : utf-8

 Date: 06/08/2019 15:08:30 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(128) NOT NULL DEFAULT '' COMMENT '业务唯一主键',
  `name` varchar(20) DEFAULT '' COMMENT '名字',
  `sex` varchar(10) DEFAULT '' COMMENT '性别',
  `address` varchar(100) DEFAULT '' COMMENT '地址',
  `age` smallint(6) DEFAULT '0' COMMENT '年龄',
  `creater` varchar(100) NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(100) NOT NULL COMMENT '修改者',
  `updater_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uuid_unique` (`uuid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
