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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.google.gson.Gson;
import com.upsoft.login.controller.BaseController;
import com.upsoft.login.support.webservice.SysUtils;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.yxsw.dao.EquipmentTypeDao;
import com.upsoft.yxsw.entity.BizTSbTypesEntity;
import com.upsoft.yxsw.service.BizTSbBaseinfoService;
import com.upsoft.yxsw.service.EquipmentTypeService;
@Controller
@RequestMapping(ReSbTjController.FORWARD_PREFIX)
public class ReSbTjController extends BaseController {
	protected static final String FORWARD_PREFIX = "/sbtj";
	protected static final String JSP_PR = "/WEB-INF/jsp/sbtj";
	
    private Logger logger=Logger.getLogger(SbSafeController.class);
    @Autowired
    private BizTSbBaseinfoService bizTSbBaseinfoService;
	@Autowired
	private EquipmentTypeDao equipmentTypeDao;

	
	@RequestMapping("/init")
	public String getNeedDate(HttpServletRequest request,ModelMap map){
		String csOrgId="";
		//表单提交时候的类型id
		String sbTypeId = WebUtils.findParameterValue(request, "sbTypeId");
		String sbStatus = WebUtils.findParameterValue(request, "sbStatus");
		String csOrgId1 = WebUtils.findParameterValue(request, "csOrgId");
		String backUrl = WebUtils.findParameterValue(request, "backUrl");
		//查看设备类型详情的类型id
		String idNew = WebUtils.findParameterValue(request, "id");
		WSLoginInfoBean user = SysUtils.getLoginInfo(request);
	    String csOrgId2 = user.getCsOrgId();
		if(null!=csOrgId1&&StringUtils.isNotBlank(csOrgId1)){
			csOrgId =csOrgId1 ;
        
		} else{
			csOrgId =csOrgId2 ;
		}
		List<Map<String, Object>> sbList = bizTSbBaseinfoService.getdate(
				csOrgId, sbTypeId, sbStatus,idNew);
	    
		List<String> csNameList=new ArrayList<String>();
//		csNameList.add(StringUtils.EMPTY);
		List<String> sbTypeList = new ArrayList<String>();
		List<String> sbTypeIdList = new ArrayList<String>();//
		Map<String,Object> tmpDataMap = new HashMap<String,Object>();
		for (Map<String, Object> sb : sbList) {
			String wcsName = sb.get("belong_wsc_name").toString();
			if(!csNameList.contains(wcsName)){
				csNameList.add(wcsName);
			}
			String sbType = sb.get("typename").toString();
			if(!sbTypeList.contains(sbType)){
				sbTypeList.add(sbType);
			}
			String sbTypeIdNeed = sb.get("typeid").toString();//
			if(!sbTypeIdList.contains(sbTypeIdNeed)){
				sbTypeIdList.add(sbTypeIdNeed);
			}//
			tmpDataMap.put(wcsName+sbType, sb.get("count"));
		}
		
		List<List<Object>> data = new ArrayList<List<Object>>();
				
		for (String sbType : sbTypeList) {
			List<Object> tmp = new ArrayList<Object>();
			tmp.add(sbType);
			for (String csName : csNameList) {
				if(StringUtils.equals(csName, StringUtils.EMPTY)){
					continue;
				}
				Object count = tmpDataMap.get(csName+sbType);
				if(null==count){
					count = 0;
				}
				tmp.add(count);
			}
			data.add(tmp);
		}
		
		map.put("csOrgId",csOrgId);
//		map.put("csNameList", csNameList);
//		map.put("data", data);
		map.put("csNameList", new Gson().toJson(csNameList));
		map.put("data", new Gson().toJson(data));
		map.put("sbTypeIdList", new Gson().toJson(sbTypeIdList));
		//参数回显
		map.put("sbTypeId",sbTypeId );
		map.put("sbStatus", sbStatus);
		map.put("csOrgId", csOrgId);
		map.put("backUrl",backUrl);
		//判断没有子节点的不显示a标签
		List<String> listA=bizTSbBaseinfoService.getRenderList(csOrgId, sbTypeId, sbStatus,idNew);
	    map.put("listA",new Gson().toJson(listA));
		return JSP_PR + "/main";
	}
	
    /**
     * 验证是否点击
     * @date 2017年10月25日 下午5:31:09
     * @author 杨磊
     * @param request
     * @param map
     * @return
     */
	@RequestMapping("/validate")
	@ResponseBody
  public Map<String,Object> validate(HttpServletRequest request,ModelMap map){
		String csOrgId="";
		//表单提交时候的类型id
		String sbTypeId = WebUtils.findParameterValue(request, "sbTypeId");
		String sbStatus = WebUtils.findParameterValue(request, "sbStatus");
		String csOrgId1 = WebUtils.findParameterValue(request, "csOrgId");
		//查看设备类型详情的类型id
		String idNew = WebUtils.findParameterValue(request, "id");
		WSLoginInfoBean user = SysUtils.getLoginInfo(request);
	    String csOrgId2 = user.getCsOrgId();
		if(null!=csOrgId1&&StringUtils.isNotBlank(csOrgId1)){
			csOrgId =csOrgId1 ;
        
		} else{
			csOrgId =csOrgId2 ;
		}
		List<Map<String, Object>> sbList = bizTSbBaseinfoService.getdate(
				csOrgId, sbTypeId, sbStatus,idNew);
		Map<String, Object>  hashMap=new HashMap<String,Object>();
		if(null!=sbList&&sbList.size()>0){
			
			hashMap.put("status", "1");
		}else{
			
			hashMap.put("status", "0");
		}
		
		return hashMap;
}
}
