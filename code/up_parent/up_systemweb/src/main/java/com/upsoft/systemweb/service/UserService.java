package com.upsoft.systemweb.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.service.BaseService;

/**
 * 
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 * 
 * 文件名称：UserService.java<br>
 * 摘要：用户服务接口<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：TW<br>
 * 完成日期：2015年1月22日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：TW<br>
 * 完成日期：2015年1月22日<br>
 */
public interface UserService extends BaseService {

	/**
	 * 查询用户分页数据列表
	 * 
	 * @date 2015年1月21日 下午5:59:05
	 * @author TW
	 * @param pars
	 *            过滤条件
	 * @param start
	 *            开始条数
	 * @param length
	 *            分页长度
	 * @return
	 */
	public List<Map<String, Object>> getUserList(Map<String, Object> pars,
			int start, int length);

	/**
	 * 查询用户列表总条数
	 * 
	 * @date 2015年1月21日 下午6:01:08
	 * @author TW
	 * @param pars
	 *            过滤条件
	 * @return
	 */
	public long getUserListCount(Map<String, Object> pars);

	/**
	 * 查询用户分页数据列表及总条数
	 * 
	 * @date 2015年1月28日 下午12:22:48
	 * @author TW
	 * @param pars
	 *            查询过滤条件
	 * @param pageBean
	 *            分页参数
	 * @return
	 */
	public Map<String, Object> getUserListAndCount(Map<String, Object> pars,
			PageBean pageBean);

	/**
	 * 保存用户
	 * 
	 * @date 2015年1月23日 下午2:51:29
	 * @author TW
	 * @param user
	 *            用户实体
	 */
	public void saveUser(SysUser user);

	/**
	 * 根据用户Id删除用户
	 * 
	 * @date 2015年1月23日 下午5:52:03
	 * @author TW
	 * @param userId
	 *            用户Id
	 */
	public void deleteUser(String userId);

	/**
	 * 删除以逗号分隔的用户Id集（eg:"id,id,id,id,"）
	 * 
	 * @date 2015年1月23日 下午7:37:12
	 * @author TW
	 * @param userIds
	 *            以逗号分隔的多个用户Id
	 */
	public void deleteSplitUsers(String userIds);

	public Integer getMaxOrderNo();

	/**
	 * 用户修改密码时，验证输入的密码经MD5加密后，与原密码是否一致
	 * 
	 * @date 2015年2月2日 下午4:19:13
	 * @author 屈锐华
	 * @param loginId
	 * @param oldPwd
	 * @param inputPwd
	 * @return
	 */
	public boolean validUserPwd(String loginId, String oldPwd, String inputPwd);

	/**
	 * 获取系统所有角色
	 * 
	 * @date 2015年2月11日 下午6:35:35
	 * @author TW
	 * @return
	 */
	public List<Map<String, Object>> getSystemRole();

	/**
	 * 根据用户Id 获取用户拥有的角色 获取[{roleid:value}]格式
	 * @date 2015年2月11日 下午6:35:57
	 * @author TW
	 * @param userId 用户Id
	 * @return
	 */
	public List<Map<String, Object>> getUserRoleByUserId(String userId);
	
	/**
	 * 根据用户Id 获取用户拥有的角色 获取[roleid]格式
	 * @date 2017年8月25日 下午4:14:53
	 * @author 胡毅
	 * @param userId
	 * @param onlyId true
	 * @return
	 */
	public List<String> getUserRoleByUserId(String userId,boolean onlyId);
	
	/**
	 * 根据用户Id 获取用户拥有的角色对应的系统代码
	 * 
	 * @date 2015年2月11日 下午6:35:57
	 * @author chengcong
	 * @param userId 用户Id
	 * @return
	 */
	public List<Map<String, Object>> getSystemCodeByUserIdInRole(String userId);

	/**
	 * 保存用户与角色关联关系
	 * 
	 * @date 2015年2月11日 下午6:36:54
	 * @author TW
	 * @param userId
	 *            用户id
	 * @param roleIds
	 *            以逗号分隔的一个或多个角色Id
	 */
	public void saveUserRole(String userId, String roleIds);
	
	/**
	 * 根据权限获取用户列表
	 * @date 2015年4月15日 上午9:33:08
	 * @author Awesan
	 * @param orgId
	 * @return 
	 */
	public List<Map<String, Object>> queryUsersWithPermission(String orgId);
	
	/**
	 * 根据组织机构ID的状态，改变用户的状态（用于组织机构停用，启动。同时改变用户停用，启用）
	 * @date 2015年4月17日 下午1:57:22
	 * @author 蒋  迪
	 * @param orgId		组织结构ID
	 * @param status 	组织机构状态
	 */
	public void updateUserByOrg(String orgId, int status);
	
	/**
	 * 重置用户密码为6个0
	 * @date 2015年4月17日 下午2:24:58
	 * @author 蒋  迪
	 * @param userId 
	 */
	public void updateUserPassword(String userId);

	/**
	 * 获取手机串号集合
	 * @date 2016年9月28日 上午9:58:49
	 * @author 胡毅
	 * @return
	 */
	public List<String> getImeiList();
	
	
	/**
	 * 获取机构用户树,用于后台新增机构时
	 * @date 2017年8月9日 下午5:20:00
	 * @author 胡毅
	 * @return
	 */
	public List<Map<String,Object>> findOrgUserTree(Map<String, Object> pars);
	
	/**
	 * 根据用户可访问机构Id List 获取机构用户树（配合数据权限使用）
	 * @date 2017年9月12日 下午8:16:25
	 * @author 胡毅
	 * @param orgIdList
	 * @return
	 */
	public List<Map<String, Object>> findOrgUserTreeInOrgList(List<String> orgIdList);
	
	public SysUser findOne(String userId);
	
	/**
	 * 返回某个用户可访问的用户数据（数据权限控制）
	 * @date 2017年8月25日 下午5:06:36
	 * @author 胡毅
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> getUserSelectInPermission(String userId);

	/**
	 * 根据common-config 配置的超级用户登录名获取超级用户ID
	 * @date 2017年8月29日 下午2:48:03
	 * @author 胡毅
	 * @return
	 */
	public List<String> getAdminUsers();

}
