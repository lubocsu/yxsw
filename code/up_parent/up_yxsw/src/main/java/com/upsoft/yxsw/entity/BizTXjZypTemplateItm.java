package com.upsoft.yxsw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.upsoft.system.entity.BaseEntity;

@Entity
@Table(name = "BIZ_T_XJ_ZYP_TEMPLATE_ITM")
public class BizTXjZypTemplateItm extends BaseEntity {

	/**
	 * 模板项主键ID
	 */
	 @Id
	 @GenericGenerator(name = "generator", strategy = "uuid.hex")
	 @GeneratedValue(generator = "generator")
	 @Column(name = "ZXP_TEMP_ITM_ID")
	private String zxpTempItmId;
	/**
	 * 内容名称,定义的工作票上的分块内容，比如：进水水质
	 */
	 @Column(name = "ZXP_TEMP_ITM_NAME")
	private String zxpTempItmName;
	/**
	 * 模板主键
	 */
	 @Column(name = "TEMP_ID")
	private String tempId;
	/**
	 * 记录下达值，1是 0否
	 */
	 @Column(name = "JLXDZ")
	private Long jlxdz;
	/**
	 * 记录完成值，1是 0否
	 */
	 @Column(name = "JLSBZ")
	private Long jlsbz;
	/**
	 * 记录去除率，1是 0否
	 */
	 @Column(name = "JLQCL")
	private Long jlqcl;
	/**
	 * 配置说明
	 */
	 @Column(name = "CONF_DESC")
	private String confDesc;
	/**
	 * 排序号
	 */
	 @Column(name = "ZXP_TEMP_SORT")
	private Long zxpTempSort;
	/**
	 * 备用字段
	 */
	 @Column(name = "BYZD")
	private String byzd;
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
	 * 设置模板项主键ID
	 */
	public void setZxpTempItmId(String zxpTempItmId) {
		this.zxpTempItmId = zxpTempItmId;
	}
	
	/**
	 * 获取模板项主键ID
	 */
	public String getZxpTempItmId() {
		return zxpTempItmId;
	}
	/**
	 * 设置内容名称,定义的工作票上的分块内容，比如：进水水质
	 */
	public void setZxpTempItmName(String zxpTempItmName) {
		this.zxpTempItmName = zxpTempItmName;
	}
	
	/**
	 * 获取内容名称,定义的工作票上的分块内容，比如：进水水质
	 */
	public String getZxpTempItmName() {
		return zxpTempItmName;
	}
	/**
	 * 设置模板主键
	 */
	public void setTempId(String tempId) {
		this.tempId = tempId;
	}
	
	/**
	 * 获取模板主键
	 */
	public String getTempId() {
		return tempId;
	}
	/**
	 * 设置记录下达值，1是 0否
	 */
	public void setJlxdz(Long jlxdz) {
		this.jlxdz = jlxdz;
	}
	
	/**
	 * 获取记录下达值，1是 0否
	 */
	public Long getJlxdz() {
		return jlxdz;
	}
	/**
	 * 设置记录完成值，1是 0否
	 */
	public void setJlsbz(Long jlsbz) {
		this.jlsbz = jlsbz;
	}
	
	/**
	 * 获取记录完成值，1是 0否
	 */
	public Long getJlsbz() {
		return jlsbz;
	}
	/**
	 * 设置记录去除率，1是 0否
	 */
	public void setJlqcl(Long jlqcl) {
		this.jlqcl = jlqcl;
	}
	
	/**
	 * 获取记录去除率，1是 0否
	 */
	public Long getJlqcl() {
		return jlqcl;
	}
	/**
	 * 设置配置说明
	 */
	public void setConfDesc(String confDesc) {
		this.confDesc = confDesc;
	}
	
	/**
	 * 获取配置说明
	 */
	public String getConfDesc() {
		return confDesc;
	}
	/**
	 * 设置排序号
	 */
	public void setZxpTempSort(Long zxpTempSort) {
		this.zxpTempSort = zxpTempSort;
	}
	
	/**
	 * 获取排序号
	 */
	public Long getZxpTempSort() {
		return zxpTempSort;
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
}