/*
 * ExportExcelBean.java
 * Created on 2015年1月28日 下午4:27:56
 * Copyright (c) 重庆扬讯软件技术有限公司  All Rights Reserved.
 * http://www.upsoft.com.cn
 *
 * This software is the confidential and proprietary information of UPSoft.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with UPSoft.
 */
package com.upsoft.system.bean;

import java.util.List;
import java.util.Map;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：ExportExcelBean.java<br>
 * 摘要：导出Excel用bean<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：蒋迪<br>
 * 完成日期：2015年1月28日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：蒋迪<br>
 * 完成日期：2015年1月28日<br>
 */
public class ExportExcelBean {
	//表头定义
	private List<ExcelHeaderBean> header;
	//数据集合
	private List<Map<String, Object>> data;
	
	public List<ExcelHeaderBean> getHeader() {
		return header;
	}
	public void setHeader(List<ExcelHeaderBean> header) {
		this.header = header;
	}
	public List<Map<String, Object>> getData() {
		return data;
	}
	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}
}
