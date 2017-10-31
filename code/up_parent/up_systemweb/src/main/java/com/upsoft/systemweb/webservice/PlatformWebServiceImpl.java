/*
 * Created on 2015年1月21日 下午4:28:44
 * Copyright (c) 重庆扬讯软件技术有限公司  All Rights Reserved.
 * http://www.upsoft.com.cn
 *
 * This software is the confidential and proprietary information of UPSoft.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with UPSoft.
 */
package com.upsoft.systemweb.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.entity.DictTreeDataEntity;
import com.upsoft.system.entity.SysLogEntity;
import com.upsoft.system.entity.SysOrgEntity;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.service.CommonService;
import com.upsoft.system.support.cache.UpSysCache;
import com.upsoft.systemweb.dao.SystemDefineDao;
import com.upsoft.systemweb.service.DictTreeDataService;
import com.upsoft.systemweb.service.LoginService;
import com.upsoft.systemweb.service.MenuService;
import com.upsoft.systemweb.service.OrgService;
import com.upsoft.systemweb.service.PermissionDataService;
import com.upsoft.systemweb.service.PermissionService;
import com.upsoft.systemweb.service.SysLogService;
import com.upsoft.systemweb.service.UserService;
import com.upsoft.systemweb.support.SystemLogServiceCache;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 摘要：简要描述本文件的内容<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：蒋迪<br>
 * 完成日期：2015年1月21日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：蒋迪<br>
 * 完成日期：2015年1月21日<br>
 */
@WebService
@SOAPBinding(style = Style.RPC)
@Service("platformWS")
public class PlatformWebServiceImpl implements PlatformWebService {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private OrgService orgService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DictTreeDataService dictTreeDataService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private PermissionDataService permissionDataService;
	
	@Autowired
	private SystemDefineDao systemDefineDao;
	
	@Autowired
	private SysLogService sysLogService;
	
	@Autowired
	private SystemLogServiceCache systemLogServiceCache;
	
	/***************************** 登录相关  *****************************/
	
	@Override
	public String login(String loginId, String password, String systemCode) {
		WSLoginInfoBean bean = loginService.login(loginId, password, systemCode);
		// 登陆信息过于复杂 使用xml传递
		return new XStream().toXML(bean);
	}
	
	@Override
	public boolean logout(String token) {
		return loginService.logout(token);
	}
	
	@Override
	public boolean checkLogin(String token) {
		if (StringUtils.isEmpty(token))	return false;
		return UpSysCache.getCache(token)!=null;
	}
	
	@Override
	public String getLoginInfoByToken(String token) {
		WSLoginInfoBean bean = (WSLoginInfoBean)UpSysCache.getCache(token);
		if(bean != null){
			return new XStream().toXML(bean);
		}
		return new String();
	}
	
	@Override
	public String getUserInfoByToken(String token) {
		WSLoginInfoBean bean = (WSLoginInfoBean)UpSysCache.getCache(token);
		if(bean!=null){
			Gson gson = new Gson();
			return gson.toJson(bean.getUser());
		}
		return new String();
	}
	
	/***************************** 后台数据获取  *****************************/
	
	@Override
	public String getUser(String userId) {
		SysUser user = commonService.findOne(SysUser.class, userId);
		Gson gson = new Gson();
		return gson.toJson(user);
	}
	
	@Override
	public String getOrg(String orgId) {
		SysOrgEntity org = commonService.findOne(SysOrgEntity.class, orgId);
		Gson gson = new Gson();
		return gson.toJson(org);
	}
	
	@Override
	public String getDictValue1(String parentNodeId, String code) {
		DictTreeDataEntity result =dictTreeDataService.queryByParentIdAndNodeCode(parentNodeId, code);
		Map<String,Object> data = new HashMap<String,Object>();
		if(null!=result){
			data.put(result.getCode(), result.getData1());
		}
		return new Gson().toJson(data);
	}
	
	
	@Override
	public String getDictSelect(String parentNodeId) {
		List<DictTreeDataEntity> result = dictTreeDataService.queryDataByParentId(parentNodeId);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for (DictTreeDataEntity dict : result) {
			Map<String,Object> tmp = new HashMap<String,Object>();
			tmp.put("key", dict.getCode());
			tmp.put("value",StringUtils.isBlank(dict.getData1())?StringUtils.EMPTY:dict.getData1());
			tmp.put("value2",StringUtils.isBlank(dict.getData2())?StringUtils.EMPTY:dict.getData2());
			tmp.put("value3",StringUtils.isBlank(dict.getData3())?StringUtils.EMPTY:dict.getData3());
			list.add(tmp);
		}
		Gson gson = new Gson();
		return gson.toJson(list);
	}
//	
	/***************************** 权限相关  **************** *************/
	
	@Override
	public String getPermissionOrgIds(String userId) {
		List<String> orgIds = permissionDataService.getPermissionOrgIds(userId);
		Gson gson = new Gson();
		return gson.toJson(orgIds);
	}
	
	@Override
	public String getBtnPermission(String permissionNo, String userId, String sysCode) {
		Gson gson = new Gson();
		Map<String, Integer> btnAuths = permissionService.queryMenuBtnPermission(permissionNo, userId, sysCode);
		return gson.toJson(btnAuths);
	};
	
