/*
Navicat MySQL Data Transfer

Source Server         : conn
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : shop-db

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2018-06-22 11:11:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
  `uid` int(10) unsigned zerofill NOT NULL,
  `pid` int(10) unsigned zerofill NOT NULL,
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`uid`,`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` varchar(255) NOT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `receiverAddress` varchar(255) DEFAULT NULL,
  `receiverName` varchar(255) DEFAULT NULL,
  `receiverPhone` varchar(255) DEFAULT NULL,
  `paystate` int(4) DEFAULT NULL,
  `orderTime` varchar(20) DEFAULT NULL,
  `uid` int(11) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('15296339130144', '300', '张三路李四街王五号赵六栋三单元305室1', '郑七1', '15915913575', '1', '2018-06-22 10:18:33', '00000000004');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `orderid` varchar(255) NOT NULL,
  `pid` int(11) unsigned zerofill NOT NULL,
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderid`,`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('15296339130144', '00000000004', '1');
INSERT INTO `orderitem` VALUES ('15296339130144', '00000000007', '2');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `pnum` int(11) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('00000000001', 'MySQL', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000002', 'MySQL', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000003', 'MySQL1', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000004', 'MySQL1', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000005', 'MySQL2', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000006', 'MySQL3', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000007', 'MySQL4', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000008', 'MySQL5', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000009', 'MySQL6', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000010', 'MySQL7', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000011', 'MySQL8', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000012', 'MySQL9', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000013', 'MySQL0', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000014', 'MySQL1', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000015', 'MySQL2', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000016', 'MySQL3', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000017', 'MySQL4', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000018', 'MySQL5', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000019', 'MySQL6', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000020', 'MySQL7', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000021', 'MySQL8', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000022', 'MySQL9', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000023', 'MySQL0', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000024', 'MySQL1', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000025', 'MySQL2', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000026', 'MySQL3', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000027', 'MySQL4', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000028', 'MySQL5', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000029', 'MySQL6', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000030', 'MySQL7', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000031', 'MySQL8', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000032', 'MySQL9', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');
INSERT INTO `product` VALUES ('00000000033', 'MySQL0', '100', '20', '计算机', 'MySQL操作指南', '/images/ffd5129c-0550-4293-9084-982cde6a228d.jpg');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  `activeCode` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `state` int(2) DEFAULT NULL,
  `registTime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `asdf` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('00000000003', 'zhangsan', '7C4A8D09CA3762AF61E59520943DC26494F8941B', '男', '123@qq.com', '15911135711', '我是土豪', '123456', 'admin', '1', '2018-06-19 10:35:49');
INSERT INTO `user` VALUES ('00000000004', 'wangwu123', 'FE747FF59C32FB8EE63BB220FCCF1C2C26A94C8A', '女', 'wangwu@qq.com', '15911135719', '我也是土豪', '4321Aa@', 'user', '1', '2018-06-19 15:38:00');
