/*
 * ExcelReader.java
 * Created on 2015年1月29日 下午2:51:26
 * Copyright (c) 重庆扬讯软件技术有限公司  All Rights Reserved.
 * http://www.upsoft.com.cn
 *
 * This software is the confidential and proprietary information of UPSoft.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with UPSoft.
 */
package com.upsoft.system.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：ExcelReader.java<br>
 * 摘要：读取Excel工具类<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：蒋迪<br>
 * 完成日期：2015年1月29日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：蒋迪<br>
 * 完成日期：2015年1月29日<br>
 */
public class ExcelReaderUtil {
	
	/**
	 * 读取Excel行数据
	 * @date 2015年1月30日 下午2:48:59
	 * @author 蒋迪
	 * @param row	Excel行
	 * @param cols	列数
	 * @return 
	 */
	public static List<Object> getRowValues(HSSFRow row, int cols){
		List<Object> result = new ArrayList<Object>();
		for (int i = 0; i < cols; i++) {
			HSSFCell cell = row.getCell(i);
			if (cell!=null){
				result.add(getCellValue(cell));
			}else{
				result.add("");
			}
		}
		return result;
	}

	/**
	 * 读取Cell中对应格式的数据
	 * @date 2015年1月30日 下午2:48:32
	 * @author 蒋迪
	 * @param cell
	 * @return 
	 */
	private static Object getCellValue(HSSFCell cell){
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		case HSSFCell.CELL_TYPE_NUMERIC:
			return cell.getNumericCellValue();
		}
		return null;
	}
}
