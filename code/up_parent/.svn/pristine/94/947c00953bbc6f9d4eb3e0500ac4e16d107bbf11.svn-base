package com.upsoft.system.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
* 
* Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：LogEntity.java<br>
* 摘要：系统日志实体类-目前仅记录了登录日志信息<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.2<br>
* 作者：胡毅<br>
* 完成日期：2017年8月24日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：冉恒鑫<br>
* 完成日期：2015年3月24日<br>
 */
@Entity
@Table(name = "sys_log")
public class SysLogEntity extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6115374104794414752L;
	
	//日志ID
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "LogId")
	private String logId;
	
	//登录账户
	@Column(name = "UserId")
	private String userId;
	
	//功能名字
	@Column(name = "FunctionName")
	private String functionName;
	
	//日志类型(1：系统登录；2：功能访问；3：数据变更)
	@Column(name = "LogType")
	private int logType; 
	
	//日志记录时间
	@Column(name = "LogTime")
	private Date LogTime;
	
	//功能停留时间
	@Column(name = "DwellTime")
	private Long dwellTime;
	
	//系统编码
	@Column(name = "SystemCode")
	private String systemCode;
	
	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public int getLogType() {
		return logType;
	}

	public void setLogType(int logType) {
		this.logType = logType;
	}

	public Date getLogTime() {
		return LogTime;
	}

	public void setLogTime(Date logTime) {
		LogTime = logTime;
	}

	public Long getDwellTime() {
		return dwellTime;
	}

	public void setDwellTime(Long dwellTime) {
		this.dwellTime = dwellTime;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
