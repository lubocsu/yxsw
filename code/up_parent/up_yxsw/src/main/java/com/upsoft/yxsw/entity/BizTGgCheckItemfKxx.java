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
@Table(name = "BIZ_T_GG_CHECK_ITEMF_KXX")
public class BizTGgCheckItemfKxx  extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 8956385993463581201L;
	/**
	 * ID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "ITEMF_KXX_ID")
	private String itemfKxxId;
	/**
	 * 名称
	 */
	@Column(name = "KXX_VALUE")
	private String kxxValue;
	/**
	 * 是否有效
	 */
	@Column(name = "IS_VALID")
	private Long isValid;
	/**
	 * 说明
	 */
	@Column(name = "KXX_DESC")
	private String kxxDesc;

	/**
	 * 设置ID
	 */
	public void setItemfKxxId(String itemfKxxId) {
		this.itemfKxxId = itemfKxxId;
	}

	/**
	 * 获取ID
	 */
	public String getItemfKxxId() {
		return itemfKxxId;
	}

	/**
	 * 设置名称
	 */
	public void setKxxValue(String kxxValue) {
		this.kxxValue = kxxValue;
	}

	/**
	 * 获取名称
	 */
	public String getKxxValue() {
		return kxxValue;
	}

	/**
	 * 设置是否有效
	 */
	public void setIsValid(Long isValid) {
		this.isValid = isValid;
	}

	/**
	 * 获取是否有效
	 */
	public Long getIsValid() {
		return isValid;
	}

	/**
	 * 设置说明
	 */
	public void setKxxDesc(String kxxDesc) {
		this.kxxDesc = kxxDesc;
	}

	/**
	 * 获取说明
	 */
	public String getKxxDesc() {
		return kxxDesc;
	}
}