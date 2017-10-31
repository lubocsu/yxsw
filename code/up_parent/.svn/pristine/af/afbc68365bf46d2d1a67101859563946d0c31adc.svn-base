package com.upsoft.system.entity;

import java.io.Serializable;

import javax.persistence.Column;

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
public class SysRolePermissionPK implements Serializable{
	private static final long serialVersionUID = 1L;
    /**
     * 角色ID
     */
    @Column(name = "roleid")
    private String roleId;
    /**
     * 权限ID
     */
    @Column(name = "permissionid")
    private String permissionId;
    
    public SysRolePermissionPK(){
        	
    }
    
    public SysRolePermissionPK(String roleId, String permissionId){
    	this.roleId = roleId;
    	this.permissionId = permissionId;
    }
    
    @Override  
    public int hashCode() {  
    	return roleId.hashCode()+permissionId.hashCode();
    }  
  
    @Override  
    public boolean equals(Object obj) {  
    	if (this == obj)	return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        SysRolePermissionPK other = (SysRolePermissionPK)obj;
        if (other.roleId.equals(roleId) && other.permissionId.equals(permissionId))	return true;
        return false;
    }  
}
