package com.upsoft.systemweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.system.entity.SysPermssionDataEntity;
import com.upsoft.system.entity.SysUser;
import com.upsoft.systemweb.constant.SystemWebConstant;
import com.upsoft.systemweb.dao.PermissionDataDao;
import com.upsoft.systemweb.service.OrgService;
import com.upsoft.systemweb.service.PermissionDataService;
import com.upsoft.systemweb.service.UserService;

@Service
public class PermissionDataServiceImpl implements PermissionDataService{
	
	@Autowired
	private PermissionDataDao permissionDataDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrgService orgService;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getPermissionOrgIds(String userId) {
		SysUser user = userService.findOne(userId);
		// 当前用户机构及以下(默认每个用户都有的权限),这些机构默认即使没有数据权限配置也能看到的机构数据
		List<String> defaultOrgIds = orgService.iterateOrgIdsById(user.getOrgId());
		// 允许的机构sql
		String sql = "select distinct t.orgid from sys_permission_data t\n" +
					 "where 1=1 and t.status = 1\n" +  // t.systemcode = :systemCode -> 1=1
					 "and t.type=:type and t.relateid in (:ids)";
		// 禁止的机构sql
		String sql2 = "select t.orgid from sys_permission_data t\n" + 
					  "where 1=1 and t.status = 0\n" + 
					  "and t.type=:type and t.relateid in (:ids)";
		Map<String, Object> params = new HashMap<String, Object>();
		
		// 整合当前用户对应角色下权限
		List<String> roleIds = userService.getUserRoleByUserId(userId,true);
		if(roleIds.size() > 0){
			params.put("type", SystemWebConstant.PERMISSION_DATA_TYPE_ROLE);
			params.put("ids", roleIds);
			List<String> roleOfAddIds = permissionDataDao.queryListResultBySql(sql, 0, 0, params);
			roleOfAddIds.removeAll(defaultOrgIds);// 默认有权限的机构加上通过数据全配置的机构，防止重复
			defaultOrgIds.addAll(roleOfAddIds);
			List<String> roleOfRemoveIds = permissionDataDao.queryListResultBySql(sql2, 0, 0, params);
			defaultOrgIds.removeAll(roleOfRemoveIds); // 减去角色手动设置无法访问的机构
		}
		// 整合当前用户下权限
		params.put("type", SystemWebConstant.PERMISSION_DATA_TYPE_USER);
		params.put("ids", userId);
		List<String> userOfAddIds = permissionDataDao.queryListResultBySql(sql, 0, 0, params);
		userOfAddIds.removeAll(defaultOrgIds);
		defaultOrgIds.addAll(userOfAddIds);
		List<String> userOfRemoveIds = permissionDataDao.queryListResultBySql(sql2, 0, 0, params);
		defaultOrgIds.removeAll(userOfRemoveIds);
		return defaultOrgIds;
	}

	@Override
	public List<String> getPermissionRemoteOrgCodes(String userId, String systemCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getPermissionOrgTree(String type, String relateId, String systemCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);
		String subSql = "and p.systemcode='"+systemCode+"'";
		if(StringUtils.equals(type, SystemWebConstant.PERMISSION_DATA_TYPE_USER)){
			subSql = "";
		}
		params.put("relateId", relateId);
		String sql = "select t.orgid id,t.parentorgid parentId,t.orgname name,p.status status\n" +
					 "from sys_org t\n" + 
					 "left join SYS_PERMISSION_DATA p on p.orgid = t.orgid\n" + 
					 "and p.type =:type and p.relateid=:relateId "+subSql+"\n" + 
					 "where t.enabled=1\n" + 
					 "order by t.orderno";
		List<Map<String, Object>> tree_datas = permissionDataDao.queryListBySql(sql, params);
		for(Map<String, Object> treeNode:tree_datas){
			treeNode.put("parentId", treeNode.get("parentid"));
			// 添加自定义颜色
			Map<String, String> font = new HashMap<String, String>();
			String status = (treeNode.get("status") != null) ? (treeNode.get("status").toString()) : null;
			if(SystemWebConstant.PERMISSION_DATA_STATUS_VALID.equals(status)){
				font.put("background-color", "#8BC34A");
				treeNode.put("menuType", "del");
			}else if(SystemWebConstant.PERMISSION_DATA_STATUS_INVALID.equals(status)){
				font.put("background-color", "#D4C5C0");
				font.put("color", "white");
				treeNode.put("menuType", "add");
			}else{
				font.put("background-color", "white");
				treeNode.put("menuType", "either");
			}
			treeNode.put("font", font);
		}
		return tree_datas;
	}
	
