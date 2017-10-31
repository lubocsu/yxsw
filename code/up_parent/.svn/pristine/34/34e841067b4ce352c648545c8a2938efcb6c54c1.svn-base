package com.upsoft.yxsw.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.service.BaseService;
import com.upsoft.yxsw.entity.BizTXjTechnicsScopeManage;


/**
* Copyright (c) 2017,重庆扬讯软件技术股份有限公司<br>
* All rights reserved.<br>
*
* 文件名称：BizTXjTechnicsScopeManageService.java<br>
* 摘要：简要描述本文件的内容<br>
* -------------------------------------------------------<br>
* 当前版本：1.0<br>
* 作者：陈涛<br>
* 完成日期：2017年9月15日<br>
* -------------------------------------------------------<br>
*/
public interface BizTXjTechnicsScopeManageService  extends BaseService {

	/**
	 * 查询工艺段树
	 *
	 * @date 2017年9月15日 上午10:07:23
	 * @author 陈涛
	 * @param params
	 * @return 
	 */
	List<Map<String, Object>> queryByTreeId(WSLoginInfoBean loginInfo,String csOrg);

	/**
	 * 新增工艺段
	 * 
	 * @date 2017年9月15日 下午4:45:12
	 * @author 陈涛
	 * @param bizTXjTechnicsScopeManage
	 * @param loginInfo
	 * @return 
	 */
	BizTXjTechnicsScopeManage saveTechnicsScope(
			BizTXjTechnicsScopeManage bizTXjTechnicsScopeManage,
			WSLoginInfoBean loginInfo);

	/**
	 * 通过id获取工艺段
	 * 
	 * @date 2017年9月15日 下午5:01:38
	 * @author 陈涛
	 * @param technicsId
	 * @return 
	 */
	BizTXjTechnicsScopeManage findOne(String technicsId);

	/**
	 * 关联巡检点
	 * 
	 * @date 2017年9月19日 上午10:12:59
	 * @author 陈涛
	 * @param technicsId
	 * @param idArray
	 * @param nameArray
	 * @param value 
	 */
	void saveRelated(String technicsId, List<String> idArray,
			List<String> nameArray, String value);

	/**
	 * 删除关联关系
	 * 
	 * @date 2017年9月19日 上午10:44:47
	 * @author 陈涛
	 * @param idArray 
	 */
	void delRelated(List<String> idArray);

	/**
	 * 更新工艺段
	 * 
	 * @date 2017年9月19日 下午1:50:06
	 * @author 陈涛
	 * @param bizTXjTechnicsScopeManage
	 * @param loginInfo
	 * @return 
	 */
	void updateTechnicsScope(
			BizTXjTechnicsScopeManage bizTXjTechnicsScopeManage,
			WSLoginInfoBean loginInfo);

	/**
	 * 逻辑删除工艺段信息，物理删除工艺段关联的排班信息和巡检信息
	 * 
	 * @date 2017年9月19日 下午3:19:44
	 * @author 陈涛
	 * @param technicsScopeId
	 * @param loginInfo 
	 */
	void delTechnicsScope(String technicsScopeId, WSLoginInfoBean loginInfo);
	
}
