package com.upsoft.yxsw.service;

import java.util.List;
import java.util.Map;


public interface HistoryCxMakeService {
	/**
	 * 查询指定厂站特定指标项的历史数据
	 * @param args 参数数组 依次是厂站ID,指标项ID，作业票模板项ID 开始时间  结束时间
	 * @param sql 要查询的sql信息
	 * @return 查询结果集
	 */
	public List<Map<String, Object>> queryZbxHis(Object args[],String sql);
	
	/**
	 * 根据SQL与参数查询计算数据 
	 * @param args 参数数组 依次是厂站ID,指标项ID，作业票模板项ID 开始时间  结束时间
	 * @param sql 要查询的SQL信息
	 * @return 查询结果集
	 */
	public Map<String, Object> getCalZbxHis(Object args[],String sql);
	
	/**
	 * 获取指标项名称
	 * @param zbxid 指标项ID
	 * @return
	 */
	public String getZbxName(String zbxid);
}
