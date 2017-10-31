package com.upsoft.yxsw.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.service.BaseService;


/**
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：BizTSsBaseinfoService.java<br>
* 摘要：设施信息管理service<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：胡毅<br>
* 完成日期：2017年9月13日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：胡毅<br>
* 完成日期：2017年9月13日<br>
 */
public interface BizTSsBaseinfoService  extends BaseService {
	
	public Map<String, Object> getList(PageBean bean, Map<String, Object> params);

	/**
	 * 查询设施树，根据用户厂站查询
	 * @date 2017年9月14日 下午2:01:59
	 * @author 胡毅
	 * @param csOrgId
	 * @param csOrgType 
	 * @return
	 */
	public List<Map<String, Object>> getTree(String csOrgId,String csOrgType);

	/**
	 * 验证设施编码唯一性
	 * @date 2017年9月14日 下午3:26:53
	 * @author 胡毅
	 * @param ssCode
	 * @return
	 */
	public long validateSSCode(String ssCode);

	/**
	 * 获取最大序号
	 * @date 2017年9月14日 下午3:31:15
	 * @author 胡毅
	 */
	public Integer getMaxNum();

	/**
	 * 获取设施的层级关系名称
	 * @date 2017年9月14日 下午5:39:48
	 * @author 胡毅
	 * @param layer
	 * @return
	 */
	public Map<String, Object> getSSLayer(String layer);

	/**
	 * 验证设施下是否挂接有设备
	 */
	public long validateSbUnderSs(String ssId);

	/**
	 * 删除设施和及其子设施（逻辑），同时删除设施和设备的管理关系（物理）
	 * @date 2017年9月16日 上午10:28:59
	 * @author 胡毅
	 * @param ssId
	 * @return
	 */
	public Boolean updateSSAndDelRelationSb(String ssId);

	/**
	 * 获取本厂所下未关联的设施
	 * 
	 * @date 2017年9月18日 下午4:14:09
	 * @author 陈涛
	 * @param pageBean
	 * @param params
	 * @param loginInfo
	 * @return 
	 */
	public Map<String, Object> getNoRelatedData(PageBean pageBean,
			Map<String, Object> params, WSLoginInfoBean loginInfo);

	/**
	 * 获取巡检点挂接的设施
	 * 
	 * @date 2017年9月18日 下午4:45:38
	 * @author 陈涛
	 * @param pageBean
	 * @param spotId
	 * @param loginInfo
	 * @return 
	 */
	public Map<String, Object> getRelatedFaData(PageBean pageBean,
			String spotId, WSLoginInfoBean loginInfo);
	
    /**
     * 获取树，还要过滤输入框的值
     * @date 2017年9月18日 下午8:00:01
     * @author 杨磊
     * @param csOrgId
     * @param csOrgTypeId
     * @param param
     * @return
     */
	public List<Map<String, Object>> getTree(String csOrgId, String csOrgTypeId, Map<String, Object> param);
    /**
     * 设施 安全
     * @date 2017年9月20日 下午3:44:32
     * @author 杨磊
     * @param csOrgId
     * @param csOrgTypeId
     * @param param
     * @return
     */
	public List<Map<String, Object>> getTree1(String csOrgId, String csOrgTypeId, Map<String, Object> param);
	
	/**
	 * 根据任务ID获取该任务当前未关联的可关联设备列表
	 * @date 2017年9月23日 上午10:23:22
	 * @param pageBean
	 * @param freqId
	 * @return
	 */
	public Map<String, Object> getFreqNoReSsList(PageBean pageBean, String freqId, Map<String, Object> params);
    
	
}
