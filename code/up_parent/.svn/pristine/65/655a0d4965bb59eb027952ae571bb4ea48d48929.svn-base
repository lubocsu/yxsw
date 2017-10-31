package com.upsoft.yxsw.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.upsoft.login.controller.BaseController;
import com.upsoft.login.support.webservice.SysUtils;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.bean.ResultBean;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.util.DateUtil;
import com.upsoft.yxsw.entity.BizTGgCheckItem;
import com.upsoft.yxsw.entity.BizTGgSbssAttachBase;
import com.upsoft.yxsw.entity.BizTSbBaseinfo;
import com.upsoft.yxsw.service.BizTGgCheckItemService;
import com.upsoft.yxsw.service.BizTGgSbssAttachBaseService;
import com.upsoft.yxsw.service.BizTGgWarningManageService;
import com.upsoft.yxsw.service.BizTSbBaseinfoService;
import com.upsoft.yxsw.service.BizTXjdItemService;
/**
 * 
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：EqWarnController.java<br>
* 摘要：设备检查项<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：杨磊<br>
* 完成日期：2017年9月18日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：杨磊<br>
* 完成日期：2017年9月18日<br>
 */
@Controller
@RequestMapping(EqWarnController.FORWARD_PREFIX)
public class EqWarnController extends BaseController {
	protected static final String  FORWARD_PREFIX="/sbWarn";
	protected static final String  JSP_PR="/WEB-INF/jsp/sbattachwarn";
	protected Logger logger=Logger.getLogger(EqWarnController.class);
	
	@Autowired
    private  BizTSbBaseinfoService  BizTSbBaseinfoService;//设备信息
	@Autowired
	private  BizTGgSbssAttachBaseService   BizTGgSbssAttachBaseService ;//设备信息与检查项的关系
	@Autowired
	private  BizTGgWarningManageService bizTGgWarningManageService  ;//安全警告
	@Autowired
	private BizTGgCheckItemService  bizTGgCheckItemService;//检查项
	@Autowired
	public BizTXjdItemService bizTXjdItemService;
	
	@RequestMapping("/init")
	public  String init(){
		
		return  JSP_PR +"/main";
	}
	/**
	 * 获取设备与安全定义的树
	 * @date 2017年9月18日 上午10:39:32
	 * @author 杨磊
	 * @param request
	 * @return
	 */
	@RequestMapping("/getSbTree")
	@ResponseBody
	public List<Map<String, Object>> getEquipmentTree(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		String input = WebUtils.findParameterValue(request, "input");// 刷新获取填入的值
		params.put("input", input);
		String csOrgId = SysUtils.getLoginInfo(request).getCsOrgId();
		params.put("csOrgId", csOrgId);
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		//List<Map<String, Object>> sb = BizTGgSbssAttachBaseService.queryByTreeId(params);
		List<Map<String, Object>> sb = BizTGgSbssAttachBaseService.queryByTreeId(input,params);
		for (Map<String, Object> map : sb) {
			
			if(map.get("has_attach").toString().equals("1")){  //通过判断 has_attach 来渲染颜色

				Map<String, Object> mapColor = new HashMap<String, Object>();
				mapColor.put("color", "green");
				map.put("font", mapColor);
				map.put("parentId", map.get("parentid"));
				map.remove("parentid");

			} else {
				map.put("parentId", map.get("parentid"));
				map.remove("parentid");

			}
		}
		List<Map<String, Object>> orgList = bizTXjdItemService.getOrgList(loginInfo);
		for (Map<String, Object> org : orgList) {
			org.put("flag", "noClick");
			
			sb.add(org);
		
		}
		return sb;

	}
	@RequestMapping("/showDetail")
	  public String  showEqInfo (HttpServletRequest request,ModelMap map){
		  String eqId = request.getParameter("sbId");
		  Map<String,Object> hashMap=new HashMap<String,Object>();
		  BizTSbBaseinfo entity = BizTSbBaseinfoService.findOne(BizTSbBaseinfo.class, eqId);
		  String name = entity.getSbName();
		  String address = entity.getSetAddress();
		  String sbxh = entity.getSbxh();
		  String xncs = entity.getXncs();
		  String jgyl = entity.getJgyl();
		  String byzd = entity.getByzd();//是否必须扫码。
		  String code = entity.getSbCode();
		  hashMap.put("name", name);
		  hashMap.put("address", address);
		  hashMap.put("sbxh", sbxh);
		  hashMap.put("xncs",xncs);
		  hashMap.put("jgyl", jgyl);
		  hashMap.put("byzd", byzd);
		  hashMap.put("code", code);
		  hashMap.put("sbId",eqId);
		  //这个是取出的STRING 字符传，取的Key值也为String  字符串 如"name",name
		  map.addAttribute("sb", hashMap);
		  WSLoginInfoBean sysUser = SysUtils.getLoginInfo(request);
		  String typeId = sysUser.getCsOrgTypeId();
		  map.put("typeId", typeId);
		  
		  return JSP_PR+"/detail";
	  }
	  @SuppressWarnings("unchecked")
	  @RequestMapping("/getUnCheckList")
	  @ResponseBody
	  public Map<String,Object> getUnCheckList(HttpServletRequest  request,ModelMap map){ 
		    String code = WebUtils.findParameterValue(request, "sbId");
		    String checkCode = WebUtils.findParameterValue(request, "code");
		    String name = WebUtils.findParameterValue(request, "name");
		    PageBean bean = new PageBean(request);
			Map<String, Object> par = new HashMap<String, Object>();
			par.put("code", code);
			par.put("checkCode", checkCode);
			par.put("name", name);

			Map<String, Object> warningListAndCount =  BizTGgSbssAttachBaseService.getUnCheckListAndCount(
					par, bean);
			List<Map<String, Object>> list = (List<Map<String, Object>>) warningListAndCount
					.get("rows");
			long count = (Long) warningListAndCount.get(PageBean.TOTAL);

			Map<String, Object> resultData = new HashMap<String, Object>();
			
			resultData.put("pager.pageNo", 1);
			resultData.put("pager.pageSize", 10);
			resultData.put("pager.totalRows", count);
			resultData.put("rows", list);
			return resultData;  
	  }
	  @RequestMapping("/delOne")
	  @ResponseBody
	  public ResultBean delOne(HttpServletRequest request){
		  Map<String, Object> map=new HashMap<String,Object>();
		  String ids = WebUtils.findParameterValue(request, "ids");
		  String sbId= WebUtils.findParameterValue(request, "sbId");
		  ResultBean bean=new ResultBean();
		   try {
			   BizTGgSbssAttachBaseService.deleteALL(ids,sbId);
			   bean.setMessage("删除成功");
		} catch (Exception e) {
			 bean.setMessage("删除失败");
			 bean.setFlag(false);
			 logger.error(e.getMessage());
		}
		   return bean;
	  }
	  @SuppressWarnings("unchecked")
	  @RequestMapping("/getList")
	  @ResponseBody
	  public Map<String,Object> getWarningList(HttpServletRequest  request,ModelMap map){
		  
		    String code = WebUtils.findParameterValue(request, "sbId");
		    PageBean bean = new PageBean(request);
			Map<String, Object> par = new HashMap<String, Object>();
			par.put("code", code);

			Map<String, Object> warningListAndCount =  BizTGgSbssAttachBaseService.getWarningListAndCount(
					par, bean);
			List<Map<String, Object>> list = (List<Map<String, Object>>) warningListAndCount
					.get("rows");
			long count = (Long) warningListAndCount.get(PageBean.TOTAL);

			Map<String, Object> resultData = new HashMap<String, Object>();
			
			resultData.put("pager.pageNo", 1);
			resultData.put("pager.pageSize", 20);
			resultData.put("pager.totalRows", count);
			resultData.put("rows", list);
			return resultData;
	  }

