package com.upsoft.yxsw.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.upsoft.login.support.webservice.ServiceReceiver;
import com.upsoft.login.support.webservice.SysUtils;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.entity.SysUser;
import com.upsoft.yxsw.constant.DictConstant;
import com.upsoft.yxsw.entity.BizTGgMsgManage;
import com.upsoft.yxsw.service.BizTGgMsgManageService;


/**
* Copyright (c) 2017,重庆扬讯软件技术股份有限公司<br>
* All rights reserved.<br>
*
* 文件名称：BizTGgMsgManageController.java<br>
* 摘要：消息接收和发送Controller<br>
* -------------------------------------------------------<br>
* 当前版本：1.0<br>
* 作者：陈涛<br>
* 完成日期：2017年9月11日<br>
* -------------------------------------------------------<br>
*/
@Controller
@RequestMapping(BizTGgMsgManageController.FORWARD_PREFIX)
public class BizTGgMsgManageController  {
	
	protected static final Logger logger = Logger.getLogger(BizTGgMsgManageController.class);
	protected static final String FORWARD_PREFIX = "/msgManage";
	private static final String JSP_PR = "/WEB-INF/jsp/msgManage";
	
	@Autowired
	public BizTGgMsgManageService bizTGgMsgManageService;
	
	
	
	
	/**
	 * 跳转到消息发送主页
	 * 
	 * @date 2017年9月11日 下午2:12:58
	 * @author 陈涛
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/inits")
	public String inits(HttpServletRequest request,ModelMap map){
		
		return JSP_PR + "/send/main";
	}
	
	/**
	 * 获取消息发送数据
	 * 
	 * @date 2017年9月11日 下午2:23:52
	 * @author 陈涛
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/getMsgSendData")
	@ResponseBody
	public Map<String, Object> getMsgSendData(HttpServletRequest request,ModelMap map){
		
		PageBean pageBean = new PageBean(request);
		
		Map<String, Object> params = new HashMap<String, Object>();
		//获取消息标题
		String title  = WebUtils.findParameterValue(request, "title");
		//获取消息内容
		String content = WebUtils.findParameterValue(request, "content");
		//获取发送开始时间
		String startTime = WebUtils.findParameterValue(request, "startTime");
		//获取发送接收时间
		String endTime = WebUtils.findParameterValue(request, "endTime");
		
		//获取用户信息
		SysUser user = SysUtils.getLoginSysUser(request);
		String userId = user.getUserId();
		
		if (StringUtils.isNotBlank(title)) {
			params.put("title", title);
		}
		if (StringUtils.isNotBlank(content)) {
			params.put("content", content);
		}
		if (StringUtils.isNotBlank(startTime)) {
			params.put("startTime", startTime);
		}
		if (StringUtils.isNotBlank(endTime)) {
			params.put("endTime", endTime);
		}
		if (StringUtils.isNotBlank(userId)) {
			params.put("userId", userId);
		}
		return bizTGgMsgManageService.getMsgSendData(pageBean, params);
	}
	
	/**
	 * 跳转到新增页面
	 * 
	 * @date 2017年9月11日 下午3:52:40
	 * @author 陈涛
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/toAddMsg")
	public String toAddMsg(HttpServletRequest request,ModelMap map){
		
		map.put("importantLevel", DictConstant.IMPORTANT_LEVEL.getValue());
		return JSP_PR + "/send/addMsg";
	}
	
	/**
	 * 执行新增
	 * 
	 * @date 2017年9月11日 下午3:55:40
	 * @author 陈涛
	 * @param request
	 * @param map
	 * @param bizTGgMsgManage
	 * @return 
	 */
	@RequestMapping("/doAddMsg")
	@ResponseBody
	public Map<String, Object> doAddMsg(HttpServletRequest request,ModelMap map){
		Map<String, Object> msg = new HashMap<String,Object>();
		//获取消息标题
		String title  = WebUtils.findParameterValue(request, "title");
		//获取消息内容
		String content = WebUtils.findParameterValue(request, "content");
		//获取接收人ID
		String reciverId = WebUtils.findParameterValue(request, "reciverId");
		//获取重要程度
		String importantLevel = WebUtils.findParameterValue(request, "importantLevel");
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		try{
			bizTGgMsgManageService.saveMsg(title,content,reciverId,importantLevel,loginInfo);
			msg.put("message", "新增消息成功！");
		}catch(Exception e){
			msg.put("message", "新增消息失败!");
			logger.error("新增消息失败，" + e.getMessage());
		}
		return msg;
	}
	
