package com.upsoft.systemweb.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.entity.SysLogEntity;
import com.upsoft.system.service.BaseService;

/**
 * 
* Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：SysLogService.java<br>
* 摘要：系统日志接口<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：冉恒鑫<br>
* 完成日期：2015年3月25日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：冉恒鑫<br>
* 完成日期：2015年3月25日<br>
 */
public interface SysLogService extends BaseService{
	
	/**
	 * 根据userId和systemCode查询得到sysLog实体
	 * @date 2015年3月25日 下午4:14:00
	 * @author 冉恒鑫
	 * @param pars
	 * @return
	 */
	public SysLogEntity getLogByUserIdAndSystemCode(Map<String, Object> pars);
	
	/**
	 * 根据系统名字取得系统登录总次数
	 * @date 2015年3月26日 下午7:57:58
	 * @author 冉恒鑫
	 * @return
	 */
	public Long getLoginCount(String TreeNode);
	
	/**
	 * 取得系统登录次数最多的某天及次数
	 * @date 2015年3月26日 下午8:20:46
	 * @author 冉恒鑫
	 * @return
	 */
	public List<Map<String, Object>> getLoginMaxCountAndTime(String TreeNode);
	
	/**
	 * 取得所有功能点名称
	 * @date 2015年3月27日 上午9:54:31
	 * @author 冉恒鑫
	 * @return
	 */
	public List<Map<String, Object>> getFunctionNameList();
	
	/**
	 * 根据系统名字取得所有功能点的总停留时间
	 * @date 2015年3月27日 上午9:56:19
	 * @author 冉恒鑫
	 * @return
	 */
	public Long getDwellTime(String TreeNode);
	
	/**
	 * 根据系统名字获得最近十二月每月登录系统总次数
	 * @date 2015年3月27日 上午9:58:54
	 * @author 冉恒鑫
	 * @return
	 */
	public List<Map<String, Object>> initLine(String TreeNode);
	
	/**
	 * 获得功能使用总次数
	 * @date 2015年3月27日 上午10:01:30
	 * @author 冉恒鑫
	 * @return
	 */
	public Long getFunctionNameCount(String TreeNode);
	
	/**
	 * 根据系统名字获得每个功能点的使用总次数
	 * @date 2015年3月27日 上午10:08:47
	 * @author 冉恒鑫
	 * @return
	 */
	public List<Map<String, Object>> initColumn(String TreeNode);
	
	/**
	 * 根据系统名字获得每个功能点的总停留时间
	 * @date 2015年3月27日 上午10:09:46
	 * @author 冉恒鑫
	 * @return
	 */
	public List<Map<String, Object>> initPie(String TreeNode);
	
	/**
	 *  获得单独一个功能点的总停留时间
	 * @date 2015年3月30日 下午5:07:41
	 * @author 冉恒鑫
	 * @param functionName
	 * @return
	 */
	public Map<String, Object> getDwellTImeByFunctionName(String systemName,String functionName);
	
	/**
	 * 初始化系统名字树
	 * @date 2015年4月23日 上午11:39:44
	 * @author 冉恒鑫
	 * @return
	 */
	public List<Map<String, Object>> getSystemName();
	/**
	 * 根据systemcode获得不同系统的功能点作为树根
	 * @date 2015年4月23日 下午7:43:44
	 * @author 冉恒鑫
	 * @param systemCode
	 * @return
	 */
	public List<Map<String, Object>> getFunctionBySystemCode(String TreeNode);
	
	public Long updatePreviousLog(Long dwellTime,String logId);

}
