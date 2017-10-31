package com.upsoft.yxsw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.upsoft.system.entity.BaseEntity;

/**
* Copyright (c) 2017,重庆扬讯软件技术股份有限公司<br>
* All rights reserved.<br>
*
* 文件名称：BizTBaseFactoryInfoEntity.java<br>
* 摘要：设备厂商entity<br>
* -------------------------------------------------------<br>
* 当前版本：1.0<br>
* 作者：陈涛<br>
* 完成日期：2017年9月9日<br>
* -------------------------------------------------------<br>
*/
@Entity
@Table(name = "BIZ_T_BASE_FACTORY_INFO")
public class BizTBaseFactoryInfoEntity extends BaseEntity implements Serializable{


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
	 @Column(name = "ID")
	private String id;
	/**
	 * 厂商名称
	 */
	 @Column(name = "NAME")
	private String name;
	/**
	 * 厂商类型：施工或设备商
		01	设备供应商
		02	施工商
	 */
	 @Column(name = "TYPE")
	private String type;
	/**
	 * 厂商性质：厂商的资金性质
		01	国企
		02	民营
		03	中外合资
		04	外资
	 */
	 @Column(name = "ZJXZ")
	private String zjxz;
	/**
	 * 厂商地址
	 */
	 @Column(name = "ADDRESS")
	private String address;
	/**
	 * 厂商法人代表
	 */
	 @Column(name = "RESPONSOBILITY")
	private String responsobility;
	/**
	 * 企业联系电话
	 */
	 @Column(name = "FACTORY_TEL")
	private String factoryTel;
	/**
	 * 主要联系人
	 */
	 @Column(name = "CONTACT_PEOPLE")
	private String contactPeople;
	/**
	 * 主要联系人手机
	 */
	 @Column(name = "CONTACT_PHONE")
	private String contactPhone;
	/**
	 * 其他联系方式
	 */
	 @Column(name = "CONTACT_OTHERWAY")
	private String contactOtherway;
	/**
	 * 联系人邮箱
	 */
	 @Column(name = "CONTACT_MAIL")
	private String contactMail;
	/**
	 * 主要经营范围
	 */
	 @Column(name = "BUSINESS_SCOPE")
	private String businessScope;
	/**
	 * 工商登记号码
	 */
	 @Column(name = "GSDJH")
	private String gsdjh;
	/**
	 * 纳税人识别号
	 */
	 @Column(name = "NRSSBH")
	private String nrssbh;
	/**
	 * 社会信用代码
	 */
	 @Column(name = "SHXYDM")
	private String shxydm;
	/**
	 * 企业官网
	 */
	 @Column(name = "OFFICIAL_WEBSITE")
	private String officialWebsite;
	/**
	 * X坐标
	 */
	 @Column(name = "LONGITUDE")
	private Double longitude;
	/**
	 * Y坐标
	 */
	 @Column(name = "LATITUDE")
	private Double latitude;
	/**
	 * 备注说明
	 */
	 @Column(name = "REAMRK1")
	private String reamrk1;
	/**
	 * 设置是否停用该供应商，已经停用的供应商不能再作为未来采购的来源：1是 0否
	 */
	 @Column(name = "OUT_SERVICE")
	private Long outService;
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
	 * 修改人ID或账号
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
	 * 设置主键ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 获取主键ID
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置厂商名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取厂商名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置厂商类型：施工或设备商
		01	设备供应商
		02	施工商
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 获取厂商类型：施工或设备商
		01	设备供应商
		02	施工商
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置厂商性质：厂商的资金性质
		01	国企
		02	民营
		03	中外合资
		04	外资
	 */
	public void setZjxz(String zjxz) {
		this.zjxz = zjxz;
	}
	
	/**
	 * 获取厂商性质：厂商的资金性质
		01	国企
		02	民营
		03	中外合资
		04	外资
	 */
	public String getZjxz() {
		return zjxz;
	}
	/**
	 * 设置厂商地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 获取厂商地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置厂商法人代表
	 */
	public void setResponsobility(String responsobility) {
		this.responsobility = responsobility;
	}
	
