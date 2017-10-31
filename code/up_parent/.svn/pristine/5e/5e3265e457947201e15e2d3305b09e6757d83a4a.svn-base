package com.upsoft.yxsw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.upsoft.system.entity.BaseEntity;

@Entity
@Table(name = "BIZ_T_GG_MSG_MANAGE")
public class BizTGgMsgManage extends BaseEntity implements Serializable {

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
	@Column(name = "MSG_ID")
	private String msgId;
	/**
	 * 标题
	 */
	@Column(name = "TITLE")
	private String title;
	/**
	 * 内容
	 */
	@Column(name = "CONTENT")
	private String content;
	/**
	 * 备用，重要程度： 1 一般 2 重要
	 */
	@Column(name = "IMPORTANT_LEVEL")
	private String importantLevel;
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
	 * 所属部门
	 */
	@Column(name = "BELONG_DEPT")
	private String belongDept;
	/**
	 * 消息接收人ID
	 */
	@Column(name = "RECIVER_ID")
	private String reciverId;
	/**
	 * 消息接收人名称
	 */
	@Column(name = "RECIVER_NAME")
	private String reciverName;
	/**
	 * 是否已读 1是 0否
	 */
	@Column(name = "IS_HAVE_READ")
	private Long isHaveRead;

	@Transient
    private String sendOrReciver ; // 自定义字段 ，判断是我发送还是接收1 我发送  0 我接收
	
	/**
	 * 设置主键ID
	 */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	/**
	 * 获取主键ID
	 */
	public String getMsgId() {
		return msgId;
	}

	/**
	 * 设置标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置备用，重要程度： 1 一般 2 重要
	 */
	public void setImportantLevel(String importantLevel) {
		this.importantLevel = importantLevel;
	}

	/**
	 * 获取备用，重要程度： 1 一般 2 重要
	 */
	public String getImportantLevel() {
		return importantLevel;
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
	 * 设置所属部门
	 */
	public void setBelongDept(String belongDept) {
		this.belongDept = belongDept;
	}

	/**
	 * 获取所属部门
	 */
	public String getBelongDept() {
		return belongDept;
	}

	/**
	 * 设置消息接收人ID
	 */
	public void setReciverId(String reciverId) {
		this.reciverId = reciverId;
	}

	/**
	 * 获取消息接收人ID
	 */
	public String getReciverId() {
		return reciverId;
	}

	/**
	 * 设置消息接收人名称
	 */
	public void setReciverName(String reciverName) {
		this.reciverName = reciverName;
	}

	/**
	 * 获取消息接收人名称
	 */
	public String getReciverName() {
		return reciverName;
	}

	/**
	 * 设置是否已读 1是 0否
	 */
	public void setIsHaveRead(Long isHaveRead) {
		this.isHaveRead = isHaveRead;
	}

	/**
	 * 获取是否已读 1是 0否
	 */
	public Long getIsHaveRead() {
		return isHaveRead;
	}

	public String getSendOrReciver() {
		return sendOrReciver;
	}

	public void setSendOrReciver(String sendOrReciver) {
		this.sendOrReciver = sendOrReciver;
	}
}