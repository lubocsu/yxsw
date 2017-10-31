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
import com.upsoft.yxsw.constant.Constant;
import com.upsoft.yxsw.constant.DictConstant;
import com.upsoft.yxsw.entity.BizTGgWarningManageEntity;
import com.upsoft.yxsw.entity.BizTXjdItem;
import com.upsoft.yxsw.service.BizTGgWarningManageService;
import com.upsoft.yxsw.service.BizTSbBaseinfoService;
import com.upsoft.yxsw.service.BizTSsBaseinfoService;
import com.upsoft.yxsw.service.BizTXjdItemDetailService;
import com.upsoft.yxsw.service.BizTXjdItemService;
import com.upsoft.yxsw.service.EquipmentTypeService;



/**
* Copyright (c) 2017,重庆扬讯软件技术股份有限公司<br>
* All rights reserved.<br>
*
* 文件名称：BizTXjdItemDetailController.java<br>
* 摘要：简要描述本文件的内容<br>
* -------------------------------------------------------<br>
* 当前版本：1.0<br>
* 作者：陈涛<br>
* 完成日期：2017年9月16日<br>
* -------------------------------------------------------<br>
*/
@Controller
@RequestMapping(BizTXjdItemDetailController.FORWARD_PREFIX)
public class BizTXjdItemDetailController  {
	
	protected static final Logger logger = Logger.getLogger(BizTXjdItemDetailController.class);
	protected static final String FORWARD_PREFIX = "/spotRelate";
	private static final String JSP_PR = "/WEB-INF/jsp/spotRelate";
	
	@Autowired
	public BizTXjdItemDetailService bizTXjdItemDetailService;
	@Autowired
	public BizTXjdItemService bizTXjdItemService;
	@Autowired
	public BizTSbBaseinfoService bizTSbBaseinfoService;
	@Autowired
	public EquipmentTypeService equipmentTypeService;
	@Autowired
	public BizTSsBaseinfoService bizTSsBaseinfoService;
	@Autowired
	public BizTGgWarningManageService bizTGgWarningManageService;
	
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
	 * 获取巡检点数
	 * 
	 * @date 2017年9月16日 下午2:44:51
	 * @author 陈涛
	 * @param request
	 * @return 
	 */
	@RequestMapping("/getPointTree")
	@ResponseBody
	public List<Map<String, Object>> getPointTree(HttpServletRequest request) {
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		
		List<Map<String, Object>> result = bizTXjdItemService.getPointTree(loginInfo);
		for (Map<String, Object> map : result) {
			if (map.get("count").toString().equals("0")) {// 通过判断 has_attach来渲染颜色												
				
				map.put("parentId", map.get("parentid"));
				map.remove("parentid");

			} else {
				Map<String, Object> mapColor = new HashMap<String, Object>();
				mapColor.put("color", "green");
				map.put("font", mapColor);
				map.put("parentId", map.get("parentid"));
				map.remove("parentid");

			}
		}
		
		List<Map<String, Object>> orgList = bizTXjdItemService.getOrgList(loginInfo);
		for (Map<String, Object> org : orgList) {
			org.put("flag", "noClick");
			result.add(org);
		}
		return result;
	}
	
	/**
	 * 跳转到详情页面
	 * 
	 * @date 2017年9月17日 下午2:22:59
	 * @author 陈涛
	 * @param spotId
	 * @param map
	 * @return 
	 */
	@RequestMapping("/showDetail")
	public String showDetail(@RequestParam(value="spotId",required=true)String spotId,ModelMap map){
		
		BizTXjdItem BizTXjdItem =bizTXjdItemService.findOneById(spotId);
		map.put("BizTXjdItem", BizTXjdItem);
		
		return JSP_PR+"/detail";
	}
	
