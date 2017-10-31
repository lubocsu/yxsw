package com.upsoft.system.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
* Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：PropertiesReader.java<br>
* 摘要：配置文件读取工具<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：Awesan<br>
* 完成日期：2015年2月6日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：Awesan<br>
* 完成日期：2015年2月6日<br>
*/
public class PropertiesUtil {
	private static String defaultFileName = "application.properties";
	private static Logger logger = Logger.getLogger(PropertiesUtil.class);
	
	/**
	 * 返回tomcat根路径
	 * @date 2015年4月28日 下午3:32:24
	 * @author null
	 * @return 
	 */
	public static String getTomcatHome(){
		String home = System.getProperty("catalina.base");
		return home;
	}
	
	/**
	 * 获取webapps目录下的common-config.properties文件，取得公共配置管理里文件
	 * @date 2016年9月21日 上午11:01:49
	 * @author 胡毅
	 * @return
	 */
	public static Properties getCommonCfgProperties(){
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(System.getProperty("catalina.home")+"/webapps/common-config.properties"));
		} catch (FileNotFoundException e1) {
			logger.error("未找到common-config配置文件");
		} catch (IOException e1) {
			logger.error("未找到common-config配置文件");
		}
		return p;
	}
	
	/**
	 * 读取class路径下application.properties 文件
	 * @date 2015年2月6日 上午11:08:09
	 * @author Awesan
	 * @param key 属性名
	 * @return 
	 */
	public static String getProperties(String key) {
		String propertyValue = null;
		Properties properties = new Properties();
		try {
			InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream(defaultFileName);
			properties.load(is);
			propertyValue = properties.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return propertyValue;
	}
	
	/**
	 * 读取class路径下，自定义文件名
	 * @date 2015年2月6日 上午11:10:23
	 * @author Awesan
	 * @param key		属性名
	 * @param fileName	配置文件名
	 * @return 
	 */
	public static String getProperties(String key, String fileName){
		defaultFileName = fileName;
		return getProperties(key);
	}
	
	/**
	 * 自定义路径
	 * @date 2015年2月6日 上午11:13:28
	 * @author Awesan
	 * @param key		属性名
	 * @param filePath	路径名（定位到配置文件）
	 * @return 
	 */
	public static String getPropertiesInAnyPath(String key, String filePath){
		String propertyValue = null;
		Properties properties = new Properties();
		try {
			InputStream is = new FileInputStream(new File(filePath));
			properties.load(is);
			propertyValue = properties.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return propertyValue;
	}
	
	/**
	 * PropertiesLoaderUtils 读取
	 * 实时加载配置文件，修改后立即生效，不必重启 
	 * 只能读取classes下
	 * @date 2015年2月6日 下午1:48:04
	 * @author Awesan
	 * @param key 		属性名
	 * @param fileName 	文件名
	 * @return 
	 */
	public static String getPropertyBySpring(String key, String fileName){
		String propertyValue = null;
		Properties props = new Properties();  
		try {  
		    props = PropertiesLoaderUtils.loadAllProperties(fileName);  
		    if(props.get(key) != null){
		    	propertyValue = (String) props.get(key);
		    }
		} catch (IOException e) {  
		    e.printStackTrace();  
		}  
		return propertyValue;
	}
	
	/**
	 * ResourceBundle 方式读取
	 * 只能读取.properties文件
	 * @date 2015年2月6日 下午12:00:28
	 * @author Awesan
	 * @param key		属性名	
	 * @param filePath	文件名
	 * @return 
	 */
	public static String getPropertyByBundle(String key, String filePath) {
		String propertyValue = null;
	    // 获得资源包  
		// 1、resource源包下，不用加后缀 如：application.properties --> application
		// 2、放在包里面的  com.test.application
	    ResourceBundle rb = ResourceBundle.getBundle(filePath.trim());  
	    // 通过资源包拿到所有的key  
	    Enumeration<String> allKey = rb.getKeys();  
	    while (allKey.hasMoreElements()) {
	    	String keyName = allKey.nextElement();
	        if(keyName.equals(key)){
	        	propertyValue = (String) rb.getString(keyName);
	        }
	    }  
	    return propertyValue;  
	}  
	
}
