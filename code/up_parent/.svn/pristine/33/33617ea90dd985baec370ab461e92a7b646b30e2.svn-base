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
@Table(name = "BIZ_T_XJ_TECHNICS_SCOPE_MANAGE")
public class BizTXjTechnicsScopeManage extends BaseEntity implements Serializable{

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
	 @Column(name = "TECHNICS_ID")
	private String technicsId;
	/**
	 * 工艺段名称
	 */
	 @Column(name = "TECHNICS_NAME")
	private String technicsName;
	/**
	 * 工艺段说明
	 */
	 @Column(name = "TECHNICS_DESC")
	private String technicsDesc;
	/**
	 * 备用字段
	 */
	 @Column(name = "YBZD")
	private String ybzd;
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
	 * 创建人名称
	 */
	 @Column(name = "CREATOR_NAME")
	private String creatorName;
	/**
	 * 所属厂站ID，非业务数据时非必填
	 */
	 @Column(name = "BELONG_WSC_ID")
	private String belongWscId;
	/**
	 * 所属厂站名称，非业务数据时非必填
	 */
	 @Column(name = "BELONG_WSCN_AME")
	private String belongWscnAme;
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
	 * 是否删除，1是 0否
	 */
	 @Column(name = "DEL_FLAG")
	private Long delFlag;
	
	
	/**
	 * 设置主键ID
	 */
	public void setTechnicsId(String technicsId) {
		this.technicsId = technicsId;
	}
	
	/**
	 * 获取主键ID
	 */
	public String getTechnicsId() {
		return technicsId;
	}
	/**
	 * 设置工艺段名称
	 */
	public void setTechnicsName(String technicsName) {
		this.technicsName = technicsName;
	}
	
	/**
	 * 获取工艺段名称
	 */
	public String getTechnicsName() {
		return technicsName;
	}
	/**
	 * 设置工艺段说明
	 */
	public void setTechnicsDesc(String technicsDesc) {
		this.technicsDesc = technicsDesc;
	}
	
	/**
	 * 获取工艺段说明
	 */
	public String getTechnicsDesc() {
		return technicsDesc;
	}
	/**
	 * 设置备用字段
	 */
	public void setYbzd(String ybzd) {
		this.ybzd = ybzd;
	}
	
	/**
	 * 获取备用字段
	 */
	public String getYbzd() {
		return ybzd;
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
	 * 设置创建人名称
	 */
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	
	/**
	 * 获取创建人名称
	 */
	public String getCreatorName() {
		return creatorName;
	}
	/**
	 * 设置所属厂站ID，非业务数据时非必填
	 */
	public void setBelongWscId(String belongWscId) {
		this.belongWscId = belongWscId;
	}
	
	/**
	 * 获取所属厂站ID，非业务数据时非必填
	 */
	public String getBelongWscId() {
		return belongWscId;
	}
	/**
	 * 设置所属厂站名称，非业务数据时非必填
	 */
	public void setBelongWscnAme(String belongWscnAme) {
		this.belongWscnAme = belongWscnAme;
	}
	
	/**
	 * 获取所属厂站名称，非业务数据时非必填
	 */
	public String getBelongWscnAme() {
		return belongWscnAme;
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