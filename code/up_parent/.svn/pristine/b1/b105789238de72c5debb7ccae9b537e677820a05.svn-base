package com.upsoft.systemweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.system.entity.SystemTLog;
import com.upsoft.systemweb.dao.SystemLogDao;
import com.upsoft.systemweb.service.SystemLogService;

@Service
public class SystemLogServiceImpl implements SystemLogService{

	@Autowired
	private SystemLogDao systemLogDao;
	
	@Override
	public void saveSystemLog(List<SystemTLog> systemTLogs) {
		for(SystemTLog systemTLog : systemTLogs){
			systemLogDao.save(systemTLog);
		}
	}

}
