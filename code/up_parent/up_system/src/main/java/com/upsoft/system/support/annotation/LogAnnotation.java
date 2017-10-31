package com.upsoft.system.support.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 
* Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：LogAnnotation.java<br>
* 摘要：描述实体属性含义的自定义注解<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：冉恒鑫<br>
* 完成日期：2015年3月24日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：冉恒鑫<br>
* 完成日期：2015年3月24日<br>
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {
	
	/**
	 * 传递功能名字，由程序员定义
	 * @date 2015年3月24日 上午10:15:24
	 * @author 冉恒鑫
	 * @param String，例如"删除用户"
	 */
	String FunctionName();
	
}
