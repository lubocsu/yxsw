package com.upsoft.yxsw.mobile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.upsoft.login.support.webservice.ServiceReceiver;
import com.upsoft.login.support.webservice.SysUtils;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.bean.ResultBean;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.constant.LoginTipMsgConstants;
import com.upsoft.system.util.DateUtil;
import com.upsoft.yxsw.constant.Constant;
import com.upsoft.yxsw.constant.DictConstant;
import com.upsoft.yxsw.entity.BizTXjCxTask;
import com.upsoft.yxsw.service.BizTXjCxTaskService;

@Controller
@RequestMapping(InspectionRecordController.FORWARD_PREFIX)
public class InspectionRecordController {

	protected static final String FORWARD_PREFIX = "/mobile/inspectionRecord";
	protected static final String JSP_PR = "/WEB-INF/mobile/inspectionRecord";
	
	@Autowired
	private BizTXjCxTaskService bizTXjCxTaskService;
	/**
	 * 跳转到巡检任务列表
	 * 
	 * @date 2017年10月17日 上午9:54:15
	 * @author 陈涛
	 * @param request
	 * @param tokenId
	 * @param map
	 * @return 
	 */
	@RequestMapping("/toCxTaskList")
	public String toCxTaskList(HttpServletRequest request,@RequestParam(value="tokenId",required=true) String tokenId,ModelMap map){
		String jsp = "";
		if(!ServiceReceiver.checkLogin(tokenId)){
			
			jsp = JSP_PR+"/relogin";
		}else{
			
			map.put("pageIndex", Constant.PageStatus.PAGE_INDEX);
			map.put("pageSize", Constant.PageStatus.PAGE_SIZE);
			map.put("tokenId", tokenId);
			jsp = JSP_PR+"/cxTaskList";
		}
		return jsp; 
	}
	
	/**
	 * 获取巡检任务列表
	 * 
	 * @date 2017年10月17日 上午10:04:21
	 * @author 陈涛
	 * @param request
	 * @param tokenId
	 * @return 
	 */
	@RequestMapping("/getCxTaskList")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ResultBean getCxTaskList(HttpServletRequest request,@RequestParam(value="tokenId",required=true) String tokenId){
		ResultBean result = new ResultBean();
		result.setFlag(true);
		result.setMessage("请求成功");
		if(ServiceReceiver.checkLogin(tokenId)){
			Map<String,Object> params = new HashMap<String,Object>();
			Map<String,Object> data = new HashMap<String,Object>();
			PageBean bean = new PageBean(request);
			//转换页面查询数据格式
			String jsonParam = request.getParameter("param");
			GsonBuilder gb = new GsonBuilder();
			Gson g = gb.create();
			Map<String, String> paramMap = g.fromJson(jsonParam, new TypeToken<Map<String, String>>() {}.getType());
			if(null != paramMap){
				//获取查询开始时间
				String starttime1 = paramMap.get("starttime1");
				//获取查询结束时间
				String endtime1 = paramMap.get("endtime1");
				//获取查询名称
				String taskName = paramMap.get("taskName");
				if (StringUtils.isNotBlank(starttime1)) {
					params.put("startTime1", starttime1);
				}
				if (StringUtils.isNotBlank(endtime1)) {
					params.put("endTime1", endtime1);
				}
				if (StringUtils.isNotBlank(taskName)) {
					params.put("taskName", taskName);
				}
			}
				//获取数据
				WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
				
				Map<String, Object> cxTaskLists = bizTXjCxTaskService.getCxTaskDataOnMobile(bean, params,loginInfo);
				List<Map<String, Object>> cxTaskList = (List<Map<String, Object>>) cxTaskLists.get("rows");
				//是否超期
				List<Map<String,Object>> yOrN = ServiceReceiver.getDictSelect(DictConstant.CHECKITEM_SFMR.getValue());
				Map<String,Object> yOrNMap = new HashMap<String,Object>();
				for (Map<String, Object> map2 : yOrN) {
					yOrNMap.put(map2.get("key").toString(), map2.get("value"));
				}
				for (Map<String, Object> cxTask : cxTaskList) {
					if(null != cxTask.get("is_over_time")){
						cxTask.put("is_over_time", yOrNMap.get(cxTask.get("is_over_time").toString()));
					}
					if(null != cxTask.get("cx_task_date")){
						cxTask.put("cx_task_date", DateUtil.stringToString(cxTask.get("cx_task_date").toString(), "yyyyMMdd", "yyyy-MM-dd"));
					}
				}
				data.put("cxTaskList",cxTaskList);
				//获取页码
				Map<String,Object> page = new HashMap<String,Object>();
				page.put("pageIndex", bean.getPageIndex());
				page.put("pageSize", bean.getPageSize());
				data.put("page",page);
				result.setData(data);
		}else{
			result.setFlag(false);
			result.setMessage(LoginTipMsgConstants.LOGIN_EXPIRED);
			result.setData(new HashMap<String, Object>(){{put("login",false);}});
		}
		return result;
	}
	
