package com.upsoft.yxsw.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.system.util.MapUtil;
import com.upsoft.yxsw.constant.Constant;
import com.upsoft.yxsw.dao.BizTGgSbssAttachBaseDAO;
import com.upsoft.yxsw.entity.BizTGgSbssAttachBase;
import com.upsoft.yxsw.entity.BizTXjCxTaskItemSbss;
import com.upsoft.yxsw.mobile.bean.execute.CheckItemBean;
import com.upsoft.yxsw.mobile.bean.execute.CheckItemDetailBean;
import com.upsoft.yxsw.service.BizTGgSbssAttachBaseService;
import com.upsoft.yxsw.service.BizTXjCxTaskItemSbssService;



@Service
public class BizTGgSbssAttachBaseServiceImpl extends BaseServiceImpl implements BizTGgSbssAttachBaseService {
   
	private enum QueryType {
		LIST, COUNT
	}

	@Autowired
	private BizTGgSbssAttachBaseDAO bizTGgSbssAttachBaseDAO;
	@Autowired
	private BizTXjCxTaskItemSbssService bizTXjCxTaskItemSbssService;
	
	@Override
	public List<BizTGgSbssAttachBase> getAttachInfoByCheckItemIds(List<String> checkItemIdList) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT T.ATTACH_BASE_ID, T.CODE, T.CONF_DESC, T.BYZD, T.SB_OR_SS, T.CONF_TYPE, T.DETAIL_ID, T.NAME, T.SORT_NUM,");
		sql.append(" T.CREATE_TIMESTEMP, T.CREATOR_ACCOUNT, T.CREATOR_NAME, T.BELONG_WSC_ID, T.BELONG_WSC_NAME, T.BELONG_DEPT, T.UPDATOR_ACCOUNT, T.UPDATOR_NAME, T.UPDATE_TIMESTEMP ");
		sql.append("FROM BIZ_T_GG_SBSS_ATTACH_BASE T WHERE 1=1 ");
		Map<String, Object> params = new HashMap<String, Object>();
		if (null != checkItemIdList) {
			sql.append(" AND T.DETAIL_ID IN (:checkItemIdList)");
			params.put("checkItemIdList", checkItemIdList);
		}
		List<BizTGgSbssAttachBase> list = bizTGgSbssAttachBaseDAO.queryListBySql(sql.toString(), params, BizTGgSbssAttachBase.class);