	@Override
	public List<Map<String, Object>> getFullPermissionOrgTree(String userId, String systemCode) {
		List<Map<String, Object>> orgs = orgService.iterateOrgAllColumnById(null);
		List<String> orgIds = getPermissionOrgIds(userId);
		List<Map<String, Object>> results = new ArrayList<Map<String,Object>>();
		for(Map<String, Object> org:orgs){
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("id", org.get("orgid"));
			result.put("parentId", org.get("parentorgid"));
			result.put("name", org.get("orgname"));
			if(orgIds.contains(org.get("orgid"))){
				Map<String, String> font = new HashMap<String, String>();
				font.put("background-color", "#8BC34A");
				result.put("font", font);
			}
			results.add(result);
		}
		return results;
	}
	
	@Override
	public SysPermssionDataEntity findOne(int type, String relateId, String orgId, String systemCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", type);
		params.put("relateId", relateId);
		params.put("orgId", orgId);
		String subSql = "and t.systemcode= '"+systemCode+"'";
		if(StringUtils.equals(String.valueOf(type),SystemWebConstant.PERMISSION_DATA_TYPE_USER)){
			subSql = "";
		}
		String sql = "select * from sys_permission_data t\n" +
					 "where t.type=:type and t.relateid =:relateId\n" + 
					 "and t.orgid=:orgId "+subSql;
		List<SysPermssionDataEntity> results = permissionDataDao.queryListBySql(sql, params, SysPermssionDataEntity.class);
		if(results != null && results.size() > 0){
			return results.get(0);
		}
		return null;
	}

	@Override
	public void savePermissionData(int type, String relateId, String orgId, int status, String systemCode) {
		// 不存在则新增 已存在则更新
		SysPermssionDataEntity one = findOne(type, relateId, orgId, systemCode);
		if(one != null){
			one.setStatus(status);
			permissionDataDao.save(one);
		}else{
			SysPermssionDataEntity permssionDataEntity = new SysPermssionDataEntity();
			permssionDataEntity.setType(type);
			permssionDataEntity.setRelateId(relateId);
			permssionDataEntity.setOrgId(orgId);
			permssionDataEntity.setStatus(status);
			permssionDataEntity.setSystemCode(systemCode);
			permissionDataDao.save(permssionDataEntity);
		}
	}

	@Override
	public void savePermissionDataInBatch(List<SysPermssionDataEntity> datas) {
		for(SysPermssionDataEntity entity:datas){
			int type = entity.getType();
			String relateId = entity.getRelateId();
			String orgId = entity.getOrgId(); 
			int status = entity.getStatus();
			String systemCode = entity.getSystemCode();
			savePermissionData(type, relateId, orgId, status, systemCode);
		}
	}

	@Override
	public void deletePermissionDataInBatch(int type, String relateId, String systemCode, List<String> orgIds) {
		String sql = "delete from sys_permission_data t\n" +
					 "where t.type=:type and t.relateid =:relateId\n" + 
					 " and t.orgid in (:ids)";
		Map<String, Object> params = new HashMap<String, Object>();
		if(StringUtils.equals(String.valueOf(type), SystemWebConstant.PERMISSION_DATA_TYPE_ROLE)){
			sql += " and t.systemcode=:systemCode ";
			params.put("systemCode", systemCode);
		}
		params.put("type", type);
		params.put("relateId", relateId);
		params.put("ids", orgIds);
		permissionDataDao.executeSql(sql, params);
	}
	
}
