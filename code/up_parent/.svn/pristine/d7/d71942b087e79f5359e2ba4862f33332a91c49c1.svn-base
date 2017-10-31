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
import com.upsoft.yxsw.constant.Constant;
import com.upsoft.yxsw.constant.DictConstant;
import com.upsoft.yxsw.entity.BizTSbTypesEntity;
import com.upsoft.yxsw.entity.BizTXjTechnicsScopeManage;
import com.upsoft.yxsw.service.BizTXjTechnicsScopeManageService;
import com.upsoft.yxsw.service.BizTXjdItemService;


/**
* Copyright (c) 2017,重庆扬讯软件技术股份有限公司<br>
* All rights reserved.<br>
*
* 文件名称：BizTXjTechnicsScopeManageController.java<br>
* 摘要：简要描述本文件的内容<br>
* -------------------------------------------------------<br>
* 当前版本：1.0<br>
* 作者：陈涛<br>
* 完成日期：2017年9月15日<br>
* -------------------------------------------------------<br>
*/
@Controller
@RequestMapping(BizTXjTechnicsScopeManageController.FORWARD_PREFIX)
public class BizTXjTechnicsScopeManageController  {

	protected static final Logger logger = Logger.getLogger(BizTXjTechnicsScopeManageController.class);
	protected static final String FORWARD_PREFIX = "/technicsScope";
	protected static final String JSP_PR = "/WEB-INF/jsp/technicsScope";
	
	@Autowired
	public BizTXjTechnicsScopeManageService bizTXjTechnicsScopeManageService;
	@Autowired
	public BizTXjdItemService bizTXjdItemService;
	
	
	/**
	 *  初始化页面
	 * @param map
	 * @return
	 */
	@RequestMapping("/init")
	public String init(HttpServletRequest request,ModelMap map){
		
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		String csOrgType = loginInfo.getCsOrgTypeId();
		map.put("csOrgType", csOrgType);
		
		return JSP_PR + "/main";
	}
	
	/**
	 * 获取工艺段树
	 * 
	 * @date 2017年9月15日 下午4:34:45
	 * @author 陈涛
	 * @param request
	 * @return 
	 */
	@RequestMapping("/getTechnicsScopeTree")
	@ResponseBody
	public List<Map<String, Object>> getTechnicsScopeTree(String csOrg,HttpServletRequest request) {
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		List<Map<String, Object>> types = bizTXjTechnicsScopeManageService.queryByTreeId(loginInfo,csOrg);
		for (Map<String, Object> map : types) {
			map.put("parentId", map.get("parentid"));
			map.remove("parentid");
		}
		return types;
	}
	
	/**
	 * 跳转到新增工艺段
	 * 
	 * @date 2017年9月15日 下午2:43:32
	 * @author 陈涛
	 * @param orgId
	 * @param orgName
	 * @param map
	 * @return 
	 */
	@RequestMapping("/addTechnicsScope")
	public String addTechnicsScope(HttpServletRequest request,ModelMap map){
		
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		String csOrgId = loginInfo.getCsOrgId();
		String csOrgName = loginInfo.getCsOrgName();
		
		map.put("csOrgId", csOrgId);
		map.put("csOrgName", csOrgName);
		
		return JSP_PR+"/addTechnicsScope";
	}
	
	/**
	 * 执行新增
	 * @date 2017年9月15日 下午4:43:22
	 * @author 陈涛
	 * @param request
	 * @param map
	 * @param bizTXjTechnicsScopeManage
	 * @return 
	 */
	@RequestMapping("/doAddTechnicsScope")
	@ResponseBody
	public Map<String, Object> doAddTechnicsScope(HttpServletRequest request,ModelMap map,BizTXjTechnicsScopeManage bizTXjTechnicsScopeManage){
		Map<String,Object> msg = new HashMap<String,Object>();
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
			try{
				BizTXjTechnicsScopeManage entity=bizTXjTechnicsScopeManageService.saveTechnicsScope(bizTXjTechnicsScopeManage,loginInfo);
				String technicsId = entity.getTechnicsId();
				msg.put("technicsId", technicsId);
				msg.put("message", "新增工艺段成功");
			}catch(Exception e){
				msg.put("message", "新增工艺段失败");
				logger.error("新增工艺段失败，" + e.getMessage());
			}
		return msg;
	}
	
