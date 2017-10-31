/*
 * IndexController.java
 * Created on 2015年3月4日 下午5:52:04
 * Copyright (c) 重庆扬讯软件技术有限公司  All Rights Reserved.
 * http://www.upsoft.com.cn
 *
 * This software is the confidential and proprietary information of UPSoft.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with UPSoft.
 */
package com.upsoft.yxsw.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.upsoft.login.controller.BaseController;
import com.upsoft.login.listener.ConfigListener;
import com.upsoft.login.support.webservice.ServiceReceiver;
import com.upsoft.login.support.webservice.SysUtils;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.constant.CommonConstant;
import com.upsoft.system.entity.SysUser;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 * 
 * 文件名称：IndexController.java<br>
 * 摘要：简要描述本文件的内容<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：蒋迪<br>
 * 完成日期：2015年3月4日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：蒋迪<br>
 * 完成日期：2015年3月4日<br>
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{

	/**
	 * 进入资产管理系统首页，初始化配置文件，获取登录人映射GIS系统机构编码
	 * @date 2016年8月22日 下午1:59:01
	 * @author 胡毅
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/init")
	public String init(HttpServletRequest request, ModelMap map) {
		super.putMapUrl(request, map);
		WSLoginInfoBean loginInfo = (WSLoginInfoBean) SysUtils.getLoginInfo(request);
		map.addAttribute("tokenId", loginInfo.getTokenId());
		map.addAttribute("_platform", request.getParameter("_platform"));//登录用户是否可以跳转到平台首页
		
		//是否有后台管理系统访问权限
		List<Map<String,Object>> _owned_systemcodes = ServiceReceiver.getSystemCodeByUserIdInRole(loginInfo.getUser().getUserId());
		boolean _system_manage_permission = false;
		String UP_SYSTEM_CODE =  CommonConstant.SYSTEMCODE.UP_SYSTEM_CODE;
		List<Map<String,Object>> _owned_pc_systemcodes = new ArrayList<Map<String,Object>>();
		for (Map<String, Object> code : _owned_systemcodes) {
			String systemCode = code.get("systemcode").toString();
			if(StringUtils.equals(systemCode,UP_SYSTEM_CODE)){
				_system_manage_permission = true; 
			}
			// 只查询pc端系统
			String app = CommonConstant.SYSTEMCODE_PREFIX.APP_PREFIX.getValue();
			if (!(String.valueOf(code.get("systemcode")).indexOf(app) >= 0)) {
				_owned_pc_systemcodes.add(code);
			}
		}
		map.addAttribute("_system", _system_manage_permission);
		map.addAttribute("_owned_pc_systemcodes", new Gson().toJson(_owned_pc_systemcodes));

		String REQUEST_IP = loginInfo.getRequestIp();
		if(StringUtils.isBlank(REQUEST_IP)){
			REQUEST_IP = getRequestIp(request);
			loginInfo.setRequestIp(REQUEST_IP);
			loginInfo.setLocalAddress(getLocalAddress(request));
		}
		// 设置各PC系统首页路径
		Properties props = ConfigListener.CasConfig;
		for (Map<String, Object> map2 : _owned_pc_systemcodes) {
			String systemCode = map2.get("systemcode").toString();
			map.addAttribute(systemCode+"_index", props.getProperty("cas.index_init_"+systemCode+"_"+REQUEST_IP));
		}
		// 设置平台首页路径
		map.addAttribute(UP_SYSTEM_CODE+"_platform", props.getProperty("cas.paltform_init_"+UP_SYSTEM_CODE+"_"+REQUEST_IP));
		
		return "/system/layout/index";
	}

	/**
	 * 获取登录用户友情信息
	 * 
	 * @date 2015年3月19日 下午8:50:29
	 * @author TW
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getFriendlyInfo")
	public Map<String, Object> getFriendlyInfo(HttpServletRequest request) {

		Map<String, Object> userInfo = new HashMap<String, Object>();
		WSLoginInfoBean loginInfo = (WSLoginInfoBean)SysUtils.getLoginInfo(request);
		String userName = loginInfo.getUser().getUserName();
		String orgId = loginInfo.getUser().getOrgId();
		if (StringUtils.isNotEmpty(orgId)){
			String orgName = ServiceReceiver.getOrg(orgId).getOrgName();
			userInfo.put("orgName", orgName);
		}
		
		userInfo.put("userName", userName);
		Calendar cal = Calendar.getInstance();
		String[] week = {"一","二","三","四","五","六","日"};
		String today = cal.get(Calendar.YEAR)+"年"+(cal.get(Calendar.MONTH)+1)+"月"+cal.get(Calendar.DAY_OF_MONTH)+"日"+" 星期"+week[cal.get(Calendar.WEEK_OF_MONTH)-1];
		userInfo.put("today", today);
		userInfo.put("tokenId", loginInfo.getTokenId());

		return userInfo;

	}

	/**
	 * 获取主页菜单（主菜单及子菜单）
	 * 
	 * @date 2015年3月19日 下午8:51:36
	 * @author TW
	 * @author hy 修改菜单显示，通过用户权限控制，增加request参数
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getIndexMainFuncList")
	public List<Map<String, Object>> getIndexMainFuncList(HttpServletRequest request) {
		List<Map<String, Object>> menulist = new ArrayList<Map<String, Object>>();
		SysUser user = (SysUser)SysUtils.getLoginSysUser(request);
		menulist = ServiceReceiver.queryMenusBySysCodeAndUserId(user.getUserId(), CommonConstant.SYSTEMCODE.UP_YXSW_CODE);
		return menulist;
	}
}
