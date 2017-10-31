package com.upsoft.yxsw.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;
import org.w3c.dom.ls.LSException;

import com.upsoft.login.controller.BaseController;
import com.upsoft.login.support.webservice.SysUtils;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.util.DateUtil;
import com.upsoft.yxsw.constant.DictConstant;
import com.upsoft.yxsw.entity.BizTXjCxTaskItemFaultlc;
import com.upsoft.yxsw.entity.BizTXjCxTaskItemSbss;
import com.upsoft.yxsw.service.BizTXjCxTaskItemFaultlcService;
import com.upsoft.yxsw.service.BizTXjCxTaskItemSbssService;
import com.upsoft.yxsw.service.BizTXjCxTaskService;
/**
 * 
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：SbSsXjController.java<br>
* 摘要：设备设施巡检记录<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：杨磊<br>
* 完成日期：2017年9月23日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：杨磊<br>
* 完成日期：2017年9月23日<br>
 */

@Controller
@RequestMapping(SbSsXjController.FORWARD_PREFIX)
public class SbSsXjController extends BaseController {
    
	protected static final String  FORWARD_PREFIX="/sbssxj";
	protected static final String  JSP_PR="/WEB-INF/jsp/sbssxj";
	protected Logger logger=Logger.getLogger(SbSsXjController.class);
	@Autowired
	private BizTXjCxTaskService bizXjCxTxTaskService ;//巡检任务
	@Autowired
    private BizTXjCxTaskItemSbssService bizTXjCxTaskItemSbssService;
	@Autowired
	private BizTXjCxTaskItemFaultlcService BizTXjCxTaskItemFaultlcService;
	@RequestMapping("/init")
	 public String init(HttpServletRequest request,ModelMap map){
		WSLoginInfoBean user = SysUtils.getLoginInfo(request);
		String type = user.getCsOrgTypeId();
	//	SysUser sysUser = SysUtils.getLoginSysUser(request);
		//String userName = sysUser.getUserName();
	//	String nowId = sysUser.getLoginId();
	//	String id = sysUser.getUserId();
	//	String id = user.getCsOrgId();
		SysUser loginSysUser = SysUtils.getLoginSysUser(request);
		String id = loginSysUser.getUserId();
	
		
		
		map.put("type", type);
		map.put("id",  id);
		map.put("CHECKITEM_TYPE", DictConstant.SB_OR_SS.getValue());
		 return JSP_PR+"/main"; 
	 }
	
	/**
	 * 获取任务的设备设施情况
	 * @date 2017年9月26日 下午5:08:01
	 * @author 杨磊
	 * @param request
	 * @param map
	 * @return
	 */
	 @SuppressWarnings("unchecked")
	 @RequestMapping("/getSbSsXj")
	 @ResponseBody
	public Map<String,Object>  getSbSsXj(HttpServletRequest request,ModelMap map){
		
		PageBean bean = new PageBean(request);
		Map<String, Object> par = new HashMap<String, Object>();
		// 获取页面查询参数
		String startDate = WebUtils.findParameterValue(request, "startDate");
	    String	endDate = WebUtils.findParameterValue(request, "endDate");
	    String	xjd_name = WebUtils.findParameterValue(request, "xjd_name");
	    String	sborss_name = WebUtils.findParameterValue(request, "sborss_name");
	    String	task_code = WebUtils.findParameterValue(request, "task_code");
	    String	task_type = WebUtils.findParameterValue(request, "task_type");
	    String	org = WebUtils.findParameterValue(request, "org");
	    String startTime = DateUtil.stringToString(startDate, DateUtil.DATE_FORMAT_WITHOUT_TIME, DateUtil.DATE_FORMAT_yyyyMMdd);
	    String endTime = DateUtil.stringToString(endDate, DateUtil.DATE_FORMAT_WITHOUT_TIME, DateUtil.DATE_FORMAT_yyyyMMdd);
		WSLoginInfoBean user = SysUtils.getLoginInfo(request);
		String csOrgId = user.getCsOrgId();
	
	    par.put("csOrgId", csOrgId);
	    par.put("startTime", startTime);
	    par.put("endTime", endTime);
	    par.put("xjd_name", xjd_name);
	    par.put("sborss_name", sborss_name);
	    par.put("task_code",task_code);
	    par.put("task_type",task_type );
	    par.put("org", org);
        Map<String, Object> listAndCount= bizXjCxTxTaskService.getSbSsXjListAndCount(par,bean);
		List<Map<String, Object>> list  = (List<Map<String, Object>>) listAndCount.get("rows");
		Map<String, Object> resultData = new HashMap<String, Object>();
		Long count= (Long) listAndCount.get(PageBean.TOTAL);
		resultData.put("pager.pageNo", 1);
		resultData.put("pager.pageSize", 20);
		resultData.put("pager.totalRows", count);
		resultData.put("rows", list);
		return resultData;
		
		
		
		
	}
	 /**
	  * 跳转到班组长确认页面
	  * @date 2017年9月26日 下午5:08:41
	  * @author 杨磊
	  * @param request
	  * @param map
	  * @return
	  */
	 @RequestMapping("/bzzEdit")
	 public String bzzEdit(HttpServletRequest request,ModelMap map ){
		map.put("yesorno", DictConstant.CHECKITEM_SFMR.getValue());
		String taskSbSs = WebUtils.findParameterValue(request, "taskSbSs");
		map.put("taskSbSs", taskSbSs);
		return JSP_PR + "/confirm";
	 }
	 
