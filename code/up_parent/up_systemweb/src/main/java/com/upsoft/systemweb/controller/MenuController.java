package com.upsoft.systemweb.controller;

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
import com.upsoft.system.entity.SysMenuEntity;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.support.annotation.LogAnnotation;
import com.upsoft.systemweb.dao.MenuDao;
import com.upsoft.systemweb.dao.SystemDefineDao;
import com.upsoft.systemweb.service.MenuService;
import com.upsoft.systemweb.support.SystemLogServiceCache;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 * 
 * 文件名称：MenuController.java<br>
 * 摘要：菜单管理相关操作<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：胡毅<br>
 * 完成日期：2015年2月2日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：吴炫<br>
 * 完成日期：2015年1月29日<br>
 */
@Controller
@RequestMapping(MenuController.FORWARD_PREFIX)
public class MenuController extends BaseController {

	protected static final String FORWARD_PREFIX = "/menu";
	private static final String JSP_PR = "/WEB-INF/jsp/menu";
	@Autowired
	private MenuService menuService;

	@Autowired
	private MenuDao menuDao;

	@Autowired
	private SystemDefineDao systemDefineDao;

	@Autowired
	private SystemLogServiceCache systemLogServiceCache;

	/**
	 * 
	 * @date 2015年1月29日 下午5:23:15
	 * @author 吴炫
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@LogAnnotation(FunctionName = "菜单功能")
	@RequestMapping("/init")
	public String toMain(HttpServletRequest request, ModelMap map) throws Exception {
		super.findMenuResourcePermission(request, map);
		// 添加日志
		String optContent = "查询系统菜单";
		SysUser user = SysUtils.getLoginSysUser(request);
		systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(),
				Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_CDGL,
				SystemLogConstant.OPT_MODEL_TYPE_NAME_CDGL, SystemLogConstant.OPT_TYPE_CODE_QUERYINFO, SystemLogConstant.OPT_TYPE_NAME_QUERYINFO,
				optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
		return JSP_PR + "/main";
	}

	/**
	 * 获取菜单树
	 * 
	 * @date 2015年2月4日 上午10:07:14
	 * @author 胡毅
	 * @param systemCode
	 * @return
	 */
	@RequestMapping("/queryMenuTree")
	@ResponseBody
	public List<Map<String, Object>> queryMenuTree(String systemCode, HttpServletRequest request) {
		SysUser user = SysUtils.getLoginSysUser(request);
		String userId = user.getUserId();
		List<Map<String, Object>> menuTree = menuService.queryMenuTree(systemCode, request.getContextPath().toLowerCase(), userId);

		for (Map<String, Object> map : menuTree) {
			// 组装树
			map.put("parentId", map.get("parentid"));
			// 设置系统图标
			if ("0".equals(map.get("parentid"))) {
				map.put("icon", "../image/systemList.png");
			}
			if ("0".equals(map.get("enabled").toString())) {
				Map<String, Object> color = new HashMap<String, Object>();
				color.put("color", "red");
				map.put("font", color);// font:{'color':'red'}
			}
		}
		return menuTree;
	}

	/**
	 * 获取菜单Grid数据
	 * 
	 * @date 2015年2月4日 上午10:07:35
	 * @author 胡毅
	 * @param request
	 * @param systemCode
	 *            所属系统编码
	 * @return
	 */
	@RequestMapping(value = "/getGridMenuData")
	@ResponseBody
	public Map<String, List<Map<String, Object>>> getGridOrgData(HttpServletRequest request, SysMenuEntity menu) {
		PageBean bean = new PageBean(request);
		SysUser user = SysUtils.getLoginSysUser(request);
		Map<String, List<Map<String, Object>>> menuRows = menuService.queryMenusBySysCodeAndUserIdForGridData(menu, bean,user.getUserId());
		return menuRows;
	}