	/**
	 * 获取厂商法人代表
	 */
	public String getResponsobility() {
		return responsobility;
	}
	/**
	 * 设置企业联系电话
	 */
	public void setFactoryTel(String factoryTel) {
		this.factoryTel = factoryTel;
	}
	
	/**
	 * 获取企业联系电话
	 */
	public String getFactoryTel() {
		return factoryTel;
	}
	/**
	 * 设置主要联系人
	 */
	public void setContactPeople(String contactPeople) {
		this.contactPeople = contactPeople;
	}
	
	/**
	 * 获取主要联系人
	 */
	public String getContactPeople() {
		return contactPeople;
	}
	/**
	 * 设置主要联系人手机
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	
	/**
	 * 获取主要联系人手机
	 */
	public String getContactPhone() {
		return contactPhone;
	}
	/**
	 * 设置其他联系方式
	 */
	public void setContactOtherway(String contactOtherway) {
		this.contactOtherway = contactOtherway;
	}
	
	/**
	 * 获取其他联系方式
	 */
	public String getContactOtherway() {
		return contactOtherway;
	}
	/**
	 * 设置联系人邮箱
	 */
	public void setContactMail(String contactMail) {
		this.contactMail = contactMail;
	}
	
	/**
	 * 获取联系人邮箱
	 */
	public String getContactMail() {
		return contactMail;
	}
	/**
	 * 设置主要经营范围
	 */
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}
	
	/**
	 * 获取主要经营范围
	 */
	public String getBusinessScope() {
		return businessScope;
	}
	/**
	 * 设置工商登记号码
	 */
	public void setGsdjh(String gsdjh) {
		this.gsdjh = gsdjh;
	}
	
	/**
	 * 获取工商登记号码
	 */
	public String getGsdjh() {
		return gsdjh;
	}
	/**
	 * 设置纳税人识别号
	 */
	public void setNrssbh(String nrssbh) {
		this.nrssbh = nrssbh;
	}
	
	/**
	 * 获取纳税人识别号
	 */
	public String getNrssbh() {
		return nrssbh;
	}
	/**
	 * 设置社会信用代码
	 */
	public void setShxydm(String shxydm) {
		this.shxydm = shxydm;
	}
	
	/**
	 * 获取社会信用代码
	 */
	public String getShxydm() {
		return shxydm;
	}
	/**
	 * 设置企业官网
	 */
	public void setOfficialWebsite(String officialWebsite) {
		this.officialWebsite = officialWebsite;
	}
	
	/**
	 * 获取企业官网
	 */
	public String getOfficialWebsite() {
		return officialWebsite;
	}
	/**
	 * 设置X坐标
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * 获取X坐标
	 */
	public Double getLongitude() {
		return longitude;
	}
	/**
	 * 设置Y坐标
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * 获取Y坐标
	 */
	public Double getLatitude() {
		return latitude;
	}
	/**
	 * 设置备注说明
	 */
	public void setReamrk1(String reamrk1) {
		this.reamrk1 = reamrk1;
	}
	
	/**
	 * 获取备注说明
	 */
	public String getReamrk1() {
		return reamrk1;
	}
	/**
	 * 设置设置是否停用该供应商，已经停用的供应商不能再作为未来采购的来源：1是 0否
	 */
	public void setOutService(Long outService) {
		this.outService = outService;
	}
	
	/**
	 * 获取设置是否停用该供应商，已经停用的供应商不能再作为未来采购的来源：1是 0否
	 */
	public Long getOutService() {
		return outService;
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
	 * 设置修改人ID或账号
	 */
	public void setUpdatorAccount(String updatorAccount) {
		this.updatorAccount = updatorAccount;
	}
	
	/**
	 * 获取修改人ID或账号
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
