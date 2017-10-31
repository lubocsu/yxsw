package com.upsoft.yxsw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "BIZ_T_XJ_WORK_GROUP_DETIAL")
public class BizTXjWorkGroupDetial {

	/**
	 * 主键ID
	 */
	 @Id
	 @GenericGenerator(name = "generator", strategy = "uuid.hex")
	 @GeneratedValue(generator = "generator")
	 @Column(name = "DETAIL_ID")
	private String detailId;
	/**
	 * 主表BIZ_T_XJ_WORK_GROUP的ID
	 */
	 @Column(name = "GROUP_ID")
	private String groupId;
	/**
	 * 班次名称,如白班，中班，晚班等
	 */
	 @Column(name = "DETAIL_NAME")
	private String detailName;
	/**
	 * 设置说明
	 */
	 @Column(name = "DETAIL_DESC")
	private String detailDesc;
	/**
	 * 开始时间，格式如：08:30
	 */
	 @Column(name = "START_TIME")
	private String startTime;
	/**
	 * 截止时间，格式如：08:30
	 */
	 @Column(name = "END_TIME")
	private String endTime;
	/**
	 * 排序号
	 */
	 @Column(name = "SORT_NUM")
	private Long sortNum;
	/**
	 * 备用字段
	 */
	 @Column(name = "BYZD")
	private String byzd;
	/**
	 * 是否删除，1是 0否
	 */
	 @Column(name = "DEL_FLAG")
	private Long delFlag;
	
	/**
	 * 设置主键ID
	 */
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	
	/**
	 * 获取主键ID
	 */
	public String getDetailId() {
		return detailId;
	}
	/**
	 * 设置主表BIZ_T_XJ_WORK_GROUP的ID
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	/**
	 * 获取主表BIZ_T_XJ_WORK_GROUP的ID
	 */
	public String getGroupId() {
		return groupId;
	}
	/**
	 * 设置班次名称,如白班，中班，晚班等
	 */
	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}
	
	/**
	 * 获取班次名称,如白班，中班，晚班等
	 */
	public String getDetailName() {
		return detailName;
	}
	/**
	 * 设置设置说明
	 */
	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
	}
	
	/**
	 * 获取设置说明
	 */
	public String getDetailDesc() {
		return detailDesc;
	}
	/**
	 * 设置开始时间，格式如：08:30
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * 获取开始时间，格式如：08:30
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * 设置截止时间，格式如：08:30
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * 获取截止时间，格式如：08:30
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * 设置排序号
	 */
	public void setSortNum(Long sortNum) {
		this.sortNum = sortNum;
	}
	
	/**
	 * 获取排序号
	 */
	public Long getSortNum() {
		return sortNum;
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
	 * 设置是否删除，1是 0否
	 */
	public void setDelFlag(Long delFlag) {
		this.delFlag = delFlag;
	}
	
	/**
	 * 获取是否删除，1是 0否
	 */
	public Long getDelFlag() {
		return delFlag;
	}
}