	/**
	 * 跳转到挂接设备列表
	 * 
	 * @date 2017年9月17日 下午2:58:15
	 * @author 陈涛
	 * @param spotId
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/toViewEquipment")
	public String toViewEquipment(@RequestParam(value="spotId",required=true)String spotId,HttpServletRequest request,ModelMap map){
		
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		String csType = loginInfo.getCsOrgTypeId();
		
		map.put("csType", csType);
		map.put("spotId", spotId);
		return JSP_PR + "/equipentList";
	}
	
	/**
	 * 跳转到设备新增页面
	 * 
	 * @date 2017年9月18日 下午3:19:05
	 * @author 陈涛
	 * @param spotId
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/toAddEq")
	public String toAddEq(@RequestParam(value="spotId",required=true)String spotId,HttpServletRequest request,ModelMap map){
		
		map.put("spotId", spotId);
		return JSP_PR + "/addEquipentList";
	}
	
	/**
	 * 获取用户所属厂所下未关联的设备
	 * 
	 * @date 2017年9月17日 下午3:37:07
	 * @author 陈涛
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/getNoRelatedData")
	@ResponseBody
	public Map<String, Object> getNoRelatedData(HttpServletRequest request,ModelMap map){
		
		PageBean pageBean = new PageBean(request);
		
		Map<String, Object> params = new HashMap<String, Object>();
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		//获取设备名称
		String eqName  = WebUtils.findParameterValue(request, "eqName");
		//获取安装地址
		String setAddress = WebUtils.findParameterValue(request, "setAddress");
		
		if (StringUtils.isNotBlank(eqName)) {
			params.put("eqName", eqName);
		}
		if (StringUtils.isNotBlank(setAddress)) {
			params.put("setAddress", setAddress);
		}
		Map<String, Object> result = bizTSbBaseinfoService.getNoRelatedData(pageBean, params,loginInfo);
		
		List<Map<String,Object>> rows = (List<Map<String, Object>>) result.get("rows");
		if(rows.size()>0){
			for (Map<String, Object> map2 : rows) {
				if(null != map2.get("sb_type_id")){
					Map<String,Object> bizTSbTypesEntity = equipmentTypeService.findOne(map2.get("sb_type_id").toString());
					map2.put("sb_type_id",bizTSbTypesEntity.get("name"));
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 获取巡检点已关联的设备数据
	 * 
	 * @date 2017年9月17日 下午4:23:14
	 * @author 陈涛
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/getRelatedEqData")
	@ResponseBody
	public Map<String, Object> getRelatedEqData(@RequestParam(value="spotId",required=true)String spotId,HttpServletRequest request,ModelMap map){
		
		PageBean pageBean = new PageBean(request);
		
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		
		Map<String, Object> result = bizTSbBaseinfoService.getRelatedEqData(pageBean, spotId,loginInfo);
		List<Map<String,Object>> rows = (List<Map<String, Object>>) result.get("rows");
		if(rows.size()>0){
			for (Map<String, Object> map2 : rows) {
				if(null != map2.get("sb_type_id")){
					Map<String,Object> bizTSbTypesEntity = equipmentTypeService.findOne(map2.get("sb_type_id").toString());
					map2.put("sb_type_id",bizTSbTypesEntity.get("name"));
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 关联设备
	 * @date 2017年9月17日 下午5:20:57
	 * @author 陈涛
	 * @param spotId
	 * @param request
	 * @param ids
	 * @param names 
	 */
	@RequestMapping("/doRelateEq")
	@ResponseBody
	public Map<String,Object> doRelateEq(@RequestParam(value="spotId",required=true)String spotId,HttpServletRequest request,String ids,String names,ModelMap map){
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
			
			bizTXjdItemDetailService.saveRelated(spotId,idArray,nameArray,Constant.DETAIL_TYPE.EQUIPMENT.getValue());
			msg.put("message", "关联成功");
		} catch (Exception e) {
			msg.put("message", "关联失败");
			logger.error("关联设备失败，" + e.getMessage());
		}
		return msg;
	}
	
	
	/**
	 * 解除关联关系
	 * 
	 * @date 2017年9月18日 下午1:38:45
	 * @author 陈涛
	 * @param spotId
	 * @param request
	 * @param ids
	 * @param names
	 * @param map
	 * @return 
	 */
	@RequestMapping("/delRelate")
	@ResponseBody
	public Map<String,Object> delRelate(String spotId,HttpServletRequest request,String ids,ModelMap map){
		Boolean flag = null ;
		Map<String,Object> msg = new HashMap<String,Object>();
		String[] idsArray = ids.split(",");
		List<String> idArray = new ArrayList<String>();
		for (int i = 0; i < idsArray.length; i++) {
			idArray.add(idsArray[i]);
		}
		try {
			
			bizTXjdItemDetailService.delRelated(idArray);
		} catch (Exception e) {
			logger.error("删除失败，" + e.getMessage());
		}
		
		flag = bizTXjdItemDetailService.getCount(spotId);
		msg.put("message", flag);
		
		return msg;
	}
	
	/**
	 * @date 2017年9月18日 下午3:19:45
	 * @author 陈涛
	 * @param spotId
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/toViewFacility")
	public String toViewFacility(@RequestParam(value="spotId",required=true)String spotId,HttpServletRequest request,ModelMap map){
		
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		String csType = loginInfo.getCsOrgTypeId();
		
		map.put("csType", csType);
		map.put("spotId", spotId);
		
		return JSP_PR + "/facilityList";
	}
	
	/**
	 * 跳转到关联设施列表页面
	 * 
	 * @date 2017年9月18日 下午4:07:42
	 * @author 陈涛
	 * @param spotId
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/toAddFa")
	public String toAddFa(@RequestParam(value="spotId",required=true)String spotId,HttpServletRequest request,ModelMap map){
		
		map.put("spotId", spotId);
		
		return JSP_PR + "/addFacilityList";
	}
	
	/**
	 * 获取未关联的设施列表
	 * 
	 * @date 2017年9月18日 下午4:12:05
	 * @author 陈涛
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/getNoRelatedFaData")
	@ResponseBody
	public Map<String, Object> getNoRelatedFaData(HttpServletRequest request,ModelMap map){
		
		PageBean pageBean = new PageBean(request);
		
		Map<String, Object> params = new HashMap<String, Object>();
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		//获取设备名称
		String eqName  = WebUtils.findParameterValue(request, "eqName");
		//获取安装地址
		String setAddress = WebUtils.findParameterValue(request, "setAddress");
		
		if (StringUtils.isNotBlank(eqName)) {
			params.put("eqName", eqName);
		}
		if (StringUtils.isNotBlank(setAddress)) {
			params.put("setAddress", setAddress);
		}
		Map<String, Object> result = bizTSsBaseinfoService.getNoRelatedData(pageBean, params,loginInfo);
		
		List<Map<String,Object>> rows = (List<Map<String, Object>>) result.get("rows");
		if(rows.size() > 0){
			for (Map<String, Object> map2 : rows) {
				if(null != map2.get("layer")){
					Map<String,Object> bizTSsEntity = bizTSsBaseinfoService.getSSLayer(map2.get("layer").toString());;
					map2.put("layer",bizTSsEntity.get("layername"));
				}
			}
		}
		
		return result;
	}
	/**
	 * 关联设施
	 * 
	 * @date 2017年9月18日 下午4:39:40
	 * @author 陈涛
	 * @param spotId
	 * @param request
	 * @param ids
	 * @param names
	 * @param map
	 * @return 
	 */
	@RequestMapping("/doRelateFa")
	@ResponseBody
	public Map<String,Object> doRelateFa(@RequestParam(value="spotId",required=true)String spotId,HttpServletRequest request,String ids,String names,ModelMap map){
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
			
			bizTXjdItemDetailService.saveRelated(spotId,idArray,nameArray,Constant.DETAIL_TYPE.FACILITY.getValue());
			msg.put("message", "关联成功");
		} catch (Exception e) {
			msg.put("message", "关联失败");
			logger.error("关联设备失败，" + e.getMessage());
		}
		return msg;
	}
	
	
	/**
	 * 获取已挂接的设施
	 * 
	 * @date 2017年9月18日 下午4:44:47
	 * @author 陈涛
	 * @param spotId
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/getRelatedFaData")
	@ResponseBody
	public Map<String, Object> getRelatedFaData(@RequestParam(value="spotId",required=true)String spotId,HttpServletRequest request,ModelMap map){
		
		PageBean pageBean = new PageBean(request);
		
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		
		Map<String, Object> result = bizTSsBaseinfoService.getRelatedFaData(pageBean, spotId,loginInfo);
		List<Map<String,Object>> rows = (List<Map<String, Object>>) result.get("rows");
		if(rows.size() > 0){
			for (Map<String, Object> map2 : rows) {
				if(null != map2.get("layer")){
					Map<String,Object> bizTSsEntity = bizTSsBaseinfoService.getSSLayer(map2.get("layer").toString());;
					map2.put("layer",bizTSsEntity.get("layername"));
				}
			}
		}
		return result;
	}
	
	/**
	 * 跳转到安全定义列表
	 * 
	 * @date 2017年9月18日 下午5:34:28
	 * @author 陈涛
	 * @param spotId
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/toViewWarning")
	public String toViewWarning(@RequestParam(value="spotId",required=true)String spotId,HttpServletRequest request,ModelMap map){
		
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		String csType = loginInfo.getCsOrgTypeId();
		
		map.put("csType", csType);
		map.put("spotId", spotId);
		
		return JSP_PR + "/warningList";
	}
	
	/**
	 * 获取已关联的安全提醒定义
	 * 
	 * @date 2017年9月18日 下午5:35:28
	 * @author 陈涛
	 * @param spotId
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/getRelatedWData")
	@ResponseBody
	public Map<String, Object> getRelatedWData(@RequestParam(value="spotId",required=true)String spotId,HttpServletRequest request,ModelMap map){
		
		PageBean pageBean = new PageBean(request);
		
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		
		Map<String, Object> result = bizTGgWarningManageService.getRelatedData(pageBean, spotId,loginInfo);
		List<Map<String,Object>> rows = (List<Map<String, Object>>) result.get("rows");
		if(rows.size() > 0){
			for (Map<String, Object> map2 : rows) {
				if(null != map2.get("is_important")){
					List<Map<String, Object>> yesOrNo = ServiceReceiver.getDictSelect(DictConstant.CHECKITEM_SFMR.getValue());
					for (Map<String, Object> map3 : yesOrNo) {
						if((map2.get("is_important").toString()).equals(map3.get("key").toString())){
							map2.put("is_important", map3.get("value"));
							break;
						}else{
							continue;
						}
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * 跳转到关联安全提醒定义列表
	 * 
	 * @date 2017年9月18日 下午5:54:45
	 * @author 陈涛
	 * @param spotId
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/toAddWar")
	public String toAddWar(@RequestParam(value="spotId",required=true)String spotId,HttpServletRequest request,ModelMap map){
		
		map.put("spotId", spotId);
		
		return JSP_PR + "/addWaringList";
	}
	
	/**
	 * 获取本场所下该巡检点未关联的安全定义
	 * 
	 * @date 2017年9月18日 下午7:32:31
	 * @author 陈涛
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/getNoRelatedWData")
	@ResponseBody
	public Map<String, Object> getNoRelatedWData(String spotId ,HttpServletRequest request,ModelMap map){
		
		PageBean pageBean = new PageBean(request);
		
		Map<String, Object> params = new HashMap<String, Object>();
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		//获取设备名称
		String eqName  = WebUtils.findParameterValue(request, "eqName");
		//获取安装地址
		String setAddress = WebUtils.findParameterValue(request, "setAddress");
		
		if (StringUtils.isNotBlank(eqName)) {
			params.put("eqName", eqName);
		}
		if (StringUtils.isNotBlank(setAddress)) {
			params.put("setAddress", setAddress);
		}
		Map<String, Object> result = bizTGgWarningManageService.getNoRelatedData(spotId,pageBean, params,loginInfo);
		List<Map<String,Object>> rows = (List<Map<String, Object>>) result.get("rows");
		if(rows.size() > 0){
			for (Map<String, Object> map2 : rows) {
				if(null != map2.get("is_important")){
					List<Map<String, Object>> yesOrNo = ServiceReceiver.getDictSelect(DictConstant.CHECKITEM_SFMR.getValue());
					for (Map<String, Object> map3 : yesOrNo) {
						if((map2.get("is_important").toString()).equals(map3.get("key").toString())){
							map2.put("is_important", map3.get("value"));
							break;
						}else{
							continue;
						}
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * 关联安全定义
	 * 
	 * @date 2017年9月18日 下午7:32:00
	 * @author 陈涛
	 * @param spotId
	 * @param request
	 * @param ids
	 * @param map
	 * @return 
	 */
	@RequestMapping("/doRelateWar")
	@ResponseBody
	public Map<String,Object> doRelateWar(@RequestParam(value="spotId",required=true)String spotId,HttpServletRequest request,String ids,ModelMap map){
		Map<String,Object> msg = new HashMap<String,Object>();
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		String[] idsArray = ids.split(",");
		List<String> idArray = new ArrayList<String>();
		List<String> nameArray = new ArrayList<String>();
		for (int i = 0; i < idsArray.length; i++) {
			idArray.add(idsArray[i]);
			BizTGgWarningManageEntity bizTGgWarningManageEntity = bizTGgWarningManageService.findOne(BizTGgWarningManageEntity.class, idsArray[i]);
			nameArray.add(bizTGgWarningManageEntity.getTitle());
		}
		try {
			
			bizTXjdItemDetailService.saveRelatedWar(spotId,idArray,nameArray,loginInfo);
			msg.put("message", "关联成功");
		} catch (Exception e) {
			msg.put("message", "关联失败");
			logger.error("关联失败，" + e.getMessage());
		}
		return msg;
	}
	
	@RequestMapping("/delRelateWar")
	@ResponseBody
	public Map<String,Object> delRelateWar(String spotId,HttpServletRequest request,String ids,ModelMap map){
		Boolean flag = null ;
		Map<String,Object> msg = new HashMap<String,Object>();
		String[] idsArray = ids.split(",");
		List<String> idArray = new ArrayList<String>();
		for (int i = 0; i < idsArray.length; i++) {
			idArray.add(idsArray[i]);
		}
		try {
			
			bizTXjdItemDetailService.delRelateWar(idArray);
		} catch (Exception e) {
			logger.error("删除失败，" + e.getMessage());
		}
		
		flag = bizTXjdItemDetailService.getCount(spotId);
		msg.put("message", flag);
		
		return msg;
	}
}
