package com.upsoft.systemweb.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.upsoft.login.controller.BaseController;
import com.upsoft.login.support.webservice.SysUtils;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.constant.SystemLogConstant;
import com.upsoft.system.entity.FeedBackEntity;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.support.annotation.LogAnnotation;
import com.upsoft.systemweb.service.FeedBackService;
import com.upsoft.systemweb.service.PermissionService;
import com.upsoft.systemweb.support.SystemLogServiceCache;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：ErrorController.java<br>
 * 摘要：问题反馈功能<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：李 红<br>
 * 完成日期：2015年4月2日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：李 红<br>
 * 完成日期：2015年4月2日<br>
 */
@Controller
@RequestMapping("/feedBack")
public class FeedBackController extends BaseController {
	@Autowired
	private FeedBackService feedBackService;
	// @Autowired
	// private LogService logService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private SystemLogServiceCache systemLogServiceCache;

	private static final Logger logger = Logger.getLogger(FeedBackController.class);

	@LogAnnotation(FunctionName = "问题反馈功能")
	@RequestMapping("/init")
	public String init(ModelMap map, String menuid, HttpServletRequest request) {
		List<Map<String, Object>> systemLists = permissionService.findSystemDefine();
		map.put("treeMap", new Gson().toJson(systemLists));
		super.putDictConstant(map);
		super.findMenuResourcePermission(request, map);
		// 添加日志
		String optContent = "查询问题反馈";
		SysUser user = SysUtils.getLoginSysUser(request);
		systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(),
				Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_WTFK,
				SystemLogConstant.OPT_MODEL_TYPE_NAME_WTFK, SystemLogConstant.OPT_TYPE_CODE_QUERYINFO, SystemLogConstant.OPT_TYPE_NAME_QUERYINFO,
				optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
		return "/WEB-INF/jsp/feedBack/main";
	}

	/**
	 * 页面展示
	 * 
	 * @date 2015年4月2日 下午4:52:08
	 * @author 李 红
	 * @param map
	 * @param request
	 * @param title
	 * @param systemCode
	 * @param content
	 * @return
	 */
	@RequestMapping("/findList")
	@ResponseBody
	public Map<String, Object> findList(ModelMap map, HttpServletRequest request, String title, String systemCode, String content, String beginTime,
			String endTime) {
		PageBean pb = new PageBean(request);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", title);
		params.put("content", content);
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		params.put("systemCode", systemCode);
		Map<String, Object> ListAndCount = this.feedBackService.findListAndCount(params, pb);
		Map<String, Object> lists = new HashMap<String, Object>();
		lists.put("pager.totalRows", ListAndCount.get(PageBean.TOTAL));
		lists.put("rows", ListAndCount.get("rows"));
		return lists;
	}

	/**
	 * 新增跳转
	 * 
	 * @date 2015年4月2日 下午4:38:01
	 * @author 李 红
	 * @param map
	 * @param systemCode
	 * @param systemName
	 * @return
	 */
	@RequestMapping("/toAddFeedBack")
	public String toAddFeedBack(ModelMap map, String systemCode, String systemName) {
		map.put("systemCode", systemCode);
		map.put("systemName", systemName);
		return "/WEB-INF/jsp/feedBack/feedBack";
	}

	/**
	 * 保存
	 * 
	 * @date 2015年4月2日 下午4:39:37
	 * @author 李 红
	 * @param roleEntity
	 * @param request
	 * @return
	 */
	@RequestMapping("/addFeedBack")
	@ResponseBody
	public JSONObject addFeedBack(FeedBackEntity feedbackEntity, HttpServletRequest request) {
		JSONObject response = new JSONObject();
		SysUser user = SysUtils.getLoginSysUser(request);
		try {
			feedbackEntity.setUserId(user.getUserId());
			feedbackEntity.setCreateTime(new Date());
			this.feedBackService.saveOrUpdate(feedbackEntity);
			// logService.saveLog(request, feedbackEntity);
			// 添加日志
			String optContent = "新增标题为" + feedbackEntity.getTitle() + "的问题反馈";
			systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_WTFK,
					SystemLogConstant.OPT_MODEL_TYPE_NAME_WTFK, SystemLogConstant.OPT_TYPE_CODE_ADDINFO, SystemLogConstant.OPT_TYPE_NAME_ADDINFO,
					optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
			response.put("message", "保存成功");
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.put("message", "保存失败");
		}
		return response;
	}

	/**
	 * 删除
	 * 
	 * @date 2015年4月2日 下午4:52:23
	 * @author 李 红
	 * @param ids
	 * @param request
	 * @return
	 */
	@RequestMapping("/removeFeedBack")
	@ResponseBody
	public JSONObject removeFeedBack(String ids, HttpServletRequest request) {
		JSONObject response = new JSONObject();
		try {
			if (StringUtils.isNotBlank(ids)) {
				String id[] = ids.split(",");
				for (int i = 0; i < id.length; i++) {
					if (id[i] != null && !"".equals(id[i])) {
						FeedBackEntity feedbackEntity = new FeedBackEntity();
						feedbackEntity.setId(id[i]);
						this.feedBackService.delete(feedbackEntity);
						// this.logService.saveLog(request, feedbackEntity);
						// 添加日志
						String optContent = "删除id为" + id[i] + "的问题反馈";
						SysUser user = SysUtils.getLoginSysUser(request);
						systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(),
								Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_WTFK,
								SystemLogConstant.OPT_MODEL_TYPE_NAME_WTFK, SystemLogConstant.OPT_TYPE_CODE_DELINFO,
								SystemLogConstant.OPT_TYPE_NAME_DELINFO, optContent, SystemLogConstant.UP_SYSTEMWEB_CODE,
								SystemLogConstant.UP_SYSTEMWEB_NAME);
					}
				}
			}
			response.put("message", "删除成功");
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.put("message", "删除失败");
		}
		return response;
	}

	/**
	 * 查看详细
	 * 
	 * @date 2015年4月2日 下午4:54:38
	 * @author 李 红
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping("/showInfo")
	public String showInfo(ModelMap map, String id) {
		map.put("feedBack", this.feedBackService.findOne(id));
		return "/WEB-INF/jsp/feedBack/showInfo";
	}
}
