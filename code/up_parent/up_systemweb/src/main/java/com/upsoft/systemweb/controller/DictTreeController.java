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

import com.upsoft.login.controller.BaseController;
import com.upsoft.login.support.webservice.SysUtils;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.constant.SystemLogConstant;
import com.upsoft.system.entity.DictTreeDataEntity;
import com.upsoft.system.entity.DictTreeEntity;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.support.annotation.LogAnnotation;
import com.upsoft.system.util.PageUtil;
import com.upsoft.system.util.SequenceGernerator;
import com.upsoft.systemweb.service.DictTreeDataService;
import com.upsoft.systemweb.service.DictTreeService;
import com.upsoft.systemweb.support.SystemLogServiceCache;

/**
* Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：DictController.java<br>
* 摘要：数据字典控制层<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：Awesan<br>
* 完成日期：2015年1月21日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：Awesan<br>
* 完成日期：2015年1月21日<br>
*/
@Controller
@RequestMapping("/dict")
public class DictTreeController extends BaseController{
	public static final String FORWARD_PR = "/dict";
	public static final String JSP_PR = "/WEB-INF/jsp/dict/";
	public static final String REDIRECT_PR = "redirect:" + FORWARD_PR;
	
	@Autowired
	private DictTreeService dictTreeService;
	
	@Autowired
	private DictTreeDataService dictTreeDataService;
	@Autowired
	private SystemLogServiceCache systemLogServiceCache;
	
	
	/**
	 * 跳转至字典管理页面
	 * @date 2015年1月21日 下午2:56:28
	 * @author Awesan
	 * @param map
	 * @param request
	 * @return 页面路径
	 */
	@LogAnnotation(FunctionName="数据字典功能")
	@RequestMapping("/init")
	public String toManager(ModelMap map, HttpServletRequest request){
		super.findMenuResourcePermission(request, map);
		SysUser user = SysUtils.getLoginSysUser(request);
		
		//添加日志
		String optContent = "查询数据字典";
		systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(), 
				Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_RZGL, 
				SystemLogConstant.OPT_MODEL_TYPE_NAME_RZGL, SystemLogConstant.OPT_TYPE_CODE_QUERYINFO, SystemLogConstant.OPT_TYPE_NAME_QUERYINFO, 
				optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
		return JSP_PR + "main";
	}
	
	/**
	 * 跳转至字典数据项新增页面
	 * @date 2015年1月29日 上午9:55:15
	 * @author Awesan
	 * @param map
	 * @param request
	 * @return 
	 */
	@RequestMapping("/toAddDictData")
	public String toAddDictData(ModelMap map, HttpServletRequest request){
		String parentNodeId = (String) request.getParameter("parentNodeId");
		if(StringUtils.isNotBlank(parentNodeId)){
			DictTreeEntity dict = dictTreeService.findOne(DictTreeEntity.class, parentNodeId);
			if(dict != null){
				map.addAttribute("parentNodeName", dict.getTreeDescription());
			}else{
				parentNodeId = "";
			}
		}
		map.addAttribute("parentNodeId", parentNodeId);
		map.addAttribute("defaultNo", dictTreeDataService.queryMaxOrderNoByTreeId(parentNodeId) + 1);
		return JSP_PR + "addData";
	}
	
	/**
	 * 跳转至字典数据项查看页面
	 * @date 2015年1月29日 上午9:56:28
	 * @author Awesan
	 * @param map
	 * @param request
	 * @return 
	 */
	@RequestMapping("/toViewDictData")
	public String toViewDictData(ModelMap map, HttpServletRequest request){
		String nodeId = (String) request.getParameter("nodeId");
		if(StringUtils.isNotBlank(nodeId)){
			DictTreeDataEntity dictData = dictTreeDataService.findOne(DictTreeDataEntity.class, nodeId);
			map.addAttribute("dictDataBean", dictData);
		}
		return JSP_PR + "viewData";
	}
	
