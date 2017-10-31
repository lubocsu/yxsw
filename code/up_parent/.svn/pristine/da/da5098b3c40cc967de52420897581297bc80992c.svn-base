package com.upsoft.systemweb.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upsoft.login.controller.BaseController;
import com.upsoft.system.support.annotation.LogAnnotation;
import com.upsoft.systemweb.service.SysLogService;

/**
 * 
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：LogAnalyzeController.java<br>
 * 摘要：日志分析Controller<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：冉恒鑫<br>
 * 完成日期：2015年3月26日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：冉恒鑫<br>
 * 完成日期：2015年3月26日<br>
 */
@Controller
@RequestMapping("/logAnalyze")
public class LogAnalyzeController extends BaseController {

	private static final String JSP_PR = "/WEB-INF/jsp/logAnalyze";
	private static final int HOURS = 3600;

	@Autowired
	private SysLogService sysLogService;

	@RequestMapping("/getSystemName")
	@ResponseBody
	public List<Map<String, Object>> getSystemName(HttpServletRequest request) {
		List<Map<String, Object>> systemNames = sysLogService.getSystemName();
		Map<String, Object> resultMap = null;
		List<Map<String, Object>> tree = new ArrayList<Map<String, Object>>();
		if (systemNames.size() > 0) {
			for (int i = 0; i < systemNames.size(); i++) {
				resultMap = new HashMap<String, Object>();
				resultMap.put("parentId", "0");
				resultMap.put("id", systemNames.get(i).get("systemname"));
				resultMap.put("name", systemNames.get(i).get("systemname"));
				resultMap.put("icon", "../libs/icons/home.gif");
				tree.add(resultMap);
				String systemCode = String.valueOf(systemNames.get(i).get("systemcode"));
				List<Map<String, Object>> functionList = sysLogService.getFunctionBySystemCode(systemCode);
				if (functionList.size() > 0) {
					for (int j = 0; j < functionList.size(); j++) {
						resultMap = new HashMap<String, Object>();
						resultMap.put("parentId", systemNames.get(i).get("systemname"));
						resultMap.put("id", functionList.get(j).get("functionname"));
						resultMap.put("name", functionList.get(j).get("functionname"));
						resultMap.put("icon", "../image/company.png");
						tree.add(resultMap);
					}
				}
			}
		}
		return tree;
	}

