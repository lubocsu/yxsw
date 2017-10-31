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
@Table(name = "BIZ_T_XJD_ITEM")
public class BizTXjdItem extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 巡检点位置
	 */
	 @Column(name = "XJD_ITEM_ADDRESS")
	private String xjdItemAddress;
	/**
	 * 巡检点说明
	 */
	 @Column(name = "XJD_ITEM_DESC")
	private String xjdItemDesc;
	/**
	 * 备用字段
	 */
	 @Column(name = "BYZD")
	private String byzd;
	/**
	 * RFID编号
	 */
	 @Column(name = "RFID_CODE")
	private String rfidCode;
	/**
	 * 是否删除，1是 0否
	 */
	 @Column(name = "DEL_FLAG")
	private Long delFlag;
	/**
	 * 创建时间，一般不作为业务字段使用
	 */
	 @Column(name = "CREATE_TIMESTEMP")
	private String createTimestemp;
	/**
	 * 创建人ID或账号
	 */
	 @Column(name = "CREATOR_ACCOUNT")
	private String creatorAccount;
	/**
	 * 创建人名称
	 */
	 @Column(name = "CREATOR_NAME")
	private String creatorName;
	/**
	 * 所属厂站ID，非业务数据时非必填
	 */
	 @Column(name = "BELONG_WSC_ID")
	private String belongWscId;
	/**
	 * 所属厂站名称，非业务数据时非必填
	 */
	 @Column(name = "BELONG_WSC_NAME")
	private String belongWscName;
	/**
	 * 所属部门
	 */
	 @Column(name = "BELONG_DEPT")
	private String belongDept;
	/**
	 * 创建人ID或账号
	 */
	 @Column(name = "UPDATOR_ACCOUNT")
	private String updatorAccount;
	/**
	 * 修改人姓名
	 */
	 @Column(name = "UPDATOR_NAME")
	private String updatorName;
	/**
	 * 修改时间，一般不作为业务字段使用
	 */
	 @Column(name = "UPDATE_TIMESTEMP")
	private String updateTimestemp;
	/**
	 * 主键
	 */
	 @Id
	 @GenericGenerator(name = "generator", strategy = "uuid.hex")
	 @GeneratedValue(generator = "generator")
	 @Column(name = "XJD_ITEM_ID")
	private String xjdItemId;
	/**
	 * 巡检点名称
	 */
	 @Column(name = "XJD_ITEM_NAME")
	private String xjdItemName;
	
	
	/**
	 * 设置巡检点位置
	 */
	public void setXjdItemAddress(String xjdItemAddress) {
		this.xjdItemAddress = xjdItemAddress;
	}
	
	/**
	 * 获取巡检点位置
	 */
	public String getXjdItemAddress() {
		return xjdItemAddress;
	}
	/**
	 * 设置巡检点说明
	 */
	public void setXjdItemDesc(String xjdItemDesc) {
		this.xjdItemDesc = xjdItemDesc;
	}
	
	/**
	 * 获取巡检点说明
	 */
	public String getXjdItemDesc() {
		return xjdItemDesc;
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
	/**
	 * 设置RFID编号
	 */
	public void setRfidCode(String rfidCode) {
		this.rfidCode = rfidCode;
	}
	
	/**
	 * 获取RFID编号
	 */
	public String getRfidCode() {
		return rfidCode;
	}
	/**
	 * 设置是否删除，1是 0否
	 */
	public void setDelFlag(Long delFlag) {
		this.delFlag = delFlag;
	}
	
	/**
	 * 获取是否删除，1是 0否
	 */
	public Long getDelFlag() {
		return delFlag;
	}
	/**
	 * 设置创建时间，一般不作为业务字段使用
	 */
	public void setCreateTimestemp(String createTimestemp) {
		this.createTimestemp = createTimestemp;
	}
	
	/**
	 * 获取创建时间，一般不作为业务字段使用
	 */
	public String getCreateTimestemp() {
		return createTimestemp;
	}
	/**
	 * 设置创建人ID或账号
	 */
	public void setCreatorAccount(String creatorAccount) {
		this.creatorAccount = creatorAccount;
	}
	
	/**
	 * 获取创建人ID或账号
	 */
	public String getCreatorAccount() {
		return creatorAccount;
	}
	/**
	 * 设置创建人名称
	 */
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	
	/**
	 * 获取创建人名称
	 */
	public String getCreatorName() {
		return creatorName;
	}
	/**
	 * 设置所属厂站ID，非业务数据时非必填
	 */
	public void setBelongWscId(String belongWscId) {
		this.belongWscId = belongWscId;
	}
	
	/**
	 * 获取所属厂站ID，非业务数据时非必填
	 */
	public String getBelongWscId() {
		return belongWscId;
	}
	/**
	 * 设置所属厂站名称，非业务数据时非必填
	 */
	public void setBelongWscName(String belongWscName) {
		this.belongWscName = belongWscName;
	}
	
	/**
	 * 获取所属厂站名称，非业务数据时非必填
	 */
	public String getBelongWscName() {
		return belongWscName;
	}
	/**
	 * 设置所属部门
	 */
	public void setBelongDept(String belongDept) {
		this.belongDept = belongDept;
	}
	
	/**
	 * 获取所属部门
	 */
	public String getBelongDept() {
		return belongDept;
	}
	/**
	 * 设置创建人ID或账号
	 */
	public void setUpdatorAccount(String updatorAccount) {
		this.updatorAccount = updatorAccount;
	}
	
	/**
	 * 获取创建人ID或账号
	 */
	public String getUpdatorAccount() {
		return updatorAccount;
	}
	/**
	 * 设置修改人姓名
	 */
	public void setUpdatorName(String updatorName) {
		this.updatorName = updatorName;
	}
	
	/**
	 * 获取修改人姓名
	 */
	public String getUpdatorName() {
		return updatorName;
	}
	/**
	 * 设置修改时间，一般不作为业务字段使用
	 */
	public void setUpdateTimestemp(String updateTimestemp) {
		this.updateTimestemp = updateTimestemp;
	}
	
	/**
	 * 获取修改时间，一般不作为业务字段使用
	 */
	public String getUpdateTimestemp() {
		return updateTimestemp;
	}
	/**
	 * 设置主键
	 */
	public void setXjdItemId(String xjdItemId) {
		this.xjdItemId = xjdItemId;
	}
	
	/**
	 * 获取主键
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
}