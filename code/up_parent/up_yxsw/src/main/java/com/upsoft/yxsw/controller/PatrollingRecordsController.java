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
import com.upsoft.system.entity.ComTAttachment;
import com.upsoft.system.util.CommonUtils;
import com.upsoft.yxsw.constant.DictConstant;
import com.upsoft.yxsw.service.BizTXjCxTaskService;
import com.upsoft.yxsw.service.PatrollingRecordsService;

/**
* Copyright (c) 2017,重庆扬讯软件技术股份有限公司<br>
* All rights reserved.<br>
*
* 文件名称：InspectionRecordsController.java<br>
* 摘要：巡检任务记录<br>
* -------------------------------------------------------<br>
* 当前版本：1.0<br>
* 作者：陈涛<br>
* 完成日期：2017年9月23日<br>
* -------------------------------------------------------<br>
*/
@Controller
@RequestMapping(PatrollingRecordsController.FORWARD_PREFIX)
public class PatrollingRecordsController {

	protected static final Logger logger = Logger.getLogger(PatrollingRecordsController.class);
	protected static final String FORWARD_PREFIX = "/patrollingRecords";
	protected static final String JSP_PR = "/WEB-INF/jsp/patrollingRecords";
	
	@Autowired
	private BizTXjCxTaskService bizTXjCxTaskService;
	@Autowired
	private PatrollingRecordsService patrollingRecordsService;
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
		
		//是否超时
		map.put("overTime", DictConstant.CHECKITEM_SFMR.getValue());
		
		//获取登录用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		map.put("csOrgType", loginInfo.getCsOrgTypeId());
		
