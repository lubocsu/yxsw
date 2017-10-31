package com.upsoft.systemweb.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 * 
 * 文件名称：SystemConstant.java<br>
 * 摘要：系统常量类<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：吴炫<br>
 * 完成日期：2015年1月21日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：吴炫<br>
 * 完成日期：2015年1月21日<br>
 */
public class SystemWebConstant {

	/**
	 * 日志-来源类型
	 */
	public enum LogModuleType {

		UserModule("001", "用户模块"), MenuModule("002", "菜单模块"), RoleModule("003",
				"角色模块"), PermissionModule("004", "权限模块");

		private String key;
		private String value;

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		private LogModuleType(String key, String value) {
			this.key = key;
			this.value = value;
		}

	}

	/**
	 * 日志-操作类型
	 */
	public enum LogOperationType {

		ADD("1", "增加"), DEL("2", "删除"), UPD("3", "修改");

		private String key;
		private String value;

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		private LogOperationType(String key, String value) {
			this.key = key;
			this.value = value;
		}

	}

	/**
	 * 字段-名称 映射
	 */
	public static Map<String, String> colNameRelation = null;
	static {
		colNameRelation = new HashMap<String, String>();
		colNameRelation.put("", "");
	}

	/**
	 * 类名-所属模块 映射
	 */
	public static Map<String, String> classModuleRelation = null;
	static {
		classModuleRelation = new HashMap<String, String>();
		classModuleRelation.put("", "");
	}
	
	/**
	 * 机构类型枚举
	 * @author hy
	 *
	 */
	public enum orgType{
		GROUP("1", "集团"), COMPANY("2", "公司"), FACTORY("3", "厂站"),DEPT("4", "部门");

		private String key;
		private String value;

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		private orgType(String key, String value) {
			this.key = key;
			this.value = value;
		}
	} 
	
	/**
	 * 数据权限类型常量
	 */
	public static final String PERMISSION_DATA_TYPE_ROLE = "1";
	public static final String PERMISSION_DATA_TYPE_USER = "2";
	
	/**
	 * 数据权限状态常量
	 */
	public static final String PERMISSION_DATA_STATUS_VALID = "1";
	public static final String PERMISSION_DATA_STATUS_INVALID = "0";
	
	public enum IS_SYSTEM_USER{
		YES("1","系统用户"),NO("0","非系统用户");
		private String key;
		private String value;
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		private IS_SYSTEM_USER(String key ,String value){
			this.key = key;
			this.value= value;
		}
	}

}
