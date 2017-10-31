package com.upsoft.login.listener;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.upsoft.system.util.PropertiesUtil;

/**
* Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：ConfigListener.java<br>
* 摘要：获取配置文件中的配置信息<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：吴炫<br>
* 完成日期：2015年2月10日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：吴炫<br>
* 完成日期：2015年2月10日<br>
 */
public class ConfigListener implements ServletContextListener {
	// 加载properties-cas总的配置信息
	public static Properties CasConfig = null;
	public static Properties CommConfig = null;

	public final static Logger logger = Logger.getLogger(ConfigListener.class);
	
    public ConfigListener() {}

	/**
	 * 上下文初始化的时候读取配置文件
	 */
    public void contextInitialized(ServletContextEvent sce) {
    	initCommonConfig();
    	initCasConifg();
    }

    private void initCommonConfig() {
    	CommConfig = PropertiesUtil.getCommonCfgProperties();
//    	sce.getServletContext().setAttribute(CommonConstant.KEY_FOR_SYS_CONFIG, CommConfig);
	}

	public void initCasConifg(){
		if(CasConfig==null){
			Resource resource = new ClassPathResource("/application-cas.properties");
			try {
				CasConfig = PropertiesLoaderUtils.loadProperties(resource);
				
			} catch (IOException e) {
				logger.error("项目启动时加载配置文件出错===>"+e.getMessage());
			}
		}
	}
    
    /**
	 * 上下文销毁的时候清空配置信息
	 */
    public void contextDestroyed(ServletContextEvent sce) {
//    	sce.getServletContext().removeAttribute(CommonConstant.KEY_FOR_SYS_CONFIG);
    	CasConfig = null;
    	CommConfig = null;
    }
	
}
