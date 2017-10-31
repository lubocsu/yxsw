package com.upsoft.system.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：SystemDefine.java<br>
 * 摘要：软件系统定义实体<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：李  红<br>
 * 完成日期：2015年1月22日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：李  红<br>
 * 完成日期：2015年1月22日<br>
 */
@Entity
@Table(name = "SYS_ROLE_PERMISSION")
@IdClass(SysRolePermissionPK.class)
public class SysRolePermissionEntity extends BaseEntity{
    /**
     * 角色ID
     */
    @Id 
    private String roleId;
    /**
     * 权限ID
     */
    @Id 
    private String permissionId;
    public String getRoleId() {
        return roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getPermissionId() {
        return permissionId;
    }
    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }
    
    
}