	/**
	 * 跳转至字典数据项修改页面
	 * @date 2015年1月29日 上午9:56:59
	 * @author Awesan
	 * @param map
	 * @param request
	 * @return 
	 */
	@RequestMapping("/toModifyDictData")
	public String toModifyDictData(ModelMap map, HttpServletRequest request){
		String nodeId = (String) request.getParameter("nodeId");
		if(StringUtils.isNotBlank(nodeId)){
			DictTreeDataEntity dictData = dictTreeDataService.findOne(DictTreeDataEntity.class, nodeId);
			DictTreeEntity dict = dictTreeService.findOne(DictTreeEntity.class, dictData.getParentNodeId());
			//dict 为空说明父节点为数据类型
			if(dict == null){
				DictTreeDataEntity parentData = dictTreeDataService.findOne(DictTreeDataEntity.class, dictData.getParentNodeId());
				map.addAttribute("parentNodeName", parentData.getData1());
				map.addAttribute("treeId", dictTreeDataService.queryTreeIdByNodeId(dictData.getNodeId()));
			}else{
				map.addAttribute("parentNodeName", dict.getTreeDescription());
				map.addAttribute("treeId", dict.getTreeId());
			}
			map.addAttribute("dictDataBean", dictData);
		}
		return JSP_PR + "modifyData";
	}
	
	/**
	 * 新增/修改字典数据项对象
	 * @date 2015年1月23日 下午5:24:37
	 * @author Awesan
	 * @param dictData
	 * @param map
	 * @param request
	 * @return 
	 */
	@RequestMapping("/addDictData")
	@ResponseBody
	public Map<String, Object> addDictData(DictTreeDataEntity dictData, ModelMap map, HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		String optContent = "";
		String optTypeCode = "";
		String optTypeName = "";
		String dictCode = "";
		if(StringUtils.isNotBlank(dictData.getParentNodeId())){
			try {
				if(StringUtils.isBlank(dictData.getNodeId())){
					DictTreeDataEntity dictDatas =  dictTreeDataService.save(dictData);
					dictCode = dictDatas.getCode();
					optContent = "新增数据代码为"+dictCode+"的字典数据项";
					optTypeCode = SystemLogConstant.OPT_TYPE_CODE_ADDINFO;
					optTypeName = SystemLogConstant.OPT_TYPE_NAME_ADDINFO;
				}else{
					dictTreeDataService.update(dictData);
					dictCode = dictData.getCode();
					optContent = "修改数据代码为"+dictCode+"的字典数据项";
					optTypeCode = SystemLogConstant.OPT_TYPE_CODE_EDITINFO;
					optTypeName = SystemLogConstant.OPT_TYPE_NAME_EDITINFO;
				}
				//添加日志
				SysUser user = SysUtils.getLoginSysUser(request);
				systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(), 
						Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_RZGL, 
						SystemLogConstant.OPT_MODEL_TYPE_NAME_RZGL,optTypeCode, optTypeName, 
						optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
			} catch (Exception e) {
				e.printStackTrace();
				result.put("msg", "保存失败！");
			}
			result.put("msg", "保存成功！");
		}else{
			result.put("msg", "请先选择字典树目录！");
		}
		return result;
	}
	
	/**
	 * 删除字典数据项对象(可以批量删除)
	 * @date 2015年1月23日 下午5:36:33
	 * @author Awesan
	 * @param ids
	 * @param map
	 * @param request
	 * @return 
	 */
	@RequestMapping("/removeDictData")
	@ResponseBody
	public Map<String, Object> removeDictData(String[] ids, ModelMap map, HttpServletRequest request){
		boolean flag = dictTreeDataService.deleteByIds(ids);
		Map<String, Object> result = new HashMap<String, Object>();
		//返回删除成功状态
		result.put("status", flag ? 1 : 0);
		SysUser user = SysUtils.getLoginSysUser(request);
		String optContent = "删除id分别为"+ids+"的字典数据项";
		systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(), 
				Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_RZGL, 
				SystemLogConstant.OPT_MODEL_TYPE_NAME_RZGL,SystemLogConstant.OPT_TYPE_CODE_EDITINFO, SystemLogConstant.OPT_TYPE_NAME_EDITINFO, 
				optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
		return result;
	}
	
