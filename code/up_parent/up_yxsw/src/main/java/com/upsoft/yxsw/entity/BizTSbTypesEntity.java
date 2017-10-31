package com.upsoft.yxsw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.upsoft.system.entity.BaseEntity;

/**
* Copyright (c) 2017,重庆扬讯软件技术股份有限公司<br>
* All rights reserved.<br>
*
* 文件名称：BizTSbTypesEntity.java<br>
* 摘要：设备类型管理Entity<br>
* -------------------------------------------------------<br>
* 当前版本：1.0<br>
* 作者：陈涛<br>
* 完成日期：2017年9月8日<br>
* -------------------------------------------------------<br>
*/
@Entity
@Table(name = "BIZ_T_SB_TYPES")
public class BizTSbTypesEntity extends BaseEntity implements Serializable{
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
		 @Column(name = "SB_TYPE_ID")
		private String sbTypeId;
		/**
		 * 类型编码
		 */
		 @Column(name = "CODE")
		private String code;
		/**
		 * 父类型ID，如果是顶级为NULL
		 */
		 @Column(name = "PARENT_TYPE_ID")
		private String parentTypeId;
		/**
		 * 类型名称
		 */
		 @Column(name = "NAME")
		private String name;
		/**
		 * 排序号
		 */
		 @Column(name = "ORDERS")
		private Long orders;
		/**
		 * 层级关系
		 */
		 @Column(name = "LAYER")
		private String layer;
		/**
		 * 计量单位
		 */
		 @Column(name = "UNIT")
		private String unit;
		/**
		 * 类型说明
		 */
		 @Column(name = "REMARK")
		private String remark;
		/**
		 * 是否停用该类型，停用后不可再被设备选择该类型，1是 0否
		 */
		 @Column(name = "OUT_SERVICE")
		private Long outService;
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
		 * 设置主键ID
		 */
		public void setSbTypeId(String sbTypeId) {
			this.sbTypeId = sbTypeId;
		}
		
		/**
		 * 获取主键ID
		 */
		public String getSbTypeId() {
			return sbTypeId;
		}
		/**
		 * 设置类型编码
		 */
		public void setCode(String code) {
			this.code = code;
		}
		
		/**
		 * 获取类型编码
		 */
		public String getCode() {
			return code;
		}
		/**
		 * 设置父类型ID，如果是顶级为NULL
		 */
		public void setParentTypeId(String parentTypeId) {
			this.parentTypeId = parentTypeId;
		}
		
		/**
		 * 获取父类型ID，如果是顶级为NULL
		 */
		public String getParentTypeId() {
			return parentTypeId;
		}
		/**
		 * 设置类型名称
		 */
		public void setName(String name) {
			this.name = name;
		}
		
		/**
		 * 获取类型名称
		 */
		public String getName() {
			return name;
		}
		/**
		 * 设置排序号
		 */
		public void setOrders(Long orders) {
			this.orders = orders;
		}
		
		/**
		 * 获取排序号
		 */
		public Long getOrders() {
			return orders;
		}
		/**
		 * 设置层级关系
		 */
		public void setLayer(String layer) {
			this.layer = layer;
		}
		
		/**
		 * 获取层级关系
		 */
		public String getLayer() {
			return layer;
		}
		/**
		 * 设置计量单位
		 */
		public void setUnit(String unit) {
			this.unit = unit;
		}
		
		/**
		 * 获取计量单位
		 */
		public String getUnit() {
			return unit;
		}
		/**
		 * 设置类型说明
		 */
		public void setRemark(String remark) {
			this.remark = remark;
		}
		
		/**
		 * 获取类型说明
		 */
		public String getRemark() {
			return remark;
		}
		/**
		 * 设置是否停用该类型，停用后不可再被设备选择该类型，1是 0否
		 */
		public void setOutService(Long outService) {
			this.outService = outService;
		}
		
		/**
		 * 获取是否停用该类型，停用后不可再被设备选择该类型，1是 0否
		 */
		public Long getOutService() {
			return outService;
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
