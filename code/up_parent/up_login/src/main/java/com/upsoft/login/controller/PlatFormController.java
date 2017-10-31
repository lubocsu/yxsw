package com.upsoft.login.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.upsoft.login.listener.ConfigListener;
import com.upsoft.login.support.webservice.ServiceReceiver;
import com.upsoft.login.support.webservice.SysUtils;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.constant.CommonConstant;

/**
 * Copyright (c) 2016,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：PlatFormController.java<br>
 * 摘要：平台首页<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.0.0<br>
 * 作者：chengcong<br>
 * 完成日期：2015年1月22日<br>
 * -------------------------------------------------------<br>
 */
@Controller
@RequestMapping(PlatFormController.FORWARD_PREFIX)
public class PlatFormController extends BaseController {

	public static final String FORWARD_PREFIX = "/platform";
	private static final String JSP_PREFIX = "/system/platform";

	/**
	 * 跳转到系统平台首页
	 * 
	 * @date 2016年8月2日 下午2:44:48
	 * @author chengcong
	 * @param message
	 * @param map
	 * @return
	 */
	@RequestMapping("/toIndex")
	public String toIndex(@RequestParam(value = "tokenId", required = false) String tokenId,
						  @RequestParam(value = "message", required = false) String message,
			ModelMap map, HttpServletRequest request) {
		if (StringUtils.isNoneBlank(message)) map.addAttribute("message", message);
		if (StringUtils.isNoneBlank(tokenId)) map.addAttribute("tokenId", tokenId);

		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		map.addAttribute("userName", loginInfo.getUser().getUserName());
//		map.addAttribute("userType", loginInfo.getUser().getUserType());
		String orgId = loginInfo.getUser().getOrgId();
		if (StringUtils.isNotEmpty(orgId)) {
			String orgName = ServiceReceiver.getOrg(orgId).getOrgName();
			map.addAttribute("orgName", orgName);
		}

		// 读取当前用户用户有的系统角色，遍历出有权限查看的系统
		List<Map<String, Object>> _owned_systemcodes = ServiceReceiver.getSystemCodeByUserIdInRole(loginInfo.getUser().getUserId());
		boolean _system_manage_permission = false;
		Map<String, Object> _sys_code_map = null;
		String UP_SYSTEM_CODE =  CommonConstant.SYSTEMCODE.UP_SYSTEM_CODE;
		for (Iterator<Map<String, Object>> iterator = _owned_systemcodes.iterator(); iterator.hasNext();) {
			Map<String, Object> code = (Map<String, Object>) iterator.next();
			if (StringUtils.contains(code.get("systemcode").toString(), "systemweb")) {
				_system_manage_permission = true;
				_sys_code_map = code;
			}
			// 只查询pc端系统
			String app = CommonConstant.SYSTEMCODE_PREFIX.APP_PREFIX.getValue();
			if (String.valueOf(code.get("systemcode")).indexOf(app) >= 0) {
				iterator.remove();
			}
		}
		map.addAttribute("_system", _system_manage_permission);
		map.addAttribute("_owned_pc_systemcodes", new Gson().toJson(_owned_systemcodes));
		String REQUEST_IP = loginInfo.getRequestIp();
		if (REQUEST_IP == null) {
			REQUEST_IP = getRequestIp(request);
			loginInfo.setRequestIp(REQUEST_IP);
			loginInfo.setLocalAddress(getLocalAddress(request));
		}
		Properties props = ConfigListener.CasConfig;
		for (Map<String, Object> map2 : _owned_systemcodes) {
			String systemCode = map2.get("systemcode").toString();
			map.addAttribute(systemCode+"_index", props.getProperty("cas.index_init_"+systemCode+"_"+REQUEST_IP));
		}
		// 设置平台首页路径
		map.addAttribute(UP_SYSTEM_CODE+"_platform", props.getProperty("cas.paltform_init_"+UP_SYSTEM_CODE+"_"+REQUEST_IP));
		// 设置PC子系统模块
		if (null != _sys_code_map) {
			_owned_systemcodes.remove(_sys_code_map);
		}
		map.addAttribute("_owned_pc_systemcodes_el", _owned_systemcodes);
		
		return JSP_PREFIX.concat("/index");
	}

	/**
	 * 获取字典支持数据
	 * 
	 * @date 2017年9月8日 下午3:17:29
	 * @author 陈涛
	 * @param request
	 * @return 
	 */
	@RequestMapping(value = "/getSupportData")
	@ResponseBody
	public List<Map<String, Object>> getSelectSupport(HttpServletRequest request){
		String parentNodeId = request.getParameter("parentNodeId");
		if (StringUtils.isEmpty(parentNodeId)) return null;
		return ServiceReceiver.getDictSelect(parentNodeId);
	}
}
