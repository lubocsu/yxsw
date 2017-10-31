package com.upsoft.system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
* Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：DictTreeEntity.java<br>
* 摘要：数据字典树实体类<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：Awesan<br>
* 完成日期：2015年1月21日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：Awesan<br>
* 完成日期：2015年1月21日<br>
*/
@Entity
@Table(name="sys_dict_tree")
public class DictTreeEntity extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 树ID（此处的treeid不一定非要使用GUID，可以使用一些合适的字符串以方便程序处理）
	 */
	@Id
	/*@GenericGenerator(name="custom",strategy="uuid.hex")
	@GeneratedValue(generator="custom")*/
	@Column(name="TreeId")
	private String treeId;
	
	/**
	 * 树描述（这里的树描述相当于业务中对应的数据字典的类型名称，如“机构类型”、“性别”、“职务”等）
	 */
	@Column(name="TreeDescription")
	private String treeDescription;
	
	/**
	 * 树类型
	 * 0：系统级别，用户不可以维护数据，只读；
	 * 1：用户级别，用户可以维护数据，也即是可以维护数据字典公共树数据表中对应该数据字典的数据；
	 * 2：自定义级别，树本身也属于用户维护，也即是这里的数据字典公共树表用户也可以维护，同时公共树对应的数据字典公共树数据表中与之对应的数据项也可以维护
	 */
	@Column(name="TreeType")
	private Integer treeType;
	
	/**
	 * 限制级别（指树数据的级数限制（主要用于保护数据字典公共树数据表）： 
	 * 0：不限制；
	 * 1：1级（1级表示不分级），也即是没有数据字典公共树数据表中数据；
	 * 2：2级；
	 * 3：3级
	 */
	@Column(name="LevelLimit")
	private Integer levelLimit;
	
	/**
	 * 排序号
	 */
	@Column(name="OrderNo")
	private Long orderNo;
	
	/**
	 * 备注
	 */
	@Column(name="Remark")
	private String remark;
	
	/**
	 * 增加分组字段 2015.03.24 hy
	 */
	@Column(name="groupId")
	private Integer groupId;

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	public String getTreeDescription() {
		return treeDescription;
	}

	public void setTreeDescription(String treeDescription) {
		this.treeDescription = treeDescription;
	}

	public Integer getTreeType() {
		return treeType;
	}

	public void setTreeType(Integer treeType) {
		this.treeType = treeType;
	}

	public Integer getLevelLimit() {
		return levelLimit;
	}

	public void setLevelLimit(Integer levelLimit) {
		this.levelLimit = levelLimit;
	}

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
}
