package com.upsoft.yxsw.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.upsoft.login.controller.BaseController;
import com.upsoft.login.support.webservice.ServiceReceiver;
import com.upsoft.login.support.webservice.SysUtils;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.constant.CommonConstant;
import com.upsoft.yxsw.constant.Constant;
import com.upsoft.yxsw.controller.cxMake.bean.CxMakeDetailPojo;
import com.upsoft.yxsw.entity.BizTXjZypCxMake;
import com.upsoft.yxsw.service.BizTXjZypCxMakeService;

/**
* Copyright (c) 2017,重庆扬讯软件技术股份有限公司<br>
* All rights reserved.<br>
*
* 文件名称：ZypCheckController.java<br>
* 摘要：作业票中止<br>
* -------------------------------------------------------<br>
* 当前版本：1.0<br>
* 作者：陈涛<br>
* 完成日期：2017年10月9日<br>
* -------------------------------------------------------<br>
*/
@Controller
@RequestMapping(ZypDiscontinueController.FORWARD_PREFIX)
public class ZypDiscontinueController extends BaseController{

	protected static final Logger logger = Logger.getLogger(ZypDiscontinueController.class);
	protected static final String FORWARD_PREFIX = "/zypDiscontinue";
	private static final String JSP_PREFIX = "/WEB-INF/jsp/zypDiscontinue";
	
	@Autowired
	private BizTXjZypCxMakeService bizTXjZypCxMakeService;
	/**
	 * 跳转到主页
	 * 
	 * @date 2017年10月9日 下午2:58:36
	 * @author 陈涛
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/init")
	public String init(HttpServletRequest request, ModelMap map,String queryParam){
		
		map.put("zyp",new Gson().fromJson(queryParam, BizTXjZypCxMake.class));
		return JSP_PREFIX + "/main";
	}
	
	/**
	 * 获取已审核作业票
	 * 
	 * @date 2017年10月9日 下午4:46:59
	 * @author 陈涛
	 * @param request
	 * @param param
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getZypDiscontinueList")
	@ResponseBody
	public Map<String,Object> getZypDiscontinueList(HttpServletRequest request,BizTXjZypCxMake param){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		PageBean pageBean = new PageBean(request);
		try {
			paramMap = BeanUtils.describe(param);
			// 查询已审核作业票
			List<String> cxzypStatusList = new ArrayList<String>();
			cxzypStatusList.add(Constant.ZYP_STAUTS.AUDITED.getValue());
			paramMap.put("cxzypStatus", cxzypStatusList);
			// 获取当前厂站作业票
			WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
			List<String> belongWscIdList = new ArrayList<String>();
			if(Integer.valueOf(loginInfo.getCsOrgTypeId()) < Integer.valueOf(CommonConstant.orgType.FACTORY.getKey()) ){
				belongWscIdList = ServiceReceiver.getPermissionOrgIds(loginInfo.getUser().getUserId());
			}else{
				belongWscIdList.add(loginInfo.getCsOrgId());
			}
			paramMap.put("belongWscId",belongWscIdList);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e1) {
			logger.error("查询已审核作业票分页数据时，将参数bean转为map时出错："+e1.getMessage());
		}
		Map<String,Object> result = bizTXjZypCxMakeService.getCxMakeList(pageBean,paramMap);
		return result;
	}
	
	/**
	 * 跳转到选项卡页面
	 * 
	 * @date 2017年10月9日 下午5:32:43
	 * @author 陈涛
	 * @param backURL
	 * @param queryParam
	 * @param map
	 * @return 
	 */
	@RequestMapping("/detailAndDiscontinue")
	public String detailAndDiscontinue(@RequestParam(value="cxMakeId",required=true)String cxMakeId,HttpServletRequest request,String backURL,String queryParam,ModelMap map){
		
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		map.put("cxMakeId", cxMakeId);
		map.put("csOrgType", loginInfo.getCsOrgTypeId());
		map.put("queryParam", queryParam);
		map.put("backURL", backURL);
		return JSP_PREFIX + "/iframe";
	}
	
	/**
	 * 作业票详情
	 * 
	 * @date 2017年10月9日 下午7:12:31
	 * @author 陈涛
	 * @param cxMakeId
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/detail")
	public String detail(@RequestParam(value="cxMakeId",required=true)String cxMakeId,HttpServletRequest request,ModelMap map){
		
		map.put("cxMakeId", cxMakeId);
		CxMakeDetailPojo pojo = bizTXjZypCxMakeService.getZYPDetailForPC(cxMakeId);
		map.put("zyp", pojo);
		map.put("TEXT_INPUT_TYPE", Constant.CHECKITEM_INPUTTYPE_VALUE.TEXT.getValue());
		map.put("NUM_INPUT_TYPE", Constant.CHECKITEM_INPUTTYPE_VALUE.NUM.getValue());
		
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		map.put("cxMakeId", cxMakeId);
		map.put("csOrgType", loginInfo.getCsOrgTypeId());
		return JSP_PREFIX + "/detail";
	}
	
	/**
	 * 中止作业票
	 * 
	 * @date 2017年10月10日 下午7:30:14
	 * @author 陈涛
	 * @param cxMakeId
	 * @param content
	 * @param request
	 * @param map
	 * @return 
	 */
	@RequestMapping("/doDiscontinueZyp")
	@ResponseBody
	public Map<String, Object> doDiscontinueZyp(@RequestParam(value="cxMakeId",required=true)String cxMakeId,String content,
			String updateTimestemp,HttpServletRequest request,ModelMap map){
		Map<String, Object> msg = new HashMap<String,Object>();
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		CxMakeDetailPojo pojo = bizTXjZypCxMakeService.getZYPDetailForPC(cxMakeId);
		String nupdateTimestemp = pojo.getUpdateTimestemp();
		if(updateTimestemp.equals(nupdateTimestemp)){
			try{
				bizTXjZypCxMakeService.updateZypDiscontinue(cxMakeId,content,loginInfo);
				msg.put("message", "中止成功!");
			}catch(Exception e){
					
				msg.put("message", "中止失败!");
				logger.error("作业票中止失败，" + e.getMessage());
			}
		}else{
			msg.put("message", "数据已发生变化，请刷新后重试!");
		}
		return msg;
	}
}
