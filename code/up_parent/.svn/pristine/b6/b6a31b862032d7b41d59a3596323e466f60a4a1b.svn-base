package com.upsoft.systemweb.controller;

import java.text.MessageFormat;
import java.util.HashMap;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.google.gson.Gson;
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
* 摘要：框架首页<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：吴炫<br>
* 完成日期：2015年1月22日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：吴炫<br>
* 完成日期：2015年1月22日<br>
 */
@Controller
@RequestMapping(IndexController.FORWARD_PREFIX)
public class IndexController {

	public static final String FORWARD_PREFIX = "/index";
	private static final String JSP_PREFIX = "/system/layout";
	
	/**
	 * 跳转到系统平台首页
	 * @date 2015年1月22日 下午2:44:48
	 * @author 吴炫
	 * @param message
	 * @param map
	 * @return
	 */
	@RequestMapping("/toIndex")
	public String toIndex(	@RequestParam(value="tokenId",required=false)String tokenId,
							@RequestParam(value="message",required=false)String message, 
							ModelMap map,HttpServletRequest request){
		
		if(StringUtils.isNoneBlank(message)) map.addAttribute("message", message);
		if(StringUtils.isNoneBlank(tokenId)) map.addAttribute("tokenId", tokenId);
		//是否可以访问平台首页 2016.09.07
		map.addAttribute("_platform", WebUtils.findParameterValue(request,"_platform"));
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		List<Map<String,Object>> _owned_systemcodes = ServiceReceiver.getSystemCodeByUserIdInRole(loginInfo.getUser().getUserId());
		for (Iterator<Map<String, Object>> iterator = _owned_systemcodes.iterator(); iterator.hasNext();) {
			// 只查询pc端系统
			String app = CommonConstant.SYSTEMCODE_PREFIX.APP_PREFIX.getValue();
			if (String.valueOf(iterator.next().get("systemcode")).indexOf(app) >= 0) {
				iterator.remove();
			}
		}
		map.addAttribute("_owned_pc_systemcodes", new Gson().toJson(_owned_systemcodes));
		
		String REQUEST_IP = loginInfo.getRequestIp();
		if(REQUEST_IP == null){
			REQUEST_IP = getRequestIp(request);
			loginInfo.setRequestIp(REQUEST_IP);
		}
		Properties props = ConfigListener.CasConfig;
		for (Map<String, Object> map2 : _owned_systemcodes) {
			String systemCode = map2.get("systemcode").toString();
			map.addAttribute(systemCode+"_index", props.getProperty("cas.index_init_"+systemCode+"_"+REQUEST_IP));
		}
		// 设置平台首页路径
		map.addAttribute(CommonConstant.SYSTEMCODE.UP_SYSTEM_CODE+"_platform",  props.getProperty("cas.paltform_init_"+CommonConstant.SYSTEMCODE.UP_SYSTEM_CODE+"_"+REQUEST_IP));
		return JSP_PREFIX.concat("/main");
	}
	
	/**
	 * 系统首页左侧tab菜单
	 * @date 2015年1月22日 下午3:34:42
	 * @author 吴炫
	 * @param map
	 * @return
	 */
	@RequestMapping("/toLeft")
	public String toLeft(HttpServletRequest request, ModelMap map, RedirectAttributes redirectAttrs){
		SysUser user = SysUtils.getLoginSysUser(request);
		// 获取菜单显示级别信息(1:显示，0：不显示)
		String sysCode = request.getContextPath().substring(1);
		String type = (String)ConfigListener.CommConfig.get(sysCode.concat("_menu_type"));
		if(StringUtils.equals("1", type)){
			String sysMenus = ServiceReceiver.querySysCodeAndNameByUserId(user.getUserId());
			map.addAttribute("tabs", sysMenus);
		}else{
			map.addAttribute("systemCode", request.getContextPath().substring(1));
		}
		//菜单显示方式：Tree树方式、Accordion手风琴方式
		String menuTypeMode = WebUtils.findParameterValue(request, "menuTypeMode");
		map.addAttribute("menuTypeMode", StringUtils.isNoneBlank(menuTypeMode)?menuTypeMode:"Tree");
		return JSP_PREFIX.concat("/left");
	}
	
	@RequestMapping("/initMenuTree")
	public String initMenuTree(HttpServletRequest request, ModelMap map){
		String syscode = WebUtils.findParameterValue(request,"syscode");
		String selMenuType = WebUtils.findParameterValue(request,"selMenuType");
		String path=request.getContextPath();
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		String userId = loginInfo.getUser().getUserId();
		List<Map<String, Object>> menus = null;
		if(StringUtils.equals(selMenuType,"Accordion")){
			menus = ServiceReceiver.queryAccordionMenusByCondition(syscode, userId);
			map.addAttribute("menus", menus);
		}else{
			menus = ServiceReceiver.queryMenusBySysCodeAndUserId(userId, syscode);
			String url = null;
			for(Map<String, Object> menu : menus){
				menu.put("isParent", menu.get("isparent"));
				menu.put("parentId", menu.get("parentid"));
				menu.put("icon", path+menu.get("icon"));
				url = (String)menu.get("url");
//				if("9".equals(menu.get("operatetype"))&&StringUtils.isNoneBlank(url)){
				if(StringUtils.isNoneBlank(url)){
					menu.put("url", MessageFormat.format(url, menu.get("permissionno"), loginInfo.getTokenId()));
				}
			}
			Gson gson = new Gson();
			map.addAttribute("menus", gson.toJson(menus));
		}
		return JSP_PREFIX.concat("/left").concat(selMenuType);
	}
	
	/**
	 * 获取子菜单
	 * @date 2015年1月27日 下午3:02:47
	 * @author 吴炫
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/findLeafMenu")
	@ResponseBody
	public List<Map<String, Object>> findLeafMenu(HttpServletRequest request, ModelMap map){
		String menuId = WebUtils.findParameterValue(request,"id");
		SysUser user = SysUtils.getLoginSysUser(request);
		List<Map<String, Object>> menus = ServiceReceiver.queryMenusByMenuIdAndUserId(menuId, user.getUserId());
		String url = null;
		for(Map<String, Object> menu : menus){
			menu.put("isParent", menu.get("isparent"));
			menu.put("parentId", menu.get("parentid"));
			url = (String)menu.get("url");
			if("9".equals(menu.get("operatetype"))&&StringUtils.isNoneBlank(url)){
				menu.put("url", MessageFormat.format(url, menu.get("permissionno")));
			}
		}
		return menus;
	}
	/**
	 * 系统首页右侧打开界面
	 * @date 2015年1月22日 下午3:35:02
	 * @author 吴炫
	 * @param map
	 * @return
	 */
	@RequestMapping("/toOpen")
	public String toOpen(ModelMap map){
		return JSP_PREFIX.concat("/open");
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
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		String userName = loginInfo.getUser().getUserName();
		String orgId = loginInfo.getUser().getOrgId();
		if (StringUtils.isNotEmpty(orgId)){
			String orgName = ServiceReceiver.getOrg(orgId).getOrgName();
			userInfo.put("orgName", orgName);
		}
		
		userInfo.put("userName", userName);
		userInfo.put("tokenId", loginInfo.getTokenId());
		return userInfo;

	}
	
	/**
	 * 获取请求ip
	 * 
	 * @date 2017年4月20日11:12:19
	 * @author 刘志华
	 * @param request
	 * @return
	 */
	private String getRequestIp(HttpServletRequest request) {
		return request.getServerName();
	}
}