		return list;
	}

	
	public List<Map<String, Object>> queryByTreeId(Map<String, Object> params) {

		String sql = "SELECT  B.SB_ID ID  ,  B.SB_CODE,B.SB_FN_CODE,B.SB_NAME NAME , B.SBXH, B.XNCS, B.SET_ADDRESS, B.ZY_STATUS, B.SB_SORT,B.JGYL,B.BELONG_WSC_NAME PARENTID, B.BELONG_WSC_ID,";
		sql += "(CASE WHEN(SELECT COUNT(1) FROM BIZ_T_GG_SBSS_ATTACH_BASE A WHERE A.CODE = B.SB_ID AND A.CONF_TYPE='1')>0 THEN 1  ELSE 0 END)";
		sql += "AS HAS_ATTACH FROM BIZ_T_SB_BASEINFO B ";
		sql += "  WHERE B.DEL_FLAG = '0' ";
		sql += "  ORDER BY B.BELONG_WSC_ID ASC, B.SB_TYPE_ID ASC";
		List<Map<String, Object>> list = bizTGgSbssAttachBaseDAO.queryListBySql(sql, new HashMap<String, Object>());
		if (null != list && list.size() > 0) {
			return list;
		} else {
			return new ArrayList<Map<String, Object>>();
		}
	}


		@SuppressWarnings("unchecked")
		@Override
		public Map<String, Object> getWarningListAndCount(Map<String, Object> pars, PageBean bean) {
		
			Object[] querySentence = this.getQuerySentence1(pars, QueryType.LIST);//查询总的数据条数
			Map<String,Object>  warningListAndCount=new  HashMap<String, Object>();
			
			 warningListAndCount=	bizTGgSbssAttachBaseDAO.queryPaginationListBySql(querySentence[0].toString(),(Map<String, Object>)querySentence[1] , bean);
			
				
			return  warningListAndCount;
		}
		private Object[] getQuerySentence1(Map<String, Object> pars, QueryType type) {
			StringBuffer  sql=new StringBuffer();
			Map<String, Object> parsCon = new HashMap<String, Object>();
			parsCon.put("code", pars.get("code"));
			switch (type) {
			case LIST:
			
				sql.append("SELECT I.CHECK_ITEM_ID ,   I.CHECK_ITEM_CODE, I.CHECK_ITEM_NAME, ");
				sql.append(" I.CHECK_ITEM_DESC,");
				sql.append(" F_GET_CHECK_ITEM_ATTACHED_SBSS(I.CHECK_ITEM_ID,'1'");
				sql.append(" ,:code)");
				sql.append(" AS HAS_ATTACH,");
				sql.append(" I.INPUT_TYPE");
				
				break;
			case COUNT:
				
				sql.append("select count(1)");
				
				break;
			default:
				
				sql.append("select count(1)");
				break;
			}
			
			sql.append(" FROM BIZ_T_GG_CHECK_ITEM  I");
			sql.append(" WHERE I.DEL_FLAG ='0'");
			sql.append(" AND I.CHECK_ITEM_TYPE='1'");
			sql.append(" AND  F_GET_CHECK_ITEM_ATTACHED_SBSS(I.CHECK_ITEM_ID,'1'");
			sql.append(" ,:code)");
			sql.append(" ='1'");
		
			return  new Object[]{sql,parsCon};
			
			
		}


		@Override
		public Boolean deleteByCheckId(String id) {
			String sql="delete  from  BIZ_T_GG_SBSS_ATTACH_BASE WHERE DETAIL_ID="+"'"+id+"'";
			Map<String, Object> map=new HashMap<String,Object>();
			int executeSql = bizTGgSbssAttachBaseDAO.executeSql(sql, map);
			if(executeSql>0){
				
				return true;
			}
			return  false ;
		}


		@Override
		public Map<String, Object> getUnCheckListAndCount(Map<String, Object> par, PageBean bean) {
			Object[] querySentence = this.getQuerySentence(par, QueryType.LIST);//查询总的数据条数
			Map<String,Object>  warningListAndCount=new  HashMap<String, Object>();
			
			 warningListAndCount=	bizTGgSbssAttachBaseDAO.queryPaginationListBySql(querySentence[0].toString(),(Map<String, Object>)querySentence[1] , bean);
			
				
			return  warningListAndCount;
		}
		private Object[] getQuerySentence(Map<String, Object> pars, QueryType type) {
			StringBuffer  sql=new StringBuffer();
			Map<String, Object> parsCon = new HashMap<String, Object>();
			parsCon.put("code", pars.get("code"));
			switch (type) {
			case LIST:
			
				sql.append("SELECT I.CHECK_ITEM_ID ,   I.CHECK_ITEM_CODE, I.CHECK_ITEM_NAME, ");
				sql.append(" I.CHECK_ITEM_DESC,");
				sql.append(" F_GET_CHECK_ITEM_ATTACHED_SBSS(I.CHECK_ITEM_ID,'1'");
				sql.append(" ,:code)");
				sql.append(" AS HAS_ATTACH,");
				sql.append(" I.INPUT_TYPE");
				
				break;
			case COUNT:
				
				sql.append("select count(1)");
				
				break;
			default:
				
				sql.append("select count(1)");
				break;
			}
			
			sql.append(" FROM BIZ_T_GG_CHECK_ITEM  I");
			sql.append(" WHERE I.DEL_FLAG ='0'");
			sql.append(" AND I.CHECK_ITEM_TYPE='1'");
			if (MapUtil.hasParam(pars, "checkCode")) {
				sql.append(" AND ( I.CHECK_ITEM_CODE  LIKE:checkCode ) ");
				parsCon.put("checkCode", "%" + pars.get("checkCode") + "%");
	
			}
			if (MapUtil.hasParam(pars, "name")) {
				sql.append(" AND(I.CHECK_ITEM_NAME LIKE:name OR I.CHECK_ITEM_DESC LIKE:name)");
				parsCon.put("name", "%" + pars.get("name") + "%");
			}
			sql.append(" AND  F_GET_CHECK_ITEM_ATTACHED_SBSS(I.CHECK_ITEM_ID,'1'");
			sql.append(" ,:code)");
			sql.append(" ='0'");
		
			return  new Object[]{sql,parsCon};
			
			
		}


		@Override
		public Boolean findOneBySbId(String sbId) {
			
			String sql= " SELECT * FROM BIZ_T_GG_SBSS_ATTACH_BASE WHERE CODE="+"'" +sbId+"'";
		//	int i = bizTGgSbssAttachBaseDAO.executeSql(sql, new HashMap<String,Object>());
			List<Map<String, Object>> list = bizTGgSbssAttachBaseDAO.queryListBySql(sql, new HashMap<String,Object>());
			if(list!=null&&list.size()>0){
				return true;
			}
			    return false;
		}


		@Override
		public void deleteALL(String ids,String ssId) {
			 String[] split = ids.split(",");
			 for (String id : split) {
				 String sql=" DELETE  FROM BIZ_T_GG_SBSS_ATTACH_BASE WHERE DETAIL_ID="+"'" +id+"'" +"AND CODE="+"'" +ssId+"'";
				 bizTGgSbssAttachBaseDAO.executeSql(sql, new HashMap<String,Object>());
			}
		}


	@Override
	public List<Map<String, Object>> queryByTreeId(String input, Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		String sql = "SELECT  B.SB_ID ID  ,  B.SB_CODE,B.SB_FN_CODE,B.SB_NAME NAME , B.SBXH, B.XNCS, B.SET_ADDRESS, B.ZY_STATUS, B.SB_SORT,B.JGYL,B.BELONG_WSC_NAME , B.BELONG_WSC_ID PARENTID,";
		sql += "(CASE WHEN(SELECT COUNT(1) FROM BIZ_T_GG_SBSS_ATTACH_BASE A WHERE A.CODE = B.SB_ID AND A.CONF_TYPE='1')>0 THEN 1  ELSE 0 END)";
		sql += "AS HAS_ATTACH FROM BIZ_T_SB_BASEINFO B ";
		
		sql += "  WHERE B.DEL_FLAG = '0' ";
		
		if (MapUtil.hasParam(params, "csOrgId")) {

			sql += " AND B.BELONG_WSC_ID in(select o.ORGID from SYS_ORG O where o.ORGTYPEID in(1,2,3)";
			sql += " START WITH O.ORGID =:csOrgId";
			sql += " CONNECT BY prior O.ORGID =  O.PARENTORGID)";
			map.put("csOrgId", params.get("csOrgId"));
		}
		if (StringUtils.isNotBlank(input)) {

			sql += "AND (B.SB_CODE like :input or B.SB_FN_CODE like :input or B.SB_NAME like:input  OR B.BELONG_WSC_NAME  LIKE:input)";
			map.put("input", "%" + params.get("input") + "%");
		}
		sql += "  ORDER BY B.BELONG_WSC_ID ASC, B.SB_TYPE_ID ASC";
		List<Map<String, Object>> list = bizTGgSbssAttachBaseDAO.queryListBySql(sql, map);
		if (null != list && list.size() > 0) {
			return list;
		} else {
			return new ArrayList<Map<String, Object>>();
		}
	}


		@Override
		public Map<String, Object> getSsListAndCount(Map<String, Object> par, PageBean bean) {
			Object[] querySentence = this.getQuerySentence2(par, QueryType.LIST);//查询总的数据条数
			Map<String,Object>  warningListAndCount=new  HashMap<String, Object>();
			
			 warningListAndCount=	bizTGgSbssAttachBaseDAO.queryPaginationListBySql(querySentence[0].toString(),(Map<String, Object>)querySentence[1] , bean);
			
				
			return  warningListAndCount;
		}
		
		private Object[] getQuerySentence2(Map<String, Object> par, QueryType type) {
			StringBuffer  sql=new StringBuffer();
			Map<String, Object> parsCon = new HashMap<String, Object>();
			parsCon.put("code", par.get("code"));
			switch (type) {
			case LIST:
				sql.append("SELECT I.CHECK_ITEM_ID ,   I.CHECK_ITEM_CODE, I.CHECK_ITEM_NAME, ");
				sql.append(" I.CHECK_ITEM_DESC,");
				sql.append(" F_GET_CHECK_ITEM_ATTACHED_SBSS(I.CHECK_ITEM_ID,'2'");
				sql.append(" ,:code)");
				sql.append(" AS HAS_ATTACH,");
				sql.append(" I.INPUT_TYPE");
				
				break;
			case COUNT:
				
				sql.append("select count(1)");
				
				break;
			default:
				
				sql.append("select count(1)");
				break;
			}
			sql.append(" FROM BIZ_T_GG_CHECK_ITEM  I");
			sql.append(" WHERE I.DEL_FLAG ='0'");
			sql.append(" AND I.CHECK_ITEM_TYPE='2'");
		/*	if(MapUtil.hasParam(pars, "checkCode")){
			sql.append(" AND I.CHECK_ITEM_CODE  LIKE:checkCode");
			parsCon.put("checkCode","%"+ pars.get("checkCode")+"%");	
			}*/
			sql.append(" AND  F_GET_CHECK_ITEM_ATTACHED_SBSS(I.CHECK_ITEM_ID,'2'");
			sql.append(" ,:code)");
			sql.append(" ='1'");
			return  new Object[]{sql,parsCon};
			
			
		}


		@Override
		public Map<String, Object> getNoCheckListAndCount(Map<String, Object> par, PageBean bean) {
		Object[] querySentence = this.getQuerySentence3(par, QueryType.LIST);// 查询总的数据条数
		Map<String, Object> warningListAndCount = new HashMap<String, Object>();
		warningListAndCount = bizTGgSbssAttachBaseDAO.queryPaginationListBySql(querySentence[0].toString(), (Map<String, Object>) querySentence[1],
				bean);
		return warningListAndCount;
		}
		private Object[] getQuerySentence3(Map<String, Object> pars, QueryType type) {
			StringBuffer  sql=new StringBuffer();
			Map<String, Object> parsCon = new HashMap<String, Object>();
			parsCon.put("code", pars.get("code"));
			switch (type) {
			case LIST:
				sql.append("SELECT I.CHECK_ITEM_ID ,   I.CHECK_ITEM_CODE, I.CHECK_ITEM_NAME, ");
				sql.append(" I.CHECK_ITEM_DESC,");
				sql.append(" F_GET_CHECK_ITEM_ATTACHED_SBSS(I.CHECK_ITEM_ID,'2'");
				sql.append(" ,:code)");
				sql.append(" AS HAS_ATTACH,");
				sql.append(" I.INPUT_TYPE");				
				break;
			case COUNT:
				sql.append("select count(1)");
				break;
			default:
				sql.append("select count(1)");
				break;
			}
			sql.append(" FROM BIZ_T_GG_CHECK_ITEM  I");
			sql.append(" WHERE I.DEL_FLAG ='0'");
			sql.append(" AND I.CHECK_ITEM_TYPE='2'");
			if(MapUtil.hasParam(pars, "checkCode")){
			sql.append(" AND ( I.CHECK_ITEM_CODE  LIKE:checkCode ) ");
			parsCon.put("checkCode","%"+ pars.get("checkCode")+"%");	
			
			}
			if(MapUtil.hasParam(pars, "name")){
				sql.append(" AND(I.CHECK_ITEM_NAME LIKE:name OR I.CHECK_ITEM_DESC LIKE:name)");
				parsCon.put("name","%"+ pars.get("name")+"%");	
			}
		
			sql.append(" AND  F_GET_CHECK_ITEM_ATTACHED_SBSS(I.CHECK_ITEM_ID,'2'");
			sql.append(" ,:code)");
			sql.append(" ='0'");
		
			return  new Object[]{sql,parsCon};
			
			
		}


		@Override
		public Map<String, Object> getNoSqfeListAndCount(Map<String, Object> par, PageBean bean) {
		Object[] querySentence = this.getQuerySentence4(par, QueryType.LIST);// 查询总的数据条数
		Map<String, Object> warningListAndCount = new HashMap<String, Object>();

		warningListAndCount = bizTGgSbssAttachBaseDAO.queryPaginationListBySql(querySentence[0].toString(), (Map<String, Object>) querySentence[1],
				bean);

		return warningListAndCount;
		}
		private Object[] getQuerySentence4(Map<String, Object> pars, QueryType type) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> parsCon = new HashMap<String, Object>();
		parsCon.put("code", pars.get("code"));
		switch (type) {
		case LIST:
			sql.append("SELECT  W.WARNING_ID,  W.TITLE,	W.IS_IMPORTANT, W.TX_TYPE,DBMS_LOB.SUBSTR(W.CONTENT)AS CONTENT ,");
			sql.append(" F_GET_CHECK_WARN_ATTACHED_SBSS (w.WARNING_ID,'2'");
			sql.append(" ,:code)");
			sql.append(" AS HAS_ATTACH ");
			
			break;
		case COUNT:

			sql.append("select count(1)");

			break;
		default:

			sql.append("select count(1)");
			break;
		}
			
			sql.append("  FROM  BIZ_T_GG_WARNING_MANAGE  W");
			sql.append("  WHERE W.DEL_FLAG ='0'");
			sql.append("  AND W.TX_TYPE='2'");
			 if(MapUtil.hasParam(pars, "csOrgId")){
		        	sql.append(" AND W.BELONG_WSC_ID=:csOrgId");
		        	parsCon.put("csOrgId", pars.get("csOrgId"));
		        }
			sql.append("  AND  	F_GET_CHECK_WARN_ATTACHED_SBSS (w.WARNING_ID,'2'");
			sql.append(" ,:code)");
			sql.append(" ='0'");
			if (MapUtil.hasParam(pars, "checkCode")) {
				sql.append("  AND W.TITLE  LIKE:checkCode");
				parsCon.put("checkCode", "%" + pars.get("checkCode") + "%");
			}
		
			return  new Object[]{sql,parsCon};
			
			
		}


		@Override
		public Map<String, Object> getSsSafeListAndCount(Map<String, Object> par, PageBean bean) {
		Object[] querySentence = this.getQuerySentence5(par, QueryType.LIST);// 查询总的数据条数
		Map<String, Object> warningListAndCount = new HashMap<String, Object>();

		warningListAndCount = bizTGgSbssAttachBaseDAO.queryPaginationListBySql(querySentence[0].toString(), (Map<String, Object>) querySentence[1],
				bean);

		return warningListAndCount;
		}
		private Object[] getQuerySentence5(Map<String, Object> pars, QueryType type) {
			StringBuffer  sql=new StringBuffer();
			Map<String, Object> parsCon = new HashMap<String, Object>();
			parsCon.put("code", pars.get("code"));
			switch (type) {
			case LIST:
			sql.append("SELECT  W.WARNING_ID,   	W.TITLE,	W.IS_IMPORTANT, w.tx_type,DBMS_LOB.SUBSTR(W.CONTENT)AS CONTENT ,");

			sql.append(" F_GET_CHECK_WARN_ATTACHED_SBSS (w.WARNING_ID,'2'");
			sql.append(" ,:code)");
			sql.append(" AS HAS_ATTACH ");
				
			break;
		case COUNT:

			sql.append("select count(1)");

			break;
		default:

			sql.append("select count(1)");
			break;
		}

		sql.append("  FROM  BIZ_T_GG_WARNING_MANAGE  W");
		sql.append("  WHERE W.DEL_FLAG ='0'");
		sql.append("  AND W.TX_TYPE='2'");
		sql.append("  AND  	F_GET_CHECK_WARN_ATTACHED_SBSS (w.WARNING_ID,'2'");
		sql.append(" ,:code)");
		sql.append(" ='1'");

		return new Object[] { sql, parsCon };
			
		}


		@Override
		public Map<String, Object> getSbSafeListAndCount(Map<String, Object> par, PageBean bean) {
		Object[] querySentence = this.getQuerySentence6(par, QueryType.LIST);// 查询总的数据条数
		Map<String, Object> warningListAndCount = new HashMap<String, Object>();

		warningListAndCount = bizTGgSbssAttachBaseDAO.queryPaginationListBySql(querySentence[0].toString(), (Map<String, Object>) querySentence[1],
				bean);
		return warningListAndCount;
		}
		
		private Object[] getQuerySentence6(Map<String, Object> pars, QueryType type) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> parsCon = new HashMap<String, Object>();
		parsCon.put("code", pars.get("code"));
		switch (type) {
		case LIST:
			sql.append("SELECT  W.WARNING_ID,   	W.TITLE,	W.IS_IMPORTANT, W.TX_TYPE,DBMS_LOB.SUBSTR(W.CONTENT)AS CONTENT, ");
			sql.append(" F_GET_CHECK_WARN_ATTACHED_SBSS (w.WARNING_ID,'1'");
			sql.append(" ,:code)");
			sql.append(" AS HAS_ATTACH ");
			break;
		case COUNT:

			sql.append("select count(1)");

			break;
		default:

			sql.append("select count(1)");
			break;
		}

		sql.append("  FROM  BIZ_T_GG_WARNING_MANAGE  W");
		sql.append("  WHERE W.DEL_FLAG ='0'");
		sql.append("  AND W.TX_TYPE='1'");
		sql.append("  AND  	F_GET_CHECK_WARN_ATTACHED_SBSS (w.WARNING_ID,'1'");
		sql.append(" ,:code)");
		sql.append(" ='1'");

		return new Object[] { sql, parsCon };
			
		}


		@Override
	public Map<String, Object> getNoSbSqfeListAndCount(Map<String, Object> par, PageBean bean) {
		Object[] querySentence = this.getQuerySentence7(par, QueryType.LIST);// 查询总的数据条数
		Map<String, Object> warningListAndCount = new HashMap<String, Object>();

		warningListAndCount = bizTGgSbssAttachBaseDAO.queryPaginationListBySql(querySentence[0].toString(), (Map<String, Object>) querySentence[1],
				bean);

		return warningListAndCount;
	}

	private Object[] getQuerySentence7(Map<String, Object> pars, QueryType type) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> parsCon = new HashMap<String, Object>();
		parsCon.put("code", pars.get("code"));
		switch (type) {
		case LIST:
			sql.append("SELECT  W.WARNING_ID,   	W.TITLE,	W.IS_IMPORTANT, w.tx_type, dbms_lob.substr(w.content)AS CONTENT, ");
			sql.append(" F_GET_CHECK_WARN_ATTACHED_SBSS (w.WARNING_ID,'1'");
			sql.append(" ,:code)");
			sql.append(" AS HAS_ATTACH ");
			break;
		case COUNT:

			sql.append("select count(1)");

			break;
		default:

			sql.append("select count(1)");
			break;
		}

		sql.append("  FROM  BIZ_T_GG_WARNING_MANAGE  W");
		sql.append("  WHERE W.DEL_FLAG ='0'");
		sql.append("  AND W.TX_TYPE='1'");
        if(MapUtil.hasParam(pars, "csOrgId")){
        	sql.append(" AND W.BELONG_WSC_ID=:csOrgId");
        	parsCon.put("csOrgId", pars.get("csOrgId"));
        }
		sql.append("  AND  	F_GET_CHECK_WARN_ATTACHED_SBSS (w.WARNING_ID,'1'");
		sql.append(" ,:code)");
		sql.append(" ='0'");
		if (MapUtil.hasParam(pars, "checkCode")) {
			sql.append("  AND W.TITLE  LIKE:checkCode");
			parsCon.put("checkCode", "%" + pars.get("checkCode") + "%");
		}

		return new Object[] { sql, parsCon };

	}


	@Override
	public List<Map<String, Object>> queryByTreeId1( Map<String, Object> params) {
		Map<String, Object> map = new HashMap<String, Object>();
		String sql = "SELECT  B.SB_ID ID  ,  B.SB_CODE,B.SB_FN_CODE,B.SB_NAME NAME , B.SBXH, B.XNCS, B.SET_ADDRESS, B.ZY_STATUS, B.SB_SORT,B.JGYL,B.BELONG_WSC_NAME , B.BELONG_WSC_ID PARENTID ,";
		sql += "(CASE WHEN(SELECT COUNT(1) FROM BIZ_T_GG_SBSS_ATTACH_BASE A WHERE A.CODE = B.SB_ID AND A.CONF_TYPE='2' AND SB_OR_SS='1')>0 THEN 1  ELSE 0 END)";
		sql += "AS HAS_ATTACH FROM BIZ_T_SB_BASEINFO B ";
		// sql+=" WHERE B.DEL_FLAG ='0' AND B.BELONG_WSC_ID in(select o.ORGID from SYS_ORG O where o.ORGTYPEID in(1,2,3)";
		// sql+="START WITH O.ORGID ="+"'"+ orgId+"'" +
		// " CONNECT BY prior O.ORGID =  O.PARENTORGID) ORDER BY B.BELONG_WSC_ID ASC,B.SB_TYPE_ID ASC";
		sql += "  WHERE B.DEL_FLAG = '0' ";
		if (MapUtil.hasParam(params, "input")) {

			sql += "AND (B.SB_CODE like :input or B.SB_FN_CODE like :input or B.SB_NAME like:input  OR B.BELONG_WSC_NAME  LIKE:input)";
//			//sql +="  OR B.BELONG_WSC_NAME  LIKE:input";
			/*sql+=" OR B.SB_SORT  LIKE:input OR B.XNCS  LIKE:input";
			sql+=" OR B.XNCS  LIKE:input OR B.SBXH  LIKE:input";
			sql+=" OR B.JGYL  LIKE:input OR B.SB_CODE  LIKE:input"*/;
			map.put("input", "%" + params.get("input") + "%");
		}
		if(MapUtil.hasParam(params, "csOrgId")){
			
		    sql+=" AND B.BELONG_WSC_ID in(select o.ORGID from SYS_ORG O where o.ORGTYPEID in(1,2,3)";
		    sql+=" START WITH O.ORGID =:csOrgId";
		    sql+=" CONNECT BY prior O.ORGID =  O.PARENTORGID)";
		    map.put("csOrgId", params.get("csOrgId"));
		}
		sql += "  ORDER BY B.BELONG_WSC_ID ASC, B.SB_TYPE_ID ASC";
		List<Map<String, Object>> list = bizTGgSbssAttachBaseDAO.queryListBySql(sql, map);
		if (null != list && list.size() > 0) {
			return list;
		} else {
			return new ArrayList<Map<String, Object>>();
		}
	}

	@Override
	public List<CheckItemBean> getAttachInfoByTaskItemSbssId(String ttaskItemSbssId) {
		
		BizTXjCxTaskItemSbss taskItemSbss = bizTXjCxTaskItemSbssService.findOne(BizTXjCxTaskItemSbss.class, ttaskItemSbssId);
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ITEM.CHECK_ITEM_ID,ITEM.CHECK_ITEM_CODE,ITEM.CHECK_ITEM_NAME,ITEM.INPUT_TYPE,DETAIL.SEL_VALUE,DETAIL.SEL_NAME,DETAIL.SFZC,DETAIL.SFMR");
		sql.append("   FROM BIZ_T_GG_CHECK_ITEM ITEM");
		sql.append("  LEFT JOIN BIZ_T_GG_CHECK_ITEM_DETAIL DETAIL ON DETAIL.CHECK_ITEM_ID = ITEM.CHECK_ITEM_ID");
		sql.append(" WHERE ITEM.CHECK_ITEM_ID IN (SELECT T.DETAIL_ID");
		sql.append("     FROM BIZ_T_GG_SBSS_ATTACH_BASE T ");
		sql.append("      WHERE T.CONF_TYPE = '"+Constant.PZ_TYPE.CHECKTYPE.getValue()+"'");
		sql.append("       AND T.CODE = '"+taskItemSbss.getSbssId()+"')");
		List<Map<String, Object>> list = bizTGgSbssAttachBaseDAO.queryListBySql(sql.toString(), new HashMap<String,Object>());
		List<CheckItemBean> result = new ArrayList<CheckItemBean>();
		Map<String,List<CheckItemDetailBean>> checkItemMap = new HashMap<String,List<CheckItemDetailBean>>();
		Map<String,CheckItemBean> checkItemMap2 = new HashMap<String,CheckItemBean>();
		for (Map<String, Object> map : list) {
			List<CheckItemDetailBean> detailList = null;
			String checkItemId = map.get("check_item_id").toString();
			CheckItemBean checkItem = new CheckItemBean();
			if(!checkItemMap2.containsKey(checkItemId)){
				checkItem.setCheckItemId(checkItemId);
				checkItem.setCheckItemCode(map.get("check_item_code").toString());
				checkItem.setCheckItemName(map.get("check_item_name").toString());
				checkItem.setInputType(map.get("input_type").toString());
				checkItemMap2.put(checkItemId, checkItem);
			}
			
			if(StringUtils.equals(map.get("input_type").toString(), Constant.CHECKITEM_INPUTTYPE_VALUE.RADIO.getValue())){
				if(checkItemMap.containsKey(checkItemId)){
					detailList = checkItemMap.get(checkItemId);
				}else{
					detailList = new ArrayList<CheckItemDetailBean>();
				}
				CheckItemDetailBean detail = new CheckItemDetailBean();
				detail.setSelName(map.get("sel_name").toString());
				detail.setSelValue(map.get("sel_value").toString());
				detail.setSfmr(map.get("sfmr").toString());
				detail.setSfzc(map.get("sfzc").toString());
				detailList.add(detail);
				checkItemMap.put(map.get("check_item_id").toString(), detailList);
			}
			
		}
		
		for (String key : checkItemMap2.keySet()) {
			checkItemMap2.get(key).setDetailList(checkItemMap.get(key));
			result.add(checkItemMap2.get(key));
		}
		
		return result;
	}
	
}
                   