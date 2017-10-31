/*
 * ICommonService.java
 * Created on 2015年1月27日 上午11:50:27
 * Copyright (c) 重庆扬讯软件技术有限公司  All Rights Reserved.
 * http://www.upsoft.com.cn
 *
 * This software is the confidential and proprietary information of UPSoft.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with UPSoft.
 */
package com.upsoft.system.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：ICommonService.java<br>
 * 摘要：简要描述本文件的内容<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：蒋迪<br>
 * 完成日期：2015年1月27日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：蒋迪<br>
 * 完成日期：2015年1月27日<br>
 */
public interface CommonService extends BaseService {
	
	/**
	 * 根据supportData中的id，过滤条件。获取源数据
	 * @date 2015年1月28日 上午9:17:52
	 * @author 蒋迪
	 * @param id				supportData中的id
	 * @param whereCondition	过滤条件
	 * @param iconPath			图标路径，仅针对树型结构
	 * @return 
	 */
	public List<Map<String, Object>> getSupportDataSource(String id, String whereCondition, String iconPath);
	
	/**
	 * 读取inputStream流中的excel文件，并保存入库
	 * @date 2015年1月30日 下午2:52:14
	 * @author 蒋迪
	 * @param is		excel文件流
	 * @param className 实体名（大小写敏感，如SysOrg）
	 */
	public void saveExcelImport(InputStream is, String className);
}
