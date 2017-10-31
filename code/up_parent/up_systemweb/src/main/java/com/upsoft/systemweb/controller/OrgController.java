package com.upsoft.systemweb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.upsoft.login.controller.BaseController;
import com.upsoft.login.support.webservice.SysUtils;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.constant.DictConstant;
import com.upsoft.system.constant.SystemLogConstant;
import com.upsoft.system.entity.DictTreeDataEntity;
import com.upsoft.system.entity.SysOrgEntity;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.support.annotation.LogAnnotation;
import com.upsoft.systemweb.constant.SystemWebConstant;
import com.upsoft.systemweb.dao.IOrgDao;
import com.upsoft.systemweb.service.DictTreeDataService;
import com.upsoft.systemweb.service.OrgService;
import com.upsoft.systemweb.service.UserService;
import com.upsoft.systemweb.support.SystemLogServiceCache;

/**
 * 
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 * 
 * 文件名称：OrgController.java<br>
 * 摘要：简要描述本文件的内容<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：胡毅<br>
 * 完成日期：2015年1月21日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：胡毅<br>
 * 完成日期：2015年1月21日<br>
 */
@Controller
@RequestMapping(value = OrgController.FORWARD_PR)
public class OrgController extends BaseController {

	protected static final String JSP_PR = "/WEB-INF/jsp/org";
	protected static final String FORWARD_PR = "/org";
	protected static final String REDIRECT_PR = "redirect:" + FORWARD_PR;

	@Autowired
	private OrgService orgService;

	@Autowired
	private UserService userService;

	@Autowired
	private DictTreeDataService dictTreeDataService;

	@Autowired
	private IOrgDao orgDao;

	@Autowired
	private SystemLogServiceCache systemLogServiceCache;

	/**
	 * 跳转到机构管理页面
	 * 
	 * @date 2015年1月22日 上午10:12:53
	 * @author 胡毅
	 * @return
	 */
	@LogAnnotation(FunctionName = "机构管理功能")
	@RequestMapping(value = "/init")
	public String toManageOrgPage(HttpServletRequest request, ModelMap map) {
		super.findMenuResourcePermission(request, map);
		// 添加日志
		String optContent = "查询系统组织机构";
		SysUser user = SysUtils.getLoginSysUser(request);
		systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(),
				Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_JGGL,
				SystemLogConstant.OPT_MODEL_TYPE_NAME_JGGL, SystemLogConstant.OPT_TYPE_CODE_QUERYINFO, SystemLogConstant.OPT_TYPE_NAME_QUERYINFO,
				optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
		return JSP_PR + "/main";
	}

	/**
	 * 获取组织机构树
	 * 
	 * @date 2015年1月22日 上午10:14:03
	 * @author 胡毅
	 * @return
	 */
	@RequestMapping(value = "/getOrgTree")
	@ResponseBody
	public List<Map<String, Object>> getOrgTree(HttpServletRequest request, String path) {
		Map<String, Object> pars = new HashMap<String, Object>();
		pars.put("parentOrgId", "0");
		List<SysOrgEntity> datas = orgService.querySysOrgList(0, 0, pars);
		// 组织JSON格式数据
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> item = null;
		for (SysOrgEntity sysOrg : datas) {
			item = new HashMap<String, Object>();
			item.put("id", sysOrg.getOrgId());
			item.put("name", sysOrg.getOrgName());
			item.put("parentId", sysOrg.getParentOrgId());
			item.put("orgTypeId", sysOrg.getOrgTypeId());
			if ("0".equals(sysOrg.getParentOrgId())) {
				item.put("icon", "../image/company.png");
			} else {
				item.put("icon", "../image/dept.png");
			}
			if (sysOrg.getEnabled() == 0) {
				Map<String, Object> color = new HashMap<String, Object>();
				color.put("color", "red");
				item.put("font", color);// font:{'color':'red'}
			}
			item.put("orgCode", sysOrg.getOrgCode());
			result.add(item);
		}
		return result;
	}

