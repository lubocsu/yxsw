package com.upsoft.yxsw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.upsoft.system.entity.BaseEntity;

@Entity
@Table(name = "BIZ_T_SB_BASEINFO")
public class BizTSbBaseinfo extends BaseEntity{

	/**
	 * SB_ID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "SB_ID")
	private String sbId;
	/**
	 * 资产编号
	 */
	@Column(name = "SB_CODE")
	private String sbCode;
	/**
	 * 资产财务编号
	 */
	@Column(name = "SB_FN_CODE")
	private String sbFnCode;
	/**
	 * 设备名称
	 */
	@Column(name = "SB_NAME")
	private String sbName;
	/**
	 * 简称：备用
	 */
	 @Column(name = "SREMARK")
	private String sremark;
	/**
	 * ABCDEF类
	 */
	@Column(name = "SB_SORT")
	private String sbSort;
	/**
	 * 设备类型分类 见设备累类型表
	 */
	@Column(name = "SB_TYPE_ID")
	private String sbTypeId;
	/**
	 * GC	国产
	 * HZ	合资
	 * JK	进口
	 * YZ	外资
	 * QT	其他
	 */
	 @Column(name = "GCJK")
	private String gcjk;
	/**
	 * 设备型号
	 */
	 @Column(name = "SBXH")
	private String sbxh;
	/**
	 * 性能参数
	 */
	 @Column(name = "XNCS")
	private String xncs;
	/**
	 * 结构原理
	 */
	 @Column(name = "JGYL")
	private String jgyl;
	/**
	 * 设备制造商 BIZ_T_BASE_FACTORY_INFO表
	 */
	 @Column(name = "MANUFACTURE_ID")
	private String manufactureId;
	/**
	 * 备用：设备供应商 BIZ_T_BASE_FACTORY_INFO表
	 */
	 @Column(name = "SUPPLY_ID")
	private String supplyId;
	/**
	 * 是、否
	 * 0	否
	 * 1	是
	 */
	@Column(name = "IS_ZXCJY")
	private String isZxcjy;
	/**
	 * 新购在库、在用、更换闲置在库、报废在库、报废已处理
	 * 01	新购
	 * 02	在用
	 * 03	更换闲置
	 * 04	报废
	 */
	@Column(name = "ZY_STATUS")
	private String zyStatus;
	/**
	 * 设备原价值
	 */
	@Column(name = "START_VALUE")
	private Double startValue;
	/**
	 * 最近评估时间
	 */
	@Column(name = "ZJPG_SJ")
	private String zjpgSj;
	/**
	 * 最近评估现值 元
	 */
	@Column(name = "ZJPG_VALUE")
	private Double zjpgValue;
	/**
	 * 备注
	 */
	@Column(name = "REMARK")
	private String remark;
	/**
	 * 最低使用年限
	 */
	@Column(name = "CAN_USE_YEAR")
	private Long canUseYear;
	/**
	 * 已折旧年限
	 */
	@Column(name = "HAVE_LOST_YEAR")
	private Long haveLostYear;
	/**
	 * X_坐标
	 */
	@Column(name = "LONGITUDE")
	private Double longitude;
	/**
	 * Y_坐标
	 */
	@Column(name = "LATITUDE")
	private Double latitude;
	/**
	 * 购入日期
	 */
	@Column(name = "BUY_DATE")
	private String buyDate;
	/**
	 * 开始使用日期
	 */
	@Column(name = "START_USE_DATE")
	private String startUseDate;
	/**
	 * 安装位置
	 */
	@Column(name = "SET_ADDRESS")
	private String setAddress;
	/**
	 * 备用字段
	 */
	@Column(name = "BYZD")
	private String byzd;
	/**
	 * 是否删除，1是 0否
	 */
	@Column(name = "DEL_FLAG")
	private String delFlag;
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
	 * 所属水厂ID，非业务数据时非必填
	 */
	@Column(name = "BELONG_WSC_ID")
	private String belongWscId;
	/**
	 * 所属水厂名称，非业务数据时非必填
	 */
	@Column(name = "BELONG_WSC_NAME")
	private String belongWscName;
	/**
	 * 所属部门
	 */
	@Column(name = "BELONG_DEPT")
	private String belongDept;
	/**
	 * 修改人ID或账号
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
	 * 设置SB_ID
	 */
	public void setSbId(String sbId) {
		this.sbId = sbId;
	}
	
