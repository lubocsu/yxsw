package com.upsoft.yxsw.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.persistence.Access;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import com.google.gson.Gson;
import com.upsoft.login.controller.BaseController;
import com.upsoft.login.support.webservice.SysUtils;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.util.DateUtil;
import com.upsoft.system.util.MapUtil;
import com.upsoft.yxsw.service.BizTSbBaseinfoService;
import com.upsoft.yxsw.utils.cxmakestrategy.ReportForm;
/**
 * 
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：SbtjController.java<br>
* 摘要：    统计各个厂站的设备类型情况   <br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：杨磊<br>
* 完成日期：2017年10月9日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：杨磊<br>
* 完成日期：2017年10月9日<br>
 */

//@Controller
@RequestMapping(SbtjController.FORWARD_PREFIX)
public class SbtjController extends BaseController {
	protected static final String FORWARD_PREFIX = "/sbtj";
	protected static final String JSP_PR = "/WEB-INF/jsp/sbtj";
	
    private Logger logger=Logger.getLogger(SbSafeController.class);
    @Autowired
    private BizTSbBaseinfoService bizTSbBaseinfoService;

	
	/**  
	 * 获取echart的所需的数据
	 * @date 2017年10月10日 下午4:17:40
	 * @author 杨磊
	 * @param request
	 * @param map
	 * @param reportForm
	 * @return
	 */@Deprecated
//		@RequestMapping("/init")
//		public  String getDate(HttpServletRequest request,ModelMap map){
//			
//		Map<String, Object> pars = new HashMap<String, Object>();
//		String csOrgId = WebUtils.findParameterValue(request, "csOrgId");
//		String sbTypeId = WebUtils.findParameterValue(request, "sbTypeId");
//		String sbStatus = WebUtils.findParameterValue(request, "sbStatus");
//		WSLoginInfoBean user = SysUtils.getLoginInfo(request);
//		String typeId = user.getCsOrgTypeId();
//		String orgName = user.getCsOrgName();
//		if (null == csOrgId || StringUtils.isBlank(csOrgId)) {
//			csOrgId = user.getCsOrgId();
//
//		}
//		pars.put("csOrgId", csOrgId);
//		pars.put("sbTypeId", sbTypeId);
//		pars.put("sbStatus", sbStatus);
//	//	List<Map<String, Object>> pissList = bizTSbBaseinfoService.getdate(
//		//		csOrgId, sbTypeId, sbStatus);
//
//		Map<String, Object> chartMap = new HashMap<String, Object>();
//		List<String> chartLegend = new ArrayList<String>();
//		List<String> chartLegendCount = new ArrayList<String>();
//		chartLegendCount.add("count");
//
//		for (Map<String, Object> map2 : pissList) {
//			String name = (String) map2.get("name");
//
//			chartLegend.add(name);
//		}
//		chartMap = this.getCharts(pissList, chartLegend, chartLegendCount,
//				request);
//		Gson gson = new Gson();
//		String json = gson.toJson(chartMap);
//		map.put("chartMap", json);
//
//		map.put("typeId", typeId);
//		map.put("list", pissList);
//		return JSP_PR + "/main";
//	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, Object> getCharts(List<Map<String, Object>> list,
			List<String> chartLend, List<String> chartLegendCount,
			HttpServletRequest request) {

		Map<String, Object> chartMap = new HashMap<String, Object>();
		List<List<String>> chartDatas = new ArrayList<List<String>>();
		List<String> chartXAxis = new ArrayList<String>();
		List<String> chartXAxisTemp;
		List chartData;
		chartXAxisTemp = new ArrayList<String>();
		WSLoginInfoBean user = SysUtils.getLoginInfo(request);
		String csOrgName = user.getCsOrgName();
		chartXAxisTemp.add(csOrgName);
		chartXAxis = chartXAxisTemp;
		String clend = chartLegendCount.get(0);
		for (Map<String, Object> map : list) {
			chartData = new ArrayList<String>();
			// 从数据库中取出来的有没值
			if (null != map.get(clend)) {
			
				chartData.add(map.get(clend));// 这里要根据count添加数据
			}
			else {
				chartData = new ArrayList<String>();
				chartData.add(0);
			}
			chartDatas.add(chartData);
		}

		List<List<String>> checkChartLend = new ArrayList<List<String>>();
		checkChartLend.add(chartLend);
		// 这里为了使data:chartMap.chartLegend 所以要改造成 List<List<String>>
		// chartMap.put("legend", chartLend);
		chartMap.put("legend", checkChartLend);
		chartMap.put("xAxis", chartXAxis);
		chartMap.put("dates", chartDatas);

		return chartMap;
	}
  
}
