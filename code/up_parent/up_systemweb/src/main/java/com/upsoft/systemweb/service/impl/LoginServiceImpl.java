package com.upsoft.systemweb.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.constant.CommonConstant;
import com.upsoft.system.constant.DictConstant;
import com.upsoft.system.constant.LoginTipMsgConstants;
import com.upsoft.system.entity.SysOrgEntity;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.service.BaseServiceImpl;
import com.upsoft.system.support.cache.UpSysCache;
import com.upsoft.system.util.DateUtil;
import com.upsoft.systemweb.constant.SystemWebConstant;
import com.upsoft.systemweb.dao.ILoginDao;
import com.upsoft.systemweb.dao.IOrgDao;
import com.upsoft.systemweb.service.LoginService;
import com.upsoft.systemweb.service.OrgService;

@Service("loginService")
public class LoginServiceImpl extends BaseServiceImpl  implements LoginService{
	
	Logger logger = Logger.getLogger(LoginServiceImpl.class);

	@Autowired 
	private ILoginDao loginDao;
	@Autowired
	IOrgDao orgDao;
	@Autowired
	OrgService orgService;
	@Autowired
	Md5PasswordEncoder passwordEncoder;
	
	@Override
	public SysUser queryUserByLoginId(String loginId) {
		return loginDao.findByLoginId(loginId);
	}

	@Override
	public WSLoginInfoBean login(String loginId, String password,String systemCode) {
		// FIXME 必须，验证当前loginId用户是否已经登录过。若是，这删除旧的tokenId，生成新的tokenId,在登录拦截器中同时判断服务器中是否还有有原来的tokenId，若无提示认证失效 2017.04.12 by huyi
		logger.debug("验证前："+UpSysCache.getCache2());
		checkLogined(loginId);
		WSLoginInfoBean bean = new WSLoginInfoBean();
		SysUser user = loginDao.findByLoginId(loginId);
		String msg = "";
		if (user==null){
			bean.setMsg(LoginTipMsgConstants.USER_NOT_EXCITE);
			return bean;
		}
		if(!StringUtils.equals(user.getIsSysUser(), SystemWebConstant.IS_SYSTEM_USER.YES.getKey())){
			bean.setMsg(LoginTipMsgConstants.NOT_SYSTEM_USER);
			return bean;
		}
		msg = validLogin(user, password);
		if (!msg.equals("")){
			bean.setMsg(msg);
			return bean;
		}
		//机构信息
		SysOrgEntity sysOrg = orgDao.findByOrgId(user.getOrgId());
//		bean.setOrgCode(sysOrg.getOrgCode());
//		bean.setOrgName(sysOrg.getOrgName());
//		bean.setOrgTypeId(sysOrg.getOrgTypeId());
		
		//获取登录用户所属厂站、公司或集团级别的机构信息
		SysOrgEntity sorg = orgService.queryUperCsSysOrgByOrgId(user.getOrgId(), CommonConstant.orgType.FACTORY.getKey());
		sorg = sorg == null ? orgService.queryUperCsSysOrgByOrgId(user.getOrgId(), CommonConstant.orgType.COMPANY.getKey()) : sorg;
		sorg = sorg == null ? orgService.queryUperCsSysOrgByOrgId(user.getOrgId(), CommonConstant.orgType.GROUP.getKey()) : sorg;
		if(null != sorg){
			bean.setCsOrgTypeId(sorg.getOrgTypeId());
			bean.setCsOrgId(sorg.getOrgId());
			bean.setCsOrgCode(sorg.getOrgCode());
			bean.setCsOrgName(sorg.getOrgName());
		}
		String orgLinkSql = "SELECT T.ORGCODE, T.ORGNAME,D.CODE,D.DATA1 FROM SYS_ORG T  LEFT JOIN SYS_DICT_TREE_DATA D ON D.CODE= T.ORGTYPEID AND D.PARENTNODEID = '"+DictConstant.ORG_TYPE.getValue()+"' WHERE 1 = 1 START WITH T.ORGID = '"+sysOrg.getOrgId()+"' CONNECT BY PRIOR T.PARENTORGID = T.ORGID ORDER BY T.ORGTYPEID ASC";
		List<Map<String,Object>> orgLinkMap = orgDao.queryListBySql(orgLinkSql, new HashMap<String,Object>());
		bean.setOrgLinkMap(orgLinkMap);
		
		bean.setUser(user);
		String loginDate = DateUtil.getFormatInstance(DateUtil.DATE_FULL_FORMAT).format(new Date());
		bean.setRefreshTime(loginDate);
		String token = generateToken(bean);
		bean.setTokenId(token);
		UpSysCache.setCache(token, bean);
		UpSysCache.setCache2(loginId, token); // add at 2017.04.12 by huyi
		logger.debug("验证后："+UpSysCache.getCache2());
		return bean;
	}

	private void checkLogined(String loginId) {
		Object o = UpSysCache.getCache2(loginId);
		if((null!=o)&&StringUtils.isNotBlank(o+"")&&!StringUtils.equals((o+"").trim(), "null")){
			logger.error("已有tokenId"+UpSysCache.getCache2(loginId));
			UpSysCache.removeCache((String)UpSysCache.getCache2(loginId));
		}
	}

	@Override
	public String generateToken(WSLoginInfoBean source) {
		return new String(new Base64().encode((source.getUser().getUserId()+"|"+source.getRefreshTime()).getBytes()));
	}

	@Override
	public WSLoginInfoBean parseToken(String token) {
		String str = new String(new Base64().decode(token));
		WSLoginInfoBean bean = new WSLoginInfoBean();
		String[] strs = str.split("\\|");
		bean.getUser().setUserId(strs[0]);
		bean.setRefreshTime(strs[1]);
		return bean;
	}
	
	@Override
	public Boolean logout(String token) {
		try {
			UpSysCache.removeCache(token);
			Map<String,Object> cache2 = UpSysCache.getCache2();
			for (String key : cache2.keySet()) {
				if(StringUtils.equals(token, cache2.get(key).toString())){
					cache2.remove(key);
					break;
				}
			}
			logger.debug("-----登出后-----");
			new UpSysCache().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 验证用户登陆
	 * @date 2015年1月23日 上午10:06:51
	 * @author 吴炫
	 * @param user
	 * @param password
	 * @return 返回验证信息（null:验证通过，not null：验证失败的提示信息）
	 */
	private String validLogin(SysUser user, String password) {
		String message = "";
		if(user == null||!passwordEncoder.isPasswordValid(user.getPassword(), password, user.getLoginId())){
			message = LoginTipMsgConstants.ERROR_ACCOUNT_OR_PASSWORD;
		}else if(1 != user.getStatus()){
			message = LoginTipMsgConstants.USER_INVALID;
		}
		return message;
	}

}
