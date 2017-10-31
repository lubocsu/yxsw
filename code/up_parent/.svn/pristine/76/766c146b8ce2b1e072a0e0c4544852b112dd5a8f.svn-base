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
@Table(name = "BIZ_T_XJD_ITEM_DETAIL")
public class BizTXjdItemDetail extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	 @Id
	 @GenericGenerator(name = "generator", strategy = "uuid.hex")
	 @GeneratedValue(generator = "generator")
	 @Column(name = "DETAIL_ID")
	private String detailId;
	/**
	 * 巡检点主键
	 */
	 @Column(name = "XJD_ITEM_ID")
	private String xjdItemId;
	/**
	 * 设备或设施ID
	 */
	 @Column(name = "SBSS_ID")
	private String sbssId;
	/**
	 * 设备或设施名称
	 */
	 @Column(name = "NAME")
	private String name;
	/**
	 * 类型 1设备，2设施
	 */
	 @Column(name = "DETAIL_TYPE")
	private String detailType;
	/**
	 * 备用字段
	 */
	 @Column(name = "BYZD")
	private String byzd;
	
	
	/**
	 * 设置主键
	 */
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	
	/**
	 * 获取主键
	 */
	public String getDetailId() {
		return detailId;
	}
	/**
	 * 设置巡检点主键
	 */
	public void setXjdItemId(String xjdItemId) {
		this.xjdItemId = xjdItemId;
	}
	
	/**
	 * 获取巡检点主键
	 */
	public String getXjdItemId() {
		return xjdItemId;
	}
	/**
	 * 设置设备或设施ID
	 */
	public void setSbssId(String sbssId) {
		this.sbssId = sbssId;
	}
	
	/**
	 * 获取设备或设施ID
	 */
	public String getSbssId() {
		return sbssId;
	}
	/**
	 * 设置设备或设施名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取设备或设施名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置类型 1设备，2设施
	 */
	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}
	
	/**
	 * 获取类型 1设备，2设施
	 */
	public String getDetailType() {
		return detailType;
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