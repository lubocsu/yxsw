package com.upsoft.system.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SYS_VERSION_MANAGE")
public class SysVersionManage extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1551726580902932981L;
	/**
	 * 主键ID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID")
	private String id;
	/**
	 * 版本类型： 1 APP更新 2 PC主程序更新
	 */
	@Column(name = "VERSION_TYPE")
	private String versionType;
	/**
	 * 更新时间
	 */
	@Column(name = "CHANGE_TIME")
	private String changeTime;
	/**
	 * 更新说明
	 */
	@Column(name = "CHANGE_DESC")
	private String changeDesc;
	/**
	 * 创建时间
	 */
	@Column(name = "CREATE_TIME")
	private Date createTime;
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
	 * 当前版本名称：如：10.1.8_25_10
	 */
	@Column(name = "CURRENT_VERSION_NAME")
	private String currentVersionName;
	/**
	 * 当前版本号:版本号为正整数
	 */
	@Column(name = "CURRENT_VERSION")
	private Integer currentVersion;

	/**
	 * 设置主键ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取主键ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置版本类型： 1 APP更新 2 PC主程序更新
	 */
	public void setVersionType(String versionType) {
		this.versionType = versionType;
	}

	/**
	 * 获取版本类型： 1 APP更新 2 PC主程序更新
	 */
	public String getVersionType() {
		return versionType;
	}

	/**
	 * 设置更新时间
	 */
	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}

	/**
	 * 获取更新时间
	 */
	public String getChangeTime() {
		return changeTime;
	}

	/**
	 * 设置更新说明
	 */
	public void setChangeDesc(String changeDesc) {
		this.changeDesc = changeDesc;
	}

	/**
	 * 获取更新说明
	 */
	public String getChangeDesc() {
		return changeDesc;
	}

	/**
	 * 设置创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取创建时间
	 */
	public Date getCreateTime() {
		return createTime;
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
	 * 设置当前版本名称：如：10.1.8_25_10
	 */
	public void setCurrentVersionName(String currentVersionName) {
		this.currentVersionName = currentVersionName;
	}

	/**
	 * 获取当前版本名称：如：10.1.8_25_10
	 */
	public String getCurrentVersionName() {
		return currentVersionName;
	}

	/**
	 * 设置当前版本号:版本号为正整数
	 */
	public void setCurrentVersion(Integer currentVersion) {
		this.currentVersion = currentVersion;
	}

	/**
	 * 获取当前版本号:版本号为正整数
	 */
	public Integer getCurrentVersion() {
		return currentVersion;
	}

}