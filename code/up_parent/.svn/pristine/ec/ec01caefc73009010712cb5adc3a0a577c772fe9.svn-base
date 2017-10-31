package com.upsoft.login.support.webservice;

import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.WebUtils;

import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.constant.CommonConstant;
import com.upsoft.system.entity.SysUser;

/**
* Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：SysUtils.java<br>
* 摘要：系统工具类<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：吴炫<br>
* 完成日期：2015年1月21日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：吴炫<br>
* 完成日期：2015年1月21日<br>
 */
public class SysUtils {
	/**
	 * 随机生成UUID
	 * @return
	 * @author： WX
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().toUpperCase(Locale.getDefault()).replaceAll("-", "");
	}
	
	/**
	 * 获取登陆用户信息
	 * @date 2015年4月15日 下午3:22:12
	 * @author Awesan
	 * @param request
	 * @return 
	 */
	public static SysUser getLoginSysUser(HttpServletRequest request){
		WSLoginInfoBean logInfo = getLoginInfo(request);
		return logInfo.getUser();
	}
 
//	/**
//	 * 获取登陆信息
//	 * 有request则取session中的，无request则取Threadlocal中的
//	 * @date 2015年9月2日 下午5:05:23
//	 * @author null
//	 * @param request
//	 * @return 
//	 */
	public static WSLoginInfoBean getLoginInfo(HttpServletRequest request){
		WSLoginInfoBean logInfo = ((WSLoginInfoBean) request.getSession().getAttribute(CommonConstant.KEY_FOR_SYSUSER));
		if(null==logInfo){
			String tokenId = WebUtils.findParameterValue(request, CommonConstant.TOKEN_ID);
			if(StringUtils.isNotBlank(tokenId)){
				logInfo = ServiceReceiver.getLoginInfoByToken(tokenId);
				if(null!=logInfo){
					 request.getSession().setAttribute(CommonConstant.KEY_FOR_SYSUSER,logInfo);
				}
			}
		}
		return logInfo;
	}
}
