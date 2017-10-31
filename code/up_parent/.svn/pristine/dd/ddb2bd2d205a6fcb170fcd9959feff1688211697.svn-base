/*
 * Sys_User.java
 * Created on 2015年1月21日 下午4:00:36
 * Copyright (c) 重庆扬讯软件技术有限公司  All Rights Reserved.
 * http://www.upsoft.com.cn
 *
 * This software is the confidential and proprietary information of UPSoft.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with UPSoft.
 */
package com.upsoft.system.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.upsoft.system.support.annotation.ExcelAnnotation;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 * 
 * 文件名称：Sys_User.java<br>
 * 摘要：简要描述本文件的内容<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.2<br>
 * 作者：吴炫<br>
 * 完成日期：2015年1月22日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.1<br>
 * 原作者：蒋迪<br>
 * 完成日期：2015年1月21日<br>
 */
@Entity
@Table(name = "sys_user")
public class SysUser extends BaseEntity {

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "UserId")
	// 人员ID
	private String userId;

	// 人员姓名
	@Column(name = "UserName")
	@ExcelAnnotation(label = "姓名")
	private String userName;

	// 登录用户名
	@Column(name = "LoginId")
	@ExcelAnnotation(label = "用户名")
	private String loginId;

	// 登录密码
	@Column(name = "Password")
	private String password;

	// 人员所属机构ID
	@Column(name = "OrgId")
	@ExcelAnnotation(label = "机构代码")
	private String orgId;
	// 创建日期
	@Column(name = "CreateDate")
	private Date createDate;

	// 帐号有效时间
	@Column(name = "ValidDate")
	private Date validDate;

	// 生日
	@Column(name = "Birthday")
	private Date birthday;

	// 性别
	@Column(name = "Sex")
	private String sex;

	// 邮箱
	@Column(name = "Email")
	private String email;

	// 联系地址
	@Column(name = "Address")
	private String address;

	// 传真
	@Column(name = "Fax")
	private String fax;

	// 工种
	@Column(name = "Occupation")
	@ExcelAnnotation(label = "工种")
	private Integer occupation;
	
	// 办公电话
	@Column(name = "OfficeTel")
	@ExcelAnnotation(label = "办公电话")
	private String officeTel;

	// 移动电话
	@Column(name = "MobileTel")
	private String mobileTel;

	// QQ帐号
	@Column(name = "QQ")
	private String qq;

	// 身份证号
	/**
	 * 巡检系统中有特殊含义，表示登录手机IMME串号
	 */
	@Column(name = "IdCard")
	private String idCard;

	// 用户类型（1系统管理员、2单位管理员、3普通用户）
	@Column(name = "UserType")
	private Integer userType;

	// 是有有效（0停用、1可用、2已删除）
	@Column(name = "Status")
	private Integer status;

	// 排序号
	@Column(name = "OrderNo")
	private Integer orderNo;

	// 备注说明
	@Column(name = "Description")
	private String description;

	//在线状态 0离线,1在线
	@Column(name="ISONLINE")
	private String isOnline;
	
	// 工种岗位
	@Column
	private String workPost;
	
	// 是否时系统用户，默认为否 （0）
	@Column
	private String isSysUser;
	/**
	 * @return the isOnline
	 */
	public String getIsOnline() {
	    return isOnline;
	}

	/**
	 * @param isOnline the isOnline to set
	 */
	public void setIsOnline(String isOnline) {
	    this.isOnline = isOnline;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Integer getOccupation() {
		return occupation;
	}

	public void setOccupation(Integer occupation) {
		this.occupation = occupation;
	}

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	public String getMobileTel() {
		return mobileTel;
	}

	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getWorkPost() {
		return workPost;
	}

	public void setWorkPost(String workPost) {
		this.workPost = workPost;
	}

	public String getIsSysUser() {
		return isSysUser;
	}

	public void setIsSysUser(String isSysUser) {
		this.isSysUser = isSysUser;
	}
}
