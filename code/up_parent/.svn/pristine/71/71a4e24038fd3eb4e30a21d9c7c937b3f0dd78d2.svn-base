package com.upsoft.yxsw.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.upsoft.system.entity.BaseEntity;

@Entity
@Table(name = "BIZ_T_XJ_ZYP_CX_MAKE_TMP")
public class BizTXjZypCxMakeTmp extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -2553652724234490940L;
	/**
	 * 主键ID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "MAKE_TMP_ID")
	private String makeTmpId;
	/**
	 * 拟定表BIZ_T_XJ_ZYP_CX_MAKE ID
	 */
	@Column(name = "CX_MAKE_ID")
	private String cxMakeId;
	/**
	 * 模板主键ID,拟定工作票时关联的模板表BIZ_T_XJ_ZYP_TEMPLATE_ITM 的主键ID
	 */
	@Column(name = "ZXP_TEMP_ITM_ID")
	private String zxpTempItmId;
	/**
	 * 内容名称,定义的工作票上的分块内容，比如：进水水质
	 */
	@Column(name = "ZXP_TEMP_ITM_NAME")
	private String zxpTempItmName;
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
	 * 备用字段
	 */
	@Column(name = "BYZD")
	private String byzd;
	/**
	 * 排序号
	 */
	@Column(name = "ZXP_TEMP_SORT")
	private Long zxpTempSort;

	@Transient
	private List<BizTXjZypCxMakeTmpItem> makeTmpItemList; // 具体作业票指标内容
	
	/**
	 * 设置主键ID
	 */
	public void setMakeTmpId(String makeTmpId) {
		this.makeTmpId = makeTmpId;
	}

	/**
	 * 获取主键ID
	 */
	public String getMakeTmpId() {
		return makeTmpId;
	}

	/**
	 * 设置拟定表BIZ_T_XJ_ZYP_CX_MAKE ID
	 */
	public void setCxMakeId(String cxMakeId) {
		this.cxMakeId = cxMakeId;
	}

	/**
	 * 获取拟定表BIZ_T_XJ_ZYP_CX_MAKE ID
	 */
	public String getCxMakeId() {
		return cxMakeId;
	}

	/**
	 * 设置模板主键ID,拟定工作票时关联的模板表BIZ_T_XJ_ZYP_TEMPLATE_ITM 的主键ID
	 */
	public void setZxpTempItmId(String zxpTempItmId) {
		this.zxpTempItmId = zxpTempItmId;
	}

	/**
	 * 获取模板主键ID,拟定工作票时关联的模板表BIZ_T_XJ_ZYP_TEMPLATE_ITM 的主键ID
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

	public List<BizTXjZypCxMakeTmpItem> getMakeTmpItemList() {
		return makeTmpItemList;
	}

	public void setMakeTmpItemList(List<BizTXjZypCxMakeTmpItem> makeTmpItemList) {
		this.makeTmpItemList = makeTmpItemList;
	}
}