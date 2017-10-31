package com.upsoft.yxsw.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.service.BaseService;
import com.upsoft.yxsw.entity.BizTXjZypTemplateItm;


/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：BizTXjZypTemplateItmService.java<br>
 * 摘要：<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.0<br>
 * 作者：<br>
 * 完成日期：2017-09-27 <br>
 */
public interface BizTXjZypTemplateItmService extends BaseService {
	
	/**
	 * 获取作业票配置详细配置项
	 * @date 2017-09-27
	 * @author 
	 * @param bean
	 * @param params
	 * @return 
	 */
	public Map<String, Object> getList(PageBean bean, Map<String, Object> params);
	
	/**
	 * 获取作业票配置项关联指标项
	 * @date 2017年10月9日 下午2:37:35
	 * @param bean
	 * @param params
	 * @return
	 */
	public Map<String, Object> getZbxList(PageBean bean, Map<String, Object> params);	
	
	/**
	 * 保存作业票配置内容项
	 * @date 2017-09-27
	 * @author 
	 * @param order
	 * @return 
	 */
	public BizTXjZypTemplateItm save(BizTXjZypTemplateItm bizTXjZypTemplateItm);
	
	
	/**
	 * 获取配置内容
	 * @date 2017-09-27
	 * @author 
	 * @param 
	 * @return 
	 */
	public BizTXjZypTemplateItm getBizTXjZypTemplateItmById(String id);
	
	/**
	 * 删除作业票配置内容及其关联的指标项
	 * @date 2017年10月10日 下午7:09:18
	 * @param zxpTempItmId
	 */
	public void deleteById(String zxpTempItmId);
}
