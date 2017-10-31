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
@Table(name = "BIZ_T_XJ_ZB_PLAN_DETAIL_PERSON")
public class BizTXjZbPlanDetailPerson extends BaseEntity implements Serializable{

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
	 @Column(name = "PLAN_DETAIL_PERSON_ID")
	private String planDetailPersonId;
	/**
	 * 排班从表ID
	 */
	 @Column(name = "PLAN_DETAIL_ID")
	private String planDetailId;
	/**
	 * 值班人员ID
	 */
	 @Column(name = "PERSON_ID")
	private String personId;
	/**
	 * 值班人员名称
	 */
	 @Column(name = "PERSON_NAME")
	private String personName;
	/**
	 * 备用字段
	 */
	 @Column(name = "BYZD")
	private String byzd;
	
	
	/**
	 * 设置主键ID
	 */
	public void setPlanDetailPersonId(String planDetailPersonId) {
		this.planDetailPersonId = planDetailPersonId;
	}
	
	/**
	 * 获取主键ID
	 */
	public String getPlanDetailPersonId() {
		return planDetailPersonId;
	}
	/**
	 * 设置排班从表ID
	 */
	public void setPlanDetailId(String planDetailId) {
		this.planDetailId = planDetailId;
	}
	
	/**
	 * 获取排班从表ID
	 */
	public String getPlanDetailId() {
		return planDetailId;
	}
	/**
	 * 设置值班人员ID
	 */
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	
	/**
	 * 获取值班人员ID
	 */
	public String getPersonId() {
		return personId;
	}
	/**
	 * 设置值班人员名称
	 */
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	
	/**
	 * 获取值班人员名称
	 */
	public String getPersonName() {
		return personName;
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