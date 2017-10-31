package com.upsoft.yxsw.constant;

/**
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：DictConstant.java<br>
* 摘要：系统用字典常量<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：胡毅<br>
* 完成日期：2017年9月10日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：胡毅<br>
* 完成日期：2017年9月10日<br>
 */
public enum DictConstant {

	CHECKITEM_INPUTTYPE("UM000014"),	//检查项输入类型
	CHECKITEM_TYPE("UM000011"), //检查项类型
	CHECKITEM_SFYC("UM000024"), //检查项是否正常异常
	CHECKITEM_SFMR("UM000006"), //公用是否
	FACTORY_TYPE("UM000003"),//厂商类型
	FACTORY_XZ("UM000004"),//厂商性质
	IMPORTANT_LEVEL("UM000010"),//重要程度
	NOTICE_TYPE("UM000009"),//公告类型
	UNIT("UM000007"),//设备类型计量单位
	EQ_TYPE("UM000002"),//设备类别
	GC_JK("UM000001"),//国产进口
	ZY_STATUS("UM000005"),//在用状态
	WEATHER_TYPE("UM000017"),// 天气类型字典
	TASK_STATUS("UM000020"),//任务状态
	RFID_STATUS("UM000021"),//厂巡任务巡检点RFID扫描状态
	SBSS_STATUS("UM000022"),//厂巡任务设备设施扫描状态
	SB_OR_SS("UM000012"),//设备或者设施
	ZYP_STATUS("UM000019"), // 作业票状态 
	ZYP_OPT_TYPE("UM000025"), // 作业票操作类型
	ZYP_WARNING("UM000018") // 作业票工艺预警状态
	;
	
	
	private String value;
	
	private DictConstant(String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
	
}