	/**
	 * 跳转到巡检任务详情
	 * 
	 * @date 2017年10月17日 下午2:07:20
	 * @author 陈涛
	 * @param request
	 * @param tokenId
	 * @param rfidCode
	 * @param map
	 * @return 
	 */
	@RequestMapping("/toCxTaskDetail")
	public String toCxTaskDetail(HttpServletRequest request,
			@RequestParam(value="tokenId",required=true) String tokenId,
			@RequestParam(value="cxTaskId",required=true) String cxTaskId,ModelMap map){
		String jsp = "";
		if(!ServiceReceiver.checkLogin(tokenId)){
			
			jsp = JSP_PR+"/relogin";
		}else{
			
				Map<String,Object>  bizTXjCxTask = bizTXjCxTaskService.findOneCxTask(cxTaskId);
				map.put("result", bizTXjCxTask);
				map.put("tokenId", tokenId);
				
				jsp = JSP_PR+"/cxTaskDetail";
			}
			
		return jsp; 
	}
	
	/**
	 * 巡检任务的设施设备列表
	 * 
	 * @date 2017年10月17日 下午2:54:39
	 * @author 陈涛
	 * @param request
	 * @param tokenId
	 * @param cxTaskId
	 * @param map
	 * @return 
	 */
	@RequestMapping("/toSbssList")
	public String toSbssList(HttpServletRequest request,@RequestParam(value="tokenId",required=true) String tokenId,
			@RequestParam(value="cxTaskId",required=true) String cxTaskId,ModelMap map){
		String jsp = "";
		if(!ServiceReceiver.checkLogin(tokenId)){
			
			jsp = JSP_PR+"/relogin";
		}else{
			
			map.put("cxTaskId", cxTaskId);
			map.put("pageIndex", Constant.PageStatus.PAGE_INDEX);
			map.put("pageSize", Constant.PageStatus.PAGE_SIZE);
			map.put("tokenId", tokenId);
			jsp = JSP_PR+"/cxTaskSbssList";
		}
		return jsp; 
	}
	
	@RequestMapping("/getCxTaskSbssList")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public ResultBean getCxTaskSbssList(HttpServletRequest request,@RequestParam(value="tokenId",required=true) String tokenId,
			@RequestParam(value="cxTaskId",required=true) String cxTaskId,ModelMap map){
		ResultBean result = new ResultBean();
		result.setFlag(true);
		result.setMessage("请求成功");
		if(ServiceReceiver.checkLogin(tokenId)){
			Map<String,Object> params = new HashMap<String,Object>();
			Map<String,Object> data = new HashMap<String,Object>();
			PageBean bean = new PageBean(request);
			//转换页面查询数据格式
			String jsonParam = request.getParameter("param");
			GsonBuilder gb = new GsonBuilder();
			Gson g = gb.create();
			Map<String, String> paramMap = g.fromJson(jsonParam, new TypeToken<Map<String, String>>() {}.getType());
			if(null != paramMap){
				//获取查询开始时间
				String sbOrssid = paramMap.get("sbOrssid");
				if (StringUtils.isNotBlank(sbOrssid)) {
					params.put("sbOrssid", sbOrssid);
				}
			}
				//获取数据
				Map<String, Object> sbssLists = bizTXjCxTaskService.getCxTaskSbssData(bean, params,cxTaskId);
				List<Map<String, Object>> sbssList = (List<Map<String, Object>>) sbssLists.get("rows");
				//设备或者设施
				List<Map<String,Object>> sbOrss = ServiceReceiver.getDictSelect(DictConstant.SB_OR_SS.getValue());
				data.put("sbOrss",sbOrss);
				Map<String,Object> sbOrssMap = new HashMap<String,Object>();
				for (Map<String, Object> map2 : sbOrss) {
					sbOrssMap.put(map2.get("key").toString(), map2.get("value"));
				}
				for (Map<String, Object> sbss : sbssList) {
					if(null != sbss.get("detail_type")){
						sbss.put("detail_type", sbOrssMap.get(sbss.get("detail_type").toString()));
					}
					if(null != sbss.get("opt_time")){
						sbss.put("opt_time", DateUtil.stringToString(sbss.get("opt_time").toString(), "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss"));
					}
				}
				data.put("sbssList",sbssList);
				//获取页码
				Map<String,Object> page = new HashMap<String,Object>();
				page.put("pageIndex", bean.getPageIndex());
				page.put("pageSize", bean.getPageSize());
				data.put("page",page);
				result.setData(data);
		}else{
			result.setFlag(false);
			result.setMessage(LoginTipMsgConstants.LOGIN_EXPIRED);
			result.setData(new HashMap<String, Object>(){{put("login",false);}});
		}
		return result;
	}
}
