package com.upsoft.system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 * 
 * 文件名称：Role.java<br>
 * 摘要：数据权限实体类<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：孔纲<br>
 * 完成日期：2016年5月17日<br>
 */
@Entity
@Table(name = "SYS_PERMISSION_DATA")
public class SysPermssionDataEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GenericGenerator(name = "custom", strategy = "uuid.hex")
    @GeneratedValue(generator = "custom")
	@Column(name = "id")
	private String id;
	
	@Column(name = "type")
	private Integer type;
	
	@Column(name = "relateId")
	private String relateId;
	
	@Column(name = "orgId")
	private String orgId;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "systemCode")
	private String systemCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRelateId() {
		return relateId;
	}

	public void setRelateId(String relateId) {
		this.relateId = relateId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

}
