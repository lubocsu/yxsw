package com.upsoft.yxsw.controller;


import java.util.ArrayList;
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

import com.upsoft.login.controller.BaseController;
import com.upsoft.login.support.webservice.ServiceReceiver;
import com.upsoft.login.support.webservice.SysUtils;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.bean.ResultBean;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.util.DateUtil;
import com.upsoft.yxsw.constant.DictConstant;
import com.upsoft.yxsw.entity.BizTGgSbssAttachBase;
import com.upsoft.yxsw.entity.BizTGgWarningManageEntity;
import com.upsoft.yxsw.entity.BizTSsBaseinfo;
import com.upsoft.yxsw.service.BizTGgCheckItemService;
import com.upsoft.yxsw.service.BizTGgSbssAttachBaseService;
import com.upsoft.yxsw.service.BizTGgWarningManageService;
import com.upsoft.yxsw.service.BizTSbBaseinfoService;
import com.upsoft.yxsw.service.BizTSsBaseinfoService;
import com.upsoft.yxsw.service.BizTXjdItemService;
/**
 * 
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：SsSafeController.java<br>
* 摘要 设施绑定安全定义<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：杨磊<br>
* 完成日期：2017年9月19日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：杨磊<br>
* 完成日期：2017年9月19日<br>
 */
@Controller
@RequestMapping(SsSafeController.FORWARD_PREFIX)
public class SsSafeController extends BaseController {

	protected static final String  FORWARD_PREFIX="/SsSafe";
	protected static final String  JSP_PR="/WEB-INF/jsp/SsSafe";
	
    private static final String ORG_TYPE="3";
	protected Logger logger=Logger.getLogger(SsSafeController.class);
	
	@Autowired
    private  BizTSbBaseinfoService  bizTSbBaseinfoService;//设备信息
	@Autowired
	private  BizTGgSbssAttachBaseService   bizTGgSbssAttachBaseService ;//设备信息与检查项的关系
	@Autowired
	private  BizTGgWarningManageService bizTGgWarningManageService  ;//安全警告
	@Autowired
	private BizTGgCheckItemService  bizTGgCheckItemService;//检查项
	@Autowired
	private BizTSsBaseinfoService  bizTSsBaseinfoService ;//设施
	@Autowired
	public BizTXjdItemService bizTXjdItemService;
	/**
	 * 初始化登录main.jsp
	 * @date 2017年9月19日 下午1:46:28
	 * @author 杨磊
	 * @return
	 */
	@RequestMapping("/init")
	public String init() {

		return JSP_PR + "/main";
	}

