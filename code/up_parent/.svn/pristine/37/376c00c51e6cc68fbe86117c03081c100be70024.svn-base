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
* 文件名称：SysMenu.java<br>
* 摘要：系统菜单类<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：吴炫<br>
* 完成日期：2015年1月22日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：吴炫<br>
* 完成日期：2015年1月22日<br>
 */
@Entity
@Table(name = "sys_menu")
public class SysMenuEntity extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1572347482340232443L;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	@Column(name = "MenuId")
	// 机构ID
	private String menuId;

	// 菜单名称
	@Column(name = "MenuName")
	private String menuName;

	// 菜单类型
	@Column(name = "MenuType")
	private Integer menuType;

	// 菜单图标
	@Column(name = "ImagePath")
	private String imagePath;

	// 父菜单ID
	@Column(name = "ParentMenuId")
	private String parentMenuId;

	// 权限编号
	@Column(name = "PermissionNo")
	private String permissionNo;

	// 模块链接地址
	@Column(name = "LinkURL")
	private String linkURL;

	// 参数
	@Column(name = "Param")
	private String param;

	// 是否有效（是否有效, 0:否 1:是）
	@Column(name = "Enabled")
	private Integer enabled;

	// 是否公开（是否公开,0:否 1是 ）
	@Column(name = "IsPublic")
	private Integer isPublic;

	// 菜单的功能类型
	@Column(name = "FunctionType")
	private Integer functionType;

	// 菜单操作类型（该属性用于对LinkURL进行控制,因为业务入口值可能存在不一致的逻辑,目前默认OperateType = 9 时 LinkURL采用如上所述的处理逻辑）
	@Column(name = "OperateType")
	private Integer operateType;

	// 描述说明
	@Column(name = "Description")
	private String description;

	// 排序号
	@Column(name = "OrderNo")
	private Integer orderNo;
	
	@Column(name = "SystemCode")
	private String SystemCode;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public String getPermissionNo() {
		return permissionNo;
	}

	public void setPermissionNo(String permissionNo) {
		this.permissionNo = permissionNo;
	}

	public String getLinkURL() {
		return linkURL;
	}

	public void setLinkURL(String linkURL) {
		this.linkURL = linkURL;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Integer getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Integer isPublic) {
		this.isPublic = isPublic;
	}

	public Integer getFunctionType() {
		return functionType;
	}

	public void setFunctionType(Integer functionType) {
		this.functionType = functionType;
	}

	public Integer getOperateType() {
		return operateType;
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getSystemCode() {
		return SystemCode;
	}

	public void setSystemCode(String systemCode) {
		SystemCode = systemCode;
	}

}
