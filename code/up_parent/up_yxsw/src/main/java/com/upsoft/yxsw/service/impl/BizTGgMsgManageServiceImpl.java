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
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.system.util.DateUtil;
import com.upsoft.yxsw.constant.Constant;
import com.upsoft.yxsw.dao.BizTGgMsgManageDAO;
import com.upsoft.yxsw.entity.BizTGgMsgManage;
import com.upsoft.yxsw.service.BizTGgMsgManageService;


@Service
public class BizTGgMsgManageServiceImpl extends BaseServiceImpl implements BizTGgMsgManageService {

	@Autowired
	private BizTGgMsgManageDAO bizTGgMsgManageDAO;
	
	@Override
	public Map<String, Object> getMsgSendData(PageBean pageBean,
			Map<String, Object> params) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT T.MSG_ID, T.TITLE, T.CONTENT, T.IMPORTANT_LEVEL, T.CREATE_TIMESTEMP, T.CREATOR_ACCOUNT, T.CREATOR_NAME, T.BELONG_WSC_ID, T.BELONG_WSCN_AME, T.BELONG_DEPT, T.RECIVER_ID, T.RECIVER_NAME, T.IS_HAVE_READ ");
		sql.append(" FROM ");
		sql.append(" BIZ_T_GG_MSG_MANAGE T ");
		sql.append(" WHERE 1=1 ");

		if(null != params){
			if(params.containsKey("userId")){
				sql.append(" AND T.CREATOR_ACCOUNT = '"+ params.get("userId").toString() +"' ");
			}
			if(params.containsKey("content")){
				sql.append(" AND T.CONTENT LIKE '%"+params.get("content").toString()+"%' ");
			}
			if(params.containsKey("title")){
				sql.append(" AND T.TITLE LIKE '%"+params.get("title").toString()+"%' ");
			}
			if(params.containsKey("startTime")){
				sql.append(" AND T.CREATE_TIMESTEMP >= '"+ params.get("startTime").toString().replaceAll("-", "") + "000000" +"' ");
			}
			if(params.containsKey("endTime")){
				sql.append(" AND T.CREATE_TIMESTEMP <= '"+ params.get("endTime").toString().replaceAll("-", "") + "235959" +"' ");
			}
		}
		
		sql.append(" ORDER BY T.IS_HAVE_READ ASC,T.CREATE_TIMESTEMP DESC ");
		
