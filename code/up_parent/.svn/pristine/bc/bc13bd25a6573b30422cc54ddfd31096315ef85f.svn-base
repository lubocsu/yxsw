package com.upsoft.yxsw.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.upsoft.yxsw.service.HistoryCxMakeService;


@Service("historyCxMakeService")
public class HistoryCxMakeServiceImpl implements HistoryCxMakeService {

	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询指定厂站特定指标项的历史数据
	 * @param args 参数数组 依次是厂站ID,指标项ID，作业票模板项ID 开始时间  结束时间
	 * @param sql 要查询的sql信息
	 * @return 查询结果集
	 */
	public List<Map<String, Object>> queryZbxHis(Object args[],String sql){
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql.toString(), args);
		
		return list;
	}
	
	/**
	 * 根据SQL与参数查询计算数据 
	 * @param args 参数数组 依次是厂站ID,指标项ID，作业票模板项ID 开始时间  结束时间
	 * @param sql 要查询的SQL信息
	 * @return 查询结果集
	 */
	public Map<String, Object> getCalZbxHis(Object args[],String sql){
		Map<String, Object> map = jdbcTemplate.queryForMap(sql, args);
		
		return map;
	}
	
	/**
	 * 获取指标项名称
	 * @param zbxid 指标项ID
	 * @return
	 */
	public String getZbxName(String zbxid){
		String sql = "SELECT CXZB_NAME FROM BIZ_T_XJ_ZB_ITEM WHERE CXZB_ID = ? ";
		return jdbcTemplate.queryForObject(sql, new Object[]{zbxid}, String.class);
	}
}