	/**
	 * 查看工艺段详情
	 * 
	 * @date 2017年9月19日 上午9:26:39
	 * @author 陈涛
	 * @param technicsId
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/showDetail")
	public String showDetail(@RequestParam(value="technicsId",required=true)String technicsId,HttpServletRequest request,ModelMap map){
		BizTXjTechnicsScopeManage technicsScope = new BizTXjTechnicsScopeManage();
		if(StringUtils.isNotBlank(technicsId)){
			technicsScope = bizTXjTechnicsScopeManageService.findOne(technicsId);
		}
		map.put("technicsScope", technicsScope);
		
		return JSP_PR+"/detail";
	}
	/**
	 * 获取巡检点列表
	 * 
	 * @date 2017年9月15日 下午3:17:27
	 * @author 陈涛
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/toInspectionPointList")
	public String toInspectionPointList(@RequestParam(value="technicsId",required=true)String technicsId,HttpServletRequest request ,ModelMap map){
		
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		String csOrgType = loginInfo.getCsOrgTypeId();
		map.put("csOrgType", csOrgType);
		map.put("technicsId", technicsId);
		
		return JSP_PR+"/inspectionPointList";
	}
	
	/**
	 * 跳转到关联巡检点页面
	 * 
	 * @date 2017年9月19日 上午9:35:39
	 * @author 陈涛
	 * @param technicsId
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/toAddItem")
	public String toAddItem(@RequestParam(value="technicsId",required=true)String technicsId,HttpServletRequest request,ModelMap map){
		
		map.put("technicsId", technicsId);
		
		return JSP_PR + "/addItemList";
	}
	
	/**
	 * 获取本场所下未关联的巡检点
	 * 
	 * @date 2017年9月19日 上午9:57:49
	 * @author 陈涛
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/getNoRelatedItemData")
	@ResponseBody
	public Map<String, Object> getNoRelatedItemData(HttpServletRequest request,ModelMap map){
		
		PageBean pageBean = new PageBean(request);
		
		Map<String, Object> params = new HashMap<String, Object>();
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		//获取巡检点名称
		String eqName  = WebUtils.findParameterValue(request, "eqName");
		//获取巡检点位置
		String setAddress = WebUtils.findParameterValue(request, "setAddress");
		
		if (StringUtils.isNotBlank(eqName)) {
			params.put("eqName", eqName);
		}
		if (StringUtils.isNotBlank(setAddress)) {
			params.put("setAddress", setAddress);
		}
		Map<String, Object> result = bizTXjdItemService.getNoRelatedData(pageBean, params,loginInfo);
		
		return result;
	}
	
	/**
	 * 关联巡检点
	 * 
	 * @date 2017年9月19日 上午10:20:42
	 * @author 陈涛
	 * @param technicsId
	 * @param request
	 * @param ids
	 * @param names
	 * @param map
	 * @return 
	 */
	@RequestMapping("/doRelateItem")
	@ResponseBody
	public Map<String,Object> doRelateItem(@RequestParam(value="technicsId",required=true)String technicsId,HttpServletRequest request,String ids,String names,ModelMap map){
		Map<String,Object> msg = new HashMap<String,Object>();
		String[] idsArray = ids.split(",");
		String[] namesArray = names.split(",");
		List<String> idArray = new ArrayList<String>();
		List<String> nameArray = new ArrayList<String>();
		for (int i = 0; i < idsArray.length; i++) {
			idArray.add(idsArray[i]);
			nameArray.add(namesArray[i]);
		}
		try {
			
			bizTXjTechnicsScopeManageService.saveRelated(technicsId,idArray,nameArray,Constant.DETAIL_TYPE.EQUIPMENT.getValue());
			msg.put("message", "关联成功");
		} catch (Exception e) {
			msg.put("message", "关联失败");
			logger.error("关联巡检点失败，" + e.getMessage());
		}
		return msg;
	}
	
