package com.upsoft.yxsw.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.service.BaseService;
import com.upsoft.yxsw.entity.BizTXjWorkGroupDetial;


/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：BizTXjWorkGroupDetialService.java<br>
 * 摘要：<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.0<br>
 * 作者：<br>
 * 完成日期：2017-09-15 <br>
 */
public interface BizTXjWorkGroupDetialService extends BaseService {
	
	/**
	 * 获取班次详情列表
	 * @date 2017-09-15
	 * @author 
	 * @param bean
	 * @param params
	 * @return 
	 */
	public Map<String, Object> getList(PageBean bean, Map<String, Object> params);
	
	/**
	 * 根据厂站ID获取可用的厂巡排班详情
	 * @date 2017年9月21日 上午9:58:17
	 * @param csId
	 * @return
	 */
	public List<Map<String, Object>> workGroupDetialList(String csId);
	
	/**
	 * XXXXXXXXXXXXXXXXXXXXXXXXXXXX
	 * @date 2017-09-15
	 * @author 
	 * @param order
	 * @return 
	 */
	public BizTXjWorkGroupDetial save(BizTXjWorkGroupDetial bizTXjWorkGroupDetial);
	
	
	/**
	 * XXXXXXXXXXXXXXX
	 * @date 2017-09-15
	 * @author 
	 * @param 
	 * @return 
	 */
	public BizTXjWorkGroupDetial getBizTXjWorkGroupDetialById(String id);
	
	/**
	 * 批量删除班次详情并删除班次详情关联的排班和厂巡内容
	 * @date 2017年9月26日 下午8:47:28
	 * @param delDetialList
	 */
	public void deleteDetials(List<BizTXjWorkGroupDetial> delDetialList);
}
