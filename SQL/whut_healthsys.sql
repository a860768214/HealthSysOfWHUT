/*
 Navicat Premium Data Transfer

 Source Server         : 当前计算机
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : whut_healthsys

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 09/08/2020 22:14:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for healthsys_appointment
-- ----------------------------
DROP TABLE IF EXISTS `healthsys_appointment`;
CREATE TABLE `healthsys_appointment`  (
  `uid` int(11) NOT NULL,
  `did` int(11) NOT NULL,
  `time` int(11) NULL DEFAULT NULL,
  `status` int(1) NULL DEFAULT -1,
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for healthsys_clinic
-- ----------------------------
DROP TABLE IF EXISTS `healthsys_clinic`;
CREATE TABLE `healthsys_clinic`  (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `status` int(1) NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of healthsys_clinic
-- ----------------------------
INSERT INTO `healthsys_clinic` VALUES (1, '南湖门诊', '123123', 1, '南湖大门旁');
INSERT INTO `healthsys_clinic` VALUES (2, '其他门诊', '000000', 0, '老九旁');

-- ----------------------------
-- Table structure for healthsys_psydoc
-- ----------------------------
DROP TABLE IF EXISTS `healthsys_psydoc`;
CREATE TABLE `healthsys_psydoc`  (
  `did` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `gender` int(1) NULL DEFAULT 1,
  `qq` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `cretime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(1) NOT NULL,
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(3) NULL DEFAULT NULL,
  PRIMARY KEY (`did`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for healthsys_reservation
-- ----------------------------
DROP TABLE IF EXISTS `healthsys_reservation`;
CREATE TABLE `healthsys_reservation`  (
  `uid` int(11) NOT NULL,
  `cid` int(11) NOT NULL,
  `time` int(11) NULL DEFAULT NULL,
  `status` int(1) NULL DEFAULT -1,
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dept` int(3) NULL DEFAULT NULL,
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for healthsys_user
-- ----------------------------
DROP TABLE IF EXISTS `healthsys_user`;
CREATE TABLE `healthsys_user`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `gender` int(1) NULL DEFAULT 1,
  `qq` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `schmail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `cretime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(1) NOT NULL,
  `age` int(3) NULL DEFAULT NULL,
  `height` double(4, 0) NULL DEFAULT NULL,
  `weight` double NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of healthsys_user
-- ----------------------------
INSERT INTO `healthsys_user` VALUES (1, 'hsy', 1, '', '18171080047', 'a860768214@whut.edu.cn', '1596364001277', 1, NULL, NULL, NULL);
INSERT INTO `healthsys_user` VALUES (2, 'admin', 1, '', '', '123@whut.edu.cn', NULL, -1, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
