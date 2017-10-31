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
@Table(name = "BIZ_T_XJ_CX_TASK_ITEM_RST_DET")
public class BizTXjCxTaskItemRstDet extends BaseEntity implements Serializable{

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
	 * 主表BIZ_T_XJ_CX_TASK_ITEM_SBSS_RST
	 */
	 @Column(name = "SBSS_RST_ID")
	private String sbssRstId;
	/**
	 * 选项值
	 */
	 @Column(name = "SEL_VALUE")
	private String selValue;
	/**
	 * 选项名称
	 */
	 @Column(name = "SEL_NAME")
	private String selName;
	/**
	 * 排序号
	 */
	 @Column(name = "SEL_SORT")
	private Long selSort;
	/**
	 * 该选项被选择时代表是否有问题：1正常，2异常
	 */
	 @Column(name = "SFZC")
	private String sfzc;
	/**
	 * 是否默认 1是，0否
	 */
	 @Column(name = "SFMR")
	private String sfmr;
	
	
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
	 * 设置主表BIZ_T_XJ_CX_TASK_ITEM_SBSS_RST
	 */
	public void setSbssRstId(String sbssRstId) {
		this.sbssRstId = sbssRstId;
	}
	
	/**
	 * 获取主表BIZ_T_XJ_CX_TASK_ITEM_SBSS_RST
	 */
	public String getSbssRstId() {
		return sbssRstId;
	}
	/**
	 * 设置选项值
	 */
	public void setSelValue(String selValue) {
		this.selValue = selValue;
	}
	
	/**
	 * 获取选项值
	 */
	public String getSelValue() {
		return selValue;
	}
	/**
	 * 设置选项名称
	 */
	public void setSelName(String selName) {
		this.selName = selName;
	}
	
	/**
	 * 获取选项名称
	 */
	public String getSelName() {
		return selName;
	}
	/**
	 * 设置排序号
	 */
	public void setSelSort(Long selSort) {
		this.selSort = selSort;
	}
	
	/**
	 * 获取排序号
	 */
	public Long getSelSort() {
		return selSort;
	}
	/**
	 * 设置该选项被选择时代表是否有问题：1正常，2异常
	 */
	public void setSfzc(String sfzc) {
		this.sfzc = sfzc;
	}
	
	/**
	 * 获取该选项被选择时代表是否有问题：1正常，2异常
	 */
	public String getSfzc() {
		return sfzc;
	}
	/**
	 * 设置是否默认 1是，0否
	 */
	public void setSfmr(String sfmr) {
		this.sfmr = sfmr;
	}
	
	/**
	 * 获取是否默认 1是，0否
	 */
	public String getSfmr() {
		return sfmr;
	}
}