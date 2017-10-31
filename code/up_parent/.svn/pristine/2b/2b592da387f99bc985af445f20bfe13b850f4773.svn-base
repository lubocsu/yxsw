/**
 * Project Name:plate
 * File Name:   ILoginDao.java
 * Package Name:com.upsoft.dao.login
 * Created on:  2015年1月20日 下午5:08:10
 * author:      JiangDi
 * Copyright(c) 重庆扬讯软件技术有限公司  All Rights Reserved.
 * http://www.upsoft.com.cn
*/

package com.upsoft.systemweb.dao;

import java.io.Serializable;

import com.upsoft.system.dao.IBaseDao;
import com.upsoft.system.entity.SysUser;

/**
 * ClassName:ILoginDao <br/>
 * Description:简单描述该对象（可选）<br/>  
 * Date:     2015年1月20日 下午5:08:10 <br/>
 * @author   JiangDi
 * @version  v1.0
 * @JDK 1.6
 */
public interface ILoginDao extends IBaseDao<SysUser, Serializable>{

	/**
	 * 根据用户登陆编码获取用户信息
	 * @date 2015年1月22日 下午2:23:58
	 * @author 吴炫
	 * @param loginId
	 * @return
	 */
	public SysUser findByLoginId(String loginId);
}

