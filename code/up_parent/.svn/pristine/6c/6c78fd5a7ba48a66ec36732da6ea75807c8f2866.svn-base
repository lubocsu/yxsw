package com.upsoft.yxsw.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
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
import com.upsoft.system.bean.ResultBean;
import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.constant.CommonConstant;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.util.DateUtil;
import com.upsoft.yxsw.constant.Constant;
import com.upsoft.yxsw.constant.DictConstant;
import com.upsoft.yxsw.entity.BizTSsBaseinfo;
import com.upsoft.yxsw.service.BizTSsBaseinfoService;

/**
* Copyright (c) 2017,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：BizTSsBaseinfoController.java<br>
* 摘要：设施信息管理<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：胡毅<br>
* 完成日期：2017年9月13日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：胡毅<br>
* 完成日期：2017年9月13日<br>
 */
@Controller
@RequestMapping("/ssbase")
public class BizTSsBaseinfoController  {
	private static final String JSP_PREFIX = "/WEB-INF/jsp/ssbase";
	
	private Logger logger = Logger.getLogger(BizTSsBaseinfoController.class);
	
	@Autowired
	public BizTSsBaseinfoService bizTSsBaseinfoService;
	
	@RequestMapping("/init")
	public String init(ModelMap map,HttpServletRequest request){
		map.addAttribute("DELFLAG", Constant.YES_NO.YES.getValue());
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		if(Integer.valueOf(loginInfo.getCsOrgTypeId()) < Integer.valueOf(CommonConstant.orgType.FACTORY.getKey()) ){
			map.addAttribute("onlyQuery", true);
		}else{
			map.addAttribute("onlyQuery", false);
		}
		return JSP_PREFIX + "/main";
	}
	
	@RequestMapping("/getSSTree")
	@ResponseBody
	public List<Map<String, Object>> getSSTree(HttpServletRequest request) {
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		List<Map<String, Object>> types = bizTSsBaseinfoService.getTree(loginInfo.getCsOrgId(),loginInfo.getCsOrgTypeId());
		for (Map<String, Object> map : types) {
			map.put("parentId", map.get("parentid"));
			map.remove("parentid");
		}
		return types;
	}
	
