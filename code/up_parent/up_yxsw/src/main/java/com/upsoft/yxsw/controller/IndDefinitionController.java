package com.upsoft.yxsw.controller;

import java.util.Date;
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
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.util.DateUtil;
import com.upsoft.yxsw.entity.BizTGgWarningManageEntity;
import com.upsoft.yxsw.entity.BizTXjZbItemEntity;
import com.upsoft.yxsw.service.BizTXjZbItemService;

/**
 * 
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：IndDefinitionController.java<br>
* 摘要：指标性管理<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：杨磊<br>
* 完成日期：2017年9月11日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：杨磊<br>
* 完成日期：2017年9月11日<br>
 */
@Controller
@RequestMapping(IndDefinitionController.FORWARD_PREFIX)
public class IndDefinitionController {
	protected static final Logger logger = Logger.getLogger(IndDefinitionController.class);
	protected static final String  FORWARD_PREFIX="/indicative";
	protected static final String  JSP_PR="/WEB-INF/jsp/inddefinition";
	
	@Autowired
	private BizTXjZbItemService  bizTXjZbItemService;
	/**
	 * 初始化使跳转到主页面
	 * @date 2017年9月11日 上午10:44:47
	 * @author 杨磊
	 * @return
	 */
	@RequestMapping("/init")
	public String init(HttpServletRequest request,ModelMap map){
		WSLoginInfoBean sysUser = SysUtils.getLoginInfo(request);
		String typeId = sysUser.getCsOrgTypeId();
		map.put("typeId", typeId);
		return JSP_PR+"/main";
	}
	/**
	 * 查询指标性管理
	 * @date 2017年9月11日 上午11:28:27
	 * @author 杨磊
	 * @param request
	 * @param map
	 * @return
	 */

