package com.upsoft.system.constant;


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
public class CommonConstant {
	// 查询类型
	public enum QueryType {
		LIST, COUNT;
	}

	public static final String TOKEN_ID = "tokenId";
	
	/**
	 * YES
	 */
	public static final String STATUS_VALID = "1";
	/**
	 * NO
	 */
	public static final String STATUS_DISABLE = "0";
	/**
	 * 状态：删除
	 */
	public static final String STATUS_DELETE = "2";
	/**
	 * 获取系统用户对象信息的键
	 */
	public static final String KEY_FOR_SYSUSER = "KEY_FOR_SYSUSER";
	/**
	 * 保存配置文件信息KEY
	 */
//	public static final String KEY_FOR_SYS_CONFIG = "BASE_CONFIG_PRPERTIES";
	
	/**
	 * 保存菜单下资源权限KEY
	 */
	public static final String KEY_FOR_IN_MENU_PERMISSION = "KEY_FOR_IN_MENU_PERMISSION";
	
	/**
	 * 地图查询范围字典编码，单位米
	 */
	public static final String GIS_QUERY_BUFFER ="queryBuffer";
	
	
	/**
	* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
	* All rights reserved.<br>
	* 文件名称：CommonConstant.java<br>
	* 摘要：系统编码常量<br>
	* -------------------------------------------------------<br>
	 */
	public static class SYSTEMCODE{
		public static final String UP_SYSTEM_CODE = "up_systemweb";
		public static final String UP_YXSW_CODE = "up_yxsw";
		public static final String APP_YXSW_CODE = "app_yxsw";
	}
	
	/**
	* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
	* All rights reserved.<br>
	* 文件名称：CommonConstant.java<br>
	* 摘要：系统编码前缀<br>
	 */
	public enum SYSTEMCODE_PREFIX{
		UP_PREFIX("up","PC端系统编码前缀"),
		APP_PREFIX("app","APP系统编码前缀");
		private String name;
		private String value;  
	    private SYSTEMCODE_PREFIX(String value, String name) {  
	        this.value = value;
	        this.name = name;
	    }  
	    public String getValue(){
	    	return value;
	    }
	    public String getName(){
	    	return name;
	    }
	}
	
	/**
	* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
	* All rights reserved.<br>
	* 文件名称：CommonConstant.java<br>
	* 摘要：系统消息状态<br>
	* -------------------------------------------------------<br>
	 */
	public enum MsgStatus{
		
		READED(1, "已读"),
		UNREADED(0, "未读");
		private String name;
		private int value;  
	    private MsgStatus(int value, String name) {  
	        this.value = value;
	        this.name = name;
	    }  
	    public int getValue(){
	    	return value;
	    }
	    public String getName(){
	    	return name;
	    }
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
	 * 是或否常量
	 * @author Administrator
	 * @date 2017年9月11日 下午4:55:02
	 */
	public static class STATUS_YoN{
		
		public static final String YES = "1";
		public static final String NO = "0";
		
	}
}
