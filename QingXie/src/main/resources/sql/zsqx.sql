/**
 * 3/5更新
 */
-- ----------------------------
-- Table structure for icon
-- 删除了user_id键
-- ----------------------------
DROP TABLE IF EXISTS `icon`;
CREATE TABLE `icon`  (
  `icon_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '头像图片id',
  `icon_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片路径',
  `create_time` TIMESTAMP DEFAULT current_timestamp() ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`icon_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

/**
 *用户信息表
 */
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `student_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生学号',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码，用md5加密存储',
  `flag` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'S' COMMENT '用户标识,S:学生/游客，V:volunteer，Q：青协工作者，A：管理人员',
  `role_id` int DEFAULT 0 COMMENT '对应角色的id',
  `gender` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT 'M' COMMENT 'M: Male, F: Female',
  `class_id` int(6) NOT NULL DEFAULT 0 COMMENT '班级id',
  `hours` int(11) NOT NULL DEFAULT 0 COMMENT '用户总工时',
  `icon_id` int(11) DEFAULT NULL COMMENT '头像ID',
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户手机，用于活动负责人与志愿者间联系',
  `qq` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户qq，用于活动负责人与志愿者间联系',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户邮箱，方便用于找回密码等操作',
  `wechat` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户微信，用于活动负责人与志愿者间联系',
  `token` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用于验证系统用户的token值',
  `validation` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用于验证邮箱等等操作的时候',
  `last_login_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后一次登陆的时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `student_id`(`student_id`) USING BTREE,
  INDEX `idx_studentId`(`student_id`) USING BTREE,
  INDEX `idx_name`(`name`) USING BTREE,
  CONSTRAINT `user_icon_id` FOREIGN KEY (`icon_id`) REFERENCES `icon` (`icon_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

/**
 *活动信息表
 *记录所有志愿活动的信息
 * 时间类型选择问题，timestamp和datetime
 */
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动名称',
  `manager_id` int(11) NOT NULL DEFAULT 0 COMMENT '活动负责人',
  `type` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '1表示特色活动，2表示普通活动',
  `status` int DEFAULT NULL COMMENT '1表示未开始，2表示进行中，3表示已结束',
  `hours` int(11) DEFAULT 0 COMMENT '志愿活动的总工时数',
  `hour_per_time` int(11) NOT NULL DEFAULT 0 COMMENT '每次参与志愿活动的工时数',
  `need_volunteers` int(11) NOT NULL DEFAULT 10 COMMENT '接受报名的志愿者数',
  `place` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '志愿活动开展地点。如东院敬老活动的地点为东院。。甚至更详细',
  `general` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '活动概述',
  `descriptions` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '志愿活动的详细描述,活动内容？活动任务等？',
  `reg_time` datetime(0) COMMENT '活动报名开始时间',
  `reg_end_time` datetime(0) COMMENT '活动报名结束时间',
  `interview_time` datetime(0) DEFAULT NULL COMMENT '活动面试时间',
  `start_time` datetime(0) NOT NULL COMMENT '活动开始时间',
  `end_time` datetime(0) NOT NULL COMMENT '活动结束时间',
  `create_time` TIMESTAMP DEFAULT current_timestamp() COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_manager`(`manager_id`) USING BTREE,
  INDEX `idx_voluntary_hour`(`hour_per_time`) USING BTREE,
  INDEX `idx_startTime`(`start_time`) USING BTREE,
  INDEX `idx_endTime`(`end_time`) USING BTREE,
  CONSTRAINT `activity_manager_id` FOREIGN KEY (`manager_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '活动信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for activity_datails_picture
-- ----------------------------
DROP TABLE IF EXISTS `activity_details_picture`;
CREATE TABLE `activity_details_picture`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动详情页图片ID',
  `activity_id` int(11) NOT NULL COMMENT '活动ID',
  `actividt_detail_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动详情页图片地址',
  `create_time` TIMESTAMP DEFAULT current_timestamp() COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `fk_activity_id` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for homepage_picture
-- ----------------------------
DROP TABLE IF EXISTS `homepage_picture`;
CREATE TABLE `homepage_picture`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '首页图片ID',
  `activity_id` int(11) NOT NULL COMMENT '活动ID',
  `picture_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片地址',
  `create_time` TIMESTAMP DEFAULT current_timestamp() COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `fk_homepage_picture_activity` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


/**
 * 新增我的收藏----活动收藏
 */
DROP TABLE IF EXISTS `favourite`;
CREATE TABLE `favourite` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` INT  COMMENT '用户id',
  `activity_id` INT COMMENT '活动id',
  `create_time` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `user_activity_activity_id` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_activity_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户志愿活动关联表';

/**
 * 用户自定义的活动经历
 */
DROP TABLE IF EXISTS `user_experience`;
CREATE TABLE `user_experience` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` INT  COMMENT '用户id',
  `activity_name` VARCHAR(225) COMMENT '活动名称',
  `begin` DATETIME  COMMENT '创建时间',
  `end` DATETIME  COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `user_experience_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户经历表';



-- ----------------------------
-- Table structure for icon
-- 删除了user_id键
-- ----------------------------
DROP TABLE IF EXISTS `icon`;
CREATE TABLE `icon`  (
  `icon_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '头像图片id',
  `icon_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片路径',
  `create_time` TIMESTAMP DEFAULT current_timestamp() ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`icon_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;




/**
 *志愿活动报名表
 *
 *用户id根据使用用户的id填充
 */
DROP TABLE IF EXISTS `register_form`;
CREATE TABLE `register_form` (
  `user_id` INT COMMENT '用户id',
  `activity_id`INT COMMENT '活动id',
  `telephone` BIGINT DEFAULT NULL COMMENT '用户手机，用于活动负责人与志愿者间联系',
  `qq` BIGINT DEFAULT NULL COMMENT '用户qq，用于活动负责人与志愿者间联系',
  `email` varchar(30) DEFAULT NULL COMMENT '用户邮箱，方便用于找回密码等操作',
  `descriptions` text COMMENT '个性特长等',
  `create_time` TIMESTAMP DEFAULT current_timestamp() COMMENT '创建时间',
  PRIMARY KEY (user_id,activity_id),
  KEY idx_create_time(create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '志愿活动报名表';

/**
 *用户志愿活动关联表
 *绑定用户报名、面试等信息
 */
DROP TABLE IF EXISTS `user_activity`;
CREATE TABLE `user_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` INT  COMMENT '用户id',
  `activity_id` INT COMMENT '活动id',
  `count` INT  COMMENT '参与志愿工作次数---根据次数统计总时间',
  `status` int(4) COMMENT '进行状态，1：报名，-1:报名失败，2:面试，-2:面试失败，3:参与志愿服务当中,0:默认值，无意义',
  `stuff` int(4) COMMENT '志愿活动中担任的角色,0:志愿者，1:负责人,2:青协工作人员',
  `condition` INT DEFAULT 0 COMMENT '进行状态，1：报名，-1:报名失败，2:面试，-2:面试失败，3:参与志愿服务当中,0:默认值，无意义',
  `reg_time` DATETIME COMMENT '报名时间',
  `interview_time` DATETIME  COMMENT '面试时间',
  `create_time` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY idx_registerTime(reg_time),
  KEY idx_interviewTime(interview_time),
  CONSTRAINT `user_activity_activity_id` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_activity_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户志愿活动关联表';


/**
 *   PRIMARY KEY (user_id,activity_id), 防止重复
 *用户活动工时关联表
 *每个用户参与每个志愿活动在此表中都有对应的一条工时记录
 *统计用户总工时时将用户在此表对应的工时数累加
 */
DROP TABLE IF EXISTS `user_activity_hours`;
CREATE TABLE `user_activity_hours` (
  `user_id` INT  COMMENT '用户id',
  `activity_id` INT COMMENT '活动id',
  `voluntary_hours` INT NOT NULL DEFAULT '0' COMMENT '用户对应活动的工时数',
  `is_confirm` INT NOT NULL COMMENT '是否确认无误,1:确认，0:未确认，-1:有误',
  `create_time` timestamp DEFAULT current_timestamp() COMMENT '记录时间',
  PRIMARY KEY (user_id,activity_id),
  KEY idx_create_time(create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户活动工时明细表';




/**
工时变化日志表
 */
DROP TABLE IF EXISTS `hours_change_log`;
CREATE TABLE `hours_change_log`(
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT 'id',
  `user_id` INT NOT NULL COMMENT '用户id',
  `change` DOUBLE NOT NULL COMMENT '工时变化值',
  `hours` DOUBLE NOT NULL COMMENT '变化后的工时',
  `update_time` TIMESTAMP DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '更新时间',
  CONSTRAINT `fk_hours_log_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '工时信息变化表';

/**
 *班级表
 */
DROP TABLE IF EXISTS `class_info`;
CREATE TABLE `class_info` (
  `id` smallint(5)  PRIMARY KEY AUTO_INCREMENT COMMENT '班级id',
  `name` varchar(50) NOT NULL COMMENT '班级名称',
  `user_id` INT COMMENT '班级负责人id',
  KEY idx_className(name),
  CONSTRAINT `fk_class_info_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '班级表';


/**系统类表**/

/**
 *活动通知表
 */
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int  PRIMARY KEY AUTO_INCREMENT COMMENT '通知id',
  `information` text NOT NULL COMMENT '通知内容',
  `create_time` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '活动通知表';


