package com.upsoft.systemweb.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.entity.SysMessageEntity;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.systemweb.dao.MessageDao;
import com.upsoft.systemweb.service.MessageService;

/**
 * 
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 * 
 * 文件名称：MessageServiceImpl.java<br>
 * 摘要：系统消息接口实现类<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.0.0<br>
 * 作者：冉恒鑫<br>
 * 完成日期：2015年3月17日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.0.0<br>
 * 原作者：冉恒鑫<br>
 * 完成日期：2015年3月17日<br>
 */
@Service
public class MessageServiceImpl extends BaseServiceImpl implements MessageService {
	
	@Autowired
	private MessageDao messageDao;
	

	@Override
	public int updateMessage(List<String> messageIds) {
		String sql = "update sys_message set status = 2 where messageId in ( :messageIds)";
		Map<String, Object> pars = new HashMap<String, Object>();
		pars.put("messageIds",messageIds);
		return messageDao.executeSql(sql, pars);
	}
	
	@Override
	public boolean saveMessage(String title, String content, String reciverIds) {
		String[] reciverIdArray = reciverIds.split(",");
		int result = 0;
		for (int i = 0; i < reciverIdArray.length; i++) {
			SysMessageEntity message = new SysMessageEntity();
			message.setTitle(title);
			message.setTitle(content);
			message.setStatus(SysMessageEntity.STATUS_UN_READ);
			message.setSenderId(SysMessageEntity.ADMIN_ID);
			message.setReceiverId(reciverIdArray[i]);
			message.setSendTime(new Date());
			SysMessageEntity reMessage = super.save(message);
			if (reMessage!=null) {
				result++;
			}
		}
		if (result == reciverIdArray.length) {
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Object> queryPagination(PageBean bean,
			Map<String, Object> pars) {
		String sql = getQuerySql(pars);
		Map<String, Object> datas = messageDao.queryPaginationListBySql(sql, pars, bean);
		return datas;
	}

	@Override
	public List<Map<String, Object>> getUserList() {
		String sql = "select userId,userName from sys_user";
		Map<String, Object> pars = new HashMap<String, Object>();
		List<Map<String, Object>> userNameList = messageDao.queryListBySql(sql, pars);
		return userNameList;
	}
	
	private String getQuerySql(Map<String, Object> pars){
		StringBuffer sb = new StringBuffer();
		sb.append("select m.*,u.userName from sys_message m left join sys_user u on m.senderId = u.userId where 1 = 1 ");
		if (pars.containsKey("startTime")) {
			sb.append("and m.sendTime >= :startTime ");
		}
		if (pars.containsKey("endTime")) {
			sb.append("and m.sendTime <= :endTime ");
		}
		if (pars.containsKey("status")) {
			sb.append("and m.status = :status ");
		}
		if (pars.containsKey("senderName")) {
			sb.append("and u.userName like :senderName ");
		}
		return sb.toString();
	}

	@Override
	public Map<String, Object> findMessageAndChangeStatus(String messageId) {
		Map<String, Object> pars = null;
		String updateSql = "update sys_message set status = 2 where messageId = " + "'" +messageId + "'";
		messageDao.executeSql(updateSql, pars);
		String selectSql = "select m.*,u.userName from sys_message m left join sys_user u on m.senderId = u.userId where messageId = " + "'" + messageId + "'";
		List<Map<String, Object>> messageList = messageDao.queryListBySql(selectSql,pars);
		return messageList.size() != 0 ? messageList.get(0) : null ;
	}

	@Override
	public int deleteMessageByIds(List<String> messageIds) {
		String sql = "delete from sys_message where messageId in ( :messageIds) "; 
		Map<String, Object> pars = new HashMap<String, Object>();
		pars.put("messageIds", messageIds);
		return messageDao.executeSql(sql, pars);
	}

	@Override
	public Long queryUnreadMessageByUserId(String userId) {
		String sql = "select count(1) from sys_message m where m.status = :status and receiverid = :userId";
		Map<String, Object> pars = new HashMap<String, Object>();
		pars.put("status", SysMessageEntity.STATUS_UN_READ);
		pars.put("userId", userId);
		return messageDao.queryCountBySql(sql, pars);
	}

	@Override
	public Long queryMessageCountByUserId(String userId) {
		String sql = "select count(1) from sys_message m where receiverid = '" + userId + "'";
		Map<String, Object> pars = null;
		return messageDao.queryCountBySql(sql, pars);
	}

	@Override
	public Map<String, Object> queryCurrentUserMessage(PageBean bean,String userId) {
		String sql = "select m.*,u.userName from sys_message m left join sys_user u on m.senderId = u.userId where m.receiverid = '" + userId + "'";
		Map<String, Object> pars = null;
		return messageDao.queryPaginationListBySql(sql, pars, bean);
	}


	

}
