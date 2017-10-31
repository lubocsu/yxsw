package com.upsoft.systemweb.controller;

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
import com.upsoft.system.entity.SysPermissionEntity;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.support.annotation.LogAnnotation;
import com.upsoft.systemweb.service.PermissionService;
import com.upsoft.systemweb.support.SystemLogServiceCache;

/**
 * 
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：PermissionController.java<br>
 * 摘要：权限管理模块<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：李 红<br>
 * 完成日期：2015年2月3日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：李 红<br>
 * 完成日期：2015年2月3日<br>
 */
@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController {
	private static final Logger logger = Logger.getLogger(PermissionController.class);
	@Autowired
	private PermissionService permissionService;
	// @Autowired
	// private LogService logService;

	@Autowired
	private SystemLogServiceCache systemLogServiceCache;

	/**
	 * 权限管理页面跳转
	 * 
	 * @date 2015年1月22日 下午3:39:30
	 * @author 李 红
	 * @param map
	 * @return
	 */
	@LogAnnotation(FunctionName = "权限管理功能")
	@RequestMapping("/init")
	public String roleManage(ModelMap map, String menuid, HttpServletRequest request) {
		List<Map<String, Object>> systemLists = permissionService.findSystemDefine();
		map.put("treeMap", new Gson().toJson(systemLists));
		checkButtonPermission(request, menuid, map);
		super.putDictConstant(map);
		super.findMenuResourcePermission(request, map);
		// 添加日志
		String optContent = "查询系统权限";
		SysUser user = SysUtils.getLoginSysUser(request);
		systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(),
				Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_QXGL,
				SystemLogConstant.OPT_MODEL_TYPE_NAME_QXGL, SystemLogConstant.OPT_TYPE_CODE_QUERYINFO, SystemLogConstant.OPT_TYPE_NAME_QUERYINFO,
				optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
		return "/WEB-INF/jsp/permission/main";
	}

	/**
	 * 通过系统编号获取对应权限信息
	 * 
	 * @date 2015年1月26日 上午11:44:52
	 * @author 李 红
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping("/findPermissionList")
	@ResponseBody
	public Map<String, Object> findPermissionList(ModelMap map, HttpServletRequest request, String permissionName, String systemCode,
			String parentId) {
		PageBean pb = new PageBean(request);
		Map<String, Object> roleListAndCount = permissionService.findPermissionListAndCount(systemCode, parentId, permissionName, pb);
		return roleListAndCount;
	}

	/**
	 * 异步加载子权限
	 * 
	 * @date 2015年2月4日 下午2:03:35
	 * @author 李 红
	 * @param map
	 * @param permissionId
	 * @return
	 */
	@RequestMapping("/findPermissionChild")
	@ResponseBody
	public Map<String, Object> findPermissionChild(ModelMap map, String parentId) {
		Map<String, Object> permissionChild = permissionService.findPermissionChild(parentId);
		return permissionChild;
	}

	/**
	 * 新增权限跳转
	 * 
	 * @date 2015年1月26日 上午11:45:19
	 * @author 李 红
	 * @param map
	 * @param systemCode
	 * @param systemName
	 * @return
	 */
	@RequestMapping("/toAddPermission")
	public String toAddPermission(ModelMap map, String btnPermission, String systemCode, String systemName, String parentPermissionId) {
		map.put("systemCode", systemCode);
		map.put("systemName", systemName);
		map.put("parentPermissionId", parentPermissionId);
		Long orderNo = this.permissionService.queryOrderNo(systemCode);
		map.put("btnPermissions", this.permissionService.findBtnPermission(btnPermission));
		// if(1==orderNo){
		// map.put("noDataInfo", "当前子系统未找到上级权限选项");
		// }
		map.put("orderNo", orderNo);
		super.putDictConstant(map);
		return "/WEB-INF/jsp/permission/addPermission";
	}

	/**
	 * 获取权限树
	 * 
	 * @date 2015年2月5日 上午9:36:09
	 * @author 李 红
	 * @param map
	 * @param systemCode
	 * @return
	 */
	@RequestMapping("/findPermissionTree")
	@ResponseBody
	public List<Map<String, Object>> findPermissionTree(ModelMap map, String systemCode, String permissionId) {
		return this.permissionService.findPermissionTree(systemCode, permissionId);
	}

	/**
	 * 获取菜单树
	 * 
	 * @date 2015年2月5日 上午9:36:09
	 * @author 李 红
	 * @param map
	 * @param systemCode
	 * @return
	 */
	@RequestMapping("/findMenuTree")
	@ResponseBody
	public List<Map<String, Object>> findMenuTree(ModelMap map, String systemCode) {
		return this.permissionService.findMenuTree(systemCode);
	}

	/**
	 * 验证唯一性
	 * 
	 * @date 2015年2月5日 下午3:29:08
	 * @author 李 红
	 * @param map
	 * @param systemCode
	 * @param field
	 * @param value
	 * @return
	 */
	@RequestMapping("/validExists")
	@ResponseBody
	public Map<String, Object> validExists(HttpServletRequest request, ModelMap map, String systemCode, String validateKey, String validateValue,
			String permissionId) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("valid", this.permissionService.validExists(systemCode, validateKey, validateValue, permissionId));
		Map<String, Object> resultJson = new HashMap<String, Object>();
		resultJson.put("validateResult", result);
		return resultJson;
	}

	/**
	 * 新增 权限
	 * 
	 * @date 2015年1月26日 上午11:44:39
	 * @author 李 红
	 * @param roleEntity
	 * @return
	 */
	@RequestMapping("/addPermission")
	@ResponseBody
	public JSONObject addPermission(SysPermissionEntity permissionEntity, HttpServletRequest request) {
		JSONObject response = new JSONObject();
		try {
			// logService.saveLog(request, permissionEntity);
			permissionService.saveOrUpdatePermission(permissionEntity);
			// 添加日志
			String optContent = "新增权限名为" + permissionEntity.getPermissionName() + "的系统权限";
			SysUser user = SysUtils.getLoginSysUser(request);
			systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_QXGL,
					SystemLogConstant.OPT_MODEL_TYPE_NAME_QXGL, SystemLogConstant.OPT_TYPE_CODE_ADDINFO, SystemLogConstant.OPT_TYPE_NAME_ADDINFO,
					optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
			response.put("message", "新增成功");
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.put("message", "新增失败");
		}

		return response;
	}

	/**
	 * 删除权限
	 * 
	 * @date 2015年1月26日 上午11:44:30
	 * @author 李 红
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/removePermission")
	@ResponseBody
	public JSONObject removePermission(String permissionIds, HttpServletRequest request) {
		JSONObject response = new JSONObject();
		try {
			SysPermissionEntity permissionEntity = new SysPermissionEntity();
			permissionEntity.setPermissionId(permissionIds);
			String permisstionIds[] = permissionIds.split(",");
			for (int i = 0; i < permisstionIds.length; i++) {
				if (permisstionIds[i] != null && !"".equals(permisstionIds[i])) {
					permissionEntity.setPermissionId(permisstionIds[i]);
					// logService.saveLog(request, permissionEntity);
				}
			}

			permissionService.delPermission(permissionIds);
			// 添加日志
			String optContent = "删除权限id分别为" + permissionIds + "的系统权限";
			SysUser user = SysUtils.getLoginSysUser(request);
			systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_QXGL,
					SystemLogConstant.OPT_MODEL_TYPE_NAME_QXGL, SystemLogConstant.OPT_TYPE_CODE_DELINFO, SystemLogConstant.OPT_TYPE_NAME_DELINFO,
					optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
			response.put("message", "删除成功");
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.put("message", "删除失败");
		}
		return response;
	}

	/**
	 * 编辑跳转
	 * 
	 * @date 2015年1月26日 上午11:44:12
	 * @author 李 红
	 * @param map
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/toModifyPermission")
	public String toModifyPermission(ModelMap map, String btnPermission, String permissionId, String systemCode, String systemName) {
		super.putDictConstant(map);
		List<Map<String, Object>> permission = this.permissionService.findOne(permissionId);
		for (Map<String, Object> perm : permission) {
			if ("1".equals(perm.get("permissiontype") + "")) {
				String permissionNo = perm.get("permissionno") + "";
				perm.put("permissionno", permissionNo.substring(permissionNo.lastIndexOf("_") + 1, permissionNo.length()));
			}
			map.put("permissionEntity", perm);
		}
		List<Map<String, Object>> btnPermissions = this.permissionService.findByParent(permissionId);
		String childPermissionNo = "";
		for (Map<String, Object> perm : btnPermissions) {
			childPermissionNo = perm.get("permissionno") + "";
		}
		map.put("childPermissionNo", childPermissionNo);
		super.putDictConstant(map);
		Long orderNo = this.permissionService.queryOrderNo(systemCode);
		map.put("btnPermissions", this.permissionService.findBtnPermission(btnPermission));
		map.put("orderNo", orderNo);
		map.put("systemName", systemName);
		map.put("systemCode", systemCode);
		super.putDictConstant(map);
		return "/WEB-INF/jsp/permission/modifyPermission";
	}

	/**
	 * 查看权限
	 * 
	 * @date 2015年2月26日 下午4:59:15
	 * @author 李 红
	 * @param map
	 * @param permissionId
	 * @param systemCode
	 * @param systemName
	 * @return
	 */
	@RequestMapping("/onView")
	public String onView(ModelMap map, String btnPermission, String permissionId) {
		if (StringUtils.isNotBlank(permissionId)) {
			map.put("permissionView", this.permissionService.findPermissionView(permissionId).get(0));
		}
		map.put("btnPermissions", this.permissionService.findBtnPermission(btnPermission));
		return "/WEB-INF/jsp/permission/onView";
	}

	/**
	 * 修改 权限
	 * 
	 * @date 2015年1月26日 上午11:44:39
	 * @author 李 红
	 * @param roleEntity
	 * @return
	 */
	@RequestMapping("/modifyPermission")
	@ResponseBody
	public JSONObject modifyPermission(SysPermissionEntity permissionEntity, String menuId, HttpServletRequest request) {
		JSONObject response = new JSONObject();
		try {
			// logService.saveLog(request, permissionEntity);
			this.permissionService.saveOrUpdatePermission(permissionEntity);
			// 添加日志
			String optContent = "修改权限id为" + permissionEntity.getPermissionId() + "的系统权限";
			SysUser user = SysUtils.getLoginSysUser(request);
			systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_QXGL,
					SystemLogConstant.OPT_MODEL_TYPE_NAME_QXGL, SystemLogConstant.OPT_TYPE_CODE_EDITINFO, SystemLogConstant.OPT_TYPE_NAME_EDITINFO,
					optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
			response.put("message", "修改成功");
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.put("message", "修改失败");
		}
		return response;
	}

	/**
	 * 权限菜单跳转
	 * 
	 * @date 2015年1月26日 上午11:44:12
	 * @author 李 红
	 * @param map
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/toPermissionViewMenu")
	public String toPermissionViewMenu(ModelMap map, String systemCode, String permissionId) {
		map.put("treeMap", this.permissionService.findPermissionForMenu(systemCode, permissionId));
		map.put("permissionId", permissionId);
		return "/WEB-INF/jsp/permission/permissionViewMenu";
	}

	/**
	 * 保存权限菜单信息
	 * 
	 * @date 2015年1月28日 下午5:46:12
	 * @author 李 红
	 * @param roleId
	 * @param userIds
	 * @return
	 */
	@RequestMapping("/addPermissionViewMenu")
	@ResponseBody
	public JSONObject addPermissionViewMenu(String permissionId, String menuIds) {
		JSONObject jsonObject = new JSONObject();
		try {
			this.permissionService.savePermissionToMenu(permissionId, menuIds);
			jsonObject.put("message", "保存成功!");
		} catch (Exception e) {
			logger.error(e.getMessage());
			jsonObject.put("message", "保存失败!");
		}
		return jsonObject;
	}

	/**
	 * 按扭权限控制
	 * 
	 * @date 2015年1月29日 下午4:49:32
	 * @author 李 红
	 * @param request
	 * @param map
	 */
	public void checkButtonPermission(HttpServletRequest request, String menuId, ModelMap map) {
		// WSLoginReturnBasicInfoBean userInfo=(WSLoginReturnBasicInfoBean)
		// request.getSession().getAttribute(SystemConstant.KEY_FOR_SYSUSER);
		// super.findMenuResourcePermission(userInfo.getUserId(),menuId, map);
	}
}