	/**
	 * 跳转至字典目录新增页面
	 * @date 2015年1月29日 上午9:57:58
	 * @author Awesan
	 * @param map
	 * @param request
	 * @return 
	 */
	@RequestMapping("/toAddDict")
	public String toAddDict(ModelMap map, HttpServletRequest request){
		super.putDictConstant(map);
		map.addAttribute("defaultNo", dictTreeService.queryMaxOrderNo() + 1);
		return JSP_PR + "addDict";
	}
	
	/**
	 * 跳转至字典目录修改页面
	 * @date 2015年1月29日 上午9:59:04
	 * @author Awesan
	 * @param map
	 * @param request
	 * @return 
	 */
	@RequestMapping("/toModifyDict")
	public String toModifyDict(ModelMap map, HttpServletRequest request){
		super.putDictConstant(map);
		String treeId = (String) request.getParameter("treeId");
		DictTreeEntity dict = dictTreeService.findOne(DictTreeEntity.class, treeId);
		map.addAttribute("dictBean", dict);
		return JSP_PR + "modifyDict";
	}
	
	/**
	 * 新增/修改字典目录对象
	 * @date 2015年1月26日 上午11:15:03
	 * @author Awesan
	 * @param dictData
	 * @param map
	 * @param request
	 * @return 
	 */
	@RequestMapping("/addDict")
	@ResponseBody
	public Map<String, Object> addDict(DictTreeEntity dictTree, ModelMap map, HttpServletRequest request){
		DictTreeEntity node = null;
		Map<String, Object> result = new HashMap<String, Object>();
		String optContent = "";
		String optTypeCode = "";
		String optTypeName = "";
		String dictDe = "";
		try {
			if(StringUtils.isBlank(dictTree.getTreeId())){
				dictTree.setTreeId(SequenceGernerator.getSequenceByType(dictTree.getTreeType()));
				node = dictTreeService.save(dictTree);
				dictDe = node.getTreeDescription();
				optContent = "新增树描述为"+dictDe+"的字典数据项";
				optTypeCode = SystemLogConstant.OPT_TYPE_CODE_ADDINFO;
				optTypeName = SystemLogConstant.OPT_TYPE_NAME_ADDINFO;
			}else{
				node = dictTreeService.update(dictTree);
				dictDe = node.getTreeDescription();
				optContent = "修改树描述为"+dictDe+"的字典数据项";
				optTypeCode = SystemLogConstant.OPT_TYPE_CODE_EDITINFO;
				optTypeName = SystemLogConstant.OPT_TYPE_NAME_EDITINFO;
			}
			//添加日志
			SysUser user = SysUtils.getLoginSysUser(request);
			systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(), 
					Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_RZGL, 
					SystemLogConstant.OPT_MODEL_TYPE_NAME_RZGL,optTypeCode, optTypeName, 
					optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
			// 返回当前页码
			Long curPage = dictTreeService.getPageNoById(node.getTreeId(),null, 20);
			result.put("curPage", curPage);
			result.put("node", node);
		} catch (Exception e) {
			e.printStackTrace();
			SequenceGernerator.rollbackNum(dictTree.getTreeType());
			result.put("msg", "保存失败");
			result.put("status", false);
		}
		result.put("msg", "保存成功");
		result.put("status", true);
		return result;
	}
	
