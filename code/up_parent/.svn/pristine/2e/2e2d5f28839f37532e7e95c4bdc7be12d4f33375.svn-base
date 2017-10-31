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
@Table(name = "BIZ_T_XJ_CX_TASK_ITEM_SBSS")
public class BizTXjCxTaskItemSbss extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "TTASK_ITEM_SBSS_ID")
	private String ttaskItemSbssId;
	/**
	 * 任务巡检点表ID
	 */
	@Column(name = "TASK_ITEM_ID")
	private String taskItemId;
	/**
	 * 类型 1设备，2设施
	 */
	@Column(name = "DETAIL_TYPE")
	private String detailType;
	/**
	 * 设备或设施ID
	 */
	@Column(name = "SBSS_ID")
	private String sbssId;
	/**
	 * 设备或设施名称
	 */
	@Column(name = "NAME")
	private String name;
	/**
	 * 是否必扫标签:1是 0否
	 */
	@Column(name = "MUST_SCAN")
	private String mustScan;
	/**
	 * 确认方式： 1 正常扫描 2无法扫描
	 */
	@Column(name = "EWM_CONFIRMED_TYPE")
	private String ewmConfirmedType;
	/**
	 * 是否已完成:1是 0否
	 */
	@Column(name = "HAVE_COMPLETE")
	private Long haveComplete;
	/**
	 * 操作时间 精确到秒
	 */
	@Column(name = "OPT_TIME")
	private String optTime;
	/**
	 * 备用字段
	 */
	@Column(name = "BYZD")
	private String byzd;
	/**
	 * 是否有问题 1是 0否 巡检选择此设备的最终检查是否存在问题，后期可能要推送机修
	 */
	@Column(name = "SF_FAULT")
	private Integer sfFault;
	/**
	 * 巡检说明
	 */
	@Column(name = "XJ_DESC")
	private String xjDesc;
	/**
	 * 班组长判断是否有问题1是 0否
	 */
	@Column(name = "BZZ_SF_FAULT")
	private String bzzSfFault;
	/**
	 * 生技科判断是否有问题1是 0否
	 */
	@Column(name = "SJK_SF_FAULT")
	private String sjkSfFault;

	/**
	 * 设置ID
	 */
	public void setTtaskItemSbssId(String ttaskItemSbssId) {
		this.ttaskItemSbssId = ttaskItemSbssId;
	}

	/**
	 * 获取ID
	 */
	public String getTtaskItemSbssId() {
		return ttaskItemSbssId;
	}

	/**
	 * 设置任务巡检点表ID
	 */
	public void setTaskItemId(String taskItemId) {
		this.taskItemId = taskItemId;
	}

	/**
	 * 获取任务巡检点表ID
	 */
	public String getTaskItemId() {
		return taskItemId;
	}

	/**
	 * 设置类型 1设备，2设施
	 */
	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}

	/**
	 * 获取类型 1设备，2设施
	 */
	public String getDetailType() {
		return detailType;
	}

	/**
	 * 设置设备或设施ID
	 */
	public void setSbssId(String sbssId) {
		this.sbssId = sbssId;
	}

	/**
	 * 获取设备或设施ID
	 */
	public String getSbssId() {
		return sbssId;
	}

	/**
	 * 设置设备或设施名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取设备或设施名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置是否必扫标签:1是 0否
	 */
	public void setMustScan(String mustScan) {
		this.mustScan = mustScan;
	}

	/**
	 * 获取是否必扫标签:1是 0否
	 */
	public String getMustScan() {
		return mustScan;
	}

	/**
	 * 设置确认方式： 1 正常扫描 2无法扫描
	 */
	public void setEwmConfirmedType(String ewmConfirmedType) {
		this.ewmConfirmedType = ewmConfirmedType;
	}

	/**
	 * 获取确认方式： 1 正常扫描 2无法扫描
	 */
	public String getEwmConfirmedType() {
		return ewmConfirmedType;
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

	/**
	 * 设置是否有问题 1是 0否 巡检选择此设备的最终检查是否存在问题，后期可能要推送机修
	 */
	public void setSfFault(Integer sfFault) {
		this.sfFault = sfFault;
	}

	/**
	 * 获取是否有问题 1是 0否 巡检选择此设备的最终检查是否存在问题，后期可能要推送机修
	 */
	public Integer getSfFault() {
		return sfFault;
	}

	/**
	 * 设置巡检说明
	 */
	public void setXjDesc(String xjDesc) {
		this.xjDesc = xjDesc;
	}

	/**
	 * 获取巡检说明
	 */
	public String getXjDesc() {
		return xjDesc;
	}

	/**
	 * 设置班组长判断是否有问题1是 0否
	 */
	public void setBzzSfFault(String bzzSfFault) {
		this.bzzSfFault = bzzSfFault;
	}

	/**
	 * 获取班组长判断是否有问题1是 0否
	 */
	public String getBzzSfFault() {
		return bzzSfFault;
	}

	/**
	 * 设置生技科判断是否有问题1是 0否
	 */
	public void setSjkSfFault(String sjkSfFault) {
		this.sjkSfFault = sjkSfFault;
	}

	/**
	 * 获取生技科判断是否有问题1是 0否
	 */
	public String getSjkSfFault() {
		return sjkSfFault;
	}
}