	/**
	 * 获取已关联的数据
	 * 
	 * @date 2017年9月19日 上午10:43:03
	 * @author 陈涛
	 * @param technicsId
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/getRelatedItemData")
	@ResponseBody
	public Map<String, Object> getRelatedItemData(@RequestParam(value="technicsId",required=true)String technicsId,HttpServletRequest request,ModelMap map){
		
		PageBean pageBean = new PageBean(request);
		
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		
		Map<String, Object> result = bizTXjdItemService.getRelatedItemData(pageBean, technicsId,loginInfo);
		
		return result;
	}
	
	/**
	 * 删除关联巡检点
	 * 
	 * @date 2017年9月19日 上午11:00:50
	 * @author 陈涛
	 * @param request
	 * @param ids
	 * @param map
	 * @return 
	 */
	@RequestMapping("/delRelate")
	@ResponseBody
	public Map<String,Object> delRelate(HttpServletRequest request,String ids,ModelMap map){
		Map<String,Object> msg = new HashMap<String,Object>();
		String[] idsArray = ids.split(",");
		List<String> idArray = new ArrayList<String>();
		for (int i = 0; i < idsArray.length; i++) {
			idArray.add(idsArray[i]);
		}
		try {
			
			bizTXjTechnicsScopeManageService.delRelated(idArray);
		} catch (Exception e) {
			logger.error("删除失败，" + e.getMessage());
		}
		
		return msg;
	}
	
	/**
	 * 跳转到修改界面
	 * 
	 * @date 2017年9月19日 上午11:05:25
	 * @author 陈涛
	 * @param technicsScopeId
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/toModifyTechnicsScope")
	public String toModifyTechnicsScope(@RequestParam(value="technicsScopeId",required=true)String technicsScopeId,HttpServletRequest request,ModelMap map){
		
		BizTXjTechnicsScopeManage bizTXjTechnicsScopeManage = bizTXjTechnicsScopeManageService.findOne(technicsScopeId);
		
		map.put("bizTXjTechnicsScopeManage", bizTXjTechnicsScopeManage);
		
		return JSP_PR+"/modifyTechnicsScope";
	}
	
	/**
	 * 执行修改
	 * 
	 * @date 2017年9月19日 上午11:26:23
	 * @author 陈涛
	 * @param request
	 * @param map
	 * @param bizTXjTechnicsScopeManage
	 * @return 
	 */
	@RequestMapping("/doModifyTechnicsScope")
	@ResponseBody
	public Map<String, Object> doModifyTechnicsScope(HttpServletRequest request,ModelMap map,BizTXjTechnicsScopeManage bizTXjTechnicsScopeManage){
		Map<String,Object> msg = new HashMap<String,Object>();
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
			try{
				bizTXjTechnicsScopeManageService.updateTechnicsScope(bizTXjTechnicsScopeManage,loginInfo);
				msg.put("message", "修改工艺段成功");
			}catch(Exception e){
				msg.put("message", "修改工艺段失败");
				logger.error("修改工艺段失败，" + e.getMessage());
			}
		return msg;
	}
	
	/**
	 * 删除工艺段
	 * 
	 * @date 2017年9月19日 下午4:09:10
	 * @author 陈涛
	 * @param technicsScopeId
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/delTechnicsScope")
	@ResponseBody
	public Map<String, Object> delTechnicsScope(@RequestParam(value="technicsScopeId",required=true)String technicsScopeId,HttpServletRequest request,ModelMap map){
		Map<String, Object> msg = new HashMap<String,Object>();
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		try{
			bizTXjTechnicsScopeManageService.delTechnicsScope(technicsScopeId,loginInfo);
			msg.put("message", "删除成功");
		}catch(Exception e){
			msg.put("message", "删除失败");
			logger.error("删除失败，" + e.getMessage());
		}
		return msg;
	}
	
	@RequestMapping("/getSupportData")
	@ResponseBody
	public List<Map<String, Object>> getSelectSupport(HttpServletRequest request){
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		List<Map<String, Object>> org = ServiceReceiver.iterateOrgById(loginInfo.getCsOrgId());
		List<Map<String, Object>> orgs = new ArrayList<Map<String,Object>>();
		for (Map<String, Object> map : org) {
			String  orgTypeId = map.get("orgtypeid").toString();
			if(orgTypeId.equals("3")){
				Map<String,Object> orgMap = new HashMap<String, Object>();
				orgMap.put("key", map.get("orgid"));
				orgMap.put("value", map.get("orgname"));
				orgs.add(orgMap);
			}
		}
		return orgs;
	}
}
