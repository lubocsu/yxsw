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
@Table(name = "BIZ_T_XJ_ZYP_CX_MAKE_HIS")
public class BizTXjZypCxMakeHis extends BaseEntity implements Serializable{

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
	 @Column(name = "HIS_ID")
	private String hisId;
	/**
	 * 作业票拟定主键ID
	 */
	 @Column(name = "CX_MAKE_ID")
	private String cxMakeId;
	/**
	 * 操作人ID
	 */
	 @Column(name = "OPT_ID")
	private String optId;
	/**
	 * 操作人名称
	 */
	 @Column(name = "OPT_NAME")
	private String optName;
	/**
	 * 操作类型 1?拟定??2??审核??3?中止?4?填报5?验收
	 */
	 @Column(name = "OPT_TYPE")
	private String optType;
	/**
	 * 操作内容，主要填写流程中的结果，比如通过、不通过
	 */
	 @Column(name = "OPT_CONTENT")
	private String optContent;
	/**
	 * 操作说明，填写审批时的意见
	 */
	 @Column(name = "OPT_DESC")
	private String optDesc;
	/**
	 * 操作时间，yyyyMMddHHmmss
	 */
	 @Column(name = "OPT_TIME")
	private String optTime;
	
	
	/**
	 * 设置主键ID
	 */
	public void setHisId(String hisId) {
		this.hisId = hisId;
	}
	
	/**
	 * 获取主键ID
	 */
	public String getHisId() {
		return hisId;
	}
	/**
	 * 设置作业票拟定主键ID
	 */
	public void setCxMakeId(String cxMakeId) {
		this.cxMakeId = cxMakeId;
	}
	
	/**
	 * 获取作业票拟定主键ID
	 */
	public String getCxMakeId() {
		return cxMakeId;
	}
	/**
	 * 设置操作人ID
	 */
	public void setOptId(String optId) {
		this.optId = optId;
	}
	
	/**
	 * 获取操作人ID
	 */
	public String getOptId() {
		return optId;
	}
	/**
	 * 设置操作人名称
	 */
	public void setOptName(String optName) {
		this.optName = optName;
	}
	
	/**
	 * 获取操作人名称
	 */
	public String getOptName() {
		return optName;
	}
	/**
	 * 设置操作类型 1?拟定??2??审核??3?中止?4?填报5?验收
	 */
	public void setOptType(String optType) {
		this.optType = optType;
	}
	
	/**
	 * 获取操作类型 1?拟定??2??审核??3?中止?4?填报5?验收
	 */
	public String getOptType() {
		return optType;
	}
	/**
	 * 设置操作内容，主要填写流程中的结果，比如通过、不通过
	 */
	public void setOptContent(String optContent) {
		this.optContent = optContent;
	}
	
	/**
	 * 获取操作内容，主要填写流程中的结果，比如通过、不通过
	 */
	public String getOptContent() {
		return optContent;
	}
	/**
	 * 设置操作说明，填写审批时的意见
	 */
	public void setOptDesc(String optDesc) {
		this.optDesc = optDesc;
	}
	
	/**
	 * 获取操作说明，填写审批时的意见
	 */
	public String getOptDesc() {
		return optDesc;
	}
	/**
	 * 设置操作时间，yyyyMMddHHmmss
	 */
	public void setOptTime(String optTime) {
		this.optTime = optTime;
	}
	
	/**
	 * 获取操作时间，yyyyMMddHHmmss
	 */
	public String getOptTime() {
		return optTime;
	}
}