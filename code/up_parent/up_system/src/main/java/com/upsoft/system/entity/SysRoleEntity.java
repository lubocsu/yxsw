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
@Table(name = "SYS_ROLE")
public class SysRoleEntity extends BaseEntity implements Serializable {
    @Id
    @GenericGenerator(name = "custom", strategy = "uuid.hex")
    @GeneratedValue(generator = "custom")
    /**
     * 角色ID
     */
    @Column(name = "roleid")
    private String roleId;

    /**
     * 角色名称
     */
    @Column(name = "rolename")
    private String roleName;

    /**
     * 角色类型，从数据字典选择，主要包括：1岗位角色 2流程角色
     */
    @Column(name = "roletype")
    private String roleType;

    /**
     * 角色定义类型，1系统定义（系统定义的角色，不允许用户删除，例如超级用户角色） 2用户自定义角色
     */
    @Column(name = "definetype")
    private String defineType;

    /**
     * 角色描述
     */
    @Column(name = "roledescription")
    private String roleDescription;

    /**
     * 系统代码
     */
    @Column(name = "systemcode")
    private String systemCode;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getDefineType() {
        return defineType;
    }

    public void setDefineType(String defineType) {
        this.defineType = defineType;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }
    
}
