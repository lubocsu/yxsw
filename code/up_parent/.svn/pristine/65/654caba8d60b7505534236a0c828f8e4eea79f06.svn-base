/*
 * CommonController.java
 * Created on 2015年1月27日 上午11:48:57
 * Copyright (c) 重庆扬讯软件技术有限公司  All Rights Reserved.
 * http://www.upsoft.com.cn
 *
 * This software is the confidential and proprietary information of UPSoft.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with UPSoft.
 */
package com.upsoft.systemweb.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.upsoft.login.controller.BaseController;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.service.CommonService;
import com.upsoft.systemweb.service.ExportExcelDataSource;
import com.upsoft.systemweb.util.ExportExcelUtil;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：CommonController.java<br>
 * 摘要：公用Controller<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：蒋迪<br>
 * 完成日期：2015年1月27日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：蒋迪<br>
 * 完成日期：2015年1月27日<br>
 */
@Controller
@RequestMapping("/common")
public class CommonController extends BaseController{

	@Autowired
	private CommonService service;
	
	/**
	 * 获取支持数据
	 * @date 2015年1月28日 上午9:19:03
	 * @author 蒋迪
	 * @param request
	 * @return 
	 */
	@RequestMapping(value = "/getSupportData")
	@ResponseBody
	public List<Map<String, Object>> getSelectSupport(HttpServletRequest request){
		String dataId = request.getParameter("dataId");
		if (StringUtils.isEmpty(dataId)) return null;
		String condition = request.getParameter("condition");
		String iconPath = request.getParameter("icon");
		return service.getSupportDataSource(dataId, condition, iconPath);
	}
	
	/**
	 * 导出Excel
	 * @date 2015年1月30日 上午10:27:37
	 * @author 蒋迪
	 * @param model
	 * @param request
	 * @param serviceName	业务实现类bean名称
	 * @param fileName		导出Excel文件名
	 * @param sheetName		导出Excel Sheet名
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/exportExcel")
	public ModelAndView exportExcel(ModelMap model, HttpServletRequest request,
			@RequestParam(value = "serviceName", required = true) String serviceName,
			@RequestParam(value = "fileName", required = false, defaultValue = "downloadExcel.xls") String fileName,
			@RequestParam(value = "sheetName", required = false, defaultValue = "Sheet1") String sheetName){
		PageBean bean = new PageBean(request);
		Map<String, String[]> oldParams = request.getParameterMap();
		Map<String, Object> params = new HashMap<String, Object>();
		for (String key : oldParams.keySet()) {
			if (!key.equals("serviceName")
					&&!key.equals("fileName")
					&&!key.equals("sheetName")
					&&!key.equals(PageBean.PAGE_INDEX)
					&&!key.equals(PageBean.PAGE_SIZE)
					&&!key.equals(PageBean.SORT_NAME)
					&&!key.equals(PageBean.SORT_ORDER)
					&&!key.equals(PageBean.TOTAL)){
				
				params.put(key, oldParams.get(key)[0]);	
			}
		}
		ExportExcelDataSource exportExcelDataSourceService = getService(serviceName, request.getSession().getServletContext());
		ExportExcelUtil util = new ExportExcelUtil(exportExcelDataSourceService, params, bean, null, fileName, sheetName);
		return new ModelAndView(util, model);
	}
	
	/**
	 * 跳转到文件上传页面
	 * @date 2015年2月2日 上午11:57:13
	 * @author 胡毅
	 * @return
	 */
	@RequestMapping(value = "/toImportExcelPage")
	public String toImportExcelPage(String templateName,
			@RequestParam(value = "entityName", required = true) String entityName,ModelMap map){
		map.put("templateName", templateName);
		map.put("entityName", entityName);
		return "/uploadFiles/uploadFile";
	}
	
	/**
	 * 通用Excel导入功能
	 * @date 2015年1月30日 下午2:47:58
	 * @author 蒋迪
	 * @param request
	 * @param entityName	实体名（大小写敏感，如SysOrg）
	 * @return 
	 */
	@RequestMapping(value = "/importExcel")
	public String importExcel(MultipartHttpServletRequest request,
			@RequestParam(value = "entityName", required = true) String entityName){
		if (request.getFileNames().hasNext()){
			try {
				service.saveExcelImport(request.getFile(request.getFileNames().next()).getInputStream(), entityName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	/**
	 * 通过serviceName，获取实现了IExportExcelDataSource接口的实现bean
	 * @date 2015年1月28日 下午4:33:24
	 * @author 蒋迪
	 * @param serviceName	实现service名称
	 * @param sc			ServletContext
	 * @return 
	 */
	private ExportExcelDataSource getService(String serviceName, ServletContext sc){
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(sc);
		return (ExportExcelDataSource)context.getBean(serviceName);
	}
}
