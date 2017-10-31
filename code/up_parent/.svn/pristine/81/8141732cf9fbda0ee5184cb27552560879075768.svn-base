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

import com.upsoft.login.support.webservice.SysUtils;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.constant.CommonConstant;
import com.upsoft.system.entity.SysUser;
import com.upsoft.yxsw.constant.DictConstant;
import com.upsoft.yxsw.entity.BizTBaseFactoryInfoEntity;
import com.upsoft.yxsw.service.EquipmentFactoryService;

/**
* Copyright (c) 2017,重庆扬讯软件技术股份有限公司<br>
* All rights reserved.<br>
*
* 文件名称：EqFactoryManagementController.java<br>
* 摘要：设备厂商管理Controller<br>
* -------------------------------------------------------<br>
* 当前版本：1.0<br>
* 作者：陈涛<br>
* 完成日期：2017年9月9日<br>
* -------------------------------------------------------<br>
*/
@Controller
@RequestMapping(EqFactoryManagementController.FORWARD_PREFIX)
public class EqFactoryManagementController {

	protected static final Logger logger = Logger.getLogger(EqFactoryManagementController.class);
	protected static final String FORWARD_PREFIX = "/eqFactory";
	protected static final String JSP_PR = "/WEB-INF/jsp/eqFactory";
	
	@Autowired
	private EquipmentFactoryService equipmentFactoryService;
	/**
	 * 跳转到初始化页面
	 * 
	 * @date 2017年9月9日 下午2:28:34
	 * @author 陈涛
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/init")
	public String init(HttpServletRequest request,ModelMap map){
		
		//获取厂商名称
		String name  = WebUtils.findParameterValue(request, "factoryName");
		//获取是否停用
		String outService = WebUtils.findParameterValue(request, "outService");
		//获取厂商性质
		String factoryXz = WebUtils.findParameterValue(request, "factoryXz");
		//获取厂商类型
		String factoryType = WebUtils.findParameterValue(request, "factoryType");
		
		map.put("name", name);
		map.put("outService", outService);
		map.put("factoryXz", factoryXz);
		map.put("factoryType", factoryType);
		
		//是否停用
		map.put("out_Service", DictConstant.CHECKITEM_SFMR.getValue());
		//厂商类型
		map.put("factory_Type", DictConstant.FACTORY_TYPE.getValue());
		//厂商性质
		map.put("factory_Xz", DictConstant.FACTORY_XZ.getValue());
		
		return JSP_PR + "/main";
	}
	
	/**
	 * 获取设备厂商数据
	 * @date 2017年9月9日 下午2:36:18
	 * @author 陈涛
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/getEqFactoryData")
	@ResponseBody
	public Map<String, Object> getEqFactoryData(HttpServletRequest request,ModelMap map){
		
		PageBean pageBean = new PageBean(request);
		
		Map<String, Object> params = new HashMap<String, Object>();
		//获取厂商名称
		String name  = WebUtils.findParameterValue(request, "factoryName");
		//获取是否停用
		String outService = WebUtils.findParameterValue(request, "outService");
		//获取厂商性质
		String factoryXz = WebUtils.findParameterValue(request, "factoryXz");
		//获取厂商类型
		String factoryType = WebUtils.findParameterValue(request, "factoryType");
		
		if (StringUtils.isNotBlank(name)) {
			params.put("name", name);
		}
		if (StringUtils.isNotBlank(outService)) {
			params.put("outService", outService);
		}
		if (StringUtils.isNotBlank(factoryXz)) {
			params.put("factoryXz", factoryXz);
		}
		if (StringUtils.isNotBlank(factoryType)) {
			params.put("factoryType", factoryType);
		}
		return equipmentFactoryService.getEqFactoryData(pageBean, params);
	}
	
	/**
	 * 跳转到用于弹出框的厂家列表页面
	 * @date 2017年9月13日 下午5:59:44
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/toListMini")
	public String toListMini(HttpServletRequest request,ModelMap map){
		
		//获取厂商名称
		String name  = WebUtils.findParameterValue(request, "factoryName");
		//获取厂商性质
		String factoryXz = WebUtils.findParameterValue(request, "factoryXz");
		//获取厂商类型
		String factoryType = WebUtils.findParameterValue(request, "factoryType");
		
		map.put("name", name);
		map.put("factoryXz", factoryXz);
		map.put("factoryType", factoryType);
		
		return JSP_PR + "/fac_list_mini";
	}
	
	/**
	 * 获取用于弹出框的厂家列表
	 * @date 2017年9月13日 下午5:57:40
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/getEqFactoryListMini")
	@ResponseBody
	public Map<String, Object> getEqFactoryListMini(HttpServletRequest request,ModelMap map){
		
		PageBean pageBean = new PageBean(request);
		
		Map<String, Object> params = new HashMap<String, Object>();
		//获取厂商名称
		String name  = WebUtils.findParameterValue(request, "factoryName");
		//获取厂商性质
		String factoryXz = WebUtils.findParameterValue(request, "factoryXz");
		//获取厂商类型
		String factoryType = WebUtils.findParameterValue(request, "factoryType");
		
		if (StringUtils.isNotBlank(name)) {
			params.put("name", name);
		}
		if (StringUtils.isNotBlank(factoryXz)) {
			params.put("factoryXz", factoryXz);
		}
		if (StringUtils.isNotBlank(factoryType)) {
			params.put("factoryType", factoryType);
		}
		//只获取启用的厂家
		params.put("outService", CommonConstant.STATUS_YoN.NO);
		return equipmentFactoryService.getEqFactoryData(pageBean, params);
	}
	
	/**
	 * 跳转到新增厂商页面
	 * 
	 * @date 2017年9月9日 下午3:42:47
	 * @author 陈涛
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("toAddFactory")
	public String toAddFactory(HttpServletRequest request,ModelMap map){
		//是否停用
		map.put("out_Service", DictConstant.CHECKITEM_SFMR.getValue());
		//厂商类型
		map.put("factory_Type", DictConstant.FACTORY_TYPE.getValue());
		//厂商性质
		map.put("factory_Xz", DictConstant.FACTORY_XZ.getValue());
		
		return JSP_PR + "/addFactory";
	}
	
	/**
	 * 执行新增
	 * 
	 * @date 2017年9月9日 下午5:10:30
	 * @author 陈涛
	 * @param request
	 * @param map
	 * @param bizTBaseFactoryInfoEntity
	 * @return 
	 */
	@RequestMapping("/doAddEquipmentFactory")
	@ResponseBody
	public Map<String, Object> doAddEquipmentFactory(HttpServletRequest request,ModelMap map,BizTBaseFactoryInfoEntity bizTBaseFactoryInfoEntity){
		Map<String, Object> msg = new HashMap<String,Object>();
		//获取用户信息
		SysUser user = SysUtils.getLoginSysUser(request);
		try{
			equipmentFactoryService.saveEquipmentFactory(bizTBaseFactoryInfoEntity,user);
			msg.put("message", "新增设备厂商成功！");
		}catch(Exception e){
			msg.put("message", "新增设备厂商失败!");
			logger.error("新增设备厂商失败，" + e.getMessage());
		}
		return msg;
	}
	
