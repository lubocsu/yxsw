package com.upsoft.yxsw.mobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upsoft.login.support.webservice.ServiceReceiver;
import com.upsoft.login.support.webservice.SysUtils;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.bean.ResultBean;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.constant.LoginTipMsgConstants;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.util.ExceptionFormatUtil;
import com.upsoft.yxsw.service.BizTGgMsgManageService;
import com.upsoft.yxsw.service.BizTGgNoticeManageService;

/**
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：MobileNewsController.java<br>
* 摘要：APP消息接口<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：胡毅<br>
* 完成日期：2017年9月19日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：胡毅<br>
* 完成日期：2017年9月19日<br>
 */
@Controller
@RequestMapping("/mobile")
public class NewsController {

	private Logger logger = Logger.getLogger(MobileController.class);
	
	@Autowired
	public BizTGgMsgManageService bizTGgMsgManageService;
	@Autowired
	public BizTGgNoticeManageService bizTGgNoticeManageService;
	
	/**
	 * 获取登录用户厂站下面的接收人
	 * @date 2017年9月19日 上午9:24:39
	 * @author 胡毅
	 * @return
	 */
	@RequestMapping(value = "/getNewsReceiver",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean getNewsReceiver(@RequestParam(required = true)String tokenId,HttpServletRequest request){
		ResultBean result = new ResultBean();
		if(!ServiceReceiver.checkLogin(tokenId)){
			result.setFlag(false);
    		result.setMessage(LoginTipMsgConstants.LOGIN_EXPIRED);
    		return result;
		}
		try {
			WSLoginInfoBean loignInfo = SysUtils.getLoginInfo(request);
			List<Map<String,Object>> userOrgTree = ServiceReceiver.getUserOrgTreeByOrgId(loignInfo.getCsOrgId(),null);
			List<Map<String,Object>> userList = new ArrayList<Map<String,Object>>();
			for (Map<String, Object> map : userOrgTree) {
				if(StringUtils.equals(map.get("clickExpand").toString(), "false")){
					if(StringUtils.equals(map.get("id").toString(), loignInfo.getUser().getUserId())){
						continue;
					}
					Map<String,Object> userMap = new HashMap<String,Object>();
					userMap.put("id",map.get("id"));
					userMap.put("name", map.get("name"));
					userList.add(userMap);
				}
			}
			if(userList.size()>0){
				Map<String,Object> data = new HashMap<String,Object>();
				data.put("userList", userList);
				result.setData(data);
			}
		} catch (Exception e) {
			logger.error("获取消息用户列表异常："+ExceptionFormatUtil.formatExceptionTrace(e));
			result.setFlag(false);
			result.setMessage("获取消息用户列表异常");
		}
		return result;
	}
	
	/**
	 * 查看自己发送和接收的消息
	 * @date 2017年9月19日 上午10:08:46
	 * @author 胡毅
	 * @param tokenId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getMyNewsList",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean getNewsList(@RequestParam(required = true)String tokenId,HttpServletRequest request){
		PageBean pageBean = new PageBean(request);
		ResultBean result = new ResultBean();
		if(!ServiceReceiver.checkLogin(tokenId)){
			result.setFlag(false);
    		result.setMessage(LoginTipMsgConstants.LOGIN_EXPIRED);
    		return result;
		}
		try {
			SysUser user = SysUtils.getLoginSysUser(request);
			Map<String, Object> data = bizTGgMsgManageService.getMyMessageData(pageBean,user.getUserId());
			result.setData(data);
		} catch (Exception e) {
			logger.error("获取消息列表异常："+ExceptionFormatUtil.formatExceptionTrace(e));
			result.setFlag(false);
			result.setMessage("获取消息列表异常");
		}
		return result;
	}
	
	/**
	 * 将消息标记为已读
	 * @date 2017年9月19日 下午1:58:33
	 * @author 胡毅
	 * @param request
	 * @param tokenId
	 * @param msgId
	 * @return
	 */
	@RequestMapping(value = "/readMsg",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean readMsg(@RequestParam(required = true)String tokenId,@RequestParam(required = true)String msgId){
		ResultBean result = new ResultBean();
		if(!ServiceReceiver.checkLogin(tokenId)){
			result.setFlag(false);
    		result.setMessage(LoginTipMsgConstants.LOGIN_EXPIRED);
    		return result;
		}
		try {
			bizTGgMsgManageService.updateMsg(msgId);
		} catch (Exception e) {
			result.setFlag(false);
			result.setMessage("标记消息ID为："+msgId+"的消息为已读失败："+e.getMessage());
			logger.error("标记消息ID为："+msgId+"的消息为已读失败："+ExceptionFormatUtil.formatExceptionTrace(e));
		}
		return result;
	}
	
	/**
	 * 新增一条消息
	 * @date 2017年9月19日 下午2:07:44
	 * @author 胡毅
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/doAddMsg",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean doAddMsg(HttpServletRequest request,
										@RequestParam(required = true)String tokenId,
										@RequestParam(required = true)String title,
										@RequestParam(required = true)String receiverId,
										@RequestParam(required = true)String important,String content){
		ResultBean result = new ResultBean();
		if(!ServiceReceiver.checkLogin(tokenId)){
			result.setFlag(false);
    		result.setMessage(LoginTipMsgConstants.LOGIN_EXPIRED);
    		return result;
		}
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		try{
			bizTGgMsgManageService.saveMsg(title,content,receiverId,important,loginInfo);
//			Map<String,Object> data = new HashMap<String,Object>();
//			result.setData(data);
		}catch(Exception e){
			result.setFlag(false);
			result.setMessage("保存消息【"+title+"】失败："+e.getMessage());
			logger.error("保存消息【"+title+"】失败："+ExceptionFormatUtil.formatExceptionTrace(e));
		}
		return result;
	}
	
	/**
	 * 获取公告列表
	 * @date 2017年9月19日 下午3:35:18
	 * @author 胡毅
	 * @param request
	 * @param tokenId
	 * @param readed 查询已读、未读公告，若为空，则查询所有
	 * @return
	 */
	@RequestMapping(value = "/getNoticeList",method=RequestMethod.POST)
	@ResponseBody
	public ResultBean getNoticeList(HttpServletRequest request,@RequestParam(required = true)String tokenId){
		PageBean pageBean = new PageBean(request);
		ResultBean result = new ResultBean();
		if(!ServiceReceiver.checkLogin(tokenId)){
			result.setFlag(false);
    		result.setMessage(LoginTipMsgConstants.LOGIN_EXPIRED);
    		return result;
		}
		try {
			SysUser user = SysUtils.getLoginSysUser(request);
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
			Map<String, Object> data = bizTGgNoticeManageService.getNoticeData(pageBean,user,basePath);
			result.setData(data);
		} catch (Exception e) {
			result.setFlag(false);
			result.setMessage("获取公告列表失败："+e.getMessage());
			logger.error("获取公告列表失败："+ExceptionFormatUtil.formatExceptionTrace(e));
		}
		return result;
	}
}
