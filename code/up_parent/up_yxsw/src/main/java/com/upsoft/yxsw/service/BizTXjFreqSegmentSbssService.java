package com.upsoft.yxsw.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.service.BaseService;
import com.upsoft.yxsw.entity.BizTXjFreqSegmentSbss;


/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：BizTXjFreqSegmentSbssService.java<br>
 * 摘要：<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.0<br>
 * 作者：<br>
 * 完成日期：2017-09-20 <br>
 */
public interface BizTXjFreqSegmentSbssService extends BaseService {
	
	/**
	 * 获取任务关联设备列表
	 * @date 2017-09-20
	 * @author 
	 * @param bean
	 * @param params
	 * @return 
	 */
	public Map<String, Object> getEqList(PageBean bean, Map<String, Object> params);
	
	/**
	 * 获取任务关联设施列表
	 * @date 2017年9月26日 上午9:32:49
	 * @param bean
	 * @param params
	 * @return
	 */
	public Map<String, Object> getSsList(PageBean bean, Map<String, Object> params);
	
	/**
	 * XXXXXXXXXXXXXXXXXXXXXXXXXXXX
	 * @date 2017-09-20
	 * @author 
	 * @param order
	 * @return 
	 */
	public BizTXjFreqSegmentSbss save(BizTXjFreqSegmentSbss bizTXjFreqSegmentSbss);
	
	
	/**
	 * XXXXXXXXXXXXXXX
	 * @date 2017-09-20
	 * @author 
	 * @param 
	 * @return 
	 */
	public BizTXjFreqSegmentSbss getBizTXjFreqSegmentSbssById(String id);
	
	/**
	 * 批量保存巡检任务与设备设施的关联
	 * @date 2017年9月25日 下午1:50:23
	 * @param sbssList
	 */
	public List<BizTXjFreqSegmentSbss> saveBizTXjFreqSegmentSbssList(List<BizTXjFreqSegmentSbss> sbssList);
	
	/**
	 * 删除设备设施与任务关联关系
	 * @date 2017年9月25日 下午2:11:44
	 * @param ferqSbssId
	 */
	public void delFreqSegmentSbss(String ferqSbssId);
	
	/**
	 * 批量删除设备设施与任务关联关系
	 * @date 2017年9月25日 下午3:37:46
	 * @param ids
	 */
	public Boolean delAll(String ids);
}
