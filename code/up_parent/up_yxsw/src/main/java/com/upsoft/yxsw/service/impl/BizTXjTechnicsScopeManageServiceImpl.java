package com.upsoft.yxsw.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.impl.jam.mutable.MPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.login.support.webservice.ServiceReceiver;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.constant.CommonConstant;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.system.util.DateUtil;
import com.upsoft.yxsw.dao.BizTXjTechnicsScopeAttXjDao;
import com.upsoft.yxsw.dao.BizTXjTechnicsScopeManageDao;
import com.upsoft.yxsw.entity.BizTXjTechnicsScopeAttXj;
import com.upsoft.yxsw.entity.BizTXjTechnicsScopeManage;
import com.upsoft.yxsw.entity.BizTXjZbPlan;
import com.upsoft.yxsw.entity.BizTXjdItemDetail;
import com.upsoft.yxsw.service.BizTXjTechnicsScopeManageService;
import com.upsoft.yxsw.service.BizTXjZbPlanService;


@Service
public class BizTXjTechnicsScopeManageServiceImpl extends BaseServiceImpl implements BizTXjTechnicsScopeManageService {

	@Autowired
	private BizTXjTechnicsScopeManageDao bizTXjTechnicsScopeManageDao;
	@Autowired
	private BizTXjTechnicsScopeAttXjDao bizTXjTechnicsScopeAttXjDao;
	@Autowired
	private BizTXjZbPlanService bizTXjZbPlanService;
	
	@Override
	public List<Map<String, Object>> queryByTreeId(WSLoginInfoBean loginInfo,String csOrg) {
		
		String csId = loginInfo.getCsOrgId();
		StringBuffer oSql = new StringBuffer();
		StringBuffer tSql = new StringBuffer();
		List<String> orgIds  = new ArrayList<String>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> orgList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> allList = new ArrayList<Map<String,Object>>();
		oSql.append(" SELECT T.ORGID ID,T.PARENTORGID PARENTID,T.Orgname NAME,t.orgtypeid typeIds FROM SYS_ORG  T ");
		oSql.append(" WHERE 1 = 1 ");
		if(StringUtils.isNotBlank(csOrg)){
			oSql.append(" AND T.ORGID ='"+csOrg+"'");
		}else{
			
			oSql.append(" AND T.ORGTYPEID IN ('"+CommonConstant.orgType.FACTORY.getKey()+"') ");
		}
		oSql.append(" START WITH (T.ORGID = '"+csId+"') ");
		oSql.append(" CONNECT BY PRIOR T.ORGID = T.PARENTORGID ");
		oSql.append(" ORDER BY T.ORGNAME DESC ");
		orgList = ServiceReceiver.getListDataBySql(oSql.toString(), new HashMap<String,Object>());
		allList.addAll(orgList);
		if(null != orgList && orgList.size()>0){
			for (Map<String, Object> org : orgList) {
				String orgId = org.get("id").toString();
				orgIds.add("'"+orgId+"'");
			}
		}
		tSql.append(" SELECT T.TECHNICS_ID ID,T.BELONG_WSC_ID PARENTID,T.TECHNICS_NAME NAME,'' typeIds FROM BIZ_T_XJ_TECHNICS_SCOPE_MANAGE T ");
		tSql.append(" WHERE T.BELONG_WSC_ID IN ("+StringUtils.join(orgIds.toArray(), ",")+")");
		tSql.append(" AND T.DEL_FLAG='0' ");
		list = bizTXjTechnicsScopeManageDao.queryListBySql(tSql.toString(),new HashMap<String,Object>());
		allList.addAll(list);
		
		if(null!=allList&& allList.size()>0){
			
			return allList;
		}else{
			return new ArrayList<Map<String,Object>>();
		}
	}

	@Override
	public BizTXjTechnicsScopeManage saveTechnicsScope(
			BizTXjTechnicsScopeManage bizTXjTechnicsScopeManage,
			WSLoginInfoBean loginInfo) {
		String userId = loginInfo.getUser().getUserId();
		String userName = loginInfo.getUser().getUserName();
		Date date = new Date();
		String csOrgId = loginInfo.getCsOrgId();
		String csOrgName = loginInfo.getCsOrgName();
		bizTXjTechnicsScopeManage.setBelongWscId(csOrgId);
		bizTXjTechnicsScopeManage.setBelongWscnAme(csOrgName);
		bizTXjTechnicsScopeManage.setCreatorAccount(userId);
		bizTXjTechnicsScopeManage.setCreatorName(userName);
		bizTXjTechnicsScopeManage.setCreateTimestemp(DateUtil.dateToString(date, "yyyyMMddHHmmss"));
		bizTXjTechnicsScopeManage.setUpdatorAccount(userId);
		bizTXjTechnicsScopeManage.setUpdatorName(userName);
		bizTXjTechnicsScopeManage.setUpdateTimestemp(DateUtil.dateToString(date, "yyyyMMddHHmmss"));
		bizTXjTechnicsScopeManage.setDelFlag(Long.parseLong(CommonConstant.STATUS_DISABLE));
		
		BizTXjTechnicsScopeManage result = bizTXjTechnicsScopeManageDao.save(bizTXjTechnicsScopeManage);
		//获取所有未生效的厂巡排班并全部删除
		List<BizTXjZbPlan> zbPlanList =  bizTXjZbPlanService.getNoEffectList(loginInfo.getCsOrgId());
		for (BizTXjZbPlan zbPlan : zbPlanList) {
			
			bizTXjZbPlanService.delZbPlan(zbPlan.getZbPlanId());
		}
		return result;
	}

