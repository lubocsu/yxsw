package com.upsoft.system.entity;

import java.io.Serializable;
import java.util.Date;

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
* 文件名称：ErrorEntity.java<br>
* 摘要：问题反馈实体类<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：李  红<br>
* 完成日期：2015年4月2日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：李  红<br>
* 完成日期：2015年4月2日<br>
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SYS_FEEDBACK")
public class FeedBackEntity extends BaseEntity implements Serializable {
    @Id
    @GenericGenerator(name = "custom", strategy = "uuid.hex")
    @GeneratedValue(generator = "custom")
    /**
     * ID
     */
    @Column(name = "ID")
    private String id;

    /**
     * 标题
     */
    @Column(name = "TITLE")
    private String title;

    /**
     * 内容
     */
    @Column(name = "CONTENT")
    private String content;

    /**
     * 系统代码
     */
    @Column(name = "SYSTEMCODE")
    private String systemCode;

    /**
     * 反馈人
     */
    @Column(name = "USERID")
    private String userId;

    /**
     * 反馈时间
     */
    @Column(name = "CREATETIME")
    private Date createTime;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the systemCode
     */
    public String getSystemCode() {
        return systemCode;
    }

    /**
     * @param systemCode the systemCode to set
     */
    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    
}