	/**
	 * 获取SB_ID
	 */
	public String getSbId() {
		return sbId;
	}
	/**
	 * 设置资产编号
	 */
	public void setSbCode(String sbCode) {
		this.sbCode = sbCode;
	}
	
	/**
	 * 获取资产编号
	 */
	public String getSbCode() {
		return sbCode;
	}
	/**
	 * 设置资产财务编号
	 */
	public void setSbFnCode(String sbFnCode) {
		this.sbFnCode = sbFnCode;
	}
	
	/**
	 * 获取资产财务编号
	 */
	public String getSbFnCode() {
		return sbFnCode;
	}
	/**
	 * 设置设备名称
	 */
	public void setSbName(String sbName) {
		this.sbName = sbName;
	}
	
	/**
	 * 获取设备名称
	 */
	public String getSbName() {
		return sbName;
	}
	/**
	 * 设置简称：备用
	 */
	public void setSremark(String sremark) {
		this.sremark = sremark;
	}
	
	/**
	 * 获取简称：备用
	 */
	public String getSremark() {
		return sremark;
	}
	/**
	 * 设置ABCDEF类
A	A
B	B
C	C
D	D
E	E
F	F
	 */
	public void setSbSort(String sbSort) {
		this.sbSort = sbSort;
	}
	
	/**
	 * 获取ABCDEF类
A	A
B	B
C	C
D	D
E	E
F	F
	 */
	public String getSbSort() {
		return sbSort;
	}
	/**
	 * 设置设备类型分类 见设备累类型表
	 */
	public void setSbTypeId(String sbTypeId) {
		this.sbTypeId = sbTypeId;
	}
	
	/**
	 * 获取设备类型分类 见设备累类型表
	 */
	public String getSbTypeId() {
		return sbTypeId;
	}
	/**
	 * 设置GC	国产
HZ	合资
JK	进口
YZ	外资
QT	其他
	 */
	public void setGcjk(String gcjk) {
		this.gcjk = gcjk;
	}
	
	/**
	 * 获取GC	国产
HZ	合资
JK	进口
YZ	外资
QT	其他
	 */
	public String getGcjk() {
		return gcjk;
	}
	/**
	 * 设置设备型号
	 */
	public void setSbxh(String sbxh) {
		this.sbxh = sbxh;
	}
	
	/**
	 * 获取设备型号
	 */
	public String getSbxh() {
		return sbxh;
	}
	/**
	 * 设置性能参数
	 */
	public void setXncs(String xncs) {
		this.xncs = xncs;
	}
	
	/**
	 * 获取性能参数
	 */
	public String getXncs() {
		return xncs;
	}
	/**
	 * 设置结构原理
	 */
	public void setJgyl(String jgyl) {
		this.jgyl = jgyl;
	}
	
	/**
	 * 获取结构原理
	 */
	public String getJgyl() {
		return jgyl;
	}
	/**
	 * 设置设备制造商 BIZ_T_BASE_FACTORY_INFO表
	 */
	public void setManufactureId(String manufactureId) {
		this.manufactureId = manufactureId;
	}
	
	/**
	 * 获取设备制造商 BIZ_T_BASE_FACTORY_INFO表
	 */
	public String getManufactureId() {
		return manufactureId;
	}
	/**
	 * 设置备用：设备供应商 BIZ_T_BASE_FACTORY_INFO表
	 */
	public void setSupplyId(String supplyId) {
		this.supplyId = supplyId;
	}
	
	/**
	 * 获取备用：设备供应商 BIZ_T_BASE_FACTORY_INFO表
	 */
	public String getSupplyId() {
		return supplyId;
	}
	/**
	 * 设置是、否
0	否
1	是
	 */
	public void setIsZxcjy(String isZxcjy) {
		this.isZxcjy = isZxcjy;
	}
	
