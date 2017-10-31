package com.upsoft.system.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 * 
 * 文件名称：SysMessageEntity.java<br>
 * 摘要：系统消息实体<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.0.0<br>
 * 作者：冉恒鑫<br>
 * 完成日期：2015年3月17日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.0.0<br>
 * 原作者：冉恒鑫<br>
 * 完成日期：2015年3月17日<br>
 */
@Entity
@Table(name = "sys_message")
public class SysMessageEntity extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8449887580516874702L;
	
	//未读
	public static final Integer STATUS_UN_READ = 1;
	//已读
	public static final Integer STATUS_READ = 2;
	//全部
	public static final Integer STATUS_ALL = 0;
	
	
	//管理员ID，发送人默认管理员
	public static final String ADMIN_ID = "D6F6FDF53BE24D2EB871F3E52A1F5B45";
	
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "MessageId")
	// 消息ID
	private String messageId;

	// 消息标题
	@Column(name = "Title")
	private String title;

	// 消息内容
	@Column(name = "Content")
	private String content;

	// 消息状态(1：未读；2：已读；3：删除)
	@Column(name = "Status")
	private Integer status;

	// 发送人ID
	@Column(name = "SenderId")
	private String senderId;

	// 接收人ID
	@Column(name = "ReceiverId")
	private String receiverId;

	// 发送时间
	@Column(name = "SendTime")
	private Date sendTime;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

}
