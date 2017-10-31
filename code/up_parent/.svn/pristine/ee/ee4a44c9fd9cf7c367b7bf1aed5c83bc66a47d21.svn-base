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
@Table(name = "BIZ_T_XJ_ZB_PLAN_DETAIL")
public class BizTXjZbPlanDetail extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	 @Id
	 @GenericGenerator(name = "generator", strategy = "uuid.hex")
	 @GeneratedValue(generator = "generator")
	 @Column(name = "PLAN_DETAIL_ID")
	private String planDetailId;
	/**
	 * 值班计划ID
	 */
	 @Column(name = "ZB_PLAN_ID")
	private String zbPlanId;
	/**
	 * 班次ID 表BIZ_T_XJ_WORK_GROUP_DETIAL的ID
	 */
	 @Column(name = "DETAIL_ID")
	private String detailId;
	/**
	 * 班次名称
	 */
	 @Column(name = "DETAIL_NAME")
	private String detailName;
	/**
	 * 工段ID
	 */
	 @Column(name = "TECHNICS_ID")
	private String technicsId;
	/**
	 * 工段名称
	 */
	 @Column(name = "TECHNICS_NAME")
	private String technicsName;
	/**
	 * 备用字段
	 */
	 @Column(name = "BYZD")
	private String byzd;
	
	
	/**
	 * 设置主键ID
	 */
	public void setPlanDetailId(String planDetailId) {
		this.planDetailId = planDetailId;
	}
	
	/**
	 * 获取主键ID
	 */
	public String getPlanDetailId() {
		return planDetailId;
	}
	/**
	 * 设置值班计划ID
	 */
	public void setZbPlanId(String zbPlanId) {
		this.zbPlanId = zbPlanId;
	}
	
	/**
	 * 获取值班计划ID
	 */
	public String getZbPlanId() {
		return zbPlanId;
	}
	/**
	 * 设置班次ID 表BIZ_T_XJ_WORK_GROUP_DETIAL的ID
	 */
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	
	/**
	 * 获取班次ID 表BIZ_T_XJ_WORK_GROUP_DETIAL的ID
	 */
	public String getDetailId() {
		return detailId;
	}
	/**
	 * 设置班次名称
	 */
	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}
	
	/**
	 * 获取班次名称
	 */
	public String getDetailName() {
		return detailName;
	}
	/**
	 * 设置工段ID
	 */
	public void setTechnicsId(String technicsId) {
		this.technicsId = technicsId;
	}
	
	/**
	 * 获取工段ID
	 */
	public String getTechnicsId() {
		return technicsId;
	}
	/**
	 * 设置工段名称
	 */
	public void setTechnicsName(String technicsName) {
		this.technicsName = technicsName;
	}
	
	/**
	 * 获取工段名称
	 */
	public String getTechnicsName() {
		return technicsName;
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