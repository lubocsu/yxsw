package com.upsoft.yxsw.service.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.constant.CommonConstant;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.system.util.BeanUtil;
import com.upsoft.system.util.DateUtil;
import com.upsoft.yxsw.constant.Constant;
import com.upsoft.yxsw.dao.BizTXjWorkGroupDAO;
import com.upsoft.yxsw.dao.BizTXjWorkGroupDetialDAO;
import com.upsoft.yxsw.entity.BizTXjWorkGroup;
import com.upsoft.yxsw.entity.BizTXjWorkGroupDetial;
import com.upsoft.yxsw.service.BizTXjFreqSegmentService;
import com.upsoft.yxsw.service.BizTXjWorkGroupService;
import com.upsoft.yxsw.service.BizTXjZbPlanService;

import oracle.jdbc.OracleTypes;


@Service
public class BizTXjWorkGroupServiceImpl extends BaseServiceImpl implements BizTXjWorkGroupService {
	// 任务定时专用日志
	private Logger scheduleLogger = Logger.getLogger("com.upsoft.yxsw.scheduleTask");
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private BizTXjWorkGroupDAO workGroupDAO;
	@Autowired
	private BizTXjWorkGroupDetialDAO workGroupDetialDAO;
	@Autowired
	private BizTXjFreqSegmentService freqSegmentService;
	@Autowired
	private BizTXjZbPlanService zbPlanService;
	
	public Map<String, Object> getList(PageBean bean, Map<String, Object> params){
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT WORK_GROUP_ID, WORK_GROUP_NAME, WORK_GROUP_DESC, START_TIME, END_TIME, CREATE_TIMESTEMP, CREATOR_ACCOUNT, CREATOR_NAME, BELONG_WSC_ID, BELONG_WSCN_AME, UPDATOR_ACCOUNT, UPDATOR_NAME, UPDATE_TIMESTEMP ");
		sql.append(" FROM BIZ_T_XJ_WORK_GROUP G ");
		sql.append(" WHERE 1=1 ");
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		if(null != params.get("workGroupName") && StringUtils.isNotBlank(params.get("workGroupName").toString())){
			sql.append(" and G.WORK_GROUP_NAME like :workGroupName ");
			paramsMap.put("workGroupName", MessageFormat.format("%{0}%", params.get("workGroupName").toString()));
		}
		if(null != params.get("orgCode") && StringUtils.isNotBlank(params.get("orgCode").toString())){
			sql.append(" and G.BELONG_WSC_ID = :orgCode ");
			paramsMap.put("orgCode", params.get("orgCode").toString());
		}
		return workGroupDAO.queryPaginationListBySql(sql.toString(), paramsMap, bean);
	}
	
	public BizTXjWorkGroup save(BizTXjWorkGroup bizTXjWorkGroup){
		return bizTXjWorkGroup;
	
	}
	
	public BizTXjWorkGroup getBizTXjWorkGroupById(String id){
		
		return workGroupDAO.findOne(id);
	}

	@Override
	public BizTXjWorkGroup save(BizTXjWorkGroup bizTXjWorkGroup, List<BizTXjWorkGroupDetial> workGroupDetialList) {
		
		bizTXjWorkGroup = workGroupDAO.save(bizTXjWorkGroup);
		for (BizTXjWorkGroupDetial detail : workGroupDetialList) {
			detail.setGroupId(bizTXjWorkGroup.getWorkGroupId());
			detail.setDelFlag(Long.valueOf(CommonConstant.STATUS_YoN.NO));
			detail.setStartTime(DateUtil.stringToString(detail.getStartTime(), "HH:mm", "HHmm"));
			detail.setEndTime(DateUtil.stringToString(detail.getEndTime(), "HH:mm", "HHmm"));
		}
		workGroupDetialDAO.save(workGroupDetialList);
		return bizTXjWorkGroup;
	}
	
