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
@Table(name = "BIZ_T_XJ_CX_TASK_ITEM_FAULTLC")
public class BizTXjCxTaskItemFaultlc extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	 @Id
	 @GenericGenerator(name = "generator", strategy = "uuid.hex")
	 @GeneratedValue(generator = "generator")
	 @Column(name = "ID")
	private String id;
	/**
	 * 巡检任务巡检点设备与设施表主键
	 */
	 @Column(name = "TTASK_ITEM_SBSS_ID")
	private String ttaskItemSbssId;
	/**
	 * 操作时间
	 */
	 @Column(name = "OPT_TIME")
	private String optTime;
	/**
	 * 操作人员ID
	 */
	 @Column(name = "OPT_ID")
	private String optId;
	/**
	 * 操作人员名称
	 */
	 @Column(name = "OPT_NAME")
	private String optName;
	/**
	 * 操作内容
	 */
	 @Column(name = "OPT_CONTENT")
	private String optContent;
	
	
	/**
	 * 设置ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 获取ID
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置巡检任务巡检点设备与设施表主键
	 */
	public void setTtaskItemSbssId(String ttaskItemSbssId) {
		this.ttaskItemSbssId = ttaskItemSbssId;
	}
	
	/**
	 * 获取巡检任务巡检点设备与设施表主键
	 */
	public String getTtaskItemSbssId() {
		return ttaskItemSbssId;
	}
	/**
	 * 设置操作时间
	 */
	public void setOptTime(String optTime) {
		this.optTime = optTime;
	}
	
	/**
	 * 获取操作时间
	 */
	public String getOptTime() {
		return optTime;
	}
	/**
	 * 设置操作人员ID
	 */
	public void setOptId(String optId) {
		this.optId = optId;
	}
	
	/**
	 * 获取操作人员ID
	 */
	public String getOptId() {
		return optId;
	}
	/**
	 * 设置操作人员名称
	 */
	public void setOptName(String optName) {
		this.optName = optName;
	}
	
	/**
	 * 获取操作人员名称
	 */
	public String getOptName() {
		return optName;
	}
	/**
	 * 设置操作内容
	 */
	public void setOptContent(String optContent) {
		this.optContent = optContent;
	}
	
	/**
	 * 获取操作内容
	 */
	public String getOptContent() {
		return optContent;
	}
}