package com.upsoft.yxsw.service.impl;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.constant.CommonConstant;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.yxsw.constant.Constant;
import com.upsoft.yxsw.dao.BizTSsBaseinfoDAO;
import com.upsoft.yxsw.service.BizTSsBaseinfoService;


@Service
public class BizTSsBaseinfoServiceImpl extends BaseServiceImpl implements BizTSsBaseinfoService {
	
	private Logger logger = Logger.getLogger(BizTSsBaseinfoServiceImpl.class);
	
	@Autowired
	private BizTSsBaseinfoDAO bizTSsBaseinfoDAO;
	
	public Map<String, Object> getList(PageBean bean, Map<String, Object> params){
		return null;
	}

	@Override
	public List<Map<String, Object>> getTree(String csOrgId,String csOrgType) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("del_flag", Constant.YES_NO.NO.getValue());
		String subSql = " AND T.BELONG_WSC_ID = '"+csOrgId+"'";
		if(Integer.valueOf(csOrgType) < Integer.valueOf(CommonConstant.orgType.FACTORY.getKey()) ){
			subSql = "";
		}
		String sql = "SELECT T.SS_ID ID,T.CODE,T.NAME, CASE WHEN T.PARENT_ID IS NULL THEN T.BELONG_WSC_ID ELSE T.PARENT_ID END AS PARENTID,T.DEL_FLAG DELFLAG,"
				+ "(SELECT COUNT(0) FROM BIZ_T_SBSS_RELATION R WHERE R.SS_ID = T.SS_ID) relationCount"
				+ " FROM BIZ_T_SS_BASEINFO T WHERE T.DEL_FLAG = :del_flag "+subSql;
		sql += " UNION ALL";
		sql += " SELECT DISTINCT T.BELONG_WSC_ID ID ,NULL CODE,T.BELONG_WSC_NAME NAME,NULL PARENTID ,NULL DELFLAG,null relationCount  FROM BIZ_T_SS_BASEINFO T WHERE 1=1 "+subSql;
		List<Map<String, Object>> list = bizTSsBaseinfoDAO.queryListBySql(sql, params);
		return list;
	}

	@Override
	public long validateSSCode(String ssCode) {
		String sql = "SELECT COUNT(0) FROM BIZ_T_SS_BASEINFO T WHERE 1=1 AND T.CODE =:ssCode";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("ssCode", ssCode);
		return bizTSsBaseinfoDAO.queryCountBySql(sql, params);
	}

	@Override
	public Integer getMaxNum() {
		Integer sortNum = 0;
		String sql = "SELECT NVL(MAX(T.SORT),0) AS SORT  FROM BIZ_T_SS_BASEINFO T ORDER BY T.SORT DESC";
		Map<String, Object> pars = new HashMap<String, Object>();
		List<Map<String, Object>> rlt = bizTSsBaseinfoDAO.queryListBySql(sql, pars);
		if (rlt != null && rlt.size() > 0) {
			sortNum = Integer.valueOf(rlt.get(0).get("sort").toString());
		}
		return sortNum;
	}

	@Override
	public Map<String, Object> getSSLayer(String layer) {
		Map<String,Object> layerName = new HashMap<String,Object>();
		StringBuilder list = new StringBuilder();
		//通过点号分割层级关系
		List<String> lists = Arrays.asList(layer.split("\\."));
		  if(null != lists && lists.size() > 0 ){
		      boolean first = true;
		      //第一个前面不拼接","
		      for(String string :lists) {
		    	  if(first) {
		    		  first=false;
		    	  }else{
		    		  list.append(",");
		    	  }
		    	  list.append("'" + string + "'");
		      }
		   }
		   
		String sql = " SELECT REPLACE(WM_CONCAT(NAME),',',' ／  ') LAYERNAME FROM (SELECT T.NAME FROM BIZ_T_SS_BASEINFO T WHERE 1 = 1  AND T.SS_ID IN( "+ list +" ) ORDER BY T.SORT ASC)";
		List<Map<String,Object>> result = bizTSsBaseinfoDAO.queryListBySql(sql, new HashMap<String,Object>());
		if(null != result && result.size() > 0){
			layerName = result.get(0);
		}else {
			layerName = null;
		}
		return layerName;
	}

	@Override
	public long validateSbUnderSs(String ssId) {
		String sql = "SELECT COUNT(1) FROM BIZ_T_SBSS_RELATION T WHERE T.SS_ID='"+ssId+"' ";
		long count = bizTSsBaseinfoDAO.queryCountBySql(sql, new HashMap<String,Object>());
		return count;
	}

	@Override
	public Boolean updateSSAndDelRelationSb(String ssId) {
		boolean flag = true;
		String sql = "UPDATE BIZ_T_SS_BASEINFO T SET T.DEL_FLAG='"+Constant.YES_NO.YES.getValue()+"' WHERE T.SS_ID IN (SELECT S.SS_ID FROM BIZ_T_SS_BASEINFO S START WITH S.SS_ID = '"+ssId+"' CONNECT BY PRIOR S.SS_ID = S.PARENT_ID)";
		Map<String,Object>  params = new HashMap<String,Object>();
		int updateCount = bizTSsBaseinfoDAO.executeSql(sql,params);
		if(updateCount>0){
			// 删除设施设备关联关系
			String sql2 = "DELETE BIZ_T_SBSS_RELATION T WHERE T.SS_ID IN (SELECT SS_ID FROM BIZ_T_SS_BASEINFO S START WITH S.SS_ID  = '"+ssId+"'  CONNECT BY PRIOR S.SS_ID =S.PARENT_ID)";
			bizTSsBaseinfoDAO.executeSql(sql2, params);
			// 删除设施巡检点关联关系
			String sql3 = "DELETE BIZ_T_XJD_ITEM_DETAIL T WHERE T.SBSS_ID IN (SELECT SS_ID FROM BIZ_T_SS_BASEINFO S START WITH S.SS_ID  = '"+ssId+"'  CONNECT BY PRIOR S.SS_ID =S.PARENT_ID)";
			bizTSsBaseinfoDAO.executeSql(sql3, params);
		}else{
			flag = false;
			logger.error("删除ID为"+ssId+"的设施失败");
		}
		
		return flag;
	}


	@Override
	public Map<String, Object> getNoRelatedData(PageBean pageBean,
			Map<String, Object> params, WSLoginInfoBean loginInfo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT FA.* FROM ( ");
		sql.append(" SELECT B.SS_ID, B.CODE,B.NAME,B.LAYER,B.LONGITUDE,B.LATITUDE,F_GET_SS_IS_ATTACH_XJD(B.SS_ID,NULL) AS IS_ATTACH ");
		sql.append(" FROM BIZ_T_SS_BASEINFO B WHERE 1=1");
		sql.append(" AND B.DEL_FLAG = '0' ");
		sql.append(" AND B.BELONG_WSC_ID ='"+loginInfo.getCsOrgId()+"' ");
		if(null != params){
			if(params.containsKey("eqName")){
				sql.append(" AND B.NAME LIKE '%"+params.get("eqName").toString()+"%' ");
			}
			if(params.containsKey("setAddress")){
				sql.append(" AND B.CODE LIKE '%"+params.get("setAddress").toString()+"%' ");
			}
		}   
		sql.append(" ) FA ");
		sql.append(" WHERE FA.IS_ATTACH = '0'  ");
		
		return bizTSsBaseinfoDAO.queryPaginationListBySql(sql.toString(), new HashMap<String,Object>(), pageBean);
	}
	@Override
	public List<Map<String, Object>> getTree(String csOrgId, String csOrgTypeId, Map<String, Object> param) {
		Map<String,Object> params = new HashMap<String,Object>();
		// params.put("del_flag", Constant.YES_NO.NO.getValue());
		String subSql = " AND T.BELONG_WSC_ID = '" + csOrgId + "'";
		if (Integer.valueOf(csOrgTypeId) < Integer.valueOf(CommonConstant.orgType.FACTORY.getKey())) {
			subSql = "";
		}

		//String sql = "SELECT T.SS_ID ID,T.CODE,T.NAME,T.PARENT_ID PARENTID,T.DEL_FLAG DELFLAG FROM BIZ_T_SS_BASEINFO T WHERE T.DEL_FLAG ='0' ";
		String sql = "SELECT T.SS_ID ID,T.CODE,T.NAME,T.PARENT_ID PARENTID,T.DEL_FLAG DELFLAG,  ";
		sql+=" CASE WHEN(SELECT COUNT(1)  FROM  BIZ_T_GG_SBSS_ATTACH_BASE  A";
		sql+=" WHERE A.CODE = T.SS_ID ";
		sql+=" AND A.CONF_TYPE ='1'  AND A.SB_OR_SS ='2')>0 THEN  1 ELSE  0 END  AS  HAS_ATTACH";
        sql+=" FROM BIZ_T_SS_BASEINFO T WHERE T.DEL_FLAG ='0'";
		sql += subSql;
		if (param.containsKey("value") && param.get("value") != null && StringUtils.isNotBlank(param.get("value").toString())) {

			sql += " AND  T.NAME LIKE :value  ";
			params.put("value", "%" + param.get("value") + "%");
		}
		List<Map<String, Object>> list = bizTSsBaseinfoDAO.queryListBySql(sql, params);
		return list;
	}
	
	
	@Override
	public Map<String, Object> getRelatedFaData(PageBean pageBean,
			String spotId, WSLoginInfoBean loginInfo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT D.DETAIL_ID,B.SS_ID, B.CODE,B.NAME,B.LAYER,B.LONGITUDE,B.LATITUDE,F_GET_SS_IS_ATTACH_XJD(B.SS_ID,NULL) AS IS_ATTACH ");
		sql.append(" FROM BIZ_T_SS_BASEINFO B ");
		sql.append(" LEFT JOIN BIZ_T_XJD_ITEM_DETAIL D ON B.SS_ID = D.SBSS_ID ");
	    sql.append(" WHERE 1=1 ");
	    sql.append(" AND B.DEL_FLAG = '0' ");
	    sql.append(" AND D.XJD_ITEM_ID ='"+ spotId +"'");
	  
		return bizTSsBaseinfoDAO.queryPaginationListBySql(sql.toString(), new HashMap<String,Object>(), pageBean);
	}

	@Override
	public List<Map<String, Object>> getTree1(String csOrgId, String csOrgTypeId, Map<String, Object> param) {
		Map<String,Object> params = new HashMap<String,Object>();
		/*if(MapUtil.hasParam(param, "value")){
	        String 	csOrgIdNew	=(String) param.get("value");
			
			String subSql1 = " AND T.BELONG_WSC_ID = '" + csOrgIdNew + "'";
		}*/
		// params.put("del_flag", Constant.YES_NO.NO.getValue());
		String subSql = " AND T.BELONG_WSC_ID = '" + csOrgId + "'";
		if (Integer.valueOf(csOrgTypeId) < Integer.valueOf(CommonConstant.orgType.FACTORY.getKey())) {
			subSql = "";
		}

		//String sql = "SELECT T.SS_ID ID,T.CODE,T.NAME,T.PARENT_ID PARENTID,T.DEL_FLAG DELFLAG FROM BIZ_T_SS_BASEINFO T WHERE T.DEL_FLAG ='0' ";
		String sql = "SELECT T.SS_ID ID,T.CODE,T.NAME,T.PARENT_ID PARENTID,T.DEL_FLAG DELFLAG,  ";
		sql+=" CASE WHEN(SELECT COUNT(1)  FROM  BIZ_T_GG_SBSS_ATTACH_BASE  A";
		sql+=" WHERE A.CODE = T.SS_ID ";
		sql+=" AND A.CONF_TYPE ='2'  AND A.SB_OR_SS ='2')>0 THEN  1 ELSE  0 END  AS  HAS_ATTACH";
        sql+=" FROM BIZ_T_SS_BASEINFO T WHERE T.DEL_FLAG ='0'";
		sql += subSql;
		/*if (param.containsKey("value") && param.get("value") != null && StringUtils.isNotBlank(param.get("value").toString())) {

			sql += " AND  T.NAME LIKE :value  ";
			params.put("value", "%" + param.get("value") + "%");
		}*/
		List<Map<String, Object>> list = bizTSsBaseinfoDAO.queryListBySql(sql, params);
		return list;
	}

	@Override
	public Map<String, Object> getFreqNoReSsList(PageBean pageBean, String freqId, Map<String, Object> params) {
		
		StringBuilder sql = new StringBuilder();
		Map<String, Object> paramsMap = new HashMap<String,Object>();
		sql.append(" SELECT M.TECHNICS_ID, M.TECHNICS_NAME, I.XJD_ITEM_ID, I.XJD_ITEM_NAME, I.XJD_ITEM_ADDRESS, D.SBSS_ID, SS.NAME, SS.CODE, SS.FUNCTION, SS.SS_ID, SS.LAYER ");
		sql.append(" FROM BIZ_T_XJ_TECHNICS_SCOPE_MANAGE M, BIZ_T_XJ_TECHNICS_SCOPE_ATT_XJ A, BIZ_T_XJD_ITEM I, BIZ_T_XJD_ITEM_DETAIL D, BIZ_T_SS_BASEINFO SS ");
		sql.append(" WHERE M.TECHNICS_ID = A.TECHNICS_ID ");
		sql.append(" AND A.XJD_ITEM_ID = I.XJD_ITEM_ID ");
		sql.append(" AND I.XJD_ITEM_ID = D.XJD_ITEM_ID ");
		sql.append(" AND D.SBSS_ID = SS.SS_ID ");
		sql.append(" AND M.DEL_FLAG = '0' ");	//工艺段未删除
		sql.append(" AND I.DEL_FLAG = '0' ");	//巡检点未删除
		sql.append(" AND SS.DEL_FLAG ='0' ");	//设施未删除
		sql.append(" AND D.DETAIL_TYPE = '2' ");	//只查询巡检点关联的设施,不查询设备
		
		if(null != params.get("csOrgId") && StringUtils.isNotBlank(params.get("csOrgId").toString())){
			sql.append("  AND M.BELONG_WSC_ID= :csOrgId ");	//操作人所在机构的ID
			paramsMap.put("csOrgId", params.get("csOrgId").toString());
		}
		if(null != params.get("name") && StringUtils.isNotBlank(params.get("name").toString())){
			sql.append(" and SS.NAME like :name ");
			paramsMap.put("name", MessageFormat.format("%{0}%", params.get("name").toString()));
		}
		if(null != params.get("code") && StringUtils.isNotBlank(params.get("code").toString())){
			sql.append(" and SS.CODE like :code ");
			paramsMap.put("code", MessageFormat.format("%{0}%", params.get("code").toString()));
		}
		if(null != params.get("sbSort") && StringUtils.isNotBlank(params.get("sbSort").toString())){
			sql.append("  AND SS.SS_SORT= :sbSort ");	//操作人所在机构的ID
			paramsMap.put("sbSort", params.get("sbSort").toString());
		}
		
		sql.append(" AND F_XJ_FREQ_SEGMENT_SBSS_ISRPT(:freqId,D.SBSS_ID) ='0' "); //参数  班次频率ID
		paramsMap.put("freqId", freqId);
		return bizTSsBaseinfoDAO.queryPaginationListBySql(sql.toString(), paramsMap, pageBean);
	}

	

}