	/**
	 * 查询机构用户树
	 * 
	 * @date 2017年8月9日 下午5:17:09
	 * @author 胡毅
	 * @param request
	 * @param path
	 * @return
	 */
	@RequestMapping(value = "/getOrgUserTree")
	@ResponseBody
	public Map<String, Object> getOrgUserTree(HttpServletRequest request, String path) {
		Map<String, Object> pars = new HashMap<String, Object>();
		List<Map<String, Object>> datas = userService.findOrgUserTree(pars);
		// 组织JSON格式数据
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("treeNodes", datas);
		return result;
	}

	/**
	 * 获取Grid数据，用以列表展示机构
	 * 
	 * @date 2015年1月22日 下午4:42:49
	 * @author 胡毅
	 * @return
	 */
	@RequestMapping(value = "/getGridOrgData")
	@ResponseBody
	public Map<String, Object> getGridOrgData(HttpServletRequest request, SysOrgEntity org) {
		PageBean pb = new PageBean(request);
		Map<String, Object> pars = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(org.getParentOrgId())) {
			pars.put("parentOrgId", org.getParentOrgId());
		}
		if (StringUtils.isNotBlank(org.getOrgName())) {
			pars.put("orgName", org.getOrgName());
		}
		// pars.put("pageBean", pb);

