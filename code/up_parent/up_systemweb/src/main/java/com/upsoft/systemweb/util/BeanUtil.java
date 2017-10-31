/*
 * BeanUtil.java
 * Created on 2015年1月30日 下午1:39:00
 * Copyright (c) 重庆扬讯软件技术有限公司  All Rights Reserved.
 * http://www.upsoft.com.cn
 *
 * This software is the confidential and proprietary information of UPSoft.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with UPSoft.
 */
package com.upsoft.systemweb.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.upsoft.system.entity.BaseEntity;
import com.upsoft.system.support.annotation.ExcelAnnotation;
import com.upsoft.system.util.DateUtil;
import com.upsoft.systemweb.service.DictTreeDataService;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：BeanUtil.java<br>
 * 摘要：实体反射工具类，主要用于excel导入<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：蒋迪<br>
 * 完成日期：2015年1月30日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：蒋迪<br>
 * 完成日期：2015年1月30日<br>
 */
public class BeanUtil {
	
	private DictTreeDataService service;
	private Map<String, Map<String, String>> dictMap = new HashMap<String, Map<String,String>>();
	public BeanUtil(DictTreeDataService server){
		service = server;
	}
	
	private Field[] fields;
	
	/**
	 * 根据实体中的@ExcelAnnotation注解，将对应的值设置到实体对象中
	 * @date 2015年1月30日 下午2:43:13
	 * @author 蒋迪
	 * @param labels	注解集合
	 * @param datas		值集合
	 * @param classOfT	实体名（大小写敏感）
	 * @return 
	 */
	public <T extends BaseEntity> BaseEntity getEntity(List<Object> labels, List<Object> datas, Class<T> classOfT){
		fields = classOfT.getDeclaredFields();
		BaseEntity entity = null;
		try {
			entity = classOfT.newInstance();
			int length = datas.size();
			for (int i = 0; i < length; i++) {
				try {
					setPropertyValue(labels.get(i).toString(), datas.get(i).toString(), entity);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e1) {
			return null;
		}
		return entity;
	}
	
	/**
	 * 设置属性
	 * @date 2015年1月30日 下午2:45:12
	 * @author 蒋迪
	 * @param label		@ExcelAnnotation注解
	 * @param value		值
	 * @param entity	实体对象
	 * @throws Exception 
	 */
	protected <T> void setPropertyValue(String label, Object value, BaseEntity entity) throws Exception{
		for (Field field : fields) {
			ExcelAnnotation ea = field.getAnnotation(com.upsoft.system.support.annotation.ExcelAnnotation.class);
			if (ea!=null && !StringUtils.isEmpty(ea.label())){
				if (label.equals(ea.label())){
					if (!StringUtils.isEmpty(ea.dictCode())){
						if (dictMap.get(ea.dictCode())==null){
							dictMap.put(ea.dictCode(), service.queryByTreeId(ea.dictCode()));
						}
						Map<String, String> subDictMap = (Map<String, String>)dictMap.get(ea.dictCode());
						value = subDictMap.get(value.toString());
					}
					Method m = entity.getClass().getDeclaredMethod("set"+upcaseFirstChar(field.getName()), field.getType());
					value = typeParse(field.getType().getSimpleName(), value);
					if (value!=null){
						m.invoke(entity, value);	
					}
					return;
				}
			}
		}
	}
	
	/**
	 * 类型转换
	 * @date 2015年1月30日 下午2:45:44
	 * @author 蒋迪
	 * @param name
	 * @param value
	 * @return 
	 */
	protected Object typeParse(String name, Object value){
		if (value==null)	return null;
		String str = value.toString();
		if (name.equals("String"))	return str;
		if (name.equals("Integer")){
			try {
				Integer result = Integer.valueOf(str);
				return result;
			} catch (NumberFormatException e) {
				return null;
			}
		}else if (name.equals("Long")){
			try {
				Long result = Long.valueOf(str);
				return result;
			} catch (NumberFormatException e) {
				return null;
			}
		}else if (name.equals("Date")){
			if (str.length()<11){
				try {
					return DateUtil.getFormatInstance(DateUtil.DATE_FORMAT_WITHOUT_TIME).format(str);
				} catch (Exception e) {
					return null;
				}
			}else{
				try {
					return DateUtil.getFormatInstance(DateUtil.DATE_FULL_FORMAT).format(str);
				} catch (Exception e) {
					return null;
				}
			}
		}
		return null;
	}
	
	/**
	 * 返回首字母大写的字符串，用于组装set方法
	 * @date 2015年1月30日 下午2:46:06
	 * @author 蒋迪
	 * @param str
	 * @return 
	 */
	private String upcaseFirstChar(String str){
		String first = str.substring(0,1);
		first = first.toUpperCase();
		String other = str.substring(1,str.length());
		return first+other;
	}
}