	@RequestMapping("/toAddCheck")
	public String toAddCheck(HttpServletRequest request, ModelMap map) {
		String code = WebUtils.findParameterValue(request, "sbId");
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("code", code);
		map.addAttribute("code", hashMap);
		return JSP_PR + "/add";

	}
	  @RequestMapping("addUnit")
	  @ResponseBody
	  public  Map<String,Object> addUnit(HttpServletRequest request){
		   Map<String,Object> hashMap=new  HashMap<String,Object>();
		   String sbId = WebUtils.findParameterValue(request,"sbId");
		   String ckIds =WebUtils.findParameterValue(request,"ids");
		   String[] strings = ckIds.split(",");
		   for (String string : strings) {
			try {

				BizTGgCheckItem ckEntity = bizTGgCheckItemService.findOne(BizTGgCheckItem.class, string);
				BizTSbBaseinfo sbEntity = BizTSbBaseinfoService.findOne(BizTSbBaseinfo.class, sbId);
				BizTGgSbssAttachBase attachBaseEntity = new BizTGgSbssAttachBase();
				String eqId = sbEntity.getSbId();// 设备ID
				String itemId = ckEntity.getCheckItemId();// 检查项ID;
				attachBaseEntity.setCode(eqId);
				attachBaseEntity.setSbOrSs("1");
				attachBaseEntity.setDetailId(itemId);
				SysUser user = SysUtils.getLoginSysUser(request);
				attachBaseEntity.setCreatorAccount(user.getUserId());
				attachBaseEntity.setCreatorName(user.getUserName());
				attachBaseEntity.setBelongDept(user.getOrgId());
				attachBaseEntity.setConfDesc(ckEntity.getCheckItemDesc());
				Date date = new Date();
				String timestemp = DateUtil.dateToString(date, DateUtil.DATE_FULL_FORMAT_N);
				attachBaseEntity.setCreateTimestemp(timestemp);
				attachBaseEntity.setConfType("1");

				BizTGgSbssAttachBaseService.save(attachBaseEntity);
				hashMap.put("status", 1);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}

		return hashMap;
	}
}
