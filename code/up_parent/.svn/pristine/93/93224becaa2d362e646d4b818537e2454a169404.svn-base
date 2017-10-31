package com.upsoft.systemweb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.entity.SysRoleEntity;
import com.upsoft.system.entity.SysRolePermissionEntity;
import com.upsoft.system.entity.SysRoleUser;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.systemweb.dao.RoleDao;
import com.upsoft.systemweb.dao.SystemDefineDao;
import com.upsoft.systemweb.service.RoleService;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 * 
 * 文件名称：RoleManageServiceImpl.java<br>
 * 摘要：简要描述本文件的内容<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：李 红<br>
 * 完成日期：2015年1月22日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：李 红<br>
 * 完成日期：2015年1月22日<br>
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {
	
	private static final Logger logger = Logger.getLogger(RoleServiceImpl.class);
	
	@Autowired
	private RoleDao roleManageDao;
	@Autowired
	private SystemDefineDao systemDefineDao;

	@Override
	public List<SysRoleEntity> findRoleList(Map<String, Object> params) {
		String querySql = "SELECT T.ROLEID,T.ROLENAME,T.ROLETYPE,T.DEFINETYPE,T.ROLEDESCRIPTION,T.SYSTEMCODE FROM SYS_ROLE T ";
		return roleManageDao.queryListBySql(querySql, 0, 0, params, SysRoleEntity.class);
	}

	@Override
	public Map<String, Object> findRoleListAndCount(String systemCode, String roleName, PageBean pageBean,
			String loginId) {
		StringBuffer querySql = new StringBuffer(
				"SELECT T.ROLEID,T.ROLENAME,T.ROLETYPE,T.DEFINETYPE,T.ROLEDESCRIPTION,T.SYSTEMCODE");
		querySql.append(" FROM SYS_ROLE T WHERE 1=1 ");
		Map<String, Object> params = new HashMap<String, Object>();
		if (systemCode != null && !"".equals(systemCode) && !"0".equals(systemCode)) {
			querySql.append(" AND T.SYSTEMCODE=:systemCode");
			params.put("systemCode", systemCode);
		}
		if (roleName != null && !"".equals(roleName)) {
			querySql.append(" AND T.ROLENAME LIKE '%" + roleName + "%'");
		}
		if (!"admin".equals(loginId)) {
			querySql.append(" AND T.DEFINETYPE !=1 ");
		}

		return roleManageDao.queryPaginationListBySql(querySql.toString(), params, pageBean);
	}

	@Override
	public void saveOrUpdateRole(SysRoleEntity roleEntity) {
		try {
			if (roleEntity.getRoleId() != null) {
				update(roleEntity);
			} else {
				save(roleEntity);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public List<Map<String, Object>> findSystemDefine() {
		String querySql = "SELECT SYSTEMCODE ID,SYSTEMNAME NAME,0 PARENTID FROM SYS_SYSTEM_DEFINE";
		List<Map<String, Object>> systemList = systemDefineDao.queryListBySql(querySql, new HashMap<String, Object>());
		for (Map<String, Object> systemDefineEntity : systemList) {
			systemDefineEntity.put("parentId", "0");
			systemDefineEntity.put("icon", "/up_systemweb/image/systemList.png");
		}
		return systemList;
	}

	@Override
	public void delRole(String roleIds) {
		// TODO 删除角色
		roleManageDao.executeSql("DELETE FROM SYS_ROLE R WHERE R.ROLEID IN(" + roleIds + ")",
				new HashMap<String, Object>());
		// TODO 删除角色用户关系
		roleManageDao.executeSql("DELETE FROM SYS_ROLE_USER U WHERE U.ROLEID IN(" + roleIds + ")",
				new HashMap<String, Object>());
		// TODO 删除角色权限关系
		roleManageDao.executeSql("DELETE FROM SYS_ROLE_PERMISSION P WHERE P.ROLEID IN(" + roleIds + ")",
				new HashMap<String, Object>());
	}

	@Override
	public SysRoleEntity findRoleById(String roleId) {
		return findOne(SysRoleEntity.class, roleId);
	}

	@Override
	public JSONObject findUserForOrgTree(String roleId) {
		Map<String, Object> params = new HashMap<String, Object>();
		JSONObject treeList = new JSONObject();
		params.put("roleId", roleId);
		String fromSQL = " SELECT O.ORGID ID,O.PARENTORGID PARENTID,O.ORGNAME NAME,0 ISUSER FROM SYS_ORG O WHERE O.ENABLED=1"
				+ " UNION "
				+ " SELECT U.USERID,U.ORGID,U.USERNAME,1 ISUSER FROM SYS_USER U,SYS_ORG O  WHERE U.ORGID=O.ORGID AND O.ENABLED=1 AND U.STATUS=1 "
				+ " MINUS " + " SELECT U.USERID,U.ORGID,U.USERNAME,1 ISUSER FROM SYS_USER U,SYS_ROLE_USER R "
				+ " WHERE U.STATUS=1 AND U.USERID=R.USERID AND R.ROLEID=:roleId ";
		List<Map<String, Object>> fromList = roleManageDao.queryListBySql(fromSQL, 0, 0, params);
		for (Map<String, Object> listMap : fromList) {
			listMap.put("parentId", listMap.get("parentid"));
			listMap.remove("parentid");
			if ("1".equals(listMap.get("isuser").toString())) {
				listMap.put("icon", "../image/person.png");
				listMap.put("oldParentId", listMap.get("parentId"));
			} else {
				listMap.put("drag", "false");
				if ("0".equals(listMap.get("parentId") + "")) {
					listMap.put("icon", "../image/company.png");
				} else {
					listMap.put("icon", "../image/dept.png");
				}
			}
		}
		String toSQL = " SELECT U.USERID ID, U.ORGID PARENTID, U.USERNAME NAME,1 ISUSER FROM SYS_USER U,SYS_ROLE_USER R "
				+ " WHERE U.STATUS=1 AND U.USERID=R.USERID AND R.ROLEID=:roleId ";
		List<Map<String, Object>> toList = roleManageDao.queryListBySql(toSQL, 0, 0, params);
		for (Map<String, Object> listMap : toList) {
			listMap.put("parentId", listMap.get("parentid"));
			listMap.put("oldParentId", listMap.get("parentid"));
			listMap.remove("parentid");
			listMap.put("drag", "false");
			listMap.put("icon", "../image/person.png");
		}
		treeList.put("fromList", fromList);
		treeList.put("toList", toList);
		return treeList;
	}

	@Override
	public void saveRoleViewUser(String roleId, String userIds) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		roleManageDao.executeSql("DELETE FROM SYS_ROLE_USER S WHERE S.ROLEID =:roleId", params);
		String[] users = userIds.split(",");
		for (int i = 0; i < users.length; i++) {
			SysRoleUser roleUser = new SysRoleUser();
			roleUser.setRoleId(roleId);
			roleUser.setUserId(users[i]);
			save(roleUser);
		}
	}

	@Override
	public List<Map<String, Object>> findPermissionForRole(String roleId, String systemCode) {
		String queryStr = "SELECT * FROM ( SELECT P.PERMISSIONID ID,P.PARENTPERMISSIONID PARENTID,P.PERMISSIONNAME NAME,'FALSE' CHECKED,'FALSE' EXPAND,P.ORDERNO FROM SYS_PERMISSION P WHERE P.ENABLED=1 AND P.SYSTEMCODE='"
				+ systemCode + "' \n" + " MINUS\n"
				+ " SELECT P.PERMISSIONID ,P.PARENTPERMISSIONID ,P.PERMISSIONNAME,'FALSE' CHECKED,'FALSE' EXPAND,P.ORDERNO FROM SYS_ROLE_PERMISSION RP, SYS_PERMISSION P WHERE P.ENABLED=1 AND RP.PERMISSIONID=P.PERMISSIONID AND RP.ROLEID='"
				+ roleId + "'\n" + " UNION\n"
				+ " SELECT P.PERMISSIONID ,P.PARENTPERMISSIONID ,P.PERMISSIONNAME,'TRUE' CHECKED,'TRUE' EXPAND,P.ORDERNO FROM SYS_ROLE_PERMISSION RP , SYS_PERMISSION P WHERE P.ENABLED=1 AND RP.PERMISSIONID=P.PERMISSIONID AND RP.ROLEID='"
				+ roleId + "' ";
		queryStr += " ) ORDER BY ORDERNO ";
		List<Map<String, Object>> permissionList = roleManageDao.queryListBySql(queryStr, 0, 0,
				new HashMap<String, Object>());
		for (Map<String, Object> map : permissionList) {
			map.put("parentId", map.get("parentid"));
			map.remove("parentid");
			map.put("icon", "../image/permissionList.png");
		}
		return permissionList;
	}

	@Override
	public void saveRoleViewPermission(String roleId, String permissionIds) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		roleManageDao.executeSql("DELETE FROM SYS_ROLE_PERMISSION P WHERE P.ROLEID =:roleId", params);
		String[] permissions = permissionIds.split(",");
		if (permissions != null && permissions.length > 0) {
			for (int i = 0; i < permissions.length; i++) {
				String str = permissions[i];
				if (str != null && !"".equals(str)) {
					SysRolePermissionEntity rolePermission = new SysRolePermissionEntity();
					rolePermission.setRoleId(roleId);
					rolePermission.setPermissionId(str);
					save(rolePermission);
				}
			}
		}
	}
}
