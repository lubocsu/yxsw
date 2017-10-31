package com.upsoft.systemweb.aspect;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.upsoft.login.support.webservice.SysUtils;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.constant.CommonConstant;
import com.upsoft.system.entity.SysLogEntity;
import com.upsoft.system.support.annotation.LogAnnotation;
import com.upsoft.systemweb.service.SysLogService;

/**
 * 
* Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：LogAspect.java<br>
* 摘要：日志切面<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：冉恒鑫<br>
* 完成日期：2015年3月24日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：冉恒鑫<br>
* 完成日期：2015年3月24日<br>
 */

@Aspect
@Component
public class LogAspect {
	
	//日志类型常量(1：系统登录；2：功能访问；3：数据变更)
//	private static final int SYSTEM_LOGIN = 1;
	private static final int FUNCTION_VISIT = 2;
//	private static final int DATA_CHANGE = 3;
	private final long defaultDwellTime = 0; 
	
	@Autowired
	private SysLogService sysLogService;
	
	/**
	 * 定义切入点,拦截所有使用了LogAnnotation注解的方法，建议在controller层使用
	 * @date 2015年3月24日 下午5:55:55
	 * @author 冉恒鑫
	 */
	@Pointcut("@annotation(com.upsoft.system.support.annotation.LogAnnotation)")
	public void controllerAspect(){	}
	
	/**
	 * 前置通知 用于拦截Controller层标有LogAnnotation注解的方法并记录用户的操作  
	 * @date 2015年3月24日 上午10:57:20
	 * @author 冉恒鑫
	 */
	@Before("controllerAspect()")
	public void doBefore(JoinPoint jp){
		try {
			checkUserAndSystemCode(jp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private synchronized void checkUserAndSystemCode(JoinPoint jp) throws Exception{
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		String userId = loginInfo.getUser().getUserId();
		String systemCode = request.getContextPath().substring(1, request.getContextPath().length());
		Map<String, Object> pars = new HashMap<String, Object>();
		pars.put("userId", userId);
		pars.put("systemCode", systemCode);
		SysLogEntity previousLog = sysLogService.getLogByUserIdAndSystemCode(pars);
		if (previousLog != null ) {
			if (userId.equals(previousLog.getUserId()) && systemCode.equals(previousLog.getSystemCode()) &&previousLog.getLogType() ==  FUNCTION_VISIT) {
				saveNowLog(jp, loginInfo);
				updatePreviousLogDwellTime(previousLog);
			}else{
				saveNowLog(jp,loginInfo);
			}
		}else{
			saveNowLog(jp,loginInfo);
		}
		
		
	}
	/**
	 * 更改上一次日志记录的停留时间
	 * @date 2015年3月25日 上午11:46:30
	 * @author 冉恒鑫
	 * @param previousLog
	 */
	private void updatePreviousLogDwellTime(SysLogEntity previousLog){
		Date previousDate = previousLog.getLogTime();
		Date now = new Date();
		long dwellTime = (now.getTime() - previousDate.getTime()) / 1000 ;
		if (dwellTime < 0) {
			dwellTime = 0;
		}
		if (dwellTime > 900) {
			dwellTime = 900;
		}
		sysLogService.updatePreviousLog(dwellTime, previousLog.getLogId());
	}
	
	/**
	 * 保存当前操作日志
	 * @date 2015年3月25日 上午11:46:48
	 * @author 冉恒鑫
	 * @param jp
	 * @param loginInfo
	 * @throws Exception
	 */
	private void saveNowLog(JoinPoint jp,WSLoginInfoBean loginInfo) throws Exception{
		SysLogEntity log = new SysLogEntity();
		Date now = new Date();
		log.setUserId(loginInfo.getUser().getUserId());
		log.setFunctionName(getFunctionName(jp));
		log.setLogType(FUNCTION_VISIT);
		log.setLogTime(now);
		log.setDwellTime(defaultDwellTime);
		sysLogService.save(log);
	}
	
	
	
	
	
	
	/**
	 * 私有方法，获取被拦截方法及具体参数组装成操作内容返回
	 * @date 2015年3月24日 下午2:54:32
	 * @author 冉恒鑫
	 * @param JoinPoint
	 * @return
	 */
	private String getFunctionName(JoinPoint jp) throws Exception{
		String methodName = jp.getSignature().getName();
		Class<? extends Object> targetClass = jp.getTarget().getClass();
		Method[] methods = targetClass.getDeclaredMethods();
		String content = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
					content = method.getAnnotation(LogAnnotation.class).FunctionName();
					break;
			}
		}
		return content;
	}
}
