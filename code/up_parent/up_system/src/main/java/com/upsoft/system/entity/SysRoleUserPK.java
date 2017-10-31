/*
 * SysRoleUserPK.java
 * Created on 2015年1月23日 下午2:46:22
 * Copyright (c) 重庆扬讯软件技术有限公司  All Rights Reserved.
 * http://www.upsoft.com.cn
 *
 * This software is the confidential and proprietary information of UPSoft.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with UPSoft.
 */
package com.upsoft.system.entity;

import java.io.Serializable;

import javax.persistence.Column;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：SysRoleUserPK.java<br>
 * 摘要：SysRoleUser复合主键类<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：蒋迪<br>
 * 完成日期：2015年1月23日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：蒋迪<br>
 * 完成日期：2015年1月23日<br>
 */
public class SysRoleUserPK  implements Serializable{
	private static final long serialVersionUID = 1L;

	@Column(name="RoleId")  
    private String roleId;  
    @Column(name="UserId")  
    private String userId;  
    
    public SysRoleUserPK(){
    	
    }
    
    public SysRoleUserPK(String roleId, String userId){
    	this.roleId = roleId;
    	this.userId = userId;
    }
    
    @Override  
    public int hashCode() {  
    	return roleId.hashCode()+userId.hashCode();
    }  
  
    @Override  
    public boolean equals(Object obj) {  
    	if (this == obj)	return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        SysRoleUserPK other = (SysRoleUserPK)obj;
        if (other.roleId.equals(roleId) && other.userId.equals(userId))	return true;
        return false;
    }  
}
