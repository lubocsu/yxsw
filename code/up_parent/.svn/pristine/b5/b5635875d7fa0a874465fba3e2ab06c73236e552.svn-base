package com.upsoft.systemweb.service;

import java.util.List;
import java.util.Map;

import com.upsoft.system.bean.PageBean;
import com.upsoft.system.service.BaseService;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 *
 * 文件名称：MessageService.java<br>
 * 摘要：简要描述本文件的内容<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.0.0<br>
 * 作者：冉恒鑫<br>
 * 完成日期：2015年3月17日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.0.0<br>
 * 原作者：冉恒鑫<br>
 * 完成日期：2015年3月17日<br>
 */
public interface MessageService extends BaseService{
	
	
	
	/**
	 * 通用数据源(内容、数据)，可以带有分页参数
	 * 
	 * @date 2015年1月26日 下午2:15:52
	 * @author 冉恒鑫
	 * @param bean
	 * @param pars
	 * @return
	 */
	public Map<String, Object> queryPagination(PageBean bean, Map<String, Object> pars);
	
	/**
	 * 给下拉菜单提供用户集
	 * @date 2015年3月20日 下午3:21:43
	 * @author 冉恒鑫
	 * @return
	 */
	public List<Map<String, Object>> getUserList();
	
	/**
	 * 查看系统消息并且更改消息状态
	 * @date 2015年3月25日 下午4:09:48
	 * @author 冉恒鑫
	 * @param messageId
	 * @return
	 */
	public Map<String, Object> findMessageAndChangeStatus(String messageId);
	
	/**
	 * 批量和单个删除系统消息
	 * @date 2015年3月25日 下午4:11:57
	 * @author 冉恒鑫
	 * @param messageIds
	 * @return
	 */
	public int deleteMessageByIds(List<String> messageIds);
	
	/**
	 * 发送系统消息
	 * @date 2015年3月25日 下午4:12:09
	 * @author 冉恒鑫
	 * @param title
	 * @param content
	 * @param reciverIds
	 * @return
	 */
	public boolean saveMessage(String title, String content, String reciverIds);
	
	/**
	 * 批量和单个标记系统消息
	 * @date 2015年3月25日 下午5:54:38
	 * @author 冉恒鑫
	 * @param messageIds
	 * @return
	 */
	public int updateMessage(List<String> messageIds);
	
	/**
	 * 根据当前登录用户取出未读的消息
	 * @date 2015年4月14日 下午8:32:28
	 * @author 冉恒鑫
	 * @param userId
	 * @return
	 */
	public Long queryUnreadMessageByUserId(String userId);
	
	/**
	 * 根据当前登录用户取该用户所有消息
	 * @date 2015年4月15日 上午9:27:12
	 * @author 冉恒鑫
	 * @param userId
	 * @return
	 */
	public Long queryMessageCountByUserId(String userId);
	
	/**
	 * 根据当前登录用户取得用户所有的消息进行分页显示
	 * @date 2015年4月15日 下午2:53:30
	 * @author 冉恒鑫
	 * @param bean
	 * @param pars
	 * @return
	 */
	public Map<String, Object> queryCurrentUserMessage(PageBean bean, String userId);
	
}
