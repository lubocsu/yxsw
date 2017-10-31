package com.upsoft.yxsw.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javassist.expr.NewArray;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.regexp.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;
import com.upsoft.login.controller.BaseController;
import com.upsoft.login.support.webservice.SysUtils;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.util.DateUtil;
import com.upsoft.yxsw.entity.BizTGgCheckItem;
import com.upsoft.yxsw.entity.BizTGgSbssAttachBase;
import com.upsoft.yxsw.entity.BizTSbBaseinfo;
import com.upsoft.yxsw.service.BizTGgCheckItemService;
import com.upsoft.yxsw.service.BizTGgSbssAttachBaseService;
import com.upsoft.yxsw.service.BizTSbBaseinfoService;
/**
 * 设备关联检查项
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：EqAsCheck.java<br>
* 摘要：简要描述本文件的内容<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：杨磊<br>
* 完成日期：2017年9月13日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：杨磊<br>
* 完成日期：2017年9月13日<br>
 */
@Deprecated
@Controller
@RequestMapping(EqAsCheckController.FORWARD_PREFIX)
public class EqAsCheckController extends BaseController {
	
	protected static final String  FORWARD_PREFIX="/eqAsCheck";
	protected static final String  JSP_PR="/WEB-INF/jsp/eqAsCheck";
	protected Logger logger=Logger.getLogger(EqAsCheckController.class);
	
	@Autowired
    private  BizTSbBaseinfoService  BizTSbBaseinfoService;//设备信息
	@Autowired
	private  BizTGgSbssAttachBaseService   BizTGgSbssAttachBaseService ;//设备信息与检查项的关系
	@Autowired
	private BizTGgCheckItemService bizTGgCheckItemService;//检查项
	
	@RequestMapping("/init")
	public  String init(){
		
		return  JSP_PR +"/main";
	}
	
	/**
	 * 获取设备树
	 * @date 2017年9月13日 下午3:39:40
	 * @author 杨磊
	 * @param request
	 * @return
	 */
	  @RequestMapping("/getEquipmentTree1")	
	  @ResponseBody
	 public List<Map<String, Object>> getEquipmentTree(HttpServletRequest request){
		 Map<String,Object> params=new HashMap<String,Object>();
		 String input = WebUtils.findParameterValue(request, "input");//刷新获取填入的值
		 System.out.println(input+"input");
		 String orgId = SysUtils.getLoginInfo(request).getCsOrgId();
	     List<Map<String, Object>>   sb  =	 BizTGgSbssAttachBaseService.queryByTreeId(params);
	 
	     for (Map<String, Object> map : sb) {
	    	 map.put("parentId", map.get("parentid"));
			 map.remove("parentid"); 
		}
		   
		 return sb;
	     	  
	 }
	  
	  /**
	   * 跳转到设备详情页面
	   * @date 2017年9月14日 下午2:37:27
	   * @author 杨磊
	   * @param request
	   * @return
	   */
	  
	  @RequestMapping("/showEqInfo")
	  public String  showEqInfo (HttpServletRequest request,ModelMap map){
		  String eqId = request.getParameter("eqId");
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
		  
		  return JSP_PR+"/showEqInfo2";
	  }
	  /**
	   * 测试获得设备的树通过表 BIZ_T_SB_BASEINFO
	   * @date 2017年9月15日 上午9:51:57
	   * @author 杨磊
	   * @param request
	   * @return
	   */
	//  @RequestMapping("/getEquipmentTree1")
	  @ResponseBody
	  public List<Map<String, Object>> getEquipmentTree1(HttpServletRequest request){
		  Map<String,Object> params=new HashMap<String,Object>();
		  List<Map<String, Object>> eq = BizTSbBaseinfoService.queryByTreeId(params);
		 
		   for (Map<String, Object> map : eq) {
			map.put("parentId", map.get("parentid"));
			map.remove("parentid");
			
		}
		 
		
		  return eq;
	  }
	  /**
	   * 获取检查项
	   * @date 2017年9月15日 上午9:55:49
	   * @author 杨磊
	   * @param request
	   * @return
	   */
	
	  @ResponseBody
	  public List<Map<String,Object>> getCheck(HttpServletRequest request){
		 Map<String,Object> params=new HashMap<String,Object>(); 
	     String sbId = request.getParameter("sbId");
	     if(sbId==null){ 
	    	 sbId="ewew";
	     }
	     List<Map<String,Object>>  list= bizTGgCheckItemService.getCheckBySbId(sbId,params);
		  
		  return  list;
	  }
	  /**
	   * 添加设备和检查项信息
	   * @date 2017年9月17日 下午4:15:55
	   * @author 杨磊
	   * @param request
	   * @return
	   */
    