	@RequestMapping("/getIndList")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public Map<String,Object> getIndList(HttpServletRequest  request,ModelMap map){
		PageBean bean = new PageBean(request);
		Map<String, Object> par = new HashMap<String, Object>();
		// 获取页面查询参数
		String cxzb_code = WebUtils.findParameterValue(request, "cxzb_code");
	    String	cxzb_name = WebUtils.findParameterValue(request, "cxzb_name");
	    String	cxzb_unit = WebUtils.findParameterValue(request, "cxzb_unit");
	    String	cxzb_desc = WebUtils.findParameterValue(request, "cxzb_desc");
	    String	cxzb_tblx = WebUtils.findParameterValue(request, "cxzb_tblx");
	    
	    System.out.println(cxzb_name+"fdfwef");
	    System.out.println(cxzb_tblx+"fddsdasdfwef");
		if(cxzb_tblx.equals("普通文本")){
			cxzb_tblx="1";
		}else if(cxzb_tblx.equals("数值")){
			
			cxzb_tblx="2";
		}
		else{
			
			cxzb_tblx="";
		}
		
		par.put("cxzb_code", cxzb_code);
		par.put("cxzb_name", cxzb_name);
		par.put("cxzb_unit", cxzb_unit);
		par.put("cxzb_desc", cxzb_desc);
		par.put("cxzb_tblx", cxzb_tblx);
		Map<String, Object> indListAndCount = bizTXjZbItemService.getIndListAndCount(
				par, bean);
		List<Map<String, Object>> list = (List<Map<String, Object>>) indListAndCount
				.get("rows");
		long count = (Long) indListAndCount.get(PageBean.TOTAL);

		Map<String, Object> resultData = new HashMap<String, Object>();
		
		resultData.put("pager.pageNo", 1);
		resultData.put("pager.pageSize", 20);
		resultData.put("pager.totalRows", count);
		resultData.put("rows", list);
		return resultData;
		
	}
	/**
	 * 只删除一个
	 * @date 2017年9月12日 下午5:16:19
	 * @author 杨磊
	 * @param request
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delOneInd")
	public Map<String, Object> delOneInd(HttpServletRequest request, ModelMap map) {
		Map<String, Object> result = new HashMap<String, Object>();
		String id = request.getParameter("id");
		// 查询厂巡检模板指标项关联是否关联，如果关联提醒用户不能删除
		Boolean checkbooleans = bizTXjZbItemService.checkTemplate(id);
		if(checkbooleans){
			result.put("status", 0);		
		}else{
			
		  boolean delboolean = bizTXjZbItemService.deleteOneWarning(id);
		if(delboolean){
		    result.put("status", 1);
		}else{
			
			result.put("status", 2);
		}
		
		}
		return result;
	}
	/**
	 * 跳转到添加页面
	 * @date 2017年9月11日 下午3:27:35
	 * @author 杨磊
	 * @return
	 */
	@RequestMapping("/toAddInd")
	public String toAddWarning(){
		
		return JSP_PR+"/addInd";
	}
	/**
	 * 添加
	 * @date 2017年9月11日 下午3:38:34
	 * @author 杨磊
	 * @param request
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/doAddInd")
	public Map<String, Object> doAddWarning(HttpServletRequest request, ModelMap map  ) {
		Map<String, Object> msg = new HashMap<String,Object>();
		//获取用户信息
		SysUser user = SysUtils.getLoginSysUser(request);
		
		String cxzb_code = request.getParameter("cxzb_code");
		String cxzb_name = request.getParameter("cxzb_name");
		String cxzb_unit = request.getParameter("cxzb_unit");
		String cxzb_tblx = request.getParameter("cxzb_tblx");
		String cxzb_desc = request.getParameter("cxzb_desc");
		
		BizTXjZbItemEntity bizTXjZbItemEntity=new BizTXjZbItemEntity();
		bizTXjZbItemEntity.setCxzbCode(cxzb_code);
        bizTXjZbItemEntity.setCxzbName(cxzb_name);
        bizTXjZbItemEntity.setCxzbUnit(cxzb_unit);
        bizTXjZbItemEntity.setCxzbTblx(cxzb_tblx);
        bizTXjZbItemEntity.setCxzbDesc(cxzb_desc);
     	String userId = user.getUserId();
		String userName = user.getUserName();
		bizTXjZbItemEntity.setCreatorAccount(userId);
		bizTXjZbItemEntity.setUpdatorAccount(userId);
        bizTXjZbItemEntity.setUpdatorName(userName);
        bizTXjZbItemEntity.setDelFlag(0);
        Date date = new Date();
        bizTXjZbItemEntity.setCreateTimestemp(DateUtil.dateToString(date, "yyyyMMddHHmmss"));
        bizTXjZbItemEntity.setUpdateTimestemp(DateUtil.dateToString(date, "yyyyMMddHHmmss"));
       
		System.out.println(cxzb_code+"yayaya");
		try{
			// bizTXjZbItemService.saveInd(bizTXjZbItemEntity,user);
			bizTXjZbItemService.save(bizTXjZbItemEntity);
			msg.put("message", "新增指标项信息成功！");
		}catch(Exception e){
			msg.put("message", "新增指标项信息失败!");
			logger.error("新增指标项信息失败，" + e.getMessage());
		}
		return msg;
	
}  

 /**
  * 验证输入的code是否存在
  * @date 2017年9月11日 下午8:49:55
  * @author 杨磊
  * @param request
  * @param map
  * @return
  */
	 @ResponseBody
	 @RequestMapping("/validLoginId")
	  public String validLoginId(HttpServletRequest request,ModelMap map){
	
	     String cxzb_code = request.getParameter("validateValue");   
	     List<Map<String, Object>> list = bizTXjZbItemService.findOneByCode(cxzb_code);
	     System.out.println(list.size()+"sizesize");
	        if(list.size()>0){
	        	
	        	  return "{\"validateResult\":{\"valid\":false}}";
	        }
	        else{
	        	  return "{\"validateResult\":{\"valid\":true}}";
	        }
	  
	  }
	 
