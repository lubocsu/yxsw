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
@Table(name = "BIZ_T_XJ_ZYP_CX_MAKE")
public class BizTXjZypCxMake extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -1866600985293269772L;
	/**
	 * 拟定表主键ID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "CX_MAKE_ID")
	private String cxMakeId;
	/**
	 * 作业票日期
	 */
	@Column(name = "ZYP_DATE")
	private String zypDate;
	/**
	 * 作业票编号
	 */
	@Column(name = "ZYP_CODE")
	private String zypCode;
	/**
	 * 状态：red 红色 orange 橙色 green 绿色
	 */
	@Column(name = "WARNING")
	private String warning;
	/**
	 * 天气： suny 晴 overcast 阴 smallrain 小雨 normalrain 中雨 bigrain 大雨
	 */
	@Column(name = "WEATHER")
	private String weather;
	/**
	 * 备用字段
	 */
	@Column(name = "BYZD")
	private String byzd;
	/**
	 * 模板版本号,不需要人工维护，从模板主表获取
	 */
	@Column(name = "TEMP_VERSION")
	private Long tempVersion;
	/**
	 * 作业票状态: 01 拟定中 02 审批中 03 已审批 04 在填报 05 待验收 06 已验收
	 */
	@Column(name = "CXZYP_STATUS")
	private String cxzypStatus;
	/**
	 * 班组负责人ID
	 */
	@Column(name = "FZR_ID")
	private String fzrId;
	/**
	 * 班组负责人名称
	 */
	@Column(name = "FZR_NAME")
	private String fzrName;
	/**
	 * 工作票说明
	 */
	@Column(name = "ZYP_DESC")
	private String zypDesc;
	/**
	 * 当前操作人，填报人员ID
	 */
	@Column(name = "TBRY_ID")
	private String tbryId;
	/**
	 * 填报人员名称
	 */
	@Column(name = "TBRY_NAME")
	private String tbryName;
	/**
	 * 创建时间
	 */
	@Column(name = "CREATOR_TIMESTEMP")
	private String creatorTimestemp;
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
	 * 设置拟定表主键ID
	 */
	public void setCxMakeId(String cxMakeId) {
		this.cxMakeId = cxMakeId;
	}

	/**
	 * 获取拟定表主键ID
	 */
	public String getCxMakeId() {
		return cxMakeId;
	}

	/**
	 * 设置作业票日期
	 */
	public void setZypDate(String zypDate) {
		this.zypDate = zypDate;
	}

	/**
	 * 获取作业票日期
	 */
	public String getZypDate() {
		return zypDate;
	}

	/**
	 * 设置作业票编号
	 */
	public void setZypCode(String zypCode) {
		this.zypCode = zypCode;
	}

	/**
	 * 获取作业票编号
	 */
	public String getZypCode() {
		return zypCode;
	}

	/**
	 * 设置状态：red 红色 orange 橙色 green 绿色
	 */
	public void setWarning(String warning) {
		this.warning = warning;
	}

	/**
	 * 获取状态：red 红色 orange 橙色 green 绿色
	 */
	public String getWarning() {
		return warning;
	}

	/**
	 * 设置天气： suny 晴 overcast 阴 smallrain 小雨 normalrain 中雨 bigrain 大雨
	 */
	public void setWeather(String weather) {
		this.weather = weather;
	}

	/**
	 * 获取天气： suny 晴 overcast 阴 smallrain 小雨 normalrain 中雨 bigrain 大雨
	 */
	public String getWeather() {
		return weather;
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
	 * 设置模板版本号,不需要人工维护，从模板主表获取
	 */
	public void setTempVersion(Long tempVersion) {
		this.tempVersion = tempVersion;
	}

	/**
	 * 获取模板版本号,不需要人工维护，从模板主表获取
	 */
	public Long getTempVersion() {
		return tempVersion;
	}

	/**
	 * 设置作业票状态: 01 拟定中 02 审批中 03 已审批 04 在填报 05 待验收 06 已验收
	 */
	public void setCxzypStatus(String cxzypStatus) {
		this.cxzypStatus = cxzypStatus;
	}

	/**
	 * 获取作业票状态: 01 拟定中 02 审批中 03 已审批 04 在填报 05 待验收 06 已验收
	 */
	public String getCxzypStatus() {
		return cxzypStatus;
	}

	/**
	 * 设置班组负责人ID
	 */
	public void setFzrId(String fzrId) {
		this.fzrId = fzrId;
	}

	/**
	 * 获取班组负责人ID
	 */
	public String getFzrId() {
		return fzrId;
	}

	/**
	 * 设置班组负责人名称
	 */
	public void setFzrName(String fzrName) {
		this.fzrName = fzrName;
	}

	/**
	 * 获取班组负责人名称
	 */
	public String getFzrName() {
		return fzrName;
	}

	/**
	 * 设置工作票说明
	 */
	public void setZypDesc(String zypDesc) {
		this.zypDesc = zypDesc;
	}

	/**
	 * 获取工作票说明
	 */
	public String getZypDesc() {
		return zypDesc;
	}

	/**
	 * 设置当前操作人，填报人员ID
	 */
	public void setTbryId(String tbryId) {
		this.tbryId = tbryId;
	}

	/**
	 * 获取当前操作人，填报人员ID
	 */
	public String getTbryId() {
		return tbryId;
	}

	/**
	 * 设置填报人员名称
	 */
	public void setTbryName(String tbryName) {
		this.tbryName = tbryName;
	}

	/**
	 * 获取填报人员名称
	 */
	public String getTbryName() {
		return tbryName;
	}

	/**
	 * 设置创建时间
	 */
	public void setCreatorTimestemp(String creatorTimestemp) {
		this.creatorTimestemp = creatorTimestemp;
	}

	/**
	 * 获取创建时间
	 */
	public String getCreatorTimestemp() {
		return creatorTimestemp;
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