	@RequestMapping("/toAdd")
	public String toAdd(String ssId,String ssName,ModelMap mod){
		mod.addAttribute("ssId", ssId);
		mod.addAttribute("ssName", ssName);
		mod.addAttribute("DELFLAG", Constant.YES_NO.YES.getValue());
		mod.addAttribute("DELFLAG_DICT", DictConstant.CHECKITEM_SFMR.getValue());
		return JSP_PREFIX+"/add";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/doAdd")
	@ResponseBody
	public ResultBean doAdd(BizTSsBaseinfo entity,HttpServletRequest request){
		String function = WebUtils.findParameterValue(request,"_function");
		ResultBean result = new ResultBean();
		try {
			// 设置序号
			int sort = bizTSsBaseinfoService.getMaxNum();
			entity.setSort(++sort);
			// 设置层级管理
			if(StringUtils.isNotBlank(entity.getParentId())){
				BizTSsBaseinfo parentSS = bizTSsBaseinfoService.findOne(BizTSsBaseinfo.class, entity.getParentId());
				entity.setLayer(parentSS.getLayer());
			}
			entity.setFunction(function);
			entity.setDelFlag(Integer.valueOf(Constant.YES_NO.NO.getValue()));
			WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
			SysUser user = loginInfo.getUser();
			String timestemp = DateUtil.dateToString(new Date(), DateUtil.DATE_FULL_FORMAT_N);
			entity.setCreateTimestemp(timestemp);
			entity.setCreatorAccount(user.getUserId());
			entity.setCreatorName(user.getUserName());
			entity.setBelongWscId(loginInfo.getCsOrgId());
			entity.setBelongWscName(loginInfo.getCsOrgName());
			entity.setUpdateTimestemp(timestemp);
			entity.setUpdatorAccount(user.getUserId());
			entity.setUpdatorName(user.getUserName());
			BizTSsBaseinfo saved1 = bizTSsBaseinfoService.save(entity);
			if(null!=saved1){
				if(StringUtils.isBlank(saved1.getLayer())){
					saved1.setLayer(StringUtils.EMPTY);
				}else{
					saved1.setLayer(saved1.getLayer()+".");
				}
				saved1.setLayer(saved1.getLayer()+saved1.getSsId());
				BizTSsBaseinfo saved2 = bizTSsBaseinfoService.update(saved1);
				if(null!=saved2){
					if(StringUtils.isBlank(saved2.getParentId())){
						saved2.setParentId(loginInfo.getCsOrgId());
					}
					result.setData(BeanUtils.describe(saved2));
					result.setMessage("新增设施成功");
				}else{
					result.setFlag(false);
					result.setMessage("新增设施时层级关系保存失败");
				}
			}else{
				result.setFlag(false);
				result.setMessage("新增设施失败");
			}
		} catch (Exception e) {
			result.setFlag(false);
			result.setMessage("新增设施时抛出了异常"+e.getMessage());
			logger.error("新增设施时抛出了异常"+e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("/validateSSCode")
	@ResponseBody
	public String validateSSCode(@RequestParam(value="validateValue")String ssCode,String old){
		long count = bizTSsBaseinfoService.validateSSCode(ssCode);
		
		if(count>0){
			return "{\"validateResult\":{\"valid\":false}}";
		}else{
			return "{\"validateResult\":{\"valid\":true}}";
		}
	}
	
	@RequestMapping("/showDetail")
	public String showDetail(@RequestParam(required = true)String ssId,String parentName ,ModelMap map){
		BizTSsBaseinfo entity = bizTSsBaseinfoService.findOne(BizTSsBaseinfo.class,ssId);
		String layer = entity.getLayer();
		//查询层级关系
		Map<String,Object> layerNames = bizTSsBaseinfoService.getSSLayer(layer);
		map.put("parentName", parentName);
		map.put("layerName",layerNames.get("layername"));
		map.put("ssItem", entity);
		map.put("byzdName", ServiceReceiver.getDictValue1(DictConstant.CHECKITEM_SFMR.getValue(), entity.getByzd()).get(entity.getByzd()));
		return JSP_PREFIX+"/detail";
	}
	
	/**
	 *  设施删除时为逻辑删除，删除时如果设施还有子级则则同时删除自己设施和关联关系
	 * @date 2017年9月14日 下午8:14:41
	 * @author 胡毅
	 * @return
	 */
	@RequestMapping("/del")
	@ResponseBody
	public ResultBean del(@RequestParam(required = true)String ssId){
		ResultBean result = new ResultBean();
		try {
			boolean flag = bizTSsBaseinfoService.updateSSAndDelRelationSb(ssId);
			if(flag){
				Map<String,Object> data = new HashMap<String,Object>();
				data.put("ssId",ssId);
				result.setMessage("删除成功");
				result.setData(data);
			}else{
				result.setFlag(false);
				result.setMessage("删除失败");
			}
		} catch (Exception e) {
			result.setFlag(false);
			result.setMessage("设施删除出现异常："+e.getMessage());
		}
		return result;
		
	}
	
	/**
	 * 验证设施下是否挂接有设备
	 * @date 2017年9月14日 下午8:24:27
	 * @author 胡毅
	 * @param ssId
	 * @return
	 */
	@RequestMapping("/validateSbUnderSs")
	@ResponseBody
	public ResultBean validateSbUnderSs(@RequestParam(required = true)String ssId){
		ResultBean result = new ResultBean();
		long count = bizTSsBaseinfoService.validateSbUnderSs(ssId);
		if(count>0){
			result.setFlag(false);
		}else{
			result.setFlag(true);
		}
		return result;
	}
	
	/**
	 * 修改设施
	 * @date 2017年9月15日 下午5:07:25
	 * @author 胡毅
	 * @param ssId
	 * @return
	 */
	@RequestMapping("/toModify")
	public String toModify(@RequestParam(required = true)String ssId,String parentName,ModelMap mod){
		BizTSsBaseinfo ss =  bizTSsBaseinfoService.findOne(BizTSsBaseinfo.class, ssId);
		mod.addAttribute("ss",ss);
		mod.addAttribute("parentName", parentName);
		mod.addAttribute("DELFLAG_DICT", DictConstant.CHECKITEM_SFMR.getValue());
		return JSP_PREFIX+"/modify";
	}
	
	@RequestMapping("/doModify")
	@ResponseBody
	public ResultBean doModify(BizTSsBaseinfo entity,HttpServletRequest request){
		ResultBean result = new ResultBean();
		BizTSsBaseinfo parentSS = bizTSsBaseinfoService.findOne(BizTSsBaseinfo.class, entity.getParentId());
		BizTSsBaseinfo old = bizTSsBaseinfoService.findOne(BizTSsBaseinfo.class, entity.getSsId());
		String function = WebUtils.findParameterValue(request,"_function");
		old.setParentId(entity.getParentId());
		old.setFunction(function);
		if(null==parentSS){
			old.setLayer(entity.getSsId());
		}else{
			old.setLayer(parentSS.getLayer()+"."+entity.getSsId());
		}
		old.setByzd(entity.getByzd());
		old.setRemark(entity.getRemark());
		old.setCode(entity.getCode());
		old.setName(entity.getName());
		WSLoginInfoBean loginInfo = SysUtils.getLoginInfo(request);
		old.setBelongWscId(loginInfo.getCsOrgId());
		old.setBelongWscName(loginInfo.getCsOrgName());
		old.setUpdateTimestemp(DateUtil.dateToString(new Date(), DateUtil.DATE_FULL_FORMAT_N));
		old.setUpdatorAccount(loginInfo.getUser().getUserId());
		old.setUpdatorName(loginInfo.getUser().getUserName());
		try {
			bizTSsBaseinfoService.update(old);
			result.setMessage("修改设施成功");
		} catch (Exception e) {
			result.setFlag(false);
			result.setMessage("修改设施时出现异常："+e.getMessage());
			logger.error("修改设施时出现异常："+e.getMessage());
		}
		
		return result;
	}
}
