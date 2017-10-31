package com.upsoft.yxsw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.upsoft.system.entity.BaseEntity;

@Entity
@Table(name = "BIZ_T_XJ_FREQ_SEGMENT_SBSS")
public class BizTXjFreqSegmentSbss extends BaseEntity {

	/**
	 * 主键ID
	 */
	 @Id
	 @GenericGenerator(name = "generator", strategy = "uuid.hex")
	 @GeneratedValue(generator = "generator")
	 @Column(name = "FREQ_SEGMENT_SBSS_ID")
	private String freqSegmentSbssId;
	/**
	 * 分段ID
	 */
	 @Column(name = "FREQ_SEGMENT_ID")
	private String freqSegmentId;
	/**
	 * 设施与设备类型：1.设备  2设施
	 */
	 @Column(name = "SBSS_TYPE")
	private String sbssType;
	/**
	 * 设别或设施的ID
	 */
	 @Column(name = "SBSS_ID")
	private String sbssId;
	/**
	 * 设备或设施的名称
	 */
	 @Column(name = "SBSS_NAME")
	private String sbssName;
	/**
	 * 冗余字段：巡检点ID
	 */
	 @Column(name = "XJD_ITEM_ID")
	private String xjdItemId;
	/**
	 * 冗余字段：巡检点名称
	 */
	 @Column(name = "XJD_ITEM_NAME")
	private String xjdItemName;
	/**
	 * 工艺段ID
	 */
	 @Column(name = "TECHNICS_ID")
	private String technicsId;
	/**
	 * 工艺名称
	 */
	 @Column(name = "TECHNICS_NAME")
	private String technicsName;
	/**
	 * 备用字段
	 */
	 @Column(name = "BYZD")
	private String byzd;
	
	
	/**
	 * 设置主键ID
	 */
	public void setFreqSegmentSbssId(String freqSegmentSbssId) {
		this.freqSegmentSbssId = freqSegmentSbssId;
	}
	
	/**
	 * 获取主键ID
	 */
	public String getFreqSegmentSbssId() {
		return freqSegmentSbssId;
	}
	/**
	 * 设置分段ID
	 */
	public void setFreqSegmentId(String freqSegmentId) {
		this.freqSegmentId = freqSegmentId;
	}
	
	/**
	 * 获取分段ID
	 */
	public String getFreqSegmentId() {
		return freqSegmentId;
	}
	/**
	 * 设置设施与设备类型：1.设备  2设施
	 */
	public void setSbssType(String sbssType) {
		this.sbssType = sbssType;
	}
	
	/**
	 * 获取设施与设备类型：1.设备  2设施
	 */
	public String getSbssType() {
		return sbssType;
	}
	/**
	 * 设置设别或设施的ID
	 */
	public void setSbssId(String sbssId) {
		this.sbssId = sbssId;
	}
	
	/**
	 * 获取设别或设施的ID
	 */
	public String getSbssId() {
		return sbssId;
	}
	/**
	 * 设置设备或设施的名称
	 */
	public void setSbssName(String sbssName) {
		this.sbssName = sbssName;
	}
	
	/**
	 * 获取设备或设施的名称
	 */
	public String getSbssName() {
		return sbssName;
	}
	/**
	 * 设置冗余字段：巡检点ID
	 */
	public void setXjdItemId(String xjdItemId) {
		this.xjdItemId = xjdItemId;
	}
	
	/**
	 * 获取冗余字段：巡检点ID
	 */
	public String getXjdItemId() {
		return xjdItemId;
	}
	/**
	 * 设置冗余字段：巡检点名称
	 */
	public void setXjdItemName(String xjdItemName) {
		this.xjdItemName = xjdItemName;
	}
	
	/**
	 * 获取冗余字段：巡检点名称
	 */
	public String getXjdItemName() {
		return xjdItemName;
	}
	/**
	 * 设置工艺段ID
	 */
	public void setTechnicsId(String technicsId) {
		this.technicsId = technicsId;
	}
	
	/**
	 * 获取工艺段ID
	 */
	public String getTechnicsId() {
		return technicsId;
	}
	/**
	 * 设置工艺名称
	 */
	public void setTechnicsName(String technicsName) {
		this.technicsName = technicsName;
	}
	
	/**
	 * 获取工艺名称
	 */
	public String getTechnicsName() {
		return technicsName;
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