	 /**
	  * 验证是否值没变，而且如果值变了是否重复
	  * @date 2017年9月12日 下午5:22:55
	  * @author 杨磊
	  * @param request
	  * @param map
	  * @return
	  */
	  @ResponseBody
	  @RequestMapping("/validOldLoginId")
	  public String validOldLoginId(HttpServletRequest request,ModelMap map){
	
		String cxzb_code = request.getParameter("validateValue");
		String old = request.getParameter("old");
		if (cxzb_code.equals(old)) {
			return "{\"validateResult\":{\"valid\":true}}";
		} else {
			List<Map<String, Object>> list = bizTXjZbItemService.findOneByCode(cxzb_code);
			if (list.size() > 0) {

				return "{\"validateResult\":{\"valid\":false}}";
			} 
			else {
				return "{\"validateResult\":{\"valid\":true}}";
			}
		}
	  
	  }
	 /**
	  * 展示指标项
	  * @date 2017年9月12日 上午11:26:28
	  * @author 杨磊
	  * @param cxzb_id
	  * @param map
	  * @return
	  */
        @RequestMapping("/toShowInd")
      public String toShowInd(@RequestParam String cxzb_id,ModelMap map){

	    Map<String, Object> cxzb_idInfo = new HashMap<String, Object>();

	    // 获取用户信息
	     if (cxzb_id != null && StringUtils.isNotBlank(cxzb_id)) {
		Map<String, Object> pars = new HashMap<String, Object>();
		pars.put("cxzb_id", cxzb_id);
		List<Map<String, Object>> list = bizTXjZbItemService
				.getIndList(pars, 0, 0);
	
		cxzb_idInfo = list.get(0);
		
		map.addAttribute("cxzb_id",cxzb_idInfo );

	}

	
	
	return JSP_PR+"/showInd";
}
    /**
     * 去更新指标项并且带数据去 
     * @date 2017年9月12日 上午11:27:37
     * @author 杨磊
     * @param cxzb_id
     * @param map
     * @return
     */
	@RequestMapping("/toUpInd")
	public String toUpInd(@RequestParam String cxzb_id, ModelMap map) {

		Map<String, Object> cxzb_idInfo = new HashMap<String, Object>();

		// 获取用户信息
		if (cxzb_id != null && StringUtils.isNotBlank(cxzb_id)) {
			Map<String, Object> pars = new HashMap<String, Object>();
			pars.put("cxzb_id", cxzb_id);
			List<Map<String, Object>> list = bizTXjZbItemService.getIndList(pars, 0, 0);

			cxzb_idInfo = list.get(0);
			map.addAttribute("cxzb_id", cxzb_idInfo);
		}

	
	return JSP_PR+"/updateInd";
}
    /**
     * 更新指标项
     * @date 2017年9月12日 上午11:29:03
     * @author 杨磊
     * @param request
     * @param map
     * @return
     */
	@ResponseBody
	@RequestMapping("/doUpInd")
	public Map<String, Object> doUpInd(HttpServletRequest request, ModelMap map) {

		String cxzb_id = WebUtils.findParameterValue(request, "cxzb_id");
		System.out.println(cxzb_id + "idididididd");
		String cxzb_tblx = WebUtils.findParameterValue(request, "cxzb_tblx");
		String cxzb_unit = WebUtils.findParameterValue(request, "cxzb_unit");
		String cxzb_desc = WebUtils.findParameterValue(request, "cxzb_desc");
		String cxzb_name = WebUtils.findParameterValue(request, "cxzb_name");
		String cxzb_code = WebUtils.findParameterValue(request, "cxzb_code");
	
		BizTXjZbItemEntity entity = bizTXjZbItemService.findOne(BizTXjZbItemEntity.class, cxzb_id);
		entity.setDelFlag(0);
		entity.setCxzbCode(cxzb_code);
		entity.setCxzbName(cxzb_name);
		entity.setCxzbUnit(cxzb_unit);
		entity.setCxzbDesc(cxzb_desc);
		entity.setCxzbTblx(cxzb_tblx);
		String timestemp = DateUtil.dateToString(new Date(), DateUtil.DATE_FULL_FORMAT_N);
	    entity.setCreateTimestemp(timestemp);
	    SysUser loginSysUser = SysUtils.getLoginSysUser(request);
	    entity.setCreatorAccount(loginSysUser.getUserId());
	    
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			bizTXjZbItemService.update(entity);
			result.put("message", "修改指标信息成功！");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 查询指标项列表，供作业票模版关联
	 * @date 2017年9月11日 上午11:28:27
	 * @author 杨磊
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/getIndList4Temp")
	@ResponseBody
	public Map<String,Object> getIndList4Temp(HttpServletRequest  request,ModelMap map){
		PageBean bean = new PageBean(request);
		Map<String, Object> par = new HashMap<String, Object>();
		// 获取页面查询参数
		String cxzb_code = WebUtils.findParameterValue(request, "cxzb_code");
	    String	cxzb_name = WebUtils.findParameterValue(request, "cxzb_name");
	    String	cxzb_unit = WebUtils.findParameterValue(request, "cxzb_unit");
	    String	cxzb_tblx = WebUtils.findParameterValue(request, "cxzb_tblx");
	    String	zxpTempItmId = WebUtils.findParameterValue(request, "zxpTempItmId");
	    
		if(cxzb_tblx.equals("普通文本")){
			cxzb_tblx="1";
		}else if(cxzb_tblx.equals("数值")){
			
			cxzb_tblx="2";
		}
		else{
			
			cxzb_tblx="";
		}
		
		par.put("cxzb_code", cxzb_code);
		par.put("cxzb_name", cxzb_name);
		par.put("cxzb_unit", cxzb_unit);
		par.put("cxzb_tblx", cxzb_tblx);
		par.put("zxpTempItmId", zxpTempItmId);
		Map<String, Object> resultData = bizTXjZbItemService.getIndList4Temp(par, bean);
		return resultData;
		
	}

}