	/**
	 * 跳转到新增菜单页面
	 * 
	 * @date 2015年2月5日 下午3:11:26
	 * @author 胡毅
	 * @param menu
	 * @param map
	 * @return
	 */
	@RequestMapping("/toAddMenu")
	public String toAddMenu(SysMenuEntity menu, ModelMap map) {
		super.putDictConstant(map);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentmenuid", menu.getParentMenuId());
		// 获取最大序号值
		List<Map<String, Object>> no = menuDao
				.queryListBySql("SELECT MAX(to_number(t.orderno))+1 as maxOrderNo from sys_menu t where t.parentmenuid =:parentmenuid", 0, 1, params);
		if ("0".equals(menu.getParentMenuId())) {
			menu.setParentMenuId("");
		}
		Map<String, Object> sName = getSystemName(menu);
		map.put("maxOrderNo", no.get(0));
		map.put("systemName", sName);
		map.put("menu", menu);
		return JSP_PR + "/addMenu";
	}

	@RequestMapping("/getSystemNameByAjax")
	@ResponseBody
	public Map<String, Object> getSystemNameByAjax(SysMenuEntity menu) {
		Map<String, Object> sName = getSystemName(menu);
		return sName;
	}

	/**
	 * 执行新增菜单操作
	 * 
	 * @date 2015年2月5日 下午3:11:43
	 * @author 胡毅
	 * @param request
	 * @param menu
	 * @return
	 */
	@RequestMapping("/doAddMenu")
	@ResponseBody
	public Map<String, Object> doAddMenu(HttpServletRequest request, SysMenuEntity menu) {
		// 返回信息{message: "修改用户成功"}
		Map<String, Object> msg = new HashMap<String, Object>();
		SysMenuEntity m = null;
		// 设置操作类型为默认
		menu.setOperateType(9);
		// 给URL添加前缀后缀,
		if (menu.getFunctionType() == 2) {
			if (StringUtils.isNotBlank(menu.getLinkURL())) {
				if (!menu.getLinkURL().contains(menu.getSystemCode())) {
					menu.setLinkURL("/" + menu.getSystemCode() + menu.getLinkURL());
				}
				menu.setLinkURL(menu.getLinkURL());
			}
		}
		m = menuService.save(menu);
		if (m != null) {
			msg.put("message", "新增菜单成功");
			// 组织tree节点数据
			Map<String, Object> newNode = new HashMap<String, Object>();
			newNode.put("id", menu.getMenuId());
			newNode.put("name", menu.getMenuName());
			newNode.put("parentId", menu.getParentMenuId());
			newNode.put("systemcode", menu.getSystemCode());
			msg.put("newNode", newNode);
			// 添加日志
			String optContent = "新增名称为" + menu.getMenuName() + "的菜单";
			SysUser user = SysUtils.getLoginSysUser(request);
			systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_CDGL,
					SystemLogConstant.OPT_MODEL_TYPE_NAME_CDGL, SystemLogConstant.OPT_TYPE_CODE_ADDINFO, SystemLogConstant.OPT_TYPE_NAME_ADDINFO,
					optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
		} else {
			msg.put("message", "新增菜单失败");
		}
		return msg;
	}

	/**
	 * 删除菜单
	 * 
	 * @date 2015年2月5日 下午4:26:27
	 * @author 胡毅
	 * @param orgIds
	 * @return
	 */
	@RequestMapping(value = "/removeMenu")
	@ResponseBody
	public Map<String, Object> removeOrg(String menuIds, HttpServletRequest request) {
		Map<String, Object> msg = new HashMap<String, Object>();
		boolean f = false;
		String[] ids = menuIds.split(",");
		for (String id : ids) {
			f = menuService.delete(SysMenuEntity.class, id);
			if (f) {
				msg.put("status", "1");
				// 添加日志
				String optContent = "删除id为" + id + "的菜单";
				SysUser user = SysUtils.getLoginSysUser(request);
				systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(),
						Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_CDGL,
						SystemLogConstant.OPT_MODEL_TYPE_NAME_CDGL, SystemLogConstant.OPT_TYPE_CODE_DELINFO, SystemLogConstant.OPT_TYPE_NAME_DELINFO,
						optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
			} else {
				msg.put("status", f);
				break;
			}
		}
		return msg;
	}

