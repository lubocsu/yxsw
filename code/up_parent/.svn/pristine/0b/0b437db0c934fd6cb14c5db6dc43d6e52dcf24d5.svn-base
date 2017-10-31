package com.upsoft.system.entity;

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
@Table(name = "SYS_SYSTEM_DEFINE")
public class SystemDefineEntity extends BaseEntity{

    @Id
    @GenericGenerator(name = "custom", strategy = "uuid.hex")
    @GeneratedValue(generator = "custom")
    /**
     * 系统ID
     */
    @Column(name = "systemid")
    private String systemId;
    /**
     * 身份码
     */
    @Column(name = "systemcode")
    private String systemCode;
    /**
     * 软件名称
     */
    @Column(name = "systemname")
    private String systemName;
    /**
     * 说明
     */
    @Column(name = "systemdescription")
    private String systemDescription;
    
    public String getSystemId() {
        return systemId;
    }
    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }
    public String getSystemCode() {
        return systemCode;
    }
    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }
    public String getSystemName() {
        return systemName;
    }
    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }
    public String getSystemDescription() {
        return systemDescription;
    }
    public void setSystemDescription(String systemDescription) {
        this.systemDescription = systemDescription;
    }
    
    
}
