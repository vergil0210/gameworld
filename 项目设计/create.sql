/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : game_world

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 07/04/2020 19:17:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for g_admin
-- ----------------------------
DROP TABLE IF EXISTS `g_admin`;
CREATE TABLE `g_admin`  (
  `admin_id` varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `g_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `g_permission_level` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限等级\r\nT0 表示最高权限\r\nT1 拥有订单模块和产品模块权限\r\nT2 拥有订单模块权限\r\nT3 无权限',
  `g_username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of g_admin
-- ----------------------------
INSERT INTO `g_admin` VALUES ('1', '123123', 'T0', 'T0Admin');
INSERT INTO `g_admin` VALUES ('1111', '123123', 'T1', 'T1Admin');
INSERT INTO `g_admin` VALUES ('1815', '123123', 'T0', 'vivian');
INSERT INTO `g_admin` VALUES ('19980816', '123123', 'T0', 'vergil');
INSERT INTO `g_admin` VALUES ('2222', '123123', 'T2', 'T2Admin');
INSERT INTO `g_admin` VALUES ('3333', '123123', 'T3', 'T3Admin');

-- ----------------------------
-- Table structure for g_comment
-- ----------------------------
DROP TABLE IF EXISTS `g_comment`;
CREATE TABLE `g_comment`  (
  `comment_id` int(8) NOT NULL,
  `user_id` int(9) NOT NULL,
  `product_id` int(9) NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  `comment_time` datetime(0) NULL,
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `pk_comment_user_id`(`user_id`) USING BTREE,
  INDEX `pk_comment_product_id`(`product_id`) USING BTREE,
  CONSTRAINT `pk_comment_product_id` FOREIGN KEY (`product_id`) REFERENCES `g_product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `pk_comment_user_id` FOREIGN KEY (`user_id`) REFERENCES `g_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for g_discount
-- ----------------------------
DROP TABLE IF EXISTS `g_discount`;
CREATE TABLE `g_discount`  (
  `discount_id` int(9) NOT NULL AUTO_INCREMENT,
  `type` int(1) NOT NULL COMMENT '使用check约束\r\n1 表示数量折扣\r\n2 表示现金折扣\r\n3 表示按比例打折',
  `value` float(5, 2) NOT NULL COMMENT '根据type指定打折力度\r\ntype=1 则存入满足数量减少的金额\r\ntype=2 则存入满足一定金额所减少的金额\r\ntype=3 则存入折扣百分比\r\n',
  `quantity_demanded` float(5, 2) NULL DEFAULT NULL COMMENT '根据Type指定条件 ，用于保存打折条件。\r\ntype=1  表示满足该值执行数量折扣\r\ntype=2 表示满足该数量执行现金折扣\r\ntype=3 该字段应该为null\r\n',
  PRIMARY KEY (`discount_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for g_order
-- ----------------------------
DROP TABLE IF EXISTS `g_order`;
CREATE TABLE `g_order`  (
  `order_id` int(9) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `create_time` datetime(0) NULL,
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '订单状态 默认为0\r\n0 表示 待付款\r\n1 表示 正在处理\r\n2 表示 拒绝退款\r\n3 表示 退款申请中\r\n4 表示 已退款\r\n5 表示 订单已关闭\r\n',
  `pay_pattern` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '支付方式 默认为0 0表示未支付 1表示支付宝 2表示微信 3表示银行卡',
  `total_price` double(8, 2) NOT NULL COMMENT '总价',
  `return_reason` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `fk_order_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `fk_order_user_id` FOREIGN KEY (`user_id`) REFERENCES `g_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 78 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of g_order
-- ----------------------------
INSERT INTO `g_order` VALUES (65, 1, '2020-04-02 07:43:10', '5', '3', 159.00, NULL);
INSERT INTO `g_order` VALUES (66, 1, '2020-04-02 07:43:11', '5', '3', 159.00, NULL);
INSERT INTO `g_order` VALUES (67, 1, '2020-04-02 07:43:12', '5', '3', 159.00, '');
INSERT INTO `g_order` VALUES (68, 1, '2020-04-02 07:43:13', '4', '3', 159.00, NULL);
INSERT INTO `g_order` VALUES (69, 1, '2020-04-02 08:22:22', '5', '2', 249.00, NULL);
INSERT INTO `g_order` VALUES (70, 1, '2020-04-02 08:22:23', '3', '2', 249.00, NULL);
INSERT INTO `g_order` VALUES (71, 1, '2020-04-02 08:22:24', '3', '2', 249.00, NULL);
INSERT INTO `g_order` VALUES (72, 1, '2020-04-02 08:22:26', '4', '2', 249.00, NULL);
INSERT INTO `g_order` VALUES (73, 1, '2020-04-03 17:13:46', '4', '2', 249.00, NULL);
INSERT INTO `g_order` VALUES (74, 1, '2020-04-03 17:13:47', '4', '2', 249.00, NULL);
INSERT INTO `g_order` VALUES (75, 1, '2020-04-03 17:13:48', '3', '2', 249.00, NULL);
INSERT INTO `g_order` VALUES (76, 1, '2020-04-03 17:13:49', '3', '2', 249.00, NULL);
INSERT INTO `g_order` VALUES (77, 1, '2020-04-03 17:13:51', '3', '2', 249.00, NULL);
INSERT INTO `g_order` VALUES (78, 1, '2020-04-03 17:13:51', '3', '2', 249.00, NULL);

-- ----------------------------
-- Table structure for g_order_items
-- ----------------------------
DROP TABLE IF EXISTS `g_order_items`;
CREATE TABLE `g_order_items`  (
  `product_id` int(9) NOT NULL,
  `order_id` int(9) NOT NULL,
  PRIMARY KEY (`product_id`, `order_id`) USING BTREE,
  INDEX `fk_order_id`(`order_id`) USING BTREE,
  INDEX `fk_product_id`(`product_id`) USING BTREE,
  CONSTRAINT `fk_order_id` FOREIGN KEY (`order_id`) REFERENCES `g_order` (`order_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_product_id` FOREIGN KEY (`product_id`) REFERENCES `g_product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of g_order_items
-- ----------------------------
INSERT INTO `g_order_items` VALUES (13, 65);
INSERT INTO `g_order_items` VALUES (16, 65);
INSERT INTO `g_order_items` VALUES (13, 66);
INSERT INTO `g_order_items` VALUES (16, 66);
INSERT INTO `g_order_items` VALUES (13, 67);
INSERT INTO `g_order_items` VALUES (16, 67);
INSERT INTO `g_order_items` VALUES (13, 68);
INSERT INTO `g_order_items` VALUES (16, 68);
INSERT INTO `g_order_items` VALUES (13, 69);
INSERT INTO `g_order_items` VALUES (14, 69);
INSERT INTO `g_order_items` VALUES (16, 69);
INSERT INTO `g_order_items` VALUES (13, 70);
INSERT INTO `g_order_items` VALUES (14, 70);
INSERT INTO `g_order_items` VALUES (16, 70);
INSERT INTO `g_order_items` VALUES (13, 71);
INSERT INTO `g_order_items` VALUES (14, 71);
INSERT INTO `g_order_items` VALUES (16, 71);
INSERT INTO `g_order_items` VALUES (13, 72);
INSERT INTO `g_order_items` VALUES (14, 72);
INSERT INTO `g_order_items` VALUES (16, 72);
INSERT INTO `g_order_items` VALUES (13, 73);
INSERT INTO `g_order_items` VALUES (14, 73);
INSERT INTO `g_order_items` VALUES (16, 73);
INSERT INTO `g_order_items` VALUES (13, 74);
INSERT INTO `g_order_items` VALUES (14, 74);
INSERT INTO `g_order_items` VALUES (16, 74);
INSERT INTO `g_order_items` VALUES (13, 75);
INSERT INTO `g_order_items` VALUES (14, 75);
INSERT INTO `g_order_items` VALUES (16, 75);
INSERT INTO `g_order_items` VALUES (13, 76);
INSERT INTO `g_order_items` VALUES (14, 76);
INSERT INTO `g_order_items` VALUES (16, 76);
INSERT INTO `g_order_items` VALUES (13, 77);
INSERT INTO `g_order_items` VALUES (14, 77);
INSERT INTO `g_order_items` VALUES (16, 77);
INSERT INTO `g_order_items` VALUES (13, 78);
INSERT INTO `g_order_items` VALUES (14, 78);
INSERT INTO `g_order_items` VALUES (16, 78);

-- ----------------------------
-- Table structure for g_product
-- ----------------------------
DROP TABLE IF EXISTS `g_product`;
CREATE TABLE `g_product`  (
  `product_id` int(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述\r\n',
  `original_price` float(5, 2) NOT NULL,
  `sales_volume` int(10) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '销售额',
  `developer` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '开发商',
  `publisher` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发行商',
  `promote_price` float(5, 2) NULL DEFAULT NULL COMMENT '促销价',
  `type_id` int(9) NULL DEFAULT NULL,
  `is_on_sale` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否上架 0表示不上架 1表示上架',
  `is_hot` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否热销',
  `show_in_front` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否前台展示',
  `publish_date` datetime(0) NULL DEFAULT NULL COMMENT '上线时间',
  `grade` float(2, 0) NULL DEFAULT NULL COMMENT '评分：5.0 -0.0',
  PRIMARY KEY (`product_id`) USING BTREE,
  INDEX `fk_product_type_id`(`type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of g_product
-- ----------------------------
INSERT INTO `g_product` VALUES (13, 'ARK: Survival Evolved', '由虚幻4引擎打造的一款多人在线生存竞技网游，在一个超高自由度的开放世界里，可以体验采集、制造、打猎、收获、建造、研究以及驯服恐龙等超多自由内容，感受酷热白昼、冰冷夜晚的动态天气系统以及饥饿口渴等现实中的生存挑战，还要面对其它生存者的威胁，合作生存还是竞技厮杀，由你决定！', 100.00, NULL, 'Studio Wildcard', 'Studio Wildcard', NULL, NULL, 2, 0, 0, '2020-03-01 04:00:00', NULL);
INSERT INTO `g_product` VALUES (14, 'ASTRONEER', '探索并重塑遥远世界！《ASTRONEER》的舞台设定在淘金热潮兴起的 25 世纪。玩家必须探索外太空边境，冒着生命危险在艰难环境下勘探资源，寻找一夜暴富的机会。', 90.00, NULL, 'System Era Softworks', 'System Era Softworks', NULL, NULL, 1, 0, 0, '2020-03-03 08:00:00', NULL);
INSERT INTO `g_product` VALUES (16, 'Yes, Your Grace', '我的陛下，在这纷乱的时代，上访者蜂拥而至。有限的物资无法满足所有人的需求；除此之外，我们还要为战争做准备。在这之间维持平衡的确很艰难……但是，该上朝了，陛下。', 59.00, NULL, 'Brave At Night', 'No More Robots', NULL, NULL, 1, 0, 0, '2020-03-03 04:00:00', NULL);
INSERT INTO `g_product` VALUES (17, 'THE LONGING', '地底王国原本由一位国王统治，他的力量现已消逝，需要安睡400天才能恢复。你名叫“影子”，是国王最后的仆人。你必须独守地下宫殿，直到国王醒来为止。\n', 50.00, NULL, 'Studio Seufz', 'Application Systems Heidelberg', NULL, NULL, 1, 0, 0, '2020-03-14 04:00:00', NULL);
INSERT INTO `g_product` VALUES (18, 'Grim Dawn', 'Enter an apocalyptic fantasy world where humanity is on the brink of extinction, iron is valued above gold and trust is hard earned. This ARPG features complex character development, hundreds of unique items, crafting and quests with choice & consequence.', 120.00, NULL, 'Crate Entertainment', 'Crate Entertainment', NULL, NULL, 1, 0, 0, '2020-04-02 04:00:00', NULL);

-- ----------------------------
-- Table structure for g_product__type
-- ----------------------------
DROP TABLE IF EXISTS `g_product__type`;
CREATE TABLE `g_product__type`  (
  `g_product_id` int(9) NOT NULL,
  `g_type_id` int(9) NOT NULL,
  PRIMARY KEY (`g_product_id`, `g_type_id`) USING BTREE,
  INDEX `fk_pt_type_id`(`g_type_id`) USING BTREE,
  CONSTRAINT `fk_pt_product_id` FOREIGN KEY (`g_product_id`) REFERENCES `g_product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_pt_type_id` FOREIGN KEY (`g_type_id`) REFERENCES `g_type` (`type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of g_product__type
-- ----------------------------
INSERT INTO `g_product__type` VALUES (14, 20);
INSERT INTO `g_product__type` VALUES (16, 20);
INSERT INTO `g_product__type` VALUES (17, 20);
INSERT INTO `g_product__type` VALUES (18, 20);
INSERT INTO `g_product__type` VALUES (14, 21);
INSERT INTO `g_product__type` VALUES (16, 21);
INSERT INTO `g_product__type` VALUES (18, 21);
INSERT INTO `g_product__type` VALUES (16, 22);
INSERT INTO `g_product__type` VALUES (18, 23);
INSERT INTO `g_product__type` VALUES (14, 24);
INSERT INTO `g_product__type` VALUES (16, 24);
INSERT INTO `g_product__type` VALUES (17, 24);
INSERT INTO `g_product__type` VALUES (14, 27);
INSERT INTO `g_product__type` VALUES (17, 27);

-- ----------------------------
-- Table structure for g_product_attribute
-- ----------------------------
DROP TABLE IF EXISTS `g_product_attribute`;
CREATE TABLE `g_product_attribute`  (
  `g_prod_attri_id` int(9) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性名',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '属性值',
  `prod_id` int(9) NOT NULL,
  PRIMARY KEY (`g_prod_attri_id`) USING BTREE,
  INDEX `fk_property_product_id`(`prod_id`) USING BTREE,
  CONSTRAINT `fk_property_product_id` FOREIGN KEY (`prod_id`) REFERENCES `g_product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for g_product_pic
-- ----------------------------
DROP TABLE IF EXISTS `g_product_pic`;
CREATE TABLE `g_product_pic`  (
  `product_pic_id` int(9) NOT NULL,
  `data` mediumblob NOT NULL COMMENT '保存的图片',
  `product_id` int(9) NULL DEFAULT NULL,
  `type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`product_pic_id`) USING BTREE,
  INDEX `fk_pic_product_id`(`product_id`) USING BTREE,
  CONSTRAINT `fk_pic_product_id` FOREIGN KEY (`product_id`) REFERENCES `g_product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for g_type
-- ----------------------------
DROP TABLE IF EXISTS `g_type`;
CREATE TABLE `g_type`  (
  `type_id` int(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of g_type
-- ----------------------------
INSERT INTO `g_type` VALUES (20, '冒险');
INSERT INTO `g_type` VALUES (21, '模拟');
INSERT INTO `g_type` VALUES (22, '策略');
INSERT INTO `g_type` VALUES (23, '多人在线');
INSERT INTO `g_type` VALUES (24, '休闲');
INSERT INTO `g_type` VALUES (25, '体育');
INSERT INTO `g_type` VALUES (26, '动作');
INSERT INTO `g_type` VALUES (27, '独立');
INSERT INTO `g_type` VALUES (28, '竞速');
INSERT INTO `g_type` VALUES (29, '角色扮演');
INSERT INTO `g_type` VALUES (30, '开放世界');
INSERT INTO `g_type` VALUES (31, '沙盒');
INSERT INTO `g_type` VALUES (32, '驾驶');
INSERT INTO `g_type` VALUES (36, '拉屎');
INSERT INTO `g_type` VALUES (37, '单机');

-- ----------------------------
-- Table structure for g_user
-- ----------------------------
DROP TABLE IF EXISTS `g_user`;
CREATE TABLE `g_user`  (
  `user_id` int(9) NOT NULL AUTO_INCREMENT,
  `username` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `balance` float(9, 0) NULL DEFAULT NULL,
  `phone_number` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of g_user
-- ----------------------------
INSERT INTO `g_user` VALUES (1, 'test1', '123123', 1000388, '18079809987', '845800056@qq.com');
INSERT INTO `g_user` VALUES (2, '君君子', '123123', 532130496, '123123123', '2414124124@qq.com');

SET FOREIGN_KEY_CHECKS = 1;