		return JSP_PR + "/main";
	}
	
	/**
	 * 获取厂巡任务记录
	 * 
	 * @date 2017年9月23日 下午2:45:26
	 * @author 陈涛
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/getRecordsData")
	@ResponseBody
	public Map<String, Object> getRecordsData(HttpServletRequest request,ModelMap map){
		
		PageBean pageBean = new PageBean(request);
		
		Map<String, Object> params = new HashMap<String, Object>();
		//获取登录用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		//获取厂商名称
		String csOsrg  = WebUtils.findParameterValue(request, "csOsrg");
		//获取是否停用
		String isOverTime = WebUtils.findParameterValue(request, "isOverTime");
		//获取厂商性质
		String startTime = WebUtils.findParameterValue(request, "startTime");
		//获取厂商类型
		String endTime = WebUtils.findParameterValue(request, "endTime");
		
		if (StringUtils.isNotBlank(csOsrg)) {
			params.put("csOsrg", csOsrg);
		}
		if (StringUtils.isNotBlank(isOverTime)) {
			params.put("isOverTime", isOverTime);
		}
		if (StringUtils.isNotBlank(startTime)) {
			params.put("startTime", startTime);
		}
		if (StringUtils.isNotBlank(endTime)) {
			params.put("endTime", endTime);
		}
		//任务状态map
		List<Map<String,Object>> taskStatus = ServiceReceiver.getDictSelect(DictConstant.TASK_STATUS.getValue());
		Map<String,Object> taskMap = new HashMap<String,Object>();
		for (Map<String, Object> status : taskStatus) {
			taskMap.put(status.get("key").toString(), status.get("value"));
		}
		//公用是否map
		List<Map<String,Object>> yOrN = ServiceReceiver.getDictSelect(DictConstant.CHECKITEM_SFMR.getValue());
		Map<String,Object> ynMap = new HashMap<String,Object>();
		for (Map<String, Object> maps : yOrN) {
			ynMap.put(maps.get("key").toString(), maps.get("value"));
		}
		Map<String, Object> result =bizTXjCxTaskService.getRecordsData(pageBean, params,loginInfo);
		List<Map<String,Object>> rows = (List<Map<String, Object>>) result.get("rows");
		for (Map<String, Object> map2 : rows) {
			map2.put("cx_task_status", taskMap.get(map2.get("cx_task_status")));
			if(null != map2.get("is_over_time")){
				
				map2.put("is_over_time", ynMap.get(map2.get("is_over_time").toString()).toString());
			}
			if(null != map2.get("sf_zc_over_time")){
				
				map2.put("sf_zc_over_time", ynMap.get(map2.get("sf_zc_over_time").toString()).toString());
			}
		}
		
		return  result;
	}
	
	/**
	 * 跳转到是否正常超期
	 * 
	 * @date 2017年9月25日 上午10:13:59
	 * @author 陈涛
	 * @param cxTaskId
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/toIsZcOverTime")
	public String toIsZcOverTime(@RequestParam(value="cxTaskId",required=true)String cxTaskId,HttpServletRequest request,ModelMap map){
		
		//是否正常超期
		map.put("overTime", DictConstant.CHECKITEM_SFMR.getValue());
		map.put("cxTaskId", cxTaskId);
		//获取登录用户信息
		
		return JSP_PR + "/isZcOverTime";
	}
	
	/**
	 * 保存超期说明
	 * 
	 * @date 2017年9月25日 上午10:14:36
	 * @author 陈涛
	 * @param cxTaskId
	 * @param isOverTime
	 * @param overtime_content
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/isZcOverTime")
	@ResponseBody
	public Map<String, Object> isZcOverTime(@RequestParam(value="cxTaskId",required=true)String cxTaskId,String isOverTime,String overtime_content,HttpServletRequest request,ModelMap map){
		Map<String, Object> msg = new HashMap<String,Object>();
		//获取用户信息
		WSLoginInfoBean user = SysUtils.getLoginInfo(request);
		try{
			bizTXjCxTaskService.updateCxTask(cxTaskId,isOverTime,overtime_content,user);
			msg.put("message", "保存成功！");
		}catch(Exception e){
			msg.put("message", "保存失败!");
			logger.error("保存失败，" + e.getMessage());
		}
		return msg;
	}
	
	/**
	 * 查看巡检任务详情
	 * 
	 * @date 2017年9月25日 上午10:30:36
	 * @author 陈涛
	 * @param cxTaskId
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/toViewCxTask")
	public String toViewCxTask(@RequestParam(value="cxTaskId",required=true)String cxTaskId,HttpServletRequest request,ModelMap map){
		
		//任务状态map
		List<Map<String,Object>> taskStatus = ServiceReceiver.getDictSelect(DictConstant.TASK_STATUS.getValue());
		Map<String,Object> taskMap = new HashMap<String,Object>();
		for (Map<String, Object> status : taskStatus) {
				taskMap.put(status.get("key").toString(), status.get("value"));
		}
		//公用是否map
		List<Map<String,Object>> yOrN = ServiceReceiver.getDictSelect(DictConstant.CHECKITEM_SFMR.getValue());
		Map<String,Object> ynMap = new HashMap<String,Object>();
		for (Map<String, Object> maps : yOrN) {
			ynMap.put(maps.get("key").toString(), maps.get("value"));
		}
		Map<String, Object> result =bizTXjCxTaskService.findOneCxTask(cxTaskId);
		result.put("cx_task_status", taskMap.get(result.get("cx_task_status")));
		if(null != result.get("is_over_time")){
			
			result.put("is_over_time", ynMap.get(result.get("is_over_time").toString()).toString());
		}
		if(null != result.get("sf_zc_over_time")){
						
			result.put("sf_zc_over_time", ynMap.get(result.get("sf_zc_over_time").toString()).toString());
		}
		map.put("result", result);
		
		return JSP_PR + "/cxTaskDeatil";
	}
	
	/**
	 * 获取巡检任务相关的巡检点
	 * 
	 * @date 2017年9月25日 上午11:11:54
	 * @author 陈涛
	 * @param cxTaskId
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/toPointList")
	public String toPointList(@RequestParam(value="cxTaskId",required=true)String cxTaskId,HttpServletRequest request,ModelMap map){
		
		map.put("cxTaskId", cxTaskId);
		
		return JSP_PR + "/pointList";
	}
	
	/**
	 * 获取巡检任务的巡检点
	 * 
	 * @date 2017年9月25日 下午1:37:44
	 * @author 陈涛
	 * @param cxTaskId
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/getPointData")
	@ResponseBody
	public Map<String, Object> getPointData(@RequestParam(value="cxTaskId",required=true)String cxTaskId,HttpServletRequest request,ModelMap map){
		
		PageBean pageBean = new PageBean(request);
		
		//公用是否map
		List<Map<String,Object>> yOrN = ServiceReceiver.getDictSelect(DictConstant.CHECKITEM_SFMR.getValue());
		Map<String,Object> ynMap = new HashMap<String,Object>();
		for (Map<String, Object> maps : yOrN) {
			ynMap.put(maps.get("key").toString(), maps.get("value"));
		}
		
		//厂巡任务巡检点RFID扫描状态
		List<Map<String,Object>> rfidStatus = ServiceReceiver.getDictSelect(DictConstant.RFID_STATUS.getValue());
		Map<String,Object> rfidMap = new HashMap<String,Object>();
		for (Map<String, Object> status : rfidStatus) {
			rfidMap.put(status.get("key").toString(), status.get("value"));
		}
				
		Map<String, Object> result =patrollingRecordsService.getpointList(cxTaskId,pageBean);
		List<Map<String,Object>> rows = (List<Map<String, Object>>) result.get("rows");
		for (Map<String, Object> map2 : rows) {
			if(null != map2.get("rfid_confirmed_type")){
				
				map2.put("rfid_confirmed_type", rfidMap.get(map2.get("rfid_confirmed_type").toString()).toString());
			}
			if(null != map2.get("have_complete")){
				
				map2.put("have_complete", ynMap.get(map2.get("have_complete").toString()).toString());
			}
		}
		return  result;
	}
	
	/**
	 * 跳转到巡检点详情
	 * 
	 * @date 2017年9月25日 下午2:35:23
	 * @author 陈涛
	 * @param taskItemId
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/toViewXjdItem")
	public String toViewXjdItem(@RequestParam(value="taskItemId",required=true)String taskItemId,HttpServletRequest request,ModelMap map){
		
		//公用是否map
		List<Map<String,Object>> yOrN = ServiceReceiver.getDictSelect(DictConstant.CHECKITEM_SFMR.getValue());
		Map<String,Object> ynMap = new HashMap<String,Object>();
		for (Map<String, Object> maps : yOrN) {
			ynMap.put(maps.get("key").toString(), maps.get("value"));
		}
				
		//厂巡任务巡检点RFID扫描状态
		List<Map<String,Object>> rfidStatus = ServiceReceiver.getDictSelect(DictConstant.RFID_STATUS.getValue());
		Map<String,Object> rfidMap = new HashMap<String,Object>();
		for (Map<String, Object> status : rfidStatus) {
			rfidMap.put(status.get("key").toString(), status.get("value"));
		}
				
		Map<String, Object> result =patrollingRecordsService.findPoint(taskItemId);
		if(null != result.get("rfid_confirmed_type")){
				
			result.put("rfid_confirmed_type", rfidMap.get(result.get("rfid_confirmed_type").toString()).toString());
		}
		if(null != result.get("have_complete")){
				
			result.put("have_complete", ynMap.get(result.get("have_complete").toString()).toString());
		}
		
		map.put("result", result);
		
		return JSP_PR + "/taskPointDeatil";
	}
	
	/**
	 * 跳转到设施设备列表
	 * 
	 * @date 2017年9月25日 下午2:39:23
	 * @author 陈涛
	 * @param xjdItemId
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/toSbssList")
	public String toSbssList(@RequestParam(value="taskItemId",required=true)String taskItemId,HttpServletRequest request,ModelMap map){
		
		map.put("taskItemId", taskItemId);
		
		return JSP_PR + "/sbssList";
	}
	
	/**
	 * 获取设施设备数据
	 * 
	 * @date 2017年9月25日 下午2:45:08
	 * @author 陈涛
	 * @param cxTaskId
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/getSbssData")
	@ResponseBody
	public Map<String, Object> getSbssData(@RequestParam(value="taskItemId",required=true)String taskItemId,HttpServletRequest request,ModelMap map){
		
		PageBean pageBean = new PageBean(request);
		
		//公用是否map
		List<Map<String,Object>> yOrN = ServiceReceiver.getDictSelect(DictConstant.CHECKITEM_SFMR.getValue());
		Map<String,Object> ynMap = new HashMap<String,Object>();
		for (Map<String, Object> maps : yOrN) {
			ynMap.put(maps.get("key").toString(), maps.get("value"));
		}
		
		//厂巡任务设备或设施
		List<Map<String,Object>> sbOrSs = ServiceReceiver.getDictSelect(DictConstant.SB_OR_SS.getValue());
		Map<String,Object> sbOrSsMap = new HashMap<String,Object>();
		for (Map<String, Object> status : sbOrSs) {
			sbOrSsMap.put(status.get("key").toString(), status.get("value"));
		}
		//厂巡任务设备设施
		List<Map<String,Object>> sbssStatus = ServiceReceiver.getDictSelect(DictConstant.SBSS_STATUS.getValue());
		Map<String,Object> sbssSMap = new HashMap<String,Object>();
		for (Map<String, Object> sbss : sbssStatus) {
			sbssSMap.put(sbss.get("key").toString(), sbss.get("value"));
		}
				
		Map<String, Object> result =patrollingRecordsService.getSbssList(taskItemId,pageBean);
		List<Map<String,Object>> rows = (List<Map<String, Object>>) result.get("rows");
		for (Map<String, Object> map2 : rows) {
			if(null != map2.get("detail_type")){
				
				map2.put("detail_type", sbOrSsMap.get(map2.get("detail_type").toString()).toString());
			}
			if(null != map2.get("must_scan")){
				
				map2.put("must_scan", ynMap.get(map2.get("must_scan").toString()).toString());
			}
			if(null != map2.get("have_complete")){
				
				map2.put("have_complete", ynMap.get(map2.get("have_complete").toString()).toString());
			}
			if(null != map2.get("ewm_confirmed_type")){
				
				map2.put("ewm_confirmed_type", sbssSMap.get(map2.get("ewm_confirmed_type").toString()).toString());
			}
		}
		return  result;
	}
	
	
	/**
	 * 获取设备设施巡检信息
	 * 
	 * @date 2017年9月25日 下午5:05:57
	 * @author 陈涛
	 * @param toViewCheckInfo
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/toViewCheckInfo")
	public String toViewCheckInfo(@RequestParam(value="ttaskItemSbssId",required=true)String ttaskItemSbssId,HttpServletRequest request,ModelMap map){
		//公用是否map
		List<Map<String,Object>> yOrN = ServiceReceiver.getDictSelect(DictConstant.CHECKITEM_SFMR.getValue());
		Map<String,Object> ynMap = new HashMap<String,Object>();
		for (Map<String, Object> maps : yOrN) {
			ynMap.put(maps.get("key").toString(), maps.get("value"));
		}
				
		//厂巡任务设备或设施
		List<Map<String,Object>> sbOrSs = ServiceReceiver.getDictSelect(DictConstant.SB_OR_SS.getValue());
		Map<String,Object> sbOrSsMap = new HashMap<String,Object>();
		for (Map<String, Object> status : sbOrSs) {
			sbOrSsMap.put(status.get("key").toString(), status.get("value"));
		}
		//厂巡任务设备设施
		List<Map<String,Object>> sbssStatus = ServiceReceiver.getDictSelect(DictConstant.SBSS_STATUS.getValue());
		Map<String,Object> sbssSMap = new HashMap<String,Object>();
		for (Map<String, Object> sbss : sbssStatus) {
			sbssSMap.put(sbss.get("key").toString(), sbss.get("value"));
		}
		//获取单个设施设备检查详情
		Map<String,Object> sbssInfo = patrollingRecordsService.getSbssCheckDetail(ttaskItemSbssId);
		if(null != sbssInfo.get("detail_type")){
			
			sbssInfo.put("detail_type", sbOrSsMap.get(sbssInfo.get("detail_type").toString()).toString());
		}
		if(null != sbssInfo.get("must_scan")){
			
			sbssInfo.put("must_scan", ynMap.get(sbssInfo.get("must_scan").toString()).toString());
		}
		if(null != sbssInfo.get("have_complete")){
			
			sbssInfo.put("have_complete", ynMap.get(sbssInfo.get("have_complete").toString()).toString());
		}
		if(null != sbssInfo.get("ewm_confirmed_type")){
			
			sbssInfo.put("ewm_confirmed_type", sbssSMap.get(sbssInfo.get("ewm_confirmed_type").toString()).toString());
		}
		
		
		//获取设施设备检查项详情
		List<Map<String,Object>> list = patrollingRecordsService.getSbssInfo(ttaskItemSbssId);
		//获取设施设备处理流程
		List<Map<String,Object>> dealFlow = patrollingRecordsService.getDealFlow(ttaskItemSbssId);
		//获取附件
		List<ComTAttachment> attachments = patrollingRecordsService.getAttachmentList(ttaskItemSbssId);
		List<ComTAttachment> imgs = new ArrayList<ComTAttachment>();
		List<ComTAttachment> videos = new ArrayList<ComTAttachment>();
		for (ComTAttachment comTAttachment : attachments) {
			String type = comTAttachment.getAttachmentSuffix();
			if(type.equals("png") || type.equals("jpg")){
				imgs.add(comTAttachment);
			}else{
				videos.add(comTAttachment);
			}
		}
		
		map.put("sbssInfo", sbssInfo);
		map.put("imgs", imgs);
		map.put("videos", videos);
		map.put("ServerURL",CommonUtils.getWebappsURLPath(request));
		map.put("dealFlow", dealFlow);
		map.put("list", list);
		
		return JSP_PR + "/sbssCheckInfo";
	}
	
	/**
	 * 查看图片
	 * 
	 * @date 2017年9月26日 上午10:18:26
	 * @author 陈涛
	 * @param processId
	 * @param request
	 * @param attachmentType
	 * @param mod
	 * @return 
	 */
	@RequestMapping("/viewPic")
	public String viewPic(@RequestParam(value="ttaskItemSbssId",required=true)String ttaskItemSbssId,HttpServletRequest request ,ModelMap mod){
		List<ComTAttachment> imgs = new ArrayList<ComTAttachment>();
		List<ComTAttachment> attachments = patrollingRecordsService.getAttachmentList(ttaskItemSbssId);
		
		for (ComTAttachment comTAttachment : attachments) {
			String type = comTAttachment.getAttachmentSuffix();
			if(type.equals("png") || type.equals("jpg")){
				imgs.add(comTAttachment);
			}
		}
		mod.addAttribute("imgs", imgs);
		mod.addAttribute("ImageServerURL", CommonUtils.getWebappsURLPath(request));
		
		return JSP_PR+"/album";
	}
}
