/*
Navicat MySQL Data Transfer

Source Server         : jiesong
Source Server Version : 50149
Source Host           : localhost:3306
Source Database       : myblogs

Target Server Type    : MYSQL
Target Server Version : 50149
File Encoding         : 65001

Date: 2017-10-31 11:28:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(20) NOT NULL,
  `nick_name` varchar(40) NOT NULL,
  `pass_word` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `mobile_num` varchar(11) NOT NULL,
  `user_type` char(1) NOT NULL,
  `status` char(1) NOT NULL COMMENT '用户状态  Y有效/N注销/L锁定',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '牛志伟', 'test', '111111', '125@126.com', '13611263262', 'S', 'Y');
