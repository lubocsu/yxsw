package com.upsoft.systemweb.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.constant.CommonConstant;
import com.upsoft.system.entity.SysMenuEntity;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.systemweb.dao.MenuDao;
import com.upsoft.systemweb.service.MenuService;
import com.upsoft.systemweb.service.UserService;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 * 
 * 文件名称：MenuServiceImpl.java<br>
 * 摘要：菜单服务实现类<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：吴炫<br>
 * 完成日期：2015年1月22日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：吴炫<br>
 * 完成日期：2015年1月22日<br>
 */
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;
	@Autowired
	private UserService userService;

	@Override
	public String querySysMenusByUserId(String userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select unique systemCode as code, nvl(systemName, '其他') as name");
		sql.append("  from view_menu");
		sql.append(" where ispublic = 1");
		sql.append("    or userid = ?1");
		sql.append(" order by systemCode asc");
		List<Map<String, Object>> sysMenus = menuDao.queryListBySql(sql.toString(), new Object[] { userId });
		Gson gsonUtil = new Gson();
		Map<String, List<Map<String, Object>>> menuObject = new HashMap<String, List<Map<String, Object>>>();
		menuObject.put("list", sysMenus);
		return gsonUtil.toJson(menuObject);
	}

	@Override
	public List<Map<String, Object>> queryMenusBySysCodeAndUserId(String sysCode, String userId) {
		StringBuilder sql = new StringBuilder();
		Object[] param = null;
		sql.append("select unique menuId as id,");
		sql.append("       menuName as name,");
		sql.append("       imagePath as icon,");
		sql.append("       nvl(parentMenuId,'-1') as parentId,");
		sql.append("       permissionNo,");
		sql.append("       linkUrl as url,");
		sql.append("       param,");
		sql.append("       functionType,");
		sql.append("       case");
		sql.append("         when functionType = 1 then");
		sql.append("          1");
		sql.append("         else");
		sql.append("          0");
		sql.append("       end as isParent,");
		sql.append("       case");
		sql.append("         when functionType = 2 then");
		sql.append("          'frmright'");
		sql.append("         when functionType = 3 then");
		sql.append("          '_blank'");
		sql.append("       end as target,");
		sql.append("       to_char(operateType) as operateType,");
		sql.append("       systemCode,");
		sql.append("       nvl(systemName, '其他') as systemName,");
		sql.append("       orderno");
		sql.append("  from view_menu");
		if (StringUtils.isBlank(sysCode)) {
			sql.append(" where systemCode is null");
			sql.append("   and (ispublic = 1 or permissionno is null or userid = ?1)");
			param = new Object[] { userId };
		} else {
			sql.append(" where systemCode = ?1");
			sql.append("   and (ispublic = 1 or permissionno is null or userid = ?2)");
			param = new Object[] { sysCode, userId };
		}
		sql.append(" order by orderno asc");
		return menuDao.queryListBySql(sql.toString(), param);
	}

	@Override
	public List<Map<String, Object>> queryAccordionMenusByCondition(String sysCode, String userId) {
		List<Map<String, Object>> menus = queryMenusBySysCodeAndUserId(sysCode, userId);
		// 组装数据
		Map<String, List<Map<String, Object>>> resultSets = new HashMap<String, List<Map<String, Object>>>();
		List<Map<String, Object>> itemSets = null;
		String pid = null;
		String url = null;
		for (Map<String, Object> menu : menus) {
			pid = (String) menu.get("parentid");
			url = (String) menu.get("url");
			if ("9".equals(menu.get("operatetype")) && StringUtils.isNoneBlank(url)) {
				menu.put("url", MessageFormat.format(url, menu.get("permissionno")));
			}
			if (resultSets.containsKey(pid)) {
				itemSets = resultSets.get(pid);
			} else {
				itemSets = new ArrayList<Map<String, Object>>();
				resultSets.put(pid, itemSets);
			}
			itemSets.add(menu);
		}
		// 如果存在顶级菜单，则取出顶级菜单递归子节点
		List<Map<String, Object>> topMenus = resultSets.get("-1");
		if (topMenus != null) {
			assambleMenus(resultSets, topMenus);
		}
		return topMenus;
	}

	@Override
	public List<Map<String, Object>> queryMenusByMenuIdAndUserId(String menuId, String userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select menuId as id,");
		sql.append("       menuName as name,");
		sql.append("       imagePath as icon,");
		sql.append("       nvl(parentMenuId,'-1') as parentId,");
		sql.append("       permissionNo,");
		sql.append("       linkUrl as url,");
		sql.append("       param,");
		sql.append("       functionType,");
		sql.append("       case");
		sql.append("         when functionType = 1 then");
		sql.append("          1");
		sql.append("         else");
		sql.append("          0");
		sql.append("       end as isParent,");
		sql.append("       case");
		sql.append("         when functionType = 2 then");
		sql.append("          'frmright'");
		sql.append("         when functionType = 3 then");
		sql.append("          '_blank'");
		sql.append("       end as target,");
		sql.append("       to_char(operateType) as operateType,");
		sql.append("       systemCode,");
		sql.append("       nvl(systemName, '其他') as systemName");
		sql.append("  from view_menu");
		sql.append(" where parentmenuid = :parentMenuId");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("parentMenuId", menuId);
		if(StringUtils.isNotBlank(userId)){
			// 超级用户不进行权限控制
			List<String> adminUsers = userService.getAdminUsers();
			if(!adminUsers.contains(userId)){
				sql.append("   and (ispublic = 1 or permissionno is null or userid =:userId)");
				params.put("userId", userId);
			}
		}
		sql.append(" order by orderno asc");
		return menuDao.queryListBySql(sql.toString(),params);
	}

	@Override
	public List<Map<String, Object>> queryMenusByUserIdAndSystemCode(String userId, String systemCode) {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> param = new HashMap<String, Object>();
		sql.append("select distinct menuId as id,");
		sql.append("       menuName as name,");
		sql.append("       imagePath as icon,");
		sql.append("       nvl(parentMenuId,'-1') as parentId,");
		sql.append("       permissionNo,");
		sql.append("       linkUrl as url,");
		sql.append("       param,");
		sql.append("       functionType,");
		sql.append("       case");
		sql.append("         when functionType = 1 then");
		sql.append("          1");
		sql.append("         else");
		sql.append("          0");
		sql.append("       end as isParent,");
		sql.append("       case");
		sql.append("         when functionType = 2 then");
		sql.append("          'frmright'");
		sql.append("         when functionType = 3 then");
		sql.append("          '_blank'");
		sql.append("       end as target,");
		sql.append("       systemCode,");
		sql.append("       nvl(systemName, '未命名') as systemName,");
		sql.append("       orderno");
		sql.append("  from view_menu");
		sql.append(" where systemCode = '"+systemCode+"'");
		if(StringUtils.isNotBlank(userId)){
			// FIXME 超级用户不进行权限控制
			List<String> adminUsers = userService.getAdminUsers();
			if(!adminUsers.contains(userId)){
				//or permissionno is null 没有权限的菜单 暂时去掉
				sql.append("   and (ispublic = 1  or userid = '"+userId+"')");
			}
		}
		sql.append(" order by orderno asc");
		List<Map<String, Object>> list = menuDao.queryListBySql(sql.toString(), param);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> queryMenuTree(String systemCode,String path,String userid) {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		String sql2 = "";
		String sql3 = "";
		if (StringUtils.isNotBlank(systemCode)) {
			sql2 += "'" + systemCode + "'";
			sql3 += " where t.systemcode='" + systemCode + "'";
		} else {
			sql2 += "select systemcode from sys_system_define";
		}
		sql.append("select * from (");
		sql.append(" 	select m.menuid as id,");
		sql.append(" 			m.menuname as name,");
		sql.append(" 			case when (m.parentmenuid is null or m.parentmenuid='') then m.systemcode else m.parentmenuid end as parentid,");
		sql.append(" 			m.systemcode as systemcode,");
		sql.append(" 			m.orderno as orderno,");
		sql.append("			m.enabled as enabled,");
		sql.append(" 			decode(systemcode,'"+path.replace("/", "")+"','"+path+"'||imagepath,'') icon");
		// 不限制菜单显示
		sql.append(" 	from sys_menu m  where 1=1 and m.systemcode in (" + sql2 + ")");
		// 限制显示菜单
//		sql.append(" 	from view_menu m  where 1=1 and m.systemcode in (" + sql2 + ")");
//		sql.append(" and (ispublic = 1 or permissionno is null or userid='"+userid+"')");
		
		sql.append(" UNION ");
		sql.append("	select t.systemcode as id,"
				+ "		 		t.systemname as name, "
				+ "				'0' as parentid,"
				+ "				t.systemcode as systemcode,"
				+ "				0 as orderno,"
				+ "				1 as enabled,"
				+ "				'' icon "
				+ "		from sys_system_define t"
				+ sql3 + ")");
		sql.append(" order by case when id = '"+CommonConstant.SYSTEMCODE.UP_SYSTEM_CODE+"' then 0 end,orderno asc");
		List<Map<String, Object>> menuList = menuDao.queryListBySql(sql.toString(), params);
		return menuList;
	}

	@Override
	public Map<String, List<Map<String, Object>>> queryMenusBySysCodeAndUserIdForGridData(SysMenuEntity m,PageBean bean, String userId) {
		String sysCode = m.getSystemCode();
		String parentMenuId = m.getParentMenuId();
		String menuName = m.getMenuName();
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("select unique menuId as id,");
		sql.append("	   menuName as name,");
		sql.append("	   case when parentMenuId=systemCode then '-1' else nvl(parentMenuId, '-1') end as parentid,");
		sql.append("	   linkUrl as linkurl,");
		sql.append("	   case when enabled='1' then '有效' else '无效' end as enabled,");
		sql.append("	   orderno as orderno,");
		sql.append("	   description as description");
		// 不限制菜单显示
		sql.append(" from sys_menu where 1=1");
		sql.append(" and systemcode =:systemCode");
		params.put("systemCode", sysCode);
		// 限制菜单显示
//		sql.append(" from view_menu where 1=1 ");
//		if (StringUtils.isBlank(sysCode)) {
//			sql.append(" and systemCode is null ");
//			sql.append(" and (ispublic = 1 or permissionno is null or userid=:userId)");
//			params.put("userId", userId);
//		} else {
//			sql.append(" and systemCode=:systemCode ");
//			sql.append(" and (ispublic = 1 or permissionno is null or userid=:userId)");
//			params.put("systemCode", sysCode);
//			params.put("userId", userId);
//		}
		
		// 根据方法queryMenuTree()返回的数据表明，当systemCode==parentmenuid时，该节点为子系统,此时的parentmenuid不作判断依据
		boolean f = sysCode.equals(parentMenuId) ? true : false;
		if (StringUtils.isNotBlank(parentMenuId) && !f) {
			sql.append(" and parentMenuId=:parentMenuId");
			params.put("parentMenuId", parentMenuId);
		}
		// 用于菜单名称搜索
		if (StringUtils.isNotBlank(menuName)) {
			sql.append(" and (" + "menuName like '%" + menuName + "%' " + "or fn_getpy(menuName) like '%" + menuName
					+ "%' " + "or fn_getpy(menuName,1) like '%" + menuName + "%' " + "or fn_getpy(menuName,2) like '%"
					+ menuName + "%' " + "or fn_getpy(menuName,3) like '%" + menuName + "%' "
					+ "or fn_getpy(menuName,4) like '%" + menuName + "%' " + ")");
		}
		sql.append(" order by orderno asc");
		List<Map<String, Object>> menus = menuDao.queryListBySql(sql.toString(), params);
//		Map<String, Object> pageMenus = menuDao.queryPaginationListBySql(sql.toString(), params, bean);
//		List<Map<String, Object>> menus = (List<Map<String, Object>>) pageMenus.get("rows");
		// 组装数据
		Map<String, List<Map<String, Object>>> resultSets = new HashMap<String, List<Map<String, Object>>>();
		List<Map<String, Object>> itemSets = null;
		String pid = null;
		for (Map<String, Object> menu : menus) {
			pid = (String) menu.get("parentid");
			menu.put("parentId", pid);
			if (resultSets.containsKey(pid)) {
				itemSets = resultSets.get(pid);
			} else {
				itemSets = new ArrayList<Map<String, Object>>();
				resultSets.put(pid, itemSets);
			}
			itemSets.add(menu);
		}
		// 定义最终返回结果类型
		Map<String, List<Map<String, Object>>> resultDatas = new HashMap<String, List<Map<String, Object>>>();
		// 如果存在顶级菜单，则取出顶级菜单递归子节点
		List<Map<String, Object>> topMenus = resultSets.get("-1");
		if (topMenus == null) {
			resultDatas.put("rows", itemSets);
		} else {
			assambleMenus(resultSets, topMenus);
			resultDatas.put("rows", topMenus);
		}
		return resultDatas;
	}

	@Override
	public List<Map<String, Object>> queryAccordionMenusByUserIdAndSystemCode(String userId, String sysCode) {
		List<Map<String, Object>> menus = queryMenusByUserIdAndSystemCode(userId, sysCode);
		// 组装数据
		Map<String, List<Map<String, Object>>> resultSets = new HashMap<String, List<Map<String, Object>>>();
		List<Map<String, Object>> itemSets = null;
		String pid = null;
//		String url = null;
		for (Map<String, Object> menu : menus) {
			pid = (String) menu.get("parentid");
//			url = (String) menu.get("url");
//			if (StringUtils.isNotBlank(url)) {
//				menu.put("url", MessageFormat.format(url, menu.get("permissionno")));
//			}
			if (resultSets.containsKey(pid)) {
				itemSets = resultSets.get(pid);
			} else {
				itemSets = new ArrayList<Map<String, Object>>();
				resultSets.put(pid, itemSets);
			}
			itemSets.add(menu);
		}
		// 顶级菜单递归子节点
		List<Map<String, Object>> topMenus = resultSets.get("-1");
		if (topMenus != null) {
			assambleMenus(resultSets, topMenus);
		}
		return topMenus;
	}
	
	/**
	 * 递归菜单子节点
	 * 
	 * @date 2015年2月5日 下午8:11:32
	 * @author 胡毅
	 * @param resultSets
	 * @param targetMenuChilds
	 */
	private void assambleMenus(Map<String, List<Map<String, Object>>> resultSets,
			List<Map<String, Object>> targetMenuChilds) {
		String menuId = null;
		List<Map<String, Object>> childMenus = null;
		if (targetMenuChilds != null) {
			for (Map<String, Object> menu : targetMenuChilds) {
				menuId = (String) menu.get("id");
				childMenus = resultSets.get(menuId);
				if (childMenus != null) {
					menu.put("children", childMenus);
					assambleMenus(resultSets, childMenus);
				}
			}
		}
	}

	@Override
	public List<Map<String, Object>> queryMenuListBySystemCode(String systemCode) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> parsCon = new HashMap<String, Object>();
		sql.append("select * from SYS_MENU t where 1=1");

		if (StringUtils.isNotBlank(systemCode)) {
			sql.append("and t.systemcode =:systemCodeCon");
			parsCon.put("systemCodeCon", systemCode);
		}

		List<Map<String, Object>> menuList = menuDao.queryListBySql(sql.toString(), parsCon);

		return menuList;
	}

}
