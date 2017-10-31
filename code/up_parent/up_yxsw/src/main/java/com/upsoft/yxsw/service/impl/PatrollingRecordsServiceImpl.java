package com.upsoft.yxsw.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.dao.AttachmentDao;
import com.upsoft.system.entity.ComTAttachment;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.yxsw.dao.BizTXjCxTaskItemDAO;
import com.upsoft.yxsw.dao.BizTXjCxTaskItemSbssDAO;
import com.upsoft.yxsw.service.PatrollingRecordsService;

@Service
public class PatrollingRecordsServiceImpl extends BaseServiceImpl implements PatrollingRecordsService{

	@Autowired
	private BizTXjCxTaskItemDAO bizTXjCxTaskItemDAO;
	@Autowired
	private BizTXjCxTaskItemSbssDAO bizTXjCxTaskItemSbssDAO;
	@Autowired
	private AttachmentDao attachmentDao;
	
	@Override
	public Map<String, Object> getpointList(String cxTaskId,PageBean pageBean) {

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT T.TASK_ITEM_ID,T.TASK_ID,T.XJD_ITEM_ID,T.XJD_ITEM_NAME,T.XJD_ITEM_ADDRESS,T.XJD_ITEM_DESC,T.RFID_CONFIRMED_TYPE,T.OPT_TIME,T.HAVE_COMPLETE,T.RFID_CODE FROM BIZ_T_XJ_CX_TASK_ITEM T ");
		sql.append(" WHERE 1=1 ");
		sql.append(" AND T.TASK_ID ='"+ cxTaskId +"'");
		
		return bizTXjCxTaskItemDAO.queryPaginationListBySql(sql.toString(), new HashMap<String,Object>(), pageBean);
	}
	@Override
	public Map<String, Object> findPoint(String taskItemId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT T.TASK_ITEM_ID,T.TASK_ID,T.XJD_ITEM_ID,T.XJD_ITEM_NAME,T.XJD_ITEM_ADDRESS,T.XJD_ITEM_DESC,T.RFID_CONFIRMED_TYPE,T.OPT_TIME,T.HAVE_COMPLETE,T.RFID_CODE FROM BIZ_T_XJ_CX_TASK_ITEM T ");
		sql.append(" WHERE 1=1 ");
		sql.append(" AND T.TASK_ITEM_ID ='"+ taskItemId +"'");
		List<Map<String,Object>> result = bizTXjCxTaskItemDAO.queryListBySql(sql.toString(), new HashMap<String,Object>());
		if(null != result && result.size() > 0){
			return result.get(0);
		}else{
			return new HashMap<String,Object>();
		}
	}
	@Override
	public Map<String, Object> getSbssList(String taskItemId, PageBean pageBean) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT T.TTASK_ITEM_SBSS_ID,T.TASK_ITEM_ID,T.DETAIL_TYPE,T.SBSS_ID,T.NAME,T.MUST_SCAN,T.EWM_CONFIRMED_TYPE,T.HAVE_COMPLETE,T.OPT_TIME,T.XJ_DESC,T.SF_FAULT,T.BZZ_SF_FAULT,T.SJK_SF_FAULT FROM BIZ_T_XJ_CX_TASK_ITEM_SBSS T ");
		sql.append(" WHERE 1=1 ");
		sql.append(" AND t.task_item_id='"+ taskItemId +"'");
		
		return bizTXjCxTaskItemSbssDAO.queryPaginationListBySql(sql.toString(), new HashMap<String,Object>(), pageBean);
		
	}
	@Override
	public List<Map<String, Object>> getSbssInfo(String ttaskItemSbssId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT R.CHECK_ITEM_NAME,R.CHECK_ITEM_CODE,R.INPUT_TYPE,R.CHECK_VALUE,(CASE WHEN R.INPUT_TYPE = '0' THEN ");
	    sql.append(" (SELECT D.SEL_NAME FROM BIZ_T_XJ_CX_TASK_ITEM_RST_DET D WHERE D.SBSS_RST_ID = R.SBSS_RST_ID AND D.SEL_VALUE = R.CHECK_VALUE) ELSE R.CHECK_VALUE END) AS SEL_NAME ");    
	    sql.append(" FROM BIZ_T_XJ_CX_TASK_ITEM_SBSS_RST R ");  
	    sql.append(" WHERE 1=1 ");    
	    sql.append(" AND R.TASK_ITEM_SBSS_ID ='"+ ttaskItemSbssId +"'");    
	   
		return bizTXjCxTaskItemSbssDAO.queryListBySql(sql.toString(), new HashMap<String,Object>());
	}
	@Override
	public List<Map<String, Object>> getDealFlow(String ttaskItemSbssId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT T.ID,T.TTASK_ITEM_SBSS_ID,T.OPT_TIME,T.OPT_ID,T.OPT_NAME,T.OPT_CONTENT FROM BIZ_T_XJ_CX_TASK_ITEM_FAULTLC T ");
		sql.append(" where 1=1 ");
		sql.append(" AND T.TTASK_ITEM_SBSS_ID ='"+ ttaskItemSbssId +"'");
		sql.append(" ORDER BY T.OPT_TIME ASC ");
		
		return bizTXjCxTaskItemSbssDAO.queryListBySql(sql.toString(), new HashMap<String,Object>());
	}
	@Override
	public List<ComTAttachment> getAttachmentList(String ttaskItemSbssId) {
		String sql = "select * from com_t_attachment t where t.business_id='"+ttaskItemSbssId+"'";
		
		return attachmentDao.queryListBySql(sql, new HashMap<String,Object>(), ComTAttachment.class);
	}
	@Override
	public Map<String, Object> getSbssCheckDetail(String ttaskItemSbssId) {
		String sql = "SELECT * FROM BIZ_T_XJ_CX_TASK_ITEM_SBSS T WHERE T.TTASK_ITEM_SBSS_ID='"+ ttaskItemSbssId +"'";
		List<Map<String,Object>> result  = bizTXjCxTaskItemSbssDAO.queryListBySql(sql, new HashMap<String, Object>());
		if(null != result && result.size() > 0){
			return result.get(0);
		}else{
			
			return new HashMap<String, Object>();
		}
	}
	@Override
	public Map<String, Object> getSbssDetail(String ttaskItemSbssId) {
		String sql ="SELECT S.DETAIL_TYPE,S.NAME,S.HAVE_COMPLETE,S.OPT_TIME,S.SF_FAULT,S.XJ_DESC,S.BZZ_SF_FAULT,S.SJK_SF_FAULT FROM BIZ_T_XJ_CX_TASK_ITEM_SBSS S WHERE S.TTASK_ITEM_SBSS_ID='"+ttaskItemSbssId+"'";
		List<Map<String,Object>> result  = bizTXjCxTaskItemSbssDAO.queryListBySql(sql, new HashMap<String, Object>());
		if(null != result && result.size()>0){
			return result.get(0);
		}else{
			
			return new HashMap<String,Object>();
		}
	}

}
