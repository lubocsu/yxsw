package com.upsoft.yxsw.controller;

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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.upsoft.login.controller.BaseController;
import com.upsoft.login.support.webservice.ServiceReceiver;
import com.upsoft.login.support.webservice.SysUtils;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.bean.ResultBean;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.constant.CommonConstant;
import com.upsoft.yxsw.constant.DictConstant;
import com.upsoft.yxsw.entity.BizTSbssRelation;
import com.upsoft.yxsw.entity.BizTSsBaseinfo;
import com.upsoft.yxsw.service.BizTSbssRelationService;
import com.upsoft.yxsw.service.BizTSsBaseinfoService;

/**
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：BizTSbssRelationController.java<br>
* 摘要：设施绑定设备管理<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：胡毅<br>
* 完成日期：2017年9月16日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：胡毅<br>
* 完成日期：2017年9月16日<br>
 */
@Controller
@RequestMapping("/ssattachsb")
public class BizTSbssRelationController  extends BaseController {
	private static final String JSP_PREFIX = "/WEB-INF/jsp/ssattachsb";
	private Logger logger = Logger.getLogger(BizTSbssRelationController.class);
	
	@Autowired
	public BizTSbssRelationService bizTSbssRelationService;
	@Autowired
	public BizTSsBaseinfoService bizTSsBaseinfoService;
	
	/**
	 *  初始化页面
	 * @param map
	 * @return
	 */
	@RequestMapping("/init")
	public String init(ModelMap map){
		return JSP_PREFIX + "/main";
	}
	
	@RequestMapping("/showDetail")
	public String showDetail(@RequestParam(required = true)String ssId,String parentName ,ModelMap map,HttpServletRequest request){
		super.findMenuResourcePermission(request, map);
		BizTSsBaseinfo entity = bizTSsBaseinfoService.findOne(BizTSsBaseinfo.class,ssId);
		String layer = entity.getLayer();
		//查询层级关系
		Map<String,Object> layerNames = bizTSsBaseinfoService.getSSLayer(layer);
		map.put("parentName", parentName);
		map.put("layerName",layerNames.get("layername"));
		map.put("ssItem", entity);
		map.put("byzdName", ServiceReceiver.getDictValue1(DictConstant.CHECKITEM_SFMR.getValue(), entity.getByzd()).get(entity.getByzd()));
		String wscOrgType = SysUtils.getLoginInfo(request).getCsOrgTypeId();
		if(Integer.valueOf(wscOrgType) < Integer.valueOf(CommonConstant.orgType.FACTORY.getKey()) ){
			map.addAttribute("onlyQuery", true);
		}else{
			map.addAttribute("onlyQuery", false);
		}
		return JSP_PREFIX+"/detail";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/getSBSSRelationList")
	@ResponseBody
	public Map<String,Object> getSBSSRelationList (HttpServletRequest request,@RequestParam(required = true)String ssId){
		PageBean pageBean  = new PageBean(request);
		Map<String,Object> data = bizTSbssRelationService.getSBSSRelationList(ssId,pageBean);
		List<Map<String,Object>> gcjcDictList = ServiceReceiver.getDictSelect(DictConstant.GC_JK.getValue());
		Map<String,Object> gcjkMap = new HashMap<String,Object>();
		for (Map<String, Object> map : gcjcDictList) {
			gcjkMap.put(map.get("key").toString(),map.get("value"));
		}
		List<Map<String,Object>> row = (List<Map<String, Object>>) data.get("rows");
		for (Map<String, Object> map : row) {
			if(gcjkMap.containsKey(map.get("gcjk"))){
				map.put("gcjkname", gcjkMap.get(map.get("gcjk")));
			}
		}
		return data;
	}
	
	/**
	 * 获取设备列表
	 * @date 2017年10月20日 下午1:50:37
	 * @author 胡毅
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/toEqInfoList")
	public String toEqInfoList(ModelMap map){
		map.put("EQ_TYPE", new Gson().toJson(ServiceReceiver.getDictKeyValueMap(DictConstant.EQ_TYPE.getValue())));
		map.put("ZY_STATUS", new Gson().toJson(ServiceReceiver.getDictKeyValueMap(DictConstant.ZY_STATUS.getValue())));
		return JSP_PREFIX + "/eq_list";
	}
	
	/**
	 *  获取设备列表
	 * @date 2017年10月20日 下午1:51:12
	 * @author 胡毅
	 * @param request
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getEqInfoGridList")
	public Map<String,Object> getEqList(HttpServletRequest request, ModelMap map){
		
		WSLoginInfoBean userLoginInfo = SysUtils.getLoginInfo(request);
		PageBean bean = new PageBean(request);
		Map<String, Object> params = new HashMap<String, Object>();
		//设备名称
		params.put("sbName", WebUtils.findParameterValue(request, "sbName"));
		//设备类别
		params.put("sbSort", WebUtils.findParameterValue(request, "sbSort"));
		//设备类型ID
		params.put("sbTypeId", WebUtils.findParameterValue(request, "sbTypeId"));
		//在用状态
		params.put("zyStatus", WebUtils.findParameterValue(request, "zyStatus"));
		if(StringUtils.equals(userLoginInfo.getCsOrgTypeId(), CommonConstant.orgType.FACTORY.getKey())){
			params.put("orgCode", userLoginInfo.getCsOrgId());
		}
		return bizTSbssRelationService.getEqList(bean, params);
	}
	
	@RequestMapping("/doAdd")
	@ResponseBody
	public ResultBean doAdd(@RequestParam(required =true)String relations){
		ResultBean result = new ResultBean();
		List<BizTSbssRelation> relationsList = new Gson().fromJson(relations, new TypeToken<List<BizTSbssRelation>>() {}.getType());
		try {
			bizTSbssRelationService.saveAll(relationsList);
			result.setMessage("设施关联设备成功");
		} catch (Exception e) {
			result.setFlag(false);
			result.setMessage("保存设备设施关联关系时出错："+e.getMessage());
			logger.error("保存设备设施关联关系时出错："+e.getMessage());
		}
		return result;
	}

	@RequestMapping("/doDel")
	@ResponseBody
	public ResultBean doDel(@RequestParam(required =true)String ids){
		ResultBean result = new ResultBean();
		try {
			bizTSbssRelationService.deleteAll(ids);
			result.setMessage("删除成功");
		} catch (Exception e) {
			result.setMessage("删除失败");
			result.setFlag(false);
			logger.error("移除设备设施关联关系异常："+e.getMessage());
		}
		return result;
	}
}
