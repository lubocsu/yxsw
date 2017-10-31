package com.upsoft.yxsw.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.service.BaseService;
import com.upsoft.yxsw.entity.BizTXjWorkGroup;
import com.upsoft.yxsw.entity.BizTXjWorkGroupDetial;


/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：BizTXjWorkGroupService.java<br>
 * 摘要：<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.0<br>
 * 作者：<br>
 * 完成日期：2017-09-14 <br>
 */
public interface BizTXjWorkGroupService  extends BaseService {
	
	/**
	 * 获取班次列表
	 * @date 2017-09-14
	 * @author 
	 * @param bean
	 * @param params
	 * @return 
	 */
	public Map<String, Object> getList(PageBean bean, Map<String, Object> params);
	
	/**
	 * 获取所有班次列表，不用分页
	 * @date 2017年9月18日 下午7:28:58
	 * @author 胡毅
	 * @param wscOrgId
	 * @return
	 */
	public List<BizTXjWorkGroup> getList(String wscOrgId);
	
	/**
	 * 保存班次信息
	 * @date 2017-09-14
	 * @author 
	 * @param order
	 * @return 
	 */
	public BizTXjWorkGroup save(BizTXjWorkGroup bizTXjWorkGroup);
	
	/**
	 * 保存班次和班次详情信息
	 * @date 2017年9月15日 下午4:29:06
	 * @param bizTXjWorkGroup
	 * @param workGroupDetial
	 * @return
	 */
	public BizTXjWorkGroup save(BizTXjWorkGroup bizTXjWorkGroup, List<BizTXjWorkGroupDetial> workGroupDetialList);
	
	/**
	 * 保存班次和班次详情信息
	 * @date 2017年9月15日 下午4:29:06
	 * @param bizTXjWorkGroup
	 * @param workGroupDetial
	 * @return
	 */
	public BizTXjWorkGroup save(BizTXjWorkGroup bizTXjWorkGroup, List<BizTXjWorkGroupDetial> workGroupDetialList, List<BizTXjWorkGroupDetial> delDetialList);
	
	/**
	 * 根据Id获取班次信息
	 * @date 2017-09-14
	 * @author 
	 * @param 
	 * @return 
	 */
	public BizTXjWorkGroup getBizTXjWorkGroupById(String id);
	
	
	/**
	 * 删除排班信息，同时删除排班详情
	 * @date 2017年9月20日 上午11:15:35
	 * @param id
	 */
	public void deleteWorkGroup(String id);

	/**
	 * 根据工作班次设置主表信息生成定时任务
	 * @date 2017年9月18日 下午7:47:41
	 * @author 胡毅
	 */
	public void saveCXTaskBySchedule(List<BizTXjWorkGroup> list);
}
