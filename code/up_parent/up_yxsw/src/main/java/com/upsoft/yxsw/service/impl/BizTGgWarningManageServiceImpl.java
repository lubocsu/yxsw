package com.upsoft.yxsw.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


















import org.springframework.test.context.jdbc.Sql;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.system.util.MapUtil;
import com.upsoft.yxsw.constant.Constant;
import com.upsoft.yxsw.dao.BizTGgWarningManageDao;
import com.upsoft.yxsw.entity.BizTGgWarningManageEntity;
import com.upsoft.yxsw.service.BizTGgWarningManageService;
@Service
public class BizTGgWarningManageServiceImpl extends BaseServiceImpl implements
		BizTGgWarningManageService  {
	
	private enum QueryType {
		LIST, COUNT
	}

	
	@Autowired
	private BizTGgWarningManageDao bizTGgWarningManageDao;
     @SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getWarningListAndCount(Map<String, Object> pars,
			PageBean bean) {
		Object[] querySentence = this.getQuerySentence(pars, QueryType.LIST);//查询总的数据条数
		Map<String,Object>  warningListAndCount=new  HashMap<String, Object>();
		
		 warningListAndCount=	bizTGgWarningManageDao.queryPaginationListBySql(querySentence[0].toString(),(Map<String, Object>)querySentence[1] , bean);
		
			
		return  warningListAndCount;
	}

	private Object[] getQuerySentence(Map<String, Object> pars, QueryType type) {
		StringBuffer  sql=new StringBuffer();
		Map<String, Object> parsCon = new HashMap<String, Object>();
		
		switch (type) {
		case LIST:
			sql.append("select title ,warning_id ,dbms_lob.substr(content) as content, tx_type,is_important ");
			break;
		case COUNT:
			
			sql.append("select count(1)");
			
			break;
		default:
			
			sql.append("select count(1)");
			break;
		}
		
		sql.append(" FROM  BIZ_T_GG_WARNING_MANAGE  " );
		sql.append( " WHERE 1=1 AND  DEL_FLAG=0");
		 if(MapUtil.hasParam(pars,"csOrgId")){
		 sql.append(" AND  BELONG_WSC_ID IN");
		 sql.append("  (SELECT O.ORGID");
		 sql.append("  FROM SYS_ORG O");
		 sql.append("  WHERE O.ORGTYPEID IN (1, 2, 3) START WITH O.ORGID =:csOrgId");
		 sql.append("  CONNECT BY prior O.ORGID = O.PARENTORGID)");
		 parsCon.put("csOrgId", pars.get("csOrgId"));
		 }
		if(pars.containsKey("title")&&pars.get("title")!=null&&StringUtils.isNotBlank(pars.get("title").toString())){
			
			sql.append(" and title like :title");
			parsCon.put("title", "%"+pars.get("title")+"%");
			
		}

		if (pars.containsKey("warningType") && pars.get("warningType") != null
				&& StringUtils.isNotBlank(pars.get("warningType").toString())) {
			sql.append(" and  tx_type =:warningType");
			parsCon.put("warningType", pars.get("warningType"));
		}
		
		if (pars.containsKey("is_important") && pars.get("is_important") != null
				&& StringUtils.isNotBlank(pars.get("is_important").toString())) {
			sql.append("  and  is_important =:is_important");
			parsCon.put("is_important", pars.get("is_important"));
		}
		
		if (pars.containsKey("warningId") && pars.get("warningId") != null
				&& StringUtils.isNotBlank(pars.get("warningId").toString())) {
			sql.append(" and warning_id =:warningIdCon");
			parsCon.put("warningIdCon", pars.get("warningId"));
		}
		
		if(MapUtil.hasParam(pars, "content")){
			sql.append(" AND CONTENT LIKE :content");
			parsCon.put("content", "%"+ pars.get("content")+"%");
			
		}
		
		sql.append(" ORDER BY CREATE_TIMESTEMP DESC");
		return  new Object[]{sql,parsCon};
		
		
	}

	@Override
	public void deleteSplitWarnings(String warningids) {
		String[] ids = warningids.split(",");
		  for (String id : ids) {
			  if(StringUtils.isNotBlank(id)){
				  BizTGgWarningManageEntity entity = bizTGgWarningManageDao.findOne(id);
				  entity.setDelFlag(1);//把实体状态设置为1表示逻辑删除
				  bizTGgWarningManageDao.save(entity);
			
		}
		}
		
	}

	@Override
	public void deleteOneWarning(String warningid) {
		if(StringUtils.isNotBlank(warningid)){
			BizTGgWarningManageEntity entity = bizTGgWarningManageDao.findOne(warningid);
			  entity.setDelFlag(1);//把实体状态设置为1表示逻辑删除
			  bizTGgWarningManageDao.save(entity);	
		
	}

	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getWarningList(Map<String, Object> pars,
			int start, int length) {
		Object[] obj = this.getQuerySentence(pars, QueryType.LIST);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list =  bizTGgWarningManageDao.queryListBySql(obj[0].toString(), start, length,
				(Map<String, Object>) obj[1]);

		return list;
	}

	@Override
	public Boolean checkWarning(String id) {
		 String str="select distinct code  from BIZ_T_GG_SBSS_ATTACH_BASE where  CONF_TYPE='2' and  detail_id =:id ";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", id );
			List<Map<String, Object>> queryListBySql =bizTGgWarningManageDao .queryListBySql(str,params );
			if(queryListBySql.size()>0){
				return true;
			}
			return false;
	}

	@Override
	public Map<String, Object> getRelatedData(PageBean pageBean, String spotId,
			WSLoginInfoBean loginInfo) {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT D.ATTACH_BASE_ID,B.TITLE,DBMS_LOB.SUBSTR(B.CONTENT) AS CONTENT,B.IS_IMPORTANT ");
		sql.append(" FROM BIZ_T_GG_WARNING_MANAGE B ");
		sql.append(" LEFT JOIN BIZ_T_GG_SBSS_ATTACH_BASE D ON B.WARNING_ID = D.DETAIL_ID");
		sql.append(" WHERE 1 = 1 ");
		sql.append(" AND B.DEL_FLAG = '0' ");
	    sql.append(" AND D.CODE ='"+ spotId +"'");
	    sql.append(" ORDER BY D.CREATE_TIMESTEMP DESC ");
	  
		return bizTGgWarningManageDao.queryPaginationListBySql(sql.toString(), new HashMap<String,Object>(), pageBean);
	}

	@Override
	public Map<String, Object> getNoRelatedData(String spotId,PageBean pageBean,
			Map<String, Object> params, WSLoginInfoBean loginInfo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT FA.* FROM ( ");
		sql.append(" SELECT  B.WARNING_ID,B.TITLE,DBMS_LOB.SUBSTR(B.CONTENT) AS CONTENT,B.IS_IMPORTANT");
		sql.append(" FROM BIZ_T_GG_WARNING_MANAGE B WHERE 1=1");
		sql.append(" AND B.DEL_FLAG = '0' ");
		sql.append(" AND B.TX_TYPE = '3' ");
		sql.append(" AND B.WARNING_ID NOT IN (SELECT D.DETAIL_ID FROM BIZ_T_GG_WARNING_MANAGE B ");
		sql.append(" LEFT JOIN BIZ_T_GG_SBSS_ATTACH_BASE D ON B.WARNING_ID = D.DETAIL_ID ");
        sql.append("  WHERE 1 = 1  AND B.DEL_FLAG = '0' AND D.CODE = '"+ spotId +"' )");          
		sql.append(" AND B.BELONG_WSC_ID ='"+loginInfo.getCsOrgId()+"' ");
		if(null != params){
			if(params.containsKey("eqName")){
				sql.append(" AND B.TITLE LIKE '%"+params.get("eqName").toString()+"%' ");
			}
			if(params.containsKey("setAddress")){
				sql.append(" AND B.CONTENT LIKE '%"+params.get("setAddress").toString()+"%' ");
			}
		}   
		sql.append(" ) FA ");
		
		return bizTGgWarningManageDao.queryPaginationListBySql(sql.toString(), new HashMap<String,Object>(), pageBean);
	}

	@Override
	public List<Map<String, Object>> getWaningListByTxType(String attach_code, String tx_type) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT T.WARNING_ID,");
		sql.append("              T.TITLE,");
		sql.append("              DBMS_LOB.SUBSTR(T.CONTENT) CONTENT,");
		sql.append("              T.TITLE_ICO,");
		sql.append("              T.TX_TYPE,");
		sql.append("              T.IS_HAVE_READ,");
		sql.append("              T.IS_IMPORTANT");
		sql.append("         from BIZ_T_GG_SBSS_ATTACH_BASE A, BIZ_T_GG_WARNING_MANAGE T");
		sql.append("        WHERE A.SB_OR_SS = '"+tx_type+"'");
		sql.append("          AND A.CONF_TYPE = '"+Constant.PZ_TYPE.SAFETYPE.getValue()+"'");
		sql.append("          AND A.CODE = '"+attach_code+"'");
		sql.append("          AND T.WARNING_ID = A.DETAIL_ID");
		return bizTGgWarningManageDao.queryListBySql(sql.toString(), new HashMap<String,Object>());
	}
	
}
