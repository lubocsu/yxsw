package com.upsoft.yxsw.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.upsoft.system.entity.BaseEntity;

@Entity
@Table(name = "BIZ_T_XJ_ZYP_TEMPLATE_ITM_DET")
public class BizTXjZypTemplateItmDet extends BaseEntity {

	/**
	 * 主键ID
	 */
	 @Id
	 @GenericGenerator(name = "generator", strategy = "uuid.hex")
	 @GeneratedValue(generator = "generator")
	 @Column(name = "DETAIL_ID")
	private String detailId;
	/**
	 * 模板项ID
	 */
	 @Column(name = "ZXP_TEMP_ITM_ID")
	private String zxpTempItmId;
	/**
	 * 巡检指标项ID
	 */
	 @Column(name = "CXZB_ID")
	private String cxzbId;
	/**
	 * 冗余：指标项名称
	 */
	 @Column(name = "CXZB_NAME")
	private String cxzbName;
	/**
	 * 简称
	 */
	 @Column(name = "CXZB_JC")
	private String cxzbJc;
	/**
	 * 排序号
	 */
	 @Column(name = "DETAIL_SORT")
	private Long detailSort;
	/**
	 * 备用字段
	 */
	 @Column(name = "BYZD")
	private String byzd;
	
	
	/**
	 * 设置主键ID
	 */
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	
	/**
	 * 获取主键ID
	 */
	public String getDetailId() {
		return detailId;
	}
	/**
	 * 设置模板项ID
	 */
	public void setZxpTempItmId(String zxpTempItmId) {
		this.zxpTempItmId = zxpTempItmId;
	}
	
	/**
	 * 获取模板项ID
	 */
	public String getZxpTempItmId() {
		return zxpTempItmId;
	}
	/**
	 * 设置巡检指标项ID
	 */
	public void setCxzbId(String cxzbId) {
		this.cxzbId = cxzbId;
	}
	
	/**
	 * 获取巡检指标项ID
	 */
	public String getCxzbId() {
		return cxzbId;
	}
	/**
	 * 设置冗余：指标项名称
	 */
	public void setCxzbName(String cxzbName) {
		this.cxzbName = cxzbName;
	}
	
	/**
	 * 获取冗余：指标项名称
	 */
	public String getCxzbName() {
		return cxzbName;
	}
	/**
	 * 设置简称
	 */
	public void setCxzbJc(String cxzbJc) {
		this.cxzbJc = cxzbJc;
	}
	
	/**
	 * 获取简称
	 */
	public String getCxzbJc() {
		return cxzbJc;
	}
	/**
	 * 设置排序号
	 */
	public void setDetailSort(Long detailSort) {
		this.detailSort = detailSort;
	}
	
	/**
	 * 获取排序号
	 */
	public Long getDetailSort() {
		return detailSort;
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