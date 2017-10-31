package com.upsoft.yxsw.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.service.BaseService;
import com.upsoft.yxsw.entity.BizTXjFreqSegment;


/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：BizTXjFreqSegmentService.java<br>
 * 摘要：<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.0<br>
 * 作者：<br>
 * 完成日期：2017-09-20 <br>
 */
public interface BizTXjFreqSegmentService extends BaseService {
	
	/**
	 * XXXXXXXXXXXXXXXXXXXXXXXX
	 * @date 2017-09-20
	 * @author 
	 * @param bean
	 * @param params
	 * @return 
	 */
	public Map<String, Object> getList(PageBean bean, Map<String, Object> params);
	
	/**
	 * XXXXXXXXXXXXXXXXXXXXXXXXXXXX
	 * @date 2017-09-20
	 * @author 
	 * @param order
	 * @return 
	 */
	public BizTXjFreqSegment save(BizTXjFreqSegment bizTXjFreqSegment);
	
	
	/**
	 * XXXXXXXXXXXXXXX
	 * @date 2017-09-20
	 * @author 
	 * @param 
	 * @return 
	 */
	public BizTXjFreqSegment getBizTXjFreqSegmentById(String id);
	
	/**
	 * 删除班次下的排班内容
	 * @date 2017年9月26日 下午9:02:53
	 * @param periodDetailIds
	 */
	public void deleteFreqSegmentByPerid(List<String> periodDetailIds);
}
