package com.upsoft.yxsw.service.impl;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.constant.CommonConstant;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.yxsw.dao.BizTXjZypTemplateItmDao;
import com.upsoft.yxsw.entity.BizTXjZypTemplateItm;
import com.upsoft.yxsw.service.BizTXjZypTemplateItmService;


@Service
public class BizTXjZypTemplateItmServiceImpl extends BaseServiceImpl implements BizTXjZypTemplateItmService {
	
	@Autowired
	private BizTXjZypTemplateItmDao templateItmDao;
	
	public Map<String, Object> getList(PageBean bean, Map<String, Object> params){
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT I.ZXP_TEMP_ITM_ID, I.ZXP_TEMP_ITM_NAME, I.TEMP_ID, I.JLXDZ, I.JLSBZ, I.JLQCL, I.CONF_DESC, I.ZXP_TEMP_SORT, I.BYZD, I.CREATE_TIMESTEMP, I.CREATOR_ACCOUNT, I.CREATOR_NAME, I.UPDATOR_ACCOUNT, I.UPDATOR_NAME, I.UPDATE_TIMESTEMP FROM BIZ_T_XJ_ZYP_TEMPLATE_ITM I ");
		sql.append(" WHERE 1=1 ");
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		if(null != params.get("tempItmName") && StringUtils.isNotBlank(params.get("tempItmName").toString())){
			sql.append(" and T.ZXP_TEMP_ITM_NAME like :tempItmName ");
			paramsMap.put("tempItmName", MessageFormat.format("%{0}%", params.get("tempItmName").toString()));
		}
		if(null != params.get("tempId") && StringUtils.isNotBlank(params.get("tempId").toString())){
			sql.append(" and I.TEMP_ID = :tempId ");
			paramsMap.put("tempId", params.get("tempId").toString());
		}
		
		return templateItmDao.queryPaginationListBySql(sql.toString(), paramsMap, bean);
	}
	
	public BizTXjZypTemplateItm save(BizTXjZypTemplateItm bizTXjZypTemplateItm){
		return templateItmDao.save(bizTXjZypTemplateItm);
	
	}
	
	public BizTXjZypTemplateItm getBizTXjZypTemplateItmById(String id){
		return templateItmDao.findOne(id);
	
	}

	@Override
	public Map<String, Object> getZbxList(PageBean bean, Map<String, Object> params) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tid.DETAIL_ID, tid.ZXP_TEMP_ITM_ID, tid.CXZB_ID, tid.CXZB_NAME, tid.CXZB_JC, tid.DETAIL_SORT, tid.BYZD, zi.CXZB_CODE ");
		sql.append(" FROM BIZ_T_XJ_ZYP_TEMPLATE_ITM_DET tid");
		sql.append(" LEFT JOIN BIZ_T_XJ_ZB_ITEM zi ON tid.CXZB_ID = zi.CXZB_ID ");
		sql.append(" WHERE (zi.DEL_FLAG = " + CommonConstant.STATUS_YoN.NO + " OR zi.DEL_FLAG IS NULL) ");
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		if(null != params.get("zxpTempItmId") && StringUtils.isNotBlank(params.get("zxpTempItmId").toString())){
			sql.append(" AND tid.ZXP_TEMP_ITM_ID = :zxpTempItmId ");
			paramsMap.put("zxpTempItmId", params.get("zxpTempItmId").toString());
		}
		
		return templateItmDao.queryPaginationListBySql(sql.toString(), paramsMap, bean);
	}

	@Override
	public void deleteById(String zxpTempItmId) {
		// TODO Auto-generated method stub
		templateItmDao.delete(findOne(BizTXjZypTemplateItm.class, zxpTempItmId));
		String sql = "DELETE FROM BIZ_T_XJ_ZYP_TEMPLATE_ITM_DET D WHERE D.ZXP_TEMP_ITM_ID = '" + zxpTempItmId + "' ";
		templateItmDao.executeSql(sql, new HashMap<String, Object>());
	}
}
