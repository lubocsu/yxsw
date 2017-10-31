/*
 * ViewExcelUtil.java
 * Created on 2015年1月28日 下午5:02:22
 * Copyright (c) 重庆扬讯软件技术有限公司  All Rights Reserved.
 * http://www.upsoft.com.cn
 *
 * This software is the confidential and proprietary information of UPSoft.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with UPSoft.
 */
package com.upsoft.systemweb.util;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.upsoft.system.bean.ExcelHeaderBean;
import com.upsoft.system.bean.ExportExcelBean;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.util.EncodingUtil;
import com.upsoft.systemweb.service.ExportExcelDataSource;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：ExportExcelUtil.java<br>
 * 摘要：导出Excel工具类<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：蒋迪<br>
 * 完成日期：2015年1月28日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：蒋迪<br>
 * 完成日期：2015年1月28日<br>
 */
public class ExportExcelUtil extends AbstractExcelView {

	private static final String DEFAULT_FILE_NAME = "downloadFile.xls";
	private ExportExcelDataSource service;
	private Map<String, Object> params;
	private PageBean pageBean;
	private String fileName;
	private String sheetName;

	public ExportExcelUtil(ExportExcelDataSource service, Map<String, Object> params, PageBean pageBean, Object[] otherParams, String fileName, String sheetName){
		this.service = service;
		this.params = params;
		this.pageBean = pageBean;
		setFileName(fileName);
		this.sheetName = sheetName;
	}

	/**
	 * 设置文件名，针对中文文件进行编码转换
	 * @date 2015年1月29日 上午10:53:00
	 * @author 蒋迪
	 * @param fileName	文件名 
	 */
	private void setFileName(String fileName){
		if (StringUtils.isEmpty(fileName)){
			this.fileName = DEFAULT_FILE_NAME;
		}else if (EncodingUtil.isContainChinese(fileName)){
			try {
				this.fileName = EncodingUtil.encode(fileName, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				this.fileName = DEFAULT_FILE_NAME;
			}
		}else{
			this.fileName = fileName;
		}
	}

	@Override
	protected void buildExcelDocument(Map<String, Object> obj, HSSFWorkbook workbook, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		ExportExcelBean bean = service.getDataSource(params, pageBean);
		HSSFSheet sheet = workbook.createSheet(sheetName);
		sheet.setDefaultColumnWidth(15);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFRow header = sheet.createRow(0);
		createHeaderRow(header, bean, style);
		createDataRow(sheet, bean, style);
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName);
		OutputStream ouputStream = response.getOutputStream();
		workbook.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	/**
	 * 构造excel表头
	 * @date 2015年1月29日 上午10:53:29
	 * @author 蒋迪
	 * @param head
	 * @param bean 
	 */
	private void createHeaderRow(HSSFRow head, ExportExcelBean bean, HSSFCellStyle style){
		List<ExcelHeaderBean> header = bean.getHeader();
		int index = 0;
		for (ExcelHeaderBean excelHeaderBean : header) {
			HSSFCell cell = head.createCell(index++);
			cell.setCellStyle(style);
			cell.setCellValue(excelHeaderBean.getValue());
		}
	}

	/**
	 * 构造excel数据
	 * @date 2015年1月29日 上午10:53:38
	 * @author 蒋迪
	 * @param sheet
	 * @param bean 
	 */
	private void createDataRow(HSSFSheet sheet, ExportExcelBean bean, HSSFCellStyle style){
		List<Map<String, Object>> data = bean.getData();
		List<ExcelHeaderBean> heads = bean.getHeader();
		int rowIndex = 1;
		for (Map<String, Object> map : data) {
			HSSFRow row = sheet.createRow(rowIndex++);
			int cellIndex = 0;
			for (ExcelHeaderBean ehb : heads) {
				if (ehb.getWidth()!=0){
					sheet.setColumnWidth(cellIndex, ehb.getWidth()*256);
				}
				HSSFCell cell = row.createCell(cellIndex++);
				cell.setCellStyle(style);
				cell.setCellValue(map.get(ehb.getLabel())!=null ? map.get(ehb.getLabel()).toString() : "");
			}
		}
	}
}