	@Override
	public String getOrgTreeInPermission(String userId, String parentId, String parentCode) {
		Gson gson = new Gson();
		List<Map<String, Object>> tree_datas = orgService.getOrgTreeInPermission(userId, parentId, parentCode);
		return gson.toJson(tree_datas);
	}
	
	@Override
	public String getUserSelectInPermission(String userId) {
		List<Map<String, Object>> result = userService.getUserSelectInPermission(userId);
		Gson gson = new Gson();
		return gson.toJson(result);
	}

	/***************************** 菜单相关  *****************************/
	@Override
	public String queryMenusByMenuIdAndUserId(String menuId, String userId) {
		List<Map<String, Object>> queryList = menuService.queryMenusByMenuIdAndUserId(menuId, userId);
		Gson gson = new Gson();
		return gson.toJson(queryList);
	}

	@Override
	public String queryMenusBySysCodeAndUserId(String userId, String systemCode) {
		List<Map<String, Object>> queryList = menuService.queryMenusByUserIdAndSystemCode(userId,systemCode );
		Gson gson = new Gson();
		return gson.toJson(queryList);
	}
	
	@Override
	public String queryAccordionMenusByCondition(String userId,String systemCode) {
		List<Map<String, Object>> queryList = menuService.queryAccordionMenusByUserIdAndSystemCode(userId, systemCode);
		Gson gson = new Gson();
		return gson.toJson(queryList);
	}

	@Override
	public String iterateOrgById(String orgId) {
		List<Map<String, Object>> queryList = orgService.iterateOrgAllColumnById(orgId);
		Gson gson = new Gson();
		return gson.toJson(queryList);
	}
	
	@Override
	public String getChildOrgCodes(String orgCode) {
		List<Map<String, Object>> orgs = orgService.iterateOrgAllColumnByCode(orgCode);
		List<String> codes = new ArrayList<String>();
		for(Map<String, Object> org:orgs){
			codes.add((String) org.get("orgcode"));
		}
		Gson gson = new Gson();
		return gson.toJson(codes);
	}

	@Override
	public String getSystemCodeByUserIdInRole(String userId) {
		List<Map<String, Object>> list = userService.getSystemCodeByUserIdInRole(userId);
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	@Override
	public String getDefinedSystemCodeAndName() {
		 List<Map<String, Object>> list = systemDefineDao.queryListBySql("SELECT SYSTEMCODE,SYSTEMNAME FROM SYS_SYSTEM_DEFINE", new HashMap<String, Object>());
		 Gson gson = new Gson();
		return gson.toJson(list);
	}

	@Override
	public String querySysCodeAndNameByUserId(String userId) {
		 String jsonlist = menuService.querySysMenusByUserId(userId);
		 return jsonlist;
	}

	@Override
	public String getListDataBySql(String sql, String paramsStr) {
		Map<String,Object> params =  new Gson().fromJson(paramsStr, new TypeToken<Map<String, Object>>() {}.getType());
		if(null==params){
			params = new HashMap<String,Object>();
		}
		List<Map<String, Object>> list = systemDefineDao.queryListBySql(sql, params);
		
		return new Gson().toJson(list);
	}

	@Override
	public void saveLoginLog(String loginLog) {
		SysLogEntity log = new Gson().fromJson(loginLog, SysLogEntity.class);
		sysLogService.save(log);
	}

	@Override
	public void saveLog(String loginUserStr,String uri,String className,String methodName,String contentStr) {
		Gson g = new Gson();
		SysUser user  = g.fromJson(loginUserStr, SysUser.class);
		String[] content = g.fromJson(contentStr, new TypeToken<String[]>() {}.getType());
		systemLogServiceCache.cacheLog(user, uri, className, methodName, content[0], content[1], content[2], content[3], content[4], content[5], content[6]);

	}

	@Override
	public String getUserOrgTreeByOrgId(String orgId,String userId) {
		Map<String,Object> pars = new HashMap<String,Object>();
		if(StringUtils.isBlank(orgId)){
			pars.put("parentOrgId", "0");
		}else{
			pars.put("parentOrgId", orgId);
		}
		if(StringUtils.isNotBlank(userId)){
			pars.put("userId", userId);
		}
		List<Map<String, Object>> list = userService.findOrgUserTree(pars);
		return new Gson().toJson(list);
	}

	@Override
	public String getUserOrgTreeInPermission(String userId,String orgId) {
		// 获取可访问的机构Id列表
		List<String> orgIdList =  permissionDataService.getPermissionOrgIds(userId);
		if(StringUtils.isNotBlank(orgId)){
			List<String> appointedList = orgService.iterateOrgIdsById(orgId);
			List<String> permissionedOrgList = new ArrayList<String>();
			for (String id : appointedList) {
				if(orgIdList.contains(id)){
					permissionedOrgList.add(id);
				}
			}
			orgIdList = null;
			orgIdList = permissionedOrgList;
		}
		List<Map<String,Object>> list = userService.findOrgUserTreeInOrgList(orgIdList);
		return new Gson().toJson(list);
	}

	@Override
	public String validUserPwd(String loginId, String oldPwd, String inputPwd) {
		boolean f = userService.validUserPwd(loginId, oldPwd, inputPwd);
		return String.valueOf(f);
	}

	@Override
	public void saveUser(String userStr) {
		SysUser user = new Gson().fromJson(userStr, SysUser.class);
		userService.saveUser(user);
	}

}
