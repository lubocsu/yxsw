package com.upsoft.system.util;

import javax.servlet.http.HttpServletRequest;


public class CommonUtils {
	
	/**
	 * 通过request获取服务器webapps的绝对路径地址
	 * @date 2017年5月5日 下午3:56:16
	 * @author 胡毅
	 * @param request
	 * @return %TOMCAT_HOME%\webapps
	 */
	public static String getWebappsURLPath(HttpServletRequest request){
		return "http://" + request.getServerName() + ":" + request.getServerPort();
	}
}
