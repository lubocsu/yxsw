package com.upsoft.systemweb.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import com.upsoft.login.controller.BaseController;
import com.upsoft.login.support.webservice.SysUtils;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.constant.SystemLogConstant;
import com.upsoft.system.entity.SysMessageEntity;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.support.annotation.LogAnnotation;
import com.upsoft.system.util.DateUtil;
import com.upsoft.systemweb.service.MessageService;
import com.upsoft.systemweb.support.SystemLogServiceCache;

/**
 * 
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 * 
 * 文件名称：MessageController.java<br>
 * 摘要：系统消息控制器<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.0.0<br>
 * 作者：冉恒鑫<br>
 * 完成日期：2015年3月18日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.0.0<br>
 * 原作者：冉恒鑫<br>
 * 完成日期：2015年3月18日<br>
 */
@Controller
@RequestMapping("/message")
public class MessageControll extends BaseController {

	private static final String JSP_PR = "/WEB-INF/jsp/message";

	@Autowired
	private MessageService messageService;

	@Autowired
	private SystemLogServiceCache systemLogServiceCache;

	/**
	 * 进入系统消息主页面
	 * 
	 * @date 2015年3月17日 晚上21:22:33
	 * @author RHX
	 */
	@LogAnnotation(FunctionName = "系统消息功能")
	@RequestMapping("/init")
	public String init(HttpServletRequest request, ModelMap map) {
		// 初始化查询时间
		request.setAttribute("initStartTime", initTime("start"));
		request.setAttribute("initEndTime", initTime("end"));
		// 添加日志
		String optContent = "查询系统消息";
		SysUser user = SysUtils.getLoginSysUser(request);
		systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(),
				Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_XTXX,
				SystemLogConstant.OPT_MODEL_TYPE_NAME_XTXX, SystemLogConstant.OPT_TYPE_CODE_QUERYINFO, SystemLogConstant.OPT_TYPE_NAME_QUERYINFO,
				optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
		return JSP_PR + "/main";
	}

