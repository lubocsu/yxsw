package com.upsoft.yxsw.mobile.bean.task;

import java.io.Serializable;
import java.util.List;

/**
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：TaskListBean.java<br>
* 摘要：我的任务列表bean<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：胡毅<br>
* 完成日期：2017年9月22日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：胡毅<br>
* 完成日期：2017年9月22日<br>
 */
public class TaskListBean  implements Serializable{
	
	private static final long serialVersionUID = -3869532979630193582L;
	private String taskId;
	private String taskName;
	private String taskStatus;
	private String taskDate;
	
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	private String planEndTime;
	private List<TaskPerson> taskPersonList;
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getPlanEndTime() {
		return planEndTime;
	}
	public void setPlanEndTime(String planEndTime) {
		this.planEndTime = planEndTime;
	}
	public String getTaskDate() {
		return taskDate;
	}
	public void setTaskDate(String taskDate) {
		this.taskDate = taskDate;
	}
	public List<TaskPerson> getTaskPersonList() {
		return taskPersonList;
	}
	public void setTaskPersonList(List<TaskPerson> taskPersonList) {
		this.taskPersonList = taskPersonList;
	} 
}
