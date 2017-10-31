package com.upsoft.yxsw.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hdgf.chunks.ChunkTrailer;
import org.apache.poi.ss.usermodel.charts.ChartData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.google.gson.Gson;
import com.sun.istack.logging.Logger;
import com.upsoft.login.controller.BaseController;
import com.upsoft.login.support.webservice.SysUtils;
import com.upsoft.system.bean.ResultBean;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.constant.CommonConstant;
import com.upsoft.system.util.DateUtil;
import com.upsoft.system.util.MapUtil;
import com.upsoft.yxsw.service.BizTXjCxTaskService;
import com.upsoft.yxsw.utils.cxmakestrategy.ReportForm;


/**
 * 
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：PissController.java<br>
* 摘要：人员巡检情况统计<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：杨磊<br>
* 完成日期：2017年9月20日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：杨磊<br>
* 完成日期：2017年9月20日<br>
 */


@Controller
@RequestMapping(PissController.FORWARD_PREFIX)
public class PissController extends BaseController {
	protected static final String FORWARD_PREFIX = "/piss";
	protected static final String JSP_PR = "/WEB-INF/jsp/piss";
	protected Logger logger = Logger.getLogger(PissController.class);
	@Autowired
	private BizTXjCxTaskService  bizTXjCxTaskService;//产寻记录

	//@RequestMapping("/init")
	public String init() {

		return JSP_PR + "/main";
	}
	/**
	 * 渲染echart 图的所需要的数据，并把所需要的数据传递到mian.jsp
	 * @date 2017年9月23日 下午3:20:52
	 * @author 杨磊
	 * @param request
	 * @param map
	 * @param reportForm
	 * @return
	 */
	@RequestMapping("/init")
	public  String getPissDate(HttpServletRequest request,ModelMap map,ReportForm reportForm){
		
		WSLoginInfoBean user = SysUtils.getLoginInfo(request);
		String csOrgId = user.getCsOrgId();
		String typeId = user.getCsOrgTypeId();
		String orgName = user.getCsOrgName();

		String startTime = reportForm.getStartDate();
		String endTime = reportForm.getEndDate();
		
		List<Map<String, Object>> pissList = bizTXjCxTaskService.getdate(csOrgId, startTime, endTime);

		if (true) {
			Map<String, Object> chartMap = new HashMap<String, Object>();
			List<String> chartLegend = new ArrayList<String>();
			chartLegend.add("alls");// 任务总量
			chartLegend.add("completes");// 完成量
			chartLegend.add("rate");// 按时完成率
			chartMap = this.getCharts(pissList, chartLegend);
			Gson gson = new Gson();
			map.put("chartMap", gson.toJson(chartMap));

		}
        
        map.put("ReportForm", reportForm);
        map.put("typeId", typeId);
        map.put("list",pissList );
        map.put("st", DateUtil.stringToString(startTime, DateUtil.DATE_FORMAT_yyyyMMdd, DateUtil.DATE_FORMAT_WITHOUT_TIME));
		map.put("et", DateUtil.stringToString(endTime, DateUtil.DATE_FORMAT_yyyyMMdd, DateUtil.DATE_FORMAT_WITHOUT_TIME));
		return JSP_PR +"/main";
	}
	/**
	 * 拼装数据是使其满足echart的格式
	 * @date 2017年9月23日 下午3:20:08
	 * @author 杨磊
	 * @param list
	 * @param chartLend
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String,Object>  getCharts(List<Map<String,Object>> list,List<String> chartLend){
		
		Map<String, Object> chartMap = new HashMap<String, Object>();
		List<List<String>> chartDatas = new ArrayList<List<String>>();
		List<String> chartXAxis = new ArrayList<String>();
		List<String> chartXAxisTemp;
		List chartData;
		chartXAxisTemp = new ArrayList<String>();
		for (Map<String, Object> map : list) {
			if (MapUtil.hasParam(map, "belong_wsc_name")) {
				chartXAxisTemp.add(map.get("belong_wsc_name").toString());
			}
		}
		chartXAxis = chartXAxisTemp;

		for (String clend : chartLend) {
			chartData = new ArrayList<String>();
			for (Map<String, Object> map : list) {
				// 从数据库中取出来的有没值
				if (null != map.get(clend)) {
					chartData.add(map.get(clend));
				} else {
					chartData.add(0);
				}

			}
			chartDatas.add(chartData);

		}

		chartMap.put("legend", chartLend);
		chartMap.put("xAxis", chartXAxis);
		chartMap.put("dates", chartDatas);

		return chartMap;
	}
	/**
	 * 钻取厂所人员的详细信息
	 * @date 2017年9月23日 下午3:19:19
	 * @author 杨磊
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/getPerson")
	public String getPerson(HttpServletRequest request,ModelMap map){
		Map<String, Object> pars = new HashMap<String, Object>();
		String csId = WebUtils.findParameterValue(request, "csId");
		String startTime = WebUtils.findParameterValue(request, "startTime");
		String endTime = WebUtils.findParameterValue(request, "endTime");

		pars.put("csId", csId);

		String startTime1 = DateUtil.stringToString(startTime, DateUtil.DATE_FORMAT_WITHOUT_TIME, DateUtil.DATE_FORMAT_yyyyMMdd);
		String endTime1 = DateUtil.stringToString(endTime, DateUtil.DATE_FORMAT_WITHOUT_TIME, DateUtil.DATE_FORMAT_yyyyMMdd);
		pars.put("startTime", startTime1);
		pars.put("endTime", endTime1);
		List<Map<String, Object>> listPerson = bizTXjCxTaskService.getPerson(pars);
		map.put("list", listPerson);
		return JSP_PR + "/peopledetail";
	}
	/**
	 * 获取按钮的时间
	 * @date 2017年9月23日 下午3:18:53
	 * @author 杨磊
	 * @param request
	 * @param dateType
	 * @return
	 */
	
	@RequestMapping("/getDateByType")
	@ResponseBody
	public ResultBean getDateByType(HttpServletRequest request, int dateType){
		
		ResultBean result = new ResultBean();
		ReportForm reportForm = new ReportForm();
		Map<String, Object> times = new HashMap<String, Object>();
		
		reportForm.setDateType(dateType);//默认为本周的
		times.put("startDate", DateUtil.stringToString(reportForm.getStartDate(), DateUtil.DATE_FORMAT_yyyyMMdd, DateUtil.DATE_FORMAT_WITHOUT_TIME));
		times.put("endDate", DateUtil.stringToString(reportForm.getEndDate(), DateUtil.DATE_FORMAT_yyyyMMdd, DateUtil.DATE_FORMAT_WITHOUT_TIME));
		//result.setFlag(true);
		result.setData(times);
		
		return result;
	}
}
