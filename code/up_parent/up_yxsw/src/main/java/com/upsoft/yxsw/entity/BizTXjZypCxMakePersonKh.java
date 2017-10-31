package com.upsoft.yxsw.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.upsoft.system.entity.BaseEntity;

@Entity
@Table(name = "BIZ_T_XJ_ZYP_CX_MAKE_PERSON_KH")
public class BizTXjZypCxMakePersonKh extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -2697750837432187959L;
	/**
	 * 人员考核主键
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "PERSON_KH_ID")
	private String personKhId;
	/**
	 * 拟定表BIZ_T_XJ_ZYP_CX_MAKE主键ID
	 */
	@Column(name = "CX_MAKE_ID")
	private String cxMakeId;
	/**
	 * 被考核人员ID
	 */
	@Column(name = "BKH_ID")
	private String bkhId;
	/**
	 * 被考核人姓名
	 */
	@Column(name = "BKH_NAME")
	private String bkhName;
	/**
	 * 考核情况说明
	 */
	@Column(name = "KH_DESC")
	private String khDesc;
	/**
	 * 所扣分分值
	 */
	@Column(name = "KH_SCORE")
	private Double khScore;
	/**
	 * 是否已经确认 1是 0否
	 */
	@Column(name = "HAVE_CONFIRM")
	private Long haveConfirm;
	/**
	 * 确认时间
	 */
	@Column(name = "CONFIRM_TIME")
	private Date confirmTime;
	/**
	 * 备用字段
	 */
	@Column(name = "BYZD")
	private String byzd;

	/**
	 * 设置人员考核主键
	 */
	public void setPersonKhId(String personKhId) {
		this.personKhId = personKhId;
	}

	/**
	 * 获取人员考核主键
	 */
	public String getPersonKhId() {
		return personKhId;
	}

	/**
	 * 设置拟定表BIZ_T_XJ_ZYP_CX_MAKE主键ID
	 */
	public void setCxMakeId(String cxMakeId) {
		this.cxMakeId = cxMakeId;
	}

	/**
	 * 获取拟定表BIZ_T_XJ_ZYP_CX_MAKE主键ID
	 */
	public String getCxMakeId() {
		return cxMakeId;
	}

	/**
	 * 设置被考核人员ID
	 */
	public void setBkhId(String bkhId) {
		this.bkhId = bkhId;
	}

	/**
	 * 获取被考核人员ID
	 */
	public String getBkhId() {
		return bkhId;
	}

	/**
	 * 设置被考核人姓名
	 */
	public void setBkhName(String bkhName) {
		this.bkhName = bkhName;
	}

	/**
	 * 获取被考核人姓名
	 */
	public String getBkhName() {
		return bkhName;
	}

	/**
	 * 设置考核情况说明
	 */
	public void setKhDesc(String khDesc) {
		this.khDesc = khDesc;
	}

	/**
	 * 获取考核情况说明
	 */
	public String getKhDesc() {
		return khDesc;
	}

	/**
	 * 设置所扣分分值
	 */
	public void setKhScore(Double khScore) {
		this.khScore = khScore;
	}

	/**
	 * 获取所扣分分值
	 */
	public Double getKhScore() {
		return khScore;
	}

	/**
	 * 设置是否已经确认 1是 0否
	 */
	public void setHaveConfirm(Long haveConfirm) {
		this.haveConfirm = haveConfirm;
	}

	/**
	 * 获取是否已经确认 1是 0否
	 */
	public Long getHaveConfirm() {
		return haveConfirm;
	}

	/**
	 * 设置确认时间
	 */
	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	/**
	 * 获取确认时间
	 */
	public Date getConfirmTime() {
		return confirmTime;
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