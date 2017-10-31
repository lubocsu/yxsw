/*
 * CommonServiceImpl.java
 * Created on 2015年1月27日 上午11:50:52
 * Copyright (c) 重庆扬讯软件技术有限公司  All Rights Reserved.
 * http://www.upsoft.com.cn
 *
 * This software is the confidential and proprietary information of UPSoft.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with UPSoft.
 */
package com.upsoft.systemweb.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.upsoft.system.entity.BaseEntity;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.system.service.CommonService;
import com.upsoft.system.util.ExcelReaderUtil;
import com.upsoft.systemweb.dao.SystemDefineDao;
import com.upsoft.systemweb.service.DictTreeDataService;
import com.upsoft.systemweb.util.BeanUtil;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：CommonServiceImpl.java<br>
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
@Service("commonService")
public class CommonServiceImpl extends BaseServiceImpl implements CommonService {
	@Autowired
	private SystemDefineDao systemDefineData;
	
	@Autowired
	private DictTreeDataService dictService;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getSupportDataSource(String dataId, String whereCondition, String iconPath){
		Map<String, Object> condition = getConfigSql(dataId, whereCondition); 
		String sql = condition.get("sql").toString();
		List<Map<String, Object>> list = systemDefineData.queryListBySql(sql, (Map<String, Object>)condition.get("params"));
		Boolean hasParentId = null;
		if (list!=null && list.size()>0){
			hasParentId = list.get(0).get("parentid")!=null && !StringUtils.isEmpty(list.get(0).get("parentid").toString());
			
			for (Map<String, Object> map : list) {
				if (hasParentId){
					map.put("parentId", map.get("parentid"));
				}
				if (!StringUtils.isEmpty(iconPath)){
					map.put("icon",iconPath);
				}
			}
		}
		
		return list;
	}

	/**
	 * 从supportData配置文件中读取sql配置，组装过滤条件
	 * @date 2015年1月28日 上午9:20:03
	 * @author 蒋迪
	 * @param dataId			supportData中配置的id
	 * @param whereCondition	过滤条件
	 * @return 
	 */
	private Map<String, Object> getConfigSql(String dataId, String whereCondition){
		String path = getClass().getClassLoader().getResource("supportData.xml").getPath();
		File f = null;
		try {
			f = new File(java.net.URLDecoder.decode(path,"utf-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		SAXReader reader = new SAXReader();
		Document doc;
		try {
			doc = reader.read(f);
			Element root = doc.getRootElement();
			Node node = root.selectSingleNode("data[@id='"+dataId+"']");
			String sql = node.getStringValue().trim();
			Map<String, Object> result = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(whereCondition)){
				Map<String, Object> condition = getWhereSql(whereCondition);
				sql += " where " + condition.get("sql").toString();
				result.put("params", condition.get("params"));
			}
			result.put("sql", sql);
			return result;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 解析前端过滤条件参数
	 * @date 2015年1月28日 上午9:20:46
	 * @author 蒋迪
	 * @param whereCondition	过滤条件
	 * @return 
	 */
	private Map<String, Object> getWhereSql(String whereCondition){
		String strs[] = whereCondition.split(",");
		String where = "";
		Map<String, Object> params = new HashMap<String, Object>();
		for (String string : strs) {
			String tmp[] = string.split("=");
			where += tmp[0] + " = :" + tmp[0] + " and ";
			params.put(tmp[0], tmp[1]);
		}
		where = where.substring(0, where.length()-5);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("sql", where);
		result.put("params", params);
		return result;
	}

	@Override
	public void saveExcelImport(InputStream is, String className) {
		POIFSFileSystem fs = null;
	    HSSFWorkbook wb = null;
	    HSSFSheet sheet = null;
		try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        sheet = wb.getSheetAt(0);
        HSSFRow head = sheet.getRow(0);
        int cols = head.getPhysicalNumberOfCells();
        List<Object> headList = ExcelReaderUtil.getRowValues(head, cols);
        int rows = sheet.getLastRowNum();
        BeanUtil beanUtil = new BeanUtil(dictService);
        BaseEntity entity = null;
		try {
			entity = (BaseEntity) Class.forName("com.upsoft.system.entity."+className).newInstance();
			for (int i = 1; i <= rows; i++) {
	        	List<Object> dataRow = ExcelReaderUtil.getRowValues(sheet.getRow(i), cols);
	        	entity = beanUtil.getEntity(headList, dataRow, entity.getClass());
	        	save(entity);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
