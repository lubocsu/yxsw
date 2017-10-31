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
@Table(name = "BIZ_T_XJ_ZB_PLAN")
public class BizTXjZbPlan extends BaseEntity implements Serializable{

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
	 @Column(name = "ZB_PLAN_ID")
	private String zbPlanId;
	/**
	 * 值班日期 yyyyMMdd
	 */
	 @Column(name = "ZB_DATE")
	private String zbDate;
	/**
	 * 值班负责人ID
	 */
	 @Column(name = "ZB_FZR_ID")
	private String zbFzrId;
	/**
	 * 值班负责人名称
	 */
	 @Column(name = "ZB_FZR_MC")
	private String zbFzrMc;
	/**
	 * 开始时间，格式如：yyyyMMddHHmm,不需要秒，需要根据该厂站的班次周期计算出在本天值班的开始时间
开始日期是值班日期 yyyyMMdd+本次值班开始时间
	 */
	 @Column(name = "START_TIME")
	private String startTime;
	/**
	 * 截止时间，格式如：yyyyMMddHHmm,不需要秒，需要根据该厂站的班次周期计算出在本天值班的截止时间
开始日期是值班日期 yyyyMMdd+本次值班结束时间
	 */
	 @Column(name = "END_TIME")
	private String endTime;
	/**
	 * 备用
	 */
	 @Column(name = "BYZD")
	private String byzd;
	/**
	 * 是否已经生效，1是 0否 当天的值班任务被生成后即变为生效状态
生效后不能删除
	 */
	 @Column(name = "VALID_FLAG")
	private Long validFlag;
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
	public void setZbPlanId(String zbPlanId) {
		this.zbPlanId = zbPlanId;
	}
	
	/**
	 * 获取主键ID
	 */
	public String getZbPlanId() {
		return zbPlanId;
	}
	/**
	 * 设置值班日期 yyyyMMdd
	 */
	public void setZbDate(String zbDate) {
		this.zbDate = zbDate;
	}
	
	/**
	 * 获取值班日期 yyyyMMdd
	 */
	public String getZbDate() {
		return zbDate;
	}
	/**
	 * 设置值班负责人ID
	 */
	public void setZbFzrId(String zbFzrId) {
		this.zbFzrId = zbFzrId;
	}
	
	/**
	 * 获取值班负责人ID
	 */
	public String getZbFzrId() {
		return zbFzrId;
	}
	/**
	 * 设置值班负责人名称
	 */
	public void setZbFzrMc(String zbFzrMc) {
		this.zbFzrMc = zbFzrMc;
	}
	
	/**
	 * 获取值班负责人名称
	 */
	public String getZbFzrMc() {
		return zbFzrMc;
	}
	/**
	 * 设置开始时间，格式如：yyyyMMddHHmm,不需要秒，需要根据该厂站的班次周期计算出在本天值班的开始时间
开始日期是值班日期 yyyyMMdd+本次值班开始时间
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * 获取开始时间，格式如：yyyyMMddHHmm,不需要秒，需要根据该厂站的班次周期计算出在本天值班的开始时间
开始日期是值班日期 yyyyMMdd+本次值班开始时间
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * 设置截止时间，格式如：yyyyMMddHHmm,不需要秒，需要根据该厂站的班次周期计算出在本天值班的截止时间
开始日期是值班日期 yyyyMMdd+本次值班结束时间
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * 获取截止时间，格式如：yyyyMMddHHmm,不需要秒，需要根据该厂站的班次周期计算出在本天值班的截止时间
开始日期是值班日期 yyyyMMdd+本次值班结束时间
	 */
	public String getEndTime() {
		return endTime;
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
	 * 设置是否已经生效，1是 0否 当天的值班任务被生成后即变为生效状态
生效后不能删除
	 */
	public void setValidFlag(Long validFlag) {
		this.validFlag = validFlag;
	}
	
	/**
	 * 获取是否已经生效，1是 0否 当天的值班任务被生成后即变为生效状态
生效后不能删除
	 */
	public Long getValidFlag() {
		return validFlag;
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