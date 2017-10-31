package com.upsoft.yxsw.schedule;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.upsoft.yxsw.entity.BizTGgNoticeManage;
import com.upsoft.yxsw.entity.BizTXjWorkGroup;
import com.upsoft.yxsw.service.BizTGgNoticeManageService;
import com.upsoft.yxsw.service.BizTXjCxTaskService;
import com.upsoft.yxsw.service.BizTXjWorkGroupService;

/**
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：XJCXTaskSchedule.java<br>
* 摘要：系统定时器<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：胡毅<br>
* 完成日期：2017年9月18日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：胡毅<br>
* 完成日期：2017年9月18日<br>
 */
public class DefinedSchedule {
	
	protected static final Logger logger = Logger.getLogger("com.upsoft.yxsw.scheduleTask");
	
	@Autowired
	private BizTXjWorkGroupService bizTXjWorkGroupService;  // 设置每个厂站的班次主表， 一个厂站都有一个或多个班次（厂站在主表中只会有一条数据）
	@Autowired
	private BizTGgNoticeManageService bizTGgNoticeManageService;//公告，将所有到有效日期的公告设为无效
	@Autowired
	private BizTXjCxTaskService bizTXjCxTaskService;
	
	// 生成巡检任务
	public void createXJTask(){
		List<BizTXjWorkGroup> workGroupList = bizTXjWorkGroupService.getList(null);
		bizTXjWorkGroupService.saveCXTaskBySchedule(workGroupList);
	}
	
	// 下发巡检任务
	public void distributeXJTask(){
		long start = System.currentTimeMillis();
		logger.info("检查任务，下发-------------------------start--------------------");
		bizTXjCxTaskService.updateToDistributeXJTask();
		long end = System.currentTimeMillis();
		logger.info("检查任务，下发-------------------------end 用时："+((end-start)/1000)+"秒--------------------");
	}
	
	// 任务超期
	public void overTimeXJTask(){
		long start = System.currentTimeMillis();
		logger.info("检查任务，超期-------------------------start--------------------");
		bizTXjCxTaskService.updateToOverTimeXJTask();
		long end = System.currentTimeMillis();
		logger.info("检查任务，超期-------------------------end 用时："+((end-start)/1000)+"秒--------------------");
	}
	
	// 将到期公告置为无效
	public void stopGgTask(){
		long start = System.currentTimeMillis();
		logger.info("检查公告，设置公告无效-------------------------start--------------------");
		List<BizTGgNoticeManage> ggList = bizTGgNoticeManageService.getDqGgList();
		bizTGgNoticeManageService.saveCXTaskBySchedule(ggList);
		long end = System.currentTimeMillis();
		logger.info("检查公告，设置公告无效-------------------------end 用时："+((end-start)/1000)+"秒--------------------");
	}
}
