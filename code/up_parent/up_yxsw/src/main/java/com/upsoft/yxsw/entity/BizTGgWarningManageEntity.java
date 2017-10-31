package com.upsoft.yxsw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.upsoft.system.entity.BaseEntity;

@Entity
@Table(name = "BIZ_T_GG_WARNING_MANAGE")
public class BizTGgWarningManageEntity extends BaseEntity {

	/**
	 * 主键ID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "WARNING_ID")
	private String warningId;
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
	 * 备用：标题图标，附件ID，只能有一个
	 */
	@Column(name = "TITLE_ICO")
	private String titleIco;
	/**
	 * 提醒类型: 1 设备安全 2 设施安全 3 巡检点安全
	 */
	@Column(name = "TX_TYPE")
	private String txType;
	/**
	 * 备用：是否要求必读，1是 0否
	 */
	@Column(name = "IS_HAVE_READ")
	private Integer isHaveRead;
	/**
	 * 是否重要提醒，[重要提醒可标星]，1是 0否
	 */
	@Column(name = "IS_IMPORTANT")
	private Integer isImportant;
	/**
	 * 是否删除，1是 0否
	 */
	@Column(name = "DEL_FLAG")
	private Integer delFlag;
	/**
	 * 备用字段
	 */
	@Column(name = "BYZD")
	private String byzd;
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

	/**
	 * 设置主键ID
	 */
	public void setWarningId(String warningId) {
		this.warningId = warningId;
	}

	/**
	 * 获取主键ID
	 */
	public String getWarningId() {
		return warningId;
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
	 * 设置备用：标题图标，附件ID，只能有一个
	 */
	public void setTitleIco(String titleIco) {
		this.titleIco = titleIco;
	}

	/**
	 * 获取备用：标题图标，附件ID，只能有一个
	 */
	public String getTitleIco() {
		return titleIco;
	}

	/**
	 * 设置提醒类型: 1 设备安全 2 设施安全 3 巡检点安全
	 */
	public void setTxType(String txType) {
		this.txType = txType;
	}

	/**
	 * 获取提醒类型: 1 设备安全 2 设施安全 3 巡检点安全
	 */
	public String getTxType() {
		return txType;
	}

	/**
	 * 设置备用：是否要求必读，1是 0否
	 */
	public void setIsHaveRead(Integer isHaveRead) {
		this.isHaveRead = isHaveRead;
	}

	/**
	 * 获取备用：是否要求必读，1是 0否
	 */
	public Integer getIsHaveRead() {
		return isHaveRead;
	}

	/**
	 * 设置是否重要提醒，[重要提醒可标星]，1是 0否
	 */
	public void setIsImportant(Integer isImportant) {
		this.isImportant = isImportant;
	}

	/**
	 * 获取是否重要提醒，[重要提醒可标星]，1是 0否
	 */
	public Integer getIsImportant() {
		return isImportant;
	}

	/**
	 * 设置是否删除，1是 0否
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * 获取是否删除，1是 0否
	 */
	public Integer getDelFlag() {
		return delFlag;
	}

	/**
	 * 设置备用字段
	 */
	public void setByzd(String byzd) {
		this.byzd = byzd;
	}

	/**
	 * 获取备用字段
	 */
	public String getByzd() {
		return byzd;
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
	public void setBelongWscName(String belongWscName) {
		this.belongWscName = belongWscName;
	}

	/**
	 * 获取所属厂站名称，非业务数据时非必填
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

}
