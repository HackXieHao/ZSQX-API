-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: localhost    Database: qingxie
-- ------------------------------------------------------
-- Server version	5.7.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity`
--


DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动id',
  `name` varchar(50) NOT NULL COMMENT '活动名称',
  `manager_id` int(11) DEFAULT NULL COMMENT '活动负责人',
  `type` varchar(11) NOT NULL COMMENT '1表示特色活动，2表示普通活动',
  `status` int(11) COMMENT '1表示未开始，2表示进行中，3表示已结束',
  `hours` double DEFAULT NULL COMMENT '志愿活动的总工时数',
  `hour_per_time` double DEFAULT NULL COMMENT '每次参与志愿活动的工时数',
  `need_volunteers` int(11) DEFAULT NULL COMMENT '接受报名的志愿者数',
  `place` varchar(100) NOT NULL COMMENT '志愿活动开展地点。如东院敬老活动的地点为东院。。甚至更详细',
  `general` varchar(255) DEFAULT NULL COMMENT '活动概述',
  `descriptions` varchar(255) DEFAULT NULL COMMENT '志愿活动的详细描述,活动内容？活动任务等？',
  `reg_time` datetime DEFAULT NULL COMMENT '活动报名开始时间',
  `reg_end_time` datetime DEFAULT NULL COMMENT '活动报名结束时间',
  `interview_time` datetime DEFAULT NULL COMMENT '活动面试时间',
  `start_time` datetime NOT NULL COMMENT '活动开始时间',
  `end_time` datetime NOT NULL COMMENT '活动结束时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `sponser` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_manager` (`manager_id`) USING BTREE,
  KEY `idx_voluntary_hour` (`hour_per_time`) USING BTREE,
  KEY `idx_startTime` (`start_time`) USING BTREE,
  KEY `idx_endTime` (`end_time`) USING BTREE,
  CONSTRAINT `activity_manager_id` FOREIGN KEY (`manager_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='活动信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (2,'敬老院活动',1,'2',NULL,0,4,10,'东院敬老院','东院敬老院活动，打扫卫生',NULL,'2018-03-03 14:00:00','2018-03-04 17:00:00','2018-03-05 10:30:00','2018-03-12 10:00:00','2018-03-12 14:00:00','2018-03-05 15:54:59',NULL),(3,'敬老院活动',1,'2',NULL,0,4,10,'东院敬老院','东院敬老院活动，打扫卫生',NULL,'2018-03-03 14:00:00','2018-03-04 17:00:00','2018-03-05 10:30:00','2018-03-12 10:00:00','2018-03-12 14:00:00','2018-03-05 15:59:24',NULL),(4,'敬老院活动',1,'2',NULL,0,4,10,'东院敬老院','东院敬老院活动，打扫卫生',NULL,'2018-03-03 14:00:00','2018-03-04 17:00:00','2018-03-05 10:30:00','2018-03-12 10:00:00','2018-03-12 14:00:00','2018-03-06 02:00:37',NULL),(5,'kidsChanel乐文尼',1,'0',NULL,NULL,6,12,'武汉理工whut','南湖图书馆',NULL,'2018-03-07 05:05:55','2018-03-10 05:06:00','2018-03-13 05:06:02','2018-03-21 05:06:04','2018-03-25 05:06:07','2018-03-07 05:06:16',NULL);
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_details_picture`
--

DROP TABLE IF EXISTS `activity_details_picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_details_picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动详情页图片ID',
  `activity_id` int(11) NOT NULL COMMENT '活动ID',
  `actividt_detail_uri` varchar(255) NOT NULL COMMENT '活动详情页图片地址',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_activity_id` (`activity_id`),
  CONSTRAINT `fk_activity_id` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_details_picture`
--

LOCK TABLES `activity_details_picture` WRITE;
/*!40000 ALTER TABLE `activity_details_picture` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity_details_picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_info`
--

DROP TABLE IF EXISTS `class_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '班级id',
  `name` varchar(50) NOT NULL COMMENT '班级名称',
  `user_id` int(11) DEFAULT NULL COMMENT '班级负责人id',
  PRIMARY KEY (`id`),
  KEY `idx_className` (`name`),
  KEY `fk_class_info_user` (`user_id`),
  CONSTRAINT `fk_class_info_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='班级表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_info`
--

LOCK TABLES `class_info` WRITE;
/*!40000 ALTER TABLE `class_info` DISABLE KEYS */;
INSERT INTO `class_info` VALUES (1,'信管1501',2);
/*!40000 ALTER TABLE `class_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favourite`
--

DROP TABLE IF EXISTS `favourite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favourite` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `activity_id` int(11) DEFAULT NULL COMMENT '活动id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_favourite_activity` (`activity_id`),
  KEY `fk_favourite_user` (`user_id`),
  CONSTRAINT `fk_favourite_activity` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`),
  CONSTRAINT `fk_favourite_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户志愿活动关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favourite`
--

LOCK TABLES `favourite` WRITE;
/*!40000 ALTER TABLE `favourite` DISABLE KEYS */;
/*!40000 ALTER TABLE `favourite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `homepage_picture`
--

DROP TABLE IF EXISTS `homepage_picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `homepage_picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '首页图片ID',
  `activity_id` int(11) NOT NULL COMMENT '活动ID',
  `picture_url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_homepage_picture_activity` (`activity_id`),
  CONSTRAINT `fk_homepage_picture_activity` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `homepage_picture`
--

LOCK TABLES `homepage_picture` WRITE;
/*!40000 ALTER TABLE `homepage_picture` DISABLE KEYS */;
/*!40000 ALTER TABLE `homepage_picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hours_change_log`
--

DROP TABLE IF EXISTS `hours_change_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hours_change_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `change` double NOT NULL COMMENT '工时变化值',
  `hours` double NOT NULL COMMENT '变化后的工时',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `fk_hours_log_user` (`user_id`),
  CONSTRAINT `fk_hours_log_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工时信息变化表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hours_change_log`
--

LOCK TABLES `hours_change_log` WRITE;
/*!40000 ALTER TABLE `hours_change_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `hours_change_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `icon`
--

DROP TABLE IF EXISTS `icon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `icon` (
  `icon_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '头像图片id',
  `icon_path` varchar(255) DEFAULT NULL COMMENT '图片路径',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`icon_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `icon`
--

LOCK TABLES `icon` WRITE;
/*!40000 ALTER TABLE `icon` DISABLE KEYS */;
INSERT INTO `icon` VALUES (1,'/home','2018-03-10 08:35:50'),(2,'/homae','2018-03-10 08:36:04');
/*!40000 ALTER TABLE `icon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '通知id',
  `information` varchar(225) NOT NULL COMMENT '通知内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动通知表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `register_form`
--

DROP TABLE IF EXISTS `register_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `register_form` (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `activity_id` int(11) NOT NULL COMMENT '活动id',
  `telephone` varchar(20) DEFAULT NULL COMMENT '用户手机，用于活动负责人与志愿者间联系',
  `qq` varchar(20) DEFAULT NULL COMMENT '用户qq，用于活动负责人与志愿者间联系',
  `email` varchar(30) DEFAULT NULL COMMENT '用户邮箱，方便用于找回密码等操作',
  `descriptions` varchar(225) DEFAULT NULL COMMENT '个性特长等',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`user_id`,`activity_id`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='志愿活动报名表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `register_form`
--

LOCK TABLES `register_form` WRITE;
/*!40000 ALTER TABLE `register_form` DISABLE KEYS */;
/*!40000 ALTER TABLE `register_form` ENABLE KEYS */;
UNLOCK TABLES;

-- # 自我描述、政治身份、生日、年龄
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `student_id` varchar(20) NOT NULL COMMENT '学生学号',
  `name` varchar(50) NOT NULL COMMENT '用户姓名',
  `password` varchar(50) NOT NULL COMMENT '用户密码，用md5加密存储',
  `flag` varchar(1) DEFAULT 'S' COMMENT '用户标识,S:学生/游客，V:volunteer，Q：青协工作者，A：管理人员',
  `role_id` int(11) DEFAULT '0' COMMENT '对应角色的id',
  `gender` varchar(1) DEFAULT 'M' COMMENT 'M: Male, F: Female',
  `class_id` int(6) DEFAULT NULL COMMENT '班级id',
  `political_status` varchar(20) COMMENT '政治身份',
  `birthdate` datetime comment '出生年月日',
  `age` int comment '年龄',
  `profile` varchar(100) comment '自我介绍',
  `hours` double DEFAULT NULL COMMENT '用户总工时',
  `icon_id` int(11) DEFAULT NULL COMMENT '头像ID',
  `telephone` varchar(20) DEFAULT NULL COMMENT '用户手机，用于活动负责人与志愿者间联系',
  `qq` varchar(20) DEFAULT NULL COMMENT '用户qq，用于活动负责人与志愿者间联系',
  `email` varchar(30) DEFAULT NULL COMMENT '用户邮箱，方便用于找回密码等操作',
  `wechat` varchar(20) DEFAULT NULL COMMENT '用户微信，用于活动负责人与志愿者间联系',
  `token` varchar(50) DEFAULT NULL COMMENT '用于验证系统用户的token值',
  `validation` varchar(100) DEFAULT NULL COMMENT '用于验证邮箱等等操作的时候',
  `last_login_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后一次登陆的时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `student_id` (`student_id`) USING BTREE,
  KEY `idx_studentId` (`student_id`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE,
  KEY `user_icon_id` (`icon_id`),
  CONSTRAINT `user_icon_id` FOREIGN KEY (`icon_id`) REFERENCES `icon` (`icon_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'246659','谢豪','1241','S',0,'M',0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-03-06 03:23:18'),(2,'246662','谢意宏','34556','S',0,'M',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2018-03-07 05:16:43'),(3,'246666','武汉','9106f84b1de8d11c9917f5c74dcb4a93','S',0,'M',1,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,'2018-03-10 10:18:47');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_activity`
--

DROP TABLE IF EXISTS `user_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `activity_id` int(11) DEFAULT NULL COMMENT '活动id',
  `count` int(11) DEFAULT NULL COMMENT '参与志愿工作次数---根据次数统计总时间',
  `status` int(4) DEFAULT NULL COMMENT '进行状态，1：报名，-1:报名失败，2:面试，-2:面试失败，3:参与志愿服务当中,0:默认值，无意义',
  `stuff` int(4) DEFAULT NULL COMMENT '志愿活动中担任的角色,0:志愿者，1:负责人,2:青协工作人员',
  `condition` int(11) DEFAULT '0' COMMENT '进行状态，1：报名，-1:报名失败，2:面试，-2:面试失败，3:参与志愿服务当中,0:默认值，无意义',
  `reg_time` datetime DEFAULT NULL COMMENT '报名时间',
  `interview_time` datetime DEFAULT NULL COMMENT '面试时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_registerTime` (`reg_time`),
  KEY `idx_interviewTime` (`interview_time`),
  KEY `user_activity_activity_id` (`activity_id`),
  KEY `user_activity_user_id` (`user_id`),
  CONSTRAINT `user_activity_activity_id` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`),
  CONSTRAINT `user_activity_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户志愿活动关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_activity`
--

LOCK TABLES `user_activity` WRITE;
/*!40000 ALTER TABLE `user_activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_activity_hours`
--

DROP TABLE IF EXISTS `user_activity_hours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_activity_hours` (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `activity_id` int(11) NOT NULL COMMENT '活动id',
  `voluntary_hours` int(11) NOT NULL DEFAULT '0' COMMENT '用户对应活动的工时数',
  `is_confirm` int(11) NOT NULL COMMENT '是否确认无误,1:确认，0:未确认，-1:有误',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录时间',
  PRIMARY KEY (`user_id`,`activity_id`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户活动工时明细表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_activity_hours`
--

LOCK TABLES `user_activity_hours` WRITE;
/*!40000 ALTER TABLE `user_activity_hours` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_activity_hours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_experience`
--

DROP TABLE IF EXISTS `user_experience`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_experience` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `activity_name` varchar(225) DEFAULT NULL COMMENT '活动名称',
  `begin` datetime DEFAULT NULL COMMENT '创建时间',
  `end` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_experience_user` (`user_id`),
  CONSTRAINT `user_experience_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户经历表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_experience`
--

LOCK TABLES `user_experience` WRITE;
/*!40000 ALTER TABLE `user_experience` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_experience` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-10 19:45:59