	/**
	 * 秒转换成小时以字符串返回
	 * 
	 * @date 2015年3月30日 下午4:04:14
	 * @author 冉恒鑫
	 * @param second
	 * @return
	 */
	private String secondToHours(Long second) {
		String hours = "";
		try {
			if (second == 0) {
				hours = "0";
			} else {
				double s = (double) second / HOURS;
				DecimalFormat df = new DecimalFormat("0.0");
				hours = df.format(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hours + "小时";
	}

	@LogAnnotation(FunctionName = "系统使用情况报表")
	@RequestMapping("/init")
	public String init(HttpServletRequest request) {
		return JSP_PR + "/main";
	}

	@RequestMapping("/updateDwellTimeAndCount")
	@ResponseBody
	public Map<String, Object> updateDwellTimeAndCount(String systemName, String functionName) {
		Map<String, Object> result = sysLogService.getDwellTImeByFunctionName(systemName, functionName);
		Object obj = result.get("dwelltime");
		Long second = Long.valueOf(String.valueOf(obj));
		String hours = secondToHours(second);
		result.remove("dwelltime");
		result.put("hours", hours);
		return result;
	}

	@RequestMapping("/initDatas")
	@ResponseBody
	public Map<String, Object> initDatas(String treeNode) {
		Map<String, Object> result = null;
		try {
			// 获取子系统登录总次数
			result = new HashMap<String, Object>();
			Long loginCount = sysLogService.getLoginCount(treeNode);
			// 获取子系统功能停留总时间
			Long dwellTime = sysLogService.getDwellTime(treeNode);
			// 取得子系统登录次数最多的某天及次数
			List<Map<String, Object>> maxCountTime = sysLogService.getLoginMaxCountAndTime(treeNode);
			// 取得子系统功能使用总次数
			Long functionNameCount = sysLogService.getFunctionNameCount(treeNode);

			result.put("loginCount", loginCount);
			result.put("dewllTime", secondToHours(dwellTime));
			result.put("maxCountTime", maxCountTime.size() > 0 ? maxCountTime.get(0) : null);
			result.put("functionNameCount", functionNameCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 一次性请求多个chart的数据，切换chart的时候也只需要一次请求完成，减少服务器压力，但增加数据流量，过大会影响JS的渲染效率
	 * 
	 * @date 2015年4月24日 下午1:26:51
	 * @author 冉恒鑫
	 * @param treeNode
	 * @return
	 */
	@RequestMapping("/initCharts")
	@ResponseBody
	public Map<String, Object> initCharts(String treeNode) {
		if (StringUtils.isBlank(treeNode))
			return null;
		Map<String, Object> chartMap = null;
		try {
			chartMap = new HashMap<String, Object>();
			Map<String, Object> columnMap = new HashMap<String, Object>();
			List<Map<String, Object>> columnList = sysLogService.initColumn(treeNode);
			List<Map<String, Object>> columnDatas = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = null;
			for (int i = 0; i < columnList.size(); i++) {
				map = new HashMap<String, Object>();
				map.put("label", columnList.get(i).get("functionname"));
				map.put("value", columnList.get(i).get("count"));
				columnDatas.add(map);
			}
			Map<String, Object> columnSummary = new HashMap<String, Object>();
			columnSummary.put("xaxisname", "功能点");
			columnSummary.put("numberSuffix", "次");
			columnSummary.put("bgColor", "#d8ebf2");
			columnSummary.put("canvasBgAlpha", 0);
			columnSummary.put("showBorder", 0);
			columnMap.put("chart", columnSummary);
			columnMap.put("data", columnDatas);
			chartMap.put("columnChart", columnMap); // 到这里为止组装了第一个column chart

			List<Map<String, Object>> pieList = sysLogService.initPie(treeNode);
			Map<String, Object> pieMap = new HashMap<String, Object>();
			List<Map<String, Object>> pieDatas = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < pieList.size(); i++) {
				map = new HashMap<String, Object>();
				map.put("label", pieList.get(i).get("functionname"));
				map.put("value", pieList.get(i).get("dwelltime"));
				pieDatas.add(map);
			}
			Map<String, Object> pieSummary = new HashMap<String, Object>();
			pieSummary.put("showpercentvalues", 1);
			pieSummary.put("canvasBgAlpha", 0);
			pieSummary.put("showBorder", 0);
			pieSummary.put("bgColor", "#d8ebf2");
			pieMap.put("chart", pieSummary);
			pieMap.put("data", pieDatas);
			chartMap.put("pieChart", pieMap); // 到这里位置组装了第二个pie chart

			List<Map<String, Object>> lineList = sysLogService.initLine(treeNode);
			Map<String, Object> lineMap = new HashMap<String, Object>();
			List<Map<String, Object>> lineDatas = new ArrayList<Map<String, Object>>();
			for (int i = lineList.size(); i > 0; i--) {
				map = new HashMap<String, Object>();
				map.put("label", lineList.get(i - 1).get("logtime"));
				map.put("value", lineList.get(i - 1).get("logincount"));
				lineDatas.add(map);
			}
			Map<String, Object> lineSummary = new HashMap<String, Object>();
			lineSummary.put("caption", treeNode);
			lineSummary.put("xaxisname", "月份");
			lineSummary.put("yaxisname", "次数");
			lineSummary.put("numberSuffix", "次");
			lineSummary.put("canvasBgAlpha", 0);
			lineSummary.put("showvalues", 0);
			lineSummary.put("showBorder", 0);
			lineSummary.put("bgColor", "#d8ebf2");
			lineMap.put("chart", lineSummary);
			lineMap.put("data", lineDatas);
			chartMap.put("lineChart", lineMap); // 组装了第三个line chart
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return chartMap;
	}

	@RequestMapping("/initColumn")
	@ResponseBody
	public Map<String, Object> initColumn(String treeNode) {
		Map<String, Object> JsonResult = new HashMap<String, Object>();
		List<Map<String, Object>> queryList = sysLogService.initColumn(treeNode);
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		for (int i = 0; i < queryList.size(); i++) {
			map = new HashMap<String, Object>();
			map.put("label", queryList.get(i).get("functionname"));
			map.put("value", queryList.get(i).get("count"));
			datas.add(map);
		}
		Map<String, Object> chartSummary = new HashMap<String, Object>();
		chartSummary.put("xaxisname", "功能点");
		chartSummary.put("numberSuffix", "次");
		chartSummary.put("bgColor", "#d8ebf2");
		chartSummary.put("canvasBgAlpha", 0);
		chartSummary.put("showBorder", 0);
		// chartSummary.put("showPlotBorder", 1); 显示柱子边框
		// chartSummary.put("plotBorderColor", "#ff0000"); 柱子边框颜色
		JsonResult.put("chart", chartSummary);
		JsonResult.put("data", datas);
		Map<String, Object> columnMap = new HashMap<String, Object>();
		columnMap.put("column", JsonResult);
		return JsonResult;
	}

	@RequestMapping("/initPie")
	@ResponseBody
	public Map<String, Object> initPie(String treeNode) {
		List<Map<String, Object>> queryList = sysLogService.initPie(treeNode);
		Map<String, Object> JsonResult = new HashMap<String, Object>();
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		for (int i = 0; i < queryList.size(); i++) {
			map = new HashMap<String, Object>();
			map.put("label", queryList.get(i).get("functionname"));
			map.put("value", queryList.get(i).get("dwelltime"));
			datas.add(map);
		}
		Map<String, Object> chartSummary = new HashMap<String, Object>();
		chartSummary.put("showpercentvalues", 1);
		chartSummary.put("canvasBgAlpha", 0);
		chartSummary.put("showBorder", 0);
		chartSummary.put("bgColor", "#d8ebf2");
		JsonResult.put("chart", chartSummary);
		JsonResult.put("data", datas);
		return JsonResult;
	}

	@RequestMapping("initLine")
	@ResponseBody
	public Map<String, Object> initLine(String treeNode) {
		List<Map<String, Object>> queryList = sysLogService.initLine(treeNode);
		Map<String, Object> JsonResult = new HashMap<String, Object>();
		Map<String, Object> data = null;
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		for (int i = queryList.size(); i > 0; i--) {
			data = new HashMap<String, Object>();
			data.put("label", queryList.get(i - 1).get("logtime"));
			data.put("value", queryList.get(i - 1).get("logincount"));
			datas.add(data);
		}
		Map<String, Object> chartSummary = new HashMap<String, Object>();
		chartSummary.put("caption", treeNode);
		chartSummary.put("xaxisname", "月份");
		chartSummary.put("yaxisname", "次数");
		chartSummary.put("numberSuffix", "次");
		chartSummary.put("canvasBgAlpha", 0);
		chartSummary.put("showvalues", 0);
		chartSummary.put("showBorder", 0);
		chartSummary.put("bgColor", "#d8ebf2");
		JsonResult.put("chart", chartSummary);
		JsonResult.put("data", datas);
		return JsonResult;
	}

}
