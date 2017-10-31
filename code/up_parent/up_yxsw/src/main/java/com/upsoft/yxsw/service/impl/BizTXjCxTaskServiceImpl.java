package com.upsoft.yxsw.service.impl;

import java.util.ArrayList;
import java.util.Date;
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
import com.upsoft.system.util.DateUtil;
import com.upsoft.system.util.ExceptionFormatUtil;
import com.upsoft.system.util.MapUtil;
import com.upsoft.yxsw.constant.Constant;
import com.upsoft.yxsw.dao.BizTXjCxTaskDAO;
import com.upsoft.yxsw.entity.BizTXjCxTask;
import com.upsoft.yxsw.entity.BizTXjCxTaskPersons;
import com.upsoft.yxsw.mobile.bean.task.TaskListBean;
import com.upsoft.yxsw.mobile.bean.task.TaskPerson;
import com.upsoft.yxsw.service.BizTGgMsgManageService;
import com.upsoft.yxsw.service.BizTXjCxTaskPersonsService;
import com.upsoft.yxsw.service.BizTXjCxTaskService;

@Service
public class BizTXjCxTaskServiceImpl extends BaseServiceImpl implements BizTXjCxTaskService {
	
	private Logger logger = Logger.getLogger(BizTXjCxTaskServiceImpl.class);
	
	@Autowired
	private BizTXjCxTaskDAO bizTXjCxTaskDAO;
	@Autowired
	private BizTXjCxTaskPersonsService bizTXjCxTaskPersonsService;
	@Autowired
	private BizTGgMsgManageService bizTGgMsgManageService;

	private enum QueryType {
		LIST, COUNT
	}

	@Override
	public Map<String, Object> getList(PageBean bean, Map<String, Object> params) {
		return null;
	}

	@Override
	public List<TaskListBean> getMyTask(String userId) {

		List<TaskListBean> result = new ArrayList<TaskListBean>();
		// 1、获取的的任务
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT T2.CX_TASK_ID,T2.CX_TASK_NAME,T2.CX_TASK_PEND_TIME,T2.CX_TASK_STATUS,T2.CX_TASK_DATE ");
		sql.append("  FROM BIZ_T_XJ_CX_TASK_PERSONS T1, BIZ_T_XJ_CX_TASK T2");
		sql.append(" WHERE T2.CX_TASK_ID = T1.CX_TASK_ID");
		sql.append(" AND T1.PERSON_ID =:userId ");
		sql.append(
				" AND (" + "(SELECT COUNT(0) FROM BIZ_T_XJ_CX_TASK_PERSONS T WHERE T.CX_TASK_ID=T2.Cx_Task_Id AND T.IN_CHARGE='"
						+ Constant.YES_NO.YES.getValue() + "')=0 " + " OR"
						+ " (SELECT COUNT(0) FROM BIZ_T_XJ_CX_TASK_PERSONS T WHERE T.CX_TASK_ID=T2.Cx_Task_Id AND T.PERSON_ID=:userId AND T.IN_CHARGE='1')=1 "
						+ " )");
		sql.append(" AND (T2.CX_TASK_STATUS=:DISTRIBUTE OR T2.CX_TASK_STATUS=:EXECUTING )");
		sql.append(" ORDER BY T2.CX_TASK_DATE ASC ,T2.CX_TASK_STATUS");
		params.put("userId", userId);
		params.put("DISTRIBUTE", Constant.CX_TASK_STATUS.DISTRIBUTE.getValue());
		params.put("EXECUTING", Constant.CX_TASK_STATUS.EXECUTING.getValue());
		List<Map<String, Object>> mytaskList = bizTXjCxTaskDAO.queryListBySql(sql.toString(), params);
		if (mytaskList.size() == 0) {
			return null;
		}
		// 2、获取执行人信息
		List<String> taskIdList = new ArrayList<String>();
		for (Map<String, Object> task : mytaskList) {
			taskIdList.add(task.get("cx_task_id").toString());
		}
		StringBuilder sql2 = new StringBuilder();
		sql2.append(
				"SELECT T.CX_TASK_ID, T.PERSON_ID,T.PERSON_NAME FROM BIZ_T_XJ_CX_TASK_PERSONS T WHERE T.CX_TASK_ID IN (:taskIdList) AND T.IS_VALID = '"
						+ Constant.YES_NO.YES.getValue() + "'");
		params.clear();
		params.put("taskIdList", taskIdList);
		List<Map<String, Object>> personList = bizTXjCxTaskDAO.queryListBySql(sql2.toString(), params);
		// 3、组装数据
		Map<String, List<TaskPerson>> personMap = new HashMap<String, List<TaskPerson>>();

		for (Map<String, Object> person : personList) {
			List<TaskPerson> tmp = null;
			if (personMap.containsKey(person.get("cx_task_id"))) {
				tmp = personMap.get(person.get("cx_task_id"));
			} else {
				tmp = new ArrayList<TaskPerson>();
			}
			TaskPerson tp = new TaskPerson();
			tp.setPersonId(person.get("person_id").toString());
			tp.setPersonName(person.get("person_name").toString());
			tmp.add(tp);
			personMap.put(person.get("cx_task_id").toString(), tmp);
		}
		for (Map<String, Object> task : mytaskList) {
			TaskListBean tlb = new TaskListBean();
			tlb.setTaskId(task.get("cx_task_id").toString());
			tlb.setTaskName(task.get("cx_task_name").toString());
			tlb.setTaskDate(task.get("cx_task_date").toString());
			String format1 =  DateUtil.DATE_FULL_FORMAT_N;
			String format2 =  DateUtil.DATE_FULL_FORMAT;
			String ptime = task.get("cx_task_pend_time").toString();
			if(ptime.length()==12){
				format1 = "yyyyMMddHHmm";
				format2 = "yyyy-MM-dd HH:mm";
			}
			tlb.setPlanEndTime(DateUtil.stringToString(task.get("cx_task_pend_time").toString(),format1, format2));
			tlb.setTaskStatus(task.get("cx_task_status").toString());
			tlb.setTaskPersonList(personMap.get(task.get("cx_task_id")));
			result.add(tlb);
		}

		return result;
	}