	/**
	 * QuiGrid通用列表数据获取
	 * 
	 * @date 2015年3月20日 下午2:02:12
	 * @author 冉恒鑫
	 * @param request
	 * @return
	 */
	@RequestMapping("/getGridMessageData")
	@ResponseBody
	public Map<String, Object> getGridMessageData(HttpServletRequest request) {
		PageBean pb = new PageBean(request);
		Map<String, Object> pars = new HashMap<String, Object>();
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String status = request.getParameter("status");
		String senderName = request.getParameter("senderName");
		if (StringUtils.isBlank(startTime)) {
			pars.put("startTime", DateUtil.stringToDate(initTime("start") + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
		}
		if (StringUtils.isBlank(endTime)) {
			pars.put("endTime", DateUtil.stringToDate(initTime("end") + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
		}
		if (StringUtils.isNotBlank(senderName)) {
			senderName = "%" + senderName + "%";
			pars.put("senderName", senderName);
		}
		if (StringUtils.isNotBlank(startTime)) {
			pars.put("startTime", DateUtil.stringToDate(startTime + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
		}
		if (StringUtils.isNotBlank(endTime)) {
			pars.put("endTime", DateUtil.stringToDate(endTime + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
		}
		if (!("0").equals(status) & StringUtils.isNotBlank(status)) {
			pars.put("status", status);
		}
		// 公用分页排序方法
		return messageService.queryPagination(pb, pars);
	}

	/**
	 * 获取用户列表给多选下拉框
	 * 
	 * @date 2015年3月20日 下午2:01:05
	 * @author 冉恒鑫
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getUserList")
	@ResponseBody
	public List<Map<String, Object>> getUserList(HttpServletRequest request) {
		// 组装JSON数据
		Map<String, Object> userTree = null;
		List<Map<String, Object>> userList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> userNameList = messageService.getUserList();
		for (int i = 0; i < userNameList.size(); i++) {
			userTree = new HashMap<String, Object>();
			userTree.put("id", userNameList.get(i).get("userid"));
			userTree.put("parentId", 0);
			userTree.put("name", userNameList.get(i).get("username"));
			userList.add(userTree);
		}
		return userList;
	}

	/**
	 * 私有方法为时间框组件提供初始值
	 * 
	 * @date 2015年3月20日 下午2:01:42
	 * @author 冉恒鑫
	 * @param mark
	 * @return
	 */
	private String initTime(String mark) {
		Date date = new Date();
		if (mark.equals("start")) {
			Calendar calendar = Calendar.getInstance();// 日历对象
			calendar.setTime(date);// 设置当前日期
			calendar.add(Calendar.MONTH, -1);// 当前月份减1
			return DateUtil.dateToString(calendar.getTime(), "yyyy-MM-dd");
		} else {
			return DateUtil.dateToString(date, "yyyy-MM-dd");
		}
	}

	/**
	 * 根据ID查询单个系统消息对象及关系对象属性
	 * 
	 * @date 2015年3月20日 下午3:09:16
	 * @author 冉恒鑫
	 * @param request
	 * @return
	 */
	@RequestMapping("/findMessageAndChangeStatus")
	public String findMessageAndChangeStatus(String messageId, ModelMap map) {
		Map<String, Object> message = messageService.findMessageAndChangeStatus(messageId);
		String sendtime = String.valueOf(message.get("sendtime"));
		message.put("sendtime", sendtime.substring(0, sendtime.length() - 2));
		map.put("message", message);
		return JSP_PR + "/viewMessage";
	}

	/**
	 * 批量和单个系统消息删除
	 * 
	 * @date 2015年3月23日 下午1:39:49
	 * @author 冉恒鑫
	 * @param messageIds
	 * @return
	 */
	@RequestMapping("/deleteMessage")
	@ResponseBody
	public Map<String, Object> deleteMessage(String messageIds, HttpServletRequest request) {
		String[] messageArray = messageIds.split(",");
		List<String> messageIdArray = new ArrayList<String>();
		for (int i = 0; i < messageArray.length; i++) {
			messageIdArray.add(messageArray[i]);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		int result = messageService.deleteMessageByIds(messageIdArray);
		if (result == messageArray.length) {
			map.put("status", "删除成功!");
			// 添加日志
			String optContent = "删除id分别为" + messageIds + "的系统消息";
			SysUser user = SysUtils.getLoginSysUser(request);
			systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_XTXX,
					SystemLogConstant.OPT_MODEL_TYPE_NAME_XTXX, SystemLogConstant.OPT_TYPE_CODE_DELINFO, SystemLogConstant.OPT_TYPE_NAME_DELINFO,
					optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
		} else {
			map.put("status", "删除失败，请联系管理员!");
		}
		return map;
	}

	/**
	 * 批量和单个标记消息为已读
	 * 
	 * @date 2015年3月25日 下午5:52:18
	 * @author 冉恒鑫
	 * @return
	 */
	@RequestMapping("/updateMessage")
	@ResponseBody
	public Map<String, Object> updateMessage(String messageIds, HttpServletRequest request) {
		String[] messageArray = messageIds.split(",");
		List<String> messageIdArray = new ArrayList<String>();
		for (int i = 0; i < messageArray.length; i++) {
			messageIdArray.add(messageArray[i]);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		int result = messageService.updateMessage(messageIdArray);
		if (result == messageArray.length) {
			map.put("status", "标记成功!");
			// 添加日志
			String optContent = "修改系统消息，将消息标记为已读";
			SysUser user = SysUtils.getLoginSysUser(request);
			systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_XTXX,
					SystemLogConstant.OPT_MODEL_TYPE_NAME_XTXX, SystemLogConstant.OPT_TYPE_CODE_EDITINFO, SystemLogConstant.OPT_TYPE_NAME_EDITINFO,
					optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
		} else {
			map.put("status", "标记失败,请联系管理员!");
		}
		return map;
	}

	@RequestMapping("/toAddMessage")
	public String toAddMessage() {
		return JSP_PR + "/addMessage";
	}

	/**
	 * 组装并添加一个系统消息
	 * 
	 * @date 2015年3月23日 下午3:21:04
	 * @author 冉恒鑫
	 * @param request
	 * @return
	 */
	@RequestMapping("/addMessage")
	@ResponseBody
	public Map<String, Object> addMessage(HttpServletRequest request) {
		SysUser user = SysUtils.getLoginSysUser(request);
		String userId = user.getUserId();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String receiverIds = request.getParameter("receiverIds");
		String[] receiverIdArray = receiverIds.split(",");
		int result = 0;
		for (int i = 0; i < receiverIdArray.length; i++) {
			SysMessageEntity sm = new SysMessageEntity();
			sm.setTitle(title);
			sm.setContent(content);
			sm.setStatus(SysMessageEntity.STATUS_UN_READ);
			sm.setSenderId(userId);
			sm.setReceiverId(receiverIdArray[i]);
			sm.setSendTime(new Date());
			SysMessageEntity sme = messageService.save(sm);
			if (sme != null) {
				result++;
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (result == receiverIdArray.length) {
			map.put("message", "发送成功!");
			// 添加日志
			String optContent = "新增标题为" + title + "的系统消息";
			systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_XTXX,
					SystemLogConstant.OPT_MODEL_TYPE_NAME_XTXX, SystemLogConstant.OPT_TYPE_CODE_ADDINFO, SystemLogConstant.OPT_TYPE_NAME_ADDINFO,
					optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
		} else {
			map.put("message", "发送失败");
		}
		return map;
	}

}
