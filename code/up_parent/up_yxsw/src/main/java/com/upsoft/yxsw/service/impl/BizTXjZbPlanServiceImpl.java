package com.upsoft.yxsw.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.login.support.webservice.ServiceReceiver;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.constant.CommonConstant;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.system.util.DateUtil;
import com.upsoft.yxsw.dao.BizTXjZbPlanDao;
import com.upsoft.yxsw.dao.BizTXjZbPlanDetailDao;
import com.upsoft.yxsw.dao.BizTXjZbPlanDetailPersonDao;
import com.upsoft.yxsw.entity.BizTXjZbPlan;
import com.upsoft.yxsw.entity.BizTXjZbPlanDetail;
import com.upsoft.yxsw.entity.BizTXjZbPlanDetailPerson;
import com.upsoft.yxsw.service.BizTXjZbPlanService;


@Service
public class BizTXjZbPlanServiceImpl extends BaseServiceImpl implements BizTXjZbPlanService {

	@Autowired
	private BizTXjZbPlanDao bizTXjZbPlanDao;
	@Autowired
	private BizTXjZbPlanDetailDao bizTXjZbPlanDetailDao;
	@Autowired
	private BizTXjZbPlanDetailPersonDao bizTXjZbPlanDetailPersonDao;
	
	@Override
	public Map<String, Object> getZbPlanData(PageBean pageBean,
			Map<String, Object> params,WSLoginInfoBean loginInfo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT T.ZB_PLAN_ID, T.ZB_DATE, T.ZB_FZR_ID,T.ZB_FZR_MC,T.START_TIME,T.END_TIME,T.BYZD,T.VALID_FLAG,T.CREATE_TIMESTEMP,T.CREATOR_ACCOUNT,");
		sql.append(" T.CREATOR_NAME,T.BELONG_WSC_ID, T.BELONG_WSC_NAME, T.UPDATOR_ACCOUNT,T.UPDATOR_NAME, T.UPDATE_TIMESTEMP ");
		sql.append(" FROM BIZ_T_XJ_ZB_PLAN T WHERE 1=1 ");
		if(null != params){
			if(params.containsKey("zbFzrId")){
				sql.append(" AND T.ZB_FZR_ID = '"+params.get("zbFzrId").toString()+"' ");
			}
			if(params.containsKey("zbDate")){
				sql.append(" AND T.ZB_DATE = '"+ params.get("zbDate").toString().replaceAll("-", "") +"' ");
			}
			if(params.containsKey("org")){
				sql.append(" AND T.BELONG_WSC_ID = '"+ params.get("org").toString() +"' ");
			}
		}
		sql.append(" AND T.BELONG_WSC_ID IN ");
	    sql.append(" (SELECT O.ORGID FROM SYS_ORG O WHERE O.ORGTYPEID = '3' ");  
	    sql.append(" START WITH O.ORGID = '"+loginInfo.getCsOrgId()+"'"); 
	    sql.append(" CONNECT BY PRIOR O.ORGID = O.PARENTORGID) ");
	    sql.append(" ORDER BY T.BELONG_WSC_ID ASC,T.ZB_DATE DESC ");

		
		return bizTXjZbPlanDao.queryPaginationListBySql(sql.toString(), new HashMap<String,Object>(), pageBean);
	}
	
	@Override
	public Map<String, Object> getTimes(String csOrgId) {
		String sql = "SELECT T.START_TIME,T.END_TIME FROM BIZ_T_XJ_WORK_GROUP T WHERE T.BELONG_WSC_ID='"+csOrgId+"' ORDER BY T.UPDATE_TIMESTEMP DESC";
		List<Map<String,Object>> result = ServiceReceiver.getListDataBySql(sql, new HashMap<String, Object>());
		if(null != result && result.size() > 0 ){
			return result.get(0);
		}else{
			
			return new HashMap<String, Object>();
		}
	}

