package com.upsoft.systemweb.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.upsoft.login.listener.ConfigListener;
import com.upsoft.system.bean.ExcelHeaderBean;
import com.upsoft.system.bean.ExportExcelBean;
import com.upsoft.system.bean.PageBean;
import com.upsoft.system.constant.CommonConstant;
import com.upsoft.system.constant.DictConstant;
import com.upsoft.system.entity.SysRoleUser;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.system.util.MapUtil;
import com.upsoft.systemweb.dao.UserDAO;
import com.upsoft.systemweb.service.ExportExcelDataSource;
import com.upsoft.systemweb.service.PermissionDataService;
import com.upsoft.systemweb.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService,
		ExportExcelDataSource {

	private enum QueryType {
		LIST, COUNT
	}

	@Autowired
	private UserDAO userDAO;

	@Autowired(required = false)
	@Qualifier("passwordEncoder")
	private Md5PasswordEncoder passwordEncoder;
	
	@Autowired
	private PermissionDataService permissionDataService;

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getUserList(Map<String, Object> pars,
			int start, int length) {

		Object[] obj = this.getQuerySentence(pars, QueryType.LIST);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = userDAO.queryListBySql(obj[0].toString(), start, length,
				(Map<String, Object>) obj[1]);

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public long getUserListCount(Map<String, Object> pars) {
		Object[] obj = this.getQuerySentence(pars, QueryType.COUNT);
		long count = userDAO.queryCountBySql(obj[0].toString(),
				(Map<String, Object>) obj[1]);
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getUserListAndCount(Map<String, Object> pars,
			PageBean pageBean) {
		Object[] obj = this.getQuerySentence(pars, QueryType.LIST);
		Map<String, Object> userListAndCount = new HashMap<String, Object>();
		userListAndCount = userDAO.queryPaginationListBySql(obj[0].toString(),
				(Map<String, Object>) obj[1], pageBean);

		return userListAndCount;
	}

	/**
	 * 私有方法：公共查询语句拼装
	 * 
	 * @date 2015年1月22日 下午5:10:59
	 * @author TW
	 * @param pars
	 *            查询条件
	 * @param type
	 *            查询类型
	 * @return
	 */
	private Object[] getQuerySentence(Map<String, Object> pars, QueryType type) {

		StringBuffer sql = new StringBuffer();
		Map<String, Object> parsCon = new HashMap<String, Object>();

		switch (type) {
		case LIST:
			sql.append("select o.orderno orgno,sdn.data1 usertypes,sdt.data1 occuptions,o.orgName orgName,u.*");
			break;
		case COUNT:
			sql.append("select count(1)");
			break;

		default:
			sql.append("select count(1)");
			break;
		}

		sql.append(" from (select orderno,orgId,parentOrgId,orgName as orgName from sys_org");
		if (pars.containsKey("parentOrgId") && pars.get("parentOrgId") != null
				&& StringUtils.isNotBlank(pars.get("parentOrgId").toString())) {
			sql.append(" start with orgid=:parentOrgIdCon connect by prior orgid = parentorgid");
			parsCon.put("parentOrgIdCon", pars.get("parentOrgId"));
		}
		sql.append(") o,sys_user u ");
		sql.append(" left join sys_dict_tree_data sdn on sdn.code=u.usertype and sdn.parentnodeid='"+DictConstant.USER_TYPE.getValue()+"' ");
		sql.append(" left join sys_dict_tree_data sdt on  sdt.code=u.occupation and sdt.parentnodeid='"+DictConstant.USER_WORK_TYPE.getValue()+"' ");
		sql.append(" where o.orgId=u.orgId");

		if (pars.containsKey("userName") && pars.get("userName") != null
				&& StringUtils.isNotBlank(pars.get("userName").toString())) {
			sql.append(" and u.userName like :userNameCon");
			parsCon.put("userNameCon", "%" + pars.get("userName") + "%");
		}
		if (pars.containsKey("loginId") && pars.get("loginId") != null
				&& StringUtils.isNotBlank(pars.get("loginId").toString())) {
			sql.append(" and u.loginId like :loginIdCon");
			parsCon.put("loginIdCon", "%" + pars.get("loginId") + "%");
		}
		
		if (pars.containsKey("occupation") && pars.get("occupation") != null
				&& StringUtils.isNotBlank(pars.get("occupation").toString())) {
			sql.append(" and u.occupation = :occupationCon");
			parsCon.put("occupationCon", pars.get("occupation"));
		}
		
		if (pars.containsKey("userId") && pars.get("userId") != null
				&& StringUtils.isNotBlank(pars.get("userId").toString())) {
			sql.append(" and u.userId =:userIdCon");
			parsCon.put("userIdCon", pars.get("userId"));
		}
		if (pars.containsKey("orgId") && pars.get("orgId") != null
				&& StringUtils.isNotBlank(pars.get("orgId").toString())) {
			sql.append(" and u.orgId =:orgIdCon");
			parsCon.put("orgIdCon", pars.get("orgId"));
		}
//		if (pars.containsKey("status") && pars.get("status") != null
//				&& StringUtils.isNotBlank(pars.get("status").toString())) {
//			sql.append(" and u.status =:statusCon");
//			parsCon.put("statusCon", pars.get("status"));
//		}
		if (pars.containsKey("status") && pars.get("status") != null) {
			sql.append(" and u.status in (");
			String status = pars.get("status").toString();
			sql.append(status);
			sql.append(")");
		}

		// sql.append(" order by u.loginId");

		return new Object[] { sql, parsCon };

	}

	@Override
	public void saveUser(SysUser user) {

		if (StringUtils.isBlank(user.getUserId())
				|| StringUtils.isBlank(user.getPassword())) {
			user.setPassword(passwordEncoder.encodePassword("000000",
					user.getLoginId()));
		} else {
			String oldPassword = super.findOne(SysUser.class, user.getUserId())
					.getPassword();
			if (!user.getPassword().equals(oldPassword)) {
				user.setPassword(passwordEncoder.encodePassword(
						user.getPassword(), user.getLoginId()));
			}
		}
		userDAO.save(user);
	}

	@Override
	public void deleteUser(String userId) {
		// userDAO.delete(userId);
		SysUser user = userDAO.findOne(userId);
		user.setStatus(Integer.valueOf(CommonConstant.STATUS_DELETE));
		userDAO.save(user);

	}

	@Override
	public void deleteSplitUsers(String userIds) {
		String[] userIdArr = userIds.split(",");

		for (String userId : userIdArr) {
			if (StringUtils.isNotBlank(userId)) {
				// delete(SysUser.class, userId);
				SysUser user = userDAO.findOne(userId);
				user.setStatus(Integer.valueOf(CommonConstant.STATUS_DELETE));
				userDAO.save(user);
			}
		}
	}

	/**
	 * 用户修改密码时，验证输入的密码经MD5加密后，与原密码是否一致
	 * 
	 * @date 2015年2月2日 下午4:19:13
	 * @author 屈锐华
	 * @param loginId
	 * @param oldPwd
	 * @param inputPwd
	 * @return
	 */
	@Override
	public boolean validUserPwd(String loginId, String oldPwd, String inputPwd) {
		boolean flag = false;
		if (StringUtils.isNotBlank(loginId) && StringUtils.isNotBlank(oldPwd)
				&& StringUtils.isNotBlank(inputPwd)) {
			flag = passwordEncoder.isPasswordValid(oldPwd, inputPwd, loginId);
		}
		return flag;
			// 用Spring的MD5加密工具进行加密
//			String md5InputPwd = passwordEncoder.encodePassword(inputPwd,
//					loginId);
//
//			if (!oldPwd.equals(md5InputPwd)) {
//				flag = false;
//			}
//		} else {
//			flag = false;
//		}

//		return flag;
	}

	@Override
	public Integer getMaxOrderNo() {

		Integer maxOrderNo = 0;

		String sql = "select nvl(max(t.orderno),0) as orderno from SYS_USER t order by t.orderno desc";
		Map<String, Object> pars = new HashMap<String, Object>();

		List<Map<String, Object>> rlt = userDAO.queryListBySql(sql, pars);
		if (rlt != null && rlt.size() > 0) {
			maxOrderNo = Integer.valueOf(rlt.get(0).get("orderno").toString());
		}
		return maxOrderNo;
	}

	@Override
	public List<Map<String, Object>> getSystemRole() {
		String sql = "select ssd.systemcode as id, '0' as parentId, ssd.systemname as name, 'false' as drag, 'null' as oldParentId,'../image/systemList.png' icon from sys_system_define ssd union (select sr.roleid as id, ssd.systemcode as parentId, sr.rolename as name, 'true' as drag, ssd.systemcode as oldParentId,'../image/roleList.png' icon from sys_system_define ssd, sys_role sr where ssd.systemcode = sr.systemcode)";
		Map<String, Object> params = new HashMap<String, Object>();
		List<Map<String, Object>> systemRoleListTree = userDAO.queryListBySql(
				sql, params);
		return systemRoleListTree;
	}

	@Override
	public List<Map<String, Object>> getUserRoleByUserId(String userId) {
		String sql = "select ru.roleid from sys_role_user ru where ru.userid=:userIdCon";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userIdCon", userId);
		List<Map<String, Object>> systemRoleListTree = userDAO.queryListBySql(sql, params);
		return systemRoleListTree;
	}
	
	@Override
	public List<String> getUserRoleByUserId(String userId,boolean onlyId) {
		List<Map<String, Object>> systemRoleListTree = this.getUserRoleByUserId(userId);
		List<String> roleIdList = new ArrayList<String>();
		for (Map<String, Object> map : systemRoleListTree) {
			roleIdList.add(map.get("roleid").toString());
		}
		return roleIdList;
	}
	
	@Override
	public List<Map<String, Object>> getSystemCodeByUserIdInRole(String userId) {
		String sql = "select distinct sr.systemcode from sys_role_user ru join sys_role sr on sr.roleid=ru.roleid  where ru.userid=:userIdCon";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userIdCon", userId);
		List<Map<String, Object>> systemRoleCodeListTree = userDAO.queryListBySql(sql, params);
		return systemRoleCodeListTree;
	}

	@Override
	public void saveUserRole(String userId, String roleIds) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userIdCon", userId);
		userDAO.executeSql(
				"DELETE FROM sys_role_user ru WHERE ru.userid =:userIdCon",
				params);
		String[] roleIdArr = roleIds.split(",");
		for (String roleId : roleIdArr) {
			SysRoleUser roleUser = new SysRoleUser();
			roleUser.setUserId(userId);
			roleUser.setRoleId(roleId);
			if (StringUtils.isNotBlank(roleId)) {
				save(roleUser);
			}
		}
	}

	@Override
	public ExportExcelBean getDataSource(Map<String, Object> params,
			PageBean pageBean) {
		ExportExcelBean excelBean = new ExportExcelBean();
		// 获取excel表数据
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> data = (List<Map<String, Object>>) getUserListAndCount(
				params, pageBean).get("rows");
		// 组装表头（key值与数据源名称保持一致）
		List<ExcelHeaderBean> header = new ArrayList<ExcelHeaderBean>();
		header.add(new ExcelHeaderBean("username", "姓名"));
		header.add(new ExcelHeaderBean("loginid", "用户名"));
		header.add(new ExcelHeaderBean("orgname", "所属机构"));
		header.add(new ExcelHeaderBean("officetel", "办公电话"));

		excelBean.setData(data);
		excelBean.setHeader(header);
		return excelBean;
	}

	@Override
	public List<Map<String, Object>> queryUsersWithPermission(String orgId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgId", orgId);
		String sql ="SELECT * FROM SYS_USER T WHERE T.ORGID IN(SELECT O.ORGID FROM SYS_ORG O CONNECT BY PRIOR O.ORGID = O.PARENTORGID START WITH O.ORGID = :orgId) AND T.STATUS = 1 ORDER BY T.USERTYPE ASC,T.ORDERNO ASC";
		return userDAO.queryListBySql(sql, params);
	}

	@Override
	public void updateUserByOrg(String orgId, int status) {
		String sql = "update sys_user set status = "+status+" where orgid='"+orgId+"'";
		userDAO.executeSql(sql, null);
	}

	@Override
	public void updateUserPassword(String userId){
		SysUser user = userDAO.getOne(userId);
		if (user==null)	return;
		user.setPassword(passwordEncoder.encodePassword("000000", user.getLoginId()));
		userDAO.save(user);
	}

	@Override
	public List<String> getImeiList() {
		List<Map<String,Object>> imeilist = userDAO.queryListBySql("select distinct t.idcard from sys_user t  where t.idcard is not null", new HashMap<String, Object>());
		List<String> list = new ArrayList<String>();
		for (Map<String, Object> l : imeilist) {
			list.add(l.get("idcard")+"");
		}
		return list;
	}


	@Override
	public SysUser findOne(String userId) {

		return userDAO.findOne(userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findOrgUserTree(Map<String, Object> pars) {
		
		String startWith = "O.ORGID";
		if(!MapUtil.hasParam(pars, "parentOrgId")){
			pars.put("parentOrgId", 0);
			startWith = "O.PARENTORGID";
		}
		// 查询可访问用户Id
		String userSql = "SELECT distinct U.USERID ID FROM SYS_USER U WHERE U.STATUS = 1 AND U.ORGID in (SELECT O.ORGID FROM SYS_ORG O CONNECT BY PRIOR O.ORGID = O.PARENTORGID START WITH "+startWith+" = '"+pars.get("parentOrgId")+"')";
		List<String> userIdList = userDAO.queryListResultBySql(userSql, 0,0,  new HashMap<String,Object>());
		// 排除登录人
		if(MapUtil.hasParam(pars, "userId")){
			userIdList.remove(pars.get("userId"));
		}
		if(userIdList.size()>0){
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT distinct OU.ID,OU.PARENTID,OU.NAME,OU.CLICKEXPAND FROM ((SELECT O.ORGID ID,");
			sql.append("       O.PARENTORGID PARENTID,");
			sql.append("       O.ORGNAME NAME,");
			sql.append("       'true' clickExpand");
			sql.append("  FROM SYS_ORG O");
			sql.append(" WHERE O.ENABLED = 1");
			sql.append("CONNECT BY PRIOR O.ORGID = O.PARENTORGID");
			sql.append(" START WITH "+startWith+" = '"+pars.get("parentOrgId")+"')");
			sql.append("UNION");
			sql.append("(SELECT distinct U.USERID ID,");
			sql.append("                U.ORGID PARENTID,");
			sql.append("                U.USERNAME NAME,");
			sql.append("                'false' clickExpand");
			sql.append("  FROM SYS_USER U");
			sql.append(" WHERE U.STATUS = 1");
			sql.append("   AND U.ORGID in (SELECT O.ORGID");
			sql.append("                     FROM SYS_ORG O");
			sql.append("                   CONNECT BY PRIOR O.ORGID = O.PARENTORGID");
			sql.append("                    START WITH "+startWith+" = '"+pars.get("parentOrgId")+"'))) OU START WITH OU.ID IN (:userIdList) CONNECT BY PRIOR OU.PARENTID = OU.ID");
			
			Map<String,Object> params =  new HashMap<String,Object>(); 
			params.put("userIdList", userIdList);
			List<Map<String,Object>> fromList=userDAO.queryListBySql(sql.toString(), 0, 0,params);
			for (Map<String, Object> listMap : fromList) {
				listMap.put("parentId", listMap.get("parentid"));
				listMap.remove("parentid");
				listMap.put("clickExpand", listMap.get("clickexpand"));
				listMap.remove("clickexpand");
				if("true".equals(listMap.get("clickExpand").toString())){
					listMap.put("icon", "../image/company.png");
				}else{
					listMap.put("icon", "../image/person.png");
				}
			}
			return fromList;
		}else{
			return new ArrayList<Map<String,Object>>();
		}
	}
	

	@Override
	public List<Map<String, Object>> findOrgUserTreeInOrgList(List<String> orgIdList) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("orgIdList", orgIdList);
		String fromSQL= " (SELECT O.ORGID ID, O.PARENTORGID PARENTID, O.ORGNAME NAME, 'true' clickExpand FROM SYS_ORG O "
							+ "WHERE O.ENABLED = 1  AND O.ORGID IN(:orgIdList) )" +
		         		" UNION " + 
		         		 " (SELECT distinct U.USERID ID, U.ORGID PARENTID, U.USERNAME NAME, 'false' clickExpand " + 
		               "FROM SYS_USER U WHERE U.STATUS = 1" + 
		               " AND U.ORGID in (:orgIdList))";
			List<Map<String,Object>> fromList=userDAO.queryListBySql(fromSQL, 0, 0,params );
			for (Map<String, Object> listMap : fromList) {
			    listMap.put("parentId", listMap.get("parentid"));
			    listMap.remove("parentid");
			    listMap.put("clickExpand", listMap.get("clickexpand"));
			    listMap.remove("clickexpand");
			    if("true".equals(listMap.get("clickExpand").toString())){
			    	listMap.put("icon", "../image/company.png");
			    }else{
			    	listMap.put("icon", "../image/person.png");
			    }
			}
			
			return fromList;
	}
	
	@Override
	public List<Map<String, Object>> getUserSelectInPermission(String userId) {
		List<String> orgIds = permissionDataService.getPermissionOrgIds(userId);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgIds", orgIds);
		StringBuilder sql = new StringBuilder();
		sql.append("select t.username as key, t.userid as value from sys_user t ");
		sql.append("where t.status = 1 ");
		sql.append("and t.orgid in (:orgIds) ");
		sql.append("order by t.orderno asc ");
		List<Map<String, Object>> results = userDAO.queryListBySql(sql.toString(), params);
		return results;
	}

	@Override
	public List<String> getAdminUsers() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<String> result = new ArrayList<String>();
		// 从配置文件中读取 超级权限用户
		String admins = ConfigListener.CommConfig.getProperty("admin_login_num");
		if(StringUtils.isNotBlank(admins)){
			List<String> adminLoginIds = Arrays.asList(admins.split(","));
			Map<String, Object> parmas = new HashMap<String, Object>();
			parmas.put("loginIds", adminLoginIds);
			String sql = "select t.userid from SYS_USER t where t.loginid in (:loginIds) ";
			list = userDAO.queryListBySql(sql, parmas);
			for (Map<String, Object> map : list) {
				result.add(map.get("userid").toString());
			}
		}
		return result;
	}

}
