package com.upsoft.yxsw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.upsoft.system.entity.BaseEntity;

@Entity
@Table(name = "BIZ_T_XJ_CX_TASK")
public class BizTXjCxTask extends BaseEntity{

	/**
	 * 主键ID
	 */
	 @Id
	 @GenericGenerator(name = "generator", strategy = "uuid.hex")
	 @GeneratedValue(generator = "generator")
	 @Column(name = "CX_TASK_ID")
	private String cxTaskId;
	/**
	 * 任务所属日期,该日期是标明属于哪一天的厂巡范围 比如9号的作业票到10号8点都会有厂巡，任务都算是9号的 yyyyMMdd格式
	 */
	 @Column(name = "CX_TASK_DATE")
	private String cxTaskDate;
	/**
	 * 任务编号
	 */
	 @Column(name = "CX_TASK_CODE")
	private String cxTaskCode;
	/**
	 * 任务名称
	 */
	 @Column(name = "CX_TASK_NAME")
	private String cxTaskName;
	/**
	 * 任务概述
	 */
	 @Column(name = "CX_TASK_DESC")
	private String cxTaskDesc;
	/**
	 * 计划开始时间
	 */
	 @Column(name = "CX_TASK_PSTART_TIME")
	private String cxTaskPstartTime;
	/**
	 * 计划结束时间
	 */
	 @Column(name = "CX_TASK_PEND_TIME")
	private String cxTaskPendTime;
	/**
	 * 实际开始时间
	 */
	 @Column(name = "CX_TASK_ASTART_TIME")
	private String cxTaskAstartTime;
	/**
	 * 实际完成时间
	 */
	 @Column(name = "CX_TASK_AEND_TIME")
	private String cxTaskAendTime;
	/**
	 * 任务方式:1系统下发2主动处理
	 */
	 @Column(name = "CX_TASK_TYPE")
	private String cxTaskType;
	/**
	 * 任务状态：
0 待下发
1 已下发
2 在执行
3 已完成
	 */
	 @Column(name = "CX_TASK_STATUS")
	private String cxTaskStatus;
	/**
	 * 是否超期,任务超时完成时修改该字段 1是0否
	 */
	 @Column(name = "IS_OVER_TIME")
	private Long isOverTime;
	/**
	 * 是否可以被转交 1 是 0否
当任务的责任人多于1个时，任务将不可被转交
	 */
	 @Column(name = "CX_TASK_CAN_DELIV")
	private Long cxTaskCanDeliv;
	/**
	 * 是否被转交 1 是 0否
	 */
	 @Column(name = "CX_TASK_HAVE_DELIV")
	private Long cxTaskHaveDeliv;
	/**
	 * 任务生成时间
	 */
	 @Column(name = "CX_TASK_GEN_TIME")
	private String cxTaskGenTime;
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
	 * 备用字段
	 */
	 @Column(name = "BYZD")
	private String byzd;
	/**
	 * 是否正常超期 1 是 0否
	 */
	 @Column(name = "SF_ZC_OVER_TIME")
	private String sfZcOverTime;
	/**
	 * 超期说明
	 */
	 @Column(name = "OVER_TIME_DESC")
	private String overTimeDesc;
	
	
	/**
	 * 设置主键ID
	 */
	public void setCxTaskId(String cxTaskId) {
		this.cxTaskId = cxTaskId;
	}
	
	/**
	 * 获取主键ID
	 */
	public String getCxTaskId() {
		return cxTaskId;
	}
	/**
	 * 设置任务所属日期,该日期是标明属于哪一天的厂巡范围 比如9号的作业票到10号8点都会有厂巡，任务都算是9号的 yyyyMMdd格式
	 */
	public void setCxTaskDate(String cxTaskDate) {
		this.cxTaskDate = cxTaskDate;
	}
	
	/**
	 * 获取任务所属日期,该日期是标明属于哪一天的厂巡范围 比如9号的作业票到10号8点都会有厂巡，任务都算是9号的 yyyyMMdd格式
	 */
	public String getCxTaskDate() {
		return cxTaskDate;
	}
	/**
	 * 设置任务编号
	 */
	public void setCxTaskCode(String cxTaskCode) {
		this.cxTaskCode = cxTaskCode;
	}
	
	/**
	 * 获取任务编号
	 */
	public String getCxTaskCode() {
		return cxTaskCode;
	}
	/**
	 * 设置任务名称
	 */
	public void setCxTaskName(String cxTaskName) {
		this.cxTaskName = cxTaskName;
	}
	
