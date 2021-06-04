/*
 Navicat Premium Data Transfer

 Source Server         : docker-mysql-5.7
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : ds

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 04/06/2021 23:48:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_config
-- ----------------------------
DROP TABLE IF EXISTS `t_config`;
CREATE TABLE `t_config` (
  `config_id` int(16) NOT NULL AUTO_INCREMENT,
  `para_name` varchar(255) DEFAULT NULL,
  `para_value` varchar(255) DEFAULT NULL,
  `para_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order0
-- ----------------------------
DROP TABLE IF EXISTS `t_order0`;
CREATE TABLE `t_order0` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order1
-- ----------------------------
DROP TABLE IF EXISTS `t_order1`;
CREATE TABLE `t_order1` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order10
-- ----------------------------
DROP TABLE IF EXISTS `t_order10`;
CREATE TABLE `t_order10` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order11
-- ----------------------------
DROP TABLE IF EXISTS `t_order11`;
CREATE TABLE `t_order11` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order2
-- ----------------------------
DROP TABLE IF EXISTS `t_order2`;
CREATE TABLE `t_order2` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order3
-- ----------------------------
DROP TABLE IF EXISTS `t_order3`;
CREATE TABLE `t_order3` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order4
-- ----------------------------
DROP TABLE IF EXISTS `t_order4`;
CREATE TABLE `t_order4` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order5
-- ----------------------------
DROP TABLE IF EXISTS `t_order5`;
CREATE TABLE `t_order5` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order6
-- ----------------------------
DROP TABLE IF EXISTS `t_order6`;
CREATE TABLE `t_order6` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order7
-- ----------------------------
DROP TABLE IF EXISTS `t_order7`;
CREATE TABLE `t_order7` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order8
-- ----------------------------
DROP TABLE IF EXISTS `t_order8`;
CREATE TABLE `t_order8` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order9
-- ----------------------------
DROP TABLE IF EXISTS `t_order9`;
CREATE TABLE `t_order9` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order_item0
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item0`;
CREATE TABLE `t_order_item0` (
  `item_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order_item1
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item1`;
CREATE TABLE `t_order_item1` (
  `item_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order_item10
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item10`;
CREATE TABLE `t_order_item10` (
  `item_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order_item11
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item11`;
CREATE TABLE `t_order_item11` (
  `item_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order_item2
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item2`;
CREATE TABLE `t_order_item2` (
  `item_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order_item3
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item3`;
CREATE TABLE `t_order_item3` (
  `item_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order_item4
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item4`;
CREATE TABLE `t_order_item4` (
  `item_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order_item5
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item5`;
CREATE TABLE `t_order_item5` (
  `item_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order_item6
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item6`;
CREATE TABLE `t_order_item6` (
  `item_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order_item7
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item7`;
CREATE TABLE `t_order_item7` (
  `item_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order_item8
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item8`;
CREATE TABLE `t_order_item8` (
  `item_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for t_order_item9
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item9`;
CREATE TABLE `t_order_item9` (
  `item_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` bigint(128) NOT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `account` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