		// 公用分页排序方法
		return orgService.queryPagination(pb, pars);

	}

	/**
	 * 验证组织机构编码的唯一性
	 * 
	 * @date 2015年1月23日 下午5:04:07
	 * @author 胡毅
	 * @return
	 */
	@RequestMapping(value = "/checkOrgCode")
	@ResponseBody
	public Map<String, Object> checkOrgCode(HttpServletRequest request, String validateValue) {
		// 返回提示信息{"validateResult":{"valid":true}}
		// 如果是更新机构，不检测自身编码
		String orgId = WebUtils.findParameterValue(request, "orgId");
		Map<String, Object> validateResult = new HashMap<String, Object>();
		Map<String, Object> valid = new HashMap<String, Object>();
		validateResult.put("validateResult", valid);
		if (StringUtils.isNotBlank(validateValue)) {
			long count = orgService.checkOrgCode(validateValue, orgId);
			if (count > 0) {
				valid.put("valid", false);
			} else {
				valid.put("valid", true);
			}
			return validateResult;
		} else {
			valid.put("valid", true);
			return validateResult;
		}
	}

	@RequestMapping(value = "/toAddOrg")
	public String addOrg(HttpServletRequest request, ModelMap map) {
		super.putDictConstant(map);
		String parentOrgId = request.getParameter("parentOrgId");
		Map<String, Object> pars = new HashMap<String, Object>();
		pars.put("parentorgid", parentOrgId);
		List<Map<String, Object>> no = orgDao
				.queryListBySql("SELECT MAX(to_number(t.orderno))+1 as maxOrderNo from sys_org t where t.parentorgid =:parentorgid", 0, 1, pars);
		List<Map<String, Object>> orgtypeid = orgDao.queryListBySql("SELECT t.orgTypeID from sys_org t where t.orgid =:parentorgid", 0, 1, pars);
		map.put("defaultParentOrgId", parentOrgId);
		// 当parentOrgId=0，orgtypeid将为空，说明未选父级机构，此时设置默认对比机构类为0（对比类型见机构类型枚举）
		map.put("defaultOrgTypeId", orgtypeid.size() == 0 ? 0 : orgtypeid.get(0).get("orgtypeid"));
		map.put("default_orgtype_dept", SystemWebConstant.orgType.DEPT.getKey());
		map.put("maxOrderNo", no.get(0));
		return JSP_PR + "/addOrg";
	}

	/**
	 * 新增一个组织机构
	 * 
	 * @date 2015年1月23日 下午2:09:55
	 * @author 胡毅
	 * @param orgBean
	 * @return
	 */
	@RequestMapping(value = "/doAddOrg")
	@ResponseBody
	public Map<String, Object> doAddOrg(HttpServletRequest request, SysOrgEntity org) {
		String path = WebUtils.findParameterValue(request, "path");
		// 添加日志
		String optContent = "新增名称为" + org.getOrgName() + "的组织机构";
		SysUser user = SysUtils.getLoginSysUser(request);
		systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(),
				Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_JGGL,
				SystemLogConstant.OPT_MODEL_TYPE_NAME_JGGL, SystemLogConstant.OPT_TYPE_CODE_ADDINFO, SystemLogConstant.OPT_TYPE_NAME_ADDINFO,
				optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);

		return saveOneOrg(org, "add", path);
	}

	private Map<String, Object> saveOneOrg(SysOrgEntity sysOrg, String tip, String path) {
		// 返回信息{message: "修改用户成功"}
		Map<String, Object> msg = new HashMap<String, Object>();
		if (StringUtils.isBlank(sysOrg.getParentOrgId())) {
			sysOrg.setParentOrgId("0");
		}
		// 设置空字符串拼音码
		sysOrg.setShortSpelling("No_Spell");
		SysOrgEntity ss = null;
		// 将执行的具体操作转化成中文显示
		String doWhat = "";
		if ("add".equals(tip)) {
			// if("0".equals(sysOrg.getParentOrgId())){
			// item.put("icon", "../image/company.png");
			// }else{
			// item.put("icon", "../image/dept.png");
			// }
			ss = orgService.save2(sysOrg);
			doWhat = "新增";
		}
		if ("modify".equals(tip)) {
			ss = orgService.update2(sysOrg);
			doWhat = "修改";
		}
		if (ss != null) {
			msg.put("message", doWhat + "机构成功");
			// 组织tree节点数据
			Map<String, Object> newNode = new HashMap<String, Object>();
			newNode.put("id", sysOrg.getOrgId());
			newNode.put("name", sysOrg.getOrgName());
			newNode.put("parentId", sysOrg.getParentOrgId());
			if ("0".equals(sysOrg.getParentOrgId())) {
				newNode.put("icon", "../image/company.png");
			} else {
				newNode.put("icon", "../image/dept.png");
			}
			newNode.put("orgCode", sysOrg.getOrgCode());
			msg.put("newNode", newNode);
			return msg;
		} else {
			msg.put("message", doWhat + "机构失败");
			return msg;
		}
	}

	/**
	 * 展示一条组织机构数据
	 * 
	 * @date 2015年1月23日 下午6:25:24
	 * @author 胡毅
	 * @param orgId
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/findOrg")
	public String findOrg(String orgId, ModelMap map) {
		return findOneOrg(orgId, map, "/viewOrg");
	}

	@RequestMapping(value = "/toModifyOrg")
	public String modifyOrg(String orgId, ModelMap map) {
		super.putDictConstant(map);
		map.put("orgId", orgId);
		return findOneOrg(orgId, map, "/modifyOrg");
	}

	/**
	 * 更新一条组织机构数据
	 * 
	 * @date 2015年1月23日 下午7:44:25
	 * @author 胡毅
	 * @param orgId
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "doModifyOrg")
	@ResponseBody
	public Map<String, Object> doModifyOrg(SysOrgEntity org, HttpServletRequest request) {
		// 添加日志
		String optContent = "修改名称为" + org.getOrgName() + "的组织机构";
		SysUser user = SysUtils.getLoginSysUser(request);
		systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(),
				Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_JGGL,
				SystemLogConstant.OPT_MODEL_TYPE_NAME_JGGL, SystemLogConstant.OPT_TYPE_CODE_EDITINFO, SystemLogConstant.OPT_TYPE_NAME_EDITINFO,
				optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
		return saveOneOrg(org, "modify", null);
	}

	private String findOneOrg(String orgId, ModelMap map, String returnStr) {
		SysOrgEntity s = orgService.findOne(SysOrgEntity.class, orgId);
		if (StringUtils.isNotBlank(s.getParentOrgId()) && !"0".equals(s.getParentOrgId())) {
			SysOrgEntity parentOrg = orgService.findOne(SysOrgEntity.class, s.getParentOrgId());
			map.put("parentName", parentOrg.getOrgName());
			// 若该机构父级机构不为空，这在修改时去掉父级机构编码前缀 by hy 2017.10.23
			if(returnStr.indexOf("modifyOrg")>0){
				s.setOrgCode(s.getOrgCode().replace(parentOrg.getOrgCode()+"_",StringUtils.EMPTY));
			}
		} else {
			map.put("parentName", "顶级机构");
		}
		map.put("principalName", null == s.getPrincipal() ? "" : userService.findOne(s.getPrincipal()).getUserName());
		map.put("msg", true);
		// 获取对应机构类型的汉字
		DictTreeDataEntity dictData = dictTreeDataService.queryByTreeIdAndNodeCode(DictConstant.ORG_TYPE.getValue(), s.getOrgTypeId());
		if (dictData != null) {
			map.put("orgTypeName", dictData.getData1());
		}
		map.put("sysOrg", s);
		SysOrgEntity pOrg = orgService.findOne(SysOrgEntity.class, s.getParentOrgId());
		map.put("defaultOrgTypeId", (null==pOrg)? SystemWebConstant.orgType.GROUP.getKey():pOrg.getOrgTypeId());
		map.put("default_orgtype_dept", SystemWebConstant.orgType.DEPT.getKey());
		return JSP_PR + returnStr;
	}

	/**
	 * 删除一条组织机构数据
	 * 
	 * @date 2015年1月23日 下午6:27:11
	 * @author 胡毅
	 * @param orgId
	 * @return
	 */
	@RequestMapping(value = "/removeOrg")
	@ResponseBody
	public Map<String, Object> removeOrg(String orgIds, String hasChildIds, HttpServletRequest request) {
		// 返回信息{status: "1"}
		String[] orgIdArr = orgIds.split(",");
		Map<String, Object> msg = new HashMap<String, Object>();
		boolean f = false;
		for (String orgId : orgIdArr) {
			f = orgService.delete(SysOrgEntity.class, orgId);
			// 添加日志
			String optContent = "删除ID为" + orgId + "的组织机构";
			SysUser user = SysUtils.getLoginSysUser(request);
			systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_JGGL,
					SystemLogConstant.OPT_MODEL_TYPE_NAME_JGGL, SystemLogConstant.OPT_TYPE_CODE_DELINFO, SystemLogConstant.OPT_TYPE_NAME_DELINFO,
					optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
		}
		// 是否存在有子机构的机构
		if (StringUtils.isNotBlank(hasChildIds)) {
			String[] haveChildOrgIds = hasChildIds.split(",");
			for (String id : haveChildOrgIds) {
				orgService.deleteOrgByParentId(id);
			}
		}
		if (f) {
			msg.put("status", "1");
		} else {
			msg.put("status", false);
		}
		return msg;
	}

	/**
	 * 验证机构下是否存在用户
	 * 
	 * @date 2015年2月28日 下午12:13:34
	 * @author 胡毅
	 * @param orgId
	 * @return
	 */
	@RequestMapping("/validateUser")
	@ResponseBody
	public boolean validateUser(String orgId) {
		Map<String, Object> pars = new HashMap<String, Object>();
		long count = 0;
		if (StringUtils.isNotBlank(orgId)) {
			pars.put("orgId", orgId);
		} else {
			count = 0;
		}
		count = userService.getUserListCount(pars);
		return count > 0 ? true : false;
	}
}
