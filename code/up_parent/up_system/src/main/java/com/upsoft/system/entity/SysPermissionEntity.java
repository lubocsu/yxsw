package com.upsoft.system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 * 
 * 文件名称：Role.java<br>
 * 摘要：角色管理实体类<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：李 红<br>
 * 完成日期：2015年1月21日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：李 红<br>
 * 完成日期：2015年1月21日<br>
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SYS_PERMISSION")
public class SysPermissionEntity extends BaseEntity implements Serializable {

    @Id
    @GenericGenerator(name = "custom", strategy = "uuid.hex")
    @GeneratedValue(generator = "custom")
    /**
     * 权限ID
     */
    @Column(name = "permissionid")
    private String permissionId;

    /**
     * 父权限ID
     */
    @Column(name = "parentpermissionid")
    private String parentPermissionId;

    /**
     * 权限名称
     */
    @Column(name = "permissionname")
    private String permissionName;

    /**
     * 权限编号
     */
    @Column(name = "permissionno")
    private String permissionNo;

    /**
     * 排序号
     */
    @Column(name = "orderno")
    private Integer orderNo;

    /**
     * 是否有效（是否有效, 0:否 1:是 ）
     */
    @Column(name = "enabled")
    private Integer enabled;

    /**
     * 系统代码
     */
    @Column(name="systemcode")
    private String systemCode;

    /**
     * 权限类型（0功能权限1按钮权限）
     */
    @Column(name="permissiontype")
    private Integer permissionType;
    
    @Transient
    private String menuId;
    
    @Transient
    private String btnPermission;
    


    public Integer getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(Integer permissionType) {
        this.permissionType = permissionType;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getParentPermissionId() {
        return parentPermissionId;
    }

    public void setParentPermissionId(String parentPermissionId) {
        this.parentPermissionId = parentPermissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionNo() {
        return permissionNo;
    }

    public void setPermissionNo(String permissionNo) {
        this.permissionNo = permissionNo;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }
    /**
     * @return the menuId
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * @param menuId the menuId to set
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    /**
     * @return the btnPermission
     */
    public String getBtnPermission() {
        return btnPermission;
    }

    /**
     * @param btnPermission the btnPermission to set
     */
    public void setBtnPermission(String btnPermission) {
        this.btnPermission = btnPermission;
    }
}
