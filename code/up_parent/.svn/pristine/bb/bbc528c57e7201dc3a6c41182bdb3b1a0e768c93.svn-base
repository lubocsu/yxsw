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
@Table(name = "BIZ_T_SBSS_RELATION")
public class BizTSbssRelation extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -9000184107921804898L;
	/**
	 * 主键ID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "SS_SB_ID")
	private String ssSbId;
	/**
	 * 设施ID
	 */
	@Column(name = "SS_ID")
	private String ssId;
	/**
	 * 设备ID
	 */
	@Column(name = "SB_ID")
	private String sbId;

	/**
	 * 设置主键ID
	 */
	public void setSsSbId(String ssSbId) {
		this.ssSbId = ssSbId;
	}

	/**
	 * 获取主键ID
	 */
	public String getSsSbId() {
		return ssSbId;
	}

	/**
	 * 设置设施ID
	 */
	public void setSsId(String ssId) {
		this.ssId = ssId;
	}

	/**
	 * 获取设施ID
	 */
	public String getSsId() {
		return ssId;
	}

	/**
	 * 设置设备ID
	 */
	public void setSbId(String sbId) {
		this.sbId = sbId;
	}

	/**
	 * 获取设备ID
	 */
	public String getSbId() {
		return sbId;
	}
}