package com.upsoft.yxsw.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.upsoft.system.entity.BaseEntity;

@Entity
@Table(name = "BIZ_T_XJ_CX_TASK_ITEM_SBSS_RST")
public class BizTXjCxTaskItemSbssRst extends BaseEntity implements Serializable {

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
	@Column(name = "SBSS_RST_ID")
	private String sbssRstId;
	/**
	 * 任务巡检点设备设施表BIZ_T_XJ_CX_TASK_ITEM_SBSS的ID
	 */
	@Column(name = "TASK_ITEM_SBSS_ID")
	private String taskItemSbssId;
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
	 * 冗余，输入类型：文本、数字、单选 0 单选 1 文本 2 数字
	 */
	@Column(name = "INPUT_TYPE")
	private String inputType;
	/**
	 * 检查值，即巡检填报的值
	 */
	@Column(name = "CHECK_VALUE")
	private String checkValue;
	/**
	 * 备用字段
	 */
	@Column(name = "BYZD")
	private String byzd;

	// 巡检结果明细
	@Transient
	private List<BizTXjCxTaskItemRstDet> rstDetList;
	
	/**
	 * 设置ID
	 */
	public void setSbssRstId(String sbssRstId) {
		this.sbssRstId = sbssRstId;
	}

	/**
	 * 获取ID
	 */
	public String getSbssRstId() {
		return sbssRstId;
	}

	/**
	 * 设置任务巡检点设备设施表BIZ_T_XJ_CX_TASK_ITEM_SBSS的ID
	 */
	public void setTaskItemSbssId(String taskItemSbssId) {
		this.taskItemSbssId = taskItemSbssId;
	}

	/**
	 * 获取任务巡检点设备设施表BIZ_T_XJ_CX_TASK_ITEM_SBSS的ID
	 */
	public String getTaskItemSbssId() {
		return taskItemSbssId;
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
	 * 设置冗余，输入类型：文本、数字、单选 0 单选 1 文本 2 数字
	 */
	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	/**
	 * 获取冗余，输入类型：文本、数字、单选 0 单选 1 文本 2 数字
	 */
	public String getInputType() {
		return inputType;
	}

	/**
	 * 设置检查值，即巡检填报的值
	 */
	public void setCheckValue(String checkValue) {
		this.checkValue = checkValue;
	}

	/**
	 * 获取检查值，即巡检填报的值
	 */
	public String getCheckValue() {
		return checkValue;
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

	public List<BizTXjCxTaskItemRstDet> getRstDetList() {
		return rstDetList;
	}

	public void setRstDetList(List<BizTXjCxTaskItemRstDet> rstDetList) {
		this.rstDetList = rstDetList;
	}
}