	@Override
	public List<Map<String, Object>> getdate(String csOrgId, String startTime, String endTime) {

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("csOrgId", csOrgId);
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT BELONG_WSC_ID, BELONG_WSC_NAME,");
		sql.append("  COUNT(1) AS ALLS,");
		sql.append("  SUM(CASE WHEN T.CX_TASK_STATUS = '3' THEN 1 ELSE 0 END) COMPLETES,");
		sql.append("   ROUND((SUM(CASE  WHEN T.IS_OVER_TIME = '0' AND T.CX_TASK_STATUS = '3' THEN");//
		sql.append("   1  ELSE  0  END)/COUNT(1)) ,4 )*100 rate ,");
		//sql.append("  SUM(CASE  WHEN T.IS_OVER_TIME = '1' AND T.CX_TASK_STATUS = '3' THEN");
		//不只是超期的
		sql.append("  SUM(CASE  WHEN T.IS_OVER_TIME = '1'  THEN");
		sql.append("  1 ELSE 0  END) AS OVER_TIMES,");
		sql.append("  SUM(CASE WHEN T.IS_OVER_TIME = '0' AND T.CX_TASK_STATUS = '3' THEN ");
		sql.append("  1 ELSE  0  END) AS NOT_OVER_TIMES,");
		sql.append("  SUM(CASE  WHEN T.SF_ZC_OVER_TIME = '1' AND T.CX_TASK_STATUS = '3' THEN");
		sql.append("  1 ELSE  0  END) ZC_OVER_TIMES  FROM BIZ_T_XJ_CX_TASK T");
		sql.append("  WHERE T.BELONG_WSC_ID IN  (select o.ORGID  from SYS_ORG O");
		sql.append("  where o.ORGTYPEID in (1, 2, 3) START WITH O.ORGID = :csOrgId");
		// sql.append(" AND T.CX_TASK_DATE BETWEEN :startTime AND :endTime");
		// sql.append("AND T.CX_TASK_DATE BETWEEN ");
		// sql.append( "'" + startTime+"'");
		// sql.append("AND" );
		// sql.append( "'" +endTime+"'");
		sql.append("  CONNECT BY prior O.ORGID = O.PARENTORGID)");
		sql.append("  AND T.CX_TASK_DATE BETWEEN :startTime AND  :endTime");
		sql.append("  GROUP BY T.BELONG_WSC_ID ,T.BELONG_WSC_NAME");

		List<Map<String, Object>> list = bizTXjCxTaskDAO.queryListBySql(sql.toString(), params);
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return new ArrayList<Map<String, Object>>();
		}

	}

	@Override
	public Map<String, Object> getRecordsData(PageBean pageBean, Map<String, Object> params,
			WSLoginInfoBean loginInfo) {
		StringBuffer sql = new StringBuffer();
		sql.append(
				" SELECT T.CX_TASK_ID, T.CX_TASK_DATE,T.CX_TASK_CODE, T.CX_TASK_NAME,T.CX_TASK_DESC,T.CX_TASK_PSTART_TIME,T.CX_TASK_PEND_TIME,");
		sql.append(
				" T.CX_TASK_ASTART_TIME,T.CX_TASK_AEND_TIME, T.CX_TASK_TYPE,T.CX_TASK_STATUS,T.IS_OVER_TIME,T.CX_TASK_CAN_DELIV,T.CX_TASK_HAVE_DELIV,");
		sql.append(" T.CX_TASK_GEN_TIME,T.BELONG_WSC_ID,T.BELONG_WSC_NAME,T.BYZD,T.SF_ZC_OVER_TIME,T.OVER_TIME_DESC,");
		sql.append(" (SELECT M.FZR_ID FROM BIZ_T_XJ_ZYP_CX_MAKE M WHERE M.ZYP_DATE = T.CX_TASK_DATE  AND M.BELONG_WSC_ID = T.BELONG_WSC_ID) AS BZZ_ID,  ");
		sql.append(" F_GET_CX_TASK_PERSONS(T.CX_TASK_ID) TASK_PERSON  ");
		sql.append(" FROM BIZ_T_XJ_CX_TASK T ");
		sql.append(" WHERE 1=1  ");

		if (null != params) {
			if (params.containsKey("isOverTime")) {
				sql.append(" AND T.IS_OVER_TIME =  '" + params.get("isOverTime").toString() + "' ");
			}
			if (params.containsKey("startTime")) {
				sql.append(" AND T.CX_TASK_DATE >= '" + params.get("startTime").toString().replaceAll("-", "") + "' ");
			}
			if (params.containsKey("endTime")) {
				sql.append(" AND T.CX_TASK_DATE <= '" + params.get("endTime").toString().replaceAll("-", "") + "' ");
			}
		}

		sql.append(" AND T.BELONG_WSC_ID IN (SELECT O.ORGID FROM SYS_ORG O WHERE O.ORGTYPEID IN ('"
				+ CommonConstant.orgType.GROUP.getKey() + "', '" + CommonConstant.orgType.COMPANY.getKey() + "', '"
				+ CommonConstant.orgType.FACTORY.getKey() + "') ");
		if (null != params && params.containsKey("csOsrg")) {
			sql.append(" START WITH O.ORGID = '" + params.get("csOsrg").toString() + "'  ");
		} else {
			sql.append(" START WITH O.ORGID = '" + loginInfo.getCsOrgId() + "'  ");
		}
		sql.append(" CONNECT BY prior O.ORGID = O.PARENTORGID) ");
		sql.append(" ORDER BY T.CX_TASK_DATE DESC,T.CX_TASK_PSTART_TIME DESC ");

		return bizTXjCxTaskDAO.queryPaginationListBySql(sql.toString(), new HashMap<String, Object>(), pageBean);
	}

	@Override
	public List<Map<String, Object>> getPerson(Map<String, Object> pars) {

		Map<String, Object> map = new HashMap<String, Object>();
	
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT PERSON_name,PERSON_id,");
		sql.append(" COUNT(1) AS ALLS,");
		sql.append(" SUM(CASE WHEN T.CX_TASK_STATUS = '3' THEN  1ELSE 0 END) COMPLETES, ");
		sql.append("  ROUND( ( ( SUM(CASE  WHEN T.IS_OVER_TIME = '0' AND T.CX_TASK_STATUS = '3' THEN");//
		sql.append("  1  ELSE 0  END))/ COUNT(1) ),4)*100  rate,");
		//sql.append(" SUM(CASE WHEN T.IS_OVER_TIME = '1' AND T.CX_TASK_STATUS = '3' THEN ");
		//不只是完成的
		sql.append(" SUM(CASE WHEN T.IS_OVER_TIME = '1'  THEN ");
		sql.append(" 1   ELSE  0  END) AS OVER_TIMES,");
		sql.append("   SUM(CASE WHEN T.IS_OVER_TIME = '0' AND T.CX_TASK_STATUS = '3' THEN");
		sql.append("  1    ELSE  0   END) AS NOT_OVER_TIMES,");
		sql.append("  SUM(CASE WHEN T.SF_ZC_OVER_TIME = '1' AND T.CX_TASK_STATUS = '3' THEN ");
		sql.append("  1  ELSE   0 ");
		sql.append("  END) ZC_OVER_TIMES");
		sql.append("   FROM BIZ_T_XJ_CX_TASK T, BIZ_T_XJ_CX_TASK_PERSONS P");
		sql.append(" WHERE T.CX_TASK_ID = P.CX_TASK_ID");
		if (MapUtil.hasParam(pars, "csId")) {
			sql.append("  AND T.BELONG_WSC_ID =:csId ");
			map.put("csId", pars.get("csId"));
		}
		
		if (MapUtil.hasParam(pars, "startTime")&&MapUtil.hasParam(pars, "endTime")) {
			sql.append(" AND T.CX_TASK_DATE BETWEEN :startTime AND :endTime "); 
			
			map.put("startTime", pars.get("startTime"));
			map.put("endTime", pars.get("endTime"));
		}
		
		
		sql.append(" GROUP BY P.PERSON_name ,P.PERSON_id ORDER BY ALLS DESC");

		List<Map<String, Object>> list = bizTXjCxTaskDAO.queryListBySql(sql.toString(), map);
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return new ArrayList<Map<String, Object>>();
		}

	}

	@Override
	public void updateToStartTask(String taskId, String userId) {
		BizTXjCxTask task = findOne(BizTXjCxTask.class, taskId);
		task.setCxTaskStatus(Constant.CX_TASK_STATUS.EXECUTING.getValue());
		task.setCxTaskAstartTime(DateUtil.dateToString(new Date(), DateUtil.DATE_FULL_FORMAT_N));
		bizTXjCxTaskDAO.save(task);
		// 设置任务责任人
		bizTXjCxTaskPersonsService.updateToSetCharge(task.getCxTaskId(), userId);
	}


	@Override
	public void updateToCompleteTask(String taskId) {
		BizTXjCxTask task = findOne(BizTXjCxTask.class, taskId);
		task.setCxTaskStatus(Constant.CX_TASK_STATUS.COMPLETE.getValue());
		task.setCxTaskAendTime(DateUtil.dateToString(new Date(), DateUtil.DATE_FULL_FORMAT_N));
		bizTXjCxTaskDAO.save(task);
	}
	
	@Override
	public void updateCxTask(String cxTaskId, String isOverTime, String overtime_content, WSLoginInfoBean user) {
		BizTXjCxTask cxTask = findOne(BizTXjCxTask.class, cxTaskId);
		cxTask.setSfZcOverTime(isOverTime);
		cxTask.setOverTimeDesc(overtime_content);
		bizTXjCxTaskDAO.save(cxTask);
	}

	@Override
	public Map<String, Object> findOneCxTask(String cxTaskId) {

		StringBuffer sql = new StringBuffer();
		sql.append(
				" SELECT T.CX_TASK_ID, T.CX_TASK_DATE,T.CX_TASK_CODE, T.CX_TASK_NAME,T.CX_TASK_DESC,T.CX_TASK_PSTART_TIME,T.CX_TASK_PEND_TIME,");
		sql.append(
				" T.CX_TASK_ASTART_TIME,T.CX_TASK_AEND_TIME, T.CX_TASK_TYPE,T.CX_TASK_STATUS,T.IS_OVER_TIME,T.CX_TASK_CAN_DELIV,T.CX_TASK_HAVE_DELIV,");
		sql.append(" T.CX_TASK_GEN_TIME,T.BELONG_WSC_ID,T.BELONG_WSC_NAME,T.BYZD,T.SF_ZC_OVER_TIME,T.OVER_TIME_DESC,");
		sql.append(" (SELECT M.FZR_ID FROM BIZ_T_XJ_ZYP_CX_MAKE M WHERE M.ZYP_DATE = T.CX_TASK_DATE AND M.BELONG_WSC_ID = T.BELONG_WSC_ID) AS BZZ_ID,  ");
		sql.append(" F_GET_CX_TASK_PERSONS(T.CX_TASK_ID) TASK_PERSON  ");
		sql.append(" FROM BIZ_T_XJ_CX_TASK T ");
		sql.append(" WHERE 1=1  ");
		sql.append(" AND T.CX_TASK_ID = '" + cxTaskId + "'");
		List<Map<String, Object>> result = bizTXjCxTaskDAO.queryListBySql(sql.toString(),
				new HashMap<String, Object>());
		if (null != result && result.size() > 0) {
			return result.get(0);
		} else {
			return new HashMap<String, Object>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getSbSsXjListAndCount(Map<String, Object> par, PageBean bean) {
		Object[] querySentence = this.getQuerySentence(par, QueryType.LIST);// 查询总的数据条数
		Map<String, Object> indListAndCount = new HashMap<String, Object>();
		indListAndCount = bizTXjCxTaskDAO.queryPaginationListBySql(querySentence[0].toString(),
				(Map<String, Object>) querySentence[1], bean);

		return indListAndCount;
	}

	private Object[] getQuerySentence(Map<String, Object> pars, QueryType type) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> parsCon = new HashMap<String, Object>();
		parsCon.put("csorgid", pars.get("csOrgId"));
		switch (type) {
		case LIST:
			sql.append(" SELECT T.CX_TASK_ID,  T.CX_TASK_DATE,T.CX_TASK_CODE,I.TASK_ITEM_ID,I.OPT_TIME,");
			sql.append(" T.CX_TASK_NAME,  T.BELONG_WSC_ID,  T.BELONG_WSC_NAME,");
			sql.append(" T.CX_TASK_AEND_TIME, I.XJD_ITEM_ID, I.XJD_ITEM_NAME,");
			sql.append(" S.TTASK_ITEM_SBSS_ID,S.SBSS_ID,");
			sql.append(" S.DETAIL_TYPE,  S.NAME,");
			sql.append(" s.bzz_sf_fault, S.SJK_SF_FAULT,  S.SF_FAULT, ");
			sql.append("  (SELECT M.FZR_ID FROM BIZ_T_XJ_ZYP_CX_MAKE M");
			sql.append(" WHERE M.ZYP_DATE = T.CX_TASK_DATE AND M.BELONG_WSC_ID = T.BELONG_WSC_ID) AS BZZ_ID, ");
			sql.append("  (SELECT M.FZR_NAME  FROM BIZ_T_XJ_ZYP_CX_MAKE M");
			sql.append(" WHERE M.ZYP_DATE = T.CX_TASK_DATE  AND M.BELONG_WSC_ID = T.BELONG_WSC_ID) AS BZZ_NAME,");
			sql.append("  F_GET_CX_TASK_PERSONS(T.CX_TASK_ID) TASK_PERSON ");
			break;
		case COUNT:

			sql.append("select count(1)");

			break;
		default:

			sql.append("select count(1)");
			break;
		}

		sql.append(" FROM BIZ_T_XJ_CX_TASK    T, BIZ_T_XJ_CX_TASK_ITEM      I,");
		sql.append(" BIZ_T_XJ_CX_TASK_ITEM_SBSS S");
		sql.append(" WHERE T.CX_TASK_ID = I.TASK_ID AND I.TASK_ITEM_ID = S.TASK_ITEM_ID ");
		sql.append(" AND T.CX_TASK_STATUS = '3'  ");
		sql.append(" AND S.SF_FAULT = '1' " );
		if (MapUtil.hasParam(pars, "task_type")) {
			sql.append(" AND S.DETAIL_TYPE =:task_type ");
			parsCon.put("task_type", pars.get("task_type"));

		}
		if (MapUtil.hasParam(pars, "org")) {

			sql.append(" AND T.BELONG_WSC_ID=:org");
			parsCon.put("org", pars.get("org"));
		}
		if (MapUtil.hasParam(pars, "startTime") && MapUtil.hasParam(pars, "endTime")) {

			sql.append("AND T.CX_TASK_DATE BETWEEN :startTime  AND :endTime");
			parsCon.put("startTime", pars.get("startTime"));
			parsCon.put("endTime", pars.get("endTime"));
		}
		if (MapUtil.hasParam(pars, "sborss_name")) {

			sql.append(" AND S.NAME LIKE :sborss_name ");
			parsCon.put("sborss_name", "%" + pars.get("sborss_name") + "%");
		}
		sql.append("   AND T.BELONG_WSC_ID IN  (select o.ORGID");
		sql.append("  from SYS_ORG O  where o.ORGTYPEID in (1, 2, 3) START WITH");
		sql.append("  O.ORGID =:csorgid");
		sql.append("   CONNECT BY prior O.ORGID = O.PARENTORGID) ");
		if (MapUtil.hasParam(pars, "xjd_name")) {
			sql.append(" AND I.XJD_ITEM_NAME LIKE :xjd_name");
			parsCon.put("xjd_name", "%" + pars.get("xjd_name") + "%");
		}

		if (MapUtil.hasParam(pars, "task_code")) {
			sql.append(" AND F_GET_CX_TASK_PERSONS(T.CX_TASK_ID) LIKE :task_code");
			parsCon.put("task_code", "%" + pars.get("task_code") + "%");
		}

		sql.append("  order by  t.cx_task_date desc");

		return new Object[] { sql, parsCon };
	}

	@Override
	public void updateToDistributeXJTask() {
		Map<String,Object> params = new HashMap<String,Object>();
		StringBuilder sql1 = new StringBuilder();
		String currentTime = DateUtil.dateToString(new Date(), DateUtil.DATE_FULL_FORMAT_N);
		sql1.append("SELECT * FROM BIZ_T_XJ_CX_TASK T WHERE TO_DATE( T.CX_TASK_PSTART_TIME,'YYYYMMDDHH24MISS')<=TO_DATE('"+currentTime+"','YYYYMMDDHH24MISS') ");
		sql1.append(" AND T.CX_TASK_STATUS='"+Constant.CX_TASK_STATUS.UNDISTRIBUTE.getValue()+"'");
		// 获取需要下发任务
		List<BizTXjCxTask> cxTaskList = bizTXjCxTaskDAO.queryListBySql(sql1.toString(), params, BizTXjCxTask.class);
		List<String> cxTaskIdList = new ArrayList<String>();
		for (BizTXjCxTask task : cxTaskList) {
			cxTaskIdList.add(task.getCxTaskId());
			// 单独给任务负责人发送消息,包裹在try里防止影响任务下发
			try {
				List<BizTXjCxTaskPersons> cxTaskPersonList = bizTXjCxTaskPersonsService.getCxTaskPersonListByTaskId(task.getCxTaskId(),false);
				String reciverId = "";
				for (BizTXjCxTaskPersons person : cxTaskPersonList) {
					reciverId += person.getPersonId()+",";
				}
				if(reciverId.length()==0){
					continue;
				}
				bizTGgMsgManageService.saveSystemMsg("系统消息【任务下发】", "您有一条新的任务【"+task.getCxTaskName()+"】已下发，请及时处理！",reciverId.substring(0, reciverId.length()-1), Constant.MSG_IMPORTANT.ORDINARY.getValue());
			} catch (Exception e) {
				logger.error("下发任务时向责任人发送消息是失败："+ExceptionFormatUtil.formatExceptionTrace(e));
			}
		}
		if(cxTaskIdList.size()>0){
			logger.info("下发共"+cxTaskIdList.size()+"条任务");
			String sql = "UPDATE BIZ_T_XJ_CX_TASK T SET T.CX_TASK_TYPE='"+Constant.TASK_TYPE.SYSTEMAUTO.getValue()+"',T.CX_TASK_STATUS='"+Constant.CX_TASK_STATUS.DISTRIBUTE.getValue()+"'"
					+ " WHERE T.CX_TASK_ID IN (:cxTaskIdList)";
			params.put("cxTaskIdList", cxTaskIdList);
			bizTXjCxTaskDAO.executeSql(sql, params);
		}
	}

	@Override
	public void updateToOverTimeXJTask() {
		Map<String,Object> params = new HashMap<String,Object>();
		String currentTime = DateUtil.dateToString(new Date(), DateUtil.DATE_FULL_FORMAT_N);
		StringBuilder sql1 = new StringBuilder();
		sql1.append("SELECT * FROM BIZ_T_XJ_CX_TASK T WHERE TO_DATE( T.CX_TASK_PEND_TIME,'YYYYMMDDHH24MISS')<TO_DATE('"+currentTime+"','YYYYMMDDHH24MISS') ");
		sql1.append(" AND T.CX_TASK_STATUS<>'"+Constant.CX_TASK_STATUS.COMPLETE.getValue()+"'");
		sql1.append(" AND (T.IS_OVER_TIME='"+Constant.YES_NO.NO.getValue()+"' OR T.IS_OVER_TIME IS NULL) ");
		// 已超期任务
		List<BizTXjCxTask> cxTaskList = bizTXjCxTaskDAO.queryListBySql(sql1.toString(), params, BizTXjCxTask.class);
		List<String> cxTaskIdList = new ArrayList<String>();
		for (BizTXjCxTask task : cxTaskList) {
			cxTaskIdList.add(task.getCxTaskId());
			try {
				List<BizTXjCxTaskPersons> cxTaskPersonList = bizTXjCxTaskPersonsService.getCxTaskPersonListByTaskId(task.getCxTaskId(),true);
				String reciverId = "";
				for (BizTXjCxTaskPersons person : cxTaskPersonList) {
					reciverId += person.getPersonId()+",";
				}
				if(reciverId.length()==0){
					continue;
				}
				bizTGgMsgManageService.saveSystemMsg("系统消息【任务超期】", "您有一条的任务【"+task.getCxTaskName()+"】已超期，请及时处理！", reciverId.substring(0, reciverId.length()-1), Constant.MSG_IMPORTANT.ORDINARY.getValue());
			} catch (Exception e) {
				logger.error("下发任务时向责任人发送消息是失败："+ExceptionFormatUtil.formatExceptionTrace(e));
			}
		}
		if(cxTaskIdList.size()>0){
			logger.info("超时共"+cxTaskIdList.size()+"条任务");
			String sql = "UPDATE BIZ_T_XJ_CX_TASK T SET T.IS_OVER_TIME='"+Constant.YES_NO.YES.getValue()+"' WHERE T.CX_TASK_ID IN (:cxTaskIdList)";
			params.put("cxTaskIdList", cxTaskIdList);
			bizTXjCxTaskDAO.executeSql(sql, params);
		}
	}

	@Override
	public Map<String, Object> getCxTaskDataOnMobile(PageBean bean,
			Map<String, Object> params, WSLoginInfoBean loginInfo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT T.CX_TASK_ID,T.CX_TASK_DATE,T.CX_TASK_CODE,T.CX_TASK_NAME,T.IS_OVER_TIME FROM BIZ_T_XJ_CX_TASK T ");
		sql.append(" WHERE 1=1 ");
		sql.append(" AND T.BELONG_WSC_ID IN ");
		sql.append(" (SELECT O.ORGID FROM SYS_ORG O START WITH O.ORGID = '" + loginInfo.getCsOrgId() + "' CONNECT BY PRIOR O.ORGID = O.PARENTORGID)");
	    sql.append(" AND T.CX_TASK_STATUS='3' ");
	    if (params.containsKey("startTime1")) {
			sql.append(" AND T.CX_TASK_DATE >='"+String.valueOf(params.get("startTime1")).replace("-","")+"'");
		}
		if (params.containsKey("endTime1")) {
			sql.append(" AND T.CX_TASK_DATE <='"+String.valueOf(params.get("endTime1")).replace("-","")+"'");
		}
		if (params.containsKey("taskName")) {
			sql.append(" AND T.CX_TASK_NAME LIKE '%" +params.get("taskName") + "%' ");
		}
	    sql.append(" ORDER BY T.CX_TASK_AEND_TIME DESC ");
		return bizTXjCxTaskDAO.queryPaginationListBySql(sql.toString(), new HashMap<String,Object>(), bean);
	}


	@Override
	public Map<String, Object> getCxTaskSbssData(PageBean bean,
			Map<String, Object> params, String cxTaskId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT S.TTASK_ITEM_SBSS_ID,S.DETAIL_TYPE,S.NAME,S.OPT_TIME FROM BIZ_T_XJ_CX_TASK_ITEM_SBSS S,BIZ_T_XJ_CX_TASK_ITEM I,BIZ_T_XJ_CX_TASK T ");
		sql.append(" WHERE 1=1 ");
		sql.append(" AND S.TASK_ITEM_ID = I.TASK_ITEM_ID ");
		sql.append(" AND I.TASK_ID = T.CX_TASK_ID ");
		sql.append(" AND T.CX_TASK_ID = '"+cxTaskId+"' ");       
		if (params.containsKey("sbOrssid")) {
			sql.append(" AND S.DETAIL_TYPE ='"+params.get("sbOrssid") +"'");
		}      
		return bizTXjCxTaskDAO.queryPaginationListBySql(sql.toString(), new HashMap<String,Object>(), bean);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getSbXj(String sbId, PageBean bean) {
		Object[] querySentence = this.getQuerySentence(sbId, QueryType.LIST);// 查询总的数据条数
		Map<String, Object> indListAndCount = new HashMap<String, Object>();
		indListAndCount = bizTXjCxTaskDAO.queryPaginationListBySql(querySentence[0].toString(),
				(Map<String, Object>) querySentence[1], bean);

		return indListAndCount;
	}
	private Object[] getQuerySentence( String  sbId, QueryType type) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> parsCon = new HashMap<String, Object>();
		
		switch (type) {
		case LIST:
			sql.append(" SELECT T.CX_TASK_ID,  T.CX_TASK_DATE,T.CX_TASK_CODE,I.TASK_ITEM_ID,I.OPT_TIME,");
			sql.append(" T.CX_TASK_NAME,  T.BELONG_WSC_ID,  T.BELONG_WSC_NAME,");
			sql.append(" T.CX_TASK_AEND_TIME, I.XJD_ITEM_ID, I.XJD_ITEM_NAME,");
			sql.append(" S.TTASK_ITEM_SBSS_ID,S.SBSS_ID,");
			sql.append(" S.DETAIL_TYPE,  S.NAME,");
			sql.append(" s.bzz_sf_fault, S.SJK_SF_FAULT,  S.SF_FAULT, ");
			sql.append("  (SELECT M.FZR_ID FROM BIZ_T_XJ_ZYP_CX_MAKE M");
			sql.append(" WHERE M.ZYP_DATE = T.CX_TASK_DATE AND M.BELONG_WSC_ID = T.BELONG_WSC_ID AND M.BELONG_WSC_ID = T.BELONG_WSC_ID) AS BZZ_ID, ");
			sql.append("  (SELECT M.FZR_NAME  FROM BIZ_T_XJ_ZYP_CX_MAKE M");
			sql.append(" WHERE M.ZYP_DATE = T.CX_TASK_DATE  AND M.BELONG_WSC_ID = T.BELONG_WSC_ID AND M.BELONG_WSC_ID = T.BELONG_WSC_ID) AS BZZ_NAME,");
			sql.append("  F_GET_CX_TASK_PERSONS(T.CX_TASK_ID) TASK_PERSON ");
			break;
		case COUNT:

			sql.append("select count(1)");

			break;
		default:

			sql.append("select count(1)");
			break;
		}

		sql.append(" FROM BIZ_T_XJ_CX_TASK    T, BIZ_T_XJ_CX_TASK_ITEM      I,");
		sql.append(" BIZ_T_XJ_CX_TASK_ITEM_SBSS S");
		sql.append(" WHERE T.CX_TASK_ID = I.TASK_ID AND I.TASK_ITEM_ID = S.TASK_ITEM_ID ");
		sql.append(" AND T.CX_TASK_STATUS = '3'  ");
		sql.append(" AND S.SF_FAULT = '1' " );
		if(null!=sbId&&StringUtils.isNotBlank(sbId)){
		     sql.append(" AND S.SBSS_ID=:sbId");
			parsCon.put("sbId", sbId);
		}

		sql.append("  order by  t.cx_task_date desc");

		return new Object[] { sql, parsCon };
	}

}
