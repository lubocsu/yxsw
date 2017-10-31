package com.upsoft.systemweb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.upsoft.login.support.webservice.SysUtils;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.constant.DictConstant;
import com.upsoft.system.constant.SystemLogConstant;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.entity.SystemTLog;
import com.upsoft.system.support.annotation.LogAnnotation;
import com.upsoft.systemweb.service.LogService;
import com.upsoft.systemweb.support.SystemLogServiceCache;

/**
 * 
 * Copyright (c) 2015,重庆扬讯软件技术股份有限公司<br>
 * All rights reserved.<br>
 * 
 * 文件名称：LogController.java<br>
 * 摘要：日志Controller<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：TW<br>
 * 完成日期：2015年2月3日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：TW<br>
 * 完成日期：2015年2月3日<br>
 */
@Controller
@RequestMapping(value = "/log")
public class LogController {

	@Autowired
	private LogService logService;
	@Autowired
	private SystemLogServiceCache systemLogServiceCache;

	/**
	 * 首次进入日志主页面
	 * 
	 * @date 2015年2月3日 下午4:45:58
	 * @author TW
	 * @param request
	 * @param map
	 * @return
	 */
	@LogAnnotation(FunctionName = "日志功能")
	@RequestMapping(value = "/init")
	public String toLogIndex(HttpServletRequest request, ModelMap map) {

		// 预置页面首次进入默认查询参数
		Map<String, Object> pars = new HashMap<String, Object>();
		return getLogList(request, map, pars);

	}

	/**
	 * 跳转到日志列表页面
	 * 
	 * @date 2015年2月3日 下午4:46:25
	 * @author TW
	 * @param request
	 * @param map
	 * @param pars
	 * @return
	 */
	@RequestMapping(value = "/getLogList")
	public String getLogList(HttpServletRequest request, ModelMap map, Map<String, Object> pars) {
		map.put("LOG_OPT_MODEL", DictConstant.LOG_OPT_MODEL.getValue());
		map.put("LOG_OPT_TYPE", DictConstant.LOG_OPT_TYPE.getValue());
		map.put("LOG_BELONG_SYSTEM", DictConstant.LOG_BELONG_SYSTEM.getValue());

		// 添加日志
		String optContent = "查询系统日志";
		SysUser user = SysUtils.getLoginSysUser(request);
		systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(),
				Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_RZGL,
				SystemLogConstant.OPT_MODEL_TYPE_NAME_RZGL, SystemLogConstant.OPT_TYPE_CODE_QUERYINFO, SystemLogConstant.OPT_TYPE_NAME_QUERYINFO,
				optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
		return "/WEB-INF/jsp/log/main";

	}

	/**
	 * 获取日志数据
	 * 
	 * @date 2015年1月23日 下午4:38:16
	 * @author TW
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/getLogData")
	public Map<String, Object> getLogData(HttpServletRequest request, ModelMap map) {

		PageBean pb = new PageBean(request);
		// 获取页面查询参数
		Map<String, Object> pars = new HashMap<String, Object>();
		// 获取操作人
		String optusername = WebUtils.findParameterValue(request, "optusername");
		// 获取操作模块
		String optmodelcode = WebUtils.findParameterValue(request, "optmodelcode");
		// 获取操作类型
		String opttypecode = WebUtils.findParameterValue(request, "opttypecode");
		// 获取所属系统
		String belongsystemcode = WebUtils.findParameterValue(request, "belongsystemcode");
		// 获取开始时间
		String optTimeStart = WebUtils.findParameterValue(request, "optTimeStart");
		// 获取结束时间
		String optTimeEnd = WebUtils.findParameterValue(request, "optTimeEnd");
		if (StringUtils.isNotBlank(optusername)) {
			pars.put("optusername", optusername);
		}
		if (StringUtils.isNotBlank(optmodelcode)) {
			pars.put("optmodelcode", optmodelcode);
		}
		if (StringUtils.isNotBlank(opttypecode)) {
			pars.put("opttypecode", opttypecode);
		}

		if (StringUtils.isNotBlank(belongsystemcode)) {
			pars.put("belongsystemcode", belongsystemcode);
		}
		if (StringUtils.isNotBlank(optTimeStart)) {
			pars.put("optTimeStart", optTimeStart);
		}
		if (StringUtils.isNotBlank(optTimeEnd)) {
			pars.put("optTimeEnd", optTimeEnd);
		}
		// 查询结果
		Map<String, Object> userListAndCount = logService.getLogListAndCount(pars, pb);
		List<Map<String, Object>> list = (List<Map<String, Object>>) userListAndCount.get("rows");
		long count = (Long) userListAndCount.get(PageBean.TOTAL);

		// 组装查询结果
		Map<String, Object> resultData = new HashMap<String, Object>();
		resultData.put("pager.pageNo", 1);
		resultData.put("pager.pageSize", 20);
		resultData.put("pager.totalRows", count);
		resultData.put("rows", list);
		return resultData;
	}

	/**
	 * 查看日志详细信息
	 * 
	 * @date 2015年2月5日 上午10:09:57
	 * @author TW
	 * @param request
	 * @param map
	 * @param logId
	 *            日志Id
	 * @return
	 */
	@RequestMapping("/toViewLog")
	public String toViewUser(HttpServletRequest request, ModelMap map, String logId) {

		// 获取日志信息
		if (logId != null && StringUtils.isNotBlank(logId)) {
			Map<String, Object> pars = new HashMap<String, Object>();
			pars.put("id", logId);
			SystemTLog systemTLog = logService.findOne(SystemTLog.class, logId);
			map.addAttribute("log", systemTLog);
		}

		return "/WEB-INF/jsp/log/viewLog";
	}

}
