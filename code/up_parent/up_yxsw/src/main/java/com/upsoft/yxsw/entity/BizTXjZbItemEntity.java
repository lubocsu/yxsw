
package com.upsoft.yxsw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.upsoft.system.entity.BaseEntity;
/**
 * 作业票下的指标项管理实体
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：BizTXjZbItemEntity.java<br>
* 摘要：简要描述本文件的内容<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：杨磊<br>
* 完成日期：2017年9月11日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：杨磊<br>
* 完成日期：2017年9月11日<br>
 */
@Entity
@Table(name = "BIZ_T_XJ_ZB_ITEM")
public class BizTXjZbItemEntity extends BaseEntity {

	/**
	 * CXZB_ID
	 */
	 @Id
	 @GenericGenerator(name = "generator", strategy = "uuid.hex")
	 @GeneratedValue(generator = "generator")
	 @Column(name = "CXZB_ID")
	private String cxzbId;
	/**
	 * 指标CODE
	 */
	 @Column(name = "CXZB_CODE")
	private String cxzbCode;
	/**
	 * 指标名称
	 */
	 @Column(name = "CXZB_NAME")
	private String cxzbName;
	/**
	 * 简称
	 */
	 @Column(name = "CXZB_JC")
	private String cxzbJc;
	/**
	 * 计量单位
	 */
	 @Column(name = "CXZB_UNIT")
	private String cxzbUnit;
	/**
	 * 指标说明
	 */
	 @Column(name = "CXZB_DESC")
	private String cxzbDesc;
	/**
	 * 巡检指标填报类型，字典项【作业票指标输入类型】
2	数值
1	普通文本
	 */
	 @Column(name = "CXZB_TBLX")
	private String cxzbTblx;
	/**
	 * 备用字段
	 */
	 @Column(name = "BYZD")
	private String byzd;
	/**
	 * 是否删除，1是 0否
	 */
	 @Column(name = "DEL_FLAG")
	private Integer delFlag;
	/**
	 * 创建时间，一般不作为业务字段使用
	 */
	 @Column(name = "CREATE_TIMESTEMP")
	private String createTimestemp;
	/**
	 * 创建人ID或账号
	 */
	 @Column(name = "CREATOR_ACCOUNT")
	private String creatorAccount;
	/**
	 * 创建人ID或账号
	 */
	 @Column(name = "UPDATOR_ACCOUNT")
	private String updatorAccount;
	/**
	 * 修改人姓名
	 */
	 @Column(name = "UPDATOR_NAME")
	private String updatorName;
	/**
	 * 修改时间，一般不作为业务字段使用
	 */
	 @Column(name = "UPDATE_TIMESTEMP")
	private String updateTimestemp;
	
	
	/**
	 * 设置CXZB_ID
	 */
	public void setCxzbId(String cxzbId) {
		this.cxzbId = cxzbId;
	}
	
	/**
	 * 获取CXZB_ID
	 */
	public String getCxzbId() {
		return cxzbId;
	}
	/**
	 * 设置指标CODE
	 */
	public void setCxzbCode(String cxzbCode) {
		this.cxzbCode = cxzbCode;
	}
	
	/**
	 * 获取指标CODE
	 */
	public String getCxzbCode() {
		return cxzbCode;
	}
	/**
	 * 设置指标名称
	 */
	public void setCxzbName(String cxzbName) {
		this.cxzbName = cxzbName;
	}
	
	/**
	 * 获取指标名称
	 */
	public String getCxzbName() {
		return cxzbName;
	}
	/**
	 * 设置简称
	 */
	public void setCxzbJc(String cxzbJc) {
		this.cxzbJc = cxzbJc;
	}
	
	/**
	 * 获取简称
	 */
	public String getCxzbJc() {
		return cxzbJc;
	}
	/**
	 * 设置计量单位
	 */
	public void setCxzbUnit(String cxzbUnit) {
		this.cxzbUnit = cxzbUnit;
	}
	
	/**
	 * 获取计量单位
	 */
	public String getCxzbUnit() {
		return cxzbUnit;
	}
	/**
	 * 设置指标说明
	 */
	public void setCxzbDesc(String cxzbDesc) {
		this.cxzbDesc = cxzbDesc;
	}
	
	/**
	 * 获取指标说明
	 */
	public String getCxzbDesc() {
		return cxzbDesc;
	}
	/**
	 * 设置巡检指标填报类型，字典项【作业票指标输入类型】
2	数值
1	普通文本
	 */
	public void setCxzbTblx(String cxzbTblx) {
		this.cxzbTblx = cxzbTblx;
	}
	
	/**
	 * 获取巡检指标填报类型，字典项【作业票指标输入类型】
2	数值
1	普通文本
	 */
	public String getCxzbTblx() {
		return cxzbTblx;
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
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	
	/**
	 * 获取是否删除，1是 0否
	 */
	public Integer getDelFlag() {
		return delFlag;
	}
	/**
	 * 设置创建时间，一般不作为业务字段使用
	 */
	public void setCreateTimestemp(String createTimestemp) {
		this.createTimestemp = createTimestemp;
	}
	
	/**
	 * 获取创建时间，一般不作为业务字段使用
	 */
	public String getCreateTimestemp() {
		return createTimestemp;
	}
	/**
	 * 设置创建人ID或账号
	 */
	public void setCreatorAccount(String creatorAccount) {
		this.creatorAccount = creatorAccount;
	}
	
	/**
	 * 获取创建人ID或账号
	 */
	public String getCreatorAccount() {
		return creatorAccount;
	}
	/**
	 * 设置创建人ID或账号
	 */
	public void setUpdatorAccount(String updatorAccount) {
		this.updatorAccount = updatorAccount;
	}
	
	/**
	 * 获取创建人ID或账号
	 */
	public String getUpdatorAccount() {
		return updatorAccount;
	}
	/**
	 * 设置修改人姓名
	 */
	public void setUpdatorName(String updatorName) {
		this.updatorName = updatorName;
	}
	
	/**
	 * 获取修改人姓名
	 */
	public String getUpdatorName() {
		return updatorName;
	}
	/**
	 * 设置修改时间，一般不作为业务字段使用
	 */
	public void setUpdateTimestemp(String updateTimestemp) {
		this.updateTimestemp = updateTimestemp;
	}
	
	/**
	 * 获取修改时间，一般不作为业务字段使用
	 */
	public String getUpdateTimestemp() {
		return updateTimestemp;
	}
}