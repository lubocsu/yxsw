package com.upsoft.system.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：LogDataOperationEntity.java<br>
* 摘要：原系统操作功能日志-见SystemTLog.java<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：胡毅<br>
* 完成日期：2017年8月24日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：胡毅<br>
* 完成日期：2017年8月24日<br>
 */
@Entity
@Table(name = "log_dataoperation")
@Deprecated
public class LogDataOperationEntity extends BaseEntity {

	/**
	 * 日志ID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "LogId")
	private String logId;

	/**
	 * 日志分类
	 */
	@Column(name = "LogType")
	private String logType;

	/**
	 * 登录用户名称
	 */
	@Column(name = "UserName")
	private String userName;

	/**
	 * 登录用户Id
	 */
	@Column(name = "UserId")
	private String userId;

	/**
	 * 操作时间
	 */
	@Column(name = "OperationTime")
	private Date operationTime;

	/**
	 * 操作类型
	 */
	@Column(name = "OperationType")
	private String operationType;

	/**
	 * 计算机信息
	 */
	@Column(name = "ComputerInfo")
	private String computerInfo;

	/**
	 * 修改字段
	 */
	@Column(name = "ColumnName")
	private String columnName;

	/**
	 * 字段名称
	 */
	@Column(name = "ColumnDesc")
	private String columnDesc;

	/**
	 * 修改前值
	 */
	@Column(name = "OldValue")
	private String oldValue;

	/**
	 * 修改后的值
	 */
	@Column(name = "NewValue")
	private String newValue;

	/**
	 * 备注说明
	 */
	@Column(name = "Remark")
	private String remark;

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getComputerInfo() {
		return computerInfo;
	}

	public void setComputerInfo(String computerInfo) {
		this.computerInfo = computerInfo;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnDesc() {
		return columnDesc;
	}

	public void setColumnDesc(String columnDesc) {
		this.columnDesc = columnDesc;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
