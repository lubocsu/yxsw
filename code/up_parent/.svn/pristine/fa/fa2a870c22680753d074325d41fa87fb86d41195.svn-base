package com.upsoft.yxsw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "BIZ_T_XJ_FREQ_SEGMENT")
public class BizTXjFreqSegment {

	/**
	 * 主键ID
	 */
	 @Id
	 @GenericGenerator(name = "generator", strategy = "uuid.hex")
	 @GeneratedValue(generator = "generator")
	 @Column(name = "FREQ_SEGMENT_ID")
	private String freqSegmentId;
	/**
	 * 频率名称
	 */
	 @Column(name = "FREQ_SEGMENT_NAME")
	private String freqSegmentName;
	/**
	 * 班次ID，表BIZ_T_XJ_WORK_GROUP_DETIAL的ID
	 */
	 @Column(name = "DETAIL_ID")
	private String detailId;
	/**
	 * 班次名称
	 */
	 @Column(name = "DETAIL_NAME")
	private String detailName;
	/**
	 * 起始时间:格式如：08:30一个班次下面的所有分段时间不可用重叠，且班次下的所有分段连续起来应与班次时间段一致
	 */
	 @Column(name = "START_TIME")
	private String startTime;
	/**
	 * 结束时间:格式如：08:30一个班次下面的所有分段时间不可用重叠，且班次下的所有分段连续起来应与班次时间段一致
	 */
	 @Column(name = "END_TIME")
	private String endTime;
	/**
	 * 备用字段
	 */
	 @Column(name = "BYZD")
	private String byzd;
	/**
	 * 是否删除，1是 0否
	 */
	 @Column(name = "DEL_FLAG")
	private Long delFlag;
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
	public void setFreqSegmentId(String freqSegmentId) {
		this.freqSegmentId = freqSegmentId;
	}
	
	/**
	 * 获取主键ID
	 */
	public String getFreqSegmentId() {
		return freqSegmentId;
	}
	/**
	 * 设置频率名称
	 */
	public void setFreqSegmentName(String freqSegmentName) {
		this.freqSegmentName = freqSegmentName;
	}
	
	/**
	 * 获取频率名称
	 */
	public String getFreqSegmentName() {
		return freqSegmentName;
	}
	/**
	 * 设置班次ID，表BIZ_T_XJ_WORK_GROUP_DETIAL的ID
	 */
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	
	/**
	 * 获取班次ID，表BIZ_T_XJ_WORK_GROUP_DETIAL的ID
	 */
	public String getDetailId() {
		return detailId;
	}
	/**
	 * 设置班次名称
	 */
	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}
	
	/**
	 * 获取班次名称
	 */
	public String getDetailName() {
		return detailName;
	}
	/**
	 * 设置起始时间:格式如：08:30一个班次下面的所有分段时间不可用重叠，且班次下的所有分段连续起来应与班次时间段一致
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * 获取起始时间:格式如：08:30一个班次下面的所有分段时间不可用重叠，且班次下的所有分段连续起来应与班次时间段一致
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * 设置结束时间:格式如：08:30一个班次下面的所有分段时间不可用重叠，且班次下的所有分段连续起来应与班次时间段一致
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * 获取结束时间:格式如：08:30一个班次下面的所有分段时间不可用重叠，且班次下的所有分段连续起来应与班次时间段一致
	 */
	public String getEndTime() {
		return endTime;
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
	public void setDelFlag(Long delFlag) {
		this.delFlag = delFlag;
	}
	
	/**
	 * 获取是否删除，1是 0否
	 */
	public Long getDelFlag() {
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