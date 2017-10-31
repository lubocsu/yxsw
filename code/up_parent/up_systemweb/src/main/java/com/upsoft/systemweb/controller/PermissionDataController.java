package com.upsoft.systemweb.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.google.gson.Gson;
import com.upsoft.system.entity.SysPermssionDataEntity;
import com.upsoft.systemweb.constant.SystemWebConstant;
import com.upsoft.systemweb.service.PermissionDataService;

@Controller
@RequestMapping("/permissionData")
public class PermissionDataController {
	private static final Logger logger = Logger.getLogger(PermissionDataController.class);

	@Autowired
	private PermissionDataService permissionDataService;

	/**
	 * 角色的数据权限管理页（弹窗）
	 * 
	 * @date 2016年5月18日 下午3:53:04
	 * @author null
	 * @param request
	 * @param map
	 * @param roleId
	 * @param roleName
	 * @return
	 */
	@RequestMapping("/toRoleManage")
	public String toRoleManage(HttpServletRequest request, ModelMap map,
			@RequestParam(required = true)  String roleId,
			@RequestParam(required = true)  String roleName,
			@RequestParam(required = true) String systemCode) {
		map.put("treeMap",new Gson().toJson(permissionDataService.getPermissionOrgTree(SystemWebConstant.PERMISSION_DATA_TYPE_ROLE, roleId, systemCode)));
		map.put("roleId", roleId);
		map.put("roleName", roleName);
		map.put("systemCode", systemCode);
		return "/WEB-INF/jsp/permissionData/roleManagePage";
	}

	/**
	 * 用户的数据权限管理页（弹窗）
	 * 
	 * @date 2016年5月19日 上午10:53:01
	 * @author null
	 * @param request
	 * @param map
	 * @param roleId
	 * @param roleName
	 * @return
	 */
	@RequestMapping("/toUserManage")
	public String toUserManage(HttpServletRequest request, ModelMap map, String userId, String userName) {
		map.put("treeMap", new Gson().toJson(permissionDataService.getPermissionOrgTree(SystemWebConstant.PERMISSION_DATA_TYPE_USER, userId, null)));
		map.put("userId", userId);
		map.put("userName", userName);
		return "/WEB-INF/jsp/permissionData/userManagePage";
	}

	/**
	 * 查看用户完整的数据权限
	 * 
	 * @date 2016年5月19日 上午11:12:10
	 * @author null
	 * @param request
	 * @param map
	 * @param userId
	 * @param userName
	 * @return
	 */
	@RequestMapping("/toFullPermissionData")
	public String toFullPermissionData(HttpServletRequest request, ModelMap map,@RequestParam(required=true) String userId, String userName) {
		String systemCode = WebUtils.findParameterValue(request, "systemCode");
		map.put("treeMap", new Gson().toJson(permissionDataService.getFullPermissionOrgTree(userId, systemCode)));
		map.put("userId", userId);
		map.put("userName", userName);
		return "/WEB-INF/jsp/permissionData/fullPermissionPage";
	}

	/**
	 * 异步保存数据权限
	 * 
	 * @date 2016年5月18日 下午3:53:36
	 * @author null
	 * @param request
	 * @param map
	 * @param type
	 * @param relateId
	 * @param orgId
	 * @param status
	 * @return
	 */
	@RequestMapping("/savePermissionData")
	@ResponseBody
	public Map<String, Object> addPermissionData(HttpServletRequest request, ModelMap map, int type, String relateId, String orgId, int status) {
		String systemCode = WebUtils.findParameterValue(request, "systemCode");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			permissionDataService.savePermissionData(type, relateId, orgId, status, systemCode);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.put("success", false);
		}
		result.put("success", true);
		return result;
	}

	/**
	 * 批量保存数据权限信息
	 * 
	 * @date 2016年5月18日 下午4:18:17
	 * @author null
	 * @param request
	 * @param map
	 * @param type
	 * @param relateId
	 * @param orgIds
	 * @param status
	 * @return
	 */
	@RequestMapping("/savePermissionDataList")
	@ResponseBody
	public Map<String, Object> savePermissionDataList(HttpServletRequest request, ModelMap map, int type, String relateId, String orgIds,
			int status) {
		String systemCode = WebUtils.findParameterValue(request, "systemCode");
		// 拼装数据
		List<SysPermssionDataEntity> permissions = new ArrayList<SysPermssionDataEntity>();
		String[] ids = orgIds.split(",");
		for (String id : ids) {
			SysPermssionDataEntity permission = new SysPermssionDataEntity();
			permission.setType(type);
			permission.setRelateId(relateId);
			permission.setOrgId(id);
			permission.setStatus(status);
			permission.setSystemCode(systemCode);
			permissions.add(permission);
		}
		// 保存权限
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			permissionDataService.savePermissionDataInBatch(permissions);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.put("success", false);
		}
		result.put("success", true);
		return result;
	}

	/**
	 * 清除数据权限
	 * 
	 * @date 2016年5月18日 下午5:28:27
	 * @author null
	 * @param request
	 * @param map
	 * @param type
	 * @param relateId
	 * @param orgIds
	 * @return
	 */
	@RequestMapping("/delPermissionDataList")
	@ResponseBody
	public Map<String, Object> delPermissionDataList(HttpServletRequest request, ModelMap map, int type, String relateId, String orgIds) {
		String systemCode = WebUtils.findParameterValue(request, "systemCode");
		List<String> ids = Arrays.asList(orgIds.split(","));
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			permissionDataService.deletePermissionDataInBatch(type, relateId, systemCode, ids);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.put("success", false);
		}
		result.put("success", true);
		return result;
	}

}
