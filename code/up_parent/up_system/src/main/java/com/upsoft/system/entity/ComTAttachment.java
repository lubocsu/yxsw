package com.upsoft.system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "COM_T_ATTACHMENT")
public class ComTAttachment extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 附件ID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "ATTACHMENT_ID")
	private String attachmentId;
	/**
	 * 处理信息ID
	 */
	@Column(name = "BUSINESS_ID")
	private String businessId;
	/**
	 * 附件路径，相对路径加完整重命名后的附件名称+后缀
	 */
	@Column(name = "ATTACHMENT_PATH")
	private String attachmentPath;
	/**
	 * 不包含后缀，时间戳重命名
	 */
	@Column(name = "NEW_ATTACHMENT_NAME")
	private String newAttachmentName;
	/**
	 * 附件后缀名
	 */
	@Column(name = "ATTACHMENT_SUFFIX")
	private String attachmentSuffix;
	/**
	 * 附件大小
	 */
	@Column(name = "ATTACHMENT_SIZE")
	private String attachmentSize;
	/**
	 * 附件序号
	 */
	@Column(name = "ATTACHMENT_INDEX")
	private Integer attachmentIndex;
	/**
	 * 附件类型：1.处理前 | 2.处理后
	 */
	@Column(name = "ATTACHMENT_TYPE")
	private String attachmentType;
	/**
	 * 是否有效：0.无效 | 1.有效
	 */
	@Column(name = "VALID")
	private String valid;
	/**
	 * 创建人机构
	 */
	@Column(name = "CREATOR_ORG")
	private String creatorOrg;
	/**
	 * 创建人
	 */
	@Column(name = "CREATOR")
	private String creator;
	/**
	 * 创建时间
	 */
	@Column(name = "CREATE_TIME")
	private String createTime;
	/**
	 * 修改人机构
	 */
	@Column(name = "MODIFIER_ORG")
	private String modifierOrg;
	/**
	 * 修改人
	 */
	@Column(name = "MODIFIER")
	private String modifier;
	/**
	 * 修改时间
	 */
	@Column(name = "MODIFY_TIME")
	private String modifyTime;
	/**
	 * 时间戳
	 */
	@Column(name = "TIME_STAMP")
	private String timeStamp;
	/**
	 * 业务模块ID
	 */
	@Column(name = "MODEL_ID")
	private String modelId;
	/**
	 * 原有文件名
	 */
	@Column(name = "OLD_ATTACHMENT_NAME")
	private String oldAttachmentName;

	/**
	 * 设置附件ID
	 */
	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	/**
	 * 获取附件ID
	 */
	public String getAttachmentId() {
		return attachmentId;
	}

	/**
	 * 设置处理信息ID
	 */
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	/**
	 * 获取处理信息ID
	 */
	public String getBusinessId() {
		return businessId;
	}

	/**
	 * 设置附件路径，相对路径加完整重命名后的附件名称+后缀
	 */
	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	/**
	 * 获取附件路径，相对路径加完整重命名后的附件名称+后缀
	 */
	public String getAttachmentPath() {
		return attachmentPath;
	}

	/**
	 * 设置不包含后缀，时间戳重命名
	 */
	public void setNewAttachmentName(String newAttachmentName) {
		this.newAttachmentName = newAttachmentName;
	}

	/**
	 * 获取不包含后缀，时间戳重命名
	 */
	public String getNewAttachmentName() {
		return newAttachmentName;
	}

	/**
	 * 设置附件后缀名
	 */
	public void setAttachmentSuffix(String attachmentSuffix) {
		this.attachmentSuffix = attachmentSuffix;
	}

	/**
	 * 获取附件后缀名
	 */
	public String getAttachmentSuffix() {
		return attachmentSuffix;
	}

	/**
	 * 设置附件大小
	 */
	public void setAttachmentSize(String attachmentSize) {
		this.attachmentSize = attachmentSize;
	}

	/**
	 * 获取附件大小
	 */
	public String getAttachmentSize() {
		return attachmentSize;
	}

	/**
	 * 设置附件序号
	 */
	public void setAttachmentIndex(Integer attachmentIndex) {
		this.attachmentIndex = attachmentIndex;
	}

	/**
	 * 获取附件序号
	 */
	public Integer getAttachmentIndex() {
		return attachmentIndex;
	}

	/**
	 * 设置附件类型：1.处理前 | 2.处理后
	 */
	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}

	/**
	 * 获取附件类型：1.处理前 | 2.处理后
	 */
	public String getAttachmentType() {
		return attachmentType;
	}

	/**
	 * 设置是否有效：0.无效 | 1.有效
	 */
	public void setValid(String valid) {
		this.valid = valid;
	}

	/**
	 * 获取是否有效：0.无效 | 1.有效
	 */
	public String getValid() {
		return valid;
	}

	/**
	 * 设置创建人机构
	 */
	public void setCreatorOrg(String creatorOrg) {
		this.creatorOrg = creatorOrg;
	}

	/**
	 * 获取创建人机构
	 */
	public String getCreatorOrg() {
		return creatorOrg;
	}

	/**
	 * 设置创建人
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * 获取创建人
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * 设置创建时间
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取创建时间
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 设置修改人机构
	 */
	public void setModifierOrg(String modifierOrg) {
		this.modifierOrg = modifierOrg;
	}

	/**
	 * 获取修改人机构
	 */
	public String getModifierOrg() {
		return modifierOrg;
	}

	/**
	 * 设置修改人
	 */
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	/**
	 * 获取修改人
	 */
	public String getModifier() {
		return modifier;
	}

	/**
	 * 设置修改时间
	 */
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * 获取修改时间
	 */
	public String getModifyTime() {
		return modifyTime;
	}

	/**
	 * 设置时间戳
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * 获取时间戳
	 */
	public String getTimeStamp() {
		return timeStamp;
	}

	/**
	 * 设置业务模块ID
	 */
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	/**
	 * 获取业务模块ID
	 */
	public String getModelId() {
		return modelId;
	}

	/**
	 * 设置原有文件名
	 */
	public void setOldAttachmentName(String oldAttachmentName) {
		this.oldAttachmentName = oldAttachmentName;
	}

	/**
	 * 获取原有文件名
	 */
	public String getOldAttachmentName() {
		return oldAttachmentName;
	}
}