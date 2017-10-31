package com.upsoft.yxsw.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.service.BaseService;
import com.upsoft.yxsw.entity.BizTXjCxTaskItem;


/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：BizTXjCxTaskItemService.java<br>
 * 摘要：巡检任务巡检点记录service<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.0<br>
 * 作者：<br>
 * 完成日期：2017-09-22 <br>
 */
public interface BizTXjCxTaskItemService  extends BaseService {
	
	Map<String, Object> getList(PageBean bean, Map<String, Object> params);

	/**
	 * 巡检任务巡检点记录
	 * @date 2017年9月22日 下午2:50:36
	 * @author 胡毅
	 * @param taskId
	 * @return
	 */
	List<BizTXjCxTaskItem> getTaskPointList(String taskId);

	/**
	 * 根据巡检点标签获取巡检点安全提醒和设备设施列表,同时更新巡检记录
	 * @date 2017年9月22日 下午4:39:39
	 * @author 胡毅
	 * @param taskItemId 巡检点记录Id
	 * @param rfid 如果rfid为空，则巡检点非正常扫描
	 * @param basePath
	 * @return
	 */
	Map<String, Object> updatePointItemAndGetSbssInfoAndWarningByPointId(String taskItemId,String rfid,String basePath);
	
}
