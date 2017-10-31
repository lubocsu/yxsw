package com.upsoft.yxsw.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.login.support.webservice.ServiceReceiver;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.system.util.DateUtil;
import com.upsoft.yxsw.constant.DictConstant;
import com.upsoft.yxsw.controller.cxMake.bean.CxMakeDetailPojo;
import com.upsoft.yxsw.dao.BizTXjZypTemplateDao;
import com.upsoft.yxsw.dao.BizTXjZypTemplateItmDao;
import com.upsoft.yxsw.dao.BizTXjZypTemplateItmDetDao;
import com.upsoft.yxsw.entity.BizTXjZypCxMakeTmp;
import com.upsoft.yxsw.entity.BizTXjZypCxMakeTmpItem;
import com.upsoft.yxsw.entity.BizTXjZypTemplate;
import com.upsoft.yxsw.entity.BizTXjZypTemplateItm;
import com.upsoft.yxsw.service.BizTXjZypTemplateService;


@Service
public class BizTXjZypTemplateServiceImpl extends BaseServiceImpl implements BizTXjZypTemplateService {
	
	@Autowired
	private BizTXjZypTemplateDao zypTemplateDao;
	@Autowired
	private BizTXjZypTemplateItmDao itmDao;
	@Autowired
	private BizTXjZypTemplateItmDetDao itmDetDao;
	
	private String NVL(Object o){ return null!=o?o.toString():""; }
	
	/**
	 * 获取作业票配置列表
	 * @date 2017-09-27
	 * @author 
	 * @param bean
	 * @param params
	 * @return 
	 */
	public Map<String, Object> getList(PageBean bean, Map<String, Object> params){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT T.TEMP_ID, T.TEMP_NAME, T.TEMP_VERSION, T.CREATE_TIMESTEMP, T.CREATOR_ACCOUNT, T.CREATOR_NAME, T.BELONG_WSC_ID, T.BELONG_WSC_NAME, T.BELONG_DEPT, T.UPDATOR_ACCOUNT, T.UPDATOR_NAME, T.UPDATE_TIMESTEMP FROM BIZ_T_XJ_ZYP_TEMPLATE T");
		sql.append(" WHERE 1=1 ");
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		if(null != params.get("tempName") && StringUtils.isNotBlank(params.get("tempName").toString())){
			sql.append(" and T.TEMP_NAME like :tempName ");
			paramsMap.put("tempName", MessageFormat.format("%{0}%", params.get("tempName").toString()));
		}
		if(null != params.get("orgId") && StringUtils.isNotBlank(params.get("orgId").toString())){
			sql.append(" and T.BELONG_WSC_ID = :orgId ");
			paramsMap.put("orgId", params.get("orgId").toString());
		}
		
		return zypTemplateDao.queryPaginationListBySql(sql.toString(), paramsMap, bean);
	}
	
	public BizTXjZypTemplate save(BizTXjZypTemplate bizTXjZypTemplate){
		return zypTemplateDao.save(bizTXjZypTemplate);
	}
	
	public BizTXjZypTemplate getBizTXjZypTemplateById(String id){
		return zypTemplateDao.findOne(id);
	}

	@Override
	public BizTXjZypTemplate getTempByOrg(String csOrgId) {
		
		StringBuilder sql = new StringBuilder(" SELECT TEMP_ID, TEMP_NAME, TEMP_VERSION, CREATE_TIMESTEMP, CREATOR_ACCOUNT, CREATOR_NAME, BELONG_WSC_ID, BELONG_WSC_NAME, BELONG_DEPT, UPDATOR_ACCOUNT, UPDATOR_NAME, UPDATE_TIMESTEMP, TEMP_DESC, IS_VAILD FROM BIZ_T_XJ_ZYP_TEMPLATE t ");
		sql.append(" WHERE t.IS_VAILD = 1 AND t.BELONG_WSC_ID = :csOrgId ");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("csOrgId", csOrgId);
		List<BizTXjZypTemplate> tempList = zypTemplateDao.queryListBySql(sql.toString(), params, BizTXjZypTemplate.class);
		if(tempList.isEmpty()){
			return null;
		}else{
			return tempList.get(0);
		}
	}
	