	  @RequestMapping("addUnit")
	  @ResponseBody
	  public  Map<String,Object> addUnit(HttpServletRequest request){
		   Map<String,Object> hashMap=new  HashMap<String,Object>();
		   String sbId = WebUtils.findParameterValue(request,"sbId");
		   String ckId =WebUtils.findParameterValue(request,"id");
		   BizTGgCheckItem ckEntity = bizTGgCheckItemService.findOne(BizTGgCheckItem.class, ckId);
    	   BizTSbBaseinfo sbEntity = BizTSbBaseinfoService.findOne(BizTSbBaseinfo.class, sbId);
    	   BizTGgSbssAttachBase attachBaseEntity=new BizTGgSbssAttachBase();
    	   String eqId = sbEntity.getSbId();//设备ID
    	   String itemId = ckEntity.getCheckItemId();//检查项ID;
    	   attachBaseEntity.setCode(eqId);
    	   attachBaseEntity.setSbOrSs("1");
    	   attachBaseEntity.setDetailId(itemId);
           SysUser user = SysUtils.getLoginSysUser(request);    
           attachBaseEntity.setCreatorAccount(user.getUserId());
           attachBaseEntity.setCreatorName(user.getUserName());
           attachBaseEntity.setBelongDept(user.getOrgId());
           Date date=new Date();
           String timestemp = DateUtil.dateToString(date, DateUtil.DATE_FULL_FORMAT_N);
           attachBaseEntity.setCreateTimestemp(timestemp);
           attachBaseEntity.setConfType("1");
           try {
        	    BizTGgSbssAttachBaseService.save(attachBaseEntity);
        	    hashMap.put("status", 1);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
           
    	   return hashMap;
		  // return JSP_PR+"/shouEqInfo2";
		    
	  }
	  /**
	   * 获取以及关联了的检查项
	   * @date 2017年9月17日 下午4:16:51
	   * @author 杨磊
	   * @param request
	   * @param map
	   * @return
	   */
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
	  /**
	   * 删除设备与检查项关联表通过检查项id
	   * @date 2017年9月15日 下午7:06:59
	   * @author 杨磊
	   * @param request
	   * @return
	   */
	  @RequestMapping("/delOne")
	  @ResponseBody
	  public Map<String,Object> delOne(HttpServletRequest request){
		  Map<String, Object> map=new HashMap<String,Object>();
		  String id = WebUtils.findParameterValue(request, "id");
		//  Boolean delBoolean = BizTGgSbssAttachBaseService.delete(BizTGgSbssAttachBase.class, id);
	      Boolean boolean1=BizTGgSbssAttachBaseService.deleteByCheckId(id);
		  if(boolean1){ 
			  map.put("status", 1);
			  return  map;
		  }else{
			  
			  map.put("status", 2);
			  return map;
		  }
	  }
	  
	  /**
	   * 获取未关联的检查项
	   * @date 2017年9月17日 下午4:17:20
	   * @author 杨磊
	   * @param request
	   * @param map
	   * @return
	   */
	  @SuppressWarnings("unchecked")
	  @RequestMapping("/getUnCheckList")
	  @ResponseBody
	  public Map<String,Object> getUnCheckList(HttpServletRequest  request,ModelMap map){ 
		    String code = WebUtils.findParameterValue(request, "sbId");
		    String checkCode = WebUtils.findParameterValue(request, "code");
		   // String checkName = WebUtils.findParameterValue(request, "name");
		   // System.out.println(checkCode+"codecoe");
		    PageBean bean = new PageBean(request);
			Map<String, Object> par = new HashMap<String, Object>();
			par.put("code", code);
			par.put("checkCode", checkCode);

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
	  
	/**
	 * 把设备id传递到弹窗
	 * @date 2017年9月17日 下午4:17:55
	 * @author 杨磊
	 * @param request
	 * @param map
	 * @return
	 */
	  @RequestMapping("/toAddCheck")
	  public String toAddCheck(HttpServletRequest request,ModelMap map){
		 String code = WebUtils.findParameterValue(request, "sbId"); 
		 Map<String, Object> hashMap=new HashMap<String, Object>();
		 hashMap.put("code",code);
		 map.addAttribute("code",hashMap);
		 return JSP_PR+"/add";
		  
	  }

}

