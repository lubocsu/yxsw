package com.upsoft.yxsw.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.entity.ComTAttachment;
import com.upsoft.system.service.BaseService;

/**
* Copyright (c) 2017,重庆扬讯软件技术股份有限公司<br>
* All rights reserved.<br>
*
* 文件名称：PatrollingRecordsService.java<br>
* 摘要：简要描述本文件的内容<br>
* -------------------------------------------------------<br>
* 当前版本：1.0<br>
* 作者：陈涛<br>
* 完成日期：2017年9月25日<br>
* -------------------------------------------------------<br>
*/
public interface PatrollingRecordsService extends BaseService{

	/**
	 * 通过巡检任务ID获取巡检点列表
	 * 
	 * @date 2017年9月25日 上午11:17:34
	 * @author 陈涛
	 * @param cxTaskId
	 * @return 
	 */
	Map<String, Object> getpointList(String cxTaskId, PageBean pageBean);

	/**
	 * 通过任务巡检点ID查找巡检点详情
	 * 
	 * @date 2017年9月25日 下午2:22:35
	 * @author 陈涛
	 * @param taskItemId
	 * @return 
	 */
	Map<String, Object> findPoint(String taskItemId);

	/**
	 * 通过巡检点ID获取设备设施列表
	 * 
	 * @date 2017年9月25日 下午2:47:11
	 * @author 陈涛
	 * @param xjdItemId
	 * @param pageBean
	 * @return 
	 */
	Map<String, Object> getSbssList(String taskItemId, PageBean pageBean);

	/**
	 * 获取设备设施巡检的详细信息
	 * 
	 * @date 2017年9月25日 下午5:07:41
	 * @author 陈涛
	 * @param ttaskItemSbssId
	 * @return 
	 */
	List<Map<String, Object>> getSbssInfo(String ttaskItemSbssId);

	/**
	 * 获取设备设施发现问题处理流程
	 * 
	 * @date 2017年9月25日 下午5:44:01
	 * @author 陈涛
	 * @param ttaskItemSbssId
	 * @return 
	 */
	List<Map<String, Object>> getDealFlow(String ttaskItemSbssId);

	/**
	 * 获取设备设施附件
	 * 
	 * @date 2017年9月26日 上午9:32:09
	 * @author 陈涛
	 * @param ttaskItemSbssId
	 * @return 
	 */
	List<ComTAttachment> getAttachmentList(String ttaskItemSbssId);

	/**
	 * 获取设备设施检查详情
	 * 
	 * @date 2017年9月29日 下午2:28:48
	 * @author 陈涛
	 * @param ttaskItemSbssId
	 * @return 
	 */
	Map<String, Object> getSbssCheckDetail(String ttaskItemSbssId);

	/**
	 * @date 2017年10月17日 下午4:38:36
	 * @author 陈涛
	 * @param ttaskItemSbssId
	 * @return 
	 */
	Map<String, Object> getSbssDetail(String ttaskItemSbssId);

}