	/**
	 * 获取是、否
0	否
1	是
	 */
	public String getIsZxcjy() {
		return isZxcjy;
	}
	/**
	 * 设置新购在库、在用、更换闲置在库、报废在库、报废已处理
01	新购
02	在用
03	更换闲置
04	报废
	 */
	public void setZyStatus(String zyStatus) {
		this.zyStatus = zyStatus;
	}
	
	/**
	 * 获取新购在库、在用、更换闲置在库、报废在库、报废已处理
01	新购
02	在用
03	更换闲置
04	报废
	 */
	public String getZyStatus() {
		return zyStatus;
	}
	/**
	 * 设置设备原价值
	 */
	public void setStartValue(Double startValue) {
		this.startValue = startValue;
	}
	
	/**
	 * 获取设备原价值
	 */
	public Double getStartValue() {
		return startValue;
	}
	/**
	 * 设置最近评估时间
	 */
	public void setZjpgSj(String zjpgSj) {
		this.zjpgSj = zjpgSj;
	}
	
	/**
	 * 获取最近评估时间
	 */
	public String getZjpgSj() {
		return zjpgSj;
	}
	/**
	 * 设置最近评估现值 元
	 */
	public void setZjpgValue(Double zjpgValue) {
		this.zjpgValue = zjpgValue;
	}
	
	/**
	 * 获取最近评估现值 元
	 */
	public Double getZjpgValue() {
		return zjpgValue;
	}
	/**
	 * 设置备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * 获取备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置最低使用年限
	 */
	public void setCanUseYear(Long canUseYear) {
		this.canUseYear = canUseYear;
	}
	
	/**
	 * 获取最低使用年限
	 */
	public Long getCanUseYear() {
		return canUseYear;
	}
	/**
	 * 设置已折旧年限
	 */
	public void setHaveLostYear(Long haveLostYear) {
		this.haveLostYear = haveLostYear;
	}
	
	/**
	 * 获取已折旧年限
	 */
	public Long getHaveLostYear() {
		return haveLostYear;
	}
	/**
	 * 设置X_坐标
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * 获取X_坐标
	 */
	public Double getLongitude() {
		return longitude;
	}
	/**
	 * 设置Y_坐标
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * 获取Y_坐标
	 */
	public Double getLatitude() {
		return latitude;
	}
	/**
	 * 设置购入日期
	 */
	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}
	
	/**
	 * 获取购入日期
	 */
	public String getBuyDate() {
		return buyDate;
	}
	/**
	 * 设置开始使用日期
	 */
	public void setStartUseDate(String startUseDate) {
		this.startUseDate = startUseDate;
	}
	
	/**
	 * 获取开始使用日期
	 */
	public String getStartUseDate() {
		return startUseDate;
	}
	/**
	 * 设置安装位置
	 */
	public void setSetAddress(String setAddress) {
		this.setAddress = setAddress;
	}
	
	/**
	 * 获取安装位置
	 */
	public String getSetAddress() {
		return setAddress;
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
	 * 设置是否删除，1是 0否
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	/**
	 * 获取是否删除，1是 0否
	 */
	public String getDelFlag() {
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
	 * 设置所属水厂ID，非业务数据时非必填
	 */
	public void setBelongWscId(String belongWscId) {
		this.belongWscId = belongWscId;
	}
	
	/**
	 * 获取所属水厂ID，非业务数据时非必填
	 */
	public String getBelongWscId() {
		return belongWscId;
	}
	/**
	 * 设置所属水厂名称，非业务数据时非必填
	 */
	public void setBelongWscName(String belongWscName) {
		this.belongWscName = belongWscName;
	}
	
	/**
	 * 获取所属水厂名称，非业务数据时非必填
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
	 * 设置修改人ID或账号
	 */
	public void setUpdatorAccount(String updatorAccount) {
		this.updatorAccount = updatorAccount;
	}
	
	/**
	 * 获取修改人ID或账号
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