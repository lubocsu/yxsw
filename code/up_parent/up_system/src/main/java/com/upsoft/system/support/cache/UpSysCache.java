/*
 * UpSysCache.java
 * Created on 2015年1月22日 下午2:58:25
 * Copyright (c) 重庆扬讯软件技术有限公司  All Rights Reserved.
 * http://www.upsoft.com.cn
 *
 * This software is the confidential and proprietary information of UPSoft.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with UPSoft.
 */
package com.upsoft.system.support.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：UpSysCache.java<br>
 * 摘要：系统换成临时解决方案，后续替换成redis<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：蒋迪<br>
 * 完成日期：2015年1月22日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：蒋迪<br>
 * 完成日期：2015年1月22日<br>
 */
//TODO JiangDi 替换成redis缓存
public class UpSysCache {
	/**
	 * key-value(tokenId-用户信息)
	 */
	private static Map<String, Object> cache = new HashMap<String, Object>();
	/**
	 * key-value(loginId-tokenId) 用于验证当前用户是否已经登录，是，这删除之前的token及用户信息重新生成toenId
	 */
	private static Map<String, Object> cache2 = new HashMap<String, Object>();

	/**
	 * 设置缓存
	 * @date 2015年1月22日 下午3:00:32
	 * @author 蒋迪
	 * @param key		缓存key
	 * @param value 	缓存value
	 */
	public static void setCache(String key, Object value){
		cache.put(key, value);
	}
	
	/**
	 * 读取缓存
	 * @date 2015年1月22日 下午3:00:48
	 * @author 蒋迪
	 * @param key	缓存key
	 * @return 
	 */
	public static Object getCache(String key){
		return cache.get(key);
	}
	
	public static void removeCache(String key){
		cache.remove(key);
	}
	
	public static void setCache2(String key, Object value){
		cache2.put(key, value);
	}
	public static Object getCache2(String key){
		return cache2.get(key);
	}
	public static Map<String, Object> getCache2(){
		return cache2;
	}
	
	public static void removeCache2(String key){
		cache2.remove(key);
	}
}
