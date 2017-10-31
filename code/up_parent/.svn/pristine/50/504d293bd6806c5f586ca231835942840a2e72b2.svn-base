package com.upsoft.yxsw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.upsoft.system.entity.BaseEntity;

@Entity
@Table(name = "BIZ_T_GG_CHECK_ITEM")
public class BizTGgCheckItem  extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "CHECK_ITEM_ID")
	private String checkItemId;
	/**
	 * 检查项CODE
	 */
	@Column(name = "CHECK_ITEM_CODE")
	private String checkItemCode;
	/**
	 * 检查项名称
	 */
	@Column(name = "CHECK_ITEM_NAME")
	private String checkItemName;
	/**
	 * 检查项类型: 1 设备检查项 2 设施检查项
	 */
	@Column(name = "CHECK_ITEM_TYPE")
	private String checkItemType;
	/**
	 * 输入类型：文本、数字、单选 0 单选 1 文本 2 数字
	 */
	@Column(name = "INPUT_TYPE")
	private String inputType;
	/**
	 * 检查项说明
	 */
	@Column(name = "CHECK_ITEM_DESC")
	private String checkItemDesc;
	/**
	 * 备用字段
	 */
	@Column(name = "BYZD")
	private String byzd;
	/**
	 * 是否删除，1是 0否
	 */
	@Column(name = "DEL_FLAG")
	private Integer delFlag;
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
	 * 创建人ID或账号
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
	public void setCheckItemId(String checkItemId) {
		this.checkItemId = checkItemId;
	}

	/**
	 * 获取主键ID
	 */
	public String getCheckItemId() {
		return checkItemId;
	}

	/**
	 * 设置检查项CODE
	 */
	public void setCheckItemCode(String checkItemCode) {
		this.checkItemCode = checkItemCode;
	}

	/**
	 * 获取检查项CODE
	 */
	public String getCheckItemCode() {
		return checkItemCode;
	}

	/**
	 * 设置检查项名称
	 */
	public void setCheckItemName(String checkItemName) {
		this.checkItemName = checkItemName;
	}

	/**
	 * 获取检查项名称
	 */
	public String getCheckItemName() {
		return checkItemName;
	}

	/**
	 * 设置检查项类型: 1 设备检查项 2 设施检查项
	 */
	public void setCheckItemType(String checkItemType) {
		this.checkItemType = checkItemType;
	}

	/**
	 * 获取检查项类型: 1 设备检查项 2 设施检查项
	 */
	public String getCheckItemType() {
		return checkItemType;
	}

	/**
	 * 设置输入类型：文本、数字、单选 0 单选 1 文本 2 数字
	 */
	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	/**
	 * 获取输入类型：文本、数字、单选 0 单选 1 文本 2 数字
	 */
	public String getInputType() {
		return inputType;
	}

	/**
	 * 设置检查项说明
	 */
	public void setCheckItemDesc(String checkItemDesc) {
		this.checkItemDesc = checkItemDesc;
	}

	/**
	 * 获取检查项说明
	 */
	public String getCheckItemDesc() {
		return checkItemDesc;
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
	 * 设置创建人ID或账号
	 */
	public void setUpdatorAccount(String updatorAccount) {
		this.updatorAccount = updatorAccount;
	}

	/**
	 * 获取创建人ID或账号
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