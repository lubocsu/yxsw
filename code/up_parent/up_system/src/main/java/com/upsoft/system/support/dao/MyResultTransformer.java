/*
 * MyResultTransformer.java
 * Created on 2015年1月23日 下午4:17:01
 * Copyright (c) 重庆扬讯软件技术有限公司  All Rights Reserved.
 * http://www.upsoft.com.cn
 *
 * This software is the confidential and proprietary information of UPSoft.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with UPSoft.
 */
package com.upsoft.system.support.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.transform.ResultTransformer;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：MyResultTransformer.java<br>
 * 摘要：将数据库字段名全部转换成小写<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：蒋迪<br>
 * 完成日期：2015年1月23日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：蒋迪<br>
 * 完成日期：2015年1月23日<br>
 */
public class MyResultTransformer implements ResultTransformer {

	private static final long serialVersionUID = 2547865729505825914L;

	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Map<String, Object> map = new HashMap<String, Object>();
		int i = 0;
		for(String name : aliases){
			map.put(name.toLowerCase(), tuple[i++]);
		}
		return map;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List transformList(List collection) {
		return collection;
	}

}
