package com.upsoft.yxsw.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.yxsw.dao.BizTSbssRelationDAO;
import com.upsoft.yxsw.entity.BizTSbssRelation;
import com.upsoft.yxsw.service.BizTSbssRelationService;


@Service
public class BizTSbssRelationServiceImpl extends BaseServiceImpl implements BizTSbssRelationService {

	@Autowired
	private BizTSbssRelationDAO bizTSbssRelationDAO;
	
	@Override
	public Map<String, Object> getSBSSRelationList(String ssId,PageBean pageBean) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT T.SS_SB_ID SBSSID, S.SB_NAME SBNAME,S.SB_TYPE_ID SBTYPE,TY.NAME SBTYPENAME,S.SB_SORT SBSORT,S.GCJK GCJK,S.SBXH,S.XNCS,S.JGYL");
		sql.append(" FROM BIZ_T_SBSS_RELATION T,BIZ_T_SB_BASEINFO S LEFT JOIN BIZ_T_SB_TYPES TY ON TY.SB_TYPE_ID = S.SB_TYPE_ID WHERE T.SB_ID = S.SB_ID AND T.SS_ID=:SSId");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("SSId", ssId);
		
		return bizTSbssRelationDAO.queryPaginationListBySql(sql.toString(), params, pageBean);
	}

	@Override
	public List<BizTSbssRelation> saveAll(List<BizTSbssRelation> relationsList) {
		
		List<BizTSbssRelation> list = new ArrayList<BizTSbssRelation>();
		for (BizTSbssRelation entity : relationsList) {
			list.add(bizTSbssRelationDAO.save(entity));
		}
		return list;
	}

	@Override
	public void deleteAll(String ids) {
		String[] idarr = ids.split(",");
		for (String id : idarr) {
			bizTSbssRelationDAO.delete(findOne(BizTSbssRelation.class,id));
		}
	}
	
public Map<String, Object> getEqList(PageBean pageBean, Map<String, Object> params){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT T.SB_ID, T.SB_CODE, T.SB_NAME, T.SB_SORT, st.name SB_TYPE_ID, T.SBXH,T.ZY_STATUS ");
		sql.append(" FROM BIZ_T_SB_BASEINFO T ");
		sql.append(" left join BIZ_T_SB_TYPES st on st.SB_TYPE_ID = T.SB_TYPE_ID ");
		sql.append(" where 1=1 ");
		sql.append(" and T.DEL_FLAG = 0");
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		if(null != params.get("sbName") && StringUtils.isNotBlank(params.get("sbName").toString())){
			sql.append(" and T.SB_NAME like :sbName ");
			paramsMap.put("sbName", MessageFormat.format("%{0}%", params.get("sbName").toString()));
		}
		if(null != params.get("sbSort") && StringUtils.isNotBlank(params.get("sbSort").toString())){
			sql.append(" and T.SB_SORT = :sbSort ");
			paramsMap.put("sbSort", params.get("sbSort").toString());
		}
		//设备类型，可通过父类型查询所有子类型的设备
		if(null != params.get("sbTypeId") && StringUtils.isNotBlank(params.get("sbTypeId").toString())){
//			sql.append(" and T.SB_TYPE_ID = :sbTypeId ");
			sql.append(" and T.SB_TYPE_ID in (SELECT st.sb_type_id from  biz_t_sb_types st START WITH st.sb_type_id = :sbTypeId CONNECT BY PRIOR st.sb_type_id = st.parent_type_id)");
			paramsMap.put("sbTypeId", params.get("sbTypeId").toString());
		}
		if(null != params.get("zyStatus") && StringUtils.isNotBlank(params.get("zyStatus").toString())){
			sql.append(" and T.ZY_STATUS = :zyStatus ");
			paramsMap.put("zyStatus", params.get("zyStatus").toString());
		}
		
		if(null != params.get("orgCode") && StringUtils.isNotBlank(params.get("orgCode").toString())){
			sql.append(" and T.BELONG_WSC_ID = :orgCode ");
			paramsMap.put("orgCode", params.get("orgCode").toString());
		}
		sql.append(" AND (SELECT COUNT(R.SB_ID) FROM BIZ_T_SBSS_RELATION R WHERE R.SB_ID = T.SB_ID)=0");
				
		return bizTSbssRelationDAO.queryPaginationListBySql(sql.toString(), paramsMap, pageBean);
	}
	
}
