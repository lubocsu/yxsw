package com.upsoft.systemweb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.upsoft.system.entity.SysRoleEntity;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.support.annotation.LogAnnotation;
import com.upsoft.systemweb.service.RoleService;
import com.upsoft.systemweb.support.SystemLogServiceCache;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：RoleManageController.java<br>
 * 摘要：角色管理类<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：李 红<br>
 * 完成日期：2015年1月21日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：李 红<br>
 * 完成日期：2015年1月21日<br>
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
	private static final Logger logger = Logger.getLogger(RoleController.class);
	@Autowired
	private RoleService roleManageService;
	// @Autowired
	// private LogService logService;

	@Autowired
	private SystemLogServiceCache systemLogServiceCache;

	/**
	 * 角色管理页面跳转
	 * 
	 * @date 2015年1月22日 下午3:39:30
	 * @author 李 红
	 * @param map
	 * @return
	 */
	@LogAnnotation(FunctionName = "角色管理功能")
	@RequestMapping("/init")
	public String roleManage(ModelMap map, String menuid, HttpServletRequest request) {
		List<Map<String, Object>> systemLists = roleManageService.findSystemDefine();
		map.put("treeMap", new Gson().toJson(systemLists));
		super.findMenuResourcePermission(request, map);
		// 添加日志
		String optContent = "查询系统角色";
		SysUser user = SysUtils.getLoginSysUser(request);
		systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(),
				Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_JSGL,
				SystemLogConstant.OPT_MODEL_TYPE_NAME_JSGL, SystemLogConstant.OPT_TYPE_CODE_QUERYINFO, SystemLogConstant.OPT_TYPE_NAME_QUERYINFO,
				optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
		return "/WEB-INF/jsp/role/main";
	}

	/**
	 * 通过系统编号获取对应角色信息
	 * 
	 * @date 2015年1月26日 上午11:44:52
	 * @author 李 红
	 * @param map
	 * @param request
	 * @return
	 */
	@RequestMapping("/findRoleList")
	@ResponseBody
	public Map<String, Object> findRoleList(ModelMap map, HttpServletRequest request, String roleName, String systemCode) {
		PageBean pb = new PageBean(request);
		SysUser user = SysUtils.getLoginSysUser(request);
		Map<String, Object> roleListAndCount = roleManageService.findRoleListAndCount(systemCode, roleName, pb, user.getLoginId());
		Map<String, Object> roleInfo = new HashMap<String, Object>();
		roleInfo.put("pager.totalRows", roleListAndCount.get(PageBean.TOTAL));
		roleInfo.put("rows", roleListAndCount.get("rows"));
		return roleInfo;
	}

	/**
	 * 新增跳转
	 * 
	 * @date 2015年1月26日 上午11:45:19
	 * @author 李 红
	 * @param map
	 * @param systemCode
	 * @param systemName
	 * @return
	 */
	@RequestMapping("/toAddRole")
	public String toAddRole(ModelMap map, String systemCode, String systemName) {
		map.put("systemCode", systemCode);
		map.put("systemName", systemName);
		super.putDictConstant(map);
		return "/WEB-INF/jsp/role/addRole";
	}

	/**
	 * 新增 角色
	 * 
	 * @date 2015年1月26日 上午11:44:39
	 * @author 李 红
	 * @param roleEntity
	 * @return
	 */
	@RequestMapping("/addRole")
	@ResponseBody
	public JSONObject addRole(SysRoleEntity roleEntity, HttpServletRequest request) {
		JSONObject response = new JSONObject();
		try {
			// logService.saveLog(request, roleEntity);
			roleManageService.saveOrUpdateRole(roleEntity);
			// 添加日志
			String optContent = "新增名称为" + roleEntity.getRoleName() + "的系统角色";
			SysUser user = SysUtils.getLoginSysUser(request);
			systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_JSGL,
					SystemLogConstant.OPT_MODEL_TYPE_NAME_JSGL, SystemLogConstant.OPT_TYPE_CODE_ADDINFO, SystemLogConstant.OPT_TYPE_NAME_ADDINFO,
					optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
			response.put("message", "新增成功");
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.put("message", "新增失败");
		}
		return response;
	}

	/**
	 * 删除角色
	 * 
	 * @date 2015年1月26日 上午11:44:30
	 * @author 李 红
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/removeRole")
	@ResponseBody
	public JSONObject removeRole(String roleIds, HttpServletRequest request) {
		JSONObject response = new JSONObject();
		try {
			if (roleIds != null && !"".equals(roleIds)) {
				String roles[] = roleIds.split(",");
				for (int i = 0; i < roles.length; i++) {
					if (roles[i] != null && !"".equals(roles[i])) {
						SysRoleEntity role = new SysRoleEntity();
						role.setRoleId(roles[i]);
						// logService.saveLog(request, role);
					}
				}
			}
			roleManageService.delRole(roleIds);
			// 添加日志
			String optContent = "删除id分别为" + roleIds + "的系统角色";
			SysUser user = SysUtils.getLoginSysUser(request);
			systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_JSGL,
					SystemLogConstant.OPT_MODEL_TYPE_NAME_JSGL, SystemLogConstant.OPT_TYPE_CODE_DELINFO, SystemLogConstant.OPT_TYPE_NAME_DELINFO,
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
	@RequestMapping("/toModifyRole")
	public String toModifyRole(ModelMap map, String roleId) {
		super.putDictConstant(map);
		map.put("roleEntity", roleManageService.findRoleById(roleId));
		return "/WEB-INF/jsp/role/modifyRole";
	}

	/**
	 * 修改 角色
	 * 
	 * @date 2015年1月26日 上午11:44:39
	 * @author 李 红
	 * @param roleEntity
	 * @return
	 */
	@RequestMapping("/modifyRole")
	@ResponseBody
	public JSONObject modifyRole(SysRoleEntity roleEntity, HttpServletRequest request) {
		JSONObject response = new JSONObject();
		try {
			// logService.saveLog(request, roleEntity);
			roleManageService.saveOrUpdateRole(roleEntity);
			// 添加日志
			String optContent = "修改名称为" + roleEntity.getRoleName() + "的系统角色";
			SysUser user = SysUtils.getLoginSysUser(request);
			systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_JSGL,
					SystemLogConstant.OPT_MODEL_TYPE_NAME_JSGL, SystemLogConstant.OPT_TYPE_CODE_EDITINFO, SystemLogConstant.OPT_TYPE_NAME_EDITINFO,
					optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
			response.put("message", "修改成功");
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.put("message", "修改失败");
		}
		return response;
	}

	/**
	 * 角色用户跳转
	 * 
	 * @date 2015年1月26日 上午11:44:12
	 * @author 李 红
	 * @param map
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/toRoleViewUser")
	public String toRoleViewUser(HttpServletRequest request, ModelMap map, String roleId, String roleName) {
		map.put("treeMap", roleManageService.findUserForOrgTree(roleId));
		map.put("roleId", roleId);
		map.put("roleName", roleName);
		return "/WEB-INF/jsp/role/roleViewUser";
	}

	/**
	 * 角色权限跳转
	 * 
	 * @date 2015年1月26日 上午11:44:12
	 * @author 李 红
	 * @param map
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/toRoleViewPermission")
	public String toRoleViewPermission(ModelMap map, String roleId, String roleName, String systemCode) {
		map.put("treeMap", new Gson().toJson(roleManageService.findPermissionForRole(roleId, systemCode)));
		map.put("roleId", roleId);
		map.put("roleName", roleName);
		return "/WEB-INF/jsp/role/roleViewPermission";
	}

	/**
	 * 保存角色用户信息
	 * 
	 * @date 2015年1月28日 下午5:46:12
	 * @author 李 红
	 * @param roleId
	 * @param userIds
	 * @return
	 */
	@RequestMapping("/addRoleViewUser")
	@ResponseBody
	public JSONObject addRoleViewUser(String roleId, String userIds) {
		JSONObject jsonObject = new JSONObject();
		try {
			roleManageService.saveRoleViewUser(roleId, userIds);
			jsonObject.put("message", "保存成功!");
		} catch (Exception e) {
			logger.error(e.getMessage());
			jsonObject.put("message", "保存失败!");
		}
		return jsonObject;
	}

	/**
	 * 保存角色权限信息
	 * 
	 * @date 2015年1月28日 下午5:46:05
	 * @author 李 红
	 * @param roleId
	 * @param permissionIds
	 * @return
	 */
	@RequestMapping("/addRoleViewPermission")
	@ResponseBody
	public JSONObject addRoleViewPermission(String roleId, String permissionIds) {
		JSONObject jsonObject = new JSONObject();
		try {
			roleManageService.saveRoleViewPermission(roleId, permissionIds);
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
