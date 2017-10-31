package com.upsoft.systemweb.support;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.system.constant.SystemLogConstant;
import com.upsoft.system.entity.SysOrgEntity;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.entity.SystemTLog;
import com.upsoft.systemweb.service.OrgService;
import com.upsoft.systemweb.service.SystemLogService;

/**
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：SysLogCache.java<br>
* 摘要：操作日志缓存类<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：陈涛<br>
* 完成日期：2017年3月9日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：陈涛<br>
* 完成日期：2017年3月9日<br>
*/
@Service
public class SystemLogServiceCache {

//	private static Map<String,List<SystemTLog>> logarr = new  HashMap<String,List<SystemTLog>>();
//	private static List<SystemTLog> logarr = new  ArrayList<SystemTLog>();


	@Autowired
	private SystemLogService systemLogService;
//	
	@Autowired
	private OrgService orgService;
	
	/**
	 * 存储日志信息
	 * 
	 * @date 2017年3月9日 下午1:58:03
	 * @author 陈涛
	 * @ 还需要的数据字段包括user,请求url,类名、方法名、模块类型编码、模块类型名称、操作类型编码、操作类型名称、操作内容,模块所属系统编码，模块所属系统名称
	 */
	
	public void cacheLog(SysUser user,String requestUrl,String className,String methodName,
			String optModelCode,String optModelName,String optTypeCode,String optTypeName,String optContent,String belongSystemCode,String belongSystemName){
		try {
			SystemTLog systemTLog = new SystemTLog();
			systemTLog.setActionclassname(className);
			systemTLog.setActionfunctionname(methodName);
			systemTLog.setModelcode(optModelCode);
			systemTLog.setModelname(optModelName);
			systemTLog.setOpttypecode(optTypeCode);
			systemTLog.setOpttypename(optTypeName);
			systemTLog.setOptcontent(optContent);
			systemTLog.setBelongsystemcode(belongSystemCode);
			systemTLog.setBelongsystemname(belongSystemName);
			if(null != user){
				systemTLog.setOptuserid(user.getUserId());
				systemTLog.setOptusername(user.getUserName());
				String optUnitName = "";
				if(StringUtils.isNoneBlank(user.getOrgId()))
				{
					SysOrgEntity sysOrgEntity = orgService.findOneById(user.getOrgId());
					optUnitName = sysOrgEntity.getOrgName();
				}
				systemTLog.setOptunitid(user.getOrgId());
				systemTLog.setOptunitname(optUnitName);
			}
			systemTLog.setOpttime(DateFormatUtils.format(new Date(),"yyyyMMddHHmmss"));
			systemTLog.setRequesturi(requestUrl);
			systemTLog.setFlag(SystemLogConstant.IS_VALID);
			
			List<SystemTLog> log = new  ArrayList<SystemTLog>();
			log.add(systemTLog);
			systemLogService.saveSystemLog(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		SystemLogServiceCache.putLog(systemTLog);
	}

}
