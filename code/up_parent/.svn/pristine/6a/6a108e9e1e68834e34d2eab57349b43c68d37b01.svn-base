
package com.upsoft.login.support.webservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import com.upsoft.login.listener.ConfigListener;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.entity.SysLogEntity;
import com.upsoft.system.entity.SysOrgEntity;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.util.ExceptionFormatUtil;

/**
* Copyright (c) 2016,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：ServiceReceiver.java<br>
* 平台服务接收者<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：null<br>
* 完成日期：2016年5月27日<br>
*/
@Component
public class ServiceReceiver {
	
	private final static Logger logger = Logger.getLogger(ServiceReceiver.class);
	
	/**
	 * 读取配置文件 获得服务接口
	 * @date 2016年5月27日 下午4:40:22
	 * @author null
	 * @return 
	 */
	public static PlatformWebService getServicePort(){
		// 获得服务地址
		String WS_URL = ConfigListener.CasConfig.getProperty("platform_ws_url");
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = PlatformWebServiceImplService.class.getResource(".");
			url = new URL(baseUrl, WS_URL);
		} catch (MalformedURLException e) {
			logger.error("Failed to create URL for the wsdl Location: '"+ WS_URL +"', retrying as a local file");
			logger.error(e.getMessage());
		}
		QName qname = new QName("http://webservice.systemweb.upsoft.com/", "PlatformWebServiceImplService");
		PlatformWebServiceImplService service = new PlatformWebServiceImplService(url, qname);
		PlatformWebService getServicePort = service.getPlatformWebServiceImplPort();
		return getServicePort;
	}
	
	
	
	/**
	 * 针对webservice方法参数不能为null的问题
	 * 指定的参数如果为null 就设置为 ""
	 * @param parameter
	 */
	public static String NVL(String parameter, String repalce){
		if (parameter == null){
			return repalce;
		}else{
			return parameter;
		}
		
	}
	
	/***************************** 登录相关  *****************************/
	
	/**
	 * 登录接口
	 * @date 2016年5月27日 下午4:42:24
	 * @author null
	 * @param loginId
	 * @param password
	 * @param systemCode
	 * @return 
	 */
	public static WSLoginInfoBean login(String loginId, String password, String systemCode) {
		PlatformWebService servicePort = getServicePort();
		String result = servicePort.login(loginId, password, systemCode);
		if(StringUtils.isNotBlank(result)){
			WSLoginInfoBean loginInfo = (WSLoginInfoBean) new XStream().fromXML(result);
			return loginInfo;
		}else{
			return null;
		}
	}
	
	/**
	 * 注销登录
	 * @date 2016年5月27日 下午4:52:33
	 * @author null
	 * @param token
	 * @return 
	 */
	public static boolean logout(String token) {
		PlatformWebService servicePort = getServicePort();
		return servicePort.logout(token);
	}
	
	/**
	 * 验证token
	 * @date 2016年5月27日 下午4:51:08
	 * @author null
	 * @param token
	 * @return 
	 */
	public static boolean checkLogin(String token) {
		PlatformWebService servicePort = getServicePort();
		return servicePort.checkLogin(token);
	}
	
	/**
	 * 根据token获取登录信息
	 * @date 2016年5月27日 下午4:55:31
	 * @author null
	 * @param token
	 * @return 
	 */
	public static WSLoginInfoBean getLoginInfoByToken(String token) {
		PlatformWebService servicePort = getServicePort();
		String result = servicePort.getLoginInfoByToken(token);
		if(StringUtils.isNotBlank(result)){
			WSLoginInfoBean loginInfo = (WSLoginInfoBean) new XStream().fromXML(result);
			return loginInfo;
		}else{
			return null;
		}
	}
	
	/**
	 * 根据token获取登录用户信息
	 * @date 2016年5月27日 下午4:58:25
	 * @author null
	 * @param token
	 * @return 
	 */
	public static SysUser getUserInfoByToken(String token) {
		PlatformWebService servicePort = getServicePort();
		String result = servicePort.getUserInfoByToken(token);
		SysUser sysUser = new Gson().fromJson(result, SysUser.class);
		return sysUser;
	}
	
	/***************************** 后台数据获取  *****************************/
	
	/**
	 * 根据userId获取用户
	 * @date 2016年5月27日 下午4:45:47
	 * @author null
	 * @param userId
	 * @return 
	 */
	public static SysUser getUser(String userId) {
		PlatformWebService servicePort = getServicePort();
		String result = servicePort.getUser(userId);
		SysUser user = new Gson().fromJson(result, SysUser.class);
		return user;
	}
	
	/**
	 * 根据orgId获取机构
	 * @date 2016年5月27日 下午4:49:41
	 * @author null
	 * @param orgId
	 * @return 
	 */
	public static SysOrgEntity getOrg(String orgId) {
		PlatformWebService servicePort = getServicePort();
		String result = servicePort.getOrg(orgId);
		SysOrgEntity org = new Gson().fromJson(result, SysOrgEntity.class);
		return org;
	}
	
	/**
	 * 获取字典data1值{code:value1}
	 * @param parentNodeId
	 * @param code
	 * @param systemCode
	 * @return
	 */
	public static Map<String,Object> getDictValue1(String parentNodeId, String code) {
		PlatformWebService servicePort = getServicePort();
		String result = servicePort.getDictValue1(parentNodeId, ServiceReceiver.NVL(code, ""));
		Map<String,Object> data =  new Gson().fromJson(result, new TypeToken<Map<String, Object>>() {}.getType());
		return data;
	}
	
	/**
	 * 根据字典类型获取下拉格式数据[{key:"code",value:"data1",value2:"data2",value3:"data3"}]
	 * @param parentNodeId
	 * @author hy
	 * @return
	 */
	public static List<Map<String, Object>> getDictSelect(String parentNodeId) {
		PlatformWebService servicePort = getServicePort();
		String result = servicePort.getDictSelect(parentNodeId);
		List<Map<String, Object>> queryList = new Gson().fromJson(result, new TypeToken<List<Map<String, Object>>>() {}.getType());
		return queryList;
	}
	/**
	 * 根据字典类型获取code-data1格式数据，便于查找
	 * @param parentNodeId
	 * @author hy
	 * @return
	 */
	public static Map<String, Object> getDictKeyValueMap(String parentNodeId) {
		PlatformWebService servicePort = getServicePort();
		String result = servicePort.getDictSelect(parentNodeId);
		List<Map<String, Object>> queryList = new Gson().fromJson(result, new TypeToken<List<Map<String, Object>>>() {}.getType());
		Map<String, Object> data = new HashMap<String,Object>();
		for (Map<String, Object> map : queryList) {
			data.put(map.get("key").toString(),map.get("value"));
		}
		return data;
	}
	
	/***************************** 权限相关  *****************************/
	
	/**
	 * 返回指定用户在指定系统下可以访问的机构集合
	 * @param userId
	 * @return
	 */
	public static List<String> getPermissionOrgIds(String userId) {
		PlatformWebService servicePort = getServicePort();
		String result = servicePort.getPermissionOrgIds(userId);
		List<String> orgIds = new Gson().fromJson(result, new TypeToken<List<String>>() {}.getType());
		return orgIds;
	}
	
	/**
	 * 获取按钮权限
	 * 内容字段：{add=1, modify=1, import=1, upfile=1, query=1, downfile=1, export=1, remove=1}
	 * @param permissionNo
	 * @param userId
	 * @param sysCode
	 * @return
	 */
	public static Map<String, Integer> getBtnPermission(String permissionNo, String userId, String sysCode) {
		PlatformWebService servicePort = getServicePort();
		String result = servicePort.getBtnPermission(NVL(permissionNo, StringUtils.EMPTY), userId, sysCode);
		Map<String, Integer> query = new Gson().fromJson(result, new TypeToken<Map<String, Integer>>() {}.getType());
		return query;
	};
	
	/**
	 * 返回用户可访问的结构树(数据权限控制)
	 * 2 parentId和parentCode可以指定根节点，不指定则返回所有
	 * 3 parentId和parentCode同时指定，则parentId生效
	 * @param userId
	 * @param parentId
	 * @param parentCode
	 * @return
	 */
	public static List<Map<String, Object>> getOrgTreeInPermission(String userId,  String parentId, String parentCode) {
		PlatformWebService servicePort = getServicePort();
		String result = servicePort.getOrgTreeInPermission(userId, NVL(parentId,""), NVL(parentCode,""));
		List<Map<String, Object>> tree_datas = new Gson().fromJson(result, new TypeToken<List<Map<String, Object>>>() {}.getType());
		return tree_datas;
	}
	
	/**
	 * 返回用户可访问用户下拉数据(数据权限控制)
	 * @param userId
	 * @return
	 */
	public static List<Map<String, Object>> getUserSelectInPermission(String userId) {
		PlatformWebService servicePort = getServicePort();
		String result = servicePort.getUserSelectInPermission(userId);
		List<Map<String, Object>> datas = new Gson().fromJson(result, new TypeToken<List<Map<String, Object>>>() {}.getType());
		return datas;
	}
	
	/**
	 * 返回用户机构机构树，其中机构不可以被选中只能展开
	 * orgId不为空时，表示查看指定机构用户数据，若该机构不在该用户拥有的权限机构内，则返回空集合
	 * @date 2017年9月12日 下午5:52:17
	 * @param userId
	 * @param orgId 
	 * @author 胡毅
	 * @return
	 */
	@Deprecated
	public static List<Map<String, Object>> getUserOrgTreeInPermission(String userId,String orgId) {
		PlatformWebService servicePort = getServicePort();
		String result = servicePort.getUserOrgTreeInPermission(userId, NVL(orgId, StringUtils.EMPTY));
		List<Map<String, Object>> datas = new Gson().fromJson(result, new TypeToken<List<Map<String, Object>>>() {}.getType());
		return datas;
	}
	
	/**
	 * 根据用所在单位（渝西特指登录用户的公司或者厂站Id,其他系统不限制机构ID）
	 * @date 2017年9月12日 下午8:01:29
	 * @author 胡毅
	 * @param orgId
	 * @param userId 如果不为空，这排除userId
	 * @return
	 */
	public static List<Map<String, Object>> getUserOrgTreeByOrgId(String orgId,String userId ) {
		PlatformWebService servicePort = getServicePort();
		String result = servicePort.getUserOrgTreeByOrgId(orgId,NVL(userId,""));
		List<Map<String, Object>> datas = new Gson().fromJson(result, new TypeToken<List<Map<String, Object>>>() {}.getType());
		return datas;
	}

	/***************************** 菜单相关  *****************************/
	/**
	 * 获取系统下菜单:树形结构,管理员用户不进行权限控制
	 * @date 2017年8月29日 下午4:16:03
	 * @author 胡毅
	 * @param userId
	 * @param systemCode
	 * @return
	 */
	public static List<Map<String, Object>> queryMenusBySysCodeAndUserId(String userId, String systemCode) {
		PlatformWebService servicePort = getServicePort();
		String result = servicePort.queryMenusBySysCodeAndUserId(userId, systemCode);
		List<Map<String, Object>> queryList = new Gson().fromJson(result, new TypeToken<List<Map<String, Object>>>() {}.getType());
		return queryList;
	}
	
	/**
	 * 根据用户ID和parentMenuId获取子菜单
	 * @date 2017年8月29日 下午3:05:39
	 * @author 胡毅
	 * @param menuId
	 * @param userId
	 * @return
	 */
	public static List<Map<String, Object>> queryMenusByMenuIdAndUserId(String menuId, String userId) {
		PlatformWebService servicePort = getServicePort();
		String result = servicePort.queryMenusByMenuIdAndUserId(menuId, userId);
		List<Map<String, Object>> queryList = new Gson().fromJson(result, new TypeToken<List<Map<String, Object>>>() {}.getType());
		return queryList;
	}
	
	/**
	 * 在queryMenusBySysCodeAndUserId上查询折叠式菜单数据
	 * @date 2017年8月29日 下午4:16:09
	 * @author 胡毅
	 * @param userId
	 * @param systemCode
	 * @return
	 */
	public static List<Map<String, Object>> queryAccordionMenusByCondition(String userId, String systemCode) {
		PlatformWebService servicePort = getServicePort();
		String result = servicePort.queryAccordionMenusByCondition(userId, systemCode);
		List<Map<String, Object>> queryList = new Gson().fromJson(result, new TypeToken<List<Map<String, Object>>>() {}.getType());
		return queryList;
	}
	
	/**
	 * 根据机构ID获取当前机构以及下属所有的机构，全部字段
	 * @date 2017年8月29日 下午4:39:07
	 * @author 胡毅
	 * @param orgId
	 * @return
	 */
	public static List<Map<String, Object>> iterateOrgById(String orgId) {
		PlatformWebService servicePort = getServicePort();
		String result = servicePort.iterateOrgById(orgId);
		List<Map<String, Object>> queryList = new Gson().fromJson(result, new TypeToken<List<Map<String, Object>>>() {}.getType());
		return queryList;
	}
	
	/**
	 * 根据机构编码获取当前机构以及下属所有的机构的编码，仅编码
	 * @date 2017年8月29日 下午4:27:13
	 * @author 胡毅
	 * @param orgCode
	 * @return
	 */
	public static List<String> getChildOrgCodes(String orgCode) {
		PlatformWebService servicePort = getServicePort();
		String result = servicePort.getChildOrgCodes(orgCode);
		List<String> queryList = new Gson().fromJson(result, new TypeToken<List<String>>(){}.getType());
		return queryList;
	}

	/**
	 * 根据用户ID获取用户拥有的角色code
	 * @date 2017年8月29日 下午4:20:19
	 * @author 胡毅
	 * @param userId
	 * @return
	 */
	public static List<Map<String, Object>> getSystemCodeByUserIdInRole(String userId) {
		PlatformWebService servicePort = getServicePort();
		String roleListStr = servicePort.getSystemCodeByUserIdInRole(userId);
		List<Map<String, Object>> roleList = new Gson().fromJson(roleListStr, new TypeToken<List<Map<String, Object>>>() {}.getType());
		return roleList;
	}

	/**
	 *  获取所有已定义的系统编码和名称
	 * @date 2017年8月29日 下午4:20:42
	 * @author 胡毅
	 * @return
	 */
	public static List<Map<String, Object>> getDefinedSystemCodeAndName() {
		PlatformWebService servicePort = getServicePort();
		String definedSysListStr = servicePort.getDefinedSystemCodeAndName();
		List<Map<String, Object>> sysList = new Gson().fromJson(definedSysListStr, new TypeToken<List<Map<String, Object>>>() {}.getType());
		return sysList;
	}

	/**
	 * 根据用户ID从菜单视图中获取系统编码和名称，用于后台系统左侧菜单的tab数据
	 * @date 2017年8月29日 下午4:26:34
	 * @author 胡毅
	 * @param userId
	 * @return
	 */
	public static String querySysCodeAndNameByUserId(String userId) {
		PlatformWebService servicePort = getServicePort();
		String jsonlist = servicePort.querySysCodeAndNameByUserId(userId);
		return jsonlist;
	}

	/**
	 * 根据自定义sql和参数查询后台数据（只用作查询）
	 * @date 2017年9月2日 上午11:50:16
	 * @author 胡毅
	 * @param sql
	 * @param params 
	 * @return
	 */
	public static List<Map<String, Object>> getListDataBySql(String sql, Map<String,Object> params){
		PlatformWebService servicePort = getServicePort();
		if(null==params){
			params = new HashMap<String,Object>();
		}
		String paramsStr = new Gson().toJson(params);
		String result = servicePort.getListDataBySql(sql, paramsStr);
		List<Map<String, Object>> queryList = new Gson().fromJson(result, new TypeToken<List<Map<String, Object>>>() {}.getType());
		return queryList;
	}
	
	public static void saveLoginLog(SysLogEntity log){
		PlatformWebService servicePort = getServicePort();
		String loginLogStr = new Gson().toJson(log);
		try {
			servicePort.saveLoginLog(loginLogStr);
		} catch (Exception e) {
			logger.error("通过webservice保存登录日志时出错："+ExceptionFormatUtil.formatExceptionTrace(e));
		}
	}
	/**
	 * 
	 * @date 2017年9月4日 上午10:10:42
	 * @author 胡毅
	 * @param loginUser
	 * @param request
	 * @param _this
	 * @param ct
	 * @param content [modelTypeCode,modelTypeValue,optTypeCode,optTypeName,content,syscode,sysName]
	 */
	public static void saveLog(SysUser loginUser,HttpServletRequest request,Class _this,Thread ct,String[] content){
		Gson g = new Gson();
		String contentStr = g.toJson(content);
		String userStr = g.toJson(loginUser);
		PlatformWebService servicePort = getServicePort();
		try {
			servicePort.saveLog(userStr, request.getRequestURI(), _this.getCanonicalName(), ct.getStackTrace()[1].getMethodName(), contentStr);
//		    logCache.cacheLog(loginUser, request.getRequestURI(), this.getClass().getCanonicalName(), 
//				Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_UPLOAD_ORDER_PROCESS_INFO, 
//				SystemLogConstant.OPT_MODEL_TYPE_VALUE_UPLOAD_ORDER_PROCESS_INFO,SystemLogConstant.OPT_TYPE_CODE_ADDINFO, SystemLogConstant.OPT_TYPE_NAME_ADDINFO, 
//				uploadJosnStr, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
		} catch (Exception e) {
			logger.error("通过webservice保存操作日志时出错："+ExceptionFormatUtil.formatExceptionTrace(e));
		}
	}
	
	public static boolean validUserPwd(String loginId, String oldPwd, String inputPwd){
		PlatformWebService servicePort = getServicePort();
		return Boolean.valueOf(servicePort.validUserPwd(loginId, oldPwd, inputPwd));
	}
	
	public static boolean saveUser(SysUser user){
		PlatformWebService servicePort = getServicePort();
		try {
			servicePort.saveUser(new Gson().toJson(user));
			return true;
		} catch (Exception e) {
			logger.error("通过webservice更新用户密码时出错："+ExceptionFormatUtil.formatExceptionTrace(e));
			return false;
		}
	}
	
}