	@Override
	public CxMakeDetailPojo getZypTempInfo(String tempId) {
		
		// 获取作业票基本信息
		String tempSql = "SELECT TEMP_ID, TEMP_NAME, TEMP_VERSION, CREATE_TIMESTEMP, CREATOR_ACCOUNT, CREATOR_NAME, BELONG_WSC_ID, BELONG_WSC_NAME, BELONG_DEPT, UPDATOR_ACCOUNT, UPDATOR_NAME, UPDATE_TIMESTEMP, TEMP_DESC, IS_VAILD FROM BIZ_T_XJ_ZYP_TEMPLATE t WHERE t.IS_VAILD = 1 AND t.TEMP_ID = '"+tempId+"'";
		List<BizTXjZypTemplate> tempList = zypTemplateDao.queryListBySql(tempSql, new HashMap<String,Object>(), BizTXjZypTemplate.class);
		BizTXjZypTemplate temp = tempList.get(0);	//模版信息
		// 获取作业票指标组
		String tmpItmSql = "SELECT ZXP_TEMP_ITM_ID, ZXP_TEMP_ITM_NAME, TEMP_ID, JLXDZ, JLSBZ, JLQCL, CONF_DESC, ZXP_TEMP_SORT, BYZD, CREATE_TIMESTEMP, CREATOR_ACCOUNT, CREATOR_NAME, UPDATOR_ACCOUNT, UPDATOR_NAME, UPDATE_TIMESTEMP FROM BIZ_T_XJ_ZYP_TEMPLATE_ITM t WHERE T.TEMP_ID='"+tempId+"' ORDER BY T.ZXP_TEMP_SORT ASC";
		List<BizTXjZypTemplateItm> tmpItmList = itmDao.queryListBySql(tmpItmSql,  new HashMap<String,Object>(), BizTXjZypTemplateItm.class);	//模版信息
		List<BizTXjZypCxMakeTmp> makeTmpList = new ArrayList<BizTXjZypCxMakeTmp>();	//拟定表信息
		// 获取指标组具体指标
		for (BizTXjZypTemplateItm item : tmpItmList) {
			
			BizTXjZypCxMakeTmp cxMakeTmp = new BizTXjZypCxMakeTmp();
			cxMakeTmp.setZxpTempSort(item.getZxpTempSort());
			cxMakeTmp.setZxpTempItmName(item.getZxpTempItmName());
			cxMakeTmp.setZxpTempItmId(item.getZxpTempItmId());
			cxMakeTmp.setJlxdz(item.getJlxdz());
			cxMakeTmp.setJlsbz(item.getJlsbz());
			cxMakeTmp.setJlqcl(item.getJlqcl());
			cxMakeTmp.setByzd(item.getByzd());
			String itmDetSql = "SELECT t.DETAIL_ID, t.ZXP_TEMP_ITM_ID, t.CXZB_ID, t.CXZB_NAME, t.CXZB_JC, t.DETAIL_SORT, t.BYZD, zb.CXZB_TBLX FROM BIZ_T_XJ_ZYP_TEMPLATE_ITM_DET t "
								 + " LEFT JOIN BIZ_T_XJ_ZB_ITEM ZB ON ZB.CXZB_ID=T.CXZB_ID WHERE T.ZXP_TEMP_ITM_ID='"+item.getZxpTempItmId()+"' ORDER BY ZB.CXZB_TBLX DESC, ZB.CXZB_ID ASC";
			List<Map<String, Object>> itmDetList = itmDetDao.queryListBySql(itmDetSql, new HashMap<String,Object>());
			List<BizTXjZypCxMakeTmpItem> makeTmpItemList = new ArrayList<BizTXjZypCxMakeTmpItem>();
			for (Map<String, Object> map : itmDetList) {
				BizTXjZypCxMakeTmpItem tmpItem = new BizTXjZypCxMakeTmpItem();
				tmpItem.setCxzbId(NVL(map.get("cxzb_id")));
				tmpItem.setCxzbName(NVL(map.get("cxzb_name")));
				tmpItem.setCxzbJc(NVL(map.get("cxzb_jc")));
				tmpItem.setByzd(NVL(map.get("byzd")));
				tmpItem.setTblx(NVL(map.get("cxzb_tblx")));
				makeTmpItemList.add(tmpItem);
				
			}
			cxMakeTmp.setMakeTmpItemList(makeTmpItemList);
			makeTmpList.add(cxMakeTmp);
		}		
		CxMakeDetailPojo detail = new CxMakeDetailPojo();
		detail.setTempVersion(temp.getTempVersion());
		detail.setMakeTmpList(makeTmpList);
		return detail;
	}
}
