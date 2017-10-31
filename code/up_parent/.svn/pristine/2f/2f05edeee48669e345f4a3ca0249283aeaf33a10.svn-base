package com.upsoft.system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
* Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：DictTreeDataEntity.java<br>
* 摘要：数据字典树节点实体类<br>
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
@Table(name="sys_dict_tree_data")
public class DictTreeDataEntity extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 树节点ID
	 */
	@Id
	@GenericGenerator(name="custom",strategy="uuid.hex")
	@GeneratedValue(generator="custom")
	@Column(name="NodeId")
	private String nodeId;
	
	/**
	 * 树父节点ID
	 */
	@Column(name="ParentNodeId")
	private String parentNodeId;
	
	/**
	 * 数据代码
	 */
	@Column(name="Code")
	private String code;
	
	/**
	 * 数据名称
	 */
	@Column(name="Data1")
	private String data1;
	
	/**
	 * 数据2
	 */
	@Column(name="Data2")
	private String data2;
	
	/**
	 * 数据3
	 */
	@Column(name="Data3")
	private String data3;
	
	/**
	 * 排序号
	 */
	@Column(name="OrderNo")
	private Long orderNo;

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getParentNodeId() {
		return parentNodeId;
	}

	public void setParentNodeId(String parentNodeId) {
		this.parentNodeId = parentNodeId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getData1() {
		return data1;
	}

	public void setData1(String data1) {
		this.data1 = data1;
	}

	public String getData2() {
		return data2;
	}

	public void setData2(String data2) {
		this.data2 = data2;
	}

	public String getData3() {
		return data3;
	}

	public void setData3(String data3) {
		this.data3 = data3;
	}

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
}
