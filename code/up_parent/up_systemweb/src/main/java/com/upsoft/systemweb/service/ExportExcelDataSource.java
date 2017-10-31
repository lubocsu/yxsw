/*
 * IExportExcelDataSource.java
 * Created on 2015年1月28日 下午2:50:03
 * Copyright (c) 重庆扬讯软件技术有限公司  All Rights Reserved.
 * http://www.upsoft.com.cn
 *
 * This software is the confidential and proprietary information of UPSoft.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with UPSoft.
 */
package com.upsoft.systemweb.service;

import java.util.Map;

import com.upsoft.system.bean.ExportExcelBean;
import com.upsoft.system.bean.PageBean;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：IExportExcelDataSource.java<br>
 * 摘要：导出Excel获取数据源接口<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：蒋迪<br>
 * 完成日期：2015年1月28日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：蒋迪<br>
 * 完成日期：2015年1月28日<br>
 */
public interface ExportExcelDataSource {
	/**
	 * 获取导出Excel数据源
	 * @date 2015年1月29日 上午10:51:31
	 * @author 蒋迪
	 * @param params	sql查询参数
	 * @param pageBean	分页对象
	 * @return 
	 */
	public ExportExcelBean getDataSource(Map<String, Object> params, PageBean pageBean);
}
