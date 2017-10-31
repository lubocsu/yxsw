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
@Table(name = "BIZ_T_XJ_TECHNICS_SCOPE_ATT_XJ")
public class BizTXjTechnicsScopeAttXj extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	 @Id
	 @GenericGenerator(name = "generator", strategy = "uuid.hex")
	 @GeneratedValue(generator = "generator")
	 @Column(name = "ATT_ID")
	private String attId;
	/**
	 * 工艺段ID
	 */
	 @Column(name = "TECHNICS_ID")
	private String technicsId;
	/**
	 * 巡检点ID
	 */
	 @Column(name = "XJD_ITEM_ID")
	private String xjdItemId;
	/**
	 * 巡检点名称
	 */
	 @Column(name = "XJD_ITEM_NAME")
	private String xjdItemName;
	/**
	 * 备用字段
	 */
	 @Column(name = "YBZD")
	private String ybzd;
	
	
	/**
	 * 设置主键ID
	 */
	public void setAttId(String attId) {
		this.attId = attId;
	}
	
	/**
	 * 获取主键ID
	 */
	public String getAttId() {
		return attId;
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
	 * 设置巡检点ID
	 */
	public void setXjdItemId(String xjdItemId) {
		this.xjdItemId = xjdItemId;
	}
	
	/**
	 * 获取巡检点ID
	 */
	public String getXjdItemId() {
		return xjdItemId;
	}
	/**
	 * 设置巡检点名称
	 */
	public void setXjdItemName(String xjdItemName) {
		this.xjdItemName = xjdItemName;
	}
	
	/**
	 * 获取巡检点名称
	 */
	public String getXjdItemName() {
		return xjdItemName;
	}
	/**
	 * 设置备用字段
	 */
	public void setYbzd(String ybzd) {
		this.ybzd = ybzd;
	}
	
	/**
	 * 获取备用字段
	 */
	public String getYbzd() {
		return ybzd;
	}
}