	 /**
	  * 跳转到生技科页面
	  * @date 2017年9月26日 下午5:09:11
	  * @author 杨磊
	  * @param request
	  * @param map
	  * @return
	  */
	 @RequestMapping("/sjkEdit")
	 public String sjkEdit(HttpServletRequest request,ModelMap map ){
		map.put("yesorno", DictConstant.CHECKITEM_SFMR.getValue());
		String taskSbSs = WebUtils.findParameterValue(request, "taskSbSs");
		map.put("taskSbSs", taskSbSs);
		return JSP_PR + "/sjkconfirm";
	 }
	 /**
	  * 班组长确认
	  * @date 2017年9月26日 下午5:10:02
	  * @author 杨磊
	  * @param request
	  * @param map
	  * @return
	  */
    @ResponseBody
	@RequestMapping("/doAddByBzz")
	public Map<String, Object> doAddByBzz(HttpServletRequest request, ModelMap map) {
		Map<String, Object> result = new HashMap<String, Object>();
		String taskSbSs = WebUtils.findParameterValue(request, "taskSbSs");
		String yesorno = WebUtils.findParameterValue(request, "yesorno");
		String content = WebUtils.findParameterValue(request, "content");
        SysUser user = SysUtils.getLoginSysUser(request);
        String timestemp = DateUtil.dateToString(new Date(), DateUtil.DATE_FULL_FORMAT_N);

		BizTXjCxTaskItemSbss entity = bizTXjCxTaskItemSbssService.findOne(BizTXjCxTaskItemSbss.class, taskSbSs);
		entity.setBzzSfFault(yesorno);
		BizTXjCxTaskItemFaultlc faultEntity=new BizTXjCxTaskItemFaultlc();
		faultEntity.setOptName(user.getUserName());
		faultEntity.setOptId(user.getUserId());
		faultEntity.setTtaskItemSbssId(taskSbSs);
		faultEntity.setOptTime(timestemp);
		faultEntity.setOptContent(content);
		//entity.setXjDesc(content);
		try {
			bizTXjCxTaskItemSbssService.update(entity);
			BizTXjCxTaskItemFaultlcService.save(faultEntity);
			result.put("message", "确认成功");
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.put("message", "确认失败");
		}

		return result;
	}
    /**
     * 生技科确认
     * @date 2017年9月26日 下午5:10:39
     * @author 杨磊
     * @param request
     * @param map
     * @return
     */
    @ResponseBody
	@RequestMapping("/doAddBySjk")
	public Map<String, Object> doAddBySjk(HttpServletRequest request, ModelMap map) {
		Map<String, Object> result = new HashMap<String, Object>();
		String taskSbSs = WebUtils.findParameterValue(request, "taskSbSs");
		String yesorno = WebUtils.findParameterValue(request, "yesorno");
		String content = WebUtils.findParameterValue(request, "content");
		SysUser user = SysUtils.getLoginSysUser(request);
		String timestemp = DateUtil.dateToString(new Date(), DateUtil.DATE_FULL_FORMAT_N);

		BizTXjCxTaskItemSbss entity = bizTXjCxTaskItemSbssService.findOne(BizTXjCxTaskItemSbss.class, taskSbSs);
		entity.setSjkSfFault(yesorno);
		BizTXjCxTaskItemFaultlc faultEntity=new BizTXjCxTaskItemFaultlc();
		faultEntity.setOptName(user.getUserName());
		faultEntity.setOptId(user.getUserId());
		faultEntity.setTtaskItemSbssId(taskSbSs);
		faultEntity.setOptTime(timestemp);
		faultEntity.setOptContent(content);
		//entity.setXjDesc(content);
		try {
			bizTXjCxTaskItemSbssService.update(entity);
			BizTXjCxTaskItemFaultlcService.save(faultEntity);
			result.put("message", "确认成功");
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.put("message", "确认失败");
		}

		return result;
	}
}
