package com.upsoft.systemweb.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.entity.SysPermssionDataEntity;


/**
* Copyright (c) 2016,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：PermissionDataService.java<br>
* 摘要：数据权限接口<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：null<br>
* 完成日期：2016年5月17日<br>
*/
public interface PermissionDataService {
	
	/**
	 * 返回对应系统下，该用户允许访问的机构ID列表
	 * @date 2016年5月17日 上午10:42:56
	 * @author null
	 * @param userId
	 * @return 
	 */
	public List<String> getPermissionOrgIds(String userId);
	
	/**
	 * 返回对应系统下，该用户允许访问的远程机构code列表
	 * @date 2016年5月17日 上午10:44:23
	 * @author null
	 * @return 
	 */
	public List<String> getPermissionRemoteOrgCodes(String userId, String systemCode);
	
	/**
	 * 返回数据权限树
	 * @date 2016年5月17日 下午5:52:19
	 * @author null
	 * @param type
	 * @param relateId
	 * @param systemCode
	 * @return 
	 */
	public List<Map<String, Object>> getPermissionOrgTree(String type, String relateId, String systemCode);
	
	/**
	 * 查询用户的完整数据权限树
	 * @date 2016年5月19日 上午11:14:07
	 * @author null
	 * @param userId
	 * @param systemCode
	 * @return 
	 */
	public List<Map<String, Object>> getFullPermissionOrgTree(String userId, String systemCode);
	
	/**
	 * 根据类型、关联ID、机构ID、系统编码返回唯一的权限信息
	 * @date 2016年5月18日 下午3:28:59
	 * @author null
	 * @param type
	 * @param relateId
	 * @param orgId
	 * @param systemCode
	 * @return 
	 */
	public SysPermssionDataEntity findOne(int type, String relateId, String orgId, String systemCode);
	
	/**
	 * 保存数据权限
	 * @date 2016年5月18日 下午3:06:52
	 * @author null
	 * @param type
	 * @param relateId
	 * @param systemCode 
	 */
	public void savePermissionData(int type, String relateId, String orgId, int status, String systemCode);
	
	/**
	 * 批量保存数据权限
	 * @date 2016年5月18日 下午3:11:52
	 * @author null
	 * @param datas 
	 */
	public void savePermissionDataInBatch(List<SysPermssionDataEntity> datas);
	
	/**
	 * 批量删除数据权限
	 * @date 2016年5月18日 下午5:03:47
	 * @author null
	 * @param datas 
	 */
	public void deletePermissionDataInBatch(int type, String relateId, String systemCode, List<String> orgIds);
	
}
