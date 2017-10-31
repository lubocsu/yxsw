package com.upsoft.systemweb.service;

import com.upsoft.system.bean.WSLoginInfoBean;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.service.BaseService;

/**
* Copyright (c) 2015,重庆扬讯软件技术有限公司<br>
* All rights reserved.<br>
*
* 文件名称：ILoginService.java<br>
* 摘要：登陆服务<br>
* -------------------------------------------------------<br>
* 当前版本：1.1.1<br>
* 作者：吴炫<br>
* 完成日期：2015年1月22日<br>
* -------------------------------------------------------<br>
* 取代版本：1.1.0<br>
* 原作者：吴炫<br>
* 完成日期：2015年1月22日<br>
 */
public interface LoginService extends BaseService{
	/**
	 * 根据用户
	 * @date 2015年1月22日 上午9:54:45
	 * @author 吴炫
	 * @param loginId
	 * @param password
	 */
	public SysUser queryUserByLoginId(String loginId);
	
	/**
	 * 登录方法
	 * @date 2015年1月22日 下午2:45:11
	 * @author 蒋迪
	 * @param loginId		登录账号
	 * @param password		登录密码
	 * @param systemCode	系统编码
	 * @return WSLoginReturnBasicInfoBean中msg字段为空，登录成功
	 */
	public WSLoginInfoBean login(String loginId, String password, String systemCode);
	
	/**
	 * 生成token
	 * @date 2015年1月22日 下午1:50:02
	 * @author 蒋迪
	 * @param src
	 * @return 
	 */
	public String generateToken(WSLoginInfoBean src);
	
	/**
	 * 解析token
	 * @date 2015年1月22日 下午1:52:03
	 * @author 蒋迪
	 * @param token
	 * @return 
	 */
	public WSLoginInfoBean parseToken(String token);
	
	/**
	 * 用户注销
	 * @date 2015年1月28日 上午10:03:26
	 * @author 蒋迪
	 * @param token
	 * @return true=注销成功，false=注销失败
	 */
	public Boolean logout(String token);
	
}
