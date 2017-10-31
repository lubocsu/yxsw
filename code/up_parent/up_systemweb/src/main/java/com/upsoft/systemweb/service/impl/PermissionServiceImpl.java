package com.upsoft.systemweb.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.entity.SysPermissionEntity;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.systemweb.dao.PermissionDao;
import com.upsoft.systemweb.dao.SystemDefineDao;
import com.upsoft.systemweb.service.PermissionService;

/**
 * Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
 * All rights reserved.<br>
 * 
 * 文件名称：RoleManageServiceImpl.java<br>
 * 摘要：权限管理<br>
 * -------------------------------------------------------<br>
 * 当前版本：1.1.1<br>
 * 作者：李 红<br>
 * 完成日期：2015年1月22日<br>
 * -------------------------------------------------------<br>
 * 取代版本：1.1.0<br>
 * 原作者：李 红<br>
 * 完成日期：2015年1月22日<br>
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl implements PermissionService {
	@Autowired
	private SystemDefineDao systemDefineDao;
	@Autowired
	private PermissionDao permissionDao;

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findPermissionListAndCount(String systemCode, String parentId, String permissionName,
			PageBean pageBean) {
		StringBuffer querySql = new StringBuffer(
				" SELECT P.PERMISSIONID,(SELECT COUNT(1) FROM SYS_PERMISSION P1 WHERE P1.PARENTPERMISSIONID=P.PERMISSIONID) CHILDNUM,P.PERMISSIONNAME,P.PERMISSIONTYPE,P.PERMISSIONNO,P.ORDERNO,P.ENABLED,P.SYSTEMCODE,S.SYSTEMNAME  FROM SYS_PERMISSION P LEFT JOIN SYS_SYSTEM_DEFINE S ON P.SYSTEMCODE=S.SYSTEMCODE WHERE 1=1 ");
		Map<String, Object> params = new HashMap<String, Object>();

		if (systemCode != null && !"".equals(systemCode) && !"0".equals(systemCode)) {
			querySql.append(" AND P.SYSTEMCODE=:systemCode");
			params.put("systemCode", systemCode);
		}
		if (permissionName != null && !"".equals(permissionName)) {
			querySql.append(" AND P.PERMISSIONNAME LIKE '%" + permissionName + "%'");
		} else {
			querySql.append(" AND P.PARENTPERMISSIONID IS NULL ");
		}
		// querySql.append(" ORDER BY P.ORDERNO ");
		Map<String, Object> listAndCount = permissionDao.queryPaginationListBySql(querySql.toString(), params,
				pageBean);
		Map<String, Object> permissionList = new HashMap<String, Object>();
		List<Map<String, Object>> permissions = (List<Map<String, Object>>) listAndCount.get("rows");
		for (Map<String, Object> map : permissions) {
			if (map.get("childnum") != null && Integer.parseInt(map.get("childnum") + "") > 0) {
				map.put("isParent", true);
			} else {
				map.put("isParent", false);
			}
			map.put("open", false);
			map.remove("childnum");
		}
		permissionList.put("pager.totalRows", listAndCount.get(PageBean.TOTAL));
		permissionList.put("rows", permissions);
		return permissionList;
	}

	@Override
	public void saveOrUpdatePermission(SysPermissionEntity permissionEntity) {
		try {
			if (permissionEntity.getPermissionId() != null) {
				this.update(permissionEntity);
				this.permissionDao.executeSql(
						" UPDATE SYS_PERMISSION P SET P.ENABLED=" + permissionEntity.getEnabled()
								+ " WHERE P.PARENTPERMISSIONID='" + permissionEntity.getPermissionId() + "'",
						new HashMap<String, Object>());
			} else {
				this.permissionDao.saveAndFlush(permissionEntity);
			}
			// 此处需要对按钮权限做保存
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("permissionNo", permissionEntity.getPermissionNo());
			params.put("menuId", permissionEntity.getMenuId());
			// 修改菜单
			this.permissionDao.executeSql(" UPDATE SYS_MENU M SET M.PERMISSIONNO=:permissionNo WHERE M.MENUID=:menuId",
					params);
			String btnPermission = permissionEntity.getBtnPermission();
			String permissionNo = permissionEntity.getPermissionNo();
			String parentPermissionId = permissionEntity.getPermissionId();
			String systemCode = permissionEntity.getSystemCode();
			if (StringUtils.isNotBlank(btnPermission)) {
				if (btnPermission.startsWith(",")) {
					btnPermission = btnPermission.substring(1, btnPermission.length());
				}
				if (permissionEntity.getPermissionId() != null) {
					// 删除角色权限关联
					this.permissionDao
							.executeSql(
									" DELETE FROM SYS_ROLE_PERMISSION RP WHERE RP.PERMISSIONID IN (SELECT PERMISSIONID FROM SYS_PERMISSION P WHERE SUBSTR(P.PERMISSIONNO,INSTR(P.PERMISSIONNO,'_',1,1)+1,LENGTH(P.PERMISSIONNO)) NOT IN("
											+ btnPermission + ") AND P.PERMISSIONTYPE=1 AND P.PARENTPERMISSIONID='"
											+ permissionEntity.getPermissionId() + "' )",
									new HashMap<String, Object>());
					// 删除权限
					this.permissionDao.executeSql(
							" DELETE FROM SYS_PERMISSION P WHERE SUBSTR(P.PERMISSIONNO,INSTR(P.PERMISSIONNO,'_',1,1)+1,LENGTH(P.PERMISSIONNO)) NOT IN("
									+ btnPermission + ") AND P.PERMISSIONTYPE=1 AND P.PARENTPERMISSIONID='"
									+ permissionEntity.getPermissionId() + "'",
							new HashMap<String, Object>());
					for (String btn : btnPermission.split(",")) {
						if (btn != null && !"".equals(btn.replaceAll(" ", ""))) {
							Long orderNo = queryOrderNo(systemCode);// 验证是否新增
							Long count = this.permissionDao
									.queryCountBySql(
											"SELECT COUNT(1) FROM SYS_PERMISSION P WHERE SUBSTR(P.PERMISSIONNO,INSTR(P.PERMISSIONNO,'_',1,1)+1,LENGTH(P.PERMISSIONNO))="
													+ btn + " AND P.PARENTPERMISSIONID='"
													+ permissionEntity.getPermissionId() + "' AND P.PERMISSIONTYPE=1 ",
											new HashMap<String, Object>());
							if (count != null && count.intValue() < 1) {
								// 数据插入
								this.permissionDao.executeSql(
										" INSERT INTO SYS_PERMISSION(PERMISSIONID,PARENTPERMISSIONID,PERMISSIONNAME,PERMISSIONNO,ORDERNO,ENABLED,SYSTEMCODE,PERMISSIONTYPE) SELECT SYS_GUID(),'"
												+ parentPermissionId + "',D.DATA1,'" + permissionNo + "_'||" + btn + ","
												+ orderNo + "," + permissionEntity.getEnabled() + ",'" + systemCode
												+ "',1 FROM SYS_DICT_TREE_DATA D WHERE D.PARENTNODEID='S000009' AND CODE="
												+ btn,
										new HashMap<String, Object>());
							}
						}
					}
				} else {
					for (String btn : btnPermission.split(",")) {
						if (btn != null && !"".equals(btn.replaceAll(" ", ""))) {
							Long orderNo = queryOrderNo(systemCode);
							this.permissionDao.executeSql(
									" INSERT INTO SYS_PERMISSION(PERMISSIONID,PARENTPERMISSIONID,PERMISSIONNAME,PERMISSIONNO,ORDERNO,ENABLED,SYSTEMCODE,PERMISSIONTYPE) SELECT SYS_GUID(),'"
											+ parentPermissionId + "',D.DATA1,'" + permissionNo + "_'||" + btn + ","
											+ orderNo + "," + permissionEntity.getEnabled() + ",'" + systemCode
											+ "',1 FROM SYS_DICT_TREE_DATA D WHERE D.PARENTNODEID='S000009' AND CODE="
											+ btn,
									new HashMap<String, Object>());
						}
					}
				}

			} else {
				params = new HashMap<String, Object>();
				params.put("permissionId", permissionEntity.getPermissionId());
				this.permissionDao.executeSql(
						" DELETE FROM SYS_PERMISSION P WHERE P.PARENTPERMISSIONID=:permissionId AND P.PERMISSIONTYPE=1 ",
						params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Map<String, Object>> findSystemDefine() {
		String querySql = "SELECT SYSTEMCODE ID,SYSTEMNAME NAME,0 PARENTID FROM SYS_SYSTEM_DEFINE";
		List<Map<String, Object>> systemList = systemDefineDao.queryListBySql(querySql, new HashMap<String, Object>());
		for (Map<String, Object> systemDefineEntity : systemList) {
			systemDefineEntity.put("parentId", "0");
			systemDefineEntity.put("icon", "/up_systemweb/image/systemList.png");
		}
		return systemList;
	}

	@Override
	public void delPermission(String permissionIds) {
		Map<String, Object> params = new HashMap<String, Object>();
		// TODO 删除菜单对应权限关系
		permissionDao.executeSql(
				"UPDATE SYS_MENU S SET S.PERMISSIONNO=NULL WHERE S.PERMISSIONNO IN(SELECT T.PERMISSIONNO FROM SYS_PERMISSION T CONNECT BY PRIOR T.PERMISSIONID=T.PARENTPERMISSIONID START WITH T.PERMISSIONID IN("
						+ permissionIds + "))",
				params);
		// TODO 删除角色权限关系
		permissionDao.executeSql(
				"DELETE FROM SYS_ROLE_PERMISSION P WHERE P.PERMISSIONID IN(SELECT T.PERMISSIONID FROM SYS_PERMISSION T CONNECT BY PRIOR T.PERMISSIONID=T.PARENTPERMISSIONID START WITH T.PERMISSIONID IN("
						+ permissionIds + "))",
				params);
		// TODO 删除权限
		permissionDao.executeSql(
				"DELETE FROM SYS_PERMISSION P WHERE P.PERMISSIONID IN(SELECT T.PERMISSIONID FROM SYS_PERMISSION T CONNECT BY PRIOR T.PERMISSIONID=T.PARENTPERMISSIONID START WITH T.PERMISSIONID IN("
						+ permissionIds + "))",
				params);
	}

	@Override
	public List<Map<String, Object>> findOne(String permissionId) {
		String queryStr = "SELECT P.PERMISSIONID,P.PARENTPERMISSIONID,P.PERMISSIONNAME,P.PERMISSIONNO,P.ORDERNO,P.ENABLED,P.SYSTEMCODE,P.PERMISSIONTYPE,M.MENUID FROM SYS_PERMISSION P LEFT JOIN SYS_MENU M ON P.PERMISSIONNO=M.PERMISSIONNO WHERE P.PERMISSIONID=:permissionId AND ROWNUM<2";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("permissionId", permissionId);
		return this.permissionDao.queryListBySql(queryStr, params);
	}

	@Override
	public Map<String, Object> findPermissionChild(String parentId) {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> permissionChilds = new HashMap<String, Object>();
		params.put("parentPermissionId", parentId);
		String queryStr = " SELECT P.PERMISSIONID,(SELECT COUNT(1) FROM SYS_PERMISSION P1 WHERE P1.PARENTPERMISSIONID=P.PERMISSIONID) CHILDNUM,P.PERMISSIONTYPE,P.PERMISSIONNO,SUBSTR(P.PERMISSIONNO,INSTR(P.PERMISSIONNO,'_',-1,1)+1,LENGTH(P.PERMISSIONNO)) CODE,P.PERMISSIONNAME,P.ORDERNO,P.ENABLED,P.SYSTEMCODE,S.SYSTEMNAME FROM SYS_PERMISSION P LEFT JOIN SYS_SYSTEM_DEFINE S ON P.SYSTEMCODE=S.SYSTEMCODE WHERE P.PARENTPERMISSIONID=:parentPermissionId ORDER BY ORDERNO";
		List<Map<String, Object>> permissionList = this.permissionDao.queryListBySql(queryStr, 0, 0, params);
		for (Map<String, Object> listMap : permissionList) {
			listMap.put("parentId", listMap.get("parentid"));
			Boolean isParent = false;
			if (listMap.get("childnum") != null && Integer.parseInt(listMap.get("childnum").toString()) > 0) {
				isParent = true;
			}
			listMap.put("isParent", isParent);
			listMap.put("open", false);
			listMap.remove("parentid");
		}
		permissionChilds.put("rows", permissionList);
		return permissionChilds;
	}

	@Override
	public void savePermissionToMenu(String permissionId, String menuIds) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("permissionId", permissionId);
		permissionDao.executeSql(
				"UPDATE SYS_MENU M SET M.PERMISSIONNO=(SELECT PERMISSIONNO FROM SYS_PERMISSION P WHERE P.PERMISSIONID=:permissionId) WHERE M.MENUID IN("
						+ menuIds + ")",
				params);
	}

	public List<Map<String, Object>> findPermissionTree(String systemCode, String permissionId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("systemCode", systemCode);
		String queryStr = "SELECT T.PERMISSIONID ID,T.PARENTPERMISSIONID PARENTID,T.PERMISSIONNAME NAME,PERMISSIONNO FROM SYS_PERMISSION T WHERE T.ENABLED=1 AND T.PERMISSIONTYPE='0' AND T.SYSTEMCODE=:systemCode ";
		if (permissionId != null && !"".equals(permissionId)) {
			params.put("permissionId", permissionId);
			queryStr += " AND T.PERMISSIONID NOT IN(SELECT PERMISSIONID FROM SYS_PERMISSION SP CONNECT BY PRIOR SP.PERMISSIONID = SP.PARENTPERMISSIONID START WITH SP.PERMISSIONID=:permissionId ) ";
		}
		queryStr += " ORDER BY T.ORDERNO ";
		List<Map<String, Object>> permissionList = this.permissionDao.queryListBySql(queryStr, params);
		if (permissionList != null && permissionList.size() > 0) {
			for (Map<String, Object> map : permissionList) {
				map.put("parentId", map.get("parentid"));
				map.remove("parentid");
				map.put("icon", "../image/permissionList.png");

			}
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", 0);
			map.put("name", "当前子系统无上级权限选项");
			map.put("id", "-1");
			// permissionList.add(map);
		}

		return permissionList;
	}

	public List<Map<String, Object>> findBtnPermission(String btnPermission) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("btnPermission", btnPermission);
		String queryStr = " SELECT T.CODE,T.DATA1  FROM SYS_DICT_TREE_DATA T WHERE T.PARENTNODEID=:btnPermission ";
		return this.permissionDao.queryListBySql(queryStr, params);
	}

	public List<Map<String, Object>> findMenuTree(String systemCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("systemCode", systemCode);
		String queryStr = "SELECT M.MENUID ID,MENUNAME NAME,PARENTMENUID PARENTID,'..'||IMAGEPATH ICON FROM SYS_MENU M WHERE M.ENABLED=1 AND M.SYSTEMCODE=:systemCode ORDER BY M.ORDERNO";
		List<Map<String, Object>> permissionList = this.permissionDao.queryListBySql(queryStr, params);
		if (permissionList != null && permissionList.size() > 0) {
			for (Map<String, Object> map : permissionList) {
				map.put("parentId", map.get("parentid"));
				map.remove("parentid");
			}
		}
		return permissionList;
	}

	public Long queryOrderNo(String systemCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("systemCode", systemCode);
		String queryStr = "SELECT NVL(MAX(ORDERNO),0)+1 ORDERNO FROM SYS_PERMISSION P WHERE P.SYSTEMCODE=:systemCode";
		return this.permissionDao.queryCountBySql(queryStr, params);
	}

	public Boolean validExists(String systemCode, String field, String value, String permissionId) {
		Boolean returnStr = true;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("systemCode", systemCode);
		StringBuffer queryStr = new StringBuffer("SELECT COUNT(1) FROM SYS_PERMISSION P WHERE P." + field + "='" + value
				+ "' AND P.SYSTEMCODE=:systemCode");
		if (permissionId != null && !"".equals(permissionId)) {
			queryStr.append(" AND P.PERMISSIONID!=:permissionId");
			params.put("permissionId", permissionId);
		}
		if (this.permissionDao.queryCountBySql(queryStr.toString(), params) > 0) {
			returnStr = false;
		}
		return returnStr;
	}

	@Override
	public Map<String, Integer> queryMenuBtnPermission(String permissionNo, String userId, String sysCode) {
		StringBuilder sql = new StringBuilder();
		sql.append("select permissionno, max(isok) as isOk");
		sql.append("  from (select p.permissionno,");
		sql.append("               case");
		sql.append("                 when ?1 = ru.userid then");
		sql.append("                  1");
		sql.append("                 else");
		sql.append("                  0");
		sql.append("               end as isOk");
		sql.append("          from sys_permission p");
		sql.append("          left join sys_role_permission rp");
		sql.append("            on p.permissionid = rp.permissionid");
		sql.append("          left join sys_role r");
		sql.append("            on rp.roleid = r.roleid");
		sql.append("          left join sys_role_user ru");
		sql.append("            on r.roleid = ru.roleid");
		sql.append("         where exists (select 1");
		sql.append("                  from sys_permission pe");
		sql.append("                 where pe.permissionno = ?2");
		sql.append("                   and pe.systemcode = ?3");
		sql.append("                   and p.parentpermissionid = pe.permissionid))");
		sql.append(" group by permissionno");
		List<Map<String, Object>> menuBtnPerms = permissionDao.queryListBySql(sql.toString(),
				new Object[] { userId, permissionNo, sysCode });
		Map<String, Integer> auth = new HashMap<String, Integer>();
		String permission = null;
		String[] pcodes = null;
		for (Map<String, Object> menuBtnPerm : menuBtnPerms) {
			permission = (String) menuBtnPerm.get("permissionno");
			// 权限编码不为空、“_”符号下标大于-1、子权限编码不为空
			if (StringUtils.isNoneBlank(permission) && (pcodes = permission.split("_")).length > 1) {
				auth.put(pcodes[pcodes.length - 1].toLowerCase(), ((BigDecimal) menuBtnPerm.get("isok")).intValue());
			}
		}
		return auth;
	}

	@Override
	public JSONObject findPermissionForMenu(String systemCode, String permissionId) {
		Map<String, Object> params = new HashMap<String, Object>();
		JSONObject treeList = new JSONObject();
		params.put("systemCode", systemCode);
		params.put("permissionId", permissionId);
		String fromSQL = "SELECT M.MENUID ID, M.PARENTMENUID PARENTID, M.MENUNAME NAME FROM SYS_MENU M WHERE M.SYSTEMCODE=:systemCode\n"
				+ " MINUS\n"
				+ " SELECT M.MENUID ID, M.PARENTMENUID PARENTID, M.MENUNAME NAME FROM SYS_MENU M, SYS_PERMISSION P\n"
				+ " WHERE M.PERMISSIONNO = P.PERMISSIONNO AND P.PERMISSIONID =:permissionId";
		List<Map<String, Object>> fromList = this.permissionDao.queryListBySql(fromSQL, 0, 0, params);
		for (Map<String, Object> fromMap : fromList) {
			fromMap.put("parentId", fromMap.get("parentid"));
			fromMap.put("oldParentId", fromMap.get("parentid"));
			fromMap.remove("parentid");
		}
		params.remove("systemCode");
		String toSQL = " SELECT M.MENUID ID, M.PARENTMENUID PARENTID, M.MENUNAME NAME FROM SYS_MENU M, SYS_PERMISSION P\n"
				+ " WHERE M.PERMISSIONNO = P.PERMISSIONNO AND P.PERMISSIONID =:permissionId";
		List<Map<String, Object>> toList = this.permissionDao.queryListBySql(toSQL, 0, 0, params);
		for (Map<String, Object> toMap : toList) {
			toMap.put("parentId", toMap.get("parentid"));
			toMap.put("oldParentId", toMap.get("parentid"));
			toMap.remove("parentid");
		}
		treeList.put("fromList", fromList);
		treeList.put("toList", toList);
		return treeList;
	}

	@Override
	public List<Map<String, Object>> findByParent(String parentPermissionId) {
		String queryStr = " SELECT LISTAGG(SUBSTR(P.PERMISSIONNO,INSTR(P.PERMISSIONNO,'_',-1,1)+1,LENGTH(P.PERMISSIONNO)), ',') WITHIN GROUP(ORDER BY SUBSTR(P.PERMISSIONNO,INSTR(P.PERMISSIONNO,'_',-1,1)+1,LENGTH(P.PERMISSIONNO)))  PERMISSIONNO FROM SYS_PERMISSION P  WHERE P.PARENTPERMISSIONID=:parentPermissionId AND PERMISSIONTYPE=1";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentPermissionId", parentPermissionId);
		return this.permissionDao.queryListBySql(queryStr, params);
	}

	public List<Map<String, Object>> findPermissionView(String permissionId) {
		String queryStr = "  SELECT (SELECT LISTAGG(SUBSTR(P2.PERMISSIONNO,\n"
				+ "                              INSTR(P2.PERMISSIONNO, '_', -1, 1) + 1,\n"
				+ "                              LENGTH(P2.PERMISSIONNO)),\n"
				+ "                       ',') WITHIN GROUP(ORDER BY SUBSTR(P2.PERMISSIONNO, INSTR(P2.PERMISSIONNO, '_', -1, 1) + 1, LENGTH(P2.PERMISSIONNO))) PERMISSIONNO\n"
				+ "          FROM SYS_PERMISSION P2\n" + "         WHERE P2.PARENTPERMISSIONID = P.PERMISSIONID\n"
				+ "           AND PERMISSIONTYPE = 1) BTNPERMISSION,\n" + "       M.MENUNAME,\n"
				+ "       S.SYSTEMNAME,\n" + "       P1.PERMISSIONNAME PNAME,\n" + "       P.PERMISSIONNAME,\n"
				+ "       DECODE(P.PERMISSIONTYPE, 0, '功能权限', '按钮权限') PERMISSIONTYPE,\n"
				+ "       DECODE(P.ENABLED, 0, '否', '是') ENABLED,\n" + "       P.PERMISSIONNO,P.ORDERNO\n"
				+ "  FROM SYS_PERMISSION P\n" + "  LEFT JOIN SYS_PERMISSION P1\n"
				+ "    ON P.PARENTPERMISSIONID = P1.PERMISSIONID\n" + "  LEFT JOIN SYS_MENU M\n"
				+ "    ON P.PERMISSIONNO = M.PERMISSIONNO\n" + "  LEFT JOIN SYS_SYSTEM_DEFINE S\n"
				+ "    ON P.SYSTEMCODE = S.SYSTEMCODE\n" + "  WHERE P.PERMISSIONID =:permissionId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("permissionId", permissionId);
		return this.permissionDao.queryListBySql(queryStr, params);
	}
}