	@RequestMapping("/viewMenu")
	public String viewMenu(HttpServletRequest request, String menuId, ModelMap map) {
		if (StringUtils.isNotBlank(menuId)) {
			SysMenuEntity m = menuService.findOne(SysMenuEntity.class, menuId);
			if (StringUtils.isBlank(m.getParentMenuId())) {
				m.setParentMenuId("无");
			} else {
				// 获取父菜单名（当父ID为系统编码时，此菜单为顶级菜单）
				if (!m.getParentMenuId().equals(m.getSystemCode())) {
					Map<String, Object> pName = getParentMenuName(m);
					m.setParentMenuId(pName.get("name").toString());
				} else {
					m.setParentMenuId("无");
				}
			}
			// 获取所属系统名
			Map<String, Object> sName = getSystemName(m);
			// 获取权限名称
			if (StringUtils.isNotBlank(m.getPermissionNo())) {
				Map<String, Object> perName = getPermissionIDorName(m, "name");
				m.setPermissionNo(perName.get("permissionname").toString());
			}
			m.setSystemCode(sName.get("systemname").toString());
			map.put("menu", m);
		}

		return JSP_PR + "/viewMenu";
	}

	@RequestMapping("/toModifyMenu")
	public String toModifyMenu(String menuId, ModelMap map) {
		super.putDictConstant(map);
		if (StringUtils.isNotBlank(menuId)) {
			SysMenuEntity menu = menuService.findOne(SysMenuEntity.class, menuId);
			Map<String, Object> sName = getSystemName(menu);
			if (StringUtils.isNotBlank(menu.getPermissionNo())) {
				Map<String, Object> perID = getPermissionIDorName(menu, "id");
				map.put("permissionID", perID.get("permissionid"));
			}
			map.put("menu", menu);
			map.put("systemName", sName.get("systemname"));
		}
		return JSP_PR + "/modifyMenu";
	}

	private Map<String, Object> getParentMenuName(SysMenuEntity m) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("menuId", m.getParentMenuId());
		List<Map<String, Object>> pName = menuDao.queryListBySql("select m.menuName as name from sys_menu m where m.menuId=:menuId", params);
		return pName.get(0);
	}

	private Map<String, Object> getSystemName(SysMenuEntity menu) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("systemCode", menu.getSystemCode());
		List<Map<String, Object>> sName = systemDefineDao
				.queryListBySql("SELECT t.systemName as systemName from sys_system_define t where t.systemcode =:systemCode", 0, 1, params);
		return sName.get(0);
	}

	private Map<String, Object> getPermissionIDorName(SysMenuEntity m, String type) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("permissionNo", m.getPermissionNo());
		String condition = "";
		if ("id".equals(type)) {
			condition = "permissionid";
		} else {
			condition = "permissionname";
		}
		List<Map<String, Object>> pName = systemDefineDao.queryListBySql(
				"SELECT p." + condition + " as " + condition + " from sys_permission p where p.permissionno =:permissionNo", 0, 1, params);
		if (pName.size() > 0) {
			return pName.get(0);
		} else {
			return new HashMap<String, Object>();
		}
	}

	@RequestMapping("/doModifyMenu")
	@ResponseBody
	public Map<String, Object> doModifyMenu(SysMenuEntity menu, HttpServletRequest request) {
		Map<String, Object> msg = new HashMap<String, Object>();
		SysMenuEntity m = menuService.update(menu);
		if (m != null) {
			// 添加日志
			String optContent = "修改名称为" + menu.getMenuName() + "的菜单";
			SysUser user = SysUtils.getLoginSysUser(request);
			systemLogServiceCache.cacheLog(user, request.getRequestURI(), this.getClass().getCanonicalName(),
					Thread.currentThread().getStackTrace()[1].getMethodName(), SystemLogConstant.OPT_MODEL_TYPE_CODE_CDGL,
					SystemLogConstant.OPT_MODEL_TYPE_NAME_CDGL, SystemLogConstant.OPT_TYPE_CODE_EDITINFO, SystemLogConstant.OPT_TYPE_NAME_EDITINFO,
					optContent, SystemLogConstant.UP_SYSTEMWEB_CODE, SystemLogConstant.UP_SYSTEMWEB_NAME);
			msg.put("msg", "更新菜单成功");
		} else {
			msg.put("msg", "更新菜单失败");
		}
		return msg;
	}
}