	@Override
	public BizTXjWorkGroup save(BizTXjWorkGroup bizTXjWorkGroup, List<BizTXjWorkGroupDetial> workGroupDetialList, List<BizTXjWorkGroupDetial> delDetialList) {
		
		bizTXjWorkGroup = workGroupDAO.save(bizTXjWorkGroup);
		List<String> delDetailId = new ArrayList<String>();	//需要删除排班和任务的班次
		List<BizTXjWorkGroupDetial> detialList = new ArrayList<BizTXjWorkGroupDetial>();
		for (BizTXjWorkGroupDetial detail : workGroupDetialList) {
			
			BizTXjWorkGroupDetial oriDetail;
			detail.setStartTime(DateUtil.stringToString(detail.getStartTime(), "HH:mm", "HHmm"));
			detail.setEndTime(DateUtil.stringToString(detail.getEndTime(), "HH:mm", "HHmm"));
			if(StringUtils.isNotBlank(detail.getDetailId())){
				oriDetail = workGroupDetialDAO.findOne(detail.getDetailId());
				//如果班次时间发生变动，删除该班次下的任务
				if(!StringUtils.equals(oriDetail.getStartTime(), detail.getStartTime()) || !StringUtils.equals(oriDetail.getEndTime(), detail.getEndTime())){
					delDetailId.add(oriDetail.getDetailId());
				}
				BeanUtil.copyPropertiesIgnoreNull(detail, oriDetail);
			}else{
				oriDetail = detail;
			}
			oriDetail.setGroupId(bizTXjWorkGroup.getWorkGroupId());
			oriDetail.setDelFlag(Long.valueOf(CommonConstant.STATUS_YoN.NO));
			detialList.add(oriDetail);
		}
		workGroupDetialDAO.save(detialList);
		workGroupDetialDAO.delete(delDetialList);
		
		for (BizTXjWorkGroupDetial delDetial : delDetialList) {
			delDetailId.add(delDetial.getDetailId());
		}
		if(!delDetailId.isEmpty()){
			freqSegmentService.deleteFreqSegmentByPerid(delDetailId);	//删除班次的巡检任务
			zbPlanService.deleteZbPlanByPeriod(delDetailId);	//删除影响到的未生效排班
		}
		return bizTXjWorkGroup;
	}

	@Override
	public List<BizTXjWorkGroup> getList(String wscOrgId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT WORK_GROUP_ID, WORK_GROUP_NAME, WORK_GROUP_DESC, START_TIME, END_TIME, CREATE_TIMESTEMP, CREATOR_ACCOUNT, CREATOR_NAME, BELONG_WSC_ID, BELONG_WSCN_AME, UPDATOR_ACCOUNT, UPDATOR_NAME, UPDATE_TIMESTEMP ");
		sql.append(" FROM BIZ_T_XJ_WORK_GROUP G ");
		sql.append(" WHERE 1=1 ");
		if(StringUtils.isNotBlank(wscOrgId)){
			sql.append(" AND G.BELONG_WSC_ID='"+wscOrgId+"'");
		}
		return workGroupDAO.queryListBySql(sql.toString(),new Object[]{}, BizTXjWorkGroup.class);
	}
	
	@Override
	public void deleteWorkGroup(String id) {
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void saveCXTaskBySchedule(List<BizTXjWorkGroup> list) {
		Date now = new Date();
		scheduleLogger.info("################ 【"+DateUtil.dateToString(now, DateUtil.DATE_FORMAT_WITHOUT_TIME)+"，"+DateUtil.dateToString(now,"HH")+"时】定时任务日志 ############");
		for (final BizTXjWorkGroup workGrop : list) {
			final String date = DateUtil.dateToString(now, DateUtil.DATE_FORMAT_yyyyMMdd);
			String param3Value = (String) jdbcTemplate.execute(
				new CallableStatementCreator() {
					 public CallableStatement createCallableStatement(Connection con) throws SQLException {
						String storedProc = "{CALL P_GENERATE_CX_TASK(?,?,?)}"; 
						CallableStatement cs = con.prepareCall(storedProc);
						cs.setString(1, date);
						cs.setString(2, workGrop.getBelongWscId());
						cs.registerOutParameter(3, OracleTypes.VARCHAR);// 注册输出参数的类型
						return cs;
					}
				},
				new CallableStatementCallback() {
					public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
						cs.execute();
						return cs.getString(3);
					}
			});
			// 解析返回结果 返回值：格式：数字#内容描述；  其中数字为0/1的选项，0代表生成失败，1代表成功，内容描述为结果描述
			scheduleLogger.error(param3Value);
			String[] param3ValueArr = param3Value.split("#");
			if(StringUtils.equals(param3ValueArr[0],"1")){
				String sql = "UPDATE BIZ_T_XJ_ZB_PLAN T SET T.VALID_FLAG='"+Constant.YES_NO.YES.getValue()+"' WHERE T.ZB_DATE='"+date+"' AND T.BELONG_WSC_ID='"+workGrop.getBelongWscId()+"'";
				jdbcTemplate.execute(sql);
			}
		}
		scheduleLogger.info("################ 【"+DateUtil.dateToString(now, DateUtil.DATE_FORMAT_WITHOUT_TIME)+"，"+DateUtil.dateToString(now,"HH")+"时】定时任务生成完成 ############");
	}
}
