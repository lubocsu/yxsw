package com.upsoft.systemweb.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.constant.CommonConstant.QueryType;
import com.upsoft.system.entity.DictTreeDataEntity;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.systemweb.dao.DictTreeDataDao;
import com.upsoft.systemweb.service.DictTreeDataService;

/**
* Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：DictTreeDataServiceImpl.java<br>
* 摘要：数据字典节点服务层实现类<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：Awesan<br>
* 完成日期：2015年1月22日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：Awesan<br>
* 完成日期：2015年1月22日<br>
*/
@Service
@SuppressWarnings("unchecked")
public class DictTreeDataServiceImpl extends BaseServiceImpl implements DictTreeDataService{

	@Autowired
	private DictTreeDataDao dictTreeDataDao;

	@Override
	public List<DictTreeDataEntity> queryDictTreeDataByPage(int pageNo,
			int pageSize, Map<String, Object> pars) {
		Object[] obj = getQuerySQL(pars, QueryType.LIST);
		List<DictTreeDataEntity> datas = dictTreeDataDao.queryListBySql(obj[0].toString(), pageNo, pageSize, (Map<String, Object>) obj[1], DictTreeDataEntity.class);
		return datas;
	}
	
	/**
	 * 拼装查询sql
	 * @date 2015年1月22日 下午3:59:14
	 * @author Awesan
	 * @param pars
	 * @param list
	 * @return 
	 */
	private Object[] getQuerySQL(Map<String, Object> pars, QueryType list) {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> conditions = new HashMap<String, Object>();
		switch (list) {
			case LIST:
				sql.append("select * from sys_dict_tree_data dtd where 1=1 ");
				break;
			case COUNT:
				sql.append("select count(0) from sys_dict_tree_data dtd where 1=1 ");
				break;
			default:
				break;
		}
		if(pars != null && pars.size() > 0){
			if(pars.containsKey("DictCode")){
				conditions.put("DictCode", pars.get("DictCode"));
				sql.append("and dtd.code = :DictCode ");
			}
			if(pars.containsKey("DictName")){
				conditions.put("DictName", pars.get("DictName"));
				sql.append("and dtd.data1 = :DictName ");
			}
		}
		sql.append("start with dtd.parentnodeid in (select dt.treeid from sys_dict_tree dt where 1=1 ");
		if(pars != null && pars.size() > 0){
			if(pars.containsKey("treeId")){
				conditions.put("treeId", pars.get("treeId"));
				sql.append("and dt.treeid = :treeId ");
			}
			if(pars.containsKey("treeDesc")){
				conditions.put("treeDesc", pars.get("treeDesc"));
				sql.append("and dt.treedescription = :treeDesc ");
			}
		}
		sql.append(") connect by dtd.parentnodeid=prior dtd.nodeid ");
		
		if(pars != null && pars.size() > 0){
			if(pars.containsKey("sort")){
				sql.append("order by dtd.").append(pars.get("sort")).append(" ");
			}else{
				sql.append("order by dtd.parentnodeid, dtd.orderno asc ");
			}
			if(pars.containsKey("direction")){
				sql.append(pars.get("direction"));
			}
		}
		
		return new Object[] { sql, conditions };
	}

	@Override
	public Long queryDictDataCount(Map<String, Object> pars) {
		Object[] obj = getQuerySQL(pars, QueryType.COUNT);
		long counts = dictTreeDataDao.queryCountBySql(obj[0].toString(), (Map<String, Object>) obj[1]);
		return counts;
	}

	public DictTreeDataDao getDao() {
		return dictTreeDataDao;
	}

	@Override
	public List<DictTreeDataEntity> queryDataByTreeId(String id) {
		if(StringUtils.isNotBlank(id)){
			Map<String, Object> conditions = new HashMap<String, Object>();
			conditions.put("treeId", id);
			List<DictTreeDataEntity> datas = queryDictTreeDataByPage(0, 0, conditions);
			return datas;
		}
		return null;
	}

	public Long queryMaxOrderNoByTreeId(String treeId) {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("treeId", treeId);
		StringBuffer sql = new StringBuffer();
		sql.append("select max(dtd.orderno) as max from sys_dict_tree_data dtd ");
		sql.append("start with dtd.parentnodeid = :treeId ");
		sql.append("connect by dtd.parentnodeid = prior dtd.nodeid ");
		List<Map<String, Object>> result = dictTreeDataDao.queryListBySql(sql.toString(), conditions);
		BigDecimal bigMax = (BigDecimal)result.get(0).get("max");
		//查询结构为空则返回0
		Long max = (bigMax == null ? 0 : bigMax.longValue());
		return max;
	}

