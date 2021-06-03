CREATE TABLE `dict` (
  `dic_id` int(11) unsigned NOT NULL COMMENT '自增序号',
  `category` varchar(32) NOT NULL DEFAULT '' COMMENT '大类、域',
  `category_name` varchar(255) NOT NULL DEFAULT '' COMMENT '大类、域描述',
  `type` varchar(32) NOT NULL DEFAULT '' COMMENT '小类',
  `type_name` varchar(128) NOT NULL DEFAULT '' COMMENT '小类描述',
  `enum_name` varchar(32) NOT NULL DEFAULT '' COMMENT '枚举英文名',
  `enum_value` smallint(3) NOT NULL COMMENT '枚举值',
  `enum_desc` varchar(255) NOT NULL DEFAULT '' COMMENT '描述',
  `status` smallint(1) NOT NULL DEFAULT '2' COMMENT '启用状态: 1停用;2启用',
  `creator` varchar(32) NOT NULL DEFAULT 'CcSys' COMMENT '创建人',
  `updater` varchar(32) NOT NULL DEFAULT 'CcSys' COMMENT '更新人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`dic_id`)
) ;