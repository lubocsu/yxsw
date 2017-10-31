/*
 * SysRoleUser.java
 * Created on 2015年1月23日 下午1:59:07
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

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：SysRoleUser.java<br>
 * 摘要：简要描述本文件的内容<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：蒋迪<br>
 * 完成日期：2015年1月23日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：蒋迪<br>
 * 完成日期：2015年1月23日<br>
 */
@Entity
@Table(name="sys_attachment")
public class SysAttachment extends BaseEntity {
	
	@Id
	@GenericGenerator(name="custom",strategy="uuid.hex")
	@GeneratedValue(generator="custom")
	@Column(name="Aid")
	private String aId;
	
	@Column(name="RecordType")
	private String recordType;
	
	@Column(name="RecordId")
	private String recordId;
	
	@Column(name="Title")
	private String title;
	
	@Column(name="SaveTag")
	private Integer saveTag;
	
	@Column(name="SavePath")
	private String savePath;
	
	@Column(name="FileName")
	private String fileName;
	
	@Column(name="UpdateDate")
	private Date updateDate;
	
	@Column(name = "Remark")
	private String remark;
	
	public String getaId() {
		return aId;
	}

	public void setaId(String aId) {
		this.aId = aId;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getSaveTag() {
		return saveTag;
	}

	public void setSaveTag(Integer saveTag) {
		this.saveTag = saveTag;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