	@Override
	public DictTreeDataEntity queryByTreeIdAndNodeCode(String treeId, String nodeCode) {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("treeId", treeId);
		conditions.put("code", nodeCode);
		StringBuffer sql = new StringBuffer();
		sql.append("select * from sys_dict_tree_data dtd ");
		sql.append("where dtd.parentnodeid=:treeId and dtd.code=:code ");
		List<DictTreeDataEntity> dictNodes = dictTreeDataDao.queryListBySql(sql.toString(), conditions, DictTreeDataEntity.class);
		if(dictNodes != null && dictNodes.size() > 0){
			if(dictNodes.size() == 1){
				return dictNodes.get(0);
			}
		}
		return null;
	}
	
	public DictTreeDataEntity queryByTreeIdAndData1(String treeId, String data1) {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("treeId", treeId);
		conditions.put("data1", data1);
		StringBuffer sql = new StringBuffer();
		sql.append("select * from sys_dict_tree_data dtd ");
		sql.append("where dtd.parentnodeid=:treeId and dtd.data1=:data1 ");
		List<DictTreeDataEntity> dictNodes = dictTreeDataDao.queryListBySql(sql.toString(), conditions, DictTreeDataEntity.class);
		if(dictNodes != null && dictNodes.size() > 0){
			if(dictNodes.size() == 1){
				return dictNodes.get(0);
			}
		}
		return null;
	}
	
	public DictTreeDataEntity queryByParentIdAndNodeCode(String parentId, String nodeCode) {
		return queryByTreeIdAndNodeCode(parentId, nodeCode);
	}