	/**
	 * 删除字典目录对象
	 * @date 2015年1月27日 上午11:26:36
	 * @author Awesan
	 * @param treeId
	 * @param map
	 * @return 
	 */
	@RequestMapping("/removeDict")
	@ResponseBody
	public Map<String, Object> removeDict(String treeId, ModelMap map,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			//删除子节点再删除字典
			DictTreeEntity dict = dictTreeService.findOne(DictTreeEntity.class, treeId);
			List<DictTreeDataEntity> dictDatas = dictTreeDataService.queryDataByTreeId(dict.getTreeId());
			for(DictTreeDataEntity data:dictDatas){
				dictTreeDataService.delete(DictTreeDataEntity.class, data.getNodeId());
			}
			dictTreeService.delete(DictTreeEntity.class, treeId);
			//添加日志
			SysUser user = SysUtils.getLoginSysUser(request);
			String optContent = "删除treeId为"+treeId+"字典目录";
			systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(), 
					Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_RZGL, 
					SystemLogConstant.OPT_MODEL_TYPE_NAME_RZGL,SystemLogConstant.OPT_TYPE_CODE_DELINFO, SystemLogConstant.OPT_TYPE_NAME_DELINFO, 
					optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
		} catch (Exception e) {
			e.printStackTrace();
			//删除失败
			result.put("status", 0);
		}
		//删除成功
		result.put("status", 1);
		return result;
	}
	
	
	/**
	 * 检查代码是否存在
	 * @date 2015年2月3日 下午2:35:39
	 * @author Awesan
	 * @param request
	 * @return 
	 */
	@RequestMapping("/checkCodeOrData")
	@ResponseBody
	public String checkCodeOrData(HttpServletRequest request){
		String treeId = request.getParameter("treeId");
		String nodeId = request.getParameter("nodeId");
		String validateName = request.getParameter("validateName");
		String validateValue = request.getParameter("validateValue");
		if(StringUtils.isNotBlank(validateValue)){
			List<DictTreeDataEntity> repeatEntities = dictTreeDataService.checkRepeatInTreeId(treeId, validateName, validateValue);
			if(repeatEntities != null && repeatEntities.size()>0){
				if(repeatEntities.size()>1){
					return "{\"validateResult\":{\"valid\":false}}"; //验证不通过
				}else if(repeatEntities.size() ==1 && !repeatEntities.get(0).getNodeId().equals(nodeId)){
					return "{\"validateResult\":{\"valid\":false}}"; //验证不通过
				}
			}
		}
		return "{\"validateResult\":{\"valid\":true}}"; //验证通过
	}
	
	/**
	 * 获取数据字典目录树
	 * @date 2015年1月27日 下午5:00:54
	 * @author Awesan
	 * @param request
	 * @return 
	 */
	@RequestMapping("/findDictTree")
	@ResponseBody
	public Map<String, Object> findDictTree(HttpServletRequest request){
		PageBean bean = new PageBean(request);
		Map<String, Object> pars = new HashMap<String, Object>();
		String treeDesc = (String) request.getParameter("treeDesc");
		if(StringUtils.isNotBlank(treeDesc)){
			pars.put("treeDesc", treeDesc);
		}
		Map<String, Object> result = dictTreeService.queryDictByPagination(bean, pars);
		return result;
	}
	
	/**
	 * 查找父节点列表
	 * @date 2015年1月29日 上午9:08:06
	 * @author Awesan
	 * @param request
	 * @return 
	 */
	@RequestMapping("/findDataParentsGrid")
	@ResponseBody
	public Map<String, Object> findDictDataParents(HttpServletRequest request){
		PageBean bean = new PageBean(request);
		Map<String, Object> pars = new HashMap<String, Object>();
		String treeDesc = (String) request.getParameter("treeDesc");
		if(StringUtils.isNotBlank(treeDesc)){
			pars.put("treeDesc", treeDesc);
		}
		Map<String, Object> result = dictTreeService.queryDataParentByPagination(bean, pars);
		return result;
	}
	
	/**
	 * 异步获取数据字典表格
	 * @date 2015年2月3日 下午4:12:49
	 * @author Awesan
	 * @param request
	 * @return 
	 */
	@RequestMapping("/findDictDataGridByAjax")
	@ResponseBody
	public Map<String, Object> findDictDataGridByAjax(HttpServletRequest request){
		PageBean bean = new PageBean(request);
		Map<String, Object> pars = new HashMap<String, Object>();
		//获取查询参数
		String treeId = (String) request.getParameter("parentId");
		String treeDesc = (String) request.getParameter("parentNodeName");
		String code = (String) request.getParameter("code");
		String data1 = (String) request.getParameter("data1");
		if(StringUtils.isNotBlank(treeId)){
			pars.put("treeId", treeId);
		}
		if(StringUtils.isNotBlank(treeDesc)){
			pars.put("treeDesc", treeDesc);
		}
		if(StringUtils.isNotBlank(code)){
			pars.put("code", code);
		}
		if(StringUtils.isNotBlank(data1)){
			pars.put("data1", data1);
		}
		Map<String, Object> result = dictTreeDataService.queryDictDataByAjax(pars, bean);
		return result;
	}
	
	@RequestMapping("/findChildDataByAjax")
	@ResponseBody
	public Map<String, Object> findChildDataByAjax(HttpServletRequest request){
		//获取查询参数
		String parentId = (String) request.getParameter("parentId");
		Map<String, Object> result = dictTreeDataService.queryChildDataByAjax(parentId);
		return result;
	}
	
	
	
	/**
	 * 获取字典数据项列表
	 * @date 2015年1月27日 上午9:28:41
	 * @author Awesan
	 * @param request
	 * @return 
	 */
	@RequestMapping("/findDictTreeDataGrid")
	@ResponseBody
	public String findDictTreeDataGrid(HttpServletRequest request){
		//获取分页信息
		int pageNo = PageUtil.getPageNo(request);
		int pageSize = PageUtil.getPageSize(request, 10);
		String sort = (String) request.getParameter("sort");
		String direction = (String) request.getParameter("direction");
		//获取查询参数
		String treeId = (String) request.getParameter("parentId");
		String treeDesc = (String) request.getParameter("parentNodeName");
		Map<String, Object> pars = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(treeId)){
			pars.put("treeId", treeId);
		}
		if(StringUtils.isNotBlank(treeDesc)){
			pars.put("treeDesc", treeDesc);
		}
		if(StringUtils.isNotBlank(sort)){
			pars.put("sort", sort);
		}
		if(StringUtils.isNotBlank(direction)){
			pars.put("direction", direction);
		}
		//查询数据
		Long total = dictTreeDataService.queryDictDataCount(pars);
		List<DictTreeDataEntity> datas = dictTreeDataService.queryDictTreeDataByPage((pageNo-1)*pageSize, pageSize, pars);
		List<Map<String,String>> jsonData = new ArrayList<Map<String,String>>();
		if(datas != null){
			for (DictTreeDataEntity data : datas) {
				Map<String, String> item = new HashMap<String, String>();
				item.put("id", data.getNodeId());
				item.put("parentId", data.getParentNodeId());
				item.put("name", data.getData1());
				//item.put("children", data.g);
				item.put("code", data.getCode());
				item.put("data2", data.getData2());
				item.put("data3", data.getData3());
				jsonData.add(item);
			}
		}
		//拼装分页数据返回
		return PageUtil.getPageReturn(pageNo, total, jsonData);
	}
	
	/**
	 * 获取数据字典下拉列表（非树形）
	 * @date 2015年1月27日 上午9:19:36
	 * @author Awesan
	 * @param treeId
	 * @param request
	 * @return 
	 */
	@RequestMapping("/findDictItems")
	@ResponseBody
	public List<Map<String,String>> findDictItemsById(String treeId, HttpServletRequest request){
		List<DictTreeDataEntity> datas = dictTreeDataService.queryDataByTreeId(treeId);
		List<Map<String,String>> jsonData = new ArrayList<Map<String,String>>();
		if(datas != null){
			for (DictTreeDataEntity dict : datas) {
				Map<String, String> item = new HashMap<String, String>();
				item.put("value", dict.getCode());
				item.put("key", dict.getData1());
				jsonData.add(item);
			}
		}
		return jsonData;
	}
	
	
	/**
	 * 获取父节点下所有子节点
	 * @date 2015年3月16日 下午5:36:37
	 * @author Awesan
	 * @param parentId
	 * @param request
	 * @return 
	 */
	@RequestMapping("/getChildNodes")
	@ResponseBody
	public List<DictTreeDataEntity> getChildNodes(String nodeId, HttpServletRequest request){
		List<DictTreeDataEntity> datas = dictTreeDataService.queryDataByParentId(nodeId);
		return datas;
	}
}
