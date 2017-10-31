package com.upsoft.yxsw.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.upsoft.system.entity.BaseEntity;
import com.upsoft.yxsw.mobile.bean.AttachmentPathAndNameBean;

@Entity
@Table(name = "BIZ_T_GG_NOTICE_MANAGE")
public class BizTGgNoticeManage extends BaseEntity implements Serializable {

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
	@Column(name = "NOTICE_ID")
	private String noticeId;
	/**
	 * 标题
	 */
	@Column(name = "TITLE")
	private String title;
	/**
	 * 内容
	 */
	@Column(name = "CONTENT", columnDefinition = "CLOB")
	private String content;
	/**
	 * 重要程度： 1 一般 2 重要
	 */
	@Column(name = "IMPORTANT_LEVEL")
	private String importantLevel;
	/**
	 * 公告类型： 1 政策宣传 2 教育学习 3 安全提醒 4 其他
	 */
	@Column(name = "GG_TYPE")
	private String ggType;
	/**
	 * 有效期
	 */
	@Column(name = "LIMIT_DATE")
	private String limitDate;
	/**
	 * 是否有效
	 */
	@Column(name = "IS_ALIVE")
	private Long isAlive;
	/**
	 * 备用：标题图标，附件ID
	 */
	@Column(name = "TITLE_ICO")
	private String titleIco;
	/**
	 * 备注
	 */
	@Column(name = "NOTICE_DESC")
	private String noticeDesc;
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
	 * 所属部门
	 */
	@Column(name = "BELONG_DEPT")
	private String belongDept;
	/**
	 * 所属单位ID，非业务数据时非必填
	 */
	@Column(name = "BELONG_WSC_ID")
	private String belongWscId;
	/**
	 * 所属单位名称，非业务数据时非必填
	 */
	@Column(name = "BELONG_WSC_NAME")
	private String belongWscName;
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

	// 自定义字段
	@Transient
	private String importantLevelName;
	@Transient
	private String noticeTypeName;
	@Transient
	private List<AttachmentPathAndNameBean> attachments;
	

	/**
	 * 设置主键ID
	 */
	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	/**
	 * 获取主键ID
	 */
	public String getNoticeId() {
		return noticeId;
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
	 * 设置重要程度： 1 一般 2 重要
	 */
	public void setImportantLevel(String importantLevel) {
		this.importantLevel = importantLevel;
	}

	/**
	 * 获取重要程度： 1 一般 2 重要
	 */
	public String getImportantLevel() {
		return importantLevel;
	}

	/**
	 * 设置公告类型： 1 政策宣传 2 教育学习 3 安全提醒 4 其他
	 */
	public void setGgType(String ggType) {
		this.ggType = ggType;
	}

	/**
	 * 获取公告类型： 1 政策宣传 2 教育学习 3 安全提醒 4 其他
	 */
	public String getGgType() {
		return ggType;
	}

	/**
	 * 设置有效期
	 */
	public void setLimitDate(String limitDate) {
		this.limitDate = limitDate;
	}

	/**
	 * 获取有效期
	 */
	public String getLimitDate() {
		return limitDate;
	}

	/**
	 * 设置是否有效
	 */
	public void setIsAlive(Long isAlive) {
		this.isAlive = isAlive;
	}

	/**
	 * 获取是否有效
	 */
	public Long getIsAlive() {
		return isAlive;
	}

	/**
	 * 设置备用：标题图标，附件ID
	 */
	public void setTitleIco(String titleIco) {
		this.titleIco = titleIco;
	}

	/**
	 * 获取备用：标题图标，附件ID
	 */
	public String getTitleIco() {
		return titleIco;
	}

	/**
	 * 设置备注
	 */
	public void setNoticeDesc(String noticeDesc) {
		this.noticeDesc = noticeDesc;
	}

	/**
	 * 获取备注
	 */
	public String getNoticeDesc() {
		return noticeDesc;
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
	 * 设置所属单位ID，非业务数据时非必填
	 */
	public void setBelongWscId(String belongWscId) {
		this.belongWscId = belongWscId;
	}

	/**
	 * 获取所属单位ID，非业务数据时非必填
	 */
	public String getBelongWscId() {
		return belongWscId;
	}

	/**
	 * 设置所属单位名称，非业务数据时非必填
	 */
	public void setBelongWscName(String belongWscName) {
		this.belongWscName = belongWscName;
	}

	/**
	 * 获取所属单位名称，非业务数据时非必填
	 */
	public String getBelongWscName() {
		return belongWscName;
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

	public String getImportantLevelName() {
		return importantLevelName;
	}

	public void setImportantLevelName(String importantLevelName) {
		this.importantLevelName = importantLevelName;
	}

	public String getNoticeTypeName() {
		return noticeTypeName;
	}

	public void setNoticeTypeName(String noticeTypeName) {
		this.noticeTypeName = noticeTypeName;
	}

	public List<AttachmentPathAndNameBean> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<AttachmentPathAndNameBean> attachments) {
		this.attachments = attachments;
	}
}