	@Override
	public boolean deleteByIds(String[] ids) {
		try {
			for(String id:ids){
				DictTreeDataEntity parent = findOne(DictTreeDataEntity.class, id);
				List<DictTreeDataEntity> children = queryDataByParentId(id);
				//子节点加上父节点
				if(parent != null){
					children.add(parent);
				}
				dictTreeDataDao.delete(children);
				//dictTreeDataDao.delete(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<DictTreeDataEntity> queryDataByParentId(String pId) {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("pId", pId);
		StringBuffer sql = new StringBuffer();
		sql.append("select * from sys_dict_tree_data dtd ");
		sql.append("start with dtd.parentnodeid = :pId ");
		sql.append("connect by dtd.parentnodeid=prior dtd.nodeid ");
		sql.append("order by dtd.orderno asc ");
		List<DictTreeDataEntity> result = dictTreeDataDao.queryListBySql(sql.toString(), conditions, DictTreeDataEntity.class);
		return result;
	}
	
	public List<DictTreeDataEntity> queryDataByNodeId(String nodeId) {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("nodeId", nodeId);
		StringBuffer sql = new StringBuffer();
		sql.append("select * from sys_dict_tree_data dtd ");
		sql.append("start with dtd.nodeid = :nodeId ");
		sql.append("connect by dtd.parentnodeid=prior dtd.nodeid ");
		sql.append("order by dtd.orderno asc ");
		List<DictTreeDataEntity> result = dictTreeDataDao.queryListBySql(sql.toString(), conditions, DictTreeDataEntity.class);
		return result;
	}

	@Override
	public Map<String, String> queryByTreeId(String treeId) {
		Map<String, String> dataMap = new HashMap<String, String>();
		List<DictTreeDataEntity> dictDatas = queryDataByTreeId(treeId);
		for(DictTreeDataEntity data:dictDatas){
			dataMap.put(data.getData1(), data.getCode());
		}
		return dataMap;
	}

	@Override
	public List<DictTreeDataEntity> checkRepeatInTreeId(String treeId, String fieldName, String fieldvalue) {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("fieldvalue", fieldvalue);
		conditions.put("treeId", treeId);
		StringBuffer sql = new StringBuffer();
		sql.append("select * from sys_dict_tree_data dtd where dtd.");
		sql.append(fieldName);
		sql.append(" = :fieldvalue ");
		sql.append("start with dtd.parentnodeid = :treeId ");
		sql.append("connect by dtd.parentnodeid = prior dtd.nodeid ");
		List<DictTreeDataEntity> dictDatas = dictTreeDataDao.queryListBySql(sql.toString(), conditions, DictTreeDataEntity.class);
		return dictDatas;
	}

	@Override
	public Map<String, Object> queryDictDataByAjax(Map<String, Object> pars, PageBean pageBean) {
		Map<String, Object> result = new HashMap<String, Object>();
		Object[] obj = getAjaxSQL(pars);
		//没有传入pageBean则不分页
		result = dictTreeDataDao.queryPaginationListBySql(obj[0].toString(), (Map<String, Object>) obj[1], pageBean);
		for(Map<String, Object> data:(List<Map<String, Object>>) result.get("rows")){
			int num = ((BigDecimal)data.get("childnum")).intValue();
			if(num == 0){
				data.put("isParent", false);
			}else{
				data.put("isParent", true);
			}
			data.put("open", false);
		}
		return result;
	}
	
	private Object[] getAjaxSQL(Map<String, Object> pars) {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> conditions = new HashMap<String, Object>();
		// 若存在分组，则分组条件查询 2015.03.25 hy
		String sql2 = "";
		String sql3 = "";
		if(pars.containsKey("groupid")){
			sql2 = " ,sys_dict_tree treedict ";  // 增加链表查询条件
			sql3 = " and dtd.parentnodeid = treedict.treeid and treedict.groupid='"+pars.get("groupid")+"' ";  // 增加分组过滤条件
//			conditions.put("groupid", pars.get("groupid"));
		}
//		sql.append("select dtd.*, ");
		sql.append("select dtd.nodeid,dtd.parentnodeid,dtd.code,dtd.data1,dtd.data2,dtd.data3,dtd.orderno, ");
		sql.append("(select dt.treetype from sys_dict_tree dt where dt.treeid=dtd.parentnodeid) as treetype, ");
		sql.append("(select count(0) from sys_dict_tree_data where parentnodeid=dtd.nodeid) as childnum from sys_dict_tree_data dtd "+sql2);
		sql.append("where 1=1 "+sql3);
		if(pars != null && pars.size() > 0){
			if(pars.containsKey("treeId") || pars.containsKey("treeDesc")){
				sql.append("and dtd.parentnodeid in (select t.treeid from sys_dict_tree t where 1=1 ");
				if(pars.containsKey("treeId")){
					conditions.put("treeId", pars.get("treeId"));
					sql.append("and t.treeid = :treeId ");
				}
				if(pars.containsKey("treeDesc")){
					conditions.put("treeDesc", "%"+pars.get("treeDesc")+"%");
					sql.append("and t.treedescription like :treeDesc ");
				}
				sql.append(") ");
			}
			if(pars.containsKey("code")){
				conditions.put("code", pars.get("code"));
				sql.append("and dtd.code = :code  ");
			}
			if(pars.containsKey("data1")){
				conditions.put("data1", "%"+pars.get("data1")+"%");
				sql.append("and dtd.data1 like :data1  ");
			}
		}
		sql.append("order by dtd.parentnodeid ");
		return new Object[] { sql, conditions };
	}

	@Override
	public Map<String, Object> queryChildDataByAjax(String parentId) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("parentId", parentId);
		StringBuffer sql = new StringBuffer();
		sql.append("select dtd.*, ");
		sql.append("(select dt.treetype from sys_dict_tree dt where dt.treeid in  ");
		sql.append("(select dt2.parentnodeid from sys_dict_tree_data dt2 start with dt2.nodeid = :parentId ");
		sql.append("connect by dt2.nodeid= prior dt2.parentnodeid) or dt.treeid = :parentId) as treetype, ");
		sql.append("(select count(0) from sys_dict_tree_data where parentnodeid=dtd.nodeid) as childnum from sys_dict_tree_data dtd ");
		sql.append("where dtd.parentnodeid=:parentId ");
		sql.append("order by dtd.orderno asc ");
		List<Map<String, Object>> dictDatas = dictTreeDataDao.queryListBySql(sql.toString(), conditions);
		for(Map<String, Object> data : dictDatas){
			int num = ((BigDecimal)data.get("childnum")).intValue();
			if(num == 0){
				data.put("isParent", false);
			}else{
				data.put("isParent", true);
			}
			data.put("open", false);
		}
		result.put("rows", dictDatas);
		return result;
	}

	@Override
	public String queryTreeIdByNodeId(String nodeId) {
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("nodeId", nodeId);
		StringBuffer sql = new StringBuffer();
		sql.append("select * from sys_dict_tree_data dtd ");
		sql.append("start with dtd.nodeid=:nodeId  ");
		sql.append("connect by prior dtd.parentnodeid = dtd.nodeid ");
		List<DictTreeDataEntity> dictDatas = dictTreeDataDao.queryListBySql(sql.toString(), conditions, DictTreeDataEntity.class);
		if(dictDatas != null && dictDatas.size() > 0){
			DictTreeDataEntity lastData = dictDatas.get(dictDatas.size()-1);
			return lastData.getParentNodeId();
		}
		return null;
	}

	@Override
	public List<DictTreeDataEntity> queryRootDateByPid(String nodeId) {
		String sql = "select * from sys_dict_tree_data where parentnodeid = ?1 order by orderno asc";
		return dictTreeDataDao.queryListBySql(sql, new Object[]{nodeId}, DictTreeDataEntity.class);
	}

	@Override
	public Map<String, Object> queryMobileVersion() {
		String sql ="select dtd.* from sys_dict_tree_data dtd where dtd.parentnodeid = 'U000008' order by dtd.orderno desc";
		Map<String, Object> pars = null;
		List<Map<String, Object>> result = dictTreeDataDao.queryListBySql(sql, pars);
		return result.size() > 0 ? result.get(0) : null;
	}
	
	@Override
	public List<Map<String, Object>> getFeedbackType() {
		String sql = "select dtd.* from sys_dict_tree_data dtd where dtd.parentnodeid = 'U000009' order by dtd.orderno asc";
		Map<String, Object> pars = null;
		List<Map<String, Object>> result = dictTreeDataDao.queryListBySql(sql, pars);
		return result;
	}
}