	/**
	 * 查看详情
	 * 
	 * @date 2017年9月9日 下午6:09:48
	 * @author 陈涛
	 * @param id
	 * @param backURL
	 * @param request
	 * @param mod
	 * @return 
	 */
	@RequestMapping("/factoryDetail")
	public String factoryDetail(@RequestParam(value="id",required=true)String id,
			String backURL,HttpServletRequest request,ModelMap mod){
		Map<String, Object> result = new HashMap<String,Object>();
		mod.put("hidBack", WebUtils.findParameterValue(request, "hidBack"));	//是否隐藏返回按钮
		result = equipmentFactoryService.findOneById(id);
		mod.put("result", result);
		mod.put("backURL", backURL);
		
		return JSP_PR+"/detail";
	}
	
	/**
	 * 跳转到修改页面
	 * 
	 * @date 2017年9月11日 上午9:50:03
	 * @author 陈涛
	 * @param id
	 * @param request
	 * @param mod
	 * @return 
	 */
	@RequestMapping("/toModifyFactory")
	public String toModifyFactory(@RequestParam(value="id",required=true)String id,String backURL,HttpServletRequest request,ModelMap map){
		Map<String, Object> result = new HashMap<String,Object>();
		result = equipmentFactoryService.findOneById(id);
		map.put("result", result);
		map.put("backURL", backURL);
		
		//是否停用
		map.put("out_Service", DictConstant.CHECKITEM_SFMR.getValue());
		//厂商类型
		map.put("factory_Type", DictConstant.FACTORY_TYPE.getValue());
		//厂商性质
		map.put("factory_Xz", DictConstant.FACTORY_XZ.getValue());
				
		return JSP_PR+"/modifyFactory";
	}
	
	
	/**
	 * 修改厂商
	 * 
	 * @date 2017年9月11日 上午10:13:05
	 * @author 陈涛
	 * @param request
	 * @param map
	 * @param bizTBaseFactoryInfoEntity
	 * @return 
	 */
	@RequestMapping("/doModifyFactory")
	@ResponseBody
	public Map<String, Object> doModifyFactory(HttpServletRequest request,ModelMap map,BizTBaseFactoryInfoEntity bizTBaseFactoryInfoEntity){
		Map<String, Object> msg = new HashMap<String,Object>();
		//获取用户信息
		SysUser user = SysUtils.getLoginSysUser(request);
		try{
			equipmentFactoryService.updateEquipmentFactory(bizTBaseFactoryInfoEntity,user);
			msg.put("message", "修改设备厂商成功！");
		}catch(Exception e){
			msg.put("message", "修改设备厂商失败!");
			logger.error("修改设备厂商失败，" + e.getMessage());
		}
		return msg;
	}
	
	/**
	 * 单个或者批量停用设备厂商
	 * 
	 * @date 2017年9月11日 上午10:33:33
	 * @author 陈涛
	 * @param request
	 * @param ids
	 * @return 
	 */
	@RequestMapping("/stopFactory")
	@ResponseBody
	public Map<String, Object> stopFactory(HttpServletRequest request,String ids){
		SysUser user = SysUtils.getLoginSysUser(request);
		String[] idsArray = ids.split(",");
		List<String> idArray = new ArrayList<String>();
		for (int i = 0; i < idsArray.length; i++) {
			idArray.add(idsArray[i]);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			int result = equipmentFactoryService.updateFactoryByIds(idArray,user);
			if (result == idsArray.length) {
				map.put("status", "设备厂商停用成功!");
			}else{
				map.put("status", "设备厂商停用失败!");
				logger.error("设备厂商停用失败！");
			}
		} catch (Exception e) {
			map.put("status", "设备厂商停用失败!");
			logger.error("设备厂商停用失败，" + e.getMessage());
		}
		return map;
	}
}