	@Override
	public Boolean dateExists(String validateValue,String csOrgId,String zbPlanId) {
		String sql = " SELECT * FROM BIZ_T_XJ_ZB_PLAN T WHERE 1=1 AND T.BELONG_WSC_ID = '"+ csOrgId + "'";
		if(StringUtils.isNotBlank(zbPlanId)){
			sql += " AND T.ZB_DATE='"+validateValue.replace("-", "")+"' AND T.ZB_PLAN_ID != '"+zbPlanId+"'";
		}else{
			sql += " AND T.ZB_DATE='"+validateValue.replace("-", "")+"'";
		}
		
		int count = bizTXjZbPlanDao.queryListBySql(sql,  new String[]{}).size();
		if(count > 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<Map<String, Object>> getBc(String csOrgId) {
		String sql = " SELECT T.DETAIL_ID,T.DETAIL_NAME FROM BIZ_T_XJ_WORK_GROUP_DETIAL T LEFT JOIN BIZ_T_XJ_WORK_GROUP D ON T.GROUP_ID = D.WORK_GROUP_ID WHERE D.BELONG_WSC_ID = '"+csOrgId+"'";
		
		return ServiceReceiver.getListDataBySql(sql, new HashMap<String, Object>());
	}

	@Override
	public List<Map<String, Object>> getGyd(String csOrgId) {
		String sql = "SELECT M.TECHNICS_ID,M.TECHNICS_NAME FROM BIZ_T_XJ_TECHNICS_SCOPE_MANAGE M WHERE M.DEL_FLAG = '0' AND M.BELONG_WSC_ID='"+csOrgId+"'";
		
		return ServiceReceiver.getListDataBySql(sql, new HashMap<String, Object>());
	}

	@Override
	public void saveZbPlan(List<Map<String,Object>> resultList, String zbDate, 
			 String startTime, String endTime,
			WSLoginInfoBean loginInfo) {
		Date date = new Date();
		//存储排班信息
		BizTXjZbPlan bizTXjZbPlan = new BizTXjZbPlan();
		bizTXjZbPlan.setZbDate(zbDate.replaceAll("-", ""));
		bizTXjZbPlan.setStartTime(startTime.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", ""));
		bizTXjZbPlan.setEndTime(endTime.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", ""));
		bizTXjZbPlan.setValidFlag(Long.parseLong(CommonConstant.STATUS_DISABLE));
		bizTXjZbPlan.setBelongWscId(loginInfo.getCsOrgId());
		bizTXjZbPlan.setBelongWscName(loginInfo.getCsOrgName());
		bizTXjZbPlan.setCreatorAccount(loginInfo.getUser().getUserId());
		bizTXjZbPlan.setCreatorName(loginInfo.getUser().getUserName());
		bizTXjZbPlan.setCreateTimestemp(DateUtil.dateToString(date, "yyyyMMddHHmmss"));
		bizTXjZbPlan.setUpdatorAccount(loginInfo.getUser().getUserId());
		bizTXjZbPlan.setUpdatorName(loginInfo.getUser().getUserName());
		bizTXjZbPlan.setUpdateTimestemp(DateUtil.dateToString(date, "yyyyMMddHHmmss"));
		
		BizTXjZbPlan nBizTXjZbPlan = bizTXjZbPlanDao.save(bizTXjZbPlan);
		//获取排班主表中的ID
		String zbPlanId = nBizTXjZbPlan.getZbPlanId();
		//将信息存到从表
		for (Map<String, Object> detail : resultList) {
			BizTXjZbPlanDetail bizTXjZbPlanDetail = new BizTXjZbPlanDetail();
			bizTXjZbPlanDetail.setZbPlanId(zbPlanId);
			bizTXjZbPlanDetail.setDetailId(detail.get("detail_id").toString());
			bizTXjZbPlanDetail.setDetailName(detail.get("detail_name").toString());
			bizTXjZbPlanDetail.setTechnicsId(detail.get("technics_id").toString());
			bizTXjZbPlanDetail.setTechnicsName(detail.get("technics_name").toString());
			BizTXjZbPlanDetail nBizTXjZbPlanDetail = bizTXjZbPlanDetailDao.save(bizTXjZbPlanDetail);
			//保存负责人
			String planDetailId = nBizTXjZbPlanDetail.getPlanDetailId();
			String[] peopleIds = detail.get("peopleId").toString().split(",");
			String[] peopleNames = detail.get("peopleName").toString().split(",");
			for (int i = 0; i < peopleIds.length; i++) {
				String peopleId = peopleIds[i];
				String peopleName = peopleNames[i];
				BizTXjZbPlanDetailPerson bizTXjZbPlanDetailPerson =new BizTXjZbPlanDetailPerson();
				bizTXjZbPlanDetailPerson.setPlanDetailId(planDetailId);
				bizTXjZbPlanDetailPerson.setPersonId(peopleId);
				bizTXjZbPlanDetailPerson.setPersonName(peopleName);
				
				bizTXjZbPlanDetailPersonDao.save(bizTXjZbPlanDetailPerson);
			}
		}
	}

	@Override
	public List<Map<String, Object>> findInfoList(String zbPlanId) {
		//String sql = "SELECT T.PLAN_DETAIL_ID,T.ZB_PLAN_ID,T.DETAIL_ID,T.DETAIL_NAME,T.TECHNICS_ID,T.TECHNICS_NAME, D.PLAN_DETAIL_PERSON_ID,D.PERSON_ID,D.PERSON_NAME FROM BIZ_T_XJ_ZB_PLAN_DETAIL T LEFT JOIN BIZ_T_XJ_ZB_PLAN_DETAIL_PERSON D ON T.PLAN_DETAIL_ID= D.PLAN_DETAIL_ID  WHERE T.ZB_PLAN_ID  = '"+zbPlanId+"'";
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT T.TECHNICS_ID,T.TECHNICS_NAME,T.DETAIL_ID,T.DETAIL_NAME,WMSYS.WM_CONCAT (D.PERSON_ID) AS PID,WMSYS.WM_CONCAT (D.PERSON_NAME) AS PNAME ");
		sql.append(" FROM BIZ_T_XJ_ZB_PLAN_DETAIL T "); 
	    sql.append(" LEFT JOIN BIZ_T_XJ_ZB_PLAN_DETAIL_PERSON D ON T.PLAN_DETAIL_ID = D.PLAN_DETAIL_ID ");  
	    sql.append(" LEFT JOIN BIZ_T_XJ_WORK_GROUP_DETIAL GD ON GD.DETAIL_ID = T.DETAIL_ID ");
	    sql.append(" WHERE T.ZB_PLAN_ID = '"+ zbPlanId +"'");   
	    sql.append(" GROUP BY T.DETAIL_ID,T.DETAIL_NAME,T.TECHNICS_ID,T.TECHNICS_NAME,GD.SORT_NUM ");   
	    sql.append(" ORDER BY GD.SORT_NUM ASC ");   
	 
		return ServiceReceiver.getListDataBySql(sql.toString(), new HashMap<String, Object>());
	}

	@Override
	public void delZbPlan(String id) {
		String sql1= "DELETE FROM BIZ_T_XJ_ZB_PLAN_DETAIL_PERSON P WHERE P.PLAN_DETAIL_ID IN (SELECT D.PLAN_DETAIL_ID FROM BIZ_T_XJ_ZB_PLAN_DETAIL D WHERE D.ZB_PLAN_Id ='"+id+"' )";
		bizTXjZbPlanDetailPersonDao.executeSql(sql1, new HashMap<String,Object>());
		String sql2 = "DELETE  FROM BIZ_T_XJ_ZB_PLAN_DETAIL D WHERE D.ZB_PLAN_ID ='"+id+"'";
		bizTXjZbPlanDetailPersonDao.executeSql(sql2, new HashMap<String,Object>());
		
		bizTXjZbPlanDao.delete(findOne(BizTXjZbPlan.class,id));
	}

	@Override
	public List<Map<String, Object>> getGydIndetail(String zbPlanId) {
		String sql = "SELECT DISTINCT D.TECHNICS_ID,D.TECHNICS_NAME FROM BIZ_T_XJ_ZB_PLAN_DETAIL D WHERE D.ZB_PLAN_ID ='"+zbPlanId+"' ORDER BY D.TECHNICS_ID ASC";
		
		return ServiceReceiver.getListDataBySql(sql, new HashMap<String, Object>());
	}

	@Override
	public List<Map<String, Object>> getBcIndetail(String zbPlanId) {
		String sql ="SELECT DISTINCT D.DETAIL_ID,D.DETAIL_NAME FROM BIZ_T_XJ_ZB_PLAN_DETAIL D WHERE D.ZB_PLAN_ID ='"+zbPlanId+"' ORDER BY D.DETAIL_ID ASC ";
		
		return ServiceReceiver.getListDataBySql(sql, new HashMap<String, Object>());
	}

	@Override
	public void updateZbPlan(BizTXjZbPlan bizTXjZbPlan, String zbDate,
			 String startTime, String endTime,
			List<Map<String, Object>> detailList, WSLoginInfoBean loginInfo) {
		
		String sql1= "DELETE FROM BIZ_T_XJ_ZB_PLAN_DETAIL_PERSON P WHERE P.PLAN_DETAIL_ID IN (SELECT D.PLAN_DETAIL_ID FROM BIZ_T_XJ_ZB_PLAN_DETAIL D WHERE D.ZB_PLAN_Id ='"+bizTXjZbPlan.getZbPlanId()+"' )";
		bizTXjZbPlanDao.executeSql(sql1, new HashMap<String,Object>());
		String sql2 = "DELETE  FROM BIZ_T_XJ_ZB_PLAN_DETAIL D WHERE D.ZB_PLAN_ID ='"+bizTXjZbPlan.getZbPlanId()+"'";
		bizTXjZbPlanDao.executeSql(sql2, new HashMap<String,Object>());
		
		Date date = new Date();
		//存储排班信息
		bizTXjZbPlan.setZbDate(zbDate.replaceAll("-", ""));
		bizTXjZbPlan.setStartTime(startTime.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", ""));
		bizTXjZbPlan.setEndTime(endTime.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", ""));
		bizTXjZbPlan.setUpdatorAccount(loginInfo.getUser().getUserId());
		bizTXjZbPlan.setUpdatorName(loginInfo.getUser().getUserName());
		bizTXjZbPlan.setUpdateTimestemp(DateUtil.dateToString(date, "yyyyMMddHHmmss"));
		
		bizTXjZbPlanDao.save(bizTXjZbPlan);
		
		//将信息存到从表
		for (Map<String, Object> detail : detailList) {
			BizTXjZbPlanDetail bizTXjZbPlanDetail = new BizTXjZbPlanDetail();
			bizTXjZbPlanDetail.setZbPlanId(bizTXjZbPlan.getZbPlanId());
			bizTXjZbPlanDetail.setDetailId(detail.get("detail_id").toString());
			bizTXjZbPlanDetail.setDetailName(detail.get("detail_name").toString());
			bizTXjZbPlanDetail.setTechnicsId(detail.get("technics_id").toString());
			bizTXjZbPlanDetail.setTechnicsName(detail.get("technics_name").toString());
			BizTXjZbPlanDetail nBizTXjZbPlanDetail = bizTXjZbPlanDetailDao.save(bizTXjZbPlanDetail);
			//保存负责人
			String planDetailId = nBizTXjZbPlanDetail.getPlanDetailId();
			String[] peopleIds = detail.get("peopleId").toString().split(",");
			String[] peopleNames = detail.get("peopleName").toString().split(",");
			for (int i = 0; i < peopleIds.length; i++) {
				String peopleId = peopleIds[i];
				String peopleName = peopleNames[i];
				BizTXjZbPlanDetailPerson bizTXjZbPlanDetailPerson =new BizTXjZbPlanDetailPerson();
				bizTXjZbPlanDetailPerson.setPlanDetailId(planDetailId);
				bizTXjZbPlanDetailPerson.setPersonId(peopleId);
				bizTXjZbPlanDetailPerson.setPersonName(peopleName);
				
				bizTXjZbPlanDetailPersonDao.save(bizTXjZbPlanDetailPerson);
			}
		}
		
	}

	@Override
	public void deleteZbPlanByPeriod(List<String> periodDetailIds) {
		
		StringBuilder sqlZbPlan = new StringBuilder() , sqlDetail = new StringBuilder(), sqlDetailPerson = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("periodDetailIds", periodDetailIds);
		sqlDetailPerson.append("DELETE FROM BIZ_T_XJ_ZB_PLAN_DETAIL_PERSON DP WHERE DP.PLAN_DETAIL_ID IN (SELECT D.PLAN_DETAIL_ID FROM BIZ_T_XJ_ZB_PLAN_DETAIL D LEFT JOIN BIZ_T_XJ_ZB_PLAN P ON D.ZB_PLAN_ID = P.ZB_PLAN_ID WHERE D.DETAIL_ID IN (:periodDetailIds) AND P.VALID_FLAG = '0')");
		sqlDetail.append("DELETE FROM BIZ_T_XJ_ZB_PLAN_DETAIL D WHERE D.DETAIL_ID IN (:periodDetailIds) AND D.ZB_PLAN_ID IN (SELECT P.ZB_PLAN_ID FROM BIZ_T_XJ_ZB_PLAN P WHERE P.VALID_FLAG = '0')");
//		sqlDetail.append("DELETE FROM BIZ_T_XJ_ZB_PLAN_DETAIL D WHERE D.ZB_PLAN_ID IN (SELECT P.ZB_PLAN_ID FROM BIZ_T_XJ_ZB_PLAN P WHERE P.VALID_FLAG = '0' AND P.ZB_PLAN_ID IN (SELECT D.ZB_PLAN_ID FROM BIZ_T_XJ_ZB_PLAN_DETAIL D WHERE D.DETAIL_ID IN (:periodDetailIds)))");
		sqlZbPlan.append("DELETE FROM BIZ_T_XJ_ZB_PLAN P WHERE P.ZB_PLAN_ID IN (SELECT D.ZB_PLAN_ID FROM BIZ_T_XJ_ZB_PLAN_DETAIL D WHERE D.DETAIL_ID IN (:periodDetailIds)) AND P.VALID_FLAG = '0'");
		bizTXjZbPlanDetailPersonDao.executeSql(sqlDetailPerson.toString(), params);
		bizTXjZbPlanDao.executeSql(sqlZbPlan.toString(), params);
		bizTXjZbPlanDetailDao.executeSql(sqlDetail.toString(), params);	//人员排班详情作为连接排班和班次的关键，需最后删除
	}

	@Override
	public List<BizTXjZbPlan> getNoEffectList(String csOrgId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM BIZ_T_XJ_ZB_PLAN T WHERE 1=1 AND T.VALID_FLAG='0' ");
		sql.append(" AND T.BELONG_WSC_ID IN ");
	    sql.append(" (SELECT O.ORGID FROM SYS_ORG O WHERE O.ORGTYPEID = '3' ");  
	    sql.append(" START WITH O.ORGID = '"+csOrgId+"'"); 
	    sql.append(" CONNECT BY PRIOR O.ORGID = O.PARENTORGID) ");
		return bizTXjZbPlanDao.queryListBySql(sql.toString(), new HashMap<String, Object>(),BizTXjZbPlan.class);
	}
	
}