	/**
	 * 获取任务名称
	 */
	public String getCxTaskName() {
		return cxTaskName;
	}
	/**
	 * 设置任务概述
	 */
	public void setCxTaskDesc(String cxTaskDesc) {
		this.cxTaskDesc = cxTaskDesc;
	}
	
	/**
	 * 获取任务概述
	 */
	public String getCxTaskDesc() {
		return cxTaskDesc;
	}
	/**
	 * 设置计划开始时间
	 */
	public void setCxTaskPstartTime(String cxTaskPstartTime) {
		this.cxTaskPstartTime = cxTaskPstartTime;
	}
	
	/**
	 * 获取计划开始时间
	 */
	public String getCxTaskPstartTime() {
		return cxTaskPstartTime;
	}
	/**
	 * 设置计划结束时间
	 */
	public void setCxTaskPendTime(String cxTaskPendTime) {
		this.cxTaskPendTime = cxTaskPendTime;
	}
	
	/**
	 * 获取计划结束时间
	 */
	public String getCxTaskPendTime() {
		return cxTaskPendTime;
	}
	/**
	 * 设置实际开始时间
	 */
	public void setCxTaskAstartTime(String cxTaskAstartTime) {
		this.cxTaskAstartTime = cxTaskAstartTime;
	}
	
	/**
	 * 获取实际开始时间
	 */
	public String getCxTaskAstartTime() {
		return cxTaskAstartTime;
	}
	/**
	 * 设置实际完成时间
	 */
	public void setCxTaskAendTime(String cxTaskAendTime) {
		this.cxTaskAendTime = cxTaskAendTime;
	}
	
	/**
	 * 获取实际完成时间
	 */
	public String getCxTaskAendTime() {
		return cxTaskAendTime;
	}
	/**
	 * 设置任务方式:1系统下发2主动处理
	 */
	public void setCxTaskType(String cxTaskType) {
		this.cxTaskType = cxTaskType;
	}
	
	/**
	 * 获取任务方式:1系统下发2主动处理
	 */
	public String getCxTaskType() {
		return cxTaskType;
	}
	/**
	 * 设置任务状态：
0 待下发
1 已下发
2 在执行
3 已完成
	 */
	public void setCxTaskStatus(String cxTaskStatus) {
		this.cxTaskStatus = cxTaskStatus;
	}
	
	/**
	 * 获取任务状态：
0 待下发
1 已下发
2 在执行
3 已完成
	 */
	public String getCxTaskStatus() {
		return cxTaskStatus;
	}
	/**
	 * 设置是否超期,任务超时完成时修改该字段 1是0否
	 */
	public void setIsOverTime(Long isOverTime) {
		this.isOverTime = isOverTime;
	}
	
	/**
	 * 获取是否超期,任务超时完成时修改该字段 1是0否
	 */
	public Long getIsOverTime() {
		return isOverTime;
	}
	/**
	 * 设置是否可以被转交 1 是 0否
当任务的责任人多于1个时，任务将不可被转交
	 */
	public void setCxTaskCanDeliv(Long cxTaskCanDeliv) {
		this.cxTaskCanDeliv = cxTaskCanDeliv;
	}
	
	/**
	 * 获取是否可以被转交 1 是 0否
当任务的责任人多于1个时，任务将不可被转交
	 */
	public Long getCxTaskCanDeliv() {
		return cxTaskCanDeliv;
	}
	/**
	 * 设置是否被转交 1 是 0否
	 */
	public void setCxTaskHaveDeliv(Long cxTaskHaveDeliv) {
		this.cxTaskHaveDeliv = cxTaskHaveDeliv;
	}
	
	/**
	 * 获取是否被转交 1 是 0否
	 */
	public Long getCxTaskHaveDeliv() {
		return cxTaskHaveDeliv;
	}
	/**
	 * 设置任务生成时间
	 */
	public void setCxTaskGenTime(String cxTaskGenTime) {
		this.cxTaskGenTime = cxTaskGenTime;
	}
	
	/**
	 * 获取任务生成时间
	 */
	public String getCxTaskGenTime() {
		return cxTaskGenTime;
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
	 * 设置是否正常超期 1 是 0否
	 */
	public void setSfZcOverTime(String sfZcOverTime) {
		this.sfZcOverTime = sfZcOverTime;
	}
	
	/**
	 * 获取是否正常超期 1 是 0否
	 */
	public String getSfZcOverTime() {
		return sfZcOverTime;
	}
	/**
	 * 设置超期说明
	 */
	public void setOverTimeDesc(String overTimeDesc) {
		this.overTimeDesc = overTimeDesc;
	}
	
	/**
	 * 获取超期说明
	 */
	public String getOverTimeDesc() {
		return overTimeDesc;
	}
}