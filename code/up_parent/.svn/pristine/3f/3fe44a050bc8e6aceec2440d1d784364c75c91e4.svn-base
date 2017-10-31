package com.upsoft.yxsw.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.service.BaseService;
import com.upsoft.yxsw.controller.cxMake.bean.CxMakeDetailPojo;
import com.upsoft.yxsw.entity.BizTXjZypTemplate;


/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：BizTXjZypTemplateService.java<br>
 * 摘要：<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.0<br>
 * 作者：<br>
 * 完成日期：2017-09-27 <br>
 */
public interface BizTXjZypTemplateService extends BaseService {
	
	/**
	 * 获取作业票模版列表
	 * @date 2017-09-27
	 * @author 
	 * @param bean
	 * @param params
	 * @return 
	 */
	public Map<String, Object> getList(PageBean bean, Map<String, Object> params);
	
	/**
	 * 保存作业票模版
	 * @date 2017-09-27
	 * @author 
	 * @param order
	 * @return 
	 */
	public BizTXjZypTemplate save(BizTXjZypTemplate bizTXjZypTemplate);
	
	
	/**
	 * 获取作业票模版 by ID
	 * @date 2017-09-27
	 * @author 
	 * @param 
	 * @return 
	 */
	public BizTXjZypTemplate getBizTXjZypTemplateById(String id);
	
	/**
	 * 获取可用厂巡作业票模版 by 厂所ID
	 * @date 2017年10月11日 下午3:39:20
	 * @param orgId
	 * @return
	 */
	public BizTXjZypTemplate getTempByOrg(String csOrgId);
	
	/**
	 * 获取作业票模版信息用于拟定新作业票
	 * @date 2017年10月11日 下午4:35:41
	 * @param tempId
	 * @return
	 */
	public CxMakeDetailPojo getZypTempInfo(String tempId);
}
