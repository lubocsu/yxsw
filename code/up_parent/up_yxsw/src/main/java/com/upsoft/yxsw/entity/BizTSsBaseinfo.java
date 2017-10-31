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
@Table(name = "BIZ_T_SS_BASEINFO")
public class BizTSsBaseinfo extends BaseEntity implements Serializable{

	private static final long serialVersionUID = -6910056497732761558L;
	/**
	 * 主键ID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "SS_ID")
	private String ssId;
	/**
	 * 设施编号
	 */
	@Column(name = "CODE")
	private String code;
	/**
	 * 设施名称
	 */
	@Column(name = "NAME")
	private String name;
	/**
	 * 设施功能说明
	 */
	@Column(name = "FUNCTION")
	private String function;
	/**
	 * 上级设施ID
	 */
	@Column(name = "PARENT_ID")
	private String parentId;
	/**
	 * 层级关系
	 */
	@Column(name = "LAYER")
	private String layer;
	/**
	 * 排序号
	 */
	@Column(name = "SORT")
	private Integer sort;
	/**
	 * 是否必须扫描二维码 1是 0否
	 */
	@Column(name = "BYZD")
	private String byzd;
	/**
	 * 备注、预留字段
	 */
	@Column(name = "REMARK")
	private String remark;
	/**
	 * X_坐标
	 */
	@Column(name = "LONGITUDE")
	private Double longitude;
	/**
	 * Y_坐标
	 */
	@Column(name = "LATITUDE")
	private Double latitude;
	/**
	 * 是否删除，1是 0否,下属有设施或设备时不可被删除
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
	public void setSsId(String ssId) {
		this.ssId = ssId;
	}

	/**
	 * 获取主键ID
	 */
	public String getSsId() {
		return ssId;
	}

	/**
	 * 设置设施编号
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取设施编号
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置设施名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取设施名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置设施功能说明
	 */
	public void setFunction(String function) {
		this.function = function;
	}

	/**
	 * 获取设施功能说明
	 */
	public String getFunction() {
		return function;
	}

	/**
	 * 设置上级设施ID
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * 获取上级设施ID
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * 设置层级关系
	 */
	public void setLayer(String layer) {
		this.layer = layer;
	}

	/**
	 * 获取层级关系
	 */
	public String getLayer() {
		return layer;
	}

	/**
	 * 设置排序号
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 获取排序号
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * 设置是否必须扫描二维码 1是 0否
	 */
	public void setByzd(String byzd) {
		this.byzd = byzd;
	}

	/**
	 * 获取是否必须扫描二维码 1是 0否
	 */
	public String getByzd() {
		return byzd;
	}

	/**
	 * 设置备注、预留字段
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取备注、预留字段
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置X_坐标
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * 获取X_坐标
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * 设置Y_坐标
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * 获取Y_坐标
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * 设置是否删除，1是 0否,下属有设施或设备时不可被删除
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * 获取是否删除，1是 0否,下属有设施或设备时不可被删除
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