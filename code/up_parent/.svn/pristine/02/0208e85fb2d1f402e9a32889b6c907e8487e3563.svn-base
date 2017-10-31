package com.upsoft.systemweb.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.util.WebUtils;

import com.google.gson.Gson;
import com.upsoft.login.controller.BaseController;
import com.upsoft.login.support.webservice.SysUtils;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.constant.CommonConstant;
import com.upsoft.system.constant.DictConstant;
import com.upsoft.system.constant.SystemLogConstant;
import com.upsoft.system.entity.DictTreeDataEntity;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.support.annotation.LogAnnotation;
import com.upsoft.system.util.EncodingUtil;
import com.upsoft.systemweb.service.DictTreeDataService;
import com.upsoft.systemweb.service.UserService;
import com.upsoft.systemweb.support.SystemLogServiceCache;

/**
 * 
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 * 
 * 文件名称：UserController.java<br>
 * 摘要：用户Controller<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：TW<br>
 * 完成日期：2015年1月23日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：TW<br>
 * 完成日期：2015年1月23日<br>
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	// @Autowired
	// private LogService logService;

	@Autowired
	private DictTreeDataService dictTreeDataService;

	@Autowired
	private SystemLogServiceCache systemLogServiceCache;

	/**
	 * 进入用户管理主页面
	 * 
	 * @date 2015年1月23日 下午4:35:33
	 * @author TW
	 */
	@LogAnnotation(FunctionName = "用户管理功能")
	@RequestMapping("/init")
	public String init(HttpServletRequest request, ModelMap map) {
		// 预置页面首次进入默认查询参数
		Map<String, Object> pars = new HashMap<String, Object>();
		return getUserList(request, map, pars);
	}

	/**
	 * 跳转用户列表页面
	 * 
	 * @date 2015年1月23日 下午4:36:42
	 * @author TW
	 */
	@RequestMapping("/getUserList")
	public String getUserList(HttpServletRequest request, ModelMap map, Map<String, Object> pars) {
		super.findMenuResourcePermission(request, map);
		// 添加日志
		String optContent = "查询系统用户";
		SysUser user = SysUtils.getLoginSysUser(request);
		systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(),
				Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_YHGL,
				SystemLogConstant.OPT_MODEL_TYPE_NAME_YHGL, SystemLogConstant.OPT_TYPE_CODE_QUERYINFO, SystemLogConstant.OPT_TYPE_NAME_QUERYINFO,
				optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
		return "/WEB-INF/jsp/user/main";
	}

	/**
	 * 获取用户数据
	 * 
	 * @date 2015年1月23日 下午4:38:16
	 * @author TW
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/getUserData")
	public Map<String, Object> getUserData(HttpServletRequest request, ModelMap map, String parentOrgId) {

		PageBean pb = new PageBean(request);
		// 获取页面查询参数
		Map<String, Object> pars = new HashMap<String, Object>();
		String userName = WebUtils.findParameterValue(request, "userName");
		String loginId = WebUtils.findParameterValue(request, "loginId");
		String occupation = WebUtils.findParameterValue(request, "occupation");
		pars.put("userName", userName);
		pars.put("loginId", loginId);
		pars.put("occupation", occupation);
		pars.put("parentOrgId", parentOrgId);
		pars.put("status", CommonConstant.STATUS_VALID + "," + CommonConstant.STATUS_DISABLE);
		// 查询结果
		Map<String, Object> userListAndCount = userService.getUserListAndCount(pars, pb);
		List<Map<String, Object>> list = (List<Map<String, Object>>) userListAndCount.get("rows");
		long count = (Long) userListAndCount.get(PageBean.TOTAL);

		// 组装查询结果
		Map<String, Object> resultData = new HashMap<String, Object>();
		resultData.put("pager.pageNo", 1);
		resultData.put("pager.pageSize", 20);
		resultData.put("pager.totalRows", count);
		resultData.put("rows", list);
		return resultData;
	}

	/**
	 * 跳转到新增用户页面
	 * 
	 * @date 2015年1月23日 下午4:40:25
	 * @author TW
	 */
	@RequestMapping("/toAddUser")
	public String toAddUser(HttpServletRequest request, ModelMap map, String path, String orgId) {

		// 引用公共常量
		super.putDictConstant(map);

		map.addAttribute("orderNo", userService.getMaxOrderNo() + 1);
		map.addAttribute("defaultOrgId", orgId);

		return "/WEB-INF/jsp/user/addUser";
	}

	/**
	 * 跳转到用户修改页面
	 * 
	 * @date 2015年1月27日 下午3:43:52
	 * @author TW
	 * @param request
	 * @param map
	 * @param userId
	 *            用户Id
	 * @return
	 */
	@RequestMapping("/toModifyUser")
	public String toUpdUser(HttpServletRequest request, ModelMap map, String userId) {
		SysUser user = SysUtils.getLoginSysUser(request);
		if (user.getLoginId().equals("admin")) {
			map.addAttribute("isRoot", true);
		} else {
			map.addAttribute("isRoot", false);
		}
		map.addAttribute("userId", userId);
		// 引用公共常量
		super.putDictConstant(map);

		// 获取用户信息
		if (userId != null && StringUtils.isNotBlank(userId)) {
			Map<String, Object> pars = new HashMap<String, Object>();
			pars.put("userId", userId);
			List<Map<String, Object>> list = userService.getUserList(pars, 0, 0);
			map.addAttribute("user", list.get(0));
		}

		return "/WEB-INF/jsp/user/modifyUser";
	}

	/**
	 * 查看用户信息
	 * 
	 * @date 2015年1月27日 下午3:37:08
	 * @author TW
	 * @param request
	 * @param map
	 * @param userId
	 *            用户Id
	 * @return
	 */
	@RequestMapping("/toViewUser")
	public String toViewUser(HttpServletRequest request, ModelMap map, String userId) {
		Map<String, Object> userInfo = new HashMap<String, Object>();
		// 获取用户信息
		if (userId != null && StringUtils.isNotBlank(userId)) {
			Map<String, Object> pars = new HashMap<String, Object>();
			pars.put("userId", userId);
			List<Map<String, Object>> list = userService.getUserList(pars, 0, 0);
			userInfo = list.get(0);
			map.addAttribute("user", userInfo);
		}

		String userTypeStr = "";
		List<DictTreeDataEntity> userTypes = dictTreeDataService.queryDataByTreeId(DictConstant.USER_TYPE.getValue());
		for (DictTreeDataEntity temp : userTypes) {
			if (userInfo.get("usertype") != null) {
				if (temp.getCode().toString().equals(userInfo.get("usertype").toString())) {
					userTypeStr = temp.getData1();
				}
			}
		}
		map.addAttribute("userTypeStr", userTypeStr);

		return "/WEB-INF/jsp/user/viewUser";
	}

	/**
	 * 保存用户
	 * 
	 * @date 2015年1月23日 下午4:42:20
	 * @author TW
	 * @param request
	 * @param map
	 * @param user
	 *            用户
	 */
	@ResponseBody
	@RequestMapping("/doAddUser")
	public Map<String, Object> doAddUser(HttpServletRequest request, ModelMap map, SysUser user) {

		// 规避关键字，别名传递页面值到后台
		String validDate = WebUtils.findParameterValue(request, "_validDate");
		String birthday = WebUtils.findParameterValue(request, "_birthday");
		String userType = WebUtils.findParameterValue(request, "_userType");
		String occupation = WebUtils.findParameterValue(request, "occupation");

		// 别名值替换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (validDate != null && StringUtils.isNotBlank(validDate)) {
				user.setValidDate(sdf.parse(validDate));
			}
			if (birthday != null && StringUtils.isNotBlank(birthday)) {
				user.setBirthday(sdf.parse(birthday));
			}
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		if (StringUtils.isNotBlank(userType)) {
			user.setUserType(Integer.valueOf(userType));
		} else {
			user.setUserType(3);// 默认普通用户
		}
		if (StringUtils.isNotBlank(occupation)) {
			user.setOccupation(Integer.valueOf(occupation));
		}
		// 保存
		try {
			// logService.saveLog(request, user);
			userService.saveUser(user);
			// 添加日志
			String optContent = "修改名称为" + user.getUserName() + "的用户";
			SysUser users = SysUtils.getLoginSysUser(request);
			systemLogServiceCache.cacheLog(users, request.getRequestURI(), this.getClass().getCanonicalName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_YHGL,
					SystemLogConstant.OPT_MODEL_TYPE_NAME_YHGL, SystemLogConstant.OPT_TYPE_CODE_EDITINFO, SystemLogConstant.OPT_TYPE_NAME_EDITINFO,
					optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", "操作成功！");
		return result;
	}

	/**
	 * 根据用户Id，删除用户
	 * 
	 * @date 2015年1月23日 下午5:51:17
	 * @author TW
	 * @param request
	 * @param map
	 * @param ids
	 *            用户Id
	 */
	@ResponseBody
	@RequestMapping("/delUser")
	public Map<String, Object> delUser(HttpServletRequest request, ModelMap map, String ids) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			userService.deleteUser(ids);
			result.put("status", 1);
			// 添加日志
			String optContent = "删除id为" + ids + "的用户";
			SysUser users = SysUtils.getLoginSysUser(request);
			systemLogServiceCache.cacheLog(users, request.getRequestURI(), this.getClass().getCanonicalName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_YHGL,
					SystemLogConstant.OPT_MODEL_TYPE_NAME_YHGL, SystemLogConstant.OPT_TYPE_CODE_DELINFO, SystemLogConstant.OPT_TYPE_NAME_DELINFO,
					optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.put("status", 0);
		}
		return result;
	}

	/**
	 * 批量删除用户，以逗号分隔多个用户Id
	 * 
	 * @date 2015年1月23日 下午8:58:53
	 * @author TW
	 * @param request
	 * @param map
	 * @param ids
	 *            以逗号分隔多个用户Id
	 */
	@ResponseBody
	@RequestMapping("/delUsers")
	public Map<String, Object> delUsers(HttpServletRequest request, ModelMap map, String ids) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			userService.deleteSplitUsers(ids);
			result.put("status", 1);
			// 添加日志
			String optContent = "删除id分别为" + ids + "的用户";
			SysUser users = SysUtils.getLoginSysUser(request);
			systemLogServiceCache.cacheLog(users, request.getRequestURI(), this.getClass().getCanonicalName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_YHGL,
					SystemLogConstant.OPT_MODEL_TYPE_NAME_YHGL, SystemLogConstant.OPT_TYPE_CODE_DELINFO, SystemLogConstant.OPT_TYPE_NAME_DELINFO,
					optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.put("status", 0);
		}
		return result;
	}

	/**
	 * Ajax 验证用户账户是否已存在
	 * 
	 * @date 2015年1月27日 下午3:42:23
	 * @author TW
	 * @param validateValue
	 *            待验证的值（账户名）
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/validLoginId")
	public Map<String, Object> validLoginId(String validateValue) {

		// 数据库已存在数据条数查询
		Map<String, Object> pars = new HashMap<String, Object>();
		pars.put("loginId", validateValue);
		long count = userService.getUserListCount(pars);

		// 结果判断
		Map<String, Object> result = new HashMap<String, Object>();
		if (count > 0) {
			result.put("valid", false);
		} else {
			result.put("valid", true);
		}

		// 返回指定格式结果
		Map<String, Object> resultJson = new HashMap<String, Object>();
		resultJson.put("validateResult", result);
		return resultJson;
	}

	/**
	 * 打开修改密码页面内容
	 * 
	 * @date 2015年2月3日 上午9:40:26
	 * @author 屈锐华
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/toAlterUserPwd")
	public String toAlterUserPwd(HttpServletRequest request, ModelMap map, String path, String userId) {
		// 引用公共常量
		super.putDictConstant(map);

		// 获取用户信息
		if (StringUtils.isNotBlank(userId)) {
			SysUser user = this.userService.findOne(SysUser.class, userId);
			try {
				user.setLoginId(EncodingUtil.encode(user.getLoginId(), "utf-8"));
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage());
			}
			map.put("alterUser", user);
			return "/WEB-INF/jsp/user/alterUserPwd";
		} else {
			return "";
		}
	}

	/**
	 * 执行修改用户密码操作
	 * 
	 * @date 2015年2月2日 下午5:32:20
	 * @author 屈锐华
	 * @param userId
	 * @param inputPwd
	 * @param newPwd
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doAlterUserPwd")
	public Map<String, Object> doAlterUserPwd(HttpServletRequest request, ModelMap map, String userId, String inputPwd, String newPwd) {

		Map<String, Object> result = new HashMap<String, Object>();
		String msg = "操作成功！";
		if (!StringUtils.isNotBlank(newPwd)) {
			msg = "新密码不能为空！";
			result.put("msg", msg);
			return result;
		}

		if (StringUtils.isNotBlank(userId)) {

			SysUser user = this.userService.findOne(SysUser.class, userId);
			if (user != null) {
				// 验证输入的密码经MD5加密后，与原密码是否一致
				boolean flag = this.userService.validUserPwd(user.getLoginId(), user.getPassword(), inputPwd);
				if (flag) {
					user.setPassword(newPwd);
					this.userService.saveUser(user);
					// 添加日志
					String optContent = "修改名称为" + user.getUserName() + "的用户密码";
					SysUser users = SysUtils.getLoginSysUser(request);
					systemLogServiceCache.cacheLog(users, request.getRequestURI(), this.getClass().getCanonicalName(),
							Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_YHGL,
							SystemLogConstant.OPT_MODEL_TYPE_NAME_YHGL, SystemLogConstant.OPT_TYPE_CODE_EDITINFO,
							SystemLogConstant.OPT_TYPE_NAME_EDITINFO, optContent, SystemLogConstant.UP_SYSTEMWEB_CODE,
							SystemLogConstant.UP_SYSTEMWEB_NAME);
				} else {
					msg = "旧密码验证失败！";
				}

			} else {
				msg = "操作失败！";
			}

		} else {
			msg = "操作失败！";
		}

		result.put("msg", msg);
		return result;
	}

	/**
	 * 用户修改密码时，验证输入的密码经MD5加密后，与原密码是否一致
	 * 
	 * @date 2015年2月2日 下午4:07:30
	 * @author 屈锐华
	 * @param loginId
	 * @param oldPwd
	 * @param inputPwd
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/validUserPwd")
	public Map<String, Object> validUserPwd(HttpServletRequest request, ModelMap map, String loginId, String oldPwd, String validateValue) {
		// 状态标识，为1表示验证成功，反之验证失败。
		Map<String, Object> validateResult = new HashMap<String, Object>();
		Map<String, Object> valid = new HashMap<String, Object>();
		validateResult.put("validateResult", valid);
		boolean flag = this.userService.validUserPwd(loginId, oldPwd, validateValue);
		valid.put("valid", flag);
		return validateResult;
	}

	/**
	 * 跳转到用户角色选择器界面
	 * 
	 * @date 2015年2月11日 下午6:47:19
	 * @author TW
	 * @param request
	 * @param map
	 * @param userId
	 *            用户Id
	 * @return
	 */
	@RequestMapping("/toRelateRole")
	public String toRelateRole(HttpServletRequest request, ModelMap map, String userId) {

		SysUser user = userService.findOne(SysUser.class, userId);
		map.addAttribute("userName", user.getUserName());
		map.addAttribute("userId", userId);

		map.addAttribute("relateRole", this.getUserRoleRelationJsonData(userId));

		return "/WEB-INF/jsp/user/relateRole";
	}

	/**
	 * 保存用户角色关联关系
	 * 
	 * @date 2015年2月11日 下午6:47:47
	 * @author TW
	 * @param userId
	 *            用户Id
	 * @param roleIds
	 *            以逗号分隔的一个或多个roleId
	 */
	@ResponseBody
	@RequestMapping("/doSaveUserRole")
	public void doSaveUserRole(String userId, String roleIds) {
		userService.saveUserRole(userId, roleIds);
	}

	/**
	 * 私有方法：获取用户角色选择器需要的Json格式数据
	 * 
	 * @date 2015年2月11日 下午6:48:38
	 * @author TW
	 * @param userId
	 *            用户Id
	 * @return
	 */
	private String getUserRoleRelationJsonData(String userId) {

		// 系统角色
		List<Map<String, Object>> allRoleList = userService.getSystemRole();
		// 用户已经拥有的角色
		List<Map<String, Object>> userRoleList = userService.getUserRoleByUserId(userId);

		// 未关联的角色列表
		List<Map<String, Object>> fromList = new ArrayList<Map<String, Object>>();

		// 已关联的角色列表
		List<Map<String, Object>> toList = new ArrayList<Map<String, Object>>();

		// 分离未选角色（fromList）和已选角色（toList）两中数据
		boolean isUserRoleFlag = false;
		for (Map<String, Object> roleMap : allRoleList) {
			isUserRoleFlag = false;
			for (Map<String, Object> userRoleMap : userRoleList) {
				if (roleMap.get("id").equals(userRoleMap.get("roleid"))) {
					toList.add(roleMap);
					isUserRoleFlag = true;
					break;
				}
			}
			if (!isUserRoleFlag) {
				fromList.add(roleMap);
			}
		}
		Map<String, Object> relateRoleMap = new HashMap<String, Object>();
		relateRoleMap.put("fromList", fromList);
		relateRoleMap.put("toList", toList);

		return this.relateRoleMapJsonFormat(relateRoleMap);

	}

	/**
	 * 私有方法：将List<Map> 形式的用户角色关系转化为Json字符串
	 * 
	 * @date 2015年2月11日 下午6:49:27
	 * @author TW
	 * @param relateRoleMap
	 *            List<Map>格式的用户角色数据
	 * @return
	 */
	private String relateRoleMapJsonFormat(Map<String, Object> relateRoleMap) {
		// 将List<Map>转化为Json格式
		Gson gson = new Gson();
		String jsonData = gson.toJson(relateRoleMap);
		// 大小写替换
		jsonData = jsonData.replaceAll("oldparentid", "oldParentId");
		jsonData = jsonData.replaceAll("parentid", "parentId");
		return jsonData;
	}

	@ResponseBody
	@RequestMapping("/resetUserPWD")
	public void resetUserPWD(String userId) {
		userService.updateUserPassword(userId);
	}
}