		return bizTGgMsgManageDAO.queryPaginationListBySql(sql.toString(), new HashMap<String,Object>(), pageBean);
	}

	@Override
	public void saveMsg(String title, String content, String reciverId, String importantLevel,WSLoginInfoBean loginInfo) {
		BizTGgMsgManage bizTGgMsgManage;
		Date date = new Date();
		String[] idList = reciverId.split(",");
		for (int i = 0; i < idList.length; i++) {
			bizTGgMsgManage = new BizTGgMsgManage();
			
			bizTGgMsgManage.setTitle(title);
			bizTGgMsgManage.setContent(content);
			bizTGgMsgManage.setImportantLevel(importantLevel);
			bizTGgMsgManage.setCreateTimestemp(DateUtil.dateToString(date, "yyyyMMddHHmmss"));
			bizTGgMsgManage.setCreatorAccount(loginInfo.getUser().getUserId());
			bizTGgMsgManage.setCreatorName(loginInfo.getUser().getUserName());
			bizTGgMsgManage.setBelongWscId(loginInfo.getCsOrgCode());
			bizTGgMsgManage.setBelongWscnAme(loginInfo.getCsOrgName());
			bizTGgMsgManage.setBelongDept(loginInfo.getUser().getOrgId());
			bizTGgMsgManage.setIsHaveRead(Long.parseLong(CommonConstant.STATUS_DISABLE));
				
			bizTGgMsgManage.setReciverId(idList[i]);
			SysUser sysUser = ServiceReceiver.getUser(idList[i]);
			bizTGgMsgManage.setReciverName(sysUser.getUserName());
			bizTGgMsgManageDAO.save(bizTGgMsgManage);
		}
	}
	
	@Override
	public Map<String, Object> getMsgReceiveData(PageBean pageBean,Map<String, Object> params) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT D1.DATA1 IS_HAVE_READ_NAME,T.MSG_ID, T.TITLE, T.CONTENT, T.IMPORTANT_LEVEL, T.CREATE_TIMESTEMP, T.CREATOR_ACCOUNT, T.CREATOR_NAME, T.BELONG_WSC_ID, T.BELONG_WSCN_AME, T.BELONG_DEPT, T.RECIVER_ID, T.RECIVER_NAME, T.IS_HAVE_READ ");
		sql.append(" FROM ");
		sql.append(" BIZ_T_GG_MSG_MANAGE T ");
		sql.append(" LEFT JOIN SYS_DICT_TREE_DATA D1 ON D1.PARENTNODEID='UM000006' AND D1.CODE = T.IS_HAVE_READ ");
		sql.append(" WHERE 1=1 ");

		if(null != params){
			if(params.containsKey("userId")){
				sql.append(" AND T.RECIVER_ID = '"+ params.get("userId").toString() +"' ");
			}
			if(params.containsKey("content")){
				sql.append(" AND T.CONTENT LIKE '%"+params.get("content").toString()+"%' ");
			}
			if(params.containsKey("title")){
				sql.append(" AND T.TITLE LIKE '%"+params.get("title").toString()+"%' ");
			}
			if(params.containsKey("creatorName")){
				sql.append(" AND (T.CREATOR_NAME LIKE '%"+params.get("creatorName").toString()+"%' ");
				sql.append(" OR T.CREATOR_ACCOUNT LIKE '%" +params.get("creatorName").toString()+"%') ");
			}
			if(params.containsKey("isHaveRead")){
				sql.append(" AND T.IS_HAVE_READ = '"+ params.get("isHaveRead").toString() +"' ");
			}
		}
		
		sql.append(" ORDER BY T.IS_HAVE_READ ASC,T.CREATE_TIMESTEMP DESC ");
		
		return bizTGgMsgManageDAO.queryPaginationListBySql(sql.toString(), new HashMap<String,Object>(), pageBean);
	}

	@Override
	public Map<String, Object> findOneById(String id) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT D2.DATA1 IMPORTANT_LEVEL_NAME,D1.DATA1 IS_HAVE_READ_NAME,T.* FROM BIZ_T_GG_MSG_MANAGE T ");
		sql.append(" LEFT JOIN SYS_DICT_TREE_DATA D1 ON D1.PARENTNODEID='UM000006' AND D1.CODE = T.IS_HAVE_READ ");
		sql.append(" LEFT JOIN SYS_DICT_TREE_DATA D2 ON D2.PARENTNODEID='UM000010' AND D2.CODE = T.IMPORTANT_LEVEL ");
		sql.append(" WHERE 1=1 ");
		sql.append(" AND T.MSG_ID = '"+ id +"'");
		List<Map<String,Object>> result = bizTGgMsgManageDAO.queryListBySql(sql.toString(), new HashMap<String,Object>());
		if(null != result && result.size()>0){
			return result.get(0);
		}else{
			
			return null;
		}
	}

	@Override
	public BizTGgMsgManage updateMsg(String id) {
		BizTGgMsgManage bizTGgMsgManage = new BizTGgMsgManage();
		if(StringUtils.isNotBlank(id)){
			
			bizTGgMsgManage = bizTGgMsgManageDAO.findOne(id);
			bizTGgMsgManage.setIsHaveRead(Long.parseLong(CommonConstant.STATUS_VALID));
			bizTGgMsgManageDAO.save(bizTGgMsgManage);
		}
		
		return bizTGgMsgManage;
	}

	@Override
	public int updateMsgByIds(List<String> idArray) {
		int result = 0;
		for (String id : idArray) {
			
			BizTGgMsgManage bizTGgMsgManage = bizTGgMsgManageDAO.findOne(id);
			bizTGgMsgManage.setIsHaveRead(Long.parseLong(CommonConstant.STATUS_VALID));
			bizTGgMsgManageDAO.save(bizTGgMsgManage);
			result ++;
		}
		return result;
	}

	@Override
	public Map<String,Object> getMyMessageData(PageBean bean,String userId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT T.MSG_ID, T.TITLE, T.CONTENT, T.IMPORTANT_LEVEL, T.CREATE_TIMESTEMP, T.CREATOR_ACCOUNT, T.CREATOR_NAME, T.BELONG_WSC_ID, T.BELONG_WSCN_AME, T.BELONG_DEPT, T.RECIVER_ID, T.RECIVER_NAME, T.IS_HAVE_READ ");
		sql.append(" FROM ");
		sql.append(" BIZ_T_GG_MSG_MANAGE T ");
		sql.append(" WHERE 1=1 AND (T.CREATOR_ACCOUNT = '"+userId+"' OR T.RECIVER_ID = '"+userId+"')");
		sql.append(" ORDER BY T.IS_HAVE_READ ASC,T.CREATE_TIMESTEMP DESC");
		List<BizTGgMsgManage> list = bizTGgMsgManageDAO.queryListBySql(sql.toString(),bean.getStartIndex(),bean.getPageSize(),new HashMap<String,Object>(),BizTGgMsgManage.class);
		long total = bizTGgMsgManageDAO.queryCountBySql("SELECT COUNT(1) FROM ("+sql.toString()+")", new HashMap<String,Object>());
		for (BizTGgMsgManage msg : list) {
			if(StringUtils.equals(userId, msg.getCreatorAccount())){
				msg.setSendOrReciver("1");
			}else{
				msg.setSendOrReciver("0");
			}
		}
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("total",total);
		data.put("list",list);
		return data;
	}

	@Override
	public long getMyReceivedUnreadMsgCount(String userId) {
		String sql = "SELECT COUNT(0) FROM BIZ_T_GG_MSG_MANAGE T WHERE T.RECIVER_ID = '"+userId+"' AND T.IS_HAVE_READ = '"+Constant.YES_NO.NO.getValue()+"'";
		return bizTGgMsgManageDAO.queryCountBySql(sql,  new HashMap<String,Object>());
	}

	@Override
	public void saveSystemMsg(String title, String content, String reciverIds, String level) {
		BizTGgMsgManage bizTGgMsgManage;
		Date date = new Date();
		String[] idList = reciverIds.split(",");
		for (int i = 0; i < idList.length; i++) {
			bizTGgMsgManage = new BizTGgMsgManage();
			
			bizTGgMsgManage.setTitle(title);
			bizTGgMsgManage.setContent(content);
			bizTGgMsgManage.setImportantLevel(level);
			bizTGgMsgManage.setCreateTimestemp(DateUtil.dateToString(date, "yyyyMMddHHmmss"));
			bizTGgMsgManage.setCreatorAccount("system");
			bizTGgMsgManage.setCreatorName("system");
			SysUser sysUser = ServiceReceiver.getUser(idList[i]);
			String orgLinkSql = "SELECT T.ORGID, T.ORGNAME,T.ORGTYPEID FROM SYS_ORG T WHERE 1 = 1 START WITH T.ORGID = '"+sysUser.getOrgId()+"' CONNECT BY PRIOR T.PARENTORGID = T.ORGID ORDER BY T.ORGTYPEID ASC";
			List<Map<String,Object>> wscMap = ServiceReceiver.getListDataBySql(orgLinkSql,  new HashMap<String,Object>());
			for (Map<String, Object> map : wscMap) {
				if(StringUtils.equals(map.get("orgtypeid").toString(),CommonConstant.orgType.FACTORY.getValue())){
					bizTGgMsgManage.setBelongWscId(map.get("orgid").toString());
					bizTGgMsgManage.setBelongWscnAme(map.get("orgname").toString());
					break;
				}
			}
			bizTGgMsgManage.setBelongDept(sysUser.getOrgId());
			bizTGgMsgManage.setIsHaveRead(Long.parseLong(CommonConstant.STATUS_DISABLE));
			bizTGgMsgManage.setReciverId(idList[i]);
			bizTGgMsgManage.setReciverName(sysUser.getUserName());
			bizTGgMsgManageDAO.save(bizTGgMsgManage);
		}
	}
}
