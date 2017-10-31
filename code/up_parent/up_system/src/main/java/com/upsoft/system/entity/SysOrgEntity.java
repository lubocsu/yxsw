package com.upsoft.system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.upsoft.system.support.annotation.ExcelAnnotation;

/**
 * 
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 * 
 * 文件名称：SysOrg.java<br>
 * 摘要：组织机构实体<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：胡毅<br>
 * 完成日期：2015年1月21日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：胡毅<br>
 * 完成日期：2015年1月21日<br>
 */
@Entity
@Table(name = "sys_org")
public class SysOrgEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -6675670175345099190L;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "OrgId")
	// 机构ID
	private String orgId;

	// 父机构ID（0表示顶级机构）
	@Column(name = "ParentOrgId")
	private String parentOrgId;

	// 机构代码
	@Column(name = "OrgCode")
	@ExcelAnnotation(label = "机构代码")
	private String orgCode;

	// 机构名称
	@Column(name = "OrgName")
	@ExcelAnnotation(label = "机构名称")
	private String orgName;

	// 机构简称
	@Column(name = "OrgShrotName")
	@ExcelAnnotation(label = "机构简称")
	private String orgShrotName;

	// 机构拼音码
	@Column(name = "ShortSpelling")
	@ExcelAnnotation(label = "机构拼音码")
	private String shortSpelling;

	// 机构类型ID
	@Column(name = "OrgTypeId")
	@ExcelAnnotation(label = "机构类型", dictCode = "S000011")
	private String orgTypeId;

	// 是否有效（是否有效：0:否；1:是）
	@Column(name = "Enabled")
	@ExcelAnnotation(label = "是否有效")
	private Integer enabled;

	// 地址X坐标
	@Column(name = "X")
	private String x;

	// 地址Y坐标
	@Column(name = "Y")
	private String y;

	// 机构地址
	@Column(name = "Address")
	@ExcelAnnotation(label = "机构地址")
	private String address;

	// 机构邮编
	@Column(name = "Zip")
	@ExcelAnnotation(label = "邮编")
	private String zip;

	// 联系电话
	@Column(name = "Tel")
	@ExcelAnnotation(label = "联系电话")
	private String tel;

	// 机构负责人
	@Column(name = "Principal")
	@ExcelAnnotation(label = "负责人")
	private String principal;

	// 排序号
	@Column(name = "OrderNo")
	@ExcelAnnotation(label = "排序号")
	private Integer orderNo;

	// 备注说明
	@Column(name = "Description")
	@ExcelAnnotation(label = "备注")
	private String description;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgShrotName() {
		return orgShrotName;
	}

	public void setOrgShrotName(String orgShrotName) {
		this.orgShrotName = orgShrotName;
	}

	public String getShortSpelling() {
		return shortSpelling;
	}

	public void setShortSpelling(String shortSpelling) {
		this.shortSpelling = shortSpelling;
	}

	public String getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(String orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
