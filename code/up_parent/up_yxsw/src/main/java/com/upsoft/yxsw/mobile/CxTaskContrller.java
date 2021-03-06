package com.upsoft.yxsw.mobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upsoft.login.support.webservice.ServiceReceiver;
import com.upsoft.login.support.webservice.SysUtils;
import com.upsoft.system.bean.ResultBean;
import com.upsoft.system.constant.LoginTipMsgConstants;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.util.ExceptionFormatUtil;
import com.upsoft.yxsw.entity.BizTXjCxTaskItem;
import com.upsoft.yxsw.mobile.bean.task.TaskListBean;
import com.upsoft.yxsw.service.BizTXjCxTaskItemSbssService;
import com.upsoft.yxsw.service.BizTXjCxTaskItemService;
import com.upsoft.yxsw.service.BizTXjCxTaskPersonsService;
import com.upsoft.yxsw.service.BizTXjCxTaskService;

@Controller
@RequestMapping("/mobile/task")
public class CxTaskContrller {

	private Logger logger = Logger.getLogger(MobileController.class);
	
	@Autowired
	private BizTXjCxTaskService bizTXjCxTaskService;
	@Autowired
	private BizTXjCxTaskItemService bizTXjCxTaskItemService;
	@Autowired
	private BizTXjCxTaskItemSbssService bizTXjCxTaskItemSbssService;
	@Autowired
	private BizTXjCxTaskPersonsService bizTXjCxTaskPersonsService;
	
	private static final String HAVE_CHARGE = "该任务已有人处理";
	/**
	 * 获取巡检点列表
	 * @date 2017年9月22日 下午2:12:44
	 * @author 胡毅
	 * @param tokenId
	 * @param request
	 * @return
	 */
	@RequestMapping("/getMyTask")
	@ResponseBody
	public ResultBean getMyTask(@RequestParam(required = true)String tokenId,HttpServletRequest request){
		ResultBean result = new ResultBean();
		if(!ServiceReceiver.checkLogin(tokenId)){
			result.setFlag(false);
    		result.setMessage(LoginTipMsgConstants.LOGIN_EXPIRED);
    		return result;
		}
		try {
			SysUser user = SysUtils.getLoginSysUser(request);
			List<TaskListBean> taskList = bizTXjCxTaskService.getMyTask(user.getUserId());
			Map<String,Object> data = new HashMap<String,Object>();
			if(null==taskList){
				data.put("taskList", new ArrayList<TaskListBean>());
			}else{
				data.put("taskList", taskList);
			}
			result.setData(data);
		} catch (Exception e) {
			result.setFlag(false);
			result.setMessage("获取APP任务列表失败："+e.getMessage());
			logger.error("获取APP任务列表失败："+ExceptionFormatUtil.formatExceptionTrace(e));
		}
		
		return result;
	}
	
	@RequestMapping("/startTask")
	@ResponseBody
	public ResultBean startTask(@RequestParam(required = true)String tokenId,
								@RequestParam(required = true)String taskId,HttpServletRequest request){
		ResultBean result = new ResultBean();
		if(!ServiceReceiver.checkLogin(tokenId)){
			result.setFlag(false);
    		result.setMessage(LoginTipMsgConstants.LOGIN_EXPIRED);
    		return result;
		}
		try {
			SysUser user = SysUtils.getLoginSysUser(request);
			// 验证 任务是否有人受理
			boolean flag = bizTXjCxTaskPersonsService.queryCharge(taskId,user.getUserId());
			if(flag){
				result.setFlag(false);
				result.setMessage(HAVE_CHARGE);
				return result;
			}
			bizTXjCxTaskService.updateToStartTask(taskId,user.getUserId());
		} catch (Exception e) {
			result.setFlag(false);
			result.setMessage("获取APP任务列表失败："+e.getMessage());
			logger.error("获取APP任务列表失败："+ExceptionFormatUtil.formatExceptionTrace(e));
		}
		
		return result;
	}
	
