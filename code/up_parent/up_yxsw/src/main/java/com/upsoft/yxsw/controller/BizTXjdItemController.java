package com.upsoft.yxsw.controller;


import java.util.HashMap;
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

import com.upsoft.login.support.webservice.SysUtils;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.yxsw.entity.BizTXjdItem;
import com.upsoft.yxsw.service.BizTXjdItemService;

/**
* Copyright (c) 2017,重庆扬讯软件技术股份有限公司<br>
* All rights reserved.<br>
*
* 文件名称：BizTXjdItemController.java<br>
* 摘要：简要描述本文件的内容<br>
* -------------------------------------------------------<br>
* 当前版本：1.0<br>
* 作者：陈涛<br>
* 完成日期：2017年9月15日<br>
* -------------------------------------------------------<br>
*/
@Controller
@RequestMapping(BizTXjdItemController.FORWARD_PREFIX)
public class BizTXjdItemController  {
	
	protected static final Logger logger = Logger.getLogger(BizTXjdItemController.class);
	protected static final String FORWARD_PREFIX = "/xjItem";
	private static final String JSP_PR = "/WEB-INF/jsp/xjItem";
	
	@Autowired
	public BizTXjdItemService bizTXjdItemService;
	
	
	/**
	 *  初始化页面
	 * @param map
	 * @return
	 */
	@RequestMapping("/init")
	public String init(HttpServletRequest request,ModelMap map){
		
		return JSP_PR + "/main";
	}
	
	/**
	 * 获取巡检点数据
	 * 
	 * @date 2017年9月15日 下午7:30:48
	 * @author 陈涛
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/getXjItemData")
	@ResponseBody
	public Map<String, Object> getXjItemData(HttpServletRequest request,ModelMap map){
		
		PageBean pageBean = new PageBean(request);
		
		Map<String, Object> params = new HashMap<String, Object>();
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		//获取厂商名称
		String xjdItemName  = WebUtils.findParameterValue(request, "xjdItemName");
		//获取是否停用
		String xjdItemAddress = WebUtils.findParameterValue(request, "xjdItemAddress");
		//获取厂商性质
		String rfidCode = WebUtils.findParameterValue(request, "rfidCode");
		
		if (StringUtils.isNotBlank(xjdItemName)) {
			params.put("xjdItemName", xjdItemName);
		}
		if (StringUtils.isNotBlank(xjdItemAddress)) {
			params.put("xjdItemAddress", xjdItemAddress);
		}
		if (StringUtils.isNotBlank(rfidCode)) {
			params.put("rfidCode", rfidCode);
		}
		
		return bizTXjdItemService.getXjItemData(pageBean, params,loginInfo);
	}
	
	/**
	 * 跳转到新增页面
	 * 
	 * @date 2017年9月15日 下午8:02:57
	 * @author 陈涛
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("toAddXjItem")
	public String toAddXjItem(HttpServletRequest request,ModelMap map){
		
		return JSP_PR + "/addXjItem";
	}
	
	/**
	 * 新增巡检点
	 * 
	 * @date 2017年9月15日 下午8:17:28
	 * @author 陈涛
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/doAddXjItem")
	@ResponseBody
	public Map<String, Object> doAddXjItem(HttpServletRequest request,ModelMap map){
		Map<String, Object> msg = new HashMap<String,Object>();
		//获取巡检点名称
		String xjdItemName  = WebUtils.findParameterValue(request, "xjdItemName");
		//获取巡检点位置
		String xjdItemAddress = WebUtils.findParameterValue(request, "xjdItemAddress");
		//获取RFID编号
		String rfidCode = WebUtils.findParameterValue(request, "rfidCode");
		//获取巡检点说明
		String xjdItemDesc = WebUtils.findParameterValue(request, "xjdItemDesc");
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		try{
				
			bizTXjdItemService.saveXjItem(xjdItemName,xjdItemAddress,rfidCode,xjdItemDesc,loginInfo);
			msg.put("message", "新增巡检点成功！");
		}catch(Exception e){
				
			msg.put("message", "新增巡检点失败!");
			logger.error("新增巡检点失败，" + e.getMessage());
		}
		return msg;
	}
	
	/**
	 * 验证RFID是否存在
	 * 
	 * @date 2017年9月16日 上午9:23:47
	 * @author 陈涛
	 * @param request
	 * @param map
	 * @param validateValue
	 * @return 
	 */
	@ResponseBody
	@RequestMapping("/validateRfidCode")
	public Map<String, Object> validateRfidCode(HttpServletRequest request, ModelMap map, String validateValue){
		
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		Boolean isExists = bizTXjdItemService.rfidExists(validateValue);
		
		if(isExists){
			result.put("valid", false);
		}else{
			result.put("valid", true);
		}
		
		Map<String, Object> resultJson = new HashMap<String, Object>();
		resultJson.put("validateResult", result);
		return resultJson;
	}
	
