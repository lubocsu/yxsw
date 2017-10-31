package com.upsoft.systemweb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.system.entity.SysLogEntity;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.systemweb.dao.SysLogDao;
import com.upsoft.systemweb.service.SysLogService;

/**
 * 
* Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：SysLogServiceImpl.java<br>
* 摘要：系统日志接口实现<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：冉恒鑫<br>
* 完成日期：2015年3月25日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：冉恒鑫<br>
* 完成日期：2015年3月25日<br>
 */
@Service
public class SysLogServiceImpl extends BaseServiceImpl implements SysLogService{

	@Autowired
	private SysLogDao sysLogDao;
	
	
	@Override
	public Long getLoginCount(String systemName) {
		String sql = 	"select count(1) from sys_log l\n" +
						"left join sys_system_define sd on sd.systemcode = l.systemcode\n" + 
						"where logType = 1 and sd.systemname = '" + systemName + "'";
		Map<String, Object> pars = null;
		Long result = sysLogDao.queryCountBySql(sql, pars);
		return result;
	}

	@Override
	public SysLogEntity getLogByUserIdAndSystemCode(Map<String, Object> pars) {
		String sql = "select log.* from sys_log log where log.userId = :userId and log.systemCode = :systemCode order by log.logTime desc";
		List<SysLogEntity> logList = sysLogDao.queryListBySql(sql, pars, SysLogEntity.class);
		return logList.size() > 0 ? logList.get(0) : null;
	}

	@Override
	public List<Map<String, Object>> getLoginMaxCountAndTime(String systemName) {
		String sql = 	"select count(1) as logincount, to_char(logtime, 'yyyy-MM-dd') as logtime\n" +
						"from sys_log l left join sys_system_define sd on sd.systemcode = l.systemcode\n" + 
						"where logtype = 1 and sd.systemname = :systemName \n" + 
						"group by to_char(logtime, 'yyyy-MM-dd')\n" + 
						"order by logincount desc";
		Map<String, Object> pars = new HashMap<String, Object>();
		pars.put("systemName", systemName);
		List<Map<String, Object>> result = sysLogDao.queryListBySql(sql, pars);
		return result;
	}

	@Override
	public Long getDwellTime(String systemName) {
		String sql = 	"select sum(dwellTime) from sys_log l\n" +
						"left join sys_system_define sd on sd.systemcode = l.systemcode\n" + 
						"where logtype = 2 and sd.systemname = '" + systemName + "'";
		Map<String, Object> pars = null;
		Long result = sysLogDao.queryCountBySql(sql, pars);
		return result;
	}

	@Override
	public List<Map<String, Object>> getFunctionNameList() {
		String sql = "select functionName from sys_log where logtype = 2 group by functionName";
		Map<String, Object> pars = null;
		List<Map<String, Object>> functionNameList = sysLogDao.queryListBySql(sql, pars);
		return functionNameList;
	}

	@Override
	public List<Map<String, Object>> initLine(String systemName) {
		String sql = 	"select * from (select to_char(logtime, 'yyyy-MM') as logtime,\n" +
						"count(1) as logincount from sys_log l\n" + 
						"left join sys_system_define sd on sd.systemcode = l.systemcode\n" + 
						"where logtype = 1 and sd.systemname = :systemName \n" + 
						"group by to_char(logtime, 'yyyy-MM')\n" + 
						"order by logtime desc) tmp\n" + 
						"where rownum < 13";
		Map<String, Object> pars = new HashMap<String, Object>();
		pars.put("systemName", systemName);
		List<Map<String, Object>> monthLoginCount = sysLogDao.queryListBySql(sql, pars);
		return monthLoginCount;
	}

	@Override
	public Long getFunctionNameCount(String systemName) {
		String sql  = 	"select count(1) from sys_log l\n" +
						"left join sys_system_define sd on sd.systemcode = l.systemcode\n" + 
						"where logtype = 2 and sd.systemname = '" + systemName + "'";
		Map<String, Object> pars = null;
		Long result = sysLogDao.queryCountBySql(sql, pars);
		return result;
	}

	@Override
	public List<Map<String, Object>> initColumn(String systemName) {
		String sql = 	"select count(1) as count,functionname from sys_log l\n" +
						"left join sys_system_define sd on sd.systemcode = l.systemcode\n" + 
						"where sd.systemname = :systemName and l.logtype = 2\n" + 
						"group by functionname";
		Map<String, Object> pars = new HashMap<String, Object>();
		pars.put("systemName", systemName);
		List<Map<String, Object>> functionNameAndCount = sysLogDao.queryListBySql(sql, pars);
		pars.clear();
		return functionNameAndCount;
	}

	@Override
	public List<Map<String, Object>> initPie(String systemName) {
		String sql = 	"select sum(dwelltime) as dwelltime ,functionname from sys_log l\n" +
						"left join sys_system_define sd on sd.systemcode = l.systemcode\n" + 
						"where sd.systemname = :systemName and logtype = 2\n" + 
						"group by functionname order by dwelltime desc";
		Map<String, Object> pars = new HashMap<String, Object>();
		pars.put("systemName", systemName);
		List<Map<String, Object>> functionNameAndDwell = sysLogDao.queryListBySql(sql, pars);
		pars.clear();
		return functionNameAndDwell;
	}

	@Override
	public Map<String, Object> getDwellTImeByFunctionName(String systemName,String functionName) {
		String sql = 	"select tmp.dwelltime, tmp.functionname, tmp.count\n" +
						"from (select count(1) as count, sum(dwelltime) as dwelltime, functionname\n" + 
						"from sys_log l left join sys_system_define sd on sd.systemcode = l.systemcode\n" + 
						"where logtype = 2 and sd.systemname = :systemName\n" + 
						"group by functionname) tmp\n" + 
						"where tmp.functionname = :functionName";
		Map<String, Object> pars = new HashMap<String, Object>();
		pars.put("systemName", systemName);
		pars.put("functionName", functionName);
		List<Map<String, Object>> dwellTimeByFunctionName = sysLogDao.queryListBySql(sql, pars);
		pars.clear();
		return dwellTimeByFunctionName.size() > 0 ? dwellTimeByFunctionName.get(0) : null;
	}

	@Override
	public List<Map<String, Object>> getSystemName() {
		String sql = 	"select l.systemcode,sd.systemname from sys_log l\n" +
						"left join sys_system_define sd on sd.systemcode = l.systemcode\n" + 
						"group by l.systemcode,sd.systemname";
		Map<String, Object> pars = null;
		return sysLogDao.queryListBySql(sql, pars);
	}

	@Override
	public List<Map<String, Object>> getFunctionBySystemCode(String systemCode) {
		String sql = "select functionname,systemcode from sys_log where systemcode = :systemCode and logtype <> 1 group by functionname,systemcode";
		Map<String, Object> pars = new HashMap<String, Object>();
		pars.put("systemCode", systemCode);
		return sysLogDao.queryListBySql(sql, pars);
	}

	@Override
	public Long updatePreviousLog(Long dwellTime,String logId) {
		String sql = "update sys_log set dwellTime = :dwellTime where logId = :logId";
		Map<String, Object> pars = new HashMap<String, Object>();
		pars.put("dwellTime", dwellTime);
		pars.put("logId", logId);
		Long result = (long) sysLogDao.executeSql(sql, pars);
		return result;
	}
	
}
