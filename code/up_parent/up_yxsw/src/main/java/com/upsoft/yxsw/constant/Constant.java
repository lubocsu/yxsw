package com.upsoft.yxsw.constant;

/**
 * Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：Constant.java<br>
 * 摘要：系统内部的常量类<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：胡毅<br>
 * 完成日期：2017年9月10日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：胡毅<br>
 * 完成日期：2017年9月10日<br>
 */
public class Constant {

	// 通用是否状态
	public enum YES_NO {
		YES("1", "是"), NO("0", "否");
		private String name;
		private String value;

		private YES_NO(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public String getName() {
			return name;
		}
	}

	// 通用是否正常值
	public enum SFZC {
		YES("1", "正常"), NO("2", "异常");
		private String name;
		private String value;

		private SFZC(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public String getName() {
			return name;
		}
	}

	// 检查项定义是否被删除标识
	public enum CHECKITEM_DEL_FLAG {
		YES("1", "已删除"), NO("0", "未删除");
		private String name;
		private String value;

		private CHECKITEM_DEL_FLAG(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public String getName() {
			return name;
		}
	}

	// 检查项输入类型值 定义
	public enum CHECKITEM_INPUTTYPE_VALUE {
		RADIO("0", "单选"), TEXT("1", "文本"), NUM("2", "数字");
		private String name;
		private String value;

		private CHECKITEM_INPUTTYPE_VALUE(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public String getName() {
			return name;
		}
	}

	// 设备设施分类
	public enum DETAIL_TYPE {
		EQUIPMENT("1", "设备"), FACILITY("2", "设施"), WARNING("3", "安全提醒");
		private String name;
		private String value;

		private DETAIL_TYPE(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public String getName() {
			return name;
		}
	}

	// 任务执行状态
	public enum CX_TASK_STATUS {
		UNDISTRIBUTE("0", "待下发"), DISTRIBUTE("1", "已下发"), EXECUTING("2", "在执行"), COMPLETE("3", "已完成");
		private String name;
		private String value;

		private CX_TASK_STATUS(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public String getName() {
			return name;
		}
	}

	// 配置项类型
	public enum PZ_TYPE {
		CHECKTYPE("1", "检查项配置"), SAFETYPE("2", "安全定义配置");
		private String name;
		private String value;

		private PZ_TYPE(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public String getName() {
			return name;
		}
	}

	// 配置项类型
	public enum RFID_CONFIRMED_TYPE {
		SCANNED("1", "扫描"), CANNOT_SCAN("2", "无法扫描");
		private String name;
		private String value;

		private RFID_CONFIRMED_TYPE(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public String getName() {
			return name;
		}
	}

	// 页码和页大小
	public class PageStatus {

		/** 页码 */
		public static final String PAGE_INDEX = "1";
		/** 页大小 */
		public static final String PAGE_SIZE = "4";
	}


	// 通用是否删除
	public enum ISDEL_FLAG {
		YES("1", "是"), NO("0", "否");
		private String name;
		private String value;

		private ISDEL_FLAG(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public String getName() {
			return name;
		}
	}


	// 作业票状态作业票状态: 01 拟定中 、02  审批中、03  已审批、04  在填报、05  待验收、06  已验收
	public enum ZYP_STAUTS {
		PROTOCOL("01", "拟定中"), AUDITING("02", "审核中"),AUDITED("03","已审核"),WRITING("04","在填报"),CHECKING("05","待验收"),CHECKED("06","已验收");
		private String name;
		private String value;

		private ZYP_STAUTS(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public String getName() {
			return name;
		}
	}
	
	//作业票是否通过
	public enum ZYP_CHECK {
		PASS("1", "通过"), NOPASS("0", "不通过");
		private String name;
		private String value;

		private ZYP_CHECK(String value, String name) {
			this.value = value;
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public String getName() {
			return name;
		}
	}

		//作业票操作类型
		public enum ZYP_OPT_TYPE {
			PREPARED("1", "拟定"), AUDIT("2", "审核"),DISCONTINUE("3","中止"),WRITE("4","填报"),ACCEPTANCED("5","验收");
			private String name;
			private String value;

			private ZYP_OPT_TYPE(String value, String name) {
				this.value = value;
				this.name = name;
			}

			public String getValue() {
				return value;
			}

			public String getName() {
				return name;
			}
		}
		
		//消息重要程度
		public enum MSG_IMPORTANT {
			ORDINARY("1", "一般"), IMPORTANT("2", "重要");
			private String name;
			private String value;

			private MSG_IMPORTANT(String value, String name) {
				this.value = value;
				this.name = name;
			}

			public String getValue() {
				return value;
			}

			public String getName() {
				return name;
			}
		}
		
		// 消息下发类型
		public enum TASK_TYPE {
			SYSTEMAUTO("1", "系统下发"), MANUAL("2", "主动处理");
			private String name;
			private String value;

			private TASK_TYPE(String value, String name) {
				this.value = value;
				this.name = name;
			}

			public String getValue() {
				return value;
			}

			public String getName() {
				return name;
			}
		}
}
