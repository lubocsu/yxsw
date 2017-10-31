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
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.systemweb.dao.DictTreeDao;
import com.upsoft.systemweb.service.DictTreeService;

/**
* Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：DictTreeServiceImpl.java<br>
* 摘要：数据字典目录树服务层实现类<br>
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
public class DictTreeServiceImpl extends BaseServiceImpl implements DictTreeService {

	@Autowired
	private DictTreeDao dictTreeDao;
	
	public List<Map<String, Object>> queryDictTreeByPage(int pageNo, int pageSize, Map<String, Object> pars) {
		Object[] obj = getQuerySQL(pars, QueryType.LIST);
		List<Map<String, Object>> dicts = dictTreeDao.queryListBySql(obj[0].toString(), pageNo, pageSize, (Map<String, Object>) obj[1]);
		return dicts;
	}
	
	/**
	 * 拼装sql
	 * @date 2015年1月22日 下午2:32:54
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
				sql.append("select t.treeid as id, t.treedescription as name, 0 as parentId ,'../image/dict_tree.png' as icon, t.treetype as treetype, t.remark as remark from SYS_DICT_TREE t where 1=1 ");
				break;
			case COUNT:
				sql.append(" select count(*) from SYS_DICT_TREE t where 1=1 ");
				break;
			default:
				break;
		}
		if(pars != null && pars.size() > 0){
			if(pars.containsKey("treeDesc")){
				conditions.put("treeDesc", "%" + pars.get("treeDesc") + "%");
				sql.append("and t.treeDescription like :treeDesc ");
			}
			// 筛选分组 2015.03.24 hy
			if(pars.containsKey("groupid")){
				sql.append(" and t.groupid=:groupid ");
				conditions.put("groupid", pars.get("groupid"));
			}
		}
		sql.append("order by t.orderno asc ");
		return new Object[] { sql, conditions };
	}

	@Override
	public Map<String, Object> queryDictByPagination(PageBean bean, Map<String, Object> pars) {
		Object[] obj = getQuerySQL(pars, QueryType.LIST);
		return dictTreeDao.queryPaginationListBySql(obj[0].toString(), (Map<String, Object>) obj[1], bean);
	}

	@Override
	public Long queryMaxOrderNo() {
		String sql = "select max(orderno) as max from sys_dict_tree";
		List<Map<String, Object>> result = dictTreeDao.queryListBySql(sql, new HashMap<String, Object>());
		Long max = ((BigDecimal)result.get(0).get("max")).longValue();
		return max;
	}
	
	private Object[] getQuerySQL2(Map<String, Object> pars) {
		Map<String, Object> conditions = new HashMap<String, Object>();
		String sql2 = "";
		if(pars.containsKey("groupid")){
			sql2 +=" and groupid=:groupid ";
			conditions.put("groupid", pars.get("groupid"));
		}
		pars.remove("groupid");
		StringBuilder sql = new StringBuilder();
		sql.append("(select dtd.nodeid as id,dtd.data1 as name,dtd.parentnodeid as parentid ");
		sql.append("from sys_dict_tree_data dtd ");
		sql.append("start with dtd.parentnodeid in (select treeid from sys_dict_tree where 1=1 "+sql2);
		if(pars != null && pars.size() > 0){
			if(pars.containsKey("treeDesc")){
				conditions.put("treeDesc", "%" + pars.get("treeDesc") + "%");
				sql.append("and treedescription like :treeDesc ) ");
				sql.append("or dtd.data1 like :treeDesc ");
			}
		}else{
			sql.append(") ");
		}
		sql.append("connect by dtd.parentnodeid = prior dtd.nodeid) union ");
		sql.append("(select dt.treeid as id,dt.treedescription as name,'-1' as parentid ");
		sql.append("from sys_dict_tree dt where 1=1 "+sql2);
		if(pars != null && pars.size() > 0){
			if(pars.containsKey("treeDesc")){
				sql.append("and dt.treedescription like :treeDesc ");
			}
		}
		sql.append(") ");
		return new Object[] { sql, conditions };
	}
	
	@Override
	public Map<String, Object> queryDataParentByPagination(PageBean bean, Map<String, Object> pars) {
		Object[] obj = getQuerySQL2(pars);
		return dictTreeDao.queryPaginationListBySql(obj[0].toString(), (Map<String, Object>) obj[1], bean);
	}

	@Override
	public Long getPageNoById(String id, String groupid, int pageSize) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("treeId", id);
		// 巡检系统需要考虑分组
		Map<String, Object> pars = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(groupid)){
			pars.put("groupid", groupid);
			condition.put("groupid", groupid);
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select ttt.rNUM from (select tt.*,rownum as rNUM from ( ");
		sql.append(getQuerySQL(pars, QueryType.LIST)[0].toString());
		sql.append(" ) tt) ttt where ttt.id=:treeId ");
		List<Map<String, Object>> result = dictTreeDao.queryListBySql(sql.toString(), condition);
		if(result != null && result.size() == 1){
			long rownum = ((BigDecimal) result.get(0).get("rnum")).longValue();
			return (rownum%pageSize == 0)?(rownum/pageSize):(rownum/pageSize + 1);
		}
		//默认第一页
		return (long) 1;
	}
}
