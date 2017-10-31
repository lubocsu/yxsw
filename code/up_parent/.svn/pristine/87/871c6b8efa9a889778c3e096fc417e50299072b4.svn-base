package com.upsoft.yxsw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "BIZ_T_XJ_CX_TASK_PERSONS")
public class BizTXjCxTaskPersons {

	/**
	 * ID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID")
	private String id;
	/**
	 * 任务ID
	 */
	@Column(name = "CX_TASK_ID")
	private String cxTaskId;
	/**
	 * 参与人ID
	 */
	@Column(name = "PERSON_ID")
	private String personId;
	/**
	 * 参与人名称
	 */
	@Column(name = "PERSON_NAME")
	private String personName;
	/**
	 * 是否有效 1 有效，0无效
	 */
	@Column(name = "IS_VALID")
	private Long isValid;
	/**
	 * 是否负责人 1是 0否 任务有多个参与人员时，第一个启动任务的为任务的负责人
	 */
	@Column(name = "IN_CHARGE")
	private Long inCharge;
	/**
	 * 备用，转入人员ID
	 */
	@Column(name = "ZR_ID")
	private String zrId;
	/**
	 * 备用，转入人员名称
	 */
	@Column(name = "ZR_NAME")
	private String zrName;
	/**
	 * 备用字段
	 */
	@Column(name = "BYZD")
	private String byzd;

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
	 * 设置任务ID
	 */
	public void setCxTaskId(String cxTaskId) {
		this.cxTaskId = cxTaskId;
	}

	/**
	 * 获取任务ID
	 */
	public String getCxTaskId() {
		return cxTaskId;
	}

	/**
	 * 设置参与人ID
	 */
	public void setPersonId(String personId) {
		this.personId = personId;
	}

	/**
	 * 获取参与人ID
	 */
	public String getPersonId() {
		return personId;
	}

	/**
	 * 设置参与人名称
	 */
	public void setPersonName(String personName) {
		this.personName = personName;
	}

	/**
	 * 获取参与人名称
	 */
	public String getPersonName() {
		return personName;
	}

	/**
	 * 设置是否有效 1 有效，0无效
	 */
	public void setIsValid(Long isValid) {
		this.isValid = isValid;
	}

	/**
	 * 获取是否有效 1 有效，0无效
	 */
	public Long getIsValid() {
		return isValid;
	}

	/**
	 * 设置是否负责人 1是 0否 任务有多个参与人员时，第一个启动任务的为任务的负责人
	 */
	public void setInCharge(Long inCharge) {
		this.inCharge = inCharge;
	}

	/**
	 * 获取是否负责人 1是 0否 任务有多个参与人员时，第一个启动任务的为任务的负责人
	 */
	public Long getInCharge() {
		return inCharge;
	}

	/**
	 * 设置备用，转入人员ID
	 */
	public void setZrId(String zrId) {
		this.zrId = zrId;
	}

	/**
	 * 获取备用，转入人员ID
	 */
	public String getZrId() {
		return zrId;
	}

	/**
	 * 设置备用，转入人员名称
	 */
	public void setZrName(String zrName) {
		this.zrName = zrName;
	}

	/**
	 * 获取备用，转入人员名称
	 */
	public String getZrName() {
		return zrName;
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
}