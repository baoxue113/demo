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

 Date: 06/08/2019 15:08:47 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `teacher_student_relationship`
-- ----------------------------
DROP TABLE IF EXISTS `teacher_student_relationship`;
CREATE TABLE `teacher_student_relationship` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `teacher_uuid` varchar(128) NOT NULL DEFAULT '',
  `student_uuid` varchar(128) NOT NULL DEFAULT '',
  `student_name` varchar(20) NOT NULL DEFAULT '',
  `creater` varchar(20) NOT NULL DEFAULT '',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updater` varchar(20) NOT NULL DEFAULT '',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `teacher_uuid_ student_name_uni` (`teacher_uuid`,`student_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