	@Override
	public BizTXjTechnicsScopeManage findOne(String technicsId) {
		
		return bizTXjTechnicsScopeManageDao.findOne(technicsId);
	}

	@Override
	public void saveRelated(String technicsId, List<String> idArray,
			List<String> nameArray, String value) {
		BizTXjTechnicsScopeAttXj bizTXjTechnicsScopeAttXj;
		for (int i = 0; i < idArray.size(); i++) {
			bizTXjTechnicsScopeAttXj = new BizTXjTechnicsScopeAttXj();
			 
			bizTXjTechnicsScopeAttXj.setTechnicsId(technicsId);
			bizTXjTechnicsScopeAttXj.setXjdItemId(idArray.get(i));
			bizTXjTechnicsScopeAttXj.setXjdItemName(nameArray.get(i));
			
			bizTXjTechnicsScopeAttXjDao.save(bizTXjTechnicsScopeAttXj);
		}
	}
	
	@Override
	public void delRelated(List<String> idArray) {
		for (String id : idArray) {
			
			bizTXjTechnicsScopeAttXjDao.delete(findOne(BizTXjTechnicsScopeAttXj.class,id));
		}
		
	}

	@Override
	public void updateTechnicsScope(
			BizTXjTechnicsScopeManage bizTXjTechnicsScopeManage,
			WSLoginInfoBean loginInfo) {
		
		String userId = loginInfo.getUser().getUserId();
		String userName = loginInfo.getUser().getUserName();
		Date date = new Date();
		bizTXjTechnicsScopeManage.setUpdatorAccount(userId);
		bizTXjTechnicsScopeManage.setUpdatorName(userName);
		bizTXjTechnicsScopeManage.setUpdateTimestemp(DateUtil.dateToString(date, "yyyyMMddHHmmss"));
		
		bizTXjTechnicsScopeManageDao.save(bizTXjTechnicsScopeManage);
	}

	@Override
	public void delTechnicsScope(String technicsScopeId,
			WSLoginInfoBean loginInfo) {
		//删除巡检信息
		String sql1 = " DELETE  FROM BIZ_T_XJ_FREQ_SEGMENT_SBSS T WHERE T.TECHNICS_ID='"+technicsScopeId+"'";
		bizTXjTechnicsScopeManageDao.executeSql(sql1, new HashMap<String,Object>());
		//删除排班信息
//		String sql4 = " DELETE FROM BIZ_T_XJ_ZB_PLAN_DETAIL_PERSON PDP WHERE PDP.PLAN_DETAIL_ID IN (SELECT ZPD.PLAN_DETAIL_ID FROM BIZ_T_XJ_ZB_PLAN_DETAIL ZPD WHERE ZPD.ZB_PLAN_ID IN (SELECT ZP.ZB_PLAN_ID FROM BIZ_T_XJ_ZB_PLAN ZP WHERE ZP.VALID_FLAG='0')) ";
//		bizTXjTechnicsScopeManageDao.executeSql(sql4, new HashMap<String,Object>());
//		String sql2 = " DELETE  FROM BIZ_T_XJ_ZB_PLAN_DETAIL ZPD WHERE ZPD.ZB_PLAN_ID IN (SELECT ZP.ZB_PLAN_ID FROM BIZ_T_XJ_ZB_PLAN ZP WHERE ZP.VALID_FLAG='0')";
//		bizTXjTechnicsScopeManageDao.executeSql(sql2, new HashMap<String,Object>());
//		String sql3 = " DELETE FROM BIZ_T_XJ_ZB_PLAN ZP1 WHERE ZP1.VALID_FLAG='0' ";
//		bizTXjTechnicsScopeManageDao.executeSql(sql3, new HashMap<String,Object>());
		//获取所有未生效的厂巡排班并全部删除
		List<BizTXjZbPlan> zbPlanList =  bizTXjZbPlanService.getNoEffectList(loginInfo.getCsOrgId());
		for (BizTXjZbPlan zbPlan : zbPlanList) {
					
			bizTXjZbPlanService.delZbPlan(zbPlan.getZbPlanId());
		}
		//修改工艺段状态
		BizTXjTechnicsScopeManage bizTXjTechnicsScopeManage = bizTXjTechnicsScopeManageDao.findOne(technicsScopeId);
		bizTXjTechnicsScopeManage.setDelFlag(Long.parseLong(CommonConstant.STATUS_VALID));
		bizTXjTechnicsScopeManage.setUpdatorAccount(loginInfo.getUser().getUserId());
		bizTXjTechnicsScopeManage.setUpdatorName(loginInfo.getUser().getUserName());
		bizTXjTechnicsScopeManage.setUpdateTimestemp(DateUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
		bizTXjTechnicsScopeManageDao.save(bizTXjTechnicsScopeManage);
	}
}