	/**
	 * 查看消息
	 * 
	 * @date 2017年9月11日 下午6:00:06
	 * @author 陈涛
	 * @param id
	 * @param backURL
	 * @param request
	 * @param mod
	 * @return 
	 */
	@RequestMapping("/sMsgDetail")
	public String sMsgDetail(@RequestParam(value="id",required=true)String id,HttpServletRequest request,ModelMap mod){
		Map<String, Object> result = new HashMap<String,Object>();
		result = bizTGgMsgManageService.findOneById(id);
		mod.put("result", result);
		
		return JSP_PR+"/send/detail";
	}
	
	/**
	 *  跳转到消息接收界面
	 *  
	 * @param map
	 * @return
	 */
	@RequestMapping("/initr")
	public String initr(HttpServletRequest request,ModelMap map){
		
		map.put("isHaveRead", DictConstant.CHECKITEM_SFMR.getValue());
		return JSP_PR + "/receive/main";
	}
	
	/**
	 * 获取消息接收数据
	 * 
	 * @date 2017年9月11日 下午1:52:50
	 * @author 陈涛
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/getMsgReceiveData")
	@ResponseBody
	public Map<String, Object> getMsgReceiveData(HttpServletRequest request,ModelMap map){
		
		PageBean pageBean = new PageBean(request);
		
		Map<String, Object> params = new HashMap<String, Object>();
		//获取消息标题
		String title  = WebUtils.findParameterValue(request, "title");
		//获取消息内容
		String content = WebUtils.findParameterValue(request, "content");
		//获取发送人
		String creatorName = WebUtils.findParameterValue(request, "creatorName");
		//获取是否已读
		String isHaveRead = WebUtils.findParameterValue(request, "isHaveRead");
		
		//获取用户信息
		SysUser user = SysUtils.getLoginSysUser(request);
		String userId = user.getUserId();
		
		if (StringUtils.isNotBlank(title)) {
			params.put("title", title);
		}
		if (StringUtils.isNotBlank(content)) {
			params.put("content", content);
		}
		if (StringUtils.isNotBlank(creatorName)) {
			params.put("creatorName", creatorName);
		}
		if (StringUtils.isNotBlank(isHaveRead)) {
			params.put("isHaveRead", isHaveRead);
		}
		if (StringUtils.isNotBlank(userId)) {
			params.put("userId", userId);
		}
		return bizTGgMsgManageService.getMsgReceiveData(pageBean, params);
	}
	
	/**
	 * 查看消息
	 * 
	 * @date 2017年9月11日 下午7:58:05
	 * @author 陈涛
	 * @param id
	 * @param request
	 * @param mod
	 * @return 
	 */
	@RequestMapping("/rMsgDetail")
	public String rMsgDetail(@RequestParam(value="id",required=true)String id,HttpServletRequest request,ModelMap mod){
		BizTGgMsgManage result = new BizTGgMsgManage();
		try {
			
			 result = bizTGgMsgManageService.updateMsg(id);
		} catch (Exception e) {
			logger.error("获取消息失败"+e.getMessage());
		}
		mod.put("result", result);
		
		return JSP_PR+"/receive/detail";
	}
	
	/**
	 * 单个或者批量设为已读
	 * 
	 * @date 2017年9月11日 下午8:21:23
	 * @author 陈涛
	 * @param request
	 * @param ids
	 * @return 
	 */
	@RequestMapping("/readMsg")
	@ResponseBody
	public Map<String, Object> readMsg(HttpServletRequest request,String ids){
		String[] idsArray = ids.split(",");
		List<String> idArray = new ArrayList<String>();
		for (int i = 0; i < idsArray.length; i++) {
			idArray.add(idsArray[i]);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			int result = bizTGgMsgManageService.updateMsgByIds(idArray);
			if (result == idsArray.length) {
				map.put("status", "设为已读成功!");
			}else{
				map.put("status", "设为已读失败!");
				logger.error("设为已读失败！");
			}
		} catch (Exception e) {
			map.put("status", "设为已读失败!");
			logger.error("设为已读失败，" + e.getMessage());
		}
		return map;
	}
	
	
	/**
	 * 获取接收人下拉树
	 * 
	 * @date 2017年9月13日 下午2:18:10
	 * @author 陈涛
	 * @param request
	 * @return 
	 */
	@RequestMapping("/getPeopleTree")
	@ResponseBody
	public Map<String,Object> getPeopleTree(HttpServletRequest request){
		Map<String, Object> zNodes = new HashMap<String, Object>();
		
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		String orgId = loginInfo.getCsOrgId();
		List<Map<String,Object>> result = ServiceReceiver.getUserOrgTreeByOrgId(orgId,loginInfo.getUser().getUserId());
		
		zNodes.put("treeNodes", result);
		
		return zNodes;
	}
}
