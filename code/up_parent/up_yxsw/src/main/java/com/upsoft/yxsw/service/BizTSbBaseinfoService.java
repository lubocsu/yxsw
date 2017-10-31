package com.upsoft.yxsw.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.service.BaseService;
import com.upsoft.yxsw.entity.BizTSbBaseinfo;


/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：BizTSbBaseinfoService.java<br>
 * 摘要：<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.0<br>
 * 作者：<br>
 * 完成日期：2017-09-08 <br>
 */
public interface BizTSbBaseinfoService  extends BaseService {
	
	/**
	 * 获取列表
	 * @date 2017-09-08
	 * @author 
	 * @param bean
	 * @param params
	 * @return 
	 */
	public Map<String, Object> getList(PageBean bean, Map<String, Object> params);
	
	/**
	 * 保存
	 * @date 2017-09-08
	 * @author 
	 * @param order
	 * @return 
	 */
	public BizTSbBaseinfo save(BizTSbBaseinfo bizTSbBaseinfo);
	
	
	/**
	 * 保存设备信息及其附件
	 * @date 2017年9月14日 下午8:04:12
	 * @param bizTSbBaseinfo
	 * @return
	 */
	public BizTSbBaseinfo save(BizTSbBaseinfo bizTSbBaseinfo, String attachmentList, WSLoginInfoBean loginInfo);
	
	/**
	 * 保存设备信息及其附件
	 * @date 2017年9月14日 下午8:04:12
	 * @param bizTSbBaseinfo
	 * @return
	 */
	public BizTSbBaseinfo save(BizTSbBaseinfo bizTSbBaseinfo, String attachmentList, String delAttachmentList, WSLoginInfoBean loginInfo);
	
	
	/**
	 * 获取设备信息
	 * @date 2017-09-08
	 * @author 
	 * @param 
	 * @return 
	 */
	public BizTSbBaseinfo getBizTSbBaseinfoById(String id);
	
	/**
	 * 验证设备是否存在
	 * @date 2017年9月11日 下午8:08:46
	 * @return
	 */
	public boolean eqExists(String eqCode);

	
	/**
	 * 测试设备树能否成功
	 * @date 2017年9月14日 下午3:10:31
	 * @author 杨磊
	 * @param params
	 */
	public	 List<Map<String, Object>> queryByTreeId(Map<String, Object> params);

	/**
	 * 获取登录用户厂所下未关联的设备列表
	 * 
	 * @date 2017年9月17日 下午3:41:42
	 * @author 陈涛
	 * @param pageBean
	 * @param params
	 * @param loginInfo
	 * @return 
	 */
	public Map<String, Object> getNoRelatedData(PageBean pageBean,
			Map<String, Object> params, WSLoginInfoBean loginInfo);

	/**
	 * 获取该巡检点下已关联的设备数据
	 * 
	 * @date 2017年9月17日 下午4:26:11
	 * @author 陈涛
	 * @param pageBean
	 * @param spotId
	 * @param loginInfo
	 * @return 
	 */
	public Map<String, Object> getRelatedEqData(PageBean pageBean,
			String spotId, WSLoginInfoBean loginInfo);
	
	/**
	 * 根据任务ID获取该任务当前未关联的可关联设备列表
	 * @date 2017年9月23日 上午10:23:22
	 * @param pageBean
	 * @param freqId
	 * @return
	 */
	public Map<String, Object> getFreqNoReEqList(PageBean pageBean, String freqId, Map<String, Object> params);
   /**
    * 设备统计获取数据，通过一下参数
    * @date 2017年10月9日 下午7:04:27
    * @author 杨磊
    * @param csOrgId
    * @param sbType
    * @param sbStatus
    * @return
    */
	public List<Map<String, Object>> getdate(String csOrgId, String sbType,
			String sbStatus,String idNew);
/**
 * 获取那些有a标签
 * @date 2017年10月26日 下午3:38:14
 * @author 杨磊
 * @param csOrgId
 * @param sbTypeId
 * @param sbStatus
 * @param idNew
 * @return
 */
public List<String> getRenderList(String csOrgId, String sbTypeId,
		String sbStatus, String idNew);
}
