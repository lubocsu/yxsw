/*
 * UpExcelAnnotation.java
 * Created on 2015年1月29日 下午3:44:35
 * Copyright (c) 重庆扬讯软件技术有限公司  All Rights Reserved.
 * http://www.upsoft.com.cn
 *
 * This software is the confidential and proprietary information of UPSoft.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with UPSoft.
 */
package com.upsoft.system.support.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：UpExcelAnnotation.java<br>
 * 摘要：描述实体属性含义的Annotation，设计用于Excel导入时，将属性和表头进行对应<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：蒋迪<br>
 * 完成日期：2015年1月29日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：蒋迪<br>
 * 完成日期：2015年1月29日<br>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelAnnotation {
	//Excel表头名称
	String label();
	//数据字典id
	String dictCode() default "";
	/**
	 * 例如：
	 * @Column(name = "OrgTypeId")
	 * @ExcelAnnotation(label = "机构类型", dictCode = "4028800a4b2a645d014b2a69c2740000")
	 * private String orgTypeId;
	 */
}