	@RequestMapping("/completeTask")
	@ResponseBody
	public ResultBean completeTask(@RequestParam(required = true)String tokenId,
								@RequestParam(required = true)String taskId){
		ResultBean result = new ResultBean();
		if(!ServiceReceiver.checkLogin(tokenId)){
			result.setFlag(false);
    		result.setMessage(LoginTipMsgConstants.LOGIN_EXPIRED);
    		return result;
		}
		try {
			bizTXjCxTaskService.updateToCompleteTask(taskId);
		} catch (Exception e) {
			result.setFlag(false);
			result.setMessage("获取APP任务列表失败："+e.getMessage());
			logger.error("获取APP任务列表失败："+ExceptionFormatUtil.formatExceptionTrace(e));
		}
		
		return result;
	}
	
	/**
	 * 巡检任务巡检点记录
	 * @date 2017年9月22日 下午2:56:24
	 * @author 胡毅
	 * @param tokenId
	 * @param taskId
	 * @param request
	 * @return
	 */
	@RequestMapping("/getTaskPointList")
	@ResponseBody
	public ResultBean getTaskPointList(@RequestParam(required = true)String tokenId,
									   @RequestParam(required = true)String taskId,HttpServletRequest request){
		ResultBean result = new ResultBean();
		if(!ServiceReceiver.checkLogin(tokenId)){
			result.setFlag(false);
    		result.setMessage(LoginTipMsgConstants.LOGIN_EXPIRED);
    		return result;
		}
		try {
			List<BizTXjCxTaskItem> pointList = bizTXjCxTaskItemService.getTaskPointList(taskId);
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("pointList", pointList);
			result.setData(data);
		} catch (Exception e) {
			result.setFlag(false);
			result.setMessage("获取任务点位列表失败："+e.getMessage());
			logger.error("获取任务点位列表失败："+ExceptionFormatUtil.formatExceptionTrace(e));
		}
		return result;
	}
	
	/**
	 * 根据巡检点标签获取巡检点安全提醒和设备设施列表,同时更新巡检记录
	 * @date 2017年9月22日 下午4:35:43
	 * @author 胡毅
	 * @param tokenId
	 * @param taskItemId 巡检点记录Id
	 * @param rfid 如果rfid为空，则巡检点非正常扫描
	 * @return
	 */
	@RequestMapping("/getDetailByRfid")
	@ResponseBody
	public ResultBean getDetailByRfid(@RequestParam(required = true)String tokenId,
			   						 @RequestParam(required = true)String taskItemId,
			   						 String rfid,HttpServletRequest request){
		ResultBean result = new ResultBean();
		if(!ServiceReceiver.checkLogin(tokenId)){
			result.setFlag(false);
    		result.setMessage(LoginTipMsgConstants.LOGIN_EXPIRED);
    		return result;
		}
		try {
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
			Map<String, Object> data = bizTXjCxTaskItemService.updatePointItemAndGetSbssInfoAndWarningByPointId(taskItemId,rfid,basePath);
			result.setData(data);
		} catch (Exception e) {
			result.setFlag(false);
			result.setMessage("获取点位详情失败："+e.getMessage());
			logger.error("获取点位详情失败："+ExceptionFormatUtil.formatExceptionTrace(e));
		}
		return result;
	}
	
	/**
	 * 根据巡检带你设备设施记录ID获取设备的安全提醒，同时更新改设备设施的扫描方式
	 * @date 2017年9月23日 下午1:45:14
	 * @author 胡毅
	 * @param tokenId
	 * @param ttaskItemSbssId
	 * @param confirmType
	 * @param request
	 * @return
	 */
	@RequestMapping("/getWarningByQRCode")
	@ResponseBody
	public ResultBean getDetailByQRCode(@RequestParam(required = true)String tokenId,
			   						 @RequestParam(required = true)String ttaskItemSbssId,
			   						 String confirmType,HttpServletRequest request){
		ResultBean result = new ResultBean();
		if(!ServiceReceiver.checkLogin(tokenId)){
			result.setFlag(false);
    		result.setMessage(LoginTipMsgConstants.LOGIN_EXPIRED);
    		return result;
		}
		try {
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
			Map<String, Object> data = bizTXjCxTaskItemSbssService.updateAndGetWarningOfThis(ttaskItemSbssId,confirmType,basePath);
			result.setData(data);
		} catch (Exception e) {
			result.setFlag(false);
			result.setMessage("获取设备设施安全提醒失败："+e.getMessage());
			logger.error("获取设备设施安全提醒失败："+ExceptionFormatUtil.formatExceptionTrace(e));
		}
		return result;
	}
}
