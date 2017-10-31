package com.upsoft.yxsw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.upsoft.system.entity.BaseEntity;

@Entity
@Table(name = "BIZ_T_XJ_CX_TASK_ITEM")
public class BizTXjCxTaskItem extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 2147856773154938651L;
	/**
	 * 主键ID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "TASK_ITEM_ID")
	private String taskItemId;
	/**
	 * 任务ID
	 */
	@Column(name = "TASK_ID")
	private String taskId;
	/**
	 * 巡检点ID
	 */
	@Column(name = "XJD_ITEM_ID")
	private String xjdItemId;
	/**
	 * 巡检点名称
	 */
	@Column(name = "XJD_ITEM_NAME")
	private String xjdItemName;
	/**
	 * 巡检点位置
	 */
	@Column(name = "XJD_ITEM_ADDRESS")
	private String xjdItemAddress;
	/**
	 * 巡检点说明
	 */
	@Column(name = "XJD_ITEM_DESC")
	private String xjdItemDesc;
	/**
	 * RFID确定方式： 1 扫描 2 无法处理
	 */
	@Column(name = "RFID_CONFIRMED_TYPE")
	private String rfidConfirmedType;
	/**
	 * 操作时间 精确到秒
	 */
	@Column(name = "OPT_TIME")
	private String optTime;
	/**
	 * 是否已完成:1是 0否
	 */
	@Column(name = "HAVE_COMPLETE")
	private Long haveComplete;
	/**
	 * 巡检点的RFID编号
	 */
	@Column(name = "RFID_CODE")
	private String rfidCode;
	/**
	 * 备用字段
	 */
	@Column(name = "BYZD")
	private String byzd;

	/**
	 * 设置主键ID
	 */
	public void setTaskItemId(String taskItemId) {
		this.taskItemId = taskItemId;
	}

	/**
	 * 获取主键ID
	 */
	public String getTaskItemId() {
		return taskItemId;
	}

	/**
	 * 设置任务ID
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * 获取任务ID
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * 设置巡检点ID
	 */
	public void setXjdItemId(String xjdItemId) {
		this.xjdItemId = xjdItemId;
	}

	/**
	 * 获取巡检点ID
	 */
	public String getXjdItemId() {
		return xjdItemId;
	}

	/**
	 * 设置巡检点名称
	 */
	public void setXjdItemName(String xjdItemName) {
		this.xjdItemName = xjdItemName;
	}

	/**
	 * 获取巡检点名称
	 */
	public String getXjdItemName() {
		return xjdItemName;
	}

	/**
	 * 设置巡检点位置
	 */
	public void setXjdItemAddress(String xjdItemAddress) {
		this.xjdItemAddress = xjdItemAddress;
	}

	/**
	 * 获取巡检点位置
	 */
	public String getXjdItemAddress() {
		return xjdItemAddress;
	}

	/**
	 * 设置巡检点说明
	 */
	public void setXjdItemDesc(String xjdItemDesc) {
		this.xjdItemDesc = xjdItemDesc;
	}

	/**
	 * 获取巡检点说明
	 */
	public String getXjdItemDesc() {
		return xjdItemDesc;
	}

	/**
	 * 设置RFID确定方式： 1 扫描 2 无法处理
	 */
	public void setRfidConfirmedType(String rfidConfirmedType) {
		this.rfidConfirmedType = rfidConfirmedType;
	}

	/**
	 * 获取RFID确定方式： 1 扫描 2 无法处理
	 */
	public String getRfidConfirmedType() {
		return rfidConfirmedType;
	}

	/**
	 * 设置操作时间 精确到秒
	 */
	public void setOptTime(String optTime) {
		this.optTime = optTime;
	}

	/**
	 * 获取操作时间 精确到秒
	 */
	public String getOptTime() {
		return optTime;
	}

	/**
	 * 设置是否已完成:1是 0否
	 */
	public void setHaveComplete(Long haveComplete) {
		this.haveComplete = haveComplete;
	}

	/**
	 * 获取是否已完成:1是 0否
	 */
	public Long getHaveComplete() {
		return haveComplete;
	}

	/**
	 * 设置巡检点的RFID编号
	 */
	public void setRfidCode(String rfidCode) {
		this.rfidCode = rfidCode;
	}

	/**
	 * 获取巡检点的RFID编号
	 */
	public String getRfidCode() {
		return rfidCode;
	}

	/**
	 * 设置备用字段
	 */
	public void setByzd(String byzd) {
		this.byzd = byzd;
	}

	/**
	 * 获取备用字段
	 */
	public String getByzd() {
		return byzd;
	}
}