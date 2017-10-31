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
@Table(name = "BIZ_T_GG_SBSS_ATTACH_BASE")
public class BizTGgSbssAttachBase  extends BaseEntity implements Serializable  {

	private static final long serialVersionUID = 3962688262378644357L;
	/**
	 * 主键ID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "ATTACH_BASE_ID")
	private String attachBaseId;
	/**
	 * 设备或设施编号,根据SB_OR_SS字段判断
	 */
	@Column(name = "CODE")
	private String code;
	/**
	 * 配置说明
	 */
	@Column(name = "CONF_DESC")
	private String confDesc;
	/**
	 * 备用
	 */
	@Column(name = "BYZD")
	private String byzd;
	/**
	 * 代表此项是为设备或设施做配置：1设备，2设施
	 */
	@Column(name = "SB_OR_SS")
	private String sbOrSs;
	/**
	 * 配置类型：1检查项配置，2安全定义配置
	 */
	@Column(name = "CONF_TYPE")
	private String confType;
	/**
	 * 配置项CODE或ID,根据CONF_TYPE字段确定 检查项定义表BIZ_T_GG_CHECK_ITEM的CODE
	 * 安全提醒定义表BIZ_T_GG_WARNING_MANAGE的ID
	 */
	@Column(name = "DETAIL_ID")
	private String detailId;
	/**
	 * 冗余字段，配置项名称
	 */
	@Column(name = "NAME")
	private String name;
	/**
	 * 排序号
	 */
	@Column(name = "SORT_NUM")
	private Long sortNum;
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
	 * 所属部门
	 */
	@Column(name = "BELONG_DEPT")
	private String belongDept;
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
	public void setAttachBaseId(String attachBaseId) {
		this.attachBaseId = attachBaseId;
	}

	/**
	 * 获取主键ID
	 */
	public String getAttachBaseId() {
		return attachBaseId;
	}

	/**
	 * 设置设备或设施编号,根据SB_OR_SS字段判断
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取设备或设施编号,根据SB_OR_SS字段判断
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置配置说明
	 */
	public void setConfDesc(String confDesc) {
		this.confDesc = confDesc;
	}

	/**
	 * 获取配置说明
	 */
	public String getConfDesc() {
		return confDesc;
	}

	/**
	 * 设置备用
	 */
	public void setByzd(String byzd) {
		this.byzd = byzd;
	}

	/**
	 * 获取备用
	 */
	public String getByzd() {
		return byzd;
	}

	/**
	 * 设置代表此项是为设备或设施做配置：1设备，2设施
	 */
	public void setSbOrSs(String sbOrSs) {
		this.sbOrSs = sbOrSs;
	}

	/**
	 * 获取代表此项是为设备或设施做配置：1设备，2设施
	 */
	public String getSbOrSs() {
		return sbOrSs;
	}

	/**
	 * 设置配置类型：1检查项配置，2安全定义配置
	 */
	public void setConfType(String confType) {
		this.confType = confType;
	}

	/**
	 * 获取配置类型：1检查项配置，2安全定义配置
	 */
	public String getConfType() {
		return confType;
	}

	/**
	 * 设置配置项CODE或ID,根据CONF_TYPE字段确定 检查项定义表BIZ_T_GG_CHECK_ITEM的CODE
	 * 安全提醒定义表BIZ_T_GG_WARNING_MANAGE的ID
	 */
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	/**
	 * 获取配置项CODE或ID,根据CONF_TYPE字段确定 检查项定义表BIZ_T_GG_CHECK_ITEM的CODE
	 * 安全提醒定义表BIZ_T_GG_WARNING_MANAGE的ID
	 */
	public String getDetailId() {
		return detailId;
	}

	/**
	 * 设置冗余字段，配置项名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取冗余字段，配置项名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置排序号
	 */
	public void setSortNum(Long sortNum) {
		this.sortNum = sortNum;
	}

	/**
	 * 获取排序号
	 */
	public Long getSortNum() {
		return sortNum;
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