	@RequestMapping("/getTree")
	@ResponseBody
	public List<Map<String, Object>> getSSTree(HttpServletRequest request) {
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		String csOrgId = "";
		String csOrgTypeId ="" ;
		
		String csOrgIdSelect = WebUtils.findParameterValue(request,"csOsrg");
		String value = WebUtils.findParameterValue(request, "value");
		if(null!=value&&StringUtils.isNotBlank(value)){
			csOrgId=value;
			csOrgTypeId="3";
		}else{
			csOrgId = loginInfo.getCsOrgId();
			csOrgTypeId = loginInfo.getCsOrgTypeId();
			
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("csOrgIdSelect", csOrgIdSelect);
		param.put("value", value);
		param.put("csOrgId", csOrgId);
		List<Map<String, Object>> ss = bizTSsBaseinfoService.getTree1(csOrgId, csOrgTypeId, param);
		for (Map<String, Object> map : ss) {
			if (map.get("has_attach").toString().equals("1")) {// 通过判断 has_attach来渲染颜色												
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
		/*List<Map<String, Object>> orgList = bizTXjdItemService.getOrgList(loginInfo);
		for (Map<String, Object> org : orgList) {
			org.put("flag", "noClick");
			
			ss.add(org);
		
		}*/
		return ss;
	}

	@RequestMapping("/showDetail")
	public String showDetail(@RequestParam(required = true) String ssId, String parentName, ModelMap map,HttpServletRequest request) {
		BizTSsBaseinfo entity = bizTSsBaseinfoService.findOne(BizTSsBaseinfo.class, ssId);
		String layer = entity.getLayer();
		// 查询层级关系
		Map<String, Object> layerNames = bizTSsBaseinfoService.getSSLayer(layer);
		map.put("parentName", parentName);
		map.put("layerName", layerNames.get("layername"));
		map.put("ssItem", entity);
		map.put("byzdName", ServiceReceiver.getDictValue1(DictConstant.CHECKITEM_SFMR.getValue(), entity.getByzd()).get(entity.getByzd()));
		WSLoginInfoBean sysUser = SysUtils.getLoginInfo(request);
		String typeId = sysUser.getCsOrgTypeId();
		map.put("typeId", typeId);
		return JSP_PR + "/detail";
	}
	    
	@RequestMapping("/toAddCheck")
	public String toAddCheck(HttpServletRequest request, ModelMap map) {
		String code = WebUtils.findParameterValue(request, "ssId");
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("code", code);
		map.addAttribute("code", hashMap);
		return JSP_PR + "/add";

	}
  /**
   * 获取为关联的
   * @date 2017年9月21日 下午1:36:36
   * @author 杨磊
   * @param request
   * @param map
   * @return
   */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getUnCheckList")
	@ResponseBody
	public Map<String, Object> getUnCheckList(HttpServletRequest request, ModelMap map) {
		String code = WebUtils.findParameterValue(request, "ssId");
		String checkCode = WebUtils.findParameterValue(request, "code");
		WSLoginInfoBean user = SysUtils.getLoginInfo(request);
		String csOrgId = user.getCsOrgId();
		PageBean bean = new PageBean(request);
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("code", code);
		par.put("checkCode", checkCode);
		par.put("csOrgId", csOrgId);
		Map<String, Object> warningListAndCount = bizTGgSbssAttachBaseService.getNoSqfeListAndCount(par, bean);
		List<Map<String, Object>> list = (List<Map<String, Object>>) warningListAndCount.get("rows");
		long count = (Long) warningListAndCount.get(PageBean.TOTAL);

		Map<String, Object> resultData = new HashMap<String, Object>();

		resultData.put("pager.pageNo", 1);
		resultData.put("pager.pageSize", 10);
		resultData.put("pager.totalRows", count);
		resultData.put("rows", list);
		return resultData;
	}
	  /**
	   * 添加
	   * @date 2017年9月21日 下午1:37:04
	   * @author 杨磊
	   * @param request
	   * @return
	   */
	@RequestMapping("addUnit")
	@ResponseBody
	public Map<String, Object> addUnit(HttpServletRequest request) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		String ssId = WebUtils.findParameterValue(request, "ssId");
		String ckIds = WebUtils.findParameterValue(request, "ids");
		String[] splits = ckIds.split(",");
		for (String ckId : splits) {
			
			try {

				BizTGgWarningManageEntity safeEntity = bizTGgWarningManageService.findOne(BizTGgWarningManageEntity.class, ckId);

				BizTSsBaseinfo ssEntity = bizTSsBaseinfoService.findOne(BizTSsBaseinfo.class, ssId);

				BizTGgSbssAttachBase attachBaseEntity = new BizTGgSbssAttachBase();

				String ssId2 = ssEntity.getSsId();
				String itemId = safeEntity.getWarningId();// ID;

				attachBaseEntity.setCode(ssId2);
				attachBaseEntity.setSbOrSs("2");
				
				attachBaseEntity.setDetailId(itemId);
				SysUser user = SysUtils.getLoginSysUser(request);

				attachBaseEntity.setCreatorAccount(user.getUserId());
				attachBaseEntity.setCreatorName(user.getUserName());
				attachBaseEntity.setBelongDept(user.getOrgId());
				Date date = new Date();
				String timestemp = DateUtil.dateToString(date, DateUtil.DATE_FULL_FORMAT_N);
				attachBaseEntity.setCreateTimestemp(timestemp);
				attachBaseEntity.setConfType("2");

				bizTGgSbssAttachBaseService.save(attachBaseEntity);
				hashMap.put("status", 1);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}

		return hashMap;
	}
	    
	@SuppressWarnings("unchecked")
	@RequestMapping("/getList")
	@ResponseBody
	public Map<String, Object> getWarningList(HttpServletRequest request, ModelMap map) {

		String code = WebUtils.findParameterValue(request, "ssId");
		PageBean bean = new PageBean(request);
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("code", code);

		Map<String, Object> warningListAndCount = bizTGgSbssAttachBaseService.getSsSafeListAndCount(par, bean);
		List<Map<String, Object>> list = (List<Map<String, Object>>) warningListAndCount.get("rows");
		long count = (Long) warningListAndCount.get(PageBean.TOTAL);

		Map<String, Object> resultData = new HashMap<String, Object>();

		resultData.put("pager.pageNo", 1);
		resultData.put("pager.pageSize", 20);
		resultData.put("pager.totalRows", count);
		resultData.put("rows", list);
		return resultData;
	}

	@RequestMapping("/delOne")
	@ResponseBody
	public ResultBean delOne(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String ids = WebUtils.findParameterValue(request, "ids");
		String ssId = WebUtils.findParameterValue(request, "ssId");
		ResultBean bean = new ResultBean();
		try {
			bizTGgSbssAttachBaseService.deleteALL(ids,ssId);
			bean.setMessage("删除成功");
		} catch (Exception e) {
			bean.setMessage("删除失败");
			bean.setFlag(false);
			logger.error(e.getMessage());
		}
		return bean;
	}
	
	@RequestMapping("/getSupportData")
	@ResponseBody
	public List<Map<String, Object>> getSelectSupport(HttpServletRequest request){
		//获取用户信息
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		List<Map<String, Object>> org = ServiceReceiver.iterateOrgById(loginInfo.getCsOrgId());
		List<Map<String, Object>> orgs = new ArrayList<Map<String,Object>>();
		for (Map<String, Object> map : org) {
			String  orgTypeId = map.get("orgtypeid").toString();
		
				if(ORG_TYPE.equals(orgTypeId)){
				Map<String,Object> orgMap = new HashMap<String, Object>();
				orgMap.put("key", map.get("orgid"));
				orgMap.put("value", map.get("orgname"));
				orgs.add(orgMap);
			}
		}
		return orgs;
	}
	
}
