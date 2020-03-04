CREATE TABLE `g_category` (
`category_id` int(9) NOT NULL AUTO_INCREMENT,
`name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
PRIMARY KEY (`category_id`) 
)
ENGINE = InnoDB
AUTO_INCREMENT = 7
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Dynamic;

CREATE TABLE `g_discount` (
`discount_id` int(9) NOT NULL AUTO_INCREMENT,
`type` int(1) NOT NULL COMMENT '使用check约束\r\n1 表示数量折扣\r\n2 表示现金折扣\r\n3 表示按比例打折',
`value` float(5,2) NOT NULL COMMENT '根据type指定打折力度\r\ntype=1 则存入满足数量减少的金额\r\ntype=2 则存入满足一定金额所减少的金额\r\ntype=3 则存入折扣百分比\r\n',
`quantity_demanded` float(5,2) NULL DEFAULT NULL COMMENT '根据Type指定条件 ，用于保存打折条件。\r\ntype=1  表示满足该值执行数量折扣\r\ntype=2 表示满足该数量执行现金折扣\r\ntype=3 该字段应该为null\r\n',
PRIMARY KEY (`discount_id`) 
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Dynamic;

CREATE TABLE `g_order` (
`order_id` int(9) NOT NULL AUTO_INCREMENT,
`user_id` int(11) NOT NULL,
`create_time` datetime NOT NULL,
PRIMARY KEY (`order_id`) ,
INDEX `fk_order_user_id` (`user_id` ASC) USING BTREE
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Dynamic;

CREATE TABLE `g_product` (
`product_id` int(9) NOT NULL AUTO_INCREMENT,
`price` float(5,2) NOT NULL,
`category_id` int(9) NOT NULL,
`name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
PRIMARY KEY (`product_id`) 
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Dynamic;

CREATE TABLE `g_product_order` (
`product_order_id` int(9) NOT NULL AUTO_INCREMENT,
`product_id` int(9) NOT NULL,
`order_id` int(9) NOT NULL,
`product_count` int(2) NOT NULL,
PRIMARY KEY (`product_order_id`) ,
INDEX `fk_order_id` (`order_id` ASC) USING BTREE,
INDEX `fk_product_id` (`product_id` ASC) USING BTREE
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Dynamic;

CREATE TABLE `g_user` (
`user_id` int(9) NOT NULL AUTO_INCREMENT,
`username` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`password` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
`balance` float(9,0) NULL DEFAULT NULL,
`phone_number` int(11) NOT NULL,
`email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
PRIMARY KEY (`user_id`) 
)
ENGINE = InnoDB
AUTO_INCREMENT = 1
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Dynamic;


ALTER TABLE `g_product` ADD CONSTRAINT `fk_product_category_id` FOREIGN KEY (`product_id`) REFERENCES `g_category` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `g_product_order` ADD CONSTRAINT `fk_order_id` FOREIGN KEY (`order_id`) REFERENCES `g_order` (`order_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `g_product_order` ADD CONSTRAINT `fk_product_id` FOREIGN KEY (`product_id`) REFERENCES `g_product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