	/**
	 * 查看详情
	 * 
	 * @date 2017年9月16日 上午9:58:16
	 * @author 陈涛
	 * @param xjItemId
	 * @param request
	 * @param mod
	 * @return 
	 */
	@RequestMapping("/xjItemDetail")
	public String xjItemDetail(@RequestParam(value="xjItemId",required=true)String xjItemId,HttpServletRequest request,ModelMap mod){
		
		BizTXjdItem result =  bizTXjdItemService.findOneById(xjItemId);
		mod.put("result", result);
		
		return JSP_PR+"/detail";
	}
	
	@RequestMapping("/toModifyXjItem")
	public String toModifyFactory(@RequestParam(value="xjItemId",required=true)String xjItemId,HttpServletRequest request,ModelMap map){
		
		BizTXjdItem result =  bizTXjdItemService.findOneById(xjItemId);
		map.put("result", result);
		
		return JSP_PR+"/modifyXjItem";
	}
	
	/**
	 * 修改巡检点
	 * 
	 * @date 2017年9月16日 上午10:33:49
	 * @author 陈涛
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/doModifyXjItem")
	@ResponseBody
	public Map<String, Object> doModifyXjItem(HttpServletRequest request,ModelMap map){
		Map<String, Object> msg = new HashMap<String,Object>();
		//获取id
		String xjdItemId  = WebUtils.findParameterValue(request, "xjdItemId");
		//获取巡检点名称
		String xjdItemName  = WebUtils.findParameterValue(request, "xjdItemName");
		//获取巡检点位置
		String xjdItemAddress = WebUtils.findParameterValue(request, "xjdItemAddress");
		//获取RFID编号
		String rfidCode = WebUtils.findParameterValue(request, "rfidCode");
		//获取巡检点说明
		String xjdItemDesc = WebUtils.findParameterValue(request, "xjdItemDesc");
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		try{
				
			bizTXjdItemService.updateXjItem(xjdItemId,xjdItemName,xjdItemAddress,rfidCode,xjdItemDesc,loginInfo);
			msg.put("message", "修改巡检点成功！");
		}catch(Exception e){
				
			msg.put("message", "修改巡检点失败!");
			logger.error("修改巡检点失败，" + e.getMessage());
		}
		return msg;
	}
	
	/**
	 * 验证是否已挂接工艺段
	 * 
	 * @date 2017年9月16日 上午11:38:09
	 * @author 陈涛
	 * @param request
	 * @param map
	 * @param id
	 * @return 
	 */
	@ResponseBody
	@RequestMapping("/checkItem")
	public Map<String, Object> checkItem(@RequestParam(value="id",required=true)String id,HttpServletRequest request, ModelMap map){
		
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> msg = new HashMap<String,Object>();
		Map<String, Object> text = new HashMap<String,Object>();
		Boolean isExists = bizTXjdItemService.checkItem(id);
		
		if(isExists){
			result.put("valid", false);
			msg.put("message", "已挂接到工艺段，不能删除！");
		}else{
			result.put("valid", true);
		}
		text.put("result", result);
		text.put("msg", msg);
		
		return text;
	}
	
	/**
	 * 逻辑删除巡检点
	 * 
	 * @date 2017年9月16日 上午11:58:51
	 * @author 陈涛
	 * @param request
	 * @param id
	 * @return 
	 */
	@RequestMapping("/delXjItem")
	@ResponseBody
	public Map<String, Object> delXjItem(HttpServletRequest request,String id){
		Map<String,Object> msg = new HashMap<String,Object>();
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		try {
			
			bizTXjdItemService.updateXjItemByIds(id,loginInfo);
			msg.put("message", "删除成功!");
		} catch (Exception e) {
			msg.put("message", "删除失败!");
			logger.error("删除失败，" + e.getMessage());
		}
		
		return msg;
	}
}
