/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : exam2

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2015-07-06 13:44:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `DeptId` bigint(20) NOT NULL auto_increment COMMENT '部门主键ID',
  `DeptName` varchar(64) collate utf8_bin default NULL COMMENT '名称',
  `ParentId` bigint(20) default NULL COMMENT '父ID',
  `AreaCode` varchar(64) collate utf8_bin default NULL COMMENT '区域编码',
  PRIMARY KEY  (`DeptId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='机构部门表';

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '所有机构部门', null, '00124');
INSERT INTO `department` VALUES ('2', '县机构1', '1', '00245');
INSERT INTO `department` VALUES ('3', '县机构2', '1', '0002658');
INSERT INTO `department` VALUES ('4', '县机构3', '1', '0036');
INSERT INTO `department` VALUES ('5', '技术中心', '2', '0045');
INSERT INTO `department` VALUES ('6', '防雷办', '3', '0069');
INSERT INTO `department` VALUES ('7', '影视部', '4', '00369');
INSERT INTO `department` VALUES ('8', '科技中心', '3', '00689');

-- ----------------------------
-- Table structure for exam_fill_blanks
-- ----------------------------
DROP TABLE IF EXISTS `exam_fill_blanks`;
CREATE TABLE `exam_fill_blanks` (
  `Id` bigint(20) NOT NULL auto_increment COMMENT '主键ID',
  `Question` text collate utf8_bin NOT NULL COMMENT '问题',
  `Answer` varchar(255) collate utf8_bin NOT NULL COMMENT '答案',
  `Score` decimal(10,1) NOT NULL COMMENT '分值(存在0.5的值)',
  `TypeId` bigint(20) default NULL COMMENT '考试分类表ID',
  `Status` tinyint(2) default '1' COMMENT '状态: 1应用;2禁用;3已删除',
  `Remark` varchar(255) collate utf8_bin default NULL COMMENT '备注',
  `CreateUser` bigint(20) default NULL COMMENT '创建人',
  `CreateTime` datetime default NULL COMMENT '入库时间',
  `UpdateTime` datetime default NULL COMMENT '变更时间',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='填空题表';

-- ----------------------------
-- Records of exam_fill_blanks
-- ----------------------------

-- ----------------------------
-- Table structure for exam_multiple_choice
-- ----------------------------
DROP TABLE IF EXISTS `exam_multiple_choice`;
CREATE TABLE `exam_multiple_choice` (
  `Id` bigint(20) NOT NULL auto_increment COMMENT '主键ID',
  `Question` text collate utf8_bin NOT NULL COMMENT '问题',
  `Answer` varchar(255) collate utf8_bin NOT NULL COMMENT '答案',
  `Score` decimal(10,1) NOT NULL COMMENT '分值(存在0.5的值)',
  `TypeId` bigint(20) default NULL COMMENT '考试分类表ID',
  `Status` tinyint(2) default '1' COMMENT '状态: 1应用;2禁用;3已删除',
  `Remark` varchar(255) collate utf8_bin default NULL COMMENT '备注',
  `CreateUser` bigint(20) default NULL COMMENT '创建人',
  `CreateTime` datetime default NULL COMMENT '入库时间',
  `UpdateTime` datetime default NULL COMMENT '变更时间',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='多选选择题表';

-- ----------------------------
-- Records of exam_multiple_choice
-- ----------------------------

-- ----------------------------
-- Table structure for exam_operation_question
-- ----------------------------
DROP TABLE IF EXISTS `exam_operation_question`;
CREATE TABLE `exam_operation_question` (
  `Id` bigint(20) NOT NULL auto_increment COMMENT '主键ID',
  `Question` text collate utf8_bin NOT NULL COMMENT '问题',
  `Answer` varchar(255) collate utf8_bin NOT NULL COMMENT '答案',
  `Score` decimal(10,1) NOT NULL COMMENT '分值(存在0.5的值)',
  `ImgSrc` varchar(500) collate utf8_bin default NULL COMMENT '图片路径',
  `TypeId` bigint(20) default NULL COMMENT '考试分类表ID',
  `Status` tinyint(2) default '1' COMMENT '状态: 1应用;2禁用;3已删除',
  `Remark` varchar(255) collate utf8_bin default NULL COMMENT '备注',
  `CreateUser` bigint(20) default NULL COMMENT '创建人',
  `CreateTime` datetime default NULL COMMENT '入库时间',
  `UpdateTime` datetime default NULL COMMENT '变更时间',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='简答题表';

-- ----------------------------
-- Records of exam_operation_question
-- ----------------------------

-- ----------------------------
-- Table structure for exam_paper_item
-- ----------------------------
DROP TABLE IF EXISTS `exam_paper_item`;
CREATE TABLE `exam_paper_item` (
  `ItemId` bigint(20) NOT NULL auto_increment COMMENT '试题主键ID',
  `PaperId` bigint(20) NOT NULL COMMENT '标题',
  `TypeId` bigint(20) default NULL COMMENT '考试分类表ID',
  `Numbers` int(11) NOT NULL COMMENT '题目数量',
  `Percent` int(11) NOT NULL COMMENT '占总分百分比',
  `TopicType` tinyint(4) default NULL COMMENT '题目类型: 1单选题目、2多选题目、3填空题、4简答题、5操作题',
  PRIMARY KEY  (`ItemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='试题分项表';

-- ----------------------------
-- Records of exam_paper_item
-- ----------------------------

-- ----------------------------
-- Table structure for exam_paper_liberary
-- ----------------------------
DROP TABLE IF EXISTS `exam_paper_liberary`;
CREATE TABLE `exam_paper_liberary` (
  `LibId` bigint(20) NOT NULL auto_increment COMMENT '主键ID',
  `TemplateId` bigint(20) NOT NULL COMMENT '试题模板ID',
  `ExamId` bigint(20) default NULL COMMENT '题目的ID，是选择题则为选择题的ID，填空题目则为填空填空ID',
  `Question` text collate utf8_bin NOT NULL COMMENT '问题',
  `Answer` text collate utf8_bin COMMENT '答案： 填空题答案逗号分隔; 选择题完全正确才得分',
  `Score` decimal(10,1) NOT NULL COMMENT '分值(存在0.5的值)',
  `ImgSrc` varchar(500) collate utf8_bin default NULL COMMENT '操作题上传的图片路径，逗号分隔',
  `Remark` varchar(255) collate utf8_bin default NULL COMMENT '备注',
  `CreatetUser` bigint(20) default NULL COMMENT '创建人ID',
  `CreateTime` datetime default NULL COMMENT '入库时间',
  PRIMARY KEY  (`LibId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='正式考试题库表(最新的一分试题,仅供考试用�';

-- ----------------------------
-- Records of exam_paper_liberary
-- ----------------------------

-- ----------------------------
-- Table structure for exam_paper_liberary_his
-- ----------------------------
DROP TABLE IF EXISTS `exam_paper_liberary_his`;
CREATE TABLE `exam_paper_liberary_his` (
  `LibHisId` bigint(20) NOT NULL auto_increment COMMENT '主键ID',
  `LibId` bigint(20) NOT NULL COMMENT '题库表ID',
  `PaperId` bigint(20) NOT NULL COMMENT '试题表ID',
  `ExamId` bigint(20) default NULL COMMENT '题目的ID，是选择题则为选择题的ID，填空题目则为填空填空ID',
  `Question` text collate utf8_bin NOT NULL COMMENT '问题',
  `Answer` text collate utf8_bin COMMENT '答案： 填空题答案逗号分隔; 选择题完全正确才得分',
  `Score` decimal(10,1) NOT NULL COMMENT '分值(存在0.5的值)',
  `ImgSrc` varchar(500) collate utf8_bin default NULL COMMENT '操作题上传的图片路径，逗号分隔',
  `Remark` varchar(255) collate utf8_bin default NULL COMMENT '备注',
  `CreatetUser` bigint(20) default NULL COMMENT '创建人ID',
  `CreateTime` datetime default NULL COMMENT '入库时间',
  PRIMARY KEY  (`LibHisId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='历史题库表';

-- ----------------------------
-- Records of exam_paper_liberary_his
-- ----------------------------

-- ----------------------------
-- Table structure for exam_paper_setting
-- ----------------------------
DROP TABLE IF EXISTS `exam_paper_setting`;
CREATE TABLE `exam_paper_setting` (
  `paperId` bigint(20) NOT NULL auto_increment COMMENT '试题主键ID',
  `Title` varchar(255) collate utf8_bin NOT NULL COMMENT '标题',
  `SubTitle` varchar(255) collate utf8_bin default NULL COMMENT '副标题',
  `duration` int(11) NOT NULL COMMENT '时长:  单位秒',
  `TotalScore` decimal(10,1) NOT NULL COMMENT '总分',
  `Remark` varchar(255) collate utf8_bin default NULL COMMENT '备注',
  `CreateUser` bigint(20) default NULL COMMENT '创建人',
  `CreateTime` datetime default NULL COMMENT '入库时间',
  `UpdateTime` datetime default NULL COMMENT '变更时间',
  `Type` tinyint(4) default NULL COMMENT '考试类型： 1正式考试，2自测考试',
  PRIMARY KEY  (`paperId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='试题设置表';

-- ----------------------------
-- Records of exam_paper_setting
-- ----------------------------

-- ----------------------------
-- Table structure for exam_paper_template_bind
-- ----------------------------
DROP TABLE IF EXISTS `exam_paper_template_bind`;
CREATE TABLE `exam_paper_template_bind` (
  `TemplateId` bigint(20) NOT NULL auto_increment COMMENT '模板主键ID',
  `PaperId` bigint(20) NOT NULL COMMENT '试卷ID',
  `Status` tinyint(2) default '0' COMMENT '状态: 默认0 没有参加考试，1 试卷已作废 ',
  `Remark` varchar(255) collate utf8_bin default NULL COMMENT '备注',
  `CreateUser` bigint(20) default NULL COMMENT '创建人',
  `CreateTime` datetime default NULL COMMENT '入库时间',
  `UpdateTime` datetime default NULL COMMENT '变更时间',
  PRIMARY KEY  (`TemplateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='参加考试的试卷和试题模板绑定表';

-- ----------------------------
-- Records of exam_paper_template_bind
-- ----------------------------

-- ----------------------------
-- Table structure for exam_paper_user_bind
-- ----------------------------
DROP TABLE IF EXISTS `exam_paper_user_bind`;
CREATE TABLE `exam_paper_user_bind` (
  `Id` bigint(20) NOT NULL auto_increment COMMENT '主键ID',
  `TemplateId` bigint(20) NOT NULL COMMENT '试卷与考试绑定表主键ID',
  `UserId` bigint(20) NOT NULL COMMENT '考生登录ID',
  `UserName` varchar(64) collate utf8_bin default NULL COMMENT '考生用户名',
  `beginTime` datetime default NULL COMMENT '考试时间区间: 正式考试则为空，自测考试如果前台设置以此为开始时间为准',
  `endTime` datetime default NULL COMMENT '考试时间区间: 正式考试则为空，自测考试如果前台设置以此为结束时间',
  `ExamNumbers` int(11) default '1' COMMENT '考试次数: 默认1次, 正式考试为1次， 自测考试为N（根据设置）次',
  `Status` tinyint(2) default '0' COMMENT '状态: 默认0 没有参加考试，1 试卷已作废 ',
  `Remark` varchar(255) collate utf8_bin default NULL COMMENT '备注',
  `CreateUser` bigint(20) default NULL COMMENT '创建人',
  `CreateTime` datetime default NULL COMMENT '入库时间',
  `UpdateTime` datetime default NULL COMMENT '变更时间',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='试卷模板考生绑定表';

-- ----------------------------
-- Records of exam_paper_user_bind
-- ----------------------------

-- ----------------------------
-- Table structure for exam_score
-- ----------------------------
DROP TABLE IF EXISTS `exam_score`;
CREATE TABLE `exam_score` (
  `Id` bigint(20) NOT NULL auto_increment COMMENT '角色主键ID',
  `UserId` bigint(20) NOT NULL COMMENT '用户ID',
  `UserName` varchar(64) collate utf8_bin NOT NULL COMMENT '用户姓名',
  `ForcastNumber` varchar(32) collate utf8_bin default NULL COMMENT '预报员编号',
  `TotalScore` decimal(10,1) NOT NULL COMMENT '成绩',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='正式考试成绩表';

-- ----------------------------
-- Records of exam_score
-- ----------------------------

-- ----------------------------
-- Table structure for exam_short_question
-- ----------------------------
DROP TABLE IF EXISTS `exam_short_question`;
CREATE TABLE `exam_short_question` (
  `Id` bigint(20) NOT NULL auto_increment COMMENT '主键ID',
  `Question` text collate utf8_bin NOT NULL COMMENT '问题',
  `Answer` varchar(255) collate utf8_bin NOT NULL COMMENT '答案',
  `Score` decimal(10,1) NOT NULL COMMENT '分值(存在0.5的值)',
  `TypeId` bigint(20) default NULL COMMENT '考试分类表ID',
  `Status` tinyint(2) default '1' COMMENT '状态: 1应用;2禁用;3已删除',
  `Remark` varchar(255) collate utf8_bin default NULL COMMENT '备注',
  `CreateUser` bigint(20) default NULL COMMENT '创建人',
  `CreateTime` datetime default NULL COMMENT '入库时间',
  `UpdateTime` datetime default NULL COMMENT '变更时间',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='简答题表';

-- ----------------------------
-- Records of exam_short_question
-- ----------------------------

-- ----------------------------
-- Table structure for exam_single_choice
-- ----------------------------
DROP TABLE IF EXISTS `exam_single_choice`;
CREATE TABLE `exam_single_choice` (
  `Id` bigint(20) NOT NULL auto_increment COMMENT '主键ID',
  `Question` text collate utf8_bin NOT NULL COMMENT '问题',
  `Answer` varchar(255) collate utf8_bin NOT NULL COMMENT '答案',
  `Score` decimal(10,1) NOT NULL COMMENT '分值(存在0.5的值)',
  `TypeId` bigint(20) default NULL COMMENT '考试分类表ID',
  `Status` tinyint(2) default '1' COMMENT '状态: 1应用;2禁用;3已删除',
  `Remark` varchar(255) collate utf8_bin default NULL COMMENT '备注',
  `CreateUser` bigint(20) default NULL COMMENT '创建人',
  `CreateTime` datetime default NULL COMMENT '入库时间',
  `UpdateTime` datetime default NULL COMMENT '变更时间',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='单项选择题表';

-- ----------------------------
-- Records of exam_single_choice
-- ----------------------------
INSERT INTO `exam_single_choice` VALUES ('1', 0x75696A6B686B6A686B686B, 'A', '0.5', '1', '1', null, null, '2015-06-24 10:17:51', null);
INSERT INTO `exam_single_choice` VALUES ('2', 0x6F70696F70686B6A686B6B6A6A6A6AE7BB93E6A0B8E69D86E88F8CE4BAA4E68DA2E69CBA, 'D', '1.0', '2', '1', null, null, '2015-06-24 12:17:54', null);

-- ----------------------------
-- Table structure for exam_test_paper_liberary
-- ----------------------------
DROP TABLE IF EXISTS `exam_test_paper_liberary`;
CREATE TABLE `exam_test_paper_liberary` (
  `TestLibId` bigint(20) NOT NULL auto_increment COMMENT '主键ID',
  `TemplateId` bigint(20) NOT NULL COMMENT '试题模板ID',
  `ExamId` bigint(20) default NULL COMMENT '题目的ID，是选择题则为选择题的ID，填空题目则为填空填空ID',
  `Question` text collate utf8_bin NOT NULL COMMENT '问题',
  `Answer` text collate utf8_bin COMMENT '答案： 填空题答案逗号分隔; 选择题完全正确才得分',
  `Score` decimal(10,1) NOT NULL COMMENT '分值(存在0.5的值)',
  `ImgSrc` varchar(500) collate utf8_bin default NULL COMMENT '操作题上传的图片路径，逗号分隔',
  `Remark` varchar(255) collate utf8_bin default NULL COMMENT '备注',
  `CreatetUser` bigint(20) default NULL COMMENT '创建人ID',
  `CreateTime` datetime default NULL COMMENT '入库时间',
  PRIMARY KEY  (`TestLibId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='自测题库表';

-- ----------------------------
-- Records of exam_test_paper_liberary
-- ----------------------------

-- ----------------------------
-- Table structure for exam_test_user_score
-- ----------------------------
DROP TABLE IF EXISTS `exam_test_user_score`;
CREATE TABLE `exam_test_user_score` (
  `Id` bigint(20) NOT NULL auto_increment COMMENT '主键ID',
  `UserId` bigint(20) NOT NULL COMMENT '用户ID',
  `UserName` varchar(64) collate utf8_bin NOT NULL COMMENT '用户姓名',
  `ForcastNumber` varchar(32) collate utf8_bin default NULL COMMENT '预报员编号',
  `TotalScore` decimal(10,1) NOT NULL COMMENT '成绩',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='自测考试总成绩表';

-- ----------------------------
-- Records of exam_test_user_score
-- ----------------------------

-- ----------------------------
-- Table structure for exam_type
-- ----------------------------
DROP TABLE IF EXISTS `exam_type`;
CREATE TABLE `exam_type` (
  `Id` bigint(20) NOT NULL auto_increment COMMENT '角色主键ID',
  `TypeName` varchar(64) collate utf8_bin NOT NULL COMMENT '考试类型名称： 市级考试; 县级考试',
  `EngName` varchar(64) collate utf8_bin default NULL COMMENT '英文名称',
  `ParentId` bigint(20) default NULL COMMENT '父ID',
  `Status` tinyint(2) default '1' COMMENT '状态: 1应用;2禁用;3已删除',
  `Sort` int(11) default NULL COMMENT '排序',
  `Remark` varchar(255) collate utf8_bin default NULL COMMENT '备注',
  `CreateUser` bigint(20) default NULL COMMENT '创建人',
  `CreateTime` datetime default NULL COMMENT '入库时间',
  `UpdateTime` datetime default NULL COMMENT '变更时间',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='考试类型表';

-- ----------------------------
-- Records of exam_type
-- ----------------------------

-- ----------------------------
-- Table structure for exam_user_paper_answer
-- ----------------------------
DROP TABLE IF EXISTS `exam_user_paper_answer`;
CREATE TABLE `exam_user_paper_answer` (
  `AnswerId` bigint(20) NOT NULL auto_increment COMMENT '主键ID',
  `LibId` char(10) collate utf8_bin default NULL COMMENT '正式题ID',
  `UserId` bigint(20) NOT NULL COMMENT '用户ID',
  `ExamId` bigint(20) default NULL COMMENT '题目的ID，是选择题则为选择题的ID，填空题目则为填空填空ID',
  `Answer` text collate utf8_bin COMMENT '答案： 填空题答案逗号分隔; 选择题完全正确才得分',
  `ImgSrc` varchar(500) collate utf8_bin default NULL COMMENT '操作题上传的图片路径，逗号分隔',
  PRIMARY KEY  (`AnswerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='正式考生试卷答案表（考生考试提交数据到此';

-- ----------------------------
-- Records of exam_user_paper_answer
-- ----------------------------

-- ----------------------------
-- Table structure for exam_user_paper_score
-- ----------------------------
DROP TABLE IF EXISTS `exam_user_paper_score`;
CREATE TABLE `exam_user_paper_score` (
  `Id` bigint(20) NOT NULL auto_increment COMMENT '主键ID',
  `AnswerId` char(10) collate utf8_bin default NULL COMMENT '正式考试用户答案ID',
  `UserId` bigint(20) NOT NULL COMMENT '用户ID',
  `ExamId` bigint(20) default NULL COMMENT '题目的ID，是选择题则为选择题的ID，填空题目则为填空填空ID',
  `SysAnswer` text collate utf8_bin COMMENT '系统标准答案： 填空题答案逗号分隔; 选择题完全正确才得分',
  `UserAnswer` text collate utf8_bin COMMENT '考生答案： 填空题答案逗号分隔; 选择题完全正确才得分',
  `ImgSrc` varchar(500) collate utf8_bin default NULL COMMENT '操作题上传的图片路径，逗号分隔',
  `Score` decimal(10,1) default NULL COMMENT '各小题目得分',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='正式考生试卷各题目得分表（系统自动评分与';

-- ----------------------------
-- Records of exam_user_paper_score
-- ----------------------------

-- ----------------------------
-- Table structure for exam_user_test_paper_answer
-- ----------------------------
DROP TABLE IF EXISTS `exam_user_test_paper_answer`;
CREATE TABLE `exam_user_test_paper_answer` (
  `TestAnswerId` bigint(20) NOT NULL auto_increment COMMENT '主键ID',
  `TestLibId` char(10) collate utf8_bin default NULL COMMENT '测试题ID',
  `UserId` bigint(20) NOT NULL COMMENT '用户ID',
  `ExamId` bigint(20) default NULL COMMENT '题目的ID，是选择题则为选择题的ID，填空题目则为填空填空ID',
  `UserAnswer` text collate utf8_bin COMMENT '答案： 填空题答案逗号分隔; 选择题完全正确才得分',
  `ImgSrc` varchar(500) collate utf8_bin default NULL COMMENT '操作题上传的图片路径，逗号分隔',
  PRIMARY KEY  (`TestAnswerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='自测考生自测试卷答案表（考生考试提交数据';

-- ----------------------------
-- Records of exam_user_test_paper_answer
-- ----------------------------

-- ----------------------------
-- Table structure for exam_user_test_paper_score
-- ----------------------------
DROP TABLE IF EXISTS `exam_user_test_paper_score`;
CREATE TABLE `exam_user_test_paper_score` (
  `Id` bigint(20) NOT NULL auto_increment COMMENT '主键ID',
  `TestAnswerId` char(10) collate utf8_bin default NULL COMMENT '测试答案表ID',
  `UserId` bigint(20) NOT NULL COMMENT '用户ID',
  `ExamId` bigint(20) default NULL COMMENT '题目的ID，是选择题则为选择题的ID，填空题目则为填空填空ID',
  `SysAnswer` text collate utf8_bin COMMENT '系统标准答案： 填空题答案逗号分隔; 选择题完全正确才得分',
  `UserAnswer` text collate utf8_bin COMMENT '考生答案： 填空题答案逗号分隔; 选择题完全正确才得分',
  `ImgSrc` varchar(500) collate utf8_bin default NULL COMMENT '操作题上传的图片路径，逗号分隔',
  `Score` decimal(10,1) default NULL COMMENT '各小题目得分',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='自测考生试卷各题目得分表（系统自动评分与';

-- ----------------------------
-- Records of exam_user_test_paper_score
-- ----------------------------

-- ----------------------------
-- Table structure for system_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `system_dictionary`;
CREATE TABLE `system_dictionary` (
  `DictId` bigint(20) NOT NULL auto_increment COMMENT ' 字典主键ID',
  `DictName` varchar(64) collate utf8_bin default NULL COMMENT '名称',
  `DictValue` varchar(100) collate utf8_bin default NULL COMMENT '值',
  `ParentId` bigint(20) default NULL COMMENT '父ID',
  `TypeFlag` varchar(32) collate utf8_bin default NULL COMMENT '分类标识',
  `Remark` varchar(500) collate utf8_bin default NULL COMMENT '备注',
  `CreateUser` bigint(20) default NULL COMMENT '创建人',
  `CreateTime` datetime default NULL COMMENT '入库时间',
  `UpdateTime` datetime default NULL COMMENT '变更时间',
  PRIMARY KEY  (`DictId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='字典表';

-- ----------------------------
-- Records of system_dictionary
-- ----------------------------

-- ----------------------------
-- Table structure for system_group
-- ----------------------------
DROP TABLE IF EXISTS `system_group`;
CREATE TABLE `system_group` (
  `GroupId` bigint(20) NOT NULL auto_increment COMMENT ' 主键ID',
  `GroupName` varchar(64) collate utf8_bin default NULL COMMENT '组名称',
  `EngName` varchar(64) collate utf8_bin default NULL COMMENT '英文名称',
  `ParentId` bigint(20) default NULL COMMENT '父组ID',
  `Status` tinyint(2) default '1' COMMENT '状态:  1应用, 2禁用',
  `Remark` varchar(255) collate utf8_bin default NULL COMMENT '备注',
  `CreateUser` bigint(20) default NULL COMMENT '创建人',
  `CreateTime` datetime default NULL COMMENT '入库时间',
  `UpdateTime` datetime default NULL COMMENT '变更时间',
  PRIMARY KEY  (`GroupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='组信息表';

-- ----------------------------
-- Records of system_group
-- ----------------------------
INSERT INTO `system_group` VALUES ('1', '所有组', 'all_group', null, '1', null, null, '2015-06-17 13:25:15', null);
INSERT INTO `system_group` VALUES ('8', '系统组', '', '1', '1', '', null, '2015-06-26 14:18:34', null);
INSERT INTO `system_group` VALUES ('9', '超级管理员组', '', '8', '1', '', null, '2015-06-26 14:18:53', null);
INSERT INTO `system_group` VALUES ('11', '市级管理员', 'city_group', '8', '1', '', null, '2015-07-01 16:36:16', null);
INSERT INTO `system_group` VALUES ('12', '县级管理员', 'country_group', '8', '1', '', null, '2015-07-01 16:36:37', null);
INSERT INTO `system_group` VALUES ('13', '系统管理员', 'system_group', '8', '1', '', null, '2015-07-01 16:40:23', null);
INSERT INTO `system_group` VALUES ('14', '考生组', 'examer', '8', '1', '考生组可以考试', null, '2015-07-06 10:24:21', null);

-- ----------------------------
-- Table structure for system_log
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log` (
  `LogId` bigint(20) NOT NULL auto_increment COMMENT ' 主键ID',
  `Content` varchar(500) collate utf8_bin default NULL COMMENT '日志内容',
  `CurrentUser` varchar(32) collate utf8_bin default NULL COMMENT '操作人',
  `Ip` varchar(64) collate utf8_bin default NULL COMMENT 'ip地址',
  `Operate` varchar(64) collate utf8_bin default NULL COMMENT '操作',
  `Remark` varchar(500) collate utf8_bin default NULL COMMENT '备注',
  `CreateUser` bigint(20) default NULL COMMENT '创建人',
  `CreateTime` datetime default NULL COMMENT '入库时间',
  `UpdateTime` datetime default NULL COMMENT '变更时间',
  PRIMARY KEY  (`LogId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='日志表';

-- ----------------------------
-- Records of system_log
-- ----------------------------
INSERT INTO `system_log` VALUES ('3', 'WKY9FDS0QG7TV49571MRCN24OOOY295AG1PGYENL1FL1S12XB0GULCHH0EH 8F3KC4UOTGKWMLSXOCPUYKW109D1DKDH0M7ORJJM TRH GDVHDI9CE6ORYB4U7KY6VOBVV99YA8JRI9HRVK5O1YAMERTJ7M20LGTYLQNR TYK4LGIYP79X2GXXTSKDBLAMA6A6Y0YG293IVQB6KPDGN1HAIF5GSKC7R06X7RNPCN VSCBFMOBW40OOKQH2C12Y4OY21EG2QY ADG241PHG8GCOICTQPCVXI30PCNKC8OVQWHTYS385YNY8NNEVSXNMSWRW9CLQH F40X75PS 0K1GR2LJVSCWUNCLL5IUS NF8HHH7AE7YM7B0NTKY8DLXHD0OQB0I 6RRIXE4V6DRXXJ81MUAVX9HJJIU03FBJOABHA9AK005N1Q1NYSXS2YNT4LS7AU0HXXG4OVIAINQ5NXDQETTC 9V30U3UJNTEDMKFFLXPVU24Y', '6N3IF2HF42 XVINJ18FH7RFOVUJ 834T', 'HYR C1L 1ICSX0B5SHTHNS6Q366U68 95CP8781555C3KFYPBH8CIG MD5G5XV95', 'I BQ1NWEJ0Y2YP7CF1PHU94NTMUXN6FYKKM0DMJDWTHI TDBJVTMPAM72VMVHOXJ', 'R6C65KI4Y SQFA2HQKMKSH0X B9JR9YUEH8SQGVTQ33W6DW4DK6SG88SXSU19 BIWX94L2XSNV5VMHN5B6O1NQ4OXUG1FBXAQJOOGQXJEIH68AHAP3850C2OXX1WBFKYSQJNXQES7NH2LDWE8ERN77O2DUDQ BDFK9MXQ64CRC8F99MG7OYR FY8AVY7J PR3FH M567C2MTWCPMDTA9313R7N2Q39A9WTI0BLYIRMDE3XF705 OAYLD68RBTX091GUSNC3MDAY3K9FP E44D A S4XNWN5C4TFTE9IX RKD0GJPNQIG8U6M6463K 86APX8HXF3039E6JX1WQP2O7OG0KYM2G0O6FVSQ0CVDRT0SF2M7A44OKEVH4FVQFLS1MHY4Y08JNWPP0MJHSWD3D8CAWL7T8BRKCVUK6I8H3SLQ L29109Y0ELOC7KO0TV12T61Y53QQPIR2BLJ65V9PDEHETKQX2M7PYEKJK9BPJVNRBOVSTX', '11', '0119-06-21 01:13:53', '1159-01-18 17:16:43');
INSERT INTO `system_log` VALUES ('5', ' 0TMHTBU0HFM DWXSY1VU8XDFM1VTU2RATU2SV3MQ6G456W2QK75VBS9TABB7VNE0918X5PF NHTDWQ184VUS52WLQOAS4AVIK9XSE6ATS50W4VMEHQ3HE0 415X9M2YO83N88VFXTTQ C4NWVAPUN28BOJ3GBHL 8WG67G7IGQNWXK4X U3RE2KMJE9418WDWV4IRS39SU1 6J77VED70042W5K4KB2B2X4884SGBYWJBQA01SA5U945HXTAJ0KUVRICVW4VXKU0G0S4BN9M2F WENFKIWYJVX0VWV2SX04RKMEU DCNTE7X42KVYSXG3O2UB3Q42FP9M1W2S4AB3EKBMMX49TD8X3PE5VF6FN6 F084SVHPVTWP6M COFB061MM9TMVKDREPLJ IBX0YG9AJ4SKF9YH136PGTVPE2E35312U0BC3ATRUY3TLANV4PFSE1YK9WNL858C491XYNVCNR966SWVG114PWT0WYOJGNMC252', '30TQRCSOSSBMBEYWPMMULGX2X3HLQDUD', ' DNWE0D1P6A89K8YLDT37GBAH0ACNHIA7VTJMDMSTGBG5EC2OCRC7PCCRE2XB9Q2', ' W9LTWS28Q1YC8 CDH6CPVXO76PMCEA9 EGCBKQAL7LBI X MI5VPGS NBMT5QPR', ' S559NMIJYXC2SLTAJMTO46BOK8RNYO18TUX6LBMNV6FFT63YC6TKCDHS4N28MD U1800ECTGA6J2XMN6AKCTOHOQCF6URSBRW63V3 F0AYSWS1EGBA5DRNNLAQUMCESXRT1D1CIEUUH8KCX7937JOTQHV0V7MHVBWIWN18TRUE20Q5IYU1MA R0JW1BQD3M I VEBAN81J4OAY18PHN1KY5W5SDYP2Q P6BUGW3 6FF6IY1MEYRRRQIT9LC271NCGEOOJATAUIW166SPEFI 962HS2RUSEE9P3N8XF4CJHL9GF5511MR65J7708MTCNT852AH20GPERGV8MKK8 M3978UMM62TT7RLU7FP4WLISOE EN7QOPYY1W1BUWOKVMYU6SFPS2IE6KFTB6 LVM2GPKWT1O84HCAKAIJ5XAJXO9QNWUX2 K6QGMJ2HXQT0M7NO96W7 Q64HG5AHVRG7IK8HYEMMKPER3CVJ8MUK2NIF9UAWOJ0', '7', '1763-08-16 20:08:49', '0900-06-29 13:38:27');
INSERT INTO `system_log` VALUES ('9', '0TMNLOSKRNXQWE0666D6TQ7Y3Y145WKO1L3EXQ8BLV2 R97LS6AJI572TMD3RNTUN UYT9N11W EKUQPF3K5HIWJ2Y24U4G2ASQ40FTJTRSXWMBH9IV7HW9RU6NXH9XD 8AF79UT6XD1C0034CPI8CHCPXDVUYV29XW15P21Y8G LC4VAVBKJQ3 OAC 2X8H7YOROO7IHYGMAT99MWPJ7WVKEPEXCUA7FPL3LX3OLQNF5K9WGKQ71LW5Y273XCHW12B6S506OO2MWLBLPD 1YFIY0N4G0VOHQ9CQ3WFSP9I CWU9TBT5TXDAR4ENXTXXO  AGP4F2CK099K QREBLJCOXL 0 HKGMA7VTX46S9LVIGH1YGOY8NNSSJOHJ29CTMW96WSADOQCW5VUILE6QHRG2W Q OX32VOWITQ6WPYUDWBXXR0O57GM4X8 ODSDNDWUXTVDWVYJV6M0CWUWIXQHSDJ730AYGQSSGWCMH285ACX0CHXV', 'S HTCHABHXM54TMYHQEVC FW8P9YHPA5', 'B1GHJB35A50GBN9LOUC5YSGOL5FP4T1O49782 YPDG6JPK1ONIX902 WRRSU64O9', 'DU8RY2G KTEDIQ60I456TXDT9RFBIG1KDRWWVN2RPWDRP398UAEMRWIS6GY0 L28', 'FSU18D2CT6BK C2G196AJGVYKJEK79L7SQYALHMJM6KV90CR6S0 ISKAKK4257GORY86K5B5RLMRTLQCOW3SV L 20NOW3F1CLDWVJSKC7CU4 P31AQX2XIR8M1M51D0KRCROAERLPWX3JHKUGJU4XL A3K85LXYKIEBREDGPPQV902YPWE4IPWNFXT OPF EO26EBPYWPKROJ969YIH9ECFYMOJ1Q4Q8356DU39I4EUUPMR4VAT JJM5J9O0FYL8IRB1940S9KIXFJU1UH7Q2W7BX1E0 NKNI8O873J4DK9I53BOY4T6COUU PTWYRCONAKEP3RVLBTQK9NU6H1H4TR3S964BK8MDSFG7 BFXX2CXO0BF5DCJ3YICYYLH67R00UUMGH9DSYISW7WG H 620IJCPU8HXFSFKL6Y6RPLP154PF 484026M697VR3O XTI6KO IRDL5EENTNYL4E6DB UB7OGEUF9RPVM5 6B4OELSXX9O', '5', '0001-01-01 00:00:00', '1986-02-13 00:43:54');
INSERT INTO `system_log` VALUES ('10', 'H429 L188KYND4SLQ7AD2LC1LIFU6KBDKSV74UWP2DRX9M 80CHOOYEJL H6414OBU6F22VT3AXOUOMCHX5Y12BY2EODSNP X05RFKBN8SFA6FIQ3EBFS5NY7EDMP L43EORXHYH0P VAWRABJSWND8ES MVHJI12JGJAYOQEA42B2R37L5XCS3A1MELQQH NCX070PYX54BCS7D86SMNDNQS6C22UT645EMIILVNE2MLT1KIBSUCYI2CWX27446APVI0B4E6GTO8UH7MH329DMQKHF6TP9JL55II5BKM8 THE1YQIGE987DU TJOLE3V0V1WNLKO0 1K1JPHNBM75QR5VX JQ RK9D93BIRHF20U32I3KY72M TV1D 7 PNOAUX0N1NTL50KWXH8BF8Q20M0SHOI3K NU7GMYCWV1013NS 8EJH8R5G3TFVCOU7J37XXIWCGM7QUFLHUNYBJW1O6751JC4UVMA4YHIXVO2B7AINEE0V', 'HFAGG 1T1BERXMIYL4YRFHAV5ROPN 03', 'I4PLYDN0SXCW9FHK2149 860PR4GQVT0J3YT85SLWK7SGFR4TT26RPSTCG 4FOM ', '79TO QS9ORD8B9E02MHNLCNQYK428GB6UCJWVTONEQP9C1LWFV3B ANOCG372DA1', 'EMHKCUBGLJT60VWO0LD7NS9RGJLVEYH7 DS7NWK1Q18BYV3HVIIK0U7BKS02R1MQ97HYOGO0 PPC0JSKMFDOGVO O222L9W6QXAUQLMPFIO3R59CBLDK2PGFW9DAR50RUABYSU4779YNEGKWX86ELJ72F6TDQMED6XUJ93O5HOJ4NLJYJERJ8 CVGHJ2H0AM9CI25PLYGHPXP4SV85EIP21K5T 9H3OPQ262YB KD62ID QR3CKXA89HSOW2T EH8E0BQ5OOH7GIXIJ9AU5MS9RKMGAP 9 IDNIO0OCM3 2S6H6S B6LKG3I0HEJ4WTYVYD7XVDCIRB9J2FAFE1XHFLL9 V4V90NGEL4420H3QV2VTRHSNFYX7ASQ4H5C4I3VYIRQ07B9YSHMRIOEG82TA4PVVFH9O HXKPOHVRTEGAXP9RVJ8OVNJQM2924THXLF 0IK87J 3Y0M3EPJ5A5E6JGT258SLLV7XK4S1WSGPMFEH1FQGTR', '1', '0314-12-15 03:16:34', '1074-04-08 16:18:24');
INSERT INTO `system_log` VALUES ('11', ' 24 UVXCR39BTG3CXW4I2IYFI6Q 3SI2SVLQIWM2Q4JSGYMCIER99LJE7NW76T DNS5 8HUR90O7674N5H2FQ40RB1UCO41YV2AOASBFJD4STUXL7FML3IJDJGLK92UFSWNXNDJSUQGQSX542C9GHI0QNUGA1CE3UCA1P8V4EKNJ0XOU395ULA5 F400R6GWFEGEEREFJ6XR N42R4NSOGFYO22DM3MGY OM8RXDLTS6MF OITIFG 33AEU9E25  VUG7E77WO OJWFAU1O38B8J4PPMEI7FYT0SVBKVWSMDQ6I4AJEAS9M22JCLE6OBHFMPCHB1 40FTQY3FT9MH V33ASIR97X26CD7DDXMKR8WFDQUD0X74DR6BASEDA3DK46KRKGBWTCMM4A4KT3CRBRATE2PI5E0V8O6VF8KSCF60AKYQ18V9MIHYW4N2S0ND5I5EAGXVTCAOS402BN7H13URYUOW1R 7PJBCB0 M 265C50MLQ', '0H5WRNLOH84T4LLQNRDCJW5D79LJOBG6', '5HYSWA8NHOTHXGS ODREPP9IRWY4MP5SQMWAAQF QVTFHGPN1FB2K58GII3MPQ87', 'SC1UV9X93EPQC72EDCE196Q7WQIIFVFDB F4LU9NT7 NRD2V5S0XALJB2C2RBJI3', 'RLIOM2B07HOB265OA0STJCY1V2YEW1IH0DU0HNPEQQE2YMU 24KODC4 2BJOFI4U99R5MFNPRW9JSHHVWO OMPDUUUF CO28A23NJ J B HF QFIF6UPT299KS7ONWBYIXKSUW3G2F4X189CPIPBKUM12UAW8J5GQHKD4K8G41V ERXVFYWQHFJ3S0TD6U4CFPE00C7J62C6L1LSS9JS74EKNQQIHX1FJ02U7GJDE4UQ7NQXFHL1G4UO4TSE8U1KI9CQD21TK9JJS23BO7ONXJVV7KIQIKHWDMT7A7TUXXA5 PQ38BBHDCNPGK5VGX1NOQVENV6L9 SREWXKG5RYLHQWXXTU7PP6GNM6BWTEXPH8RV986G1ACSUD73BD0KN2DT1Y22NQRQN3QPNPQDX8IX45EYCR13F I2SWS2W665BH2H8R1G2AXMWF7RA1KRUUJNDRDG1J3G98J3FCJNUKKBSJS7CUV YNO811PSURYI3UYFYK7BET', '17', '1373-02-23 15:43:07', '0001-01-01 00:00:00');
INSERT INTO `system_log` VALUES ('12', 'WSYNM61TW1D6AVCI0SFTF7XQQUGFCUPFU N8SEONEDB8QPJ9K098GYNCPE5ENB30I35OL9R1DULFICL1Q7V64Y3DO0CJB8ODN7G5N3IM5KWVIF3KQWXYU9BS1TXJGED10AX06OWP4O7T5VC4TJD IB986W09TYV5D92TT5P9P5UQF4EPCGEWM1IYI60OKINJKVS7T2HTCWB6SX5YLXIF5MKVJIM0H2 OFW70YFKOJOHWAJ95 2IGNSNLPNTJU1WNUVR9HM1RS9GTFI5AUYH35MPCL8USG789PD419EUMN8MYAU1XNLPJ8LK1RW9CPSLY0RBOM MGP4OKJ0WDUSU60MT7R2V1EFCSOFKNGYT7ICK09TIHXESJ6YQ294SS05OJQKFI6APCR9JS5HHWE2QBVDIIVEDPFCJM7CR2PG94344LEN3UUD5CTX00A1C15XN26RM9K8CC8139RDI6SVRM96YR96K852ILQUG4RVHJW3LTI2LGXWNC', 'OYX79U2 DLMT4XT XB6ULWM8LQWJ5FTS', '6948E0KH2MXHJRLQOKPJ1F2QV MXG9KSLDT9OLXM4N U N6E2HS FNR7UMNT1URC', 'INT XMA8I MD3YXOLKEGA8ISB24GY52FTHBPPX3IW0QCHSCV6HRGUC8UGF93BHXE', 'AYE7BPCVPALP2DK7P5JRSPCVT2LGSCYA3US1FVN3IYYHGSA36EWPICAQY61DGVI7YLMF9OAABUMXQQRM6NB2FV2SG3A4OK U6FIRTKAQI7ABHRM0BEP305AFHQY90Y60OYU60UVWYLGGAAVNPFXRSYU7MCC04F7NT7REWSME12AXTOQ01YHL7 XS3NL8N7GVLN8VHHD RYVXO4PTJOQVWT02MSNE9KLXG2 LT16LK5 BNTNXXKTGD U EA5ROJJ8DRJC1DHBGU6JFSMAG4LVAVGQ80K4O6JDWB6G9BXD0NUUR0Y2Y6SDAS6MGC7  E324VYYNPG59M5 GCCKQ0UR1YJ3U02GG  7LURKD5C7U7YFCKP9NA L9JHUT6YDXPATH38LO928U90D9D9R32A0OH5L8CI785D3EYQ1UIG4  TJ4BGV TIU2TYAKI11BWUEL1JL3AJVF3LFUX4HTGAL8BM2Q0SN3EM9QHKJTWQNWNG3DK9OIAYE', '6', '0904-11-05 10:05:24', '0196-02-24 01:25:20');
INSERT INTO `system_log` VALUES ('13', 'QQAADW44B2XRU7SOPCO3DYM B5A 8IICBV1HYV2YQJPJ8J4MFMHU4WAYF88DGHKAC2KK3IC6Q I9MD8GQ3S1YJ73XIK855TJ0Y8SCBEN0JAFRVRH4KAPYVJC8V4F2Q1V2K519V1ESOP8A6O3LCJL5WNSEHGHV8DCRC7UHJ8JVD8H0B7VN1R2QH8NX0YIDM51W53FNNCEKX5PP4TIDP35UC7CXHPDDS0BT8AMFEXHP3G05ICYDUDIO7TDYBPINHWMCA YKXWCN0 764U7RA8JGLLH7DM24D0KXD9LQ9CUA7CSPONH6B27F2AJAXYUM3VXF XDRDSW2PWTMME4UN45WX1JFMYA347ND JRF1VKYSS4UH1MGCCP5J6NDIOHIT3TOPHXK2N5FA 2CKPOAWHUL3C8C3Y6SJVNWSXM23D0ID7S44X43946JOGQ48FS0G6L2ERCSOLRSLY07CGV7OJ FBPU27GE6OU6R Q5OA63AYH4OBA4GVV7', 'B3CGCKP92K53GGTGTTC7DOULOCWD1P8U', 'TJXK1Q30I37HRPKPG2PJQU5QHDWEW1I56KKSQ1E 0XA653K4GQ2YX94TAF CYAJ2', '9G9PAJESPVLEW50SH6U1LLAVVQ SW05GFG VQPMFXK7AHQ24J85Y6A8S6K2QWGGU', ' MWRYCRID 7OFIOK71NAUI0SCKF2DVBT8V0SYX3A13RTJ55E0GKVD3N1SVSMMUPM2D1TV9RJ73H XSBA3QTVLQO1 XSDTGT1M2L9MVEIEPEBIVY9LP3BO77VYY5ILXAF2PJPMOG24KU2QCK0P4FFAM18R0TE7WJTK 2YV15SY9GNKIGWLM5HPJTKG40JCDRVCJKWUOIXH73NJ3OXIRU1OWWV66BJBSWYMTTNKRCADSYTO84QP COH1JW20IAYYQ1QATDMU7G98G2JGFOGPT90RNYBBPPJ5SRX9TH4XFLTRDE9O0P56PQK7 23R9X YDFLYFJNB7KTEO1GA2G2JP81Y3XOTU9NHM03HI7DUEU 6OCA0M7NYS W 8UBYD4I5C4TX5D0OQL4  UG1EXBC9H1OJTCJ34 U9176R0RDOL1KF67X 7HNO90UWG1SH 4USOP53BD HDKO589MPANNU3SOW2TUBNDS5MUK2L4V80LXKYXIN3B2WW', '0', '0485-02-16 04:39:20', '0498-09-12 05:41:15');
INSERT INTO `system_log` VALUES ('14', 'QNJVUID82K9OSFTDRX5U WORL419QBXTV0UR7MJ9WOBIUWTSGV6C5FXNJSYA9HNK2WE6VV I00RF8U42545VARI8H09UVFW4G6 600P3EP9MMHDKYI01UVTF0EC602788KLWE4MQ4VRE7E A4QWMR34SOJSPOPV7JPJQ7TASAJJARM5XUE8JL5GI2VG1D2Y1N6E67 4C0K3XJKUUC0STA7JHTRKX49DFTG2TP0B3FSA6C3S XYIQIU09LGJ7IVPOCUNIKNUDOY5YJFK0WKRN9 7 344YNSERJOL832F61M4F 7G8L314809J1WAD5EPBR4M27UL9PETU1IKMEN8TJQ2D6PEU7FJME8PAC NVYN6OORRSPAGNXACOBG0VH1PMW24DFTQ03SEHCTQYXX5KJAL98XY48TRXOGGV 6NCF0121 W4KG Q6R1LSVEF7Q1R88HI 924DHUAAXHT4LYJ42RSWVRSP5M333D8 OGCBAV85PIC31AE', 'D6Y3DBH 7LTO1P23JLVBK5HBYNQ88VFH', ' 0JH 74R77D0XJON2WC5D 0J7NLB0FSEK4011ANKINMHA93S2 6OEJRM47XIDRKS', 'GD4G24GYN7C5KNV0YYYOQ2U7FW96D YW N CRFYWK1VDXLUL6M6VQ4 CDRB B BV', 'LYSSL3L6GC1H50SHEPWGD D5K062L4DT9VHVY PDLL276PEP8 549KN68PY5H68I1HELGB0HMJAF8ID5N2B O 3R  F VG280N4INKBBCT4AAO 8JKUY9MP9 OBIASSIUOYT74PS7FOJCIRKAH JNUO6V0897TJ1P92FRAL04TYJF3JXV8QYHWL7WMHH66TOBKCS 7N9LTNENIG9RMW1GX77RJ62TBCEW1I5M1SXLPAS1PULYV3AC M5Q89KPP7D9O BEQ8MO354SIE7 MMWW067LJE5JLOWPR2R39J1W2CE1Q3HYWOOVXKNQQ1QY8CH11RNIFF2U3OC 05YG6535GPYYHFSNR3PNNWSIX1RM0T9TY2CL8S9JASSOULIE62EG62MYGOBY565U1OI2E1 Y1BUUCDPYFQHUKOARHY3 5XOBM0H2A7PB524UTBU4DS2T6UJUHUKE436K1GEB AIJ22OPKT3PR8DURIT6TIGJJEKXINM2Y67', '9', '1089-02-09 11:52:54', '0625-10-24 07:52:16');
INSERT INTO `system_log` VALUES ('16', 'O04O0DE79LO3S8OEFC6U7XBSAPVPCPA0D4H1F7382ECOOM9NEQAVUG0IR82I4W1WX5FA7XBWYSU7CY9PIVUWM2R02SXF7 HDE58X1KAW5BML9 QSC5PH1INAEV0 EX3IY0EM9J0LWIFWHVFTK 2F6XJ12Y7T 3Y PJQ6NO6XHS9DK6XATLCKKT2KCFOPR745THCLJ8CK7GEELULDS6BYC4NUJKTN4GA2IEL SUTTEREI79KX7SYT48IH1ICSYE C1W5LTKQMGT5EJ6RHUGLI2UMT7D9CO2JDNDYWJUXMSOQXBUV5G0TJW6QQH5TR9S02O68IGJ22TAM3QSDQVAFKTRGVAQMO5EA4EKF5IXMW HG2K0JQY0MHLK3H5YGRE21S8OEQ9OSOU2872YEALRS7I QGJGGYJEN2KL5CQWKPVM8X1ES4N1NWETAE1X0CNFEJTA16K2RSMSYXVKAP4A6WL7FI6VS2CK7GFMOLT2WKQ19YF2B4YUMM', 'RWQEL4VDOF6HXYMJ8OUEH REB8YF8XBW', '0MDW39IWW0 29IN4OEMTYXREHQDU10SYIY6FHPST1ODUO6VVQR5RGYI8QGE3O2MM', '0DX4WYHJ U9BFMQFR6 XC6BQ95WD8NLAS4GNWQAB92YUAQAA7 FHVIXM8X29WPAR', 'X G W3F4 13XKQ64SL3XMYX68L2UM920UXAO GKFOD9S 7CP1SKHGBKGKCRE N2HA3AF3FXTW7P7FH8R3Y0GAVUA3AXN4CE87VFL95D0LYHDMTCY5RR1P86IHIG4PJOJ04GAJO0X7GD37CH6X8W6AUD30J1YFDVUR3V60Y7ORX XY5QDPTCDN6F8FA602S8NYGYOUTNGS0M1L5HPADSUFHO8242SES8RO8SS63APEPH7Y8DH 5OGB1PJPRSE74O1IW977ECY5HHWV6B6OV9P XEUSC5OCJMHLBY96BDW76AB5OJEQJLS87VU6ER2OOFCJTA AD4F72G6PAIX3OGHRSLJAXYP42PYL0I WWOIDR6YHI1O75GBFKA03AXCM5ETG1JMA8FDEEKQDQ5R2AXF28XI48ME4APMED7TSL ALDFAFA6E6R2LHGTGCXQ MR265X 8T1ATPMD0LER1LOS9MTIED SU6P9F2084QMIJ2FS0Y4R6WJSJ', '2', '1621-06-07 19:34:08', '1651-01-26 22:32:07');
INSERT INTO `system_log` VALUES ('17', 'QXLB0Q7QEA434JCUOA9WPUX7L S5900EVCV4DWO5Q5F9QBUUS077521X37IEGLLVEBME07LPD5IOU3B70LQ6AUJOBUEGJJ2HTH4DEJF3F0EI30KNG5T7CSRGHJK3G7PUPC0HI9JN5DE4QK9M5NOR3WY RWH8QCH3OF6CV2 ERT30KQR371VIRM2KFFUYD376B1RMH3N IRCSUW9UT4696RU6RKPHX0FLCG9A U4RPMABFSR54MP0OLTI2MB6J3JJFUDCEBVNNMEJNKY3C5VU5QCTPU9LTOMTAB5 FU3BP CJFTDBP893Y8G30BDGWJN NTUM65S1AR4M6JC5HBU61L3YE3TLHVB6D92QEFORS 08U3 K0QUOVMVNB5 0SA167FN A8D3OIKYWA37KRRCFL837BLVKR5H11R8MI69E6MIEBDDKJC498FXLCKJ3OO6QPTHNO78IPATVS91D5P1FKGC0X7F7VE37XIT8ICIJN WEPYRFJ5B', 'MCAP61Y3U4G5PEWGOYV7OIK99TP7 IVU', '8P8C3NN32VPG00CUIDP243WIYGGM8F4PASHHUIFF3D8NAISXOUK XL BIRE0LQVO', 'WBQS8Y5QHAIT 4C82ADORT8OXP5 0P JIQTVSFPREB S57CSFEFP42W 6BVW9VPX', 'XR0M1TO6URD453 CUVPB2SEI2EQ72I8OI5GP5NWR4B625AC6V6JPVQL6YHMTFJIP487LL7MTB970GN W3F4912GFJCST1JQBYU27WAPU2YQ5PK0GWMKQI0JV6 FRIV30O7K3JLDKK3YEJBSY88WKEQM9WTMUJR5JFY2J2O3SIRQ7N30MK2SRP9UJ3N4WTDOO04NE43MJ6IRTP LXWER3JKLRTQIG3RSGO7A796Y B9K0J1RERV4V2HJ6MO6M2VU070L65BFF7CBBU41T8JX7FTM6JI8AMLHNYT26GOMCQH4VH370WU2PT63C7PNVIONUQAXQWEFC9UQHANB1VP5RG7GCQ23JMTI80J9Y8I08O TL4GR3TCKCLCJ1TV22TLMJM SQ7 RXDTOBV15KXIK4J0DGMSTV2IPP3J6JLLGKDRJGITEXX94EKHBRVVF4AYTVDIRHA61C5JBE 6OV22Y52 1BULQHG8B70LOE5BTPQDP9ADH64VN ', '15', '0565-07-09 06:14:59', '1507-09-05 21:52:32');
INSERT INTO `system_log` VALUES ('18', '60JLGFQBIUGFROI33F KNEH57UQI1CKYOU0ID BD0251UIJAWXO3UN9DK7PYK K1I5JLNUQC3C7SX0H6K AUEV5FW3UUBCFK3 BLA9LV5PU8NIIAQVEDUQC1D871XQHNCRAWCDOP5L3IQ87CM7BYPTA2PQP31BFJQC5LV83TKRRJTDC4Y17EKMQG4335G77OA GEXS98EIERM 0N1SXC2KPEFAS1UF2LIAGOPWT86EEE  L3MC6WU J7XL1A0VG1HC9HCCR6 HV8M9PQ2 G0K1TDFRX1SD7WTMPG1R9MG8SRFLB18BX4YWEHA 820NE8L GY RWAOGT37OLMIGPGQLGCGQJUICC8IPV GTF0CGG1V219SVJSO1FVC19H8 O7QG77RBXUMU 0B9GWKG1NL307W8JR31CQ DFX7JSX9PG5INVNSHL5BI8KN2B42TJFIX934QMLJIO6GLMP0PB3PYCDKRCL5MP440ACW CD759I48CH965X', 'SD76IAMR0CSWEOOLHM6IHKC4CS5BCHPM', 'YIA5SI7WOM07NGNW N4H08P7XP5AUTXBW9IK3FE5JSWFOEQ03DXFTBE3JTSGE0QT', '1E2UP6A8J98JXB7LLUCMDC6CQTGWKGXE7U5TYFXI2HX1TGEG6QWOQ6CFQXE7QU28', 'O3ORYC08M8CTYIF6RRFJ3CFIT3IN42G5NEJSM6UQRDO P03EMFCTGTG9LT83SMIFL5HABN0O0M3L68SUTXBEFOLRQMBQYBIRIBQ 3M1UCDPH0LJLG7OMD1RMPBILY8GJKVRRMF7MRWXQCW9CM6 W8FL30OVQPV1SUNAM3UJ5AEGIVBJHHM5UETK17AQFP6S7JDBS6YV4Y 86RIS 98B7WIW1TQO3WF15VP6J5CVWVLI7TG7K4LYX89KCHGMCSDTM16GERXWL47UA9LRJC8S2D7FRTTPWF40ABA01A35VVK08WR7W7LTTXX0WG3FND8AG0D08KKK2AG4 473B0XAM EOV5FHMRM14FPUY JY8LGKORKP22IU06YKKKXW26NGWXI8CFLR004 M7W53SGANNFLGIJ BT2L2XT7NAODQUVB8FNB2LTRE2XGQP7AVE15KSN BJ7TFC2R4UOU8UJPAP4N51OPG34P10W6WO  NVYU1G87I3GRH', '12', '1151-04-30 13:23:39', '0562-08-29 06:25:33');

-- ----------------------------
-- Table structure for system_message
-- ----------------------------
DROP TABLE IF EXISTS `system_message`;
CREATE TABLE `system_message` (
  `MsgId` bigint(20) NOT NULL auto_increment COMMENT ' 字典主键ID',
  `Message` text collate utf8_bin COMMENT '名称',
  `ReciveUsers` varchar(2000) collate utf8_bin default '-1' COMMENT '消息接收者ID,逗号分隔， 默认 -1表示所有用户都可接收到',
  `Remark` varchar(500) collate utf8_bin default NULL COMMENT '备注',
  `CreateUser` bigint(20) default NULL COMMENT '发布人',
  `CreateTime` datetime default NULL COMMENT '入库时间',
  `UpdateTime` datetime default NULL COMMENT '变更时间',
  PRIMARY KEY  (`MsgId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='消息通知表';

-- ----------------------------
-- Records of system_message
-- ----------------------------

-- ----------------------------
-- Table structure for system_operate
-- ----------------------------
DROP TABLE IF EXISTS `system_operate`;
CREATE TABLE `system_operate` (
  `OperateId` bigint(20) NOT NULL auto_increment COMMENT '操作主键ID',
  `OperateName` varchar(64) collate utf8_bin default NULL COMMENT '操作名称',
  `EngName` varchar(64) collate utf8_bin default NULL COMMENT '英文名称',
  `Status` tinyint(2) default '1' COMMENT '状态:  1应用, 2禁用',
  `Remark` varchar(255) collate utf8_bin default NULL COMMENT '备注',
  `CreateUser` bigint(20) default NULL COMMENT '创建人',
  `CreateTime` datetime default NULL COMMENT '入库时间',
  `UpdateTime` datetime default NULL COMMENT '变更时间',
  PRIMARY KEY  (`OperateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='操作信息表';

-- ----------------------------
-- Records of system_operate
-- ----------------------------
INSERT INTO `system_operate` VALUES ('1', '添加', 'add', '1', '是否有添加操作权', null, null, null);
INSERT INTO `system_operate` VALUES ('2', '修改', 'update', '1', '修改按钮', null, null, null);
INSERT INTO `system_operate` VALUES ('3', '删除', 'remove', '1', '删除按钮', null, null, null);
INSERT INTO `system_operate` VALUES ('4', '预览', 'review', '1', '预览按钮', null, null, null);
INSERT INTO `system_operate` VALUES ('5', '考试', 'exam', '1', '是否有正式考试操作功能', null, null, null);
INSERT INTO `system_operate` VALUES ('6', '自测题目', 'mytest', '1', ' 是否有自测题目操作功能', null, null, null);
INSERT INTO `system_operate` VALUES ('7', '出题', 'makepaper', '1', '是否有出题操作权', null, null, null);
INSERT INTO `system_operate` VALUES ('8', '上传', 'upload', '1', '是否有文件上传操作权', null, null, null);
INSERT INTO `system_operate` VALUES ('9', '下载', 'download', '1', '是否有下载操作权', null, null, null);

-- ----------------------------
-- Table structure for system_resource
-- ----------------------------
DROP TABLE IF EXISTS `system_resource`;
CREATE TABLE `system_resource` (
  `ResourceId` bigint(20) NOT NULL auto_increment COMMENT '主键ID',
  `ResourceName` varchar(64) collate utf8_bin default NULL COMMENT '资源名称',
  `EngName` varchar(64) collate utf8_bin default NULL COMMENT '英文名称',
  `ParentId` bigint(20) default NULL COMMENT '父ID',
  `Path` varchar(255) collate utf8_bin default NULL COMMENT 'URL路径',
  `SourceIcon` varchar(255) collate utf8_bin default NULL COMMENT '图标地址',
  `SourceType` varchar(10) collate utf8_bin default NULL COMMENT '类型',
  `Status` tinyint(2) default '1' COMMENT '状态: 1应用;2禁用;3已删除',
  `Sort` int(11) default NULL COMMENT '排序',
  `Remark` varchar(255) collate utf8_bin default NULL COMMENT '备注',
  `CreateUser` bigint(20) default NULL COMMENT '创建人',
  `CreateTime` datetime default NULL COMMENT '入库时间',
  `UpdateTime` datetime default NULL COMMENT '变更时间',
  PRIMARY KEY  (`ResourceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='资源表';

-- ----------------------------
-- Records of system_resource
-- ----------------------------
INSERT INTO `system_resource` VALUES ('1', '系统菜单', null, null, null, null, null, '1', null, null, null, null, null);
INSERT INTO `system_resource` VALUES ('2', '系统管理', 'system_mgr', '1', '-', null, null, '1', '100', '', '21', '2015-06-23 17:14:27', '2015-07-06 13:28:39');
INSERT INTO `system_resource` VALUES ('3', '日志管理', 'log_mgr', '2', 'log/list.html', null, null, '1', '4', '系统日志管理', null, '2015-06-23 17:21:45', '2015-06-23 17:31:58');
INSERT INTO `system_resource` VALUES ('5', '模块管理', 'module_mgr', '2', '/module/list.html', null, null, '1', '3', '系统模块管理', null, '2015-06-23 17:26:36', null);
INSERT INTO `system_resource` VALUES ('6', '机构管理', 'dept_mgt', '1', '-', null, null, '1', '2', '机构管理', null, '2015-06-23 17:28:07', '2015-07-02 10:14:12');
INSERT INTO `system_resource` VALUES ('7', '机构管理', 'departmetnt_mgr', '6', 'department/list.html', null, null, '1', '1', '', null, '2015-06-23 17:28:43', '2015-07-02 08:54:55');
INSERT INTO `system_resource` VALUES ('8', '用户角色管理', '', '1', '-', null, null, '1', '3', '', null, '2015-06-23 17:29:40', '2015-07-02 10:14:19');
INSERT INTO `system_resource` VALUES ('9', '用户管理', 'user_mgr', '8', 'user/list.html', null, null, '1', '1', '用户管理', null, '2015-06-23 17:30:03', null);
INSERT INTO `system_resource` VALUES ('10', '角色管理', 'role_mgr', '8', 'role/list.html', null, null, '1', '2', '角色管理', null, '2015-06-23 17:30:30', null);
INSERT INTO `system_resource` VALUES ('11', '操作管理', 'operate_mgr', '8', 'operate/list.html', null, null, '1', '59', '操作功能管理', null, '2015-06-23 17:31:50', '2015-07-06 09:24:20');
INSERT INTO `system_resource` VALUES ('12', '试题管理', 'paper_mgr', '1', '-', null, null, '1', '9', '', null, '2015-06-24 09:35:59', '2015-07-02 10:14:24');
INSERT INTO `system_resource` VALUES ('13', '单选题目', 'single_choice', '12', 'singlechoice/list.html', null, null, '1', '1', '单选题目', null, '2015-06-24 09:37:50', null);
INSERT INTO `system_resource` VALUES ('14', '多选题目', 'multi_choice', '12', 'multichoice/list.html', null, null, '1', '2', '多选题目', null, '2015-06-24 09:38:29', null);
INSERT INTO `system_resource` VALUES ('15', '权限管理', 'group_mgr', '8', 'group/list.html', null, null, '1', '22', '', null, '2015-06-26 13:58:54', '2015-07-02 15:06:12');
INSERT INTO `system_resource` VALUES ('16', '填空题', 'fill', '12', 'fill/list.html', null, null, '1', null, '', null, '2015-06-26 14:13:07', null);
INSERT INTO `system_resource` VALUES ('17', '我的功能', 'my_effect', '1', '', null, null, '1', '1', '', null, '2015-07-06 08:52:06', null);
INSERT INTO `system_resource` VALUES ('18', '进入考试', 'begin_exam', '17', 'exam/list.html', null, null, '1', '1', '', null, '2015-07-06 08:52:50', null);
INSERT INTO `system_resource` VALUES ('19', '模拟考试', 'do_test', '17', 'test/list.html', null, null, '1', '2', '', null, '2015-07-06 08:53:21', null);
INSERT INTO `system_resource` VALUES ('20', '我的资料', 'my_profile', '17', 'profile/list.html', null, null, '1', '3', '', null, '2015-07-06 08:53:50', null);
INSERT INTO `system_resource` VALUES ('21', '查询成绩', 'find_score', '17', 'findscore', null, null, '1', '4', '', null, '2015-07-06 08:54:40', null);
INSERT INTO `system_resource` VALUES ('22', '简答题目', 'shorts', '12', 'shorts/list.html', null, null, '1', '4', '', null, '2015-07-06 08:55:50', null);
INSERT INTO `system_resource` VALUES ('23', '操作题目', 'operactes', '12', 'oper/list.html', null, null, '1', '5', '', null, '2015-07-06 08:56:44', null);
INSERT INTO `system_resource` VALUES ('24', '导入试卷', 'imp_paper', '12', 'importPaper/list.html', null, null, '1', '6', '', null, '2015-07-06 08:57:54', null);
INSERT INTO `system_resource` VALUES ('25', '出题管理', 'paper_mgr', '1', 'paper/list.html', null, null, '1', '1', '', null, '2015-07-06 08:59:12', '2015-07-06 09:04:20');
INSERT INTO `system_resource` VALUES ('26', '系统出题', 'sys_makeexam', '25', 'makeExam/list.html', null, null, '1', '1', '', null, '2015-07-06 09:00:19', null);
INSERT INTO `system_resource` VALUES ('27', '自主出题', 'myself_make', '25', 'myselfMakeExam/list.html', null, null, '1', '2', '', null, '2015-07-06 09:01:14', null);
INSERT INTO `system_resource` VALUES ('28', '历史考卷', 'his_paper', '25', 'hisPaper/list.html', null, null, '1', '3', '', null, '2015-07-06 09:02:12', null);
INSERT INTO `system_resource` VALUES ('30', '字典管理', 'dict_mgr', '2', 'dict/list.html', null, null, '1', '20', '', '21', '2015-07-06 13:16:42', null);

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `RoleId` bigint(20) NOT NULL auto_increment COMMENT '角色主键ID',
  `RoleName` varchar(64) collate utf8_bin default NULL COMMENT '角色名称',
  `EngName` varchar(64) collate utf8_bin default NULL COMMENT '英文名称',
  `Status` tinyint(2) default '1' COMMENT '状态:  1应用, 2禁用',
  `Remark` varchar(255) collate utf8_bin default NULL COMMENT '备注',
  `CreateUser` bigint(20) default NULL COMMENT '创建人',
  `CreateTime` datetime default NULL COMMENT '入库时间',
  `UpdateTime` datetime default NULL COMMENT '变更时间',
  PRIMARY KEY  (`RoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色表';

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES ('1', '县级管理员', 'country_admin', '1', '只有本县市考生管理权限', null, '2015-06-23 16:55:20', '2015-07-01 16:26:54');
INSERT INTO `system_role` VALUES ('2', '超级管理员', 'super_admin', '1', '系统最高管理者,有所有权限', null, '2015-06-23 16:56:02', '2015-07-01 16:27:07');
INSERT INTO `system_role` VALUES ('3', '考生', 'student', '1', '考生', null, '2015-06-23 16:56:31', null);
INSERT INTO `system_role` VALUES ('4', '系统管理员', 'system_admin', '1', '权限由超级管理员分配，拥有所有权限，考生管理权限，录入试题权限，安排模拟考试，安排统一考试的权限', null, '2015-07-01 16:25:58', null);
INSERT INTO `system_role` VALUES ('5', '市级管理员', 'city_admin', '1', '录入试题权限，安排模拟考试权限，安排统一考试权限', null, '2015-07-01 16:31:36', null);

-- ----------------------------
-- Table structure for system_role_group
-- ----------------------------
DROP TABLE IF EXISTS `system_role_group`;
CREATE TABLE `system_role_group` (
  `Id` bigint(20) NOT NULL auto_increment COMMENT '主键ID',
  `RoleId` bigint(20) NOT NULL COMMENT '角色表主键ID',
  `GroupId` bigint(20) NOT NULL COMMENT '组表主键ID',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色组中间表';

-- ----------------------------
-- Records of system_role_group
-- ----------------------------
INSERT INTO `system_role_group` VALUES ('8', '2', '9');
INSERT INTO `system_role_group` VALUES ('9', '5', '11');
INSERT INTO `system_role_group` VALUES ('10', '1', '12');
INSERT INTO `system_role_group` VALUES ('11', '4', '13');

-- ----------------------------
-- Table structure for system_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `system_role_resource`;
CREATE TABLE `system_role_resource` (
  `Id` bigint(20) NOT NULL auto_increment,
  `RoleId` bigint(20) NOT NULL COMMENT '角色表主键ID',
  `ResourceId` bigint(20) NOT NULL COMMENT '资源表主键ID',
  `OperateId` varchar(500) collate utf8_bin default NULL COMMENT '操作ID串,逗号分隔',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色资源中间表';

-- ----------------------------
-- Records of system_role_resource
-- ----------------------------
INSERT INTO `system_role_resource` VALUES ('25', '1', '9', '1,2,3,8');
INSERT INTO `system_role_resource` VALUES ('37', '5', '16', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('38', '5', '13', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('39', '5', '14', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('40', '2', '4', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('41', '2', '5', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('42', '2', '3', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('43', '2', '29', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('44', '2', '18', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('45', '2', '19', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('46', '2', '20', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('47', '2', '21', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('48', '2', '26', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('49', '2', '27', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('50', '2', '28', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('51', '2', '7', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('52', '2', '9', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('53', '2', '10', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('54', '2', '15', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('55', '2', '11', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('56', '2', '16', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('57', '2', '13', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('58', '2', '14', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('59', '2', '22', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('60', '2', '23', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('61', '2', '24', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('62', '4', '4', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('63', '4', '5', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('64', '4', '3', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('65', '4', '18', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('66', '4', '19', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('67', '4', '20', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('68', '4', '21', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('69', '4', '26', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('70', '4', '27', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('71', '4', '28', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('72', '4', '7', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('73', '4', '9', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('74', '4', '10', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('75', '4', '15', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('76', '4', '11', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('77', '4', '16', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('78', '4', '13', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('79', '4', '14', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('80', '4', '22', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('81', '4', '23', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('82', '4', '24', '1,2,3,4,5,6,7,8,9');
INSERT INTO `system_role_resource` VALUES ('83', '3', '18', '');
INSERT INTO `system_role_resource` VALUES ('84', '3', '19', '');
INSERT INTO `system_role_resource` VALUES ('85', '3', '20', '');
INSERT INTO `system_role_resource` VALUES ('86', '3', '21', '');

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `UserId` bigint(20) NOT NULL auto_increment,
  `DeptId` bigint(20) NOT NULL COMMENT '部门表主键ID',
  `ForcastNumber` varchar(32) collate utf8_bin default NULL COMMENT '预报员编号',
  `LoginName` varchar(64) collate utf8_bin NOT NULL COMMENT '登陆账号',
  `Password` varchar(64) collate utf8_bin default NULL COMMENT '密码',
  `UserName` varchar(64) collate utf8_bin NOT NULL COMMENT '姓名',
  `EngName` varchar(64) collate utf8_bin default NULL COMMENT '英文名',
  `Birthday` date default NULL COMMENT '出生年月',
  `PhoneNumber` varchar(20) collate utf8_bin default NULL COMMENT '电话号码',
  `Email` varchar(32) collate utf8_bin default NULL COMMENT '邮箱',
  `Sex` char(1) collate utf8_bin default NULL COMMENT '性别:   1男;2女',
  `Address` varchar(255) collate utf8_bin default NULL COMMENT '地址',
  `IdCard` varchar(64) collate utf8_bin default NULL COMMENT '身份证号码',
  `EducationFirst` varchar(64) collate utf8_bin default NULL COMMENT '第一学历',
  `SchoolFirst` varchar(64) collate utf8_bin default NULL COMMENT '毕业院校(第一学历)',
  `MajorFirst` varchar(64) collate utf8_bin default NULL COMMENT '专业(第一学历)',
  `GraduateFirst` datetime default NULL COMMENT '毕业时间(第一学历)',
  `EducationSecond` varchar(64) collate utf8_bin default NULL COMMENT '第二学历',
  `SchoolSecond` varchar(64) collate utf8_bin default NULL COMMENT '毕业院校(第二学历)',
  `MajorSecond` varchar(64) collate utf8_bin default NULL COMMENT '专业(第二学历)',
  `GraduateSecond` datetime default NULL COMMENT '毕业时间(第二学历)',
  `JobTitle` varchar(64) collate utf8_bin default NULL COMMENT '职称',
  `JobDate` datetime default NULL COMMENT '任职时间',
  `JobRemark` varchar(255) collate utf8_bin default NULL COMMENT '曾经历岗位及年限',
  `ForcastBeginDate` date default NULL COMMENT '开始从事预报年月',
  `Train` varchar(255) collate utf8_bin default NULL COMMENT '培训经历',
  `Status` tinyint(4) default NULL COMMENT '状态:  1启用,2已禁用登陆，3已被删除',
  `Remark` varchar(255) collate utf8_bin default NULL COMMENT '备注',
  `CreateUser` bigint(20) default NULL COMMENT '创建人',
  `CreateTime` datetime default NULL COMMENT '入库时间',
  `UpdateTime` datetime default NULL COMMENT '变更时间',
  PRIMARY KEY  (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES ('21', '5', null, 'admin22', '202CB962AC59075B964B07152D234B70', '李四', null, null, '158213612', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', null, null, '2015-06-23 16:26:24', null);
INSERT INTO `system_user` VALUES ('22', '5', null, 'love61v', 'E10ADC3949BA59ABBE56E057F20F883E', '胡小', null, null, '', '', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', null, null, '2015-07-01 16:47:19', null);
INSERT INTO `system_user` VALUES ('23', '6', null, 'hb', 'E10ADC3949BA59ABBE56E057F20F883E', '胡二', null, null, '', '', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', null, null, '2015-07-01 16:47:34', null);
INSERT INTO `system_user` VALUES ('24', '7', null, 'hubo', 'E10ADC3949BA59ABBE56E057F20F883E', '胡三', null, null, '', '', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', null, null, '2015-07-01 16:48:30', null);
INSERT INTO `system_user` VALUES ('25', '5', null, 'test', 'E10ADC3949BA59ABBE56E057F20F883E', '胡小小', null, null, '', '', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', null, null, '2015-07-02 14:35:27', null);
INSERT INTO `system_user` VALUES ('27', '2', null, 'vf', 'E10ADC3949BA59ABBE56E057F20F883E', '', null, null, '', '', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', null, null, '2015-07-02 16:06:26', null);
INSERT INTO `system_user` VALUES ('28', '6', null, 'aa', 'E10ADC3949BA59ABBE56E057F20F883E', '考生1', null, null, '', '', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', null, '21', '2015-07-06 10:39:58', null);
INSERT INTO `system_user` VALUES ('29', '7', null, 'bb', 'E10ADC3949BA59ABBE56E057F20F883E', '考生2', null, null, '', '', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', null, '21', '2015-07-06 10:40:09', null);
INSERT INTO `system_user` VALUES ('30', '7', null, 'cc', 'E10ADC3949BA59ABBE56E057F20F883E', '考生3', null, null, '', '', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', null, '21', '2015-07-06 10:40:18', null);
INSERT INTO `system_user` VALUES ('31', '7', null, 'dd', 'E10ADC3949BA59ABBE56E057F20F883E', '考生4', null, null, '', '', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', null, '21', '2015-07-06 10:40:27', null);
INSERT INTO `system_user` VALUES ('32', '7', null, 'ee', 'E10ADC3949BA59ABBE56E057F20F883E', '考生3', null, null, '', '', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', null, '21', '2015-07-06 10:40:35', null);
INSERT INTO `system_user` VALUES ('33', '6', null, 'uu', 'E10ADC3949BA59ABBE56E057F20F883E', '考生10', null, null, '', '', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', null, '21', '2015-07-06 10:40:48', null);
INSERT INTO `system_user` VALUES ('34', '6', null, 'ww', 'E10ADC3949BA59ABBE56E057F20F883E', '考生900', null, null, '', '', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', null, '21', '2015-07-06 10:41:07', null);

-- ----------------------------
-- Table structure for system_user_group
-- ----------------------------
DROP TABLE IF EXISTS `system_user_group`;
CREATE TABLE `system_user_group` (
  `Id` bigint(20) NOT NULL auto_increment COMMENT '主键ID',
  `UserId` bigint(20) NOT NULL COMMENT '用户表主键ID',
  `GroupId` bigint(20) NOT NULL COMMENT '组表主键ID',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户组中间表';

-- ----------------------------
-- Records of system_user_group
-- ----------------------------
INSERT INTO `system_user_group` VALUES ('22', '24', '12');
INSERT INTO `system_user_group` VALUES ('23', '23', '11');
INSERT INTO `system_user_group` VALUES ('24', '22', '13');
INSERT INTO `system_user_group` VALUES ('25', '21', '9');
INSERT INTO `system_user_group` VALUES ('26', '28', '14');
INSERT INTO `system_user_group` VALUES ('27', '29', '14');
INSERT INTO `system_user_group` VALUES ('28', '30', '14');
INSERT INTO `system_user_group` VALUES ('29', '31', '14');

-- ----------------------------
-- Table structure for system_user_role
-- ----------------------------
DROP TABLE IF EXISTS `system_user_role`;
CREATE TABLE `system_user_role` (
  `Id` bigint(20) NOT NULL auto_increment,
  `UserId` bigint(20) NOT NULL COMMENT '用户ID',
  `RoleId` bigint(20) NOT NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of system_user_role
-- ----------------------------
INSERT INTO `system_user_role` VALUES ('2', '21', '3');
INSERT INTO `system_user_role` VALUES ('3', '22', '3');
INSERT INTO `system_user_role` VALUES ('4', '23', '3');
INSERT INTO `system_user_role` VALUES ('5', '24', '3');
INSERT INTO `system_user_role` VALUES ('6', '25', '3');
INSERT INTO `system_user_role` VALUES ('7', '26', '3');
INSERT INTO `system_user_role` VALUES ('8', '27', '3');
INSERT INTO `system_user_role` VALUES ('9', '28', '3');
INSERT INTO `system_user_role` VALUES ('10', '29', '3');
INSERT INTO `system_user_role` VALUES ('11', '30', '3');
INSERT INTO `system_user_role` VALUES ('12', '31', '3');
INSERT INTO `system_user_role` VALUES ('13', '32', '3');
INSERT INTO `system_user_role` VALUES ('14', '33', '3');
INSERT INTO `system_user_role` VALUES ('15', '34', '3');
