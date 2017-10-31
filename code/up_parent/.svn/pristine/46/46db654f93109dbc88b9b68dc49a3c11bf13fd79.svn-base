package com.upsoft.yxsw.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.yxsw.dao.BizTXjWorkGroupDetialDAO;
import com.upsoft.yxsw.entity.BizTXjWorkGroupDetial;
import com.upsoft.yxsw.service.BizTXjWorkGroupDetialService;


@Service
public class BizTXjWorkGroupDetialServiceImpl extends BaseServiceImpl implements BizTXjWorkGroupDetialService {
	
	@Autowired
	private BizTXjWorkGroupDetialDAO detailDAO;
	/**
	 * 获取班次详情列表
	 * @date 2017-09-15
	 * @author 
	 * @param bean
	 * @param params
	 * @return 
	 */
	public Map<String, Object> getList(PageBean bean, Map<String, Object> params){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT D.DETAIL_ID, D.GROUP_ID, D.DETAIL_NAME, D.DETAIL_DESC, D.START_TIME, D.END_TIME, D.SORT_NUM, D.BYZD, D.DEL_FLAG ");
		sql.append(" FROM BIZ_T_XJ_WORK_GROUP_DETIAL D ");
		sql.append(" WHERE 1=1");
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		if(null != params && null != params.get("workGroupId") && StringUtils.isNotBlank(params.get("workGroupId").toString())){
			sql.append(" AND D.GROUP_ID = :workGroupId");
			paramsMap.put("workGroupId", params.get("workGroupId").toString());
		}
		return detailDAO.queryPaginationListBySql(sql.toString(), paramsMap, bean);
	}
	
	@Override
	public List<Map<String, Object>> workGroupDetialList(String csId) {
		
		StringBuilder sql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		sql.append("SELECT D.DETAIL_ID, D.GROUP_ID, D.DETAIL_NAME, D.DETAIL_DESC, D.START_TIME, D.END_TIME, D.SORT_NUM, D.BYZD, D.DEL_FLAG ");
		sql.append(" FROM BIZ_T_XJ_WORK_GROUP_DETIAL D ");
		sql.append(" LEFT JOIN BIZ_T_XJ_WORK_GROUP G ON D.GROUP_ID = G.WORK_GROUP_ID ");
		sql.append(" WHERE 1=1 ");
		sql.append(" AND G.BELONG_WSC_ID = :csId");
		params.put("csId", csId);
		return detailDAO.queryListBySql(sql.toString(), params);
	}
	
	/**
	 * XXXXXXXXXXXXXXXXXXXXXXXXXXXX
	 * @date 2017-09-15
	 * @author 
	 * @param order
	 * @return 
	 */
	public BizTXjWorkGroupDetial save(BizTXjWorkGroupDetial bizTXjWorkGroupDetial){
		return bizTXjWorkGroupDetial;
	
	}
	
	
	/**
	 * XXXXXXXXXXXXXXX
	 * @date 2017-09-15
	 * @author 
	 * @param 
	 * @return 
	 */
	public BizTXjWorkGroupDetial getBizTXjWorkGroupDetialById(String id){
		return null;
	}

	@Override
	public void deleteDetials(List<BizTXjWorkGroupDetial> delDetialList) {
		
		detailDAO.delete(delDetialList);
	}
}
