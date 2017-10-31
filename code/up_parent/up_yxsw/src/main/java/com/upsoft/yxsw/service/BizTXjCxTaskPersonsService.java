package com.upsoft.yxsw.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.service.BaseService;
import com.upsoft.yxsw.entity.BizTXjCxTaskPersons;


/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：BizTXjCxTaskService.java<br>
 * 摘要：巡检任务参与人员service<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.0<br>
 * 作者：hy<br>
 * 完成日期：2017-09-21 <br>
 */
public interface BizTXjCxTaskPersonsService  extends BaseService {
	
	Map<String, Object> getList(PageBean bean, Map<String, Object> params);

	/**
	 * 查询该任务是否已有责任人,有责任人切非自己时返回true
	 * @date 2017年9月23日 下午3:14:25
	 * @author 胡毅
	 * @param taskId
	 * @param userId
	 * @return
	 */
	Boolean queryCharge(String taskId,String userId);

	/**
	 * 将指定人设置未任务责任人
	 * @date 2017年9月23日 下午3:29:46
	 * @author 胡毅
	 * @param taskId
	 * @param userId
	 */
	void updateToSetCharge(String taskId, String userId);

	/**
	 * 获取任务下执行人
	 * @date 2017年10月11日 下午3:50:35
	 * @author 胡毅
	 * @param cxTaskId
	 * @param inCharge 是只查询责任人，在任务已被接收后只查询责任人
	 * @return
	 */
	List<BizTXjCxTaskPersons> getCxTaskPersonListByTaskId(String cxTaskId,Boolean inCharge);
	
}
